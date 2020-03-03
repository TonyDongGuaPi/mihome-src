package com.xiaomi.smarthome.stat;

import android.app.Activity;
import com.xiaomi.smarthome.ad.api.AdType;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.stat.report.StatLogSender;
import com.xiaomi.smarthome.stat.report.StatReporter;

public abstract class StatApp {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22750a = new StatReporter("app");

    public void a() {
        this.f22750a.a("entry_desk", new Object[0]);
    }

    public void a(String str) {
        this.f22750a.a("entry_lock", "did", str);
    }

    public void b(String str) {
        this.f22750a.b("entry_uri", str, new Object[0]);
    }

    public void a(String str, String str2) {
        this.f22750a.a("entry_shortcut_device", "creator", str, "did", str2);
    }

    public void b(String str, String str2) {
        this.f22750a.a("entry_shortcut_scene", "creator", str, PageUrl.j, str2);
    }

    public void c(String str) {
        this.f22750a.a("entry_shortcut_sound", "uid", str);
    }

    public void a(String str, String str2, String str3) {
        this.f22750a.b("entry_push", str3, "type", str, "id", str2);
    }

    public void c(String str, String str2) {
        this.f22750a.a("entry_notice_board", "model", str, "did", str2);
    }

    public void a(Activity activity) {
        StatLogSender.b().a(StatLogSender.f22763a, activity);
    }

    public void b(Activity activity) {
        StatLogSender.b().a(StatLogSender.b, activity);
    }

    public void b() {
        this.f22750a.a(AdType.e, new Object[0]);
    }
}
