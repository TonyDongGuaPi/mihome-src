package com.xiaomi.smarthome.printer;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

public class SendMessageCallbackImpl extends PluginApi.SendMessageCallback {
    private static final float i = 85.0f;

    /* renamed from: a  reason: collision with root package name */
    ValueAnimator f21159a;
    /* access modifiers changed from: private */
    public final XQProgressHorizontalDialog b;
    private final boolean c;
    private final DeviceRenderer.LoadingCallback d;
    /* access modifiers changed from: private */
    public final PluginApi.SendMessageHandle e;
    private CommonActivity f;
    private long g;
    private final Interpolator h = new DecelerateInterpolator();

    public SendMessageCallbackImpl(CommonActivity commonActivity, PluginRecord pluginRecord, PluginApi.SendMessageHandle sendMessageHandle, DeviceRenderer.LoadingCallback loadingCallback) {
        this.f = commonActivity;
        this.d = loadingCallback;
        this.b = XQProgressHorizontalDialog.b(commonActivity, commonActivity.getString(R.string.plugin_downloading) + pluginRecord.p());
        this.c = !pluginRecord.l() && !pluginRecord.k();
        this.e = sendMessageHandle;
    }

    public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
        if (this.f.isValid()) {
            this.b.c();
            this.b.setCancelable(true);
            this.b.a(true);
            this.b.setCanceledOnTouchOutside(false);
            this.b.show();
            this.b.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    SendMessageCallbackImpl.this.e.cancel();
                }
            });
        }
    }

    public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
        this.b.a(100, 0);
    }

    /* access modifiers changed from: package-private */
    public float a() {
        if (this.f21159a == null) {
            synchronized (this) {
                double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.g)) / 4000.0f);
                Double.isNaN(min);
                this.f21159a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                this.f21159a.setDuration(4000);
                this.f21159a.setInterpolator(this.h);
                this.f21159a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {
                        if (SendMessageCallbackImpl.this.b != null) {
                            SendMessageCallbackImpl.this.b.a(100, (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f));
                        }
                    }
                });
                this.f21159a.start();
            }
        }
        return ((Float) this.f21159a.getAnimatedValue()).floatValue();
    }

    public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
        if (this.c) {
            int i2 = (int) (f2 * 100.0f);
            if (i2 >= 99) {
                if (this.g == 0) {
                    this.g = System.currentTimeMillis();
                }
                i2 = 99;
            }
            if (i2 == 99) {
                a();
            } else if (this.b != null) {
                XQProgressHorizontalDialog xQProgressHorizontalDialog = this.b;
                double d2 = (double) i2;
                Double.isNaN(d2);
                xQProgressHorizontalDialog.a(100, (int) (d2 * 0.85d));
            }
        } else if (this.b != null) {
            this.b.a(100, (int) (f2 * 100.0f));
        }
    }

    public void onDownloadSuccess(PluginRecord pluginRecord) {
        if (!this.c && this.b != null) {
            this.b.dismiss();
        }
    }

    public void onInstallSuccess(PluginRecord pluginRecord) {
        PluginPackageInfo h2 = pluginRecord.h();
        if (this.b != null && h2 != null && "rn".equalsIgnoreCase(h2.i())) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    XQProgressHorizontalDialog b = SendMessageCallbackImpl.this.b;
                    if (b != null && b.isShowing()) {
                        b.dismiss();
                    }
                }
            }, 3000);
        }
    }

    public void onDownloadFailure(PluginError pluginError) {
        if (!this.c && this.b != null) {
            this.b.dismiss();
        }
        if (pluginError != null) {
            LogUtil.b("DeviceRenderer", "" + pluginError.b());
        }
    }

    public void onDownloadCancel() {
        if (!this.c && this.b != null) {
            this.b.dismiss();
        }
    }

    public void onSendSuccess(Bundle bundle) {
        if (this.c) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    if (SendMessageCallbackImpl.this.b != null) {
                        SendMessageCallbackImpl.this.b.dismiss();
                    }
                }
            }, 500);
        }
        if (this.d != null) {
            this.d.onLoadingFinish(true);
        }
    }

    public void onSendFailure(Error error) {
        if (this.c && this.b != null) {
            this.b.dismiss();
        }
        if (this.d != null) {
            this.d.onLoadingFinish(false);
        }
        if (error != null) {
            LogUtil.b("DeviceRenderer", error.toString());
        }
        ToastUtil.a((int) R.string.device_enter_failed, 0);
    }

    public void onSendCancel() {
        if (this.c && this.b != null) {
            this.b.dismiss();
        }
        if (this.d != null) {
            this.d.onLoadingFinish(false);
        }
    }

    public static PluginApi.SendMessageHandle a(CommonActivity commonActivity, PluginRecord pluginRecord, Intent intent, DeviceStat deviceStat, DeviceRenderer.LoadingCallback loadingCallback) {
        return a(commonActivity, pluginRecord, 1, intent, deviceStat, loadingCallback);
    }

    public static PluginApi.SendMessageHandle a(CommonActivity commonActivity, PluginRecord pluginRecord, int i2, Intent intent, DeviceStat deviceStat, DeviceRenderer.LoadingCallback loadingCallback) {
        PluginApi.SendMessageHandle sendMessageHandle = new PluginApi.SendMessageHandle();
        return PluginApi.getInstance().sendMessage(commonActivity, pluginRecord, i2, intent, deviceStat, (RunningProcess) null, false, new SendMessageCallbackImpl(commonActivity, pluginRecord, sendMessageHandle, loadingCallback), sendMessageHandle);
    }
}
