package com.alipay.mobile.common.logging.api.monitor;

import com.alipay.mobile.common.logging.api.LogCategory;
import java.util.HashMap;
import java.util.Map;

public enum PerformanceID {
    MONITORPOINT_NETWORK(LogCategory.CATEGORY_NETWORK),
    MONITORPOINT_WEBAPP(LogCategory.CATEGORY_WEBAPP),
    MONITORPOINT_SDKMONITOR(LogCategory.CATEGORY_SDKMONITOR),
    MONITORPOINT_SYNCLINK("synclink"),
    MONITORPOINT_SYNCPROTO("syncproto"),
    MONITORPOINT_PERFORMANCE(LogCategory.CATEGORY_PERFORMANCE),
    MONITORPOINT_FOOTPRINT(LogCategory.CATEGORY_FOOTPRINT),
    MONITORPOINT_KEYBIZTRACE(LogCategory.CATEGORY_KEYBIZTRACE);
    

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, PerformanceID> f953a = null;
    private String desc;

    static {
        int i;
        f953a = new HashMap();
        for (PerformanceID performanceID : values()) {
            f953a.put(performanceID.desc, performanceID);
        }
    }

    private PerformanceID(String str) {
        this.desc = str;
    }

    public String getDes() {
        return this.desc;
    }

    public static PerformanceID fromString(String str) {
        return f953a.get(str);
    }
}
