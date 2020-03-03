package com.mi.global.shop.buy;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;

public class UPIFragment_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private UPIFragment f6858a;

    @UiThread
    public UPIFragment_ViewBinding(UPIFragment uPIFragment, View view) {
        this.f6858a = uPIFragment;
        uPIFragment.mUpiListView = (NoScrollListView) Utils.findRequiredViewAsType(view, R.id.lv_upi_list, "field 'mUpiListView'", NoScrollListView.class);
        uPIFragment.mUPIView = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.tv_upi_id, "field 'mUPIView'", CustomEditTextView.class);
        uPIFragment.mBankView = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_bank_name, "field 'mBankView'", CustomTextView.class);
        uPIFragment.mPayNowBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.bt_pay, "field 'mPayNowBtn'", CustomButtonView.class);
        uPIFragment.mSelectGroup = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_select_group, "field 'mSelectGroup'", LinearLayout.class);
        uPIFragment.mUPIEnterGroup = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.layout_upi_id_enter_group, "field 'mUPIEnterGroup'", LinearLayout.class);
        uPIFragment.mUPIOtherView = (CustomEditTextView) Utils.findRequiredViewAsType(view, R.id.tv_other_upi_id, "field 'mUPIOtherView'", CustomEditTextView.class);
        uPIFragment.mUPITip = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_upi_tip, "field 'mUPITip'", CustomTextView.class);
    }

    @CallSuper
    public void unbind() {
        UPIFragment uPIFragment = this.f6858a;
        if (uPIFragment != null) {
            this.f6858a = null;
            uPIFragment.mUpiListView = null;
            uPIFragment.mUPIView = null;
            uPIFragment.mBankView = null;
            uPIFragment.mPayNowBtn = null;
            uPIFragment.mSelectGroup = null;
            uPIFragment.mUPIEnterGroup = null;
            uPIFragment.mUPIOtherView = null;
            uPIFragment.mUPITip = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
