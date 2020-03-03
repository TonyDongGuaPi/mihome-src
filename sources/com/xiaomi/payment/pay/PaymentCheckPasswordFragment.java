package com.xiaomi.payment.pay;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.mibi.common.base.IPresenter;
import com.mibi.common.component.ProgressButton;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.mishopsdk.util.Constants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.pay.contract.PaymentCheckPasswordContract;
import com.xiaomi.payment.pay.presenter.PaymentCheckPasswordPresenter;
import com.xiaomi.payment.platform.R;

public class PaymentCheckPasswordFragment extends BaseProcessFragment implements PaymentCheckPasswordContract.View {
    private View.OnClickListener A = new View.OnClickListener() {
        public void onClick(View view) {
            PaymentCheckPasswordFragment.this.v.startProgress();
            PaymentCheckPasswordFragment.this.x.setVisibility(4);
            String obj = PaymentCheckPasswordFragment.this.y.getText().toString();
            if (TextUtils.isEmpty(obj)) {
                PaymentCheckPasswordFragment.this.a(0, PaymentCheckPasswordFragment.this.getString(R.string.mibi_password_error), (Throwable) null);
                return;
            }
            PaymentCheckPasswordFragment.this.a(PaymentCheckPasswordFragment.this.b.f(), obj);
        }
    };
    private View.OnClickListener B = new View.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(MibiConstants.c(MibiConstants.bs)));
            intent.addFlags(Constants.CALLIGRAPHY_TAG_PRICE);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.addFlags(8388608);
            PaymentCheckPasswordFragment.this.startActivity(intent);
        }
    };
    protected ProgressButton v;
    private TextView w;
    /* access modifiers changed from: private */
    public TextView x;
    /* access modifiers changed from: private */
    public EditText y;
    private TextView z;

    public void a(int i, boolean z2) {
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public IPresenter onCreatePresenter() {
        return new PaymentCheckPasswordPresenter();
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_payment_check_password, (ViewGroup) null);
        this.w = (TextView) inflate.findViewById(R.id.layout_action_bar).findViewById(R.id.action_bar_title);
        this.w.setText(R.string.mibi_payment_with_login_title);
        this.x = (TextView) inflate.findViewById(R.id.error_info);
        this.y = (EditText) inflate.findViewById(R.id.password_edit);
        this.z = (TextView) inflate.findViewById(R.id.password_forget);
        this.z.setOnClickListener(this.B);
        this.v = (ProgressButton) inflate.findViewById(R.id.button_pay);
        this.v.setOnClickListener(this.A);
        return inflate;
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2) {
        ((PaymentCheckPasswordPresenter) H_()).a(getActivity(), str, str2);
    }

    public void a(int i, String str) {
        if (i == 3) {
            this.z.setVisibility(0);
        } else {
            this.z.setVisibility(8);
        }
        a(i, str, (Throwable) null);
    }

    public void a(int i, String str, Throwable th) {
        this.v.stopProgress();
        this.x.setVisibility(0);
        this.x.setText(str);
    }

    public void e(Bundle bundle) {
        b(1104, bundle);
        E();
    }

    public void N() {
        b(-1, (Bundle) null);
        E();
    }

    public void O() {
        this.v.stopProgress();
    }

    public void Q() {
        this.v.stopProgress();
        L();
    }

    public void R() {
        c(9);
        E();
    }

    public void S() {
        this.v.stopProgress();
        this.z.setVisibility(0);
        this.x.setVisibility(0);
        this.x.setText(R.string.mibi_password_error);
    }

    public void o() {
        super.o();
        Utils.a((Context) getActivity(), (View) this.y, false);
    }
}
