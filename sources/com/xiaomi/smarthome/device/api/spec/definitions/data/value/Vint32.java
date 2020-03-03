package com.xiaomi.smarthome.device.api.spec.definitions.data.value;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import java.util.Objects;

public class Vint32 extends DataValue implements Parcelable {
    public static final Parcelable.Creator<Vint32> CREATOR = new Parcelable.Creator<Vint32>() {
        public Vint32 createFromParcel(Parcel parcel) {
            return new Vint32(parcel);
        }

        public Vint32[] newArray(int i) {
            return new Vint32[i];
        }
    };
    private int value;

    public int describeContents() {
        return 0;
    }

    public Vint32() {
        this.value = 0;
    }

    public Vint32(int i) {
        this.value = i;
    }

    protected Vint32(Parcel parcel) {
        this.value = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.value);
    }

    public int getValue() {
        return this.value;
    }

    public boolean lessEquals(DataValue dataValue) {
        if (dataValue.getClass() == getClass() && this.value <= ((Vint32) dataValue).value) {
            return true;
        }
        return false;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass()) {
            return false;
        }
        Vint32 vint32 = (Vint32) dataValue2;
        if (this.value < ((Vint32) dataValue).value || this.value > vint32.value) {
            return false;
        }
        return true;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2, DataValue dataValue3) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass() || dataValue3.getClass() != getClass()) {
            return false;
        }
        int i = ((Vint32) dataValue).value;
        int i2 = ((Vint32) dataValue2).value;
        int i3 = ((Vint32) dataValue3).value;
        if (this.value < i || this.value > i2 || i3 <= 0 || (this.value - i) % i3 != 0) {
            return false;
        }
        return true;
    }

    public Object getObjectValue() {
        return Integer.valueOf(this.value);
    }

    public static Vint32 valueOf(Object obj) {
        if (obj instanceof Integer) {
            return new Vint32(((Integer) obj).intValue());
        }
        if (!(obj instanceof String)) {
            return null;
        }
        try {
            return new Vint32(Integer.valueOf((String) obj).intValue());
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && this.value == ((Vint32) obj).value) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.value)});
    }
}
