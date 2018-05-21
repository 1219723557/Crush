package com.codersan.icrushing.service.ThirdPartyService;
import com.alibaba.fastjson.JSONObject;
import com.codersan.icrushing.utils.httpUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/*
 * 阿里云服务API
 */
@Service
public class AliyunService {
    //敏感词过滤服务
    public  String dirtyWordsFiltering(String word){
        String url = "http://apistore.tongchengyue.com/sw/doFilter";
        String appcode = "778a33de33024f27b0af87ec27fa6841";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("src", word);
        Map<String, String> bodys = new HashMap<String, String>();
        String res = httpUtil.doPost(url,querys,headers);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(res);
        String result =String.valueOf(json.get("result"));
        return result;
    }
}
