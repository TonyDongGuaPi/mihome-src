package com.mi.global.bbs.model;

import java.util.List;

public class SubForumModel extends BaseResult {
    public List<DataBean> data;

    public static class DataBean {
        public String name;
        public List<SubmenuBean> submenu;

        public static class SubmenuBean {
            public String icon;
            public String name;
            public String url;
        }
    }
}
