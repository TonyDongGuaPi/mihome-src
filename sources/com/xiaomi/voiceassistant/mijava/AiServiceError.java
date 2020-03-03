package com.xiaomi.voiceassistant.mijava;

import com.taobao.weex.el.parse.Operators;

public class AiServiceError {

    /* renamed from: a  reason: collision with root package name */
    public static int f23136a = 0;
    public static int b = 1;
    public static int c = 2;
    public static int d = 3;
    public int e;
    public int f;
    public String g;

    private AiServiceError() {
    }

    public String toString() {
        return "AiServiceError{errorType=" + this.e + ", errorCode=" + this.f + ", errorMsg='" + this.g + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public AiServiceError(int i, String str, int i2) {
        this.f = i;
        this.g = str;
        this.e = i2;
    }
}
