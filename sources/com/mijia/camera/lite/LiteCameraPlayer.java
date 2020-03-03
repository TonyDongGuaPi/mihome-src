package com.mijia.camera.lite;

import android.text.TextUtils;
import android.util.Log;
import com.mijia.camera.MijiaCameraDevice;
import com.p2p.audio.AudioEngine;
import com.tutk.IAudioSender;
import com.tutk.IOTC.Packet;
import com.xiaomi.chatbot.speechsdk.record.RecordDevice;
import com.xiaomi.smarthome.camera.AVFrame;
import com.xiaomi.smarthome.camera.IClientListener;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmCameraP2p;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.plugin.DeviceConstant;

public class LiteCameraPlayer implements IClientListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7921a = "LiteCameraPlayer";
    private static final int i = 2000;
    /* access modifiers changed from: private */
    public XmCameraP2p b;
    private ICameraPlayerListener c;
    private volatile boolean d = false;
    /* access modifiers changed from: private */
    public MijiaCameraDevice e;
    private AudioEngine f;
    private volatile boolean g = true;
    private volatile boolean h;
    private volatile long j = System.currentTimeMillis();
    private int k = 0;
    private int l = 0;
    private int m = 3;

    public interface ICameraPlayerListener {
        void a(String str, VideoFrame videoFrame);
    }

    public void onCtrlData(int i2, byte[] bArr) {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onRetry(int i2, String str, int i3) {
    }

    LiteCameraPlayer(MijiaCameraDevice mijiaCameraDevice, ICameraPlayerListener iCameraPlayerListener) {
        this.e = mijiaCameraDevice;
        this.b = this.e.u();
        if (this.b != null) {
            this.b.setClientListener(this);
            this.b.updateInfo(this.e.t());
        }
        this.c = iCameraPlayerListener;
        if (this.e == null || TextUtils.isEmpty(this.e.getModel()) || (!"chuangmi.camera.ipc019".equals(this.e.getModel()) && !DeviceConstant.CHUANGMI_CAMERA_021.equals(this.e.getModel()))) {
            this.f = new AudioEngine(XmPluginHostApi.instance().context(), 8000, this.e.getModel());
        } else {
            this.f = new AudioEngine(XmPluginHostApi.instance().context(), RecordDevice.PCM_FREQUENCE_16K, this.e.getModel());
        }
        this.f.a((IAudioSender) new IAudioSender() {
            public void onSendAudio(byte[] bArr, int i) {
                LiteCameraPlayer.this.b.sendAudioData(bArr, i);
            }
        });
    }

    public void onProgress(int i2) {
        if (i2 < 100) {
            this.h = false;
        }
        LogUtil.b(f7921a, "progress - " + i2);
    }

    public void onConnected() {
        this.b.resume();
    }

    public void onDisConnected() {
        b();
    }

    public void onError(int i2, String str) {
        LogUtil.b(f7921a, "error - " + i2 + ", " + str);
        if (i2 == -20009) {
            this.e.f(new Callback<Void>() {
                public void onFailure(int i, String str) {
                }

                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (LiteCameraPlayer.this.b != null) {
                        LiteCameraPlayer.this.b.updateInfo(LiteCameraPlayer.this.e.t());
                        LiteCameraPlayer.this.e();
                    }
                }
            });
            return;
        }
        this.m--;
        if (this.m > 0) {
            e();
        }
    }

    public void onAudioData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && this.d && !this.g) {
            short byteArrayToShort = Packet.byteArrayToShort(bArr2, 0, false);
            this.f.a(bArr, (long) Packet.byteArrayToInt(bArr2, 12, false), byteArrayToShort);
        }
    }

    public void onVideoData(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && this.d) {
            if (!this.h) {
                this.h = true;
            }
            AVFrame aVFrame = new AVFrame((byte) 0, bArr2, bArr, bArr.length, false);
            short codecId = aVFrame.getCodecId();
            this.k++;
            if (System.currentTimeMillis() - this.j > 2000) {
                Log.e(f7921a, this.e.getDid() + " Receive Frame rate - " + (this.k / 2));
                this.k = 0;
                this.j = System.currentTimeMillis();
            }
            if (codecId != 138 && codecId != 136) {
                try {
                    this.c.a(this.e.getDid(), new VideoFrame(aVFrame.frmData, aVFrame.getFrmNo(), aVFrame.getFrmSize(), aVFrame.getVideoWidth(), aVFrame.getVideoHeight(), (long) aVFrame.getTimeStamp(), aVFrame.isIFrame()));
                } catch (Exception e2) {
                    LogUtil.b(f7921a, "Exception:" + e2.getLocalizedMessage());
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
            LogUtil.a(f7921a, "startPlay");
            try {
                this.b.setClientListener(this);
                this.d = true;
                this.m = 3;
                this.f.c();
                this.b.connect();
            } catch (NullPointerException e2) {
                LogUtil.b(f7921a, "startPlay NullPointerException:" + e2.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.b != null) {
            LogUtil.a(f7921a, "retryPlay");
            try {
                this.d = true;
                this.f.c();
                this.b.reconnect();
            } catch (NullPointerException e2) {
                LogUtil.b(f7921a, "retryPlay NullPointerException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void c() {
        if (this.b != null) {
            LogUtil.a(f7921a, "stopPlay");
            try {
                this.d = false;
                this.b.pause(this);
                this.f.f();
                this.b.release(true, this);
                this.b.setClientListener((IClientListener) null);
            } catch (NullPointerException e2) {
                LogUtil.b(f7921a, "stopPlay NullPointerException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void d() {
        if (this.b != null) {
            LogUtil.a(f7921a, "release");
            try {
                this.b.release(true, this);
                this.b.setClientListener((IClientListener) null);
                this.e.x();
            } catch (NullPointerException e2) {
                LogUtil.b(f7921a, "release NullPointerException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void a(int i2) {
        if (this.b != null) {
            this.l = i2;
            this.b.setQuality(i2);
        }
    }
}
