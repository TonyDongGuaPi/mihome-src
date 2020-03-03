package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class VoucherInfo implements Parcelable {
    public static final Parcelable.Creator<VoucherInfo> CREATOR = new Parcelable.Creator<VoucherInfo>() {
        public VoucherInfo createFromParcel(Parcel parcel) {
            VoucherInfo voucherInfo = new VoucherInfo();
            voucherInfo.token = VoucherInfo.readBytesWithNull(parcel);
            return voucherInfo;
        }

        public VoucherInfo[] newArray(int i) {
            return new VoucherInfo[i];
        }
    };
    public byte[] token;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        writeBytesWithNull(parcel, this.token);
    }

    public void readFromParcel(Parcel parcel) {
        this.token = readBytesWithNull(parcel);
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
}
