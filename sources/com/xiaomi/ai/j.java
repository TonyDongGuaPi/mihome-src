package com.xiaomi.ai;

import android.media.AudioTrack;
import com.xiaomi.ai.SpeechEngine;
import com.xiaomi.ai.streamplayer.k;

class j implements k.a {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f9926a;

    j(c cVar) {
        this.f9926a = cVar;
    }

    public void a(k kVar) {
        synchronized (this.f9926a.al) {
            if (kVar == this.f9926a.af && this.f9926a.af != null) {
                this.f9926a.z();
            }
        }
    }

    public void a(k kVar, int i, String str) {
        synchronized (this.f9926a.al) {
            if (kVar == this.f9926a.af && this.f9926a.af != null) {
                SpeechError speechError = new SpeechError(SpeechEngine.ProcessStage.STAGE_TTS);
                speechError.V = str;
                speechError.U = i;
                speechError.T = 2;
                this.f9926a.g(speechError);
            }
        }
    }

    public void a(k kVar, AudioTrack audioTrack) {
        synchronized (this.f9926a.al) {
            if (kVar == this.f9926a.af && this.f9926a.af != null) {
                this.f9926a.a(audioTrack);
            }
        }
    }

    public void a(k kVar, PCMInfo pCMInfo) {
        synchronized (this.f9926a.al) {
            if (kVar == this.f9926a.af && this.f9926a.af != null) {
                this.f9926a.a(pCMInfo);
            }
        }
    }
}
