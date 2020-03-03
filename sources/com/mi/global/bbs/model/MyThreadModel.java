package com.mi.global.bbs.model;

import java.util.List;

public class MyThreadModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public int curpage;
        public List<ListBean> list;
        public int perpage;
        public int totalpage;

        public static class ListBean {
            public String author;
            public String dateline;
            public String displayorder;
            public List<String> pics;
            public String replies;
            public String thread;
            public String title;
            public String views;
        }
    }
}
