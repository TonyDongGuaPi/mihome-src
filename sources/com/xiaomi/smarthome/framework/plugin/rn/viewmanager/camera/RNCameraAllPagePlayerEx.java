package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera;

import android.os.Handler;
import android.os.Looper;
import com.mijia.camera.MijiaCameraDevice;
import com.p2p.audionew.AudioEngineNew;
import com.tutk.IAudioSender;
import com.tutk.IOTC.Packet;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.camera.AVFrame;
import com.xiaomi.smarthome.camera.IClientExListener;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;

public class RNCameraAllPagePlayerEx implements IClientExListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17571a = "RNCameraAllPagePlayerEx";
    private static final int i = 2000;
    /* access modifiers changed from: private */
    public XmStreamClient b;
    private ICameraPlayerListener c;
    private volatile boolean d = false;
    private MijiaCameraDevice e;
    private AudioEngineNew f;
    /* access modifiers changed from: private */
    public volatile boolean g = true;
    private volatile boolean h;
    private volatile long j = System.currentTimeMillis();
    private int k = 0;
    private int l = 0;
    private Handler m = null;
    private int n = 3;
    private Runnable o = new Runnable() {
        public void run() {
            RNCameraAllPagePlayerEx.this.e();
        }
    };

    public interface ICameraPlayerListener {
        void a(String str, VideoFrame videoFrame);
    }

    public void onConnected() {
    }

    public void onCtrlData(int i2, byte[] bArr) {
    }

    public void onDisConnected() {
    }

    public void onDisconnectedWithCode(int i2) {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onRetry(int i2, String str, int i3) {
    }

    public RNCameraAllPagePlayerEx(MijiaCameraDevice mijiaCameraDevice, ICameraPlayerListener iCameraPlayerListener) {
        this.e = mijiaCameraDevice;
        this.b = this.e.v();
        if (this.b == null) {
            LogUtil.b(f17571a, "mXmStreamClient == null");
            return;
        }
        this.b.setClientListener(this);
        this.c = iCameraPlayerListener;
        this.f = new AudioEngineNew(XmPluginHostApi.instance().context());
        this.f.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                RNCameraAllPagePlayerEx.this.b.sendAudioData(bArr, i);
            }
        });
        this.m = new Handler(Looper.getMainLooper());
    }

    public void onProgress(int i2) {
        if (i2 < 100) {
            this.h = false;
        }
        LogUtil.a(f17571a, "progress - " + i2);
    }

    public void onError(int i2, String str) {
        LogUtil.b(f17571a, "error - " + i2 + ", " + str);
        this.n = this.n + -1;
        if (this.n > 0) {
            this.m.removeCallbacks(this.o);
            this.m.postDelayed(this.o, 3000);
        }
    }

    public void onAudioData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && this.d && !this.g) {
            try {
                short byteArrayToShort = Packet.byteArrayToShort(bArr2, 0, false);
                long byteArrayToInt = (long) Packet.byteArrayToInt(bArr2, 12, false);
                int byteArrayToInt2 = Packet.byteArrayToInt(bArr2, 8, false);
                short byteArrayToShort2 = Packet.byteArrayToShort(bArr2, 12, false);
                short byteArrayToShort3 = Packet.byteArrayToShort(bArr2, 14, false);
                if (this.f != null) {
                    this.f.a(bArr, byteArrayToInt, byteArrayToShort, byteArrayToInt2, byteArrayToShort2, byteArrayToShort3);
                }
            } catch (Exception e2) {
                LogUtil.b(f17571a, "onAudioData:" + e2.getLocalizedMessage());
            }
        }
    }

    public void onVideoData(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = bArr;
        byte[] bArr4 = bArr2;
        if (bArr3 != null && bArr4 != null && this.d) {
            if (!this.h) {
                this.h = true;
                this.b.streamToggleRemoteAudio(!this.g, (IMISSListener) null);
            }
            AVFrame aVFrame = new AVFrame((byte) 0, bArr2, bArr, bArr3.length, false);
            short codecId = aVFrame.getCodecId();
            this.k++;
            if (System.currentTimeMillis() - this.j > 2000) {
                LogUtil.a(f17571a, this.e.getDid() + " Receive Frame rate - " + (this.k / 2));
                this.k = 0;
                this.j = System.currentTimeMillis();
            }
            if (codecId != 138 && codecId != 136) {
                try {
                    this.c.a(this.e.getDid(), new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), bArr4[0] == 5 ? 2 : 1, aVFrame.isIFrame()));
                } catch (Exception e2) {
                    LogUtil.b(f17571a, "onVideoData:" + e2.getLocalizedMessage());
                }
            } else if (!this.g) {
                short byteArrayToShort = Packet.byteArrayToShort(bArr4, 0, false);
                long byteArrayToInt = (long) Packet.byteArrayToInt(bArr4, 12, false);
                int byteArrayToInt2 = Packet.byteArrayToInt(bArr4, 8, false);
                short byteArrayToShort2 = Packet.byteArrayToShort(bArr4, 12, false);
                short byteArrayToShort3 = Packet.byteArrayToShort(bArr4, 14, false);
                if (this.f != null) {
                    this.f.a(bArr, byteArrayToInt, byteArrayToShort, byteArrayToInt2, byteArrayToShort2, byteArrayToShort3);
                }
            }
        }
    }

    public boolean a() {
        return this.d && this.h;
    }

    public void b() {
        if (this.b != null) {
            LogUtil.a(f17571a, "startPlay");
            try {
                this.b.setClientListener(this);
                this.d = true;
                this.n = 3;
                this.f.c();
                this.b.streamStop((IMISSListener) null);
                this.b.streamStart(new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(RNCameraAllPagePlayerEx.f17571a, "startPlay:" + str);
                        RNCameraAllPagePlayerEx.this.b.streamToggleRemoteAudio(RNCameraAllPagePlayerEx.this.g ^ true, (IMISSListener) null);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(RNCameraAllPagePlayerEx.f17571a, "startPlay:" + i);
                    }
                });
            } catch (Exception e2) {
                LogUtil.b(f17571a, "startPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.b != null) {
            LogUtil.a(f17571a, "retryPlay");
            try {
                this.d = true;
                this.f.c();
                this.b.streamStart(new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(RNCameraAllPagePlayerEx.f17571a, "retryPlay:" + str);
                        RNCameraAllPagePlayerEx.this.b.streamToggleAudio(RNCameraAllPagePlayerEx.this.g ^ true, (IMISSListener) null);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(RNCameraAllPagePlayerEx.f17571a, "retryPlay:" + i);
                    }
                });
            } catch (Exception e2) {
                LogUtil.b(f17571a, "retryPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    public void c() {
        if (this.b != null) {
            LogUtil.a(f17571a, "stopPlay");
            try {
                this.d = false;
                this.b.streamStop(new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(RNCameraAllPagePlayerEx.f17571a, "stopPlay:" + str);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(RNCameraAllPagePlayerEx.f17571a, "stopPlay:" + i);
                    }
                });
                this.f.e();
                this.b.release(this);
                this.b.setClientListener((IClientExListener) null);
            } catch (Exception e2) {
                LogUtil.b(f17571a, "stopPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    public void d() {
        if (this.b != null) {
            LogUtil.a(f17571a, "release");
            try {
                this.b.release(this);
                this.b.setClientListener((IClientExListener) null);
                this.e.x();
            } catch (Exception e2) {
                LogUtil.b(f17571a, "release:" + e2.getLocalizedMessage());
            }
        }
    }

    public void a(boolean z) {
        this.g = z;
        if (this.b != null) {
            this.b.streamToggleRemoteAudio(!this.g, new IMISSListener() {
                public void onProgress(int i) {
                }

                public void onSuccess(String str, Object obj) {
                    LogUtil.a(RNCameraAllPagePlayerEx.f17571a, "result:" + str + " extra:" + obj);
                }

                public void onFailed(int i, String str) {
                    LogUtil.b(RNCameraAllPagePlayerEx.f17571a, "errorCode:" + i + " errorInfo:" + str);
                }
            });
        }
    }

    public void a(int i2) {
        if (this.b != null) {
            this.l = i2;
            this.b.streamResolution(i2, (IMISSListener) null);
        }
    }
}
