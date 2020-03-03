package com.xiaomi.smarthome.miio.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class LaboratoryActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private LaboratoryActivity f11766a;

    @UiThread
    public LaboratoryActivity_ViewBinding(LaboratoryActivity laboratoryActivity) {
        this(laboratoryActivity, laboratoryActivity.getWindow().getDecorView());
    }

    @UiThread
    public LaboratoryActivity_ViewBinding(LaboratoryActivity laboratoryActivity, View view) {
        this.f11766a = laboratoryActivity;
        laboratoryActivity.mXiaoAiRoomSetting = Utils.findRequiredView(view, R.id.xiaoai_room_setting, "field 'mXiaoAiRoomSetting'");
        laboratoryActivity.mEmptyView = Utils.findRequiredView(view, R.id.laboratory_empty, "field 'mEmptyView'");
        laboratoryActivity.mItemContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.item_container, "field 'mItemContainer'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        LaboratoryActivity laboratoryActivity = this.f11766a;
        if (laboratoryActivity != null) {
            this.f11766a = null;
            laboratoryActivity.mXiaoAiRoomSetting = null;
            laboratoryActivity.mEmptyView = null;
            laboratoryActivity.mItemContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
