package com.springboot.MyTodoList.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "USER_CREDENTIALS")
public class UserCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREDENTIAL_ID")
    private int credentialId;

    @Column(name = "USER_ID", nullable = false, unique = true)
    private int userId;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "PASSWORD_SALT", nullable = false)
    private String passwordSalt;

    @Column(name = "LAST_LOGIN")
    private LocalDateTime lastLogin;

    @Column(name = "FAILED_ATTEMPTS")
    private int failedAttempts;

    @Column(name = "ACCOUNT_LOCKED")
    private String accountLocked;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public UserCredential() {}

    public UserCredential(int credentialId, int userId, String username, String passwordHash,
                          String passwordSalt, LocalDateTime lastLogin, int failedAttempts,
                          String accountLocked, LocalDateTime createdAt, String isDeleted,
                          LocalDateTime deletedAt) {
        this.credentialId = credentialId;
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.lastLogin = lastLogin;
        this.failedAttempts = failedAttempts;
        this.accountLocked = accountLocked;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.deletedAt = deletedAt;
    }

    public int getCredentialId() { return credentialId; }
    public void setCredentialId(int credentialId) { this.credentialId = credentialId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getPasswordSalt() { return passwordSalt; }
    public void setPasswordSalt(String passwordSalt) { this.passwordSalt = passwordSalt; }
    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }
    public int getFailedAttempts() { return failedAttempts; }
    public void setFailedAttempts(int failedAttempts) { this.failedAttempts = failedAttempts; }
    public String getAccountLocked() { return accountLocked; }
    public void setAccountLocked(String accountLocked) { this.accountLocked = accountLocked; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getIsDeleted() { return isDeleted; }
    public void setIsDeleted(String isDeleted) { this.isDeleted = isDeleted; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
    public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt; }

    @Override
    public String toString() {
        return "UserCredential{credentialId=" + credentialId + ", userId=" + userId + ", username='" + username + "'}";
    }
}
