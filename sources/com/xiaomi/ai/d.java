package com.xiaomi.ai;

import com.xiaomi.ai.utils.UploadHelper;

class d implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SendWakeupDataStatusInterface f9920a;
    final /* synthetic */ String b;
    final /* synthetic */ UploadHelper.WakeupDataInfo c;
    final /* synthetic */ byte[] d;
    final /* synthetic */ c e;

    d(c cVar, SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str, UploadHelper.WakeupDataInfo wakeupDataInfo, byte[] bArr) {
        this.e = cVar;
        this.f9920a = sendWakeupDataStatusInterface;
        this.b = str;
        this.c = wakeupDataInfo;
        this.d = bArr;
    }

    public void run() {
        UploadHelper a2 = this.e.a(this.f9920a, this.b);
        if (a2 == null) {
            this.f9920a.b("getUploadHelper Error");
            return;
        }
        String a3 = UploadHelper.a(this.e.v, this.c);
        if (a3 == null) {
            this.f9920a.b("getUploadHelper gen asr msg Error");
            return;
        }
        a2.a(a3.getBytes(), true);
        a2.a(this.d, false);
        a2.c();
    }
}
