package com.mi.global.bbs.model;

import java.util.List;

public class SearchUserModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public ResponseBean response;

        public static class ResponseBean {
            public List<DocsBean> docs;
            public int numFound;
            public int start;

            public static class DocsBean {
                public long _version_;
                public int gender;
                public String groupid;
                public String icon;
                public String id;
                public String livingcity;
                public String username;
            }
        }
    }
}
