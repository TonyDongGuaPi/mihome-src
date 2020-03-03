package com.xiaomi.smarthome.device.api.spec.operation.controller;

import android.content.Context;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.api.spec.definitions.PropertyDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.data.DataValue;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyListener;
import com.xiaomi.smarthome.device.api.spec.operation.PropertyParam;

public class PropertyController extends SpecProperty {
    private PropertyListener mListener;

    public PropertyController(int i, PropertyDefinition propertyDefinition) {
        super(i, propertyDefinition);
    }

    public void updateValue(PropertyParam propertyParam, boolean z) {
        if (propertyParam.getResultCode() == 0 && setValue(propertyParam.getValue()) && z && this.mListener != null) {
            this.mListener.onDataChanged(propertyParam.getValue());
        }
    }

    public void setSpecProperty(Context context, PropertyParam propertyParam, final Callback<Object> callback) {
        if (!validateParam(propertyParam.getValue()) && callback != null) {
            callback.onFailure(-1, "set value wrong type");
        }
        XmPluginHostApi.instance().setPropertyValue(context, propertyParam, new Callback<PropertyParam>() {
            public void onSuccess(PropertyParam propertyParam) {
                if (PropertyController.this.setValue(propertyParam.getValue())) {
                    if (callback != null) {
                        callback.onSuccess(propertyParam.getValue());
                    }
                } else if (callback != null) {
                    callback.onFailure(-1, "set value failed");
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    private boolean validateParam(Object obj) {
        DataValue createValue = getPropertyDefinition().getFormat().createValue(obj);
        if (createValue != null && getPropertyDefinition().validate(createValue)) {
            return true;
        }
        return false;
    }

    public PropertyParam getParamObj(String str, int i, int i2, Object obj) {
        return new PropertyParam(str, i, i2, obj);
    }

    public void setListener(PropertyListener propertyListener) {
        this.mListener = propertyListener;
    }

    public void removeListener() {
        this.mListener = null;
    }
}
