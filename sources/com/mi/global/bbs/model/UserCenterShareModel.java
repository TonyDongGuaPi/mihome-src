package com.mi.global.bbs.model;

public class UserCenterShareModel extends BaseResult {
    public FacebookBean data;

    public static class FacebookBean {
        public String shareMsg;
        public String sharePic;
        public String shareSubject;
        public String shareUrl;
    }
}
