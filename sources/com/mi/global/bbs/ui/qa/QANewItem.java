package com.mi.global.bbs.ui.qa;

import com.mi.global.bbs.model.BaseResult;
import java.util.List;

public class QANewItem extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<NewItem> list;

        public static class NewItem {
            public String dateline;
            public String fid;
            public String fname;
            public String replies;
            public String subject;
            public String tid;
            public String views;
        }
    }
}
