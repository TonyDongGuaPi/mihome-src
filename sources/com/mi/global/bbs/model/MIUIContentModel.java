package com.mi.global.bbs.model;

import java.util.List;

public class MIUIContentModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public BannerBean banner;
        public List<ColumnsBean> columns;
        public List<SubmenuBean> submenu;
        public List<UsersBean> users;

        public static class BannerBean {
            public String img;
            public String link;
            public String name;
        }

        public static class ColumnsBean {
            public String background;
            public int columnID;
            public String info;
            public String name;
        }

        public static class SubmenuBean {
            public boolean adminRecommend;
            public String area;
            public int fid;
            public String fup;
            public String icon;
            public String name;
            public String threads;
            public String type;
            public String url;
        }

        public static class UsersBean {
            public String description;
            public int followStatus;
            public String icon;
            public long uid;
            public String username;
        }
    }
}
