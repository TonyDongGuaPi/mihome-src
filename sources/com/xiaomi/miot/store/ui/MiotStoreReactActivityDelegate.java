package com.xiaomi.miot.store.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.bridge.Callback;
import com.facebook.react.devsupport.DoubleTapReloadRecognizer;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionListener;
import com.xiaomi.miot.store.api.IMiotStoreActivityDelegate;
import com.xiaomi.miot.store.common.RNAppStoreApiManager;
import com.xiaomi.youpin.log.LogUtils;
import javax.annotation.Nullable;

public class MiotStoreReactActivityDelegate implements IMiotStoreActivityDelegate {
    static final String TAG = "MiotStoreReactActivityDelegate";
    @Nullable
    private final Activity mActivity;
    @Nullable
    private DoubleTapReloadRecognizer mDoubleTapReloadRecognizer;
    /* access modifiers changed from: private */
    @Nullable
    public PermissionListener mPermissionListener;
    @Nullable
    private Callback mPermissionsCallback;

    public MiotStoreReactActivityDelegate(Activity activity) {
        this.mActivity = activity;
    }

    public ReactInstanceManager getReactInstanceManager() {
        if (RNAppStoreApiManager.a() == null) {
            return null;
        }
        return RNAppStoreApiManager.a().k();
    }

    public void onCreate(Bundle bundle) {
        this.mDoubleTapReloadRecognizer = new DoubleTapReloadRecognizer();
    }

    public void onPause() {
        LogUtils.d(TAG, "onPause");
        if (!(RNAppStoreApiManager.a().i() == null || getReactInstanceManager() == null)) {
            getReactInstanceManager().onHostPause(getPlainActivity());
        }
        RNAppStoreApiManager.a().a((Activity) null);
    }

    public void onResume() {
        LogUtils.d(TAG, "onResume");
        RNAppStoreApiManager.a().a(this.mActivity);
        if (getReactInstanceManager() != null) {
            getReactInstanceManager().onHostResume(getPlainActivity(), (DefaultHardwareBackBtnHandler) getPlainActivity());
        }
        if (this.mPermissionsCallback != null) {
            this.mPermissionsCallback.invoke(new Object[0]);
            this.mPermissionsCallback = null;
        }
    }

    public void onDestroy() {
        if (getReactInstanceManager() != null) {
            new Handler().post(new Runnable() {
                public void run() {
                    if (MiotStoreReactActivityDelegate.this.getReactInstanceManager() != null) {
                        MiotStoreReactActivityDelegate.this.getReactInstanceManager().onHostDestroy(MiotStoreReactActivityDelegate.this.getPlainActivity());
                    }
                }
            });
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        RNAppStoreApiManager.a().a(i, i2, intent);
        if (getReactInstanceManager() != null) {
            getReactInstanceManager().onActivityResult(getPlainActivity(), i, i2, intent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (getReactInstanceManager() == null || !RNAppStoreApiManager.a().j().isRNDebug()) {
            return false;
        }
        if (i == 82) {
            getReactInstanceManager().showDevOptionsDialog();
            return true;
        } else if (!((DoubleTapReloadRecognizer) Assertions.assertNotNull(this.mDoubleTapReloadRecognizer)).didDoubleTapR(i, getPlainActivity().getCurrentFocus())) {
            return false;
        } else {
            getReactInstanceManager().getDevSupportManager().handleReloadJS();
            return true;
        }
    }

    public boolean onBackPressed() {
        if (getReactInstanceManager() == null) {
            return false;
        }
        getReactInstanceManager().onBackPressed();
        return true;
    }

    public boolean onNewIntent(Intent intent) {
        if (getReactInstanceManager() == null) {
            return false;
        }
        getReactInstanceManager().onNewIntent(intent);
        return true;
    }

    @TargetApi(23)
    public void requestPermissions(String[] strArr, int i, PermissionListener permissionListener) {
        this.mPermissionListener = permissionListener;
        getPlainActivity().requestPermissions(strArr, i);
    }

    public void onRequestPermissionsResult(final int i, final String[] strArr, final int[] iArr) {
        this.mPermissionsCallback = new Callback() {
            public void invoke(Object... objArr) {
                if (MiotStoreReactActivityDelegate.this.mPermissionListener != null && MiotStoreReactActivityDelegate.this.mPermissionListener.onRequestPermissionsResult(i, strArr, iArr)) {
                    PermissionListener unused = MiotStoreReactActivityDelegate.this.mPermissionListener = null;
                }
            }
        };
    }

    /* access modifiers changed from: private */
    public Activity getPlainActivity() {
        return this.mActivity;
    }
}
