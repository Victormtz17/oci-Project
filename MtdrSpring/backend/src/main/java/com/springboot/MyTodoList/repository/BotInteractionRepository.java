package com.springboot.MyTodoList.repository;

import com.springboot.MyTodoList.model.BotInteraction;
import com.springboot.MyTodoList.model.BotInteractionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.transaction.Transactional;

@Repository
@Transactional
@EnableTransactionManagement
public interface BotInteractionRepository extends JpaRepository<BotInteraction, BotInteractionId> {
}
