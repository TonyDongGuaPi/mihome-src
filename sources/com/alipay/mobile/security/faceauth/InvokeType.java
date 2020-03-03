package com.alipay.mobile.security.faceauth;

import com.alipay.mobile.common.logging.api.LogCategory;

public enum InvokeType {
    NETWORK(LogCategory.CATEGORY_NETWORK),
    SERVER_FAIL("server_fail"),
    INTERRUPT("interrupt"),
    TIMEOUT("timeout"),
    NORMAL("normal"),
    FAIL("fail"),
    MONITOR("monitor"),
    LIVENESS_FAILED("liveness_failed"),
    VIDEO("video");
    
    public String value;

    private InvokeType(String str) {
        this.value = str;
    }
}
