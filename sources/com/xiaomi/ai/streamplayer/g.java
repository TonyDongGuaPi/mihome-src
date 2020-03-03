package com.xiaomi.ai.streamplayer;

import com.xiaomi.ai.mibrain.MibrainDecoder;
import com.xiaomi.ai.streamplayer.a;
import com.xiaomi.ai.utils.Log;
import java.io.IOException;

class g implements MibrainDecoder.MibrainDecoderIO {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9941a;

    g(f fVar) {
        this.f9941a = fVar;
    }

    public int read(byte[] bArr, int i) {
        try {
            return this.f9941a.c.read(bArr, 0, i);
        } catch (IOException e) {
            Log.a("Mp3SoftDecoder", "MibrainMp3Decoder read ", e);
            return -1;
        }
    }

    public int write(byte[] bArr, int i, MibrainDecoder.AudioInfo audioInfo) {
        a.C0083a aVar = new a.C0083a();
        aVar.b = audioInfo.chinels;
        aVar.c = audioInfo.bit;
        aVar.f9934a = audioInfo.sample_rate;
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        aVar.e = bArr2;
        try {
            this.f9941a.e.put(aVar);
        } catch (InterruptedException e) {
            Log.a("Mp3SoftDecoder", "e", e);
        }
        return i;
    }
}
