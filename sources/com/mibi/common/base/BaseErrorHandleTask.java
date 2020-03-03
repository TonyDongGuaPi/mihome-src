package com.mibi.common.base;

import com.mibi.common.base.BaseErrorHandleTask.Result;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.exception.PaymentException;
import java.io.Serializable;

public abstract class BaseErrorHandleTask<Progress, TaskResult extends Result> extends Task<Progress, TaskResult> {

    public static class Result implements Serializable {
        public PaymentException mError;
    }

    /* access modifiers changed from: protected */
    public abstract void b(SortedParameter sortedParameter, TaskResult taskresult) throws PaymentException;

    public BaseErrorHandleTask(Class<TaskResult> cls) {
        super(cls);
    }

    public BaseErrorHandleTask(Class<TaskResult> cls, boolean z) {
        super(cls, z);
    }

    /* access modifiers changed from: protected */
    public final void a(SortedParameter sortedParameter, TaskResult taskresult) {
        try {
            b(sortedParameter, taskresult);
        } catch (PaymentException e) {
            e.print();
            taskresult.mError = e;
        }
    }
}
