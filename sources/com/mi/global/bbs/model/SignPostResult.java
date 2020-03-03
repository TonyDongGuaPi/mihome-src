package com.mi.global.bbs.model;

public class SignPostResult extends BaseResult {
    public SignShareBean data;

    public static class SignShareBean {
        public int consecutivedays;
        public int emotion;
        public String emotionurl;
        public String group;
        public String message;
        public String usericon;
        public String username;
    }
}
