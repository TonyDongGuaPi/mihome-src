package com.xiaomi.miot.store.api;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import com.facebook.react.modules.core.PermissionListener;

public interface IMiotStoreActivityDelegate {
    void onActivityResult(int i, int i2, Intent intent);

    boolean onBackPressed();

    void onCreate(Bundle bundle);

    void onDestroy();

    boolean onKeyUp(int i, KeyEvent keyEvent);

    boolean onNewIntent(Intent intent);

    void onPause();

    void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);

    void onResume();

    void requestPermissions(String[] strArr, int i, PermissionListener permissionListener);
}
