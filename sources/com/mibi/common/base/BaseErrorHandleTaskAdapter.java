package com.mibi.common.base;

import android.content.Context;
import com.mibi.common.base.BaseErrorHandleTask;
import com.mibi.common.base.BaseErrorHandleTask.Result;
import com.mibi.common.exception.AccountException;
import com.mibi.common.exception.CertificateDateNotValidException;
import com.mibi.common.exception.ConnectionException;
import com.mibi.common.exception.NetworkException;
import com.mibi.common.exception.PaymentException;
import com.mibi.common.exception.ResultException;
import com.mibi.common.exception.ServerException;
import com.taobao.weex.el.parse.Operators;

public abstract class BaseErrorHandleTaskAdapter<TaskType extends BaseErrorHandleTask<Progress, TaskResult>, Progress, TaskResult extends BaseErrorHandleTask.Result> extends TaskAdapter<TaskType, Progress, TaskResult> {

    /* renamed from: a  reason: collision with root package name */
    protected Context f7450a;

    /* access modifiers changed from: protected */
    public void a() {
    }

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public abstract void a(String str, int i, TaskResult taskresult);

    /* access modifiers changed from: protected */
    public void b(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return true;
    }

    public BaseErrorHandleTaskAdapter(Context context, TaskManager taskManager, TaskType tasktype) {
        super(taskManager, tasktype);
        this.f7450a = context;
    }

    public final void onTaskStart() {
        a();
    }

    public final void onTaskComplete(TaskResult taskresult) {
        if (b()) {
            PaymentException paymentException = taskresult.mError;
            if (paymentException != null) {
                a(paymentException, taskresult);
            } else {
                a(taskresult);
            }
            b(taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public void a(PaymentException paymentException, TaskResult taskresult) {
        if (paymentException instanceof NetworkException) {
            b((NetworkException) paymentException, taskresult);
        } else if (paymentException instanceof AccountException) {
            c((AccountException) paymentException, taskresult);
        } else if (paymentException instanceof ServerException) {
            d((ServerException) paymentException, taskresult);
        } else if (paymentException instanceof ResultException) {
            e((ResultException) paymentException, taskresult);
        } else {
            f(paymentException, taskresult);
        }
    }

    /* access modifiers changed from: protected */
    public void b(PaymentException paymentException, TaskResult taskresult) {
        Throwable cause;
        if ((paymentException instanceof ConnectionException) && (cause = paymentException.getCause()) != null && (cause instanceof CertificateDateNotValidException)) {
            paymentException = (PaymentException) cause;
        }
        a(paymentException.getErrorSummaryRes(), paymentException.getErrorCode(), taskresult);
    }

    /* access modifiers changed from: protected */
    public void c(PaymentException paymentException, TaskResult taskresult) {
        c(paymentException.getErrorSummaryRes(), paymentException.getErrorCode(), taskresult);
    }

    /* access modifiers changed from: protected */
    public void d(PaymentException paymentException, TaskResult taskresult) {
        a(paymentException.getErrorSummaryRes(), paymentException.getErrorCode(), ((ServerException) paymentException).getResponseCode(), taskresult);
    }

    /* access modifiers changed from: protected */
    public void e(PaymentException paymentException, TaskResult taskresult) {
        b(paymentException.getErrorSummaryRes(), paymentException.getErrorCode(), taskresult);
    }

    /* access modifiers changed from: protected */
    public void f(PaymentException paymentException, TaskResult taskresult) {
        d(paymentException.getErrorSummaryRes(), paymentException.getErrorCode(), taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3, TaskResult taskresult) {
        a(this.f7450a.getResources().getString(i) + i3, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void c(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void d(int i, int i2, TaskResult taskresult) {
        e(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public final void e(int i, int i2, TaskResult taskresult) {
        a(this.f7450a.getResources().getString(i) + Operators.ARRAY_START_STR + taskresult.mError.getFullIdentifier() + Operators.ARRAY_END_STR, i2, taskresult);
    }
}
