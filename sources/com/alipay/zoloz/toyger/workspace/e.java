package com.alipay.zoloz.toyger.workspace;

import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.mobile.security.faceauth.model.DetectTimerTask;
import com.alipay.zoloz.toyger.bean.ToygerFrame;

class e implements DetectTimerTask.TimerListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerWorkspace f1222a;
    private ToygerFrame b;

    e(ToygerWorkspace toygerWorkspace) {
        this.f1222a = toygerWorkspace;
    }

    public void countdown(int i) {
        if (i > 0) {
            if (!(this.b == this.f1222a.mCurrentToygerFrame || this.f1222a.mCurrentToygerFrame == null)) {
                this.f1222a.recordSlice(this.f1222a.mCurrentToygerFrame);
                this.b = this.f1222a.mCurrentToygerFrame;
            }
            try {
                if (this.f1222a.mSensorCollectors != null) {
                    this.f1222a.mSensorData.add(this.f1222a.mSensorCollectors.getData());
                }
            } catch (Throwable th) {
                BioLog.e(th);
            }
        }
    }
}
