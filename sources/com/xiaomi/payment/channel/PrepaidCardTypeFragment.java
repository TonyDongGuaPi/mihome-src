package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.data.CheckableArrayAdapter;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.PrepaidCardRechargeMethod;
import com.xiaomi.payment.ui.component.CarrierGridView;
import com.xiaomi.payment.ui.component.DenominationGridView;
import java.util.ArrayList;
import java.util.HashMap;

public class PrepaidCardTypeFragment extends BaseProcessFragment {
    private static final String v = "PrepaidCardTypeFragment";
    private TextView A;
    private String B;
    private PrepaidCardRechargeMethod C;
    private HashMap<String, PrepaidCardRechargeMethod.CarrierInfo> D;
    private ArrayList<PrepaidCardRechargeMethod.CarrierInfo> E;
    private String F;
    /* access modifiers changed from: private */
    public EntryData G;
    private String H;
    private int I = 0;
    private boolean J;
    private View.OnClickListener K = new View.OnClickListener() {
        public void onClick(View view) {
            PrepaidCardTypeFragment.this.N();
        }
    };
    private CheckableArrayAdapter.OnItemSelectedListener<PrepaidCardRechargeMethod.CarrierInfo> L = new CheckableArrayAdapter.OnItemSelectedListener<PrepaidCardRechargeMethod.CarrierInfo>() {
        public void a(PrepaidCardRechargeMethod.CarrierInfo carrierInfo) {
            if (carrierInfo != null) {
                PrepaidCardTypeFragment.this.x.setData(carrierInfo.mMoneyValues);
                PrepaidCardTypeFragment.this.x.setUnit(PrepaidCardTypeFragment.this.getString(R.string.mibi_denomination_money_unit));
            }
            PrepaidCardTypeFragment.this.y.setText(R.string.mibi_prepaid_picker_hint_2_2_a);
        }
    };
    private CheckableArrayAdapter.OnItemSelectedListener<Long> M = new CheckableArrayAdapter.OnItemSelectedListener<Long>() {
        public void a(Long l) {
            PrepaidCardTypeFragment.this.y.setText(Html.fromHtml(PrepaidCardTypeFragment.this.getString(R.string.mibi_prepaid_picker_hint_2_2_b, new Object[]{Utils.a(l.longValue())})));
        }
    };
    private CarrierGridView w;
    /* access modifiers changed from: private */
    public DenominationGridView x;
    /* access modifiers changed from: private */
    public TextView y;
    private Button z;

    private long a(long j) {
        return j;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_prepaid_card_type, viewGroup, false);
        this.w = (CarrierGridView) inflate.findViewById(R.id.carriers);
        this.x = (DenominationGridView) inflate.findViewById(R.id.denomination);
        this.y = (TextView) inflate.findViewById(R.id.prepaid_hint);
        this.z = (Button) inflate.findViewById(R.id.button_next);
        this.A = (TextView) inflate.findViewById(R.id.contentHint);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.C = (PrepaidCardRechargeMethod) bundle.getSerializable(MibiConstants.cE);
        this.B = this.C.mTitle;
        this.E = this.C.mCarrierInfos;
        if (this.E != null) {
            this.D = new HashMap<>();
            for (int i = 0; i < this.E.size(); i++) {
                PrepaidCardRechargeMethod.CarrierInfo carrierInfo = this.E.get(i);
                this.D.put(carrierInfo.mName, carrierInfo);
                if (carrierInfo.mIsDefault) {
                    this.I = i;
                }
            }
            this.F = this.C.mContentHint;
            this.G = this.C.mContentHintEntryData;
            this.H = this.C.mChannel;
        }
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        if (!TextUtils.isEmpty(this.t)) {
            this.J = memoryStorage.d(this.t, MibiConstants.cZ);
        }
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.B);
        b(R.string.mibi_btn_prev);
        b(MibiCodeConstants.c);
        this.x.setOnItemSelectedListener(this.M);
        this.x.setUnitAlwaysVisible(true);
        this.z.setOnClickListener(this.K);
        this.w.setData(this.E);
        this.w.setNumColumns(this.E.size());
        this.w.setOnItemSelectedListener(this.L);
        if (this.I < this.E.size()) {
            this.w.setPositionSelected(this.I);
        }
        if (!TextUtils.isEmpty(this.F)) {
            this.A.setVisibility(0);
            this.A.getPaint().setFlags(8);
            this.A.setText(this.F);
            this.A.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (PrepaidCardTypeFragment.this.G != null) {
                        EntryManager.a().a((Fragment) PrepaidCardTypeFragment.this, PrepaidCardTypeFragment.this.G, (Bundle) null, -1);
                    }
                }
            });
            return;
        }
        this.A.setVisibility(8);
    }

    public void k() {
        super.k();
        this.z.setClickable(true);
        MistatisticUtils.a((Fragment) this, this.J ? "Pay:" : "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, this.J ? "Pay:" : "Recharge:");
    }

    /* access modifiers changed from: private */
    public void N() {
        PrepaidCardRechargeMethod.CarrierInfo carrierInfo = (PrepaidCardRechargeMethod.CarrierInfo) this.w.getSelectedItem();
        if (carrierInfo == null) {
            Toast.makeText(getActivity(), R.string.mibi_prepaid_error_carrier, 0).show();
            return;
        }
        Long l = (Long) this.x.getSelectedItem();
        if (l == null || l.longValue() <= 0) {
            Toast.makeText(getActivity(), R.string.mibi_prepaid_error_denomination, 0).show();
            return;
        }
        Bundle bundle = (Bundle) getArguments().clone();
        bundle.putString("channel", this.H);
        bundle.putString("carrier", carrierInfo.mName);
        bundle.putString(MibiConstants.dc, carrierInfo.mTitle);
        bundle.putLong(MibiConstants.dd, a(l.longValue()));
        bundle.putLong(MibiConstants.f12224de, l.longValue());
        a(PrepaidCardNumberFragment.class, bundle, 0, (String) null, Utils.b() ? PadDialogActivity.class : PhoneCommonActivity.class);
    }

    public void a(int i, int i2, Bundle bundle) {
        Log.v(v, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
    }
}
