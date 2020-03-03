package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.fmsh.nfcos.client.service.constants.Constants;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.utils.Network;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletAPIs;
import com.mobikwik.sdk.ui.data.a;
import com.mobikwik.sdk.ui.data.b;

public class y extends Fragment {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Activity f8416a;
    private Transaction b;
    private Double c;
    /* access modifiers changed from: private */
    public ProgressDialog d;
    private b e;
    private WalletAPIs f;
    /* access modifiers changed from: private */
    public int g;
    /* access modifiers changed from: private */
    public CheckBox h;
    /* access modifiers changed from: private */
    public int i;
    /* access modifiers changed from: private */
    public String j;
    private boolean k;

    private void a(View view) {
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.layout_promo_parent);
        View inflate = LayoutInflater.from(this.f8416a).inflate(R.layout.mk_layout_couponcode, (ViewGroup) null, false);
        inflate.findViewById(R.id.btn_apply_promo).setOnClickListener((View.OnClickListener) null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, R.id.checkbox_promocode);
        viewGroup.addView(inflate, layoutParams);
        viewGroup.requestLayout();
        viewGroup.getViewTreeObserver().addOnGlobalLayoutListener(new ac(this, inflate, viewGroup));
    }

    /* access modifiers changed from: private */
    public void a(boolean z, String str) {
        f fVar = new f();
        Bundle bundle = new Bundle();
        bundle.putString("MSG", str);
        bundle.putString(z ? "NEWUSER" : "ISTODEBIT", "true");
        fVar.setArguments(bundle);
        FragmentTransaction beginTransaction = ((FragmentActivity) this.f8416a).getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.payment_frame, fVar);
        beginTransaction.commit();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (Network.isConnected(this.f8416a)) {
            this.d = ProgressDialog.show(this.f8416a, "", "Processing...", false);
            this.f.debitWallet(this.b.getUser().getEmail(), this.b.getUser().getCell(), this.b.getOrderId(), this.b.getAmount(), this.e.f().getMbkId(), b.b((Context) this.f8416a).i(), true, a.a(this.f8416a, this.b.getUser()), true, this.e.f().getPgResponseUrl(), this.e.f().getMerchantName(), new ah(this));
        }
    }

    /* access modifiers changed from: private */
    public void b(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getView().findViewById(R.id.layout_promo_parent);
        if (z) {
            View inflate = LayoutInflater.from(this.f8416a).inflate(R.layout.mk_layout_couponcode, (ViewGroup) null, false);
            inflate.findViewById(R.id.btn_apply_promo).setOnClickListener((View.OnClickListener) null);
            int height = viewGroup.getHeight();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(3, R.id.checkbox_promocode);
            viewGroup.addView(inflate, layoutParams);
            this.h.bringToFront();
            com.mobikwik.sdk.ui.util.a aVar = new com.mobikwik.sdk.ui.util.a(viewGroup, inflate, height, height + this.g, 1);
            aVar.setDuration(200);
            inflate.startAnimation(aVar);
            inflate.findViewById(R.id.btn_apply_promo).setOnClickListener(new ad(this));
            EditText editText = (EditText) getView().findViewById(R.id.editText_promo);
            editText.requestFocus();
            editText.addTextChangedListener(new ae(this));
            return;
        }
        a();
        this.h.bringToFront();
        View findViewById = viewGroup.findViewById(R.id.layout_promo);
        com.mobikwik.sdk.ui.util.a aVar2 = new com.mobikwik.sdk.ui.util.a(viewGroup, findViewById, viewGroup.getHeight(), viewGroup.getHeight() - findViewById.getHeight(), 2);
        aVar2.a(true);
        findViewById.startAnimation(aVar2);
    }

    /* access modifiers changed from: private */
    public void c(boolean z) {
        this.i = 0;
        this.k = z;
        String str = "";
        if (z && this.h.isChecked()) {
            EditText editText = (EditText) getView().findViewById(R.id.editText_promo);
            String obj = editText.getText().toString();
            if (Utils.isNull(obj)) {
                editText.setError("Enter coupon code");
                editText.requestFocus();
                return;
            }
            Utils.hideKeyBoard(editText);
            str = obj;
        }
        if (Network.isConnected(this.f8416a)) {
            String str2 = "Applying coupon code...";
            if (!z) {
                str2 = "Removing coupon code...";
                str = "nopevalue";
            }
            this.d = ProgressDialog.show(this.f8416a, "", str2, false);
            this.f.applyCBCoupon(str, this.b.getOrderId(), this.b.getAmount(), this.e.f().getMbkId(), b.b((Context) this.f8416a).i(), new af(this, z));
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        View findViewById = getView().findViewById(R.id.layout_promo_result_desc);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        EditText editText = (EditText) getView().findViewById(R.id.editText_promo);
        if (editText != null) {
            editText.setError((CharSequence) null);
        }
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        this.f.generateOtp(this.b.getUser().getEmail(), this.b.getUser().getCell(), this.b.getOrderId(), this.b.getAmount(), this.e.f().getMbkId(), b.b((Context) this.f8416a).i(), this.e.f().getPgResponseUrl(), this.e.f().getMerchantName(), new ai(this, z));
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f8416a = activity;
        Utils.print("WalletRequestHandler: onAttach");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.c = Double.valueOf(getArguments().getDouble(Constants.DataBase.Column4Card.COLUMN_BALANCE));
        b.b((Context) getActivity());
        this.f = WalletAPIs.getInstance("1".equals(b.b((Context) getActivity()).f().getMode()), getActivity());
        View inflate = layoutInflater.inflate(R.layout.mk_frag_wallet_debit_confirmation, viewGroup, false);
        this.b = b.b((Context) this.f8416a).d();
        this.e = b.b((Context) this.f8416a);
        ((TextView) inflate.findViewById(R.id.walletUsr)).setText(Html.fromHtml("Dear <font color=#01b7c2><b>" + this.b.getUser().getEmail() + "</b></font>,<br>"));
        StringBuilder sb = new StringBuilder();
        sb.append("Rs. ");
        sb.append(this.c);
        ((TextView) inflate.findViewById(R.id.walletBalAmt)).setText(sb.toString());
        ((TextView) inflate.findViewById(R.id.walletPayAmt)).setText("Rs. " + String.valueOf(Double.parseDouble(this.b.getAmount())));
        ((Button) inflate.findViewById(R.id.confPay)).setOnClickListener(new z(this));
        this.h = (CheckBox) inflate.findViewById(R.id.checkbox_promocode);
        this.h.setOnTouchListener(new aa(this));
        this.h.setOnCheckedChangeListener(new ab(this));
        a(inflate);
        return inflate;
    }
}
