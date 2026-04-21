package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Deployment;
import com.springboot.MyTodoList.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @GetMapping(value = "/deployments")
    public List<Deployment> getAllDeployments() {
        return deploymentService.findAll();
    }

    @GetMapping(value = "/deployments/{id}")
    public ResponseEntity<Deployment> getDeploymentById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(deploymentService.getById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/deployments")
    public ResponseEntity<Deployment> addDeployment(@RequestBody Deployment deployment) {
        Deployment saved = deploymentService.addDeployment(deployment);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getDeploymentId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/deployments/{id}")
    public ResponseEntity<Deployment> updateDeployment(@RequestBody Deployment deployment, @PathVariable int id) {
        try {
            Deployment updated = deploymentService.updateDeployment(id, deployment);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deployments/{id}")
    public ResponseEntity<Boolean> deleteDeployment(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = deploymentService.deleteDeployment(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
