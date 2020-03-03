package com.miui.tsmclient.common.net.host;

public class AssetsHost extends BaseHost {
    private static final String ASSETS_SERVICE_ONLINE = "https://sf.pay.xiaomi.com/";
    private static final String ASSETS_SERVICE_STAGING = "http://staging.sf.pay.xiaomi.com/";
    private static final String SERVICE_ID = "tsm-assets";

    public String getOnlineHost() {
        return ASSETS_SERVICE_ONLINE;
    }

    public String getServiceId() {
        return SERVICE_ID;
    }

    public String getStagingHost() {
        return ASSETS_SERVICE_STAGING;
    }
}
