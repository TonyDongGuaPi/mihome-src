package com.xiaomi.smarthome.scenenew.actiivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SmarthomeSortLiteSceneActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmarthomeSortLiteSceneActivity f21918a;

    @UiThread
    public SmarthomeSortLiteSceneActivity_ViewBinding(SmarthomeSortLiteSceneActivity smarthomeSortLiteSceneActivity) {
        this(smarthomeSortLiteSceneActivity, smarthomeSortLiteSceneActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmarthomeSortLiteSceneActivity_ViewBinding(SmarthomeSortLiteSceneActivity smarthomeSortLiteSceneActivity, View view) {
        this.f21918a = smarthomeSortLiteSceneActivity;
        smarthomeSortLiteSceneActivity.moduleA4ReturnBtn = (Button) Utils.findRequiredViewAsType(view, R.id.module_a_4_return_btn, "field 'moduleA4ReturnBtn'", Button.class);
        smarthomeSortLiteSceneActivity.moduleA4ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_4_return_title, "field 'moduleA4ReturnTitle'", TextView.class);
        smarthomeSortLiteSceneActivity.mCommitBtn = (Button) Utils.findRequiredViewAsType(view, R.id.module_a_4_commit_btn, "field 'mCommitBtn'", Button.class);
    }

    @CallSuper
    public void unbind() {
        SmarthomeSortLiteSceneActivity smarthomeSortLiteSceneActivity = this.f21918a;
        if (smarthomeSortLiteSceneActivity != null) {
            this.f21918a = null;
            smarthomeSortLiteSceneActivity.moduleA4ReturnBtn = null;
            smarthomeSortLiteSceneActivity.moduleA4ReturnTitle = null;
            smarthomeSortLiteSceneActivity.mCommitBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
