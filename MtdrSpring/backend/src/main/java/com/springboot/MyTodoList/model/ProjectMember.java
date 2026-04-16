package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROJECT_MEMBERS")
public class ProjectMember {

    @EmbeddedId
    private ProjectMemberId id;

    @Column(name = "ROLE_IN_PROJECT")
    private String roleInProject;

    @Column(name = "JOINED_AT")
    private LocalDateTime joinedAt;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public ProjectMember() {}

    public ProjectMember(ProjectMemberId id, String roleInProject, LocalDateTime joinedAt,
                         String isDeleted, LocalDateTime deletedAt) {
        this.id = id;
        this.roleInProject = roleInProject;
        this.joinedAt = joinedAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public ProjectMemberId getId() { return id; }
    public void setId(ProjectMemberId id) { this.id = id; }
    public String getRoleInProject() { return roleInProject; }
    public void setRoleInProject(String roleInProject) { this.roleInProject = roleInProject; }
    public LocalDateTime getJoinedAt() { return joinedAt; }
    public void setJoinedAt(LocalDateTime joinedAt) { this.joinedAt = joinedAt; }
    public String getIsDeleted() { return isDeleted; }
    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    @Override
    public String toString() {
        return "ProjectMember{projectId=" + id.getProjectId() + ", userId=" + id.getUserId() + ", roleInProject='" + roleInProject + "'}";
    }
}
