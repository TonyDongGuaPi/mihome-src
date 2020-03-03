package com.mi.global.bbs.model;

import com.mi.global.bbs.model.ColumnDetailModel;
import java.util.List;

public class ColumnListModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<ColumnDetailModel.DataBean.ColumnInfo> list;
    }
}
