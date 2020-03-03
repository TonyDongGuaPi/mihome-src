package com.mi.global.bbs.model;

import java.util.List;

public class FollowingFeedModel extends BaseResult {
    public String Addtime;
    public String Content;
    public String EventId;
    public String Extra;
    public ExtraBean ExtraData;
    public String FeedId;
    public String FollowUid;
    public String FollowUsername;
    public String Icon;
    public long Id;
    public String Subject;
    public String Time;
    public int Type;
    public String Uid;
    public String Url;

    public static class ExtraBean {
        public String Area;
        public String Fid;
        public String Fname;
        public String Follower;
        public int Like;
        public int LikeStatus;
        public String Posts;
        public int Replies;
        public String Threads;
        public List<String> Urls;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FollowingFeedModel)) {
            return false;
        }
        FollowingFeedModel followingFeedModel = (FollowingFeedModel) obj;
        if (!this.FollowUid.equals(followingFeedModel.FollowUid) || !this.FollowUsername.equals(followingFeedModel.FollowUsername) || !this.Addtime.equals(followingFeedModel.Addtime) || !this.EventId.equals(followingFeedModel.EventId)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.FollowUid.hashCode() + 31 + this.FollowUsername.hashCode() + this.Addtime.hashCode() + this.EventId.hashCode();
    }
}
