package com.xiaomi.smarthome;

import android.app.LocalActivityManager;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.react.modules.core.PermissionListener;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.bbs.BBSInitializer;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.shopglobal.ShopGlobalHelper;
import com.xiaomi.smarthome.shopglobal.ShopGlobalLifecycleObserver;
import com.xiaomi.youpin.MainActivityLifecycleForYP;
import org.jetbrains.annotations.NotNull;

public class SmartHomeMainActivityLifecycle implements LifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    private MainActivityLifecycleForYP f1500a;
    private ShopGlobalLifecycleObserver b;

    public void a(MainActivityLifecycleForYP mainActivityLifecycleForYP) {
        this.f1500a = mainActivityLifecycleForYP;
    }

    public void a(ShopGlobalLifecycleObserver shopGlobalLifecycleObserver) {
        this.b = shopGlobalLifecycleObserver;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("SmartHomeMainActivityLifecycle", "onCreate");
        try {
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onCreate(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.b == null) {
                return;
            }
            if (ShopGlobalHelper.a((Context) SHApplication.getApplication()) || BBSInitializer.a((Context) SHApplication.getApplication())) {
                this.b.onCreate(lifecycleOwner);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("SmartHomeMainActivityLifecycle", "onStart");
        try {
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onStart(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.b == null) {
                return;
            }
            if (ShopGlobalHelper.a((Context) SHApplication.getApplication()) || BBSInitializer.a((Context) SHApplication.getApplication())) {
                this.b.onStart(lifecycleOwner);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("SmartHomeMainActivityLifecycle", "onResume");
        try {
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onResume(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.b == null) {
                return;
            }
            if (ShopGlobalHelper.a((Context) SHApplication.getApplication()) || BBSInitializer.a((Context) SHApplication.getApplication())) {
                this.b.onResume(lifecycleOwner);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("SmartHomeMainActivityLifecycle", "onPause");
        try {
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onPause(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.b == null) {
                return;
            }
            if (ShopGlobalHelper.a((Context) SHApplication.getApplication()) || BBSInitializer.a((Context) SHApplication.getApplication())) {
                this.b.onPause(lifecycleOwner);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("SmartHomeMainActivityLifecycle", "onStop");
        try {
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onStop(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.b == null) {
                return;
            }
            if (ShopGlobalHelper.a((Context) SHApplication.getApplication()) || BBSInitializer.a((Context) SHApplication.getApplication())) {
                this.b.onStop(lifecycleOwner);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("SmartHomeMainActivityLifecycle", ActivityInfo.TYPE_STR_ONDESTROY);
        try {
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onDestroy(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (this.b != null) {
                this.b.onDestroy(lifecycleOwner);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(@NotNull LifecycleOwner lifecycleOwner) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onAny");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.onAny(lifecycleOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onWindowsVisible");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.b();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(long j) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onDelayed " + j);
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(j);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(SmartHomeMainActivity smartHomeMainActivity, Bundle bundle) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onCreateNew ");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(smartHomeMainActivity, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String[] strArr, int i, PermissionListener permissionListener) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "requestPermissions ");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(strArr, i, permissionListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(int i, String[] strArr, int[] iArr) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onRequestPermissionsResult ");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(i, strArr, iArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(Intent intent) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onNewIntent " + intent);
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LocalActivityManager b() {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "getLocalActivityManager ");
            if (this.f1500a == null || HomeManager.A()) {
                return null;
            }
            return this.f1500a.c();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onKeyUp ");
            if (this.f1500a == null || HomeManager.A()) {
                return false;
            }
            return this.f1500a.a(i, keyEvent);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void a(int i, int i2, Intent intent) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "onActivityResult ");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(i, i2, intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(boolean z) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "updateTabviewShopDot ");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a(String str) {
        try {
            LogUtil.a("SmartHomeMainActivityLifecycle", "openYPUrl ");
            if (this.f1500a != null && !HomeManager.A()) {
                this.f1500a.a(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
