package com.xiaomi.smarthome.mibrain.roomsetting.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import org.json.JSONException;
import org.json.JSONObject;

public class ControllableDevice implements Parcelable {
    public static final Parcelable.Creator<ControllableDevice> CREATOR = new Parcelable.Creator<ControllableDevice>() {
        /* renamed from: a */
        public ControllableDevice createFromParcel(Parcel parcel) {
            return new ControllableDevice(parcel);
        }

        /* renamed from: a */
        public ControllableDevice[] newArray(int i) {
            return new ControllableDevice[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f10701a;
    private String b;
    private String c;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.f10701a;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        this.f10701a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public ControllableDevice() {
    }

    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("did", this.f10701a);
        jSONObject.put("desc", this.b);
        jSONObject.put("name", this.c);
        return jSONObject;
    }

    public static ControllableDevice a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        ControllableDevice controllableDevice = new ControllableDevice();
        controllableDevice.a(jSONObject.optString("did"));
        controllableDevice.b(jSONObject.optString("desc"));
        controllableDevice.c(jSONObject.optString("name"));
        return controllableDevice;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ControllableDevice controllableDevice = (ControllableDevice) obj;
        return TextUtils.equals(this.f10701a, controllableDevice.f10701a) && TextUtils.equals(this.b, controllableDevice.b);
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.f10701a != null ? this.f10701a.hashCode() : 0) * 31;
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode + i;
    }

    public static String a(ControllableDevice controllableDevice) {
        if (controllableDevice == null) {
            return "";
        }
        if (!TextUtils.isEmpty(controllableDevice.d())) {
            return controllableDevice.d();
        }
        Device b2 = SmartHomeDeviceManager.a().b(controllableDevice.f10701a);
        if (b2 == null) {
            return "";
        }
        return (String) b2.getName();
    }

    public String d() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10701a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }

    protected ControllableDevice(Parcel parcel) {
        this.f10701a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }
}
