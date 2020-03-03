package com.xiaomi.youpin.hawkeye.appstart;

import com.xiaomi.youpin.hawkeye.entity.BaseInfo;

public class AppStartInfo extends BaseInfo {
    public long launchTime;
    public long startTime;
    public int startType;

    public AppStartInfo(long j, long j2, int i) {
        this.startTime = j;
        this.launchTime = j2;
        this.startType = i;
    }
}
