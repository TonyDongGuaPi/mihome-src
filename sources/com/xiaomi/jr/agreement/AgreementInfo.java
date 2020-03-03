package com.xiaomi.jr.agreement;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class AgreementInfo implements Parcelable {
    public static final Parcelable.Creator<AgreementInfo> CREATOR = new Parcelable.Creator<AgreementInfo>() {
        /* renamed from: a */
        public AgreementInfo createFromParcel(Parcel parcel) {
            return new AgreementInfo(parcel);
        }

        /* renamed from: a */
        public AgreementInfo[] newArray(int i) {
            return new AgreementInfo[i];
        }
    };
    @SerializedName("id")

    /* renamed from: a  reason: collision with root package name */
    private String f10285a;
    @SerializedName("protocolName")
    private String b;
    @SerializedName("protocolVersion")
    private String c;
    @SerializedName("protocolUrl")
    private String d;

    public int describeContents() {
        return 0;
    }

    public String a() {
        return this.f10285a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f10285a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }

    public AgreementInfo(Parcel parcel) {
        this.f10285a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public AgreementInfo() {
    }
}
