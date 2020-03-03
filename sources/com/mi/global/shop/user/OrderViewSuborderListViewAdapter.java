package com.mi.global.shop.user;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mi.global.shop.R;
import com.mi.global.shop.activity.TrackAcitvity;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.order.NewDeliversData;
import com.mi.global.shop.newmodel.order.NewOrderStatusInfo;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollListView;

public class OrderViewSuborderListViewAdapter extends ArrayAdapter<NewDeliversData> {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7047a = "OrderViewSuborderListViewAdapter";
    private Context b;

    public int getItemViewType(int i) {
        return 1;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public OrderViewSuborderListViewAdapter(Context context) {
        super(context);
        this.b = context;
    }

    public View a(Context context, int i, NewDeliversData newDeliversData, ViewGroup viewGroup) {
        if (newDeliversData == null) {
            return null;
        }
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.shop_orderview_suborder_item, (ViewGroup) null, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.f7049a = inflate;
        viewHolder.b = (CustomTextView) inflate.findViewById(R.id.suborder_id);
        viewHolder.c = (CustomTextView) inflate.findViewById(R.id.suborder_status);
        viewHolder.f = (CustomButtonView) inflate.findViewById(R.id.btn_track);
        viewHolder.d = (NoScrollListView) inflate.findViewById(R.id.suborder_items);
        inflate.setTag(viewHolder);
        return inflate;
    }

    public void a(View view, int i, NewDeliversData newDeliversData) {
        ViewHolder viewHolder;
        if (newDeliversData != null && (viewHolder = (ViewHolder) view.getTag()) != null) {
            String str = newDeliversData.deliver_id;
            MyListener myListener = new MyListener(str, this.b, newDeliversData.order_status_info);
            CustomTextView customTextView = viewHolder.b;
            customTextView.setText(this.b.getString(R.string.orderview_suborderid) + " " + str);
            CustomTextView customTextView2 = viewHolder.c;
            customTextView2.setText(Html.fromHtml(this.b.getString(R.string.orderview_order_status) + " <font color='#FF6700'>" + newDeliversData.order_status_info.info + "</font>"));
            if (TextUtils.isEmpty(newDeliversData.express_sn) || newDeliversData.express_sn.equals("0")) {
                viewHolder.f.setVisibility(8);
            } else {
                viewHolder.f.setVisibility(0);
                viewHolder.f.setOnClickListener(myListener);
            }
            viewHolder.e = new OrderViewItemListViewAdapter(this.b);
            viewHolder.e.c();
            viewHolder.e.a(newDeliversData.product);
            viewHolder.d.setAdapter(viewHolder.e);
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f7049a;
        CustomTextView b;
        CustomTextView c;
        NoScrollListView d;
        OrderViewItemListViewAdapter e;
        CustomButtonView f;

        ViewHolder() {
        }
    }

    private class MyListener implements View.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        String f7048a;
        NewOrderStatusInfo b;
        Context c;

        public MyListener(String str, Context context, NewOrderStatusInfo newOrderStatusInfo) {
            this.f7048a = str;
            this.c = context;
            this.b = newOrderStatusInfo;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.c, TrackAcitvity.class);
            intent.putExtra("expresssn", this.f7048a);
            if (this.b.trace != null && this.b.trace.size() > 0) {
                intent.putExtra("order_placed", this.b.trace.get(0).time);
                intent.putExtra("order_paid", this.b.trace.get(1).time);
            }
            this.c.startActivity(intent);
        }
    }
}
