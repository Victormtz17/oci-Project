package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.ProjectSprint;
import com.springboot.MyTodoList.model.ProjectSprintId;
import com.springboot.MyTodoList.service.ProjectSprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectSprintController {

    @Autowired
    private ProjectSprintService projectSprintService;

    @GetMapping(value = "/projectsprints")
    public List<ProjectSprint> getAllProjectSprints() {
        return projectSprintService.findAll();
    }

    // GET /projectsprints/{projectId}/{sprintId}
    @GetMapping(value = "/projectsprints/{projectId}/{sprintId}")
    public ResponseEntity<ProjectSprint> getProjectSprintById(
            @PathVariable int projectId,
            @PathVariable int sprintId) {
        try {
            ProjectSprintId id = new ProjectSprintId(projectId, sprintId);
            return new ResponseEntity<>(projectSprintService.getByIdSimple(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/projectsprints")
    public ResponseEntity<ProjectSprint> addProjectSprint(@RequestBody ProjectSprint projectSprint) {
        ProjectSprint saved = projectSprintService.addProjectSprint(projectSprint);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/projectsprints/{projectId}/{sprintId}")
    public ResponseEntity<ProjectSprint> updateProjectSprint(
            @RequestBody ProjectSprint projectSprint,
            @PathVariable int projectId,
            @PathVariable int sprintId) {
        try {
            ProjectSprintId id = new ProjectSprintId(projectId, sprintId);
            ProjectSprint updated = projectSprintService.updateProjectSprint(id, projectSprint);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/projectsprints/{projectId}/{sprintId}")
    public ResponseEntity<Boolean> deleteProjectSprint(
            @PathVariable int projectId,
            @PathVariable int sprintId) {
        Boolean flag = false;
        try {
            ProjectSprintId id = new ProjectSprintId(projectId, sprintId);
            flag = projectSprintService.deleteProjectSprint(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
