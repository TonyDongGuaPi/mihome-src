package com.xiaomi.router.miio.miioplugin;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.RoomStat;
import java.util.Collections;
import java.util.List;

public class RoomStatus implements Parcelable {
    public static final Parcelable.Creator<RoomStatus> CREATOR = new Parcelable.Creator<RoomStatus>() {
        /* renamed from: a */
        public RoomStatus createFromParcel(Parcel parcel) {
            return new RoomStatus(parcel);
        }

        /* renamed from: a */
        public RoomStatus[] newArray(int i) {
            return new RoomStatus[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f13055a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public int f = 0;
    public volatile List<String> g = Collections.emptyList();
    public String h = "";

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return this.c.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RoomStatus)) {
            return false;
        }
        RoomStatus roomStatus = (RoomStatus) obj;
        if (roomStatus.c == null || this.c == null || !this.c.equalsIgnoreCase(roomStatus.c)) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f13055a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeStringList(this.g);
        parcel.writeString(this.h);
    }

    public RoomStatus(Parcel parcel) {
        this.f13055a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readInt();
        this.g = parcel.createStringArrayList();
        this.h = parcel.readString();
    }

    public RoomStat a() {
        RoomStat roomStat = new RoomStat();
        roomStat.bssid = this.f13055a;
        roomStat.desc = this.b;
        roomStat.dids = this.g;
        roomStat.icon = this.h;
        roomStat.id = this.c;
        roomStat.name = this.d;
        roomStat.parentid = this.e;
        roomStat.shareflag = this.f;
        return roomStat;
    }

    public RoomStatus() {
    }
}
