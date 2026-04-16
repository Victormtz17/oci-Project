package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Task;
import com.springboot.MyTodoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public ResponseEntity<Task> getTaskById(int id) {
        Optional<Task> data = taskRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Task getTaskByIdSimple(int id) {
        Optional<Task> data = taskRepository.findById(id);
        return data.orElse(null);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    public boolean deleteTask(int id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Task updateTask(int id, Task updated) {
        Optional<Task> data = taskRepository.findById(id);
        if (data.isPresent()) {
            Task task = data.get();
            task.setProjectId(updated.getProjectId());
            task.setSprintId(updated.getSprintId());
            task.setTitle(updated.getTitle());
            task.setDescription(updated.getDescription());
            task.setTaskStage(updated.getTaskStage());
            task.setStatus(updated.getStatus());
            task.setPriority(updated.getPriority());
            task.setAssignedTo(updated.getAssignedTo());
            task.setUpdatedAt(updated.getUpdatedAt());
            task.setDueDate(updated.getDueDate());
            task.setIsDeleted(updated.getIsDeleted());
            task.setDeletedAt(updated.getDeletedAt());
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public Task moveToSprint(int id, Integer sprintId) {
        Optional<Task> data = taskRepository.findById(id);
        if (data.isPresent()) {
            Task task = data.get();
            task.setSprintId(sprintId);
            task.setTaskStage(sprintId != null ? "SPRINT" : "BACKLOG");
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public Task completeTask(int id) {
        Optional<Task> data = taskRepository.findById(id);
        if (data.isPresent()) {
            Task task = data.get();
            task.setStatus("DONE");
            task.setTaskStage("COMPLETED");
            return taskRepository.save(task);
        } else {
            return null;
        }
    }

    public Task completeTask(int id, double actualHours) {
        if (actualHours <= 0) {
            return null;
        }
        Optional<Task> data = taskRepository.findById(id);
        if (data.isPresent()) {
            Task task = data.get();
            task.setStatus("DONE");
            task.setTaskStage("COMPLETED");
            task.setActualHours(actualHours);
            return taskRepository.save(task);
        } else {
            return null;
        }
    }
}
