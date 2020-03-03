package com.mi.global.bbs.model;

import java.util.List;

public class HomeColumnList {
    public String columnid;
    public String info;
    public String name;
    public List<ColumnArticleBean> thread;

    public class ColumnArticleBean {
        public String bgimg;
        public String subject;
        public String tid;

        public ColumnArticleBean() {
        }
    }
}
