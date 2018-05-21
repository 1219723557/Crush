package com.codersan.icrushing.vo.crushVO;

import com.codersan.icrushing.domain.Crush.history;

import java.util.ArrayList;

public class queryOneResponseVO {
    private String nickName;
    private String avatar;
    private boolean beBanned;
    private boolean seeWooer;
    private int wooer;
    private boolean seeState;
    private int State;
    private ArrayList<history> histories;

    public boolean isBeBanned() {
        return beBanned;
    }

    public void setBeBanned(boolean beBanned) {
        this.beBanned = beBanned;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isSeeWooer() {
        return seeWooer;
    }

    public void setSeeWooer(boolean seeWooer) {
        this.seeWooer = seeWooer;
    }

    public int getWooer() {
        return wooer;
    }

    public void setWooer(int wooer) {
        this.wooer = wooer;
    }

    public boolean isSeeState() {
        return seeState;
    }

    public void setSeeState(boolean seeState) {
        this.seeState = seeState;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return nickName+avatar+beBanned+seeWooer+wooer+seeState+State;
    }
}
