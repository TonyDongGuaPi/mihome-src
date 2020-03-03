package org.aspectj.runtime.internal;

import org.aspectj.runtime.CFlow;

public class CFlowPlusState extends CFlow {

    /* renamed from: a  reason: collision with root package name */
    private Object[] f3459a;

    public CFlowPlusState(Object[] objArr) {
        this.f3459a = objArr;
    }

    public CFlowPlusState(Object[] objArr, Object obj) {
        super(obj);
        this.f3459a = objArr;
    }

    public Object a(int i) {
        return this.f3459a[i];
    }
}
