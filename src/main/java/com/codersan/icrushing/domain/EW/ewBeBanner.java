package com.codersan.icrushing.domain.EW;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

/*
 * 被特定表白墙封禁的名单
 */
@Entity
@IdClass(ewBeBanner.ewBeBannerPK.class)
public class ewBeBanner {
    @Id
    private int ewID;
    @Id
    private String openID;
    @Column(nullable = false)
    private Date banDate;
    @Column(nullable = false)
    private String executor;

    public ewBeBanner() {
    }

    public ewBeBanner(int ewID, String openID, Date banDate, String executor) {
        this.ewID = ewID;
        this.openID = openID;
        this.banDate = banDate;
        this.executor = executor;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Date getBanDate() {
        return banDate;
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate;
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

    public static class ewBeBannerPK implements Serializable{
        private static final long serialVersionUID = 1L;
        private int ewID;
        private String openID;

        public ewBeBannerPK() {
        }

        public ewBeBannerPK(int ewID, String openID) {
            this.ewID = ewID;
            this.openID = openID;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
