package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.AppUser;
import com.springboot.MyTodoList.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping(value = "/users")
    public List<AppUser> getAllUsers() {
        return appUserService.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable int id) {
        try {
            ResponseEntity<AppUser> responseEntity = appUserService.getUserById(id);
            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/adduser")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser newUser) throws Exception {
        AppUser dbUser = appUserService.addUser(newUser);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("location", "" + dbUser.getUserId());
        responseHeaders.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(responseHeaders).build();
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<AppUser> updateUser(@RequestBody AppUser user, @PathVariable int id) {
        try {
            AppUser dbUser = appUserService.updateUser(id, user);
            return new ResponseEntity<>(dbUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") int id) {
        Boolean flag = false;
        try {
            flag = appUserService.deleteUser(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
