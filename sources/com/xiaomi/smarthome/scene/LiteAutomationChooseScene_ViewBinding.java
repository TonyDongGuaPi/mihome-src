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

public class LiteAutomationChooseScene_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private LiteAutomationChooseScene f21289a;

    @UiThread
    public LiteAutomationChooseScene_ViewBinding(LiteAutomationChooseScene liteAutomationChooseScene) {
        this(liteAutomationChooseScene, liteAutomationChooseScene.getWindow().getDecorView());
    }

    @UiThread
    public LiteAutomationChooseScene_ViewBinding(LiteAutomationChooseScene liteAutomationChooseScene, View view) {
        this.f21289a = liteAutomationChooseScene;
        liteAutomationChooseScene.mPullRefreshLL = (PtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefreshLL'", PtrFrameLayout.class);
        liteAutomationChooseScene.mRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.scene_item_view, "field 'mRecyclerView'", RecyclerView.class);
        liteAutomationChooseScene.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        liteAutomationChooseScene.mReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mReturnBtn'", ImageView.class);
        liteAutomationChooseScene.mNoSceneView = Utils.findRequiredView(view, R.id.no_scene_item_view, "field 'mNoSceneView'");
    }

    @CallSuper
    public void unbind() {
        LiteAutomationChooseScene liteAutomationChooseScene = this.f21289a;
        if (liteAutomationChooseScene != null) {
            this.f21289a = null;
            liteAutomationChooseScene.mPullRefreshLL = null;
            liteAutomationChooseScene.mRecyclerView = null;
            liteAutomationChooseScene.mTitle = null;
            liteAutomationChooseScene.mReturnBtn = null;
            liteAutomationChooseScene.mNoSceneView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
