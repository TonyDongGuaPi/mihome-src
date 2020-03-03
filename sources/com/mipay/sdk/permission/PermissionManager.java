package com.mipay.sdk.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import com.mipay.sdk.app.Constants;

public class PermissionManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8172a = "PermissionManager";

    public interface OnPermissionCancelListener {
        void OnCancel();
    }

    private static int a(AppOpsManager appOpsManager, String str) {
        try {
            String[] strArr = (String[]) a.a(a.a(Class.forName("android.app.AppOpsManager"), "sOpPerms"), (Object) appOpsManager);
            for (int i = 0; i < strArr.length; i++) {
                if (TextUtils.equals(strArr[i], str)) {
                    return i;
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    private static boolean a(Context context, String str) {
        int i;
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        try {
            i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.uid;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            i = 0;
        }
        int a2 = a(appOpsManager, str);
        if (a2 < 0) {
            return true;
        }
        try {
            int intValue = ((Integer) a.a(a.a(Class.forName("android.app.AppOpsManager"), "checkOpNoThrow", (Class<?>[]) new Class[]{Integer.TYPE, Integer.TYPE, String.class}), (Object) appOpsManager, Integer.valueOf(a2), Integer.valueOf(i), context.getPackageName())).intValue();
            Log.d(f8172a, "checkOpNoThrow result: " + intValue);
            return intValue == 0 || intValue == 3;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public static boolean checkCameraPermission(Activity activity, OnPermissionCancelListener onPermissionCancelListener) {
        return checkPermission(activity, "android.permission.CAMERA", Constants.getString(Constants.ID_CAMERA_TEXT), onPermissionCancelListener);
    }

    public static boolean checkPermission(final Activity activity, String str, final String str2, final OnPermissionCancelListener onPermissionCancelListener) {
        if ((Build.VERSION.SDK_INT < 23 || activity.getApplicationInfo().targetSdkVersion < 23) ? a((Context) activity, str) : activity.checkSelfPermission(str) == 0) {
            return true;
        }
        new Handler(activity.getApplicationContext().getMainLooper()).post(new Runnable() {
            public void run() {
                new AlertDialog.Builder(activity).setTitle(str2).setNegativeButton(Constants.getString(Constants.ID_CANCEL_TEXT), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (onPermissionCancelListener != null) {
                            onPermissionCancelListener.OnCancel();
                        }
                    }
                }).setPositiveButton(Constants.getString(Constants.ID_OK_TEXT), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        try {
                            dialogInterface.dismiss();
                            Intent intent = new Intent();
                            intent.setAction("miui.intent.action.APP_PERM_EDITOR");
                            intent.putExtra("extra_pkgname", activity.getPackageName());
                            activity.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            Intent intent2 = new Intent();
                            intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            intent2.setData(Uri.parse("package:" + activity.getPackageName()));
                            activity.startActivity(intent2);
                        }
                    }
                }).create().show();
            }
        });
        return false;
    }
}
