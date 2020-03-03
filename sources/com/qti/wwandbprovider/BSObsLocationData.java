package com.qti.wwandbprovider;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import com.qti.wwandbreceiver.BSInfo;

public final class BSObsLocationData implements Parcelable {
    public static final Parcelable.Creator<BSObsLocationData> CREATOR = new Parcelable.Creator<BSObsLocationData>() {
        /* renamed from: a */
        public BSObsLocationData createFromParcel(Parcel parcel) {
            return new BSObsLocationData(parcel);
        }

        /* renamed from: a */
        public BSObsLocationData[] newArray(int i) {
            return new BSObsLocationData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public Location f8639a;
    public BSInfo b;
    public int c;

    public int describeContents() {
        return 0;
    }

    public BSObsLocationData() {
    }

    private BSObsLocationData(Parcel parcel) {
        a(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        parcel.writeByte((byte) (this.f8639a != null ? 1 : 0));
        if (this.f8639a != null) {
            this.f8639a.writeToParcel(parcel, i);
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
        BSInfo bSInfo = null;
        this.f8639a = parcel.readByte() == 1 ? (Location) Location.CREATOR.createFromParcel(parcel) : null;
        if (parcel.readByte() == 1) {
            bSInfo = BSInfo.CREATOR.createFromParcel(parcel);
        }
        this.b = bSInfo;
        this.c = parcel.readInt();
    }
}
