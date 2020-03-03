package com.xiaomi.smarthome.device.api.spec.instance;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.SpecDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;
import com.xiaomi.smarthome.device.api.spec.operation.controller.PropertyController;

public class SpecProperty extends Spec.SpecItem implements Parcelable {
    public static final Parcelable.Creator<SpecProperty> CREATOR = new Parcelable.Creator<SpecProperty>() {
        public SpecProperty createFromParcel(Parcel parcel) {
            return new SpecProperty(parcel);
        }

        public SpecProperty[] newArray(int i) {
            return new SpecProperty[i];
        }
    };
    private PropertyDefinition propertyDefinition;
    private DataValue value;

    public int describeContents() {
        return 0;
    }

    public SpecProperty(int i, PropertyDefinition propertyDefinition2) {
        this.iid = i;
        this.propertyDefinition = propertyDefinition2;
    }

    protected SpecProperty(Parcel parcel) {
        this.iid = parcel.readInt();
        this.propertyDefinition = (PropertyDefinition) parcel.readParcelable(PropertyDefinition.class.getClassLoader());
        this.value = (DataValue) parcel.readParcelable(DataValue.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.iid);
        parcel.writeParcelable(this.propertyDefinition, i);
        parcel.writeParcelable(this.value, i);
    }

    public PropertyDefinition getPropertyDefinition() {
        return this.propertyDefinition;
    }

    public void setPropertyDefinition(PropertyDefinition propertyDefinition2) {
        this.propertyDefinition = propertyDefinition2;
    }

    public SpecDefinition getDefinition() {
        return this.propertyDefinition;
    }

    public Object getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.getObjectValue();
    }

    public boolean setValue(Object obj) {
        DataValue createValue = this.propertyDefinition.getFormat().createValue(obj);
        if (createValue == null || !this.propertyDefinition.validate(createValue)) {
            return false;
        }
        this.value = createValue;
        return true;
    }

    public PropertyController createController() {
        return new PropertyController(this.iid, this.propertyDefinition);
    }
}
