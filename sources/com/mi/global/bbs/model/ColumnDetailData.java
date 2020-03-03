package com.mi.global.bbs.model;

import com.mi.global.bbs.model.ColumnDetailModel;
import com.mi.global.bbs.model.ColumnHomeModel;
import java.util.List;

public class ColumnDetailData {
    public static final int TYPE_ARTICLES = 2;
    public static final int TYPE_HEADER = 1;
    private ColumnHomeModel.DataBean.ColumnArticle columnArticle;
    private ColumnDetailModel.DataBean.ColumnInfo columnInfo;
    private List<ColumnDetailModel.DataBean.SubcribUser> followers;
    public boolean subcribStatus;
    private int type;

    public int getType() {
        return this.type;
    }

    private void setType(int i) {
        this.type = i;
    }

    public List<ColumnDetailModel.DataBean.SubcribUser> getFollowers() {
        return this.followers;
    }

    public void setFollowers(List<ColumnDetailModel.DataBean.SubcribUser> list) {
        this.followers = list;
    }

    public ColumnDetailModel.DataBean.ColumnInfo getColumnInfo() {
        return this.columnInfo;
    }

    public void setColumnInfo(ColumnDetailModel.DataBean.ColumnInfo columnInfo2) {
        setType(1);
        this.columnInfo = columnInfo2;
    }

    public ColumnHomeModel.DataBean.ColumnArticle getColumnArticle() {
        return this.columnArticle;
    }

    public void setColumnArticle(ColumnHomeModel.DataBean.ColumnArticle columnArticle2) {
        setType(2);
        this.columnArticle = columnArticle2;
    }
}
