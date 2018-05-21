package com.codersan.icrushing.domain.EW;

import javax.persistence.*;
import java.util.Date;

/*
*此类用于评论操作
 */
@Entity
public class epComment {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cID;//评论编号
    @Column(nullable = false)
    private int epID;//所属表白帖编号
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private Date commentDate;
    @Column(length = 32,nullable = false)
    private String fromUserID;


    public epComment() {
    }

    public epComment(int epID, String content, Date commentDate, String fromUserID) {
        this.epID = epID;
        this.content = content;
        this.commentDate = commentDate;
        this.fromUserID = fromUserID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getcID() {
        return cID;
    }

    public void setcID( int cID) {
        this.cID = cID;
    }

    public int getEpID() {
        return epID;
    }

    public void setEpID(int epID) {
        this.epID = epID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
