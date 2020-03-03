package com.xiaomi.smarthome.homeroom.initdevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;

public class InitDeviceRoomActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private InitDeviceRoomActivity f18287a;

    @UiThread
    public InitDeviceRoomActivity_ViewBinding(InitDeviceRoomActivity initDeviceRoomActivity) {
        this(initDeviceRoomActivity, initDeviceRoomActivity.getWindow().getDecorView());
    }

    @UiThread
    public InitDeviceRoomActivity_ViewBinding(InitDeviceRoomActivity initDeviceRoomActivity, View view) {
        this.f18287a = initDeviceRoomActivity;
        initDeviceRoomActivity.mTitleBar = Utils.findRequiredView(view, R.id.title_bar, "field 'mTitleBar'");
        initDeviceRoomActivity.mReturnBtn = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'");
        initDeviceRoomActivity.mTitleTv = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleTv'", TextView.class);
        initDeviceRoomActivity.mDeviceImg = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.device_img, "field 'mDeviceImg'", SimpleDraweeView.class);
        initDeviceRoomActivity.mSkipTv = Utils.findRequiredView(view, R.id.skip, "field 'mSkipTv'");
        initDeviceRoomActivity.mRoomFlowView = (CommonFlowGroup) Utils.findRequiredViewAsType(view, R.id.room_tag, "field 'mRoomFlowView'", CommonFlowGroup.class);
        initDeviceRoomActivity.mRecommendFlowView = (CommonFlowGroup) Utils.findRequiredViewAsType(view, R.id.recommend_room_tag, "field 'mRecommendFlowView'", CommonFlowGroup.class);
    }

    @CallSuper
    public void unbind() {
        InitDeviceRoomActivity initDeviceRoomActivity = this.f18287a;
        if (initDeviceRoomActivity != null) {
            this.f18287a = null;
            initDeviceRoomActivity.mTitleBar = null;
            initDeviceRoomActivity.mReturnBtn = null;
            initDeviceRoomActivity.mTitleTv = null;
            initDeviceRoomActivity.mDeviceImg = null;
            initDeviceRoomActivity.mSkipTv = null;
            initDeviceRoomActivity.mRoomFlowView = null;
            initDeviceRoomActivity.mRecommendFlowView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
