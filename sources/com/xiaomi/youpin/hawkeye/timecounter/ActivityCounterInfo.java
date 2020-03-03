package com.xiaomi.youpin.hawkeye.timecounter;

import com.xiaomi.youpin.hawkeye.entity.BaseInfo;
import com.xiaomi.youpin.hawkeye.entity.StatType;

public class ActivityCounterInfo extends BaseInfo {
    public String curActivity;
    public long launchCost;
    public long otherCost;
    public long pauseCost;
    public String prevActivity;
    public long renderCost;
    public long totalCost;

    public ActivityCounterInfo() {
        this.mStatType = StatType.PAGETRANSITION;
    }
}
