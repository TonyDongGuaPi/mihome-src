package com.alipay.zoloz.toyger.extservice;

import android.graphics.Rect;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;

class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Rect f1202a;
    final /* synthetic */ TGFrame b;
    final /* synthetic */ TGDepthFrame c;
    final /* synthetic */ ToygerIspService d;

    a(ToygerIspService toygerIspService, Rect rect, TGFrame tGFrame, TGDepthFrame tGDepthFrame) {
        this.d = toygerIspService;
        this.f1202a = rect;
        this.b = tGFrame;
        this.c = tGDepthFrame;
    }

    public void run() {
        this.d.adjustIsp(this.f1202a, this.b.data, this.c.data);
    }
}
