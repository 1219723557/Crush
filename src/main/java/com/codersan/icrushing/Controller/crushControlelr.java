package com.codersan.icrushing.Controller;

import com.codersan.icrushing.domain.Crush.history;
import com.codersan.icrushing.service.LoginService;
import com.codersan.icrushing.service.iCrushService.CrushService;
import com.codersan.icrushing.vo.crushVO.bindWechatRequestVO;
import com.codersan.icrushing.vo.crushVO.crushLoginResponseVO;
import com.codersan.icrushing.vo.crushVO.crushOnRequestVO;
import com.codersan.icrushing.vo.crushVO.crushSettingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/crush")
public class crushControlelr {
    @Autowired
    private LoginService loginService;
    @Autowired
    private CrushService crushService;
    //crush登陆，获取你的crush信息
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public crushLoginResponseVO crushLogin(@RequestBody String code){
        //先更具code获取openID;
        System.out.println(code);
        String openID = loginService.getOpenID(code);
        crushLoginResponseVO res = crushService.loginCrush(openID);
        return res;
    }
    //绑定微信，如果已绑定微信或者微信被其他账号绑定则返回false，
    @RequestMapping(value = "/bindWechat",method = RequestMethod.POST)
    public boolean bindWechat(@RequestBody bindWechatRequestVO bindWechatRequestVO){
        String openID  = loginService.getOpenID(bindWechatRequestVO.getCode());
        if(crushService.hadBindWechat(openID))
            return false;
        return crushService.bindWechat(openID,bindWechatRequestVO.getWechat());
    }
    //获取crushSetting的 信息
    @RequestMapping(value = "/getCrushSetting",method = RequestMethod.POST)
    public crushSettingVO getCrushSetting(@RequestBody String code){
        String openID = loginService.getOpenID(code);
        crushSettingVO res = crushService.queryinfo(openID);
        return res;
    }
    //修改crushSetting的设置
    @RequestMapping(value = "/setCrushSetting",method = RequestMethod.POST)
    public void setCrushSetting(@RequestBody crushSettingVO req){
        String openID = loginService.getOpenID(req.getCode());
        crushService.alterInfo(openID,req.getQQ(),req.getPhone(),req.isSeeWooer(),req.isSeeState(),req.isSeeEW());
    }
    //添加自己暗恋的对象的接口
    @RequestMapping(value = "/crushOn",method = RequestMethod.POST)
    public boolean crushON(@RequestBody crushOnRequestVO req){
        String openID = loginService.getOpenID(req.getCode());
        return crushService.crushOn(openID,req.getTaWechat());
    }
    //结束crush的接口
    @RequestMapping(value = "/crushOut",method = RequestMethod.POST)
    public void crushOut(@RequestBody String code){
        String openID = loginService.getOpenID(code);
        crushService.crushOut(openID);
    }

    //查看历史记录
    @RequestMapping(value = "/queryHistory",method = RequestMethod.POST)
    public ArrayList<history> queryHistory(@RequestBody String code){
        String openID = loginService.getOpenID(code);
        return crushService.queryHistory(openID);
    }

    //查看某人信息的接口
}
