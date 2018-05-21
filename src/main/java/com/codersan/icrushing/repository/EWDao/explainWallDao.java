package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.explainWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface explainWallDao extends JpaRepository<explainWall,Integer> ,JpaSpecificationExecutor<explainWall> {
    //判断该表白墙是否已经存在
    boolean existsByName(String name);
    //修改表白墙资料
    @Modifying@Transactional
    @Query("update explainWall as ew set ew.introduction = ?1 where ew.ewID=?2 ")
    void alterIntroduction(String newIntroduction, int ewID);
    //修改表白墙头像
    @Modifying@Transactional
    @Query("update explainWall as ew set ew.avatar = ?1 where ew.ewID=?2 ")
    void alterEWAvatar(String avatar, int ewID);
    ////修改管理员
    @Modifying@Transactional
    @Query("update explainWall as ew set ew.admin = ?1 where ew.ewID=?2")
    void alterAdmin(String newAdminOpenID, int ewID);
    //判断是否是管理员
    boolean existsByEwIDAndAdmin(int ewID, String admin);
    //获取创建的表白墙
    List<explainWall> findByAdmin(String openID);
    //查找墙墙
    List<explainWall> findByNameContaining(String Name);
    //根据name
    explainWall findByName(String name);
    explainWall findByEwID(int ewID);
}
