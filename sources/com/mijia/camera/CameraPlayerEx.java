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
import com.mijia.camera.Utils.DeviceUtils;
import com.mijia.camera.Utils.HeadSetUtils;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.p2p.audio.AudioEngine;
import com.sina.weibo.sdk.statistic.LogBuilder;
import com.tutk.IAudioSender;
import com.tutk.IOTC.Packet;
import com.xiaomi.CameraDevice;
import com.xiaomi.aaccodec.AACEncodeEx;
import com.xiaomi.aaccodec.G711;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.connection.CamCommand;
import com.xiaomi.mistream.MIStreamStatistic;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.audioprocess.ByteDataBuffer;
import com.xiaomi.smarthome.camera.AVFrame;
import com.xiaomi.smarthome.camera.IClientExListener;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmMp4Record;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.ThreadPool;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import java.util.Timer;
import java.util.TimerTask;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.json.JSONException;
import org.json.JSONObject;

public class CameraPlayerEx implements IClientExListener {
    private static final int D = 2000;
    public static int f = Integer.MAX_VALUE;
    private static final String g = "CameraPlayerEx";
    /* access modifiers changed from: private */
    public volatile long A;
    /* access modifiers changed from: private */
    public volatile long B;
    private Timer C;
    private long E = 0;
    private int F = -1;
    /* access modifiers changed from: private */
    public int G = 1;
    private volatile int H = 0;
    private volatile long I = 0;
    private volatile long J = 0;
    private boolean K = false;
    private Context L;
    private boolean M = false;
    private int N;
    private boolean O = true;
    private long P = -1;
    private long Q = 0;
    /* access modifiers changed from: private */
    public IStreamCmdMessageListener R;
    private TimerTask S = new TimerTask() {
        public void run() {
            if (CameraPlayerEx.this.i != null && CameraPlayerEx.this.y) {
                CameraPlayerEx.this.i.onVideoInfo(CameraPlayerEx.this.r, CameraPlayerEx.this.s, CameraPlayerEx.this.z / 2, (int) (CameraPlayerEx.this.A / 2048), (int) (CameraPlayerEx.this.B / 1024));
            }
            int unused = CameraPlayerEx.this.z = 0;
            long unused2 = CameraPlayerEx.this.A = 0;
        }
    };
    private boolean T;
    private byte[] U = null;
    private AACEncodeEx V = null;
    private byte[] W = null;
    private ByteDataBuffer X = new ByteDataBuffer();
    private boolean Y = true;
    private BroadcastReceiver Z = null;

    /* renamed from: a  reason: collision with root package name */
    public final int f7871a = 1920;
    public final int b = 1080;
    public IResolutionChangedListener c;
    public IVideoLiveListener d;
    public long e = 0;
    /* access modifiers changed from: private */
    public XmStreamClient h;
    /* access modifiers changed from: private */
    public ICameraPlayerListener i;
    private IRecordTimeListener j;
    private volatile boolean k = false;
    private volatile boolean l = false;
    private volatile boolean m = false;
    /* access modifiers changed from: private */
    public CameraDevice n;
    /* access modifiers changed from: private */
    public AudioEngine o;
    /* access modifiers changed from: private */
    public XmVideoViewGl p;
    private Handler q = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public volatile int r;
    /* access modifiers changed from: private */
    public volatile int s;
    private volatile XmMp4Record t;
    private volatile boolean u = false;
    private volatile boolean v = true;
    private volatile long w;
    private volatile boolean x;
    /* access modifiers changed from: private */
    public volatile boolean y;
    /* access modifiers changed from: private */
    public volatile int z;

    public interface IRecordTimeListener {
        void upDateTime(int i);
    }

    public interface IResolutionChangedListener {
        void onResolutionChanged(int i, int i2);
    }

    public interface IVideoLiveListener {
        void onVideoLiveChanged(boolean z);
    }

    interface Mp4RecordListener {
        void a(int i);

        void b(int i);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onRetry(int i2, String str, int i3) {
    }

    /* access modifiers changed from: private */
    public void c(int i2) {
        long j2 = (long) i2;
        this.A += j2;
        this.B += j2;
        this.I += j2;
    }

