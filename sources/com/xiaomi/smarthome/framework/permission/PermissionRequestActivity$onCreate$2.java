package com.xiaomi.smarthome.framework.permission;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
final class PermissionRequestActivity$onCreate$2 implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PermissionRequestActivity f17117a;

    PermissionRequestActivity$onCreate$2(PermissionRequestActivity permissionRequestActivity) {
        this.f17117a = permissionRequestActivity;
    }

    public final void onClick(View view) {
        PermissionRequestActivity.access$getSelectAllCheckBox$p(this.f17117a).toggle();
        if (PermissionRequestActivity.access$getSelectAllCheckBox$p(this.f17117a).isChecked()) {
            this.f17117a.e.clear();
            this.f17117a.e.addAll(PermissionRequestActivity.access$getPermissions$p(this.f17117a));
        } else {
            HashSet access$getCheckedPermissions$p = this.f17117a.e;
            Collection arrayList = new ArrayList();
            for (Object next : this.f17117a.e) {
                if (((PermissionBean) next).f()) {
                    arrayList.add(next);
                }
            }
            access$getCheckedPermissions$p.removeAll((List) arrayList);
        }
        RecyclerView.Adapter adapter = PermissionRequestActivity.access$getRecyclerView$p(this.f17117a).getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
