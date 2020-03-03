package com.mi.global.bbs.model;

import com.google.gson.JsonArray;
import java.util.List;

public class MyReplyModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<ListBean> list;

        public static class ListBean {
            public String author;
            public String commenturl;
            public String dateline;
            public String displayorder;
            public String fid;
            public String invisible;
            public JsonArray message;
            public String threadurl;
            public String tid;
            public String title;
        }
    }
}
