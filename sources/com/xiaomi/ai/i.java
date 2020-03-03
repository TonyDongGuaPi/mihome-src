package com.xiaomi.ai;

import com.xiaomi.ai.SpeechEngine;
import com.xiaomi.ai.c;
import com.xiaomi.ai.mibrain.MibrainRequest;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.ai.utils.UploadHelper;
import com.xiaomi.ai.utils.f;
import java.util.Arrays;
import org.json.JSONException;

class i implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SpeechEngine.ParamBuilder f9925a;
    final /* synthetic */ c b;

    i(c cVar, SpeechEngine.ParamBuilder paramBuilder) {
        this.b = cVar;
        this.f9925a = paramBuilder;
    }

    public void run() {
        synchronized (this.b.al) {
            if (this.b.ao != this.f9925a) {
                Log.g("MiSpeechSDK:MiAiEngine", "new task is already begin!\n");
                return;
            }
            if (!this.f9925a.f9904a && !this.f9925a.m) {
                if (!this.f9925a.i) {
                    if (!f.a(this.b.v)) {
                        this.b.a((CharSequence) "Network error ");
                        SpeechError speechError = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                        speechError.V = "Network error ";
                        speechError.U = 13;
                        speechError.T = 2;
                        this.b.a(this.f9925a, speechError);
                        return;
                    }
                    try {
                        MibrainRequest unused = this.b.aj = new MibrainRequest(this.b.d(this.f9925a));
                        this.b.aj.setGetAnonymousAuthorizationCallback(this.b.X);
                        Log.f("MiSpeechSDK:MiAiEngine", " new mMibrainRequest = " + this.b.aj);
                        this.b.aj.setRequestListener(new c.d(this.f9925a, this.b.aj));
                        int startRequest = this.b.aj.startRequest();
                        if (startRequest < 0) {
                            String str = "startRequest failed: ret=" + startRequest;
                            this.b.a((CharSequence) str);
                            SpeechError speechError2 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                            speechError2.V = str;
                            speechError2.U = startRequest;
                            speechError2.T = 2;
                            this.b.a(this.f9925a, speechError2);
                            return;
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        SpeechError speechError3 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                        speechError3.V = e.getMessage();
                        speechError3.U = 1;
                        speechError3.T = 2;
                        this.b.a(this.f9925a, speechError3);
                        return;
                    }
                }
            }
            if (this.b.ah != null) {
                this.b.ah.interrupt();
            }
            if (!f.a(this.b.v)) {
                this.b.a((CharSequence) "Network error ");
                SpeechError speechError4 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                speechError4.V = "Network error ";
                speechError4.U = 13;
                speechError4.T = 2;
                this.b.a(this.f9925a, speechError4);
                return;
            }
            try {
                MibrainRequest unused2 = this.b.aj = new MibrainRequest(this.b.d(this.f9925a));
                this.b.aj.setGetAnonymousAuthorizationCallback(this.b.X);
                Log.f("MiSpeechSDK:MiAiEngine", " new mMibrainRequest = " + this.b.aj);
                c.d dVar = new c.d(this.f9925a, this.b.aj);
                this.b.aj.setRequestListener(dVar);
                if (this.b.ak == null) {
                    this.b.a((CharSequence) "startRequest released!");
                    SpeechError speechError5 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                    speechError5.V = "startRequest released!";
                    speechError5.U = -1;
                    speechError5.T = 2;
                    this.b.a(this.f9925a, speechError5);
                    return;
                }
                this.b.ak.a(this.b.aj);
                this.b.ak.a(dVar);
                int startRequest2 = this.b.aj.startRequest();
                if (startRequest2 < 0) {
                    String str2 = "startRequest failed: ret=" + startRequest2;
                    this.b.a((CharSequence) str2);
                    SpeechError speechError6 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                    speechError6.V = str2;
                    speechError6.U = startRequest2;
                    speechError6.T = 2;
                    this.b.a(this.f9925a, speechError6);
                    return;
                }
                if (this.f9925a.w) {
                    if (this.f9925a.y.length <= 32768) {
                        this.b.aj.addRawData(this.f9925a.y, 1, false, true);
                    } else {
                        int length = this.f9925a.y.length / 32768;
                        int length2 = this.f9925a.y.length % 32768;
                        int i = 0;
                        while (i < length) {
                            int i2 = i + 1;
                            this.b.aj.addRawData(Arrays.copyOfRange(this.f9925a.y, i * 32768, i2 * 32768), 1, false, i == length + -1 && length2 == 0);
                            i = i2;
                        }
                        if (length2 != 0) {
                            this.b.aj.addRawData(Arrays.copyOfRange(this.f9925a.y, length * 32768, this.f9925a.y.length), 1, false, true);
                        }
                    }
                    this.b.aj.addRawData(UploadHelper.a().getBytes(), 4, false, false);
                }
                c.a unused3 = this.b.ah = new c.a(this.b.aj, this.f9925a);
                dVar.a(this.b.ah);
                this.b.ah.start();
            } catch (JSONException e2) {
                e2.printStackTrace();
                this.b.a((CharSequence) "buildRequestMsg failed !!: ");
                SpeechError speechError7 = new SpeechError(SpeechEngine.ProcessStage.STAGE_REQUESTING);
                speechError7.V = "buildRequestMsg failed !!: ";
                speechError7.U = 1;
                speechError7.T = 2;
                this.b.a(this.f9925a, speechError7);
            }
        }
    }
}
