package com.xiaomi.smarthome.framework.permission;

import android.content.Intent;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
final class PermissionRequestActivity$onCreate$1 implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PermissionRequestActivity f17116a;

    PermissionRequestActivity$onCreate$1(PermissionRequestActivity permissionRequestActivity) {
        this.f17116a = permissionRequestActivity;
    }

    public final void onClick(View view) {
        Intent intent = new Intent();
        Collection arrayList = new ArrayList();
        for (Object next : this.f17116a.e) {
            if (!((PermissionBean) next).a()) {
                arrayList.add(next);
            }
        }
        intent.putParcelableArrayListExtra(PermissionRequestActivity.ARG_KEYS_PERMISSION, new ArrayList((List) arrayList));
        this.f17116a.setResult(-1, intent);
        this.f17116a.finish();
    }
}
