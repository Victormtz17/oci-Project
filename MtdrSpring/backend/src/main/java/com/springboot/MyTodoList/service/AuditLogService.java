package com.springboot.MyTodoList.service;

import com.springboot.MyTodoList.model.AuditLog;
import com.springboot.MyTodoList.repository.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public List<AuditLog> findAll() {
        return auditLogRepository.findAll();
    }

    public ResponseEntity<AuditLog> getById(int id) {
        Optional<AuditLog> data = auditLogRepository.findById(id);
        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public AuditLog getByIdSimple(int id) {
        Optional<AuditLog> data = auditLogRepository.findById(id);
        return data.orElse(null);
    }

    public AuditLog addAuditLog(AuditLog auditLog) {
        return auditLogRepository.save(auditLog);
    }

    public boolean deleteAuditLog(int id) {
        try {
            auditLogRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public AuditLog updateAuditLog(int id, AuditLog updated) {
        Optional<AuditLog> data = auditLogRepository.findById(id);
        if (data.isPresent()) {
            AuditLog auditLog = data.get();
            auditLog.setUserId(updated.getUserId());
            auditLog.setActionType(updated.getActionType());
            auditLog.setEntityName(updated.getEntityName());
            auditLog.setEntityId(updated.getEntityId());
            auditLog.setActionDate(updated.getActionDate());
            auditLog.setIpAddress(updated.getIpAddress());
            return auditLogRepository.save(auditLog);
        } else {
            return null;
        }
    }
}
