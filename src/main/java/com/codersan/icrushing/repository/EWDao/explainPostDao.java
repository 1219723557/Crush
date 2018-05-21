package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.explainPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface explainPostDao extends JpaRepository<explainPost,explainPost.explainPostPK>,JpaSpecificationExecutor<explainPost> {
    //统计表白墙的帖子数
    int countByEwID(int ewID);
    //删帖
    @Transactional
    void deleteByEwIDAndEpID(int ewID, int epID);
    //获取date之前的十条的帖子
    List<explainPost> findTop10ByEwIDAndDateBeforeOrderByDateDesc(int ewID, Date date);
    //获取date之后的所有最新的帖子
    List<explainPost> findByEwIDAndDateAfterOrderByDateDesc(int ewID, Date date) ;
    //确定是否为发帖人
    boolean existsByEpIDAndEwIDAndFromUserID(int ID, int ewID, String openID);
}
