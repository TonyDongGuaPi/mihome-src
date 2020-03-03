package com.p2p.audio;

import android.content.Context;
import com.debug.SDKLog;
import com.p2p.audio.AudioProcess;
import com.tutk.IAudioSender;
import com.xiaomi.aaccodec.AACDecodeEx;
import com.xiaomi.aaccodec.AACEncodeEx;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.ai.AsrRequest;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioEngine {

    /* renamed from: a  reason: collision with root package name */
    public AudioProcess f8504a = null;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<SAudioFrame> b = new LinkedBlockingQueue<>(200);
    /* access modifiers changed from: private */
    public IAudioSender c;
    private int d;
    private volatile WorkThread e;
    private volatile WorkThread f;
    private Context g;
    private String h = "";
    /* access modifiers changed from: private */
    public boolean i;

    public AudioEngine(Context context, int i2) {
        this.f8504a = new AudioProcess(context);
        this.f8504a.a(i2);
        this.g = context;
    }

    public AudioEngine(Context context, int i2, String str) {
        this.f8504a = new AudioProcess(context, str);
        this.f8504a.a(i2);
        this.g = context;
        this.h = str;
        this.i = DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equalsIgnoreCase(str);
    }

    public void a() {
        AudioFoucsTool.a(this.g, "audioFoucs");
    }

    public void b() {
        AudioFoucsTool.b(this.g, "abandonFoucs");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0038, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() {
        /*
            r3 = this;
            monitor-enter(r3)
            int r0 = r3.d     // Catch:{ all -> 0x0039 }
            r1 = 1
            if (r0 != r1) goto L_0x0008
            monitor-exit(r3)
            return
        L_0x0008:
            int r0 = r3.d     // Catch:{ all -> 0x0039 }
            r2 = 3
            if (r0 != r2) goto L_0x0018
            java.util.concurrent.LinkedBlockingQueue<com.p2p.audio.SAudioFrame> r0 = r3.b     // Catch:{ all -> 0x0039 }
            r0.clear()     // Catch:{ all -> 0x0039 }
            com.p2p.audio.AudioProcess r0 = r3.f8504a     // Catch:{ all -> 0x0039 }
            r0.a()     // Catch:{ all -> 0x0039 }
            goto L_0x0037
        L_0x0018:
            r3.f()     // Catch:{ all -> 0x0039 }
            java.util.concurrent.LinkedBlockingQueue<com.p2p.audio.SAudioFrame> r0 = r3.b     // Catch:{ all -> 0x0039 }
            r0.clear()     // Catch:{ all -> 0x0039 }
            com.p2p.audio.AudioProcess r0 = r3.f8504a     // Catch:{ all -> 0x0039 }
            r0.a()     // Catch:{ all -> 0x0039 }
            com.xiaomi.smarthome.audioprocess.WorkThread r0 = r3.f     // Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x0035
            com.p2p.audio.AudioEngine$AudioDecodeThread r0 = new com.p2p.audio.AudioEngine$AudioDecodeThread     // Catch:{ all -> 0x0039 }
            r0.<init>()     // Catch:{ all -> 0x0039 }
            r3.f = r0     // Catch:{ all -> 0x0039 }
            com.xiaomi.smarthome.audioprocess.WorkThread r0 = r3.f     // Catch:{ all -> 0x0039 }
            r0.start()     // Catch:{ all -> 0x0039 }
        L_0x0035:
            r3.d = r1     // Catch:{ all -> 0x0039 }
        L_0x0037:
            monitor-exit(r3)
            return
        L_0x0039:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p2p.audio.AudioEngine.c():void");
    }

    public synchronized void d() {
        if (this.d != 2) {
            f();
            this.f8504a.b();
            if (this.e == null) {
                this.e = new AudioEncodeSendThread();
                this.e.start();
            }
            this.d = 2;
        }
    }

    public synchronized void e() {
        if (this.d != 3) {
            f();
            if (this.f8504a != null) {
                this.f8504a.c();
            }
            if (this.f == null) {
                this.f = new AudioDecodeThread();
                this.f.start();
            }
            if (this.e == null) {
                this.e = new AudioEncodeSendThread();
                this.e.start();
            }
            this.d = 3;
        }
    }

    public synchronized void f() {
        this.d = 0;
        if (this.f8504a != null) {
            this.f8504a.d();
        }
        this.b.clear();
        if (this.f != null) {
            this.f.stopThreadAsyn();
            this.f = null;
        }
        if (this.e != null) {
            this.e.stopThreadAsyn();
            this.e = null;
        }
    }

    public void a(int i2) {
        this.f8504a.b(i2);
    }

    public void a(byte[] bArr, long j, int i2) {
        if (this.d != 0) {
            SAudioFrame sAudioFrame = new SAudioFrame();
            sAudioFrame.f8512a = bArr;
            sAudioFrame.c = j;
            sAudioFrame.b = i2;
            try {
                this.b.put(sAudioFrame);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public void g() {
        this.b.clear();
        if (this.f8504a != null) {
            this.f8504a.f();
        }
    }

    public void a(IAudioSender iAudioSender) {
        this.c = iAudioSender;
    }

    class AudioDecodeThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        byte[] f8505a;
        AACDecodeEx b;

        public AudioDecodeThread() {
            super("AudioDecodeThread");
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            int i;
            try {
                SDKLog.b("Audio", "mAudioFrameQueue==" + AudioEngine.this.b.size());
                SAudioFrame sAudioFrame = (SAudioFrame) AudioEngine.this.b.take();
                if (sAudioFrame != null && this.mIsRunning) {
                    if (sAudioFrame.b != 136) {
                        i = G711.decode(sAudioFrame.f8512a, 0, sAudioFrame.f8512a.length, this.f8505a);
                    } else {
                        i = this.b.decode(sAudioFrame.f8512a, 0, sAudioFrame.f8512a.length, this.f8505a, this.f8505a.length);
                        sAudioFrame.b = 136;
                    }
                    if (i > 0 && this.mIsRunning) {
                        byte[] bArr = new byte[i];
                        System.arraycopy(this.f8505a, 0, bArr, 0, i);
                        AudioProcess.AudioFrame audioFrame = new AudioProcess.AudioFrame(bArr, sAudioFrame.c, sAudioFrame.b);
                        if (AudioEngine.this.f8504a != null) {
                            AudioEngine.this.f8504a.a(audioFrame);
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
            this.f8505a = new byte[10240];
            this.b = new AACDecodeEx();
            this.b.initial();
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            try {
                this.f8505a = null;
                this.b.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class AudioEncodeSendThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        AACEncodeEx f8506a;
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
            AudioProcess.AudioFrame audioFrame = null;
            if (AudioEngine.this.f8504a != null) {
                audioFrame = AudioEngine.this.f8504a.e();
            }
            if (!(audioFrame == null || AudioEngine.this.c == null)) {
                this.b.b(audioFrame.f8509a);
                if (audioFrame.c == 136) {
                    if (this.b.a(this.d) && (encode2 = this.f8506a.encode(this.d, 0, this.d.length)) != null) {
                        AudioEngine.this.c.onSendAudio(encode2, 136);
                    }
                } else if (AudioEngine.this.i) {
                    SDKLog.b("Hunag", "decode AUDIO_CODEC_PCM audioFrame.data=" + audioFrame.f8509a.length);
                    AudioEngine.this.c.onSendAudio(audioFrame.f8509a, 1024);
                } else if (this.b.a(this.e) && (encode = G711.encode(this.e, 0, this.e.length)) != null) {
                    AudioEngine.this.c.onSendAudio(encode, 138);
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            this.f8506a = new AACEncodeEx();
            this.f8506a.initial(RecordDevice.PCM_FREQUENCE_16K, 1, AsrRequest.d);
            this.d = new byte[2048];
            this.e = new byte[1280];
            this.b = new ByteDataBuffer();
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            if (this.f8506a != null) {
                this.f8506a.release();
                this.f8506a = null;
            }
            this.d = null;
            this.b.b();
            this.e = null;
            this.b = null;
        }
    }
}
