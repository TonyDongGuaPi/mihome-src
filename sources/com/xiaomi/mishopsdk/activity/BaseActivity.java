package com.xiaomi.mishopsdk.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.mishopsdk.volley.Response;
import com.xiaomi.mishopsdk.Listener.OnActivityResultListener;
import com.xiaomi.mishopsdk.Listener.OnBackPressedListener;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.event.UpdateCountEvent;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.SystemBarTintManager;
import com.xiaomi.shop2.mishop2ann.PermissionMethod;
import com.xiaomi.shop2.util.PermissionUtil;
import de.greenrobot.event.EventBus;
import java.util.HashMap;

public abstract class BaseActivity extends FragmentActivity {
    public static final int REQUESTCODE_PER_GET_ACCOUNTS = 103;
    private static final int REQUESTCODE_PER_PHONE_STATE = 102;
    private static final int REQUESTCODE_PER_STORAGE = 101;
    private static final long SHOPPING_COUNT_UPDATE_TIMEOUT = 180000;
    private static final String TAG = "BaseActivity";
    private static long sLastTimeOfShoppingCountUpdate = 0;
    /* access modifiers changed from: private */
    public static int sOldShoppingCount = -1;
    public static int statusBarHeight;
    public SystemBarTintManager mSystemBarTintManager;
    public OnActivityResultListener onActivityResultListener;
    public OnBackPressedListener onBackPressedListener;

    /* access modifiers changed from: protected */
    public boolean needStatusBarTintEnabled() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void setTranslucentStatusBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
        }
        this.mSystemBarTintManager = new SystemBarTintManager(this);
        this.mSystemBarTintManager.setStatusBarTintEnabled(needStatusBarTintEnabled());
        this.mSystemBarTintManager.setNavigationBarTintEnabled(false);
        this.mSystemBarTintManager.setTintColor(getResources().getColor(R.color.mishopsdk_black));
        statusBarHeight = this.mSystemBarTintManager.getConfig().getStatusBarHeight();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        ShopApp.getInstance().onActivityResumed();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ShopApp.getInstance().waitForInitAsync();
        ShopApp.getInstance();
        ShopApp.initIfHaveNot();
        setTranslucentStatusBar();
        updateCartAndAccount();
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (!PermissionUtil.onRequestPermissionsResult(this, i, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    @Deprecated
    public boolean checkPermission(String str, int i) {
        return PermissionUtil.checkPermission((Activity) this, str, i);
    }

    @Deprecated
    public boolean checkPermissions(String[] strArr, int i) {
        return PermissionUtil.checkPermissions((Activity) this, strArr, i);
    }

    public void onBackPressed() {
        if (this.onBackPressedListener == null || !this.onBackPressedListener.onBackPressed()) {
            try {
                super.onBackPressed();
                overridePendingTransition(R.anim.mishopsdk_left_enter, R.anim.mishopsdk_right_out);
            } catch (Exception e) {
                Log.d(TAG, "the activity has exit.", (Object) e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.onActivityResultListener == null || !this.onActivityResultListener.onMyActivityResult(i, i2, intent)) {
            super.onActivityResult(i, i2, intent);
            LoginManager.getInstance().onActivityResult(i, i2, intent);
        }
    }

    public void updateCartAndAccount() {
        if (!LoginManager.getInstance().hasLogin()) {
            return;
        }
        if (ShopApp.sShoppingCount == -1 || System.currentTimeMillis() - sLastTimeOfShoppingCountUpdate > SHOPPING_COUNT_UPDATE_TIMEOUT) {
            updateShoppingCount();
        } else {
            EventBus.getDefault().post(new UpdateCountEvent());
        }
    }

    public void updateShoppingCount() {
        RequestQueueManager instance = RequestQueueManager.getInstance();
        instance.postApiRequest((Object) "shoppingCount", HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "shopping/count", (HashMap<String, String>) new HashMap(), Integer.class, new ShoppingCountListener());
    }

    private static class ShoppingCountListener extends Response.SimpleListener<Integer> {
        private ShoppingCountListener() {
        }

        public void onSuccess(Integer num, boolean z) {
            BaseActivity.updateShoppingCountEx(num.intValue());
            if (BaseActivity.sOldShoppingCount != ShopApp.sShoppingCount) {
                int unused = BaseActivity.sOldShoppingCount = ShopApp.sShoppingCount;
            }
        }
    }

    public void updateShoppingCount(int i) {
        ShopApp.sShoppingCount = i;
        if (ShopApp.sShoppingCount != -1) {
            sLastTimeOfShoppingCountUpdate = System.currentTimeMillis();
        }
        EventBus.getDefault().post(new UpdateCountEvent());
    }

    @Deprecated
    public static void updateShoppingCountEx(int i) {
        ShopApp.sShoppingCount = i;
        if (ShopApp.sShoppingCount != -1) {
            sLastTimeOfShoppingCountUpdate = System.currentTimeMillis();
        }
        EventBus.getDefault().post(new UpdateCountEvent());
    }

    private void detectPermission() {
        if (PermissionUtil.checkPermissions(getParent() != null ? getParent() : this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 101)) {
            onStoragePermissionGranted();
        }
    }

    @PermissionMethod(permissionGranted = true, requestCode = 101)
    private void onStoragePermissionGranted() {
        PermissionUtil.checkReadPhoneStatePermission(getParent() != null ? getParent() : this, 102);
    }

    @PermissionMethod(permissionGranted = false, requestCode = 101)
    private void onStoragePermissionDenied() {
        PermissionUtil.onGeneralPermissionFailed(this, false, ShopApp.instance.getString(R.string.permission_storage_name), ShopApp.instance.getString(R.string.permission_func_name));
    }

    @PermissionMethod(permissionGranted = false, requestCode = 103)
    private void onGetAccountsPermissionDenied() {
        PermissionUtil.onGeneralPermissionFailed(this, false, ShopApp.instance.getString(R.string.permission_accounts_name), ShopApp.instance.getString(R.string.permission_func_name));
    }
}
