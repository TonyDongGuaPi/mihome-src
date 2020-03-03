package com.xiaomi.payment.deduct;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.base.IPresenter;
import com.mibi.common.base.StepFragment;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.decorator.AutoSave;
import com.mibi.common.ui.TranslucentActivity;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.deduct.contract.DeductTypeListContract;
import com.xiaomi.payment.deduct.presenter.DeductTypeListPresenter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.ui.adapter.DeductListAdapter;
import com.xiaomi.payment.ui.fragment.query.DeductQueryFragment;
import com.xiaomi.payment.ui.item.PayListItem;
import java.util.ArrayList;

public class DeductTypeListFragment extends BaseFragment implements AutoSave, DeductTypeListContract.View {
    private ListView t;
    /* access modifiers changed from: private */
    public DeductListAdapter u;
    private AdapterView.OnItemClickListener v = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (DeductTypeListFragment.this.u != null) {
                ((DeductTypeListContract.Presenter) DeductTypeListFragment.this.H_()).a(((PayListItem) view).getPayType());
            }
        }
    };

    public IPresenter onCreatePresenter() {
        return new DeductTypeListPresenter();
    }

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_deduct_list, viewGroup, false);
        this.t = (ListView) inflate.findViewById(16908298);
        return inflate;
    }

    public void a(Context context, ArrayList<RechargeType> arrayList) {
        if (this.u == null) {
            this.u = new DeductListAdapter(context);
        }
        this.t.setAdapter(this.u);
        this.t.setOnItemClickListener(this.v);
        this.u.a(arrayList);
    }

    public void a(int i, String str) {
        Bundle bundle = new Bundle();
        bundle.putString(MibiConstants.db, str);
        b(i, bundle);
        E();
    }

    public void e(Bundle bundle) {
        a(DoDeductFragment.class, bundle, 100, (String) null, TranslucentActivity.class);
    }

    public void d(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("processId", ((DeductTypeListContract.Presenter) H_()).m_());
        bundle.putString(MibiConstants.gA, str);
        a((Class<? extends StepFragment>) DeductQueryFragment.class, bundle, 200, (String) null);
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, "DeductTypeList:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "DeductTypeList:");
    }

    public void y() {
        super.y();
        c(202);
        E();
    }
}
