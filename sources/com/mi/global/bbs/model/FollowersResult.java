package com.mi.global.bbs.model;

import java.util.List;

public class FollowersResult extends BaseResult {
    public FollowersBean data;

    public static class FollowersBean {
        public String total;
        public List<Followers> users;

        public static class Followers {
            public String authortitle;
            public int follow;
            public String follower;
            public String icon;
            public String posts;
            public String threads;
            public String uid;
            public String username;
        }
    }
}
