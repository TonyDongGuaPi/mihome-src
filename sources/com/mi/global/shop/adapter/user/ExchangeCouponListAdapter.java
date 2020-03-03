package com.mi.global.shop.adapter.user;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
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
import com.mi.global.shop.newmodel.user.exchangecoupon.coupon.NewExchangeCouponItem;

public class ExchangeCouponListAdapter extends ArrayAdapter<NewExchangeCouponItem> {

    /* renamed from: a  reason: collision with root package name */
    private String f5580a;
    private Context b;

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5581a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5581a = viewHolder;
            viewHolder.value = (TextView) Utils.findRequiredViewAsType(view, R.id.coupon_value, "field 'value'", TextView.class);
            viewHolder.time = (TextView) Utils.findRequiredViewAsType(view, R.id.coupon_time, "field 'time'", TextView.class);
            viewHolder.state = (ImageView) Utils.findRequiredViewAsType(view, R.id.coupon_state, "field 'state'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5581a;
            if (viewHolder != null) {
                this.f5581a = null;
                viewHolder.value = null;
                viewHolder.time = null;
                viewHolder.state = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ExchangeCouponListAdapter(Context context, String str) {
        super(context);
        this.f5580a = str;
        this.b = context;
    }

    public View a(Context context, int i, NewExchangeCouponItem newExchangeCouponItem, ViewGroup viewGroup) {
        if (newExchangeCouponItem == null) {
            return null;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_exchange_coupon_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, NewExchangeCouponItem newExchangeCouponItem) {
        if (newExchangeCouponItem != null) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            TextView textView = viewHolder.value;
            textView.setText(LocaleHelper.e() + newExchangeCouponItem.amount);
            String a2 = a(newExchangeCouponItem.start_time);
            String a3 = a(newExchangeCouponItem.end_time);
            viewHolder.time.setText(a2 + " - " + a3);
        }
    }

    private String a(int i) {
        return LocaleHelper.a(Long.valueOf(((long) i) * 1000));
    }

    static class ViewHolder {
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
            this.time.setEnabled(z);
        }
    }
}
