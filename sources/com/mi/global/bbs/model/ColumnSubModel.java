package com.mi.global.bbs.model;

import com.mi.global.bbs.model.ColumnDetailModel;
import java.util.List;

public class ColumnSubModel extends BaseResult {
    public DataBean data;

    public static class DataBean {
        public List<ColumnDetailModel.DataBean.ColumnInfo> List;
        public List<ColumnDetailModel.DataBean.ColumnInfo> recommend;
    }
}
