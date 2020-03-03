package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.ProgressButton;
import com.mibi.common.data.CheckableArrayAdapter;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.channel.contract.PaytoolContract;
import com.xiaomi.payment.channel.presenter.PaytoolPresenter;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.PaytoolRechargeMethod;
import com.xiaomi.payment.ui.component.DenominationEditText;
import com.xiaomi.payment.ui.component.DenominationGridView;
import com.xiaomi.payment.ui.fragment.query.RechargeQueryFragment;
import java.util.ArrayList;

public class PaytoolFragment extends BaseProcessFragment implements PaytoolContract.View {
    private static final String v = "PaytoolFragment";
    private String A;
    private PaytoolRechargeMethod B;
    private ArrayList<Long> C;
    /* access modifiers changed from: private */
    public long D;
    /* access modifiers changed from: private */
    public long E;
    private String F;
    private String G;
    private String H;
    /* access modifiers changed from: private */
    public EntryData I;
    /* access modifiers changed from: private */
    public long J = 0;
    private boolean K = false;
    private View.OnClickListener L = new View.OnClickListener() {
        public void onClick(View view) {
            PaytoolFragment.this.y.startProgress();
            PaytoolFragment.this.a(((PaytoolPresenter) PaytoolFragment.this.H_()).i_());
        }
    };
    private DenominationGridView w;
    private TextView x;
    /* access modifiers changed from: private */
    public ProgressButton y;
    private TextView z;

    public void a(int i, boolean z2) {
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_paytool, (ViewGroup) null);
        this.w = (DenominationGridView) inflate.findViewById(R.id.denomination);
        this.x = (TextView) inflate.findViewById(R.id.money_value);
        this.y = (ProgressButton) inflate.findViewById(R.id.button_recharge);
        this.z = (TextView) inflate.findViewById(R.id.contentHint);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.B = (PaytoolRechargeMethod) bundle.getSerializable(MibiConstants.cE);
        this.F = bundle.getString(MibiConstants.fQ, "");
        this.A = this.B.mTitle;
        this.C = this.B.mMibiValues;
        this.D = this.B.mMinMibiValue;
        this.E = this.B.mMaxMibiValue;
        this.G = this.B.mContentHint;
        this.H = this.B.mDiscountDesc;
        this.I = this.B.mContentHintEntryData;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.A);
        b(R.string.mibi_btn_prev);
        if (bundle == null) {
            this.K = false;
        } else {
            this.K = true;
        }
        this.w.setEditable(true);
        this.w.setUnit(getString(R.string.mibi_denomination_mibi_unit));
        this.w.setData(this.C);
        this.w.setEditItemMaxMinDenomination(this.E, this.D);
        this.w.setOnEditChangedListener(new DenominationEditText.OnDenominationEditChangedListener() {
            public void a(long j) {
                if (j <= 0) {
                    long unused = PaytoolFragment.this.J = 0;
                } else {
                    long unused2 = PaytoolFragment.this.J = j;
                }
                PaytoolFragment.this.c(PaytoolFragment.this.J);
            }

            public void b(long j) {
                Toast.makeText(PaytoolFragment.this.getActivity(), PaytoolFragment.this.getString(R.string.mibi_recharge_grid_error_denomination, new Object[]{Utils.a(PaytoolFragment.this.D), Utils.a(PaytoolFragment.this.E)}), 0).show();
            }
        });
        this.w.setOnItemSelectedListener(new CheckableArrayAdapter.OnItemSelectedListener<Long>() {
            public void a(Long l) {
                long unused = PaytoolFragment.this.J = l.longValue();
                PaytoolFragment.this.c(PaytoolFragment.this.J);
            }
        });
        a(this.F, "0");
        this.y.setOnClickListener(this.L);
        if (!TextUtils.isEmpty(this.G)) {
            this.z.setVisibility(0);
            this.z.getPaint().setFlags(8);
            this.z.setText(this.G);
            this.z.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PaytoolFragment.this.I != null) {
                        EntryManager.a().a((Fragment) PaytoolFragment.this, PaytoolFragment.this.I, (Bundle) null, -1);
                    }
                }
            });
        } else {
            this.z.setVisibility(8);
        }
        b(this.C.get(0).longValue());
    }

    private void b(long j) {
        this.J = j;
        this.w.setItemSelected(Long.valueOf(j));
        c(j);
    }

    /* access modifiers changed from: private */
    public void c(long j) {
        a(this.F, Utils.a(((PaytoolPresenter) H_()).b(j)));
    }

    private void a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        if (TextUtils.isEmpty(str)) {
            sb.append(getString(R.string.mibi_recharge_value_money, new Object[]{str2}));
        } else {
            sb.append(getString(R.string.mibi_recharge_value_money_special_unit, new Object[]{str, str2}));
        }
        if (!TextUtils.isEmpty(this.H)) {
            sb.append(getString(R.string.mibi_string_with_bracket, new Object[]{this.H}));
        }
        this.x.setText(sb);
    }

    public IPresenter onCreatePresenter() {
        return new PaytoolPresenter();
    }

    public void k() {
        super.k();
        this.y.stopProgress();
        ((PaytoolPresenter) H_()).j_();
        MistatisticUtils.a((Fragment) this, "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "Recharge:");
    }

    /* access modifiers changed from: protected */
    public void A() {
        super.A();
        ((PaytoolPresenter) H_()).a(this.J);
    }

    /* access modifiers changed from: protected */
    public void B() {
        super.B();
        this.y.stopProgress();
    }

    public void N() {
        this.y.stopProgress();
        Toast.makeText(getActivity(), getString(R.string.mibi_recharge_grid_error_denomination, new Object[]{Utils.a(this.D), Utils.a(this.E)}), 0).show();
    }

    public void a(long j) {
        this.y.stopProgress();
        Bundle bundle = new Bundle();
        bundle.putString("title", this.B.mTitle);
        a(RechargeQueryFragment.class, bundle, 0, (String) null, Utils.b() ? PadDialogActivity.class : PhoneCommonActivity.class);
    }

    public void O() {
        this.y.stopProgress();
    }

    public void a(PaytoolContract.Function<Fragment> function) {
        function.a(this);
    }

    public void Q() {
        this.y.stopProgress();
        L();
    }

    public void a(int i, String str, Throwable th) {
        this.y.stopProgress();
        ChannelUtils.a((StepFragment) this, i, str);
    }

    public void a(Bundle bundle, int i) {
        Class cls;
        if (Utils.b()) {
            cls = PadDialogActivity.class;
        } else {
            cls = PhoneCommonActivity.class;
        }
        a(PaytoolWebFragment.class, bundle, 0, (String) null, cls);
    }

    public void a(int i, int i2, Bundle bundle) {
        Log.v(v, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
    }
}
