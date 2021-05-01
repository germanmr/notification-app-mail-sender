package com.acme.notificacionappmailsender.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

public class MessageRequest {

    private Long id;
    private UUID uuid;
    private Client client;
    private Publication publication;
    private String error;
    private MessageStates messageState;

    public MessageRequest() {
    }

    public MessageRequest(Client client, Publication publication) {
        this.uuid = UUID.randomUUID();
        this.client = client;
        this.publication = publication;
        this.messageState = MessageStates.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public String getError() {
        return error;
    }

    public MessageStates getMessageState() {
        return messageState;
    }

    public void setMessageState(MessageStates messageState) {
        this.messageState = messageState;
    }

    public void setSuccess() {
        this.messageState = MessageStates.PENDING;
        this.error = null;
    }

    public void setError(String error) {
        this.messageState = MessageStates.ERROR;
        this.error = error;
    }

    public void setBeginProccesing() {
        this.messageState = MessageStates.PROCESSING;
        this.error = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageRequest that = (MessageRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) && Objects.equals(client, that.client) && Objects.equals(publication, that.publication) && Objects.equals(error, that.error) && messageState == that.messageState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, client, publication, error, messageState);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MessageRequest{");
        sb.append("id=").append(id);
        sb.append(", uuid=").append(uuid);
        sb.append(", client=").append(client);
        sb.append(", publication=").append(publication);
        sb.append(", error='").append(error).append('\'');
        sb.append(", messageState=").append(messageState);
        sb.append('}');
        return sb.toString();
    }
}
