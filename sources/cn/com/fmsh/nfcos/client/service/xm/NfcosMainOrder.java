package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class NfcosMainOrder implements Parcelable {
    public static final Parcelable.Creator<NfcosMainOrder> CREATOR = new Parcelable.Creator<NfcosMainOrder>() {
        public NfcosMainOrder createFromParcel(Parcel parcel) {
            NfcosMainOrder nfcosMainOrder = new NfcosMainOrder();
            nfcosMainOrder.state = parcel.readInt();
            nfcosMainOrder.id = NfcosMainOrder.readBytesWithNull(parcel);
            nfcosMainOrder.date = parcel.readString();
            nfcosMainOrder.time = parcel.readString();
            nfcosMainOrder.amount = parcel.readInt();
            nfcosMainOrder.businessOrders = new ArrayList();
            parcel.readList(nfcosMainOrder.businessOrders, NfcosBusinessOrder.class.getClassLoader());
            nfcosMainOrder.payOrders = new ArrayList();
            parcel.readList(nfcosMainOrder.payOrders, NfcosPayOrder.class.getClassLoader());
            return nfcosMainOrder;
        }

        public NfcosMainOrder[] newArray(int i) {
            return new NfcosMainOrder[i];
        }
    };
    public int amount;
    public List<NfcosBusinessOrder> businessOrders;
    public String date;
    public byte[] id;
    public List<NfcosPayOrder> payOrders;
    public int state;
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
        parcel.writeList(this.businessOrders);
        parcel.writeList(this.payOrders);
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
        this.businessOrders = new ArrayList();
        parcel.readList(this.businessOrders, NfcosBusinessOrder.class.getClassLoader());
        this.payOrders = new ArrayList();
        parcel.readList(this.payOrders, NfcosPayOrder.class.getClassLoader());
    }
}
