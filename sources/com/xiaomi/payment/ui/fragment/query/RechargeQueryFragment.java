package com.xiaomi.payment.ui.fragment.query;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mibi.common.base.IPresenter;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.fragment.query.contract.QueryContract;
import com.xiaomi.payment.ui.fragment.query.presenter.RechargeQueryPresenter;

public class RechargeQueryFragment extends BaseProcessFragment implements QueryContract.View {
    private TextView A;
    private TextView B;
    private TextView C;
    private Button D;
    private View.OnClickListener E = new View.OnClickListener() {
        public void onClick(View view) {
            RechargeQueryFragment.this.a(MibiCodeConstants.f12222a, false);
        }
    };
    private int[] v = {1, 2, 5, 10, 20, 30, 30};
    private String w;
    private long x;
    private boolean y;
    private TextView z;

    public void a(int i, String str, Throwable th) {
    }

    public void e(Bundle bundle) {
    }

    public void f(Bundle bundle) {
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_recharge_progress, viewGroup, false);
        this.z = (TextView) inflate.findViewById(R.id.progress_summary);
        this.A = (TextView) inflate.findViewById(R.id.progress_warning);
        this.B = (TextView) inflate.findViewById(R.id.progress_error);
        this.C = (TextView) inflate.findViewById(R.id.progress_hint);
        this.D = (Button) inflate.findViewById(R.id.button_progress);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.w = bundle.getString("title");
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        this.x = memoryStorage.a(this.t, MibiConstants.hg, 0);
        if (this.x <= 0) {
            throw new IllegalArgumentException("mRechargeMibi should be greater than 0");
        }
    }

    public IPresenter onCreatePresenter() {
        return new RechargeQueryPresenter();
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        this.y = false;
        a((CharSequence) this.w);
        b(R.string.mibi_btn_prev);
        R();
        ((RechargeQueryPresenter) H_()).a(this.v);
    }

    private void R() {
        String a2 = Utils.a(this.x);
        this.z.setText(getString(R.string.mibi_progress_submit_recharge_summary, new Object[]{a2}));
        this.D.setText(R.string.mibi_btn_query);
        this.D.setEnabled(false);
        e((String) null);
    }

    public void a(long j) {
        this.D.setText(getString(R.string.mibi_btn_count_down_auto, new Object[]{Long.valueOf(j)}));
        if (this.B.getVisibility() == 8) {
            this.C.setVisibility(0);
        }
    }

    public void b(long j) {
        this.D.setText(getString(R.string.mibi_btn_count_down_auto, new Object[]{Long.valueOf(j)}));
    }

    public void Q() {
        this.C.setVisibility(8);
        this.D.setText(R.string.mibi_btn_query);
    }

    public void a(int i, boolean z2) {
        if (z2) {
            b(getString(R.string.mibi_progress_querying), false);
        } else {
            M();
        }
    }

    public void a(long j, String str, int i) {
        String a2 = Utils.a(j);
        this.z.setText(getString(R.string.mibi_progress_submit_recharge_summary, new Object[]{a2}));
        e(str);
        this.D.setText(R.string.mibi_btn_query);
        this.D.setEnabled(false);
    }

    public void N() {
        this.y = true;
        L();
    }

    public void O() {
        this.y = true;
        c(1002);
        String a2 = Utils.a(this.x);
        this.z.setText(getString(R.string.mibi_progress_submit_recharge_summary, new Object[]{a2}));
        this.A.setVisibility(0);
        this.A.setText(R.string.mibi_progress_warning_check_with_contact);
        e((String) null);
        this.D.setText(R.string.mibi_btn_recharge_other);
        this.D.setOnClickListener(this.E);
        this.D.setEnabled(true);
    }

    private void e(String str) {
        this.C.setVisibility(8);
        if (TextUtils.isEmpty(str)) {
            this.B.setVisibility(8);
            return;
        }
        this.B.setVisibility(0);
        this.B.setText(str);
    }

    public void g(Bundle bundle) {
        Class cls;
        if (bundle == null) {
            bundle = new Bundle();
        }
        Bundle bundle2 = bundle;
        bundle2.putString("title", this.w);
        if (Utils.b()) {
            cls = PadDialogActivity.class;
        } else {
            cls = PhoneCommonActivity.class;
        }
        a(RechargeResultFragment.class, bundle2, 0, (String) null, cls);
    }

    public void y() {
        if (this.y) {
            g(false);
        }
    }
}
