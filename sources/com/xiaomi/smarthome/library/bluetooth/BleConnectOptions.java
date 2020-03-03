package com.xiaomi.smarthome.library.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class BleConnectOptions implements Parcelable {
    public static final Parcelable.Creator<BleConnectOptions> CREATOR = new Parcelable.Creator<BleConnectOptions>() {
        /* renamed from: a */
        public BleConnectOptions createFromParcel(Parcel parcel) {
            return new BleConnectOptions(parcel);
        }

        /* renamed from: a */
        public BleConnectOptions[] newArray(int i) {
            return new BleConnectOptions[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f18452a;
    private int b;
    private int c;
    private int d;
    private String e = "";

    public int describeContents() {
        return 0;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private static final int f18453a = 0;
        private static final int b = 1;
        private static final int c = 30000;
        private static final int d = 30000;
        /* access modifiers changed from: private */
        public int e = 0;
        /* access modifiers changed from: private */
        public int f = 1;
        /* access modifiers changed from: private */
        public int g = 30000;
        /* access modifiers changed from: private */
        public int h = 30000;

        public Builder a(int i) {
            this.e = i;
            return this;
        }

        public Builder b(int i) {
            this.f = i;
            return this;
        }

        public Builder c(int i) {
            this.g = i;
            return this;
        }

        public Builder d(int i) {
            this.h = i;
            return this;
        }

        public BleConnectOptions a() {
            return new BleConnectOptions(this);
        }
    }

    public BleConnectOptions(Builder builder) {
        this.f18452a = builder.e;
        this.b = builder.f;
        this.c = builder.g;
        this.d = builder.h;
    }

    protected BleConnectOptions(Parcel parcel) {
        this.f18452a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f18452a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
    }

    public int a() {
        return this.f18452a;
    }

    public void a(int i) {
        this.f18452a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public int c() {
        return this.c;
    }

    public void c(int i) {
        this.c = i;
    }

    public int d() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public String e() {
        return this.e;
    }

    public void a(String str) {
        if (str == null) {
            this.e = "";
        } else {
            this.e = str;
        }
    }

    public String toString() {
        return "BleConnectOptions{connectRetry=" + this.f18452a + ", serviceDiscoverRetry=" + this.b + ", connectTimeout=" + this.c + ", serviceDiscoverTimeout=" + this.d + Operators.BLOCK_END;
    }
}
