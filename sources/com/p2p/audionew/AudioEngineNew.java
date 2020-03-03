package com.p2p.audionew;

import android.content.Context;
import com.debug.SDKLog;
import com.p2p.audio.AudioFoucsTool;
import com.p2p.audionew.AudioProcessNew;
import com.tutk.IAudioSender;
import com.xiaomi.aaccodec.AACDecodeEx;
import com.xiaomi.aaccodec.AACEncodeEx;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioEngineNew {

    /* renamed from: a  reason: collision with root package name */
    public AudioProcessNew f8514a = null;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<RAudioFrame> c = new LinkedBlockingQueue<>(200);
    /* access modifiers changed from: private */
    public IAudioSender d;
    private int e;
    private volatile WorkThread f;
    private volatile WorkThread g;
    private Context h;
    private String i = "";
    private boolean j;
    private boolean k = true;
    private int l;
    private int m;
    private int n;

    public AudioEngineNew(Context context) {
        this.f8514a = new AudioProcessNew(context);
        this.h = context;
    }

    public void a() {
        AudioFoucsTool.a(this.h, "audioFoucs");
    }

    public void b() {
        AudioFoucsTool.b(this.h, "abandonFoucs");
    }

    public synchronized void c() {
        if (this.e != 1) {
            e();
            this.c.clear();
            this.f8514a.a();
            if (this.g == null) {
                this.g = new AudioDecodeThread();
                this.g.start();
            }
            this.e = 1;
        }
    }

    public synchronized void d() {
        if (this.e != 3) {
            e();
            if (this.f8514a != null) {
                this.f8514a.b();
            }
            if (this.g == null) {
                this.g = new AudioDecodeThread();
                this.g.start();
            }
            if (this.f == null) {
                this.f = new AudioEncodeSendThread();
                this.f.start();
            }
            this.e = 3;
        }
    }

    public synchronized void e() {
        this.e = 0;
        if (this.f8514a != null) {
            this.f8514a.c();
        }
        this.c.clear();
        if (this.g != null) {
            this.g.stopThreadAsyn();
            this.g = null;
        }
        if (this.f != null) {
            this.f.stopThreadAsyn();
            this.f = null;
        }
    }

    public void a(int i2) {
        this.f8514a.b(i2);
    }

    public void a(byte[] bArr, long j2, int i2, int i3) {
        if (this.e != 0) {
            RAudioFrame rAudioFrame = new RAudioFrame();
            rAudioFrame.f8521a = bArr;
            rAudioFrame.e = j2;
            rAudioFrame.f = i2;
            rAudioFrame.b = i3;
            try {
                this.c.put(rAudioFrame);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void f() {
        this.c.clear();
        if (this.f8514a != null) {
            this.f8514a.e();
        }
    }

    public void a(IAudioSender iAudioSender) {
        this.d = iAudioSender;
    }

    public void a(byte[] bArr, long j2, int i2, int i3, short s, short s2) {
        if (this.e != 0) {
            RAudioFrame rAudioFrame = new RAudioFrame();
            rAudioFrame.f8521a = bArr;
            rAudioFrame.e = j2;
            rAudioFrame.b = i3;
            rAudioFrame.d = s2;
            rAudioFrame.c = s;
            rAudioFrame.f = i2;
            try {
                this.c.put(rAudioFrame);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void a(int i2, int i3, int i4) {
        this.f8514a.a(i2, i4, i3);
        this.l = i2;
        this.m = i3;
        this.n = i4;
    }

    public void b(int i2) {
        this.b = i2;
        this.f8514a.a(i2);
    }

    class AudioDecodeThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        byte[] f8515a;
        AACDecodeEx b;

        public AudioDecodeThread() {
            super("AudioDecodeThread");
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            int i;
            try {
                SDKLog.b("Audio", "mAudioFrameQueue==" + AudioEngineNew.this.c.size());
                RAudioFrame rAudioFrame = (RAudioFrame) AudioEngineNew.this.c.take();
                if (rAudioFrame != null && this.mIsRunning) {
                    if (rAudioFrame.f == 138) {
                        i = G711.decode(rAudioFrame.f8521a, 0, rAudioFrame.f8521a.length, this.f8515a);
                    } else {
                        i = rAudioFrame.f == 136 ? this.b.decode(rAudioFrame.f8521a, 0, rAudioFrame.f8521a.length, this.f8515a, this.f8515a.length) : 0;
                    }
                    rAudioFrame.f = AudioEngineNew.this.b;
                    if (i > 0 && this.mIsRunning) {
                        byte[] bArr = new byte[i];
                        System.arraycopy(this.f8515a, 0, bArr, 0, i);
                        AudioProcessNew.AudioFrame audioFrame = new AudioProcessNew.AudioFrame(bArr, rAudioFrame);
                        if (AudioEngineNew.this.f8514a != null) {
                            AudioEngineNew.this.f8514a.a(audioFrame);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            this.f8515a = new byte[10240];
            this.b = new AACDecodeEx();
            this.b.initial();
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            try {
                this.f8515a = null;
                this.b.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class AudioEncodeSendThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        AACEncodeEx f8516a;
        ByteDataBuffer b;
        private byte[] d;
        private byte[] e;

        AudioEncodeSendThread() {
            super("AudioEncodeSendThread");
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            byte[] encode;
            byte[] encode2;
            if (!this.mIsRunning) {
                return 0;
            }
            AudioProcessNew.AudioFrame audioFrame = null;
            if (AudioEngineNew.this.f8514a != null) {
                audioFrame = AudioEngineNew.this.f8514a.d();
            }
            if (!(audioFrame == null || AudioEngineNew.this.d == null)) {
                this.b.b(audioFrame.f8518a);
                if (audioFrame.c == 1030) {
                    if (this.b.a(this.d) && (encode2 = this.f8516a.encode(this.d, 0, this.d.length)) != null) {
                        AudioEngineNew.this.d.onSendAudio(encode2, 136);
                    }
                } else if (this.b.a(this.e) && (encode = G711.encode(this.e, 0, this.e.length)) != null) {
                    AudioEngineNew.this.d.onSendAudio(encode, 138);
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            this.f8516a = new AACEncodeEx();
            this.f8516a.initial(RecordDevice.PCM_FREQUENCE_16K, 1, AsrRequest.d);
            this.d = new byte[2048];
            this.e = new byte[1280];
            this.b = new ByteDataBuffer();
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            if (this.f8516a != null) {
                this.f8516a.release();
                this.f8516a = null;
            }
            this.d = null;
            this.b.b();
            this.e = null;
            this.b = null;
        }
    }
}
