package com.mi.global.shop.buy;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.buy.model.BankLogos;
import com.mi.global.shop.buy.model.EMIBank;
import com.mi.global.shop.buy.model.EMIPlan;
import com.mi.global.shop.buy.payu.Cards;
import com.mi.global.shop.buy.payu.PayU;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomEditTextView;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.global.shop.widget.NoScrollGridView;
import com.mi.global.shop.widget.NoScrollListView;
import java.util.ArrayList;
import java.util.List;

public class EMIViewWrapper {

    /* renamed from: a  reason: collision with root package name */
    private View f6822a;
    /* access modifiers changed from: private */
    public CustomTextView b;
    /* access modifiers changed from: private */
    public CustomTextView c;
    /* access modifiers changed from: private */
    public CustomTextView d;
    /* access modifiers changed from: private */
    public NoScrollGridView e;
    /* access modifiers changed from: private */
    public BankGridAdapter f;
    /* access modifiers changed from: private */
    public Context g;
    private Activity h;
    /* access modifiers changed from: private */
    public List<EMIBank> i;
    private NoScrollListView j;
    /* access modifiers changed from: private */
    public PlanListAdapter k;
    /* access modifiers changed from: private */
    public EMIBank l;
    /* access modifiers changed from: private */
    public EMICardViewWraper m;
    private boolean n = false;

    public EMIViewWrapper(Context context, View view) {
        this.g = context;
        this.f6822a = view;
        c();
        this.m = new EMICardViewWraper(context, view.findViewById(R.id.emi_card_pane), this.n) {
            /* access modifiers changed from: protected */
            public boolean b() {
                boolean z;
                if (EMIViewWrapper.this.l == null) {
                    return false;
                }
                int i = 0;
                while (true) {
                    if (i >= EMIViewWrapper.this.l.e.size()) {
                        z = false;
                        break;
                    } else if (EMIViewWrapper.this.l.e.get(i).g) {
                        z = true;
                        break;
                    } else {
                        i++;
                    }
                }
                if (!z || !super.b()) {
                    return false;
                }
                return true;
            }

            public void a(CustomEditTextView customEditTextView, Drawable drawable) {
                super.a((CustomEditTextView) null, (Drawable) null);
            }
        };
        d();
    }

    private void c() {
        if (PayU.W != null && PayU.W.size() > 0 && Cards.k.equals(PayU.W.get(0).b) && PayU.W.get(0).d.booleanValue()) {
            this.n = true;
        }
    }

