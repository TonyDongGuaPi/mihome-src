package com.xiaomi.smarthome.core.entity.net;

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
    private final String f13978a;
    private final String b;

    public int describeContents() {
        return 0;
    }

    public KeyValuePair(String str, String str2) {
        if (str != null) {
            this.f13978a = str;
            this.b = str2;
            return;
        }
        throw new IllegalArgumentException("Key may not be null");
    }

    protected KeyValuePair(Parcel parcel) {
        this.f13978a = parcel.readString();
        this.b = parcel.readString();
    }

    public String a() {
        return this.f13978a;
    }

    public String b() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13978a);
        parcel.writeString(this.b);
    }

    public String toString() {
        return this.f13978a + ":" + this.b;
    }
}
