package com.xiaomi.smarthome.device.api.spec.definitions;

import android.os.Parcelable;
import android.util.Log;
import com.xiaomi.smarthome.device.api.spec.instance.SpecDevice;

public abstract class SpecDefinition implements Parcelable {
    protected String description;
    protected String name;
    protected String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getTypeName() {
        if (this.name == null) {
            try {
                this.name = this.type.split(":")[3];
            } catch (Exception e) {
                Log.e(SpecDevice.TAG, "getTypeName", e);
            }
        }
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }
}
