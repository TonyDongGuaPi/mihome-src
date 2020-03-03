package com.mi.mistatistic.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class EventData implements Parcelable {
    public static final Parcelable.Creator<EventData> CREATOR = new Parcelable.Creator<EventData>() {
        /* renamed from: a */
        public EventData createFromParcel(Parcel parcel) {
            return new EventData(parcel);
        }

        /* renamed from: a */
        public EventData[] newArray(int i) {
            return new EventData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f7358a = "number";
    public static final String b = "string";
    public static final String c = "boolean";
    public static final String d = "array";
    public static final String e = "object";
    private String f;
    private String g;
    private String h;

    public int describeContents() {
        return 0;
    }

    public EventData(String str, String str2, String str3) {
        this.f = str;
        this.g = str2;
        this.h = str3;
    }

    protected EventData(Parcel parcel) {
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
    }

    public String a() {
        return this.f;
    }

    public void a(String str) {
        this.f = str;
    }

    public String b() {
        return this.g;
    }

    public void b(String str) {
        this.g = str;
    }

    public String c() {
        return this.h;
    }

    public void c(String str) {
        this.h = str;
    }

    public JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("key", this.f);
        jSONObject.put("value", this.g);
        jSONObject.put("type", this.h);
        return jSONObject;
    }
}
