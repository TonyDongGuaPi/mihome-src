package com.mi.global.shop.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.newmodel.order.NewTraceItem;
import com.mi.global.shop.util.TimeFormater;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.widget.CustomTextView;
import com.tencent.smtt.sdk.TbsListener;
import java.util.ArrayList;

public class OrderProgressAdapter extends RecyclerView.Adapter<OrderProgressViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5500a = "ReviewOrderListAdapter";
    private Context b;
    private Activity c;
    private ArrayList<NewTraceItem> d = new ArrayList<>();
    private Drawable e;
    private Drawable f;
    private Drawable g;
    private Drawable h;

    public class OrderProgressViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private OrderProgressViewHolder f5501a;

        @UiThread
        public OrderProgressViewHolder_ViewBinding(OrderProgressViewHolder orderProgressViewHolder, View view) {
            this.f5501a = orderProgressViewHolder;
            orderProgressViewHolder.tvOrderProgressTitle = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_order_progress_title, "field 'tvOrderProgressTitle'", CustomTextView.class);
            orderProgressViewHolder.lineLeft = (ImageView) Utils.findRequiredViewAsType(view, R.id.line_left, "field 'lineLeft'", ImageView.class);
            orderProgressViewHolder.centerCircle = (ImageView) Utils.findRequiredViewAsType(view, R.id.center_circle, "field 'centerCircle'", ImageView.class);
            orderProgressViewHolder.lineRight = (ImageView) Utils.findRequiredViewAsType(view, R.id.line_right, "field 'lineRight'", ImageView.class);
            orderProgressViewHolder.tvOrderProgressTime = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_order_progress_time, "field 'tvOrderProgressTime'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            OrderProgressViewHolder orderProgressViewHolder = this.f5501a;
            if (orderProgressViewHolder != null) {
                this.f5501a = null;
                orderProgressViewHolder.tvOrderProgressTitle = null;
                orderProgressViewHolder.lineLeft = null;
                orderProgressViewHolder.centerCircle = null;
                orderProgressViewHolder.lineRight = null;
                orderProgressViewHolder.tvOrderProgressTime = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public OrderProgressAdapter(Context context, Activity activity) {
        this.b = context;
        this.c = activity;
        this.e = context.getResources().getDrawable(R.drawable.shop_order_progress_circle_green);
        this.f = context.getResources().getDrawable(R.drawable.shop_order_progress_circle_grey);
        this.g = context.getResources().getDrawable(R.drawable.shop_order_progress_green_bg);
        this.h = context.getResources().getDrawable(R.drawable.shop_order_progress_grey_bg);
    }

    public void a(ArrayList<NewTraceItem> arrayList) {
        if (arrayList != null) {
            this.d.clear();
            this.d.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void b(ArrayList<NewTraceItem> arrayList) {
        if (arrayList != null) {
            this.d.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(OrderProgressViewHolder orderProgressViewHolder, int i) {
        NewTraceItem newTraceItem = this.d.get(i);
        if (this.d.size() > 0) {
            ViewGroup.LayoutParams layoutParams = orderProgressViewHolder.itemView.getLayoutParams();
            layoutParams.width = UIAdapter.a().b(30) / this.d.size();
            orderProgressViewHolder.itemView.setLayoutParams(layoutParams);
        }
        orderProgressViewHolder.tvOrderProgressTitle.setText(newTraceItem.text);
        try {
            orderProgressViewHolder.tvOrderProgressTime.setText(TimeFormater.b(Long.parseLong(newTraceItem.time)));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!newTraceItem.time.equals("0")) {
            orderProgressViewHolder.tvOrderProgressTitle.setTextColor(Color.rgb(110, 182, 85));
            orderProgressViewHolder.tvOrderProgressTime.setTextColor(Color.rgb(TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS));
            orderProgressViewHolder.centerCircle.setImageDrawable(this.e);
            orderProgressViewHolder.lineLeft.setImageDrawable(this.g);
            if (i < this.d.size() - 1 && !this.d.get(i + 1).time.equals("0")) {
                orderProgressViewHolder.lineRight.setImageDrawable(this.g);
            }
            orderProgressViewHolder.tvOrderProgressTime.setVisibility(0);
        } else {
            orderProgressViewHolder.tvOrderProgressTitle.setTextColor(Color.rgb(176, 176, 176));
            orderProgressViewHolder.tvOrderProgressTime.setTextColor(Color.rgb(TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS, TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS));
            orderProgressViewHolder.centerCircle.setImageDrawable(this.f);
            orderProgressViewHolder.lineLeft.setImageDrawable(this.h);
            orderProgressViewHolder.lineRight.setImageDrawable(this.h);
            orderProgressViewHolder.tvOrderProgressTime.setVisibility(4);
        }
        if (i == 0) {
            orderProgressViewHolder.lineLeft.setVisibility(4);
        } else {
            orderProgressViewHolder.lineLeft.setVisibility(0);
        }
        if (i == this.d.size() - 1) {
            orderProgressViewHolder.lineRight.setVisibility(4);
        } else {
            orderProgressViewHolder.lineRight.setVisibility(0);
        }
    }

    /* renamed from: a */
    public OrderProgressViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new OrderProgressViewHolder(LayoutInflater.from(this.b).inflate(R.layout.shop_order_progress_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.d.size();
    }

    static class OrderProgressViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493152)
        ImageView centerCircle;
        @BindView(2131493629)
        ImageView lineLeft;
        @BindView(2131493630)
        ImageView lineRight;
        @BindView(2131494238)
        CustomTextView tvOrderProgressTime;
        @BindView(2131494239)
        CustomTextView tvOrderProgressTitle;

        OrderProgressViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
