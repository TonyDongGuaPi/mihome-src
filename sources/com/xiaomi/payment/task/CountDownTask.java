package com.xiaomi.payment.task;

import com.mibi.common.base.Task;
import com.mibi.common.data.SortedParameter;

public class CountDownTask extends Task<Integer, Void> {
    public CountDownTask() {
        this(false);
    }

    public CountDownTask(boolean z) {
        super((Class) null, z);
    }

    /* access modifiers changed from: protected */
    public void a(SortedParameter sortedParameter, Void voidR) {
        int e = sortedParameter.e("count");
        while (e > 0 && !g()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException unused) {
            }
            e--;
            c(Integer.valueOf(e));
        }
    }
}
