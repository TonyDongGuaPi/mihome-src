package com.xiaomi.smarthome.device.api.spec.instance;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.EventDefinition;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;

public class SpecEvent extends Spec.SpecItem implements Parcelable {
    public static final Parcelable.Creator<SpecEvent> CREATOR = new Parcelable.Creator<SpecEvent>() {
        public SpecEvent createFromParcel(Parcel parcel) {
            return new SpecEvent(parcel);
        }

        public SpecEvent[] newArray(int i) {
            return new SpecEvent[i];
        }
    };
    private EventDefinition eventDefinition;

    public int describeContents() {
        return 0;
    }

    public SpecEvent(int i, EventDefinition eventDefinition2) {
        this.iid = i;
        this.eventDefinition = eventDefinition2;
    }

    public EventDefinition getEventDefinition() {
        return this.eventDefinition;
    }

    public void seEventDefinition(EventDefinition eventDefinition2) {
        this.eventDefinition = eventDefinition2;
    }

    public EventDefinition getDefinition() {
        return this.eventDefinition;
    }

    protected SpecEvent(Parcel parcel) {
        this.eventDefinition = (EventDefinition) parcel.readParcelable(EventDefinition.class.getClassLoader());
        this.iid = parcel.readInt();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.eventDefinition, i);
        parcel.writeInt(this.iid);
    }
}
