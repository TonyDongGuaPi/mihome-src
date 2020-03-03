package com.xiaomi.youpin.core.net;

import android.os.Parcel;
import android.os.Parcelable;

public class KeyValuePair implements Parcelable {
    public static final Parcelable.Creator<KeyValuePair> CREATOR = new Parcelable.Creator<KeyValuePair>() {
        /* renamed from: a */
        public KeyValuePair createFromParcel(Parcel parcel) {
            return new KeyValuePair(parcel);
        }

        /* renamed from: a */
        public KeyValuePair[] newArray(int i) {
            return new KeyValuePair[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private final String f23339a;
    private final String b;
    private final String c;

    public int describeContents() {
        return 0;
    }

    public KeyValuePair(String str, String str2) {
        if (str != null) {
            this.f23339a = str;
            this.b = str2;
            this.c = null;
            return;
        }
        throw new IllegalArgumentException("Key may not be null");
    }

    public KeyValuePair(String str, String str2, String str3) {
        if (str != null) {
            this.f23339a = str;
            this.b = str2;
            this.c = str3;
            return;
        }
        throw new IllegalArgumentException("Key may not be null");
    }

    protected KeyValuePair(Parcel parcel) {
        this.f23339a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public String a() {
        return this.f23339a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f23339a);
        parcel.writeString(this.b);
        if (this.c != null) {
            parcel.writeString(this.c);
        }
    }

    public String toString() {
        return this.f23339a + ":" + this.b + ":" + this.c;
    }
}
