package com.xiaomi.smarthome.device.authorization.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;

public class DeviceAuthMasterListActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceAuthMasterListActivity f15041a;
    private View b;
    private View c;

    @UiThread
    public DeviceAuthMasterListActivity_ViewBinding(DeviceAuthMasterListActivity deviceAuthMasterListActivity) {
        this(deviceAuthMasterListActivity, deviceAuthMasterListActivity.getWindow().getDecorView());
    }

    @UiThread
    public DeviceAuthMasterListActivity_ViewBinding(final DeviceAuthMasterListActivity deviceAuthMasterListActivity, View view) {
        this.f15041a = deviceAuthMasterListActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onClick'");
        deviceAuthMasterListActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceAuthMasterListActivity.onClick(view);
            }
        });
        deviceAuthMasterListActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        deviceAuthMasterListActivity.moduleA3ReturnMoreMoreBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_more_more_btn, "field 'moduleA3ReturnMoreMoreBtn'", ImageView.class);
        deviceAuthMasterListActivity.titleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", FrameLayout.class);
        deviceAuthMasterListActivity.list = (PullDownDragListView) Utils.findRequiredViewAsType(view, R.id.list, "field 'list'", PullDownDragListView.class);
        deviceAuthMasterListActivity.emptyIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.empty_icon, "field 'emptyIcon'", ImageView.class);
        deviceAuthMasterListActivity.commonWhiteEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'commonWhiteEmptyText'", TextView.class);
        deviceAuthMasterListActivity.commonWhiteEmptyText2 = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text_2, "field 'commonWhiteEmptyText2'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'commonWhiteEmptyView' and method 'onClick'");
        deviceAuthMasterListActivity.commonWhiteEmptyView = (LinearLayout) Utils.castView(findRequiredView2, R.id.common_white_empty_view, "field 'commonWhiteEmptyView'", LinearLayout.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceAuthMasterListActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        DeviceAuthMasterListActivity deviceAuthMasterListActivity = this.f15041a;
        if (deviceAuthMasterListActivity != null) {
            this.f15041a = null;
            deviceAuthMasterListActivity.moduleA3ReturnBtn = null;
            deviceAuthMasterListActivity.moduleA3ReturnTitle = null;
            deviceAuthMasterListActivity.moduleA3ReturnMoreMoreBtn = null;
            deviceAuthMasterListActivity.titleBar = null;
            deviceAuthMasterListActivity.list = null;
            deviceAuthMasterListActivity.emptyIcon = null;
            deviceAuthMasterListActivity.commonWhiteEmptyText = null;
            deviceAuthMasterListActivity.commonWhiteEmptyText2 = null;
            deviceAuthMasterListActivity.commonWhiteEmptyView = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
