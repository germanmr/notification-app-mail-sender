package com.acme.notificacionappmailsender.services.external;

import com.acme.notificacionappmailsender.domain.MessageRequest;

public interface MailClient {

    boolean sendEmail(MessageRequest messageRequest) throws Exception;
}
