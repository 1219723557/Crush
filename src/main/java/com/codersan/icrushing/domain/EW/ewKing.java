package com.codersan.icrushing.domain.EW;

import javax.persistence.*;

@Entity
public class ewKing {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int kingID;
    @Column(nullable = false)
    private String openID;

    public ewKing() {
    }

    public ewKing(String openID) {
        this.openID = openID;
    }

    public int getKingID() {
        return kingID;
    }

    public void setKingID(int kingID) {
        this.kingID = kingID;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }
}
