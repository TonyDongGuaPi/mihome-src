package com.p2p.audio;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.Process;
import android.text.TextUtils;
import com.debug.SDKLog;
import com.google.webrtc.apm.WebRtcJni;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.mistream.ClassTransUtils;
import com.xiaomi.smarthome.audioprocess.AudioUtils;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.audioprocess.WorkThread;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingQueue;

public class AudioProcess {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8508a = "AudioProcess";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 2048;
    static final int f = 8000;
    private static final int r = 10;
    /* access modifiers changed from: private */
    public int A;
    /* access modifiers changed from: private */
    public int B = 18;
    /* access modifiers changed from: private */
    public boolean C = false;
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<AudioFrame> D = new LinkedBlockingQueue<>(15);
    /* access modifiers changed from: private */
    public LinkedBlockingQueue<AudioFrame> E = new LinkedBlockingQueue<>(15);
    WebRtcJni.WebRtcAecm g = null;
    ByteDataBuffer h = null;
    private volatile int i;
    private int j = 0;
    private final int k = 0;
    private final int l = 1;
    private final int m = 2;
    private final int n = 3;
    /* access modifiers changed from: private */
    public int o;
    private AudioPlayThread p;
    private AudioRecordThread q;
    private int s;
    /* access modifiers changed from: private */
    public volatile int t = 0;
    /* access modifiers changed from: private */
    public volatile int u = 0;
    /* access modifiers changed from: private */
    public AudioManager v;
    /* access modifiers changed from: private */
    public int w = 60;
    /* access modifiers changed from: private */
    public String x;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public ConditionVariable z = new ConditionVariable();

    public static class AudioFrame {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f8509a;
        public long b = 0;
        public int c;

        public AudioFrame(byte[] bArr, long j, int i) {
            this.f8509a = bArr;
            this.b = j;
            this.c = i;
        }
    }

