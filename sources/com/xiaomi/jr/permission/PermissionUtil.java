package com.xiaomi.jr.permission;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationManagerCompat;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.MiuiClient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10995a = "PermissionUtil";
    private static HashMap<String, String> b = new HashMap<>();
    private static Field c;
    private static HashMap<String, Integer> d = new HashMap<>();
    private static final String e = Build.MANUFACTURER.toLowerCase();

    public static boolean a(Context context) {
        return Build.VERSION.SDK_INT >= 23 && context.getApplicationInfo().targetSdkVersion >= 23;
    }

    public static boolean a(Context context, String str) {
        int i = context.getApplicationInfo().targetSdkVersion;
        if (Build.VERSION.SDK_INT < 23 || i < 23) {
            return b(context, str);
        }
        return context.checkSelfPermission(str) == 0;
    }

    public static boolean b(Context context, String str) {
        int i;
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.uid;
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            i = 0;
        }
        int a2 = a(appOpsManager, str);
        if (a2 < 0) {
            return true;
        }
        try {
            int intValue = ((Integer) ReflectUtil.a(ReflectUtil.a(Class.forName("android.app.AppOpsManager"), "checkOpNoThrow", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, String.class}), (Object) appOpsManager, Integer.valueOf(a2), Integer.valueOf(i), context.getPackageName())).intValue();
            MifiLog.b(f10995a, "checkOpNoThrow result: " + intValue);
            if (intValue == 0 || intValue == 3) {
                return true;
            }
            return false;
        } catch (Exception e3) {
            e3.printStackTrace();
            return true;
        }
    }

    static {
        b.put("com.android.launcher.permission.INSTALL_SHORTCUT", "OP_INSTALL_SHORTCUT");
    }

    public static boolean a(String str) {
        return b.containsKey(str);
    }

    private static int a(AppOpsManager appOpsManager, String str) {
        try {
            if (c == null) {
                c = ReflectUtil.a(Class.forName("android.app.AppOpsManager"), "sOpPerms");
            }
            String[] strArr = (String[]) ReflectUtil.a(c, (Object) appOpsManager);
            for (int i = 0; i < strArr.length; i++) {
                if (TextUtils.equals(strArr[i], str)) {
                    return i;
                }
            }
            if (!MiuiClient.a() || !b.containsKey(str)) {
                return -1;
            }
            if (!d.containsKey(str)) {
                d.put(str, Integer.valueOf(((Integer) ReflectUtil.a(ReflectUtil.a(Class.forName("android.app.AppOpsManager"), b.get(str)), (Object) appOpsManager)).intValue()));
            }
            return d.get(str).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static Map<String, CharSequence[]> a(Context context, List<String> list) {
        HashMap hashMap = new HashMap();
        PackageManager packageManager = context.getPackageManager();
        for (String next : list) {
            try {
                PermissionInfo permissionInfo = packageManager.getPermissionInfo(next, 128);
                if (TextUtils.isEmpty(permissionInfo.group)) {
                    hashMap.put(next, new CharSequence[]{permissionInfo.loadLabel(packageManager), permissionInfo.loadDescription(packageManager)});
                } else if (!hashMap.containsKey(permissionInfo.group)) {
                    hashMap.put(permissionInfo.group, new CharSequence[2]);
                }
            } catch (PackageManager.NameNotFoundException e2) {
                MifiLog.e(f10995a, "get permission info failed for " + next, e2);
            }
        }
        for (String str : hashMap.keySet()) {
            try {
                PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(str, 128);
                ((CharSequence[]) hashMap.get(str))[0] = permissionGroupInfo.loadLabel(packageManager);
                ((CharSequence[]) hashMap.get(str))[1] = permissionGroupInfo.loadDescription(packageManager);
            } catch (PackageManager.NameNotFoundException e3) {
                MifiLog.e(f10995a, "get permission group info failed for " + str, e3);
            }
        }
        return hashMap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d2 A[SYNTHETIC, Splitter:B:30:0x00d2] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.app.Activity r5, int r6) {
        /*
            java.lang.String r0 = e
            java.lang.String r1 = "xiaomi"
            boolean r0 = r0.contains(r1)
            r1 = 0
            if (r0 == 0) goto L_0x002b
            java.lang.String r0 = a()
            if (r0 == 0) goto L_0x00cf
            java.lang.String r2 = "_global"
            boolean r0 = r0.contains(r2)
            if (r0 != 0) goto L_0x00cf
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "miui.intent.action.APP_PERM_EDITOR"
            r0.<init>(r2)
            java.lang.String r2 = "extra_pkgname"
            java.lang.String r3 = r5.getPackageName()
            r0.putExtra(r2, r3)
            goto L_0x00d0
        L_0x002b:
            java.lang.String r0 = e
            java.lang.String r2 = "huawei"
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x004e
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 23
            if (r0 >= r2) goto L_0x00cf
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            android.content.ComponentName r2 = new android.content.ComponentName
            java.lang.String r3 = "com.huawei.systemmanager"
            java.lang.String r4 = "com.huawei.permissionmanager.ui.MainActivity"
            r2.<init>(r3, r4)
            r0.setComponent(r2)
            goto L_0x00d0
        L_0x004e:
            java.lang.String r0 = e
            java.lang.String r2 = "oppo"
            boolean r0 = r0.contains(r2)
            if (r0 == 0) goto L_0x005a
            goto L_0x00cf
        L_0x005a:
            java.lang.String r0 = e
            java.lang.String r2 = "vivo"
            boolean r0 = r0.contains(r2)
            r2 = 25
            if (r0 == 0) goto L_0x0092
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r3 = "packagename"
            java.lang.String r4 = r5.getPackageName()
            r0.putExtra(r3, r4)
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r2) goto L_0x0085
            android.content.ComponentName r2 = new android.content.ComponentName
            java.lang.String r3 = "com.vivo.permissionmanager"
            java.lang.String r4 = "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity"
            r2.<init>(r3, r4)
            r0.setComponent(r2)
            goto L_0x00d0
        L_0x0085:
            android.content.ComponentName r2 = new android.content.ComponentName
            java.lang.String r3 = "com.iqoo.secure"
            java.lang.String r4 = "com.iqoo.secure.safeguard.SoftPermissionDetailActivity"
            r2.<init>(r3, r4)
            r0.setComponent(r2)
            goto L_0x00d0
        L_0x0092:
            java.lang.String r0 = e
            java.lang.String r3 = "samsung"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x009d
            goto L_0x00cf
        L_0x009d:
            java.lang.String r0 = e
            java.lang.String r3 = "meizu"
            boolean r0 = r0.contains(r3)
            if (r0 == 0) goto L_0x00c8
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 >= r2) goto L_0x00cf
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "com.meizu.safe.security.SHOW_APPSEC"
            r0.<init>(r2)
            java.lang.String r2 = "packageName"
            java.lang.String r3 = r5.getPackageName()
            r0.putExtra(r2, r3)
            android.content.ComponentName r2 = new android.content.ComponentName
            java.lang.String r3 = "com.meizu.safe"
            java.lang.String r4 = "com.meizu.safe.security.AppSecActivity"
            r2.<init>(r3, r4)
            r0.setComponent(r2)
            goto L_0x00d0
        L_0x00c8:
            java.lang.String r0 = e
            java.lang.String r2 = "smartisan"
            r0.contains(r2)
        L_0x00cf:
            r0 = r1
        L_0x00d0:
            if (r0 == 0) goto L_0x00d7
            r5.startActivityForResult(r0, r6)     // Catch:{ ActivityNotFoundException | SecurityException -> 0x00d6 }
            goto L_0x00d7
        L_0x00d6:
            r0 = r1
        L_0x00d7:
            if (r0 != 0) goto L_0x00f0
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r2 = "android.settings.APPLICATION_DETAILS_SETTINGS"
            r0.<init>(r2)
            java.lang.String r2 = "package"
            java.lang.String r3 = r5.getPackageName()
            android.net.Uri r1 = android.net.Uri.fromParts(r2, r3, r1)
            r0.setData(r1)
            r5.startActivityForResult(r0, r6)
        L_0x00f0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.jr.permission.PermissionUtil.a(android.app.Activity, int):void");
    }

    private static String a() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod("get", new Class[]{String.class}).invoke(cls, new Object[]{"ro.product.mod_device"});
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean b(Context context) {
        return NotificationManagerCompat.from(context).areNotificationsEnabled();
    }

    public static void c(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= 26) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("android.provider.extra.APP_PACKAGE", context.getPackageName());
        } else if (Build.VERSION.SDK_INT >= 21) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), (String) null));
        }
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }
}
