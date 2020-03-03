package com.xiaomi.smarthome.stat;

import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.device.api.RpcErrorDescription;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.smarthome.core.server.internal.whitelist.WhiteListManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.shop.utils.NetworkUtils;
import com.xiaomi.smarthome.stat.report.StatReporter;
import java.util.UUID;
import org.json.JSONObject;

public abstract class StatResult {

    /* renamed from: a  reason: collision with root package name */
    ActionCounter f22758a;
    private StatReporter b = new StatReporter("result");

    private String c() {
        if (!CoreApi.a().D()) {
            return CoreApi.a().s();
        }
        return "";
    }

    private static boolean b(String str) {
        return "xiaomi.repeater.v1".equals(str) || "xiaomi.repeater.v2".equals(str) || "xiaomi.repeater.v3".equals(str);
    }

    public void a(int i, String str) {
        this.b.a("ctoc_synchronization_success", "num", Integer.valueOf(i), "did", str);
    }

    public void b(int i, String str) {
        this.b.a("ctoc_relieve_success", "num", Integer.valueOf(i), "did", str);
    }

    public void a(String str) {
        StatReporter statReporter = this.b;
        Object[] objArr = new Object[2];
        objArr[0] = "uid";
        if (str == null) {
            str = "null";
        }
        objArr[1] = str;
        statReporter.a("ctoc_login", objArr);
    }

    public void a(String str, long j) {
        this.b.a("adddevice_link_time.AP", "model", str, "time", Long.valueOf((System.currentTimeMillis() - j) / 1000));
    }

    public void a(String str, int i) {
        ActionCounter b2 = STAT.i.b();
        if (!TextUtils.isEmpty(str) && i != 0) {
            this.b.a("adddevice_network_success.ap", "model", str, "category", Integer.valueOf(i), "add_device_session", Long.valueOf(b2.a()), "add_device_order", Integer.valueOf(b2.b()));
        }
    }

    public void b(String str, long j) {
        if (!CoreApi.a().p()) {
            String c = c();
            ActionCounter b2 = STAT.i.b();
            this.b.a("add_fail_device", "model", str, "time", Long.valueOf(j), "add_device_session", Long.valueOf(b2.a()), "add_device_order", Integer.valueOf(b2.b()), "uid", c);
        }
    }

    public void a(String str, String str2, int i) {
        if (!CoreApi.a().p()) {
            String c = c();
            ActionCounter b2 = STAT.i.b();
            this.b.a("adddevice_system_success", "model", str, "miui_version", str2, "category", Integer.valueOf(i), "add_device_session", Long.valueOf(b2.a()), "add_device_order", Integer.valueOf(b2.b()), "uid", c);
        }
    }

    public void b(String str, String str2, int i) {
        if (!CoreApi.a().p()) {
            String c = c();
            ActionCounter b2 = STAT.i.b();
            this.b.a("adddevice_app_succrss", "model", str, "miui_version", str2, "category", Integer.valueOf(i), "add_device_session", Long.valueOf(b2.a()), "add_device_order", Integer.valueOf(b2.b()), "uid", c);
        }
    }

    public void a(int i) {
        this.b.a("wifi_pwd_length", "password_length", Integer.valueOf(i));
    }

    public long a(String str, int i, int i2) {
        ActionCounter b2 = STAT.i.b();
        return this.b.a("combo_link_result", "model", str, "add_device_session", Long.valueOf(b2.a()), "add_device_order", Integer.valueOf(b2.b()), "type", "finalResult", "errocode", Integer.valueOf(i), Tags.Order.ORDER_TRACE, Integer.valueOf(i2));
    }

    public void b(String str, int i) {
        if (!CoreApi.a().p()) {
            String c = c();
            this.b.a("adddevice_link_time_30min.AP", "model", str, "success", Integer.valueOf(i), "uid", c);
        }
    }

    public void c(String str, int i) {
        if (!CoreApi.a().p()) {
            this.b.a("adddevice_link_all_time.AP", "model", str, "time", Integer.valueOf(i));
        }
    }

    public void d(String str, int i) {
        if (!CoreApi.a().p()) {
            String c = c();
            this.b.a("adddevice_link_startlink", "model", str, "time", Integer.valueOf(i), "uid", c);
        }
    }

    public void a(String str, String str2) {
        if (!CoreApi.a().p()) {
            String c = c();
            this.b.a("add_bluetooth_success", "model", str, "type", str2, "uid", c);
        }
    }

