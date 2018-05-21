package com.codersan.icrushing.domain.EW;

import javax.persistence.*;

@Entity
public class ewAssistant {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    int assistantID;
    @Column(nullable = false)
    private int ewID;//所属墙墙号
    @Column(nullable = false)
    private String openID;

    public ewAssistant() {
    }

    public ewAssistant(int ewID, String openID) {
        this.ewID = ewID;
        this.openID = openID;
    }

    public int getAssistantID() {
        return assistantID;
    }

    public void setAssistantID(int assistantID) {
        this.assistantID = assistantID;
    }

    public int getEwID() {
        return ewID;
    }

    public void setEwID(int ewID) {
        this.ewID = ewID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }
}
