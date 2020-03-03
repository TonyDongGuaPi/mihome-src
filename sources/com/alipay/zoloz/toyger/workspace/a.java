package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.service.ZimRecordService;
import java.util.Arrays;
import java.util.HashSet;

class a implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerActivity f1216a;

    a(ToygerActivity toygerActivity) {
        this.f1216a = toygerActivity;
    }

    public void run() {
        ((ZimRecordService) BioServiceManager.getCurrentInstance().getBioService(ZimRecordService.class)).setLogClassifier(new HashSet(Arrays.asList(this.f1216a.mFaceRemoteConfig.getUpload().getString("log_classifier").split("#"))));
    }
}
