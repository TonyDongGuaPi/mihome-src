package com.mi.global.bbs.model;

import java.util.List;

public class InCityActivityResult extends BaseResult {
    public InCityActivityBean data;

    public static class InCityActivityBean {
        public List<InCityActivity> list;
        public int total;

        public static class InCityActivity {
            public String authimg;
            public String author;
            public String authorid;
            public String dateline;
            public int displayorder;
            public String expiration;
            public String fid;
            public String fname;
            public String link;
            public String place;
            public int posttableid;
            public int replies;
            public int showlogo;
            public List<String> showpiclist;
            public int special;
            public String starttimefrom;
            public String starttimeto;
            public String subject;
            public int thumbupcount;
            public int thumbupstatus;
            public String thumbupurl;
            public String tid;
            public String ufield;
            public String views;
        }
    }
}
