package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
}
