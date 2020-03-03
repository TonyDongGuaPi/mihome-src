package com.mi.global.bbs.model;

import java.util.List;

public class HotTopicModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<HotTopicColumnBean> columnBeanList;
        public List<HotTopicArticleBean> topicArticleBeanList;
    }
}
