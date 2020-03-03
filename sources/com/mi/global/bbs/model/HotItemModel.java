package com.mi.global.bbs.model;

import java.util.List;

public class HotItemModel extends BaseResult {
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

        public static class ThreadListBean {
            public int affirmreplies;
            public String authimg;
            public String authname;
            public String authorid;
            public String dateline;
            public int displayorder;
            public String fid;
            public String fname;
            public int negareplies;
            public int pollcount;
            public String replies;
            public List<String> showpiclist;
            public int special;
            public int thumbupcount;
            public int thumbupstatus;
            public String thumbupurl;
            public String tid;
            public int views;
        }

        public static class ThreadTypesBean {
            public String name;
            public int typeid;
        }
    }
}
