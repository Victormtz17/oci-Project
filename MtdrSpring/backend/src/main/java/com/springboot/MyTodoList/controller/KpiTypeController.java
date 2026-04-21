package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.KpiType;
import com.springboot.MyTodoList.service.KpiTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KpiTypeController {

    @Autowired
    private KpiTypeService kpiTypeService;

    @GetMapping(value = "/kpitypes")
    public List<KpiType> getAllKpiTypes() {
        return kpiTypeService.findAll();
    }

    @GetMapping(value = "/kpitypes/{id}")
    public ResponseEntity<KpiType> getKpiTypeById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(kpiTypeService.getById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/kpitypes")
    public ResponseEntity<KpiType> addKpiType(@RequestBody KpiType kpiType) {
        KpiType saved = kpiTypeService.addKpiType(kpiType);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getKpiTypeId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/kpitypes/{id}")
    public ResponseEntity<KpiType> updateKpiType(@RequestBody KpiType kpiType, @PathVariable int id) {
        try {
            KpiType updated = kpiTypeService.updateKpiType(id, kpiType);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/kpitypes/{id}")
    public ResponseEntity<Boolean> deleteKpiType(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = kpiTypeService.deleteKpiType(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
