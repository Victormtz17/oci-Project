package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Project;
import com.springboot.MyTodoList.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/projects")
    public List<Project> getAllProjects() {
        return projectService.findAll();
    }

    @GetMapping(value = "/projects/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(projectService.getProjectById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/projects")
    public ResponseEntity<Project> addProject(@RequestBody Project project) {
        Project saved = projectService.addProject(project);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getProjectId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/projects/{id}")
    public ResponseEntity<Project> updateProject(@RequestBody Project project, @PathVariable int id) {
        try {
            Project updated = projectService.updateProject(id, project);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/projects/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = projectService.deleteProject(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
