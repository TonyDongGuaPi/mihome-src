package com.xiaomi.shop2.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.mishopsdk.BuildConfig;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.util.AndroidUtil;
import com.xiaomi.mishopsdk.util.PreferenceUtil;

@Deprecated
public class PermissionUtil {
    private static final String PREF_VER_CHECK_READ_PHOHE_PERMISSION = "pref_ver_check_read_phone_permission";
    private static final String SDK_FLAVOR_SYSTEM = "systemApp";
    private static final String TAG = "PermissionUtil";
    private static final String sAppName = AndroidUtil.getAppName();

    public static boolean checkPermission(Activity activity, String str, int i) {
        if (ContextCompat.checkSelfPermission(ShopApp.instance, str) == 0) {
            return true;
        }
        ActivityCompat.requestPermissions(activity, new String[]{str}, i);
        return false;
    }

    public static boolean checkPermissions(Activity activity, String[] strArr, int i) {
        if (permissionGranted(strArr)) {
            return true;
        }
        ActivityCompat.requestPermissions(activity, strArr, i);
        return false;
    }

    public static boolean checkPermission(Fragment fragment, String str, int i) {
        if (ContextCompat.checkSelfPermission(ShopApp.instance, str) == 0) {
            return true;
        }
        fragment.requestPermissions(new String[]{str}, i);
        return false;
    }

