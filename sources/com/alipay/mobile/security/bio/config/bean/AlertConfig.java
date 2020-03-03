package com.alipay.mobile.security.bio.config.bean;

import com.taobao.weex.el.parse.Operators;

public class AlertConfig {

    /* renamed from: a  reason: collision with root package name */
    private String f983a;
    private String b;
    private String c;
    private String d;
    private int e;

    public String getTitle() {
        return this.f983a;
    }

    public void setTitle(String str) {
        this.f983a = str;
    }

    public String getLeftButtonText() {
        return this.c;
    }

    public void setLeftButtonText(String str) {
        this.c = str;
    }

    public String getRightButtonText() {
        return this.d;
    }

    public void setRightButtonText(String str) {
        this.d = str;
    }

    public int getReturnCode() {
        return this.e;
    }

    public void setReturnCode(int i) {
        this.e = i;
    }

    public String getMessage() {
        return this.b;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public String toString() {
        return "AlertConfig{title='" + this.f983a + Operators.SINGLE_QUOTE + ", message='" + this.b + Operators.SINGLE_QUOTE + ", leftButtonText='" + this.c + Operators.SINGLE_QUOTE + ", rightButtonText='" + this.d + Operators.SINGLE_QUOTE + ", returnCode=" + this.e + Operators.BLOCK_END;
    }
}
