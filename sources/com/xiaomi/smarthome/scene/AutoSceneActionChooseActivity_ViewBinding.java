package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class AutoSceneActionChooseActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private AutoSceneActionChooseActivity f21191a;

    @UiThread
    public AutoSceneActionChooseActivity_ViewBinding(AutoSceneActionChooseActivity autoSceneActionChooseActivity) {
        this(autoSceneActionChooseActivity, autoSceneActionChooseActivity.getWindow().getDecorView());
    }

    @UiThread
    public AutoSceneActionChooseActivity_ViewBinding(AutoSceneActionChooseActivity autoSceneActionChooseActivity, View view) {
        this.f21191a = autoSceneActionChooseActivity;
        autoSceneActionChooseActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        autoSceneActionChooseActivity.mPullRefreshLL = (PtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefreshLL'", PtrFrameLayout.class);
        autoSceneActionChooseActivity.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.scene_item_view, "field 'mRecyclerView'", RecyclerView.class);
        autoSceneActionChooseActivity.mReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'", ImageView.class);
        autoSceneActionChooseActivity.mNoSceneView = Utils.findRequiredView(view, R.id.no_scene_item_view, "field 'mNoSceneView'");
    }

    @CallSuper
    public void unbind() {
        AutoSceneActionChooseActivity autoSceneActionChooseActivity = this.f21191a;
        if (autoSceneActionChooseActivity != null) {
            this.f21191a = null;
            autoSceneActionChooseActivity.mTitle = null;
            autoSceneActionChooseActivity.mPullRefreshLL = null;
            autoSceneActionChooseActivity.mRecyclerView = null;
            autoSceneActionChooseActivity.mReturnBtn = null;
            autoSceneActionChooseActivity.mNoSceneView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
