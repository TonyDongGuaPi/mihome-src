package com.xiaomi.smarthome.framework.statistic;

import android.content.Intent;
import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.miio.page.msgcentersetting.MsgCenterHelper;
import com.xiaomi.stat.MiStat;
import com.xiaomi.stat.MiStatParams;
import com.xiaomi.voiceassistant.mijava.AiServiceError;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatHelper {
    public static void a(Intent intent) {
        Set<String> categories = intent.getCategories();
        String action = intent.getAction();
        if (categories != null && !categories.isEmpty() && categories.contains("android.intent.category.LAUNCHER") && TextUtils.equals(action, "android.intent.action.MAIN")) {
            CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                public void onCoreReady() {
                    CoreApi.a().l();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("name", "lauch_click_app_icon");
                    } catch (JSONException unused) {
                    }
                    CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
                    MiStatInterface.a(StatType.GENERAL.getValue(), "lauch_click_app_icon");
                }
            });
        }
    }

    public static void b(Intent intent) {
        Device b;
        if (TextUtils.equals(intent.getStringExtra(ApiConst.i), "short_cut")) {
            String stringExtra = intent.getStringExtra("device_id");
            String stringExtra2 = intent.getStringExtra(ApiConst.f);
            String stringExtra3 = intent.getStringExtra("device_mac");
            if (TextUtils.isEmpty(stringExtra2) && (b = SmartHomeDeviceManager.a().b(stringExtra)) != null) {
                stringExtra2 = b.model;
            }
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("model", stringExtra2);
                jSONObject2.put("did", stringExtra);
                jSONObject2.put("mac", stringExtra3);
                jSONObject.put("name", "lauch_click_app_icon");
                jSONObject.put("extra", jSONObject2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
            HashMap hashMap = new HashMap();
            hashMap.put("model", stringExtra2);
            hashMap.put("did", stringExtra);
            hashMap.put("mac", stringExtra3);
            MiStatInterface.a(StatType.GENERAL.getValue(), "lauch_click_app_icon_shortcut", (Map<String, String>) hashMap);
        }
    }

    public static void a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "push_notified_msg");
            jSONObject.put("extra", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("msg", str);
        MiStatInterface.a(StatType.GENERAL.getValue(), "push_notified_msg", (Map<String, String>) hashMap);
    }

    public static void a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("model", str);
            jSONObject2.put("did", str2);
            jSONObject2.put("mac", str3);
            jSONObject.put("name", "locked_screen_open_plugin");
            jSONObject.put("extra", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", str);
        hashMap.put("did", str2);
        hashMap.put("mac", str3);
        MiStatInterface.a(StatType.GENERAL.getValue(), "locked_screen_open_plugin", (Map<String, String>) hashMap);
    }

    public static void a(int i, int i2) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("total", i);
            jSONObject2.put("index", i2);
            jSONObject.put("name", "bottom_tab_click");
            jSONObject.put("extra", jSONObject2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("total", "" + i);
        hashMap.put("index", "" + i2);
        MiStatInterface.a(StatType.GENERAL.getValue(), "bottom_tab_click", (Map<String, String>) hashMap);
        MiStatParams miStatParams = new MiStatParams();
        miStatParams.putString("click_key", "bottom_tab_click");
        miStatParams.putLong("value", (long) i2);
        MiStat.trackEvent("click", miStatParams);
    }

    public static void b(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_list_click_plugin");
            jSONObject.put("extra", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("msg", str);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "device_list_click_plugin", (Map<String, String>) hashMap);
    }

    public static void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "devicelistlongpress");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "devicelistlongpress");
    }

    public static void a(Device device) {
        if (device != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("name", "Rename");
                jSONObject.put("model", device.model);
                jSONObject.put("did", device.did);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
            HashMap hashMap = new HashMap();
            hashMap.put("model", device.model);
            hashMap.put("did", device.did);
            MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "Rename", (Map<String, String>) hashMap);
        }
    }

    public static void a(List<Device> list) {
        if (list != null && list.size() != 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device != null) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("name", "ShareDuringEditing");
                        jSONObject.put("extra", device.model);
                        jSONObject.put("did", device.did);
                        jSONArray.put(jSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONArray.toString(), (String) null, false);
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONArray.toString());
            MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "ShareDuringEditing", (Map<String, String>) hashMap);
        }
    }

    public static void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "MoveDevice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "MoveDevice");
    }

    public static void b(List<Device> list) {
        if (list != null && list.size() != 0) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("model", device.model);
                        jSONObject2.put("did", device.did);
                        jSONArray.put(jSONObject2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                jSONObject.put("data", jSONArray);
                jSONObject.put("name", "DeleteDuringEditing");
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONObject.toString());
            MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "DeleteDuringEditing", (Map<String, String>) hashMap);
        }
    }

    public static void c(List<Device> list) {
        if (list != null && list.size() != 0) {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                Device device = list.get(i);
                if (device != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("model", device.model);
                        jSONObject2.put("did", device.did);
                        jSONArray.put(jSONObject2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                jSONObject.put("name", "device_list_add_shortcut");
                jSONObject.put("extra", jSONArray);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
            HashMap hashMap = new HashMap();
            hashMap.put("data", jSONArray.toString());
            MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "device_list_add_shortcut", (Map<String, String>) hashMap);
        }
    }

    public static void c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_list_rearrange");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "device_list_rearrange");
    }

    public static void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "scene_auto_list_click_edit_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "scene_auto_list_click_edit_name");
    }

    public static void e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "scene_auto_list_click_delete");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "scene_auto_list_click_delete");
    }

    public static void f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "scene_auto_list_send_launch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "scene_auto_list_send_launch");
    }

    public static void g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "scene_auto_edit_click_exe_time_rl");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "scene_auto_edit_click_exe_time_rl");
    }

    public static void h() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "lite_scene_click_add_recommend_scene");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "lite_scene_click_add_recommend_scene");
    }

    public static void i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "lite_scene_click_add_user_define_scene");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "lite_scene_click_add_user_define_scene");
    }

    public static void j() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "lite_scene_click_time_delay");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.SCENE.getValue(), "lite_scene_click_time_delay");
    }

    public static void k() {
        d("add_device_add_menu");
    }

    public static void l() {
        d("add_device_add_device");
    }

    public static void m() {
        d("add_device_enter_manual");
    }

    public static void n() {
        d("add_device_choose_wifi");
    }

    public static void o() {
        d("add_device_download_plugin");
    }

    public static void p() {
        d("add_device_add_tag");
    }

    public static void q() {
        d("add_device_add_launcher");
    }

    public static void r() {
        d("add_device_combo_search_failed");
    }

    public static void s() {
        d("add_device_combo_search_success");
    }

    public static void t() {
        d("add_device_combo_wifi_send_success");
    }

    public static void u() {
        d("add_device_combo_wifi_send_failed");
    }

    public static void v() {
        d("add_device_combo_notify_error");
    }

    public static void w() {
        d("add_device_combo_notify_pwd_error");
    }

    public static void x() {
        d("add_device_combo_notify_bind_success");
    }

    public static void y() {
        d("add_device_connect_ap_failed");
    }

    public static void z() {
        d("add_device_connect_select_ap_failed");
    }

    public static void A() {
        d("add_device_find_device_failed");
    }

    public static void B() {
        d("add_device_send_pwd_failed");
    }

    public static void C() {
        d("add_device_qr_bind_failed");
    }

    public static void c(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "add_device_ap_success_page");
            jSONObject.put("extra", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.ADD_DEVICE, "PageStart", jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.ADD_DEVICE.getValue(), "add_device_ap_success_page");
    }

    public static void d(String str) {
        try {
            MiStatInterface.a(MiStatType.CLICK.getValue(), str, (Map<String, String>) new HashMap());
            CoreApi.a().a(StatType.ADD_DEVICE, str, "", (String) null, false);
        } catch (Throwable th) {
            BluetoothLog.a(th);
        }
    }

    public static void D() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_item_long_click");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_item_long_click");
    }

    public static void a(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_select_all");
            jSONObject.put("extra", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("selectAll", z ? "1" : "0");
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_select_all", (Map<String, String>) hashMap);
    }

    public static void E() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_click_delete_btn");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_click_delete_btn");
    }

    public static void b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_click_device_push");
            jSONObject.put("extra", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put(TSMAuthContants.PARAM_VALID, z ? "1" : "0");
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_click_device_push", (Map<String, String>) hashMap);
    }

    public static void F() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_click_wifi_pwd_changed");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_click_wifi_pwd_changed");
    }

    public static void a(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("name", "msg_center_click_common_msg");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject2.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_click_common_msg");
    }

    public static void G() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_accept_family_inv");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_accept_family_inv");
    }

    public static void H() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "msg_center_accept_share");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "msg_center_accept_share");
    }

    public static void I() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", MsgCenterHelper.f19886a);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.NOTIFICATION, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), MsgCenterHelper.f19886a);
    }

    public static void J() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "share_main");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "share_main");
    }

    public static void K() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_share_edit_mode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.NOTIFICATION.getValue(), "device_share_edit_mode");
    }

    public static void c(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_share_select_all");
            jSONObject.put("extra", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "device_share_select_all");
    }

    public static void L() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_share_batch_share");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "device_share_batch_share");
    }

    public static void M() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "receive_share_edit_mode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "receive_share_edit_mode");
    }

    public static void d(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "receive_share_select_all");
            jSONObject.put("extra", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "receive_share_select_all");
    }

    public static void N() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "receive_share_delete");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "receive_share_delete");
    }

    public static void e(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "share_detail_select_all");
            jSONObject.put("extra", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "share_detail_select_all");
    }

    public static void O() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "share_detail_edit_mode");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "share_detail_edit_mode");
    }

    public static void b(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "share_detail_delete");
            jSONObject.put("model", str);
            jSONObject.put("did", str2);
            jSONObject.put("mac", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("model", str);
        hashMap.put("did", str2);
        hashMap.put("mac", str3);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "share_detail_delete", (Map<String, String>) hashMap);
    }

    public static void P() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "share_detail_delete_batch");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_SHARE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_SHARE.getValue(), "share_detail_delete_batch");
    }

    public static void a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("img", str);
            jSONObject.put("url", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.EMPTY_DEVICE_ADV, "mijia_intro_default_show", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("img", str);
        hashMap.put("url", str2);
        MiStatInterface.a(StatType.EMPTY_DEVICE_ADV.getValue(), "mijia_intro_default_show", (Map<String, String>) hashMap);
    }

    public static void b(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("img", str);
            jSONObject.put("url", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.EMPTY_DEVICE_ADV, "mijia_intro_show", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("img", str);
        hashMap.put("url", str2);
        MiStatInterface.a(StatType.EMPTY_DEVICE_ADV.getValue(), "mijia_intro_show", (Map<String, String>) hashMap);
    }

    public static void c(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "mijia_intro_click");
            jSONObject.put("img", str);
            jSONObject.put("url", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.EMPTY_DEVICE_ADV, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("img", str);
        hashMap.put("url", str2);
        MiStatInterface.a(StatType.EMPTY_DEVICE_ADV.getValue(), "mijia_intro_click", (Map<String, String>) hashMap);
    }

    public static void d(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("img", str);
            jSONObject.put("url", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.EMPTY_DEVICE_ADV, "mijia_recommend_default_show", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("img", str);
        hashMap.put("url", str2);
        MiStatInterface.a(StatType.EMPTY_DEVICE_ADV.getValue(), "mijia_recommend_default_show", (Map<String, String>) hashMap);
    }

    public static void e(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("img", str);
            jSONObject.put("url", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.EMPTY_DEVICE_ADV, "mijia_recommend_show", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("img", str);
        hashMap.put("url", str2);
        MiStatInterface.a(StatType.EMPTY_DEVICE_ADV.getValue(), "mijia_recommend_show", (Map<String, String>) hashMap);
    }

    public static void f(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "mijia_recommend_click");
            jSONObject.put("img", str);
            jSONObject.put("url", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.EMPTY_DEVICE_ADV, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("img", str);
        hashMap.put("url", str2);
        MiStatInterface.a(StatType.EMPTY_DEVICE_ADV.getValue(), "mijia_recommend_click", (Map<String, String>) hashMap);
    }

    public static void f(boolean z) {
        if (z) {
            CoreApi.a().a(StatType.ACCOUNT, "account_logout_mi", (String) null, (String) null, false);
            MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_logout_mi");
            return;
        }
        CoreApi.a().a(StatType.ACCOUNT, "account_logout_wx", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_logout_wx");
    }

    public static void g(boolean z) {
        if (z) {
            CoreApi.a().a(StatType.ACCOUNT, "account_login_mi", (String) null, (String) null, false);
            MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_login_mi");
            return;
        }
        CoreApi.a().a(StatType.ACCOUNT, "account_login_wx", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_login_wx");
    }

    public static void Q() {
        CoreApi.a().a(StatType.ACCOUNT, "account_change_avstar", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_change_avstar");
    }

    public static void R() {
        CoreApi.a().a(StatType.ACCOUNT, "account_bind_wx", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_bind_wx");
    }

    public static void S() {
        CoreApi.a().a(StatType.ACCOUNT, "account_unbind_wx", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_unbind_wx");
    }

    public static void T() {
        CoreApi.a().a(StatType.ACCOUNT, "account_change_name", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_change_name");
    }

    public static void U() {
        CoreApi.a().a(StatType.ACCOUNT, "account_change_pwd", (String) null, (String) null, false);
        MiStatInterface.a(StatType.ACCOUNT.getValue(), "account_change_pwd");
    }

    public static void V() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_share_miliao");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_share_miliao");
    }

    public static void W() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_share_weibo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_share_weibo");
    }

    public static void X() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_share_wx");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_share_wx");
    }

    public static void Y() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "plugin_share_wx_pyq");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.PLUGIN, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.PLUGIN.getValue(), "plugin_share_wx_pyq");
    }

    public static void h(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "setting_set_auto_update");
            jSONObject.put("auto_update", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("autoUpdate", z ? "1" : "0");
        MiStatInterface.a(StatType.GENERAL.getValue(), "setting_set_auto_update", (Map<String, String>) hashMap);
    }

    public static void i(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "setting_set_grid_sound");
            jSONObject.put("sound", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("sound", z ? "1" : "0");
        MiStatInterface.a(StatType.GENERAL.getValue(), "setting_set_grid_sound", (Map<String, String>) hashMap);
    }

    public static void Z() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "DeviceTagDrawerFragment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, "PageStart", jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "DeviceTagDrawerFragment_open");
    }

    public static void aa() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "DeviceTagDrawerFragment");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, "PageEnd", jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "DeviceTagDrawerFragment_close");
    }

    public static void e(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "ChooseGroup");
            jSONObject.put("extra", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("label", str);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "ChooseGroup", (Map<String, String>) hashMap);
    }

    public static void f(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "ChooseRouter");
            jSONObject.put("extra", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("router", str);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "ChooseRouter", (Map<String, String>) hashMap);
    }

    public static void g(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "Room");
            jSONObject.put("extra", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("room", str);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "Room", (Map<String, String>) hashMap);
    }

    public static void h(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "AddGroup");
            jSONObject.put("extra", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("group", str);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "AddGroup", (Map<String, String>) hashMap);
    }

    public static void ab() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "Add");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "click_add_icon");
    }

    public static void ac() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "AddDevice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "AddDevice");
    }

    public static void ad() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "AddScene");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.DEVICE_LIST.getValue(), "AddScene");
    }

    public static void i(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.EVENT, "PageStart", jSONObject.toString(), (String) null, false);
        MiStatInterface.a(SHApplication.getAppContext(), str);
    }

    public static void j(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.EVENT, "PageEnd", jSONObject.toString(), (String) null, false);
        MiStatInterface.b(SHApplication.getAppContext(), str);
    }

    public static void a(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "AddScene");
            jSONObject.put("extra", i);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("type", "" + i);
        MiStatInterface.a(StatType.SCENE.getValue(), "AddScene", (Map<String, String>) hashMap);
    }

    public static void a(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "TapToExecute");
            jSONObject.put("type", i);
            jSONObject.put("extra", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("type", "" + i);
        hashMap.put("name", str);
        MiStatInterface.a(StatType.SCENE.getValue(), "TapToExecute", (Map<String, String>) hashMap);
    }

    public static void b(int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "DelScene");
            jSONObject.put("type", i);
            jSONObject.put("extra", str);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.SCENE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("type", "" + i);
        hashMap.put("name", str);
        MiStatInterface.a(StatType.SCENE.getValue(), "DelScene", (Map<String, String>) hashMap);
    }

    public static void ae() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdateAll");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdateAll");
    }

    public static void af() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdateFirmware");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdateFirmware");
    }

    public static void ag() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdateApp");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdateApp");
    }

    public static void ah() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdatePlugin");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdatePlugin");
    }

    public static void ai() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdateFirmwareFail");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdateFirmwareFail");
    }

    public static void aj() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdatePluginFail");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdatePluginFail");
    }

    public static void ak() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "UpdateAppFail");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.UPDATE, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.UPDATE.getValue(), "UpdateAppFail");
    }

    public static void a(int i, Device device) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_list_open_fail");
            jSONObject.put("source", i);
            jSONObject.put("model", device.model);
            jSONObject.put("did", device.did);
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.DEVICE_LIST, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("source", "" + i);
        hashMap.put("model", device.model);
        hashMap.put("did", device.did);
        MiStatInterface.a(StatType.GENERAL.getValue(), "device_list_open_fail", (Map<String, String>) hashMap);
    }

    public static void al() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_laboratory", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_laboratory");
    }

    public static void am() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_audio_control", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_audio_control");
    }

    public static void j(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("checked", z);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_audio_control_slip_btn", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("checked", z ? "1" : "0");
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_audio_control_slip_btn", (Map<String, String>) hashMap);
    }

    public static void an() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_audio_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_audio_btn");
    }

    public static void ao() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_record_help_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_record_help_btn");
    }

    public static void ap() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_record_close_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_record_close_btn");
    }

    public static void aq() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_click_record_record_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_click_record_record_btn");
    }

    public static void ar() {
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_iot_success", (String) null, (String) null, false);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_iot_success");
    }

    public static void a(AiServiceError aiServiceError) {
        JSONObject jSONObject = new JSONObject();
        if (aiServiceError != null) {
            try {
                jSONObject.put("code", aiServiceError.f);
                jSONObject.put("msg", aiServiceError.g);
                jSONObject.put("type", aiServiceError.e);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        CoreApi.a().a(StatType.MI_BRAIN, "mi_brain_iot_fail", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("code", "" + aiServiceError.f);
        hashMap.put("msg", "" + aiServiceError.g);
        hashMap.put("type", "" + aiServiceError.e);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "mi_brain_iot_fail", (Map<String, String>) hashMap);
    }

    public static void as() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("time", System.currentTimeMillis());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, "open_app_from_miui_lockscreen", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("time", "" + CalendarUtils.c(System.currentTimeMillis()));
        MiStatInterface.a(StatType.GENERAL.getValue(), "open_app_from_miui_lockscreen", (Map<String, String>) hashMap);
    }

    public static void at() {
        CoreApi.a().a(StatType.MICRO, "micro_click_audio_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "micro_click_audio_btn");
    }

    public static void au() {
        CoreApi.a().a(StatType.MICRO, "micro_click_record_help_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "micro_click_record_help_btn");
    }

    public static void av() {
        CoreApi.a().a(StatType.MICRO, "micro_click_record_history_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "micro_click_record_history_btn");
    }

    public static void aw() {
        CoreApi.a().a(StatType.MICRO, "micro_enter_disconnect_status", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "micro_enter_disconnect_status");
    }

    public static void ax() {
        CoreApi.a().a(StatType.MICRO, "micro_click_reconnect_btn", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "micro_click_reconnect_btn");
    }

    public static void ay() {
        CoreApi.a().a(StatType.MICRO, "micro_http_connect_err", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "micro_http_connect_err");
    }

    public static void az() {
        CoreApi.a().a(StatType.MICRO, "voice_setting_change_to_micro", (String) null, (String) null, false);
        MiStatInterface.a(StatUtil.g, "voice_setting_change_to_micro");
    }

    public static void aA() {
        CoreApi.a().a(StatType.MIUI_SPLASH, "miui_splash_view_monitor", (String) null, (String) null, false);
    }

    public static void aB() {
        CoreApi.a().a(StatType.MIUI_SPLASH, "miui_splash_click_monitor", (String) null, (String) null, false);
    }

    public static void aC() {
        CoreApi.a().a(StatType.MIUI_SPLASH, "miui_splash_skip_view_monitor", (String) null, (String) null, false);
    }

    public static void aD() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_show", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_show", (Map<String, String>) null);
    }

    public static void aE() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_click_benefit", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_click_benefit", (Map<String, String>) null);
    }

    public static void aF() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_click_experience", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_click_experience", (Map<String, String>) null);
    }

    public static void aG() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_click_close", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_click_close", (Map<String, String>) null);
    }

    public static void aH() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_click_close_trans", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_click_close_trans", (Map<String, String>) null);
    }

    public static void aI() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_click_close_back_key", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_click_close_back_key", (Map<String, String>) null);
    }

    public static void aJ() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_type_two_show", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_type_two_show", (Map<String, String>) null);
    }

    public static void aK() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_type_two_click_close", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_type_two_click_close", (Map<String, String>) null);
    }

    public static void aL() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_type_two_click_benefit", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_type_two_click_benefit", (Map<String, String>) null);
    }

    public static void aM() {
        CoreApi.a().a(StatType.YOUPIN, "yp_popup_type_two_click_close_back_key", (String) null, (String) null, false);
        MiStatInterface.a(StatType.YOUPIN.getValue(), "yp_popup_type_two_click_close_back_key", (Map<String, String>) null);
    }

    public static void aN() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_main_page_common_use_clicked");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.GENERAL.getValue(), "device_main_page_common_use_clicked", (Map<String, String>) new HashMap());
    }

    public static void aO() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_main_page_room_icon_clicked");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.GENERAL.getValue(), "device_main_page_room_icon_clicked", (Map<String, String>) new HashMap());
    }

    public static void aP() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_main_page_share_icon_clicked");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.GENERAL.getValue(), "device_main_page_share_icon_clicked", (Map<String, String>) new HashMap());
    }

    public static void aQ() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", "device_main_page_nearby_icon_clicked");
        } catch (JSONException unused) {
        }
        CoreApi.a().a(StatType.GENERAL, com.xiaomi.smarthome.core.entity.statistic.StatHelper.f13997a, jSONObject.toString(), (String) null, false);
        MiStatInterface.a(StatType.GENERAL.getValue(), "device_main_page_nearby_icon_clicked", (Map<String, String>) new HashMap());
    }

    public static void a(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content_id", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, "main_popup_show", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("content_id", "" + j);
        MiStatInterface.a(StatType.GENERAL.getValue(), "main_popup_show", (Map<String, String>) hashMap);
    }

    public static void b(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content_id", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, "main_popup_enter", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("content_id", "" + j);
        MiStatInterface.a(StatType.GENERAL.getValue(), "main_popup_enter", (Map<String, String>) hashMap);
    }

    public static void c(long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("content_id", j);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.GENERAL, "main_popup_close", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("content_id", "" + j);
        MiStatInterface.a(StatType.GENERAL.getValue(), "main_popup_close", (Map<String, String>) hashMap);
    }

    public static void k(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("where", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.MICRO, "from", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("where", str);
        MiStatInterface.a(StatType.MICRO.getValue(), "from", (Map<String, String>) hashMap);
    }

    public static void l(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("where", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CoreApi.a().a(StatType.MI_BRAIN, "from", jSONObject.toString(), (String) null, false);
        HashMap hashMap = new HashMap();
        hashMap.put("where", str);
        MiStatInterface.a(StatType.MI_BRAIN.getValue(), "from", (Map<String, String>) hashMap);
    }
}
