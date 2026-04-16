package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "INCIDENTS")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INCIDENT_ID")
    private int incidentId;

    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION", columnDefinition = "CLOB")
    private String description;

    @Column(name = "SEVERITY")
    private String severity;

    @Column(name = "OCCURRED_AT")
    private LocalDateTime occurredAt;

    @Column(name = "RESOLVED_AT")
    private LocalDateTime resolvedAt;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public Incident() {}

    public Incident(int incidentId, Integer projectId, String type, String description,
                    String severity, LocalDateTime occurredAt, LocalDateTime resolvedAt,
                    String isDeleted, LocalDateTime deletedAt) {
        this.incidentId = incidentId;
        this.projectId = projectId;
        this.type = type;
        this.description = description;
        this.severity = severity;
        this.occurredAt = occurredAt;
        this.resolvedAt = resolvedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public int getIncidentId() { return incidentId; }
    public void setIncidentId(int incidentId) { this.incidentId = incidentId; }
    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }
    public LocalDateTime getOccurredAt() { return occurredAt; }
    public void setOccurredAt(LocalDateTime occurredAt) { this.occurredAt = occurredAt; }
    public LocalDateTime getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDateTime resolvedAt) { this.resolvedAt = resolvedAt; }
    public String getIsDeleted() { return isDeleted; }
    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    @Override
    public String toString() {
        return "Incident{incidentId=" + incidentId + ", projectId=" + projectId + ", type='" + type + "', severity='" + severity + "'}";
    }
}
