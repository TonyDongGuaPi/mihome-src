package com.xiaomi.youpin;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.facebook.react.modules.core.PermissionListener;
import com.xiaomi.miot.store.api.IMiotStoreActivityDelegate;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.smarthome.shop.mishop.ProductIdMapDataManager;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import org.jetbrains.annotations.NotNull;

public class MainActivityLifecycleForYP implements LifecycleObserver {

    /* renamed from: a  reason: collision with root package name */
    Handler f1574a = new Handler(Looper.getMainLooper());
    IMiotStoreActivityDelegate b;
    private LocalActivityManager c;
    private SmartHomeMainActivity d;

    public void a(long j) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(@NotNull LifecycleOwner lifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(@NotNull LifecycleOwner lifecycleOwner) {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(@NotNull LifecycleOwner lifecycleOwner) {
    }

    public void a(SmartHomeMainActivity smartHomeMainActivity, Bundle bundle) {
        this.d = smartHomeMainActivity;
        this.f1574a.postDelayed($$Lambda$MainActivityLifecycleForYP$QVXBgXqQk13v6jDTaD9Cik_NIe4.INSTANCE, 3000);
        this.f1574a.postDelayed(new Runnable() {
            public final void run() {
                MainActivityLifecycleForYP.this.e();
            }
        }, 5000);
        StatManager.d();
        Intent intent = smartHomeMainActivity.getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("url");
            if (!TextUtils.isEmpty(stringExtra)) {
                try {
                    StatManager.a().d(stringExtra);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.c = new LocalActivityManager(smartHomeMainActivity, true);
        if (MiotStoreApi.a() != null) {
            this.b = MiotStoreApi.a().newMiotStoreActivityDelegate(smartHomeMainActivity);
        }
        if (this.b != null) {
            this.b.onCreate(bundle);
        }
        this.c.dispatchCreate(bundle);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e() {
        try {
            SHApplication.getThreadExecutor().submit(new Runnable() {
                public void run() {
                    StatManager.a().a(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume(@NotNull LifecycleOwner lifecycleOwner) {
        if (this.c != null) {
            this.c.dispatchResume();
        }
        if (this.b != null) {
            this.b.onResume();
        }
        d();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(@NotNull LifecycleOwner lifecycleOwner) {
        try {
            if (this.b != null) {
                this.b.onPause();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(@NotNull LifecycleOwner lifecycleOwner) {
        if (this.c != null) {
            this.c.dispatchStop();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(@NotNull LifecycleOwner lifecycleOwner) {
        if (this.c != null) {
            this.c.dispatchDestroy(true);
            a();
        }
        if (this.b != null) {
            this.b.onDestroy();
        }
    }

    public void a() {
        try {
            Field declaredField = this.c.getClass().getDeclaredField("mResumed");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(this.c);
            Field declaredField2 = obj.getClass().getDeclaredField("window");
            declaredField2.setAccessible(true);
            Field declaredField3 = obj.getClass().getDeclaredField("activity");
            declaredField3.setAccessible(true);
            declaredField2.set(obj, (Object) null);
            declaredField3.set(obj, (Object) null);
            declaredField.set(this.c, (Object) null);
        } catch (Exception unused) {
        }
    }

    public void b() {
        ProductIdMapDataManager.a().b();
        YouPinSplashManager.c().f();
    }

    public void a(String[] strArr, int i, PermissionListener permissionListener) {
        if (this.b != null) {
            this.b.requestPermissions(strArr, i, permissionListener);
        }
    }

    public void a(int i, String[] strArr, int[] iArr) {
        if (this.b != null) {
            this.b.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void a(Intent intent) {
        if (this.b != null) {
            this.b.onNewIntent(intent);
        }
    }

    private void d() {
        if (this.d != null) {
            Intent intent = this.d.getIntent();
            String stringExtra = intent.getStringExtra(ApiConst.j);
            String stringExtra2 = intent.getStringExtra("url");
            if (stringExtra2 != null && !stringExtra2.isEmpty()) {
                if (stringExtra2.startsWith("mijia://home.mi.com/miloan?url=")) {
                    stringExtra2 = URLDecoder.decode(stringExtra2.substring("mijia://home.mi.com/miloan?url=".length()));
                }
                UrlDispatchManger.a().a((Activity) this.d, stringExtra2, -1);
            } else if (stringExtra != null && !stringExtra.isEmpty()) {
                intent.putExtra(ApiConst.j, "");
                UrlDispatchManger.a().c("https://home.mi.com/shop/detail?gid=" + stringExtra);
            }
            String str = null;
            intent.putExtra(ApiConst.j, str);
            intent.putExtra("url", str);
        }
    }

    public LocalActivityManager c() {
        return this.c;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return this.b != null && this.b.onKeyUp(i, keyEvent);
    }

    public void a(int i, int i2, Intent intent) {
        if (this.b != null) {
            this.b.onActivityResult(i, i2, intent);
        }
    }

    public void a(boolean z) {
        StatManager.a().a("YOUPINTABBARREDPOINT", "", "");
    }

    public void a(String str) {
        UrlDispatchManger.a().c(str);
    }
}
