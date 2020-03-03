package com.xiaomi.payment.ui.fragment.recharge;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mibi.common.base.BaseActivity;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.PrivacyManager;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.animation.PopupAnimatorFactory;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.data.MibiPrivacyUtils;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.adapter.RechargeGridAdapter;
import com.xiaomi.payment.ui.fragment.recharge.RechargeContract;
import com.xiaomi.payment.ui.item.RechargeGridItem;
import java.util.ArrayList;

public class RechargeGridFragment extends BaseProcessFragment implements RechargeContract.View {
    private static final String v = "RechargeGridFragment";
    private static final String w = "more";
    private TextView A;
    private TextView B;
    private ImageView C;
    private View D;
    private Button E;
    /* access modifiers changed from: private */
    public RechargeGridAdapter F;
    private View.OnClickListener G = new View.OnClickListener() {
        public void onClick(View view) {
            if (Utils.a((Context) RechargeGridFragment.this.getActivity())) {
                RechargeGridFragment.this.a(0, true);
                ((RechargePresenter) RechargeGridFragment.this.H_()).g();
            }
        }
    };
    private AdapterView.OnItemClickListener H = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (RechargeGridFragment.this.F != null) {
                RechargeType rechargeType = ((RechargeGridItem) view).getRechargeType();
                if (TextUtils.equals("more", rechargeType.mType)) {
                    ((RechargePresenter) RechargeGridFragment.this.H_()).h();
                    return;
                }
                ((RechargePresenter) RechargeGridFragment.this.H_()).a(rechargeType);
                ((RechargePresenter) RechargeGridFragment.this.H_()).b(rechargeType);
            }
        }
    };
    private TextView x;
    private GridView y;
    private ProgressBar z;

    /* access modifiers changed from: protected */
    public StepFragment.IAnimatorFactory I() {
        return new PopupAnimatorFactory();
    }

    public IPresenter onCreatePresenter() {
        return new RechargePresenter();
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_recharge_grid, viewGroup, false);
        this.x = (TextView) inflate.findViewById(R.id.layout_action_bar).findViewById(R.id.action_bar_title);
        this.y = (GridView) inflate.findViewById(R.id.gridview);
        this.z = (ProgressBar) inflate.findViewById(R.id.progress);
        this.A = (TextView) inflate.findViewById(R.id.progress_text);
        this.B = (TextView) inflate.findViewById(R.id.error);
        this.C = (ImageView) inflate.findViewById(R.id.error_icon);
        this.D = inflate.findViewById(R.id.empty);
        this.E = (Button) inflate.findViewById(R.id.button_retry);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(R.string.mibi_btn_prev);
        this.x.setText(R.string.mibi_payment_recharge_group_title);
        if (!Utils.b()) {
            d(false);
        } else {
            d(true);
            this.x.setVisibility(8);
            b(R.string.mibi_cancel);
        }
        this.E.setOnClickListener(this.G);
        b(MibiCodeConstants.f12222a);
        ((RechargePresenter) H_()).g();
    }

    public void a(int i, boolean z2) {
        this.y.setVisibility(8);
        this.D.setVisibility(0);
        this.C.setVisibility(8);
        this.B.setVisibility(8);
        if (z2) {
            this.z.setVisibility(0);
            this.A.setVisibility(0);
            this.E.setEnabled(false);
            return;
        }
        this.z.setVisibility(8);
        this.A.setVisibility(8);
        this.E.setEnabled(true);
    }

    public void a(String str, ArrayList<RechargeType> arrayList) {
        this.y.setVisibility(0);
        this.D.setVisibility(8);
        this.t = str;
        b(str);
        a(arrayList);
    }

    public void a(Bundle bundle, Class<? extends BaseActivity> cls) {
        Class cls2 = (Class) bundle.getSerializable(MibiConstants.cR);
        if (cls2 != null) {
            a(cls2, bundle, 0, (String) null, cls);
        }
    }

    public void a(final int i, final String str) {
        MibiPrivacyUtils.a(getActivity(), new MibiPrivacyUtils.PrivacyCallBack() {
            public void a() {
                PrivacyManager.a(RechargeGridFragment.this.p(), false);
                Bundle bundle = new Bundle();
                bundle.putInt("errcode", i);
                bundle.putString("errDesc", str);
                RechargeGridFragment.this.b(i, bundle);
                RechargeGridFragment.this.E();
            }
        });
    }

    public void a_(int i, Bundle bundle) {
        b(i, bundle);
    }

    public void a() {
        E();
    }

    private void a(ArrayList<RechargeType> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            f(getString(R.string.mibi_recharge_novalid_method));
            return;
        }
        if (this.F == null) {
            this.F = new RechargeGridAdapter(getActivity());
        }
        this.y.setAdapter(this.F);
        this.y.setOnItemClickListener(this.H);
        this.F.a(arrayList);
    }

    private void e(String str) {
        this.E.setVisibility(0);
        f(str);
    }

    public void a(int i, String str, Throwable th) {
        if (i == 3) {
            e(str);
        } else {
            f(str);
        }
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, "Recharge:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "Recharge:");
    }

    private void f(String str) {
        this.y.setVisibility(8);
        this.D.setVisibility(0);
        this.B.setText(str);
        this.B.setVisibility(0);
        this.C.setVisibility(0);
    }

    public void a(int i, int i2, Bundle bundle) {
        Log.v(v, this + ".onFragmentResult, requestCode = " + i + ",resultCode = " + i2);
        super.a(i, i2, bundle);
        if (i == 0) {
            b(i2, bundle);
        }
    }
}
