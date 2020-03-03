package com.xiaomi.smarthome.shop;

import android.app.Activity;
import android.content.Context;
import com.xiaomi.smarthome.frame.mistat.MiStatApi;
import com.xiaomi.youpin.youpin_common.api.IAppStatApi;
import java.util.Map;

public class AppStatApi implements IAppStatApi {
    public void a(int i, long j) {
        MiStatApi.a().a(i, j);
    }

    public void a(Context context, String str, String str2) {
        CurrentPage.a(str, str2);
        MiStatApi.a().a(context, str, str2);
    }

    public void a(Activity activity, String str) {
        MiStatApi.a().a(activity, str);
    }

    public void a(Context context, String str) {
        MiStatApi.a().a(context, str);
    }

    public void a() {
        MiStatApi.a().b();
    }

    public void b(Context context, String str) {
        MiStatApi.a().b(context, str);
    }

    public void a(String str, String str2) {
        MiStatApi.a().a(str, str2);
    }

    public void a(String str, String str2, Map map) {
        MiStatApi.a().a(str, str2, map);
    }

    public void a(String str, String str2, long j) {
        MiStatApi.a().a(str, str2, j);
    }

    public void a(String str, String str2, long j, Map map) {
        MiStatApi.a().a(str, str2, j, map);
    }

    public void b(String str, String str2, long j) {
        MiStatApi.a().b(str, str2, j);
    }

    public void a(String str, String str2, String str3) {
        MiStatApi.a().a(str, str2, str3);
    }
}
