package com.codersan.icrushing.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @Column(length = 32,nullable = false)
    String openID;//openID
    @Column(length = 127,nullable = false)
    String avatar;//头像地址
    @Column(length = 32,nullable = false)
    String userName;//用户昵称
    boolean beBanned ;//false表示被封

    public User() {
    }

    public User( String userName,String avatar) {
        this.avatar = avatar;
        this.userName = userName;
    }

    public boolean isBeBanned() {
        return beBanned;
    }

    public void setBeBanned(boolean beBanned) {
        this.beBanned = beBanned;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
