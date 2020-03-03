package com.xiaomi.smarthome.core.entity.scene;

import android.os.Parcel;
import android.os.Parcelable;

public class Scene implements Parcelable {
    public static final Parcelable.Creator<Scene> CREATOR = new Parcelable.Creator<Scene>() {
        /* renamed from: a */
        public Scene createFromParcel(Parcel parcel) {
            return new Scene(parcel);
        }

        /* renamed from: a */
        public Scene[] newArray(int i) {
            return new Scene[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f13996a;
    public String b;
    public boolean c;
    public int d;
    public String e;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13996a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c ? 1 : 0);
        parcel.writeInt(this.d);
        parcel.writeString(this.e);
    }

    public Scene() {
    }

    public Scene(Parcel parcel) {
        this.f13996a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt() != 0;
        this.e = parcel.readString();
    }
}
