package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK_STATUS_HISTORY")
public class TaskStatusHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private int historyId;

    @Column(name = "TASK_ID", nullable = false)
    private int taskId;

    @Column(name = "OLD_STATUS")
    private String oldStatus;

    @Column(name = "NEW_STATUS", nullable = false)
    private String newStatus;

    @Column(name = "CHANGED_BY")
    private Integer changedBy;

    @Column(name = "CHANGED_AT")
    private LocalDateTime changedAt;

    public TaskStatusHistory() {}

    public TaskStatusHistory(int historyId, int taskId, String oldStatus, String newStatus,
                             Integer changedBy, LocalDateTime changedAt) {
        this.historyId = historyId;
        this.taskId = taskId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
    }

    public int getHistoryId() { return historyId; }
    public void setHistoryId(int historyId) { this.historyId = historyId; }
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public String getOldStatus() { return oldStatus; }
    public void setOldStatus(String oldStatus) { this.oldStatus = oldStatus; }
    public String getNewStatus() { return newStatus; }
    public void setNewStatus(String newStatus) { this.newStatus = newStatus; }
    public Integer getChangedBy() { return changedBy; }
    public void setChangedBy(Integer changedBy) { this.changedBy = changedBy; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }

    @Override
    public String toString() {
        return "TaskStatusHistory{historyId=" + historyId + ", taskId=" + taskId + ", oldStatus='" + oldStatus + "', newStatus='" + newStatus + "'}";
    }
}
