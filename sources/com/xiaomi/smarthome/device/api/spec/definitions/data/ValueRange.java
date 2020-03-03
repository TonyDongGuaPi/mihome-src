package com.xiaomi.smarthome.device.api.spec.definitions.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public class ValueRange implements Parcelable, ConstraintValue {
    public static final Parcelable.Creator<ValueRange> CREATOR = new Parcelable.Creator<ValueRange>() {
        public ValueRange createFromParcel(Parcel parcel) {
            return new ValueRange(parcel);
        }

        public ValueRange[] newArray(int i) {
            return new ValueRange[i];
        }
    };
    private DataFormat format;
    private boolean hasStep;
    private DataValue maxValue;
    private DataValue minValue;
    private DataValue stepValue;

    public int describeContents() {
        return 0;
    }

    public ValueRange(DataFormat dataFormat, List<Object> list) {
        if (list.size() == 2) {
            init(dataFormat, list.get(0), list.get(1), (Object) null);
        } else if (list.size() == 3) {
            init(dataFormat, list.get(0), list.get(1), list.get(2));
        } else {
            throw new IllegalArgumentException("value list is null");
        }
    }

    public ValueRange(DataFormat dataFormat, Object obj, Object obj2) {
        init(dataFormat, obj, obj2, (Object) null);
    }

    public ValueRange(DataFormat dataFormat, Object obj, Object obj2, Object obj3) {
        init(dataFormat, obj, obj2, obj3);
    }

    protected ValueRange(Parcel parcel) {
        this.format = DataFormat.from(parcel.readString());
        this.minValue = (DataValue) parcel.readParcelable(DataValue.class.getClassLoader());
        this.maxValue = (DataValue) parcel.readParcelable(DataValue.class.getClassLoader());
        this.stepValue = (DataValue) parcel.readParcelable(DataValue.class.getClassLoader());
        this.hasStep = parcel.readByte() != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.format.toString());
        parcel.writeParcelable(this.minValue, i);
        parcel.writeParcelable(this.maxValue, i);
        parcel.writeParcelable(this.stepValue, i);
        parcel.writeByte(this.hasStep ? (byte) 1 : 0);
    }

    private void init(DataFormat dataFormat, Object obj, Object obj2, Object obj3) {
        this.format = dataFormat;
        this.minValue = dataFormat.createValue(obj);
        this.maxValue = dataFormat.createValue(obj2);
        if (obj3 != null) {
            this.stepValue = dataFormat.createValue(obj3);
            this.hasStep = true;
        } else {
            this.stepValue = null;
            this.hasStep = false;
        }
        if (!dataFormat.check(this.minValue, this.maxValue, this.stepValue)) {
            throw new IllegalArgumentException("check(min, max, step) failed, min: " + obj + " max: " + obj2 + " step:" + obj3);
        }
    }

    public boolean validate(DataValue dataValue) {
        return this.format.validate(dataValue, this.minValue, this.maxValue, this.hasStep ? this.stepValue : null);
    }

    public DataValue minValue() {
        return this.minValue;
    }

    public DataValue maxValue() {
        return this.maxValue;
    }

    public DataValue stepValue() {
        return this.stepValue;
    }

    public List<Object> toList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.minValue.getObjectValue());
        arrayList.add(this.maxValue.getObjectValue());
        if (this.hasStep) {
            arrayList.add(this.stepValue.getObjectValue());
        }
        return arrayList;
    }
}
