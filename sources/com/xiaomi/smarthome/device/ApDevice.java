package com.xiaomi.smarthome.device;

import android.content.Context;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import org.json.JSONException;
import org.json.JSONObject;

public class ApDevice extends Device {

    /* renamed from: a  reason: collision with root package name */
    public String f14745a;
    public String b;
    public String c;

    public ApDevice(String str, String str2, String str3, String str4) {
        this.model = str;
        this.did = str4;
        this.c = str2;
        this.b = str4;
        this.f14745a = str3;
        this.permitLevel = 30;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("bssid", str4);
            jSONObject.put(DeviceTagInterface.e, str3);
            this.extra = jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public String getName() {
        return DeviceFactory.h(this.model);
    }

    public CharSequence getStatusDescription(Context context) {
        if (this.isOnline) {
            return SHApplication.getAppContext().getString(R.string.list_device_online);
        }
        return SHApplication.getAppContext().getString(R.string.list_device_offline);
    }
}
