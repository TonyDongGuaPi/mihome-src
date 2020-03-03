package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class CardAppStatus implements Parcelable {
    public static final Parcelable.Creator<CardAppStatus> CREATOR = new Parcelable.Creator<CardAppStatus>() {
        public CardAppStatus createFromParcel(Parcel parcel) {
            CardAppStatus cardAppStatus = new CardAppStatus();
            cardAppStatus.setStatus(parcel.readInt());
            return cardAppStatus;
        }

        public CardAppStatus[] newArray(int i) {
            return new CardAppStatus[i];
        }
    };
    private int status;

    public int describeContents() {
        return 0;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.status);
    }

    public void readFromParcel(Parcel parcel) {
        setStatus(parcel.readInt());
    }
}
