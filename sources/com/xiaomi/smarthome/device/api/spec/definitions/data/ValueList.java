package com.xiaomi.smarthome.device.api.spec.definitions.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class ValueList implements Parcelable, ConstraintValue {
    public static final Parcelable.Creator<ValueList> CREATOR = new Parcelable.Creator<ValueList>() {
        public ValueList createFromParcel(Parcel parcel) {
            return new ValueList(parcel);
        }

        public ValueList[] newArray(int i) {
            return new ValueList[i];
        }
    };
    private List<ValueDefinition> values;

    public int describeContents() {
        return 0;
    }

    public ValueList(List<ValueDefinition> list) {
        this.values = list;
    }

    protected ValueList(Parcel parcel) {
        this.values = parcel.createTypedArrayList(ValueDefinition.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(this.values);
    }

    public boolean validate(DataValue dataValue) {
        for (ValueDefinition value : this.values) {
            if (value.value().equals(dataValue)) {
                return true;
            }
        }
        return false;
    }

    public List<ValueDefinition> values() {
        return this.values;
    }
}
