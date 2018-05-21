package com.codersan.icrushing.service;


import com.alibaba.fastjson.JSONObject;
import com.codersan.icrushing.domain.User;
import com.codersan.icrushing.repository.UserDao;
import com.codersan.icrushing.utils.httpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * 登陆服务
 */
@Service
public class LoginService {
    @Autowired
    private UserDao userdao;
    //获取openID
    @Transactional
    public  String getOpenID(String code){
        String appid="wx64dee88c013c770d";
        String appsecret="9d3e311f2fdb63f1f3f5d8e6407e8d14";
        //授权（必填）
        String grant_type = "authorization_code";
        //URL
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        //请求参数
        String params = "appid=" + appid + "&secret=" + appsecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String data = httpUtil.get(requestUrl, params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(data);
        //用户的唯一标识（openid）
        String Openid =String.valueOf(json.get("openid"));
        return Openid;
    }
    //调用openID进行登陆，查看能否正常登陆
    @Transactional
    public boolean login(String openID, String username, String avatar){
        //判断openID是否已经存在
        if(userdao.existsById(openID)){
            //调用修改
            userdao.alterUser(username,avatar,openID);
            User user = userdao.getOne(openID);
            return user.isBeBanned();
        }else{
            User user = new User(username,avatar);
            user.setOpenID(openID);
            user.setBeBanned(false);
            userdao.save(user);
            return false;
        }
    }
}
