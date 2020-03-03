package com.xiaomi.smarthome.shopglobal;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.framework.log.LogUtil;
import org.jetbrains.annotations.NotNull;

public class ShopGlobalLifecycleObserver implements LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void onCreate(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", "onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", "onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onResume(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", "onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", "onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStop(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", "onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", ActivityInfo.TYPE_STR_ONDESTROY);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    public void onAny(@NotNull LifecycleOwner lifecycleOwner) {
        LogUtil.a("ShopGlobalLifecycleObserver", "onAny");
    }
}
