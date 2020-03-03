package com.mipay.ucashier.task;

import android.content.Context;
import com.mipay.common.base.b;
import com.mipay.common.base.s;
import com.mipay.common.exception.c;
import com.mipay.common.exception.f;
import com.mipay.common.exception.h;
import com.mipay.ucashier.R;
import com.mipay.ucashier.task.BaseUCashierTask;
import com.mipay.ucashier.task.BaseUCashierTask.Result;

public abstract class BaseUCashierTaskAdapter<TaskType extends BaseUCashierTask<Progress, TaskResult>, Progress, TaskResult extends BaseUCashierTask.Result> extends b<TaskType, Progress, TaskResult> {
    public BaseUCashierTaskAdapter(Context context, s sVar, TaskType tasktype) {
        super(context, sVar, tasktype);
    }

    /* access modifiers changed from: protected */
    public void a() {
        d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void b(f fVar, TaskResult taskresult) {
        Throwable cause;
        int i = R.string.ucashier_error_network;
        if ((fVar instanceof c) && (cause = fVar.getCause()) != null && (cause instanceof com.mipay.common.exception.b)) {
            i = R.string.ucashier_error_cert_date;
        }
        a(i, 1, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) {
        b(taskresult);
        int i = taskresult.mErrorCode;
        String str = taskresult.mErrorDesc;
        if (i != 200) {
            if (str.isEmpty()) {
                str = this.f8112a.getResources().getString(R.string.ucashier_error_server);
            }
            String str2 = i + " : " + str;
            if (i < 1000000 || i > 1999999) {
                if (i < 2000000 || i > 2999999) {
                    if (i < 3000000 || i > 3999999) {
                        if (i < 100000000 || i > 199999999) {
                            if (i < 200000000 || i > 299999999) {
                                if (i < 300000000 || i > 399999999) {
                                    m(str2, i, taskresult);
                                } else if (!k(str2, i, taskresult)) {
                                    l(str2, i, taskresult);
                                }
                            } else if (!i(str2, i, taskresult)) {
                                j(str2, i, taskresult);
                            }
                        } else if (!g(str2, i, taskresult)) {
                            h(str2, i, taskresult);
                        }
                    } else if (!e(str2, i, taskresult)) {
                        f(str2, i, taskresult);
                    }
                } else if (!c(str2, i, taskresult)) {
                    d(str2, i, taskresult);
                }
            } else if (!a(str2, i, taskresult)) {
                b(str2, i, taskresult);
            }
        } else {
            c(taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public void a(String str, TaskResult taskresult) {
        a(str, 1, taskresult);
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, int i, TaskResult taskresult) {
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void c(f fVar, TaskResult taskresult) {
        Throwable cause = fVar.getCause();
        if (cause == null || !(cause instanceof f)) {
            c(R.string.ucashier_error_auth, 1, taskresult);
        } else {
            a((f) cause, taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public void b(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public final void b(String str, int i, TaskResult taskresult) {
        a(str, 1, taskresult);
    }

    /* access modifiers changed from: protected */
    public void b(String str, TaskResult taskresult) {
        a(str, 2, taskresult);
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return e();
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void d(f fVar, TaskResult taskresult) {
        a(R.string.ucashier_error_server, 1, ((h) fVar).b(), taskresult);
    }

    /* access modifiers changed from: protected */
    public abstract void c(TaskResult taskresult);

    /* access modifiers changed from: protected */
    public void c(String str, TaskResult taskresult) {
        a(str, 2, taskresult);
    }

    /* access modifiers changed from: protected */
    public boolean c(String str, int i, TaskResult taskresult) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void d() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void e(f fVar, TaskResult taskresult) {
        b(R.string.ucashier_error_server, 1, taskresult);
    }

    /* access modifiers changed from: protected */
    public final void d(String str, int i, TaskResult taskresult) {
        if (i == 2000002) {
            a(str, taskresult);
        } else if (i == 2000001) {
            b(str, taskresult);
        } else if (i == 2010008 || i == 2010009) {
            c(str, taskresult);
        } else if (i == 2000004) {
            d(str, taskresult);
        } else {
            a(str, 2, taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public void d(String str, TaskResult taskresult) {
        a(str, 2, taskresult);
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void f(f fVar, TaskResult taskresult) {
        d(R.string.ucashier_error_server, 1, taskresult);
    }

    /* access modifiers changed from: protected */
    public void e(String str, TaskResult taskresult) {
        a(str, 2, taskresult);
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean e(String str, int i, TaskResult taskresult) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void f(String str, int i, TaskResult taskresult) {
        if (i == 3000001) {
            e(str, taskresult);
        } else {
            a(str, 2, taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean g(String str, int i, TaskResult taskresult) {
        return a(str, i, taskresult);
    }

    /* access modifiers changed from: protected */
    public final void h(String str, int i, TaskResult taskresult) {
        a(str, 1, taskresult);
    }

    /* access modifiers changed from: protected */
    public final boolean i(String str, int i, TaskResult taskresult) {
        return c(str, i, taskresult);
    }

    /* access modifiers changed from: protected */
    public final void j(String str, int i, TaskResult taskresult) {
        a(str, 2, taskresult);
    }

    /* access modifiers changed from: protected */
    public boolean k(String str, int i, TaskResult taskresult) {
        return e(str, i, taskresult);
    }

    /* access modifiers changed from: protected */
    public final void l(String str, int i, TaskResult taskresult) {
        a(str, 2, taskresult);
    }

    /* access modifiers changed from: protected */
    public final void m(String str, int i, TaskResult taskresult) {
        a(str, 1, taskresult);
    }
}
