package com.xiaomi.smarthome.application;

import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.PreferenceUtil;

/* renamed from: com.xiaomi.smarthome.application.-$$Lambda$SHApplication$GXTTPc92oEr1XgTHRlWO0aQ-3C0  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SHApplication$GXTTPc92oEr1XgTHRlWO0aQ3C0 implements Runnable {
    public static final /* synthetic */ $$Lambda$SHApplication$GXTTPc92oEr1XgTHRlWO0aQ3C0 INSTANCE = new $$Lambda$SHApplication$GXTTPc92oEr1XgTHRlWO0aQ3C0();

    private /* synthetic */ $$Lambda$SHApplication$GXTTPc92oEr1XgTHRlWO0aQ3C0() {
    }

    public final void run() {
        PreferenceUtil.setStringPref(ShopApp.instance, "app_motto", "有品生活·更好选择");
    }
}
