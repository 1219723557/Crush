package com.codersan.icrushing.vo.ewVO.explainPost;

import com.codersan.icrushing.domain.EW.epComment;

public class epCommentDetail {
    private epComment epc;
    private String fromUserName;
    private String fromUserAvatar;

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    public epComment getEpc() {

        return epc;
    }

    public void setEpc(epComment epc) {
        this.epc = epc;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
}
