package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.ProjectSprint;
import com.springboot.MyTodoList.model.ProjectSprintId;
import com.springboot.MyTodoList.repository.ProjectSprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectSprintService {

    @Autowired
    private ProjectSprintRepository projectSprintRepository;

    public List<ProjectSprint> findAll() {
        return projectSprintRepository.findAll();
    }

    public ResponseEntity<ProjectSprint> getById(ProjectSprintId id) {
        Optional<ProjectSprint> data = projectSprintRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ProjectSprint getByIdSimple(ProjectSprintId id) {
        Optional<ProjectSprint> data = projectSprintRepository.findById(id);
        return data.orElse(null);
    }

    public ProjectSprint addProjectSprint(ProjectSprint projectSprint) {
        return projectSprintRepository.save(projectSprint);
    }

    public boolean deleteProjectSprint(ProjectSprintId id) {
        try {
            projectSprintRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public ProjectSprint updateProjectSprint(ProjectSprintId id, ProjectSprint updated) {
        Optional<ProjectSprint> data = projectSprintRepository.findById(id);
        if (data.isPresent()) {
            ProjectSprint ps = data.get();
            ps.setSprintNumber(updated.getSprintNumber());
            ps.setIsActive(updated.getIsActive());
            ps.setAssignedAt(updated.getAssignedAt());
            return projectSprintRepository.save(ps);
        } else {
            return null;
        }
    }
}
