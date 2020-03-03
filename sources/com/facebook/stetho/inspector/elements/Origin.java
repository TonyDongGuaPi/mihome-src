package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.json.annotation.JsonValue;
import com.taobao.weex.http.WXHttpUtil;

public enum Origin {
    INJECTED("injected"),
    USER_AGENT(WXHttpUtil.KEY_USER_AGENT),
    INSPECTOR("inspector"),
    REGULAR("regular");
    
    private final String mValue;

    private Origin(String str) {
        this.mValue = str;
    }

    @JsonValue
    public String getProtocolValue() {
        return this.mValue;
    }
}
