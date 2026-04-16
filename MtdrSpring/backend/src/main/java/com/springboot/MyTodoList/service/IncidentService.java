package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Incident;
import com.springboot.MyTodoList.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public List<Incident> findAll() {
        return incidentRepository.findAll();
    }

    public ResponseEntity<Incident> getById(int id) {
        Optional<Incident> data = incidentRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Incident getByIdSimple(int id) {
        Optional<Incident> data = incidentRepository.findById(id);
        return data.orElse(null);
    }

    public Incident addIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public boolean deleteIncident(int id) {
        try {
            incidentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Incident updateIncident(int id, Incident updated) {
        Optional<Incident> data = incidentRepository.findById(id);
        if (data.isPresent()) {
            Incident incident = data.get();
            incident.setProjectId(updated.getProjectId());
            incident.setType(updated.getType());
            incident.setDescription(updated.getDescription());
            incident.setSeverity(updated.getSeverity());
            incident.setOccurredAt(updated.getOccurredAt());
            incident.setResolvedAt(updated.getResolvedAt());
            incident.setIsDeleted(updated.getIsDeleted());
            incident.setDeletedAt(updated.getDeletedAt());
            return incidentRepository.save(incident);
        } else {
            return null;
        }
    }
}
