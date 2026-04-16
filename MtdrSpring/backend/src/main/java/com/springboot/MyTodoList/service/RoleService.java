package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.Role;
import com.springboot.MyTodoList.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public ResponseEntity<Role> getRoleById(int id) {
        Optional<Role> data = roleRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Role getRoleByIdSimple(int id) {
        Optional<Role> data = roleRepository.findById(id);
        return data.orElse(null);
    }

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public boolean deleteRole(int id) {
        try {
            roleRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Role updateRole(int id, Role updated) {
        Optional<Role> data = roleRepository.findById(id);
        if (data.isPresent()) {
            Role role = data.get();
            role.setRoleName(updated.getRoleName());
            role.setDescription(updated.getDescription());
            role.setIsDeleted(updated.getIsDeleted());
            role.setDeletedAt(updated.getDeletedAt());
            return roleRepository.save(role);
        } else {
            return null;
        }
    }
}
