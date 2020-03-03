package com.xiaomi.smarthome.family;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FamilyItemData implements Parcelable {
    public static final Parcelable.Creator<FamilyItemData> CREATOR = new Parcelable.Creator<FamilyItemData>() {
        /* renamed from: a */
        public FamilyItemData createFromParcel(Parcel parcel) {
            return new FamilyItemData(parcel);
        }

        /* renamed from: a */
        public FamilyItemData[] newArray(int i) {
            return new FamilyItemData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f15689a = "family_data";
    public static final String b = "family_id";
    public static final String c = "name";
    public static final String d = "family_owner";
    public static final String e = "family_desc";
    public String f;
    public String g;
    public String h;
    public String i;

    public int describeContents() {
        return 0;
    }

    public static FamilyItemData a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        FamilyItemData familyItemData = new FamilyItemData();
        familyItemData.f = jSONObject.optString(b, (String) null);
        familyItemData.g = jSONObject.optString("name", (String) null);
        familyItemData.h = jSONObject.optString(d, (String) null);
        familyItemData.i = jSONObject.optString(e);
        if (familyItemData.f == null || familyItemData.f.isEmpty()) {
            return null;
        }
        return familyItemData;
    }

    public static List<FamilyItemData> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                FamilyItemData a2 = a(jSONArray.optJSONObject(i2));
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        return arrayList;
    }

    public FamilyItemData() {
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(b, this.f);
            jSONObject.put("name", this.g);
            jSONObject.put(d, this.h);
            jSONObject.put(e, this.i);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    protected FamilyItemData(Parcel parcel) {
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
    }
}
