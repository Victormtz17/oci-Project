package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.service.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @GetMapping(value = "/sprints")
    public List<Sprint> getAllSprints() {
        return sprintService.findAll();
    }

    @GetMapping(value = "/sprints/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(sprintService.getSprintById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/sprints")
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint) {
        Sprint saved = sprintService.addSprint(sprint);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getSprintId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/sprints/{id}")
    public ResponseEntity<Sprint> updateSprint(@RequestBody Sprint sprint, @PathVariable int id) {
        try {
            Sprint updated = sprintService.updateSprint(id, sprint);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/sprints/{id}")
    public ResponseEntity<Boolean> deleteSprint(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = sprintService.deleteSprint(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
