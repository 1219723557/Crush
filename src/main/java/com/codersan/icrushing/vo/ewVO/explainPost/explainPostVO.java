package com.codersan.icrushing.vo.ewVO.explainPost;

import com.codersan.icrushing.domain.EW.explainPost;

public class explainPostVO {
    private explainPost ep;
    private int comment;
    private int Zan;
    private String avatarUrl;
    private String nickName;

    public explainPostVO() {
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public explainPost getEp() {
        return ep;
    }

    public void setEp(explainPost ep) {
        this.ep = ep;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getZan() {
        return Zan;
    }

    public void setZan(int zan) {
        Zan = zan;
    }
}
