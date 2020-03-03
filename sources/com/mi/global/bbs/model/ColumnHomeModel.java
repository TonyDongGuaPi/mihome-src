package com.mi.global.bbs.model;

import java.util.List;

public class ColumnHomeModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<ColumnArticle> acticles;
        public Config config;
        public List<ColumnRecommend> recommend;
        public long subscribeUpdateTime;

        public static class ColumnArticle {
            public int appendTime;
            public String author;
            public String authorID;
            public String authorImg;
            public String bgimg;
            public String brief;
            public int columnID;
            public String columnName;
            public long fid;
            public String groupid;
            public int nativeFlag;
            public String posttimeStr;
            public int replies;
            public int showlogo;
            public String subject;
            public int thumbpCount;
            public boolean thumbpStatus;
            public long tid;
        }

        public static class ColumnRecommend {
            public String background;
            public int category;
            public long columnID;
            public int count;
            public String info;
            public String name;
            public String owner;
            public int status;
            public int subscribeCount;
        }

        public static class Config {
            public String applyUrl;
            public String bgimg;
            public boolean openApply;
        }
    }
}
