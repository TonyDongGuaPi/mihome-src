package com.ximalaya.ting.android.opensdk.httputil;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.HashMap;
import java.util.Map;

public class Config implements Parcelable {
    public static final Parcelable.Creator<Config> CREATOR = new Parcelable.Creator<Config>() {
        /* renamed from: a */
        public Config createFromParcel(Parcel parcel) {
            return new Config(parcel);
        }

        /* renamed from: a */
        public Config[] newArray(int i) {
            return new Config[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f1987a = "GET";
    public static final String b = "POST";
    public static final String c = "HEAD";
    public boolean d = false;
    public boolean e = true;
    public String f;
    public int g;
    public int h;
    public String i;
    public int j = 30000;
    public int k = 30000;
    public int l = 30000;
    public String m = "GET";
    public Map<String, String> n = new HashMap();
    public int o = -1;

    public int describeContents() {
        return 0;
    }

    public static Config a(Config config) {
        if (config == null) {
            config = new Config();
        }
        config.j = 30000;
        config.k = 30000;
        return config;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.d ? (byte) 1 : 0);
        parcel.writeByte(this.e ? (byte) 1 : 0);
        parcel.writeString(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeInt(this.n.size());
        for (Map.Entry next : this.n.entrySet()) {
            parcel.writeString((String) next.getKey());
            parcel.writeString((String) next.getValue());
        }
        parcel.writeInt(this.o);
    }

    public Config() {
    }

    public void a(Parcel parcel) {
        boolean z = true;
        this.d = parcel.readByte() != 0;
        if (parcel.readByte() == 0) {
            z = false;
        }
        this.e = z;
        this.f = parcel.readString();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.readInt();
        this.m = parcel.readString();
        int readInt = parcel.readInt();
        this.n = new HashMap(readInt);
        for (int i2 = 0; i2 < readInt; i2++) {
            this.n.put(parcel.readString(), parcel.readString());
        }
        this.o = parcel.readInt();
    }

    protected Config(Parcel parcel) {
        a(parcel);
    }

    public String toString() {
        return "Config{useProxy=" + this.d + ", useCache=" + this.e + ", proxyHost='" + this.f + Operators.SINGLE_QUOTE + ", proxyPort=" + this.g + ", httpsProxyPort=" + this.h + ", authorization='" + this.i + Operators.SINGLE_QUOTE + ", connectionTimeOut=" + this.j + ", readTimeOut=" + this.k + ", writeTimeOut=" + this.l + ", method='" + this.m + Operators.SINGLE_QUOTE + ", property=" + this.n + Operators.BLOCK_END;
    }
}
