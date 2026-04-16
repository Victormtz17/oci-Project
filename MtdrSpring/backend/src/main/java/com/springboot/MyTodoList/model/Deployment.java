package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "DEPLOYMENTS")
public class Deployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPLOYMENT_ID")
    private int deploymentId;

    @Column(name = "PROJECT_ID")
    private Integer projectId;

    @Column(name = "VERSION")
    private String version;

    @Column(name = "ENVIRONMENT")
    private String environment;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DEPLOYED_AT")
    private LocalDateTime deployedAt;

    @Column(name = "RECOVERY_TIME_MIN")
    private Double recoveryTimeMin;

    public Deployment() {}

    public Deployment(int deploymentId, Integer projectId, String version, String environment,
                      String status, LocalDateTime deployedAt, Double recoveryTimeMin) {
        this.deploymentId = deploymentId;
        this.projectId = projectId;
        this.version = version;
        this.environment = environment;
        this.status = status;
        this.deployedAt = deployedAt;
        this.recoveryTimeMin = recoveryTimeMin;
    }

    public int getDeploymentId() { return deploymentId; }
    public void setDeploymentId(int deploymentId) { this.deploymentId = deploymentId; }
    public Integer getProjectId() { return projectId; }
    public void setProjectId(Integer projectId) { this.projectId = projectId; }
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
    public String getEnvironment() { return environment; }
    public void setEnvironment(String environment) { this.environment = environment; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public LocalDateTime getDeployedAt() { return deployedAt; }
    public void setDeployedAt(LocalDateTime deployedAt) { this.deployedAt = deployedAt; }
    public Double getRecoveryTimeMin() { return recoveryTimeMin; }
    public void setRecoveryTimeMin(Double recoveryTimeMin) { this.recoveryTimeMin = recoveryTimeMin; }

    @Override
    public String toString() {
        return "Deployment{deploymentId=" + deploymentId + ", projectId=" + projectId + ", version='" + version + "', environment='" + environment + "', status='" + status + "'}";
    }
}
