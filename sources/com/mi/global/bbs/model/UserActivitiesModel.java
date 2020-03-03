package com.mi.global.bbs.model;

import java.util.ArrayList;
import java.util.List;

public class UserActivitiesModel extends BaseResult {
    public List<UserActivity> data;

    public static class UserActivity {
        public String addtime;
        public String desc;
        public String event_id;
        public String followerCount;
        public String icon;
        public String message;
        public String place;
        public String replies;
        public String starttimefrom;
        public String starttimeto;
        public String subject;
        public String threads;
        public String typeid;
        public ArrayList<String> urls;
        public String username;
        public String voters;
    }
}
