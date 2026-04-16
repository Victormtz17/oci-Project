package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.KpiValue;
import com.springboot.MyTodoList.repository.KpiValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KpiValueService {

    @Autowired
    private KpiValueRepository kpiValueRepository;

    public List<KpiValue> findAll() {
        return kpiValueRepository.findAll();
    }

    public ResponseEntity<KpiValue> getById(int id) {
        Optional<KpiValue> data = kpiValueRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public KpiValue getByIdSimple(int id) {
        Optional<KpiValue> data = kpiValueRepository.findById(id);
        return data.orElse(null);
    }

    public KpiValue addKpiValue(KpiValue kpiValue) {
        return kpiValueRepository.save(kpiValue);
    }

    public boolean deleteKpiValue(int id) {
        try {
            kpiValueRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public KpiValue updateKpiValue(int id, KpiValue updated) {
        Optional<KpiValue> data = kpiValueRepository.findById(id);
        if (data.isPresent()) {
            KpiValue kpiValue = data.get();
            kpiValue.setKpiTypeId(updated.getKpiTypeId());
            kpiValue.setScopeType(updated.getScopeType());
            kpiValue.setUserId(updated.getUserId());
            kpiValue.setProjectId(updated.getProjectId());
            kpiValue.setSprintId(updated.getSprintId());
            kpiValue.setValue(updated.getValue());
            kpiValue.setRecordedAt(updated.getRecordedAt());
            return kpiValueRepository.save(kpiValue);
        } else {
            return null;
        }
    }
}
