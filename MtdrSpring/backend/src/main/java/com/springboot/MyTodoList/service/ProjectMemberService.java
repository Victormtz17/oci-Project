package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.ProjectMember;
import com.springboot.MyTodoList.model.ProjectMemberId;
import com.springboot.MyTodoList.repository.ProjectMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectMemberService {

    @Autowired
    private ProjectMemberRepository projectMemberRepository;

    public List<ProjectMember> findAll() {
        return projectMemberRepository.findAll();
    }

    public ResponseEntity<ProjectMember> getById(ProjectMemberId id) {
        Optional<ProjectMember> data = projectMemberRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ProjectMember getByIdSimple(ProjectMemberId id) {
        Optional<ProjectMember> data = projectMemberRepository.findById(id);
        return data.orElse(null);
    }

    public ProjectMember addProjectMember(ProjectMember member) {
        return projectMemberRepository.save(member);
    }

    public boolean deleteProjectMember(ProjectMemberId id) {
        try {
            projectMemberRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectMember updateProjectMember(ProjectMemberId id, ProjectMember updated) {
        Optional<ProjectMember> data = projectMemberRepository.findById(id);
        if (data.isPresent()) {
            ProjectMember member = data.get();
            member.setRoleInProject(updated.getRoleInProject());
            member.setJoinedAt(updated.getJoinedAt());
            member.setIsDeleted(updated.getIsDeleted());
            member.setDeletedAt(updated.getDeletedAt());
            return projectMemberRepository.save(member);
        } else {
            return null;
        }
    }
}
