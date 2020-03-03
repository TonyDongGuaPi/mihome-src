package com.xiaomi.smarthome.device.api.spec.definitions;

import android.os.Parcel;
import android.os.Parcelable;

public class EventDefinition extends SpecDefinition implements Parcelable {
    public static final Parcelable.Creator<EventDefinition> CREATOR = new Parcelable.Creator<EventDefinition>() {
        public EventDefinition createFromParcel(Parcel parcel) {
            return new EventDefinition(parcel);
        }

        public EventDefinition[] newArray(int i) {
            return new EventDefinition[i];
        }
    };
    private long[] arguments;

    public int describeContents() {
        return 0;
    }

    public EventDefinition() {
    }

    public EventDefinition(String str, String str2) {
        this.type = str;
        this.description = str2;
    }

    public EventDefinition(String str, String str2, long[] jArr) {
        this.type = str;
        this.description = str2;
        this.arguments = jArr;
    }

    protected EventDefinition(Parcel parcel) {
        this.arguments = parcel.createLongArray();
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.description = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLongArray(this.arguments);
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.description);
    }

    public long[] getArguments() {
        return this.arguments;
    }

    public void setArguments(long[] jArr) {
        this.arguments = jArr;
    }
}
