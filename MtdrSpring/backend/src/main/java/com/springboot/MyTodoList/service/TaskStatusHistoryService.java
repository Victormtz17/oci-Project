package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.TaskStatusHistory;
import com.springboot.MyTodoList.repository.TaskStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskStatusHistoryService {

    @Autowired
    private TaskStatusHistoryRepository taskStatusHistoryRepository;

    public List<TaskStatusHistory> findAll() {
        return taskStatusHistoryRepository.findAll();
    }

    public ResponseEntity<TaskStatusHistory> getById(int id) {
        Optional<TaskStatusHistory> data = taskStatusHistoryRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public TaskStatusHistory getByIdSimple(int id) {
        Optional<TaskStatusHistory> data = taskStatusHistoryRepository.findById(id);
        return data.orElse(null);
    }

    public TaskStatusHistory addHistory(TaskStatusHistory history) {
        return taskStatusHistoryRepository.save(history);
    }

    public boolean deleteHistory(int id) {
        try {
            taskStatusHistoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TaskStatusHistory updateHistory(int id, TaskStatusHistory updated) {
        Optional<TaskStatusHistory> data = taskStatusHistoryRepository.findById(id);
        if (data.isPresent()) {
            TaskStatusHistory history = data.get();
            history.setOldStatus(updated.getOldStatus());
            history.setNewStatus(updated.getNewStatus());
            history.setChangedBy(updated.getChangedBy());
            history.setChangedAt(updated.getChangedAt());
            return taskStatusHistoryRepository.save(history);
        } else {
            return null;
        }
    }
}
