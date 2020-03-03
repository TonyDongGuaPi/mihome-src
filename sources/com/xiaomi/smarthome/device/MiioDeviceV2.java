package com.xiaomi.smarthome.device;

import android.content.Context;
import android.text.TextUtils;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.MyLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiioDeviceV2 extends Device {
    public static final String y = "MiioDeviceV2";
    protected boolean A;
    public boolean B = false;
    public String C;
    public String D;
    public boolean z = true;

    public static class DeviceCallback<T> {
        public void a(DeviceErrorCode deviceErrorCode) {
        }

        public void a(T t) {
        }
    }

    public enum DeviceErrorCode {
        ERROR_UNKNOW,
        ERROR_CODE_BUSY,
        ERROR_PARAM_JSON_ERROR
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
    }

    /* access modifiers changed from: protected */
    public JSONObject c() {
        return null;
    }

    public MiioDeviceV2() {
    }

    public MiioDeviceV2(String str, String str2) {
        this.did = str;
        this.token = str2;
    }

    /* access modifiers changed from: protected */
    public boolean f() {
        return this.A;
    }

    public boolean a(final DeviceCallback<Void> deviceCallback) {
        if (!isSupportCommonSwitch()) {
            if (deviceCallback != null) {
                deviceCallback.a((DeviceErrorCode) null);
            }
            return false;
        }
        MiioDeviceV2 miioDeviceV2 = isSubDevice() ? (MiioDeviceV2) SmartHomeDeviceManager.a().b(p()) : this;
        if (miioDeviceV2 == null) {
            if (deviceCallback != null) {
                deviceCallback.a((DeviceErrorCode) null);
            }
            return false;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("on");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", AppConstants.B);
            jSONObject.put("params", jSONArray);
            if (isSubDevice()) {
                jSONObject.put("sid", n());
            }
        } catch (JSONException unused) {
        }
        miioDeviceV2.a(jSONObject.toString(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (deviceCallback != null) {
                    deviceCallback.a(null);
                }
                MiioDeviceV2.this.notifyStateChanged();
            }

            public void onFailure(Error error) {
                if (error != null) {
                    MyLog.f("openDevice error: " + error.a() + ", " + error.b());
                } else {
                    MyLog.f("openDevice error: " + error);
                }
                if (deviceCallback != null) {
                    deviceCallback.a((DeviceErrorCode) null);
                }
            }
        });
        return true;
    }

    public boolean b(final DeviceCallback<Void> deviceCallback) {
        if (!isSupportCommonSwitch()) {
            return false;
        }
        MiioDeviceV2 miioDeviceV2 = isSubDevice() ? (MiioDeviceV2) SmartHomeDeviceManager.a().b(p()) : this;
        if (miioDeviceV2 == null) {
            if (deviceCallback != null) {
                deviceCallback.a((DeviceErrorCode) null);
            }
            return false;
        }
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("off");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", AppConstants.B);
            jSONObject.put("params", jSONArray);
            if (isSubDevice()) {
                jSONObject.put("sid", n());
            }
        } catch (JSONException unused) {
        }
        miioDeviceV2.a(jSONObject.toString(), (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (deviceCallback != null) {
                    deviceCallback.a(null);
                }
                MiioDeviceV2.this.notifyStateChanged();
            }

            public void onFailure(Error error) {
                if (error != null) {
                    MyLog.f("closeDevice error: " + error.a() + ", " + error.b());
                } else {
                    MyLog.f("closeDevice error: " + error);
                }
                if (deviceCallback != null) {
                    deviceCallback.a((DeviceErrorCode) null);
                }
            }
        });
        return true;
    }

    public void a(JSONObject jSONObject, AsyncCallback<JSONObject, Error> asyncCallback) {
        a(jSONObject.toString(), asyncCallback);
    }

    public void a(String str, AsyncCallback<JSONObject, Error> asyncCallback) {
        CoreApi.a().b(this.did, this.token, str, asyncCallback);
    }

    public void g() {
        if (this.propInfo != null) {
            try {
                this.propInfo.remove("turning");
                this.propInfo.put("turning", "true");
            } catch (JSONException unused) {
            }
        }
    }

    public void h() {
        if (this.propInfo != null) {
            try {
                this.propInfo.remove("turning");
                this.propInfo.put("turning", "false");
            } catch (JSONException unused) {
            }
        }
    }

    public void i() {
        if (this.propInfo != null) {
            this.propInfo.remove("turning");
        }
    }

    public boolean j() {
        if (this.propInfo == null) {
            return false;
        }
        try {
            return !TextUtils.isEmpty(this.propInfo.getString("turning"));
        } catch (JSONException unused) {
            return false;
        }
    }

    public boolean isOpen() {
        if (this.propInfo != null) {
            String optString = this.propInfo.optString("turning");
            if (!TextUtils.isEmpty(optString)) {
                return optString.equals("true");
            }
        }
        if (this.model.equalsIgnoreCase("zhimi.airpurifier.v1") || this.model.equalsIgnoreCase("zhimi.airpurifier.v2") || this.model.equalsIgnoreCase("zhimi.airpurifier.v3")) {
            if (!this.model.equalsIgnoreCase("zhimi.airpurifier.v3") || !isSupportCommonSwitch()) {
                if (this.propInfo != null) {
                    return !this.propInfo.optString("mode").equals(PrinterParmater.i);
                }
            } else if (this.propInfo == null || this.propInfo.isNull(CameraPropertyBase.l)) {
                return this.z;
            } else {
                return "on".equalsIgnoreCase(this.propInfo.optString(CameraPropertyBase.l));
            }
        } else if (this.model.equalsIgnoreCase("ge.light.mono1")) {
            if (this.propInfo != null) {
                return "on".equalsIgnoreCase(this.propInfo.optString(CameraPropertyBase.l));
            }
        } else if (this.model.equalsIgnoreCase("chuangmi.plug.v1") || this.model.equalsIgnoreCase("chuangmi.plug.v2")) {
            if (this.propInfo != null) {
                return this.propInfo.optBoolean("on");
            }
        } else if (this.model.equalsIgnoreCase("lumi.plug.v1") && this.propInfo != null) {
            return "on".equals(this.propInfo.optString("neutral_0"));
        }
        if (this.propInfo == null || this.propInfo.isNull(CameraPropertyBase.l)) {
            return this.z;
        }
        return "on".equalsIgnoreCase(this.propInfo.optString(CameraPropertyBase.l));
    }

    public static void a(MiioDeviceV2 miioDeviceV2, boolean z2) throws JSONException {
        if (miioDeviceV2.model.equalsIgnoreCase("zhimi.airpurifier.v1") || miioDeviceV2.model.equalsIgnoreCase("zhimi.airpurifier.v2") || miioDeviceV2.model.equalsIgnoreCase("zhimi.airpurifier.v3")) {
            if (!miioDeviceV2.model.equalsIgnoreCase("zhimi.airpurifier.v3") || !miioDeviceV2.isSupportCommonSwitch()) {
                if (miioDeviceV2.propInfo == null) {
                    return;
                }
                if (z2) {
                    miioDeviceV2.propInfo.put("mode", "auto");
                } else {
                    miioDeviceV2.propInfo.put("mode", PrinterParmater.i);
                }
            } else if (miioDeviceV2.propInfo == null || miioDeviceV2.propInfo.isNull(CameraPropertyBase.l)) {
                miioDeviceV2.z = z2;
            } else if (z2) {
                miioDeviceV2.propInfo.put(CameraPropertyBase.l, "on");
            } else {
                miioDeviceV2.propInfo.put(CameraPropertyBase.l, "off");
            }
        } else if (miioDeviceV2.model.equalsIgnoreCase("ge.light.mono1")) {
            if (miioDeviceV2.propInfo == null) {
                return;
            }
            if (z2) {
                miioDeviceV2.propInfo.put(CameraPropertyBase.l, "on");
            } else {
                miioDeviceV2.propInfo.put(CameraPropertyBase.l, "off");
            }
        } else if (miioDeviceV2.model.equalsIgnoreCase("chuangmi.plug.v1") || miioDeviceV2.model.equalsIgnoreCase("chuangmi.plug.v2")) {
            if (miioDeviceV2.propInfo == null) {
                return;
            }
            if (z2) {
                miioDeviceV2.propInfo.put("on", true);
            } else {
                miioDeviceV2.propInfo.put("on", false);
            }
        } else if (miioDeviceV2.model.equalsIgnoreCase("lumi.plug.v1")) {
            if (miioDeviceV2.propInfo != null && miioDeviceV2.propInfo != null) {
                if (z2) {
                    miioDeviceV2.propInfo.put("neutral_0", "on");
                } else {
                    miioDeviceV2.propInfo.put("neutral_0", "off");
                }
            }
        } else if (miioDeviceV2.propInfo == null || miioDeviceV2.propInfo.isNull(CameraPropertyBase.l)) {
            miioDeviceV2.z = z2;
        } else if (z2) {
            miioDeviceV2.propInfo.put(CameraPropertyBase.l, "on");
        } else {
            miioDeviceV2.propInfo.put(CameraPropertyBase.l, "off");
        }
    }

    @Deprecated
    public CharSequence getStatusDescription(Context context) {
        return super.getStatusDescription(context);
    }

    public void a(String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.config_router");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(DeviceTagInterface.e, str);
            jSONObject2.put("passwd", str2);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        a(jSONObject.toString(), (AsyncCallback<JSONObject, Error>) null);
    }

    public void k() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.wifi_assoc_state");
            new JSONObject();
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        a(jSONObject.toString(), (AsyncCallback<JSONObject, Error>) null);
    }

    public void l() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "miIO.stop_diag_mode");
            new JSONObject();
            jSONObject.put("params", "");
        } catch (JSONException unused) {
        }
        a(jSONObject.toString(), (AsyncCallback<JSONObject, Error>) null);
    }

    /* access modifiers changed from: protected */
    public String m() {
        return this.extra;
    }

    public String n() {
        return this.did;
    }

    /* renamed from: o */
    public String getName() {
        return this.name;
    }

    public String p() {
        return this.parentId;
    }

    public String q() {
        return this.parentModel;
    }
}
