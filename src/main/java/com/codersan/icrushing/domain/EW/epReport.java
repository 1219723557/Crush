package com.codersan.icrushing.domain.EW;

import javax.persistence.*;

@Entity
public class epReport {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @Column(nullable = false)
    private int epID;
    @Column(nullable = false)
    private String reportType;//举报类型
    private String content;
    @Column(length = 32,nullable = false)
    private String fromUserID;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getEpID() {
        return epID;
    }

    public void setEpID(int epID) {
        this.epID = epID;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }
}
