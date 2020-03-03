package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.faceauth.InvokeType;

class f implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerWorkspace f1223a;

    f(ToygerWorkspace toygerWorkspace) {
        this.f1223a = toygerWorkspace;
    }

    public void run() {
        try {
            if (this.f1223a.mUploadManager != null) {
                this.f1223a.mUploadManager.uploadBehaviourLog(InvokeType.LIVENESS_FAILED);
            }
        } catch (Exception unused) {
        }
    }
}
