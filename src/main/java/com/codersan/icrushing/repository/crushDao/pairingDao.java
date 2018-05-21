package com.codersan.icrushing.repository.crushDao;

import com.codersan.icrushing.domain.Crush.pairing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface pairingDao extends JpaRepository<pairing,pairing.pairingPK>,JpaSpecificationExecutor<pairing> {
    //判断是否已存在
    boolean existsByFirstWechat(String wechat);
    boolean existsBySecondWechatAndStateTrue(String wechat);
    //修改状态
    @Modifying@Transactional
    @Query("update pairing p set p.state=true ,p.alterDate = ?1 where p.firstWechat = ?2 and p.secondWechat=?3")
    void alterState(Date date, String firstwechat, String secondwechat);
    //确定是否已经存在
    boolean existsByFirstWechatAndSecondWechat(String firstwechat, String secondwechat);
    //获取wechat的pairing信息
    @Query("select p from pairing p where p.firstWechat=?1 or (p.secondWechat=?1 and p.state = true)")
    pairing queryPairingInfo(String wechat);
    //统计wechat的wooer
    int countBySecondWechatAndStateFalse(String secondwechat);

    //寻找单恋中的自己
    @Query("select p from pairing p where p.firstWechat = ?1 and p.state = false ")
    pairing queryStateOne(String wechat);
    //寻找恋爱中的自己
    @Query("select p from pairing p where (p.firstWechat = ?1 and p.state=true ) or (p.secondWechat = ?1 and p.state=true)")
    pairing queryStateTwo(String wechat);
}
