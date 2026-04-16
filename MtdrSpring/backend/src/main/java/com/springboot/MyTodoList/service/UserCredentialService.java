package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.UserCredential;
import com.springboot.MyTodoList.repository.UserCredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCredentialService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    public List<UserCredential> findAll() {
        return userCredentialRepository.findAll();
    }

    public ResponseEntity<UserCredential> getCredentialById(int id) {
        Optional<UserCredential> data = userCredentialRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public UserCredential getCredentialByIdSimple(int id) {
        Optional<UserCredential> data = userCredentialRepository.findById(id);
        return data.orElse(null);
    }

    public UserCredential addCredential(UserCredential credential) {
        return userCredentialRepository.save(credential);
    }

    public boolean deleteCredential(int id) {
        try {
            userCredentialRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UserCredential updateCredential(int id, UserCredential updated) {
        Optional<UserCredential> data = userCredentialRepository.findById(id);
        if (data.isPresent()) {
            UserCredential credential = data.get();
            credential.setUsername(updated.getUsername());
            credential.setPasswordHash(updated.getPasswordHash());
            credential.setPasswordSalt(updated.getPasswordSalt());
            credential.setLastLogin(updated.getLastLogin());
            credential.setFailedAttempts(updated.getFailedAttempts());
            credential.setAccountLocked(updated.getAccountLocked());
            credential.setIsDeleted(updated.getIsDeleted());
            credential.setDeletedAt(updated.getDeletedAt());
            return userCredentialRepository.save(credential);
        } else {
            return null;
        }
    }
}
