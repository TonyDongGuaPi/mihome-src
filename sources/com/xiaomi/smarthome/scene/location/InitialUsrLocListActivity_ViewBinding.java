package com.xiaomi.smarthome.scene.location;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;

public class InitialUsrLocListActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private InitialUsrLocListActivity f21586a;
    private View b;
    private View c;

    @UiThread
    public InitialUsrLocListActivity_ViewBinding(InitialUsrLocListActivity initialUsrLocListActivity) {
        this(initialUsrLocListActivity, initialUsrLocListActivity.getWindow().getDecorView());
    }

    @UiThread
    public InitialUsrLocListActivity_ViewBinding(final InitialUsrLocListActivity initialUsrLocListActivity, View view) {
        this.f21586a = initialUsrLocListActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onClick'");
        initialUsrLocListActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initialUsrLocListActivity.onClick(view);
            }
        });
        initialUsrLocListActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        initialUsrLocListActivity.titleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", FrameLayout.class);
        initialUsrLocListActivity.list = (PullDownDragListView) Utils.findRequiredViewAsType(view, R.id.list, "field 'list'", PullDownDragListView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.add, "field 'add' and method 'onClick'");
        initialUsrLocListActivity.add = (TextView) Utils.castView(findRequiredView2, R.id.add, "field 'add'", TextView.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                initialUsrLocListActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        InitialUsrLocListActivity initialUsrLocListActivity = this.f21586a;
        if (initialUsrLocListActivity != null) {
            this.f21586a = null;
            initialUsrLocListActivity.moduleA3ReturnBtn = null;
            initialUsrLocListActivity.moduleA3ReturnTitle = null;
            initialUsrLocListActivity.titleBar = null;
            initialUsrLocListActivity.list = null;
            initialUsrLocListActivity.add = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
