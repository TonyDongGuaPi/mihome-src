package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class STTicketRecord implements Parcelable {
    public static final Parcelable.Creator<STTicketRecord> CREATOR = new Parcelable.Creator<STTicketRecord>() {
        public STTicketRecord createFromParcel(Parcel parcel) {
            STTicketRecord sTTicketRecord = new STTicketRecord();
            sTTicketRecord.inOutFlag = parcel.readInt();
            sTTicketRecord.date = parcel.readString();
            sTTicketRecord.time = parcel.readString();
            sTTicketRecord.stationName = parcel.readString();
            return sTTicketRecord;
        }

        public STTicketRecord[] newArray(int i) {
            return new STTicketRecord[i];
        }
    };
    public String date;
    public int inOutFlag;
    public String stationName;
    public String time;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.inOutFlag);
        parcel.writeString(this.date);
        parcel.writeString(this.time);
        parcel.writeString(this.stationName);
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
        this.inOutFlag = parcel.readInt();
        this.date = parcel.readString();
        this.time = parcel.readString();
        this.stationName = parcel.readString();
    }
}
