package com.facebook.react.bridge;

import android.support.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;

public class DynamicFromObject implements Dynamic {
    @Nullable
    private Object mObject;

    public void recycle() {
    }

    public DynamicFromObject(@Nullable Object obj) {
        this.mObject = obj;
    }

    public boolean isNull() {
        return this.mObject == null;
    }

    public boolean asBoolean() {
        return ((Boolean) this.mObject).booleanValue();
    }

    public double asDouble() {
        return ((Double) this.mObject).doubleValue();
    }

    public int asInt() {
        return ((Double) this.mObject).intValue();
    }

    public String asString() {
        return (String) this.mObject;
    }

    public ReadableArray asArray() {
        return (ReadableArray) this.mObject;
    }

    public ReadableMap asMap() {
        return (ReadableMap) this.mObject;
    }

    public ReadableType getType() {
        if (isNull()) {
            return ReadableType.Null;
        }
        if (this.mObject instanceof Boolean) {
            return ReadableType.Boolean;
        }
        if (this.mObject instanceof Number) {
            return ReadableType.Number;
        }
        if (this.mObject instanceof String) {
            return ReadableType.String;
        }
        if (this.mObject instanceof ReadableMap) {
            return ReadableType.Map;
        }
        if (this.mObject instanceof ReadableArray) {
            return ReadableType.Array;
        }
        FLog.e(ReactConstants.TAG, "Unmapped object type " + this.mObject.getClass().getName());
        return ReadableType.Null;
    }
}
