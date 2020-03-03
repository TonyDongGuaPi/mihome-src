package com.xiaomi.smarthome.device.api.spec.definitions.data;

import android.os.Parcelable;

public interface ConstraintValue extends Parcelable {
    boolean validate(DataValue dataValue);
}
