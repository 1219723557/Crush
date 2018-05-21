package com.codersan.icrushing.vo.ewVO.ewInfo;

import com.codersan.icrushing.domain.EW.explainWall;

public class explainWallAllInfo {
    private explainWall ew;
    private boolean follow;
    private int followNumber;
    private int postNumber;

    public explainWallAllInfo() {
    }

    public explainWallAllInfo(explainWall ew, boolean follow, int followNumber, int postNumber) {
        this.ew = ew;
        this.follow = follow;
        this.followNumber = followNumber;
        this.postNumber = postNumber;
    }

    public explainWall getEw() {
        return ew;
    }

    public void setEw(explainWall ew) {
        this.ew = ew;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }

    public int getFollowNumber() {
        return followNumber;
    }

    public void setFollowNumber(int followNumber) {
        this.followNumber = followNumber;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }
}