    private void d() {
        this.i = new ArrayList();
        double amount = ((ConfirmActivity) this.g).getAmount();
        float f2 = Float.MAX_VALUE;
        if (PayU.W != null) {
            for (int i2 = 0; i2 < PayU.W.size(); i2++) {
                EMIBank eMIBank = PayU.W.get(i2);
                if (eMIBank.f6884a < f2) {
                    f2 = eMIBank.f6884a;
                }
                if (amount >= ((double) eMIBank.f6884a)) {
                    this.i.add(eMIBank);
                } else if (amount > 3000.0d && i2 == 0 && Cards.k.equals(eMIBank.b)) {
                    this.i.add(eMIBank);
                }
            }
        }
        if (this.i.size() == 0) {
            this.f6822a.findViewById(R.id.emi_pane).setVisibility(8);
            ((LinearLayout) this.f6822a.findViewById(R.id.ll_emi_less_than_min)).setVisibility(0);
            ((CustomTextView) this.f6822a.findViewById(R.id.emi_less_than_min_tip)).setText(this.g.getResources().getString(R.string.emi_amount_less_than_min, new Object[]{Float.valueOf(f2)}));
            ((Button) this.f6822a.findViewById(R.id.bt_back_pay)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ((Activity) EMIViewWrapper.this.g).onBackPressed();
                }
            });
            return;
        }
        this.f6822a.findViewById(R.id.emi_pane).setVisibility(0);
        this.f6822a.findViewById(R.id.ll_emi_less_than_min).setVisibility(8);
        if (this.i.size() > 0) {
            EMIBank eMIBank2 = this.i.get(0);
            if (Cards.k.equals(eMIBank2.b) && !eMIBank2.d.booleanValue() && this.i.size() > 1) {
                eMIBank2 = this.i.get(1);
            }
            eMIBank2.f = true;
            this.m.b(eMIBank2.h);
            if (eMIBank2.e != null && eMIBank2.e.size() > 0) {
                this.m.e(eMIBank2.e.get(0).f6885a);
                this.m.d(eMIBank2.e.get(0).e);
                this.m.a(eMIBank2.b);
            }
            for (int i3 = 0; i3 < this.i.size(); i3++) {
                EMIBank eMIBank3 = this.i.get(i3);
                if (eMIBank3.e != null && eMIBank3.e.size() > 0) {
                    eMIBank3.e.get(0).g = true;
                }
            }
        }
        this.b = (CustomTextView) this.f6822a.findViewById(R.id.tv_bank_special);
        this.c = (CustomTextView) this.f6822a.findViewById(R.id.tv_month_special);
        this.d = (CustomTextView) this.f6822a.findViewById(R.id.tv_bfl_less_than_min);
        if (this.i.size() > 0) {
            EMIBank eMIBank4 = this.i.get(0);
            if (Cards.k.equals(eMIBank4.b) && !eMIBank4.d.booleanValue() && this.i.size() > 1) {
                eMIBank4 = this.i.get(1);
            }
            this.b.setText(eMIBank4.g);
            if (eMIBank4.e.size() > 0) {
                this.c.setText(eMIBank4.e.get(0).h);
            }
        }
        this.e = (NoScrollGridView) this.f6822a.findViewById(R.id.bank_grid_view);
        this.f = new BankGridAdapter(this.g);
        this.f.a(this.i);
        this.e.setAdapter(this.f);
        this.j = (NoScrollListView) this.f6822a.findViewById(R.id.plan_list_view);
        this.k = new PlanListAdapter(this.g);
        this.j.setAdapter(this.k);
        a();
    }

    public void a() {
        this.l = null;
        int i2 = 0;
        while (true) {
            if (i2 >= this.i.size()) {
                break;
            } else if (this.i.get(i2).f) {
                this.l = this.i.get(i2);
                break;
            } else {
                i2++;
            }
        }
        if (this.l != null) {
            this.k.a(this.l.e);
        }
    }

    public class BankGridAdapter extends ArrayAdapter<EMIBank> {
        public BankGridAdapter(Context context) {
            super(context);
        }

        public View a(Context context, int i, EMIBank eMIBank, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.shop_buy_confirm_payment_emi_bank, (ViewGroup) null);
            BankViewHolder bankViewHolder = new BankViewHolder();
            bankViewHolder.c = (SimpleDraweeView) inflate.findViewById(R.id.bank_logo);
            bankViewHolder.f6826a = (ImageView) inflate.findViewById(R.id.bank_logo_border);
            bankViewHolder.b = (ImageView) inflate.findViewById(R.id.bank_logo_corner);
            bankViewHolder.d = (CustomTextView) inflate.findViewById(R.id.bank_no_cost_emi);
            inflate.setTag(bankViewHolder);
            return inflate;
        }

        public void a(View view, final int i, final EMIBank eMIBank) {
            BankViewHolder bankViewHolder = (BankViewHolder) view.getTag();
            if (BankLogos.a(eMIBank.b.toUpperCase()) != null) {
                bankViewHolder.c.setImageDrawable(BankLogos.a(eMIBank.b.toUpperCase()));
            } else if (!TextUtils.isEmpty(eMIBank.c)) {
                FrescoUtils.a(eMIBank.c, bankViewHolder.c);
            } else {
                bankViewHolder.c.setImageDrawable((Drawable) null);
            }
            if (eMIBank.f) {
                bankViewHolder.f6826a.setVisibility(0);
                bankViewHolder.b.setVisibility(0);
            } else {
                bankViewHolder.f6826a.setVisibility(8);
                bankViewHolder.b.setVisibility(8);
            }
            if (Cards.k.equals(eMIBank.b)) {
                if (eMIBank.d.booleanValue()) {
                    bankViewHolder.d.setVisibility(0);
                    EMIViewWrapper.this.d.setVisibility(8);
                } else {
                    view.setEnabled(false);
                    bankViewHolder.c.setImageDrawable(this.d.getResources().getDrawable(R.drawable.netbank_bfl_grey));
                    EMIViewWrapper.this.d.setText(eMIBank.i);
                    EMIViewWrapper.this.d.setVisibility(0);
                    EMIViewWrapper.this.d.setWidth(EMIViewWrapper.this.e.getColumnWidth());
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiShopStatInterface.a(String.format("bank%s_click", new Object[]{Integer.valueOf(i + 1)}), EMIfragment.f6830a);
                    EMIBank unused = EMIViewWrapper.this.l = eMIBank;
                    if (EMIViewWrapper.this.l.b.equals(Cards.k)) {
                        MiShopStatInterface.a("pay_channel_click", EMIfragment.f6830a, "key", EMIViewWrapper.this.l.b);
                        EMIViewWrapper.this.m.a(true);
                        EMIViewWrapper.this.m.b(EMIViewWrapper.this.l.h);
                    } else {
                        EMIViewWrapper.this.m.a(false);
                    }
                    EMIViewWrapper.this.m.a(EMIViewWrapper.this.l.b);
                    eMIBank.f = true;
                    for (int i = 0; i < EMIViewWrapper.this.i.size(); i++) {
                        if (!((EMIBank) EMIViewWrapper.this.i.get(i)).equals(eMIBank)) {
                            ((EMIBank) EMIViewWrapper.this.i.get(i)).f = false;
                        }
                    }
                    EMIViewWrapper.this.f.notifyDataSetChanged();
                    if (!(EMIViewWrapper.this.l == null || EMIViewWrapper.this.k == null)) {
                        EMIViewWrapper.this.k.a(EMIViewWrapper.this.l.e);
                    }
                    EMIViewWrapper.this.b.setText(eMIBank.g);
                    if (eMIBank.e.size() > 0) {
                        EMIViewWrapper.this.c.setText(eMIBank.e.get(0).h);
                    }
                }
            });
        }
    }

    public class PlanListAdapter extends ArrayAdapter<EMIPlan> {
        private Context b;

        public PlanListAdapter(Context context) {
            super(context);
            this.b = context;
        }

        public View a(Context context, int i, EMIPlan eMIPlan, ViewGroup viewGroup) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.shop_buy_confirm_payment_emi_plan, (ViewGroup) null);
            PlanViewHolder planViewHolder = new PlanViewHolder();
            planViewHolder.e = (CustomTextView) inflate.findViewById(R.id.emi_tenure);
            planViewHolder.d = (CustomTextView) inflate.findViewById(R.id.emi_monthly_installment);
            planViewHolder.c = (CustomTextView) inflate.findViewById(R.id.emi_interest);
            planViewHolder.b = (ImageView) inflate.findViewById(R.id.plan_border);
            planViewHolder.f6829a = (ImageView) inflate.findViewById(R.id.plan_corner);
            inflate.setTag(planViewHolder);
            return inflate;
        }

        public void a(View view, final int i, final EMIPlan eMIPlan) {
            PlanViewHolder planViewHolder = (PlanViewHolder) view.getTag();
            if (eMIPlan.g) {
                planViewHolder.b.setVisibility(0);
                planViewHolder.f6829a.setVisibility(0);
            } else {
                planViewHolder.b.setVisibility(8);
                planViewHolder.f6829a.setVisibility(8);
            }
            planViewHolder.e.setText(eMIPlan.f);
            CustomTextView customTextView = planViewHolder.d;
            customTextView.setText(LocaleHelper.e() + eMIPlan.d);
            planViewHolder.c.setText(eMIPlan.c);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiShopStatInterface.a(String.format("plan%s_click", new Object[]{Integer.valueOf(i + 1)}), EMIfragment.f6830a);
                    if (Cards.k.equals(EMIViewWrapper.this.l.b)) {
                        MiShopStatInterface.a("pay_plan_click", EMIfragment.f6830a, "key", eMIPlan.f);
                    }
                    eMIPlan.g = true;
                    for (int i = 0; i < EMIViewWrapper.this.l.e.size(); i++) {
                        if (!EMIViewWrapper.this.l.e.get(i).equals(eMIPlan)) {
                            EMIViewWrapper.this.l.e.get(i).g = false;
                        }
                    }
                    EMIViewWrapper.this.k.notifyDataSetChanged();
                    EMIViewWrapper.this.c.setText(eMIPlan.h);
                    EMIViewWrapper.this.m.e(eMIPlan.f6885a);
                    EMIViewWrapper.this.m.d(eMIPlan.e);
                    EMIViewWrapper.this.m.c(eMIPlan.f);
                    EMIViewWrapper.this.m.a((CustomEditTextView) null, (Drawable) null);
                }
            });
        }
    }

    static class BankViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f6826a;
        ImageView b;
        SimpleDraweeView c;
        CustomTextView d;

        BankViewHolder() {
        }
    }

    static class PlanViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f6829a;
        ImageView b;
        CustomTextView c;
        CustomTextView d;
        CustomTextView e;

        PlanViewHolder() {
        }
    }

    public void b() {
        d();
    }
}
