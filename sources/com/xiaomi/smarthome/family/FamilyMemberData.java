package com.xiaomi.smarthome.family;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyMemberData implements Parcelable {
    public static final Parcelable.Creator<FamilyMemberData> CREATOR = new Parcelable.Creator<FamilyMemberData>() {
        /* renamed from: a */
        public FamilyMemberData createFromParcel(Parcel parcel) {
            return new FamilyMemberData(parcel);
        }

        /* renamed from: a */
        public FamilyMemberData[] newArray(int i) {
            return new FamilyMemberData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f15708a = "family_member_data";
    public static final String b = "userId";
    public static final String c = "userName";
    public static final String d = "nickname";
    public static final String e = "icon";
    public static final String f = "relation_name";
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;

    public int describeContents() {
        return 0;
    }

    public static FamilyMemberData a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FamilyMemberData familyMemberData = new FamilyMemberData();
        familyMemberData.g = jSONObject.optString("userId", (String) null);
        familyMemberData.h = jSONObject.optString("userName", (String) null);
        familyMemberData.i = jSONObject.optString(d, (String) null);
        if (familyMemberData.i.equals("null")) {
            familyMemberData.i = null;
        }
        familyMemberData.j = jSONObject.optString("icon", (String) null);
        familyMemberData.k = jSONObject.optString(f, (String) null);
        if (familyMemberData.k.equals("null")) {
            familyMemberData.k = null;
        }
        if (familyMemberData.g == null) {
            return null;
        }
        return familyMemberData;
    }

    public FamilyMemberData() {
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("userId", this.g);
            jSONObject.put("userName", this.h);
            jSONObject.put(d, this.i);
            jSONObject.put("icon", this.j);
            jSONObject.put(f, this.k);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    protected FamilyMemberData(Parcel parcel) {
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
    }
}
