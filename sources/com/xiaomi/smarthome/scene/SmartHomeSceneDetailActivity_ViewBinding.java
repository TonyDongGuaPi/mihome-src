package com.xiaomi.smarthome.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SmartHomeSceneDetailActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmartHomeSceneDetailActivity f21444a;

    @UiThread
    public SmartHomeSceneDetailActivity_ViewBinding(SmartHomeSceneDetailActivity smartHomeSceneDetailActivity) {
        this(smartHomeSceneDetailActivity, smartHomeSceneDetailActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmartHomeSceneDetailActivity_ViewBinding(SmartHomeSceneDetailActivity smartHomeSceneDetailActivity, View view) {
        this.f21444a = smartHomeSceneDetailActivity;
        smartHomeSceneDetailActivity.mBackBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBackBtn'", ImageView.class);
        smartHomeSceneDetailActivity.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        smartHomeSceneDetailActivity.mContentList = (ListView) Utils.findRequiredViewAsType(view, R.id.content_list_view, "field 'mContentList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        SmartHomeSceneDetailActivity smartHomeSceneDetailActivity = this.f21444a;
        if (smartHomeSceneDetailActivity != null) {
            this.f21444a = null;
            smartHomeSceneDetailActivity.mBackBtn = null;
            smartHomeSceneDetailActivity.mTitle = null;
            smartHomeSceneDetailActivity.mContentList = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
