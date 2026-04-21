package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.KpiValue;
import com.springboot.MyTodoList.service.KpiValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KpiValueController {

    @Autowired
    private KpiValueService kpiValueService;

    @GetMapping(value = "/kpivalues")
    public List<KpiValue> getAllKpiValues() {
        return kpiValueService.findAll();
    }

    @GetMapping(value = "/kpivalues/{id}")
    public ResponseEntity<KpiValue> getKpiValueById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(kpiValueService.getById(id).getBody(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/kpivalues")
    public ResponseEntity<KpiValue> addKpiValue(@RequestBody KpiValue kpiValue) {
        KpiValue saved = kpiValueService.addKpiValue(kpiValue);
        HttpHeaders headers = new HttpHeaders();
        headers.set("location", String.valueOf(saved.getKpiValueId()));
        headers.set("Access-Control-Expose-Headers", "location");
        return ResponseEntity.ok().headers(headers).build();
    }

    @PutMapping(value = "/kpivalues/{id}")
    public ResponseEntity<KpiValue> updateKpiValue(@RequestBody KpiValue kpiValue, @PathVariable int id) {
        try {
            KpiValue updated = kpiValueService.updateKpiValue(id, kpiValue);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/kpivalues/{id}")
    public ResponseEntity<Boolean> deleteKpiValue(@PathVariable int id) {
        Boolean flag = false;
        try {
            flag = kpiValueService.deleteKpiValue(id);
            return new ResponseEntity<>(flag, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(flag, HttpStatus.NOT_FOUND);
        }
    }
}
