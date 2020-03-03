package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.faceauth.model.DetectTimerTask;

class m implements DetectTimerTask.TimerListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerWorkspace f1230a;

    m(ToygerWorkspace toygerWorkspace) {
        this.f1230a = toygerWorkspace;
    }

    public void countdown(int i) {
        if (this.f1230a.mDetectTimerTask != null && this.f1230a.mDetectTimerTask.isTimeOut()) {
            this.f1230a.mWorkspaceHandler.post(new n(this));
        } else if (!(this.f1230a.mDetectTimerTask == null || this.f1230a.mCurrentToygerFrame == null || this.f1230a.isFirstTime)) {
            this.f1230a.mWorkspaceHandler.post(new o(this));
        }
        if (this.f1230a.isFirstTime) {
            boolean unused = this.f1230a.isFirstTime = false;
        }
        if (this.f1230a.mWorkspaceHandler != null) {
            this.f1230a.mWorkspaceHandler.post(new p(this, i));
        }
    }
}
