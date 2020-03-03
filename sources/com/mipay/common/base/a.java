package com.mipay.common.base;

import com.mipay.common.base.a.C0063a;
import com.mipay.common.data.h;
import com.mipay.common.exception.f;
import java.io.Serializable;

public abstract class a<Progress, TaskResult extends C0063a> extends m<Progress, TaskResult> {

    /* renamed from: com.mipay.common.base.a$a  reason: collision with other inner class name */
    public static class C0063a implements Serializable {
        public f mError;
    }

    public a(Class<TaskResult> cls) {
        super(cls);
    }

    public a(Class<TaskResult> cls, boolean z) {
        super(cls, z);
    }

    /* access modifiers changed from: protected */
    public final void a(h hVar, TaskResult taskresult) {
        try {
            b(hVar, taskresult);
        } catch (f e) {
            taskresult.mError = e;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void b(h hVar, TaskResult taskresult) throws f;
}
