package com.mi.global.bbs.model;

import com.google.gson.JsonArray;
import java.util.List;

public class BaseForumCommentsBean {
    public List<PostListBean> postlist;
    public int total;

    public static class PostListBean {
        public JsonArray action;
        public List<Attach> attachlist;
        public String author;
        public String authorid;
        public int banned;
        public String dateline;
        public int deleted;
        public int edit;
        public String favtimes;
        public String fid;
        public String first;
        public String groupid;
        public String icon;
        public int manage;
        public JsonArray message;
        public String pid;
        public int position;
        public int showlogo;
        public String subject;
        public int thumbupcount;
        public int thumbupstatus;
        public String thumbupurl;
        public String tid;
    }
}
