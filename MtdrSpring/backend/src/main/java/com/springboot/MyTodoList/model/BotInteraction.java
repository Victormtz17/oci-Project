package com.springboot.MyTodoList.model;

import jakarta.persistence.*;

/*
    Tabla BOT_INTERACTIONS particionada por CREATED_AT.
    La clave primaria es compuesta: (INTERACTION_ID, CREATED_AT).
 */
@Entity
@Table(name = "BOT_INTERACTIONS")
public class BotInteraction {

    @EmbeddedId
    private BotInteractionId id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "MESSAGE", columnDefinition = "CLOB")
    private String message;

    @Column(name = "RESPONSE", columnDefinition = "CLOB")
    private String response;

    public BotInteraction() {}

    public BotInteraction(BotInteractionId id, Integer userId, String message, String response) {
        this.id = id;
        this.userId = userId;
        this.message = message;
        this.response = response;
    }

    public BotInteractionId getId() { return id; }
    public void setId(BotInteractionId id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    @Override
    public String toString() {
        return "BotInteraction{interactionId=" + id.getInteractionId() + ", userId=" + userId + ", createdAt=" + id.getCreatedAt() + "}";
    }
}
