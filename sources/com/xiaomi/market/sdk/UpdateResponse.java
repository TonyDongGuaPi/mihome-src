package com.xiaomi.market.sdk;

import android.os.Parcel;
import android.os.Parcelable;

public class UpdateResponse implements Parcelable {
    public static final Parcelable.Creator<UpdateResponse> CREATOR = new Parcelable.Creator<UpdateResponse>() {
        /* renamed from: a */
        public UpdateResponse createFromParcel(Parcel parcel) {
            return new UpdateResponse(parcel);
        }

        /* renamed from: a */
        public UpdateResponse[] newArray(int i) {
            return new UpdateResponse[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f11115a;
    public String b;
    public int c;
    public String d;
    public long e;
    public String f;
    public long g;

    public int describeContents() {
        return 0;
    }

    public UpdateResponse() {
    }

    protected UpdateResponse(Parcel parcel) {
        this.c = parcel.readInt();
        this.b = parcel.readString();
        this.d = parcel.readString();
        this.f = parcel.readString();
        this.f11115a = parcel.readString();
        this.e = parcel.readLong();
        this.g = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.c);
        parcel.writeString(this.b);
        parcel.writeString(this.d);
        parcel.writeString(this.f);
        parcel.writeString(this.f11115a);
        parcel.writeLong(this.e);
        parcel.writeLong(this.g);
    }
}
