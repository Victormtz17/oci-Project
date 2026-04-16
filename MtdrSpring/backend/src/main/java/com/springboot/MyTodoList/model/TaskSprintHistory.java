package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TASK_SPRINT_HISTORY")
public class TaskSprintHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HISTORY_ID")
    private int historyId;

    @Column(name = "TASK_ID", nullable = false)
    private int taskId;

    @Column(name = "OLD_SPRINT_ID")
    private Integer oldSprintId;

    @Column(name = "NEW_SPRINT_ID")
    private Integer newSprintId;

    @Column(name = "CHANGED_BY")
    private Integer changedBy;

    @Column(name = "CHANGED_AT")
    private LocalDateTime changedAt;

    public TaskSprintHistory() {}

    public TaskSprintHistory(int historyId, int taskId, Integer oldSprintId, Integer newSprintId,
                             Integer changedBy, LocalDateTime changedAt) {
        this.historyId = historyId;
        this.taskId = taskId;
        this.oldSprintId = oldSprintId;
        this.newSprintId = newSprintId;
        this.changedBy = changedBy;
        this.changedAt = changedAt;
    }

    public int getHistoryId() { return historyId; }
    public void setHistoryId(int historyId) { this.historyId = historyId; }
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public Integer getOldSprintId() { return oldSprintId; }
    public void setOldSprintId(Integer oldSprintId) { this.oldSprintId = oldSprintId; }
    public Integer getNewSprintId() { return newSprintId; }
    public void setNewSprintId(Integer newSprintId) { this.newSprintId = newSprintId; }
    public Integer getChangedBy() { return changedBy; }
    public void setChangedBy(Integer changedBy) { this.changedBy = changedBy; }
    public LocalDateTime getChangedAt() { return changedAt; }
    public void setChangedAt(LocalDateTime changedAt) { this.changedAt = changedAt; }

    @Override
    public String toString() {
        return "TaskSprintHistory{historyId=" + historyId + ", taskId=" + taskId + ", oldSprintId=" + oldSprintId + ", newSprintId=" + newSprintId + "}";
    }
}
