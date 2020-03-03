package com.xiaomi.smarthome.framework.page.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class MyDeviceSceneFragmentNew_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private MyDeviceSceneFragmentNew f17022a;

    @UiThread
    public MyDeviceSceneFragmentNew_ViewBinding(MyDeviceSceneFragmentNew myDeviceSceneFragmentNew, View view) {
        this.f17022a = myDeviceSceneFragmentNew;
        myDeviceSceneFragmentNew.mPullRefreshLL = (PtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefreshLL'", PtrFrameLayout.class);
        myDeviceSceneFragmentNew.mFilterTV = (TextView) Utils.findRequiredViewAsType(view, R.id.filter_tv, "field 'mFilterTV'", TextView.class);
        myDeviceSceneFragmentNew.mEmptyView = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'mEmptyView'");
        myDeviceSceneFragmentNew.mSceneViewRV = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.scene_rv, "field 'mSceneViewRV'", RecyclerView.class);
        myDeviceSceneFragmentNew.mEmptyTV = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'mEmptyTV'", TextView.class);
        myDeviceSceneFragmentNew.mPlaceHolderView = Utils.findRequiredView(view, R.id.place_holder, "field 'mPlaceHolderView'");
    }

    @CallSuper
    public void unbind() {
        MyDeviceSceneFragmentNew myDeviceSceneFragmentNew = this.f17022a;
        if (myDeviceSceneFragmentNew != null) {
            this.f17022a = null;
            myDeviceSceneFragmentNew.mPullRefreshLL = null;
            myDeviceSceneFragmentNew.mFilterTV = null;
            myDeviceSceneFragmentNew.mEmptyView = null;
            myDeviceSceneFragmentNew.mSceneViewRV = null;
            myDeviceSceneFragmentNew.mEmptyTV = null;
            myDeviceSceneFragmentNew.mPlaceHolderView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
