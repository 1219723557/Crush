package com.codersan.icrushing.vo.crushVO;

public class crushSettingVO {
    private String phone;
    private String QQ;
    private boolean seeWooer;
    private boolean seeState;
    private boolean seeEW;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
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

    public boolean isSeeEW() {
        return seeEW;
    }

    public void setSeeEW(boolean seeEW) {
        this.seeEW = seeEW;
    }
}
