package com.xiaomi.smarthome.auth.bindaccount.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountBindManager;
import com.xiaomi.smarthome.device.Device;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ThirdAccount implements Parcelable {
    public static final Parcelable.Creator<ThirdAccount> CREATOR = new Parcelable.Creator<ThirdAccount>() {
        /* renamed from: a */
        public ThirdAccount createFromParcel(Parcel parcel) {
            return new ThirdAccount(parcel);
        }

        /* renamed from: a */
        public ThirdAccount[] newArray(int i) {
            return new ThirdAccount[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final int f13920a = -1;
    public static final int b = 0;
    public static final int c = 1;
    private String d;
    private String e;
    private String f;
    private String g;
    private List<Device> h = new ArrayList();
    private int i = -1;

    public int describeContents() {
        return 0;
    }

    public void a(String str) {
        this.d = str;
    }

    public void b(String str) {
        this.e = str;
    }

    public void c(String str) {
        this.f = str;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public int d() {
        return this.i;
    }

    public String e() {
        return this.g;
    }

    public List<Device> f() {
        return this.h;
    }

    public void d(String str) {
        this.g = str;
    }

    public void a(List<Device> list) {
        this.h = list;
    }

    public static ThirdAccount a(JSONObject jSONObject) {
        ThirdAccount thirdAccount = new ThirdAccount();
        if (jSONObject == null) {
            return thirdAccount;
        }
        try {
            if (!jSONObject.isNull(FirebaseAnalytics.Param.GROUP_ID)) {
                thirdAccount.b(jSONObject.optString(FirebaseAnalytics.Param.GROUP_ID));
            }
            if (!jSONObject.isNull("name")) {
                thirdAccount.a(jSONObject.optString("name"));
            }
            if (!jSONObject.isNull("icon_url")) {
                thirdAccount.c(jSONObject.optString("icon_url"));
            }
            if (!jSONObject.isNull("intro")) {
                thirdAccount.d(jSONObject.optString("intro"));
            }
            if (!jSONObject.isNull("bind_status")) {
                thirdAccount.a(jSONObject.optInt("bind_status", -1));
            }
            if (!jSONObject.isNull("dev_list")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("dev_list");
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    Device device = new Device();
                    device.did = optJSONObject.optString("did");
                    device.name = optJSONObject.optString("name");
                    device.model = optJSONObject.optString("model");
                    if (device.propInfo == null) {
                        device.propInfo = new JSONObject();
                    }
                    device.propInfo.put(ThirdAccountBindManager.f13881a, optJSONObject.opt("icon_url"));
                    arrayList.add(device);
                }
                thirdAccount.a((List<Device>) arrayList);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return thirdAccount;
    }

    public String g() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FirebaseAnalytics.Param.GROUP_ID, b());
            jSONObject.put("name", a());
            jSONObject.put("icon_url", c());
            jSONObject.put("intro", e());
            jSONObject.put("bind_status", d());
            List<Device> list = this.h;
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    Device device = list.get(i2);
                    if (device != null) {
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("did", device.did);
                        jSONObject2.put("name", device.name);
                        jSONObject2.put("model", device.model);
                        if (device.propInfo != null) {
                            jSONObject2.put("icon_url", device.propInfo.opt(ThirdAccountBindManager.f13881a));
                        }
                        jSONArray.put(jSONObject2);
                    }
                }
                jSONObject.put("dev_list", jSONArray);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    private ThirdAccount() {
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeInt(this.i);
    }

    protected ThirdAccount(Parcel parcel) {
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.i = parcel.readInt();
    }
}
