package com.xiaomi.smarthome.framework.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;

public class GeneralOneTimePasswordHistoryActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private GeneralOneTimePasswordHistoryActivity f16866a;

    @UiThread
    public GeneralOneTimePasswordHistoryActivity_ViewBinding(GeneralOneTimePasswordHistoryActivity generalOneTimePasswordHistoryActivity) {
        this(generalOneTimePasswordHistoryActivity, generalOneTimePasswordHistoryActivity.getWindow().getDecorView());
    }

    @UiThread
    public GeneralOneTimePasswordHistoryActivity_ViewBinding(GeneralOneTimePasswordHistoryActivity generalOneTimePasswordHistoryActivity, View view) {
        this.f16866a = generalOneTimePasswordHistoryActivity;
        generalOneTimePasswordHistoryActivity.mBackBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mBackBt'", ImageView.class);
        generalOneTimePasswordHistoryActivity.mListView = (ListView) Utils.findRequiredViewAsType(view, R.id.recycler_view, "field 'mListView'", ListView.class);
        generalOneTimePasswordHistoryActivity.mEmptyView = Utils.findRequiredView(view, R.id.empty_all_page, "field 'mEmptyView'");
        generalOneTimePasswordHistoryActivity.mPullRefresh = (DevicePtrFrameLayout) Utils.findRequiredViewAsType(view, R.id.pull_down_refresh, "field 'mPullRefresh'", DevicePtrFrameLayout.class);
        generalOneTimePasswordHistoryActivity.mGeneratePwdBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.generate_pwd_btn, "field 'mGeneratePwdBtn'", ImageView.class);
        generalOneTimePasswordHistoryActivity.mSubTitle = Utils.findRequiredView(view, R.id.sub_title_bar, "field 'mSubTitle'");
    }

    @CallSuper
    public void unbind() {
        GeneralOneTimePasswordHistoryActivity generalOneTimePasswordHistoryActivity = this.f16866a;
        if (generalOneTimePasswordHistoryActivity != null) {
            this.f16866a = null;
            generalOneTimePasswordHistoryActivity.mBackBt = null;
            generalOneTimePasswordHistoryActivity.mListView = null;
            generalOneTimePasswordHistoryActivity.mEmptyView = null;
            generalOneTimePasswordHistoryActivity.mPullRefresh = null;
            generalOneTimePasswordHistoryActivity.mGeneratePwdBtn = null;
            generalOneTimePasswordHistoryActivity.mSubTitle = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
