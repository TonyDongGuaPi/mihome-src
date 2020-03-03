package com.p2p.play;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.debug.SDKLog;
import com.p2p.audio.AudioEngine;
import com.tutk.IAudioSender;
import com.tutk.IOTC.Packet;
import com.xiaomi.aaccodec.AACEncodeEx;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.camera.AVFrame;
import com.xiaomi.smarthome.camera.IClientListener;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmCameraP2p;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;

public class P2pCameraPlayer implements IClientListener {
    private static final String c = "P2pCameraPlayer";
    private volatile long A = 0;
    private volatile int B = 0;
    private boolean C = false;
    private volatile long D;
    private volatile boolean E;
    /* access modifiers changed from: private */
    public int F = 1;
    private long G = 0;
    private volatile int H;
    private volatile int I;
    private int J;
    private Context K;
    private BroadcastReceiver L = null;

    /* renamed from: a  reason: collision with root package name */
    protected Handler f8525a = new Handler(Looper.getMainLooper());
    protected boolean b = false;
    /* access modifiers changed from: private */
    public XmCameraP2p d;
    /* access modifiers changed from: private */
    public XmVideoViewGl e;
    private P2pCameraDevice f;
    private IResolutionChangedListener g;
    /* access modifiers changed from: private */
    public ICameraPlayerListener h;
    private IVideoLiveListener i;
    private IRecordTimeListener j;
    /* access modifiers changed from: private */
    public AudioEngine k;
    private volatile XmMp4Record l;
    private volatile int m = 0;
    private volatile long n = 0;
    private volatile long o = 0;
    private volatile long p = 0;
    private volatile boolean q = true;
    private boolean r = true;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private long v = 0;
    private byte[] w = null;
    private AACEncodeEx x = null;
    private byte[] y = null;
    private ByteDataBuffer z = new ByteDataBuffer();

    public interface ICameraPlayerListener {
        void a();

        void a(int i);

        void a(int i, int i2, int i3, int i4, int i5);

        void a(int i, String str);

        void a(int i, byte[] bArr);

        void b();

        void c();
    }

    public interface IRecordTimeListener {
        void a(int i);
    }

    public interface IResolutionChangedListener {
        void a(int i, int i2);
    }

    public interface IVideoLiveListener {
        void a(boolean z);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onRetry(int i2, String str, int i3) {
    }

