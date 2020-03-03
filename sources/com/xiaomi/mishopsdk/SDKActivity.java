package com.xiaomi.mishopsdk;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.xiaomi.mobilestats.StatService;
import com.xiaomi.shop2.mishop2ann.PermissionMethod;
import com.xiaomi.shop2.util.PermissionUtil;
import java.util.HashMap;

public class SDKActivity extends Activity {
    private static final int REQUESTCODE_PER_PHONE_STATE = 102;
    private static final int REQUESTCODE_PER_STORAGE = 101;
    private static final int REQUESTCODE_PRE_PRIVACY = 103;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (needCheckPrivacy()) {
            startActivityForResult(new Intent("com.xiaomi.mishopsdk.privacy.PrivacyActivity"), 103);
        } else if (isMyServiceSDK()) {
            doOnCreate();
        } else {
            checkPhoneStatePermission();
        }
    }

    private boolean needCheckPrivacy() {
        Uri data;
        if (!ShopApp.getInstance().isSystemApp() || ShopApp.getInstance().hasInitCta()) {
            return false;
        }
        if ((getIntent() == null && getIntent().getData() == null) || (data = getIntent().getData()) == null) {
            return false;
        }
        return SdkUtils.checkPluginHomePage(data);
    }

    private boolean isMyServiceSDK() {
        Uri data;
        if (!ShopApp.getInstance().isSystemApp()) {
            return false;
        }
        if ((getIntent() == null && getIntent().getData() == null) || (data = getIntent().getData()) == null) {
            return false;
        }
        return SdkUtils.checkSDKMyService(data);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 103) {
            return;
        }
        if (i2 == -1) {
            checkPhoneStatePermission();
        } else {
            finish();
        }
    }

    private void doOnCreate() {
        if (ShopApp.getInstance().isSystemApp()) {
            ShopApp.getInstance().onPermissionInit();
        }
        ShopApp.getInstance().postClientStateInfo();
        performScheme();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        performScheme();
    }

    private boolean performScheme() {
        Uri data;
        if ((getIntent() == null && getIntent().getData() == null) || (data = getIntent().getData()) == null) {
            return false;
        }
        onHostMiCom(getIntent());
        return SdkUtils.startNewPluginActivity(this, data);
    }

    private boolean onHostMiCom(Intent intent) {
        if (intent == null || intent.getData() == null || !TextUtils.equals("m.mi.com", intent.getData().getHost())) {
            return false;
        }
        String uri = intent.getData().toString();
        if (!TextUtils.isEmpty(uri)) {
            StatService.onPostEvent(this, "20000000310001000", uri, new HashMap());
        }
        return false;
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (!PermissionUtil.onRequestPermissionsResult(this, i, iArr)) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        }
    }

    private void detectPermissionAndOnCreate() {
        if (PermissionUtil.checkPermissions((Activity) this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"}, 101)) {
            onStoragePermissionGranted();
        }
    }

    @PermissionMethod(permissionGranted = true, requestCode = 101)
    private void onStoragePermissionGranted() {
        checkPhoneStatePermission();
    }

    private void checkPhoneStatePermission() {
        if (PermissionUtil.checkReadPhoneStatePermission(this, 102)) {
            onPhoneStatePermissionGranted();
        }
    }

    @PermissionMethod(permissionGranted = false, requestCode = 101)
    private void onStoragePermissionDenied() {
        PermissionUtil.onGeneralPermissionFailed(this, false, getString(R.string.permission_storage_name), getString(R.string.permission_func_name));
    }

    @PermissionMethod(permissionGranted = true, requestCode = 102)
    private void onPhoneStatePermissionGranted() {
        doOnCreate();
    }

    @PermissionMethod(permissionGranted = false, requestCode = 102)
    private void onPhoneStatePermissionDenied() {
        doOnCreate();
    }
}