    public void a(String str, String str2, int i, String str3, String str4) {
        if (!CoreApi.a().p()) {
            String c = c();
            this.b.a("add_bluetooth_fail", "model", str, "type", str2, "failcode", Integer.valueOf(i), "uid", c, "firversion", str3, "authtype", str4);
        }
    }

    public void a(String str, String str2, int i, UUID uuid, UUID uuid2) {
        String str3;
        String str4;
        if (!CoreApi.a().p()) {
            String c = c();
            if (uuid == null) {
                str3 = "";
            } else {
                str3 = uuid.toString();
            }
            if (uuid2 == null) {
                str4 = "";
            } else {
                str4 = uuid2.toString();
            }
            this.b.a("add_bluetooth_fail", "model", str, "type", str2, "failcode", Integer.valueOf(i), "uid", c, "service", str3, FirebaseAnalytics.Param.CHARACTER, str4);
        }
    }

    public void e(String str, int i) {
        STAT.i.b();
        this.b.a("check_plugin", "type", Integer.valueOf(i));
    }

    public void c(int i, String str) {
        this.b.a("plugin_download_error", "code", Integer.valueOf(i), "value", str);
    }

    public void a(int i, JSONObject jSONObject) {
        this.b.a("app_common_log", "type", Integer.valueOf(i), "value", jSONObject);
    }

    public void a(long j) {
        this.b.a("app_stat_time", "time", Long.valueOf(j));
    }

    public void a(long j, String str) {
        this.b.a("plugin_download_time", "time", Long.valueOf(j), "model", str);
    }

    public void a(long j, int i, String str, String str2) {
        this.b.a("plugin_rpc_time", "time", Long.valueOf(j), "status", Integer.valueOf(i), "params", str, "model", str2);
    }

    public void a(long j, String str, int i, String str2, String str3, String str4) {
        if (!b(str) && !WhiteListManager.a().d()) {
            AccountType c = AccountManager.a().c();
            int c2 = c(str);
            int a2 = RpcErrorDescription.a(i, str3);
            if (a2 == 0 || NetworkUtils.c()) {
                this.b.a("rpc_remote_result", "time", Long.valueOf(j), "errorcode", Integer.valueOf(a2), "model", str, "did", str2, "extra", str3, MessengerShareContentUtility.ATTACHMENT_PAYLOAD, str4, "accounttype", String.valueOf(c), "pluginversion", Integer.valueOf(c2));
            }
        }
    }

    public void b(long j, String str, int i, String str2, String str3, String str4) {
        if (!b(str) && !WhiteListManager.a().d()) {
            int c = c(str);
            this.b.a("rpc_local_result", "time", Long.valueOf(j), "errorcode", Integer.valueOf(i), "model", str, "did", str2, "extra", str3, MessengerShareContentUtility.ATTACHMENT_PAYLOAD, str4, "pluginversion", Integer.valueOf(c));
        }
    }

    private int c(String str) {
        PluginRecord c = PluginManager.a().c(str);
        if (c == null || c.h() == null) {
            return -1;
        }
        return c.h().g();
    }

    public void a() {
        this.f22758a = new ActionCounter();
    }

    /* access modifiers changed from: package-private */
    public ActionCounter b() {
        if (this.f22758a == null) {
            this.f22758a = new ActionCounter();
        }
        return this.f22758a;
    }

    public void a(String str, String str2, int i, int i2, boolean z) {
        this.b.a("band_mijia_card_result", "uid", str, "model", str2, "allcardnum", Integer.valueOf(i), "activecardnum", Integer.valueOf(i2), "haslock", Integer.valueOf(z ? 1 : 0));
    }

    public void f(String str, int i) {
        if (!b(str) && !WhiteListManager.a().d()) {
            this.b.a("ble_update_result", "model", str, "errorcode", Integer.valueOf(i));
        }
    }

    public void a(String str, int i, String str2) {
        if (!b(str) && !WhiteListManager.a().d()) {
            this.b.a("ble_update_result", "model", str, "errorcode", Integer.valueOf(i), "desc", str2);
        }
    }

    public void a(String str, int i, long j) {
        if (!b(str) && !WhiteListManager.a().d()) {
            this.b.a("ble_update_result", "errorcode", 0, "model", str, "step", Integer.valueOf(i), "duration", Long.valueOf(j));
        }
    }
}
