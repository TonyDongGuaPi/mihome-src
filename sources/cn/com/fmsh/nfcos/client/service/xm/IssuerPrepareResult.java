package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class IssuerPrepareResult implements Parcelable {
    public static final Parcelable.Creator<IssuerPrepareResult> CREATOR = new Parcelable.Creator<IssuerPrepareResult>() {
        public IssuerPrepareResult createFromParcel(Parcel parcel) {
            IssuerPrepareResult issuerPrepareResult = new IssuerPrepareResult();
            issuerPrepareResult.sir = IssuerPrepareResult.readBytesWithNull(parcel);
            issuerPrepareResult.failDesc = IssuerPrepareResult.readBytesWithNull(parcel);
            return issuerPrepareResult;
        }

        public IssuerPrepareResult[] newArray(int i) {
            return new IssuerPrepareResult[i];
        }
    };
    public byte[] failDesc;
    public byte[] sir;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        writeBytesWithNull(parcel, this.sir);
        writeBytesWithNull(parcel, this.failDesc);
    }

    static byte[] readBytesWithNull(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        parcel.readByteArray(bArr);
        return bArr;
    }

    static void writeBytesWithNull(Parcel parcel, byte[] bArr) {
        if (bArr == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(bArr.length);
        parcel.writeByteArray(bArr);
    }

    public void readFromParcel(Parcel parcel) {
        this.sir = readBytesWithNull(parcel);
        this.failDesc = readBytesWithNull(parcel);
    }
}
