package com.amap.api.services.route;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class DrivePlanPath implements Parcelable {
    public static final Parcelable.Creator<DrivePlanPath> CREATOR = new Parcelable.Creator<DrivePlanPath>() {
        /* renamed from: a */
        public DrivePlanPath[] newArray(int i) {
            return null;
        }

        /* renamed from: a */
        public DrivePlanPath createFromParcel(Parcel parcel) {
            return new DrivePlanPath(parcel);
        }
    };

    /* renamed from: a  reason: collision with root package name */
    float f4497a;
    int b;
    private List<DrivePlanStep> c = new ArrayList();

    public int describeContents() {
        return 0;
    }

    public float getDistance() {
        return this.f4497a;
    }

    public void setDistance(float f) {
        this.f4497a = f;
    }

    public float getTrafficLights() {
        return (float) this.b;
    }

    public void setTrafficLights(int i) {
        this.b = i;
    }

    public List<DrivePlanStep> getSteps() {
        return this.c;
    }

    public void setSteps(List<DrivePlanStep> list) {
        this.c = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f4497a);
        parcel.writeInt(this.b);
        parcel.writeTypedList(this.c);
    }

    public DrivePlanPath(Parcel parcel) {
        this.f4497a = parcel.readFloat();
        this.b = parcel.readInt();
        this.c = parcel.createTypedArrayList(DrivePlanStep.CREATOR);
    }

    public DrivePlanPath() {
    }
}
