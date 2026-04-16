package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Deployment;
import com.springboot.MyTodoList.repository.DeploymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeploymentService {

    @Autowired
    private DeploymentRepository deploymentRepository;

    public List<Deployment> findAll() {
        return deploymentRepository.findAll();
    }

    public ResponseEntity<Deployment> getById(int id) {
        Optional<Deployment> data = deploymentRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Deployment getByIdSimple(int id) {
        Optional<Deployment> data = deploymentRepository.findById(id);
        return data.orElse(null);
    }

    public Deployment addDeployment(Deployment deployment) {
        return deploymentRepository.save(deployment);
    }

    public boolean deleteDeployment(int id) {
        try {
            deploymentRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Deployment updateDeployment(int id, Deployment updated) {
        Optional<Deployment> data = deploymentRepository.findById(id);
        if (data.isPresent()) {
            Deployment deployment = data.get();
            deployment.setProjectId(updated.getProjectId());
            deployment.setVersion(updated.getVersion());
            deployment.setEnvironment(updated.getEnvironment());
            deployment.setStatus(updated.getStatus());
            deployment.setDeployedAt(updated.getDeployedAt());
            deployment.setRecoveryTimeMin(updated.getRecoveryTimeMin());
            return deploymentRepository.save(deployment);
        } else {
            return null;
        }
    }
}
