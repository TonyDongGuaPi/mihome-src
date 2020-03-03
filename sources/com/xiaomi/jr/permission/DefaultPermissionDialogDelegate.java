package com.xiaomi.jr.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class DefaultPermissionDialogDelegate implements PermissionDialogDelegate {
    public void a(Activity activity, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        new AlertDialog.Builder(activity).setCancelable(false).setTitle(str).setMessage(str2).setPositiveButton(str3, onClickListener).setNegativeButton(str4, onClickListener2).create().show();
    }
}
