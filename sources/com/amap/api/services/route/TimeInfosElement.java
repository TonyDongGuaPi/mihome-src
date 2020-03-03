package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class TimeInfosElement implements Parcelable {
    public static final Parcelable.Creator<TimeInfosElement> CREATOR = new Parcelable.Creator<TimeInfosElement>() {
        /* renamed from: a */
        public TimeInfosElement[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public TimeInfosElement createFromParcel(Parcel parcel) {
            return new TimeInfosElement(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    int f4527a;
    float b;
    float c;
    int d;
    private List<TMC> e = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public int getPathindex() {
        return this.f4527a;
    }

    public void setPathindex(int i) {
        this.f4527a = i;
    }

    public float getDuration() {
        return this.b;
    }

    public void setDuration(float f) {
        this.b = f;
    }

    public float getTolls() {
        return this.c;
    }

    public void setTolls(float f) {
        this.c = f;
    }

    public int getRestriction() {
        return this.d;
    }

    public void setRestriction(int i) {
        this.d = i;
    }

    public void setTMCs(List<TMC> list) {
        this.e = list;
    }

    public List<TMC> getTMCs() {
        return this.e;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4527a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeInt(this.d);
        parcel.writeTypedList(this.e);
    }

    public TimeInfosElement(Parcel parcel) {
        this.f4527a = parcel.readInt();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.d = parcel.readInt();
        this.e = parcel.createTypedArrayList(TMC.CREATOR);
    }

    public TimeInfosElement() {
    }
}
