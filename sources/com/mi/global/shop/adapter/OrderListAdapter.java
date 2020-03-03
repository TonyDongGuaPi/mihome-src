package com.mi.global.shop.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.OrderListAcitvity;
import com.mi.global.shop.adapter.util.AutoLoadArrayAdapter;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.orderlist.NewOrderListItem;
import com.mi.global.shop.util.BaseTypeConvertUtil;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;

public class OrderListAdapter extends AutoLoadArrayAdapter<NewOrderListItem> {

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5499a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5499a = viewHolder;
            viewHolder.tag = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tag, "field 'tag'", CustomTextView.class);
            viewHolder.time = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.time, "field 'time'", CustomTextView.class);
            viewHolder.packageNum = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.package_num, "field 'packageNum'", CustomTextView.class);
            viewHolder.status = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.status, "field 'status'", CustomTextView.class);
            viewHolder.tip = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tip, "field 'tip'", CustomTextView.class);
            viewHolder.tipDivider = Utils.findRequiredView(view, R.id.tip_divider, "field 'tipDivider'");
            viewHolder.orderItemListView = (NoScrollListView) Utils.findRequiredViewAsType(view, R.id.order_item_list, "field 'orderItemListView'", NoScrollListView.class);
            viewHolder.totalPrice = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.total_price, "field 'totalPrice'", CustomTextView.class);
            viewHolder.cancelBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.cancel_btn, "field 'cancelBtn'", CustomButtonView.class);
            viewHolder.payBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.pay_btn, "field 'payBtn'", CustomButtonView.class);
            viewHolder.traceBtn = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.trace_btn, "field 'traceBtn'", CustomButtonView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5499a;
            if (viewHolder != null) {
                this.f5499a = null;
                viewHolder.tag = null;
                viewHolder.time = null;
                viewHolder.packageNum = null;
                viewHolder.status = null;
                viewHolder.tip = null;
                viewHolder.tipDivider = null;
                viewHolder.orderItemListView = null;
                viewHolder.totalPrice = null;
                viewHolder.cancelBtn = null;
                viewHolder.payBtn = null;
                viewHolder.traceBtn = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public OrderListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewOrderListItem newOrderListItem, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_order_list_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, final NewOrderListItem newOrderListItem) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.time.setText(LocaleHelper.a(Long.valueOf(BaseTypeConvertUtil.a(newOrderListItem.add_time, 0) * 1000)));
        viewHolder.status.setText(newOrderListItem.order_status_info.info);
        CustomTextView customTextView = viewHolder.totalPrice;
        customTextView.setText("Total  " + LocaleHelper.e() + newOrderListItem.goods_amount);
        if (TextUtils.isEmpty(newOrderListItem.show_tag)) {
            viewHolder.tag.setVisibility(8);
        } else {
            viewHolder.tag.setVisibility(0);
            viewHolder.tag.setText(newOrderListItem.show_tag);
        }
        if (TextUtils.isEmpty(newOrderListItem.show_tips)) {
            viewHolder.tip.setVisibility(8);
            viewHolder.tipDivider.setVisibility(8);
        } else {
            viewHolder.tip.setVisibility(0);
            viewHolder.tipDivider.setVisibility(0);
            viewHolder.tip.setText(newOrderListItem.show_tips);
        }
        if (Build.VERSION.SDK_INT > 23) {
            OrderItemListAdapter orderItemListAdapter = new OrderItemListAdapter(view.getContext());
            viewHolder.orderItemListView.setAdapter(orderItemListAdapter);
            orderItemListAdapter.a(newOrderListItem.product);
        } else {
            viewHolder.f5498a.a(newOrderListItem.product);
        }
        if (newOrderListItem.hasPay) {
            viewHolder.payBtn.setVisibility(0);
            viewHolder.payBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (OrderListAdapter.this.d instanceof OrderListAcitvity) {
                        ((OrderListAcitvity) OrderListAdapter.this.d).payOrder(newOrderListItem);
                    }
                }
            });
        } else {
            viewHolder.payBtn.setVisibility(8);
        }
        viewHolder.cancelBtn.setVisibility(8);
        if (newOrderListItem.hasTrace) {
            viewHolder.traceBtn.setVisibility(0);
            viewHolder.traceBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (OrderListAdapter.this.d instanceof OrderListAcitvity) {
                        ((OrderListAcitvity) OrderListAdapter.this.d).traceOrder(newOrderListItem);
                    }
                }
            });
            if (newOrderListItem.hasSuborder) {
                viewHolder.traceBtn.setText(R.string.order_item_view_detail);
                viewHolder.packageNum.setVisibility(0);
                viewHolder.packageNum.setText(String.format(this.d.getString(R.string.order_item_packages), new Object[]{Integer.valueOf(newOrderListItem.delivers.size())}));
            } else {
                viewHolder.traceBtn.setText(R.string.order_item_track);
                viewHolder.packageNum.setVisibility(8);
            }
        } else {
            viewHolder.traceBtn.setVisibility(8);
            viewHolder.packageNum.setVisibility(8);
        }
        viewHolder.orderItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (OrderListAdapter.this.d instanceof OrderListAcitvity) {
                    ((OrderListAcitvity) OrderListAdapter.this.d).startOrderViewActivity(newOrderListItem);
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (OrderListAdapter.this.d instanceof OrderListAcitvity) {
                    ((OrderListAcitvity) OrderListAdapter.this.d).startOrderViewActivity(newOrderListItem);
                }
            }
        });
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        OrderItemListAdapter f5498a;
        @BindView(2131493097)
        CustomButtonView cancelBtn;
        @BindView(2131493765)
        NoScrollListView orderItemListView;
        @BindView(2131493829)
        CustomTextView packageNum;
        @BindView(2131493849)
        CustomButtonView payBtn;
        @BindView(2131494057)
        CustomTextView status;
        @BindView(2131494080)
        CustomTextView tag;
        @BindView(2131494122)
        CustomTextView time;
        @BindView(2131494126)
        CustomTextView tip;
        @BindView(2131494127)
        View tipDivider;
        @BindView(2131494158)
        CustomTextView totalPrice;
        @BindView(2131494162)
        CustomButtonView traceBtn;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
            this.f5498a = new OrderItemListAdapter(view.getContext());
            this.orderItemListView.setAdapter(this.f5498a);
        }
    }
}
