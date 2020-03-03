package com.mi.global.bbs.model;

import java.util.List;

public class ColumnDetailModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public ColumnInfo columnInfo;
        public int push;
        public boolean subscribeStatus;
        public List<SubcribUser> subscribeUsers;

        public static class ColumnInfo {
            public String background;
            public int category;
            public long columnID;
            public int count;
            public String info;
            public long lastAppendTime;
            public String name;
            public String owner;
            public long ownerID;
            public int subscribeCount;
            public boolean subscribeStatus;
        }

        public static class SubcribUser {
            public int groupid;
            public String icon;
            public String name;
            public String uid;
        }
    }
}
