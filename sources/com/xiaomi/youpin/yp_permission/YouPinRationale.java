package com.xiaomi.youpin.yp_permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import com.yanzhenjie.yp_permission.Permission;
import com.yanzhenjie.yp_permission.Rationale;
import com.yanzhenjie.yp_permission.RequestExecutor;
import java.util.List;

public class YouPinRationale implements Rationale<List<String>> {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public PermissionCallback f23877a;

    public YouPinRationale(PermissionCallback permissionCallback) {
        this.f23877a = permissionCallback;
    }

    public void a(Context context, List<String> list, final RequestExecutor requestExecutor) {
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity != null && !activity.isFinishing()) {
            List<String> a2 = Permission.a(context, list);
            MLAlertDialog.Builder title = new MLAlertDialog.Builder(activity).setCancelable(false).setTitle((CharSequence) "需要授权");
            title.setMessage((CharSequence) "为正常使用，需要访问" + TextUtils.join(",", a2)).setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    requestExecutor.b();
                }
            }).setNegativeButton(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    requestExecutor.c();
                    if (YouPinRationale.this.f23877a != null) {
                        YouPinRationale.this.f23877a.a(false);
                    }
                }
            }).create().show();
        } else if (this.f23877a != null) {
            this.f23877a.a(false);
        }
    }
}
