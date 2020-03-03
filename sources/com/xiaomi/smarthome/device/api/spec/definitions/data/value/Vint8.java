package com.xiaomi.smarthome.device.api.spec.definitions.data.value;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import java.util.Objects;

public class Vint8 extends DataValue implements Parcelable {
    public static final Parcelable.Creator<Vint8> CREATOR = new Parcelable.Creator<Vint8>() {
        public Vint8 createFromParcel(Parcel parcel) {
            return new Vint8(parcel);
        }

        public Vint8[] newArray(int i) {
            return new Vint8[i];
        }
    };
    private int value;

    public int describeContents() {
        return 0;
    }

    public Vint8() {
        this.value = 0;
    }

    public Vint8(int i) {
        this.value = i;
    }

    protected Vint8(Parcel parcel) {
        this.value = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.value);
    }

    public int getValue() {
        return this.value;
    }

    public boolean lessEquals(DataValue dataValue) {
        if (dataValue.getClass() == getClass() && this.value <= ((Vint8) dataValue).value) {
            return true;
        }
        return false;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass()) {
            return false;
        }
        Vint8 vint8 = (Vint8) dataValue2;
        if (this.value < ((Vint8) dataValue).value || this.value > vint8.value) {
            return false;
        }
        return true;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2, DataValue dataValue3) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass() || dataValue3.getClass() != getClass()) {
            return false;
        }
        int i = ((Vint8) dataValue).value;
        int i2 = ((Vint8) dataValue2).value;
        int i3 = ((Vint8) dataValue3).value;
        if (this.value < i || this.value > i2 || i3 <= 0 || (this.value - i) % i3 != 0) {
            return false;
        }
        return true;
    }

    public Object getObjectValue() {
        return Integer.valueOf(this.value);
    }

    public static Vint8 valueOf(Object obj) {
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() > 127 || num.intValue() < -128) {
                return null;
            }
            return new Vint8(num.intValue());
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                return new Vint8((int) Byte.valueOf((String) obj).byteValue());
            } catch (NumberFormatException unused) {
                return null;
            }
        }
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass() && this.value == ((Vint8) obj).value) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.value)});
    }
}