    public void a(int i2) {
        this.o = i2;
        if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(this.x)) {
            this.A = 640;
        } else {
            this.A = ((i2 * 2) * 10) / 1000;
        }
    }

    public AudioProcess(Context context) {
        this.v = (AudioManager) context.getSystemService("audio");
        i();
    }

    public AudioProcess(Context context, String str) {
        this.x = str;
        this.v = (AudioManager) context.getSystemService("audio");
        i();
    }

    public AudioProcess(Context context, String str, int i2) {
        this.x = str;
        this.v = (AudioManager) context.getSystemService("audio");
        i();
    }

    public synchronized void b(int i2) {
        LogUtil.a("AudioProcess", "setAudioMode mode = " + i2);
        if (this.i != i2) {
            this.i = i2;
            if (this.j == 3) {
                d();
                c();
            }
        }
    }

    private void i() {
        if (this.v.isWiredHeadsetOn()) {
            this.i = 2;
        } else {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null || defaultAdapter.getProfileConnectionState(1) != 2) {
                this.i = 1;
            } else {
                this.i = 3;
            }
        }
        LogUtil.a("AudioProcess", "initAudioMode mode = " + this.i);
    }

    /* access modifiers changed from: private */
    public void j() {
        if (this.i == 1) {
            this.v.stopBluetoothSco();
            this.v.setBluetoothScoOn(false);
            this.v.setSpeakerphoneOn(true);
        } else if (this.i == 2) {
            this.v.stopBluetoothSco();
            this.v.setBluetoothScoOn(false);
            this.v.setSpeakerphoneOn(false);
        } else if (this.i == 3) {
            this.v.startBluetoothSco();
            this.v.setBluetoothScoOn(true);
            this.v.setSpeakerphoneOn(false);
        }
    }

    public synchronized void a() {
        LogUtil.a("AudioProcess", "startPlay");
        this.j = 1;
        if (this.p == null) {
            this.p = new AudioPlayThread();
            this.p.start();
        }
    }

    public synchronized void b() {
        LogUtil.a("AudioProcess", "startSpeaking");
        this.j = 2;
        if (this.q == null) {
            this.q = new AudioRecordThread();
            this.q.start();
        }
    }

    public void c() {
        d();
        LogUtil.a("AudioProcess", "startCall");
        this.y = true;
        this.j = 3;
        this.z.close();
        if (this.q == null) {
            this.q = new AudioRecordThread();
            this.q.start();
        }
        if (this.p == null) {
            this.p = new AudioPlayThread();
            this.p.start();
        }
    }

    public synchronized void d() {
        LogUtil.a("AudioProcess", "stop");
        try {
            if (this.y) {
                this.v.setSpeakerphoneOn(false);
            }
            this.y = false;
            this.j = 0;
            this.D.clear();
            this.E.clear();
            if (this.p != null) {
                this.p.stopThreadAsyn();
                this.p = null;
            }
            if (this.q != null) {
                this.q.stopThreadAsyn();
                this.q = null;
            }
        } catch (Exception unused) {
        }
    }

    public void a(AudioFrame audioFrame) throws InterruptedException {
        if (this.p != null && this.p.isRunning()) {
            if (this.D.size() > 10) {
                this.D.clear();
            }
            this.D.put(audioFrame);
        }
    }

    public AudioFrame e() throws InterruptedException {
        return this.E.take();
    }

    public void f() {
        this.D.clear();
    }

    public int g() {
        return this.u;
    }

    public void h() {
        if (this.h != null) {
            this.h.b();
        }
        this.B = 18;
    }

    public void a(boolean z2) {
        this.C = z2;
    }

    private class AudioPlayThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        AudioTrack f8510a;
        byte[] b = null;
        ByteDataBuffer c = null;
        ByteBuffer d = null;
        private int f;

        AudioPlayThread() {
            super("AudioPlayThread");
        }

        /* access modifiers changed from: protected */
        public void doInitial() {
            LogUtil.a("AudioProcess", "AudioPlayThread doInitial");
            Process.setThreadPriority(-19);
            a();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.f8510a == null) {
                if (AudioProcess.this.o == 8000) {
                    this.f = 138;
                } else {
                    this.f = 136;
                }
                if (TextUtils.isEmpty(AudioProcess.this.x) || !"mijia.camera.v3".equals(AudioProcess.this.x)) {
                    AudioProcess.this.g = new WebRtcJni.WebRtcAecm(AudioProcess.this.o, false, 2);
                } else {
                    AudioProcess.this.g = new WebRtcJni.WebRtcAecm(AudioProcess.this.o, false, 3);
                }
                if (!TextUtils.isEmpty(AudioProcess.this.x) && ("chuangmi.camera.ipc019".equals(AudioProcess.this.x) || DeviceConstant.CHUANGMI_CAMERA_021.equals(AudioProcess.this.x))) {
                    this.f = 138;
                }
                if (AudioProcess.this.y) {
                    AudioProcess.this.z.block(Constants.x);
                }
                int minBufferSize = AudioTrack.getMinBufferSize(AudioProcess.this.o, 4, 2);
                LogUtil.a("AudioProcess", "AudioTrack minSize:" + minBufferSize + " rate " + AudioProcess.this.o);
                int i = AudioProcess.this.y ? 0 : 3;
                try {
                    if (AudioProcess.this.t > 0) {
                        this.f8510a = new AudioTrack(i, AudioProcess.this.o, 4, 2, minBufferSize, 1, AudioProcess.this.t);
                    } else {
                        this.f8510a = new AudioTrack(i, AudioProcess.this.o, 4, 2, minBufferSize, 1);
                    }
                    if (AudioProcess.this.y) {
                        AudioProcess.this.j();
                    }
                    this.f8510a.play();
                } catch (Exception e2) {
                    LogUtil.b("AudioProcess", "AudioTrack init" + e2);
                    this.f8510a = null;
                }
                if (this.c != null) {
                    this.c.b();
                }
                this.c = new ByteDataBuffer();
                AudioProcess.this.D.clear();
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
        public void b() {
            if (this.f8510a != null) {
                this.f8510a.flush();
                this.f8510a.release();
                this.f8510a = null;
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
            int i2;
            if (!this.mIsRunning) {
                return 0;
            }
            boolean h = AudioProcess.this.C;
            int i3 = RecordDevice.PCM_FREQUENCE_16K;
            int i4 = 8000;
            if (!h || !DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(AudioProcess.this.x)) {
                if (!this.c.a(this.b) || this.d == null) {
                    AudioFrame audioFrame = (AudioFrame) AudioProcess.this.D.take();
                    if (audioFrame != null) {
                        if (audioFrame.c <= 0 || this.f == audioFrame.c) {
                            if (this.d == null) {
                                a(audioFrame.f8509a.length);
                            }
                            this.c.b(audioFrame.f8509a);
                        } else {
                            b();
                            AudioProcess audioProcess = AudioProcess.this;
                            if (audioFrame.c == 136) {
                                i4 = RecordDevice.PCM_FREQUENCE_16K;
                            }
                            int unused = audioProcess.o = i4;
                            if (!TextUtils.isEmpty(AudioProcess.this.x) && ("chuangmi.camera.ipc019".equals(AudioProcess.this.x) || DeviceConstant.CHUANGMI_CAMERA_021.equals(AudioProcess.this.x))) {
                                int unused2 = AudioProcess.this.o = RecordDevice.PCM_FREQUENCE_16K;
                            }
                            LogUtil.a("AudioProcess", "change type " + AudioProcess.this.o + " " + audioFrame.c + " buffer " + audioFrame.f8509a.length);
                            a();
                            a(audioFrame.f8509a.length);
                        }
                    }
                } else {
                    if (AudioUtils.d()) {
                        this.d.clear();
                        this.d.put(this.b);
                        this.d.rewind();
                        i = this.f8510a.write(this.d, this.d.capacity(), 0);
                    } else {
                        i = this.f8510a.write(this.b, 0, this.b.length);
                    }
                    if (i != this.b.length) {
                        LogUtil.b("AudioProcess", "audioTrack.write size error:" + i);
                    }
                }
                return 0;
            }
            SDKLog.b("xm111", "音频缓存的buffer大小=" + this.c.c() + "，cacheAudioLength=" + AudioProcess.this.B);
            if (this.c.c() >= AudioProcess.this.B) {
                if (!this.c.a(this.b) || this.d == null) {
                    try {
                        AudioFrame audioFrame2 = (AudioFrame) AudioProcess.this.D.take();
                        if (audioFrame2 != null) {
                            if (audioFrame2.c <= 0 || this.f == audioFrame2.c) {
                                if (this.d == null) {
                                    a(audioFrame2.f8509a.length);
                                }
                                this.c.b(audioFrame2.f8509a);
                            } else {
                                b();
                                int unused3 = AudioProcess.this.o = audioFrame2.c == 136 ? RecordDevice.PCM_FREQUENCE_16K : 8000;
                                if (AudioProcess.this.o == 8000) {
                                    int unused4 = AudioProcess.this.o = 8000;
                                }
                                a();
                                a(audioFrame2.f8509a.length);
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        LogUtil.a("AudioProcess", "e==" + e2.getMessage());
                    }
                } else {
                    int unused5 = AudioProcess.this.B = 0;
                    if (AudioUtils.d()) {
                        this.d.clear();
                        this.d.put(this.b);
                        this.d.rewind();
                        SDKLog.b("HuangHaoJie", "播放缓存音频数据");
                        i2 = this.f8510a.write(this.d, this.d.capacity(), 0);
                    } else {
                        SDKLog.b("HuangHaoJie", "播放缓存音频数据22222");
                        i2 = this.f8510a.write(this.b, 0, this.b.length);
                    }
                    if (i2 != this.b.length) {
                        SDKLog.d("AudioProcess", "audioTrack.write size error:" + i2);
                    }
                }
            }
            try {
                if (AudioProcess.this.D.size() > 0) {
                    AudioFrame audioFrame3 = (AudioFrame) AudioProcess.this.D.take();
                    SDKLog.b("HuangHaoJie", "buffer 缓存中的数据小于 15 put");
                    if (audioFrame3 != null) {
                        if (audioFrame3.c <= 0 || this.f == audioFrame3.c) {
                            if (this.d == null) {
                                a(audioFrame3.f8509a.length);
                            }
                            this.c.b(audioFrame3.f8509a);
                        } else {
                            b();
                            AudioProcess audioProcess2 = AudioProcess.this;
                            if (audioFrame3.c != 136) {
                                i3 = 8000;
                            }
                            int unused6 = audioProcess2.o = i3;
                            if (AudioProcess.this.o == 8000) {
                                int unused7 = AudioProcess.this.o = 8000;
                            }
                            a();
                            a(audioFrame3.f8509a.length);
                        }
                    }
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                LogUtil.a("AudioProcess", "e==" + e3.getMessage());
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public void doRelease() {
            LogUtil.a("AudioProcess", "AudioPlayThread doRelease");
            b();
        }
    }

    private class AudioRecordThread extends WorkThread {

        /* renamed from: a  reason: collision with root package name */
        int f8511a;
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
                if (AudioProcess.this.y) {
                    AudioProcess.this.v.setMode(3);
                }
                AudioProcess.this.E.clear();
                this.f8511a = AudioRecord.getMinBufferSize(AudioProcess.this.o, 16, 2);
                if (AudioProcess.this.A > this.f8511a) {
                    this.f8511a = AudioProcess.this.A;
                }
                LogUtil.b("AudioProcess", "AudioRecord mini size :" + this.f8511a);
                this.b = new byte[AudioProcess.this.A];
                try {
                    this.d = new AudioRecord(AudioProcess.this.y ? 7 : 1, AudioProcess.this.o, 16, 2, this.f8511a);
                    try {
                        this.d.startRecording();
                        if (AudioProcess.this.y) {
                            AudioProcess.this.z.open();
                        }
                        LogUtil.a("AudioProcess", "init Record success");
                    } catch (Exception e) {
                        this.d = null;
                        LogUtil.b("AudioProcess", "AudioRecord initial " + e.toString());
                    }
                } catch (IllegalArgumentException e2) {
                    this.d = null;
                    int minBufferSize = AudioRecord.getMinBufferSize(AudioProcess.this.o, 16, 2);
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
            if (AudioProcess.this.v != null) {
                AudioProcess.this.v.setMode(0);
            }
        }

        /* access modifiers changed from: protected */
        public int doRepeatWork() throws InterruptedException {
            AudioFrame audioFrame;
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
                    int unused = AudioProcess.this.u = d4 > 0.0d ? (int) (Math.log10(d4) * 10.0d) : 50;
                    if (AudioProcess.this.y && this.mIsRunning) {
                        WebRtcJni.WebRtcAecm webRtcAecm = AudioProcess.this.g;
                        long currentTimeMillis = System.currentTimeMillis();
                        int i2 = 138;
                        if (TextUtils.isEmpty(AudioProcess.this.x) || !"chuangmi.camera.ipc019".equals(AudioProcess.this.x)) {
                            if (AudioProcess.this.o != 8000) {
                                i2 = 136;
                            }
                            audioFrame = new AudioFrame(bArr, currentTimeMillis, i2);
                        } else {
                            audioFrame = new AudioFrame(bArr, currentTimeMillis, 138);
                        }
                        AudioProcess.this.E.put(audioFrame);
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
