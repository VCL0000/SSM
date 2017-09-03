package com.vcl0000.data.model;

import org.springframework.stereotype.Repository;

@Repository
public class UserInfo {
    private String userId;

    private String userAdrr;

    private String userTel;

    private String userMail;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserAdrr() {
        return userAdrr;
    }

    public void setUserAdrr(String userAdrr) {
        this.userAdrr = userAdrr == null ? null : userAdrr.trim();
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail == null ? null : userMail.trim();
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userAdrr='" + userAdrr + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userMail='" + userMail + '\'' +
                '}';
    }
}