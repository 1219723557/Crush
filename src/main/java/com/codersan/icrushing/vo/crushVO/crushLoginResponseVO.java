package com.codersan.icrushing.vo.crushVO;

import java.util.Date;

public class crushLoginResponseVO {
    private String wechat;//返回你绑定的微信号
    private int wooer;//返回暗恋你的人数
    private int crushState;//你当前暗恋的状态，0代表未填写，1代表暗恋中，2代表已确定关系
    private String taWeChat;//返回ta的微信号
    private Date date;//开始日期

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public int getWooer() {
        return wooer;
    }

    public void setWooer(int wooer) {
        this.wooer = wooer;
    }

    public int getCrushState() {
        return crushState;
    }

    public void setCrushState(int crushState) {
        this.crushState = crushState;
    }

    public String getTaWeChat() {
        return taWeChat;
    }

    public void setTaWeChat(String taWeChat) {
        this.taWeChat = taWeChat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return wechat+wooer+taWeChat+crushState+date;
    }
}
