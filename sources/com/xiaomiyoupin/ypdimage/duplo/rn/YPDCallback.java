package com.xiaomiyoupin.ypdimage.duplo.rn;

import com.facebook.react.bridge.Callback;

class YPDCallback implements Callback {

    /* renamed from: a  reason: collision with root package name */
    private Callback f1788a;
    private boolean b = false;

    YPDCallback(Callback callback) {
        this.f1788a = callback;
    }

    public void invoke(Object... objArr) {
        if (!this.b && this.f1788a != null) {
            this.f1788a.invoke(objArr);
            this.b = true;
        }
    }
}
