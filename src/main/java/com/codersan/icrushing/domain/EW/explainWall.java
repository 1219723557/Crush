package com.codersan.icrushing.domain.EW;

import javax.persistence.*;
import java.util.Date;

@Entity
public class explainWall {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ewID;
    @Column(length = 32,nullable = false)
    private String name;
    @Column(length = 127,nullable = false)
    private String avatar;
    @Column(length = 256)
    private String introduction;
    @Column(length = 32,nullable = false)
    private String admin;
    @Column(nullable = false)
    private Date creatDate;
    @Column(nullable = false)
    private boolean beBanned;//

    public explainWall() {
    }

    public explainWall(String name, String avatar, String introduction, String admin, Date creatDate, boolean beBanned) {
        this.name = name;
        this.avatar = avatar;
        this.introduction = introduction;
        this.admin = admin;
        this.creatDate = creatDate;
        this.beBanned = beBanned;
    }

    public boolean isBeBanned() {
        return beBanned;
    }

    public void setBeBanned(boolean beBanned) {
        this.beBanned = beBanned;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public int getEwID() {
        return ewID;
    }

    public void setEwID(int ewID) {
        this.ewID = ewID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        admin = admin;
    }

}
