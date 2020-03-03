package com.yanzhenjie.yp_permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import com.google.android.exoplayer2.C;
import com.yanzhenjie.yp_permission.overlay.setting.AlertWindowSettingPage;
import com.yanzhenjie.yp_permission.overlay.setting.OverlaySettingPage;
import com.yanzhenjie.yp_permission.runtime.setting.RuntimeSettingPage;
import com.yanzhenjie.yp_permission.source.ContextSource;

public final class PermissionActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2434a = "KEY_INPUT_OPERATION";
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final String g = "KEY_INPUT_PERMISSIONS";
    private static RequestListener h;

    public interface RequestListener {
        void a();
    }

    public static void requestPermission(Context context, String[] strArr, RequestListener requestListener) {
        h = requestListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(f2434a, 1);
        intent.putExtra(g, strArr);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    public static void permissionSetting(Context context, RequestListener requestListener) {
        h = requestListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(f2434a, 2);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    public static void requestInstall(Context context, RequestListener requestListener) {
        h = requestListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(f2434a, 3);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    public static void requestOverlay(Context context, RequestListener requestListener) {
        h = requestListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(f2434a, 4);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    public static void requestAlertWindow(Context context, RequestListener requestListener) {
        h = requestListener;
        Intent intent = new Intent(context, PermissionActivity.class);
        intent.putExtra(f2434a, 5);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        switch (intent.getIntExtra(f2434a, 0)) {
            case 1:
                String[] stringArrayExtra = intent.getStringArrayExtra(g);
                if (stringArrayExtra == null || h == null) {
                    finish();
                    return;
                } else {
                    requestPermissions(stringArrayExtra, 1);
                    return;
                }
            case 2:
                if (h != null) {
                    new RuntimeSettingPage(new ContextSource(this)).a(2);
                    return;
                } else {
                    finish();
                    return;
                }
            case 3:
                if (h != null) {
                    Intent intent2 = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
                    intent2.setData(Uri.fromParts("package", getPackageName(), (String) null));
                    startActivityForResult(intent2, 3);
                    return;
                }
                finish();
                return;
            case 4:
                if (h != null) {
                    new OverlaySettingPage(new ContextSource(this)).a(4);
                    return;
                } else {
                    finish();
                    return;
                }
            case 5:
                if (h != null) {
                    new AlertWindowSettingPage(new ContextSource(this)).a(5);
                    return;
                } else {
                    finish();
                    return;
                }
            default:
                throw new AssertionError("This should not be the case.");
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (h != null) {
            h.a();
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (h != null) {
            h.a();
        }
        finish();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void finish() {
        h = null;
        super.finish();
    }
}
