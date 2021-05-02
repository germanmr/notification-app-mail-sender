package com.acme.notificacionappmailsender.services.external;

import com.acme.notificacionappmailsender.domain.MessageRequest;

public interface MailClient {

    void sendEmail(MessageRequest messageRequest) throws Exception;
}
