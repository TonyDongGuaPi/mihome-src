package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.FormattableEditText;
import com.mibi.common.data.FormatterUtils;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.channel.presenter.PrepaidCardChannelPresenter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.entry.IEntry;
import com.xiaomi.payment.entryData.CameraIntentData;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.PrepaidCardRechargeMethod;
import com.xiaomi.payment.ui.contract.PrepaidCardChannelContract;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PrepaidCardNumberFragment extends BaseProcessFragment implements PrepaidCardChannelContract.View {
    private static final String v = "PrepaidCardNumber";
    private static int w = 11;
    private ImageView A;
    private ImageView B;
    /* access modifiers changed from: private */
    public Button C;
    private String D;
    private boolean E;
    /* access modifiers changed from: private */
    public ArrayList<Integer> F;
    /* access modifiers changed from: private */
    public ArrayList<Integer> G;
    /* access modifiers changed from: private */
    public String H;
    /* access modifiers changed from: private */
    public String I;
    /* access modifiers changed from: private */
    public Long J;
    /* access modifiers changed from: private */
    public Bundle K;
    /* access modifiers changed from: private */
    public HashMap<String, PrepaidCardRechargeMethod.CarrierInfo> L;
    private ArrayList<PrepaidCardRechargeMethod.CarrierInfo> M;
    private View.OnClickListener N = new View.OnClickListener() {
        public void onClick(View view) {
            String string = PrepaidCardNumberFragment.this.getResources().getString(R.string.mibi_scan_prompt_number, new Object[]{PrepaidCardNumberFragment.this.I});
            String string2 = PrepaidCardNumberFragment.this.getResources().getString(R.string.mibi_scan_prompt_password, new Object[]{PrepaidCardNumberFragment.this.I});
            Bundle unused = PrepaidCardNumberFragment.this.K = new Bundle();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new CameraIntentData(R.id.number_edit, string, PrepaidCardNumberFragment.this.F));
            arrayList.add(new CameraIntentData(R.id.password_edit, string2, PrepaidCardNumberFragment.this.G));
            PrepaidCardNumberFragment.this.K.putParcelableArrayList(IEntry.b, arrayList);
            PrepaidCardNumberFragment.this.O();
        }
    };
    private View.OnClickListener O = new View.OnClickListener() {
        public void onClick(View view) {
            String string = PrepaidCardNumberFragment.this.getResources().getString(R.string.mibi_scan_prompt_password, new Object[]{PrepaidCardNumberFragment.this.I});
            Bundle unused = PrepaidCardNumberFragment.this.K = new Bundle();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new CameraIntentData(R.id.password_edit, string, PrepaidCardNumberFragment.this.G));
            PrepaidCardNumberFragment.this.K.putParcelableArrayList(IEntry.b, arrayList);
            PrepaidCardNumberFragment.this.O();
        }
    };
    private View.OnClickListener P = new View.OnClickListener() {
        public void onClick(View view) {
            boolean z;
            if (TextUtils.isEmpty(PrepaidCardNumberFragment.this.H)) {
                Toast.makeText(PrepaidCardNumberFragment.this.getActivity(), R.string.mibi_prepaid_error_carrier, 0).show();
            } else if (PrepaidCardNumberFragment.this.J.longValue() <= 0) {
                Toast.makeText(PrepaidCardNumberFragment.this.getActivity(), R.string.mibi_prepaid_error_denomination, 0).show();
            } else {
                String a2 = FormatterUtils.a(PrepaidCardNumberFragment.this.y.getText().toString().trim(), FormatterUtils.FormatterType.TYPE_NORMAL);
                String a3 = FormatterUtils.a(PrepaidCardNumberFragment.this.z.getText().toString().trim(), FormatterUtils.FormatterType.TYPE_NORMAL);
                if (TextUtils.isEmpty(a2)) {
                    Toast.makeText(PrepaidCardNumberFragment.this.getActivity(), R.string.mibi_prepaid_error_card_number, 0).show();
                } else if (TextUtils.isEmpty(a3)) {
                    Toast.makeText(PrepaidCardNumberFragment.this.getActivity(), R.string.mibi_prepaid_error_password, 0).show();
                } else {
                    Iterator<PrepaidCardRechargeMethod.CardPwdLen> it = ((PrepaidCardRechargeMethod.CarrierInfo) PrepaidCardNumberFragment.this.L.get(PrepaidCardNumberFragment.this.H)).mCardPwdLens.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        PrepaidCardRechargeMethod.CardPwdLen next = it.next();
                        if (a2.length() == next.mCardLen && a3.length() == next.mPwdLen) {
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        Toast.makeText(PrepaidCardNumberFragment.this.getActivity(), R.string.mibi_prepaid_error_card_length, 0).show();
                        return;
                    }
                    PrepaidCardNumberFragment.this.C.setClickable(false);
                    ((PrepaidCardChannelPresenter) PrepaidCardNumberFragment.this.H_()).a(a2, a3);
                }
            }
        }
    };
    private TextView x;
    /* access modifiers changed from: private */
    public FormattableEditText y;
    /* access modifiers changed from: private */
    public FormattableEditText z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_prepaid_card_number, viewGroup, false);
        this.x = (TextView) inflate.findViewById(R.id.prepaid_card_order_info);
        this.y = (FormattableEditText) inflate.findViewById(R.id.number_edit);
        this.y.setFormatType(FormatterUtils.FormatterType.TYPE_NORMAL);
        this.z = (FormattableEditText) inflate.findViewById(R.id.password_edit);
        this.z.setFormatType(FormatterUtils.FormatterType.TYPE_NORMAL);
        this.A = (ImageView) inflate.findViewById(R.id.number_camera_btn);
        this.B = (ImageView) inflate.findViewById(R.id.password_camera_btn);
        this.C = (Button) inflate.findViewById(R.id.button_recharge);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.H = bundle.getString("carrier");
        this.I = bundle.getString(MibiConstants.dc);
        this.J = Long.valueOf(bundle.getLong(MibiConstants.f12224de, 0));
        PrepaidCardRechargeMethod prepaidCardRechargeMethod = (PrepaidCardRechargeMethod) getArguments().getSerializable(MibiConstants.cE);
        this.M = prepaidCardRechargeMethod.mCarrierInfos;
        this.D = prepaidCardRechargeMethod.mTitle;
        if (this.M != null) {
            this.L = new HashMap<>();
            for (int i = 0; i < this.M.size(); i++) {
                PrepaidCardRechargeMethod.CarrierInfo carrierInfo = this.M.get(i);
                this.L.put(carrierInfo.mName, carrierInfo);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        if (!TextUtils.isEmpty(this.t)) {
            this.E = memoryStorage.d(this.t, MibiConstants.cZ);
        }
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.D);
        b(R.string.mibi_btn_prev);
        this.x.setText(getString(R.string.mibi_prepaid_card_order_info, new Object[]{this.I, Utils.a(this.J.longValue())}));
        this.C.setOnClickListener(this.P);
        if (EntryManager.a().a((Context) getActivity(), MibiConstants.cB)) {
            this.A.setVisibility(0);
            this.A.setOnClickListener(this.N);
            this.B.setVisibility(0);
            this.B.setOnClickListener(this.O);
            N();
            return;
        }
        this.A.setVisibility(4);
        this.B.setVisibility(4);
    }

    public IPresenter onCreatePresenter() {
        return new PrepaidCardChannelPresenter();
    }

    public void k() {
        super.k();
        this.C.setClickable(true);
        MistatisticUtils.a((Fragment) this, this.E ? "Pay:" : "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, this.E ? "Pay:" : "Recharge:");
    }

    public void a(int i, int i2, Intent intent) {
        Bundle extras;
        super.a(i, i2, intent);
        if (i == w && -1 == i2 && intent != null && (extras = intent.getExtras()) != null) {
            String string = extras.getString(Integer.toString(R.id.number_edit));
            String string2 = extras.getString(Integer.toString(R.id.password_edit));
            if (!TextUtils.isEmpty(string)) {
                this.y.setText(string);
            }
            if (!TextUtils.isEmpty(string2)) {
                this.z.setText(string2);
            }
        }
    }

    private void N() {
        ArrayList<PrepaidCardRechargeMethod.CardPwdLen> arrayList = this.L.get(this.H).mCardPwdLens;
        this.F = new ArrayList<>();
        this.G = new ArrayList<>();
        Iterator<PrepaidCardRechargeMethod.CardPwdLen> it = arrayList.iterator();
        while (it.hasNext()) {
            PrepaidCardRechargeMethod.CardPwdLen next = it.next();
            if (!this.F.contains(Integer.valueOf(next.mCardLen))) {
                this.F.add(Integer.valueOf(next.mCardLen));
            }
            if (!this.G.contains(Integer.valueOf(next.mPwdLen))) {
                this.G.add(Integer.valueOf(next.mPwdLen));
            }
        }
    }

    /* access modifiers changed from: private */
    public void O() {
        a("android.permission.CAMERA");
    }

    /* access modifiers changed from: protected */
    public void A() {
        super.A();
        EntryManager.a().a(MibiConstants.cB, (Fragment) this, this.K, w);
    }

    public void a(long j) {
        a(0, false);
        p().m().a(this.t, MibiConstants.hg, (Object) Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putString("title", this.D);
        bundle.putBoolean(MibiConstants.hi, false);
        ChannelUtils.a((StepFragment) this, bundle, this.E);
    }

    public void a(int i, String str, Throwable th) {
        a(0, false);
        this.C.setClickable(true);
        ChannelUtils.a((StepFragment) this, i, str);
    }

    public void a(int i, boolean z2) {
        if (z2) {
            b(getString(R.string.mibi_progress_prepaid_creating), false);
        } else {
            M();
        }
    }

    public void a(int i, int i2, Bundle bundle) {
        Log.v(v, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
    }
}
