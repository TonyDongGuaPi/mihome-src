package com.xiaomi.smarthome.homeroom;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class HomeRoomDeviceMoveActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private HomeRoomDeviceMoveActivity f18066a;

    @UiThread
    public HomeRoomDeviceMoveActivity_ViewBinding(HomeRoomDeviceMoveActivity homeRoomDeviceMoveActivity) {
        this(homeRoomDeviceMoveActivity, homeRoomDeviceMoveActivity.getWindow().getDecorView());
    }

    @UiThread
    public HomeRoomDeviceMoveActivity_ViewBinding(HomeRoomDeviceMoveActivity homeRoomDeviceMoveActivity, View view) {
        this.f18066a = homeRoomDeviceMoveActivity;
        homeRoomDeviceMoveActivity.mBack = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBack'", ImageView.class);
        homeRoomDeviceMoveActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        homeRoomDeviceMoveActivity.mMenuIcom = (ImageView) Utils.findRequiredViewAsType(view, R.id.home_change_icon, "field 'mMenuIcom'", ImageView.class);
        homeRoomDeviceMoveActivity.mGroupTitle = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.title_group, "field 'mGroupTitle'", LinearLayout.class);
        homeRoomDeviceMoveActivity.mTitleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mTitleBar'", FrameLayout.class);
        homeRoomDeviceMoveActivity.mMaskView = Utils.findRequiredView(view, R.id.mask, "field 'mMaskView'");
        homeRoomDeviceMoveActivity.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.device_move_list_view, "field 'mListView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        HomeRoomDeviceMoveActivity homeRoomDeviceMoveActivity = this.f18066a;
        if (homeRoomDeviceMoveActivity != null) {
            this.f18066a = null;
            homeRoomDeviceMoveActivity.mBack = null;
            homeRoomDeviceMoveActivity.mTitle = null;
            homeRoomDeviceMoveActivity.mMenuIcom = null;
            homeRoomDeviceMoveActivity.mGroupTitle = null;
            homeRoomDeviceMoveActivity.mTitleBar = null;
            homeRoomDeviceMoveActivity.mMaskView = null;
            homeRoomDeviceMoveActivity.mListView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
