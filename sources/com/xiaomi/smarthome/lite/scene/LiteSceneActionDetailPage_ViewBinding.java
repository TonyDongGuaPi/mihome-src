package com.xiaomi.smarthome.lite.scene;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class LiteSceneActionDetailPage_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private LiteSceneActionDetailPage f19388a;

    @UiThread
    public LiteSceneActionDetailPage_ViewBinding(LiteSceneActionDetailPage liteSceneActionDetailPage) {
        this(liteSceneActionDetailPage, liteSceneActionDetailPage.getWindow().getDecorView());
    }

    @UiThread
    public LiteSceneActionDetailPage_ViewBinding(LiteSceneActionDetailPage liteSceneActionDetailPage, View view) {
        this.f19388a = liteSceneActionDetailPage;
        liteSceneActionDetailPage.mBackBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBackBtn'", ImageView.class);
        liteSceneActionDetailPage.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitle'", TextView.class);
        liteSceneActionDetailPage.mContentList = (ListView) Utils.findRequiredViewAsType(view, R.id.content_list_view, "field 'mContentList'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        LiteSceneActionDetailPage liteSceneActionDetailPage = this.f19388a;
        if (liteSceneActionDetailPage != null) {
            this.f19388a = null;
            liteSceneActionDetailPage.mBackBtn = null;
            liteSceneActionDetailPage.mTitle = null;
            liteSceneActionDetailPage.mContentList = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
