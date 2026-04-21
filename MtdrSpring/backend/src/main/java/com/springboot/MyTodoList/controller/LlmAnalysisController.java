package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.LlmAnalysis;
import com.springboot.MyTodoList.service.LlmAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LlmAnalysisController {

    @Autowired
    private LlmAnalysisService llmAnalysisService;

    @GetMapping(value = "/llmanalysis")
    public List<LlmAnalysis> getAllAnalyses() {
        return llmAnalysisService.findAll();
    }

    @GetMapping(value = "/llmanalysis/{id}")
    public ResponseEntity<LlmAnalysis> getAnalysisById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(llmAnalysisService.getById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/llmanalysis")
    public ResponseEntity<LlmAnalysis> addAnalysis(@RequestBody LlmAnalysis analysis) {
        LlmAnalysis saved = llmAnalysisService.addAnalysis(analysis);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getAnalysisId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/llmanalysis/{id}")
    public ResponseEntity<LlmAnalysis> updateAnalysis(@RequestBody LlmAnalysis analysis, @PathVariable int id) {
        try {
            LlmAnalysis updated = llmAnalysisService.updateAnalysis(id, analysis);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/llmanalysis/{id}")
    public ResponseEntity<Boolean> deleteAnalysis(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = llmAnalysisService.deleteAnalysis(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
