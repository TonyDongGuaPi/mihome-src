package com.xiaomi.jr.idcardverifier.http;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class VerifyResponse implements Parcelable {
    public static final Parcelable.Creator<VerifyResponse> CREATOR = new Parcelable.Creator<VerifyResponse>() {
        /* renamed from: a */
        public VerifyResponse createFromParcel(Parcel parcel) {
            return new VerifyResponse(parcel);
        }

        /* renamed from: a */
        public VerifyResponse[] newArray(int i) {
            return new VerifyResponse[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private static final int f10873a = 200;
    @SerializedName("errCode")
    private int b;
    @SerializedName("errDesc")
    private String c;
    @SerializedName("partnerId")
    private String d;
    @SerializedName("userId")
    private String e;
    @SerializedName("processId")
    private String f;

    public int describeContents() {
        return 0;
    }

    public int a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public boolean f() {
        return this.b == 200;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }

    public VerifyResponse(Parcel parcel) {
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
    }

    public VerifyResponse() {
    }
}
