package com.xiaomi.youpin.network.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

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
    private final String f23654a;
    private final String b;

    public int describeContents() {
        return 0;
    }

    public KeyValuePair(String str, String str2) {
        if (str != null) {
            this.f23654a = str;
            this.b = str2;
            return;
        }
        throw new IllegalArgumentException("Key may not be null");
    }

    protected KeyValuePair(Parcel parcel) {
        this.f23654a = parcel.readString();
        this.b = parcel.readString();
    }

    public String a() {
        return this.f23654a;
    }

    public String b() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f23654a);
        parcel.writeString(this.b);
    }

    public String toString() {
        return "KeyValuePair{key='" + this.f23654a + Operators.SINGLE_QUOTE + ", value='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
