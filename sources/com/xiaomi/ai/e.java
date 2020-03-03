package com.xiaomi.ai;

import com.xiaomi.ai.utils.UploadHelper;

class e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SendWakeupDataStatusInterface f9921a;
    final /* synthetic */ String b;
    final /* synthetic */ UploadHelper.ASRRecordInfo c;
    final /* synthetic */ byte[] d;
    final /* synthetic */ c e;

    e(c cVar, SendWakeupDataStatusInterface sendWakeupDataStatusInterface, String str, UploadHelper.ASRRecordInfo aSRRecordInfo, byte[] bArr) {
        this.e = cVar;
        this.f9921a = sendWakeupDataStatusInterface;
        this.b = str;
        this.c = aSRRecordInfo;
        this.d = bArr;
    }

    public void run() {
        UploadHelper a2 = this.e.a(this.f9921a, this.b);
        if (a2 == null) {
            this.f9921a.b("getUploadHelper Error");
            return;
        }
        String a3 = UploadHelper.a(this.e.v, this.c);
        if (a3 == null) {
            this.f9921a.b("getUploadHelper gen asr msg Error");
            return;
        }
        a2.a(a3.getBytes(), true);
        a2.a(this.d, false);
        a2.c();
    }
}
