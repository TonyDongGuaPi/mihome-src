package com.mi.global.bbs.model;

import java.util.List;

public class SignContentResult extends BaseResult {
    public SignContent data;

    public static class SignContent {
        public String add_time;
        public int delete;
        public String emotion;
        public String emotionurl;
        public int havethumbup;
        public String message;
        public int replynum;
        public List<SignComment> replys;
        public int thumbupnum;
        public String uid;
        public String usericon;
        public String username;
    }
}
