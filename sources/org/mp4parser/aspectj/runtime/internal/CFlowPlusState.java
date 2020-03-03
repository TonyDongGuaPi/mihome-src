package org.mp4parser.aspectj.runtime.internal;

import org.mp4parser.aspectj.runtime.CFlow;

public class CFlowPlusState extends CFlow {

    /* renamed from: a  reason: collision with root package name */
    private Object[] f3762a;

    public CFlowPlusState(Object[] objArr) {
        this.f3762a = objArr;
    }

    public CFlowPlusState(Object[] objArr, Object obj) {
        super(obj);
        this.f3762a = objArr;
    }

    public Object a(int i) {
        return this.f3762a[i];
    }
}
