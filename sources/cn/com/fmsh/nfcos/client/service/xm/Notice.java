package cn.com.fmsh.nfcos.client.service.xm;

import android.os.Parcel;
import android.os.Parcelable;

public class Notice implements Parcelable {
    public static final Parcelable.Creator<Notice> CREATOR = new Parcelable.Creator<Notice>() {
        public Notice createFromParcel(Parcel parcel) {
            Notice notice = new Notice();
            notice.f549no = parcel.readInt();
            notice.title = parcel.readString();
            notice.content = parcel.readString();
            notice.startDate = parcel.readString();
            notice.endDate = parcel.readString();
            notice.type = parcel.readInt();
            notice.order = Notice.readBytesWithNull(parcel);
            return notice;
        }

        public Notice[] newArray(int i) {
            return new Notice[i];
        }
    };
    public static int NOTICE_TXT = 22;
    public static int NOTICE_UNSOLVED = 33;
    public String content;
    public String endDate;

    /* renamed from: no  reason: collision with root package name */
    public int f549no;
    public byte[] order;
    public String startDate;
    public String title;
    public int type;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f549no);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeString(this.startDate);
        parcel.writeString(this.endDate);
        parcel.writeInt(this.type);
        writeBytesWithNull(parcel, this.order);
    }

    public void readFromParcel(Parcel parcel) {
        this.f549no = parcel.readInt();
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.startDate = parcel.readString();
        this.endDate = parcel.readString();
        this.type = parcel.readInt();
        this.order = readBytesWithNull(parcel);
    }

    static byte[] readBytesWithNull(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt < 0) {
            return null;
        }
        byte[] bArr = new byte[readInt];
        parcel.readByteArray(bArr);
        return bArr;
    }

    static void writeBytesWithNull(Parcel parcel, byte[] bArr) {
        if (bArr == null) {
            parcel.writeInt(-1);
            return;
        }
        parcel.writeInt(bArr.length);
        parcel.writeByteArray(bArr);
    }
}
