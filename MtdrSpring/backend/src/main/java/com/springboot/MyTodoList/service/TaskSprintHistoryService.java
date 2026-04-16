package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.TaskSprintHistory;
import com.springboot.MyTodoList.repository.TaskSprintHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskSprintHistoryService {

    @Autowired
    private TaskSprintHistoryRepository taskSprintHistoryRepository;

    public List<TaskSprintHistory> findAll() {
        return taskSprintHistoryRepository.findAll();
    }

    public ResponseEntity<TaskSprintHistory> getById(int id) {
        Optional<TaskSprintHistory> data = taskSprintHistoryRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public TaskSprintHistory getByIdSimple(int id) {
        Optional<TaskSprintHistory> data = taskSprintHistoryRepository.findById(id);
        return data.orElse(null);
    }

    public TaskSprintHistory addHistory(TaskSprintHistory history) {
        return taskSprintHistoryRepository.save(history);
    }

    public boolean deleteHistory(int id) {
        try {
            taskSprintHistoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public TaskSprintHistory updateHistory(int id, TaskSprintHistory updated) {
        Optional<TaskSprintHistory> data = taskSprintHistoryRepository.findById(id);
        if (data.isPresent()) {
            TaskSprintHistory history = data.get();
            history.setOldSprintId(updated.getOldSprintId());
            history.setNewSprintId(updated.getNewSprintId());
            history.setChangedBy(updated.getChangedBy());
            history.setChangedAt(updated.getChangedAt());
            return taskSprintHistoryRepository.save(history);
        } else {
            return null;
        }
    }
}
