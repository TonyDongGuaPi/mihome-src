package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class UserInfo implements Parcelable {
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        public UserInfo createFromParcel(Parcel parcel) {
            UserInfo userInfo = new UserInfo();
            userInfo.username = parcel.readString();
            userInfo.password = parcel.readString();
            return userInfo;
        }

        public UserInfo[] newArray(int i) {
            return new UserInfo[i];
        }
    };
    public String password;
    public String username;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.username);
        parcel.writeString(this.password);
    }

    public void readFromParcel(Parcel parcel) {
        this.username = parcel.readString();
        this.password = parcel.readString();
    }
}
