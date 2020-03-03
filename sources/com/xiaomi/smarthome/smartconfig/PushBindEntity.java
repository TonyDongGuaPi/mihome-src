package com.xiaomi.smarthome.smartconfig;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.List;
import org.json.JSONObject;

public class PushBindEntity implements Parcelable {
    public static final Parcelable.Creator<PushBindEntity> CREATOR = new Parcelable.Creator<PushBindEntity>() {
        /* renamed from: a */
        public PushBindEntity createFromParcel(Parcel parcel) {
            return new PushBindEntity(parcel);
        }

        /* renamed from: a */
        public PushBindEntity[] newArray(int i) {
            return new PushBindEntity[i];
        }
    };
    private static final byte g = 1;
    private static final byte h = 2;
    private static final byte i = 4;

    /* renamed from: a  reason: collision with root package name */
    public final PluginRecord f22312a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    private byte j;

    public int describeContents() {
        return 0;
    }

    private PushBindEntity(PluginRecord pluginRecord, JSONObject jSONObject) {
        this.f22312a = pluginRecord;
        this.b = jSONObject.optString("router_did");
        this.c = jSONObject.optString(DeviceTagInterface.e);
        this.d = jSONObject.optString("bssid");
        this.f = jSONObject.optString("router_bssid");
        this.e = jSONObject.optString("router_ssid");
    }

    protected PushBindEntity(Parcel parcel) {
        this.f22312a = (PluginRecord) parcel.readParcelable(PluginRecord.class.getClassLoader());
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.j = parcel.readByte();
    }

    public static PushBindEntity a(JSONObject jSONObject) {
        String optString = jSONObject.optString("model");
        if (optString == null) {
            optString = DeviceFactory.i(jSONObject.optString(DeviceTagInterface.e));
        }
        PluginRecord d2 = CoreApi.a().d(optString);
        if (d2 != null) {
            return new PushBindEntity(d2, jSONObject).a(true);
        }
        Log.e(DevicePushBindManager.TAG, "PushBindEntity.create null pluginrecord model:" + optString);
        return null;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeParcelable(this.f22312a, i2);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeByte(this.j);
    }

    public void a() {
        this.j = (byte) (this.j | 1);
    }

    public void b() {
        this.j = (byte) (this.j | 2);
    }

    public PushBindEntity a(boolean z) {
        if (z) {
            this.j = (byte) (this.j & -5);
        } else {
            this.j = (byte) (this.j | 4);
        }
        return this;
    }

    public boolean c() {
        return (this.j & 1) != 0;
    }

    public boolean d() {
        return (this.j & 2) != 0;
    }

    public boolean e() {
        return (this.j & 4) != 4;
    }

    public boolean f() {
        PluginDeviceInfo c2 = this.f22312a.c();
        if (c2.e() != 4) {
            return false;
        }
        List<Integer> i2 = c2.i();
        return i2 == null || i2.size() == 0 || i2.contains(2);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PushBindEntity)) {
            return false;
        }
        PushBindEntity pushBindEntity = (PushBindEntity) obj;
        if (!TextUtils.equals(this.c, pushBindEntity.c) || !TextUtils.equals(this.d, pushBindEntity.d) || !TextUtils.equals(this.e, pushBindEntity.e) || !TextUtils.equals(this.f, pushBindEntity.f)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "PushBindEntity{routerDid='" + this.b + Operators.SINGLE_QUOTE + ", ssid='" + this.c + Operators.SINGLE_QUOTE + ", bssid='" + this.d + Operators.SINGLE_QUOTE + ", model='" + this.f22312a.o() + Operators.SINGLE_QUOTE + ", routerSsid='" + this.e + Operators.SINGLE_QUOTE + ", routerBssid='" + this.f + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
