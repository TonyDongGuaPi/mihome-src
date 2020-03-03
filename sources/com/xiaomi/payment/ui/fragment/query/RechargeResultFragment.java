package com.xiaomi.payment.ui.fragment.query;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.mibi.common.data.Image;
import com.mibi.common.data.MistatisticUtils;
import com.mibi.common.data.Utils;
import com.mibi.common.ui.fragment.BaseProcessFragment;
import com.xiaomi.payment.data.AnalyticsConstants;
import com.xiaomi.payment.data.EntryData;
import com.xiaomi.payment.data.MibiCodeConstants;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.payment.entry.EntryManager;
import com.xiaomi.payment.platform.R;
import java.util.HashMap;
import java.util.Map;

public class RechargeResultFragment extends BaseProcessFragment {
    private TextView A;
    private Button B;
    private int C;
    private long D;
    private long E;
    private int F;
    private String G;
    private String H;
    private EntryData I;
    private boolean J;
    private String K;
    /* access modifiers changed from: private */
    public EntryData L;
    private View.OnClickListener M = new View.OnClickListener() {
        public void onClick(View view) {
            RechargeResultFragment.this.F();
        }
    };
    private ImageView v;
    private ImageView w;
    private TextView x;
    private TextView y;
    private TextView z;

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_recharge_result, viewGroup, false);
        this.v = (ImageView) inflate.findViewById(R.id.banner);
        this.w = (ImageView) inflate.findViewById(R.id.icon);
        this.x = (TextView) inflate.findViewById(R.id.title);
        this.y = (TextView) inflate.findViewById(R.id.summary);
        this.z = (TextView) inflate.findViewById(R.id.error);
        this.A = (TextView) inflate.findViewById(R.id.promotions);
        this.B = (Button) inflate.findViewById(R.id.button_back);
        return inflate;
    }

    /* access modifiers changed from: protected */
    public void b_(Bundle bundle) {
        super.b_(bundle);
        this.C = bundle.getInt("status", -1);
        this.D = bundle.getLong(MibiConstants.dh, -1);
        this.E = bundle.getLong("balance", -1);
        this.H = bundle.getString(MibiConstants.ew);
        this.I = (EntryData) bundle.getSerializable(MibiConstants.di);
        this.J = bundle.getBoolean(MibiConstants.ex);
        if (this.J) {
            this.K = bundle.getString(MibiConstants.ey);
            this.L = (EntryData) bundle.getSerializable(MibiConstants.cJ);
        }
        this.F = bundle.getInt(MibiConstants.da, 1);
        this.G = bundle.getString(MibiConstants.db);
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(R.string.mibi_btn_finish);
        this.B.setOnClickListener(this.M);
        if (this.C == 1) {
            a(this.D, this.E, this.H, this.I);
        } else if (this.C == 2) {
            a(this.F, this.G, this.H, this.I);
        }
    }

    private void a(long j, long j2, String str, EntryData entryData) {
        Bundle bundle = new Bundle();
        if (j2 >= 0) {
            bundle.putLong("balance", j2);
        }
        b(1000, bundle);
        this.w.setImageResource(R.drawable.mibi_ic_success);
        if (j2 >= 0) {
            this.x.setText(getString(R.string.mibi_progress_success_recharge_title, new Object[]{Utils.a(j)}));
            String a2 = Utils.a(j2);
            this.y.setText(getString(R.string.mibi_progress_success_recharge_summary, new Object[]{a2}));
        } else {
            this.x.setText(getString(R.string.mibi_progress_success_recharge_title, new Object[]{Utils.a(j)}));
            this.y.setVisibility(8);
        }
        e((String) null);
        a(str, entryData);
        N();
        String h = this.b.m().h("miref");
        if (!TextUtils.isEmpty(h)) {
            HashMap hashMap = new HashMap();
            hashMap.put("miref", h);
            hashMap.put(AnalyticsConstants.bH, "recharge_success");
            MistatisticUtils.a(AnalyticsConstants.bE, AnalyticsConstants.bF, (Map<String, String>) hashMap);
        }
    }

    private void a(int i, String str, String str2, EntryData entryData) {
        this.w.setImageResource(R.drawable.mibi_ic_error);
        this.x.setText(R.string.mibi_progress_error_recharge_title);
        this.y.setText(R.string.mibi_progress_error_recharge_summary);
        e(str);
        a(str2, entryData);
        Bundle bundle = new Bundle();
        bundle.putInt(MibiConstants.da, i);
        bundle.putString(MibiConstants.db, str);
        b(1001, bundle);
    }

    private void e(String str) {
        if (TextUtils.isEmpty(str)) {
            this.z.setVisibility(8);
            return;
        }
        this.z.setVisibility(0);
        this.z.setText(str);
    }

    private void N() {
        if (!this.J) {
            this.v.setVisibility(8);
            return;
        }
        Image.ThumbnailFormat b = Image.ThumbnailFormat.b(getResources().getDimensionPixelSize(R.dimen.mibi_recharge_result_banner_width), 1);
        Drawable drawable = getResources().getDrawable(R.drawable.mibi_banner_default);
        if (!TextUtils.isEmpty(this.K)) {
            Image.a((Context) getActivity()).a(this.K, b).a(drawable).a(this.v);
            this.v.setVisibility(0);
        }
        this.v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RechargeResultFragment.this.a(RechargeResultFragment.this.L);
            }
        });
    }

    private void a(String str, final EntryData entryData) {
        if (TextUtils.isEmpty(str)) {
            this.A.setVisibility(8);
            return;
        }
        this.A.setVisibility(0);
        this.A.setText(str);
        if (entryData != null) {
            this.A.getPaint().setFlags(8);
            this.A.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    RechargeResultFragment.this.a(entryData);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(EntryData entryData) {
        if (entryData != null) {
            EntryManager.a().a((Fragment) this, entryData, new Bundle(), -1);
        }
    }

    public void y() {
        a(MibiCodeConstants.f12222a, false);
    }
}