    public CameraPlayerEx(Context context, CameraDevice cameraDevice, ICameraPlayerListener iCameraPlayerListener, XmVideoViewGl xmVideoViewGl) {
        this.L = context;
        E();
        this.n = cameraDevice;
        this.h = this.n.v();
        if (this.h == null) {
            LogUtil.b(g, "mXmStreamClient == null");
            return;
        }
        this.h.setClientListener(this);
        this.i = iCameraPlayerListener;
        this.p = xmVideoViewGl;
        if (this.n == null || TextUtils.isEmpty(this.n.getModel()) || (!"chuangmi.camera.ipc019".equals(this.n.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.n.getModel()))) {
            this.o = new AudioEngine(xmVideoViewGl.getContext(), 8000, this.n.getModel());
        } else {
            this.o = new AudioEngine(xmVideoViewGl.getContext(), RecordDevice.PCM_FREQUENCE_16K, this.n.getModel());
        }
        this.o.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                CameraPlayerEx.this.c(bArr.length);
                CameraPlayerEx.this.h.sendAudioData(bArr, i);
            }
        });
        this.C = new Timer();
        this.C.schedule(this.S, 0, 2000);
        this.v = true;
        this.h.streamResolution(((MijiaCameraDevice) this.n).e().d(), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
                LogUtil.a(CameraPlayerEx.g, "streamResolution success:" + str);
            }

            public void onFailed(int i, String str) {
                LogUtil.b(CameraPlayerEx.g, "streamResolution failed:" + i);
            }
        });
    }

    public CameraPlayerEx(CameraDevice cameraDevice, ICameraPlayerListener iCameraPlayerListener) {
        this.n = cameraDevice;
        this.h = this.n.v();
        this.h.setClientListener(this);
        this.i = iCameraPlayerListener;
    }

    public void onError(int i2, String str) {
        this.k = false;
        this.y = false;
        if (this.i != null) {
            this.i.onVideoPlayError(i2, str);
        }
    }

    public void onProgress(int i2) {
        if (i2 < 100) {
            this.y = false;
        }
        if (this.i != null) {
            this.i.onPrepare(i2);
        }
    }

    @WorkerThread
    public void onConnected() {
        if (this.i != null) {
            this.i.onConnected();
        }
    }

    public void onDisConnected() {
        if (this.i != null) {
            this.i.onDisConnected();
        }
    }

    public void onDisconnectedWithCode(int i2) {
        if (this.i != null) {
            this.i.onDisconnectedWithCode(i2);
        }
    }

    public void onCtrlData(final int i2, final byte[] bArr) {
        if (this.i != null) {
            this.q.post(new Runnable() {
                public void run() {
                    if (CameraPlayerEx.this.i != null) {
                        CameraPlayerEx.this.i.onServerCmd(i2, bArr);
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
            a(new AVFrame((byte) 0, bArr2, bArr, bArr.length, false, this.n.getModel()));
        }
    }

    public void a() {
        this.M = true;
    }

    public void b() {
        D();
        SDKLog.b("Huang", "releaseCall ...1");
        if (this.o != null) {
            this.o.f();
        }
    }

    public void c() {
        if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(this.n.getModel())) {
            this.T = true;
        }
    }

    public void d() {
        this.h.setClientListener(this);
        if (!l()) {
            SDKLog.b(g, "streamStop");
            this.h.streamStop((IMISSListener) null);
        }
        MIStreamStatistic.getInstance().latestCameraConnect = System.currentTimeMillis();
        this.h.streamStart(new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
                SDKLog.b("AlbumActivity", "streamStart onSuccess s:" + str + " o:" + obj);
            }

            public void onFailed(int i, String str) {
                SDKLog.d(CameraPlayerEx.g, "onFailed i:" + i + " s:" + str);
            }
        });
    }

    public long e() {
        return this.w;
    }

    public boolean f() {
        return this.x;
    }

    public boolean g() {
        return this.k && this.y;
    }

    public synchronized boolean h() {
        return !this.k;
    }

    private boolean C() {
        LogUtil.a(g, "initial");
        this.z = 0;
        this.A = 0;
        return true;
    }

    public void i() {
        if (C()) {
            LogUtil.a(g, "startStreamPlay");
            if (this.h != null) {
                this.h.setClientListener(this);
            }
            this.k = true;
            this.m = false;
            this.y = false;
            this.o.c();
            if (this.h != null) {
                MIStreamStatistic.getInstance().latestCameraConnect = System.currentTimeMillis();
                this.h.streamStart((IMISSListener) null);
            }
            D();
        }
    }

    public void a(IMISSListener iMISSListener) {
        if (C()) {
            LogUtil.a(g, "startStreamPlay with listener");
            try {
                if (this.h != null) {
                    this.h.setClientListener(this);
                }
                this.k = true;
                this.m = false;
                this.y = false;
                this.o.c();
                if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(this.n.getModel()) && !l() && this.h != null) {
                    this.h.streamStop((IMISSListener) null);
                }
                if (this.h != null) {
                    MIStreamStatistic.getInstance().latestCameraConnect = System.currentTimeMillis();
                    this.h.streamStart(iMISSListener);
                }
                D();
            } catch (Exception e2) {
                LogUtil.b(g, "startStreamPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    public void j() {
        LogUtil.a(g, "pausePlay");
        try {
            this.k = false;
            if (this.h != null && (!this.x || !DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equalsIgnoreCase(this.n.getModel()))) {
                a(false, (IMISSListener) null);
                b(false, (IMISSListener) null);
            }
            this.o.f();
            this.m = false;
            this.y = false;
            D();
            this.H = 0;
            this.I = 0;
        } catch (Exception e2) {
            LogUtil.b(g, "pausePlay:" + e2.getLocalizedMessage());
        }
    }

    private void D() {
        ThreadPool.a(new Runnable() {
            public void run() {
                try {
                    if (CameraPlayerEx.this.p != null) {
                        CameraPlayerEx.this.p.clearQueue();
                    }
                    if (CameraPlayerEx.this.o != null) {
                        CameraPlayerEx.this.o.g();
                    }
                } catch (NullPointerException e) {
                    LogUtil.b(CameraPlayerEx.g, "NullPointerException clearQueue:" + e.getLocalizedMessage());
                }
            }
        });
    }

    public void a(int i2, boolean z2) {
        LogUtil.a(g, "resumePlay");
        try {
            SDKLog.b(g, "resumePlay:" + i2);
            this.o.c();
            boolean z3 = true;
            this.k = true;
            this.m = false;
            if (this.h != null) {
                this.h.setClientListener(this);
                MIStreamStatistic.getInstance().latestCameraConnect = System.currentTimeMillis();
                this.h.streamStart((IMISSListener) null);
                a(true, (IMISSListener) null);
                b(z2, (IMISSListener) null);
            }
            if (i2 != 0) {
                z3 = false;
            }
            this.Y = z3;
            D();
        } catch (Exception e2) {
            LogUtil.b(g, "resumePlay:" + e2.getLocalizedMessage());
        }
    }

    public boolean k() {
        return !this.k || this.h.isPaused();
    }

    public boolean l() {
        return this.h.isConnected();
    }

    public boolean m() {
        return this.h.isConnecting();
    }

    public void n() {
        if (C()) {
            LogUtil.a(g, "retryPlay");
            try {
                this.k = true;
                this.o.c();
                if (this.h != null) {
                    if (DeviceConstant.MIJIA_CAMERA_V3_UPGRADE.equals(this.n.getModel())) {
                        this.h.streamStop((IMISSListener) null);
                    }
                    MIStreamStatistic.getInstance().latestCameraConnect = System.currentTimeMillis();
                    this.h.streamStart(new IMISSListener() {
                        public void onProgress(int i) {
                        }

                        public void onSuccess(String str, Object obj) {
                            LogUtil.a(CameraPlayerEx.g, "retryPlay success:" + str);
                        }

                        public void onFailed(int i, String str) {
                            LogUtil.b(CameraPlayerEx.g, "retryPlay failed:" + i);
                        }
                    });
                }
            } catch (Exception e2) {
                LogUtil.b(g, "retryPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    public void o() {
        if (this.h != null) {
            this.h.streamStop((IMISSListener) null);
        }
    }

    public void a(boolean z2) {
        this.k = false;
        SDKLog.b(g, "release");
        try {
            D();
            this.o.f();
            this.n.x();
            if (this.h != null && z2) {
                this.h.streamStop((IMISSListener) null);
                this.h.release(this);
            }
            if (this.V != null) {
                this.V.release();
                this.V = null;
            }
            ((MijiaCameraDevice) this.n).c().j();
            this.y = false;
            if (this.C != null) {
                this.C.cancel();
            }
            this.i = null;
            this.j = null;
            this.p = null;
            F();
            this.L = null;
        } catch (Exception e2) {
            LogUtil.b(g, "release:" + e2.getLocalizedMessage());
        }
    }

    public synchronized void p() {
        if (C()) {
            LogUtil.a(g, "startCall");
            this.h.streamStartCall((IMISSListener) null);
            this.l = true;
            this.v = false;
            if (this.o != null) {
                this.o.e();
            }
            Event.t();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void q() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.C()     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x0009
            monitor-exit(r2)
            return
        L_0x0009:
            java.lang.String r0 = "CameraPlayerEx"
            java.lang.String r1 = "stopCall"
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r0, r1)     // Catch:{ all -> 0x002c }
            com.xiaomi.mistream.XmStreamClient r0 = r2.h     // Catch:{ all -> 0x002c }
            r1 = 0
            r0.streamStopCall(r1)     // Catch:{ all -> 0x002c }
            r0 = 0
            r2.l = r0     // Catch:{ all -> 0x002c }
            com.mijia.app.Event.u()     // Catch:{ all -> 0x002c }
            com.p2p.audio.AudioEngine r0 = r2.o     // Catch:{ all -> 0x002c }
            if (r0 == 0) goto L_0x002a
            com.p2p.audio.AudioEngine r0 = r2.o     // Catch:{ all -> 0x002c }
            r0.f()     // Catch:{ all -> 0x002c }
            com.p2p.audio.AudioEngine r0 = r2.o     // Catch:{ all -> 0x002c }
            r0.c()     // Catch:{ all -> 0x002c }
        L_0x002a:
            monitor-exit(r2)
            return
        L_0x002c:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mijia.camera.CameraPlayerEx.q():void");
    }

    public void a(IRecordTimeListener iRecordTimeListener) {
        this.j = iRecordTimeListener;
    }

    public void a(String str, int i2) {
        if (this.t == null) {
            LogUtil.a(g, "startRecord");
            this.X.b();
            this.t = XmPluginHostApi.instance().createMp4Record();
            if ("chuangmi.camera.ipc019".equals(this.n.getModel()) || DeviceConstant.CHUANGMI_CAMERA_021.equals(this.n.getModel())) {
                this.t.startRecord(str, i2, 1920, 1080, RecordDevice.PCM_FREQUENCE_16K);
            } else {
                this.t.startRecord(str, i2, 1920, 1080, 8000);
            }
            this.u = true;
            int d2 = ((MijiaCameraDevice) this.n).e().d();
            if (this.h != null && d2 != 3) {
                this.h.streamResolution(3, (IMISSListener) null);
            }
        }
    }

    public void a(String str, int i2, int i3) {
        if (this.t == null) {
            LogUtil.a(g, "startRecord");
            this.X.b();
            this.t = XmPluginHostApi.instance().createMp4Record();
            this.t.startRecord(str, i2, i3, 1920, 1080, 8000);
            this.u = true;
            int d2 = ((MijiaCameraDevice) this.n).e().d();
            if (this.h != null && d2 != 3) {
                this.h.streamResolution(3, (IMISSListener) null);
            }
        }
    }

    public boolean r() {
        return this.u;
    }

    public boolean s() {
        return this.m;
    }

    public boolean t() {
        return this.l;
    }

    public void a(XmMp4Record.IRecordListener iRecordListener) {
        LogUtil.a(g, "stopRecord");
        this.u = false;
        if (this.t != null) {
            this.t.stopRecord(iRecordListener);
            this.t = null;
            int d2 = ((MijiaCameraDevice) this.n).e().d();
            if (this.h != null) {
                this.h.streamResolution(d2, (IMISSListener) null);
            }
        } else if (iRecordListener != null) {
            iRecordListener.onFailed(-1, "");
        }
    }

    public void b(boolean z2) {
        this.h.streamToggleAudio(z2, (IMISSListener) null);
        this.v = z2;
        if (this.o != null) {
            if (z2) {
                this.o.g();
                this.o.b();
            } else {
                this.o.c();
                this.o.a();
            }
        }
        Event.a(Event.g);
    }

    public void a(int i2) {
        this.h.streamResolution(i2, new IMISSListener() {
            public void onFailed(int i, String str) {
            }

            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }
        });
    }

    public void a(int i2, IMISSListener iMISSListener) {
        this.h.streamResolution(i2, iMISSListener);
    }

    public void b(int i2, IMISSListener iMISSListener) {
        this.h.streamDirection(i2, iMISSListener);
    }

    public boolean u() {
        return this.v;
    }

    public void a(int i2, int i3, int i4) {
        LogUtil.a(g, "set play time " + i2 + " " + i3 + " " + i4);
        if (i2 != 0) {
            this.k = true;
            int i5 = i4 == 0 ? 1 : 0;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AgentOptions.i, i2);
                jSONObject.put(LogBuilder.i, i2);
                jSONObject.put(LogBuilder.j, i4);
                jSONObject.put("autoswitchtolive", i5);
                jSONObject.put("offset", i3);
                jSONObject.put("speed", this.G);
                jSONObject.put("avchannelmerge", 1);
                jSONObject.toString();
                this.F = i2;
                this.h.streamPlayback(jSONObject.toString(), new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(CameraPlayerEx.g, "streamPlayback onSuccess:" + str + " " + obj);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(CameraPlayerEx.g, "streamPlayback onFailed:" + i + " " + str);
                    }
                });
                this.E = (long) (i2 + i3);
            } catch (JSONException e2) {
                LogUtil.a(g, "JSONException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void a(int i2, int i3, int i4, IMISSListener iMISSListener) {
        LogUtil.a(g, "set play time " + i2 + " " + i3 + " " + i4);
        if (i2 != 0) {
            if (this.o != null) {
                this.o.c();
            }
            this.k = true;
            int i5 = i4 == 0 ? 1 : 0;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AgentOptions.i, i2);
                jSONObject.put(LogBuilder.i, i2);
                jSONObject.put(LogBuilder.j, i4);
                jSONObject.put("autoswitchtolive", i5);
                jSONObject.put("offset", i3);
                jSONObject.put("speed", this.G);
                jSONObject.put("avchannelmerge", 1);
                this.F = i2;
                this.h.streamPlayback(jSONObject.toString(), iMISSListener);
                this.E = (long) (i2 + i3);
            } catch (JSONException e2) {
                LogUtil.a(g, "JSONException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void b(int i2, int i3, int i4, IMISSListener iMISSListener) {
        LogUtil.a(g, "set play time " + i2 + " " + i3 + " " + i4);
        if (i2 != 0) {
            this.k = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(AgentOptions.i, i2);
                jSONObject.put(LogBuilder.i, i2);
                jSONObject.put(LogBuilder.j, i4);
                jSONObject.put("autoswitchtolive", 1);
                jSONObject.put("offset", i3);
                jSONObject.put("speed", this.G);
                jSONObject.put("avchannelmerge", 1);
                this.F = i2;
                this.h.streamPlayback(jSONObject.toString(), iMISSListener);
                this.E = (long) (i2 + i3);
            } catch (JSONException e2) {
                LogUtil.a(g, "JSONException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void b(final int i2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("speed", i2);
            jSONObject.put(AgentOptions.i, this.F);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.h.streamPlaybackSpeed(jSONObject.toString(), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
                LogUtil.a(CameraPlayerEx.g, "streamPlaybackSpeed onSuccess:" + str + " " + obj);
                int unused = CameraPlayerEx.this.G = i2;
            }

            public void onFailed(int i, String str) {
                LogUtil.a(CameraPlayerEx.g, "streamPlaybackSpeed onFailed:" + i + " " + str);
            }
        });
    }

    public int v() {
        return this.G;
    }

    public void w() {
        this.k = false;
        this.y = false;
        if (this.C != null) {
            this.C.cancel();
        }
        this.h.setClientListener((IClientExListener) null);
        this.i = null;
        this.p = null;
        F();
    }

    private void a(byte[] bArr, long j2, int i2) {
        if (bArr != null && bArr.length != 0 && !this.m && this.k) {
            if (!this.v) {
                c(bArr.length);
                this.o.a(bArr, j2, i2);
            }
            if (this.u && this.t != null) {
                if (i2 != 136) {
                    if (this.U == null) {
                        this.U = new byte[10240];
                    }
                    int decode = G711.decode(bArr, 0, bArr.length, this.U);
                    if (this.V == null) {
                        this.V = new AACEncodeEx();
                        if (this.n == null || TextUtils.isEmpty(this.n.getModel()) || (!"chuangmi.camera.ipc019".equals(this.n.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.n.getModel()))) {
                            this.V.initial(8000, 1, RecordDevice.PCM_FREQUENCE_16K);
                        } else {
                            this.V.initial(RecordDevice.PCM_FREQUENCE_16K, 1, RecordDevice.PCM_FREQUENCE_16K);
                        }
                        this.W = new byte[2048];
                        this.X.b();
                    }
                    byte[] bArr2 = new byte[decode];
                    System.arraycopy(this.U, 0, bArr2, 0, decode);
                    this.X.b(bArr2);
                    while (this.X.a(this.W)) {
                        byte[] encode = this.V.encode(this.W, 0, this.W.length);
                        this.t.writeAAcData(encode, encode.length);
                    }
                    return;
                }
                this.t.writeAAcData(bArr, bArr.length);
            }
        }
    }

    private void a(AVFrame aVFrame) {
        if (this.y || aVFrame.isIFrame()) {
            if (!this.y && aVFrame.isIFrame()) {
                this.y = true;
                this.J = System.currentTimeMillis();
                G();
                this.q.post(new Runnable() {
                    public void run() {
                        if (CameraPlayerEx.this.i != null) {
                            CameraPlayerEx.this.i.onPrepare(101);
                        }
                        if (((MijiaCameraDevice) CameraPlayerEx.this.n).n()) {
                            ((MijiaCameraDevice) CameraPlayerEx.this.n).d().a((Callback<Void>) null, true);
                        } else {
                            ((MijiaCameraDevice) CameraPlayerEx.this.n).c().a((Callback<Void>) null, true);
                        }
                    }
                });
                this.K = ((MijiaCameraDevice) this.n).e().c();
            }
            c(aVFrame.frmData.length);
            if (this.d != null && this.x == aVFrame.isPlayback()) {
                this.d.onVideoLiveChanged(!aVFrame.isPlayback());
            }
            this.x = !aVFrame.isPlayback();
            if (!(this.c == null || this.r <= 0 || this.r == aVFrame.getVideoWidth())) {
                this.c.onResolutionChanged(aVFrame.getVideoWidth(), aVFrame.getVideoHeight());
            }
            this.r = aVFrame.getVideoWidth();
            this.s = aVFrame.getVideoHeight();
            this.x = !aVFrame.isPlayback();
            if (!this.x) {
                this.O = this.x;
            }
            if (!this.M || !this.x) {
                this.N = 0;
                if (!this.T || !this.x) {
                    if (this.u && this.t != null) {
                        int timeStamp = aVFrame.getTimeStamp();
                        if (!this.x && this.G == 4) {
                            timeStamp /= 4;
                        }
                        if (this.j != null) {
                            this.j.upDateTime(timeStamp);
                        }
                        if (this.r == 1920 && this.s == 1080) {
                            this.t.writeVideoData(aVFrame.frmData, aVFrame.frmData.length, aVFrame.isIFrame(), timeStamp);
                        }
                    }
                    try {
                        this.z++;
                        this.H++;
                        VideoFrame videoFrame = new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), aVFrame.getVideoType(), aVFrame.isIFrame(), (!this.x || !this.K) ? 0 : aVFrame.isWartTime() ? 2 : 1, this.x);
                        if (this.p != null) {
                            this.p.drawVideoFrame(videoFrame);
                        }
                        this.w = (long) aVFrame.getTimeStampReal();
                        if ((this.E == 0 && this.x && this.y) || (this.E > 0 && aVFrame.isIFrame() && !this.x)) {
                            this.E = -1;
                            this.q.post(new Runnable() {
                                public void run() {
                                    if (CameraPlayerEx.this.i != null) {
                                        CameraPlayerEx.this.i.onPrepare(102);
                                        if (CameraPlayerEx.this.f()) {
                                            int unused = CameraPlayerEx.this.G = 1;
                                        }
                                    }
                                }
                            });
                        }
                    } catch (Exception e2) {
                        SDKLog.e(g, e2.toString());
                    }
                } else {
                    LogUtil.a("xm111", "v3 upgrade received live frame when playbackmode, filtered");
                }
            } else if (!this.O) {
                this.N++;
                if (this.N > 10) {
                    if (this.i != null) {
                        this.i.onPauseCamera();
                    }
                    this.N = 0;
                }
            }
        }
    }

    public int x() {
        if (this.o != null) {
            return this.o.f8504a.g();
        }
        return 0;
    }

    public void y() {
        if (this.o != null && this.o.f8504a != null) {
            this.o.f8504a.h();
        }
    }

    public void c(boolean z2) {
        if (this.o != null && this.o.f8504a != null) {
            this.o.f8504a.a(z2);
        }
    }

    public void a(boolean z2, IMISSListener iMISSListener) {
        if (this.h != null && this.n != null) {
            this.h.streamToggleRemoteVideo(z2, iMISSListener);
        }
    }

    public void b(boolean z2, IMISSListener iMISSListener) {
        if (this.h != null && this.n != null) {
            this.h.streamToggleRemoteAudio(z2, iMISSListener);
        }
    }

    public void b(IMISSListener iMISSListener) {
        if (this.h != null) {
            this.h.streamGetDeviceInfo(iMISSListener);
        }
    }

    class AudioBroadcastReceiver extends BroadcastReceiver {
        AudioBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                if (HeadSetUtils.a()) {
                    CameraPlayerEx.this.o.a(3);
                } else if (HeadSetUtils.a(context)) {
                    CameraPlayerEx.this.o.a(2);
                } else {
                    CameraPlayerEx.this.o.a(1);
                }
            } else if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction()) && intent.hasExtra("state")) {
                intent.getIntExtra("state", 0);
                if (intent.getIntExtra("state", 0) == 0) {
                    if (HeadSetUtils.a()) {
                        CameraPlayerEx.this.o.a(3);
                    } else {
                        CameraPlayerEx.this.o.a(1);
                    }
                } else if (intent.getIntExtra("state", 0) == 1) {
                    CameraPlayerEx.this.o.a(2);
                }
            }
        }
    }

    private void E() {
        if (this.Z == null && this.L != null) {
            this.Z = new AudioBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            this.L.registerReceiver(this.Z, intentFilter);
        }
    }

    private void F() {
        if (this.Z != null && this.L != null) {
            this.L.unregisterReceiver(this.Z);
            this.Z = null;
        }
    }

    public void z() {
        this.h.streamCmdMessage(f, Util.a(CamCommand.a((long) ((int) ((System.currentTimeMillis() / 1000) + 1)))), new IMISSListener() {
            public void onFailed(int i, String str) {
            }

            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }
        });
    }

    public void a(IStreamCmdMessageListener iStreamCmdMessageListener) {
        this.R = iStreamCmdMessageListener;
        this.h.streamCmdMessage(f, Util.a(CamCommand.f()), new IMISSListener() {
            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }

            public void onFailed(int i, String str) {
                if (CameraPlayerEx.this.R != null) {
                    CameraPlayerEx.this.R.onSendCmdError();
                }
            }
        });
    }

    public void A() {
        this.h.streamCmdMessage(f, Util.a(CamCommand.a(DeviceUtils.a(XmPluginHostApi.instance().context()), true, true)), new IMISSListener() {
            public void onFailed(int i, String str) {
            }

            public void onProgress(int i) {
            }

            public void onSuccess(String str, Object obj) {
            }
        });
    }

    public void B() {
        if (this.h != null) {
            SDKLog.b(g, "CameraPlayer2Activity  打开设备端的声音");
            this.h.streamToggleRemoteAudio(true, (IMISSListener) null);
        }
    }

    private void G() {
        if (MIStreamStatistic.getInstance().latestCameraConnect > 0 && this.n != null) {
            try {
                long currentTimeMillis = System.currentTimeMillis() - MIStreamStatistic.getInstance().latestCameraConnect;
                if (currentTimeMillis >= 0) {
                    MIStreamStatistic.getInstance().sendCameraConnectDuration(this.n.getModel(), this.n.getDid(), currentTimeMillis);
                }
            } catch (Exception e2) {
                LogUtil.b(g, "" + e2.getLocalizedMessage());
            }
        }
    }
}
