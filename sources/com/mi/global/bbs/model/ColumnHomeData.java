package com.mi.global.bbs.model;

import com.mi.global.bbs.model.ColumnHomeModel;
import java.util.List;

public class ColumnHomeData {
    public static final int TYPE_HOT_ARTICLES = 3;
    public static final int TYPE_HOT_TOPIC = 2;
    public static final int TYPE_TITLE = 1;
    private HomeFormTitle formTitle;
    private ColumnHomeModel.DataBean.ColumnArticle topicArticleBean;
    private List<ColumnHomeModel.DataBean.ColumnRecommend> topicColumnBeenList;
    private int type;

    public int getType() {
        return this.type;
    }

    private void setType(int i) {
        this.type = i;
    }

    public List<ColumnHomeModel.DataBean.ColumnRecommend> getTopicColumnBeenList() {
        return this.topicColumnBeenList;
    }

    public void setTopicColumnBeenList(List<ColumnHomeModel.DataBean.ColumnRecommend> list) {
        setType(2);
        this.topicColumnBeenList = list;
    }

    public HomeFormTitle getFormTitle() {
        return this.formTitle;
    }

    public void setFormTitle(HomeFormTitle homeFormTitle) {
        setType(1);
        this.formTitle = homeFormTitle;
    }

    public ColumnHomeModel.DataBean.ColumnArticle getTopicArticleBean() {
        return this.topicArticleBean;
    }

    public void setTopicArticleBean(ColumnHomeModel.DataBean.ColumnArticle columnArticle) {
        setType(3);
        this.topicArticleBean = columnArticle;
    }
}
