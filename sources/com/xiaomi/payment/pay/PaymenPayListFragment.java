package com.xiaomi.payment.pay;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.mibi.common.base.TaskListener;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.SortedParameter;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.recharge.RechargeType;
import com.xiaomi.payment.task.UploadAnalyticsTask;
import com.xiaomi.payment.ui.adapter.PayListAdapter;
import com.xiaomi.payment.ui.item.PayListItem;
import java.util.ArrayList;

public class PaymenPayListFragment extends BaseProcessFragment {
    private static final String v = "PaymenPayListFragment";
    private ArrayList<RechargeType> A = new ArrayList<>();
    private RechargeType B;
    /* access modifiers changed from: private */
    public int C;
    private AdapterView.OnItemClickListener D = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (PaymenPayListFragment.this.z != null) {
                RechargeType payType = ((PayListItem) view).getPayType();
                PaymenPayListFragment.this.N();
                Bundle bundle = new Bundle();
                bundle.putSerializable(MibiConstants.cG, payType);
                PaymenPayListFragment.this.b(-1, bundle);
                PaymenPayListFragment.this.E();
                SortedParameter sortedParameter = new SortedParameter();
                sortedParameter.a("eventType", (Object) MibiConstants.cQ);
                sortedParameter.a("payType", (Object) payType.mType);
                UploadAnalyticsTask uploadAnalyticsTask = new UploadAnalyticsTask(PaymenPayListFragment.this.getActivity(), PaymenPayListFragment.this.b);
                uploadAnalyticsTask.c(sortedParameter);
                PaymenPayListFragment.this.q().b(uploadAnalyticsTask, (TaskListener) null);
            }
        }
    };
    private TextView w;
    private ImageView x;
    /* access modifiers changed from: private */
    public ListView y;
    /* access modifiers changed from: private */
    public PayListAdapter z;

    public void a(Bundle bundle) {
        super.a(bundle);
        d(false);
    }

    public void k() {
        super.k();
        MistatisticUtils.a((Fragment) this, "PayTypeList:");
    }

    public void l() {
        super.l();
        MistatisticUtils.b((Fragment) this, "PayTypeList:");
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.A = (ArrayList) bundle.getSerializable(MibiConstants.he);
        if (!this.A.isEmpty()) {
            this.B = (RechargeType) bundle.getSerializable(MibiConstants.hf);
            return;
        }
        throw new IllegalArgumentException("mPayTypes should not be empty here");
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_payment_pay_list, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.layout_action_bar);
        this.w = (TextView) findViewById.findViewById(R.id.action_bar_title);
        this.w.setText(R.string.mibi_order_pay_type_title);
        this.x = (ImageView) findViewById.findViewById(R.id.back_arrow);
        this.x.setVisibility(0);
        this.x.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PaymenPayListFragment.this.c(0);
                PaymenPayListFragment.this.E();
            }
        });
        this.y = (ListView) inflate.findViewById(16908298);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        a(getActivity(), this.A);
    }

    private void a(Context context, ArrayList<RechargeType> arrayList) {
        if (this.z == null) {
            this.z = new PayListAdapter(context);
        }
        this.y.setAdapter(this.z);
        this.y.setOnItemClickListener(this.D);
        this.z.a(arrayList);
        int a2 = this.B != null ? this.z.a(this.B) : 0;
        if (a2 == -1) {
            a2 = 0;
        }
        this.C = a2;
        N();
    }

    /* access modifiers changed from: private */
    public void N() {
        this.y.post(new Runnable() {
            public void run() {
                PaymenPayListFragment.this.y.setItemChecked(PaymenPayListFragment.this.C, true);
            }
        });
    }
}
