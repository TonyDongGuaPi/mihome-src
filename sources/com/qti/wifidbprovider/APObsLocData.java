package com.qti.wifidbprovider;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.qti.wwandbreceiver.BSInfo;

public final class APObsLocData implements Parcelable {
    public static final Parcelable.Creator<APObsLocData> CREATOR = new Parcelable.Creator<APObsLocData>() {
        /* renamed from: a */
        public APObsLocData createFromParcel(Parcel parcel) {
            return new APObsLocData(parcel);
        }

        /* renamed from: a */
        public APObsLocData[] newArray(int i) {
            return new APObsLocData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public Location f8633a;
    public BSInfo b;
    public int c;
    public APScan[] d;

    public int describeContents() {
        return 0;
    }

    public APObsLocData() {
    }

    private APObsLocData(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeTypedArray(this.d, 0);
        parcel.writeByte((byte) (this.f8633a != null ? 1 : 0));
        if (this.f8633a != null) {
            this.f8633a.writeToParcel(parcel, i);
        }
        if (this.b != null) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        if (this.b != null) {
            this.b.writeToParcel(parcel, i);
        }
        parcel.writeInt(this.c);
    }

    public void a(Parcel parcel) {
        this.d = (APScan[]) parcel.createTypedArray(APScan.CREATOR);
        BSInfo bSInfo = null;
        this.f8633a = parcel.readByte() == 1 ? (Location) Location.CREATOR.createFromParcel(parcel) : null;
        if (parcel.readByte() == 1) {
            bSInfo = BSInfo.CREATOR.createFromParcel(parcel);
        }
        this.b = bSInfo;
        this.c = parcel.readInt();
    }
}
