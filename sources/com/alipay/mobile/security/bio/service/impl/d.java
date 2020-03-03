package com.alipay.mobile.security.bio.service.impl;

import com.alipay.mobile.security.bio.service.BioUploadResult;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ BioUploadWatchThread f1014a;

    d(BioUploadWatchThread bioUploadWatchThread) {
        this.f1014a = bioUploadWatchThread;
    }

    public void run() {
        BioUploadResult bioUploadResult = new BioUploadResult();
        bioUploadResult.productRetCode = 4001;
        this.f1014a.a(bioUploadResult);
    }
}
