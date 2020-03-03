package com.xiaomi.smarthome.homeroom;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ChangeHomeActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ChangeHomeActivity f17933a;

    @UiThread
    public ChangeHomeActivity_ViewBinding(ChangeHomeActivity changeHomeActivity) {
        this(changeHomeActivity, changeHomeActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChangeHomeActivity_ViewBinding(ChangeHomeActivity changeHomeActivity, View view) {
        this.f17933a = changeHomeActivity;
        changeHomeActivity.moduleA3ReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        changeHomeActivity.listView = (ListView) Utils.findRequiredViewAsType(view, R.id.listview, "field 'listView'", ListView.class);
        changeHomeActivity.mContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.container, "field 'mContainer'", ViewGroup.class);
    }

    @CallSuper
    public void unbind() {
        ChangeHomeActivity changeHomeActivity = this.f17933a;
        if (changeHomeActivity != null) {
            this.f17933a = null;
            changeHomeActivity.moduleA3ReturnBtn = null;
            changeHomeActivity.listView = null;
            changeHomeActivity.mContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
