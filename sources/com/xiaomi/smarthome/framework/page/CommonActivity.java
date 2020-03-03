package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Menu;
import android.view.Window;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.corereceiver.ActivityListener;
import com.xiaomi.smarthome.framework.crash.MainCrashHandler;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.ActivityHandler;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SystemUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.stat.MiStat;
import com.xiaomi.stat.MiStatParams;
import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class CommonActivity extends FragmentActivity implements ActivityListener, ActivityHandler.HandleMessage {
    public static String ACTION_SPLIT_SCREEN_MODE_CHANGED = "split_screen_mode_changed";
    public static final String FULL_REFERER_KEY = "full_refetitle_barrer_key";
    public static final String SINGLE_REFERER_KEY = "single_referer_key";
    public static final int TIME_FINISH = 3000000;
    public static Map<Integer, WeakReference<Activity>> mActivityStack = new TreeMap();
    private static int sDefaultNavBarColor = Color.argb(255, 255, 255, 255);
    private final BroadcastReceiver mBaseActivityLocalReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "miot_activit_finish_tag") && CommonActivity.this.mIsPaused) {
                CommonActivity.this.finish();
            }
        }
    };
    private final BroadcastReceiver mBaseActivityReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                CommonActivity.this.onHomeKeyPressed();
                CommonApplication.getGlobalHandler().removeCallbacks(BaseActivityUtils.b);
                CommonApplication.getGlobalHandler().postDelayed(BaseActivityUtils.b, 3000000);
            }
        }
    };
    public long mEnterTime;
    private String mFullReferer;
    public Handler mHandler;
    private final IntentFilter mIntentFilter = new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    private boolean mIsDelayFrescoInit = false;
    /* access modifiers changed from: protected */
    public boolean mIsDestroyed = false;
    protected boolean mIsPaused = false;
    private final IntentFilter mLocalIntentFilter = new IntentFilter("miot_activit_finish_tag");
    long mOnresumeTimestamp;
    public String mPageId = "";
    String mPageName;
    private String mSingleReferer;

    /* access modifiers changed from: protected */
    public boolean enablePageId() {
        return true;
    }

    /* access modifiers changed from: protected */
    public String getActivityName() {
        return null;
    }

    public Context getContext() {
        return this;
    }

    public void handleMessage(Message message) {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    public void onHomeKeyPressed() {
    }

    /* access modifiers changed from: protected */
    public boolean useActivityAsStat() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void setDelayFrescoInit() {
        this.mIsDelayFrescoInit = true;
    }

    /* access modifiers changed from: protected */
    public boolean shouldFixLocale() {
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        Locale c = ServerCompact.c((Context) this);
        if (c == null) {
            return true;
        }
        LocaleList locales = getResources().getConfiguration().getLocales();
        if (locales == null) {
            return false;
        }
        if (!locales.isEmpty() && c.equals(locales.get(0))) {
            return false;
        }
        return true;
    }

    private void fixLocalIfNeeded() {
        if (shouldFixLocale() && Build.VERSION.SDK_INT >= 24) {
            Locale c = ServerCompact.c(getApplicationContext());
            if (c == null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    c = Resources.getSystem().getConfiguration().getLocales().get(0);
                } else {
                    c = Locale.getDefault();
                }
            }
            LanguageUtil.b(c);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && TextUtils.equals("android.intent.action.MAIN", action)) {
                finish();
            }
        }
        fixLocalIfNeeded();
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            CommonApplication.setInSplitScreenMode(isInMultiWindowMode());
        }
        registerReceiver(this.mBaseActivityReceiver, this.mIntentFilter);
        try {
            requestWindowFeature(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (CommonApplication.getAppContext() != null) {
            LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).registerReceiver(this.mBaseActivityLocalReceiver, this.mLocalIntentFilter);
        }
        if (bundle != null) {
            CommonApplication.getApplication().onApplicationLifeCycleStart();
        }
        ActivityStack.instance.registerActivityListener(this);
        Intent intent2 = getIntent();
        if (enablePageId()) {
            this.mPageId = getIntent().getStringExtra("iid");
        }
        this.mSingleReferer = intent2.getStringExtra("single_referer_key");
        this.mFullReferer = intent2.getStringExtra(FULL_REFERER_KEY);
        this.mHandler = new ActivityHandler(this);
        setNavBarColor();
        mActivityStack.put(Integer.valueOf(hashCode()), new WeakReference(this));
    }

    private void setNavBarColor() {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setNavigationBarColor(getPageNavBarColor());
        }
    }

    /* access modifiers changed from: protected */
    public int getPageNavBarColor() {
        if (!SystemApi.c()) {
            return -16777216;
        }
        return sDefaultNavBarColor;
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.c(this);
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
    public void onResume() {
        try {
            super.onResume();
            this.mIsDestroyed = false;
            this.mIsPaused = false;
            this.mEnterTime = System.currentTimeMillis();
            CommonApplication.getGlobalHandler().removeCallbacks(BaseActivityUtils.b);
            if (useActivityAsStat()) {
                this.mPageName = getActivityName();
                if (TextUtils.isEmpty(this.mPageName)) {
                    this.mPageName = getClass().getName();
                    String packageName = getPackageName();
                    if (this.mPageName.startsWith(packageName)) {
                        this.mPageName = this.mPageName.replace(packageName, "");
                    }
                }
                this.mOnresumeTimestamp = STAT.b.a(this, this.mPageName);
                MiStatInterface.a((Activity) this, this.mPageName);
                new MiStatParams().putLong("start_date", System.currentTimeMillis());
                MiStat.trackPageStart(this.mPageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyLog.d("Exception in super.onResume in " + this + ":" + e.getMessage());
            MainCrashHandler.a((Throwable) e);
            Intent intent = new Intent(this, GlobalSetting.z);
            intent.setFlags(268468224);
            startActivity(intent);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mIsPaused = true;
        if (useActivityAsStat()) {
            STAT.b.a(this, this.mOnresumeTimestamp, this.mPageName);
            MiStatInterface.c();
            MiStatParams miStatParams = new MiStatParams();
            miStatParams.putLong("end_date", System.currentTimeMillis());
            MiStat.trackPageEnd(this.mPageName, miStatParams);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mIsDestroyed = true;
        unregisterReceiver(this.mBaseActivityReceiver);
        CoreApi.a().a((Context) this);
        ActivityStack.instance.unregisterActivityListener(this);
        if (CommonApplication.getAppContext() != null) {
            LocalBroadcastManager.getInstance(CommonApplication.getAppContext()).unregisterReceiver(this.mBaseActivityLocalReceiver);
        }
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (!(getWindow() == null || getWindow().getDecorView() == null || getWindow().getDecorView().getHandler() == null)) {
            getWindow().getDecorView().getHandler().removeCallbacksAndMessages((Object) null);
        }
        try {
            CommonApplication.getGlobalHandler().postDelayed(new GCRunnable(), 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            super.onDestroy();
            mActivityStack.remove(Integer.valueOf(hashCode()));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setRequestedOrientation(int i) {
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            super.setRequestedOrientation(i);
        }
    }

    private static class GCRunnable implements Runnable {
        private GCRunnable() {
        }

        public void run() {
            System.gc();
            CoreApi.a().n();
        }
    }

    public void onUnauthorized() {
        if (getClass() != GlobalSetting.z) {
            finish();
        }
        ((PluginHostApi) PluginHostApi.instance()).login(this, 2);
    }

    public void onActivityResume(int i, int i2, String str) {
        CommonApplication.getGlobalHandler().removeCallbacks(BaseActivityUtils.b);
    }

    public void onServerChanged() {
        finish();
    }

    public boolean isValid() {
        return SystemUtils.a((Activity) this);
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        CommonApplication.setInSplitScreenMode(z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_SPLIT_SCREEN_MODE_CHANGED));
    }

    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        CommonApplication.setInSplitScreenMode(z);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(ACTION_SPLIT_SCREEN_MODE_CHANGED));
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.a(context));
    }
}
