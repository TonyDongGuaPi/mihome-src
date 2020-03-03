package com.xiaomi.payment.deduct;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.contract.DeductTypeListContract;
import com.xiaomi.payment.deduct.presenter.SignDeductTypeListPresenter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.adapter.DeductListAdapter;
import com.xiaomi.payment.ui.item.PayListItem;
import java.util.ArrayList;

public class SignDeductTypeListFragment extends BaseFragment implements AutoSave, DeductTypeListContract.View {
    private static final String t = "SignDeductTypeListFrag";
    private ListView u;
    /* access modifiers changed from: private */
    public DeductListAdapter v;
    private AdapterView.OnItemClickListener w = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (SignDeductTypeListFragment.this.v != null) {
                ((DeductTypeListContract.Presenter) SignDeductTypeListFragment.this.H_()).a(((PayListItem) view).getPayType());
            }
        }
    };

    public IPresenter onCreatePresenter() {
        return new SignDeductTypeListPresenter();
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_deduct_list, viewGroup, false);
        this.u = (ListView) inflate.findViewById(16908298);
        return inflate;
    }

    public void a(Context context, ArrayList<RechargeType> arrayList) {
        if (this.v == null) {
            this.v = new DeductListAdapter(context);
        }
        this.u.setAdapter(this.v);
        this.u.setOnItemClickListener(this.w);
        this.v.a(arrayList);
    }

    public void a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        E();
    }

    public void e(Bundle bundle) {
        a(DoSignDeductFragment.class, bundle, 100, (String) null, TranslucentActivity.class);
    }

    public void d(String str) {
        Log.d(t, "goQuery");
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, "SignDeductTypeList:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "SignDeductTypeList:");
    }

    public void y() {
        super.y();
        c(205);
        E();
    }
}
