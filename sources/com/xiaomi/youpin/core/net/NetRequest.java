package com.xiaomi.youpin.core.net;

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
    public static final String f23342a = "GET";
    public static final String b = "POST";
    private String c;
    private String d;
    private List<KeyValuePair> e;
    private List<KeyValuePair> f;

    public int describeContents() {
        return 0;
    }

    public NetRequest(Builder builder) {
        this.c = builder.f23343a;
        this.d = builder.b;
        this.e = builder.c;
        this.f = builder.d;
    }

    protected NetRequest(Parcel parcel) {
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.createTypedArrayList(KeyValuePair.CREATOR);
        this.f = parcel.createTypedArrayList(KeyValuePair.CREATOR);
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public List<KeyValuePair> c() {
        return this.e;
    }

    public List<KeyValuePair> d() {
        return this.f;
    }

    public String toString() {
        return "path:" + this.d + " method:" + this.c + " params:" + this.f;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeTypedList(this.e);
        parcel.writeTypedList(this.f);
    }

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public String f23343a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public List<KeyValuePair> c = new ArrayList(8);
        /* access modifiers changed from: private */
        public List<KeyValuePair> d = new ArrayList(8);

        public Builder a(String str) {
            if (str != null) {
                this.f23343a = str;
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

        public Builder a(List<KeyValuePair> list) {
            if (list != null) {
                this.c = list;
                return this;
            }
            throw new IllegalArgumentException("headers == null");
        }

        public Builder b(List<KeyValuePair> list) {
            if (list != null) {
                this.d = list;
                return this;
            }
            throw new IllegalArgumentException("queries == null");
        }

        public NetRequest a() {
            return new NetRequest(this);
        }
    }
}
