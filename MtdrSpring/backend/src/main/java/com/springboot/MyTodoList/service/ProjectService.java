package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Project;
import com.springboot.MyTodoList.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public ResponseEntity<Project> getProjectById(int id) {
        Optional<Project> data = projectRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Project getProjectByIdSimple(int id) {
        Optional<Project> data = projectRepository.findById(id);
        return data.orElse(null);
    }

    public Project addProject(Project project) {
        return projectRepository.save(project);
    }

    public boolean deleteProject(int id) {
        try {
            projectRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Project updateProject(int id, Project updated) {
        Optional<Project> data = projectRepository.findById(id);
        if (data.isPresent()) {
            Project project = data.get();
            project.setName(updated.getName());
            project.setDescription(updated.getDescription());
            project.setManagerId(updated.getManagerId());
            project.setStatus(updated.getStatus());
            project.setUpdatedAt(updated.getUpdatedAt());
            project.setIsDeleted(updated.getIsDeleted());
            project.setDeletedAt(updated.getDeletedAt());
            return projectRepository.save(project);
        } else {
            return null;
        }
    }
}
