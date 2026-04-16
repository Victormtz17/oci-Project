package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASKS")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASK_ID")
    private int taskId;

    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "SPRINT_ID")
    private Integer sprintId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION", columnDefinition = "CLOB")
    private String description;

    @Column(name = "TASK_STAGE")
    private String taskStage;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "PRIORITY")
    private String priority;

    @Column(name = "CREATED_BY", nullable = false)
    private int createdBy;

    @Column(name = "ASSIGNED_TO")
    private Integer assignedTo;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "ACTUAL_HOURS")
    private Double actualHours;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public Task() {}

    public Task(int taskId, Integer projectId, Integer sprintId, String title, String description,
                String taskStage, String status, String priority, int createdBy, Integer assignedTo,
                LocalDateTime createdAt, LocalDateTime updatedAt, LocalDate dueDate,
                String isDeleted, LocalDateTime deletedAt) {
        this.taskId = taskId;
        this.projectId = projectId;
        this.sprintId = sprintId;
        this.title = title;
        this.description = description;
        this.taskStage = taskStage;
        this.status = status;
        this.priority = priority;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.dueDate = dueDate;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }
    public Integer getSprintId() { return sprintId; }
    public void setSprintId(Integer sprintId) { this.sprintId = sprintId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTaskStage() { return taskStage; }
    public void setTaskStage(String taskStage) { this.taskStage = taskStage; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public int getCreatedBy() { return createdBy; }
    public void setCreatedBy(int createdBy) { this.createdBy = createdBy; }
    public Integer getAssignedTo() { return assignedTo; }
    public void setAssignedTo(Integer assignedTo) { this.assignedTo = assignedTo; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Double getActualHours() { return actualHours; }
    public void setActualHours(Double actualHours) { this.actualHours = actualHours; }
    public String getIsDeleted() { return isDeleted; }
    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    @Override
    public String toString() {
        return "Task{taskId=" + taskId + ", title='" + title + "', status='" + status + "', priority='" + priority + "', taskStage='" + taskStage + "'}";
    }
}
