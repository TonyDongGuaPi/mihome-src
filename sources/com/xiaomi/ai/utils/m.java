package com.xiaomi.ai.utils;

import com.xiaomi.ai.SendWakeupDataStatusInterface;
import com.xiaomi.ai.m;
import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.mibrain.MibrainRequestListener;

class m implements MibrainRequestListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SendWakeupDataStatusInterface f9957a;
    final /* synthetic */ com.xiaomi.ai.m b;
    final /* synthetic */ UploadHelper c;

    m(UploadHelper uploadHelper, SendWakeupDataStatusInterface sendWakeupDataStatusInterface, com.xiaomi.ai.m mVar) {
        this.c = uploadHelper;
        this.f9957a = sendWakeupDataStatusInterface;
        this.b = mVar;
    }

    public void onEventTrack(String str) {
    }

    public void onInstruction(MibrainRequest mibrainRequest, String str) {
    }

    public String onNeedUpdateToken() {
        return null;
    }

    public void onRequestASRResult(MibrainRequest mibrainRequest, String str, boolean z) {
    }

    public void onRequestError(MibrainRequest mibrainRequest, String str, int i, int i2) {
        Log.d("UploadHelper", "startUploadRequest error " + str);
        if (this.f9957a != null) {
            this.f9957a.b(str);
        }
        this.c.c.compareAndSet(true, false);
    }

    public String onRequestGetToken(MibrainRequest mibrainRequest, boolean z) {
        com.xiaomi.ai.m mVar = this.b;
        if (mVar != null) {
            m.a a2 = mVar.a(z);
            if (a2 == null || a2.f9930a == null) {
                return null;
            }
            return a2.f9930a;
        }
        Log.a("UploadHelper", "upload data onRequestGetToken aiOauthHelper = null ");
        return null;
    }

    public void onRequestNLpResult(MibrainRequest mibrainRequest, String str) {
    }

    public void onRequestOtherResult(MibrainRequest mibrainRequest, String str) {
        Log.d("UploadHelper", "upload data return " + str);
        if (this.f9957a != null) {
            this.f9957a.a(str);
        }
    }

    public void onRequestSessionEnd(MibrainRequest mibrainRequest) {
        Log.d("UploadHelper", "upload data end");
        if (this.f9957a != null) {
            this.f9957a.b();
        }
        this.c.c.compareAndSet(true, false);
    }

    public void onRequestSessionStart(MibrainRequest mibrainRequest) {
        Log.d("UploadHelper", "start startUploadRequest");
        if (this.f9957a != null) {
            this.f9957a.a();
        }
    }

    public void onRequestTTSResult(MibrainRequest mibrainRequest, String str, byte[] bArr, boolean z) {
    }

    public void onRequestVoiceprintDeleteResult(MibrainRequest mibrainRequest, String str) {
    }

    public void onRequestVoiceprintQueryResult(MibrainRequest mibrainRequest, String str) {
    }

    public void onRequestVoiceprintRecognizedResult(MibrainRequest mibrainRequest, String str) {
    }

    public void onRequestVoiceprintRegisteredResult(MibrainRequest mibrainRequest, String str, boolean z) {
    }
}
