package com.xiaomi.miot.store.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.WindowManager;
import com.taobao.weex.common.Constants;
import com.xiaomi.miot.store.R;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.api.MiotStoreBaseReactFragmentActivity;
import com.xiaomi.miot.store.api.RNStoreApiProvider;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.miot.store.utils.InputMethodManagerLeakUtils;
import com.xiaomi.miot.store.utils.Utils;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class MiotStoreMainActivity extends MiotStoreBaseReactFragmentActivity {
    static final String FRAGMENTS_TAG = "android:support:fragments";
    static final int MSG_AUTO_FINISH = 1;
    private static final String TAG = "MiotStoreMainActivity";
    public static final int TIME_FINISH = 30000;
    private static final List<WeakReference<Activity>> sDetailActivities = new LinkedList();
    private final BroadcastReceiver mBaseActivityReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.CLOSE_SYSTEM_DIALOGS")) {
                MiotStoreMainActivity.this.mHandler.removeMessages(1);
                MiotStoreMainActivity.this.mHandler.sendEmptyMessageDelayed(1, 30000);
            }
        }
    };
    boolean mEnableAutoFinish = false;
    Handler mHandler = null;
    boolean mIsPaused = false;
    private Uri mLaunchUri = null;
    RNAppStoreApiManager mStoreApiManager;

    private static class ActivityHandler extends Handler {
        WeakReference<MiotStoreMainActivity> mRefActivity;

        private ActivityHandler(MiotStoreMainActivity miotStoreMainActivity) {
            this.mRefActivity = new WeakReference<>(miotStoreMainActivity);
        }

        public void handleMessage(Message message) {
            MiotStoreMainActivity miotStoreMainActivity;
            if (this.mRefActivity != null && (miotStoreMainActivity = (MiotStoreMainActivity) this.mRefActivity.get()) != null && !miotStoreMainActivity.isFinishing()) {
                miotStoreMainActivity.handleMessage(message);
            }
        }
    }

    public void handleMessage(Message message) {
        if (message.what == 1 && this.mIsPaused && this.mEnableAutoFinish) {
            LogUtils.d(TAG, Constants.Event.FINISH);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        RNStoreApiProvider j;
        if (bundle != null) {
            bundle.remove(FRAGMENTS_TAG);
        }
        super.onCreate(bundle);
        LogUtils.d(TAG, "onCreate");
        Utils.a(true, (Activity) this);
        this.mHandler = new ActivityHandler();
        this.mStoreApiManager = RNAppStoreApiManager.a();
        if (MiotStoreApi.a() == null) {
            LogUtils.e(TAG, "mStoreApiManager==null finish");
            finish();
            return;
        }
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null && b.d().equals("SmartHome")) {
            this.mEnableAutoFinish = true;
        }
        this.mLaunchUri = getIntent().getData();
        if (bundle != null && this.mLaunchUri == null) {
            String string = bundle.getString("url");
            if (!TextUtils.isEmpty(string)) {
                this.mLaunchUri = Uri.parse(string);
            }
        }
        if (this.mLaunchUri == null) {
            this.mLaunchUri = Uri.parse("http://home.mi.com/shop/main");
        }
        setContentView(R.layout.miotstore_main_activity);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.container, MiotStoreApi.a().newMiotStoreFragment(this.mLaunchUri.toString(), false));
        beginTransaction.commit();
        boolean z = Build.BRAND.equalsIgnoreCase("htc") && Build.MODEL.contains("M8w");
        if (!z) {
            try {
                WindowManager.LayoutParams attributes = getWindow().getAttributes();
                Field declaredField = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field declaredField2 = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                declaredField.setAccessible(true);
                declaredField2.setAccessible(true);
                declaredField2.setInt(attributes, declaredField.getInt((Object) null) | declaredField2.getInt(attributes));
                getWindow().setAttributes(attributes);
                getWindow().setFlags(com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE, com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE);
                z = true;
            } catch (Exception unused) {
                z = false;
            }
        }
        if (!z && Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(com.xiaomi.mishopsdk.util.Constants.CALLIGRAPHY_TAG_PRICE);
            getWindow().getDecorView().setSystemUiVisibility(9216);
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(33554431);
        }
        if (this.mEnableAutoFinish) {
            registerReceiver(this.mBaseActivityReceiver, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        }
        if (!(this.mStoreApiManager == null || (j = this.mStoreApiManager.j()) == null)) {
            j.onActivityCreate(this);
        }
        addToDetailList(this.mLaunchUri.toString());
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtils.d(TAG, "onSaveInstanceState");
        if (this.mLaunchUri != null) {
            bundle.putString("url", this.mLaunchUri.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        RNStoreApiProvider j;
        super.onResume();
        LogUtils.d(TAG, "onResume");
        this.mIsPaused = false;
        this.mHandler.removeMessages(1);
        if (this.mStoreApiManager != null && (j = this.mStoreApiManager.j()) != null) {
            j.onActivityResume(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        RNStoreApiProvider j;
        super.onPause();
        LogUtils.d(TAG, "onPause");
        this.mIsPaused = true;
        if (this.mStoreApiManager != null && (j = this.mStoreApiManager.j()) != null) {
            j.onActivityPause(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        RNStoreApiProvider j;
        LogUtils.d(TAG, ActivityInfo.TYPE_STR_ONDESTROY);
        if (this.mEnableAutoFinish) {
            unregisterReceiver(this.mBaseActivityReceiver);
        }
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (!(getWindow() == null || getWindow().getDecorView() == null || getWindow().getDecorView().getHandler() == null)) {
            getWindow().getDecorView().getHandler().removeCallbacksAndMessages((Object) null);
        }
        super.onDestroy();
        InputMethodManagerLeakUtils.a(this);
        if (!(this.mStoreApiManager == null || (j = this.mStoreApiManager.j()) == null)) {
            j.onActivityDestroy(this);
        }
        removeOnDestroy();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_left, R.anim.activity_slide_out_right);
    }

    private void addToDetailList(String str) {
        if (!TextUtils.isEmpty(UrlConstants.parseShortPath(str))) {
            sDetailActivities.add(new WeakReference(this));
            if (sDetailActivities.size() > 5) {
                WeakReference weakReference = sDetailActivities.get(0);
                if (weakReference.get() != null && !((Activity) weakReference.get()).isFinishing()) {
                    ((Activity) weakReference.get()).finish();
                }
                sDetailActivities.remove(0);
            }
        }
    }

    private void removeOnDestroy() {
        int size = sDetailActivities.size();
        for (int i = 0; i < size; i++) {
            if (sDetailActivities.get(i).get() == this) {
                sDetailActivities.remove(i);
                return;
            }
        }
    }
}
