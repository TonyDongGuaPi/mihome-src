package com.alipay.zoloz.toyger.widget;

class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ CircleUploadPattern f1206a;

    a(CircleUploadPattern circleUploadPattern) {
        this.f1206a = circleUploadPattern;
    }

    public void run() {
        if (this.f1206a.mContext != null) {
            this.f1206a.intervalAction();
            boolean unused = this.f1206a.mIsShowProcess = false;
        }
    }
}
