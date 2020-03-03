package com.xiaomi.smarthome.framework.permission;

import com.xiaomi.smarthome.framework.permission.SerialPermissionRequest;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
final class SerialPermissionRequest$FocusAwareDialog$onWindowFocusChanged$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialPermissionRequest.FocusAwareDialog f17125a;

    SerialPermissionRequest$FocusAwareDialog$onWindowFocusChanged$1(SerialPermissionRequest.FocusAwareDialog focusAwareDialog) {
        this.f17125a = focusAwareDialog;
    }

    public final void run() {
        try {
            if (this.f17125a.isShowing()) {
                this.f17125a.dismiss();
            }
        } catch (Exception unused) {
            LogUtil.a("SerialPermissionRequest", "error");
        }
    }
}
