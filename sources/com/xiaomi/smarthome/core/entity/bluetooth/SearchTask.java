package com.xiaomi.smarthome.core.entity.bluetooth;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.UUID;

public class SearchTask implements Parcelable {
    public static final Parcelable.Creator<SearchTask> CREATOR = new Parcelable.Creator<SearchTask>() {
        /* renamed from: a */
        public SearchTask createFromParcel(Parcel parcel) {
            return new SearchTask(parcel);
        }

        /* renamed from: a */
        public SearchTask[] newArray(int i) {
            return new SearchTask[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private int f13970a;
    private int b;
    private UUID[] c;

    public int describeContents() {
        return 0;
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [java.util.UUID[], java.io.Serializable] */
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f13970a);
        parcel.writeInt(this.b);
        parcel.writeSerializable(this.c);
    }

    public SearchTask() {
    }

    public int a() {
        return this.f13970a;
    }

    public void a(int i) {
        this.f13970a = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        this.b = i;
    }

    public UUID[] c() {
        return this.c;
    }

    public void a(UUID[] uuidArr) {
        this.c = uuidArr;
    }

    protected SearchTask(Parcel parcel) {
        this.f13970a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = (UUID[]) parcel.readSerializable();
    }
}
