package com.xiaomi.smarthome.device.api.spec.instance;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.device.api.spec.operation.controller.ActionController;
import com.xiaomi.smarthome.device.api.spec.operation.controller.DeviceController;
import com.xiaomi.smarthome.device.api.spec.operation.controller.PropertyController;
import com.xiaomi.smarthome.device.api.spec.operation.controller.ServiceController;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpecDevice implements Parcelable {
    public static final Parcelable.Creator<SpecDevice> CREATOR = new Parcelable.Creator<SpecDevice>() {
        public SpecDevice createFromParcel(Parcel parcel) {
            return new SpecDevice(parcel);
        }

        public SpecDevice[] newArray(int i) {
            return new SpecDevice[i];
        }
    };
    public static final String TAG = "SpecDevice";
    private String description;
    private String name;
    private Map<Integer, SpecService> services;
    private String type;

    public int describeContents() {
        return 0;
    }

    public SpecDevice(String str, String str2) {
        this.type = str;
        this.description = str2;
        this.services = new LinkedHashMap(0, 0.75f, false);
    }

    public SpecDevice(String str, String str2, Map<Integer, SpecService> map) {
        this.type = str;
        this.description = str2;
        this.services = map;
    }

    protected SpecDevice(Parcel parcel) {
        this.type = parcel.readString();
        this.description = parcel.readString();
        LinkedHashMap linkedHashMap = new LinkedHashMap(0, 0.75f, false);
        parcel.readMap(linkedHashMap, SpecDevice.class.getClassLoader());
        this.services = linkedHashMap;
        this.name = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(this.description);
        parcel.writeMap(this.services);
        parcel.writeString(this.name);
    }

    public String getType() {
        return this.type;
    }

    public String getTypeName() {
        if (this.name == null) {
            try {
                this.name = this.type.split(":")[3];
            } catch (Exception e) {
                Log.e(TAG, "getTypeName", e);
            }
        }
        return this.name;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public Map<Integer, SpecService> getServices() {
        return this.services;
    }

    public void setServices(Map<Integer, SpecService> map) {
        this.services = map;
    }

    public DeviceController createController(String str) {
        if (this.services == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Integer, SpecService> value : this.services.entrySet()) {
            ArrayList arrayList2 = new ArrayList();
            SpecService specService = (SpecService) value.getValue();
            Map<Integer, SpecProperty> properties = specService.getProperties();
            if (properties != null) {
                for (Map.Entry<Integer, SpecProperty> value2 : properties.entrySet()) {
                    SpecProperty specProperty = (SpecProperty) value2.getValue();
                    arrayList2.add(new PropertyController(specProperty.getIid(), specProperty.getPropertyDefinition()));
                }
            }
            ArrayList arrayList3 = new ArrayList();
            Map<Integer, SpecAction> actions = specService.getActions();
            if (actions != null) {
                for (Map.Entry<Integer, SpecAction> value3 : actions.entrySet()) {
                    SpecAction specAction = (SpecAction) value3.getValue();
                    arrayList3.add(new ActionController(specAction.getIid(), specAction.getActionDefinition()));
                }
            }
            arrayList.add(new ServiceController(specService.getIid(), specService.getType(), specService.getDesc(), arrayList2, arrayList3));
        }
        return new DeviceController(str, this.type, this.description, arrayList);
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder();
        sb.append("SpecDevice{type='");
        sb.append(this.type);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", services=");
        if (this.services == null) {
            obj = "null";
        } else {
            obj = Integer.valueOf(this.services.size());
        }
        sb.append(obj);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }
}
