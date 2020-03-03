package com.xiaomi.smarthome.device.api.spec.definitions.data;

import android.os.Parcel;
import android.os.Parcelable;

public class ValueDefinition implements Parcelable {
    public static final Parcelable.Creator<ValueDefinition> CREATOR = new Parcelable.Creator<ValueDefinition>() {
        public ValueDefinition createFromParcel(Parcel parcel) {
            return new ValueDefinition(parcel);
        }

        public ValueDefinition[] newArray(int i) {
            return new ValueDefinition[i];
        }
    };
    private String description;
    private DataValue value;

    public int describeContents() {
        return 0;
    }

    public ValueDefinition() {
    }

    public ValueDefinition(DataValue dataValue, String str) {
        this.value = dataValue;
        this.description = str;
    }

    public ValueDefinition(DataFormat dataFormat, Object obj, String str) {
        this.description = str;
        this.value = dataFormat.createValue(obj);
        if (this.value == null) {
            throw new IllegalArgumentException("invalid value: " + obj + " type: " + obj.getClass().getSimpleName() + " description: " + str);
        }
    }

    protected ValueDefinition(Parcel parcel) {
        this.value = (DataValue) parcel.readParcelable(DataValue.class.getClassLoader());
        this.description = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.value, i);
        parcel.writeString(this.description);
    }

    public DataValue value() {
        return this.value;
    }

    public void setValue(DataValue dataValue) {
        this.value = dataValue;
    }

    public String description() {
        return this.description;
    }

    public void description(String str) {
        this.description = str;
    }
}
