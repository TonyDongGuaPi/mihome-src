package com.xiaomi.smarthome.device.api.spec.definitions;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.data.Access;
import com.xiaomi.smarthome.device.api.spec.definitions.data.ConstraintValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataFormat;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import com.xiaomi.smarthome.device.api.spec.definitions.data.Unit;

public class PropertyDefinition extends SpecDefinition implements Parcelable {
    public static final Parcelable.Creator<PropertyDefinition> CREATOR = new Parcelable.Creator<PropertyDefinition>() {
        public PropertyDefinition createFromParcel(Parcel parcel) {
            return new PropertyDefinition(parcel);
        }

        public PropertyDefinition[] newArray(int i) {
            return new PropertyDefinition[i];
        }
    };
    private Access access = new Access();
    private ConstraintValue constraintValue;
    private DataFormat format = DataFormat.UNKNOWN;
    private String unit;

    public int describeContents() {
        return 0;
    }

    public PropertyDefinition() {
    }

    public PropertyDefinition(String str, String str2, DataFormat dataFormat, ConstraintValue constraintValue2) {
        this.type = str;
        this.description = str2;
        this.format = dataFormat;
        this.constraintValue = constraintValue2;
    }

    @Deprecated
    public PropertyDefinition(String str, String str2, Access access2, DataFormat dataFormat, ConstraintValue constraintValue2, Unit unit2) {
        this.type = str;
        this.description = str2;
        this.access = access2;
        this.format = dataFormat;
        this.unit = unit2.toString();
        this.constraintValue = constraintValue2;
    }

    public PropertyDefinition(String str, String str2, Access access2, DataFormat dataFormat, ConstraintValue constraintValue2, String str3) {
        this.type = str;
        this.description = str2;
        this.access = access2;
        this.format = dataFormat;
        this.unit = str3;
        this.constraintValue = constraintValue2;
    }

    protected PropertyDefinition(Parcel parcel) {
        this.format = DataFormat.from(parcel.readString());
        this.constraintValue = (ConstraintValue) parcel.readParcelable(ConstraintValue.class.getClassLoader());
        this.access = (Access) parcel.readParcelable(Access.class.getClassLoader());
        this.unit = parcel.readString();
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.description = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.format.toString());
        parcel.writeParcelable(this.constraintValue, i);
        parcel.writeParcelable(this.access, i);
        parcel.writeString(this.unit);
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.description);
    }

    public Access access() {
        return this.access;
    }

    public boolean readable() {
        return this.access.isReadable();
    }

    public void readable(boolean z) {
        this.access.setReadable(z);
    }

    public boolean writable() {
        return this.access.isWritable();
    }

    public void writable(boolean z) {
        this.access.setWritable(z);
    }

    public boolean notifiable() {
        return this.access.isNotifiable();
    }

    public void notifiable(boolean z) {
        this.access.setNotifiable(z);
    }

    public Unit unit() {
        return Unit.from(this.unit);
    }

    public String getUnit() {
        return this.unit;
    }

    @Deprecated
    public void unit(Unit unit2) {
        this.unit = unit2.toString();
    }

    public DataFormat getFormat() {
        return this.format;
    }

    public void setFormat(DataFormat dataFormat) {
        this.format = dataFormat;
    }

    public ConstraintValue getConstraintValue() {
        return this.constraintValue;
    }

    public void setConstraintValue(ConstraintValue constraintValue2) {
        this.constraintValue = constraintValue2;
    }

    public boolean validate(DataValue dataValue) {
        if (dataValue == null || !this.format.getJavaClass().isInstance(dataValue)) {
            return false;
        }
        if (this.constraintValue == null || this.constraintValue.validate(dataValue)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return 31 + (this.type == null ? 0 : this.type.hashCode());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PropertyDefinition propertyDefinition = (PropertyDefinition) obj;
        if (this.type == null) {
            if (propertyDefinition.type != null) {
                return false;
            }
        } else if (!this.type.equals(propertyDefinition.type)) {
            return false;
        }
        return true;
    }
}
