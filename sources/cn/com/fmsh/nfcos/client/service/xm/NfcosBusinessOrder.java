package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class NfcosBusinessOrder implements Parcelable {
    public static final Parcelable.Creator<NfcosBusinessOrder> CREATOR = new Parcelable.Creator<NfcosBusinessOrder>() {
        public NfcosBusinessOrder createFromParcel(Parcel parcel) {
            NfcosBusinessOrder nfcosBusinessOrder = new NfcosBusinessOrder();
            nfcosBusinessOrder.tradeDate = parcel.readString();
            nfcosBusinessOrder.tradeTime = parcel.readString();
            nfcosBusinessOrder.order = NfcosBusinessOrder.readBytesWithNull(parcel);
            nfcosBusinessOrder.amount = parcel.readInt();
            nfcosBusinessOrder.faceNo = parcel.readString();
            nfcosBusinessOrder.tradeState = parcel.readInt();
            nfcosBusinessOrder.invoiceStatus = parcel.readInt();
            nfcosBusinessOrder.cardIoType = parcel.readInt();
            nfcosBusinessOrder.businessOrderType = parcel.readInt();
            nfcosBusinessOrder.product = parcel.readString();
            nfcosBusinessOrder.seid = NfcosBusinessOrder.readBytesWithNull(parcel);
            nfcosBusinessOrder.deviceModel = parcel.readString();
            nfcosBusinessOrder.mainOrder = NfcosBusinessOrder.readBytesWithNull(parcel);
            return nfcosBusinessOrder;
        }

        public NfcosBusinessOrder[] newArray(int i) {
            return new NfcosBusinessOrder[i];
        }
    };
    public int amount;
    public int businessOrderType;
    public int cardIoType;
    public String deviceModel;
    public String faceNo;
    public int invoiceStatus;
    public byte[] mainOrder;
    public byte[] order;
    public String product;
    public byte[] seid;
    public String tradeDate;
    public int tradeState;
    public String tradeTime;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.tradeDate);
        parcel.writeString(this.tradeTime);
        writeBytesWithNull(parcel, this.order);
        parcel.writeInt(this.amount);
        parcel.writeString(this.faceNo);
        parcel.writeInt(this.tradeState);
        parcel.writeInt(this.invoiceStatus);
        parcel.writeInt(this.cardIoType);
        parcel.writeInt(this.businessOrderType);
        parcel.writeString(this.product);
        writeBytesWithNull(parcel, this.seid);
        parcel.writeString(this.deviceModel);
        writeBytesWithNull(parcel, this.mainOrder);
    }

    public void readFromParcel(Parcel parcel) {
        this.tradeDate = parcel.readString();
        this.tradeTime = parcel.readString();
        this.order = readBytesWithNull(parcel);
        this.amount = parcel.readInt();
        this.faceNo = parcel.readString();
        this.tradeState = parcel.readInt();
        this.invoiceStatus = parcel.readInt();
        this.cardIoType = parcel.readInt();
        this.businessOrderType = parcel.readInt();
        this.product = parcel.readString();
        this.seid = readBytesWithNull(parcel);
        this.deviceModel = parcel.readString();
        this.mainOrder = readBytesWithNull(parcel);
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
