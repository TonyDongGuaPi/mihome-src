package com.xiaomi.payment.channel;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.CheckableArrayAdapter;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PadDialogActivity;
import com.mibi.common.ui.PhoneCommonActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.miui.tsmclient.util.Constants;
import com.xiaomi.payment.channel.presenter.MessageChannelPresenter;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.MsgPayRechargeMethod;
import com.xiaomi.payment.ui.component.DenominationGridView;
import com.xiaomi.payment.ui.contract.MessageChannelContract;
import java.util.ArrayList;
import java.util.Iterator;

public class MessageChannelFragment extends BaseProcessFragment implements MessageChannelContract.View {
    private TextView A;
    private TextView B;
    /* access modifiers changed from: private */
    public long C = 0;
    private String D;
    private MsgPayRechargeMethod E;
    private ArrayList<Long> F;
    private boolean G;
    private long H;
    private int I;
    private int J;
    private String K;
    /* access modifiers changed from: private */
    public EntryData L;
    private View.OnClickListener M = new View.OnClickListener() {
        public void onClick(View view) {
            MessageChannelFragment.this.Q();
        }
    };
    protected TextView v;
    protected DenominationGridView w;
    protected Button x;
    /* access modifiers changed from: private */
    public TextView y;
    private TextView z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_msg_pay, (ViewGroup) null);
        this.v = (TextView) inflate.findViewById(R.id.denomination_hint);
        this.w = (DenominationGridView) inflate.findViewById(R.id.denomination);
        this.y = (TextView) inflate.findViewById(R.id.money_value);
        this.z = (TextView) inflate.findViewById(R.id.msg_picker_hint_1_2);
        this.A = (TextView) inflate.findViewById(R.id.msg_picker_hint_2_2);
        this.x = (Button) inflate.findViewById(R.id.button_recharge);
        this.B = (TextView) inflate.findViewById(R.id.contentHint);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.D);
        b(R.string.mibi_btn_prev);
        this.z.setText(Html.fromHtml(getString(R.string.mibi_msg_picker_hint_1_2, new Object[]{Integer.valueOf(this.J), Integer.valueOf(this.I)})));
        this.A.setText(getString(R.string.mibi_msg_picker_hint_2_a, new Object[]{Constants.NFC_CONFIG_INIT_VERSION}));
        this.w.setData(this.F);
        this.w.setUnit(getString(R.string.mibi_denomination_mibi_unit));
        this.w.setOnItemSelectedListener(new CheckableArrayAdapter.OnItemSelectedListener<Long>() {
            public void a(Long l) {
                long unused = MessageChannelFragment.this.C = l.longValue();
                long a2 = ((MessageChannelPresenter) MessageChannelFragment.this.H_()).a(l.longValue());
                MessageChannelFragment.this.y.setText(MessageChannelFragment.this.getString(R.string.mibi_recharge_value_money, new Object[]{Utils.a(a2)}));
            }
        });
        this.w.setVisibility(0);
        a(O());
        this.v.setVisibility(0);
        if (!TextUtils.isEmpty(this.K)) {
            this.B.setVisibility(0);
            this.B.getPaint().setFlags(8);
            this.B.setText(this.K);
            this.B.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (MessageChannelFragment.this.L != null) {
                        EntryManager.a().a((Fragment) MessageChannelFragment.this, MessageChannelFragment.this.L, (Bundle) null, -1);
                    }
                }
            });
        } else {
            this.B.setVisibility(8);
        }
        this.x.setOnClickListener(this.M);
    }

    public IPresenter onCreatePresenter() {
        return new MessageChannelPresenter();
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, this.G ? "Pay:" : "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, this.G ? "Pay:" : "Recharge:");
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.E = (MsgPayRechargeMethod) bundle.getSerializable(MibiConstants.cE);
        this.D = this.E.mTitle;
        this.F = this.E.mDenominationMibiList;
        this.I = this.E.mMibi;
        this.J = this.E.mMoney;
        this.K = this.E.mContentHint;
        this.L = this.E.mContentHintEntryData;
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        super.a(memoryStorage);
        if (!TextUtils.isEmpty(this.t)) {
            this.G = memoryStorage.d(this.t, MibiConstants.cZ);
            this.H = memoryStorage.f(this.t, "price");
        }
    }

    private void a(long j) {
        this.w.setItemSelected(Long.valueOf(j));
        this.C = j;
        this.y.setText(getString(R.string.mibi_recharge_value_money, new Object[]{Utils.a(((MessageChannelPresenter) H_()).a(j))}));
    }

    private long O() {
        ArrayList<Long> arrayList = this.F;
        if (this.H <= 0) {
            return arrayList.get(0).longValue();
        }
        long longValue = arrayList.get(arrayList.size() - 1).longValue();
        Iterator<Long> it = arrayList.iterator();
        while (it.hasNext()) {
            long longValue2 = it.next().longValue();
            if (longValue2 >= this.H) {
                return longValue2;
            }
        }
        return longValue;
    }

    /* access modifiers changed from: private */
    public void Q() {
        a(((MessageChannelPresenter) H_()).n());
    }

    /* access modifiers changed from: protected */
    public void L() {
        super.L();
    }

    /* access modifiers changed from: protected */
    public void A() {
        super.A();
        ((MessageChannelPresenter) H_()).a(getActivity(), this.C);
    }

    public void a(int i, String str, Throwable th) {
        a(0, false);
        ChannelUtils.a((StepFragment) this, i, str);
    }

    public void a(int i, boolean z2) {
        if (z2) {
            b(getString(R.string.mibi_progress_msg_creating), false);
        } else {
            M();
        }
    }

    public void e(Bundle bundle) {
        Class cls;
        if (Utils.b()) {
            cls = PadDialogActivity.class;
        } else {
            cls = PhoneCommonActivity.class;
        }
        Class cls2 = cls;
        bundle.putSerializable(MibiConstants.cE, this.E);
        a(MessageOrderFragment.class, bundle, 0, (String) null, cls2);
    }

    public void N() {
        p().m().a(this.t, MibiConstants.hg, (Object) Long.valueOf(this.C));
        Bundle bundle = new Bundle();
        bundle.putString("title", this.D);
        bundle.putBoolean(MibiConstants.hi, false);
        ChannelUtils.a((StepFragment) this, bundle, this.G);
    }
}