    public P2pCameraPlayer(BaseDevice baseDevice, ICameraPlayerListener iCameraPlayerListener, XmVideoViewGl xmVideoViewGl) {
        this.K = xmVideoViewGl.getContext();
        h();
        this.f = (P2pCameraDevice) baseDevice;
        this.d = this.f.a();
        this.d.updateInfo(this.f.b());
        this.d.setClientListener(this);
        this.h = iCameraPlayerListener;
        this.e = xmVideoViewGl;
        if (this.f == null || TextUtils.isEmpty(this.f.getModel()) || (!"chuangmi.camera.ipc019".equals(this.f.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.f.getModel()))) {
            this.k = new AudioEngine(xmVideoViewGl.getContext(), 8000, this.f.getModel());
        } else {
            this.k = new AudioEngine(xmVideoViewGl.getContext(), RecordDevice.PCM_FREQUENCE_16K, this.f.getModel());
        }
        this.k.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                P2pCameraPlayer.this.b(bArr.length);
                P2pCameraPlayer.this.d.sendAudioData(bArr, i);
            }
        });
        this.B = 0;
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        long j2 = (long) i2;
        this.n += j2;
        this.o += j2;
        this.p += j2;
    }

    public void onError(int i2, String str) {
        if (this.h != null) {
            this.h.a(i2, str);
        }
    }

    public void onProgress(int i2) {
        if (i2 < 100) {
            this.b = false;
        }
        if (this.h != null) {
            this.h.a(i2);
        }
    }

    public void onConnected() {
        if (this.h != null) {
            this.d.resume();
            this.h.a();
            this.h.c();
        }
    }

    public void onDisConnected() {
        if (this.h != null) {
            this.h.b();
        }
    }

    public void onCtrlData(final int i2, final byte[] bArr) {
        if (this.h != null) {
            this.f8525a.post(new Runnable() {
                public void run() {
                    if (P2pCameraPlayer.this.h != null) {
                        P2pCameraPlayer.this.h.a(i2, bArr);
                    }
                }
            });
        }
    }

    public void onAudioData(byte[] bArr, byte[] bArr2) {
        long byteArrayToInt = (long) Packet.byteArrayToInt(bArr2, 12, false);
        short byteArrayToShort = Packet.byteArrayToShort(bArr2, 0, false);
        if (bArr != null && bArr.length != 0 && !this.t && this.s) {
            if (!this.q) {
                b(bArr.length);
                this.k.a(bArr, byteArrayToInt, byteArrayToShort);
            }
            if (this.u && this.l != null) {
                if (byteArrayToShort != 136) {
                    if (this.w == null) {
                        this.w = new byte[10240];
                    }
                    int decode = G711.decode(bArr, 0, bArr.length, this.w);
                    if (this.x == null) {
                        this.x = new AACEncodeEx();
                        if (this.f == null || TextUtils.isEmpty(this.f.getModel()) || (!"chuangmi.camera.ipc019".equals(this.f.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.f.getModel()))) {
                            this.x.initial(8000, 1, RecordDevice.PCM_FREQUENCE_16K);
                        } else {
                            this.x.initial(RecordDevice.PCM_FREQUENCE_16K, 1, RecordDevice.PCM_FREQUENCE_16K);
                        }
                        this.y = new byte[2048];
                        this.z.b();
                    }
                    byte[] bArr3 = new byte[decode];
                    System.arraycopy(this.w, 0, bArr3, 0, decode);
                    this.z.b(bArr3);
                    while (this.z.a(this.y)) {
                        byte[] encode = this.x.encode(this.y, 0, this.y.length);
                        this.l.writeAAcData(encode, encode.length);
                    }
                    return;
                }
                this.l.writeAAcData(bArr, bArr.length);
            }
        }
    }

    public void onVideoData(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        if (bArr3 != null && !c()) {
            AVFrame aVFrame = new AVFrame((byte) 0, bArr2, bArr, bArr3.length, false);
            if (this.b || aVFrame.isIFrame()) {
                if (!this.b && aVFrame.isIFrame()) {
                    this.b = true;
                    this.A = System.currentTimeMillis();
                    this.f8525a.post(new Runnable() {
                        public void run() {
                            if (P2pCameraPlayer.this.h != null) {
                                P2pCameraPlayer.this.h.a(101);
                            }
                        }
                    });
                }
                b(aVFrame.frmData.length);
                if (this.i != null && this.E == aVFrame.isPlayback()) {
                    this.i.a(!aVFrame.isPlayback());
                }
                this.E = !aVFrame.isPlayback();
                if (!(this.g == null || this.H <= 0 || this.H == aVFrame.getVideoWidth())) {
                    this.g.a(aVFrame.getVideoWidth(), aVFrame.getVideoHeight());
                }
                this.H = aVFrame.getVideoWidth();
                this.I = aVFrame.getVideoHeight();
                if (this.u && this.l != null && aVFrame.getVideoWidth() == 1920) {
                    int timeStamp = aVFrame.getTimeStamp();
                    if (!this.E && this.F == 4) {
                        timeStamp /= 4;
                    }
                    if (this.j != null) {
                        this.j.a(timeStamp);
                    }
                    this.l.writeVideoData(aVFrame.frmData, aVFrame.frmData.length, aVFrame.isIFrame(), timeStamp);
                }
                try {
                    this.m++;
                    this.B++;
                    this.e.drawVideoFrame(new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), aVFrame.getVideoType(), aVFrame.isIFrame(), (!this.E || !this.C) ? 0 : aVFrame.isWartTime() ? 2 : 1, this.E));
                    this.D = (long) aVFrame.getTimeStampReal();
                    if ((this.G == 0 && this.E && this.b) || (this.G > 0 && aVFrame.isIFrame() && !this.E)) {
                        this.G = -1;
                        this.f8525a.post(new Runnable() {
                            public void run() {
                                if (P2pCameraPlayer.this.h != null) {
                                    P2pCameraPlayer.this.h.a(102);
                                    if (P2pCameraPlayer.this.b()) {
                                        int unused = P2pCameraPlayer.this.F = 1;
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    SDKLog.e(c, e2.toString());
                }
            }
        }
    }

    public void a(int i2) {
        if (this.u) {
            this.J = i2;
        }
        this.d.setQuality(i2);
    }

    public void a(IRecordTimeListener iRecordTimeListener) {
        this.j = iRecordTimeListener;
    }

    public void a(String str) {
        if (this.l == null) {
            SDKLog.b(c, "startRecord");
            this.z.b();
            this.l = XmPluginHostApi.instance().createMp4Record();
            this.l.startRecord(str, 1, 1920, 1080, 8000);
            this.u = true;
            if (this.J == 0) {
                this.d.setQuality(3);
            }
        }
    }

    public void a(XmMp4Record.IRecordListener iRecordListener) {
        SDKLog.b(c, "stopRecord");
        this.u = false;
        if (this.l != null) {
            this.l.stopRecord(iRecordListener);
            this.l = null;
            this.d.setQuality(this.J);
        } else if (iRecordListener != null) {
            iRecordListener.onFailed(-1, "");
        }
    }

    public void a(boolean z2) {
        this.q = z2;
        if (z2 && this.k != null) {
            this.k.g();
        }
    }

    public boolean a() {
        return this.q;
    }

    public boolean b() {
        return this.E;
    }

    public synchronized boolean c() {
        return !this.s;
    }

    public synchronized void d() {
        this.q = false;
        this.r = true;
        this.k.e();
        this.v = System.currentTimeMillis();
    }

    public synchronized void e() {
        this.r = false;
        if (this.k != null) {
            this.k.f();
        }
        if (this.k != null) {
            this.k.c();
        }
    }

    public void f() {
        this.s = false;
        this.b = false;
        this.d.setClientListener((IClientListener) null);
        this.h = null;
        this.e = null;
    }

    private void g() {
        this.d.runInP2pThread(new Runnable() {
            public void run() {
                if (P2pCameraPlayer.this.e != null) {
                    P2pCameraPlayer.this.e.clearQueue();
                }
                if (P2pCameraPlayer.this.k != null) {
                    P2pCameraPlayer.this.k.g();
                }
            }
        });
    }

    public void b(boolean z2) {
        this.s = false;
        SDKLog.b(c, "release");
        g();
        this.k.f();
        this.d.release(z2, this);
        if (this.x != null) {
            this.x.release();
            this.x = null;
        }
        this.d.setClientListener((IClientListener) null);
        this.b = false;
        this.h = null;
        this.j = null;
        this.e = null;
        i();
        this.K = null;
    }

    class AudioBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
        }

        AudioBroadcastReceiver() {
        }
    }

    private void h() {
        if (this.L == null && this.K != null) {
            this.L = new AudioBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
            this.K.registerReceiver(this.L, intentFilter);
        }
    }

    private void i() {
        if (this.L != null && this.K != null) {
            this.K.unregisterReceiver(this.L);
            this.L = null;
        }
    }
}
