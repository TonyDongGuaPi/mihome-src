package com.mi.global.bbs.model;

import java.util.List;

public class SubFollowItem extends BaseGoResult {
    public DataBean data;

    public static class DataBean {
        public List<FollowingFeedModel> FeedData;
        public List<FollowingUserDataModel> UserData;
    }
}
