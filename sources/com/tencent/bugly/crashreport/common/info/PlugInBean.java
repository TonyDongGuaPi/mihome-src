package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() {
        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
            return new PlugInBean[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public final String f8994a;
    public final String b;
    public final String c;

    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f8994a = str;
        this.b = str2;
        this.c = str3;
    }

    public String toString() {
        return "plid:" + this.f8994a + " plV:" + this.b + " plUUID:" + this.c;
    }

    public PlugInBean(Parcel parcel) {
        this.f8994a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f8994a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
