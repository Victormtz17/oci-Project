package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.LlmAnalysis;
import com.springboot.MyTodoList.repository.LlmAnalysisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LlmAnalysisService {

    @Autowired
    private LlmAnalysisRepository llmAnalysisRepository;

    public List<LlmAnalysis> findAll() {
        return llmAnalysisRepository.findAll();
    }

    public ResponseEntity<LlmAnalysis> getById(int id) {
        Optional<LlmAnalysis> data = llmAnalysisRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public LlmAnalysis getByIdSimple(int id) {
        Optional<LlmAnalysis> data = llmAnalysisRepository.findById(id);
        return data.orElse(null);
    }

    public LlmAnalysis addAnalysis(LlmAnalysis analysis) {
        return llmAnalysisRepository.save(analysis);
    }

    public boolean deleteAnalysis(int id) {
        try {
            llmAnalysisRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public LlmAnalysis updateAnalysis(int id, LlmAnalysis updated) {
        Optional<LlmAnalysis> data = llmAnalysisRepository.findById(id);
        if (data.isPresent()) {
            LlmAnalysis analysis = data.get();
            analysis.setScopeType(updated.getScopeType());
            analysis.setUserId(updated.getUserId());
            analysis.setProjectId(updated.getProjectId());
            analysis.setSprintId(updated.getSprintId());
            analysis.setAnomalyDetected(updated.getAnomalyDetected());
            analysis.setAnomalyType(updated.getAnomalyType());
            analysis.setConfidenceScore(updated.getConfidenceScore());
            analysis.setRecommendation(updated.getRecommendation());
            analysis.setAnalysisDate(updated.getAnalysisDate());
            return llmAnalysisRepository.save(analysis);
        } else {
            return null;
        }
    }
}
