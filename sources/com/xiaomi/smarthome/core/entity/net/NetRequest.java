package com.xiaomi.smarthome.core.entity.net;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class NetRequest implements Parcelable {
    public static final Parcelable.Creator<NetRequest> CREATOR = new Parcelable.Creator<NetRequest>() {
        /* renamed from: a */
        public NetRequest createFromParcel(Parcel parcel) {
            return new NetRequest(parcel);
        }

        /* renamed from: a */
        public NetRequest[] newArray(int i) {
            return new NetRequest[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f13979a = "GET";
    public static final String b = "POST";
    private String c;
    private String d;
    private String e;
    private List<KeyValuePair> f;
    private List<KeyValuePair> g;
    private List<KeyValuePair> h;

    public int describeContents() {
        return 0;
    }

    public NetRequest(Builder builder) {
        this.c = builder.f13980a;
        this.d = builder.b;
        this.e = builder.c;
        this.f = builder.d;
        this.g = builder.e;
        this.h = builder.f;
    }

    protected NetRequest(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.createTypedArrayList(KeyValuePair.CREATOR);
        this.g = parcel.createTypedArrayList(KeyValuePair.CREATOR);
        this.h = parcel.createTypedArrayList(KeyValuePair.CREATOR);
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public List<KeyValuePair> d() {
        return this.f;
    }

    public List<KeyValuePair> e() {
        return this.g;
    }

    public List<KeyValuePair> f() {
        return this.h;
    }

    public String toString() {
        return "prefix:" + this.e + "path:" + this.d + " method:" + this.c + " params:" + this.g;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeTypedList(this.f);
        parcel.writeTypedList(this.g);
        parcel.writeTypedList(this.h);
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f13980a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public List<KeyValuePair> d = new ArrayList(8);
        /* access modifiers changed from: private */
        public List<KeyValuePair> e = new ArrayList(8);
        /* access modifiers changed from: private */
        public List<KeyValuePair> f = new ArrayList(4);

        public Builder a(String str) {
            if (str != null) {
                this.f13980a = str;
                return this;
            }
            throw new IllegalArgumentException("method == null");
        }

        public Builder b(String str) {
            if (str != null) {
                this.b = str;
                return this;
            }
            throw new IllegalArgumentException("url == null");
        }

        public Builder c(String str) {
            if (str != null) {
                this.c = str;
                return this;
            }
            throw new IllegalArgumentException("prefix == null");
        }

        public Builder a(List<KeyValuePair> list) {
            if (list != null) {
                this.d = list;
                return this;
            }
            throw new IllegalArgumentException("headers == null");
        }

        public Builder b(List<KeyValuePair> list) {
            if (list != null) {
                this.e = list;
                return this;
            }
            throw new IllegalArgumentException("queries == null");
        }

        public Builder c(List<KeyValuePair> list) {
            if (list != null) {
                this.f = list;
                return this;
            }
            throw new IllegalArgumentException("filePaths == null");
        }

        public NetRequest a() {
            return new NetRequest(this);
        }
    }
}
