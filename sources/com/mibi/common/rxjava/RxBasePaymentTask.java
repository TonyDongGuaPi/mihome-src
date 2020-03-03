package com.mibi.common.rxjava;

import android.content.Context;
import android.util.Log;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.DeviceManager;
import com.mibi.common.data.DomainManager;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.NotConnectedException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.exception.ServerErrorCodeException;
import com.mibi.common.exception.ServiceTokenExpiredException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class RxBasePaymentTask<R> extends RxTask<R> {
    private static final String c = "BasePaymentTask";

    /* renamed from: a  reason: collision with root package name */
    protected Session f7587a;
    protected Context b;
    private SortedParameter d;
    private boolean e;
    private boolean f;

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, R r) throws PaymentException {
    }

    /* access modifiers changed from: protected */
    public abstract Connection b(SortedParameter sortedParameter);

    /* access modifiers changed from: protected */
    public boolean b(JSONObject jSONObject, R r) throws PaymentException {
        return false;
    }

    /* access modifiers changed from: protected */
    public void c(JSONObject jSONObject, R r) throws PaymentException {
    }

    public RxBasePaymentTask(Context context, Session session, Class<R> cls) {
        super(cls);
        this.b = context;
        this.f7587a = session;
    }

    public void a(SortedParameter sortedParameter) {
        if (sortedParameter == null) {
            sortedParameter = new SortedParameter();
        }
        this.d = sortedParameter;
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.f = z;
    }

    public boolean a() {
        return this.f;
    }

    /* access modifiers changed from: protected */
    public void a(R r) throws PaymentException {
        if (Utils.a(this.b) || this.f) {
            this.f7587a.a(this.b);
            try {
                a(this.d, r);
            } catch (ServiceTokenExpiredException e2) {
                Log.e(c, "service token expired, re-login exception ", e2);
                this.f7587a.b(this.b);
                a(this.d, r);
            }
        } else {
            throw new NotConnectedException();
        }
    }

    /* access modifiers changed from: protected */
    public void a(SortedParameter sortedParameter, R r) throws PaymentException {
        Connection connection;
        if (this.f) {
            connection = ConnectionFactory.a(b(sortedParameter), this.f7587a);
        } else {
            DomainManager.a(this.f7587a);
            DeviceManager.a(this.f7587a);
            PrivacyManager.a(this.f7587a);
            connection = b(sortedParameter);
            if (this.e) {
                connection = ConnectionFactory.b(connection, this.f7587a);
            }
        }
        JSONObject e2 = connection.e();
        a(e2, r);
        try {
            int i = e2.getInt("errcode");
            String optString = e2.optString("errDesc");
            if (i == 1984) {
                throw new ServiceTokenExpiredException();
            } else if (i != 200) {
                if (CommonConstants.b) {
                    Log.w(c, "result error : error code " + i);
                    Log.w(c, "result error : error desc " + optString);
                }
                if (!b(e2, r)) {
                    throw new ServerErrorCodeException(i, optString, r);
                }
            } else {
                c(e2, r);
            }
        } catch (JSONException e3) {
            throw new ResultException("error code not exists", e3);
        }
    }

    public Session b() {
        return this.f7587a;
    }
}
