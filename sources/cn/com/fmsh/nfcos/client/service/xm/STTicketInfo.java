package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class STTicketInfo implements Parcelable {
    public static final Parcelable.Creator<STTicketInfo> CREATOR = new Parcelable.Creator<STTicketInfo>() {
        public STTicketInfo createFromParcel(Parcel parcel) {
            STTicketInfo sTTicketInfo = new STTicketInfo();
            sTTicketInfo.ticketType = parcel.readInt();
            sTTicketInfo.appNo = STTicketInfo.readBytesWithNull(parcel);
            sTTicketInfo.inOutFlag = parcel.readInt();
            sTTicketInfo.startUsageTime = parcel.readString();
            sTTicketInfo.validationDate = parcel.readString();
            return sTTicketInfo;
        }

        public STTicketInfo[] newArray(int i) {
            return new STTicketInfo[i];
        }
    };
    public byte[] appNo;
    public int inOutFlag;
    public String startUsageTime;
    public int ticketType;
    public String validationDate;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.ticketType);
        writeBytesWithNull(parcel, this.appNo);
        parcel.writeInt(this.inOutFlag);
        parcel.writeString(this.startUsageTime);
        parcel.writeString(this.validationDate);
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
        this.ticketType = parcel.readInt();
        this.appNo = readBytesWithNull(parcel);
        this.inOutFlag = parcel.readInt();
        this.startUsageTime = parcel.readString();
        this.validationDate = parcel.readString();
    }
}
