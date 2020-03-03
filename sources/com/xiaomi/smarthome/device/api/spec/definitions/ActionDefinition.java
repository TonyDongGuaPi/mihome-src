package com.xiaomi.smarthome.device.api.spec.definitions;

import android.os.Parcel;
import android.os.Parcelable;
import com.xiaomi.smarthome.newui.card.spec.instance.ActionDefinitionCodec;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

public class ActionDefinition extends SpecDefinition implements Parcelable {
    public static final Parcelable.Creator<ActionDefinition> CREATOR = new Parcelable.Creator<ActionDefinition>() {
        public ActionDefinition createFromParcel(Parcel parcel) {
            return new ActionDefinition(parcel);
        }

        public ActionDefinition[] newArray(int i) {
            return new ActionDefinition[i];
        }
    };

    /* renamed from: in  reason: collision with root package name */
    private JSONArray f15011in = new JSONArray();
    private JSONArray out = new JSONArray();

    public int describeContents() {
        return 0;
    }

    public ActionDefinition() {
    }

    public ActionDefinition(String str, String str2) {
        this.type = str;
        this.description = str2;
    }

    public ActionDefinition(String str, String str2, List<Object> list, List<Object> list2) {
        this.type = str;
        this.description = str2;
        setIn(list);
        setOut(list2);
    }

    public ActionDefinition(String str, String str2, JSONArray jSONArray, JSONArray jSONArray2) {
        this.type = str;
        this.description = str2;
        this.f15011in = jSONArray;
        this.out = jSONArray2;
    }

    protected ActionDefinition(Parcel parcel) {
        String readString = parcel.readString();
        String readString2 = parcel.readString();
        if (readString != null) {
            try {
                this.f15011in = new JSONArray(readString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (readString2 != null) {
            try {
                this.out = new JSONArray(readString2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        this.name = parcel.readString();
        this.type = parcel.readString();
        this.description = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        String str = null;
        parcel.writeString(this.f15011in == null ? null : this.f15011in.toString());
        if (this.out != null) {
            str = this.out.toString();
        }
        parcel.writeString(str);
        parcel.writeString(this.name);
        parcel.writeString(this.type);
        parcel.writeString(this.description);
    }

    public List<Object> getIn() {
        return ActionDefinitionCodec.a(this.f15011in);
    }

    public void setIn(List<Object> list) {
        if (list != null) {
            this.f15011in = new JSONArray(list);
        }
    }

    public List<Object> getOut() {
        return ActionDefinitionCodec.a(this.out);
    }

    public void setOut(List<Object> list) {
        if (list != null) {
            this.out = new JSONArray(list);
        }
    }
}
