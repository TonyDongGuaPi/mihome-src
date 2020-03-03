package com.xiaomi.ai;

import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.mibrain.MibrainRequestListener;
import com.xiaomi.ai.mibrain.MibrainWakeupRequestParams;
import com.xiaomi.ai.utils.n;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ int f9923a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ int e;
    final /* synthetic */ String f;
    final /* synthetic */ byte[] g;
    final /* synthetic */ MibrainRequestListener h;
    final /* synthetic */ c i;

    g(c cVar, int i2, String str, String str2, String str3, int i3, String str4, byte[] bArr, MibrainRequestListener mibrainRequestListener) {
        this.i = cVar;
        this.f9923a = i2;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = i3;
        this.f = str4;
        this.g = bArr;
        this.h = mibrainRequestListener;
    }

    public void run() {
        MibrainWakeupRequestParams mibrainWakeupRequestParams = new MibrainWakeupRequestParams();
        mibrainWakeupRequestParams.setApiKey(this.i.ar);
        mibrainWakeupRequestParams.setAppId(this.i.H);
        mibrainWakeupRequestParams.setToken((this.i.W == 0 && (this.i.aq == SpeechEngine.d || this.i.aq == SpeechEngine.f || this.i.aq == SpeechEngine.g || this.i.aq == SpeechEngine.h)) ? null : this.i.I);
        mibrainWakeupRequestParams.setChannel(this.f9923a);
        mibrainWakeupRequestParams.setCodec(this.b);
        mibrainWakeupRequestParams.setVendor(this.c);
        mibrainWakeupRequestParams.setWord(this.d);
        mibrainWakeupRequestParams.setRate(this.e);
        mibrainWakeupRequestParams.setExtra(this.f);
        mibrainWakeupRequestParams.setDeviceId(n.b(this.i.v));
        mibrainWakeupRequestParams.setData(this.g);
        mibrainWakeupRequestParams.setScopeData(n.b(n.b(this.i.v)));
        MibrainRequest mibrainRequest = new MibrainRequest(mibrainWakeupRequestParams);
        mibrainRequest.setRequestListener(this.h);
        mibrainRequest.startWakeupRequest(this.i.aa, this.i.aq);
    }
}
