package com.mi.global.bbs.model;

import java.util.List;

public class OnlineActivityResult extends BaseResult {
    public OnlineActivityBean data;

    public static class OnlineActivityBean {
        public List<OnlineActivity> list;

        public static class OnlineActivity {
            public String affirmreplies;
            public String authimg;
            public String author;
            public String authorid;
            public String dateline;
            public String displayorder;
            public String fid;
            public int finish;
            public String fname;
            public String negareplies;
            public String pollcount;
            public String replies;
            public int showlogo;
            public List<String> showpiclist;
            public String special;
            public String subject;
            public int thumbupcount;
            public int thumbupstatus;
            public String thumbupurl;
            public String tid;
            public String views;
        }
    }
}
