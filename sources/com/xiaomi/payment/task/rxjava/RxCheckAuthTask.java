package com.xiaomi.payment.task.rxjava;

import android.content.Context;
import android.text.TextUtils;
import com.mibi.common.data.Connection;
import com.mibi.common.data.ConnectionFactory;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.task.rxjava.RxBaseCheckAuthAndPayTask;

public class RxCheckAuthTask extends RxBaseCheckAuthAndPayTask<RxBaseCheckAuthAndPayTask.Result> {
    public RxCheckAuthTask(Context context, Session session) {
        super(context, session, RxBaseCheckAuthAndPayTask.Result.class);
    }

    /* access modifiers changed from: protected */
    public Connection b(SortedParameter sortedParameter) {
        String f = sortedParameter.f("processId");
        String f2 = sortedParameter.f(MibiConstants.du);
        String f3 = sortedParameter.f("smsCode");
        Connection a2 = ConnectionFactory.a(MibiConstants.a(MibiConstants.bE), this.f7587a);
        SortedParameter d = a2.d();
        d.a("processId", (Object) f);
        if (!TextUtils.isEmpty(f2)) {
            d.a(MibiConstants.du, (Object) f2);
        }
        if (!TextUtils.isEmpty(f3)) {
            d.a("smsCode", (Object) f3);
        }
        return a2;
    }
}
