package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;
import java.io.Serializable;

public class RxUploadResponseTask extends RxBasePaymentTask<Result> {

    public static class Result implements Serializable {
    }

    public RxUploadResponseTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f("responseData");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.cu), this.f7587a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        d.a("responseData", (Object) f2);
        return a2;
    }
}
