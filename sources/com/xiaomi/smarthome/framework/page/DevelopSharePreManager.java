package com.xiaomi.smarthome.framework.page;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi;
import com.xiaomi.smarthome.setting.PluginSetting;

public class DevelopSharePreManager {

    /* renamed from: a  reason: collision with root package name */
    private static DevelopSharePreManager f16719a = null;
    private static SharedPreferences b = CommonApplication.getAppContext().getSharedPreferences("developer_setting", 0);
    private static final String c = "is_open_hide_device_entrance_in_debug";
    private static final String d = "is_card_debug";

    private DevelopSharePreManager() {
    }

    public static DevelopSharePreManager a() {
        if (f16719a == null || b == null) {
            f16719a = new DevelopSharePreManager();
        }
        return f16719a;
    }

    public static void b() {
        f16719a = null;
    }

    public boolean c() {
        return b.getBoolean("is_force_to_check_update_in_debug", false);
    }

    public boolean d() {
        return b.getBoolean(c, false);
    }

    public void a(boolean z) {
        SharedPreferences.Editor edit = b.edit();
        edit.putBoolean(c, z);
        edit.commit();
    }

    public void b(boolean z) {
        SharedPreferences.Editor edit = b.edit();
        edit.putBoolean("is_force_to_check_update_in_debug", z);
        edit.commit();
    }

    public boolean e() {
        return b.getBoolean(d, false);
    }

    public void c(boolean z) {
        SharedPreferences.Editor edit = b.edit();
        edit.putBoolean(d, z);
        edit.commit();
    }

    public boolean f() {
        return b.getBoolean("rn_debug_enable", false);
    }

    public boolean g() {
        return b.getBoolean("rn_debug_enable_v2", false);
    }

    public void d(boolean z) {
        SharedPreferences.Editor edit = b.edit();
        edit.putBoolean("rn_debug_enable_v2", z);
        edit.commit();
    }

    public boolean h() {
        return b.getBoolean("rn_debug_toast_sdk_version", false);
    }

    public void e(boolean z) {
        SharedPreferences.Editor edit = b.edit();
        edit.putBoolean("rn_debug_toast_sdk_version", z);
        edit.commit();
    }

    public String i() {
        return b.getString("rn_debug_package_name_v2", "");
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        SharedPreferences.Editor edit = b.edit();
        edit.putString("rn_debug_package_name_v2", str);
        edit.commit();
    }

    public String j() {
        return b.getString("rn_debug_package_name", "");
    }

    public String k() {
        return b.getString("rn_debug_model", "");
    }

    public void a(boolean z, String str, String str2, boolean z2) {
        PluginSetting.a(z2);
        SharedPreferences.Editor edit = b.edit();
        edit.putBoolean("rn_debug_enable", z);
        if (TextUtils.equals(str, "*")) {
            PluginSmartHomeApi.a(CommonApplication.getAppContext(), str2);
        } else {
            edit.putString("rn_debug_package_name", str);
            edit.putString("rn_debug_model", str2);
        }
        edit.commit();
    }
}
