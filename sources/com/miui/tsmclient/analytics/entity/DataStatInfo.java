package com.miui.tsmclient.analytics.entity;

import com.alipay.sdk.util.i;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class DataStatInfo {
    @SerializedName("dataID")
    private int mDataID;
    @SerializedName("dataTime")
    private String mDataTime;
    @SerializedName("dataValue")
    private String mDataValue;

    public DataStatInfo() {
        this.mDataValue = "";
        this.mDataTime = "";
        this.mDataTime = System.currentTimeMillis() + "";
    }

    public DataStatInfo(int i) {
        this();
        this.mDataID = i;
    }

    public int getDataID() {
        return this.mDataID;
    }

    public void setDataID(int i) {
        this.mDataID = i;
    }

    public String getDataValue() {
        return this.mDataValue;
    }

    public void setDataValue(String str) {
        this.mDataValue = str;
    }

    public void setDataTime(String str) {
        this.mDataTime = str;
    }

    public String getDataTime() {
        return this.mDataTime;
    }

    public String toString() {
        return new Gson().toJson((Object) this);
    }

    public static String toServerData(List<DataStatInfo> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(Operators.ARRAY_START_STR);
        for (int i = 0; i < list.size(); i++) {
            DataStatInfo dataStatInfo = list.get(i);
            sb.append(Operators.BLOCK_START_STR);
            sb.append(dataStatInfo.getDataID());
            sb.append(i.b);
            sb.append(dataStatInfo.getDataValue());
            sb.append(i.b);
            sb.append(dataStatInfo.getDataTime());
            sb.append("}");
            if (i < list.size() - 1) {
                sb.append(",");
            }
        }
        sb.append(Operators.ARRAY_END_STR);
        return sb.toString();
    }
}