    public static boolean checkPermissions(Fragment fragment, String[] strArr, int i) {
        if (permissionGranted(strArr)) {
            return true;
        }
        fragment.requestPermissions(strArr, i);
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.setAccessible(true);
        r8.invoke(r11, new java.lang.Object[0]);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0045, code lost:
        r2 = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean onRequestPermissionsResult(java.lang.Object r11, int r12, @android.support.annotation.NonNull int[] r13) {
        /*
            java.lang.Class r0 = r11.getClass()
            r1 = 0
            r2 = 0
        L_0x0006:
            r3 = 1
            java.lang.Class<android.app.Activity> r4 = android.app.Activity.class
            if (r0 == r4) goto L_0x005a
            java.lang.Class<android.support.v4.app.Fragment> r4 = android.support.v4.app.Fragment.class
            if (r0 == r4) goto L_0x005a
            if (r2 != 0) goto L_0x005a
            java.lang.reflect.Method[] r4 = r0.getDeclaredMethods()     // Catch:{ Exception -> 0x0052 }
            boolean r5 = permissionGranted((int[]) r13)     // Catch:{ Exception -> 0x0052 }
            int r6 = r4.length     // Catch:{ Exception -> 0x0052 }
            r7 = 0
        L_0x001b:
            if (r7 >= r6) goto L_0x004d
            r8 = r4[r7]     // Catch:{ Exception -> 0x0052 }
            java.lang.Class<com.xiaomi.shop2.mishop2ann.PermissionMethod> r9 = com.xiaomi.shop2.mishop2ann.PermissionMethod.class
            boolean r9 = r8.isAnnotationPresent(r9)     // Catch:{ Exception -> 0x0052 }
            if (r9 != 0) goto L_0x0028
            goto L_0x004a
        L_0x0028:
            java.lang.Class<com.xiaomi.shop2.mishop2ann.PermissionMethod> r9 = com.xiaomi.shop2.mishop2ann.PermissionMethod.class
            java.lang.annotation.Annotation r9 = r8.getAnnotation(r9)     // Catch:{ Exception -> 0x0052 }
            com.xiaomi.shop2.mishop2ann.PermissionMethod r9 = (com.xiaomi.shop2.mishop2ann.PermissionMethod) r9     // Catch:{ Exception -> 0x0052 }
            int r10 = r9.requestCode()     // Catch:{ Exception -> 0x0052 }
            if (r12 != r10) goto L_0x004a
            boolean r9 = r9.permissionGranted()     // Catch:{ Exception -> 0x0052 }
            if (r5 == r9) goto L_0x003d
            goto L_0x004a
        L_0x003d:
            r8.setAccessible(r3)     // Catch:{ Exception -> 0x0047 }
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0047 }
            r8.invoke(r11, r2)     // Catch:{ Exception -> 0x0047 }
            r2 = 1
            goto L_0x004d
        L_0x0047:
            r11 = move-exception
            r2 = 1
            goto L_0x0053
        L_0x004a:
            int r7 = r7 + 1
            goto L_0x001b
        L_0x004d:
            java.lang.Class r0 = r0.getSuperclass()     // Catch:{ Exception -> 0x0052 }
            goto L_0x0006
        L_0x0052:
            r11 = move-exception
        L_0x0053:
            java.lang.String r12 = "PermissionUtil"
            java.lang.String r13 = "onRequestPermissionsResult failed."
            com.xiaomi.mishopsdk.util.Log.e((java.lang.String) r12, (java.lang.String) r13, (java.lang.Object) r11)
        L_0x005a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.util.PermissionUtil.onRequestPermissionsResult(java.lang.Object, int, int[]):boolean");
    }

    public static boolean checkReadPhoneStatePermission(Activity activity, int i) {
        if (PreferenceUtil.getIntPref(ShopApp.instance, PREF_VER_CHECK_READ_PHOHE_PERMISSION, 0) == Device.MISHOP_SDK_VERSION) {
            return true;
        }
        boolean checkPermission = checkPermission(activity, "android.permission.READ_PHONE_STATE", i);
        PreferenceUtil.setIntPref(ShopApp.instance, PREF_VER_CHECK_READ_PHOHE_PERMISSION, Device.MISHOP_SDK_VERSION);
        return checkPermission;
    }

    public static void onGeneralPermissionFailed(Activity activity, boolean z, String str, String str2) {
        if (activity != null && !activity.isFinishing()) {
            if (!SDK_FLAVOR_SYSTEM.equals(BuildConfig.FLAVOR)) {
                createPermissionDeniedDialog(activity, z, str, str2).show();
            } else if (z && activity != null && !activity.isFinishing()) {
                activity.finish();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void startPermissionSettingActivity(android.app.Activity r4) {
        /*
            boolean r0 = com.xiaomi.shop2.util.Device.IS_MIUI
            if (r0 == 0) goto L_0x002d
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "miui.intent.action.APP_PERM_EDITOR"
            r0.<init>(r1)
            java.lang.String r1 = "extra_pkgname"
            android.app.Application r2 = com.xiaomi.mishopsdk.ShopApp.instance
            java.lang.String r2 = r2.getPackageName()
            r0.putExtra(r1, r2)
            android.app.Application r1 = com.xiaomi.mishopsdk.ShopApp.instance
            android.content.pm.PackageManager r1 = r1.getPackageManager()
            r2 = 65536(0x10000, float:9.18355E-41)
            java.util.List r1 = r1.queryIntentActivities(r0, r2)
            int r1 = r1.size()
            if (r1 <= 0) goto L_0x002d
            r4.startActivity(r0)
            r0 = 1
            goto L_0x002e
        L_0x002d:
            r0 = 0
        L_0x002e:
            if (r0 != 0) goto L_0x004a
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "android.settings.APPLICATION_DETAILS_SETTINGS"
            r0.<init>(r1)
            java.lang.String r1 = "package"
            android.app.Application r2 = com.xiaomi.mishopsdk.ShopApp.instance
            java.lang.String r2 = r2.getPackageName()
            r3 = 0
            android.net.Uri r1 = android.net.Uri.fromParts(r1, r2, r3)
            r0.setData(r1)
            r4.startActivity(r0)
        L_0x004a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.shop2.util.PermissionUtil.startPermissionSettingActivity(android.app.Activity):void");
    }

    private static AlertDialog createPermissionDeniedDialog(final Activity activity, final boolean z, String str, String str2) {
        View inflate = LayoutInflater.from(ShopApp.instance).inflate(R.layout.permission_denied_dialog, (ViewGroup) null, false);
        ((TextView) inflate.findViewById(R.id.tip)).setText(ShopApp.instance.getString(R.string.permission_tip, new Object[]{sAppName, str, str2}));
        final AlertDialog create = new AlertDialog.Builder(activity, 16974394).setView(inflate).create();
        inflate.findViewById(R.id.action_dialog_permission_cancel).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (activity != null && !activity.isFinishing()) {
                    create.dismiss();
                    if (z) {
                        activity.finish();
                    }
                }
            }
        });
        inflate.findViewById(R.id.action_dialog_permission_jump).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (activity != null && !activity.isFinishing()) {
                    PermissionUtil.startPermissionSettingActivity(activity);
                    create.dismiss();
                    if (z) {
                        activity.finish();
                    }
                }
            }
        });
        create.setCancelable(false);
        return create;
    }

    private static boolean permissionGranted(@NonNull String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return false;
        }
        for (String checkSelfPermission : strArr) {
            if (ContextCompat.checkSelfPermission(ShopApp.instance, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    private static boolean permissionGranted(@NonNull int[] iArr) {
        if (iArr == null || iArr.length == 0) {
            return false;
        }
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }
}
