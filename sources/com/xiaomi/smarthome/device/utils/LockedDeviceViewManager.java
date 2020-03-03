package com.xiaomi.smarthome.device.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hannto.printservice.hanntoprintservice.entity.PrinterParmater;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.framework.page.PageUtil;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.library.common.widget.SwitchButtonLocked;
import com.xiaomi.smarthome.miio.LauncherUtil;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.miio.activity.ClientAllLockedActivity;
import com.xiaomi.smarthome.miio.lockscreen.SmartDeviceListManager;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LockedDeviceViewManager {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f15481a = "LockedDeviceViewManager";
    public static WeakReference<BaseAdapter> b = null;
    public static final int c = 0;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 3;
    public static final int g = 4;
    public static final int h = 5;
    static IPluginRequest i = null;
    private static final String j = "lock_screen_retrieving_data";

    public static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        Device f15511a = null;
        SimpleDraweeView b = null;
        TextView c = null;
        TextView d = null;
        SwitchButtonLocked e = null;
        Button f = null;
        View g = null;
    }

    private LockedDeviceViewManager() {
    }

    public static boolean a() {
        PackageInfo packageInfo;
        try {
            packageInfo = SHApplication.getAppContext().getPackageManager().getPackageInfo("com.xiaomi.router", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null || !packageInfo.versionName.startsWith("2")) {
            return false;
        }
        return true;
    }

    public static int a(Device device) {
        return ClientIconMap.a(device);
    }

    public static CharSequence b(Device device) {
        if (TextUtils.isEmpty(device.name)) {
            return device.did;
        }
        return device.name;
    }

    public static void a(View view, Device device) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.f15511a = device;
        if (!IRDeviceUtil.a(device.did)) {
            viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.image);
            viewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(viewHolder.b.getResources()).setFadeDuration(200).setPlaceholderImage(view.getResources().getDrawable(R.drawable.lock_ic_unknow)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_XY).build());
            viewHolder.c = (TextView) view.findViewById(R.id.name);
            viewHolder.c.setText("");
            viewHolder.d = (TextView) view.findViewById(R.id.name_status);
            viewHolder.d.setText("");
            if (c(device)) {
                viewHolder.f = (Button) view.findViewById(R.id.btn_lock_common);
            } else {
                viewHolder.f = null;
            }
            if (DeviceUtils.a(device)) {
                viewHolder.e = (SwitchButtonLocked) view.findViewById(R.id.switch_power);
            } else {
                viewHolder.e = null;
            }
            if (d(device)) {
                viewHolder.g = view.findViewById(R.id.lock_router_info);
            } else {
                viewHolder.g = null;
            }
        }
        view.setTag(viewHolder);
    }

    public static boolean c(Device device) {
        return device.model.equals("xiaomi.mikey.v1");
    }

    public static boolean d(Device device) {
        if (device != null && (device instanceof RouterDevice) && a()) {
            return true;
        }
        return false;
    }

    public static void a(Context context, Device device, int i2, boolean z) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.router", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            Toast.makeText(context, R.string.not_found_router_app, 0).show();
            return;
        }
        Intent intent = new Intent();
        if (packageInfo.versionName.startsWith("2")) {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.xiaomi.router");
            LauncherUtil.a(intent);
            intent.putExtra("type", i2);
            String str = device.did;
            if (str.startsWith("miwifi.")) {
                str = str.substring(7);
            }
            intent.setComponent(launchIntentForPackage.getComponent());
            intent.putExtra("routerId", str);
            intent.putExtra("userId", CoreApi.a().s());
            context.startActivity(intent);
        } else {
            intent.setClassName("com.xiaomi.router", "com.xiaomi.router.RouterLaunchReceiver");
            intent.setAction("com.xiaomi.router.launch");
            LauncherUtil.a(intent);
            intent.putExtra("type", i2);
            String str2 = device.did;
            if (str2.startsWith("miwifi.")) {
                str2 = str2.substring(7);
            }
            intent.putExtra("routerId", str2);
            intent.putExtra("userId", CoreApi.a().s());
            context.sendBroadcast(intent);
        }
        if (z) {
            ((Activity) context).overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        }
    }

    public static void a(final Device device, View view) {
        view.setVisibility(0);
        view.findViewById(R.id.download_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((RouterDevice) device).h == 0) {
                    LockedDeviceViewManager.a(view.getContext(), device, 5, false);
                } else {
                    LockedDeviceViewManager.a(view.getContext(), device, 2, false);
                }
                DisplayUtils.a(view.getContext(), 17432576, 17432577);
                OpenApi.a(view.getContext());
            }
        });
        view.findViewById(R.id.device_size_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LockedDeviceViewManager.a(view.getContext(), device, 4, false);
                DisplayUtils.a(view.getContext(), 17432576, 17432577);
                OpenApi.a(view.getContext());
            }
        });
        TextView textView = (TextView) view.findViewById(R.id.download_detail);
        TextView textView2 = (TextView) view.findViewById(R.id.device_size_detail);
        RouterDevice routerDevice = (RouterDevice) device;
        if (routerDevice.h > 0) {
            textView.setText(SHApplication.getAppContext().getString(R.string.smarthome_lock_router_download) + " " + routerDevice.h);
        } else {
            textView.setText(SHApplication.getAppContext().getString(R.string.smarthome_lock_router_download));
        }
        if (routerDevice.i > 0) {
            textView2.setText(SHApplication.getAppContext().getString(R.string.smarthome_lock_router_device) + " " + routerDevice.i);
            return;
        }
        textView2.setText(SHApplication.getAppContext().getString(R.string.smarthome_lock_router_device));
    }

    public static void a(String str, String str2, boolean z, boolean z2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("params", z ? "on" : "off");
            jSONObject.put("did", str);
            jSONObject.put("model", str2);
            jSONObject.put("return", z2 ? 0 : -1);
            CoreApi.a().a(StatType.EVENT, "lock_rpc", jSONObject.toString(), (String) null, false);
        } catch (JSONException unused) {
        }
    }

    public static void a(final MiioDeviceV2 miioDeviceV2, final MiioDeviceV2.DeviceCallback deviceCallback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", "get_bright");
            jSONObject.put("params", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
            if (deviceCallback != null) {
                deviceCallback.a(MiioDeviceV2.DeviceErrorCode.ERROR_PARAM_JSON_ERROR);
            }
        }
        Log.d(f15481a, "commonGetBright:" + jSONObject.toString());
        miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                try {
                    JSONArray optJSONArray = jSONObject.optJSONArray("result");
                    if (optJSONArray != null) {
                        if (optJSONArray.optInt(0, 0) > 0) {
                            miioDeviceV2.z = true;
                        } else {
                            miioDeviceV2.z = false;
                        }
                        miioDeviceV2.notifyStateChanged();
                    }
                } catch (Exception unused) {
                    if (deviceCallback != null) {
                        deviceCallback.a(MiioDeviceV2.DeviceErrorCode.ERROR_PARAM_JSON_ERROR);
                    }
                }
            }

            public void onFailure(Error error) {
                if (deviceCallback != null) {
                    deviceCallback.a(MiioDeviceV2.DeviceErrorCode.ERROR_UNKNOW);
                }
                Log.d(LockedDeviceViewManager.f15481a, "getBright failed,errcode=" + error.a() + ",errInfo=" + error.b());
            }
        });
    }

    public static boolean a(final MiioDeviceV2 miioDeviceV2, final MiioDeviceV2.DeviceCallback deviceCallback, final boolean z, final Handler handler) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", "set_mode");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(z ? "auto" : PrinterParmater.i);
            jSONObject.put("params", jSONArray);
        } catch (JSONException unused) {
        }
        miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Miio.g("toggle success");
                if (deviceCallback != null) {
                    deviceCallback.a(null);
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("mode", z ? "auto" : PrinterParmater.i);
                    if (XmPluginHostApi.instance().getApiLevel() > 4) {
                        XmPluginHostApi.instance().updateDeviceProperties(miioDeviceV2.did, jSONObject2);
                    }
                } catch (JSONException unused) {
                }
                miioDeviceV2.notifyStateChanged();
            }

            public void onFailure(Error error) {
                if (deviceCallback != null) {
                    deviceCallback.a((MiioDeviceV2.DeviceErrorCode) null);
                }
                Miio.g("toggle failed,errcode=" + error.a() + ",errInfo=" + error.b());
                if (handler != null) {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("mode", !z ? "auto" : PrinterParmater.i);
                                if (XmPluginHostApi.instance().getApiLevel() > 4) {
                                    XmPluginHostApi.instance().updateDeviceProperties(miioDeviceV2.did, jSONObject);
                                }
                            } catch (JSONException unused) {
                            }
                            miioDeviceV2.notifyStateChanged();
                        }
                    }, 500);
                }
            }
        });
        return true;
    }

    public static boolean b(final MiioDeviceV2 miioDeviceV2, final MiioDeviceV2.DeviceCallback deviceCallback, final boolean z, final Handler handler) {
        MiioDeviceV2 miioDeviceV22 = (MiioDeviceV2) SmartHomeDeviceManager.a().b(miioDeviceV2.p());
        if (miioDeviceV22 != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
                jSONObject.put("sid", miioDeviceV2.n());
                jSONObject.put("method", "toggle_plug");
                JSONArray jSONArray = new JSONArray();
                jSONArray.put("neutral_0");
                jSONArray.put(z ? "on" : "off");
                jSONObject.put("params", jSONArray);
            } catch (JSONException unused) {
            }
            miioDeviceV22.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    Miio.g("toggle success");
                    if (deviceCallback != null) {
                        deviceCallback.a(null);
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("on", z ? "true" : "false");
                        if (XmPluginHostApi.instance().getApiLevel() > 4) {
                            XmPluginHostApi.instance().updateDeviceProperties(miioDeviceV2.did, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                    miioDeviceV2.notifyStateChanged();
                }

                public void onFailure(Error error) {
                    if (deviceCallback != null) {
                        deviceCallback.a((MiioDeviceV2.DeviceErrorCode) null);
                    }
                    Miio.g("toggle failed,errcode=" + error.a() + ",errInfo=" + error.b());
                    if (handler != null) {
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("on", !z ? "true" : "false");
                                    if (XmPluginHostApi.instance().getApiLevel() > 4) {
                                        XmPluginHostApi.instance().updateDeviceProperties(miioDeviceV2.did, jSONObject);
                                    }
                                } catch (JSONException unused) {
                                }
                                miioDeviceV2.notifyStateChanged();
                            }
                        }, 500);
                    }
                }
            });
            return true;
        } else if (deviceCallback == null) {
            return false;
        } else {
            deviceCallback.a((MiioDeviceV2.DeviceErrorCode) null);
            return false;
        }
    }

    public static boolean c(final MiioDeviceV2 miioDeviceV2, final MiioDeviceV2.DeviceCallback deviceCallback, final boolean z, final Handler handler) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", z ? "set_on" : "set_off");
            jSONObject.put("params", new JSONArray());
        } catch (JSONException unused) {
        }
        miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Miio.g("toggle success");
                if (deviceCallback != null) {
                    deviceCallback.a(null);
                }
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put("on", z ? "true" : "false");
                    if (XmPluginHostApi.instance().getApiLevel() > 4) {
                        XmPluginHostApi.instance().updateDeviceProperties(miioDeviceV2.did, jSONObject2);
                    }
                } catch (JSONException unused) {
                }
                miioDeviceV2.notifyStateChanged();
            }

            public void onFailure(Error error) {
                if (deviceCallback != null) {
                    deviceCallback.a((MiioDeviceV2.DeviceErrorCode) null);
                }
                Miio.g("toggle failed,errcode=" + error.a() + ",errInfo=" + error.b());
                if (handler != null) {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.put("on", !z ? "true" : "false");
                                if (XmPluginHostApi.instance().getApiLevel() > 4) {
                                    XmPluginHostApi.instance().updateDeviceProperties(miioDeviceV2.did, jSONObject);
                                }
                            } catch (JSONException unused) {
                            }
                            miioDeviceV2.notifyStateChanged();
                        }
                    }, 500);
                }
            }
        });
        return true;
    }

    public static boolean d(final MiioDeviceV2 miioDeviceV2, final MiioDeviceV2.DeviceCallback deviceCallback, boolean z, final Handler handler) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", z ? "set_on" : "set_off");
            jSONObject.put("params", new JSONArray());
        } catch (JSONException unused) {
        }
        miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Miio.g("toggle success");
                if (deviceCallback != null) {
                    deviceCallback.a(null);
                }
                if (handler != null) {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            miioDeviceV2.notifyStateChanged();
                        }
                    }, 500);
                }
            }

            public void onFailure(Error error) {
                if (deviceCallback != null) {
                    deviceCallback.a((MiioDeviceV2.DeviceErrorCode) null);
                }
                Miio.g("toggle failed,errcode=" + error.a() + ",errInfo=" + error.b());
                if (handler != null) {
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            miioDeviceV2.notifyStateChanged();
                        }
                    }, 500);
                }
            }
        });
        return true;
    }

    public static void a(ClientAllLockedActivity clientAllLockedActivity, Device device, Button button) {
        if (device == null || !TextUtils.isEmpty(device.model)) {
        }
    }

    /* access modifiers changed from: private */
    public static void b(CompoundButton.OnCheckedChangeListener onCheckedChangeListener, SwitchButton switchButton, boolean z) {
        switchButton.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        switchButton.setChecked(z);
        switchButton.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    public static void a(MiioDeviceV2 miioDeviceV2, boolean z, MiioDeviceV2.DeviceCallback<Void> deviceCallback, Handler handler) {
        if (DeviceUtils.b((Device) miioDeviceV2)) {
            if (CoreApi.a().c(miioDeviceV2.model)) {
                a(miioDeviceV2, (MiioDeviceV2.DeviceCallback) deviceCallback, z, handler);
            } else if (z) {
                miioDeviceV2.a(deviceCallback);
            } else {
                miioDeviceV2.b(deviceCallback);
            }
        } else if (miioDeviceV2.model.equals("ge.light.mono1")) {
            d(miioDeviceV2, deviceCallback, z, handler);
        } else if (miioDeviceV2.model.equals("chuangmi.plug.v1")) {
            c(miioDeviceV2, deviceCallback, z, handler);
        } else if (miioDeviceV2.model.equals("lumi.plug.v1")) {
            b(miioDeviceV2, deviceCallback, z, handler);
        } else if (z) {
            miioDeviceV2.a(deviceCallback);
        } else {
            miioDeviceV2.b(deviceCallback);
        }
    }

    public static void a(final ClientAllLockedActivity clientAllLockedActivity, final Device device, final SwitchButtonLocked switchButtonLocked) {
        try {
            switchButtonLocked.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
            switchButtonLocked.setChecked(device.isOpen());
            switchButtonLocked.setVisibility(0);
            final MiioDeviceV2 miioDeviceV2 = (MiioDeviceV2) device;
            if (DeviceUtils.b(device)) {
                switchButtonLocked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                        if (CoreApi.a().c(device.model)) {
                            LockedDeviceViewManager.a(miioDeviceV2, z, (MiioDeviceV2.DeviceCallback<Void>) new MiioDeviceV2.DeviceCallback<Void>() {
                                public void a(Void voidR) {
                                    LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, true);
                                    LockedDeviceViewManager.b(switchButtonLocked, miioDeviceV2, clientAllLockedActivity);
                                }

                                public void a(MiioDeviceV2.DeviceErrorCode deviceErrorCode) {
                                    LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, false);
                                    ToastUtil.a(switchButtonLocked.getContext(), (int) R.string.toast_lock_switch_failed, 1);
                                    Handler handler = clientAllLockedActivity.getHandler();
                                    if (handler != null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                LockedDeviceViewManager.b(this, (SwitchButton) switchButtonLocked, !z);
                                            }
                                        });
                                    }
                                }
                            }, clientAllLockedActivity.getHandler());
                        } else {
                            LockedDeviceViewManager.a(miioDeviceV2, z, (MiioDeviceV2.DeviceCallback<Void>) new MiioDeviceV2.DeviceCallback<Void>() {
                                public void a(Void voidR) {
                                    LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, true);
                                    LockedDeviceViewManager.b(switchButtonLocked, miioDeviceV2, clientAllLockedActivity);
                                }

                                public void a(MiioDeviceV2.DeviceErrorCode deviceErrorCode) {
                                    LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, false);
                                    ToastUtil.a(switchButtonLocked.getContext(), (int) R.string.toast_lock_switch_failed, 1);
                                    Handler handler = clientAllLockedActivity.getHandler();
                                    if (handler != null) {
                                        handler.post(new Runnable() {
                                            public void run() {
                                                LockedDeviceViewManager.b(this, (SwitchButton) switchButtonLocked, !z);
                                            }
                                        });
                                    }
                                }
                            }, clientAllLockedActivity.getHandler());
                        }
                    }
                });
            } else if (device.model.equals("ge.light.mono1")) {
                switchButtonLocked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                        LockedDeviceViewManager.a(miioDeviceV2, z, (MiioDeviceV2.DeviceCallback<Void>) new MiioDeviceV2.DeviceCallback<Void>() {
                            public void a(Void voidR) {
                                LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, true);
                                LockedDeviceViewManager.b(switchButtonLocked, miioDeviceV2, clientAllLockedActivity);
                            }

                            public void a(MiioDeviceV2.DeviceErrorCode deviceErrorCode) {
                                LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, false);
                                ToastUtil.a(switchButtonLocked.getContext(), (int) R.string.toast_lock_switch_failed, 1);
                                Handler handler = clientAllLockedActivity.getHandler();
                                if (handler != null) {
                                    handler.post(new Runnable() {
                                        public void run() {
                                            LockedDeviceViewManager.b(this, (SwitchButton) switchButtonLocked, !z);
                                        }
                                    });
                                }
                            }
                        }, clientAllLockedActivity.getHandler());
                    }
                });
            } else if (device.model.equals("chuangmi.plug.v1")) {
                switchButtonLocked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                        LockedDeviceViewManager.a(miioDeviceV2, z, (MiioDeviceV2.DeviceCallback<Void>) new MiioDeviceV2.DeviceCallback<Void>() {
                            public void a(Void voidR) {
                                LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, true);
                                LockedDeviceViewManager.b(switchButtonLocked, miioDeviceV2, clientAllLockedActivity);
                            }

                            public void a(MiioDeviceV2.DeviceErrorCode deviceErrorCode) {
                                LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, false);
                                ToastUtil.a(switchButtonLocked.getContext(), (int) R.string.toast_lock_switch_failed, 1);
                                Handler handler = clientAllLockedActivity.getHandler();
                                if (handler != null) {
                                    handler.post(new Runnable() {
                                        public void run() {
                                            LockedDeviceViewManager.b(this, (SwitchButton) switchButtonLocked, !z);
                                        }
                                    });
                                }
                            }
                        }, clientAllLockedActivity.getHandler());
                    }
                });
            } else {
                switchButtonLocked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
                        LockedDeviceViewManager.a(miioDeviceV2, z, (MiioDeviceV2.DeviceCallback<Void>) new MiioDeviceV2.DeviceCallback<Void>() {
                            public void a(Void voidR) {
                                LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, true);
                                LockedDeviceViewManager.b(switchButtonLocked, miioDeviceV2, clientAllLockedActivity);
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put(CameraPropertyBase.l, z ? "on" : "off");
                                    if (XmPluginHostApi.instance().getApiLevel() > 4) {
                                        XmPluginHostApi.instance().updateDeviceProperties(device.did, jSONObject);
                                    }
                                } catch (JSONException unused) {
                                }
                            }

                            public void a(MiioDeviceV2.DeviceErrorCode deviceErrorCode) {
                                LockedDeviceViewManager.a(miioDeviceV2.did, miioDeviceV2.model, z, false);
                                ToastUtil.a(switchButtonLocked.getContext(), (int) R.string.toast_lock_switch_failed, 1);
                                Handler handler = clientAllLockedActivity.getHandler();
                                if (handler != null) {
                                    handler.post(new Runnable() {
                                        public void run() {
                                            LockedDeviceViewManager.b(this, (SwitchButton) switchButtonLocked, !z);
                                        }
                                    });
                                }
                            }
                        }, clientAllLockedActivity.getHandler());
                    }
                });
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public static void b(final SwitchButtonLocked switchButtonLocked, final MiioDeviceV2 miioDeviceV2, ClientAllLockedActivity clientAllLockedActivity) {
        LockedDeviceAdapter lockedDeviceAdapter = (LockedDeviceAdapter) b.get();
        if (lockedDeviceAdapter != null) {
            lockedDeviceAdapter.a(SmartHomeDeviceManager.a().b(miioDeviceV2.did));
        }
        Handler handler = clientAllLockedActivity.getHandler();
        if (handler != null) {
            if (miioDeviceV2.property != null) {
                miioDeviceV2.property.putBoolean(j, true);
            }
            handler.postDelayed(new Runnable() {
                public void run() {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(miioDeviceV2.did);
                    SmartHomeDeviceManager.a().a((List<String>) arrayList, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                        /* renamed from: a */
                        public void onSuccess(List<Device> list) {
                            if (miioDeviceV2.property != null) {
                                miioDeviceV2.property.putBoolean(LockedDeviceViewManager.j, false);
                            }
                            if (switchButtonLocked.getContext() == null) {
                                return;
                            }
                            if ((Build.VERSION.SDK_INT < 19 || switchButtonLocked.isAttachedToWindow()) && list != null && !list.isEmpty()) {
                                ArrayList arrayList = new ArrayList();
                                for (Device device : list) {
                                    arrayList.add(device.did);
                                }
                                SmartHomeDeviceManager.a().a((ArrayList<String>) arrayList);
                                LockedDeviceAdapter lockedDeviceAdapter = (LockedDeviceAdapter) LockedDeviceViewManager.b.get();
                                if (lockedDeviceAdapter != null) {
                                    lockedDeviceAdapter.onStateChanged(list.get(0));
                                }
                            }
                        }

                        public void onFailure(Error error) {
                            if (miioDeviceV2.property != null) {
                                miioDeviceV2.property.putBoolean(LockedDeviceViewManager.j, false);
                            }
                        }
                    });
                }
            }, 2500);
        }
    }

    @SuppressLint({"NewApi"})
    public static void a(final ClientAllLockedActivity clientAllLockedActivity, View view) {
        Object tag;
        if (view != null && (tag = view.getTag()) != null && (tag instanceof ViewHolder)) {
            ViewHolder viewHolder = (ViewHolder) tag;
            final Device device = viewHolder.f15511a;
            if (!IRDeviceUtil.a(device.did)) {
                SimpleDraweeView simpleDraweeView = viewHolder.b;
                TextView textView = viewHolder.c;
                TextView textView2 = viewHolder.d;
                SwitchButtonLocked switchButtonLocked = viewHolder.e;
                Button button = viewHolder.f;
                View view2 = viewHolder.g;
                if (CoreApi.a().c(device.model)) {
                    simpleDraweeView.setImageURI(Uri.parse(CoreApi.a().d(device.model).t()));
                } else {
                    int a2 = a(device);
                    if (a2 > 0) {
                        simpleDraweeView.setImageURI(CommonUtils.c(a2));
                    }
                }
                textView.setText(b(device));
                DeviceRenderer.a(device).b(clientAllLockedActivity, textView2, device, true);
                if (device.property != null && device.property.getBoolean(j, false)) {
                    textView2.setText(R.string.retrieving_data);
                }
                textView2.invalidate();
                if (button != null) {
                    a(clientAllLockedActivity, device, button);
                    if (!device.isOnline) {
                        button.setVisibility(8);
                    } else if (SmartDeviceListManager.b) {
                        button.setVisibility(0);
                    } else {
                        button.setVisibility(8);
                    }
                }
                if (switchButtonLocked != null) {
                    if (!device.isOnline) {
                        switchButtonLocked.setVisibility(8);
                    } else {
                        a(clientAllLockedActivity, device, switchButtonLocked);
                        switchButtonLocked.setEnabled(true);
                        if (SmartDeviceListManager.b) {
                            switchButtonLocked.setVisibility(0);
                        } else {
                            switchButtonLocked.setVisibility(8);
                        }
                    }
                }
                if (view2 != null) {
                    a(device, view2);
                    if (device.isOnline) {
                        view2.setEnabled(true);
                    } else {
                        view2.setEnabled(false);
                    }
                    if (SmartDeviceListManager.b) {
                        view2.setVisibility(0);
                    } else {
                        view2.setVisibility(8);
                    }
                }
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        STAT.f22748a.a(device.did);
                        if (device.did != null) {
                            ClientAllLockedActivity.clickOnPlugin = true;
                            Intent intent = new Intent(ApiConst.f16684a);
                            intent.setClass(clientAllLockedActivity, DeviceLauncher2.class);
                            intent.putExtra("device_mac", device.mac);
                            intent.putExtra("device_id", device.did);
                            intent.putExtra(ApiConst.n, true);
                            PageUtil.a(intent, (String) null, "ClientAllLockedActivity", (String) null);
                            clientAllLockedActivity.startActivity(intent);
                            DisplayUtils.a((Context) clientAllLockedActivity, 17432576, 17432577);
                            OpenApi.a((Context) clientAllLockedActivity);
                            StatHelper.a(device.model, device.did, device.mac);
                        }
                    }
                });
            }
        }
    }
}
