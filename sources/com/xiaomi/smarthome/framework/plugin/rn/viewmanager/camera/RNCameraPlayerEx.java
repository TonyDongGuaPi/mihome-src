package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.camera.Utils.HeadSetUtils;
import com.mijia.debug.SDKLog;
import com.p2p.audionew.AudioEngineNew;
import com.tutk.IAudioSender;
import com.tutk.IOTC.Packet;
import com.xiaomi.CameraDevice;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.camera.AVFrame;
import com.xiaomi.smarthome.camera.IClientExListener;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.IRdtDataListener;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera.RNCameraManager;
import com.xiaomi.smarthome.library.common.ThreadPool;

public class RNCameraPlayerEx implements IClientExListener, IRdtDataListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f17594a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    private static final long e = 2000;
    /* access modifiers changed from: private */
    public static String f = "RNCameraPlayerEx";
    private int g = 1030;
    private Handler h;
    private MijiaCameraDevice i;
    /* access modifiers changed from: private */
    public XmStreamClient j;
    /* access modifiers changed from: private */
    public AudioEngineNew k;
    private boolean l;
    private boolean m = true;
    private boolean n = false;
    private int o;
    private long p;
    private AudioBroadcastReceiver q;
    private boolean r;

    public void onDisConnected() {
    }

    public void onPause() {
    }

    public void onProgress(int i2) {
    }

    public void onResume() {
    }

    public void onRetry(int i2, String str, int i3) {
    }

    public RNCameraPlayerEx(MijiaCameraDevice mijiaCameraDevice) {
        this.i = mijiaCameraDevice;
        this.j = this.i.v();
        if (this.j == null) {
            LogUtil.b(f, "mXmStreamClient == null");
            return;
        }
        h();
        this.j.setClientListener(this);
        this.j.setAutoVideo(false);
        this.h = new Handler(Looper.getMainLooper());
        this.m = true;
        this.j.setRdtDataListener(this);
        this.k = new AudioEngineNew(XmPluginHostApi.instance().context());
        this.k.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                RNCameraPlayerEx.this.j.sendAudioData(bArr, i);
            }
        });
        this.k.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                RNCameraPlayerEx.this.j.sendAudioData(bArr, i);
            }
        });
    }

    public void onError(int i2, String str) {
        a(0, i2);
    }

    public void onConnected() {
        a(2, 0);
    }

    public void onDisconnectedWithCode(int i2) {
        RnPluginLog.d("on disconnect:" + i2);
        RNCameraManager.a().a(this.i.deviceStat(), 0, i2);
    }

    public void onCtrlData(int i2, byte[] bArr) {
        RNCameraManager.a().a(this.i.deviceStat(), i2, bArr);
    }

    public void onAudioData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && this.l && !this.m) {
            try {
                short byteArrayToShort = Packet.byteArrayToShort(bArr2, 0, false);
                long byteArrayToInt = (long) Packet.byteArrayToInt(bArr2, 12, false);
                int byteArrayToInt2 = Packet.byteArrayToInt(bArr2, 8, false);
                short byteArrayToShort2 = Packet.byteArrayToShort(bArr2, 12, false);
                short byteArrayToShort3 = Packet.byteArrayToShort(bArr2, 14, false);
                if (this.k != null) {
                    this.k.a(bArr, byteArrayToInt, byteArrayToShort, byteArrayToInt2, byteArrayToShort2, byteArrayToShort3);
                }
            } catch (Exception e2) {
                String str = f;
                LogUtil.b(str, "onAudioData:" + e2.getLocalizedMessage());
            }
        }
    }

    public void onVideoData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && this.l) {
            AVFrame aVFrame = new AVFrame((byte) 0, bArr2, bArr, bArr.length, false);
            this.o++;
            if (System.currentTimeMillis() - this.p > 2000) {
                LogUtil.a(f, this.i.getDid() + " Receive Frame rate - " + (this.o / 2));
                this.o = 0;
                this.p = System.currentTimeMillis();
            }
            try {
                if (!this.n) {
                    this.n = true;
                    a(3, 0);
                }
                RNCameraManager.a().a((CameraDevice) this.i, new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), aVFrame.isIFrame()));
            } catch (Exception e2) {
                LogUtil.b(f, "onVideoData:" + e2.getLocalizedMessage());
            }
        }
    }

    public void a(DeviceStat deviceStat, final RNCameraManager.RNCallback rNCallback) {
        if (g()) {
            this.l = true;
            this.j.streamStart(new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    rNCallback.a(str, obj);
                }

                public void onFailed(int i, String str) {
                    if (rNCallback != null) {
                        rNCallback.a(i, str);
                    }
                }
            });
        }
    }

    private boolean g() {
        LogUtil.a(f, "initial");
        return true;
    }

    public void a(byte[] bArr, final RNCameraManager.RNCallback rNCallback) {
        if (!this.j.isConnected()) {
            rNCallback.a(-8888, "");
        } else {
            this.j.sendRdtCmd(bArr, new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    rNCallback.a(str, obj);
                }

                public void onFailed(int i, String str) {
                    rNCallback.a(i, str);
                }
            });
        }
    }

    public void a(int i2, String str, final RNCameraManager.RNCallback rNCallback) {
        RnPluginLog.d("rncamera send server cmd " + i2);
        if (!this.j.isConnected()) {
            rNCallback.a(-8888, "");
        } else {
            this.j.streamCmdMessage(i2, str, new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    rNCallback.a(str, obj);
                }

                public void onFailed(int i, String str) {
                    rNCallback.a(i, str);
                }
            });
        }
    }

    public void a() {
        this.r = true;
        this.m = false;
        if (this.k != null) {
            this.k.d();
        }
    }

    public void b() {
        this.r = false;
        if (this.k != null) {
            this.k.e();
            this.j.markCallStarted(false);
            this.k.c();
        }
    }

    public void a(boolean z) {
        this.m = z;
        if (this.k == null) {
            return;
        }
        if (z) {
            this.k.f();
        } else {
            this.k.c();
        }
    }

    public void b(boolean z) {
        this.l = false;
        SDKLog.b(f, "release");
        try {
            j();
            this.k.e();
            this.i.x();
            if (this.j != null && z) {
                this.j.streamStop((IMISSListener) null);
                this.j.release(this);
            }
            this.n = false;
            i();
        } catch (Exception e2) {
            String str = f;
            LogUtil.b(str, "release:" + e2.getLocalizedMessage());
        }
    }

    private void h() {
        if (this.q == null && XmPluginHostApi.instance().context() != null) {
            this.q = new AudioBroadcastReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.HEADSET_PLUG");
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            XmPluginHostApi.instance().context().registerReceiver(this.q, intentFilter);
        }
    }

    private void i() {
        if (this.q != null && XmPluginHostApi.instance().context() != null) {
            XmPluginHostApi.instance().context().unregisterReceiver(this.q);
            this.q = null;
        }
    }

    private void j() {
        ThreadPool.a(new Runnable() {
            public void run() {
                try {
                    if (RNCameraPlayerEx.this.k != null) {
                        RNCameraPlayerEx.this.k.f();
                    }
                } catch (NullPointerException e) {
                    String f = RNCameraPlayerEx.f;
                    LogUtil.b(f, "NullPointerException clearQueue:" + e.getLocalizedMessage());
                }
            }
        });
    }

    public void c() {
        this.l = false;
        if (this.k != null) {
            this.k.e();
        }
    }

    public boolean d() {
        return this.j.isConnected();
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (this.k != null) {
            this.k.a(i2, i3, i4);
            this.k.b(i5);
            this.k.d();
            this.j.markCallStarted(true);
        }
    }

    public void a(int i2) {
        if (this.k != null) {
            this.m = false;
            this.k.b(i2);
            this.k.c();
        }
    }

    public void e() {
        if (this.k != null) {
            this.m = true;
            this.k.e();
        }
    }

    private void a(int i2, int i3) {
        RNCameraManager.a().a(this.i.deviceStat(), i2, i3);
    }

    public void onRdtDataReceived(byte[] bArr) {
        RNCameraManager.a().a(this.i.deviceStat(), bArr);
    }

    class AudioBroadcastReceiver extends BroadcastReceiver {
        AudioBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                if (HeadSetUtils.a()) {
                    RNCameraPlayerEx.this.k.a(3);
                } else if (HeadSetUtils.a(context)) {
                    RNCameraPlayerEx.this.k.a(2);
                } else {
                    RNCameraPlayerEx.this.k.a(1);
                }
            } else if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction()) && intent.hasExtra("state")) {
                intent.getIntExtra("state", 0);
                if (intent.getIntExtra("state", 0) == 0) {
                    if (HeadSetUtils.a()) {
                        RNCameraPlayerEx.this.k.a(3);
                    } else {
                        RNCameraPlayerEx.this.k.a(1);
                    }
                } else if (intent.getIntExtra("state", 0) == 1) {
                    RNCameraPlayerEx.this.k.a(2);
                }
            }
        }
    }
}
