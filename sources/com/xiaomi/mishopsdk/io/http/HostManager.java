package com.xiaomi.mishopsdk.io.http;

public class HostManager {
    public static String FORMAL_DOMAIN_APP_SHOPAPI = "http://api.m.mi.com/v1/";
    public static String FORMAL_DOMAIN_APP_SHOPAPI_HTTPS = "https://api.m.mi.com/v1/";
    private static final String FORMAL_DOMAIN_APP_SHOPAPI_HTTPS_V1 = "https://api.m.mi.com/v1/";
    private static final String FORMAL_DOMAIN_APP_SHOPAPI_HTTPS_V2 = "https://api2.m.mi.com/v1/";
    public static final String FORMAL_DOMAIN_APP_SHOPAPI_V1 = "http://api.m.mi.com/v1/";
    public static final String URL_XIAOMI_SHOP_GUEST_URL = "http://bbs.xiaomi.cn/thread-10953766-1-1.html";

    public static void setHttpV1() {
        FORMAL_DOMAIN_APP_SHOPAPI = FORMAL_DOMAIN_APP_SHOPAPI_V1;
        FORMAL_DOMAIN_APP_SHOPAPI_HTTPS = FORMAL_DOMAIN_APP_SHOPAPI_HTTPS_V1;
    }

    public static void setHttpV2() {
        FORMAL_DOMAIN_APP_SHOPAPI = FORMAL_DOMAIN_APP_SHOPAPI_HTTPS_V2;
        FORMAL_DOMAIN_APP_SHOPAPI_HTTPS = FORMAL_DOMAIN_APP_SHOPAPI_HTTPS_V2;
    }
}
