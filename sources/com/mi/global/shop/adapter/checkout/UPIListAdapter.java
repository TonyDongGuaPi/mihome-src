package com.mi.global.shop.adapter.checkout;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.util.UrlUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;

public class UPIListAdapter extends ArrayAdapter<OrderdetailFragment.PaymentMethod> {

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5532a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5532a = viewHolder;
            viewHolder.mUpiChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_upi_checked, "field 'mUpiChecked'", ImageView.class);
            viewHolder.mUpiContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_upi_container, "field 'mUpiContainer'", LinearLayout.class);
            viewHolder.logoView = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.sv_logo, "field 'logoView'", SimpleDraweeView.class);
            viewHolder.itemHint = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_hint, "field 'itemHint'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5532a;
            if (viewHolder != null) {
                this.f5532a = null;
                viewHolder.mUpiChecked = null;
                viewHolder.mUpiContainer = null;
                viewHolder.logoView = null;
                viewHolder.itemHint = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public UPIListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, OrderdetailFragment.PaymentMethod paymentMethod, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_tez_upi_list_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, OrderdetailFragment.PaymentMethod paymentMethod) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (paymentMethod.h) {
            viewHolder.mUpiContainer.setBackgroundResource(R.drawable.shop_cardless_plan_select_item_bg);
            viewHolder.mUpiChecked.setVisibility(0);
        } else {
            viewHolder.mUpiChecked.setVisibility(8);
            viewHolder.mUpiContainer.setBackgroundResource(R.drawable.shop_cardless_plan_item_bg);
        }
        if (!TextUtils.isEmpty(paymentMethod.e)) {
            FrescoUtils.a(UrlUtil.a(paymentMethod.e), viewHolder.logoView);
        }
        if (TextUtils.isEmpty(paymentMethod.b)) {
            viewHolder.itemHint.setVisibility(8);
            return;
        }
        viewHolder.itemHint.setText(paymentMethod.b);
        viewHolder.itemHint.setVisibility(0);
    }

    static class ViewHolder {
        @BindView(2131493512)
        CustomTextView itemHint;
        @BindView(2131494070)
        SimpleDraweeView logoView;
        @BindView(2131493572)
        ImageView mUpiChecked;
        @BindView(2131493683)
        LinearLayout mUpiContainer;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
