package com.codersan.icrushing.service.ewService;

import com.codersan.icrushing.domain.EW.ewAssistant;
import com.codersan.icrushing.domain.EW.ewBeBanner;
import com.codersan.icrushing.domain.EW.explainWall;
import com.codersan.icrushing.repository.EWDao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class wallAdminService {
    @Autowired
    private explainWallDao explainWallDao;
    @Autowired
    private ewBeBannerDao ewBeBannerDao;
    @Autowired
    private explainPostDao explainPostDao;
    @Autowired
    private ewKingDao ewKingDao;
    @Autowired
    private ewAssistantDao ewAssistantDao;
    @Autowired
    private epCommentDao epCommentDao;
    //返回表白墙名是否存在（测试通过）
    public boolean existExplainWall(String ewName){
        return explainWallDao.existsByName(ewName);
    }
    //创建表白墙服务(测试通过)
    public boolean creatExplainWall(String ewName,String avatar,String introduction,String admin){
        if(!explainWallDao.existsByName(ewName)){
            explainWallDao.save(new explainWall(ewName,avatar,introduction,admin,new Date(),false));
            return true;
        }else
            return false;
    }
    //更换表白墙头像(测试通过)
    public boolean alterAvatar(int ID,String admin,String avatarURL){
        if(explainWallDao.existsByEwIDAndAdmin(ID,admin)){
            explainWallDao.alterEWAvatar(avatarURL,ID);
            return true;
        }else {
            return false;
        }
    }
    //修改表白墙简介(测试通过)
    public boolean alterIntroduction(int ID,String admin,String introduction){
        if(explainWallDao.existsByEwIDAndAdmin(ID,admin)){
            explainWallDao.alterIntroduction(introduction,ID);
            return true;
        }
        return false;
    }
    //转让表白墙墙主(测试通过)
    public boolean alterAdmin(int ID,String oldAdmin,String newAdmin  ){
        if(explainWallDao.existsByEwIDAndAdmin(ID,oldAdmin)){
            explainWallDao.alterAdmin(newAdmin,ID);
            return true;
        }else
            return false;
    }
    //删除表白帖(测试通过)
    public boolean deletePost(int ewID,int epID,String admin){
        //判断是否是墙主
        if(explainWallDao.existsByEwIDAndAdmin(ewID,admin)){
            explainPostDao.deleteByEwIDAndEpID(ewID,epID);
            return true;
        }else
            return false;
    }
    //删除评论
    public boolean deleteComment(int commentID,int ewID,String admin){
        if(explainWallDao.existsByEwIDAndAdmin(ewID,admin)){
            epCommentDao.deleteByCID(commentID);
            return true;
        }
        return false;
    }
    //封禁某人
    public boolean banOne(int ewID,String openID,String admin){
        if(explainWallDao.existsByEwIDAndAdmin(ewID,admin) && !ewKingDao.existsByOpenID(openID) && (openID!=admin) && !ewBeBannerDao.existsByEwIDAndOpenID(ewID,openID)){
            //admin是墙主以及openID不是超管 以及不是封禁自己 以及此人尚未被封禁

            //如果封的是管理员,则撤销管理员身份
            if(ewAssistantDao.existsByEwIDAndOpenID(ewID,openID))
                ewAssistantDao.deleteByEwIDAndOpenID(ewID,openID);
            ewBeBannerDao.save(new ewBeBanner(ewID,openID,new Date(),admin));
            return true;
        }
        return false;
    }
    //解封某人
    public boolean unBanOne(int ewID,String openID,String admin){
        if(explainWallDao.existsByEwIDAndAdmin(ewID,admin) ){
            ewBeBannerDao.deleteByEwIDAndOpenID(ewID,openID);
            return true;
        }
        return false;
    }
    //设置墙墙助手
    public boolean setWallAssistant(int ewID,String assistant,String admin){
        /*
         * 先确定墙主身份
         * 确定对方不是超管
         * 对方尚未成为管理员
         * 管理员人数小于等于2
         */
        if(explainWallDao.existsByEwIDAndAdmin(ewID,admin) && !ewKingDao.existsByOpenID(assistant) && !ewAssistantDao.existsByEwIDAndOpenID(ewID,assistant)&&(ewAssistantDao.countByEwID(ewID)<=2)){
            ewAssistantDao.save(new ewAssistant(ewID,assistant));
            return true;
        }
        return false;
    }
    //撤销墙墙助手
    public boolean deleteWallAssistant(int ewID,String assistant,String admin){
        /*
         * 先确定墙主身份
         * 确定管理员存在
         */
        if(explainWallDao.existsByEwIDAndAdmin(ewID,admin)){
            ewAssistantDao.deleteByEwIDAndOpenID(ewID,assistant);
            return true;
        }
        return false;
    }
}
