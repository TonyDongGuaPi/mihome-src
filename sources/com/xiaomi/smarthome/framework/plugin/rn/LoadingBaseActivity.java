package com.xiaomi.smarthome.framework.plugin.rn;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.FrescoInitial;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.plugin.rn.fixbug.AndroidBug5497Workaround;
import com.xiaomi.smarthome.library.DarkModeCompat;

@SuppressLint({"Registered"})
public class LoadingBaseActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static volatile View f17210a = null;
    private static volatile boolean b = true;
    protected FrameLayout flRoot;
    protected int launchMsgType = 1;
    protected View llLoading;
    protected DeviceStat mDevice;
    protected AnimationDrawable mLoadingDrawable;
    protected SimpleDraweeView mSdvIcon;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        FrescoInitial.a(false);
        super.onCreate(bundle);
        setContentView(R.layout.plugin_rn_activity);
        DarkModeCompat.a((Activity) this);
        getWindow().setSoftInputMode(32);
        AndroidBug5497Workaround.a().a((Activity) this);
        this.flRoot = (FrameLayout) findViewById(R.id.fl_root);
        findViewById(R.id.title_bar).setBackground((Drawable) null);
        this.llLoading = findViewById(R.id.ll_loading);
        this.mSdvIcon = (SimpleDraweeView) findViewById(R.id.icon);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LoadingBaseActivity.super.onBackPressed();
            }
        });
        this.llLoading.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.mDevice = RNRuntime.a().f();
        this.launchMsgType = getIntent().getIntExtra("package_msgType", 1);
        if (this.mDevice != null) {
            DeviceFactory.b(this.mDevice.model, this.mSdvIcon);
            XmPluginHostApi instance = XmPluginHostApi.instance();
            instance.log("LoadingBaseActivity.onCreate", " did:" + this.mDevice.did + " PinCode:" + this.mDevice.isSetPinCode + " class:" + getClass().getSimpleName());
        }
    }

    public void setContentView(int i) {
        if (i == R.layout.plugin_rn_activity) {
            setContentView(a(this));
        } else {
            super.setContentView(i);
        }
    }

    private static synchronized View a(Context context) {
        synchronized (LoadingBaseActivity.class) {
            b = false;
            if (f17210a == null) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.plugin_rn_activity, (ViewGroup) null);
                return inflate;
            }
            View view = f17210a;
            f17210a = null;
            return view;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean preloadContentView() {
        /*
            java.lang.Class<com.xiaomi.smarthome.framework.plugin.rn.LoadingBaseActivity> r0 = com.xiaomi.smarthome.framework.plugin.rn.LoadingBaseActivity.class
            monitor-enter(r0)
            boolean r1 = b     // Catch:{ all -> 0x0025 }
            r2 = 0
            if (r1 == 0) goto L_0x0023
            android.view.View r1 = f17210a     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0023
            android.content.Context r1 = com.xiaomi.smarthome.application.SHApplication.getAppContext()     // Catch:{ all -> 0x0025 }
            android.view.LayoutInflater r1 = android.view.LayoutInflater.from(r1)     // Catch:{ all -> 0x0025 }
            r3 = 2130904823(0x7f0306f7, float:1.7416503E38)
            r4 = 0
            android.view.View r1 = r1.inflate(r3, r4)     // Catch:{ all -> 0x0025 }
            f17210a = r1     // Catch:{ all -> 0x0025 }
            b = r2     // Catch:{ all -> 0x0025 }
            r1 = 1
            monitor-exit(r0)
            return r1
        L_0x0023:
            monitor-exit(r0)
            return r2
        L_0x0025:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.LoadingBaseActivity.preloadContentView():boolean");
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        AndroidBug5497Workaround.a().b();
    }
}
