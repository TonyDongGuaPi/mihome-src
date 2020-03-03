package com.xiaomi.jr.appbase;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import com.miui.supportlite.app.AlertDialog;
import com.xiaomi.jr.permission.PermissionDialogDelegate;

public class MiFiPermissionDialogDelegate implements PermissionDialogDelegate {
    public void a(Activity activity, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        new AlertDialog.Builder(activity).a(false).a((CharSequence) str).b((CharSequence) str2).a((CharSequence) str3, onClickListener).b((CharSequence) str4, onClickListener2).a().show(((FragmentActivity) activity).getSupportFragmentManager(), "DefaultPermissionDialog");
    }
}
