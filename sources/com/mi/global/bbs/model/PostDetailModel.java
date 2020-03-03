package com.mi.global.bbs.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.List;

public class PostDetailModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public Author author;
        public Column column;
        public ThreadBean thread;

        public static class Author {
            public AuthorBean base;

            public static class AuthorBean {
                public String groupname;
                public String icon;
                public int isfollow;
                public String name;
                public String uid;
            }
        }

        public static class Column {
            public List<ColumnBean> column;
            public int subscribe;
            public List<ColumnThread> threads;

            public static class ColumnBean {
                public String background;
                public String columnid;
                public String count;
                public String info;
                public String name;
                public String subscribeCount;
            }

            public static class ColumnThread {
                public String bgimg;
                public String columnid;
                public String subject;
            }
        }

        public static class ListBean {
            public JsonArray data;
            public String href;
            public String type;
        }

        public static class SpecialInfo {
            public int maxchoices;
            public int multiple;
            public int polled;
            public List<PollBean> polls;
            public int total;
            public int visible;

            public static class PollBean {
                public String id;
                public JsonObject img;
                public String name;
                public float percent;
                public int voted;
                public int voters;
            }
        }

        public static class ThreadBean {
            public JsonArray arraydata;
            public String columnID;
            public String dateline;
            public String favorite;
            public String fid;
            public String fname;
            public boolean isfollow;
            public int replies;
            public SpecialInfo specialinfo;
            public Stamp stamp;
            public String subject;
            public ThumbUp thumbup;
            public String tid;
            public String typename;
            public int views;
        }

        public static class ThumbUp {
            public int count;
            public boolean status;
        }

        public class Stamp {
            public String imgurl;

            public Stamp() {
            }
        }
    }
}
