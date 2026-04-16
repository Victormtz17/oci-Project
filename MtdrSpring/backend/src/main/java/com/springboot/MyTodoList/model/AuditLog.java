package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AUDIT_LOG")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AUDIT_ID")
    private int auditId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "ACTION_TYPE")
    private String actionType;

    @Column(name = "ENTITY_NAME")
    private String entityName;

    @Column(name = "ENTITY_ID")
    private Integer entityId;

    @Column(name = "ACTION_DATE")
    private LocalDateTime actionDate;

    @Column(name = "IP_ADDRESS")
    private String ipAddress;

    public AuditLog() {}

    public AuditLog(int auditId, Integer userId, String actionType, String entityName,
                    Integer entityId, LocalDateTime actionDate, String ipAddress) {
        this.auditId = auditId;
        this.userId = userId;
        this.actionType = actionType;
        this.entityName = entityName;
        this.entityId = entityId;
        this.actionDate = actionDate;
        this.ipAddress = ipAddress;
    }

    public int getAuditId() { return auditId; }
    public void setAuditId(int auditId) { this.auditId = auditId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getActionType() { return actionType; }
    public void setActionType(String actionType) { this.actionType = actionType; }
    public String getEntityName() { return entityName; }
    public void setEntityName(String entityName) { this.entityName = entityName; }
    public Integer getEntityId() { return entityId; }
    public void setEntityId(Integer entityId) { this.entityId = entityId; }
    public LocalDateTime getActionDate() { return actionDate; }
    public void setActionDate(LocalDateTime actionDate) { this.actionDate = actionDate; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    @Override
    public String toString() {
        return "AuditLog{auditId=" + auditId + ", userId=" + userId + ", actionType='" + actionType + "', entityName='" + entityName + "', entityId=" + entityId + "}";
    }
}
