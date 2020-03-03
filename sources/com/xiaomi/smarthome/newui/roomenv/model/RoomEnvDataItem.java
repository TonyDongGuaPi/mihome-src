package com.xiaomi.smarthome.newui.roomenv.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bluetooth.XmBluetoothRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.newui.card.CardItem;
import org.json.JSONException;
import org.json.JSONObject;

public class RoomEnvDataItem implements Parcelable {
    public static final Parcelable.Creator<RoomEnvDataItem> CREATOR = new Parcelable.Creator<RoomEnvDataItem>() {
        /* renamed from: a */
        public RoomEnvDataItem createFromParcel(Parcel parcel) {
            return new RoomEnvDataItem(parcel);
        }

        /* renamed from: a */
        public RoomEnvDataItem[] newArray(int i) {
            return new RoomEnvDataItem[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f20707a = "temp";
    public static final String b = "pm25";
    public static final String c = "hum";
    public static final String d = "door";
    public static final String e = "human_motion";
    public static final String f = "curtain_position";
    private String g;
    private long h;
    private String i;
    private String j;
    private String k;
    private String l;

    public int describeContents() {
        return 0;
    }

    public static RoomEnvDataItem a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RoomEnvDataItem roomEnvDataItem = new RoomEnvDataItem();
        roomEnvDataItem.e(jSONObject.optString("attr_type"));
        roomEnvDataItem.b(jSONObject.optString("desc"));
        roomEnvDataItem.f(jSONObject.optString("did"));
        roomEnvDataItem.c(jSONObject.optString(XmBluetoothRecord.TYPE_PROP));
        roomEnvDataItem.a(jSONObject.optLong("timestamp"));
        roomEnvDataItem.d(jSONObject.optString("value"));
        return roomEnvDataItem;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("attr_type", f());
        jSONObject.put("desc_name", b());
        jSONObject.put("did", g());
        jSONObject.put(XmBluetoothRecord.TYPE_PROP, d());
        jSONObject.put("timestamp", c());
        jSONObject.put("value", e());
        return jSONObject;
    }

    public static String a(String str) {
        String[] stringArray = SHApplication.getApplication().getResources().getStringArray(R.array.home_env_info_item_title);
        return TextUtils.equals(str, c) ? stringArray.length >= 2 ? stringArray[1] : "" : TextUtils.equals(str, "temp") ? stringArray.length >= 1 ? stringArray[0] : "" : (!TextUtils.equals(str, b) || stringArray.length < 3) ? "" : stringArray[2];
    }

    public String b() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public long c() {
        return this.h;
    }

    public void a(long j2) {
        this.h = j2;
    }

    public String d() {
        return this.i;
    }

    public void c(String str) {
        this.i = str;
    }

    public String e() {
        return this.j;
    }

    public void d(String str) {
        this.j = str;
    }

    public String f() {
        return this.k;
    }

    public void e(String str) {
        this.k = str;
    }

    public String g() {
        return this.l;
    }

    public void f(String str) {
        this.l = str;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.g);
        parcel.writeLong(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
    }

    public RoomEnvDataItem() {
    }

    protected RoomEnvDataItem(Parcel parcel) {
        this.g = parcel.readString();
        this.h = parcel.readLong();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
    }

    public String h() {
        StringBuffer stringBuffer = new StringBuffer();
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            return stringBuffer.toString();
        }
        String str = this.k;
        char c2 = 65535;
        switch (str.hashCode()) {
            case 103680:
                if (str.equals(c)) {
                    c2 = 1;
                    break;
                }
                break;
            case 3089326:
                if (str.equals(d)) {
                    c2 = 4;
                    break;
                }
                break;
            case 3442944:
                if (str.equals(b)) {
                    c2 = 2;
                    break;
                }
                break;
            case 3556308:
                if (str.equals("temp")) {
                    c2 = 0;
                    break;
                }
                break;
            case 443971638:
                if (str.equals(f)) {
                    c2 = 3;
                    break;
                }
                break;
            case 1650990856:
                if (str.equals(e)) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
            case 1:
            case 2:
                stringBuffer.append(b2);
                break;
            case 3:
                Device b3 = SmartHomeDeviceManager.a().b(g());
                if (b3 != null) {
                    stringBuffer.append(b3.name);
                    stringBuffer.append(" ");
                    stringBuffer.append(CardItem.a((Object) Long.valueOf(c())));
                    stringBuffer.append(" ");
                    stringBuffer.append(b());
                    break;
                }
                break;
            case 4:
            case 5:
                if (!b2.contains("%s")) {
                    stringBuffer.append(b2);
                    break;
                } else {
                    Device b4 = SmartHomeDeviceManager.a().b(g());
                    if (b4 != null) {
                        String b5 = b();
                        stringBuffer.append(String.format(b5, new Object[]{b4.name + " " + CardItem.a((Object) Long.valueOf(c())) + " "}));
                        break;
                    }
                }
                break;
        }
        return stringBuffer.toString();
    }
}
