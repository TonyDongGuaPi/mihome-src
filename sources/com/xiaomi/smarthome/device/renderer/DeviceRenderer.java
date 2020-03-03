package com.xiaomi.smarthome.device.renderer;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.bridge.WXBridgeManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.camera.activity.CameraPlayer2ExActivity;
import com.xiaomi.smarthome.camera.activity.CameraV3UpgradePlayerActivity;
import com.xiaomi.smarthome.camera.v4.activity.CameraPlayerActivity;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.device.ApDevice;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DevicePrefManager;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.MiioVirtualDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.ZhilianCameraDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.renderer.PluginDownloadingRecord;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceShortcutUtils;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.devicelistswitch.model.DeviceListSwitchManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SpanParser;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.ViewUtils;
import com.xiaomi.smarthome.lite.LiteDevice;
import com.xiaomi.smarthome.miio.MiioManager;
import com.xiaomi.smarthome.miio.device.PhoneDevice;
import com.xiaomi.smarthome.miio.page.BaseClientAllPage;
import com.xiaomi.smarthome.miui.MiuiAlertDialog;
import com.xiaomi.smarthome.newui.DeviceListPageActionInterface;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.ShortcutResponseTimeTracer;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.plugin.DeviceConstant;
import com.xiaomi.smarthome.setting.PluginSetting;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miui.app.AlertDialog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceRenderer implements LifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    public static final int f1515a = 0;
    public static final int b = 1;
    public static boolean c = false;
    public static Device d = null;
    protected static Map<Device, PluginDownloadingRecord> e = new HashMap();
    protected static Map<Device, PluginDownloadingRecord> f = new HashMap();
    protected static Map<Device, PluginInstallingRecord> g = new HashMap();
    /* access modifiers changed from: private */
    public static final String h = "DeviceRenderer";
    /* access modifiers changed from: private */
    public static boolean i = false;

    public interface LoadingCallback {
        void onLoadingFinish(boolean z);

        void onLoadingStart();
    }

    public static DeviceRenderer a(Device device) {
        if (device instanceof ApDevice) {
            return new ApDeviceRenderer();
        }
        if (device instanceof BleDevice) {
            return new BleDeviceRenderer();
        }
        if (device instanceof MiioVirtualDevice) {
            return new MiioVirtualDeviceRenderer();
        }
        if (device instanceof MiTVDevice) {
            return new MiTVDeviceRenderer();
        }
        if (device instanceof PhoneDevice) {
            return new PhoneDeviceRenderer();
        }
        if (device instanceof ZhilianCameraDevice) {
            return new ZhilianDeviceRenderer();
        }
        return new DeviceRenderer();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        c = false;
    }

    public static boolean a(Context context) {
        if (!(context instanceof Activity)) {
            return true;
        }
        Activity activity = (Activity) context;
        if (activity.isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed()) {
            return true;
        }
        return false;
    }

    public Intent a(Device device, Context context, Bundle bundle, boolean z, LoadingCallback loadingCallback) {
        Bundle bundle2;
        Intent intent;
        final Device device2 = device;
        Context context2 = context;
        Bundle bundle3 = bundle;
        LogUtilGrey.a(h, "click_device_list getAction in");
        if (!CoreApi.a().q()) {
            boolean z2 = context2 instanceof Activity;
            if (z2) {
                SHApplication.showLoginDialog((Activity) context2, false);
            } else if (z2) {
                SHApplication.showLoginDialog((Activity) context2, false);
            }
            return null;
        } else if (device2.model.equals("mijia.camera.v3")) {
            XmPluginHostApi.instance().closeCameraFloatingWindow(device2.did);
            Intent intent2 = new Intent(context2, CameraPlayer2ExActivity.class);
            intent2.putExtra("extra_device_did", device2.did);
            return intent2;
        } else if (device2.model.equals("chuangmi.camera.ipc009") || device2.model.equals("chuangmi.camera.ipc019") || device2.model.equals(DeviceConstant.CHUANGMI_CAMERA_021)) {
            XmPluginHostApi.instance().closeCameraFloatingWindow(device2.did);
            Intent intent3 = new Intent(context2, CameraPlayerActivity.class);
            intent3.putExtra("extra_device_did", device2.did);
            intent3.putExtra("is_v4", true);
            return intent3;
        } else if (device2.model.equals(DeviceConstant.MIJIA_CAMERA_V3_UPGRADE)) {
            XmPluginHostApi.instance().closeCameraFloatingWindow(device2.did);
            Intent intent4 = new Intent(context2, CameraV3UpgradePlayerActivity.class);
            intent4.putExtra("extra_device_did", device2.did);
            return intent4;
        } else {
            if (device.isBinded() || device2.canUseNotBind) {
                if (!CoreApi.a().c(device2.model)) {
                    bundle2 = bundle3;
                    LogUtilGrey.a("click_device_list", "DeviceRenderer.getAction not isPluginDevice " + device2.model);
                    LogUtilGrey.a(h, "click_device_list DeviceRender.getAction DeviceRenderer.getAction not isPluginDevice " + device2.model);
                    Bundle bundle4 = new Bundle();
                    Class<?> a2 = DeviceShortcutUtils.a(device2.did, device2.model, bundle4);
                    if (a2 != null) {
                        Intent intent5 = new Intent(context2, a2);
                        intent5.putExtras(bundle4);
                        intent = intent5;
                    } else {
                        intent = DeviceShortcutUtils.a(context2, device2, bundle4);
                    }
                } else if (UrlResolver.b.equals(device2.model)) {
                    UrlResolver.a(context2, device2);
                    return null;
                } else {
                    PluginRecord d2 = CoreApi.a().d(device2.model);
                    if (d2 == null) {
                        LogUtilGrey.a("click_device_list", "DeviceRenderer.getAction cannot find PluginRecord " + device2.model);
                        LogUtilGrey.a(h, "click_device_list DeviceRenderer.getAction cannot find PluginRecord " + device2.model);
                        return null;
                    } else if (d2 == null || d2.k() || d2.l() || NetworkUtils.a()) {
                        long b2 = FileUtils.b(context);
                        if (d2 != null && !d2.k() && !d2.l() && b2 < 31457280) {
                            Toast.makeText(context2, R.string.space_not_enable, 1).show();
                            LogUtilGrey.a("click_device_list", "The storage space of the mobile phone is less than 30M");
                            return null;
                        } else if (z) {
                            Intent intent6 = new Intent();
                            device2.setLaunchParams(intent6);
                            if (bundle3 != null) {
                                intent6.putExtras(bundle3);
                            }
                            final XQProgressHorizontalDialog b3 = XQProgressHorizontalDialog.b(context2, context2.getString(R.string.plugin_downloading) + d2.p());
                            new PluginDownloadTask();
                            final boolean z3 = !d2.l() && !d2.k();
                            PluginApi.SendMessageHandle sendMessageHandle = new PluginApi.SendMessageHandle();
                            final Context context3 = context;
                            final PluginApi.SendMessageHandle sendMessageHandle2 = sendMessageHandle;
                            final LoadingCallback loadingCallback2 = loadingCallback;
                            PluginApi.getInstance().sendMessage(context, d2, 1, intent6, device.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                                private static final float j = 85.0f;

                                /* renamed from: a  reason: collision with root package name */
                                ValueAnimator f15371a;
                                private long h;
                                private final Interpolator i = new DecelerateInterpolator();

                                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                    if (!(context3 instanceof CommonActivity) || ((CommonActivity) context3).isValid()) {
                                        b3.c();
                                        b3.setCancelable(true);
                                        b3.a(true);
                                        b3.setCanceledOnTouchOutside(false);
                                        b3.show();
                                        b3.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                            public void onCancel(DialogInterface dialogInterface) {
                                                sendMessageHandle2.cancel();
                                            }
                                        });
                                    }
                                }

                                public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                    b3.a(100, 0);
                                }

                                /* access modifiers changed from: package-private */
                                public float a() {
                                    if (this.f15371a == null) {
                                        synchronized (this) {
                                            double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.h)) / 4000.0f);
                                            Double.isNaN(min);
                                            this.f15371a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                                            this.f15371a.setDuration(4000);
                                            this.f15371a.setInterpolator(this.i);
                                            this.f15371a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                                    if (b3 != null) {
                                                        b3.a(100, (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f));
                                                    }
                                                }
                                            });
                                            this.f15371a.start();
                                        }
                                    }
                                    return ((Float) this.f15371a.getAnimatedValue()).floatValue();
                                }

                                public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                                    if (z3) {
                                        int i2 = (int) (f2 * 100.0f);
                                        if (i2 >= 99) {
                                            if (this.h == 0) {
                                                this.h = System.currentTimeMillis();
                                            }
                                            i2 = 99;
                                        }
                                        if (i2 == 99) {
                                            a();
                                        } else if (b3 != null) {
                                            XQProgressHorizontalDialog xQProgressHorizontalDialog = b3;
                                            double d2 = (double) i2;
                                            Double.isNaN(d2);
                                            xQProgressHorizontalDialog.a(100, (int) (d2 * 0.85d));
                                        }
                                    } else if (b3 != null) {
                                        b3.a(100, (int) (f2 * 100.0f));
                                    }
                                }

                                public void onDownloadSuccess(PluginRecord pluginRecord) {
                                    if (!z3 && b3 != null) {
                                        b3.dismiss();
                                    }
                                }

                                public void onInstallSuccess(PluginRecord pluginRecord) {
                                    PluginPackageInfo h2 = pluginRecord.h();
                                    if (b3 != null && h2 != null && "rn".equalsIgnoreCase(h2.i())) {
                                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                            public void run() {
                                                XQProgressHorizontalDialog xQProgressHorizontalDialog = b3;
                                                if (xQProgressHorizontalDialog != null && xQProgressHorizontalDialog.isShowing()) {
                                                    xQProgressHorizontalDialog.dismiss();
                                                }
                                            }
                                        }, 3000);
                                    }
                                }

                                public void onDownloadFailure(PluginError pluginError) {
                                    if (!z3 && b3 != null) {
                                        b3.dismiss();
                                    }
                                    if (pluginError != null) {
                                        LogUtil.b(DeviceRenderer.h, "" + pluginError.b());
                                    }
                                }

                                public void onDownloadCancel() {
                                    if (!z3 && b3 != null) {
                                        b3.dismiss();
                                    }
                                }

                                public void onSendSuccess(Bundle bundle) {
                                    if (z3) {
                                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                            public void run() {
                                                if (b3 != null) {
                                                    b3.dismiss();
                                                }
                                            }
                                        }, 500);
                                    }
                                    if (loadingCallback2 != null) {
                                        loadingCallback2.onLoadingFinish(true);
                                    }
                                }

                                public void onSendFailure(Error error) {
                                    if (z3 && b3 != null) {
                                        b3.dismiss();
                                    }
                                    if (loadingCallback2 != null) {
                                        loadingCallback2.onLoadingFinish(false);
                                    }
                                    if (error != null) {
                                        LogUtil.b(DeviceRenderer.h, error.toString());
                                    }
                                    if (error.a() == -666) {
                                        ToastUtil.a((int) R.string.dialog_title_force_update, 1);
                                        return;
                                    }
                                    ToastUtil.a((int) R.string.device_enter_failed, 0);
                                    String b2 = DeviceRenderer.h;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("onSendFailure line 421:");
                                    sb.append(error == null ? null : error.toString());
                                    LogUtilGrey.a(b2, sb.toString());
                                }

                                public void onSendCancel() {
                                    if (z3 && b3 != null) {
                                        b3.dismiss();
                                    }
                                    if (loadingCallback2 != null) {
                                        loadingCallback2.onLoadingFinish(false);
                                    }
                                }
                            }, sendMessageHandle);
                            if (d2.k() && loadingCallback != null) {
                                loadingCallback.onLoadingStart();
                            }
                        } else if (f.containsKey(device2)) {
                            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                public void run() {
                                    if (DeviceRenderer.f.containsKey(device2)) {
                                        DeviceRenderer.f.remove(device2);
                                    }
                                }
                            }, 60000);
                            LogUtilGrey.a("click_device_list", "DeviceRender.getAction mPluginDownloadingRecordMap containsKey");
                            LogUtilGrey.a(h, "click_device_list DeviceRender.getAction mPluginDownloadingRecordMap containsKey");
                            return null;
                        } else {
                            if (d2.k() || f.size() < 1) {
                                for (PluginDownloadingRecord pluginDownloadingRecord : f.values()) {
                                    pluginDownloadingRecord.d.cancel();
                                }
                                Intent intent7 = new Intent();
                                device2.setLaunchParams(intent7);
                                if (bundle3 != null) {
                                    intent7.putExtras(bundle3);
                                }
                                final Device device3 = device;
                                final Context context4 = context;
                                final boolean z4 = !d2.l() && !d2.k();
                                Bundle bundle5 = bundle3;
                                final LoadingCallback loadingCallback3 = loadingCallback;
                                AnonymousClass3 r0 = new AnonymousClass1SendMessageCallbackWrapper() {
                                    private float h = 0.0f;

                                    public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                        if (DeviceRenderer.f.get(device3) == null) {
                                            PluginDownloadingRecord pluginDownloadingRecord = new PluginDownloadingRecord();
                                            pluginDownloadingRecord.f15409a = device3.model;
                                            pluginDownloadingRecord.b = PluginDownloadingRecord.Status.DOWNLOADING;
                                            pluginDownloadingRecord.d = this.f15385a;
                                            DeviceRenderer.f.put(device3, pluginDownloadingRecord);
                                        }
                                        LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        this.h = 0.0f;
                                        StatHelper.o();
                                    }

                                    public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                                        if (((double) (f2 - this.h)) >= 0.01d || f2 >= 1.0f) {
                                            PluginDownloadingRecord pluginDownloadingRecord = DeviceRenderer.f.get(device3);
                                            if (pluginDownloadingRecord != null) {
                                                if (z4 && ((double) f2) >= 0.99d) {
                                                    f2 = 0.99f;
                                                }
                                                pluginDownloadingRecord.c = f2;
                                            }
                                            if (f2 == 0.99f && pluginDownloadingRecord.e == 0) {
                                                pluginDownloadingRecord.e = System.currentTimeMillis();
                                            }
                                            SmartHomeDeviceManager.a().a(device3);
                                            LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                            this.h = f2;
                                        }
                                    }

                                    public void onDownloadSuccess(PluginRecord pluginRecord) {
                                        if (!z4) {
                                            DeviceRenderer.f.remove(device3);
                                            LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        }
                                    }

                                    public void onDownloadFailure(PluginError pluginError) {
                                        if (!z4) {
                                            DeviceRenderer.f.remove(device3);
                                            LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        }
                                    }

                                    public void onDownloadCancel() {
                                        if (!z4) {
                                            DeviceRenderer.f.remove(device3);
                                            LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        }
                                    }

                                    public void onSendSuccess(Bundle bundle) {
                                        if (z4) {
                                            DeviceRenderer.f.remove(device3);
                                        }
                                        DeviceRenderer.e.remove(device3);
                                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                            public void run() {
                                                LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                            }
                                        }, 700);
                                        if (loadingCallback3 != null) {
                                            loadingCallback3.onLoadingFinish(true);
                                        }
                                    }

                                    public void onSendFailure(Error error) {
                                        if (z4) {
                                            DeviceRenderer.f.remove(device3);
                                        }
                                        DeviceRenderer.e.remove(device3);
                                        LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        if (loadingCallback3 != null) {
                                            loadingCallback3.onLoadingFinish(false);
                                        }
                                        Toast.makeText(SHApplication.getAppContext(), R.string.device_enter_failed, 0).show();
                                        String b = DeviceRenderer.h;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("onSendFailure line 599:");
                                        sb.append(error == null ? null : error.toString());
                                        LogUtilGrey.a(b, sb.toString());
                                    }

                                    public void onSendCancel() {
                                        if (z4) {
                                            DeviceRenderer.f.remove(device3);
                                        }
                                        DeviceRenderer.e.remove(device3);
                                        LocalBroadcastManager.getInstance(context4).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                                        if (loadingCallback3 != null) {
                                            loadingCallback3.onLoadingFinish(false);
                                        }
                                    }
                                };
                                e.put(device2, (Object) null);
                                bundle2 = bundle5;
                                intent = null;
                                AnonymousClass3 r1 = r0;
                                r1.f15385a = PluginApi.getInstance().sendMessage(context, d2, 1, intent7, device.newDeviceStat(), (RunningProcess) null, false, r1);
                                if (d2.k() && loadingCallback != null) {
                                    loadingCallback.onLoadingStart();
                                }
                            } else {
                                Toast.makeText(context2, R.string.device_downloading, 0).show();
                                return null;
                            }
                        }
                    } else {
                        Toast.makeText(context2, String.format(context2.getString(R.string.plugin_before_network_not_avail), new Object[]{d2.p()}), 1).show();
                        LogUtilGrey.a("click_device_list", "DeviceRenderer.getAction null 1111");
                        return null;
                    }
                }
                if (!(intent == null || bundle2 == null)) {
                    intent.putExtras(bundle2);
                }
                LogUtilGrey.a(h, "click_device_list DeviceRender.getAction end:" + device2.model + ", 0 - " + System.currentTimeMillis());
                StringBuilder sb = new StringBuilder();
                sb.append(device2.model);
                sb.append(", 0 - ");
                sb.append(System.currentTimeMillis());
                Log.e("Device_Renderer", sb.toString());
                return intent;
            }
            Toast.makeText(SHApplication.getAppContext(), R.string.miio_not_bind_device, 0).show();
            intent = null;
            bundle2 = bundle3;
            intent.putExtras(bundle2);
            LogUtilGrey.a(h, "click_device_list DeviceRender.getAction end:" + device2.model + ", 0 - " + System.currentTimeMillis());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(device2.model);
            sb2.append(", 0 - ");
            sb2.append(System.currentTimeMillis());
            Log.e("Device_Renderer", sb2.toString());
            return intent;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Context context, MLAlertDialog mLAlertDialog, String str) {
        mLAlertDialog.setView(ViewUtils.a(context, mLAlertDialog, str), DisplayUtils.a(20.0f), 0, DisplayUtils.a(20.0f), DisplayUtils.a(20.0f));
    }

    public void a(final Context context, final ViewHolder viewHolder, final Device device) {
        if (CoreApi.a().q() && !device.isBinded()) {
            a(device, viewHolder);
            viewHolder.addBtn.requestLayout();
            viewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    viewHolder.progress_bar.setVisibility(0);
                    viewHolder.addBtn.setVisibility(4);
                    device.bindDevice(context, new Device.IBindDeviceCallback() {
                        public void a() {
                            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                            viewHolder.progress_bar.setVisibility(4);
                            viewHolder.addBtn.setVisibility(0);
                        }

                        public void b() {
                            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(DeviceMainPage.f));
                            viewHolder.progress_bar.setVisibility(4);
                        }

                        public void c() {
                            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                            viewHolder.addBtn.setVisibility(0);
                            viewHolder.progress_bar.setVisibility(4);
                        }

                        public void a(int i) {
                            if (viewHolder.progress_bar.getVisibility() == 0) {
                                int i2 = R.string.button_bind_failed;
                                if (i == -1) {
                                    i2 = R.string.button_bind_failed_has_binded;
                                }
                                Toast.makeText(context, i2, 0).show();
                            }
                            viewHolder.addBtn.setVisibility(0);
                            viewHolder.progress_bar.setVisibility(4);
                        }

                        public void d() {
                            if (viewHolder.progress_bar.getVisibility() == 0) {
                                Toast.makeText(context, R.string.button_bind_failed_info, 0).show();
                            }
                            viewHolder.addBtn.setVisibility(0);
                            viewHolder.progress_bar.setVisibility(4);
                        }
                    });
                }
            });
        } else if (!device.isConnected) {
            viewHolder.addBtn.setVisibility(0);
            viewHolder.addBtn.setText(R.string.begin_connection);
            viewHolder.addBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!device.isConnected) {
                        ((SmartHomeMainActivity) context).startWifiSetting(device.scanResult, device.model);
                    }
                }
            });
        } else {
            a(context, device, viewHolder);
        }
    }

    /* access modifiers changed from: protected */
    public void a(Device device, ViewHolder viewHolder) {
        viewHolder.addBtn.setVisibility(0);
        viewHolder.addBtn.setText(R.string.button_bind);
    }

    /* access modifiers changed from: protected */
    public void a(Context context, Device device, ViewHolder viewHolder) {
        viewHolder.addBtn.setVisibility(8);
        if (TextUtils.isEmpty(device.model) || !CoreApi.a().c(device.model)) {
            viewHolder.a(device.model);
        } else if (CoreApi.a().d(device.model) == null) {
            viewHolder.a(device.model);
        } else if (f.get(device) != null) {
            viewHolder.b(device.model);
            viewHolder.a(device.model, f.get(device));
        } else if (g.get(device) != null) {
            viewHolder.a(device.model, g.get(device));
        } else {
            viewHolder.a(device.model);
        }
    }

    public static void a(View view) {
        if (view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
            view.setTag(new ViewHolder(view));
        }
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.name.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.class_text_10));
        viewHolder.name.setText("");
        viewHolder.nameStatus.setText("");
        viewHolder.nameStatus.setVisibility(0);
        viewHolder.mStatusImage.setVisibility(8);
        viewHolder.addBtn.setVisibility(4);
        viewHolder.progress_bar.setVisibility(8);
        viewHolder.mSwitchBtnLayout.setVisibility(8);
        ((AnimationDrawable) viewHolder.mLLLoading.getDrawable()).stop();
        viewHolder.a((String) null);
        viewHolder.a();
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context, TextView textView, Device device, boolean z) {
        String subtitleByDesc = device.getSubtitleByDesc(context, z);
        if (TextUtils.isEmpty(subtitleByDesc)) {
            return false;
        }
        SpanParser.a(textView, subtitleByDesc, z, !device.isNoneClickableDevice());
        return true;
    }

    /* access modifiers changed from: protected */
    public String a(Context context, Device device, boolean z) {
        String subtitleByDesc = device.getSubtitleByDesc(context, z);
        if (!TextUtils.isEmpty(subtitleByDesc)) {
            return subtitleByDesc;
        }
        return null;
    }

    public void b(Context context, TextView textView, Device device, boolean z) {
        int i2;
        if (!c(context, textView, device, z) && !a(context, textView, device, z)) {
            textView.setText(new SubTitleHelper(device).a(context, z));
            if (z) {
                i2 = SpanParser.c;
            } else {
                boolean z2 = device.isOnline;
                i2 = SpanParser.f18733a;
            }
            textView.setTextColor(i2);
            textView.setVisibility(0);
        }
    }

    private boolean c(Context context, TextView textView, Device device, boolean z) {
        if (TextUtils.isEmpty(device.descNew)) {
            return false;
        }
        String str = device.descNew;
        String str2 = device.descTimeJString;
        if (!TextUtils.isEmpty(str2)) {
            try {
                JSONArray jSONArray = new JSONArray(str2);
                if (jSONArray.length() != 0) {
                    Object[] objArr = new Object[jSONArray.length()];
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        objArr[i2] = CalendarUtils.d(jSONArray.getLong(i2) * 1000);
                    }
                    str = String.format(str, objArr);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                return false;
            } catch (Exception e3) {
                e3.printStackTrace();
                return false;
            }
        }
        SpanParser.a(textView, str, z, !device.isNoneClickableDevice());
        return true;
    }

    public void a(Context context, final View view, final Device device, boolean z, boolean z2) {
        if (view.getTag() == null || !(view.getTag() instanceof ViewHolder)) {
            view.setTag(new ViewHolder(view));
        }
        final ViewHolder viewHolder = (ViewHolder) view.getTag();
        view.clearAnimation();
        if (!z || IRDeviceUtil.a(device.did)) {
            viewHolder.ckbEdit.setVisibility(8);
        } else {
            viewHolder.ckbEdit.setVisibility(0);
            viewHolder.ckbEdit.setChecked(z2);
        }
        viewHolder.avatar.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.avatar.getResources()).setFadeDuration(200).setPlaceholderImage(viewHolder.avatar.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        viewHolder.avatar.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone_no));
        View view2 = null;
        if (context instanceof BaseActivity) {
            if (((BaseActivity) context).isValid()) {
                DeviceFactory.a(device, viewHolder.avatar, 0, (BasePostprocessor) null, false);
            } else {
                return;
            }
        }
        viewHolder.name.setText(device.getName());
        if (!device.isNoneClickableDevice()) {
            viewHolder.name.setTextColor(context.getResources().getColor(R.color.black_80_transparent));
        } else {
            viewHolder.name.setTextColor(context.getResources().getColor(R.color.black_80_transparent));
        }
        if (device.isNew) {
            viewHolder.name.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, context.getResources().getDrawable(R.drawable.std_equipment_list_new), (Drawable) null);
        } else {
            viewHolder.name.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
        }
        if (device.property == null || TextUtils.isEmpty(device.property.getString(DeviceListSwitchManager.f15515a, ""))) {
            b(context, viewHolder.nameStatus, device, false);
        } else {
            viewHolder.nameStatus.setText(device.property.getString(DeviceListSwitchManager.f15515a, ""));
        }
        viewHolder.nameStatus.setCompoundDrawablePadding(0);
        viewHolder.nameStatus.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (!device.isConnected) {
            Animation loadAnimation = AnimationUtils.loadAnimation(context, R.anim.alpha);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    view.startAnimation(animation);
                }
            });
            view.startAnimation(loadAnimation);
        }
        if (z) {
            viewHolder.addBtn.setVisibility(8);
        } else {
            a(context, viewHolder, device);
        }
        if (viewHolder.f15412a != null) {
            view2 = viewHolder.f15412a.findViewById(R.id.update_progress);
        }
        if (viewHolder.ckbEdit.getVisibility() == 0 || viewHolder.progress_bar.getVisibility() == 0 || (view2 != null && view2.getVisibility() == 0 && viewHolder.f15412a != null && viewHolder.f15412a.getVisibility() == 0)) {
            viewHolder.mSwitchBtnLayout.setVisibility(8);
            return;
        }
        int a2 = DeviceListSwitchManager.a().a(device);
        if (a2 == 3 && MiotSpecCardManager.f().e(device) != null) {
            a2 = 4;
        }
        if ((a2 == 1 || a2 == 4) && device.isOnline && !device.isSharedReadOnly()) {
            viewHolder.mSwitchBtnLayout.setVisibility(0);
            if (a2 == 1) {
                viewHolder.mSwitchBtn.setChecked(DeviceListSwitchManager.a().b(device));
            } else {
                viewHolder.mSwitchBtn.setChecked(DeviceListSwitchManager.a().a(device.did));
            }
            viewHolder.mSwitchBtn.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    viewHolder.mSwitchBtn.setOnTouchEnable(false);
                    ShortcutResponseTimeTracer.a(device, 0, Integer.MAX_VALUE);
                    DeviceListSwitchManager.a().a(device, !z, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            ShortcutResponseTimeTracer.a(device, 1, 0);
                            viewHolder.mSwitchBtn.postDelayed(new Runnable() {
                                public void run() {
                                    viewHolder.mSwitchBtn.setOnTouchEnable(true);
                                }
                            }, 1000);
                        }

                        public void onFailure(Error error) {
                            ShortcutResponseTimeTracer.a(device, 2, error.a());
                            viewHolder.mSwitchBtn.setChecked(DeviceListSwitchManager.a().b(device));
                            viewHolder.mSwitchBtn.postDelayed(new Runnable() {
                                public void run() {
                                    viewHolder.mSwitchBtn.setOnTouchEnable(true);
                                }
                            }, 1000);
                        }
                    });
                    STAT.d.a(device.did, device.model, z);
                }
            });
            return;
        }
        viewHolder.mSwitchBtnLayout.setVisibility(8);
    }

    public void a(View view, Handler handler, final Device device, Context context, DeviceListPageActionInterface deviceListPageActionInterface) {
        if (device != null) {
            JSONObject jSONObject = new JSONObject();
            if (device != null) {
                try {
                    jSONObject.put("model", device.model);
                    jSONObject.put("did", device.did);
                    jSONObject.put("isonline", device.isOnline);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            StatHelper.b(jSONObject.toString());
            STAT.d.a(device.model, device.did, device.pid, device.isOnline);
            if (a(context, device, handler)) {
                PluginSetting.a(PluginSetting.j);
                if (device.isNew) {
                    device.isNew = false;
                }
                if (!device.isNoneClickableDevice()) {
                    DevicePrefManager.b(device.did);
                }
                if (!(device instanceof BleDevice)) {
                    d = device;
                }
                Intent a2 = a(device, context, (Bundle) null, true, (LoadingCallback) new LoadingCallback() {
                    private boolean c;
                    private ColorStateList d;
                    private CharSequence e;

                    public void onLoadingStart() {
                        device.property.putBoolean(DeviceListSwitchManager.b, true);
                    }

                    public void onLoadingFinish(boolean z) {
                        device.property.putBoolean(DeviceListSwitchManager.b, false);
                    }
                });
                if (a2 != null && deviceListPageActionInterface != null) {
                    Activity activity = deviceListPageActionInterface.getActivity();
                    if (activity instanceof BaseActivity) {
                        ((BaseActivity) activity).addLifecycleObserver(this);
                    }
                    try {
                        context.startActivity(a2);
                    } catch (Exception e3) {
                        StatHelper.a(0, device);
                        e3.printStackTrace();
                    }
                    c = true;
                }
            }
        }
    }

    public static void a(Context context, Runnable runnable, TextView textView, Device device) {
        Object tag = textView.getTag();
        if (tag != null && (tag instanceof Device) && TextUtils.equals(((Device) tag).did, device.did)) {
            Object tag2 = textView.getTag(R.id.cb_item_tag);
            Boolean valueOf = Boolean.valueOf(device.property.getBoolean(DeviceListSwitchManager.b, false));
            if (valueOf != null && valueOf.booleanValue() && tag2 != null && (tag2 instanceof Integer)) {
                Integer num = (Integer) tag2;
                int intValue = num.intValue();
                if (runnable != null && (intValue = num.intValue() + 1) > 3) {
                    intValue = 0;
                }
                String str = "";
                for (int i2 = 0; i2 < intValue; i2++) {
                    str = str + ".";
                }
                textView.setTag(R.id.cb_item_tag, Integer.valueOf(intValue));
                textView.setText(context.getResources().getText(R.string.loading) + str);
                if (runnable != null) {
                    textView.postDelayed(runnable, 200);
                }
            }
        }
    }

    public void a(View view, Handler handler, LiteDevice liteDevice, Context context, BaseClientAllPage baseClientAllPage) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (liteDevice.f19353a != null) {
                jSONObject.put("model", liteDevice.f19353a.model);
                jSONObject.put("isOnline", liteDevice.f19353a.isOnline);
            }
            if (!(liteDevice.c == null || liteDevice.c.f19352a == null)) {
                jSONObject.put(WXBridgeManager.COMPONENT, liteDevice.c.f19352a);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        StatHelper.b(jSONObject.toString());
        Device device = liteDevice.f19353a;
        if (a(context, device, handler)) {
            if (device.isNew) {
                device.isNew = false;
            }
            if (!device.isNoneClickableDevice()) {
                DevicePrefManager.b(device.did);
            }
            if (!(device instanceof BleDevice)) {
                d = device;
            }
            Bundle bundle = new Bundle();
            if (device.model.startsWith("lumi.gateway")) {
                if (liteDevice.b.endsWith("fm")) {
                    bundle.putString("page", "fm");
                } else if (liteDevice.b.endsWith("light")) {
                    bundle.putString("page", "light");
                } else if (liteDevice.b.endsWith("alarm")) {
                    bundle.putString("page", "alarm");
                }
            } else if (device.model.equals("lumi.ctrl_neutral2.v1")) {
                if (liteDevice.b.endsWith("ctrl_neutral2_left")) {
                    bundle.putString("page", "ctrl_neutral2_left");
                } else if (liteDevice.b.endsWith("ctrl_neutral2_right")) {
                    bundle.putString("page", "ctrl_neutral2_right");
                }
            } else if (device.model.equals("chuangmi.plug.v1")) {
                if (liteDevice.b.endsWith("usb")) {
                    bundle.putString("page", "usb");
                } else if (liteDevice.b.endsWith(CameraPropertyBase.l)) {
                    bundle.putString("page", CameraPropertyBase.l);
                }
            }
            Intent a2 = a(device, context, bundle, true, (LoadingCallback) null);
            if (a2 != null && baseClientAllPage != null) {
                FragmentActivity activity = baseClientAllPage.getActivity();
                if (activity instanceof BaseActivity) {
                    ((BaseActivity) activity).addLifecycleObserver(this);
                }
                try {
                    context.startActivity(a2);
                } catch (Exception e3) {
                    StatHelper.a(0, device);
                    e3.printStackTrace();
                }
                c = true;
            }
        }
    }

    public void a(Handler handler, Device device, Context context) {
        if (a(context, device, handler)) {
            if (!device.isNoneClickableDevice()) {
                DevicePrefManager.b(device.did);
            }
            if (!(device instanceof BleDevice)) {
                d = device;
            }
            Intent a2 = a(device, context, (Bundle) null, true, (LoadingCallback) null);
            if (a2 != null) {
                try {
                    context.startActivity(a2);
                } catch (Exception e2) {
                    StatHelper.a(0, device);
                    e2.printStackTrace();
                }
                c = true;
            }
        }
    }

    public void a(final View view, Handler handler, Device device, Context context) {
        if (a(context, device, handler)) {
            JSONObject jSONObject = new JSONObject();
            if (device != null) {
                try {
                    jSONObject.put("model", device.model);
                    jSONObject.put("isOnline", device.isOnline);
                    jSONObject.put("did", device.did);
                    jSONObject.put("mac", device.mac);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            StatHelper.b(jSONObject.toString());
            if (device.isNew) {
                if (device instanceof BleDevice) {
                    ((BleDevice) device).b(false);
                } else {
                    device.isNew = false;
                }
            }
            DeviceFinder.a().g();
            if (!device.isNoneClickableDevice()) {
                DevicePrefManager.b(device.did);
            }
            if (!(device instanceof BleDevice)) {
                d = device;
            }
            Intent a2 = a(device, context, (Bundle) null, false, (LoadingCallback) new LoadingCallback() {
                public void onLoadingStart() {
                    view.post(new Runnable() {
                        public void run() {
                            View findViewById = view.findViewById(R.id.loading_container);
                            ImageView imageView = (ImageView) view.findViewById(R.id.loading);
                            if (findViewById != null) {
                                findViewById.setVisibility(0);
                                ((AnimationDrawable) imageView.getDrawable()).start();
                            }
                        }
                    });
                }

                public void onLoadingFinish(boolean z) {
                    view.post(new Runnable() {
                        public void run() {
                            View findViewById = view.findViewById(R.id.loading_container);
                            ImageView imageView = (ImageView) view.findViewById(R.id.loading);
                            if (findViewById != null) {
                                findViewById.setVisibility(4);
                                ((AnimationDrawable) imageView.getDrawable()).stop();
                            }
                        }
                    });
                }
            });
            if (a2 != null) {
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).addLifecycleObserver(this);
                }
                try {
                    context.startActivity(a2);
                } catch (Exception e3) {
                    e3.printStackTrace();
                    StatHelper.a(1, device);
                }
                c = true;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(Context context, Device device, Handler handler) {
        if (device == null) {
            return false;
        }
        if (i) {
            handler.postDelayed(new Runnable() {
                public void run() {
                    boolean unused = DeviceRenderer.i = false;
                }
            }, 800);
            return false;
        }
        i = true;
        handler.postDelayed(new Runnable() {
            public void run() {
                boolean unused = DeviceRenderer.i = false;
            }
        }, 800);
        if (c) {
            return false;
        }
        return true;
    }

    public boolean a(Context context, Device device, int i2) {
        if (i2 == R.string.smarthome_device_rename && device.canRename()) {
            a(context, device, (AsyncCallback) null);
            return true;
        } else if (i2 != R.string.smarthome_device_device || !device.canBeShared()) {
            return true;
        } else {
            b(context, device);
            return true;
        }
    }

    public static void a(final Context context, final Device device, final ClientRemarkInputView.RenameInterface renameInterface, final boolean... zArr) {
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(context).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        MLAlertDialog d2 = new MLAlertDialog.Builder(context).a((int) R.string.change_back_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                clientRemarkInputView.onConfirm(dialogInterface);
                if (zArr != null && zArr.length == 1 && zArr[0]) {
                    STAT.d.ac(device.model);
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
                if (zArr != null && zArr.length == 1 && zArr[0]) {
                    STAT.d.ab(device.model);
                }
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (zArr != null && zArr.length == 1 && zArr[0]) {
                    STAT.d.ab(device.model);
                }
            }
        }).d();
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
            public void modifyBackName(String str) {
                if (renameInterface != null) {
                    renameInterface.modifyBackName(str);
                }
            }
        }, d2, device, 40);
        clientRemarkInputView.setNeedVerifyConfirm(false);
        final EditText editText = clientRemarkInputView.getEditText();
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
        final Button button = d2.getButton(-1);
        button.setEnabled(false);
        button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
        clientRemarkInputView.getEditText().addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                clientRemarkInputView.setAlertText("");
                button.setEnabled(true);
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = editText.getText().toString();
                if (obj.length() <= 0) {
                    button.setEnabled(false);
                    button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
                } else if (StringUtil.u(obj)) {
                    clientRemarkInputView.setAlertText(context.getString(R.string.tag_save_data_description));
                    button.setEnabled(false);
                    button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
                } else if (!HomeManager.A(obj)) {
                    clientRemarkInputView.setAlertText(context.getString(R.string.room_name_too_long));
                    button.setEnabled(false);
                    button.setTextColor(context.getResources().getColor(R.color.std_list_subtitle));
                } else {
                    clientRemarkInputView.setAlertText("");
                    button.setEnabled(true);
                    button.setTextColor(context.getResources().getColor(R.color.std_dialog_button_green));
                }
            }
        });
    }

    public static void a(Context context, Device device, final ClientRemarkInputView.RenameInterface renameInterface) {
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(context).inflate(R.layout.common_device_rename_dialog_layout, (ViewGroup) null);
        MiuiAlertDialog miuiAlertDialog = new MiuiAlertDialog(context, 2);
        miuiAlertDialog.setTitle(R.string.change_back_name);
        miuiAlertDialog.setView(clientRemarkInputView);
        miuiAlertDialog.setButton(-1, context.getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                clientRemarkInputView.onConfirm(dialogInterface);
            }
        });
        miuiAlertDialog.setButton(-2, context.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        miuiAlertDialog.show();
        try {
            final LinearLayout linearLayout = (LinearLayout) miuiAlertDialog.getButton(-1).getParent();
            final int paddingLeft = linearLayout.getPaddingLeft();
            final int paddingTop = linearLayout.getPaddingTop();
            final int paddingRight = linearLayout.getPaddingRight();
            final int paddingBottom = linearLayout.getPaddingBottom();
            clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
                public void modifyBackName(String str) {
                    if (renameInterface != null) {
                        renameInterface.modifyBackName(str);
                    }
                }
            }, (AlertDialog) miuiAlertDialog, device, 40);
            clientRemarkInputView.setNeedVerifyConfirm(false);
            EditText editText = clientRemarkInputView.getEditText();
            editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(41)});
            final Button button = miuiAlertDialog.getButton(-1);
            button.setEnabled(false);
            final EditText editText2 = editText;
            final Context context2 = context;
            clientRemarkInputView.getEditText().addTextChangedListener(new TextWatcher() {
                public void afterTextChanged(Editable editable) {
                }

                public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                    clientRemarkInputView.setAlertText("");
                    button.setEnabled(true);
                }

                public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                    String obj = editText2.getText().toString();
                    if (obj.length() <= 0) {
                        button.setEnabled(false);
                        linearLayout.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
                    } else if (StringUtil.u(obj)) {
                        clientRemarkInputView.setAlertText(context2.getString(R.string.tag_save_data_description_v2));
                        button.setEnabled(false);
                        linearLayout.setPadding(paddingLeft, 0, paddingRight, paddingBottom);
                    } else if (!HomeManager.A(obj)) {
                        clientRemarkInputView.setAlertText(context2.getString(R.string.room_name_too_long));
                        button.setEnabled(false);
                        linearLayout.setPadding(paddingLeft, 0, paddingRight, paddingBottom);
                    } else {
                        clientRemarkInputView.setAlertText("");
                        button.setEnabled(true);
                        linearLayout.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
                    }
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void a(final Context context, final Device device, final AsyncCallback asyncCallback) {
        a(context, device, (ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
            public void modifyBackName(String str) {
                XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
                xQProgressDialog.setMessage(context.getString(R.string.changeing_back_name));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                if (device != null) {
                    DeviceRenderer.a(context, device, str, xQProgressDialog, asyncCallback, false);
                }
            }
        }, true);
    }

    public static void a(final Context context, final Device device) {
        a(context, device, (ClientRemarkInputView.RenameInterface) new ClientRemarkInputView.RenameInterface() {
            public void modifyBackName(String str) {
                XQProgressDialog xQProgressDialog = new XQProgressDialog(context);
                xQProgressDialog.setMessage(context.getString(R.string.changeing_back_name));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                if (device != null) {
                    DeviceRenderer.a(context, device, str, xQProgressDialog, (AsyncCallback) null, false);
                }
            }
        });
    }

    public static void a(Context context, Device device, String str, XQProgressDialog xQProgressDialog, AsyncCallback asyncCallback, boolean z) {
        if (device instanceof BleDevice) {
            if (!TextUtils.isEmpty(device.did)) {
                final XQProgressDialog xQProgressDialog2 = xQProgressDialog;
                final Device device2 = device;
                final String str2 = str;
                final Context context2 = context;
                final AsyncCallback asyncCallback2 = asyncCallback;
                final boolean z2 = z;
                MiioManager.a().a(device, str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        if (xQProgressDialog2 != null) {
                            xQProgressDialog2.dismiss();
                        }
                        Device b2 = SmartHomeDeviceManager.a().b(device2.did);
                        if (b2 != null) {
                            b2.name = str2;
                        }
                        device2.name = str2;
                        BleCacheUtils.a(device2.mac, str2);
                        LocalBroadcastManager.getInstance(context2).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                        SmartHomeDeviceManager.a().p();
                        LocalBroadcastManager.getInstance(context2).sendBroadcast(new Intent(CommonUseDeviceDataManager.b));
                        if (asyncCallback2 != null) {
                            asyncCallback2.onSuccess(voidR);
                        }
                    }

                    public void onFailure(Error error) {
                        if (xQProgressDialog2 != null) {
                            xQProgressDialog2.dismiss();
                        }
                        if (!z2) {
                            ToastUtil.a((int) R.string.change_back_name_fail);
                        }
                        if (asyncCallback2 != null) {
                            asyncCallback2.onFailure(error);
                        }
                    }
                });
                return;
            }
            if (xQProgressDialog != null) {
                xQProgressDialog.dismiss();
            }
            device.name = str;
            BleCacheUtils.a(device.mac, str);
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
            SmartHomeDeviceManager.a().p();
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(CommonUseDeviceDataManager.b));
            if (asyncCallback != null) {
                asyncCallback.onSuccess(null);
            }
        } else if (!(device instanceof MiTVDevice) || ((MiTVDevice) device).e()) {
            final XQProgressDialog xQProgressDialog3 = xQProgressDialog;
            final Device device3 = device;
            final String str3 = str;
            final Context context3 = context;
            final AsyncCallback asyncCallback3 = asyncCallback;
            final boolean z3 = z;
            MiioManager.a().a(device, str, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (xQProgressDialog3 != null) {
                        xQProgressDialog3.dismiss();
                    }
                    device3.name = str3;
                    LocalBroadcastManager.getInstance(context3).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                    SmartHomeDeviceManager.a().p();
                    LocalBroadcastManager.getInstance(context3).sendBroadcast(new Intent(CommonUseDeviceDataManager.b));
                    if (asyncCallback3 != null) {
                        asyncCallback3.onSuccess(voidR);
                    }
                    if (device3 instanceof MiTVDevice) {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(str3);
                        XmPluginHostApi.instance().callMethodFromCloud(device3.did, "modify_device_name", jSONArray, (Callback) null, (Parser) null);
                    }
                }

                public void onFailure(Error error) {
                    if (xQProgressDialog3 != null) {
                        xQProgressDialog3.dismiss();
                    }
                    if (!z3) {
                        ToastUtil.a((int) R.string.change_back_name_fail);
                    }
                    if (asyncCallback3 != null) {
                        asyncCallback3.onFailure(error);
                    }
                }
            });
        } else {
            if (xQProgressDialog != null) {
                xQProgressDialog.dismiss();
            }
            ToastUtil.a((int) R.string.change_back_name_mitv_notsupport);
            if (asyncCallback != null) {
                asyncCallback.onFailure(null);
            }
        }
    }

    public static void b(Context context, Device device) {
        if (CoreApi.a().q()) {
            Intent intent = new Intent();
            intent.setClass(context, DeviceShortcutUtils.a());
            Bundle bundle = new Bundle();
            bundle.putString("user_id", CoreApi.a().s());
            bundle.putString("did", device.did);
            intent.putExtras(bundle);
            context.startActivity(intent);
            return;
        }
        Toast.makeText(context, R.string.loggin_first, 0).show();
    }

    public void a() {
        if (d != null) {
            String str = d.did;
            if (!TextUtils.isEmpty(str)) {
                d = null;
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                SmartHomeDeviceManager.a().a((List<String>) arrayList, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(List<Device> list) {
                        if (list != null && list.size() > 0) {
                            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
                            ArrayList arrayList = new ArrayList();
                            for (Device device : list) {
                                arrayList.add(device.did);
                            }
                            SmartHomeDeviceManager.a().a((ArrayList<String>) arrayList);
                        }
                    }
                });
            }
        }
    }

    public static boolean b(Device device) {
        try {
            return f.containsKey(device);
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
