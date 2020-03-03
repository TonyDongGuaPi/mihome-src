package com.xiaomi.payment.channel;

import com.tencent.mm.opensdk.modelbase.BaseResp;

public class WXPayUtils {

    /* renamed from: a  reason: collision with root package name */
    public static WXPayCallback f1495a;
    public static String b;

    public interface WXPayCallback {
        void a(BaseResp baseResp);
    }

    private WXPayUtils() {
    }

    public static void a(WXPayCallback wXPayCallback) {
        f1495a = wXPayCallback;
    }

    public static void a() {
        f1495a = null;
    }
}
