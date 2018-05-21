package com.codersan.icrushing.service.ewService;

import com.codersan.icrushing.domain.EW.ewBeBanner;
import com.codersan.icrushing.repository.EWDao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/*
 * 墙墙助手的服务
 */
@Service
public class wallAssistantService {
    @Autowired
    private explainPostDao explainPostDao;
    @Autowired
    private ewBeBannerDao ewBeBannerDao;
    @Autowired
    private ewAssistantDao ewAssistantDao;
    @Autowired
    private ewKingDao ewKingDao;
    @Autowired
    private explainWallDao explainWallDao;
    @Autowired
    private epCommentDao epCommentDao;
    //删除表白帖
    public boolean deletePost(int ewID,int epID,String assistant){
        //判断是否是管理员
        if(ewAssistantDao.existsByEwIDAndOpenID(ewID,assistant)){
            explainPostDao.deleteByEwIDAndEpID(ewID,epID);
            return true;
        }else
            return false;
    }
    //删除评论
    public boolean deleteComment(int ewID,int commentID,String assistant){
        if(ewAssistantDao.existsByEwIDAndOpenID(ewID,assistant)){
            epCommentDao.deleteByCID(commentID);
            return true;
        }
        return false;
    }

    //封禁某人
    public boolean banOne(int ewID,String openID,String assistant){
        if(ewAssistantDao.existsByEwIDAndOpenID(ewID,assistant) && !ewAssistantDao.existsByEwIDAndOpenID(ewID,openID) && !ewKingDao.existsByOpenID(openID) && !explainWallDao.existsByEwIDAndAdmin(ewID,openID)){
            //assistant是墙墙助手以及openID不是超管，也不是墙主 也不是墙墙助手
            ewBeBannerDao.save(new ewBeBanner(ewID,openID,new Date(),assistant));
            return true;
        }
        return false;
    }
    //解封某人
    public boolean unBanOne(int ewID,String openID,String assistant){
        if(ewAssistantDao.existsByEwIDAndOpenID(ewID,assistant) && ewBeBannerDao.existsByEwIDAndOpenID(ewID,openID)){
            ewBeBannerDao.deleteByEwIDAndOpenID(ewID,openID);
            return true;
        }
        return false;
    }
}
