package com.mibi.common.rxjava;

import android.content.Context;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;

public abstract class RxPageableTask<R> extends RxBasePaymentTask<R> {
    private final int c = 1;
    private final int d = 20;
    private int e;

    public RxPageableTask(Context context, Session session, Class<R> cls) {
        super(context, session, cls);
    }

    public void a(SortedParameter sortedParameter) {
        sortedParameter.a(CommonConstants.aS, (Object) Integer.valueOf(this.e));
        sortedParameter.a("pageSize", (Object) 20);
        super.a(sortedParameter);
    }

    public boolean c() {
        return this.e == 1;
    }

    public void d() {
        this.e = 1;
    }

    public void e() {
        this.e++;
    }
}
