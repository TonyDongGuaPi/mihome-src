package com.mi.global.bbs.model;

import com.mi.global.bbs.model.ColumnHomeModel;
import java.util.List;

public class ColumnArticleModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<ColumnHomeModel.DataBean.ColumnArticle> list;
    }
}
