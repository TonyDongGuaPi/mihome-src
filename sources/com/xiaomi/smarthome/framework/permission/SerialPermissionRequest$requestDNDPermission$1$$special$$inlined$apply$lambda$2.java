package com.xiaomi.smarthome.framework.permission;

import android.content.DialogInterface;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "v", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "w", "", "onClick", "com/xiaomi/smarthome/framework/permission/SerialPermissionRequest$requestDNDPermission$1$1$2"}, k = 3, mv = {1, 1, 13})
final class SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$2 implements DialogInterface.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialPermissionRequest$requestDNDPermission$1 f17121a;
    final /* synthetic */ String b;

    SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$2(SerialPermissionRequest$requestDNDPermission$1 serialPermissionRequest$requestDNDPermission$1, String str) {
        this.f17121a = serialPermissionRequest$requestDNDPermission$1;
        this.b = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        DialogInterface.OnClickListener a2 = this.f17121a.f17127a.a();
        if (a2 != null) {
            a2.onClick(dialogInterface, i);
        }
    }
}
