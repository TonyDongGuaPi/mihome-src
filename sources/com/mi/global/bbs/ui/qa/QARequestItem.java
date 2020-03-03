package com.mi.global.bbs.ui.qa;

import com.mi.global.bbs.model.BaseResult;
import java.util.List;

public class QARequestItem extends BaseResult {
    public List<RequestItem> data;

    public static class RequestItem {
        public String dateline;
        public String fromid;
        public String fromname;
        public String icon;
        public String id;
        public String ignore;
        public String message;
        public String subject;
        public String tid;
        public String type;
        public String uid;
    }
}
