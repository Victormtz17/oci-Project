package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LLM_ANALYSIS")
public class LlmAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANALYSIS_ID")
    private int analysisId;

    @Column(name = "SCOPE_TYPE", nullable = false)
    private String scopeType;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "SPRINT_ID")
    private Integer sprintId;

    @Column(name = "ANOMALY_DETECTED")
    private String anomalyDetected;

    @Column(name = "ANOMALY_TYPE")
    private String anomalyType;

    @Column(name = "CONFIDENCE_SCORE")
    private Double confidenceScore;

    @Column(name = "RECOMMENDATION", columnDefinition = "CLOB")
    private String recommendation;

    @Column(name = "ANALYSIS_DATE")
    private LocalDateTime analysisDate;

    public LlmAnalysis() {}

    public LlmAnalysis(int analysisId, String scopeType, Integer userId, Integer projectId,
                       Integer sprintId, String anomalyDetected, String anomalyType,
                       Double confidenceScore, String recommendation, LocalDateTime analysisDate) {
        this.analysisId = analysisId;
        this.scopeType = scopeType;
        this.userId = userId;
        this.projectId = projectId;
        this.sprintId = sprintId;
        this.anomalyDetected = anomalyDetected;
        this.anomalyType = anomalyType;
        this.confidenceScore = confidenceScore;
        this.recommendation = recommendation;
        this.analysisDate = analysisDate;
    }

    public int getAnalysisId() { return analysisId; }
    public void setAnalysisId(int analysisId) { this.analysisId = analysisId; }
    public String getScopeType() { return scopeType; }
    public void setScopeType(String scopeType) { this.scopeType = scopeType; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }
    public Integer getSprintId() { return sprintId; }
    public void setSprintId(Integer sprintId) { this.sprintId = sprintId; }
    public String getAnomalyDetected() { return anomalyDetected; }
    public void setAnomalyDetected(String anomalyDetected) { this.anomalyDetected = anomalyDetected; }
    public String getAnomalyType() { return anomalyType; }
    public void setAnomalyType(String anomalyType) { this.anomalyType = anomalyType; }
    public Double getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }
    public String getRecommendation() { return recommendation; }
    public void setRecommendation(String recommendation) { this.recommendation = recommendation; }
    public LocalDateTime getAnalysisDate() { return analysisDate; }
    public void setAnalysisDate(LocalDateTime analysisDate) { this.analysisDate = analysisDate; }

    @Override
    public String toString() {
        return "LlmAnalysis{analysisId=" + analysisId + ", scopeType='" + scopeType + "', anomalyDetected='" + anomalyDetected + "', confidenceScore=" + confidenceScore + "}";
    }
}
