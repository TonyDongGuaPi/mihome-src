package com.mibi.common.base;

import android.content.Context;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.base.BasePaymentTask.Result;
import com.mibi.common.data.CommonConstants;
import com.mibi.common.data.Session;
import com.mibi.common.data.SortedParameter;

public abstract class PageableTask<TaskResult extends BasePaymentTask.Result> extends BasePaymentTask<Void, TaskResult> {
    private final int c = 1;
    private final int d = 20;
    private int e;

    public PageableTask(Context context, Session session, Class<TaskResult> cls) {
        super(context, session, cls);
    }

    /* access modifiers changed from: protected */
    public void b(SortedParameter sortedParameter) {
        sortedParameter.a(CommonConstants.aS, (Object) Integer.valueOf(this.e));
        sortedParameter.a("pageSize", (Object) 20);
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
