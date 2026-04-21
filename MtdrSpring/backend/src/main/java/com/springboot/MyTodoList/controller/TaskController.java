package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Task;
import com.springboot.MyTodoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // GET /api/v1/ — all tasks (excluding deleted)
    @GetMapping({"", "/"})
    public List<Task> getAllTasks() {
        return taskService.findAll().stream()
                .filter(t -> !"Y".equals(t.getIsDeleted()))
                .toList();
    }

    // GET /api/v1/{id} — single task
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    // POST /api/v1/ — create task from frontend {description} payload
    @PostMapping({"", "/"})
    public ResponseEntity<Task> addTask(@RequestBody Map<String, String> body) {
        String description = body.getOrDefault("description", "");
        String title = body.getOrDefault("title", description);

        Task task = new Task();
        task.setTitle(title.isBlank() ? "Sin título" : title);
        task.setDescription(description);
        task.setStatus("TODO");
        task.setTaskStage("BACKLOG");
        task.setCreatedBy(1); // default user until auth is wired
        task.setCreatedAt(LocalDateTime.now());
        task.setIsDeleted("N");

        Task saved = taskService.addTask(task);

        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getTaskId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    // PUT /api/v1/{id} — toggle status; frontend sends {status: "DONE" | "TODO"}
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id,
                                           @RequestBody Map<String, String> body) {
        Task existing = taskService.getTaskByIdSimple(id);
        if (existing == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (body.containsKey("status")) {
            String newStatus = body.get("status");
            existing.setStatus(newStatus);
            if ("DONE".equals(newStatus)) {
                existing.setTaskStage("COMPLETED");
            } else {
                existing.setTaskStage("BACKLOG");
            }
        }
        if (body.containsKey("description")) {
            existing.setDescription(body.get("description"));
        }
        existing.setUpdatedAt(LocalDateTime.now());

        Task updated = taskService.addTask(existing); // save() works for update too
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    // DELETE /api/v1/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteTask(@PathVariable int id) {
        boolean deleted = taskService.deleteTask(id);
        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
