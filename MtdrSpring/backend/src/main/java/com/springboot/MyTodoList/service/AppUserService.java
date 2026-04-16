package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.AppUser;
import com.springboot.MyTodoList.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    public List<AppUser> findAll() {
        return appUserRepository.findAll();
    }

    public ResponseEntity<AppUser> getUserById(int id) {
        Optional<AppUser> data = appUserRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public AppUser getUserByIdSimple(int id) {
        Optional<AppUser> data = appUserRepository.findById(id);
        return data.orElse(null);
    }

    public AppUser addUser(AppUser user) {
        return appUserRepository.save(user);
    }

    public boolean deleteUser(int id) {
        try {
            appUserRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AppUser updateUser(int id, AppUser updated) {
        Optional<AppUser> data = appUserRepository.findById(id);
        if (data.isPresent()) {
            AppUser user = data.get();
            user.setFullName(updated.getFullName());
            user.setEmail(updated.getEmail());
            user.setTelegramId(updated.getTelegramId());
            user.setRoleId(updated.getRoleId());
            user.setTeamId(updated.getTeamId());
            user.setStatus(updated.getStatus());
            user.setUpdatedAt(updated.getUpdatedAt());
            user.setIsDeleted(updated.getIsDeleted());
            user.setDeletedAt(updated.getDeletedAt());
            return appUserRepository.save(user);
        } else {
            return null;
        }
    }
}
