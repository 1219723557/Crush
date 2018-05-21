package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.explainWallFollow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface explainWallFollowDao extends JpaRepository<explainWallFollow,explainWallFollow.explainWallFollowPK> {
    int countByEwID(int ewID);
    List<explainWallFollow> findByFollowFromUserID(String openID);
}
