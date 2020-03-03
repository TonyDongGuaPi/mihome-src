package com.mipay.common.base;

import android.content.Context;
import com.mipay.common.base.a;
import com.mipay.common.base.a.C0063a;
import com.mipay.common.exception.d;
import com.mipay.common.exception.f;
import com.mipay.common.exception.g;
import com.mipay.common.exception.h;
import com.taobao.weex.el.parse.Operators;

public abstract class b<TaskType extends a<Progress, TaskResult>, Progress, TaskResult extends a.C0063a> extends n<TaskType, Progress, TaskResult> {

    /* renamed from: a  reason: collision with root package name */
    protected Context f8112a;

    public b(Context context, s sVar, TaskType tasktype) {
        super(sVar, tasktype);
        this.f8112a = context;
    }

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3, TaskResult taskresult) {
        a(this.f8112a.getResources().getString(i) + i3, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void a(f fVar, TaskResult taskresult) {
        if (fVar instanceof d) {
            b((d) fVar, taskresult);
        } else if (fVar instanceof com.mipay.common.exception.a) {
            c((com.mipay.common.exception.a) fVar, taskresult);
        } else if (fVar instanceof h) {
            d((h) fVar, taskresult);
        } else if (fVar instanceof g) {
            e((g) fVar, taskresult);
        } else {
            f(fVar, taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void a(String str, int i, TaskResult taskresult);

    /* access modifiers changed from: protected */
    public void b(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void b(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void b(f fVar, TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void c(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void c(f fVar, TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void d(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void d(f fVar, TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public final void e(int i, int i2, TaskResult taskresult) {
        a(this.f8112a.getResources().getString(i) + Operators.ARRAY_START_STR + taskresult.mError.c() + Operators.ARRAY_END_STR, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void e(f fVar, TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public void f(f fVar, TaskResult taskresult) {
    }

    public final void onTaskComplete(TaskResult taskresult) {
        if (b()) {
            f fVar = taskresult.mError;
            if (fVar != null) {
                a(fVar, taskresult);
            } else {
                a(taskresult);
            }
            b(taskresult);
        }
    }

    public final void onTaskStart() {
        a();
    }
}
