package com.alipay.mobile.security.bio.config.bean;

import com.taobao.weex.el.parse.Operators;

public class NavigatePage {
    public static final String DEFAULT_URL = "https://render.alipay.com/p/f/fd-j8l9yjja/index.html";

    /* renamed from: a  reason: collision with root package name */
    private boolean f987a = false;
    private String b = DEFAULT_URL;

    public void setEnable(boolean z) {
        this.f987a = z;
    }

    public boolean isEnable() {
        return this.f987a;
    }

    public void setUrl(String str) {
        this.b = str;
    }

    public String getUrl() {
        return this.b;
    }

    public String toString() {
        return "NavigatePage{enable=" + this.f987a + ", url='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
