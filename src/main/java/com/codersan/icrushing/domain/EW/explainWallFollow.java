package com.codersan.icrushing.domain.EW;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(explainWallFollow.explainWallFollowPK.class)
public class explainWallFollow {
    @Id
    private int ewID;
    @Id
    private String followFromUserID;

    public explainWallFollow() {
    }

    public explainWallFollow(int ewID, String followFromUserID) {
        this.ewID = ewID;
        this.followFromUserID = followFromUserID;
    }

    public int getEwID() {
        return ewID;
    }

    public void setEwID(int ewID) {
        this.ewID = ewID;
    }

    public String getFollowFromUserID() {
        return followFromUserID;
    }

    public void setFollowFromUserID(String followFromUserID) {
        this.followFromUserID = followFromUserID;
    }

    public static class explainWallFollowPK implements Serializable {
        private static final long serialVersionUID = 1L;
        private int ewID;
        private String followFromUserID;

        public explainWallFollowPK() {
        }

        public explainWallFollowPK(int ewID, String followFromUserID) {
            this.ewID = ewID;
            this.followFromUserID = followFromUserID;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
