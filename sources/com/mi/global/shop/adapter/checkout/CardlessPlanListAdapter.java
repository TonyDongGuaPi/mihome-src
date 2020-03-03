package com.mi.global.shop.adapter.checkout;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.newmodel.checkout.NewCardlessEMIPlanResult;
import com.mi.global.shop.widget.CustomTextView;
import java.util.Iterator;

public class CardlessPlanListAdapter extends ArrayAdapter<NewCardlessEMIPlanResult.CardlessEMIPlanOption> {

    /* renamed from: a  reason: collision with root package name */
    public boolean f5528a = true;

    public class ViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private ViewHolder f5530a;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.f5530a = viewHolder;
            viewHolder.mCardlessPlanChecked = (ImageView) Utils.findRequiredViewAsType(view, R.id.iv_cardless_plan_checked, "field 'mCardlessPlanChecked'", ImageView.class);
            viewHolder.mCardlessPlanContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.ll_cardless_plan_container, "field 'mCardlessPlanContainer'", LinearLayout.class);
            viewHolder.mCardlessScheme = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.tv_cardless_emi_scheme, "field 'mCardlessScheme'", CustomTextView.class);
            viewHolder.mCardlessPayment = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.cardless_emi_down_payment, "field 'mCardlessPayment'", CustomTextView.class);
            viewHolder.mCardlessTotalAmount = (CustomTextView) Utils.findRequiredViewAsType(view, R.id.cardless_emi_total_amount, "field 'mCardlessTotalAmount'", CustomTextView.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.f5530a;
            if (viewHolder != null) {
                this.f5530a = null;
                viewHolder.mCardlessPlanChecked = null;
                viewHolder.mCardlessPlanContainer = null;
                viewHolder.mCardlessScheme = null;
                viewHolder.mCardlessPayment = null;
                viewHolder.mCardlessTotalAmount = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public CardlessPlanListAdapter(Context context) {
        super(context);
    }

    public View a(Context context, int i, NewCardlessEMIPlanResult.CardlessEMIPlanOption cardlessEMIPlanOption, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_cardless_plan_list_item, viewGroup, false);
        inflate.setTag(new ViewHolder(inflate));
        return inflate;
    }

    public void a(View view, int i, final NewCardlessEMIPlanResult.CardlessEMIPlanOption cardlessEMIPlanOption) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (cardlessEMIPlanOption.isDefault) {
            viewHolder.mCardlessPlanContainer.setBackgroundResource(R.drawable.shop_cardless_plan_select_item_bg);
            viewHolder.mCardlessPlanChecked.setVisibility(0);
        } else {
            viewHolder.mCardlessPlanChecked.setVisibility(8);
            viewHolder.mCardlessPlanContainer.setBackgroundResource(R.drawable.shop_cardless_plan_item_bg);
        }
        CustomTextView customTextView = viewHolder.mCardlessScheme;
        customTextView.setText(cardlessEMIPlanOption.numberOfMonths + " " + this.d.getResources().getString(R.string.cardless_emi_months));
        CustomTextView customTextView2 = viewHolder.mCardlessPayment;
        customTextView2.setText(this.d.getResources().getString(R.string.cardless_emi_money) + " " + cardlessEMIPlanOption.totalMonthlyAmount);
        CustomTextView customTextView3 = viewHolder.mCardlessTotalAmount;
        customTextView3.setText(this.d.getResources().getString(R.string.cardless_emi_money) + " " + cardlessEMIPlanOption.totalAmount);
        viewHolder.mCardlessPlanContainer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (CardlessPlanListAdapter.this.f5528a) {
                    CardlessPlanListAdapter.this.d();
                    cardlessEMIPlanOption.isDefault = true;
                    CardlessPlanListAdapter.this.notifyDataSetChanged();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            ((NewCardlessEMIPlanResult.CardlessEMIPlanOption) it.next()).isDefault = false;
        }
    }

    static class ViewHolder {
        @BindView(2131493120)
        CustomTextView mCardlessPayment;
        @BindView(2131493535)
        ImageView mCardlessPlanChecked;
        @BindView(2131493667)
        LinearLayout mCardlessPlanContainer;
        @BindView(2131494187)
        CustomTextView mCardlessScheme;
        @BindView(2131493121)
        CustomTextView mCardlessTotalAmount;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }
    }
}
