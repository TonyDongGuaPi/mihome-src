package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.rxjava.RxBasePaymentTask;
import com.xiaomi.payment.data.MibiConstants;

public class RxUploadOrderGiftcardSettingTask extends RxBasePaymentTask<Result> {

    public static class Result {
    }

    public RxUploadOrderGiftcardSettingTask(Context context, Session session) {
        super(context, session, Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        boolean b = sortedParameter.b(MibiConstants.eE);
        boolean b2 = sortedParameter.b(MibiConstants.eG);
        int a2 = sortedParameter.a(MibiConstants.gS, 0);
        Connection a3 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bY), this.f7587a);
        SortedParameter d = a3.d();
        d.a("processId", (Object) f);
        d.a(MibiConstants.eE, (Object) Boolean.valueOf(b));
        d.a(MibiConstants.eG, (Object) Boolean.valueOf(b2));
        d.a(MibiConstants.gS, (Object) Integer.valueOf(a2));
        return a3;
    }
}
