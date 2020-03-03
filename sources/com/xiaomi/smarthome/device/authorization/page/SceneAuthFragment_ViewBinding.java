package com.xiaomi.smarthome.device.authorization.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SceneAuthFragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SceneAuthFragment f15059a;

    @UiThread
    public SceneAuthFragment_ViewBinding(SceneAuthFragment sceneAuthFragment, View view) {
        this.f15059a = sceneAuthFragment;
        sceneAuthFragment.mSceneList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.device_list, "field 'mSceneList'", RecyclerView.class);
        sceneAuthFragment.emptyView = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'emptyView'");
    }

    @CallSuper
    public void unbind() {
        SceneAuthFragment sceneAuthFragment = this.f15059a;
        if (sceneAuthFragment != null) {
            this.f15059a = null;
            sceneAuthFragment.mSceneList = null;
            sceneAuthFragment.emptyView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
