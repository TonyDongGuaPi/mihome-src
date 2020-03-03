package com.p2p.audionew;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.Process;
import android.text.TextUtils;
import com.google.webrtc.apm.WebRtcJni;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.mistream.ClassTransUtils;
import com.xiaomi.smarthome.audioprocess.AudioUtils;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.download.Constants;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioProcessNew {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8517a = "AudioProcess";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    static final int e = 8000;
    private static final int g = 10;
    /* access modifiers changed from: private */
    public int A;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<AudioFrame> B = new LinkedBlockingQueue<>(15);
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<AudioFrame> C = new LinkedBlockingQueue<>(15);
    private ByteDataBuffer D = null;
    WebRtcJni.WebRtcAecm f = null;
    private final int h = 0;
    private final int i = 1;
    private final int j = 2;
    private final int k = 3;
    /* access modifiers changed from: private */
    public int l;
    private volatile int m;
    private int n = 0;
    private AudioPlayThread o;
    private AudioRecordThread p;
    /* access modifiers changed from: private */
    public volatile int q = 0;
    /* access modifiers changed from: private */
    public volatile int r = 0;
    /* access modifiers changed from: private */
    public AudioManager s;
    /* access modifiers changed from: private */
    public int t = 60;
    /* access modifiers changed from: private */
    public String u;
    /* access modifiers changed from: private */
    public boolean v = false;
    /* access modifiers changed from: private */
    public ConditionVariable w = new ConditionVariable();
    /* access modifiers changed from: private */
    public int x = -1;
    /* access modifiers changed from: private */
    public int y = -1;
    /* access modifiers changed from: private */
    public int z = -1;

    public AudioProcessNew(Context context) {
        this.s = (AudioManager) context.getSystemService("audio");
        h();
    }

    public void a(int i2) {
        this.l = i2;
    }

    public void a(int i2, int i3, int i4) {
        this.x = i4;
        this.y = i2;
        this.z = i3;
        this.A = (((i2 == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K) * 2) * 10) / 1000;
    }

    public synchronized void b(int i2) {
        LogUtil.a("AudioProcess", "setAudioMode mode = " + i2);
        if (this.m != i2) {
            this.m = i2;
            if (this.n == 3) {
                c();
                b();
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    private void h() {
        if (this.s.isWiredHeadsetOn()) {
            this.m = 2;
        } else {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || defaultAdapter.getProfileConnectionState(1) != 2) {
                this.m = 1;
            } else {
                this.m = 3;
            }
        }
        LogUtil.a("AudioProcess", "initAudioMode mode = " + this.m);
    }

    /* access modifiers changed from: private */
    public void i() {
        if (this.m == 1) {
            this.s.stopBluetoothSco();
            this.s.setBluetoothScoOn(false);
            this.s.setSpeakerphoneOn(true);
        } else if (this.m == 2) {
            this.s.stopBluetoothSco();
            this.s.setBluetoothScoOn(false);
            this.s.setSpeakerphoneOn(false);
        } else if (this.m == 3) {
            this.s.startBluetoothSco();
            this.s.setBluetoothScoOn(true);
            this.s.setSpeakerphoneOn(false);
        }
    }

    public synchronized void a() {
        LogUtil.a("AudioProcess", "startPlay");
        this.n = 1;
        if (this.o == null) {
            this.o = new AudioPlayThread();
            this.o.start();
        }
    }

    public void b() {
        c();
        LogUtil.a("AudioProcess", "startCall");
        this.v = true;
        this.n = 3;
        this.w.close();
        if (this.p == null) {
            this.p = new AudioRecordThread();
            this.p.start();
        }
        if (this.o == null) {
            this.o = new AudioPlayThread();
            this.o.start();
        }
    }

    public synchronized void c() {
        LogUtil.a("AudioProcess", "stop");
        try {
            if (this.v) {
                this.s.setSpeakerphoneOn(false);
            }
            this.v = false;
            this.n = 0;
            this.B.clear();
            this.C.clear();
            if (this.o != null) {
                this.o.stopThreadAsyn();
                this.o = null;
            }
            if (this.p != null) {
                this.p.stopThreadAsyn();
                this.p = null;
            }
        } catch (Exception unused) {
        }
    }

    public void a(AudioFrame audioFrame) throws InterruptedException {
        if (this.o != null && this.o.isRunning()) {
            if (this.B.size() > 10) {
                this.B.clear();
            }
            this.B.put(audioFrame);
        }
    }

    public AudioFrame d() throws InterruptedException {
        return this.C.take();
    }

    public void e() {
        this.B.clear();
    }

    public int f() {
        return this.r;
    }

    public void g() {
        if (this.D != null) {
            this.D.b();
        }
    }

    public static class AudioFrame {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f8518a;
        public long b = 0;
        public int c;
        public int d;
        public int e;
        public int f;

        public AudioFrame(byte[] bArr, RAudioFrame rAudioFrame) {
            this.f8518a = bArr;
            this.b = rAudioFrame.e;
            this.c = rAudioFrame.f;
            this.d = rAudioFrame.b;
            this.e = rAudioFrame.c;
            this.f = rAudioFrame.d;
        }

        public AudioFrame(byte[] bArr, long j, int i) {
            this.f8518a = bArr;
            this.b = j;
            this.c = i;
        }
    }

    private class AudioPlayThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        AudioTrack f8519a;
        byte[] b = null;
        ByteDataBuffer c = null;
        ByteBuffer d = null;
        private int f = -1;
        private int g = -1;
        private int h = -1;

        AudioPlayThread() {
            super("AudioPlayThread");
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            LogUtil.a("AudioProcess", "AudioPlayThread doInitial");
            Process.setThreadPriority(-19);
        }

        /* access modifiers changed from: package-private */
        public void a(AudioFrame audioFrame) {
            AudioFrame audioFrame2 = audioFrame;
            if (audioFrame2 != null && this.f8519a == null) {
                this.f = audioFrame2.d;
                this.g = audioFrame2.e;
                this.h = audioFrame2.f;
                int i = this.f == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K;
                if (TextUtils.isEmpty(AudioProcessNew.this.u) || !"mijia.camera.v3".equals(AudioProcessNew.this.u)) {
                    AudioProcessNew.this.f = new WebRtcJni.WebRtcAecm(i, false, 2);
                } else {
                    AudioProcessNew.this.f = new WebRtcJni.WebRtcAecm(i, false, 3);
                }
                if (AudioProcessNew.this.v) {
                    AudioProcessNew.this.w.block(Constants.x);
                }
                int minBufferSize = AudioTrack.getMinBufferSize(this.f == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K, this.h == 0 ? 4 : 12, this.g == 0 ? 3 : 2);
                LogUtil.a("AudioProcess", "AudioTrack minSize:" + minBufferSize + " rate " + i);
                int i2 = AudioProcessNew.this.v ? 0 : 3;
                try {
                    if (AudioProcessNew.this.q > 0) {
                        this.f8519a = new AudioTrack(i2, this.f == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K, this.h == 0 ? 4 : 12, this.g == 0 ? 3 : 2, minBufferSize, 1, AudioProcessNew.this.q);
                    } else {
                        this.f8519a = new AudioTrack(i2, this.f == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K, this.h == 0 ? 4 : 12, this.g == 0 ? 3 : 2, minBufferSize, 1);
                    }
                    if (AudioProcessNew.this.v) {
                        AudioProcessNew.this.i();
                    }
                    this.f8519a.play();
                } catch (Exception e2) {
                    LogUtil.b("AudioProcess", "AudioTrack init" + e2);
                    this.f8519a = null;
                }
                if (this.c != null) {
                    this.c.b();
                }
                this.c = new ByteDataBuffer();
                AudioProcessNew.this.B.clear();
            }
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            LogUtil.a("AudioProcess", "sample buffer " + i);
            if (this.c != null) {
                this.c.b();
            }
            this.b = new byte[i];
            if (this.d != null) {
                this.d.clear();
            }
            this.d = ByteBuffer.allocateDirect(i);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.f8519a != null) {
                this.f8519a.flush();
                this.f8519a.release();
                this.f8519a = null;
            }
            if (this.c != null) {
                this.c.b();
                this.c = null;
            }
            this.b = null;
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            int i;
            if (!this.mIsRunning) {
                return 0;
            }
            if (this.c == null || !this.c.a(this.b) || this.d == null) {
                AudioFrame audioFrame = (AudioFrame) AudioProcessNew.this.B.take();
                if (audioFrame != null) {
                    if (this.f == audioFrame.d && this.g == audioFrame.e && this.h == audioFrame.f) {
                        if (this.d == null) {
                            a(audioFrame.f8518a.length);
                        }
                        this.c.b(audioFrame.f8518a);
                    } else {
                        a();
                        a(audioFrame);
                        a(audioFrame.f8518a.length);
                    }
                }
            } else {
                if (AudioUtils.d()) {
                    this.d.clear();
                    this.d.put(this.b);
                    this.d.rewind();
                    i = this.f8519a.write(this.d, this.d.capacity(), 0);
                } else {
                    i = this.f8519a.write(this.b, 0, this.b.length);
                }
                if (i != this.b.length) {
                    LogUtil.b("AudioProcess", "audioTrack.write size error:" + i);
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            LogUtil.a("AudioProcess", "AudioPlayThread doRelease");
            a();
        }
    }

    private class AudioRecordThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        int f8520a;
        byte[] b;
        private AudioRecord d;

        AudioRecordThread() {
            super("AudioRecordThread");
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            LogUtil.a("AudioProcess", "AudioRecordThread doInitial");
            Process.setThreadPriority(-19);
            a();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.d == null) {
                if (AudioProcessNew.this.v) {
                    AudioProcessNew.this.s.setMode(3);
                }
                AudioProcessNew.this.C.clear();
                this.f8520a = AudioRecord.getMinBufferSize(AudioProcessNew.this.y == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K, AudioProcessNew.this.z == 0 ? 16 : 12, AudioProcessNew.this.x == 0 ? 3 : 2);
                if (AudioProcessNew.this.A > this.f8520a) {
                    this.f8520a = AudioProcessNew.this.A;
                }
                LogUtil.b("AudioProcess", "AudioRecord mini size :" + this.f8520a);
                this.b = new byte[AudioProcessNew.this.A];
                try {
                    this.d = new AudioRecord(AudioProcessNew.this.v ? 7 : 1, AudioProcessNew.this.y == 0 ? 8000 : RecordDevice.PCM_FREQUENCE_16K, AudioProcessNew.this.z == 0 ? 16 : 12, AudioProcessNew.this.x == 0 ? 3 : 2, this.f8520a);
                    try {
                        this.d.startRecording();
                        if (AudioProcessNew.this.v) {
                            AudioProcessNew.this.w.open();
                        }
                        LogUtil.a("AudioProcess", "init Record success");
                    } catch (Exception e) {
                        this.d = null;
                        LogUtil.b("AudioProcess", "AudioRecord initial " + e.toString());
                    }
                } catch (IllegalArgumentException e2) {
                    this.d = null;
                    int minBufferSize = AudioRecord.getMinBufferSize(8000, 16, 2);
                    LogUtil.b("AudioProcess", "AudioRecord init Error  min size" + minBufferSize + "   Exception:" + e2.toString());
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (this.d != null) {
                this.d.release();
                this.d = null;
            }
            this.b = null;
            if (AudioProcessNew.this.s != null) {
                AudioProcessNew.this.s.setMode(0);
            }
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            if (this.mIsRunning && this.d != null) {
                int read = this.d.read(this.b, 0, this.b.length);
                if (read != this.b.length) {
                    LogUtil.b("AudioProcess", "error record:" + read);
                }
                if (read > 0) {
                    byte[] bArr = new byte[read];
                    System.arraycopy(this.b, 0, bArr, 0, read);
                    short[] bytes2Shorts = ClassTransUtils.getInstance().bytes2Shorts(bArr);
                    long j = 0;
                    for (int i = 0; i < bytes2Shorts.length; i++) {
                        j += (long) (bytes2Shorts[i] * bytes2Shorts[i]);
                    }
                    double d2 = (double) j;
                    double d3 = (double) read;
                    Double.isNaN(d2);
                    Double.isNaN(d3);
                    double d4 = d2 / d3;
                    int unused = AudioProcessNew.this.r = d4 > 0.0d ? (int) (Math.log10(d4) * 10.0d) : 50;
                    if (AudioProcessNew.this.v && this.mIsRunning) {
                        if (AudioProcessNew.this.f != null) {
                            short[] process = AudioProcessNew.this.f.process(bytes2Shorts, (short[]) null, bytes2Shorts.length, AudioProcessNew.this.t);
                            AudioProcessNew.this.f.bufferFarend(process, process.length);
                            if (process != null) {
                                bArr = ClassTransUtils.getInstance().shorts2Bytes(process);
                            } else {
                                LogUtil.b("AudioProcess", "aecOutShorts null");
                            }
                            AudioProcessNew.this.C.put(new AudioFrame(bArr, System.currentTimeMillis(), AudioProcessNew.this.l));
                        } else {
                            AudioProcessNew.this.C.put(new AudioFrame(bArr, System.currentTimeMillis(), AudioProcessNew.this.l));
                        }
                    }
                }
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            b();
            LogUtil.a("AudioProcess", "AudioRecordThread doRelease");
        }
    }
}
