package com.tencent.tinker.loader.app;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.SystemClock;
import com.tencent.tinker.loader.TinkerLoader;
import com.tencent.tinker.loader.TinkerRuntimeException;
import com.tencent.tinker.loader.TinkerUncaughtHandler;
import com.tencent.tinker.loader.shareutil.ShareIntentUtil;
import com.tencent.tinker.loader.shareutil.ShareTinkerInternals;
import java.lang.reflect.Constructor;

public abstract class TinkerApplication extends Application {
    private static final String INTENT_PATCH_EXCEPTION = "intent_patch_exception";
    private static final String TINKER_LOADER_METHOD = "tryLoad";
    private final String delegateClassName;
    private final String loaderClassName;
    private ITinkerInlineFenceBridge mBridge;
    private final int tinkerFlags;
    private final boolean tinkerLoadVerifyFlag;
    private Intent tinkerResultIntent;
    private boolean useSafeMode;

    protected TinkerApplication(int i) {
        this(i, "com.tencent.tinker.entry.DefaultApplicationLike", TinkerLoader.class.getName(), false);
    }

    protected TinkerApplication(int i, String str, String str2, boolean z) {
        this.mBridge = null;
        this.tinkerFlags = i;
        this.delegateClassName = str;
        this.loaderClassName = str2;
        this.tinkerLoadVerifyFlag = z;
    }

    protected TinkerApplication(int i, String str) {
        this(i, str, TinkerLoader.class.getName(), false);
    }

    private void loadTinker() {
        try {
            Class<?> cls = Class.forName(this.loaderClassName, false, TinkerApplication.class.getClassLoader());
            this.tinkerResultIntent = (Intent) cls.getMethod(TINKER_LOADER_METHOD, new Class[]{TinkerApplication.class}).invoke(cls.getConstructor(new Class[0]).newInstance(new Object[0]), new Object[]{this});
        } catch (Throwable th) {
            this.tinkerResultIntent = new Intent();
            ShareIntentUtil.a(this.tinkerResultIntent, -20);
            this.tinkerResultIntent.putExtra("intent_patch_exception", th);
        }
    }

    private ITinkerInlineFenceBridge createInlineFence(int i, String str, boolean z, long j, long j2, Intent intent) {
        try {
            Constructor<?> constructor = Class.forName("com.tencent.tinker.entry.TinkerApplicationInlineFence", true, super.getClassLoader()).getConstructor(new Class[]{Integer.TYPE, String.class, Boolean.TYPE, Long.TYPE, Long.TYPE, Intent.class});
            constructor.setAccessible(true);
            return (ITinkerInlineFenceBridge) constructor.newInstance(new Object[]{Integer.valueOf(i), str, Boolean.valueOf(z), Long.valueOf(j), Long.valueOf(j2), intent});
        } catch (Throwable th) {
            throw new TinkerRuntimeException("fail to create inline fence instance.", th);
        }
    }

    private void onBaseContextAttached(Context context) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long currentTimeMillis = System.currentTimeMillis();
            loadTinker();
            this.mBridge = createInlineFence(this.tinkerFlags, this.delegateClassName, this.tinkerLoadVerifyFlag, elapsedRealtime, currentTimeMillis, this.tinkerResultIntent);
            this.mBridge.a(this, context);
            if (this.useSafeMode) {
                ShareTinkerInternals.a((Context) this, 0);
            }
        } catch (TinkerRuntimeException e) {
            throw e;
        } catch (Throwable th) {
            throw new TinkerRuntimeException(th.getMessage(), th);
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        Thread.setDefaultUncaughtExceptionHandler(new TinkerUncaughtHandler(this));
        onBaseContextAttached(context);
    }

    public void onCreate() {
        super.onCreate();
        if (this.mBridge != null) {
            this.mBridge.a(this);
        }
    }

    public void onTerminate() {
        super.onTerminate();
        if (this.mBridge != null) {
            this.mBridge.b();
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (this.mBridge != null) {
            this.mBridge.a();
        }
    }

    @TargetApi(14)
    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (this.mBridge != null) {
            this.mBridge.a(i);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.mBridge != null) {
            this.mBridge.a(configuration);
        }
    }

    public Resources getResources() {
        Resources resources = super.getResources();
        return this.mBridge != null ? this.mBridge.a(resources) : resources;
    }

    public ClassLoader getClassLoader() {
        ClassLoader classLoader = super.getClassLoader();
        return this.mBridge != null ? this.mBridge.a(classLoader) : classLoader;
    }

    public AssetManager getAssets() {
        AssetManager assets = super.getAssets();
        return this.mBridge != null ? this.mBridge.a(assets) : assets;
    }

    public Object getSystemService(String str) {
        Object systemService = super.getSystemService(str);
        return this.mBridge != null ? this.mBridge.a(str, systemService) : systemService;
    }

    public Context getBaseContext() {
        Context baseContext = super.getBaseContext();
        return this.mBridge != null ? this.mBridge.a(baseContext) : baseContext;
    }

    public void setUseSafeMode(boolean z) {
        this.useSafeMode = z;
    }

    public boolean isTinkerLoadVerifyFlag() {
        return this.tinkerLoadVerifyFlag;
    }

    public int getTinkerFlags() {
        return this.tinkerFlags;
    }
}
