package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.TaskStatusHistory;
import com.springboot.MyTodoList.service.TaskStatusHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskStatusHistoryController {

    @Autowired
    private TaskStatusHistoryService taskStatusHistoryService;

    @GetMapping(value = "/taskstatushistory")
    public List<TaskStatusHistory> getAllHistory() {
        return taskStatusHistoryService.findAll();
    }

    @GetMapping(value = "/taskstatushistory/{id}")
    public ResponseEntity<TaskStatusHistory> getHistoryById(@PathVariable int id) {
        return taskStatusHistoryService.getById(id);
    }

    // Historial es inmutable — solo POST
    @PostMapping(value = "/taskstatushistory")
    public ResponseEntity<TaskStatusHistory> addHistory(@RequestBody TaskStatusHistory history) {
        TaskStatusHistory saved = taskStatusHistoryService.addHistory(history);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
