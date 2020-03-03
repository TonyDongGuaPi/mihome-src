package com.mi.global.bbs.model;

import java.util.List;

public class MIUIForumModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<ListBean> list;

        public static class ListBean {
            public int affirmreplies;
            public String authimg;
            public String author;
            public long authorid;
            public String dateline;
            public int displayorder;
            public long fid;
            public String fname;
            public String groupid;
            public int negareplies;
            public int pollcount;
            public int replies;
            public int showlogo;
            public List<String> showpiclist;
            public int special;
            public String subject;
            public int thumbupcount;
            public int thumbupstatus;
            public String thumbupurl;
            public long tid;
            public String views;
        }
    }
}
