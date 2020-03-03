package com.xiaomi.smarthome.notishortcut;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.xiaomi.smarthome.notishortcut.AccountManager;
import com.xiaomi.smarthome.notishortcut.ISmartNoti;
import com.xiaomi.smarthome.notishortcut.inward.NotiSettingManager;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartNoti extends ISmartNoti.Stub {
    private static final String PKG_NAME = "com.xiaomi.smarthome";
    private static final String SAVE_FAIL = "com.xiaomi.smarthome.notishortcut.notification.save_fail";
    private static final String SAVE_SUCCESS = "com.xiaomi.smarthome.notishortcut.notification.save_success";

    public String getDeviceSettingFromFile() throws RemoteException {
        return DeviceConfigManager.c(NotiSmartService.getAppContext());
    }

    public boolean getSettingSwitch() throws RemoteException {
        return DeviceConfigManager.b(NotiSmartService.getAppContext());
    }

    public String getNetConfig() throws RemoteException {
        Locale c = InnerCookieManager.c(NotiSmartService.getAppContext());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userId", AccountManager.a(NotiSmartService.getAppContext()));
            jSONObject.put("server", InnerCookieManager.a(NotiSmartService.getAppContext()));
            jSONObject.put("serverEnv", InnerCookieManager.b(NotiSmartService.getAppContext()));
            AccountManager.ServiceTokenInfo b = AccountManager.b(NotiSmartService.getAppContext());
            jSONObject.put("domain", b.d);
            jSONObject.put("serviceToken", b.f1558a);
            jSONObject.put("ssecurity", b.b);
            jSONObject.put("timeDiff", b.c);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("country", c.getCountry());
            jSONObject2.put("language", c.getLanguage());
            jSONObject.put("locale", jSONObject2);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void notifyLogin(String str) throws RemoteException {
        AccountManager.b(NotiSmartService.getAppContext(), str);
        if (!TextUtils.isEmpty(AccountManager.a(NotiSmartService.getAppContext())) && DeviceConfigManager.b(NotiSmartService.getAppContext())) {
            NotiSettingManager.a(NotiSmartService.getAppContext()).d = true;
            NotiSettingManager.a(NotiSmartService.getAppContext()).a(true);
            NotiSettingManager.a(NotiSmartService.getAppContext()).a();
        }
    }

    public void notifyLogout() throws RemoteException {
        AccountManager.c(NotiSmartService.getAppContext());
        NotiSettingManager.a(NotiSmartService.getAppContext()).f();
    }

    public void notifyPushMsg(String str) throws RemoteException {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (NotiSettingManager.a(NotiSmartService.getAppContext()).d) {
                    String optString = jSONObject.optString("did");
                    if (NotiSettingManager.a(NotiSmartService.getAppContext()).b(optString)) {
                        NotiSettingManager.a(NotiSmartService.getAppContext()).a(optString, jSONObject.optJSONArray("attrs"));
                        NotiSettingManager.a(NotiSmartService.getAppContext()).g();
                    }
                }
            } catch (JSONException unused) {
            }
        }
    }

    public void setLocale(Bundle bundle) throws RemoteException {
        Locale locale;
        if (bundle != null && (locale = (Locale) bundle.getSerializable("data")) != null) {
            InnerCookieManager.a(NotiSmartService.getAppContext(), locale);
            if (!TextUtils.isEmpty(AccountManager.a(NotiSmartService.getAppContext())) && NotiSettingManager.a(NotiSmartService.getAppContext()).d) {
                NotiSettingManager.a(NotiSmartService.getAppContext()).a();
            }
        }
    }

    public void setServer(String str) throws RemoteException {
        if (!TextUtils.isEmpty(str)) {
            InnerCookieManager.a(NotiSmartService.getAppContext(), str);
            AccountManager.c(NotiSmartService.getAppContext());
            NotiSettingManager.a(NotiSmartService.getAppContext()).f();
        }
    }

    public void setServerEnv(String str) throws RemoteException {
        if (!TextUtils.isEmpty(str)) {
            InnerCookieManager.b(NotiSmartService.getAppContext(), str);
        }
    }

    public void notifyOpenNoti(String str, String str2) throws RemoteException {
        if (NotiSettingManager.a(NotiSmartService.getAppContext()).a(str, str2)) {
            Intent intent = new Intent(SAVE_SUCCESS);
            intent.setPackage("com.xiaomi.smarthome");
            NotiSmartService.getAppContext().sendBroadcast(intent);
            return;
        }
        Intent intent2 = new Intent(SAVE_FAIL);
        intent2.setPackage("com.xiaomi.smarthome");
        NotiSmartService.getAppContext().sendBroadcast(intent2);
    }

    public void notifyCloseNoti() throws RemoteException {
        DeviceConfigManager.a(NotiSmartService.getAppContext());
        NotiSettingManager.a(NotiSmartService.getAppContext()).f();
        NotiSettingManager.a(NotiSmartService.getAppContext()).d = false;
    }
}
