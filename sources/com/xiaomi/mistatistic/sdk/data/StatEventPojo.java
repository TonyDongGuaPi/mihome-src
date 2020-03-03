package com.xiaomi.mistatistic.sdk.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;

public class StatEventPojo implements Parcelable {
    public static final Parcelable.Creator<StatEventPojo> CREATOR = new Parcelable.Creator<StatEventPojo>() {
        /* renamed from: a */
        public StatEventPojo createFromParcel(Parcel parcel) {
            StatEventPojo statEventPojo = new StatEventPojo();
            statEventPojo.f12065a = parcel.readString();
            statEventPojo.b = parcel.readLong();
            statEventPojo.c = parcel.readString();
            statEventPojo.d = parcel.readString();
            statEventPojo.e = parcel.readString();
            statEventPojo.f = parcel.readString();
            return statEventPojo;
        }

        /* renamed from: a */
        public StatEventPojo[] newArray(int i) {
            return new StatEventPojo[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f12065a;
    public long b;
    public String c;
    public String d;
    public String e;
    public String f;

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "Event [" + "category=" + this.f12065a + "," + "key=" + this.c + "," + "value=" + this.e + ",params=" + this.f + Operators.ARRAY_END_STR;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f12065a);
        parcel.writeLong(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }
}
