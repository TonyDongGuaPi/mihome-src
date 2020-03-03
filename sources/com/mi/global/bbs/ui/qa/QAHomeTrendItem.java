package com.mi.global.bbs.ui.qa;

import com.mi.global.bbs.model.BaseResult;
import java.util.List;

public class QAHomeTrendItem extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<BannerItem> banner;
        public List<TrendItem> list;

        public static class Answer {
            public String author;
            public String avatar;
            public String dateline;
            public String message;
        }

        public static class BannerItem {
            public String blank;
            public String country;
            public String ctime;
            public String desc;
            public String id;
            public String link;
            public String mtime;
            public String order;
            public String pic_url;
            public String status;
            public String type;
            public String vtime;
        }

        public static class TrendItem {
            public Answer answer;
            public String fname;
            public String replies;
            public String subject;
            public String tid;
            public String views;
        }
    }
}
