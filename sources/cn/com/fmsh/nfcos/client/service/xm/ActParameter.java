package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.fmsh.util.FM_Bytes;

public class ActParameter implements Parcelable {
    public static final Parcelable.Creator<ActParameter> CREATOR = new Parcelable.Creator<ActParameter>() {
        public ActParameter createFromParcel(Parcel parcel) {
            ActParameter actParameter = new ActParameter();
            actParameter.tagName = parcel.readByte();
            actParameter.tagValue = parcel.readString();
            return actParameter;
        }

        public ActParameter[] newArray(int i) {
            return new ActParameter[i];
        }
    };
    public byte tagName;
    public String tagValue;

    public int describeContents() {
        return 0;
    }

    public ActParameter() {
    }

    public ActParameter(byte b, int i) {
        this.tagName = b;
        this.tagValue = String.valueOf(i);
    }

    public ActParameter(byte b, byte b2) {
        this.tagName = b;
        this.tagValue = String.valueOf(b2);
    }

    public ActParameter(byte b, byte[] bArr) {
        this.tagName = b;
        this.tagValue = FM_Bytes.bytesToHexString(bArr);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.tagName);
        parcel.writeString(this.tagValue);
    }

    public void readFromParcel(Parcel parcel) {
        this.tagName = parcel.readByte();
        this.tagValue = parcel.readString();
    }
}
