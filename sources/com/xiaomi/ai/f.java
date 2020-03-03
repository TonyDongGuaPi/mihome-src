package com.xiaomi.ai;

import com.xiaomi.ai.m;
import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.mibrain.MibrainRequestListener;
import com.xiaomi.ai.utils.Log;

class f implements MibrainRequestListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SendWakeupDataStatusInterface f9922a;
    final /* synthetic */ c b;

    f(c cVar, SendWakeupDataStatusInterface sendWakeupDataStatusInterface) {
        this.b = cVar;
        this.f9922a = sendWakeupDataStatusInterface;
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
        Log.f("MiSpeechSDK:MiAiEngine", "send wakeup data error " + str);
        if (this.f9922a != null) {
            this.f9922a.b(str);
        }
    }

    public String onRequestGetToken(MibrainRequest mibrainRequest, boolean z) {
        m a2 = this.b.ap;
        if (a2 != null) {
            m.a a3 = a2.a(z);
            if (a3 == null || a3.f9930a == null) {
                return null;
            }
            return a3.f9930a;
        }
        Log.h("MiSpeechSDK:MiAiEngine", "send wakeup data onRequestGetToken aiOauthHelper = null ");
        return null;
    }

    public void onRequestNLpResult(MibrainRequest mibrainRequest, String str) {
    }

    public void onRequestOtherResult(MibrainRequest mibrainRequest, String str) {
        Log.f("MiSpeechSDK:MiAiEngine", "send wakeup data return " + str);
        if (this.f9922a != null) {
            this.f9922a.a(str);
        }
    }

    public void onRequestSessionEnd(MibrainRequest mibrainRequest) {
        Log.f("MiSpeechSDK:MiAiEngine", "send wakeup data end");
        if (this.f9922a != null) {
            this.f9922a.b();
        }
    }

    public void onRequestSessionStart(MibrainRequest mibrainRequest) {
        Log.f("MiSpeechSDK:MiAiEngine", "start send wakeup data");
        if (this.f9922a != null) {
            this.f9922a.a();
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
