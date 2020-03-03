package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginInfo implements Parcelable {
    public static final Parcelable.Creator<LoginInfo> CREATOR = new Parcelable.Creator<LoginInfo>() {
        public LoginInfo createFromParcel(Parcel parcel) {
            LoginInfo loginInfo = new LoginInfo();
            loginInfo.loginResult = parcel.readInt();
            loginInfo.loginFailureCount = parcel.readInt();
            loginInfo.userLockTime = parcel.readInt();
            return loginInfo;
        }

        public LoginInfo[] newArray(int i) {
            return new LoginInfo[i];
        }
    };
    public int loginFailureCount;
    public int loginResult;
    public int userLockTime;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.loginResult);
        parcel.writeInt(this.loginFailureCount);
        parcel.writeInt(this.userLockTime);
    }

    public void readFromParcel(Parcel parcel) {
        this.loginResult = parcel.readInt();
        this.loginFailureCount = parcel.readInt();
        this.userLockTime = parcel.readInt();
    }
}
