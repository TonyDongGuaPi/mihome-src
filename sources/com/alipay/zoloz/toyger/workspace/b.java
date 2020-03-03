package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.bio.utils.ScreenUtil;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerCaptureFragment f1219a;

    b(ToygerCaptureFragment toygerCaptureFragment) {
        this.f1219a = toygerCaptureFragment;
    }

    public void run() {
        if (this.f1219a.mLight > ScreenUtil.getScreenBrightness(this.f1219a.getContext())) {
            ScreenUtil.setScreenBrightness(this.f1219a.getActivity(), this.f1219a.mLight);
        }
    }
}
