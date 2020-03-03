package com.xiaomi.smarthome.framework.plugin.rn;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.debug.SDKLog;
import com.xiaomi.plugin.core.XmPluginPackage;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.camera.RNCameraAllPagePlayerEx;
import com.xiaomi.smarthome.library.common.ThreadPool;
import java.util.HashMap;

public class RNCameraFrameManager {
    private static final String b = "RNCameraFrameManager";
    private static final int c = 1;
    private static RNCameraFrameManager i;

    /* renamed from: a  reason: collision with root package name */
    HandlerThread f17241a = new HandlerThread("rn_camera_frame_receiver");
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
                        RNCameraFrameManager.this.a(mijiaCameraDevice);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(MijiaCameraDevice mijiaCameraDevice) {
        if (mijiaCameraDevice != null) {
            LogUtil.a(b, "startPlayEx:" + mijiaCameraDevice.getModel() + ":" + mijiaCameraDevice.getDid());
            RNCameraAllPagePlayerEx rNCameraAllPagePlayerEx = new RNCameraAllPagePlayerEx(mijiaCameraDevice, new RNCameraAllPagePlayerEx.ICameraPlayerListener() {
                public void a(String str, VideoFrame videoFrame) {
                    XmPluginHostApi.instance().sendCameraFrame(str, videoFrame.data, videoFrame.num, videoFrame.size, videoFrame.timeStamp, videoFrame.videoType, videoFrame.isIFrame, videoFrame.width, videoFrame.height);
                }
            });
            rNCameraAllPagePlayerEx.a(this.h);
            rNCameraAllPagePlayerEx.a(this.g);
            rNCameraAllPagePlayerEx.b();
            this.d.put(mijiaCameraDevice.getDid(), rNCameraAllPagePlayerEx);
        }
    }

    public static RNCameraFrameManager a() {
        if (i == null) {
            i = new RNCameraFrameManager();
        }
        return i;
    }

    private RNCameraFrameManager() {
        this.f17241a.start();
        this.e = new FrameHandler(this.f17241a.getLooper());
    }

    public void a(XmPluginPackage xmPluginPackage, DeviceStat deviceStat) {
        if (MijiaCameraDevice.a(deviceStat) != null) {
            try {
                LogUtil.a(b, "stopPlayEx");
                RNCameraAllPagePlayerEx rNCameraAllPagePlayerEx = (RNCameraAllPagePlayerEx) this.d.get(deviceStat.did);
                if (rNCameraAllPagePlayerEx != null) {
                    rNCameraAllPagePlayerEx.c();
                }
            } catch (Exception e2) {
                LogUtil.b(b, "JSONException:" + e2.getLocalizedMessage());
            }
        }
    }

    public void b(XmPluginPackage xmPluginPackage, DeviceStat deviceStat) {
        XmPluginHostApi.instance().initCameraFrameSender(deviceStat.did);
    }

    public void c(XmPluginPackage xmPluginPackage, DeviceStat deviceStat) {
        if (MijiaCameraDevice.a(deviceStat) != null) {
            try {
                LogUtil.a(b, "stopRequestDataEx");
                RNCameraAllPagePlayerEx rNCameraAllPagePlayerEx = (RNCameraAllPagePlayerEx) this.d.get(deviceStat.did);
                if (rNCameraAllPagePlayerEx != null) {
                    rNCameraAllPagePlayerEx.d();
                }
                XmPluginHostApi.instance().closeCameraFrameSender(deviceStat.did);
                this.d.remove(deviceStat.did);
            } catch (Exception e2) {
                LogUtil.b(b, "JSONException:" + e2.getLocalizedMessage());
            }
        }
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
                SDKLog.a(a2.getModel());
                LogUtil.a(b, "startRequestDataEx");
                RNCameraAllPagePlayerEx rNCameraAllPagePlayerEx = (RNCameraAllPagePlayerEx) this.d.get(deviceStat.did);
                if (rNCameraAllPagePlayerEx != null) {
                    rNCameraAllPagePlayerEx.a(this.h);
                    if (rNCameraAllPagePlayerEx.a()) {
                        rNCameraAllPagePlayerEx.a(this.g);
                    } else {
                        rNCameraAllPagePlayerEx.a(this.g);
                        rNCameraAllPagePlayerEx.b();
                    }
                    LogUtil.a(b, "playerEx not null");
                    return;
                }
                ThreadPool.a(new Runnable() {
                    public void run() {
                        MijiaCameraDevice mijiaCameraDevice = new MijiaCameraDevice(deviceStat);
                        RNCameraAllPagePlayerEx rNCameraAllPagePlayerEx = new RNCameraAllPagePlayerEx(mijiaCameraDevice, new RNCameraAllPagePlayerEx.ICameraPlayerListener() {
                            public void a(String str, VideoFrame videoFrame) {
                                XmPluginHostApi.instance().sendCameraFrame(str, videoFrame.data, videoFrame.num, videoFrame.size, videoFrame.timeStamp, videoFrame.videoType, videoFrame.isIFrame, videoFrame.width, videoFrame.height);
                            }
                        });
                        rNCameraAllPagePlayerEx.a(RNCameraFrameManager.this.h);
                        rNCameraAllPagePlayerEx.a(RNCameraFrameManager.this.g);
                        rNCameraAllPagePlayerEx.b();
                        RNCameraFrameManager.this.d.put(mijiaCameraDevice.getDid(), rNCameraAllPagePlayerEx);
                        LogUtil.b(RNCameraFrameManager.b, "playerEx null");
                    }
                });
            } catch (Exception e2) {
                LogUtil.b(b, "JSONException:" + e2.getLocalizedMessage());
            }
        }
    }
}
