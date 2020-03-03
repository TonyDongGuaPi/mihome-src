package com.xiaomi.smarthome.notishortcut;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.notishortcut.inward.LogUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceConfigManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1559a = "noti_device_setting_lite";
    private static final String b = "DeviceConfigManager";
    private static final String c = "noti_device_setting";

    public static void a(Context context) {
        String a2 = AccountManager.a(context);
        SPHelper.a(context, f1559a + MD5.a(a2));
        SPHelper.a(context, c + MD5.a(a2));
    }

    public static void a(Context context, boolean z) {
        String a2 = AccountManager.a(context);
        if (!TextUtils.isEmpty(a2)) {
            SPHelper.a(context, f1559a + MD5.a(a2), "is_open", z + "");
        }
    }

    public static boolean b(Context context) {
        String a2 = AccountManager.a(context);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        return Boolean.parseBoolean(SPHelper.b(context, f1559a + MD5.a(a2), "is_open", "false"));
    }

    public static String c(Context context) {
        if (TextUtils.isEmpty(AccountManager.a(context))) {
            return "uid is null";
        }
        String b2 = SPHelper.b(context, f1559a + MD5.a(AccountManager.a(context)), "dids", "");
        LogUtil.a(b, "getDeviceSettingFromFile userId: " + AccountManager.a(context));
        LogUtil.a(b, "getDeviceSettingFromFile: " + b2);
        return b2;
    }

    public static String d(Context context) {
        if (TextUtils.isEmpty(AccountManager.a(context))) {
            LogUtil.a(b, "uid is null ");
            return "";
        }
        return SPHelper.b(context, c + MD5.a(AccountManager.a(context)), "devices", "");
    }

    public static boolean a(Context context, JSONArray jSONArray, JSONArray jSONArray2) {
        if (TextUtils.isEmpty(AccountManager.a(context))) {
            LogUtil.a(b, "AccountManager uid is null,DeviceNotiSetting fail");
            return false;
        } else if (jSONArray == null || jSONArray.length() == 0) {
            LogUtil.a(b, "DeviceNotiSetting fail");
            return false;
        } else if (jSONArray2 == null || jSONArray2.length() == 0) {
            LogUtil.a(b, "DeviceNotiSetting fail");
            return false;
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("list", jSONArray);
                jSONObject.put("config", jSONArray2);
            } catch (JSONException unused) {
            }
            LogUtil.a(b, "DeviceNotiSetting:  " + jSONObject.toString());
            boolean a2 = SPHelper.a(context, c + MD5.a(AccountManager.a(context)), "devices", jSONObject.toString());
            if (a2) {
                LogUtil.a(b, "DeviceNotiSetting success");
            } else {
                LogUtil.a(b, "DeviceNotiSetting fail");
            }
            return a2;
        }
    }
}
