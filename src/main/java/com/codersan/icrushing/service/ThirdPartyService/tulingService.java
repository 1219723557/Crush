package com.codersan.icrushing.service.ThirdPartyService;

import com.alibaba.fastjson.JSONObject;
import com.codersan.icrushing.utils.httpUtil;
import com.show.api.ShowApiRequest;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
 * 图灵服务API
 */
@Service
public class tulingService {
    String url = " http://route.showapi.com/60-27";
    String showapi_appid = "64571";
    String showapi_sign = "79d525a9fe1343f496e8d958bb6c952a";
    public String getReply(String info,String userid){
        String res=new ShowApiRequest(url,showapi_appid,showapi_sign)
                .addTextPara("info",info)
                .addTextPara("userid",userid)
                .post();
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(res);
        String result =String.valueOf(json.getJSONObject("showapi_res_body").get("text"));
        return result;
    }
}
