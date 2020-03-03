package com.xiaomi.smarthome.newui.adapter.deviceroom;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import java.util.ArrayList;
import java.util.List;

public class DeviceRoomData implements Parcelable {
    public static final Parcelable.Creator<DeviceRoomData> CREATOR = new Parcelable.Creator<DeviceRoomData>() {
        /* renamed from: a */
        public DeviceRoomData createFromParcel(Parcel parcel) {
            return new DeviceRoomData(parcel);
        }

        /* renamed from: a */
        public DeviceRoomData[] newArray(int i) {
            return new DeviceRoomData[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private List<Device> f20402a;
    private List<Room> b;
    private String c;

    public int describeContents() {
        return 0;
    }

    public List<Device> a() {
        return this.f20402a;
    }

    public void a(List<Device> list) {
        this.f20402a = list;
    }

    public List<Room> b() {
        return this.b;
    }

    public void b(List<Room> list) {
        this.b = list;
    }

    public String c() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.f20402a);
        parcel.writeTypedList(this.b);
        parcel.writeString(this.c);
    }

    public DeviceRoomData() {
    }

    protected DeviceRoomData(Parcel parcel) {
        this.f20402a = new ArrayList();
        parcel.readList(this.f20402a, Device.class.getClassLoader());
        this.b = parcel.createTypedArrayList(Room.CREATOR);
        this.c = parcel.readString();
    }

    public static DeviceRoomData d() {
        DeviceRoomData deviceRoomData = new DeviceRoomData();
        deviceRoomData.a(HomeManager.a().m(HomeManager.a().l()));
        deviceRoomData.b(HomeManager.a().e());
        return deviceRoomData;
    }
}
