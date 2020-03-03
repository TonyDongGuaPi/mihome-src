package com.xiaomi.smarthome.device.authorization.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;

public class DeviceAuthFragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceAuthFragment f15033a;

    @UiThread
    public DeviceAuthFragment_ViewBinding(DeviceAuthFragment deviceAuthFragment, View view) {
        this.f15033a = deviceAuthFragment;
        deviceAuthFragment.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.device_list, "field 'mRecyclerView'", RecyclerView.class);
        deviceAuthFragment.mSelectAllBtn = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.btn_select_all, "field 'mSelectAllBtn'", SwitchButton.class);
        deviceAuthFragment.emptyView = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'emptyView'");
        deviceAuthFragment.menuLayout = Utils.findRequiredView(view, R.id.menu_layout, "field 'menuLayout'");
        deviceAuthFragment.mAuthDeviceView = Utils.findRequiredView(view, R.id.rl_auth_device, "field 'mAuthDeviceView'");
        deviceAuthFragment.mAuthHomeRoomView = Utils.findRequiredView(view, R.id.rl_auth_home_room, "field 'mAuthHomeRoomView'");
        deviceAuthFragment.mDevIndicator = (ImageView) Utils.findRequiredViewAsType(view, R.id.device_select_indicator, "field 'mDevIndicator'", ImageView.class);
        deviceAuthFragment.mRoomIndicator = (ImageView) Utils.findRequiredViewAsType(view, R.id.home_room_select_indicator, "field 'mRoomIndicator'", ImageView.class);
        deviceAuthFragment.mTvDevSelect = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_device_select, "field 'mTvDevSelect'", TextView.class);
        deviceAuthFragment.mTvRoomSelect = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_room_select, "field 'mTvRoomSelect'", TextView.class);
        deviceAuthFragment.mRlHomeSelect = Utils.findRequiredView(view, R.id.rl_home_select, "field 'mRlHomeSelect'");
        deviceAuthFragment.mTvHomeName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_home_name, "field 'mTvHomeName'", TextView.class);
        deviceAuthFragment.mAutoSelectTv = (TextView) Utils.findRequiredViewAsType(view, R.id.auth_select_tv, "field 'mAutoSelectTv'", TextView.class);
        deviceAuthFragment.mSceneAuth = Utils.findRequiredView(view, R.id.rl_scene_auth, "field 'mSceneAuth'");
        deviceAuthFragment.mAppBarLayout = Utils.findRequiredView(view, R.id.appBarLayout, "field 'mAppBarLayout'");
    }

    @CallSuper
    public void unbind() {
        DeviceAuthFragment deviceAuthFragment = this.f15033a;
        if (deviceAuthFragment != null) {
            this.f15033a = null;
            deviceAuthFragment.mRecyclerView = null;
            deviceAuthFragment.mSelectAllBtn = null;
            deviceAuthFragment.emptyView = null;
            deviceAuthFragment.menuLayout = null;
            deviceAuthFragment.mAuthDeviceView = null;
            deviceAuthFragment.mAuthHomeRoomView = null;
            deviceAuthFragment.mDevIndicator = null;
            deviceAuthFragment.mRoomIndicator = null;
            deviceAuthFragment.mTvDevSelect = null;
            deviceAuthFragment.mTvRoomSelect = null;
            deviceAuthFragment.mRlHomeSelect = null;
            deviceAuthFragment.mTvHomeName = null;
            deviceAuthFragment.mAutoSelectTv = null;
            deviceAuthFragment.mSceneAuth = null;
            deviceAuthFragment.mAppBarLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
