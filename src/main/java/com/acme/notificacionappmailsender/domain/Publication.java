package com.acme.notificacionappmailsender.domain;

import javax.persistence.Embeddable;
import java.util.Objects;

public class Publication {

    private String messages;

    public Publication() {
    }

    public Publication(String messages) {
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(messages, that.messages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messages);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Publication{");
        sb.append("messages='").append(messages).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
