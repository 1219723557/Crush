package com.codersan.icrushing.domain.EW;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(epZan.epZanPK.class)
public class epZan {
    @Id
    private int epID;
    @Id
    private String fromUserID;

    public epZan() {
    }

    public epZan(int epID, String fromUserID) {
        this.epID = epID;
        this.fromUserID = fromUserID;
    }



    public int getEpID() {
        return epID;
    }

    public void setEpID(int epID) {
        this.epID = epID;
    }

    public String getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(String fromUserID) {
        this.fromUserID = fromUserID;
    }

    public static class epZanPK implements Serializable {
        private static final long serialVersionUID = 1L;
        private int epID;
        private String fromUserID;

        public epZanPK() {
        }

        public int getEpID() {
            return epID;
        }

        public void setEpID(int epID) {
            this.epID = epID;
        }

        public String getFromUserID() {
            return fromUserID;
        }

        public void setFromUserID(String fromUserID) {
            this.fromUserID = fromUserID;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
