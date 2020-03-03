package com.xiaomi.smarthome.devicelistswitch.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Operation implements Parcelable {
    public static final Parcelable.Creator<Operation> CREATOR = new Parcelable.Creator<Operation>() {
        /* renamed from: a */
        public Operation createFromParcel(Parcel parcel) {
            return new Operation(parcel);
        }

        /* renamed from: a */
        public Operation[] newArray(int i) {
            return new Operation[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f15535a = "on";
    public static final String b = "off";
    private String c;
    private String d;
    private String e;
    private String[] f;
    private String g;
    private Map<String, String> h;
    private String i;

    public int describeContents() {
        return 0;
    }

    public static Operation a(JSONObject jSONObject) {
        Operation operation = new Operation();
        if (jSONObject != null) {
            if (!jSONObject.isNull("prop_name")) {
                operation.f(jSONObject.optString("prop_name"));
            }
            if (!jSONObject.isNull("prop_value")) {
                operation.b(jSONObject.optString("prop_value"));
            }
            if (!jSONObject.isNull("rpc_method")) {
                operation.c(jSONObject.optString("rpc_method"));
            }
            if (!jSONObject.isNull("rpc_params")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("rpc_params");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    String[] strArr = new String[optJSONArray.length()];
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        strArr[i2] = optJSONArray.optString(i2);
                    }
                    operation.a(strArr);
                }
                operation.f(jSONObject.optString("prop_name"));
            }
            if (!jSONObject.isNull("next_value")) {
                operation.d(jSONObject.optString("next_value"));
            }
            if (!jSONObject.isNull("desc")) {
                JSONObject optJSONObject = jSONObject.optJSONObject("desc");
                HashMap hashMap = new HashMap();
                Iterator<String> keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    hashMap.put(next, optJSONObject.optString(next));
                }
                operation.a((Map<String, String>) hashMap);
            }
            if (!jSONObject.isNull("state")) {
                operation.e(jSONObject.optString("state"));
            }
        }
        return operation;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("prop_name", b());
            jSONObject.put("prop_value", c());
            jSONObject.put("rpc_method", d());
            String[] e2 = e();
            if (e2 != null && e2.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (String put : e2) {
                    jSONArray.put(put);
                }
                jSONObject.put("rpc_params", jSONArray);
            }
            jSONObject.put("next_value", f());
            Map<String, String> map = this.h;
            if (map != null && map.size() > 0) {
                JSONObject jSONObject2 = new JSONObject();
                for (String next : map.keySet()) {
                    jSONObject2.put(next, map.get(next));
                }
                jSONObject.put("desc", jSONObject2);
            }
            jSONObject.put("state", g());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return jSONObject;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String[] e() {
        return this.f;
    }

    public String f() {
        return this.g;
    }

    public String a(String str) {
        if (this.h == null) {
            return null;
        }
        return this.h.get(str);
    }

    public String g() {
        return this.i;
    }

    public void b(String str) {
        this.d = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public void a(String[] strArr) {
        this.f = strArr;
    }

    public void d(String str) {
        this.g = str;
    }

    public void a(Map<String, String> map) {
        this.h = map;
    }

    public void e(String str) {
        this.i = str;
    }

    public void f(String str) {
        this.c = str;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeStringArray(this.f);
        parcel.writeString(this.g);
        if (this.h == null || this.h.isEmpty()) {
            parcel.writeInt(0);
        } else {
            Map<String, String> map = this.h;
            parcel.writeInt(map.size() * 2);
            for (String next : map.keySet()) {
                parcel.writeString(next);
                parcel.writeString(map.get(next));
            }
        }
        parcel.writeString(this.i);
    }

    public Operation() {
    }

    protected Operation(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.createStringArray();
        this.g = parcel.readString();
        int readInt = parcel.readInt();
        if (readInt > 0) {
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < readInt; i2++) {
                hashMap.put(parcel.readString(), parcel.readString());
            }
            this.h = hashMap;
        }
        this.i = parcel.readString();
    }
}
