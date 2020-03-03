package com.mibi.common.base;

import android.content.Context;
import android.util.Log;
import com.mibi.common.base.BaseErrorHandleTask;
import com.mibi.common.base.BasePaymentTask.Result;
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
import com.mibi.common.exception.ServiceTokenExpiredException;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BasePaymentTask<Progress, TaskResult extends Result> extends BaseErrorHandleTask<Progress, TaskResult> {
    private static final String c = "BasePaymentTask";

    /* renamed from: a  reason: collision with root package name */
    protected Session f7453a;
    protected Context b;
    private boolean d;
    private boolean e;

    public static class Result extends BaseErrorHandleTask.Result {
        public int mErrorCode;
        public String mErrorDesc;
    }

    /* access modifiers changed from: protected */
    public abstract Connection a(SortedParameter sortedParameter);

    /* access modifiers changed from: protected */
    public void a(JSONObject jSONObject, TaskResult taskresult) throws PaymentException {
    }

    /* access modifiers changed from: protected */
    public void b(JSONObject jSONObject, TaskResult taskresult) throws PaymentException {
    }

    /* access modifiers changed from: protected */
    public void c(JSONObject jSONObject, TaskResult taskresult) throws PaymentException {
    }

    public BasePaymentTask(Context context, Session session, Class<TaskResult> cls) {
        super(cls);
        this.b = context;
        this.f7453a = session;
    }

    public BasePaymentTask(Context context, Session session, Class<TaskResult> cls, boolean z) {
        super(cls, z);
        this.b = context;
        this.f7453a = session;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public boolean a() {
        return this.e;
    }

    /* access modifiers changed from: protected */
    public void b(boolean z) {
        this.d = z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(SortedParameter sortedParameter, TaskResult taskresult) throws PaymentException {
        if (Utils.a(this.b) || this.e) {
            this.f7453a.a(this.b);
            try {
                b(sortedParameter, taskresult);
            } catch (ServiceTokenExpiredException e2) {
                Log.e(c, "service token expired, re-login exception ", e2);
                this.f7453a.b(this.b);
                b(sortedParameter, taskresult);
            }
        } else {
            throw new NotConnectedException();
        }
    }

    /* access modifiers changed from: protected */
    public void b(SortedParameter sortedParameter, TaskResult taskresult) throws PaymentException {
        Connection connection;
        if (this.e) {
            connection = ConnectionFactory.a(a(sortedParameter), this.f7453a);
        } else {
            DomainManager.a(this.f7453a);
            DeviceManager.a(this.f7453a);
            PrivacyManager.a(this.f7453a);
            connection = a(sortedParameter);
            if (this.d) {
                connection = ConnectionFactory.b(connection, this.f7453a);
            }
        }
        JSONObject e2 = connection.e();
        a(e2, taskresult);
        try {
            int i = e2.getInt("errcode");
            String optString = e2.optString("errDesc");
            taskresult.mErrorCode = i;
            taskresult.mErrorDesc = optString;
            if (taskresult.mErrorCode == 1984) {
                throw new ServiceTokenExpiredException();
            } else if (i != 200) {
                if (CommonConstants.b) {
                    Log.w(c, "result error : error code " + i);
                    Log.w(c, "result error : error desc " + optString);
                }
                b(e2, taskresult);
            } else {
                c(e2, taskresult);
            }
        } catch (JSONException e3) {
            throw new ResultException("error code not exists", e3);
        }
    }

    public Session b() {
        return this.f7453a;
    }
}
