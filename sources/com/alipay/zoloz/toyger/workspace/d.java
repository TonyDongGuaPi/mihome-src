package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.bio.task.ActionFrame;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ActionFrame f1221a;
    final /* synthetic */ ToygerWorkspace b;

    d(ToygerWorkspace toygerWorkspace, ActionFrame actionFrame) {
        this.b = toygerWorkspace;
        this.f1221a = actionFrame;
    }

    public void run() {
        this.b.onDoAction(this.f1221a);
    }
}
