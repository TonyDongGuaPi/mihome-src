package com.xiaomi.smarthome.core.entity.upnp;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import java.util.ArrayList;
import java.util.Arrays;

public class UPnPRequest implements Parcelable {
    public static final Parcelable.Creator<UPnPRequest> CREATOR = new Parcelable.Creator<UPnPRequest>() {
        /* renamed from: a */
        public UPnPRequest createFromParcel(Parcel parcel) {
            return new UPnPRequest(parcel);
        }

        /* renamed from: a */
        public UPnPRequest[] newArray(int i) {
            return new UPnPRequest[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f14000a;
    public String b;
    public String c;
    public ArrayList<KeyValuePair> d;

    public int describeContents() {
        return 0;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("UPnPRequest{udn='");
        sb.append(this.f14000a);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", serviceId='");
        sb.append(this.b);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", actionName='");
        sb.append(this.c);
        sb.append(Operators.SINGLE_QUOTE);
        sb.append(", params=");
        if (this.d == null) {
            str = null;
        } else {
            str = Arrays.toString(this.d.toArray());
        }
        sb.append(str);
        sb.append(Operators.BLOCK_END);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f14000a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeTypedList(this.d);
    }

    public UPnPRequest() {
    }

    protected UPnPRequest(Parcel parcel) {
        this.f14000a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.createTypedArrayList(KeyValuePair.CREATOR);
    }
}
