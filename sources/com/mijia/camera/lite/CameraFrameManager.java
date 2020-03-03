package com.mijia.camera.lite;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.mijia.app.AppConfig;
import com.mijia.app.Event;
import com.mijia.camera.CameraNativePluginManager;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.camera.lite.LiteCameraPlayer;
import com.mijia.camera.lite.LiteCameraPlayerEx;
import com.mijia.debug.SDKLog;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.library.common.ThreadPool;
import java.util.HashMap;

public class CameraFrameManager {
    private static final String b = "CameraFrameManager";
    private static final int c = 1;
    private static CameraFrameManager i;

    /* renamed from: a  reason: collision with root package name */
    HandlerThread f7914a = new HandlerThread("camera_frame_receiver");
    /* access modifiers changed from: private */
    public HashMap<String, Object> d = new HashMap<>();
    private Handler e;
    private XmPluginPackage f;
    /* access modifiers changed from: private */
    public int g = 1;
    /* access modifiers changed from: private */
    public boolean h = true;

    class FrameHandler extends Handler {
        FrameHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                final MijiaCameraDevice mijiaCameraDevice = (MijiaCameraDevice) message.obj;
                mijiaCameraDevice.f(new Callback<Void>() {
                    public void onFailure(int i, String str) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        CameraFrameManager.this.a(mijiaCameraDevice);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final MijiaCameraDevice mijiaCameraDevice) {
        if (mijiaCameraDevice == null || !mijiaCameraDevice.n()) {
            LogUtil.a(b, "startPlay:" + mijiaCameraDevice.getModel() + ":" + mijiaCameraDevice.getDid());
            LiteCameraPlayer liteCameraPlayer = new LiteCameraPlayer(mijiaCameraDevice, new LiteCameraPlayer.ICameraPlayerListener() {
                public void a(String str, VideoFrame videoFrame) {
                    VideoFrame videoFrame2 = videoFrame;
                    if (CameraNativePluginManager.a().b(mijiaCameraDevice.getModel()) == 0) {
                        XmPluginHostApi.instance().sendCameraFrame(str, videoFrame2.data, videoFrame2.num, videoFrame2.size, videoFrame2.timeStamp, 2, videoFrame2.isIFrame, videoFrame2.width, videoFrame2.height);
                        return;
                    }
                    XmPluginHostApi.instance().sendCameraFrame(str, videoFrame2.data, videoFrame2.num, videoFrame2.size, videoFrame2.timeStamp, videoFrame2.isIFrame, videoFrame2.width, videoFrame2.height);
                }
            });
            liteCameraPlayer.a(this.h);
            liteCameraPlayer.a(this.g);
            liteCameraPlayer.b();
            this.d.put(mijiaCameraDevice.getDid(), liteCameraPlayer);
            return;
        }
        LogUtil.a(b, "startPlayEx:" + mijiaCameraDevice.getModel() + ":" + mijiaCameraDevice.getDid());
        LiteCameraPlayerEx liteCameraPlayerEx = new LiteCameraPlayerEx(mijiaCameraDevice, new LiteCameraPlayerEx.ICameraPlayerListener() {
            public void a(String str, VideoFrame videoFrame) {
                VideoFrame videoFrame2 = videoFrame;
                if (CameraNativePluginManager.a().b(mijiaCameraDevice.getModel()) == 0) {
                    XmPluginHostApi.instance().sendCameraFrame(str, videoFrame2.data, videoFrame2.num, videoFrame2.size, videoFrame2.timeStamp, 2, videoFrame2.isIFrame, videoFrame2.width, videoFrame2.height);
                    return;
                }
                XmPluginHostApi.instance().sendCameraFrame(str, videoFrame2.data, videoFrame2.num, videoFrame2.size, videoFrame2.timeStamp, videoFrame2.isIFrame, videoFrame2.width, videoFrame2.height);
            }
        });
        liteCameraPlayerEx.a(this.h);
        liteCameraPlayerEx.a(this.g);
        liteCameraPlayerEx.b();
        this.d.put(mijiaCameraDevice.getDid(), liteCameraPlayerEx);
    }

    public static CameraFrameManager a() {
        if (i == null) {
            i = new CameraFrameManager();
        }
        return i;
    }

    private CameraFrameManager() {
        this.f7914a.start();
        this.e = new FrameHandler(this.f7914a.getLooper());
    }

    public void a(XmPluginPackage xmPluginPackage, DeviceStat deviceStat) {
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        if (a2 != null) {
            try {
                if (a2.n()) {
                    LogUtil.a(b, "stopPlayEx");
                    LiteCameraPlayerEx liteCameraPlayerEx = (LiteCameraPlayerEx) this.d.get(deviceStat.did);
                    if (liteCameraPlayerEx != null) {
                        liteCameraPlayerEx.c();
                        return;
                    }
                    return;
                }
            } catch (Exception e2) {
                LogUtil.b(b, "JSONException:" + e2.getLocalizedMessage());
                return;
            }
        }
        LogUtil.a(b, "stopPlay");
        LiteCameraPlayer liteCameraPlayer = (LiteCameraPlayer) this.d.get(deviceStat.did);
        if (liteCameraPlayer != null) {
            liteCameraPlayer.c();
        }
    }

    public void b(XmPluginPackage xmPluginPackage, DeviceStat deviceStat) {
        XmPluginHostApi.instance().initCameraFrameSender(deviceStat.did);
    }

    public void c(XmPluginPackage xmPluginPackage, DeviceStat deviceStat) {
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        if (a2 != null) {
            try {
                if (a2.n()) {
                    LogUtil.a(b, "stopRequestDataEx");
                    LiteCameraPlayerEx liteCameraPlayerEx = (LiteCameraPlayerEx) this.d.get(deviceStat.did);
                    if (liteCameraPlayerEx != null) {
                        liteCameraPlayerEx.d();
                    }
                    XmPluginHostApi.instance().closeCameraFrameSender(deviceStat.did);
                    this.d.remove(deviceStat.did);
                    return;
                }
            } catch (Exception e2) {
                LogUtil.b(b, "JSONException:" + e2.getLocalizedMessage());
                return;
            }
        }
        LogUtil.a(b, "stopRequestData");
        LiteCameraPlayer liteCameraPlayer = (LiteCameraPlayer) this.d.get(deviceStat.did);
        if (liteCameraPlayer != null) {
            liteCameraPlayer.d();
        }
        XmPluginHostApi.instance().closeCameraFrameSender(deviceStat.did);
        this.d.remove(deviceStat.did);
    }

    public boolean a(String str) {
        return this.d.get(str) != null;
    }

    public void a(XmPluginPackage xmPluginPackage, final DeviceStat deviceStat, Intent intent) {
        if (intent != null) {
            this.g = intent.getIntExtra("request_frame_rate", 1);
            this.h = intent.getBooleanExtra("mute", true);
        }
        MijiaCameraDevice a2 = MijiaCameraDevice.a(deviceStat);
        if (a2 != null) {
            try {
                if (a2.n()) {
                    SDKLog.a(a2.getModel());
                    LogUtil.a(b, "startRequestDataEx");
                    LiteCameraPlayerEx liteCameraPlayerEx = (LiteCameraPlayerEx) this.d.get(deviceStat.did);
                    if (liteCameraPlayerEx != null) {
                        liteCameraPlayerEx.a(this.h);
                        if (liteCameraPlayerEx.a()) {
                            liteCameraPlayerEx.a(this.g);
                        } else {
                            liteCameraPlayerEx.a(this.g);
                            liteCameraPlayerEx.b();
                        }
                        LogUtil.a(b, "playerEx not null");
                        return;
                    }
                    ThreadPool.a(new Runnable() {
                        public void run() {
                            final MijiaCameraDevice mijiaCameraDevice = new MijiaCameraDevice(deviceStat);
                            LiteCameraPlayerEx liteCameraPlayerEx = new LiteCameraPlayerEx(mijiaCameraDevice, new LiteCameraPlayerEx.ICameraPlayerListener() {
                                public void a(String str, VideoFrame videoFrame) {
                                    VideoFrame videoFrame2 = videoFrame;
                                    if (CameraNativePluginManager.a().b(mijiaCameraDevice.getModel()) == 0) {
                                        XmPluginHostApi.instance().sendCameraFrame(str, videoFrame2.data, videoFrame2.num, videoFrame2.size, videoFrame2.timeStamp, 2, videoFrame2.isIFrame, videoFrame2.width, videoFrame2.height);
                                        return;
                                    }
                                    XmPluginHostApi.instance().sendCameraFrame(str, videoFrame2.data, videoFrame2.num, videoFrame2.size, videoFrame2.timeStamp, videoFrame2.isIFrame, videoFrame2.width, videoFrame2.height);
                                }
                            });
                            liteCameraPlayerEx.a(CameraFrameManager.this.h);
                            liteCameraPlayerEx.a(CameraFrameManager.this.g);
                            liteCameraPlayerEx.b();
                            CameraFrameManager.this.d.put(mijiaCameraDevice.getDid(), liteCameraPlayerEx);
                            LogUtil.b(CameraFrameManager.b, "playerEx null");
                        }
                    });
                    return;
                }
            } catch (Exception e2) {
                LogUtil.b(b, "JSONException:" + e2.getLocalizedMessage());
                return;
            }
        }
        LogUtil.a(b, "startRequestData");
        LiteCameraPlayer liteCameraPlayer = (LiteCameraPlayer) this.d.get(deviceStat.did);
        if (liteCameraPlayer == null) {
            MijiaCameraDevice mijiaCameraDevice = new MijiaCameraDevice(deviceStat);
            this.f = xmPluginPackage;
            SDKLog.a(mijiaCameraDevice.getModel());
            Event.a(mijiaCameraDevice.getDid(), mijiaCameraDevice.getModel());
            AppConfig.a(XmPluginHostApi.instance().context());
            Message message = new Message();
            message.what = 1;
            message.obj = mijiaCameraDevice;
            this.e.sendMessage(message);
            return;
        }
        liteCameraPlayer.a(this.h);
        if (liteCameraPlayer.a()) {
            liteCameraPlayer.a(this.g);
            return;
        }
        liteCameraPlayer.a(this.g);
        liteCameraPlayer.b();
    }
}
