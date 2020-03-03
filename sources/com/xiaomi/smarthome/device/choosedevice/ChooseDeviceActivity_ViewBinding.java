package com.xiaomi.smarthome.device.choosedevice;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class ChooseDeviceActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private ChooseDeviceActivity f15323a;

    @UiThread
    public ChooseDeviceActivity_ViewBinding(ChooseDeviceActivity chooseDeviceActivity) {
        this(chooseDeviceActivity, chooseDeviceActivity.getWindow().getDecorView());
    }

    @UiThread
    public ChooseDeviceActivity_ViewBinding(ChooseDeviceActivity chooseDeviceActivity, View view) {
        this.f15323a = chooseDeviceActivity;
        chooseDeviceActivity.mNormalBackImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.normal_back_img, "field 'mNormalBackImg'", ImageView.class);
        chooseDeviceActivity.mMoreBackImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.more_back_img, "field 'mMoreBackImg'", ImageView.class);
        chooseDeviceActivity.mGoScanImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.go_scan_qrcode, "field 'mGoScanImg'", ImageView.class);
        chooseDeviceActivity.mViewPager = Utils.findRequiredView(view, R.id.pager, "field 'mViewPager'");
        chooseDeviceActivity.mSearchContainerView = Utils.findRequiredView(view, R.id.layout_search_container, "field 'mSearchContainerView'");
        chooseDeviceActivity.mSearchListView = (ListView) Utils.findRequiredViewAsType(view, R.id.device_grid_list, "field 'mSearchListView'", ListView.class);
        chooseDeviceActivity.mNoSearchResultView = Utils.findRequiredView(view, R.id.no_search_results, "field 'mNoSearchResultView'");
        chooseDeviceActivity.mSearchEt = (EditText) Utils.findRequiredViewAsType(view, R.id.search_et, "field 'mSearchEt'", EditText.class);
        chooseDeviceActivity.mSearchEtClearBtn = Utils.findRequiredView(view, R.id.search_et_clear_btn, "field 'mSearchEtClearBtn'");
        chooseDeviceActivity.mDeviceFindView = Utils.findRequiredView(view, R.id.device_find, "field 'mDeviceFindView'");
        chooseDeviceActivity.mDeviceFindCnt = (TextView) Utils.findRequiredViewAsType(view, R.id.device_find_count, "field 'mDeviceFindCnt'", TextView.class);
        chooseDeviceActivity.mTitleBar = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.title_bar, "field 'mTitleBar'", ViewGroup.class);
        chooseDeviceActivity.mNormalHeader = Utils.findRequiredView(view, R.id.normal_header, "field 'mNormalHeader'");
        chooseDeviceActivity.mMoreHeader = Utils.findRequiredView(view, R.id.more_device_header, "field 'mMoreHeader'");
    }

    @CallSuper
    public void unbind() {
        ChooseDeviceActivity chooseDeviceActivity = this.f15323a;
        if (chooseDeviceActivity != null) {
            this.f15323a = null;
            chooseDeviceActivity.mNormalBackImg = null;
            chooseDeviceActivity.mMoreBackImg = null;
            chooseDeviceActivity.mGoScanImg = null;
            chooseDeviceActivity.mViewPager = null;
            chooseDeviceActivity.mSearchContainerView = null;
            chooseDeviceActivity.mSearchListView = null;
            chooseDeviceActivity.mNoSearchResultView = null;
            chooseDeviceActivity.mSearchEt = null;
            chooseDeviceActivity.mSearchEtClearBtn = null;
            chooseDeviceActivity.mDeviceFindView = null;
            chooseDeviceActivity.mDeviceFindCnt = null;
            chooseDeviceActivity.mTitleBar = null;
            chooseDeviceActivity.mNormalHeader = null;
            chooseDeviceActivity.mMoreHeader = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
