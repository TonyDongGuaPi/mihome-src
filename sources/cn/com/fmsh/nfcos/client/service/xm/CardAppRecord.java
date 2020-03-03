package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class CardAppRecord implements Parcelable {
    public static final Parcelable.Creator<CardAppRecord> CREATOR = new Parcelable.Creator<CardAppRecord>() {
        public CardAppRecord createFromParcel(Parcel parcel) {
            CardAppRecord cardAppRecord = new CardAppRecord();
            cardAppRecord.tradeType = parcel.readInt();
            cardAppRecord.tradeDate = parcel.readString();
            cardAppRecord.tradeTime = parcel.readString();
            cardAppRecord.amount = parcel.readInt();
            cardAppRecord.balance = parcel.readInt();
            return cardAppRecord;
        }

        public CardAppRecord[] newArray(int i) {
            return new CardAppRecord[i];
        }
    };
    public int amount;
    public int balance;
    public String tradeDate;
    public String tradeTime;
    public int tradeType;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.tradeType);
        parcel.writeString(this.tradeDate);
        parcel.writeString(this.tradeTime);
        parcel.writeInt(this.amount);
        parcel.writeInt(this.balance);
    }

    public void readFromParcel(Parcel parcel) {
        this.tradeType = parcel.readInt();
        this.tradeDate = parcel.readString();
        this.tradeTime = parcel.readString();
        this.amount = parcel.readInt();
        this.balance = parcel.readInt();
    }
}
