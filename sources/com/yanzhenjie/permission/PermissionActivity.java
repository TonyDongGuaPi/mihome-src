package com.yanzhenjie.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import com.google.android.exoplayer2.C;

@RequiresApi(api = 23)
public final class PermissionActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2405a = "KEY_INPUT_PERMISSIONS";
    private static PermissionListener b;

    interface PermissionListener {
        void b(@NonNull String[] strArr);
    }

    public static void requestPermission(Context context, String[] strArr, PermissionListener permissionListener) {
        b = permissionListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra(f2405a, strArr);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a(this);
        if (Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        String[] stringArrayExtra = getIntent().getStringArrayExtra(f2405a);
        if (stringArrayExtra == null || b == null) {
            b = null;
            finish();
            return;
        }
        requestPermissions(stringArrayExtra, 1);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (b != null) {
            b.b(strArr);
            b = null;
        }
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private static void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 1024 | 256);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        }
    }
}
