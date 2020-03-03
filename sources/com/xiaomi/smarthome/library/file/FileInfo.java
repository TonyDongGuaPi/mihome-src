package com.xiaomi.smarthome.library.file;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

public class FileInfo implements Parcelable {
    public static final Parcelable.Creator<FileInfo> CREATOR = new Parcelable.Creator<FileInfo>() {
        /* renamed from: a */
        public FileInfo createFromParcel(Parcel parcel) {
            FileInfo fileInfo = new FileInfo();
            boolean z = false;
            boolean unused = fileInfo.f19097a = parcel.readByte() != 0;
            boolean unused2 = fileInfo.b = parcel.readByte() != 0;
            int unused3 = fileInfo.c = parcel.readInt();
            String unused4 = fileInfo.d = parcel.readString();
            String unused5 = fileInfo.e = parcel.readString();
            long unused6 = fileInfo.f = parcel.readLong();
            long unused7 = fileInfo.g = parcel.readLong();
            boolean unused8 = fileInfo.h = parcel.readByte() != 0;
            if (parcel.readByte() != 0) {
                z = true;
            }
            boolean unused9 = fileInfo.i = z;
            String unused10 = fileInfo.k = parcel.readString();
            return fileInfo;
        }

        /* renamed from: a */
        public FileInfo[] newArray(int i) {
            return new FileInfo[i];
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f19097a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    /* access modifiers changed from: private */
    public long f;
    /* access modifiers changed from: private */
    public long g;
    /* access modifiers changed from: private */
    public boolean h;
    /* access modifiers changed from: private */
    public boolean i;
    private boolean j;
    /* access modifiers changed from: private */
    public String k;

    public int describeContents() {
        return 0;
    }

    public boolean a() {
        return this.f19097a;
    }

    public void a(boolean z) {
        this.f19097a = z;
    }

    public boolean b() {
        return this.b;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public int c() {
        return this.c;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String e() {
        return this.e;
    }

    public void b(String str) {
        this.e = str;
    }

    public long f() {
        return this.f;
    }

    public void a(long j2) {
        this.f = j2;
    }

    public long g() {
        return this.g;
    }

    public void b(long j2) {
        this.g = j2;
    }

    public boolean h() {
        return this.h;
    }

    public void c(boolean z) {
        this.h = z;
    }

    public boolean i() {
        return this.i;
    }

    public void d(boolean z) {
        this.i = z;
    }

    public boolean j() {
        return this.j;
    }

    public void e(boolean z) {
        this.j = z;
    }

    public String k() {
        return this.k;
    }

    public void c(String str) {
        this.k = str;
    }

    public static FileInfo a(Cursor cursor) {
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        return d(cursor.getString(1));
    }

    public static FileInfo d(String str) {
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        return a(file);
    }

    public static FileInfo a(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.a(true);
        fileInfo.b(file.isDirectory());
        fileInfo.a(FileUtil.a(file));
        fileInfo.a(file.getAbsolutePath());
        fileInfo.b(FileUtil.c(file.getAbsolutePath()));
        fileInfo.a(file.length());
        fileInfo.b(file.lastModified());
        fileInfo.c(file.isHidden());
        fileInfo.e(false);
        String e2 = FileUtil.e(fileInfo.e());
        if (e2.equalsIgnoreCase("jpg") || e2.equalsIgnoreCase("jpeg") || e2.equalsIgnoreCase("gif") || e2.equalsIgnoreCase("png") || e2.equals("bmp") || e2.equalsIgnoreCase("wbmp")) {
            fileInfo.d(true);
        } else {
            fileInfo.d(false);
        }
        return fileInfo;
    }

    public static FileInfo a(String str, JSONObject jSONObject) throws JSONException {
        FileInfo fileInfo = new FileInfo();
        fileInfo.f19097a = false;
        fileInfo.e = jSONObject.getString("name");
        if (jSONObject.getInt("type") == 1) {
            fileInfo.b = true;
        }
        fileInfo.g = jSONObject.getLong("modifyTime") * 1000;
        fileInfo.c = jSONObject.getInt("itemsCount");
        fileInfo.f = jSONObject.getLong("size");
        fileInfo.i = jSONObject.optBoolean("canHasThumb");
        fileInfo.h = jSONObject.optBoolean("ishidden");
        fileInfo.j = jSONObject.optBoolean("isShared");
        if (str.endsWith(File.separator)) {
            fileInfo.d = str + fileInfo.e;
        } else {
            fileInfo.d = str + File.separator + fileInfo.e;
        }
        return fileInfo;
    }

    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeByte(this.f19097a ? (byte) 1 : 0);
        parcel.writeByte(this.b ? (byte) 1 : 0);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeByte(this.h ? (byte) 1 : 0);
        parcel.writeByte(this.i ? (byte) 1 : 0);
        parcel.writeString(this.k);
    }
}
