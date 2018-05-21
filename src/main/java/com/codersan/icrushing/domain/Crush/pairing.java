package com.codersan.icrushing.domain.Crush;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

@Entity
@IdClass(pairing.pairingPK.class)
public class pairing {
    @Id
    private String firstWechat;//申请人
    @Id
    private String secondWechat;//对方
    @Column(nullable = false)
    private boolean state;//状态，0表示暗恋，1表示交往
    @Column(nullable = false)
    private Date alterDate;//申请日期

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(Date alterDate) {
        this.alterDate = alterDate;
    }

    public static class pairingPK implements Serializable {
        private static final long serialVersionUID = 1L;
        @Column(length = 32,nullable = false)
        private String firstWechat;
        @Column(length = 32,nullable = false)
        private String secondWechat;

        public pairingPK() {
        }

        public pairingPK(String firstWechat, String secondWechat) {
            this.firstWechat = firstWechat;
            this.secondWechat = secondWechat;
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
