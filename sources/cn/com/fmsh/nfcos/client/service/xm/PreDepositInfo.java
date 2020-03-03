package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class PreDepositInfo implements Parcelable {
    public static final Parcelable.Creator<PreDepositInfo> CREATOR = new Parcelable.Creator<PreDepositInfo>() {
        public PreDepositInfo createFromParcel(Parcel parcel) {
            PreDepositInfo preDepositInfo = new PreDepositInfo();
            preDepositInfo.total = parcel.readInt();
            preDepositInfo.blance = parcel.readInt();
            return preDepositInfo;
        }

        public PreDepositInfo[] newArray(int i) {
            return new PreDepositInfo[i];
        }
    };
    public int blance;
    public int total;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.total);
        parcel.writeInt(this.blance);
    }

    public void readFromParcel(Parcel parcel) {
        this.total = parcel.readInt();
        this.blance = parcel.readInt();
    }
}
