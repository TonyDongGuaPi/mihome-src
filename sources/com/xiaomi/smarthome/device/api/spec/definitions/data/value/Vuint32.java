package com.xiaomi.smarthome.device.api.spec.definitions.data.value;

import android.os.Parcel;
import android.os.Parcelable;
import cn.com.fmsh.communication.core.MessageHead;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import java.util.Objects;

public class Vuint32 extends DataValue implements Parcelable {
    public static final Parcelable.Creator<Vuint32> CREATOR = new Parcelable.Creator<Vuint32>() {
        public Vuint32 createFromParcel(Parcel parcel) {
            return new Vuint32(parcel);
        }

        public Vuint32[] newArray(int i) {
            return new Vuint32[i];
        }
    };
    private long value;

    public int describeContents() {
        return 0;
    }

    public Vuint32() {
        this.value = 0;
    }

    public Vuint32(long j) {
        this.value = j;
    }

    protected Vuint32(Parcel parcel) {
        this.value = parcel.readLong();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.value);
    }

    public long getValue() {
        return this.value;
    }

    public boolean lessEquals(DataValue dataValue) {
        if (dataValue.getClass() == getClass() && this.value <= ((Vuint32) dataValue).value) {
            return true;
        }
        return false;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass()) {
            return false;
        }
        Vuint32 vuint32 = (Vuint32) dataValue2;
        if (this.value < ((Vuint32) dataValue).value || this.value > vuint32.value) {
            return false;
        }
        return true;
    }

    public boolean validate(DataValue dataValue, DataValue dataValue2, DataValue dataValue3) {
        if (dataValue.getClass() != getClass() || dataValue2.getClass() != getClass() || dataValue3.getClass() != getClass()) {
            return false;
        }
        long j = ((Vuint32) dataValue).value;
        long j2 = ((Vuint32) dataValue2).value;
        long j3 = ((Vuint32) dataValue3).value;
        if (this.value < j || this.value > j2 || j3 <= 0 || (this.value - j) % j3 != 0) {
            return false;
        }
        return true;
    }

    public Object getObjectValue() {
        return Long.valueOf(this.value);
    }

    public static Vuint32 valueOf(Object obj) {
        if (obj instanceof Long) {
            Long l = (Long) obj;
            if (l.longValue() < 0 || l.longValue() > MessageHead.SERIAL_MAK) {
                return null;
            }
            return new Vuint32(l.longValue());
        } else if (obj instanceof Integer) {
            Integer num = (Integer) obj;
            if (num.intValue() < 0) {
                return null;
            }
            return new Vuint32((long) num.intValue());
        } else if (!(obj instanceof String)) {
            return null;
        } else {
            try {
                Long valueOf = Long.valueOf((String) obj);
                if (valueOf.longValue() >= 0) {
                    if (valueOf.longValue() <= MessageHead.SERIAL_MAK) {
                        return new Vuint32(valueOf.longValue());
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
        if (obj != null && getClass() == obj.getClass() && this.value == ((Vuint32) obj).value) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.value)});
    }
}
