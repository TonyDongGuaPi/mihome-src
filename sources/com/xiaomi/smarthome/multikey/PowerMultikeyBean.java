package com.xiaomi.smarthome.multikey;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.drew.lang.annotations.NotNull;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PowerMultikeyBean implements Parcelable {
    public static final Parcelable.Creator<PowerMultikeyBean> CREATOR = new Parcelable.Creator<PowerMultikeyBean>() {
        /* renamed from: a */
        public PowerMultikeyBean createFromParcel(Parcel parcel) {
            return new PowerMultikeyBean(parcel);
        }

        /* renamed from: a */
        public PowerMultikeyBean[] newArray(int i) {
            return new PowerMultikeyBean[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    String f20191a;
    String b;
    String c;
    String d;
    String e;
    String f;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.f;
    }

    public PowerMultikeyBean(String str) {
        this.f20191a = str;
    }

    protected PowerMultikeyBean(Parcel parcel) {
        this.f20191a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f20191a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }

    @NotNull
    public static ArrayList<PowerMultikeyBean> a(JSONArray jSONArray) {
        ArrayList<PowerMultikeyBean> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(a(jSONArray.optJSONObject(i)));
        }
        return arrayList;
    }

    @NonNull
    public static PowerMultikeyBean a(JSONObject jSONObject) {
        PowerMultikeyBean powerMultikeyBean = new PowerMultikeyBean(jSONObject.optString("bean"));
        powerMultikeyBean.f20191a = jSONObject.optString("id");
        powerMultikeyBean.d = jSONObject.optString("name");
        powerMultikeyBean.b = jSONObject.optString("room_id");
        powerMultikeyBean.c = jSONObject.optString("home_id");
        powerMultikeyBean.e = jSONObject.optString("icon");
        powerMultikeyBean.f = jSONObject.optString("ai_desc");
        return powerMultikeyBean;
    }

    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.d);
            try {
                jSONObject.put("room_id", Long.parseLong(this.b));
            } catch (NumberFormatException e2) {
                jSONObject.put("room_id", this.b);
                Log.e("PowerMultikeyBean", "toJson", e2);
            }
            try {
                jSONObject.put("home_id", Long.parseLong(this.c));
            } catch (NumberFormatException e3) {
                jSONObject.put("home_id", this.c);
                Log.e("PowerMultikeyBean", "toJson", e3);
            }
            jSONObject.put("icon", this.e);
            try {
                jSONObject.put("id", Long.parseLong(this.f20191a));
            } catch (NumberFormatException e4) {
                jSONObject.put("id", this.f20191a);
                Log.e("PowerMultikeyBean", "toJson", e4);
            }
            jSONObject.put("ai_desc", this.f);
        } catch (JSONException e5) {
            Log.e("PowerMultikeyBean", "toJson", e5);
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PowerMultikeyBean powerMultikeyBean = (PowerMultikeyBean) obj;
        if (!TextUtils.equals(this.f20191a, powerMultikeyBean.f20191a) || !TextUtils.equals(this.b, powerMultikeyBean.b) || !TextUtils.equals(this.c, powerMultikeyBean.c) || !TextUtils.equals(this.d, powerMultikeyBean.d) || !TextUtils.equals(this.e, powerMultikeyBean.e) || !TextUtils.equals(this.f, powerMultikeyBean.f)) {
            return false;
        }
        return true;
    }

    public void a(PowerMultikeyBean powerMultikeyBean) {
        this.d = powerMultikeyBean.d;
        this.c = powerMultikeyBean.c;
        this.b = powerMultikeyBean.b;
        this.e = powerMultikeyBean.e;
        this.f = powerMultikeyBean.f;
    }

    public static class PowerMultikeyList {

        /* renamed from: a  reason: collision with root package name */
        ArrayList<PowerMultikeyBean> f20192a;
        String b;

        public PowerMultikeyList(ArrayList<PowerMultikeyBean> arrayList, String str) {
            this.f20192a = arrayList;
            this.b = str;
        }
    }
}
