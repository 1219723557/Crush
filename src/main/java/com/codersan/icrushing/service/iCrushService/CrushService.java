package com.codersan.icrushing.service.iCrushService;

import com.codersan.icrushing.domain.Crush.history;
import com.codersan.icrushing.domain.Crush.info;
import com.codersan.icrushing.domain.Crush.pairing;
import com.codersan.icrushing.domain.User;
import com.codersan.icrushing.repository.UserDao;
import com.codersan.icrushing.repository.crushDao.historyDao;
import com.codersan.icrushing.repository.crushDao.infoDao;
import com.codersan.icrushing.repository.crushDao.pairingDao;
import com.codersan.icrushing.vo.crushVO.crushLoginResponseVO;
import com.codersan.icrushing.vo.crushVO.crushSettingVO;
import com.codersan.icrushing.vo.crushVO.queryOneResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/*
 * iCrushing用户通用服务
 */
@Service
public class CrushService {
    @Autowired
    private infoDao infoDao;
    @Autowired
    private pairingDao pairingDao;
    @Autowired
    private historyDao historyDao;
    @Autowired
    private UserDao UserDao;
    //Crush登陆(已测试)
    public crushLoginResponseVO loginCrush(String openID){
        /*首先判断是否存有openID的数据，如果没有的话则insert一行数据
         *如果有，则返回state和wechat
         *根据state和wechat来返回对方的微信以及时间
         */
        crushLoginResponseVO res = new crushLoginResponseVO();
        if(infoDao.existsById(openID)){
            info i = infoDao.findById(openID).get();
            //返回自己的状态
            res.setCrushState(i.getState());
            if(i.getWechat()!=null) {
                //返回绑定的微信号
                res.setWechat(i.getWechat());
                //统计追求者人数
                res.setWooer(pairingDao.countBySecondWechatAndStateFalse(i.getWechat()));
            }
            //获取用户当前状态,返回对方微信号，日期，以及自己的状态
            pairing p = null;
            if(i.getState()==1){
                p = pairingDao.queryStateOne(i.getWechat());
                res.setTaWeChat(p.getSecondWechat());
                res.setDate(p.getAlterDate());
            }else if(i.getState()==2){
                p = pairingDao.queryStateTwo(i.getWechat());
                if(p.getFirstWechat()==i.getWechat()){
                    res.setTaWeChat(p.getSecondWechat());
                }else{
                    res.setTaWeChat(p.getFirstWechat());
                }
                res.setDate(p.getAlterDate());
            }else {
            }
            //
        }else{
            //添加一行数据
            info info = new info();
            info.setOpenID(openID);
            info.setSeeState(false);
            info.setSeeWooer(false);
            info.setSeeEW(false);
            info.setState(0);
            infoDao.save(info);
            res.setCrushState(0);
            res.setWooer(0);
        }
        return res;
    }
    //获取个人设置服务（测试通过）
    public crushSettingVO queryinfo(String openID){
        crushSettingVO crushSettingVO = new crushSettingVO();
        info i = infoDao.findById(openID).get();
        crushSettingVO.setPhone(i.getPhone());
        crushSettingVO.setQQ(i.getQQ());
        crushSettingVO.setSeeState(i.isSeeState());
        crushSettingVO.setSeeWooer(i.isSeeWooer());
        crushSettingVO.setSeeEW(i.isSeeEW());
        return crushSettingVO;
    }
    //查询他人信息服务(测试通过)
    public queryOneResponseVO queryOne(String openID){
        queryOneResponseVO res =  new queryOneResponseVO();
        User u = UserDao.findById(openID).get();
        res.setAvatar(u.getAvatar());
        res.setNickName(u.getUserName());
        res.setBeBanned(u.isBeBanned());
        info i = infoDao.findById(openID).get();
        res.setSeeState(i.isSeeState());
        res.setSeeWooer(i.isSeeWooer());
        res.setState(i.getState());
        res.setWooer(pairingDao.countBySecondWechatAndStateFalse(i.getWechat()));
        return res;
    }
    //添加喜欢的人服务(测试通过)
    public boolean crushOn(String openID,String taWechat){
        //先获取自己的微信号
        String myWechat = infoDao.findById(openID).get().getWechat();
        if(myWechat==null)
            return false;
        if(pairingDao.existsByFirstWechat(myWechat)||pairingDao.existsBySecondWechatAndStateTrue(myWechat))
            return false;
        //先判断对方是否正在暗恋你
        if(pairingDao.existsByFirstWechatAndSecondWechat(taWechat,myWechat)){
            //对方正在在暗恋你
            pairingDao.alterState(new Date(),taWechat,myWechat);
            //修改双方的状态
            infoDao.alterState(2,taWechat);
            infoDao.alterState(2,myWechat);
        }else{
            //单相思
            pairing p  =new pairing();
            p.setFirstWechat(myWechat);
            p.setSecondWechat(taWechat);
            p.setState(false);
            p.setAlterDate( new Date());
            pairingDao.save(p);
            infoDao.alterState(1,myWechat);
        }
        return true;
    }
    //放弃分手服务(测试通过)
    public void crushOut(String openID){
        //首先获取当前状态
        info i = infoDao.findById(openID).get();
        if(i.getState()==1){
            //直接删除即可
            pairing p = pairingDao.queryStateOne(i.getWechat());
            history h = new history();
            h.setBeginDate(p.getAlterDate());
            Date date = new Date();
            h.setEndDate(date);
            h.setFirstWechat(p.getFirstWechat());
            h.setSecondWechat(p.getSecondWechat());
            h.setLastState(p.isState());
            historyDao.save(h);
            pairingDao.delete(p);
            //修改自己状态
            infoDao.alterState(0,i.getWechat());
        }else if(i.getState()==2){
            //
            pairing p = pairingDao.queryStateTwo(i.getWechat());
            history h = new history();
            h.setFirstWechat(p.getFirstWechat());
            h.setSecondWechat(p.getSecondWechat());
            h.setLastState(p.isState());
            h.setBeginDate(p.getAlterDate());
            Date date = new Date();
            h.setEndDate( date);
            historyDao.save(h);
            pairingDao.delete(p);
            //修改两人状态
            infoDao.alterState(0,p.getFirstWechat());
            infoDao.alterState(0,p.getSecondWechat());
        }
    }
    //绑定微信号服务   (已测试)
    public boolean bindWechat(String openID,String wechat){
        //判断wechat是否已经被绑定
        System.out.println(infoDao.existsByWechat(wechat));
        if(wechat==null || infoDao.existsByWechat(wechat)){
            return false;
        }else{
            //进行绑定操作
            infoDao.bindWechat(wechat, new Date(),openID);
        }
        return true;
    }
    public boolean hadBindWechat(String openID){
        if(infoDao.getOne(openID).getWechat()!=null)
            return true;
        return false;
    }
    //修改个人资料服务（已测试）
    public void alterInfo(String openID,String QQ,String phone,boolean seeWooer,boolean seeState,boolean seeEW){
        infoDao.alterInfo(QQ,phone,seeWooer,seeState,seeEW,openID);
    }
    //查看历史纪录服务(已测试)
    public ArrayList<history> queryHistory(String openID){
        info info = infoDao.getOne(openID) ;
        ArrayList<history> al = historyDao.queryHistory(info.getWechat());
        return al;
    }
}
