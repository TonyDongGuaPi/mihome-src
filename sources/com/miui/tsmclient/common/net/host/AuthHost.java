package com.miui.tsmclient.common.net.host;

import java.io.File;

public class AuthHost extends BaseHost {
    private static String SERVER_ONLINE = "https://preview.tsmapi.pay.xiaomi.com/";
    private static String SERVER_STAGING = "http://staging.tsmapi.pay.xiaomi.com/";
    private static String SERVICE_ID = "tsm-auth";

    static {
        if (new File("/data/system/tsmconfig").exists()) {
            switch (getStagingIndex()) {
                case 1:
                    SERVER_STAGING = "http://staging1.tsmapi.pay.xiaomi.com/";
                    return;
                case 2:
                    SERVER_STAGING = "http://staging2.tsmapi.pay.xiaomi.com/";
                    return;
                case 3:
                    SERVER_STAGING = "http://staging3.tsmapi.pay.xiaomi.com/";
                    return;
                case 4:
                    return;
                default:
                    SERVER_STAGING = "http://staging.tsmapi.pay.xiaomi.com/";
                    return;
            }
        }
    }

    public String getStagingHost() {
        return SERVER_STAGING;
    }

    public String getOnlineHost() {
        return SERVER_ONLINE;
    }

    public String getServiceId() {
        return SERVICE_ID;
    }
}
