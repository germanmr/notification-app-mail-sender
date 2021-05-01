package com.acme.notificacionappmailsender.services.external;

import com.acme.notificacionappmailsender.domain.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MailClientImpl implements MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClientImpl.class);

    @Override
    public boolean sendEmail(MessageRequest messageRequest) throws Exception {
        logger.info("Sending mail with Message Request: {messageRequest}", messageRequest);

        if (messageRequest.getPublication().getMessages().contains("ERROR")) {
            throw new Exception("Error 15975 with mail server on request: {} " + messageRequest);
        } else {
            logger.info("Sending mail with Message Request: {messageRequest}", messageRequest);
            return true;
        }
    }

}
