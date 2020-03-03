package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class IssueProcess implements Parcelable {
    public static final Parcelable.Creator<IssueProcess> CREATOR = new Parcelable.Creator<IssueProcess>() {
        public IssueProcess createFromParcel(Parcel parcel) {
            IssueProcess issueProcess = new IssueProcess();
            issueProcess.process = parcel.readInt();
            return issueProcess;
        }

        public IssueProcess[] newArray(int i) {
            return new IssueProcess[i];
        }
    };
    public int process;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.process);
    }

    public void readFromParcel(Parcel parcel) {
        this.process = parcel.readInt();
    }
}
