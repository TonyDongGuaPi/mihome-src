package com.xiaomi.smarthome;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.ExtendPipelineControllerFactory;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.fresco.SystraceRequestListener;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.xiaomi.pluginbase.LayoutInflaterManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.youpin.log.LogUtils;
import java.lang.reflect.Field;
import java.util.HashSet;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;

public class FrescoInitial {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1497a = false;
    public static FrescoMemoryTrimmableRegistry b = new FrescoMemoryTrimmableRegistry();

    public static synchronized void a(boolean z) {
        synchronized (FrescoInitial.class) {
            if (!z) {
                if (f1497a) {
                    return;
                }
            }
            f1497a = true;
            ImagePipelineConfig a2 = a(SHApplication.getAppContext(), OkHttpClientProvider.getOkHttpClient(), (RequestListener) null, (DiskCacheConfig) null);
            try {
                LogUtil.c("FrescoInitial", "FrescoInitial->Fresco.initialize...");
                Fresco.initialize(SHApplication.getAppContext(), a2, DraweeConfig.newBuilder().setPipelineDraweeControllerFactory(new ExtendPipelineControllerFactory()).build());
            } catch (Throwable th) {
                th.printStackTrace();
                try {
                    LogUtilGrey.a("fresco", "FrescoInitial crash:" + th.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
            Class<FrescoModule> cls = FrescoModule.class;
            try {
                Field declaredField = cls.getDeclaredField("sHasBeenInitialized");
                declaredField.setAccessible(true);
                declaredField.setBoolean(cls, true);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("app_quit_broadcast");
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (intent != null && intent.getAction() != null && "app_quit_broadcast".equals(intent.getAction())) {
                        LogUtils.d("FrescoInitial", "Fresco clearMemoryCaches");
                        if (Fresco.hasBeenInitialized()) {
                            Fresco.getImagePipeline().clearMemoryCaches();
                        }
                        LayoutInflaterManager.getInstance().clear();
                    }
                }
            }, intentFilter);
        }
    }

    private static ImagePipelineConfig a(Context context, OkHttpClient okHttpClient, @Nullable RequestListener requestListener, @Nullable DiskCacheConfig diskCacheConfig) {
        HashSet hashSet = new HashSet();
        hashSet.add(new SystraceRequestListener());
        if (requestListener != null) {
            hashSet.add(requestListener);
        }
        ImagePipelineConfig.Builder requestListeners = OkHttpImagePipelineConfigFactory.newBuilder(context.getApplicationContext(), okHttpClient).setDownsampleEnabled(true).setResizeAndRotateEnabledForNetwork(true).setBitmapsConfig(Bitmap.Config.RGB_565).setMemoryTrimmableRegistry(b).setRequestListeners(hashSet);
        if (diskCacheConfig != null) {
            requestListeners.setMainDiskCacheConfig(diskCacheConfig);
        }
        final int a2 = a(context);
        requestListeners.setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
            /* renamed from: a */
            public MemoryCacheParams get() {
                return new MemoryCacheParams(a2, 56, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            }
        });
        return requestListeners.build();
    }

    private static int a(Context context) {
        int min = Math.min(((ActivityManager) context.getSystemService("activity")).getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return 4194304;
        }
        if (min < 67108864) {
            return 6291456;
        }
        if (Build.VERSION.SDK_INT < 11) {
            return 8388608;
        }
        return min / 6;
    }

    public static synchronized void a() {
        synchronized (FrescoInitial.class) {
        }
    }
}
