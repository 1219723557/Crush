package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.epZan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface epZanDao extends JpaRepository<epZan,epZan.epZanPK> {
    //确认用户是否点赞了
    boolean existsByEpIDAndFromUserID(int epID, String openID);
    //统计帖子的点赞量
    int countByEpID(int epID);
}
