package com.vcl0000.data.model;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by vcl0000 on 2017/3/25.
 */
@Repository
public class User {

    private String userId;
    private String userName;
    private String passWord;
    private BigDecimal userAge;
    private String flg;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public BigDecimal getUserAge() {
        return userAge;
    }

    public void setUserAge(BigDecimal userAge) {
        this.userAge = userAge;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userAge=" + userAge +
                ", flg='" + flg + '\'' +
                '}';
    }
}
