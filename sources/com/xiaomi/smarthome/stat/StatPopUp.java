package com.xiaomi.smarthome.stat;

import com.xiaomi.smarthome.stat.report.StatReporter;

public abstract class StatPopUp {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22756a = new StatReporter("popup");

    public void a(String str) {
        this.f22756a.a("miui_bottom_find", "model", str);
    }

    public void b(String str) {
        this.f22756a.a("miui_push_find", "model", str);
    }

    public void c(String str) {
        this.f22756a.a("app_bottom_popup", "model", str);
    }

    public void a() {
        this.f22756a.a("app_bottom_autodisc_popup", new Object[0]);
    }

    public void a(String str, int i) {
        this.f22756a.a("miui_bottom_info", "model", str, "num", Integer.valueOf(i));
    }

    public void b(String str, int i) {
        this.f22756a.a("miui_push_info", "model", str, "num", Integer.valueOf(i));
    }

    public void a(boolean z) {
        StatReporter statReporter = this.f22756a;
        int i = 2;
        Object[] objArr = new Object[2];
        objArr[0] = "type";
        if (!z) {
            i = 1;
        }
        objArr[1] = Integer.valueOf(i);
        statReporter.a("miui_mijia_card", objArr);
    }

    public void b() {
        this.f22756a.a("home_device_popup", new Object[0]);
    }

    public void c() {
        this.f22756a.a("home_score_popup", new Object[0]);
    }

    public void d(String str) {
        this.f22756a.a("home_ad_popup", "url", str);
    }

    public void d() {
        this.f22756a.a("miui_card_popup", new Object[0]);
    }

    public void e(String str) {
        this.f22756a.a("automation_ad_popup", "url", str);
    }

    public void f(String str) {
        this.f22756a.a("home_nodevice_ad_popup", "url", str);
    }

    public void g(String str) {
        this.f22756a.a("profile_ad_popup", "url", str);
    }

    public void h(String str) {
        this.f22756a.a("profile_service_icon_popup", "url", str);
    }

    public void i(String str) {
        this.f22756a.a("pintro_ad_popup", "url", str);
    }

    public void j(String str) {
        this.f22756a.a("profile_opd_ad_popup", "url", str);
    }

    public void a(String str, String str2) {
        this.f22756a.a("page_open_popup", "url", str, "title", str2);
    }

    public void a(String str, String str2, String str3) {
        this.f22756a.a("page_close_popup", "url", str, "title", str2);
    }

    public void e() {
        this.f22756a.a("infrared_popup", new Object[0]);
    }

    public void k(String str) {
        this.f22756a.a("voice_mike_popup", "model", str);
    }

    public void f() {
        this.f22756a.a("pintro_storage_popup", new Object[0]);
    }

    public void l(String str) {
        this.f22756a.a("recommend_lockspeaker_automation", "model", str);
    }

    public void m(String str) {
        this.f22756a.a("recommend_lockspeaker_touchspeaker", "type", str);
    }

    public void b(String str, String str2) {
        this.f22756a.a("rec_auto_edit", "model", str, "sr_id", str2);
    }

    public void c(String str, String str2) {
        this.f22756a.a("rec_choose_yesdevice", "model", str, "sr_id", str2);
    }

    public void d(String str, String str2) {
        this.f22756a.a("rec_choose_recdevice", "model", str, "sr_id", str2);
    }

    public void e(String str, String str2) {
        this.f22756a.a("rec_choose_recmoredevice", "model", str, "sr_id", str2);
    }

    public void f(String str, String str2) {
        this.f22756a.a("rec_auto_yesdevice", "model", str, "sr_id", str2);
    }

    public void g(String str, String str2) {
        this.f22756a.a("rec_auto_nodevice", "model", str, "sr_id", str2);
    }

    public void h(String str, String str2) {
        this.f22756a.a("rec_dll_nocble", "model", str, "sr_id", str2);
    }

    public void i(String str, String str2) {
        this.f22756a.a("rec_choose_conble", "model", str, "sr_id", str2);
    }

    public void j(String str, String str2) {
        this.f22756a.a("rec_choose_recble", "model", str, "sr_id", str2);
    }

    public void k(String str, String str2) {
        this.f22756a.a("rec_light_plugin", "model", str, "sr_id", str2);
    }

    public void l(String str, String str2) {
        this.f22756a.a("rec_light_auto", "model", str, "type", str2);
    }

    public void m(String str, String str2) {
        this.f22756a.a("recommend_lockspeaker_setting", "model", str, "type", str2);
    }

    public void n(String str) {
        this.f22756a.a("recommend_lockspeaker_chosespeaker", "model", str);
    }

    public void a(int i) {
        this.f22756a.a("homeshare_acceptsuccess_show", "from", Integer.valueOf(i));
    }

    public void g() {
        this.f22756a.a("oversea_ecom_popup", new Object[0]);
    }
}
