package com.mi.util.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import com.mi.util.Device;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PermissionUtil {

    /* renamed from: a  reason: collision with root package name */
    private static int f1353a = 528;
    private static int b = 5028;
    private static HashMap<Integer, PermissionCallback> c = new HashMap<>();

    public static void a(Activity activity, PermissionCallback permissionCallback, @NonNull String... strArr) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> b2 = b(activity, strArr);
            if (b2.size() != 0) {
                ActivityCompat.requestPermissions(activity, (String[]) b2.toArray(new String[b2.size()]), f1353a);
                c.put(Integer.valueOf(f1353a), permissionCallback);
                f1353a++;
            } else if (permissionCallback != null) {
                permissionCallback.onResult();
                permissionCallback.onGranted();
            }
        } else if (permissionCallback != null) {
            permissionCallback.onResult();
            permissionCallback.onGranted();
        }
    }

    public static void a(Activity activity, PermissionCallback permissionCallback, @NonNull String[]... strArr) {
        ArrayList arrayList = new ArrayList();
        for (String[] asList : strArr) {
            arrayList.addAll(Arrays.asList(asList));
        }
        a(activity, permissionCallback, (String[]) arrayList.toArray(new String[0]));
    }

    public static void a(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        PermissionCallback permissionCallback = c.get(Integer.valueOf(i));
        if (permissionCallback != null) {
            permissionCallback.onResult();
            if (a(iArr)) {
                permissionCallback.onGranted();
            } else {
                permissionCallback.onDenied();
            }
            c.remove(Integer.valueOf(i));
        }
    }

    public static void a(Activity activity, int i) {
        PermissionCallback permissionCallback;
        if (i == b && (permissionCallback = c.get(Integer.valueOf(i))) != null) {
            permissionCallback.onResult();
            if (b(activity)) {
                permissionCallback.onGranted();
            } else {
                permissionCallback.onDenied();
            }
        }
    }

    public static void a(Activity activity, PermissionCallback permissionCallback) {
        if (!b(activity)) {
            b(activity, permissionCallback);
        } else if (permissionCallback != null) {
            permissionCallback.onResult();
            permissionCallback.onGranted();
        }
    }

    public static void b(Activity activity, PermissionCallback permissionCallback) {
        activity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + activity.getPackageName())), b);
        if (permissionCallback != null) {
            c.put(Integer.valueOf(b), permissionCallback);
        }
    }

    public static void a(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + Device.q));
        context.startActivity(intent);
    }

    public static boolean b(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return Settings.canDrawOverlays(context);
    }

    public static boolean a(Context context, @NonNull String... strArr) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        for (String checkSelfPermission : strArr) {
            if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                return false;
            }
        }
        return true;
    }

    public static void a(final Context context, String str) {
        b(context, str, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PermissionUtil.a(context);
            }
        }, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
    }

    public static void a(final Activity activity, String str, final PermissionCallback permissionCallback) {
        b(activity, str, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                PermissionUtil.b(activity, permissionCallback);
            }
        }, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (permissionCallback != null) {
                    permissionCallback.onDenied();
                }
            }
        });
    }

    private static void b(Context context, String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        if (c(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage((CharSequence) str);
            builder.setCancelable(false);
            builder.setPositiveButton(17039370, onClickListener);
            builder.setNegativeButton(17039369, onClickListener2);
            builder.create().show();
        }
    }

    public static void a(Context context, String str, DialogInterface.OnClickListener onClickListener, DialogInterface.OnClickListener onClickListener2) {
        if (c(context)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage((CharSequence) str);
            builder.setCancelable(false);
            builder.setPositiveButton(17039370, onClickListener);
            builder.setNegativeButton(17039360, onClickListener2);
            builder.create().show();
        }
    }

    public static boolean a(Activity activity, @NonNull String... strArr) {
        for (String shouldShowRequestPermissionRationale : b(activity, strArr)) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, shouldShowRequestPermissionRationale)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> b(Activity activity, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(activity, str) != 0) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    private static boolean a(int[] iArr) {
        for (int i : iArr) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean c(Context context) {
        if (context == null || !(context instanceof Activity)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17) {
            return !((Activity) context).isFinishing();
        }
        Activity activity = (Activity) context;
        if (activity.isFinishing() || activity.isDestroyed()) {
            return false;
        }
        return true;
    }
}
