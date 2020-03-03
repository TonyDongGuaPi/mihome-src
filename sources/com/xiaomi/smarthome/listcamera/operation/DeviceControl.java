package com.xiaomi.smarthome.listcamera.operation;

import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.listcamera.adapter.ControlViewHolder;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

public abstract class DeviceControl {
    protected int b;
    protected String c;
    protected OpRpc d;

    public enum OP_TYPE {
        OP_TOGGLE,
        OP_MULTI_CHOICE,
        OP_MULTI_BUTTON,
        OP_SLIDE,
        OP_CLICK
    }

    public abstract void a(ControlViewHolder controlViewHolder, MiioDeviceV2 miioDeviceV2, boolean z);

    public boolean a(Object obj, Object obj2) {
        return false;
    }

    public String a() {
        return this.c;
    }

    public static String a(Map<String, String> map) {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = SHApplication.getAppContext().getResources().getConfiguration().locale;
        }
        if (I == null) {
            return map.get("en");
        }
        if (I.getLanguage().equalsIgnoreCase(Locale.SIMPLIFIED_CHINESE.getLanguage())) {
            return map.get("zh_CN");
        }
        if (I.getLanguage().equalsIgnoreCase(Locale.TAIWAN.getLanguage())) {
            return map.get("zh_TW");
        }
        if (I.getLanguage().equalsIgnoreCase(Locale.KOREA.getLanguage()) || I.getLanguage().equalsIgnoreCase(Locale.KOREAN.getLanguage())) {
            return map.get("korea");
        }
        if (I.getLanguage().equalsIgnoreCase(Locale.US.getLanguage()) || I.getLanguage().equalsIgnoreCase(Locale.UK.getLanguage())) {
            return map.get("en");
        }
        if (I.getLanguage().equalsIgnoreCase(new Locale("bo", "CN").getLanguage())) {
            return map.get("tibet");
        }
        return map.get("en");
    }

    public static DeviceControl a(JSONObject jSONObject) {
        String optString = jSONObject.optString("op_type");
        if (optString.equals("op_toggle")) {
            return new ToggleDeviceControl(jSONObject);
        }
        if (optString.equals("op_multiple_choice")) {
            return new MultiChoiceDeviceControl(jSONObject);
        }
        if (optString.equals("op_click")) {
            return new ClickDeviceControl(jSONObject);
        }
        if (optString.equals("op_slide")) {
            return new SlideDeviceControl(jSONObject);
        }
        if (optString.equals("op_multi_button")) {
            return new MultiButtonDeviceControl(jSONObject);
        }
        return null;
    }
}
