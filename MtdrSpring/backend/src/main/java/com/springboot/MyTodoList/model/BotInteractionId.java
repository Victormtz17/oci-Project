package com.springboot.MyTodoList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class BotInteractionId implements Serializable {

    @Column(name = "INTERACTION_ID")
    private int interactionId;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    public BotInteractionId() {}

    public BotInteractionId(int interactionId, LocalDateTime createdAt) {
        this.interactionId = interactionId;
        this.createdAt = createdAt;
    }

    public int getInteractionId() { return interactionId; }
    public void setInteractionId(int interactionId) { this.interactionId = interactionId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BotInteractionId)) return false;
        BotInteractionId that = (BotInteractionId) o;
        return interactionId == that.interactionId && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(interactionId, createdAt);
    }
}
