package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Sprint;
import com.springboot.MyTodoList.repository.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SprintService {

    @Autowired
    private SprintRepository sprintRepository;

    public List<Sprint> findAll() {
        return sprintRepository.findAll();
    }

    public ResponseEntity<Sprint> getSprintById(int id) {
        Optional<Sprint> data = sprintRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Sprint getSprintByIdSimple(int id) {
        Optional<Sprint> data = sprintRepository.findById(id);
        return data.orElse(null);
    }

    public Sprint addSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public boolean deleteSprint(int id) {
        try {
            sprintRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Sprint updateSprint(int id, Sprint updated) {
        Optional<Sprint> data = sprintRepository.findById(id);
        if (data.isPresent()) {
            Sprint sprint = data.get();
            sprint.setName(updated.getName());
            sprint.setStartDate(updated.getStartDate());
            sprint.setEndDate(updated.getEndDate());
            sprint.setStatus(updated.getStatus());
            sprint.setIsDeleted(updated.getIsDeleted());
            sprint.setDeletedAt(updated.getDeletedAt());
            return sprintRepository.save(sprint);
        } else {
            return null;
        }
    }
}
