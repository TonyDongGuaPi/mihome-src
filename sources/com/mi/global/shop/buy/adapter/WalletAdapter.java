package com.mi.global.shop.buy.adapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.buy.OrderdetailFragment;
import com.mi.global.shop.util.UrlUtil;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import java.util.ArrayList;

public class WalletAdapter extends RecyclerView.Adapter<WalletViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6863a = "WalletAdapter";
    private Context b;
    /* access modifiers changed from: private */
    public ArrayList<OrderdetailFragment.PaymentMethod> c = new ArrayList<>();

    public class WalletViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private WalletViewHolder f6865a;

        @UiThread
        public WalletViewHolder_ViewBinding(WalletViewHolder walletViewHolder, View view) {
            this.f6865a = walletViewHolder;
            walletViewHolder.itemLogo = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.item_logo, "field 'itemLogo'", SimpleDraweeView.class);
            walletViewHolder.itemHint = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.item_hint, "field 'itemHint'", CustomTextView.class);
            walletViewHolder.itemBg = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.item_bg, "field 'itemBg'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            WalletViewHolder walletViewHolder = this.f6865a;
            if (walletViewHolder != null) {
                this.f6865a = null;
                walletViewHolder.itemLogo = null;
                walletViewHolder.itemHint = null;
                walletViewHolder.itemBg = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public WalletAdapter(Context context) {
        this.b = context;
    }

    public void a(ArrayList<OrderdetailFragment.PaymentMethod> arrayList) {
        if (arrayList != null) {
            this.c.clear();
            this.c.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    public void b(ArrayList<OrderdetailFragment.PaymentMethod> arrayList) {
        if (arrayList != null) {
            this.c.addAll(arrayList);
            notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    public void onBindViewHolder(WalletViewHolder walletViewHolder, final int i) {
        OrderdetailFragment.PaymentMethod paymentMethod = this.c.get(i);
        if (!TextUtils.isEmpty(paymentMethod.e)) {
            FrescoUtils.a(UrlUtil.a(paymentMethod.e), walletViewHolder.itemLogo);
        }
        if (TextUtils.isEmpty(paymentMethod.b)) {
            walletViewHolder.itemHint.setVisibility(8);
        } else {
            walletViewHolder.itemHint.setText(paymentMethod.b);
            walletViewHolder.itemHint.setVisibility(0);
        }
        if (paymentMethod.h) {
            walletViewHolder.itemBg.setBackgroundResource(R.drawable.shop_wallet_select_bg);
        } else {
            walletViewHolder.itemBg.setBackgroundResource(R.drawable.shop_rectangle_grey_border_bold);
        }
        walletViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (int i = 0; i < WalletAdapter.this.c.size(); i++) {
                    if (i == i) {
                        ((OrderdetailFragment.PaymentMethod) WalletAdapter.this.c.get(i)).h = true;
                    } else {
                        ((OrderdetailFragment.PaymentMethod) WalletAdapter.this.c.get(i)).h = false;
                    }
                }
                WalletAdapter.this.notifyDataSetChanged();
            }
        });
    }

    /* renamed from: a */
    public WalletViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WalletViewHolder(LayoutInflater.from(this.b).inflate(R.layout.shop_pay_wallet_item, viewGroup, false));
    }

    public int getItemCount() {
        return this.c.size();
    }

    static class WalletViewHolder extends RecyclerView.ViewHolder {
        @BindView(2131493504)
        LinearLayout itemBg;
        @BindView(2131493512)
        CustomTextView itemHint;
        @BindView(2131493515)
        SimpleDraweeView itemLogo;

        WalletViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }
}
