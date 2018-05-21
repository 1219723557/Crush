package com.codersan.icrushing.repository.crushDao;

import com.codersan.icrushing.domain.Crush.info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Repository
public interface infoDao extends JpaRepository<info,String>,JpaSpecificationExecutor<info> {
    //修改个人信息
    @Modifying@Transactional
    @Query("update info as i set i.QQ=?1,i.phone=?2,i.seeWooer=?3,i.seeState=?4 ,i.seeEW=?5 where i.openID=?6")
    void alterInfo(String QQ, String phone, boolean seeWooer, boolean seeState, boolean seeEW,String openID);
    //修改微信号
    @Modifying@Transactional
    @Query("update info as i set i.wechat=?1,i.wechatAlterDate=?2 where i.openID=?3")
    void bindWechat(String Wechat, Date alterDate, String openID);
    //查询wechat的信息
    info getByWechat(String wechat);
    //设置状态
    @Modifying@Transactional
    @Query("update info as i set i.state=?1 where i.wechat=?2")
    void alterState(int state, String wechat);
    boolean existsByWechat(String wechat);
}
