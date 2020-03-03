package com.miui.tsmclient.common.net.host;

import java.io.File;

public class ActivityHost extends BaseHost {
    private static final String ACTIVITY_SERVICE_ONLINE = "https://tsmactivity.pay.xiaomi.com/";
    private static String ACTIVITY_SERVICE_STAGING = "http://staging.tsmactivity.pay.xiaomi.com/";
    private static String SERVICE_ID = "tsm-activity-api";

    public String getOnlineHost() {
        return ACTIVITY_SERVICE_ONLINE;
    }

    static {
        if (new File("/data/system/xiaomi_account_preview").exists()) {
            switch (getStagingIndex()) {
                case 1:
                    ACTIVITY_SERVICE_STAGING = "http://staging1.tsmactivity.pay.xiaomi.com/";
                    SERVICE_ID = "tsm-activity-api-1";
                    return;
                case 2:
                    ACTIVITY_SERVICE_STAGING = "http://staging2.tsmactivity.pay.xiaomi.com/";
                    SERVICE_ID = "tsm-activity-api-2";
                    return;
                case 3:
                    ACTIVITY_SERVICE_STAGING = "http://staging3.tsmactivity.pay.xiaomi.com/";
                    SERVICE_ID = "tsm-activity-api-3";
                    return;
                default:
                    ACTIVITY_SERVICE_STAGING = "http://staging.tsmactivity.pay.xiaomi.com/";
                    SERVICE_ID = "tsm-activity-api";
                    return;
            }
        }
    }

    public String getStagingHost() {
        return ACTIVITY_SERVICE_STAGING;
    }

    public String getServiceId() {
        return SERVICE_ID;
    }
}
