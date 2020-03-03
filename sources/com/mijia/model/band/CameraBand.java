package com.mijia.model.band;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class CameraBand implements Parcelable, Comparable<CameraBand> {
    public static final Parcelable.Creator<CameraBand> CREATOR = new Parcelable.Creator<CameraBand>() {
        /* renamed from: a */
        public CameraBand createFromParcel(Parcel parcel) {
            return new CameraBand(parcel);
        }

        /* renamed from: a */
        public CameraBand[] newArray(int i) {
            return new CameraBand[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f8049a;
    private String b;
    private int c;
    private int d;

    public int describeContents() {
        return 0;
    }

    public static CameraBand a(JSONObject jSONObject) {
        CameraBand cameraBand = new CameraBand();
        cameraBand.a(jSONObject.optString("name"));
        cameraBand.b(jSONObject.optString("mac"));
        cameraBand.a(jSONObject.optInt("pid"));
        cameraBand.b(jSONObject.optInt("rssi"));
        return cameraBand;
    }

    public String a() {
        return this.f8049a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public void a(String str) {
        this.f8049a = str;
    }

    private void b(String str) {
        this.b = str;
    }

    private void b(int i) {
        this.c = i;
    }

    public CameraBand() {
    }

    /* renamed from: a */
    public int compareTo(CameraBand cameraBand) {
        if (this.c > cameraBand.c) {
            return -1;
        }
        if (this.c != cameraBand.c && this.c < cameraBand.c) {
            return 1;
        }
        return 0;
    }

    public void a(int i) {
        this.d = i;
    }

    public int d() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8049a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
    }

    protected CameraBand(Parcel parcel) {
        this.f8049a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
    }
}
