package com.xiaomi.smarthome.lite.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SceneChooseActionPage_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SceneChooseActionPage f19410a;
    private View b;

    @UiThread
    public SceneChooseActionPage_ViewBinding(SceneChooseActionPage sceneChooseActionPage) {
        this(sceneChooseActionPage, sceneChooseActionPage.getWindow().getDecorView());
    }

    @UiThread
    public SceneChooseActionPage_ViewBinding(final SceneChooseActionPage sceneChooseActionPage, View view) {
        this.f19410a = sceneChooseActionPage;
        sceneChooseActionPage.mModuleA3ReturnTransparentTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mModuleA3ReturnTransparentTitle'", TextView.class);
        sceneChooseActionPage.mContentListView = (ListView) Utils.findRequiredViewAsType(view, R.id.content_list_view, "field 'mContentListView'", ListView.class);
        sceneChooseActionPage.mBuyView = Utils.findRequiredView(view, R.id.buy_empty_view, "field 'mBuyView'");
        sceneChooseActionPage.mBuyButton = (Button) Utils.findRequiredViewAsType(view, R.id.btn_see_now, "field 'mBuyButton'", Button.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "method 'close'");
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                sceneChooseActionPage.close();
            }
        });
    }

    @CallSuper
    public void unbind() {
        SceneChooseActionPage sceneChooseActionPage = this.f19410a;
        if (sceneChooseActionPage != null) {
            this.f19410a = null;
            sceneChooseActionPage.mModuleA3ReturnTransparentTitle = null;
            sceneChooseActionPage.mContentListView = null;
            sceneChooseActionPage.mBuyView = null;
            sceneChooseActionPage.mBuyButton = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
