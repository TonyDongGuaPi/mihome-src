package com.mi.global.bbs.model;

import java.util.List;

public class ForYouCustomizeModel extends BaseResult {
    public List<DataBean> data;

    public static class DataBean {
        public String name;
        public List<ForYouCustomizeBean> submenu;

        public static class ForYouCustomizeBean {
            public int fid;
            public int followCount;
            public boolean followStatus;
            public String icon;
            public String name;
            public String url;
        }
    }
}
