package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

public class FileDownloadTaskAtom implements Parcelable {
    public static final Parcelable.Creator<FileDownloadTaskAtom> CREATOR = new Parcelable.Creator<FileDownloadTaskAtom>() {
        /* renamed from: a */
        public FileDownloadTaskAtom createFromParcel(Parcel parcel) {
            return new FileDownloadTaskAtom(parcel);
        }

        /* renamed from: a */
        public FileDownloadTaskAtom[] newArray(int i) {
            return new FileDownloadTaskAtom[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private String f6444a;
    private String b;
    private long c;
    private int d;

    public int describeContents() {
        return 0;
    }

    public FileDownloadTaskAtom(String str, String str2, long j) {
        a(str);
        b(str2);
        a(j);
    }

    public int a() {
        if (this.d != 0) {
            return this.d;
        }
        int b2 = FileDownloadUtils.b(b(), c());
        this.d = b2;
        return b2;
    }

    public String b() {
        return this.f6444a;
    }

    public void a(String str) {
        this.f6444a = str;
    }

    public String c() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public long d() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f6444a);
        parcel.writeString(this.b);
        parcel.writeLong(this.c);
    }

    protected FileDownloadTaskAtom(Parcel parcel) {
        this.f6444a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readLong();
    }
}
