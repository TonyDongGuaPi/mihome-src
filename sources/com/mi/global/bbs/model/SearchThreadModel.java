package com.mi.global.bbs.model;

import java.util.List;

public class SearchThreadModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public ResponseBean response;

        public static class ResponseBean {
            public List<DocsBean> docs;
            public int numFound;
            public int start;

            public static class DocsBean {
                public long _version_;
                public int attachment;
                public String author;
                public String author_s;
                public String authorid;
                public String comments;
                public String createTime;
                public String fid;
                public int icon;
                public String id;
                public String lastposter;
                public String lastreplytime;
                public String message;
                public int price;
                public String price_c;
                public int replies;
                public String subject;
                public int views;
            }
        }
    }
}
