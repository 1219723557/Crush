package com.codersan.icrushing.repository.crushDao;

import com.codersan.icrushing.domain.Crush.history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface historyDao extends JpaRepository<history,Integer>,JpaSpecificationExecutor<history> {
    //查询firstwechat的历史记录
    @Query("select h from history h where h.firstWechat=?1 or (h.secondWechat=?1 and h.lastState = true) ")
    ArrayList<history> queryHistory(String wechat);
}