package com.codersan.icrushing.domain.EW;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(explainPost.explainPostPK.class)
public class explainPost {
    @Id
    private int epID;//表白帖编号
    @Id
    private int ewID;//所属表白墙
    @Column(nullable = false)
    private boolean anonymous;//是否匿名，true为匿名，false为不匿名
    @Column(nullable = false)
    private String text;//内容
    @Column(length = 32,nullable = false)
    private String fromUserID;//发帖人
    @Column(length = 127)
    private String image;
    @Column(nullable = false)
    private Date date;//发帖日期

    public explainPost() {
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public explainPost(int ewID, boolean anonymous, String text, String fromUserID, String image, Date date) {
        this.ewID = ewID;
        this.anonymous = anonymous;
        this.text = text;
        this.fromUserID = fromUserID;
        this.image = image;
        this.date = date;
    }

    public int getEpID() {
        return epID;
    }

    void setEpID(int epID) {
        this.epID = epID;
    }

    public int getEwID() {
        return ewID;
    }

    public void setEwID(int ewID) {
        this.ewID = ewID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class explainPostPK implements Serializable{
        private static final long serialVersionUID = 1L;
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int epID;
        private int ewID;

        public explainPostPK() {
        }

        public explainPostPK(int epID, int ewID) {
            this.epID = epID;
            this.ewID = ewID;
        }

        public int getEpID() {
            return epID;
        }

        public void setEpID(int epID) {
            this.epID = epID;
        }

        public int getEwID() {
            return ewID;
        }

        public void setEwID(int ewID) {
            this.ewID = ewID;
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
