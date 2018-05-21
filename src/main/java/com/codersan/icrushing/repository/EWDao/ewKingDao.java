package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.ewKing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ewKingDao extends JpaRepository<ewKing,Integer>,JpaSpecificationExecutor<ewKing> {
    boolean existsByOpenID(String openID);
}
