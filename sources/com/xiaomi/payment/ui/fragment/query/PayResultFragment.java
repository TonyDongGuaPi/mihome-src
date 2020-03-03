package com.xiaomi.payment.ui.fragment.query;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.component.AutoCountDownButton;
import com.mibi.common.data.Image;
import com.mibi.common.data.MemoryStorage;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.platform.R;

public class PayResultFragment extends BaseFragment {
    private static final int t = 5;
    /* access modifiers changed from: private */
    public AutoCountDownButton A;
    private String B;
    private int C;
    private long D;
    private long E;
    private String F;
    private EntryData G;
    private View.OnClickListener H = new View.OnClickListener() {
        public void onClick(View view) {
            PayResultFragment.this.N();
        }
    };
    private View.OnClickListener I = new View.OnClickListener() {
        public void onClick(View view) {
            PayResultFragment.this.a(MibiCodeConstants.b, false);
        }
    };
    private AutoCountDownButton.CountDownListener J = new AutoCountDownButton.CountDownListener() {
        public void a() {
            PayResultFragment.this.A.setText(PayResultFragment.this.f7451a.getString(R.string.mibi_button_count_down_finish, new Object[]{"5"}));
        }

        public void a(int i) {
            AutoCountDownButton c = PayResultFragment.this.A;
            BaseActivity d = PayResultFragment.this.f7451a;
            int i2 = R.string.mibi_button_count_down_finish;
            c.setText(d.getString(i2, new Object[]{i + ""}));
        }

        public void b() {
            PayResultFragment.this.a(MibiCodeConstants.b, false);
        }
    };
    private ViewStub u;
    private TextView v;
    private ImageView w;
    private ViewStub x;
    private TextView y;
    private ImageView z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_pay_result, (ViewGroup) null);
        this.u = (ViewStub) inflate.findViewById(R.id.stub_success);
        this.x = (ViewStub) inflate.findViewById(R.id.stub_uncertain);
        if (this.C == 1) {
            this.v = (TextView) this.u.inflate().findViewById(R.id.text_result_success);
        } else if (this.C == 3) {
            this.y = (TextView) this.x.inflate().findViewById(R.id.mibi_result_uncertain_detail);
        }
        this.w = (ImageView) inflate.findViewById(R.id.mibi_payment_success_banner_view);
        this.w.setOnClickListener(this.H);
        this.z = (ImageView) inflate.findViewById(R.id.corner_button_close);
        this.z.setOnClickListener(this.I);
        this.A = (AutoCountDownButton) inflate.findViewById(R.id.auto_button_close);
        this.A.setOnClickListener(this.I);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.B = bundle.getString("processId");
        this.C = bundle.getInt("status", -1);
        this.F = bundle.getString(MibiConstants.ey);
        this.G = (EntryData) bundle.getSerializable(MibiConstants.dt);
        e(bundle);
    }

    private void e(Bundle bundle) {
        String string = bundle.getString("result");
        int i = bundle.getInt("resultCode");
        Bundle bundle2 = new Bundle();
        bundle2.putString("result", string);
        b(i, bundle2);
    }

    /* access modifiers changed from: protected */
    public void a(MemoryStorage memoryStorage) {
        this.D = memoryStorage.a(this.B, "price", 0);
        this.E = memoryStorage.a(this.B, MibiConstants.hg, 0);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        d(false);
        if (this.C == 1) {
            K();
        } else if (this.C == 3) {
            L();
        }
    }

    private void K() {
        if (this.E == 0 || this.E <= this.D) {
            this.v.setText(getResources().getString(R.string.mibi_pay_success_summary, new Object[]{Utils.a(this.D)}));
        } else {
            this.v.setText(getString(R.string.mibi_pay_success_with_recharge_summary, new Object[]{Utils.a(this.E), Utils.a(this.D)}));
        }
        if (!TextUtils.isEmpty(this.F)) {
            M();
            this.A.setVisibility(8);
            this.w.setVisibility(0);
            return;
        }
        this.A.setVisibility(0);
        this.w.setVisibility(8);
        this.A.setText(this.f7451a.getString(R.string.mibi_button_count_down_finish, new Object[]{"5"}));
        this.A.setTickNum(5);
        this.A.startTick();
        this.A.setCountDownListener(this.J);
    }

    private void L() {
        String string = getString(R.string.mibi_error_frozen_service_num);
        this.y.setText(getResources().getString(R.string.mibi_order_pay_uncertain_detail, new Object[]{string}));
        this.A.setVisibility(0);
        this.w.setVisibility(8);
        this.A.setText(this.f7451a.getString(R.string.mibi_order_pay_uncertain_button));
        this.A.setOnClickListener(this.I);
    }

    private void M() {
        Image.a((Context) getActivity()).a(this.F, Image.ThumbnailFormat.a(this.w.getMeasuredWidth(), this.w.getMeasuredHeight(), 1)).a(this.w);
    }

    /* access modifiers changed from: private */
    public void N() {
        if (this.G != null) {
            EntryManager.a().a((Fragment) this, this.G, (Bundle) null, -1);
        }
    }

    public void y() {
        a(MibiCodeConstants.b, false);
    }

    public void o() {
        super.o();
        this.A.stopTick();
    }
}
