package com.xiaomi.smarthome.device.api.spec.definitions.data.value;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import java.util.Objects;

public class Vuint8 extends DataValue implements Parcelable {
    public static final Parcelable.Creator<Vuint8> CREATOR = new Parcelable.Creator<Vuint8>() {
        public Vuint8 createFromParcel(Parcel parcel) {
            return new Vuint8(parcel);
        }

        public Vuint8[] newArray(int i) {
            return new Vuint8[i];
        }
    };
    private int value;

    public int describeContents() {
        return 0;
    }

    public Vuint8() {
        this.value = 0;
    }

    public Vuint8(int i) {
        this.value = i;
    }

    protected Vuint8(Parcel parcel) {
        this.value = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.value);
    }

    public int getValue() {
        return this.value;
    }

    public boolean lessEquals(DataValue dataValue) {
        if (dataValue.getClass() == getClass() && this.value <= ((Vuint8) dataValue).value) {
            return true;
        }
        return false;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass()) {
            return false;
        }
        Vuint8 vuint8 = (Vuint8) dataValue2;
        if (this.value < ((Vuint8) dataValue).value || this.value > vuint8.value) {
            return false;
        }
        return true;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2, DataValue dataValue3) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass() || dataValue3.getClass() != getClass()) {
            return false;
        }
        int i = ((Vuint8) dataValue).value;
        int i2 = ((Vuint8) dataValue2).value;
        int i3 = ((Vuint8) dataValue3).value;
        if (this.value < i || this.value > i2 || i3 <= 0 || (this.value - i) % i3 != 0) {
            return false;
        }
        return true;
    }

    public Object getObjectValue() {
        return Integer.valueOf(this.value);
    }

    public static Vuint8 valueOf(Object obj) {
        if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() < 0 || num.intValue() > 255) {
                return null;
            }
            return new Vuint8(num.intValue());
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                int intValue = Integer.valueOf((String) obj).intValue();
                if (intValue >= 0) {
                    if (intValue <= 255) {
                        return new Vuint8(intValue);
                    }
                }
                return null;
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
        if (obj != null && getClass() == obj.getClass() && this.value == ((Vuint8) obj).value) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.value)});
    }
}
