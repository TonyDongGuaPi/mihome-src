package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class MiioDevice extends Device {
    public static final Parcelable.Creator<MiioDevice> CREATOR = new Parcelable.Creator<MiioDevice>() {
        /* renamed from: a */
        public MiioDevice createFromParcel(Parcel parcel) {
            return new MiioDevice(parcel);
        }

        /* renamed from: a */
        public MiioDevice[] newArray(int i) {
            return new MiioDevice[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private boolean f13975a = false;
    private String b;
    private String c;

    protected MiioDevice(Parcel parcel) {
        super(parcel);
        boolean z = false;
        this.f13975a = parcel.readInt() == 1 ? true : z;
        this.c = parcel.readString();
        this.b = parcel.readString();
    }

    public MiioDevice() {
    }

    public MiioDevice(String str, String str2) {
        super(str, str2);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f13975a ? 1 : 0);
        parcel.writeString(this.c);
        parcel.writeString(this.b);
    }

    public void a(JSONObject jSONObject) {
        super.a(jSONObject);
        if (!TextUtils.isEmpty(G())) {
            try {
                JSONObject jSONObject2 = new JSONObject(G());
                this.c = jSONObject2.optString("fw_version");
                this.b = jSONObject2.optString("mcu_version");
            } catch (JSONException unused) {
            }
        }
    }

    public void i() {
        super.i();
        if (!TextUtils.isEmpty(G())) {
            try {
                JSONObject jSONObject = new JSONObject(G());
                this.c = jSONObject.optString("fw_version");
                this.b = jSONObject.optString("mcu_version");
            } catch (JSONException unused) {
            }
        }
    }

    public String j() {
        JSONObject jSONObject;
        String G = G();
        try {
            if (TextUtils.isEmpty(G)) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = new JSONObject(G);
            }
            jSONObject.put("is_factory", this.f13975a);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return G;
        }
    }

    public synchronized boolean a() {
        return this.f13975a;
    }

    public synchronized void g(boolean z) {
        this.f13975a = z;
    }

    public synchronized String b() {
        return this.c;
    }

    public synchronized String c() {
        return this.b;
    }
}
