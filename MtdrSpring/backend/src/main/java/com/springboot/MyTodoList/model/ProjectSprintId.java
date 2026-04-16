package com.springboot.MyTodoList.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectSprintId implements Serializable {

    @Column(name = "PROJECT_ID")
    private int projectId;

    @Column(name = "SPRINT_ID")
    private int sprintId;

    public ProjectSprintId() {}

    public ProjectSprintId(int projectId, int sprintId) {
        this.projectId = projectId;
        this.sprintId = sprintId;
    }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }
    public int getSprintId() { return sprintId; }
    public void setSprintId(int sprintId) { this.sprintId = sprintId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectSprintId)) return false;
        ProjectSprintId that = (ProjectSprintId) o;
        return projectId == that.projectId && sprintId == that.sprintId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, sprintId);
    }
}
