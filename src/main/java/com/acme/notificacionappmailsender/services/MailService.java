package com.acme.notificacionappmailsender.services;

import com.acme.notificacionappmailsender.domain.MessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MailService {

    void sendingEmail(MessageRequest messageRequest) throws JsonProcessingException;
}
