package com.codersan.icrushing.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.codec.digest.DigestUtils.sha1;

@RestController
@RequestMapping("/message")
public class messageController {
    @RequestMapping("/checkSignature")
    public String checkSignature(@RequestParam String signature,@RequestParam String timestamp,@RequestParam  String nonce,@RequestParam String echostr){
        String token = "codersan";
        String tmpStr = token+timestamp+nonce;
        String res = String.valueOf(sha1(tmpStr));
        System.out.println(res);
        System.out.println(signature);
        if(res==signature)
            return nonce;
        else
            return "";
    }
}
