package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class NfcosPayOrder implements Parcelable {
    public static final Parcelable.Creator<NfcosPayOrder> CREATOR = new Parcelable.Creator<NfcosPayOrder>() {
        public NfcosPayOrder createFromParcel(Parcel parcel) {
            NfcosPayOrder nfcosPayOrder = new NfcosPayOrder();
            nfcosPayOrder.state = parcel.readInt();
            nfcosPayOrder.id = NfcosPayOrder.readBytesWithNull(parcel);
            nfcosPayOrder.date = parcel.readString();
            nfcosPayOrder.time = parcel.readString();
            nfcosPayOrder.amount = parcel.readInt();
            nfcosPayOrder.thirdPayInfo = parcel.readString();
            nfcosPayOrder.channel = parcel.readInt();
            nfcosPayOrder.mainOrder = NfcosPayOrder.readBytesWithNull(parcel);
            return nfcosPayOrder;
        }

        public NfcosPayOrder[] newArray(int i) {
            return new NfcosPayOrder[i];
        }
    };
    public int amount;
    public int channel;
    public String date;
    public byte[] id;
    public byte[] mainOrder;
    public int state;
    public String thirdPayInfo;
    public String time;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.state);
        writeBytesWithNull(parcel, this.id);
        parcel.writeString(this.date);
        parcel.writeString(this.time);
        parcel.writeInt(this.amount);
        parcel.writeString(this.thirdPayInfo);
        parcel.writeInt(this.channel);
        writeBytesWithNull(parcel, this.mainOrder);
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
        this.state = parcel.readInt();
        this.id = readBytesWithNull(parcel);
        this.date = parcel.readString();
        this.time = parcel.readString();
        this.amount = parcel.readInt();
        this.thirdPayInfo = parcel.readString();
        this.channel = parcel.readInt();
        this.mainOrder = readBytesWithNull(parcel);
    }
}
