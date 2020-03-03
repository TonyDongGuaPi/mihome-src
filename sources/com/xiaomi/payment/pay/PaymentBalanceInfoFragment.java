package com.xiaomi.payment.pay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.task.rxjava.RxCheckPaymentTask;
import java.util.ArrayList;

public class PaymentBalanceInfoFragment extends BaseProcessFragment {
    private CheckBox A;
    private View B;
    private TextView C;
    private TextView D;
    private CheckBox E;
    private TextView F;
    private CheckBox G;
    private Button H;
    private long I;
    private long J;
    private String K;
    private long L;
    private long M;
    /* access modifiers changed from: private */
    public ArrayList<RxCheckPaymentTask.Result.DiscountGiftCard> N;
    /* access modifiers changed from: private */
    public int O;
    /* access modifiers changed from: private */
    public boolean P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private ViewStub T;
    /* access modifiers changed from: private */
    public TableRow[] U;
    private View.OnClickListener V = new View.OnClickListener() {
        public void onClick(View view) {
            PaymentBalanceInfoFragment.this.N();
            PaymentBalanceInfoFragment.this.E();
        }
    };
    private View.OnClickListener W = new View.OnClickListener() {
        public void onClick(View view) {
            PaymentBalanceInfoFragment.this.N();
            PaymentBalanceInfoFragment.this.E();
        }
    };
    private TextView v;
    private ImageView w;
    private View x;
    private TextView y;
    private TextView z;

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, "Balance:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "Balance:");
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_payment_balance_info, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.layout_action_bar);
        this.v = (TextView) findViewById.findViewById(R.id.action_bar_title);
        this.v.setText(R.string.mibi_order_balance_title);
        this.w = (ImageView) findViewById.findViewById(R.id.back_arrow);
        this.w.setVisibility(0);
        this.w.setOnClickListener(this.W);
        this.x = inflate.findViewById(R.id.mibi_giftcard);
        this.y = (TextView) inflate.findViewById(R.id.mibi_giftcard_label);
        this.z = (TextView) inflate.findViewById(R.id.text_mibi_giftcard_value);
        this.A = (CheckBox) inflate.findViewById(R.id.checkbox_mibi_giftcard);
        this.B = inflate.findViewById(R.id.partner_giftcard);
        this.C = (TextView) inflate.findViewById(R.id.text_market_giftcard_label);
        this.D = (TextView) inflate.findViewById(R.id.text_market_giftcard_value);
        this.E = (CheckBox) inflate.findViewById(R.id.checkbox_market_giftcard);
        this.F = (TextView) inflate.findViewById(R.id.text_mibi_balance_value);
        this.G = (CheckBox) inflate.findViewById(R.id.checkbox_mibi_balance);
        this.H = (Button) inflate.findViewById(R.id.button_confirm);
        this.H.setOnClickListener(this.V);
        this.T = (ViewStub) inflate.findViewById(R.id.discount_gift_card_stub);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.Q = bundle.getBoolean(MibiConstants.eF, true);
        this.R = bundle.getBoolean(MibiConstants.eH, true);
        this.S = bundle.getBoolean(MibiConstants.eK, true);
        this.J = bundle.getLong("giftcardValue", 0);
        this.K = bundle.getString(MibiConstants.eI);
        this.L = bundle.getLong(MibiConstants.eJ, 0);
        this.M = bundle.getLong("balance", 0);
        this.I = bundle.getLong("price", 0);
        this.N = (ArrayList) bundle.getSerializable(MibiConstants.gN);
        this.O = bundle.getInt(PaymentOrderInfoFragment.z, -1);
        this.P = bundle.getBoolean(PaymentOrderInfoFragment.A, false);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        long j = this.J + this.L + this.M;
        this.A.setChecked(this.Q);
        this.E.setChecked(this.R);
        this.G.setChecked(this.S);
        if (this.I <= j) {
            this.A.setEnabled(false);
            this.E.setEnabled(false);
            this.G.setEnabled(false);
        }
        if (this.L == 0) {
            this.B.setVisibility(8);
        } else {
            this.B.setVisibility(0);
            this.C.setText(R.string.mibi_giftcard_payment);
            this.D.setText(Utils.a(this.L));
        }
        if (this.J == 0) {
            this.x.setVisibility(8);
        } else {
            this.x.setVisibility(0);
            this.y.setText(R.string.mibi_giftcard_payment);
            this.z.setText(Utils.a(this.J));
        }
        if (this.L > 0) {
            this.C.setText(getString(R.string.mibi_partner_giftcard_payment_with_append, new Object[]{this.K}));
        } else if (this.J > 0) {
            this.y.setText(R.string.mibi_giftcard_payment_with_append);
        }
        if (this.M == 0) {
            this.G.setEnabled(false);
        }
        this.F.setText(Utils.a(this.M));
        if (this.N != null && this.N.size() > 0) {
            new DiscountGiftCardBuilder((TableLayout) this.T.inflate().findViewById(R.id.table_layout)).a();
        }
    }

    public void y() {
        N();
        super.y();
    }

    /* access modifiers changed from: private */
    public void N() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(MibiConstants.eF, this.A.isChecked());
        bundle.putBoolean(MibiConstants.eH, this.E.isChecked());
        bundle.putBoolean(MibiConstants.eK, this.G.isChecked());
        bundle.putInt(PaymentOrderInfoFragment.z, this.O);
        b(-1, bundle);
    }

    private class DiscountGiftCardBuilder {
        private TableLayout b;

        public DiscountGiftCardBuilder(TableLayout tableLayout) {
            this.b = tableLayout;
        }

        public void a() {
            if (PaymentBalanceInfoFragment.this.N != null && PaymentBalanceInfoFragment.this.N.size() > 0) {
                TableRow[] unused = PaymentBalanceInfoFragment.this.U = new TableRow[PaymentBalanceInfoFragment.this.N.size()];
                int i = 0;
                while (i < PaymentBalanceInfoFragment.this.N.size()) {
                    RxCheckPaymentTask.Result.DiscountGiftCard discountGiftCard = (RxCheckPaymentTask.Result.DiscountGiftCard) PaymentBalanceInfoFragment.this.N.get(i);
                    TableRow b2 = b();
                    ((TextView) b2.findViewById(R.id.text_giftcard_name)).setText(PaymentBalanceInfoFragment.this.getString(R.string.mibi_giftcard_discount_detail, new Object[]{Utils.a(discountGiftCard.mOrderFeeRequiredValue)}));
                    ((TextView) b2.findViewById(R.id.text_mibi_balance_value)).setText(Utils.a(discountGiftCard.mGiftCardValue));
                    CheckBox checkBox = (CheckBox) b2.findViewById(R.id.giftcard_checkbox);
                    checkBox.setChecked(PaymentBalanceInfoFragment.this.O == i);
                    checkBox.setEnabled(!PaymentBalanceInfoFragment.this.P);
                    this.b.addView(b2);
                    PaymentBalanceInfoFragment.this.U[i] = b2;
                    i++;
                }
            }
        }

        private TableRow b() {
            TableRow tableRow = (TableRow) ((LayoutInflater) PaymentBalanceInfoFragment.this.getActivity().getSystemService("layout_inflater")).inflate(R.layout.mibi_payment_balance_info_item, (ViewGroup) null);
            ((CheckBox) tableRow.findViewById(R.id.giftcard_checkbox)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DiscountGiftCardBuilder.this.a(view);
                }
            });
            return tableRow;
        }

        /* access modifiers changed from: private */
        public void a(View view) {
            for (int i = 0; i < PaymentBalanceInfoFragment.this.U.length; i++) {
                CheckBox checkBox = (CheckBox) PaymentBalanceInfoFragment.this.U[i].findViewById(R.id.giftcard_checkbox);
                if (view == checkBox) {
                    int unused = PaymentBalanceInfoFragment.this.O = checkBox.isChecked() ? i : -1;
                } else {
                    checkBox.setChecked(false);
                }
            }
        }
    }
}
