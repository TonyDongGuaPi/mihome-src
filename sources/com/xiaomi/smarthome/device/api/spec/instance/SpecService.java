package com.xiaomi.smarthome.device.api.spec.instance;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.LinkedHashMap;
import java.util.Map;

public class SpecService extends Spec implements Parcelable {
    public static final Parcelable.Creator<SpecService> CREATOR = new Parcelable.Creator<SpecService>() {
        public SpecService createFromParcel(Parcel parcel) {
            return new SpecService(parcel);
        }

        public SpecService[] newArray(int i) {
            return new SpecService[i];
        }
    };
    private Map<Integer, SpecAction> actions;
    private String description;
    private Map<Integer, SpecEvent> events;
    private String name;
    private Map<Integer, SpecProperty> properties;
    private String type;

    public int describeContents() {
        return 0;
    }

    public SpecService(int i, String str, String str2) {
        this.iid = i;
        this.type = str;
        this.description = str2;
        this.properties = new LinkedHashMap(0, 0.75f, false);
        this.actions = new LinkedHashMap(0, 0.75f, false);
    }

    public SpecService(int i, String str, String str2, Map<Integer, SpecProperty> map, Map<Integer, SpecAction> map2, Map<Integer, SpecEvent> map3) {
        this.iid = i;
        this.type = str;
        this.description = str2;
        this.properties = map;
        this.actions = map2;
    }

    protected SpecService(Parcel parcel) {
        this.iid = parcel.readInt();
        this.type = parcel.readString();
        this.description = parcel.readString();
        ClassLoader classLoader = SpecService.class.getClassLoader();
        LinkedHashMap linkedHashMap = new LinkedHashMap(0, 0.75f, false);
        parcel.readMap(linkedHashMap, classLoader);
        this.properties = linkedHashMap;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(0, 0.75f, false);
        parcel.readMap(linkedHashMap2, classLoader);
        this.actions = linkedHashMap2;
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(0, 0.75f, false);
        parcel.readMap(linkedHashMap3, classLoader);
        this.events = linkedHashMap3;
        this.name = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.iid);
        parcel.writeString(this.type);
        parcel.writeString(this.description);
        parcel.writeMap(this.properties);
        parcel.writeMap(this.actions);
        parcel.writeMap(this.events);
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
                Log.e(SpecDevice.TAG, "getTypeName", e);
            }
        }
        return this.name;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getDesc() {
        return this.description;
    }

    public void setDesc(String str) {
        this.description = str;
    }

    public Map<Integer, SpecProperty> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<Integer, SpecProperty> map) {
        this.properties = map;
    }

    public Map<Integer, SpecAction> getActions() {
        return this.actions;
    }

    public void setActions(Map<Integer, SpecAction> map) {
        this.actions = map;
    }

    public Map<Integer, SpecEvent> getEvents() {
        return this.events;
    }

    public void setEvents(Map<Integer, SpecEvent> map) {
        this.events = map;
    }
}
