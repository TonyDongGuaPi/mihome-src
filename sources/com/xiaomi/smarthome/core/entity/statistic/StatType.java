package com.xiaomi.smarthome.core.entity.statistic;

import android.os.Parcel;
import android.os.Parcelable;

public enum StatType implements Parcelable {
    TIME("time"),
    EVENT("event"),
    SHOP("shop"),
    DEVICE_EDIT("devicelistedit"),
    PLUGIN("Plugin"),
    GENERAL("General"),
    DEVICE_LIST("DeviceList"),
    SCENE("Scene"),
    LABEL("Label"),
    ADD_DEVICE("AddDevice"),
    NOTIFICATION("Notification"),
    DEVICE_SHARE("DeviceShared"),
    UPDATE("Update"),
    EMPTY_DEVICE_ADV("EmptyDevAdv"),
    ACCOUNT("Account"),
    MI_BRAIN("MiBrain"),
    MICRO("Micro"),
    ADS("ads"),
    YOUPIN("youpin"),
    MIUI_SPLASH("MiuiSplash");
    
    public static final Parcelable.Creator<StatType> CREATOR = null;
    /* access modifiers changed from: private */
    public String mValue;

    public int describeContents() {
        return 0;
    }

    static {
        CREATOR = new Parcelable.Creator<StatType>() {
            /* renamed from: a */
            public StatType createFromParcel(Parcel parcel) {
                StatType statType = StatType.values()[parcel.readInt()];
                String unused = statType.mValue = parcel.readString();
                return statType;
            }

            /* renamed from: a */
            public StatType[] newArray(int i) {
                return new StatType[i];
            }
        };
    }

    private StatType(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
        parcel.writeString(this.mValue);
    }
}
