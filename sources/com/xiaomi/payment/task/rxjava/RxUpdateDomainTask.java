package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.util.Log;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.DomainManager;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.data.Utils;
import com.mibi.common.exception.NotConnectedException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ServiceTokenExpiredException;
import com.mibi.common.rxjava.RxTask;
import com.xiaomi.payment.data.MibiConstants;

public class RxUpdateDomainTask extends RxTask<Result> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12437a = "UpdateDomainTask";
    private SortedParameter b;
    private Context c;
    private Session d;

    public static class Result {
    }

    public RxUpdateDomainTask(Context context, Session session) {
        super(Result.class);
        this.c = context;
        this.d = session;
    }

    public void a(SortedParameter sortedParameter) {
        if (sortedParameter == null) {
            sortedParameter = new SortedParameter();
        }
        this.b = sortedParameter;
    }

    /* access modifiers changed from: protected */
    public void a(Result result) throws PaymentException {
        if (Utils.a(this.c)) {
            this.d.a(this.c);
            try {
                b(this.b);
            } catch (ServiceTokenExpiredException unused) {
                if (CommonConstants.b) {
                    Log.i(f12437a, "updateDomain service token expired, re-login");
                }
                this.d.b(this.c);
                b(this.b);
            }
        } else {
            throw new NotConnectedException();
        }
    }

    private void b(SortedParameter sortedParameter) throws PaymentException {
        DomainManager.a(this.d, sortedParameter.f(MibiConstants.cT), sortedParameter.f(MibiConstants.cV));
    }
}
