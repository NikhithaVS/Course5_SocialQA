package com.upgrad.quora.api.model;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user_auth")
public class UserAuth {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "user_id")
    private int userID;

    public UserAuth(int userID, String accessToken, Date expiresAt, Date loginAt) {

        this.uuid = UUID.randomUUID().toString();
        this.userID = userID;
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
        this.loginAt = loginAt;
    }

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "expires_at")
    private Date expiresAt;

    @Column(name = "login_at")
    private Date loginAt;

    @Column(name = "logout_at")
    private Date logoutAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Date getLoginAt() {
        return loginAt;
    }

    public void setLoginAt(Date loginAt) {
        this.loginAt = loginAt;
    }

    public UserAuth() {
    }

    public Date getLogoutAt() {
        return logoutAt;
    }

    public void setLogoutAt(Date logoutAt) {
        this.logoutAt = logoutAt;
    }
}
