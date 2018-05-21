package com.codersan.icrushing.Controller;

import com.codersan.icrushing.domain.EW.explainPost;
import com.codersan.icrushing.domain.EW.explainWall;
import com.codersan.icrushing.service.LoginService;
import com.codersan.icrushing.service.ThirdPartyService.AliyunService;
import com.codersan.icrushing.service.ThirdPartyService.cosService;
import com.codersan.icrushing.service.ewService.ordinaryUserService;
import com.codersan.icrushing.service.ewService.wallAdminService;
import com.codersan.icrushing.vo.ewVO.explainPost.epCommentDetail;
import com.codersan.icrushing.vo.ewVO.explainPost.explainPostVO;
import com.codersan.icrushing.vo.ewVO.ewInfo.explainWallAllInfo;
import com.codersan.icrushing.vo.ewVO.ewInfo.explainWallInfo;
import com.codersan.icrushing.vo.ewVO.ewInfo.simpleExplainWallInfo;
import com.codersan.icrushing.vo.ewVO.wallYouListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/explainWall")
public class explainWallController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private ordinaryUserService ordinaryUserService;
    @Autowired
    private wallAdminService wallAdminService;
    @Autowired
    private cosService cosService;
    @Autowired
    private AliyunService aliyunService;
    private String Bucket = "https://icrushing-1253873931.cos.ap-beijing.myqcloud.com";
    //获取你的表白墙列表
    @RequestMapping(value = "/getWallList",method = RequestMethod.POST)
    public wallYouListVO getWallList(@RequestParam(value = "code") String code){
        System.out.println(code);
        String openID = loginService.getOpenID(code);
        System.out.println(openID);
        wallYouListVO res = new wallYouListVO();
        res.setWallYouCreat(ordinaryUserService.wallYouCreat(openID));
        res.setWallYouAssist(ordinaryUserService.wallYouAssist(openID));
        res.setWallYouFollow(ordinaryUserService.wallYouFollow(openID));
        return res;
    }
    //获取你创建的表白墙
    @RequestMapping(value = "/wallYouCreat",method = RequestMethod.POST)
    public ArrayList<simpleExplainWallInfo> wallYouCreat(@RequestParam(value = "code") String code){
        String openID = loginService.getOpenID(code);
        ArrayList<simpleExplainWallInfo> res = new ArrayList<>();
        List<explainWall> ew = ordinaryUserService.wallYouCreat(openID);
        for(int i = 0;i<ew.size();i++){
            simpleExplainWallInfo sew=new simpleExplainWallInfo();
            sew.setEwID(ew.get(i).getEwID());
            sew.setEwName(ew.get(i).getName());
            sew.setAvatarUrl(ew.get(i).getAvatar());
            res.add(sew);
        }
        return res;
    }
    //获取你管理的表白墙
    @RequestMapping(value = "/wallYouAssist",method = RequestMethod.POST)
    public ArrayList<simpleExplainWallInfo> wallYouAssist(@RequestParam(value = "code")String code){
        String openID = loginService.getOpenID(code);
        ArrayList<simpleExplainWallInfo> res = new ArrayList<>();
        List<explainWall> ew = ordinaryUserService.wallYouAssist(openID);
        for(int i = 0;i<ew.size();i++){
            simpleExplainWallInfo sew=new simpleExplainWallInfo();
            sew.setEwID(ew.get(i).getEwID());
            sew.setEwName(ew.get(i).getName());
            sew.setAvatarUrl(ew.get(i).getAvatar());
            res.add(sew);
        }
        return res;
    }
    //获取你关注的表白墙
    @RequestMapping(value = "/wallYouFollow",method = RequestMethod.POST)
    public ArrayList<simpleExplainWallInfo> wallYouFollow(@RequestParam(value = "code")String code){
        String openID = loginService.getOpenID(code);
        ArrayList<simpleExplainWallInfo> res = new ArrayList<>();
        List<explainWall> ew = ordinaryUserService.wallYouFollow(openID);
        for(int i = 0;i<ew.size();i++){
            simpleExplainWallInfo sew=new simpleExplainWallInfo();
            sew.setEwID(ew.get(i).getEwID());
            sew.setEwName(ew.get(i).getName());
            sew.setAvatarUrl(ew.get(i).getAvatar());
            res.add(sew);
        }
        return res;
    }
    //查找表白墙
    @RequestMapping(value = "/queryEW",method = RequestMethod.POST)
    public ArrayList<explainWallInfo> queryEW(@RequestParam(value = "name")String name){
        ArrayList<explainWallInfo> res = new ArrayList<>();
        List<explainWall> ew = ordinaryUserService.wallYouQuery(name);
        for(int i=0;i<ew.size();i++){
            explainWallInfo ewi = new explainWallInfo();
            ewi.setEwID(ew.get(i).getEwID());
            ewi.setEwName(ew.get(i).getName());
            ewi.setAvatarUrl(ew.get(i).getAvatar());
            ewi.setEwIntroduction(ew.get(i).getIntroduction());
            ewi.setFolloweNumber(ordinaryUserService.numberOfFollow(ewi.getEwID()));
            ewi.setPostNumber(ordinaryUserService.numberOfPost(ewi.getEwID()));
            res.add(ewi);
        }
        return res;
    }
    //确定表白墙是否存在
    @RequestMapping(value = "/chechExistWall",method = RequestMethod.POST)
    public boolean checkExistWall(@RequestParam(value = "name")String name){
        return ordinaryUserService.checkExistWall(name);
    }
    //创建表白墙
    @RequestMapping(value = "/creatEW",method = RequestMethod.POST)
    public boolean creatEW(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "code")String code,@RequestParam(value = "ewName")String ewName,@RequestParam(value = "introduction")String introduction) throws IOException, InterruptedException {
        if(file==null)
            return false;
        String openID = loginService.getOpenID(code);
        if(wallAdminService.creatExplainWall(ewName,"",introduction,openID)){
            int ewID =ordinaryUserService.getEWIDByName(ewName);
            String key = "/iCrushing_EWAvatar/"+ewID+"/"+file.getOriginalFilename();
            cosService.upload(file,key);
            wallAdminService.alterAvatar(ewID,openID,Bucket+key);
            return true;
        }else{
            return false;
        }
    }
    //获取某个表白墙的详细信息,此时进入表白墙操作
    @RequestMapping(value = "/getAllEWInfo",method = RequestMethod.POST)
    public explainWallAllInfo getAllEWInfo(@RequestParam(value = "ewID")int ewID, @RequestParam(value = "code")String code){
        String openID = loginService.getOpenID(code);
        explainWall ew = ordinaryUserService.getEW(ewID);
        int postNumber = ordinaryUserService.numberOfPost(ewID);
        int followNumber = ordinaryUserService.numberOfFollow(ewID);
        boolean isFollow = ordinaryUserService.checkFollow(ewID,openID);
        return new explainWallAllInfo(ew,isFollow,followNumber,postNumber);
    }
    //获取时间参数，返回某个表白墙在这个时间前的十条Post
    @RequestMapping(value = "/getOldPost",method = RequestMethod.POST)
    public List<explainPostVO> getOldPost(@RequestParam(value = "ewID")int ewID, @RequestParam(value = "date")Date date){
        if(date==null)
            date=new Date();
        return ordinaryUserService.getOldPost(ewID,date);
    }
    //获取时间参数，返回某个表白墙在这个时间之后的所有Post
    @RequestMapping(value = "/getNewPost",method = RequestMethod.POST)
    public List<explainPostVO> getNewPost(@RequestParam(value = "ewID")int ewID,@RequestParam(value = "date")Date date ){
        return ordinaryUserService.getNewPost(ewID,date);
    }
    //关注或取关某表白墙
    @RequestMapping(value = "/followEW",method = RequestMethod.POST)
    public boolean followEW(@RequestParam(value = "ewID")int ewID,@RequestParam(value = "code")String code){
        String openID = loginService.getOpenID(code);
        return ordinaryUserService.followEW(ewID,openID);
    }
    //发帖
    @RequestMapping(value = "/sendPost",method = RequestMethod.POST)
    public boolean sendPost(@RequestParam(value = "file")MultipartFile file,@RequestParam(value = "text")String text,@RequestParam(value = "ewID")int ewID,@RequestParam(value = "code")String code,@RequestParam(value = "anonymous")boolean anonymous) throws IOException, InterruptedException {
        String openID = loginService.getOpenID(code);
        String content = aliyunService.dirtyWordsFiltering(text);
        if(openID==null)
            return false;
        long time = System.currentTimeMillis();
        String key = "/iCrushing_image/"+time+".png";
        String url = cosService.upload(file,key);
        ordinaryUserService.sendPost(content,url,ewID,openID,anonymous);
        return true;
    }
    //发帖
    @RequestMapping(value = "/sendPost1",method = RequestMethod.POST)
    public boolean sendPost1(@RequestParam(value = "text")String text,@RequestParam(value = "ewID")int ewID,@RequestParam(value = "code")String code,@RequestParam(value = "anonymous")boolean anonymous){
        return true;
    }
        //获取Post详细评论等
    @RequestMapping(value = "/postDetail",method = RequestMethod.POST)
    public ArrayList<epCommentDetail> postDetail(@RequestParam(value = "epID")int epID){
        return ordinaryUserService.postDetail(epID);
    }
    //评论某贴
    @RequestMapping(value = "/commentPost",method = RequestMethod.POST)
    public boolean commentPost(@RequestParam(value = "epID")int epID,@RequestParam(value = "content")String content,@RequestParam(value = "code")String code){
        String fromUserID  = loginService.getOpenID(code);
        if(fromUserID==null)
            return false;
        String resContent = aliyunService.dirtyWordsFiltering(content);
        ordinaryUserService.comment(epID, resContent, fromUserID);
        return true;
    }
    //删除某帖
    //删除评论
    //修改表白墙头像
    //修改表白墙信息
}
