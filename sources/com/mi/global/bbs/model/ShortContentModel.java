package com.mi.global.bbs.model;

import java.util.List;

public class ShortContentModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public String add_time;
        public int delete;
        public int havethumbup;
        public List<String> images;
        public String message;
        public int replynum;
        public String replys;
        public int thumbupnum;
        public String uid;
        public String url;
        public String usericon;
        public String username;
    }
}
