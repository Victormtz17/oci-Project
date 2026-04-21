package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.TaskSprintHistory;
import com.springboot.MyTodoList.service.TaskSprintHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskSprintHistoryController {

    @Autowired
    private TaskSprintHistoryService taskSprintHistoryService;

    @GetMapping(value = "/tasksprinthistory")
    public List<TaskSprintHistory> getAllHistory() {
        return taskSprintHistoryService.findAll();
    }

    @GetMapping(value = "/tasksprinthistory/{id}")
    public ResponseEntity<TaskSprintHistory> getHistoryById(@PathVariable int id) {
        return taskSprintHistoryService.getById(id);
    }

    // Historial es inmutable — solo POST
    @PostMapping(value = "/tasksprinthistory")
    public ResponseEntity<TaskSprintHistory> addHistory(@RequestBody TaskSprintHistory history) {
        TaskSprintHistory saved = taskSprintHistoryService.addHistory(history);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
