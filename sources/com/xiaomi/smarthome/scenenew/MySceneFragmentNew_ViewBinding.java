package com.xiaomi.smarthome.scenenew;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MySceneFragmentNew_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MySceneFragmentNew f21758a;

    @UiThread
    public MySceneFragmentNew_ViewBinding(MySceneFragmentNew mySceneFragmentNew, View view) {
        this.f21758a = mySceneFragmentNew;
        mySceneFragmentNew.mPullRefreshLL = (PtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefreshLL'", PtrFrameLayout.class);
        mySceneFragmentNew.vFilterLayout = Utils.findRequiredView(view, R.id.layout_filter, "field 'vFilterLayout'");
        mySceneFragmentNew.tvHomeFilter = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_home_filter, "field 'tvHomeFilter'", TextView.class);
        mySceneFragmentNew.tvRoomTagFilter = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_room_tag_filter, "field 'tvRoomTagFilter'", TextView.class);
        mySceneFragmentNew.mEmptyView = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'mEmptyView'");
        mySceneFragmentNew.mSceneViewRV = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.scene_rv, "field 'mSceneViewRV'", RecyclerView.class);
        mySceneFragmentNew.mEmptyTV = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'mEmptyTV'", TextView.class);
        mySceneFragmentNew.mPlaceHolderView = Utils.findRequiredView(view, R.id.place_holder, "field 'mPlaceHolderView'");
    }

    @CallSuper
    public void unbind() {
        MySceneFragmentNew mySceneFragmentNew = this.f21758a;
        if (mySceneFragmentNew != null) {
            this.f21758a = null;
            mySceneFragmentNew.mPullRefreshLL = null;
            mySceneFragmentNew.vFilterLayout = null;
            mySceneFragmentNew.tvHomeFilter = null;
            mySceneFragmentNew.tvRoomTagFilter = null;
            mySceneFragmentNew.mEmptyView = null;
            mySceneFragmentNew.mSceneViewRV = null;
            mySceneFragmentNew.mEmptyTV = null;
            mySceneFragmentNew.mPlaceHolderView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
