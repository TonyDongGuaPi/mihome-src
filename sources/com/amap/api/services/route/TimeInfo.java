package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class TimeInfo implements Parcelable {
    public static final Parcelable.Creator<TimeInfo> CREATOR = new Parcelable.Creator<TimeInfo>() {
        /* renamed from: a */
        public TimeInfo[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public TimeInfo createFromParcel(Parcel parcel) {
            return new TimeInfo(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    private long f4526a;
    private List<TimeInfosElement> b = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public long getStartTime() {
        return this.f4526a;
    }

    public void setStartTime(long j) {
        this.f4526a = j;
    }

    public List<TimeInfosElement> getElements() {
        return this.b;
    }

    public void setElements(List<TimeInfosElement> list) {
        this.b = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f4526a);
        parcel.writeTypedList(this.b);
    }

    public TimeInfo(Parcel parcel) {
        this.f4526a = (long) parcel.readInt();
        this.b = parcel.createTypedArrayList(TimeInfosElement.CREATOR);
    }

    public TimeInfo() {
    }
}
