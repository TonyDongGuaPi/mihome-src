package com.xiaomi.smarthome.miio.page.msgcentersetting;

import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.miio.page.msgcentersetting.model.MsgCenterSettingData;
import org.json.JSONException;
import org.json.JSONObject;

public class MsgCenterHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19886a = "msg_center_setting";
    public static final String b = "msg_center_content";

    public static JSONObject a() {
        String string = SHApplication.getApplication().getSharedPreferences(f19886a, 0).getString(b, "");
        if (!TextUtils.isEmpty(string)) {
            try {
                return new JSONObject(string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            return new JSONObject(MsgCenterSettingData.DEFAULT_DATA);
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void a(String str) {
        SHApplication.getApplication().getSharedPreferences(f19886a, 0).edit().putString(b, str).commit();
    }
}
