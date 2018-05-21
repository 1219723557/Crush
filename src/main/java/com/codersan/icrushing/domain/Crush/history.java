package com.codersan.icrushing.domain.Crush;

import javax.persistence.*;
import java.util.Date;

@Entity
public class history {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @Column(length = 32,nullable = false)
    private String firstWechat;
    @Column(length = 32,nullable = false)
    private String secondWechat;
    @Column(nullable = false)
    private Date beginDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private boolean lastState;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstWechat() {
        return firstWechat;
    }

    public void setFirstWechat(String firstWechat) {
        this.firstWechat = firstWechat;
    }

    public String getSecondWechat() {
        return secondWechat;
    }

    public void setSecondWechat(String secondWechat) {
        this.secondWechat = secondWechat;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isLastState() {
        return lastState;
    }

    public void setLastState(boolean lastState) {
        this.lastState = lastState;
    }

    @Override
    public String toString() {
        return ID+firstWechat+secondWechat+beginDate+endDate+lastState;
    }
}
