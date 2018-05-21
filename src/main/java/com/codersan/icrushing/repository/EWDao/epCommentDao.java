package com.codersan.icrushing.repository.EWDao;


import com.codersan.icrushing.domain.EW.epComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface epCommentDao extends JpaRepository<epComment,Integer> {
    //统计帖子的评论数
    int countByEpID(int epID);
    //删帖
    void deleteByCID(int commentID);
    //判断是否是发评论的人
    boolean existsByCIDAndFromUserID(int commentID, String openID);
    List<epComment> findByEpIDOrderByCommentDateAsc(int epID);
}
