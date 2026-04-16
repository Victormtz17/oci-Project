package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.KpiType;
import com.springboot.MyTodoList.repository.KpiTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KpiTypeService {

    @Autowired
    private KpiTypeRepository kpiTypeRepository;

    public List<KpiType> findAll() {
        return kpiTypeRepository.findAll();
    }

    public ResponseEntity<KpiType> getById(int id) {
        Optional<KpiType> data = kpiTypeRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public KpiType getByIdSimple(int id) {
        Optional<KpiType> data = kpiTypeRepository.findById(id);
        return data.orElse(null);
    }

    public KpiType addKpiType(KpiType kpiType) {
        return kpiTypeRepository.save(kpiType);
    }

    public boolean deleteKpiType(int id) {
        try {
            kpiTypeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public KpiType updateKpiType(int id, KpiType updated) {
        Optional<KpiType> data = kpiTypeRepository.findById(id);
        if (data.isPresent()) {
            KpiType kpiType = data.get();
            kpiType.setName(updated.getName());
            kpiType.setDescription(updated.getDescription());
            kpiType.setCategory(updated.getCategory());
            kpiType.setUnit(updated.getUnit());
            return kpiTypeRepository.save(kpiType);
        } else {
            return null;
        }
    }
}
