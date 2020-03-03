package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class BroadCastParameter implements Parcelable {
    public static final Parcelable.Creator<BroadCastParameter> CREATOR = new Parcelable.Creator<BroadCastParameter>() {
        public BroadCastParameter createFromParcel(Parcel parcel) {
            BroadCastParameter broadCastParameter = new BroadCastParameter();
            broadCastParameter.broadcastType = parcel.readInt();
            broadCastParameter.process = parcel.readInt();
            return broadCastParameter;
        }

        public BroadCastParameter[] newArray(int i) {
            return new BroadCastParameter[i];
        }
    };
    public int broadcastType;
    public int process;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.broadcastType);
        parcel.writeInt(this.process);
    }

    public void readFromParcel(Parcel parcel) {
        this.broadcastType = parcel.readInt();
        this.process = parcel.readInt();
    }
}
