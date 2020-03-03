package com.xiaomi.market.sdk;

public enum ServerType {
    PRODUCT("https://api.developer.xiaomi.com/autoupdate/", "https://global.developer.xiaomi.com/autoupdate/"),
    STAGING("http://staging.api.developer.appstore.pt.xiaomi.com/autoupdate/", "http://global.staging.developer.appstore.pt.xiaomi.com/autoupdate/"),
    PREVIEW("http://preview.api.developer.appstore.pt.xiaomi.com/autoupdate/", "http://global.preview.developer.appstore.pt.xiaomi.com/autoupdate/");
    
    private String baseUrl;
    private String globalBaseUrl;

    private ServerType(String str, String str2) {
        this.baseUrl = str;
        this.globalBaseUrl = str2;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getGlobalBaseUrl() {
        return this.globalBaseUrl;
    }
}
