package com.xiaomi.smarthome.framework.permission;

import android.os.Handler;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0003H\u0016¨\u0006\u0005¸\u0006\u0000"}, d2 = {"com/xiaomi/smarthome/framework/permission/SerialPermissionRequest$requestDNDPermission$1$1$3", "Lcom/xiaomi/smarthome/library/common/dialog/MLAlertDialog$DismissCallBack;", "afterDismissCallBack", "", "beforeDismissCallBack", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$3 implements MLAlertDialog.DismissCallBack {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialPermissionRequest$requestDNDPermission$1 f17122a;
    final /* synthetic */ String b;

    public void beforeDismissCallBack() {
    }

    SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$3(SerialPermissionRequest$requestDNDPermission$1 serialPermissionRequest$requestDNDPermission$1, String str) {
        this.f17122a = serialPermissionRequest$requestDNDPermission$1;
        this.b = str;
    }

    public void afterDismissCallBack() {
        new Handler().postDelayed(new Runnable(this) {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$3 f17123a;

            {
                this.f17123a = r1;
            }

            public final void run() {
                this.f17123a.f17122a.c.onComplete();
            }
        }, 100);
    }
}
