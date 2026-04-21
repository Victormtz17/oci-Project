package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.BotInteraction;
import com.springboot.MyTodoList.model.BotInteractionId;
import com.springboot.MyTodoList.service.BotInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BotInteractionController {

    @Autowired
    private BotInteractionService botInteractionService;

    @GetMapping(value = "/botinteractions")
    public List<BotInteraction> getAllInteractions() {
        return botInteractionService.findAll();
    }

    // Historial de interacciones es inmutable — solo GET y POST
    @PostMapping(value = "/botinteractions")
    public ResponseEntity<BotInteraction> addInteraction(@RequestBody BotInteraction interaction) {
        BotInteraction saved = botInteractionService.addInteraction(interaction);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
