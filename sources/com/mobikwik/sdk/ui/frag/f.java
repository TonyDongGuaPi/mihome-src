package com.mobikwik.sdk.ui.frag;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.mobikwik.sdk.R;
import com.mobikwik.sdk.lib.Constants;
import com.mobikwik.sdk.lib.Transaction;
import com.mobikwik.sdk.lib.TransactionConfiguration;
import com.mobikwik.sdk.lib.utils.Network;
import com.mobikwik.sdk.lib.utils.Utils;
import com.mobikwik.sdk.lib.wallet.WalletAPIs;
import com.mobikwik.sdk.ui.data.b;
import com.mobikwik.sdk.ui.util.d;

public class f extends Fragment implements d.a {

    /* renamed from: a  reason: collision with root package name */
    Intent f8396a = null;
    /* access modifiers changed from: private */
    public b b;
    private TransactionConfiguration c;
    private WalletAPIs d;
    private boolean e;
    /* access modifiers changed from: private */
    public Activity f;
    private d.b g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public Transaction i;
    /* access modifiers changed from: private */
    public ProgressDialog j;
    private boolean k;

    public interface a {
        void a(int i, Intent intent);
    }

    private void b(String str) {
        String str2 = str;
        this.d.getUserBalance(this.i.getUser().getEmail(), this.i.getUser().getCell(), this.i.getOrderId(), this.i.getAmount(), this.c.getMbkId(), b.b((Context) this.f).i(), false, str2, false, !this.c.isDebitWallet(), this.c.getPgResponseUrl(), this.c.getMerchantName(), new i(this));
    }

    private void c(String str) {
        this.d.createWallet(this.i.getUser().getEmail(), this.i.getUser().getCell(), this.i.getOrderId(), this.i.getAmount(), this.c.getMbkId(), b.b((Context) this.f).i(), str, this.c.getPgResponseUrl(), this.c.getMerchantName(), new j(this));
    }

    /* access modifiers changed from: private */
    public void d() {
        if (e()) {
            this.g = d.a(this.h, this.f, false, this);
        }
    }

    private void d(String str) {
        this.d.debitWallet(this.i.getUser().getEmail(), this.i.getUser().getCell(), this.i.getOrderId(), this.i.getAmount(), this.c.getMbkId(), this.b.i(), false, str, false, this.c.getPgResponseUrl(), this.c.getMerchantName(), new k(this));
    }

    private boolean e() {
        return this.f.checkCallingOrSelfPermission("android.permission.RECEIVE_SMS") == 0;
    }

    /* access modifiers changed from: private */
    public void f() {
        if (Network.isConnected(this.f)) {
            this.j = ProgressDialog.show(this.f, "", "Processing...", false);
            b b2 = b.b((Context) this.f);
            this.d.generateOtp(this.i.getUser().getEmail(), this.i.getUser().getCell(), this.i.getOrderId(), this.i.getAmount(), b2.f().getMbkId(), b.b((Context) this.f).i(), b2.f().getPgResponseUrl(), b2.f().getMerchantName(), new l(this));
        }
    }

    public void a() {
        f();
    }

    public void a(View view) {
        EditText editText = (EditText) getView().findViewById(R.id.otp);
        String obj = editText.getText().toString();
        if (obj == null || obj.trim().length() == 0) {
            editText.requestFocus();
            editText.setError("Please Enter OTP");
            return;
        }
        Utils.hideKeyBoard(editText);
        this.f8396a = new Intent(this.f.getIntent());
        if (Network.isConnected(this.f)) {
            this.j = ProgressDialog.show(this.f, "", "Processing...", false);
            if (this.e) {
                d(obj);
            } else if (this.k) {
                c(obj);
            } else {
                b(obj);
            }
        }
    }

    public void a(String str) {
        ((EditText) getView().findViewById(R.id.otp)).setText(str);
        a((View) null);
    }

    public void b() {
    }

    public void c() {
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.f = activity;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.f8396a == null) {
            this.f8396a = this.f.getIntent();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = b.b((Context) getActivity());
        this.c = b.b((Context) getActivity()).f();
        this.d = WalletAPIs.getInstance("1".equals(this.c.getMode()), getActivity());
        this.h = getArguments().getString("MSG");
        this.k = "true".equalsIgnoreCase(getArguments().getString("NEWUSER"));
        this.e = "true".equalsIgnoreCase(getArguments().getString("ISTODEBIT"));
        this.i = b.b((Context) this.f).d();
        View inflate = layoutInflater.inflate(R.layout.mk_frag_otplogin, viewGroup, false);
        String string = getString(R.string.otp_login_msg);
        ((TextView) inflate.findViewById(R.id.otpMsg)).setText(Html.fromHtml(string.replace(Constants.PLACEHOLDER_EMAIL, "<font color=#01b7c2><b>" + this.i.getUser().getEmail() + "</b></font>,<br><br>").replace(Constants.PLACEHOLDER_MOBILE, this.h)));
        Button button = (Button) inflate.findViewById(R.id.LoginButton);
        if (this.e) {
            button.setText("Confirm");
        }
        button.setOnClickListener(new g(this));
        inflate.findViewById(R.id.ResendOtpButton).setOnClickListener(new h(this));
        d();
        return inflate;
    }

    public void onStop() {
        if (this.g != null) {
            this.g.a();
        }
        super.onStop();
    }
}
