package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.mibi.common.data.Connection;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxMessagePayTask;
import org.json.JSONException;
import org.json.JSONObject;

public class RxWoUnicomMSGPayTask extends RxMessagePayTask<Result> {
    public RxWoUnicomMSGPayTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        Connection b = super.b(sortedParameter);
        SortedParameter d = b.d();
        d.a("imei", sortedParameter.a("imei"));
        d.a("mac", sortedParameter.a("mac"));
        return b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void c(JSONObject jSONObject, Result result) throws PaymentException {
        super.c(jSONObject, result);
        try {
            result.f12440a = result.d.getString("appid");
            result.b = result.d.getString(MibiConstants.fe);
            result.e = result.d.getString("channel_id");
            result.f = result.d.getString(MibiConstants.fg);
            result.g = result.d.getString(MibiConstants.fh);
            result.h = result.d.getString(MibiConstants.fi);
            result.i = result.d.getString("props");
            result.j = result.d.getString("money");
            result.k = result.d.getString("order_id");
            if (!Utils.a(result.f12440a, result.b, result.e, result.g, result.h, result.i, result.j, result.k)) {
                throw new ResultException("result has error");
            }
        } catch (JSONException e) {
            throw new ResultException((Throwable) e);
        }
    }

    public static class Result extends RxMessagePayTask.Result implements Parcelable {
        public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
            /* renamed from: a */
            public Result createFromParcel(Parcel parcel) {
                Result result = new Result();
                result.f12440a = parcel.readString();
                result.b = parcel.readString();
                result.e = parcel.readString();
                result.f = parcel.readString();
                result.g = parcel.readString();
                result.h = parcel.readString();
                result.i = parcel.readString();
                result.j = parcel.readString();
                result.k = parcel.readString();
                return result;
            }

            /* renamed from: a */
            public Result[] newArray(int i) {
                return new Result[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public String f12440a;
        public String b;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i2) {
            parcel.writeString(this.f12440a);
            parcel.writeString(this.b);
            parcel.writeString(this.e);
            parcel.writeString(this.f);
            parcel.writeString(this.g);
            parcel.writeString(this.h);
            parcel.writeString(this.i);
            parcel.writeString(this.j);
            parcel.writeString(this.k);
        }
    }
}
