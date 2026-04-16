package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROJECT_SPRINTS")
public class ProjectSprint {

    @EmbeddedId
    private ProjectSprintId id;

    @Column(name = "SPRINT_NUMBER", nullable = false)
    private int sprintNumber;

    @Column(name = "IS_ACTIVE")
    private String isActive;

    @Column(name = "ASSIGNED_AT")
    private LocalDateTime assignedAt;

    public ProjectSprint() {}

    public ProjectSprint(ProjectSprintId id, int sprintNumber, String isActive, LocalDateTime assignedAt) {
        this.id = id;
        this.sprintNumber = sprintNumber;
        this.isActive = isActive;
        this.assignedAt = assignedAt;
    }

    public ProjectSprintId getId() { return id; }
    public void setId(ProjectSprintId id) { this.id = id; }
    public int getSprintNumber() { return sprintNumber; }
    public void setSprintNumber(int sprintNumber) { this.sprintNumber = sprintNumber; }
    public String getIsActive() { return isActive; }
    public void setIsActive(String isActive) { this.isActive = isActive; }
    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }

    @Override
    public String toString() {
        return "ProjectSprint{projectId=" + id.getProjectId() + ", sprintId=" + id.getSprintId() + ", sprintNumber=" + sprintNumber + ", isActive='" + isActive + "'}";
    }
}
