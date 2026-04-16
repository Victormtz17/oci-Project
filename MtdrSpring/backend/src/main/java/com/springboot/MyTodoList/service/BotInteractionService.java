package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.BotInteraction;
import com.springboot.MyTodoList.model.BotInteractionId;
import com.springboot.MyTodoList.repository.BotInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BotInteractionService {

    @Autowired
    private BotInteractionRepository botInteractionRepository;

    public List<BotInteraction> findAll() {
        return botInteractionRepository.findAll();
    }

    public ResponseEntity<BotInteraction> getById(BotInteractionId id) {
        Optional<BotInteraction> data = botInteractionRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public BotInteraction getByIdSimple(BotInteractionId id) {
        Optional<BotInteraction> data = botInteractionRepository.findById(id);
        return data.orElse(null);
    }

    public BotInteraction addInteraction(BotInteraction interaction) {
        return botInteractionRepository.save(interaction);
    }

    public boolean deleteInteraction(BotInteractionId id) {
        try {
            botInteractionRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public BotInteraction updateInteraction(BotInteractionId id, BotInteraction updated) {
        Optional<BotInteraction> data = botInteractionRepository.findById(id);
        if (data.isPresent()) {
            BotInteraction interaction = data.get();
            interaction.setUserId(updated.getUserId());
            interaction.setMessage(updated.getMessage());
            interaction.setResponse(updated.getResponse());
            return botInteractionRepository.save(interaction);
        } else {
            return null;
        }
    }
}
