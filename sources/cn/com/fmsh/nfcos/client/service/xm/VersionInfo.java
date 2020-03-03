package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class VersionInfo implements Parcelable {
    public static final Parcelable.Creator<VersionInfo> CREATOR = new Parcelable.Creator<VersionInfo>() {
        public VersionInfo createFromParcel(Parcel parcel) {
            VersionInfo versionInfo = new VersionInfo();
            versionInfo.version = parcel.readString();
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            versionInfo.isUpdate = z;
            versionInfo.url = parcel.readString();
            return versionInfo;
        }

        public VersionInfo[] newArray(int i) {
            return new VersionInfo[i];
        }
    };
    public boolean isUpdate;
    public String url;
    public String version;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.version);
        parcel.writeInt(this.isUpdate ? 1 : 0);
        parcel.writeString(this.url);
    }

    public void readFromParcel(Parcel parcel) {
        this.version = parcel.readString();
        boolean z = true;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.isUpdate = z;
        this.url = parcel.readString();
    }
}
