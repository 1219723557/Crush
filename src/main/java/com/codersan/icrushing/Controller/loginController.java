package com.codersan.icrushing.Controller;

import com.codersan.icrushing.service.LoginService;
import com.codersan.icrushing.vo.loginVO.loginRequestVO;
import com.codersan.icrushing.vo.loginVO.loginResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/icrushing")
public class loginController {
    @Autowired
    private LoginService loginService;
    //登陆接口
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public loginResponseVO login(@RequestBody loginRequestVO request){
        //首先根据code获取openID
        String openID = loginService.getOpenID(request.getCode());
        boolean bebanned=false;
        if(openID!=null){
            //上传头像和昵称并获取beBanned信息
            bebanned = loginService.login(openID,request.getNickName(),request.getAvatar());
        }
        //设置参数并返回信息
        loginResponseVO result = new loginResponseVO(openID,bebanned);
        return result;
    }
}
