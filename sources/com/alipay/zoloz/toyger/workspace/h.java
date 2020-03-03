package com.alipay.zoloz.toyger.workspace;

import com.alipay.zoloz.toyger.bean.ToygerFrame;

class h implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerFrame f1225a;
    final /* synthetic */ ToygerWorkspace b;

    h(ToygerWorkspace toygerWorkspace, ToygerFrame toygerFrame) {
        this.b = toygerWorkspace;
        this.f1225a = toygerFrame;
    }

    public void run() {
        if (this.b.mToygerCirclePattern != null) {
            this.b.mToygerCirclePattern.getAlgorithmInfoPattern().showInfo(this.b.faceAttrToAlgAttr(this.f1225a.tgFaceAttr));
        }
    }
}
