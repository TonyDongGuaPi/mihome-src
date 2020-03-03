package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class NfcosActivity implements Parcelable {
    public static final Parcelable.Creator<NfcosActivity> CREATOR = new Parcelable.Creator<NfcosActivity>() {
        public NfcosActivity createFromParcel(Parcel parcel) {
            NfcosActivity nfcosActivity = new NfcosActivity();
            nfcosActivity.name = parcel.readString();
            nfcosActivity.code = parcel.readString();
            nfcosActivity.start = parcel.readString();
            nfcosActivity.end = parcel.readString();
            nfcosActivity.total = parcel.readInt();
            nfcosActivity.remainder = parcel.readInt();
            nfcosActivity.definition = parcel.readString();
            nfcosActivity.payChannel = parcel.readString();
            nfcosActivity.payMin = parcel.readInt();
            return nfcosActivity;
        }

        public NfcosActivity[] newArray(int i) {
            return new NfcosActivity[i];
        }
    };
    public String code;
    public String definition;
    public String end;
    public String name;
    public String payChannel;
    public int payMin;
    public int remainder;
    public String start;
    public int total;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.code);
        parcel.writeString(this.start);
        parcel.writeString(this.end);
        parcel.writeInt(this.total);
        parcel.writeInt(this.remainder);
        parcel.writeString(this.definition);
        parcel.writeString(this.payChannel);
        parcel.writeInt(this.payMin);
    }

    public void readFromParcel(Parcel parcel) {
        this.name = parcel.readString();
        this.code = parcel.readString();
        this.start = parcel.readString();
        this.end = parcel.readString();
        this.total = parcel.readInt();
        this.remainder = parcel.readInt();
        this.definition = parcel.readString();
        this.payChannel = parcel.readString();
        this.payMin = parcel.readInt();
    }
}
