package com.xiaomi.smarthome.core.entity.device;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class DeviceListResult implements Parcelable {
    public static final Parcelable.Creator<DeviceListResult> CREATOR = new Parcelable.Creator<DeviceListResult>() {
        /* renamed from: a */
        public DeviceListResult createFromParcel(Parcel parcel) {
            return new DeviceListResult(parcel);
        }

        /* renamed from: a */
        public DeviceListResult[] newArray(int i) {
            return new DeviceListResult[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private List<Device> f13973a = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public DeviceListResult() {
    }

    protected DeviceListResult(Parcel parcel) {
        this.f13973a = parcel.createTypedArrayList(Device.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.f13973a);
    }

    public synchronized List<Device> a() {
        return this.f13973a;
    }

    public synchronized void a(List<Device> list) {
        this.f13973a = list;
    }
}
