package com.codersan.icrushing.domain.Crush;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class info {
    @Id
    @Column(length = 32)
    private String openID;//openID
    @Column(length = 32)
    private String wechat;//绑定的微信号
    private Date wechatAlterDate;//记录修改微信号的时间
    @Column(length = 11)
    private String QQ;
    @Column(length = 11)
    private String phone;
    @Column(nullable = false)
    private boolean seeWooer;//能否看到追求者数目
    @Column(nullable = false)
    private boolean seeState;//能否看到状态
    @Column(nullable = false)
    private boolean seeEW;
    @Column(nullable = false)
    private int state;//0代表什么也没有，1代表正在暗恋别人，2代表正在追求别人


    public String getOpenID() {
        return openID;
    }

    public info() {
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Date getWechatAlterDate() {
        return wechatAlterDate;
    }

    public void setWechatAlterDate(Date wechatAlterDate) {
        this.wechatAlterDate = wechatAlterDate;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isSeeWooer() {
        return seeWooer;
    }

    public void setSeeWooer(boolean seeWooer) {
        this.seeWooer = seeWooer;
    }

    public boolean isSeeState() {
        return seeState;
    }

    public void setSeeState(boolean seeState) {
        this.seeState = seeState;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isSeeEW() {
        return seeEW;
    }

    public void setSeeEW(boolean seeEW) {
        this.seeEW = seeEW;
    }
}
