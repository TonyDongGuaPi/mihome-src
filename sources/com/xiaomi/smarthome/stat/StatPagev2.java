package com.xiaomi.smarthome.stat;

import android.util.Log;
import com.alipay.zoloz.toyger.face.ToygerFaceAlgorithmConfig;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.stat.report.StatReporter;

public abstract class StatPagev2 {

    /* renamed from: a  reason: collision with root package name */
    private StatReporter f22755a = new StatReporter("pgv2");

    private long t(String str) {
        return this.f22755a.a(str, "page", 1);
    }

    private long i(String str, long j) {
        return this.f22755a.a(str, "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    private long j(String str, long j) {
        return j > 0 ? i(str, j) : t(str);
    }

    public long a(long j) {
        return j("page_addcamerareset", j);
    }

    public long b(long j) {
        return j("page_addhome", j);
    }

    public long c(long j) {
        return j("page_addcamera", j);
    }

    public long d(long j) {
        return j("page_addother", j);
    }

    public long e(long j) {
        return j("page_adddevicereset", j);
    }

    private long a(String str, long j, int i, String str2) {
        return this.f22755a.a(str, "add_device_session", Long.valueOf(j), "add_device_order", Integer.valueOf(i), "model", str2, "page", 1);
    }

    private long a(String str, long j, int i, String str2, long j2) {
        return this.f22755a.a(str, "add_device_session", Long.valueOf(j), "add_device_order", Integer.valueOf(i), "model", str2, "time", Long.valueOf((System.currentTimeMillis() - j2) / 1000), "page", 2);
    }

    private long b(String str, long j, int i, String str2, long j2) {
        return j2 > 0 ? a(str, j, i, str2, j2) : a(str, j, i, str2);
    }

    public long a(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_failconnect", b.a(), b.b(), str, j);
    }

    public long b(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_wlan", b.a(), b.b(), str, j);
    }

    public long c(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_router", b.a(), b.b(), str, j);
    }

    public long d(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_mobile_device", b.a(), b.b(), str, j);
    }

    public long e(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_failovertime2nd", b.a(), b.b(), str, j);
    }

    public long f(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_failovertime", b.a(), b.b(), str, j);
    }

    public long g(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_failtm", b.a(), b.b(), str, j);
    }

    public long h(String str, long j) {
        ActionCounter b = STAT.i.b();
        return b("page_failtm2nd", b.a(), b.b(), str, j);
    }

    public long a() {
        return this.f22755a.a("page_addhome", new Object[0]);
    }

    public long b() {
        return this.f22755a.a("page_adddevice", new Object[0]);
    }

    public long c() {
        return this.f22755a.a("page_search", new Object[0]);
    }

    public long a(long j, String str, int i, int i2, int i3, String str2) {
        if (!CoreApi.a().p()) {
            String str3 = "";
            long j2 = 0;
            if (!CoreApi.a().D()) {
                str3 = CoreApi.a().s();
                j2 = j;
            }
            ActionCounter b = STAT.i.b();
            return this.f22755a.a("page_device_fail", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()), "step", Integer.valueOf(i), "uid", str3, "did", Long.valueOf(j2), "step_duration", Integer.valueOf(i2), "total_duration", Integer.valueOf(i3), "firversion", str2);
        }
        Log.d("StatPageV2", String.format("page ap device cancel fail ,model =%s,step = %d, step duration =%d ,total duration = %d", new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)}));
        return -1;
    }

    public long a(String str, long j, int i, int i2) {
        if (!CoreApi.a().p()) {
            String str2 = "";
            if (!CoreApi.a().D()) {
                str2 = CoreApi.a().s();
            } else {
                j = 0;
            }
            ActionCounter b = STAT.i.b();
            return this.f22755a.a("page_device_success", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()), "step", Integer.valueOf(i), "uid", str2, "did", Long.valueOf(j), "step_duration", Integer.valueOf(i2));
        }
        Log.d("StatPageV2", String.format("page device success ,model =%s,step = %d, step duration =%d ", new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)}));
        return -1;
    }

    public long a(long j, String str, int i, String str2) {
        if (CoreApi.a().p()) {
            return -1;
        }
        String str3 = "";
        if (!CoreApi.a().D()) {
            str3 = CoreApi.a().s();
        } else {
            j = 0;
        }
        ActionCounter b = STAT.i.b();
        return this.f22755a.a("page_device_fail", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()), "step", Integer.valueOf(i), "uid", str3, "did", Long.valueOf(j), "firversion", str2);
    }

    public long a(long j, String str, int i, int i2, String str2) {
        return a(j, str, i, i2, "", "", str2);
    }

    public long a(long j, String str, int i, int i2, String str2, String str3, String str4) {
        if (CoreApi.a().p()) {
            return -1;
        }
        String str5 = "";
        if (!CoreApi.a().D()) {
            str5 = CoreApi.a().s();
        } else {
            j = 0;
        }
        ActionCounter b = STAT.i.b();
        return this.f22755a.a("page_device_fail", "model", str, "add_device_session", Long.valueOf(b.a()), "add_device_order", Integer.valueOf(b.b()), "step", Integer.valueOf(i), "ble", Integer.valueOf(i2), "uid", str5, "did", Long.valueOf(j), DeviceTagInterface.e, str2, "mac", str3, "firversion", str4);
    }

    public long a(long j, String str) {
        if (j == 0) {
            return this.f22755a.a("page_scan_device", "page", 1, "model", str);
        }
        return this.f22755a.a("page_scan_device", "page", 2, "model", str, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long b(long j, String str) {
        if (j == 0) {
            return this.f22755a.a("page_device_Initialization", "page", 1, "model", str);
        }
        return this.f22755a.a("page_device_Initialization", "page", 2, "model", str, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long f(long j) {
        if (j == 0) {
            return this.f22755a.a("page_home", "page", 1);
        }
        return this.f22755a.a("page_home", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long g(long j) {
        if (j == 0) {
            return this.f22755a.a("auto_fav_stay", "page", 1);
        }
        return this.f22755a.a("auto_fav_stay", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long h(long j) {
        if (j == 0) {
            return this.f22755a.a("page_mycanter", "page", 1);
        }
        return this.f22755a.a("page_mycanter", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long i(long j) {
        if (j == 0) {
            return this.f22755a.a("page_deviceinfo", "page", 1);
        }
        return this.f22755a.a("page_deviceinfo", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long j(long j) {
        if (j == 0) {
            return this.f22755a.a("page_pluginedit", "page", 1);
        }
        return this.f22755a.a("page_pluginedit", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long a(long j, int i, int i2, int i3, int i4) {
        if (j == 0) {
            return this.f22755a.a("page_family", "page", 1, "home_number", Integer.valueOf(i), "sharehome_number", Integer.valueOf(i2), "invite_number", Integer.valueOf(i3), "from", Integer.valueOf(i4));
        }
        return this.f22755a.a("page_family", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long k(long j) {
        if (j == 0) {
            return this.f22755a.a("page_roomadmin", "page", 1);
        }
        return this.f22755a.a("page_roomadmin", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long l(long j) {
        if (j == 0) {
            return this.f22755a.a("page_roomadjust", "page", 1);
        }
        return this.f22755a.a("page_roomadjust", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long m(long j) {
        if (j == 0) {
            return this.f22755a.a("page_roomset", "page", 1);
        }
        return this.f22755a.a("page_roomset", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long n(long j) {
        if (j == 0) {
            return this.f22755a.a("page_share", "page", 1);
        }
        return this.f22755a.a("page_share", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public long a(String str, String str2) {
        return this.f22755a.a("page_voice", "model", str, "category", str2);
    }

    public long b(String str, String str2) {
        return this.f22755a.a("page_soundbox_popup", "model", str, "category", str2);
    }

    public void a(String str) {
        this.f22755a.a("plugin_share_show", "model", str);
    }

    public void a(String str, int i) {
        this.f22755a.a("plugin_deviceshare_show", "model", str, "contacts_number", Integer.valueOf(i));
    }

    public void d() {
        this.f22755a.a("plugin_deviceshare_mi_show", new Object[0]);
    }

    public void a(int i) {
        this.f22755a.a("plugin_deviceshare_family_show", "family_number", Integer.valueOf(i));
    }

    public void a(int i, int i2) {
        this.f22755a.a("plugin_deviceshare_edit_show", "shared_users", Integer.valueOf(i), "page_ref", Integer.valueOf(i2));
    }

    public void b(int i) {
        this.f22755a.a("mine_deviceshare_show", "shareable_devices", Integer.valueOf(i));
    }

    public void c(int i) {
        this.f22755a.a("mine_myminiprograms_show", "shareable_devices", Integer.valueOf(i));
    }

    public void b(String str, int i) {
        this.f22755a.a("addplugin_deviceshare_show", "model", str, "contacts_number", Integer.valueOf(i));
    }

    public void a(boolean z) {
        this.f22755a.a("home_device_show", ToygerFaceAlgorithmConfig.DARK, Integer.valueOf(z ? 1 : 0));
    }

    public void e() {
        this.f22755a.a("home_room_show", new Object[0]);
    }

    public void a(int i, int i2, int i3) {
        this.f22755a.a("home_navigation_show_dropdown", "category_number", Integer.valueOf(i), "share", Integer.valueOf(i2), "nearby", Integer.valueOf(i3));
    }

    public long o(long j) {
        if (j == 0) {
            return this.f22755a.a("room_room_detail_show", "page", 1);
        }
        return this.f22755a.a("room_room_detail_show", "page", 2, "time", Float.valueOf(((float) (System.currentTimeMillis() - j)) / 1000.0f));
    }

    public void d(int i) {
        this.f22755a.a("room_device_edit_show", "from", Integer.valueOf(i));
    }

    public void f() {
        this.f22755a.a("room_addroom_show", new Object[0]);
    }

    public long b(int i, int i2) {
        return this.f22755a.a("home_management_show", "from", Integer.valueOf(i), "homenumber", Integer.valueOf(i2));
    }

    public long e(int i) {
        return this.f22755a.a("home_management_numberdetail_show", "type", Integer.valueOf(i));
    }

    public long g() {
        return this.f22755a.a("my_share_show", new Object[0]);
    }

    public long p(long j) {
        return this.f22755a.a("homeshare_acceptalert_show", "home_id", Long.valueOf(j));
    }

    public long f(int i) {
        return this.f22755a.a("homeshare_acceptsuccess_show", "from", Integer.valueOf(i));
    }

    public long h() {
        return this.f22755a.a("homeshare_mi_show", new Object[0]);
    }

    public long i() {
        return this.f22755a.a("homeshare_education_show", new Object[0]);
    }

    public long c(int i, int i2) {
        return this.f22755a.a("homeshare_education_share_show", "contactnumber", Integer.valueOf(i), "homenumber", Integer.valueOf(i2));
    }

    public long g(int i) {
        return this.f22755a.a("adddevice_devicelist_show", "nearby-device", Integer.valueOf(i));
    }

    public long h(int i) {
        return this.f22755a.a("adddevice_nearbydevice_show", "nearby-device", Integer.valueOf(i));
    }

    public long j() {
        return this.f22755a.a("adddevice_scan_show", new Object[0]);
    }

    public long k() {
        return this.f22755a.a("adddevice_search_show", new Object[0]);
    }

    public long b(String str) {
        return this.f22755a.a("adddevice_selectroom_show", "model", str);
    }

    public long c(String str) {
        return this.f22755a.a("adddevice_rename_show", "model", str);
    }

    public void d(String str) {
        this.f22755a.a("adddevice_reset_show", "model", str);
    }

    public void e(String str) {
        this.f22755a.a("adddevice_scanning_show", "model", str);
    }

    public void f(String str) {
        this.f22755a.a("adddevice_scanning_fail_show", "model", str);
    }

    public void c(String str, int i) {
        this.f22755a.a("adddevice_wifi_show", "model", str, "from", Integer.valueOf(i));
    }

    public void g(String str) {
        this.f22755a.a("adddevice_QR_show", "model", str);
    }

    public void h(String str) {
        this.f22755a.a("adddevice_connecting_show", "model", str);
    }

    public void d(String str, int i) {
        this.f22755a.a("adddevice_connect_fail_show", "model", str, "type", Integer.valueOf(i));
    }

    public void i(String str) {
        this.f22755a.a("adddevice_connect_success_show", "model", str);
    }

    public void j(String str) {
        this.f22755a.a("adddevice_BLE_scanning_show", "model", str);
    }

    public void k(String str) {
        this.f22755a.a("adddevice_BLE_scanfail_show", "model", str);
    }

    public void e(String str, int i) {
        this.f22755a.a("adddevice_BLE_deviceconfirm_show", "model", str, "from", Integer.valueOf(i));
    }

    public void f(String str, int i) {
        this.f22755a.a("adddevice_BLE_neardevice_show", "model", str, "from", Integer.valueOf(i));
    }

    public void g(String str, int i) {
        this.f22755a.a("adddevice_BLE_appconfirm_show", "model", str, "from", Integer.valueOf(i));
    }

    public void l(String str) {
        this.f22755a.a("adddevice_BLE_timeout_show", "model", str);
    }

    public void m(String str) {
        this.f22755a.a("adddevice_BLE_connecting_show", "model", str);
    }

    public void h(String str, int i) {
        this.f22755a.a("adddevice_BLE_androidconnectfail_show", "model", str, "type", Integer.valueOf(i));
    }

    public void n(String str) {
        this.f22755a.a("adddevice_BLE_androidconnectsuccess_show", "model", str);
    }

    public void i(String str, int i) {
        this.f22755a.a("addlightgroup_creat_show", "from", str, "device_number", Integer.valueOf(i));
    }

    public void o(String str) {
        this.f22755a.a("addlightgroup_try_show", "model", str);
    }

    public void p(String str) {
        this.f22755a.a("lightgroup_edit_show", "model", str);
    }

    public void q(String str) {
        this.f22755a.a("lightgroup_upgrade_show", "model", str);
    }

    public void r(String str) {
        this.f22755a.a("lightgroup_mesh_upgrade_show", "model", str);
    }

    public void s(String str) {
        this.f22755a.a("lightgroup_wifi_upgrade_show", "model", str);
    }

    public void a(int i, String str) {
        this.f22755a.a("prompt_show", "number", Integer.valueOf(i), "model", str);
    }

    public void l() {
        this.f22755a.a("user_private_page", new Object[0]);
    }

    public void m() {
        this.f22755a.a("del_user_auth", new Object[0]);
    }

    public void n() {
        this.f22755a.a("del_user_authcon", new Object[0]);
    }

    public void j(String str, int i) {
        this.f22755a.a("alert_app_p0p", "model", str, "number", Integer.valueOf(i));
    }

    public void i(int i) {
        this.f22755a.a("set_found_modelpop", "type", Integer.valueOf(i));
    }
}
