package com.mi.global.bbs.model;

import com.mi.global.bbs.model.ColumnDetailModel;

public class ColumnSubData {
    public static final int TYPE_EMPTY = 1;
    public static final int TYPE_SUB_COLUMN = 2;
    public static final int TYPE_SUB_SUGGEST = 3;
    private ColumnDetailModel.DataBean.ColumnInfo subColumn;
    private ColumnDetailModel.DataBean.ColumnInfo subSuggest;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public ColumnDetailModel.DataBean.ColumnInfo getSubColumn() {
        return this.subColumn;
    }

    public void setSubColumn(ColumnDetailModel.DataBean.ColumnInfo columnInfo) {
        setType(2);
        this.subColumn = columnInfo;
    }

    public ColumnDetailModel.DataBean.ColumnInfo getSubSuggest() {
        return this.subSuggest;
    }

    public void setSubSuggest(ColumnDetailModel.DataBean.ColumnInfo columnInfo) {
        setType(3);
        this.subSuggest = columnInfo;
    }
}
