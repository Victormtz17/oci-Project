package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.UserCredential;
import com.springboot.MyTodoList.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserCredentialController {

    @Autowired
    private UserCredentialService userCredentialService;

    @GetMapping(value = "/credentials")
    public List<UserCredential> getAllCredentials() {
        return userCredentialService.findAll();
    }

    @GetMapping(value = "/credentials/{id}")
    public ResponseEntity<UserCredential> getCredentialById(@PathVariable int id) {
        return userCredentialService.getCredentialById(id);
    }

    @PostMapping(value = "/credentials")
    public ResponseEntity<UserCredential> addCredential(@RequestBody UserCredential credential) {
        UserCredential saved = userCredentialService.addCredential(credential);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/credentials/{id}")
    public ResponseEntity<UserCredential> updateCredential(
            @RequestBody UserCredential credential, @PathVariable int id) {
        try {
            UserCredential updated = userCredentialService.updateCredential(id, credential);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/credentials/{id}")
    public ResponseEntity<Boolean> deleteCredential(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = userCredentialService.deleteCredential(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
