package com.xiaomi.smarthome.newui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.renderer.PluginDownloadingRecord;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.newui.widget.DeviceInstaller;
import com.xiaomi.smarthome.newui.widget.micards.DownloadProgressProcessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceDownloadItemViewWrapper implements ProgressItemView {

    /* renamed from: a  reason: collision with root package name */
    protected static Map<Device, PluginDownloadingRecord> f20832a = new HashMap();
    /* access modifiers changed from: private */
    public Device b;
    /* access modifiers changed from: private */
    public ProgressItemView c;
    private Context d;
    /* access modifiers changed from: private */
    public ProgressCallback e;
    /* access modifiers changed from: private */
    public DownloadProgressProcessor f;
    /* access modifiers changed from: private */
    public DeviceInstaller g;
    private Timer h = new Timer();
    private TimerTask i;
    /* access modifiers changed from: private */
    public int j = 91;
    /* access modifiers changed from: private */
    public Handler k = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1 && DeviceDownloadItemViewWrapper.this.c != null) {
                DeviceDownloadItemViewWrapper.this.c.setPercent((float) DeviceDownloadItemViewWrapper.this.j);
            }
        }
    };

    public interface ProgressCallback {
        void a();

        void a(float f);

        void b();

        void c();

        void d();

        void e();
    }

    static /* synthetic */ int i(DeviceDownloadItemViewWrapper deviceDownloadItemViewWrapper) {
        int i2 = deviceDownloadItemViewWrapper.j;
        deviceDownloadItemViewWrapper.j = i2 + 1;
        return i2;
    }

    public DeviceDownloadItemViewWrapper(Context context, ProgressItemView progressItemView, Device device) {
        this.b = device;
        this.d = context;
        this.c = progressItemView;
    }

    public void a(ProgressCallback progressCallback) {
        this.e = progressCallback;
    }

    public void a(DownloadProgressProcessor downloadProgressProcessor) {
        this.f = downloadProgressProcessor;
    }

    public void a(DeviceInstaller deviceInstaller) {
        this.g = deviceInstaller;
    }

    public PluginApi.SendMessageHandle a() {
        return b(this.b);
    }

    public void a(final Device device) {
        PluginRecord d2 = CoreApi.a().d(device.model);
        device.setLaunchParams(new Intent());
        final boolean z = !d2.l() && !d2.k();
        PluginApi.getInstance().installPlugin(this.d, d2, new PluginApi.SendMessageCallback() {
            private static final float h = 85.0f;

            /* renamed from: a  reason: collision with root package name */
            ValueAnimator f20834a;
            private float e = 0.0f;
            private long f;
            private final Interpolator g = new DecelerateInterpolator();

            /* access modifiers changed from: package-private */
            public float a() {
                if (this.f20834a == null) {
                    synchronized (this) {
                        double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f)) / 4000.0f);
                        Double.isNaN(min);
                        this.f20834a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                        this.f20834a.setDuration(4000);
                        this.f20834a.setInterpolator(this.g);
                        this.f20834a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if (DeviceDownloadItemViewWrapper.this.c != null) {
                                    DeviceDownloadItemViewWrapper.this.c.setPercent((float) ((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f)));
                                }
                            }
                        });
                        this.f20834a.start();
                    }
                }
                return ((Float) this.f20834a.getAnimatedValue()).floatValue();
            }

            public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                super.onDownloadStart(pluginRecord, pluginDownloadTask);
                if (DeviceDownloadItemViewWrapper.f20832a.get(device) == null) {
                    PluginDownloadingRecord pluginDownloadingRecord = new PluginDownloadingRecord();
                    pluginDownloadingRecord.f15409a = device.model;
                    pluginDownloadingRecord.b = PluginDownloadingRecord.Status.DOWNLOADING;
                    DeviceDownloadItemViewWrapper.f20832a.put(device, pluginDownloadingRecord);
                }
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.a();
                }
                if (DeviceDownloadItemViewWrapper.this.c != null) {
                    DeviceDownloadItemViewWrapper.this.c.setStart();
                }
                this.e = 0.0f;
            }

            public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                super.onDownloadProgress(pluginRecord, f2);
                if (((double) (f2 - this.e)) >= 0.005d || f2 >= 1.0f) {
                    PluginDownloadingRecord pluginDownloadingRecord = DeviceDownloadItemViewWrapper.f20832a.get(device);
                    if (pluginDownloadingRecord != null) {
                        if (z && ((double) f2) >= 0.99d) {
                            f2 = 0.99f;
                        }
                        pluginDownloadingRecord.c = f2;
                    }
                    if (DeviceDownloadItemViewWrapper.this.e != null) {
                        DeviceDownloadItemViewWrapper.this.e.a(f2);
                    }
                    int i = (int) (100.0f * f2);
                    if (i == 99) {
                        if (this.f == 0) {
                            this.f = System.currentTimeMillis();
                        }
                        a();
                    } else if (DeviceDownloadItemViewWrapper.this.c != null) {
                        ProgressItemView a2 = DeviceDownloadItemViewWrapper.this.c;
                        double d2 = (double) i;
                        Double.isNaN(d2);
                        a2.setPercent((float) (d2 * 0.85d));
                    }
                    this.e = f2;
                }
            }

            public void onInstallSuccess(PluginRecord pluginRecord) {
                super.onInstallSuccess(pluginRecord);
                DeviceDownloadItemViewWrapper.this.setSuccess();
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.c();
                }
            }

            public void onDownloadSuccess(PluginRecord pluginRecord) {
                super.onDownloadSuccess(pluginRecord);
                if (!z) {
                    DeviceDownloadItemViewWrapper.f20832a.remove(device);
                }
                DeviceDownloadItemViewWrapper.this.b();
            }

            public void onDownloadFailure(PluginError pluginError) {
                super.onDownloadFailure(pluginError);
                if (!z) {
                    DeviceDownloadItemViewWrapper.f20832a.remove(device);
                }
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.d();
                }
                DeviceDownloadItemViewWrapper.this.c.setFail();
            }

            public void onDownloadCancel() {
                super.onDownloadCancel();
                if (!z) {
                    DeviceDownloadItemViewWrapper.f20832a.remove(device);
                }
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.e();
                }
                DeviceDownloadItemViewWrapper.this.c.setCancel();
            }
        });
    }

    public PluginApi.SendMessageHandle b(final Device device) {
        PluginRecord d2 = CoreApi.a().d(device.model);
        Intent intent = new Intent();
        device.setLaunchParams(intent);
        final boolean z = !d2.l() && !d2.k();
        return PluginApi.getInstance().sendMessage(this.d, d2, 1, intent, device.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
            private static final float h = 85.0f;

            /* renamed from: a  reason: collision with root package name */
            ValueAnimator f20836a;
            private float e = 0.0f;
            private long f;
            private final Interpolator g = new DecelerateInterpolator();

            /* access modifiers changed from: package-private */
            public float a() {
                if (this.f20836a == null) {
                    synchronized (this) {
                        double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.f)) / 4000.0f);
                        Double.isNaN(min);
                        this.f20836a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                        this.f20836a.setDuration(4000);
                        this.f20836a.setInterpolator(this.g);
                        this.f20836a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if (DeviceDownloadItemViewWrapper.this.c != null) {
                                    DeviceDownloadItemViewWrapper.this.c.setPercent((float) ((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f)));
                                }
                            }
                        });
                        this.f20836a.start();
                    }
                }
                return ((Float) this.f20836a.getAnimatedValue()).floatValue();
            }

            public void onSendSuccess(Bundle bundle) {
                super.onSendSuccess(bundle);
            }

            public void onSendCancel() {
                super.onSendCancel();
            }

            public void onSendFailure(Error error) {
                super.onSendFailure(error);
            }

            public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                super.onDownloadStart(pluginRecord, pluginDownloadTask);
                if (DeviceDownloadItemViewWrapper.f20832a.get(device) == null) {
                    PluginDownloadingRecord pluginDownloadingRecord = new PluginDownloadingRecord();
                    pluginDownloadingRecord.f15409a = device.model;
                    pluginDownloadingRecord.b = PluginDownloadingRecord.Status.DOWNLOADING;
                    DeviceDownloadItemViewWrapper.f20832a.put(device, pluginDownloadingRecord);
                }
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.a();
                }
                if (DeviceDownloadItemViewWrapper.this.c != null) {
                    DeviceDownloadItemViewWrapper.this.c.setStart();
                }
                this.e = 0.0f;
            }

            public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                super.onDownloadProgress(pluginRecord, f2);
                if (((double) (f2 - this.e)) >= 0.005d || f2 >= 1.0f) {
                    PluginDownloadingRecord pluginDownloadingRecord = DeviceDownloadItemViewWrapper.f20832a.get(device);
                    if (pluginDownloadingRecord != null) {
                        if (z && ((double) f2) >= 0.99d) {
                            f2 = 0.99f;
                        }
                        pluginDownloadingRecord.c = f2;
                    }
                    if (DeviceDownloadItemViewWrapper.this.e != null) {
                        DeviceDownloadItemViewWrapper.this.e.a(f2);
                    }
                    int i = (int) (100.0f * f2);
                    if (i == 99) {
                        if (this.f == 0) {
                            this.f = System.currentTimeMillis();
                        }
                        a();
                    } else if (DeviceDownloadItemViewWrapper.this.c != null) {
                        ProgressItemView a2 = DeviceDownloadItemViewWrapper.this.c;
                        double d2 = (double) i;
                        Double.isNaN(d2);
                        a2.setPercent((float) (d2 * 0.85d));
                    }
                    this.e = f2;
                }
            }

            public void onDownloadSuccess(PluginRecord pluginRecord) {
                super.onDownloadSuccess(pluginRecord);
                if (!z) {
                    DeviceDownloadItemViewWrapper.f20832a.remove(device);
                }
                if (DeviceDownloadItemViewWrapper.this.f == null || (DeviceDownloadItemViewWrapper.this.f != null && !DeviceDownloadItemViewWrapper.this.f.shouldInstallNow(device))) {
                    DeviceDownloadItemViewWrapper.this.setSuccess();
                    if (DeviceDownloadItemViewWrapper.this.e != null) {
                        DeviceDownloadItemViewWrapper.this.e.c();
                        return;
                    }
                    return;
                }
                if (DeviceDownloadItemViewWrapper.this.g != null) {
                    DeviceDownloadItemViewWrapper.this.g.install(DeviceDownloadItemViewWrapper.this.b, new DeviceInstaller.InstallCallback() {
                        public void a() {
                        }

                        public void c() {
                        }

                        public void b() {
                            DeviceDownloadItemViewWrapper.this.setSuccess();
                            if (DeviceDownloadItemViewWrapper.this.e != null) {
                                DeviceDownloadItemViewWrapper.this.e.c();
                            }
                            DeviceDownloadItemViewWrapper.this.c();
                        }
                    });
                }
                DeviceDownloadItemViewWrapper.this.b();
            }

            public void onDownloadFailure(PluginError pluginError) {
                super.onDownloadFailure(pluginError);
                if (!z) {
                    DeviceDownloadItemViewWrapper.f20832a.remove(device);
                }
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.d();
                }
                DeviceDownloadItemViewWrapper.this.c.setFail();
            }

            public void onDownloadCancel() {
                super.onDownloadCancel();
                if (!z) {
                    DeviceDownloadItemViewWrapper.f20832a.remove(device);
                }
                if (DeviceDownloadItemViewWrapper.this.e != null) {
                    DeviceDownloadItemViewWrapper.this.e.e();
                }
                DeviceDownloadItemViewWrapper.this.c.setCancel();
            }
        });
    }

    public void setStart() {
        if (this.c != null) {
            this.c.setStart();
        }
    }

    public void setSuccess() {
        if (this.c != null) {
            this.c.setSuccess();
        }
    }

    public void setFail() {
        if (this.c != null) {
            this.c.setFail();
        }
    }

    public void setCancel() {
        if (this.c != null) {
            this.c.setCancel();
        }
    }

    public void setVisible(boolean z) {
        if (this.c != null) {
            this.c.setVisible(z);
        }
    }

    public void setPercent(float f2) {
        if (this.c != null) {
            this.c.setPercent(f2);
        }
    }

    public float getPercent() {
        if (this.c != null) {
            return this.c.getPercent();
        }
        return 0.0f;
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.i != null) {
            this.i.cancel();
        }
        c();
        this.h = new Timer();
        this.i = new TimerTask() {
            public void run() {
                DeviceDownloadItemViewWrapper.i(DeviceDownloadItemViewWrapper.this);
                if (DeviceDownloadItemViewWrapper.this.j <= 99) {
                    DeviceDownloadItemViewWrapper.this.k.sendEmptyMessage(1);
                } else {
                    DeviceDownloadItemViewWrapper.this.c();
                }
            }
        };
        this.h.schedule(this.i, 200, 200);
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.h != null) {
            this.h.cancel();
        }
        this.j = 91;
    }
}
