package com.mi.global.bbs.model;

import java.util.List;

public class SubForumItem extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public ForumBean forum;
        public List<ThreadListBean> threadlist;
        public List<ThreadTypesBean> threadtypes;

        public static class ForumBean {
            public String favtimes;
            public String fid;
            public int follow;
            public String followurl;
            public String icon;
            public String name;
            public int photography;
            public String threads;
            public String todayposts;
        }

        public static class ThreadTypesBean {
            public String name;
            public int typeid;
        }
    }
}
