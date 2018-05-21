package com.codersan.icrushing.service.ewService;

import com.codersan.icrushing.domain.EW.*;
import com.codersan.icrushing.domain.User;
import com.codersan.icrushing.repository.EWDao.*;
import com.codersan.icrushing.repository.UserDao;
import com.codersan.icrushing.vo.ewVO.explainPost.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * 普通用户服务服务
 */
@Service
public class ordinaryUserService {
    @Autowired
    private explainPostDao explainPostDao;
    @Autowired
    private epZanDao epZanDao;
    @Autowired
    private epCommentDao epCommentDao;

    @Autowired
    private ewAssistantDao ewAssistantDao;
    @Autowired
    private explainWallDao explainWallDao;
    @Autowired
    private explainWallFollowDao explainWallFollowDao;
    @Autowired
    private UserDao userDao;
    //获取你创建的表白墙
    public List<explainWall> wallYouCreat(String openID){
        return explainWallDao.findByAdmin(openID);
    }
    //获取你管理的表白墙
    public List<explainWall> wallYouAssist(String openID){
        List<ewAssistant> li = ewAssistantDao.findByOpenID(openID);
        ArrayList<explainWall> res = new ArrayList<>();
        for(int i=0;i<li.size();i++){
            res.add(explainWallDao.findByEwID(li.get(i).getEwID()));
        }
        return res;
    }
    //获取你关注的表白墙
    public List<explainWall> wallYouFollow(String openID){
        List<explainWallFollow> li = explainWallFollowDao.findByFollowFromUserID(openID);
        ArrayList<explainWall> res = new ArrayList<>();
        for(int i=0;i<li.size();i++){
            res.add(explainWallDao.findByEwID(li.get(i).getEwID()));
        }
        return res;
    }
    //判断表白墙是否存在
    public boolean checkExistWall(String name){
        return explainWallDao.existsByName(name);
    }
    //根据name获取表白墙的ewID
    public int getEWIDByName(String name){
        return explainWallDao.findByName(name).getEwID();
    }
    //查找墙墙
    public List<explainWall> wallYouQuery(String name){
        return explainWallDao.findByNameContaining(name);
    }
    //获取表白墙的基本信息
    public explainWall getEW(int ewID){
        return explainWallDao.findByEwID(ewID);
    }
    //判断openID是否关注了某表白墙
    public boolean checkFollow(int ewID,String openID){
        return explainWallFollowDao.existsById(new explainWallFollow.explainWallFollowPK(ewID,openID));
    }
    //关注或者取关表白墙
    public boolean followEW(int ewID,String openID){
            if(explainWallFollowDao.existsById(new explainWallFollow.explainWallFollowPK(ewID,openID))){
                explainWallFollowDao.deleteById(new explainWallFollow.explainWallFollowPK(ewID,openID));
                return false;
            }else{
                explainWallFollowDao.save(new explainWallFollow(ewID,openID));
                return true;
            }
    }
    //获取某帖子的评论和回复的数量
    public int getMessageNumber(int epID){
        return epCommentDao.countByEpID(epID);
    }
    //获取某帖子的点赞数
    public int getZanNumber(int epID){
        return epZanDao.countByEpID(epID);
    }
    //获取指定ewID的前十条帖子
    public List<explainPostVO> getOldPost(int ewID,Date date){
        ArrayList<explainPostVO> res = new ArrayList<>();
        List<explainPost> ep = explainPostDao.findTop10ByEwIDAndDateBeforeOrderByDateDesc(ewID,date);
        for(int i = 0;i<ep.size();i++){
            explainPostVO epv = new explainPostVO();
            epv.setComment(getMessageNumber(ep.get(i).getEpID()));
            epv.setZan(numberOfZan(ep.get(i).getEpID()));
            if(!ep.get(i).isAnonymous()){
                User user = userDao.findByOpenID(ep.get(i).getFromUserID());
                epv.setAvatarUrl(user.getAvatar());
                epv.setNickName(user.getUserName());
            }else{
                ep.get(i).setFromUserID("");
            }
            epv.setEp(ep.get(i));
            res.add(epv);
        }
        return res;
    }
    public List<explainPostVO> getNewPost(int ewID,Date date){
        ArrayList<explainPostVO> res = new ArrayList<>();
        List<explainPost> ep = explainPostDao.findByEwIDAndDateAfterOrderByDateDesc(ewID,date);
        for(int i = 0;i<ep.size();i++){
            explainPostVO epv = new explainPostVO();
            epv.setComment(getMessageNumber(ep.get(i).getEpID()));
            epv.setZan(numberOfZan(ep.get(i).getEpID()));
            if(!ep.get(i).isAnonymous()){
                User user = userDao.findByOpenID(ep.get(i).getFromUserID());
                epv.setAvatarUrl(user.getAvatar());
                epv.setNickName(user.getUserName());
            }else{
                ep.get(i).setFromUserID("");
            }
            epv.setEp(ep.get(i));
            res.add(epv);
        }
        return res;
    }
    //发帖(测试通过)
    public void sendPost(String text,String image,int ewID,String openID,boolean anonymous){
        explainPostDao.save(new explainPost(ewID,anonymous,text,openID,image,new Date()));
    }
    //删帖,true为已删除（测试通过）
    public boolean deletePost(int ewID,int epID,String openID){
        //判断是否是发帖人
        if(explainPostDao.existsByEpIDAndEwIDAndFromUserID(epID,ewID,openID)){
            explainPostDao.deleteByEwIDAndEpID(ewID,epID);
            return true;
        }
        return false;
    }
    //点赞服务，点赞为true，取消点赞为false（测试通过）
    public boolean Zan(int epID,String openID){
        //判断是否已经点赞了
        if(epZanDao.existsByEpIDAndFromUserID(epID,openID)){
            //是的话删除
            epZanDao.delete(new epZan(epID,openID));
            return false;
        }else{
            //否则增加
            epZanDao.save(new epZan(epID,openID));
            return true;
        }
    }
    //评论
    public void comment(int epID, String content, String fromUserID){
        epCommentDao.save(new epComment(epID,content,new Date(),fromUserID));
    }
    //删除评论
    public boolean deleteComment(int commentID,String fromUserID){
        //首先判断是否是发评论的人
        if(epCommentDao.existsByCIDAndFromUserID(commentID,fromUserID)){
            epCommentDao.deleteByCID(commentID);
            return true;
        }
        return false;
    }
    //举报
    //获取帖子的所有评论与回复
    public ArrayList<epCommentDetail> postDetail(int epID){
        ArrayList<epCommentDetail> res = new ArrayList<>();
        List<epComment> epc = epCommentDao.findByEpIDOrderByCommentDateAsc(epID);
        for(int i=0;i<epc.size();i++){
            epCommentDetail epCommentDetail = new epCommentDetail();
                epCommentDetail.setEpc(epc.get(i));
                User user = userDao.getOne(epc.get(i).getFromUserID());
                epCommentDetail.setFromUserName(user.getUserName());//再解决回复问题
                epCommentDetail.setFromUserAvatar(user.getAvatar());
            res.add(epCommentDetail);
        }
        return res;
    }
    //墙墙关注数
    public int numberOfFollow(int ewID){return explainWallFollowDao.countByEwID(ewID);}
    //墙墙发帖数(测试通过)
    public int numberOfPost(int ewID){
        return explainPostDao.countByEwID(ewID);
    }
    //帖子点赞数(测试通过)
    public int numberOfZan(int epID){return epZanDao.countByEpID(epID);}
}
