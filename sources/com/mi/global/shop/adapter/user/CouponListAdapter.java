package com.mi.global.shop.adapter.user;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.Tags;
import com.mi.global.shop.newmodel.user.coupon.NewCouponItem;

public class CouponListAdapter extends ArrayAdapter<NewCouponItem> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5578a = "CouponListViewAdapter";
    private String b;

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5579a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5579a = viewHolder;
            viewHolder.value = (TextView) Utils.findRequiredViewAsType(view, R.id.coupon_value, "field 'value'", TextView.class);
            viewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.coupon_name, "field 'name'", TextView.class);
            viewHolder.range = (TextView) Utils.findRequiredViewAsType(view, R.id.coupon_range, "field 'range'", TextView.class);
            viewHolder.time = (TextView) Utils.findRequiredViewAsType(view, R.id.coupon_time, "field 'time'", TextView.class);
            viewHolder.state = (ImageView) Utils.findRequiredViewAsType(view, R.id.coupon_state, "field 'state'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5579a;
            if (viewHolder != null) {
                this.f5579a = null;
                viewHolder.value = null;
                viewHolder.name = null;
                viewHolder.range = null;
                viewHolder.time = null;
                viewHolder.state = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CouponListAdapter(Context context, String str) {
        super(context);
        this.b = str;
    }

    public View a(Context context, int i, NewCouponItem newCouponItem, ViewGroup viewGroup) {
        if (newCouponItem == null) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_coupon_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, NewCouponItem newCouponItem) {
        if (newCouponItem != null) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            viewHolder.value.setText(newCouponItem.valueDesc);
            viewHolder.name.setText(newCouponItem.couponNameDesc);
            if (TextUtils.isEmpty(newCouponItem.couponNameDesc)) {
                viewHolder.name.setVisibility(8);
            } else {
                viewHolder.name.setVisibility(0);
            }
            viewHolder.range.setText(newCouponItem.usableRange);
            String a2 = a(newCouponItem.beginTime);
            String a3 = a(newCouponItem.endTime);
            viewHolder.time.setText(a2 + " - " + a3);
            if ("coupon_choose".equalsIgnoreCase(this.b)) {
                if (Tags.Coupon.STAT_UNUSED.equalsIgnoreCase(newCouponItem.stat)) {
                    if ("true".equalsIgnoreCase(newCouponItem.check_res)) {
                        viewHolder.state.setVisibility(8);
                        viewHolder.a(true);
                        return;
                    }
                    viewHolder.state.setVisibility(0);
                    viewHolder.state.setImageResource(R.drawable.shop_disabled);
                    viewHolder.a(false);
                } else if (Tags.Coupon.STAT_USED.equalsIgnoreCase(newCouponItem.stat)) {
                    viewHolder.state.setVisibility(0);
                    viewHolder.state.setImageResource(R.drawable.shop_used);
                    viewHolder.a(false);
                } else {
                    viewHolder.state.setVisibility(0);
                    viewHolder.state.setImageResource(R.drawable.shop_expired);
                    viewHolder.a(false);
                }
            } else if (Tags.Coupon.STAT_UNUSED.equalsIgnoreCase(newCouponItem.stat)) {
                viewHolder.state.setVisibility(8);
                viewHolder.a(true);
            } else if (Tags.Coupon.STAT_USED.equalsIgnoreCase(newCouponItem.stat)) {
                viewHolder.state.setVisibility(0);
                viewHolder.state.setImageResource(R.drawable.shop_used);
                viewHolder.a(false);
            } else {
                viewHolder.state.setVisibility(0);
                viewHolder.state.setImageResource(R.drawable.shop_expired);
                viewHolder.a(false);
            }
        }
    }

    private String a(int i) {
        return LocaleHelper.a(Long.valueOf(((long) i) * 1000));
    }

    static class ViewHolder {
        @BindView(2131493218)
        TextView name;
        @BindView(2131493219)
        TextView range;
        @BindView(2131493220)
        ImageView state;
        @BindView(2131493221)
        TextView time;
        @BindView(2131493223)
        TextView value;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        public void a(boolean z) {
            this.value.setEnabled(z);
            this.name.setEnabled(z);
            this.range.setEnabled(z);
            this.time.setEnabled(z);
        }
    }
}
