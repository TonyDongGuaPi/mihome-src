package com.mibi.common.base;

import android.content.Context;
import com.mibi.common.R;
import com.mibi.common.base.BasePaymentTask;
import com.mibi.common.base.BasePaymentTask.Result;

public abstract class BasePaymentTaskAdapter<TaskType extends BasePaymentTask<Progress, TaskResult>, Progress, TaskResult extends BasePaymentTask.Result> extends BaseErrorHandleTaskAdapter<TaskType, Progress, TaskResult> {
    /* access modifiers changed from: protected */
    public void b(TaskResult taskresult) {
    }

    /* access modifiers changed from: protected */
    public boolean b(String str, int i, TaskResult taskresult) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void c() {
    }

    /* access modifiers changed from: protected */
    public abstract void c(TaskResult taskresult);

    /* access modifiers changed from: protected */
    public boolean d() {
        return true;
    }

    public BasePaymentTaskAdapter(Context context, TaskManager taskManager, TaskType tasktype) {
        super(context, taskManager, tasktype);
    }

    /* access modifiers changed from: protected */
    public void a() {
        c();
    }

    /* access modifiers changed from: protected */
    public boolean b() {
        return d();
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, TaskResult taskresult) {
        super.a(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(int i, int i2, int i3, TaskResult taskresult) {
        super.a(i, i2, i3, taskresult);
    }

    /* access modifiers changed from: protected */
    public void b(int i, int i2, TaskResult taskresult) {
        super.b(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void c(int i, int i2, TaskResult taskresult) {
        super.c(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void d(int i, int i2, TaskResult taskresult) {
        super.d(i, i2, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(TaskResult taskresult) {
        b(taskresult);
        int i = taskresult.mErrorCode;
        String str = taskresult.mErrorDesc;
        if (i != 200) {
            if (str.isEmpty()) {
                str = this.f7450a.getResources().getString(R.string.mibi_error_server_summary);
            }
            String str2 = i + " : " + str;
            if (!b(str2, i, taskresult)) {
                a(str2, i, taskresult);
                return;
            }
            return;
        }
        c(taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(String str, TaskResult taskresult) {
        a(str, 12, taskresult);
    }

    /* access modifiers changed from: protected */
    public void b(String str, TaskResult taskresult) {
        a(str, 9, taskresult);
    }

    /* access modifiers changed from: protected */
    public void c(String str, TaskResult taskresult) {
        a(str, 13, taskresult);
    }

    /* access modifiers changed from: protected */
    public void d(String str, TaskResult taskresult) {
        a(str, 0, taskresult);
    }

    /* access modifiers changed from: protected */
    public void a(String str, int i, TaskResult taskresult) {
        if (i == 1992) {
            a(str, taskresult);
        } else if (i == 1993) {
            b(str, taskresult);
        } else if (i == 1569) {
            c(str, taskresult);
        } else if (i == 8000) {
            d(str, taskresult);
        } else {
            a(str, 6, taskresult);
        }
    }
}
