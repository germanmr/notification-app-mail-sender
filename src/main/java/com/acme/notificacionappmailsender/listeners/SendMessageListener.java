package com.acme.notificacionappmailsender.listeners;

import com.acme.notificacionappmailsender.domain.MessageRequest;
import com.acme.notificacionappmailsender.services.mail.MailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
//@KafkaListener(topics = "${com.acme.notificationappmailsender.mail.topic}", groupId = "group_id")
public class SendMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageListener.class);

    private final MailService mailService;
    private final ObjectMapper objectMapper;
    private final String topic;

    @Autowired
    public SendMessageListener(MailService mailService,
                               ObjectMapper objectMapper,
                               @Value("${com.acme.notificationappmailsender.mail.topic}") String topic) {
        this.mailService = mailService;
        this.objectMapper = objectMapper;
        this.topic = topic;
    }

    //    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    @KafkaListener(topics = "com.acme.notificationapp.mail.topic", groupId = "group_id")
//    @KafkaListener(id = "thing2", topicPartitions =
//            { @TopicPartition(topic = "com.acme.notificationapp.mail.topic", partitions = "0",
//                            partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
//            })
    public void consume(String message) throws Exception {
        logger.info("Received message :" + message);
        if (Strings.isNullOrEmpty(message)) {
            throw new Exception("The body was empty!");
        }
        MessageRequest messageRequest = objectMapper.readValue(message, MessageRequest.class);
        mailService.sendingEmail(messageRequest);
        logger.info("Mail sending process finished wit request: {}", messageRequest);
    }
}
