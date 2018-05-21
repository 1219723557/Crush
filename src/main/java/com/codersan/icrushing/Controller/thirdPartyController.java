package com.codersan.icrushing.Controller;

import com.codersan.icrushing.service.ThirdPartyService.AliyunService;
import com.codersan.icrushing.service.ThirdPartyService.tulingService;
import com.codersan.icrushing.vo.thirdPartyVO.tulingVO.tulingRequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thirdParty")
public class thirdPartyController {
    @Autowired
    private tulingService tulingService;
    @Autowired
    private AliyunService aliyunService;
    @RequestMapping("/tuling")
    public String tuling(@RequestBody tulingRequestVO tulingRequestVO){
        return tulingService.getReply(tulingRequestVO.getInfo(),tulingRequestVO.getUserid());
    }
    @RequestMapping("/sensitiveWords")
    public String sensitiveWords(@RequestBody  String words){
        return aliyunService.dirtyWordsFiltering(words);
    }
}
