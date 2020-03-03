package com.xiaomi.smarthome.device.api.spec.instance;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.device.api.spec.definitions.ActionDefinition;
import com.xiaomi.smarthome.device.api.spec.definitions.SpecDefinition;
import com.xiaomi.smarthome.device.api.spec.instance.Spec;

public class SpecAction extends Spec.SpecItem implements Parcelable {
    public static final Parcelable.Creator<SpecAction> CREATOR = new Parcelable.Creator<SpecAction>() {
        public SpecAction createFromParcel(Parcel parcel) {
            return new SpecAction(parcel);
        }

        public SpecAction[] newArray(int i) {
            return new SpecAction[i];
        }
    };
    private ActionDefinition actionDefinition;

    public int describeContents() {
        return 0;
    }

    public SpecAction(int i, ActionDefinition actionDefinition2) {
        this.iid = i;
        this.actionDefinition = actionDefinition2;
    }

    protected SpecAction(Parcel parcel) {
        this.iid = parcel.readInt();
        this.actionDefinition = (ActionDefinition) parcel.readParcelable(ActionDefinition.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.iid);
        parcel.writeParcelable(this.actionDefinition, i);
    }

    public ActionDefinition getActionDefinition() {
        return this.actionDefinition;
    }

    public void setActionDefinition(ActionDefinition actionDefinition2) {
        this.actionDefinition = actionDefinition2;
    }

    public SpecDefinition getDefinition() {
        return this.actionDefinition;
    }
}
