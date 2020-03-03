package com.mi.global.bbs.model;

import java.util.List;

public class QuestionsModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public String replyclub;
        public List<List<StepsBean>> steps;

        public static class StepsBean {
            public boolean adminRecommend;
            public String area;
            public int fid;
            public boolean following;
            public String fup;
            public String icon;
            public String name;
            public String threads;
            public String type;
        }
    }
}
