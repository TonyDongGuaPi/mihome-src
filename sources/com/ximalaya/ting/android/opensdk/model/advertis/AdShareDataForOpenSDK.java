package com.ximalaya.ting.android.opensdk.model.advertis;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class AdShareDataForOpenSDK implements Parcelable {
    public static final Parcelable.Creator<AdShareDataForOpenSDK> CREATOR = new Parcelable.Creator<AdShareDataForOpenSDK>() {
        /* renamed from: a */
        public AdShareDataForOpenSDK createFromParcel(Parcel parcel) {
            return new AdShareDataForOpenSDK(parcel);
        }

        /* renamed from: a */
        public AdShareDataForOpenSDK[] newArray(int i) {
            return new AdShareDataForOpenSDK[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f2008a;
    private String b;
    private String c;
    private String d;
    private boolean e;

    public int describeContents() {
        return 0;
    }

    public AdShareDataForOpenSDK() {
    }

    protected AdShareDataForOpenSDK(Parcel parcel) {
        this.f2008a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readByte() != 0;
    }

    public AdShareDataForOpenSDK(AdShareDataForOpenSDK adShareDataForOpenSDK) {
        this.f2008a = adShareDataForOpenSDK.f2008a;
        this.b = adShareDataForOpenSDK.b;
        this.c = adShareDataForOpenSDK.c;
        this.d = adShareDataForOpenSDK.d;
        this.e = adShareDataForOpenSDK.e;
    }

    public AdShareDataForOpenSDK(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f2008a = jSONObject.optString("linkUrl");
            this.b = jSONObject.optString("linkTitle");
            this.c = jSONObject.optString("linkCoverPath");
            this.d = jSONObject.optString("linkContent");
            this.e = jSONObject.optBoolean("isExternalUrl");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public String a() {
        return this.f2008a;
    }

    public void a(String str) {
        this.f2008a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String d() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public boolean e() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2008a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeByte(this.e ? (byte) 1 : 0);
    }

    public void a(Parcel parcel) {
        this.f2008a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readByte() != 0;
    }
}
