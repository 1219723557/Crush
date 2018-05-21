package com.codersan.icrushing.vo.ewVO;

import com.codersan.icrushing.domain.EW.explainWall;
import com.codersan.icrushing.vo.ewVO.ewInfo.simpleExplainWallInfo;

import java.util.List;


public class wallYouListVO {
    private List<explainWall> wallYouCreat;
    private List<explainWall> wallYouAssist;
    private List<explainWall> wallYouFollow;

    public wallYouListVO() {
    }

    public List<explainWall> getWallYouCreat() {
        return wallYouCreat;
    }

    public void setWallYouCreat(List<explainWall> wallYouCreat) {
        this.wallYouCreat = wallYouCreat;
    }

    public List<explainWall> getWallYouAssist() {
        return wallYouAssist;
    }

    public void setWallYouAssist(List<explainWall> wallYouAssist) {
        this.wallYouAssist = wallYouAssist;
    }

    public List<explainWall> getWallYouFollow() {
        return wallYouFollow;
    }

    public void setWallYouFollow(List<explainWall> wallYouFollow) {
        this.wallYouFollow = wallYouFollow;
    }
}
