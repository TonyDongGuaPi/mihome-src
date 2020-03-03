package com.mi.global.bbs.model;

import java.util.List;

public class AllThoughtModel extends BaseResult {
    public List<ListBean> data;

    public static class ImageModel {
        public int height;
        public String url;
        public int width;
    }

    public static class ListBean {
        public String add_time;
        public String app_local;
        public int delete;
        public int followed;
        public String groupid;
        public String icon;
        public String id;
        public List<ImageModel> image;
        public int like;
        public int likestatus;
        public String message;
        public int replies;
        public String replycount;
        public int showlogo;
        public String thumbupcount;
        public int type_id;
        public String uid;
        public String urls;
        public String username;
    }
}
