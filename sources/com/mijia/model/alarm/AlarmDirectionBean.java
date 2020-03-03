package com.mijia.model.alarm;

import android.os.Parcel;
import android.os.Parcelable;

public class AlarmDirectionBean implements Parcelable {
    public static final Parcelable.Creator<AlarmDirectionBean> CREATOR = new Parcelable.Creator<AlarmDirectionBean>() {
        /* renamed from: a */
        public AlarmDirectionBean createFromParcel(Parcel parcel) {
            return new AlarmDirectionBean(parcel);
        }

        /* renamed from: a */
        public AlarmDirectionBean[] newArray(int i) {
            return new AlarmDirectionBean[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public int f7967a = 0;
    public int b = 0;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public String f;

    public int describeContents() {
        return 0;
    }

    public AlarmDirectionBean() {
    }

    protected AlarmDirectionBean(Parcel parcel) {
        this.f7967a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f7967a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof AlarmDirectionBean)) {
            return false;
        }
        AlarmDirectionBean alarmDirectionBean = (AlarmDirectionBean) obj;
        if (alarmDirectionBean.d == this.d && this.b <= alarmDirectionBean.c && this.c >= alarmDirectionBean.b) {
            return true;
        }
        return false;
    }
}
