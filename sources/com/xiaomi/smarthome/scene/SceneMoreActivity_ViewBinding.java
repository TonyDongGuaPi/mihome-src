package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SceneMoreActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SceneMoreActivity f21324a;

    @UiThread
    public SceneMoreActivity_ViewBinding(SceneMoreActivity sceneMoreActivity) {
        this(sceneMoreActivity, sceneMoreActivity.getWindow().getDecorView());
    }

    @UiThread
    public SceneMoreActivity_ViewBinding(SceneMoreActivity sceneMoreActivity, View view) {
        this.f21324a = sceneMoreActivity;
        sceneMoreActivity.ivTitleReturn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'ivTitleReturn'", ImageView.class);
        sceneMoreActivity.tvTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'tvTitle'", TextView.class);
        sceneMoreActivity.ivTitleMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_more_more_btn, "field 'ivTitleMore'", ImageView.class);
        sceneMoreActivity.viewRename = Utils.findRequiredView(view, R.id.view_rename, "field 'viewRename'");
        sceneMoreActivity.viewExecuteResultNotify = Utils.findRequiredView(view, R.id.view_execute_result_notify, "field 'viewExecuteResultNotify'");
        sceneMoreActivity.viewAddToLockscreen = Utils.findRequiredView(view, R.id.view_add_to_lockscreen, "field 'viewAddToLockscreen'");
        sceneMoreActivity.viewAddToHome = Utils.findRequiredView(view, R.id.view_add_to_home, "field 'viewAddToHome'");
        sceneMoreActivity.viewDeleteScene = Utils.findRequiredView(view, R.id.view_delete_scene, "field 'viewDeleteScene'");
        sceneMoreActivity.viewExecuteLocation = Utils.findRequiredView(view, R.id.view_execute_location, "field 'viewExecuteLocation'");
    }

    @CallSuper
    public void unbind() {
        SceneMoreActivity sceneMoreActivity = this.f21324a;
        if (sceneMoreActivity != null) {
            this.f21324a = null;
            sceneMoreActivity.ivTitleReturn = null;
            sceneMoreActivity.tvTitle = null;
            sceneMoreActivity.ivTitleMore = null;
            sceneMoreActivity.viewRename = null;
            sceneMoreActivity.viewExecuteResultNotify = null;
            sceneMoreActivity.viewAddToLockscreen = null;
            sceneMoreActivity.viewAddToHome = null;
            sceneMoreActivity.viewDeleteScene = null;
            sceneMoreActivity.viewExecuteLocation = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
