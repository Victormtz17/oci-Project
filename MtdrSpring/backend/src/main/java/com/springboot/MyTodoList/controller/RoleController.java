package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.Role;
import com.springboot.MyTodoList.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/roles")
    public List<Role> getAllRoles() {
        return roleService.findAll();
    }

    @GetMapping(value = "/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(roleService.getRoleById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/roles")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role saved = roleService.addRole(role);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getRoleId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/roles/{id}")
    public ResponseEntity<Role> updateRole(@RequestBody Role role, @PathVariable int id) {
        try {
            Role updated = roleService.updateRole(id, role);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/roles/{id}")
    public ResponseEntity<Boolean> deleteRole(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = roleService.deleteRole(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
