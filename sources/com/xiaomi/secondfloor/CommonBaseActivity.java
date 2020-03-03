package com.xiaomi.secondfloor;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.xiaomi.miot.store.api.IMiotStoreActivityDelegate;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.plugin.XmPluginHostApi;
import java.lang.ref.WeakReference;

public class CommonBaseActivity extends FragmentActivity implements DefaultHardwareBackBtnHandler, PermissionAwareActivity {
    protected Handler mHandler;
    protected boolean mIsBack = false;
    IMiotStoreActivityDelegate mMiotStoreReactActivityDelegate;

    /* access modifiers changed from: protected */
    public String getIid() {
        return "";
    }

    /* access modifiers changed from: protected */
    public String getPageName() {
        return "";
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return "";
    }

    public void handleMessage(Message message) {
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mMiotStoreReactActivityDelegate = MiotStoreApi.a().newMiotStoreActivityDelegate(this);
        this.mMiotStoreReactActivityDelegate.onCreate(bundle);
        this.mHandler = new ActivityHandler();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.onNewIntent(intent);
        }
        this.mIsBack = false;
    }

    public void onResume() {
        super.onResume();
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.onResume();
        }
        String pageName = getPageName();
        if (!TextUtils.isEmpty(pageName) && XmPluginHostApi.instance() != null) {
            XmPluginHostApi.instance().addViewRecord(pageName, getUrl(), getIid(), this.mIsBack ? 1 : 2);
            this.mIsBack = true;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.onPause();
        }
        if (XmPluginHostApi.instance() != null) {
            XmPluginHostApi.instance().addViewEndRecord();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mMiotStoreReactActivityDelegate == null || !this.mMiotStoreReactActivityDelegate.onKeyUp(i, keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.onDestroy();
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @TargetApi(23)
    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        if (this.mMiotStoreReactActivityDelegate != null) {
            this.mMiotStoreReactActivityDelegate.requestPermissions(strArr, i, permissionListener);
        }
    }

    private static class ActivityHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<CommonBaseActivity> f13057a;

        private ActivityHandler(CommonBaseActivity commonBaseActivity) {
            this.f13057a = new WeakReference<>(commonBaseActivity);
        }

        public void handleMessage(Message message) {
            CommonBaseActivity commonBaseActivity;
            if (this.f13057a != null && (commonBaseActivity = (CommonBaseActivity) this.f13057a.get()) != null && !commonBaseActivity.isFinishing()) {
                commonBaseActivity.handleMessage(message);
            }
        }
    }
}
