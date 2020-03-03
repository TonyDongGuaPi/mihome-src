package com.xiaomi.smarthome.miio.page.deviceophistory;

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

public class DeviceOpHistoryListActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private DeviceOpHistoryListActivity f19746a;
    private View b;
    private View c;

    @UiThread
    public DeviceOpHistoryListActivity_ViewBinding(DeviceOpHistoryListActivity deviceOpHistoryListActivity) {
        this(deviceOpHistoryListActivity, deviceOpHistoryListActivity.getWindow().getDecorView());
    }

    @UiThread
    public DeviceOpHistoryListActivity_ViewBinding(final DeviceOpHistoryListActivity deviceOpHistoryListActivity, View view) {
        this.f19746a = deviceOpHistoryListActivity;
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn' and method 'onClick'");
        deviceOpHistoryListActivity.moduleA3ReturnBtn = (ImageView) Utils.castView(findRequiredView, R.id.module_a_3_return_btn, "field 'moduleA3ReturnBtn'", ImageView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceOpHistoryListActivity.onClick(view);
            }
        });
        deviceOpHistoryListActivity.moduleA3ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'moduleA3ReturnTitle'", TextView.class);
        deviceOpHistoryListActivity.moduleA3ReturnMoreMoreBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_more_more_btn, "field 'moduleA3ReturnMoreMoreBtn'", ImageView.class);
        deviceOpHistoryListActivity.titleBar = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'titleBar'", FrameLayout.class);
        deviceOpHistoryListActivity.list = (PullDownDragListView) Utils.findRequiredViewAsType(view, R.id.list, "field 'list'", PullDownDragListView.class);
        deviceOpHistoryListActivity.emptyIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.empty_icon, "field 'emptyIcon'", ImageView.class);
        deviceOpHistoryListActivity.commonWhiteEmptyText = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text, "field 'commonWhiteEmptyText'", TextView.class);
        deviceOpHistoryListActivity.commonWhiteEmptyText2 = (TextView) Utils.findRequiredViewAsType(view, R.id.common_white_empty_text_2, "field 'commonWhiteEmptyText2'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.common_white_empty_view, "field 'commonWhiteEmptyView' and method 'onClick'");
        deviceOpHistoryListActivity.commonWhiteEmptyView = (LinearLayout) Utils.castView(findRequiredView2, R.id.common_white_empty_view, "field 'commonWhiteEmptyView'", LinearLayout.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                deviceOpHistoryListActivity.onClick(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        DeviceOpHistoryListActivity deviceOpHistoryListActivity = this.f19746a;
        if (deviceOpHistoryListActivity != null) {
            this.f19746a = null;
            deviceOpHistoryListActivity.moduleA3ReturnBtn = null;
            deviceOpHistoryListActivity.moduleA3ReturnTitle = null;
            deviceOpHistoryListActivity.moduleA3ReturnMoreMoreBtn = null;
            deviceOpHistoryListActivity.titleBar = null;
            deviceOpHistoryListActivity.list = null;
            deviceOpHistoryListActivity.emptyIcon = null;
            deviceOpHistoryListActivity.commonWhiteEmptyText = null;
            deviceOpHistoryListActivity.commonWhiteEmptyText2 = null;
            deviceOpHistoryListActivity.commonWhiteEmptyView = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
