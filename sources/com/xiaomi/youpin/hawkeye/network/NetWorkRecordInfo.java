package com.xiaomi.youpin.hawkeye.network;

import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.entity.StatType;

public class NetWorkRecordInfo extends BaseInfo {
    public long endTime;
    public String errorMessage;
    public String method;
    public long requestLength;
    public int responseCode;
    public long responseLength;
    public long startTime;
    public long totalCost;
    public long totalLength;
    public String url;

    public NetWorkRecordInfo() {
        this.mStatType = StatType.NETWORKINFO;
    }
}
