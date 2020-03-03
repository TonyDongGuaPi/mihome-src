package com.xiaomi.youpin.yp_permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import com.yanzhenjie.yp_permission.AndPermission;
import com.yanzhenjie.yp_permission.Permission;
import com.yanzhenjie.yp_permission.Setting;
import java.lang.ref.WeakReference;
import java.util.List;

public final class YouPinPermissionSetting {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<Activity> f23874a;
    private Context b;
    /* access modifiers changed from: private */
    public PermissionCallback c;

    public YouPinPermissionSetting(Activity activity, PermissionCallback permissionCallback) {
        this.f23874a = new WeakReference<>(activity);
        this.b = activity;
        this.c = permissionCallback;
    }

    public void a(List<String> list) {
        Activity activity = (Activity) this.f23874a.get();
        if (activity != null && !activity.isFinishing()) {
            List<String> a2 = Permission.a(this.b, list);
            final Setting a3 = AndPermission.a((Context) activity).a().a();
            new MLAlertDialog.Builder(activity).setCancelable(false).setTitle((CharSequence) "需要允许授权").setMessage((CharSequence) "请在设置中允许小米有品访问" + TextUtils.join(",", a2)).setPositiveButton((CharSequence) "去设置", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    a3.b();
                    if (YouPinPermissionSetting.this.c != null) {
                        YouPinPermissionSetting.this.c.b();
                    }
                }
            }).setNegativeButton((CharSequence) "取消", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (YouPinPermissionSetting.this.c != null) {
                        YouPinPermissionSetting.this.c.b();
                    }
                }
            }).create().show();
        }
    }
}
