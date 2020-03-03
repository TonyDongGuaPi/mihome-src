package com.mijia.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.mijia.app.Event;
import com.mijia.camera.Utils.HeadSetUtils;
import com.mijia.debug.SDKLog;
import com.mijia.debug.Tag;
import com.p2p.audio.AudioEngine;
import com.sina.weibo.sdk.statistic.LogBuilder;
import com.tutk.IAudioSender;
import com.tutk.IOTC.AVAPIs;
import com.tutk.IOTC.Packet;
import com.xiaomi.CameraDevice;
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
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraPlayer implements IClientListener {
    private static final String c = "CameraPlayer";
    private static final int z = 2000;
    private long A = 0;
    private int B = -1;
    /* access modifiers changed from: private */
    public int C = 1;
    private volatile int D = 0;
    private volatile long E = 0;
    private volatile long F = 0;
    private boolean G = false;
    private Context H;
    private TimerTask I = new TimerTask() {
        public void run() {
            if (CameraPlayer.this.e != null && CameraPlayer.this.u) {
                CameraPlayer.this.e.onVideoInfo(CameraPlayer.this.n, CameraPlayer.this.o, CameraPlayer.this.v / 2, (int) (CameraPlayer.this.w / 2048), (int) (CameraPlayer.this.x / 1024));
            }
            int unused = CameraPlayer.this.v = 0;
            long unused2 = CameraPlayer.this.w = 0;
        }
    };
    private boolean J;
    private boolean K;
    private int L;
    private byte[] M = null;
    private AACEncodeEx N = null;
    private byte[] O = null;
    private ByteDataBuffer P = new ByteDataBuffer();
    private boolean Q = true;
    private BroadcastReceiver R = null;

    /* renamed from: a  reason: collision with root package name */
    public IResolutionChangedListener f7862a;
    public IVideoLiveListener b;
    /* access modifiers changed from: private */
    public XmCameraP2p d;
    /* access modifiers changed from: private */
    public ICameraPlayerListener e;
    private IRecordTimeListener f;
    private volatile boolean g = false;
    private volatile boolean h = false;
    private volatile boolean i = false;
    private CameraDevice j;
    /* access modifiers changed from: private */
    public AudioEngine k;
    /* access modifiers changed from: private */
    public XmVideoViewGl l;
    private Handler m = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public volatile int n;
    /* access modifiers changed from: private */
    public volatile int o;
    private volatile XmMp4Record p;
    private volatile boolean q = false;
    private volatile boolean r = true;
    private volatile long s;
    private volatile boolean t;
    /* access modifiers changed from: private */
    public volatile boolean u;
    /* access modifiers changed from: private */
    public volatile int v;
    /* access modifiers changed from: private */
    public volatile long w;
    /* access modifiers changed from: private */
    public volatile long x;
    private Timer y;

    public interface IRecordTimeListener {
        void upDateTime(int i);
    }

    public interface IResolutionChangedListener {
        void onResolutionChanged(int i, int i2);
    }

    public interface IVideoLiveListener {
        void onVideoLiveChanged(boolean z);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onRetry(int i2, String str, int i3) {
    }

    /* access modifiers changed from: private */
    public void e(int i2) {
        long j2 = (long) i2;
        this.w += j2;
        this.x += j2;
        this.E += j2;
    }

    public CameraPlayer(Context context, CameraDevice cameraDevice, ICameraPlayerListener iCameraPlayerListener, XmVideoViewGl xmVideoViewGl) {
        this.H = context;
        v();
        this.j = cameraDevice;
        this.d = this.j.u();
        this.d.updateInfo(this.j.t());
        this.d.setClientListener(this);
        this.e = iCameraPlayerListener;
        this.l = xmVideoViewGl;
        if (this.j == null || TextUtils.isEmpty(this.j.getModel()) || (!"chuangmi.camera.ipc019".equals(this.j.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.j.getModel()))) {
            this.k = new AudioEngine(xmVideoViewGl.getContext(), 8000, this.j.getModel());
        } else {
            this.k = new AudioEngine(xmVideoViewGl.getContext(), RecordDevice.PCM_FREQUENCE_16K, this.j.getModel());
        }
        this.k.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                CameraPlayer.this.e(bArr.length);
                CameraPlayer.this.d.sendAudioData(bArr, i);
            }
        });
        this.y = new Timer();
        this.y.schedule(this.I, 0, 2000);
        this.r = true;
        this.d.setQuality(((MijiaCameraDevice) this.j).e().d());
    }

    public void onError(int i2, String str) {
        if (this.e != null) {
            this.e.onVideoPlayError(i2, str);
        }
    }

    public void onProgress(int i2) {
        if (i2 < 100) {
            this.u = false;
        }
        if (this.e != null) {
            this.e.onPrepare(i2);
        }
    }

    @WorkerThread
    public void onConnected() {
        this.m.post(new Runnable() {
            public void run() {
                if (CameraPlayer.this.e != null) {
                    CameraPlayer.this.e.onConnected();
                }
            }
        });
        if (this.Q) {
            this.d.resume();
        }
        ((MijiaCameraDevice) this.j).c().a((Callback<Void>) null, true);
    }

    public void onDisConnected() {
        if (this.e != null) {
            this.e.onDisConnected();
        }
    }

    public void onCtrlData(final int i2, final byte[] bArr) {
        if (this.e != null) {
            this.m.post(new Runnable() {
                public void run() {
                    if (CameraPlayer.this.e != null) {
                        CameraPlayer.this.e.onServerCmd(i2, bArr);
                    }
                }
            });
        }
    }

    public void onAudioData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            a(bArr, (long) Packet.byteArrayToInt(bArr2, 12, false), (int) Packet.byteArrayToShort(bArr2, 0, false));
        }
    }

    public void onVideoData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null) {
            AVFrame aVFrame = new AVFrame((byte) 0, bArr2, bArr, bArr.length, false);
            short codecId = aVFrame.getCodecId();
            if (codecId == 138 || codecId == 136) {
                a(bArr, (long) aVFrame.getTimeStamp(), (int) codecId);
            } else {
                a(aVFrame);
            }
        }
    }

    public void a(int i2, int i3, int i4) {
        SDKLog.e(Tag.b, "set play time " + i2 + "   " + i3 + "   " + i4);
        if (!i() || h() || i2 == 0) {
            if (i2 != 0) {
                SDKLog.e(Tag.b, "set play time fail start: " + i() + " pause: " + h());
            }
            a(i2);
            this.A = 0;
            if (!i()) {
                return;
            }
        }
        if (i2 != 0) {
            this.g = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AgentOptions.i, i2);
                jSONObject.put(LogBuilder.i, i2);
                jSONObject.put(LogBuilder.j, i4);
                jSONObject.put("autoswitchtolive", 1);
                jSONObject.put("offset", i3);
                jSONObject.put("speed", this.C);
                jSONObject.put("avchannelmerge", 1);
                String jSONObject2 = jSONObject.toString();
                this.B = i2;
                this.d.playBack(jSONObject2.getBytes(Charset.forName("UTF-8")));
                this.A = (long) (i2 + i3);
                SDKLog.e(Tag.b, "set play time ");
            } catch (JSONException e2) {
                SDKLog.e(Tag.b, "JSONException", e2);
            }
        }
    }

    public void a() {
        this.J = true;
    }

    public long b() {
        return this.s;
    }

    public boolean c() {
        return this.t;
    }

    public boolean d() {
        return this.g && this.u;
    }

    public synchronized boolean e() {
        return !this.g;
    }

    private boolean t() {
        SDKLog.b(c, "initial");
        if (TextUtils.isEmpty(this.j.r()) || TextUtils.isEmpty(this.j.w())) {
            if (this.e != null) {
                this.e.onVideoPlayError(AVAPIs.AV_ER_WRONG_VIEWACCorPWD, "");
            }
            return false;
        }
        this.v = 0;
        this.w = 0;
        this.d.updateInfo(this.j.t());
        return true;
    }

    public void f() {
        if (t()) {
            SDKLog.b(c, "startPlay");
            this.d.setClientListener(this);
            this.g = true;
            this.i = false;
            this.u = false;
            this.k.c();
            this.d.connect();
            u();
        }
    }

    public void g() {
        SDKLog.b(c, "pausePlay");
        this.g = false;
        this.d.pause(this);
        this.k.f();
        this.i = false;
        this.u = false;
        u();
        this.D = 0;
        this.E = 0;
    }

    private void u() {
        this.d.runInP2pThread(new Runnable() {
            public void run() {
                if (CameraPlayer.this.l != null) {
                    try {
                        CameraPlayer.this.l.clearQueue();
                    } catch (Exception e) {
                        LogUtil.b(CameraPlayer.c, "Exception:" + e.getLocalizedMessage());
                    }
                }
                if (CameraPlayer.this.k != null) {
                    CameraPlayer.this.k.g();
                }
            }
        });
    }

    public void a(int i2) {
        SDKLog.b(c, "resumePlay");
        this.d.setClientListener(this);
        this.k.c();
        boolean z2 = true;
        this.g = true;
        this.i = false;
        this.d.resume();
        if (i2 != 0) {
            z2 = false;
        }
        this.Q = z2;
        u();
    }

    public boolean h() {
        return !this.g || this.d.isPaused();
    }

    public boolean i() {
        return this.d.isConnected();
    }

    public void j() {
        if (t()) {
            SDKLog.b(c, "retryPlay");
            this.g = true;
            this.k.c();
            this.d.reconnect();
        }
    }

    public void a(boolean z2) {
        this.g = false;
        SDKLog.b(c, "release");
        u();
        this.k.f();
        this.j.x();
        this.d.release(!z2, this);
        if (this.N != null) {
            this.N.release();
            this.N = null;
        }
        ((MijiaCameraDevice) this.j).c().j();
        this.u = false;
        this.y.cancel();
        this.e = null;
        this.f = null;
        this.l = null;
        w();
        this.H = null;
    }

    public synchronized void k() {
        if (t()) {
            this.r = false;
            this.h = true;
            this.k.e();
            Event.t();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void l() {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.t()     // Catch:{ all -> 0x0026 }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r1)
            return
        L_0x0009:
            r0 = 0
            r1.h = r0     // Catch:{ all -> 0x0026 }
            com.mijia.app.Event.u()     // Catch:{ all -> 0x0026 }
            com.p2p.audio.AudioEngine r0 = r1.k     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0018
            com.p2p.audio.AudioEngine r0 = r1.k     // Catch:{ all -> 0x0026 }
            r0.f()     // Catch:{ all -> 0x0026 }
        L_0x0018:
            r0 = 1
            r1.r = r0     // Catch:{ all -> 0x0026 }
            com.p2p.audio.AudioEngine r0 = r1.k     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0024
            com.p2p.audio.AudioEngine r0 = r1.k     // Catch:{ all -> 0x0026 }
            r0.c()     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r1)
            return
        L_0x0026:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.camera.CameraPlayer.l():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(android.content.Context r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r2 = r1.t()     // Catch:{ all -> 0x0025 }
            if (r2 == 0) goto L_0x0023
            boolean r2 = r1.i     // Catch:{ all -> 0x0025 }
            if (r2 == 0) goto L_0x000c
            goto L_0x0023
        L_0x000c:
            java.lang.String r2 = "audio"
            java.lang.String r0 = "start speak"
            com.mijia.debug.SDKLog.e(r2, r0)     // Catch:{ all -> 0x0025 }
            r2 = 1
            r1.i = r2     // Catch:{ all -> 0x0025 }
            com.p2p.audio.AudioEngine r2 = r1.k     // Catch:{ all -> 0x0025 }
            r2.d()     // Catch:{ all -> 0x0025 }
            com.xiaomi.smarthome.camera.XmCameraP2p r2 = r1.d     // Catch:{ all -> 0x0025 }
            r2.startSpeak()     // Catch:{ all -> 0x0025 }
            monitor-exit(r1)
            return
        L_0x0023:
            monitor-exit(r1)
            return
        L_0x0025:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.camera.CameraPlayer.a(android.content.Context):void");
    }

    public synchronized void b(Context context) {
        SDKLog.e("audio", "stop speak");
        this.i = false;
        this.k.f();
        this.k.c();
        this.d.stopSpeak();
    }

    public void a(IRecordTimeListener iRecordTimeListener) {
        this.f = iRecordTimeListener;
    }

    public void a(String str, int i2) {
        if (this.p == null) {
            SDKLog.b(c, "startRecord");
            this.P.b();
            this.p = XmPluginHostApi.instance().createMp4Record();
            this.p.startRecord(str, i2, 1920, 1080, ("chuangmi.camera.ipc019".equals(this.j.getModel()) || DeviceConstant.CHUANGMI_CAMERA_021.equals(this.j.getModel())) ? RecordDevice.PCM_FREQUENCE_16K : 8000);
            this.q = true;
            if (((MijiaCameraDevice) this.j).e().d() != 3) {
                this.d.setQuality(3);
            }
        }
    }

    public boolean m() {
        return this.q;
    }

    public boolean n() {
        return this.i;
    }

    public boolean o() {
        return this.h;
    }

    public void a(XmMp4Record.IRecordListener iRecordListener) {
        SDKLog.b(c, "stopRecord");
        this.q = false;
        if (this.p != null) {
            this.p.stopRecord(iRecordListener);
            this.p = null;
            this.d.setQuality(((MijiaCameraDevice) this.j).e().d());
        } else if (iRecordListener != null) {
            iRecordListener.onFailed(-1, "");
        }
    }

    public void b(boolean z2) {
        this.r = z2;
        if (z2 && this.k != null) {
            this.k.g();
        }
        Event.a(Event.g);
    }

    public void b(int i2) {
        if (!this.q) {
            this.d.setQuality(i2);
        }
    }

    public void c(int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(HomeManager.v, i2);
            this.d.direction(jSONObject.toString().getBytes(Charset.forName("UTF-8")));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public boolean p() {
        return this.r;
    }

    public void b(int i2, int i3, int i4) {
        LogUtil.a(c, "set play time " + i2 + "   " + i3 + "   " + i4);
        if (!i() || h() || i2 == 0) {
            if (i2 != 0) {
                LogUtil.a(c, "set play time fail start: " + i() + " pause: " + h());
            }
            a(i2);
            this.A = 0;
        }
        if (i2 != 0) {
            this.g = true;
            int i5 = i4 == 0 ? 1 : 0;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AgentOptions.i, i2);
                jSONObject.put(LogBuilder.i, i2);
                jSONObject.put(LogBuilder.j, i4);
                jSONObject.put("autoswitchtolive", i5);
                jSONObject.put("offset", i3);
                jSONObject.put("speed", this.C);
                jSONObject.put("avchannelmerge", 1);
                String jSONObject2 = jSONObject.toString();
                this.B = i2;
                this.d.playBack(jSONObject2.getBytes(Charset.forName("UTF-8")));
                this.A = (long) (i2 + i3);
                SDKLog.e(Tag.b, "set play time ");
            } catch (JSONException e2) {
                SDKLog.e(Tag.b, "JSONException", e2);
            }
        }
    }

    public void d(int i2) {
        String str = "";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", i2);
            jSONObject.put(AgentOptions.i, this.B);
            str = jSONObject.toString();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.d.playBackSpeed(str.getBytes(Charset.forName("UTF-8")));
        this.C = i2;
    }

    public int q() {
        return this.C;
    }

    public void r() {
        this.g = false;
        this.u = false;
        this.y.cancel();
        this.d.setClientListener((IClientListener) null);
        this.e = null;
        this.l = null;
    }

    private void a(byte[] bArr, long j2, int i2) {
        if (bArr != null && bArr.length != 0 && !this.i && this.g) {
            if (!this.r) {
                e(bArr.length);
                this.k.a(bArr, j2, i2);
            }
            if (this.q && this.p != null) {
                if (i2 != 136) {
                    if (this.M == null) {
                        this.M = new byte[10240];
                    }
                    int decode = G711.decode(bArr, 0, bArr.length, this.M);
                    if (this.N == null) {
                        this.N = new AACEncodeEx();
                        if (this.j == null || TextUtils.isEmpty(this.j.getModel()) || (!"chuangmi.camera.ipc019".equals(this.j.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.j.getModel()))) {
                            this.N.initial(8000, 1, RecordDevice.PCM_FREQUENCE_16K);
                        } else {
                            this.N.initial(RecordDevice.PCM_FREQUENCE_16K, 1, RecordDevice.PCM_FREQUENCE_16K);
                        }
                        this.O = new byte[2048];
                        this.P.b();
                    }
                    byte[] bArr2 = new byte[decode];
                    System.arraycopy(this.M, 0, bArr2, 0, decode);
                    this.P.b(bArr2);
                    while (this.P.a(this.O)) {
                        byte[] encode = this.N.encode(this.O, 0, this.O.length);
                        this.p.writeAAcData(encode, encode.length);
                    }
                    return;
                }
                this.p.writeAAcData(bArr, bArr.length);
            }
        }
    }

    private void a(AVFrame aVFrame) {
        if (this.u || aVFrame.isIFrame()) {
            if (!this.u && aVFrame.isIFrame()) {
                this.u = true;
                this.F = System.currentTimeMillis();
                this.m.post(new Runnable() {
                    public void run() {
                        if (CameraPlayer.this.e != null) {
                            CameraPlayer.this.e.onPrepare(101);
                        }
                    }
                });
                this.G = ((MijiaCameraDevice) this.j).e().c();
            }
            e(aVFrame.frmData.length);
            if (this.b != null && this.t == aVFrame.isPlayback()) {
                this.b.onVideoLiveChanged(!aVFrame.isPlayback());
            }
            this.t = !aVFrame.isPlayback();
            if (!(this.f7862a == null || this.n <= 0 || this.n == aVFrame.getVideoWidth())) {
                this.f7862a.onResolutionChanged(aVFrame.getVideoWidth(), aVFrame.getVideoHeight());
            }
            if (!this.t) {
                this.K = this.t;
            }
            if (!this.J || !this.t) {
                this.L = 0;
                this.n = aVFrame.getVideoWidth();
                this.o = aVFrame.getVideoHeight();
                this.t = !aVFrame.isPlayback();
                if (this.t) {
                    this.C = 1;
                }
                if (this.q && this.p != null) {
                    int timeStamp = aVFrame.getTimeStamp();
                    if (!this.t && this.C == 4) {
                        timeStamp /= 4;
                    } else if (!this.t && this.C == 16) {
                        timeStamp /= 16;
                    }
                    if (this.f != null) {
                        this.f.upDateTime(timeStamp);
                    }
                    this.p.writeVideoData(aVFrame.frmData, aVFrame.frmData.length, aVFrame.isIFrame(), timeStamp);
                }
                try {
                    this.v++;
                    this.D++;
                    this.l.drawVideoFrame(new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), aVFrame.getVideoType(), aVFrame.isIFrame(), (!this.t || !this.G) ? 0 : aVFrame.isWartTime() ? 2 : 1, this.t));
                    this.s = (long) aVFrame.getTimeStampReal();
                    if ((this.A == 0 && this.t && this.u) || (this.A > 0 && aVFrame.isIFrame() && !this.t)) {
                        this.A = -1;
                        this.m.post(new Runnable() {
                            public void run() {
                                if (CameraPlayer.this.e != null) {
                                    CameraPlayer.this.e.onPrepare(102);
                                    if (CameraPlayer.this.c()) {
                                        int unused = CameraPlayer.this.C = 1;
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e2) {
                    SDKLog.e(c, e2.toString());
                }
            } else if (!this.K) {
                this.L++;
                if (this.L > 10) {
                    if (this.e != null) {
                        this.e.onPauseCamera();
                    }
                    this.L = 0;
                }
            }
        }
    }

    public int s() {
        if (this.k != null) {
            return this.k.f8504a.g();
        }
        return 0;
    }

    class AudioBroadcastReceiver extends BroadcastReceiver {
        AudioBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                if (HeadSetUtils.a()) {
                    CameraPlayer.this.k.a(3);
                } else if (HeadSetUtils.a(context)) {
                    CameraPlayer.this.k.a(2);
                } else {
                    CameraPlayer.this.k.a(1);
                }
            } else if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction()) && intent.hasExtra("state")) {
                intent.getIntExtra("state", 0);
                if (intent.getIntExtra("state", 0) == 0) {
                    if (HeadSetUtils.a()) {
                        CameraPlayer.this.k.a(3);
                    } else {
                        CameraPlayer.this.k.a(1);
                    }
                } else if (intent.getIntExtra("state", 0) == 1) {
                    CameraPlayer.this.k.a(2);
                }
            }
        }
    }

    private void v() {
        if (this.R == null && this.H != null) {
            this.R = new AudioBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            this.H.registerReceiver(this.R, intentFilter);
        }
    }

    private void w() {
        if (this.R != null && this.H != null) {
            this.H.unregisterReceiver(this.R);
            this.R = null;
        }
    }
}
