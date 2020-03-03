package com.xiaomi.smarthome.newui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.newui.widget.MainPageAppBarLayout;

public class RoomDetialsActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private RoomDetialsActivity f20363a;
    private View b;
    private View c;

    @UiThread
    public RoomDetialsActivity_ViewBinding(RoomDetialsActivity roomDetialsActivity) {
        this(roomDetialsActivity, roomDetialsActivity.getWindow().getDecorView());
    }

    @UiThread
    public RoomDetialsActivity_ViewBinding(final RoomDetialsActivity roomDetialsActivity, View view) {
        this.f20363a = roomDetialsActivity;
        roomDetialsActivity.mCoordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(view, R.id.view_container, "field 'mCoordinatorLayout'", CoordinatorLayout.class);
        roomDetialsActivity.mAppBarLayout = (MainPageAppBarLayout) Utils.findRequiredViewAsType(view, R.id.main_appbar, "field 'mAppBarLayout'", MainPageAppBarLayout.class);
        roomDetialsActivity.mAddDeviceView = Utils.findRequiredView(view, R.id.module_a_3_right_iv_setting_btn, "field 'mAddDeviceView'");
        roomDetialsActivity.mNewDot = Utils.findRequiredView(view, R.id.new_message_tag, "field 'mNewDot'");
        roomDetialsActivity.mToolbar = Utils.findRequiredView(view, R.id.title_bar, "field 'mToolbar'");
        roomDetialsActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTitle'", TextView.class);
        roomDetialsActivity.mRecyclerView = (DeviceRecyclerOrigin) Utils.findRequiredViewAsType(view, R.id.recyclerview, "field 'mRecyclerView'", DeviceRecyclerOrigin.class);
        roomDetialsActivity.mAppBarTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mAppBarTitle'", TextView.class);
        roomDetialsActivity.mRoomEnvList = (ListView) Utils.findRequiredViewAsType(view, R.id.lv_env, "field 'mRoomEnvList'", ListView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.back, "method 'onBackPressed'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                roomDetialsActivity.onBackPressed();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.home_name_arrow, "method 'onBackPressed'");
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                roomDetialsActivity.onBackPressed();
            }
        });
    }

    @CallSuper
    public void unbind() {
        RoomDetialsActivity roomDetialsActivity = this.f20363a;
        if (roomDetialsActivity != null) {
            this.f20363a = null;
            roomDetialsActivity.mCoordinatorLayout = null;
            roomDetialsActivity.mAppBarLayout = null;
            roomDetialsActivity.mAddDeviceView = null;
            roomDetialsActivity.mNewDot = null;
            roomDetialsActivity.mToolbar = null;
            roomDetialsActivity.mTitle = null;
            roomDetialsActivity.mRecyclerView = null;
            roomDetialsActivity.mAppBarTitle = null;
            roomDetialsActivity.mRoomEnvList = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
