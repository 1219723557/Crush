package com.codersan.icrushing.repository.EWDao;

import com.codersan.icrushing.domain.EW.ewBeBanner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ewBeBannerDao extends JpaRepository<ewBeBanner,ewBeBanner.ewBeBannerPK> {
    //判断是否被封禁
    boolean existsByEwIDAndOpenID(int ewID, String openID);
    //解封
    void deleteByEwIDAndOpenID(int ewID, String openID);
}
