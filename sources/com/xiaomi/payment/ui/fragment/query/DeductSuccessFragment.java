package com.xiaomi.payment.ui.fragment.query;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.component.AutoCountDownButton;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;

public class DeductSuccessFragment extends BaseFragment {
    private static final int v = 5;
    AutoCountDownButton.CountDownListener t = new AutoCountDownButton.CountDownListener() {
        public void a() {
            DeductSuccessFragment.this.x.setText(DeductSuccessFragment.this.f7451a.getString(R.string.mibi_button_count_down_finish, new Object[]{5}));
        }

        public void a(int i) {
            DeductSuccessFragment.this.x.setText(DeductSuccessFragment.this.f7451a.getString(R.string.mibi_button_count_down_finish, new Object[]{Integer.valueOf(i)}));
        }

        public void b() {
            DeductSuccessFragment.this.x.performClick();
        }
    };
    View.OnClickListener u = new View.OnClickListener() {
        public void onClick(View view) {
            if (DeductSuccessFragment.this.getActivity() != null) {
                DeductSuccessFragment.this.getActivity().finish();
            }
        }
    };
    private TextView w;
    /* access modifiers changed from: private */
    public AutoCountDownButton x;
    private String y;
    private boolean z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_deduct_success, (ViewGroup) null);
        this.w = (TextView) inflate.findViewById(R.id.text_result_success_sub);
        this.x = (AutoCountDownButton) inflate.findViewById(R.id.button_close);
        if (this.z) {
            this.w.setText(getResources().getString(R.string.mibi_signing_success_sub, new Object[]{this.y}));
        } else {
            this.w.setText(getResources().getString(R.string.mibi_deduct_success_sub, new Object[]{this.y}));
        }
        this.x.setOnClickListener(this.u);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.y = bundle.getString(MibiConstants.gB);
        this.z = bundle.getBoolean(MibiConstants.gK, false);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        if (this.z) {
            a(R.string.mibi_signing_success_title);
        } else {
            a(R.string.mibi_payment_success_title);
        }
        this.x.setText(this.f7451a.getString(R.string.mibi_button_count_down_finish, new Object[]{5}));
        this.x.setTickNum(5);
        this.x.startTick();
        this.x.setCountDownListener(this.t);
    }

    public void y() {
        super.y();
        this.x.performClick();
    }
}
