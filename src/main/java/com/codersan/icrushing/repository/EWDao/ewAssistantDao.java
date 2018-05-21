package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.ewAssistant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ewAssistantDao extends JpaRepository<ewAssistant,Integer>,JpaSpecificationExecutor<ewAssistant> {
    @Transactional
    void deleteByEwIDAndOpenID(int ewID, String openID);
    //判断是否是该墙助手
    boolean existsByEwIDAndOpenID(int ewID, String openID);
    //获取你是助手的墙墙
    List<ewAssistant> findByOpenID(String openID);
    //获取管理员人数
    int countByEwID(int ewID);
}
