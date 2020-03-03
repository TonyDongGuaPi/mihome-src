package com.xiaomi.smarthome.miio.device;

import android.content.Context;
import com.adobe.xmp.XMPConst;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import org.json.JSONException;
import org.json.JSONObject;

public class CurtainDevice extends MiioDeviceV2 {

    /* renamed from: a  reason: collision with root package name */
    private static int f13559a = 1;

    public boolean a(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    public boolean b(MiioDeviceV2.DeviceCallback<Void> deviceCallback) {
        return false;
    }

    /* access modifiers changed from: protected */
    public JSONObject c() {
        return null;
    }

    public CharSequence getStatusDescription(Context context) {
        return "";
    }

    public CurtainDevice(String str, String str2) {
        super(str, str2);
    }

    public CurtainDevice() {
    }

    public void a() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = f13559a;
            f13559a = i + 1;
            jSONObject.put("id", i);
            jSONObject.put("method", "stop");
            jSONObject.put("params", XMPConst.ai);
        } catch (JSONException unused) {
        }
        a(jSONObject, (AsyncCallback<JSONObject, Error>) null);
        notifyStateChanged();
    }

    public void b() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = f13559a;
            f13559a = i + 1;
            jSONObject.put("id", i);
            jSONObject.put("method", "open");
            jSONObject.put("params", XMPConst.ai);
        } catch (JSONException unused) {
        }
        a(jSONObject, (AsyncCallback<JSONObject, Error>) null);
        notifyStateChanged();
    }

    public void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = f13559a;
            f13559a = i + 1;
            jSONObject.put("id", i);
            jSONObject.put("method", "close");
            jSONObject.put("params", XMPConst.ai);
        } catch (JSONException unused) {
        }
        a(jSONObject, (AsyncCallback<JSONObject, Error>) null);
        notifyStateChanged();
    }
}
