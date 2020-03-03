package com.mi.global.bbs.model;

import com.google.gson.JsonArray;
import java.util.List;

public class MainLiteModel extends BaseResult {
    public MainLite data;

    public static class MainLite {
        public List<HomeBanner> banner;
        public JsonArray icon;
        public List<ThreadListBean> threadlist;
    }
}
