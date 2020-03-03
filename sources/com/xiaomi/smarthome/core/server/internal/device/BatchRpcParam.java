package com.xiaomi.smarthome.core.server.internal.device;

import android.os.Parcel;
import android.os.Parcelable;
import com.adobe.xmp.XMPConst;
import com.taobao.weex.el.parse.Operators;
import org.json.JSONArray;
import org.json.JSONException;

public class BatchRpcParam implements Parcelable {
    public static final Parcelable.Creator<BatchRpcParam> CREATOR = new Parcelable.Creator<BatchRpcParam>() {
        /* renamed from: a */
        public BatchRpcParam createFromParcel(Parcel parcel) {
            return new BatchRpcParam(parcel);
        }

        /* renamed from: a */
        public BatchRpcParam[] newArray(int i) {
            return new BatchRpcParam[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public String f14497a;
    public String b;
    public JSONArray c;
    public String d;

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "BatchRpcParam{did='" + this.f14497a + Operators.SINGLE_QUOTE + ", method='" + this.b + Operators.SINGLE_QUOTE + ", params=" + this.c + ", sid='" + this.d + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }

    public BatchRpcParam() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f14497a);
        parcel.writeString(this.b);
        parcel.writeString(this.c == null ? XMPConst.ai : this.c.toString());
        parcel.writeString(this.d);
    }

    protected BatchRpcParam(Parcel parcel) {
        this.f14497a = parcel.readString();
        this.b = parcel.readString();
        try {
            this.c = new JSONArray(parcel.readString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.d = parcel.readString();
    }
}
