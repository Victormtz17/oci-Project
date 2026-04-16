package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "KPI_VALUES")
public class KpiValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KPI_VALUE_ID")
    private int kpiValueId;

    @Column(name = "KPI_TYPE_ID", nullable = false)
    private int kpiTypeId;

    @Column(name = "SCOPE_TYPE", nullable = false)
    private String scopeType;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "SPRINT_ID")
    private Integer sprintId;

    @Column(name = "VALUE", nullable = false)
    private double value;

    @Column(name = "RECORDED_AT")
    private LocalDateTime recordedAt;

    public KpiValue() {}

    public KpiValue(int kpiValueId, int kpiTypeId, String scopeType, Integer userId,
                    Integer projectId, Integer sprintId, double value, LocalDateTime recordedAt) {
        this.kpiValueId = kpiValueId;
        this.kpiTypeId = kpiTypeId;
        this.scopeType = scopeType;
        this.userId = userId;
        this.projectId = projectId;
        this.sprintId = sprintId;
        this.value = value;
        this.recordedAt = recordedAt;
    }

    public int getKpiValueId() { return kpiValueId; }
    public void setKpiValueId(int kpiValueId) { this.kpiValueId = kpiValueId; }
    public int getKpiTypeId() { return kpiTypeId; }
    public void setKpiTypeId(int kpiTypeId) { this.kpiTypeId = kpiTypeId; }
    public String getScopeType() { return scopeType; }
    public void setScopeType(String scopeType) { this.scopeType = scopeType; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }
    public Integer getSprintId() { return sprintId; }
    public void setSprintId(Integer sprintId) { this.sprintId = sprintId; }
    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    @Override
    public String toString() {
        return "KpiValue{kpiValueId=" + kpiValueId + ", kpiTypeId=" + kpiTypeId + ", scopeType='" + scopeType + "', value=" + value + "}";
    }
}
