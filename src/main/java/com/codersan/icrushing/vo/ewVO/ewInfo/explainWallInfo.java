package com.codersan.icrushing.vo.ewVO.ewInfo;

public class explainWallInfo {
    private int ewID;//表白墙唯一ID
    private String ewName;//表白墙名字
    private String avatarUrl;//头像地址
    private String ewIntroduction;//表白墙简介
    private int followeNumber;//关注数
    private int postNumber;//帖子数

    public int getEwID() {
        return ewID;
    }

    public void setEwID(int ewID) {
        this.ewID = ewID;
    }

    public String getEwName() {
        return ewName;
    }

    public void setEwName(String ewName) {
        this.ewName = ewName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEwIntroduction() {
        return ewIntroduction;
    }

    public void setEwIntroduction(String ewIntroduction) {
        this.ewIntroduction = ewIntroduction;
    }

    public int getFolloweNumber() {
        return followeNumber;
    }

    public void setFolloweNumber(int followeNumber) {
        this.followeNumber = followeNumber;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }
}
