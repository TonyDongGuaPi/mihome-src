package com.liulishuo.filedownloader.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileDownloadHeader implements Parcelable {
    public static final Parcelable.Creator<FileDownloadHeader> CREATOR = new Parcelable.Creator<FileDownloadHeader>() {
        /* renamed from: a */
        public FileDownloadHeader createFromParcel(Parcel parcel) {
            return new FileDownloadHeader(parcel);
        }

        /* renamed from: a */
        public FileDownloadHeader[] newArray(int i) {
            return new FileDownloadHeader[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, List<String>> f6441a;

    public int describeContents() {
        return 0;
    }

    public void a(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name == null");
        } else if (str.isEmpty()) {
            throw new IllegalArgumentException("name is empty");
        } else if (str2 != null) {
            if (this.f6441a == null) {
                this.f6441a = new HashMap<>();
            }
            List list = this.f6441a.get(str);
            if (list == null) {
                list = new ArrayList();
                this.f6441a.put(str, list);
            }
            if (!list.contains(str2)) {
                list.add(str2);
            }
        } else {
            throw new NullPointerException("value == null");
        }
    }

    public void a(String str) {
        String[] split = str.split(":");
        a(split[0].trim(), split[1].trim());
    }

    public void b(String str) {
        if (this.f6441a != null) {
            this.f6441a.remove(str);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeMap(this.f6441a);
    }

    public HashMap<String, List<String>> a() {
        return this.f6441a;
    }

    public FileDownloadHeader() {
    }

    protected FileDownloadHeader(Parcel parcel) {
        this.f6441a = parcel.readHashMap(String.class.getClassLoader());
    }

    public String toString() {
        return this.f6441a.toString();
    }
}
