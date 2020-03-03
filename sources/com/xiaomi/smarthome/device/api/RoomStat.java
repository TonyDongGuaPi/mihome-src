package com.xiaomi.smarthome.device.api;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Collections;
import java.util.List;

public class RoomStat implements Parcelable {
    public static final Parcelable.Creator<RoomStat> CREATOR = new Parcelable.Creator<RoomStat>() {
        public RoomStat createFromParcel(Parcel parcel) {
            return new RoomStat(parcel);
        }

        public RoomStat[] newArray(int i) {
            return new RoomStat[i];
        }
    };
    public String bssid = "";
    public String desc = "";
    public volatile List<String> dids = Collections.emptyList();
    public String icon = "";
    public String id = "";
    public String name = "";
    public String parentid = "";
    public int shareflag = 0;

    public int describeContents() {
        return 0;
    }

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RoomStat)) {
            return false;
        }
        RoomStat roomStat = (RoomStat) obj;
        if (roomStat.id == null || this.id == null || !this.id.equalsIgnoreCase(roomStat.id)) {
            return false;
        }
        return true;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.bssid);
        parcel.writeString(this.desc);
        parcel.writeString(this.id);
        parcel.writeString(this.name);
        parcel.writeString(this.parentid);
        parcel.writeInt(this.shareflag);
        parcel.writeStringList(this.dids);
        parcel.writeString(this.icon);
    }

    protected RoomStat(Parcel parcel) {
        this.bssid = parcel.readString();
        this.desc = parcel.readString();
        this.id = parcel.readString();
        this.name = parcel.readString();
        this.parentid = parcel.readString();
        this.shareflag = parcel.readInt();
        this.dids = parcel.createStringArrayList();
        this.icon = parcel.readString();
    }

    public RoomStat() {
    }
}
