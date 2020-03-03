package com.xiaomi.smarthome.framework.permission;

import android.widget.CompoundButton;
import com.xiaomi.smarthome.framework.permission.PermissionRequestActivity;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, k = 3, mv = {1, 1, 13})
final class PermissionRequestActivity$MyViewHolder$bind$1 implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ PermissionRequestActivity.MyViewHolder f17114a;
    final /* synthetic */ PermissionBean b;

    PermissionRequestActivity$MyViewHolder$bind$1(PermissionRequestActivity.MyViewHolder myViewHolder, PermissionBean permissionBean) {
        this.f17114a = myViewHolder;
        this.b = permissionBean;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.f17114a.f17113a.e.add(this.b);
        } else {
            this.f17114a.f17113a.e.remove(this.b);
        }
        PermissionRequestActivity.access$getSelectAllCheckBox$p(this.f17114a.f17113a).setChecked(PermissionRequestActivity.access$getPermissions$p(this.f17114a.f17113a).size() == this.f17114a.f17113a.e.size());
    }
}
