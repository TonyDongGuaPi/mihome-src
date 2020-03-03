package com.alipay.zoloz.toyger.upload;

import com.alipay.mobile.security.bio.utils.BioLog;
import com.alipay.zoloz.toyger.ToygerService;
import com.alipay.zoloz.toyger.bean.ToygerFrame;

class b implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerFrame f1205a;
    final /* synthetic */ NineShootManager b;

    b(NineShootManager nineShootManager, ToygerFrame toygerFrame) {
        this.b = nineShootManager;
        this.f1205a = toygerFrame;
    }

    public void run() {
        StringBuilder sb;
        String str;
        try {
            this.b.mToygerFaceService.addMonitorImage(this.f1205a.tgFrame);
            NineShootManager.access$108(this.b);
            str = ToygerService.TAG;
            sb = new StringBuilder();
        } catch (Exception e) {
            BioLog.w((Throwable) e);
            str = ToygerService.TAG;
            sb = new StringBuilder();
        } catch (Throwable th) {
            BioLog.d(ToygerService.TAG, "NineShootManager.addMonitoryFrame() : " + this.f1205a + ", count=" + this.b.count);
            throw th;
        }
        sb.append("NineShootManager.addMonitoryFrame() : ");
        sb.append(this.f1205a);
        sb.append(", count=");
        sb.append(this.b.count);
        BioLog.d(str, sb.toString());
    }
}
