package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class TicketOperateResult implements Parcelable {
    public static final Parcelable.Creator<TicketOperateResult> CREATOR = new Parcelable.Creator<TicketOperateResult>() {
        public TicketOperateResult createFromParcel(Parcel parcel) {
            TicketOperateResult ticketOperateResult = new TicketOperateResult();
            ticketOperateResult.ticketStub = TicketOperateResult.readBytesWithNull(parcel);
            ticketOperateResult.operateResult = parcel.readInt();
            return ticketOperateResult;
        }

        public TicketOperateResult[] newArray(int i) {
            return new TicketOperateResult[i];
        }
    };
    public int operateResult;
    public byte[] ticketStub;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        writeBytesWithNull(parcel, this.ticketStub);
        parcel.writeInt(this.operateResult);
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
        this.ticketStub = readBytesWithNull(parcel);
        this.operateResult = parcel.readInt();
    }
}
