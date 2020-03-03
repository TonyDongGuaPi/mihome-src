package com.xiaomi.payment.channel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.component.ProgressButton;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.PhoneDialogActivity;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.channel.presenter.MessageOrderPresenter;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeMethod;
import com.xiaomi.payment.ui.contract.MessageOrderContract;

public class MessageOrderFragment extends BaseProcessFragment implements MessageOrderContract.View {
    private TextView A;
    private TextView B;
    private ProgressButton C;
    /* access modifiers changed from: private */
    public String D = "";
    private View.OnClickListener E = new View.OnClickListener() {
        public void onClick(View view) {
            if (!TextUtils.isEmpty(MessageOrderFragment.this.D)) {
                ((MessageOrderPresenter) MessageOrderFragment.this.H_()).a(MessageOrderFragment.this.getActivity(), MessageOrderFragment.this);
            }
        }
    };
    private RechargeMethod v;
    private String w;
    private boolean x;
    private Long y;
    private Long z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_msg_order_info, viewGroup, false);
        this.A = (TextView) inflate.findViewById(R.id.goods_info);
        this.B = (TextView) inflate.findViewById(R.id.denom);
        this.C = (ProgressButton) inflate.findViewById(R.id.button_confirm);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a((CharSequence) this.w);
        b(R.string.mibi_btn_prev);
        this.A.setText(getResources().getString(R.string.mibi_msg_telecom_order_goods, new Object[]{Utils.a(this.z.longValue())}));
        this.B.setText(getResources().getString(R.string.mibi_msg_telecom_order_denom, new Object[]{Utils.a(this.y.longValue())}));
        this.C.setOnClickListener(this.E);
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.v = (RechargeMethod) bundle.getSerializable(MibiConstants.cE);
        if (this.v != null) {
            this.w = this.v.mTitle;
        }
        this.D = bundle.getString(MibiConstants.dr);
        this.y = Long.valueOf(bundle.getLong(MibiConstants.f12224de));
        this.z = Long.valueOf(bundle.getLong(MibiConstants.dd));
        if (!TextUtils.isEmpty(this.t) && this.b != null) {
            this.x = this.b.m().d(this.t, MibiConstants.cZ);
        }
    }

    public IPresenter onCreatePresenter() {
        return new MessageOrderPresenter();
    }

    public void a(int i, String str, Throwable th) {
        a(0, false);
        ChannelUtils.a((StepFragment) this, i, str);
    }

    public void e(Bundle bundle) {
        a(TyUnicomSMSCodeFragment.class, bundle, 1000, (String) null, PhoneDialogActivity.class);
    }

    public void a(int i, boolean z2) {
        if (z2) {
            b(getString(R.string.mibi_progress_msg_creating), false);
            this.C.startProgress();
            b(false);
            return;
        }
        M();
        this.C.stopProgress();
        b(true);
    }

    public void N() {
        a(0, false);
        a(this.z.longValue());
    }

    private void a(long j) {
        p().m().a(this.t, MibiConstants.hg, (Object) Long.valueOf(j));
        Bundle bundle = new Bundle();
        bundle.putString("title", this.w);
        bundle.putBoolean(MibiConstants.hi, false);
        ChannelUtils.a((StepFragment) this, bundle, this.x);
    }
}
