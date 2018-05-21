package com.codersan.icrushing.vo.loginVO;

public class loginResponseVO {
    private String openID;
    private boolean beBanned;

    public loginResponseVO(String openID, boolean beBanned) {
        this.openID = openID;
        this.beBanned = beBanned;
    }

    public String getOpenID() {
        return openID;
    }

    public void setOpenID(String openID) {
        this.openID = openID;
    }

    public boolean isBeBanned() {
        return beBanned;
    }

    public void setBeBanned(boolean beBanned) {
        this.beBanned = beBanned;
    }
}
