package com.xiaomi.smarthome.frame.baseui;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.activity.PluginHostActivity;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;
import java.util.Locale;

public class BaseActivity extends FragmentActivity {
    public static final String FULL_REFERER_KEY = "full_referer_key";
    public static final String MIOT_ACTIVITY_FINISH_TAG = "miot_activit_finish_tag";
    public static final String SINGLE_REFERER_KEY = "single_referer_key";
    public static Runnable sClearRunnable = new Runnable() {
        public void run() {
            Intent intent = new Intent();
            intent.setAction("miot_activit_finish_tag");
            LocalBroadcastManager.getInstance(FrameManager.b().c()).sendBroadcast(intent);
            Log.e("BaseActivity", "finish page");
        }
    };
    private final BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "miot_activit_finish_tag") && BaseActivity.this.mIsPaused) {
                BaseActivity.this.finish();
            }
        }
    };
    private String mFullReferer;
    private final BroadcastReceiver mHomeKeyReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                FrameManager.b().d().removeCallbacks(BaseActivity.sClearRunnable);
                FrameManager.b().d().postDelayed(BaseActivity.sClearRunnable, 30000);
                Log.e("BaseActivity", "add Callback");
            }
        }
    };
    protected boolean mIsPaused = false;
    long mOnResumeTimestamp;
    String mPageName;
    private String mSingleReferer;

    /* access modifiers changed from: protected */
    public String getPageName() {
        return null;
    }

    public static boolean isAppOnForeground(Context context) {
        String packageName = context.getPackageName();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo next : runningAppProcesses) {
            if (next.processName.startsWith(packageName) && (next.importance == 100 || next.importance == 200)) {
                Log.e("CommonUtils", "Process:" + next.processName);
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        fixLocalIfNeeded();
        DisplayUtils.e(this);
        super.onCreate(bundle);
        DarkModeCompat.b((Activity) this);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        FrameManager.b().h().a(this);
        registerReceiver(this.mHomeKeyReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        LocalBroadcastManager.getInstance(FrameManager.b().c()).registerReceiver(this.mFinishReceiver, new IntentFilter("miot_activit_finish_tag"));
        requestWindowFeature(1);
        Intent intent = getIntent();
        if (!(this instanceof PluginHostActivity)) {
            this.mSingleReferer = intent.getStringExtra("single_referer_key");
            this.mFullReferer = intent.getStringExtra("full_referer_key");
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mIsPaused = false;
        FrameManager.b().d().removeCallbacks(sClearRunnable);
        CoreApi.a().i(getPageName());
        Log.e("BaseActivity", "Remove Callback");
        this.mPageName = getPageName();
        if (TextUtils.isEmpty(this.mPageName)) {
            this.mPageName = getClass().getName();
            String packageName = getPackageName();
            if (this.mPageName.startsWith(packageName)) {
                this.mPageName = this.mPageName.replace(packageName, "");
            }
        }
        this.mOnResumeTimestamp = STAT.b.a(this, this.mPageName);
        MiStatInterface.a((Activity) this, this.mPageName);
        MobclickAgent.b((Context) this);
        CoreApi.a().a(this.mPageName, getSingleReferer());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mIsPaused = true;
        if (!(this instanceof PluginHostActivity)) {
            MiStatInterface.c();
            MobclickAgent.a((Context) this);
            STAT.b.a(this, this.mOnResumeTimestamp, this.mPageName);
            CoreApi.a().a(this.mPageName, getSingleReferer(), (int) (this.mOnResumeTimestamp / 1000));
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        FrameManager.b().h().b(this);
        unregisterReceiver(this.mHomeKeyReceiver);
        LocalBroadcastManager.getInstance(FrameManager.b().c()).unregisterReceiver(this.mFinishReceiver);
    }

    public String getSingleReferer() {
        String str = this.mSingleReferer;
        return str == null ? "" : str;
    }

    public String getFullReferer() {
        String str = this.mFullReferer;
        return str == null ? "" : str;
    }

    /* access modifiers changed from: protected */
    public boolean shouldFixLocale() {
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        Locale I = CoreApi.a().I();
        if (I == null) {
            return true;
        }
        LocaleList locales = getResources().getConfiguration().getLocales();
        if (locales == null) {
            return false;
        }
        if (!locales.isEmpty() && I.equals(locales.get(0))) {
            return false;
        }
        return true;
    }

    private void fixLocalIfNeeded() {
        if (shouldFixLocale() && Build.VERSION.SDK_INT >= 24) {
            Locale I = CoreApi.a().I();
            if (I == null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    I = Resources.getSystem().getConfiguration().getLocales().get(0);
                } else {
                    I = Locale.getDefault();
                }
            }
            Configuration configuration = new Configuration(getResources().getConfiguration());
            configuration.setLocale(I);
            getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        }
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        Locale I;
        if (Build.VERSION.SDK_INT >= 17 && (I = CoreApi.a().I()) != null) {
            Configuration configuration = context.getResources().getConfiguration();
            if (Build.VERSION.SDK_INT >= 17) {
                configuration.setLocale(I);
                context = context.createConfigurationContext(configuration);
            }
        }
        super.attachBaseContext(context);
    }

    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }
}
