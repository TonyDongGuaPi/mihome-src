package com.xiaomi.smarthome.device.utils;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.miio.LauncherUtil;
import com.xiaomi.smarthome.miio.activity.MiioActivity;
import com.xiaomi.smarthome.miio.activity.WifiLogActivity;
import com.xiaomi.smarthome.miio.activity.YeeLinkBulbActivityV2;
import com.xiaomi.smarthome.miio.miband.MiBandMainActivity;
import com.xiaomi.smarthome.miio.page.MiioPageV2;
import javax.annotation.Nullable;

public class DeviceShortcutUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15429a = "extra_device_did";
    public static final String b = "extra_device_model";
    public static final int c = -1;

    public static Class<?> a() {
        return ShareDeviceActivity.class;
    }

    public static Class<?> a(String str, String str2, Bundle bundle) {
        if (DeviceFactory.e(str2, "yeelink.light.rgb1")) {
            bundle.putString("yeelink_mac", str);
            return YeeLinkBulbActivityV2.class;
        } else if (DeviceFactory.e(str2, "yeelight.rgb.v1")) {
            bundle.putString("yeelink_mac", str);
            return YeeLinkBulbActivityV2.class;
        } else if (DeviceFactory.e(str2, "xiaomi.myphone.v1")) {
            return WifiLogActivity.class;
        } else {
            if (DeviceFactory.e(str2, "xiaomi.ble.v1")) {
                bundle.putString("mac", str);
                return MiBandMainActivity.class;
            } else if (!DeviceFactory.e(str2, "xiaomi.curtain.v1")) {
                return null;
            } else {
                bundle.putString(MiioPageV2.e, str);
                return MiioActivity.class;
            }
        }
    }

    public static Intent a(Context context, Device device, Bundle bundle) {
        if (TextUtils.isEmpty(device.model) || !device.model.startsWith("xiaomi.router")) {
            return null;
        }
        return a(context, device.did, bundle);
    }

    private static Intent a(Context context, String str, Bundle bundle) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.xiaomi.router", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        if (packageInfo == null) {
            Toast.makeText(context, R.string.not_found_router_app, 0).show();
            return null;
        }
        Log.e("aaa", packageInfo.versionName);
        Intent intent = new Intent();
        if (packageInfo.versionName.startsWith("2")) {
            LauncherUtil.a(intent);
            intent.putExtra("type", 4);
            if (str.startsWith("miwifi.")) {
                str = str.substring(7);
            }
            intent.setAction("com.xiaomi.router.smarthome");
            intent.putExtra("routerId", str);
            intent.putExtra("userId", CoreApi.a().s());
        }
        return intent;
    }

    public static void a(Device device, Intent intent) {
        b(device, intent, (Bitmap) null, (String) null, false);
    }

    public static void a(boolean z, Device device, Intent intent, String str, AsyncResponseCallback<Void> asyncResponseCallback) {
        int i;
        if (Build.VERSION.SDK_INT >= 19) {
            if (Build.VERSION.SDK_INT >= 23) {
                i = CommonApplication.getApplication().checkSelfPermission("com.android.launcher.permission.INSTALL_SHORTCUT");
            } else {
                i = CommonApplication.getApplication().checkPermission("com.android.launcher.permission.INSTALL_SHORTCUT", Process.myPid(), Process.myUid());
            }
            if (i == -1) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(-1);
                    return;
                }
                return;
            }
        }
        PluginRecord d = CoreApi.a().d(device.model);
        if (d != null) {
            String t = d.t();
            if (TextUtils.isEmpty(t) || !t.startsWith("http")) {
                b(device, intent, (Bitmap) null, str, z);
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(null);
                }
                CoreApi.a().a(StatType.EVENT, "add_launcher", str, (String) null, false);
                return;
            }
            final Device device2 = device;
            final Intent intent2 = intent;
            final String str2 = str;
            final boolean z2 = z;
            final AsyncResponseCallback<Void> asyncResponseCallback2 = asyncResponseCallback;
            Fresco.getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(Uri.parse(t)).setProgressiveRenderingEnabled(true).build(), SHApplication.getAppContext()).subscribe(new BaseBitmapDataSubscriber() {
                public void onFailureImpl(DataSource dataSource) {
                }

                @SuppressLint({"ResourceType"})
                public void onNewResultImpl(@Nullable Bitmap bitmap) {
                    if (bitmap != null) {
                        TypedValue typedValue = new TypedValue();
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inTargetDensity = typedValue.density;
                        DisplayMetrics displayMetrics = SHApplication.getAppContext().getResources().getDisplayMetrics();
                        options.inTargetDensity = displayMetrics.densityDpi;
                        SHApplication.getAppContext().getResources().openRawResource(R.drawable.mijia_logo_icon_coner, typedValue);
                        Bitmap decodeResource = BitmapFactory.decodeResource(SHApplication.getAppContext().getResources(), R.drawable.mijia_logo_icon_coner, options);
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        int width2 = decodeResource.getWidth();
                        int height2 = decodeResource.getHeight();
                        int i = (int) (displayMetrics.density * 75.0f);
                        final Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
                        Canvas canvas = new Canvas(createBitmap);
                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setColor(-1);
                        float f = displayMetrics.density * 30.0f;
                        float f2 = (float) i;
                        float f3 = f / 6.0f;
                        canvas.drawRoundRect(new RectF(0.0f, 0.0f, f2, f2), f3, f3, paint);
                        Matrix matrix = new Matrix();
                        float max = (f2 - (0.1f * f2)) / ((float) Math.max(width, height));
                        matrix.setScale(max, max);
                        matrix.postTranslate((f2 - (((float) width) * max)) / 2.0f, (f2 - (((float) height) * max)) / 2.0f);
                        canvas.drawBitmap(bitmap, matrix, paint);
                        matrix.reset();
                        float max2 = f / ((float) Math.max(width2, height2));
                        matrix.setScale(max2, max2);
                        matrix.postTranslate(f2 - (((float) width2) * max2), f2 - (((float) height2) * max2));
                        canvas.drawBitmap(decodeResource, matrix, paint);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                try {
                                    DeviceShortcutUtils.b(device2, intent2, createBitmap, str2, z2);
                                    if (asyncResponseCallback2 != null) {
                                        asyncResponseCallback2.a(null);
                                    }
                                    CoreApi.a().a(StatType.EVENT, "add_launcher", str2, (String) null, false);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }
            }, CallerThreadExecutor.getInstance());
        }
    }

    /* access modifiers changed from: private */
    public static void b(Device device, Intent intent, Bitmap bitmap, String str, boolean z) {
        String str2;
        int i;
        Icon icon;
        Device device2 = device;
        Intent intent2 = intent;
        Bitmap bitmap2 = bitmap;
        if (device2.did.startsWith("yunyi.")) {
            MobclickAgent.a(SHApplication.getAppContext(), StatUtil.b, "create_shortcut");
        }
        Intent intent3 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        if (!"chuangmi.ir.v2".equals(device2.model) || intent2 == null) {
            str2 = device2.name;
        } else {
            str2 = intent2.getStringExtra("device_shortcut_name");
            if (str2 == null || str2.isEmpty()) {
                str2 = device2.name;
            }
        }
        intent3.putExtra("android.intent.extra.shortcut.NAME", str2);
        intent3.putExtra("duplicate", false);
        Intent intent4 = new Intent(ApiConst.f16684a);
        intent4.setComponent(new ComponentName(SHApplication.getAppContext().getPackageName(), DeviceLauncher2.class.getName()));
        intent4.putExtra("device_mac", device2.mac);
        intent4.putExtra("device_id", device2.did);
        intent4.putExtra(ApiConst.i, "short_cut");
        intent4.putExtra(ApiConst.f, device2.model);
        intent4.putExtra("timestamp", System.currentTimeMillis());
        intent4.putExtra(ApiConst.n, true);
        if (intent2 != null) {
            intent4.putExtras(intent2);
        }
        CoreApi.a().a(StatType.EVENT, "add_launcher", str, (String) null, false);
        intent3.putExtra("android.intent.extra.shortcut.INTENT", intent4);
        int o = DeviceFactory.o(device2.model);
        if (o <= 0 && (o = ClientIconMap.a(device2.model)) <= 0) {
            o = CoreApi.a().c(device2.model) ? R.drawable.plugin_shortcut : R.drawable.device_list_phone_no;
        }
        if (!"chuangmi.ir.v2".equals(device2.model) || intent2 == null || (i = ClientIconMap.b(intent2.getStringExtra("device_shortcut_icon_name"))) == 0) {
            i = o;
        }
        if (ApiHelper.c) {
            ShortcutManager shortcutManager = (ShortcutManager) SHApplication.getAppContext().getSystemService(ShortcutManager.class);
            if (shortcutManager.isRequestPinShortcutSupported()) {
                try {
                    icon = Icon.createWithBitmap(bitmap);
                } catch (Exception unused) {
                    icon = Icon.createWithResource(SHApplication.getAppContext(), R.drawable.ic_launcher);
                }
                shortcutManager.requestPinShortcut(new ShortcutInfo.Builder(SHApplication.getAppContext(), str2).setIcon(icon).setShortLabel(str2).setIntent(intent4).build(), (IntentSender) null);
            } else {
                intent3.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(SHApplication.getAppContext(), i));
                if (bitmap2 != null) {
                    intent3.putExtra("android.intent.extra.shortcut.ICON", bitmap2);
                }
                SHApplication.getAppContext().sendBroadcast(intent3);
            }
        } else {
            intent3.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(SHApplication.getAppContext(), i));
            if (bitmap2 != null) {
                intent3.putExtra("android.intent.extra.shortcut.ICON", bitmap2);
            }
            SHApplication.getAppContext().sendBroadcast(intent3);
        }
        if (!z) {
            Toast.makeText(SHApplication.getAppContext(), R.string.smarthome_scene_add_short_cut_success, 0).show();
        }
    }
}
