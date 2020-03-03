package com.xiaomi.infra.galaxy.fds;

public enum RequestParameter {
    RESPONSE_CONTENT_TYPE("response-content-type", "Content-Type"),
    RESPONSE_CACHE_CONTROL("response-cache-control", "Cache-Control"),
    RESPONSE_EXPIRES("response-expires", "Expires"),
    RESPONSE_CONTENT_ENCODING("response-content-encoding", "Content-Encoding"),
    RESPONSE_CONTENT_DISPOSITION("response-content-disposition", "Content-Disposition");
    
    private final String header;
    private final String param;

    private RequestParameter(String str, String str2) {
        this.param = str;
        this.header = str2;
    }

    public String getParam() {
        return this.param;
    }

    public String getHeader() {
        return this.header;
    }
}
