package com.mijia.camera.lite;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.mijia.camera.MijiaCameraDevice;
import com.p2p.audio.AudioEngine;
import com.tutk.IAudioSender;
import com.tutk.IOTC.Packet;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.camera.AVFrame;
import com.xiaomi.smarthome.camera.IClientExListener;
import com.xiaomi.smarthome.camera.IMISSListener;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;

public class LiteCameraPlayerEx implements IClientExListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7924a = "LiteCameraPlayerEx";
    private static final int i = 2000;
    /* access modifiers changed from: private */
    public XmStreamClient b;
    private ICameraPlayerListener c;
    private volatile boolean d = false;
    private MijiaCameraDevice e;
    private AudioEngine f;
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
            LiteCameraPlayerEx.this.e();
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

    LiteCameraPlayerEx(MijiaCameraDevice mijiaCameraDevice, ICameraPlayerListener iCameraPlayerListener) {
        this.e = mijiaCameraDevice;
        this.b = this.e.v();
        if (this.b == null) {
            LogUtil.b(f7924a, "mXmStreamClient == null");
            return;
        }
        this.b.setClientListener(this);
        this.c = iCameraPlayerListener;
        if (this.e == null || TextUtils.isEmpty(this.e.getModel()) || (!"chuangmi.camera.ipc019".equals(this.e.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.e.getModel()))) {
            this.f = new AudioEngine(XmPluginHostApi.instance().context(), 8000, this.e.getModel());
        } else {
            this.f = new AudioEngine(XmPluginHostApi.instance().context(), RecordDevice.PCM_FREQUENCE_16K, this.e.getModel());
        }
        this.f.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                LiteCameraPlayerEx.this.b.sendAudioData(bArr, i);
            }
        });
        this.m = new Handler(Looper.getMainLooper());
    }

    public void onProgress(int i2) {
        if (i2 < 100) {
            this.h = false;
        }
        LogUtil.a(f7924a, "progress - " + i2);
    }

    public void onError(int i2, String str) {
        LogUtil.b(f7924a, "error - " + i2 + ", " + str);
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
                this.f.a(bArr, (long) Packet.byteArrayToInt(bArr2, 12, false), byteArrayToShort);
            } catch (Exception e2) {
                LogUtil.b(f7924a, "onAudioData:" + e2.getLocalizedMessage());
            }
        }
    }

    public void onVideoData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && this.d) {
            if (!this.h) {
                this.h = true;
                this.b.streamToggleRemoteAudio(!this.g, (IMISSListener) null);
            }
            AVFrame aVFrame = new AVFrame((byte) 0, bArr2, bArr, bArr.length, false);
            short codecId = aVFrame.getCodecId();
            this.k++;
            if (System.currentTimeMillis() - this.j > 2000) {
                LogUtil.a(f7924a, this.e.getDid() + " Receive Frame rate - " + (this.k / 2));
                this.k = 0;
                this.j = System.currentTimeMillis();
            }
            if (codecId != 138 && codecId != 136) {
                try {
                    this.c.a(this.e.getDid(), new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), aVFrame.isIFrame()));
                } catch (Exception e2) {
                    LogUtil.b(f7924a, "onVideoData:" + e2.getLocalizedMessage());
                }
            } else if (!this.g) {
                this.f.a(bArr, (long) aVFrame.getTimeStamp(), codecId);
            }
        }
    }

    public boolean a() {
        return this.d && this.h;
    }

    public void b() {
        if (this.b != null) {
            LogUtil.a(f7924a, "startPlay");
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
                        LogUtil.a(LiteCameraPlayerEx.f7924a, "startPlay:" + str);
                        LiteCameraPlayerEx.this.b.streamToggleRemoteAudio(LiteCameraPlayerEx.this.g ^ true, (IMISSListener) null);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(LiteCameraPlayerEx.f7924a, "startPlay:" + i);
                    }
                });
            } catch (Exception e2) {
                LogUtil.b(f7924a, "startPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.b != null) {
            LogUtil.a(f7924a, "retryPlay");
            try {
                this.d = true;
                this.f.c();
                this.b.streamStart(new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(LiteCameraPlayerEx.f7924a, "retryPlay:" + str);
                        LiteCameraPlayerEx.this.b.streamToggleAudio(LiteCameraPlayerEx.this.g ^ true, (IMISSListener) null);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(LiteCameraPlayerEx.f7924a, "retryPlay:" + i);
                    }
                });
            } catch (Exception e2) {
                LogUtil.b(f7924a, "retryPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    public void c() {
        if (this.b != null) {
            LogUtil.a(f7924a, "stopPlay");
            try {
                this.d = false;
                this.b.streamStop(new IMISSListener() {
                    public void onProgress(int i) {
                    }

                    public void onSuccess(String str, Object obj) {
                        LogUtil.a(LiteCameraPlayerEx.f7924a, "stopPlay:" + str);
                    }

                    public void onFailed(int i, String str) {
                        LogUtil.b(LiteCameraPlayerEx.f7924a, "stopPlay:" + i);
                    }
                });
                this.f.f();
                this.b.release(this);
                this.b.setClientListener((IClientExListener) null);
            } catch (Exception e2) {
                LogUtil.b(f7924a, "stopPlay:" + e2.getLocalizedMessage());
            }
        }
    }

    public void d() {
        if (this.b != null) {
            LogUtil.a(f7924a, "release");
            try {
                this.b.release(this);
                this.b.setClientListener((IClientExListener) null);
                this.e.x();
            } catch (Exception e2) {
                LogUtil.b(f7924a, "release:" + e2.getLocalizedMessage());
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
                    LogUtil.a(LiteCameraPlayerEx.f7924a, "result:" + str + " extra:" + obj);
                }

                public void onFailed(int i, String str) {
                    LogUtil.b(LiteCameraPlayerEx.f7924a, "errorCode:" + i + " errorInfo:" + str);
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
