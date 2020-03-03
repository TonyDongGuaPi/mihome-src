package com.alipay.mobile.security.bio.service.impl;

import com.alipay.mobile.security.bio.service.BioUploadResult;
import com.alipay.mobile.security.bio.utils.BioLog;

class c implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BioUploadResult f1013a;
    final /* synthetic */ BioUploadWatchThread b;

    c(BioUploadWatchThread bioUploadWatchThread, BioUploadResult bioUploadResult) {
        this.b = bioUploadWatchThread;
        this.f1013a = bioUploadResult;
    }

    public void run() {
        BioLog.e("BioUploadWatchThread.doCallback()");
        this.b.a(this.f1013a);
    }
}
