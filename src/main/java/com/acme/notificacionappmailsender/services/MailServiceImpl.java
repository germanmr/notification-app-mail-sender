package com.acme.notificacionappmailsender.services;

import com.acme.notificacionappmailsender.domain.MessageRequest;
import com.acme.notificacionappmailsender.domain.MessageStates;
import com.acme.notificacionappmailsender.services.external.MailClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    private final MailClient client;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${com.acme.notificationapp.mail.acknowledgement.topic}")
    private String topic;

    public MailServiceImpl(MailClient client, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.client = client;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void sendingEmail(MessageRequest messageRequest) throws JsonProcessingException {
        try {
            logger.info("Sending mail with Message Request: {messageRequest}", messageRequest);

            client.sendEmail(messageRequest);
            messageRequest.setMessageState(MessageStates.SUCCESS);
            String messageRequestSucessfull = objectMapper.writeValueAsString(messageRequest);
            kafkaTemplate.send(topic, messageRequestSucessfull);
        } catch (Exception e) {
            logger.error("Sending mail with Message Request: " + messageRequest + " - throw Error" + e.getMessage());
            messageRequest.setError(e.getMessage());
            messageRequest.setMessageState(MessageStates.ERROR);
            String messageRequestError = objectMapper.writeValueAsString(messageRequest);
            kafkaTemplate.send(topic, messageRequestError);
        }
    }
}
