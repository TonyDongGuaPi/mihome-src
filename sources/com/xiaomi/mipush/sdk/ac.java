package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiPushClient;

final class ac implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f11526a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;

    ac(String str, String str2, String str3) {
        this.f11526a = str;
        this.b = str2;
        this.c = str3;
    }

    public void run() {
        MiPushClient.b(MiPushClient.l, this.f11526a, this.b, (MiPushClient.MiPushClientCallback) null, this.c);
    }
}
