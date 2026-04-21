package com.springboot.MyTodoList.controller;

import com.springboot.MyTodoList.model.AuditLog;
import com.springboot.MyTodoList.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping(value = "/auditlogs")
    public List<AuditLog> getAllAuditLogs() {
        return auditLogService.findAll();
    }

    @GetMapping(value = "/auditlogs/{id}")
    public ResponseEntity<AuditLog> getAuditLogById(@PathVariable int id) {
        return auditLogService.getById(id);
    }

    // AuditLog es inmutable — no se expone PUT ni DELETE
    @PostMapping(value = "/auditlogs")
    public ResponseEntity<AuditLog> addAuditLog(@RequestBody AuditLog auditLog) {
        AuditLog saved = auditLogService.addAuditLog(auditLog);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
