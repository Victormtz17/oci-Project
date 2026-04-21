package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Incident;
import com.springboot.MyTodoList.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping(value = "/incidents")
    public List<Incident> getAllIncidents() {
        return incidentService.findAll();
    }

    @GetMapping(value = "/incidents/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(incidentService.getById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/incidents")
    public ResponseEntity<Incident> addIncident(@RequestBody Incident incident) {
        Incident saved = incidentService.addIncident(incident);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getIncidentId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/incidents/{id}")
    public ResponseEntity<Incident> updateIncident(@RequestBody Incident incident, @PathVariable int id) {
        try {
            Incident updated = incidentService.updateIncident(id, incident);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/incidents/{id}")
    public ResponseEntity<Boolean> deleteIncident(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = incidentService.deleteIncident(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
