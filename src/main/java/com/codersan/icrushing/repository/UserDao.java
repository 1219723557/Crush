package com.codersan.icrushing.repository;

import com.codersan.icrushing.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {
    //修改用户昵称和头像
    @Modifying@Transactional
    @Query("update User as u set u.userName=?1,u.avatar=?2 where u.openID=?3")
    void alterUser(String username, String avatar, String openID);
    //封号API
    @Modifying@Transactional
    @Query("update User as u set u.beBanned = ?1 where u.openID = ?2")
    void banUser(boolean bebanned, String openID);
    User findByOpenID(String openID);
}
