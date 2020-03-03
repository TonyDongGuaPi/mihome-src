package com.xiaomi.infra.galaxy.fds.auth.sso;

public enum SSOParam {
    SERVICE_TOKEN("serviceToken"),
    SID("sid"),
    APP_ID("appId");
    
    private final String name;

    private SSOParam(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
