package com.mi.global.bbs.model;

import java.util.List;

public class ColumnFollowers extends BaseResult {
    public ColumnFollowersList data;

    public static class ColumnFollowersList {
        public List<ColumnFollower> list;

        public static class ColumnFollower {
            public BaseBean base;
            public boolean following;
            public StatisticsBean statistics;

            public static class BaseBean {
                public int groupid;
                public String groupname;
                public String icon;
                public String name;
                public String signature;
                public String uid;
            }

            public static class StatisticsBean {
                public int credits;
                public int digestpost;
                public int followers;
                public int followings;
                public int friends;
                public int posts;
                public int replies;
                public int threads;
            }
        }
    }
}
