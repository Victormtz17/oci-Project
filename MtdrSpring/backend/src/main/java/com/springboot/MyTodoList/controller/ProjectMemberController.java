package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.ProjectMember;
import com.springboot.MyTodoList.model.ProjectMemberId;
import com.springboot.MyTodoList.service.ProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProjectMemberController {

    @Autowired
    private ProjectMemberService projectMemberService;

    @GetMapping(value = "/projectmembers")
    public List<ProjectMember> getAllProjectMembers() {
        return projectMemberService.findAll();
    }

    // GET /projectmembers/{projectId}/{userId}
    @GetMapping(value = "/projectmembers/{projectId}/{userId}")
    public ResponseEntity<ProjectMember> getProjectMemberById(
            @PathVariable int projectId,
            @PathVariable int userId) {
        try {
            ProjectMemberId id = new ProjectMemberId(projectId, userId);
            return new ResponseEntity<>(projectMemberService.getByIdSimple(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/projectmembers")
    public ResponseEntity<ProjectMember> addProjectMember(@RequestBody ProjectMember member) {
        ProjectMember saved = projectMemberService.addProjectMember(member);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/projectmembers/{projectId}/{userId}")
    public ResponseEntity<ProjectMember> updateProjectMember(
            @RequestBody ProjectMember member,
            @PathVariable int projectId,
            @PathVariable int userId) {
        try {
            ProjectMemberId id = new ProjectMemberId(projectId, userId);
            ProjectMember updated = projectMemberService.updateProjectMember(id, member);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/projectmembers/{projectId}/{userId}")
    public ResponseEntity<Boolean> deleteProjectMember(
            @PathVariable int projectId,
            @PathVariable int userId) {
        Boolean flag = false;
        try {
            ProjectMemberId id = new ProjectMemberId(projectId, userId);
            flag = projectMemberService.deleteProjectMember(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
