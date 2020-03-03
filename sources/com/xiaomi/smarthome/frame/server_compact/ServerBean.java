package com.xiaomi.smarthome.frame.server_compact;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alipay.mobile.security.bio.api.BioDetector;
import com.unionpay.tsmservice.data.Constant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServerBean implements Parcelable, Cloneable {
    public static final Parcelable.Creator<ServerBean> CREATOR = new Parcelable.Creator<ServerBean>() {
        /* renamed from: a */
        public ServerBean createFromParcel(Parcel parcel) {
            return new ServerBean(parcel);
        }

        /* renamed from: a */
        public ServerBean[] newArray(int i) {
            return new ServerBean[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public final String f1530a;
    public final String b;
    public transient String c = "";
    public transient boolean d = false;
    public transient boolean e = false;

    public int describeContents() {
        return 0;
    }

    public ServerBean(String str, String str2, String str3) {
        this.f1530a = str;
        this.b = str2;
        this.c = str3;
    }

    protected ServerBean(Parcel parcel) {
        this.f1530a = parcel.readString();
        this.b = parcel.readString();
    }

    @NonNull
    public static List<ServerBean> a(JSONObject jSONObject) throws JSONException {
        JSONArray jSONArray = jSONObject.getJSONObject("data").getJSONArray("list");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(b(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    public static ServerBean b(JSONObject jSONObject) throws JSONException {
        return new ServerBean(jSONObject.getString(BioDetector.EXT_KEY_MACHINE_CODE), jSONObject.getString(Constant.KEY_COUNTRY_CODE), jSONObject.optString("name"));
    }

    public static ServerBean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return new ServerBean(str2, str, "");
        }
        throw new IllegalArgumentException("countryCode or machineCode is empty!");
    }

    public String a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BioDetector.EXT_KEY_MACHINE_CODE, this.f1530a);
            jSONObject.put(Constant.KEY_COUNTRY_CODE, this.b);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ServerBean serverBean = (ServerBean) obj;
        if (!this.f1530a.equals(serverBean.f1530a)) {
            return false;
        }
        return this.b.equals(serverBean.b);
    }

    public int hashCode() {
        return (this.f1530a.hashCode() * 31) + this.b.hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1530a);
        parcel.writeString(this.b);
    }

    public String toString() {
        return a();
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return new ServerBean(this.f1530a, this.b, "");
        }
    }

    public String b() {
        return "ServerBean: " + this.b + " : " + this.f1530a + " ";
    }
}
