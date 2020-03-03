package com.xiaomi.smarthome.framework.permission;

import android.app.Activity;
import android.content.DialogInterface;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.permission.SerialPermissionRequest;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.CompletableSubject;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
final class SerialPermissionRequest$requestDNDPermission$1<T> implements Consumer<Disposable> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ SerialPermissionRequest f17127a;
    final /* synthetic */ PermissionBean b;
    final /* synthetic */ CompletableSubject c;
    final /* synthetic */ Activity d;

    SerialPermissionRequest$requestDNDPermission$1(SerialPermissionRequest serialPermissionRequest, PermissionBean permissionBean, CompletableSubject completableSubject, Activity activity) {
        this.f17127a = serialPermissionRequest;
        this.b = permissionBean;
        this.c = completableSubject;
        this.d = activity;
    }

    /* renamed from: a */
    public final void accept(Disposable disposable) {
        String str;
        if (this.b.b()) {
            this.c.onComplete();
            return;
        }
        try {
            StringCompanionObject stringCompanionObject = StringCompanionObject.f2835a;
            String string = this.d.getString(R.string.permission_tips_denied_msg);
            Intrinsics.b(string, "activity.getString(R.str…rmission_tips_denied_msg)");
            Object[] objArr = {this.b.d()};
            str = String.format(string, Arrays.copyOf(objArr, objArr.length));
            Intrinsics.b(str, "java.lang.String.format(format, *args)");
        } catch (Exception unused) {
            str = this.b.e();
        }
        LogUtil.a("SerialPermissionRequest", "dialog：" + this.b.d());
        SerialPermissionRequest.FocusAwareDialog focusAwareDialog = new SerialPermissionRequest.FocusAwareDialog(this.b, this.d);
        focusAwareDialog.setTitle(R.string.tips);
        focusAwareDialog.setMessage(str);
        focusAwareDialog.setCancelable(false);
        focusAwareDialog.setButton(-1, (CharSequence) this.d.getString(R.string.set_now), (DialogInterface.OnClickListener) new SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$1(this, str));
        focusAwareDialog.setButton(-2, (CharSequence) this.d.getString(R.string.cancel), (DialogInterface.OnClickListener) new SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$2(this, str));
        focusAwareDialog.setDismissCallBack(new SerialPermissionRequest$requestDNDPermission$1$$special$$inlined$apply$lambda$3(this, str));
        focusAwareDialog.show();
    }
}
