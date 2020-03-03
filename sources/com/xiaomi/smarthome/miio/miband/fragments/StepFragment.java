package com.xiaomi.smarthome.miio.miband.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.StepDataItem;
import com.xiaomi.smarthome.miio.miband.utils.AccessManager;
import com.xiaomi.smarthome.miio.miband.widget.SimpleCircleProgressView;
import java.util.Date;

public class StepFragment extends BaseFragment implements DataManager.DataChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f19469a;
    private TextView b;
    private SimpleCircleProgressView c;
    private View d;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public void onSleepDataChanged() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.miband_step_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
        a();
        b();
    }

    private void a(View view) {
        this.f19469a = (TextView) view.findViewById(R.id.sport_step);
        this.b = (TextView) view.findViewById(R.id.sport_info);
        this.d = view.findViewById(R.id.container);
        this.c = (SimpleCircleProgressView) view.findViewById(R.id.scp_progress_view);
    }

    private void a() {
        this.d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AccessManager.c().d()) {
                    StepFragment.this.startActivity(new Intent(StepFragment.this.getActivity(), MiBandStepDetailActivity.class));
                }
            }
        });
    }

    private void b() {
        this.f19469a.setText(String.valueOf(this.e));
        TextView textView = this.b;
        String string = getString(R.string.today_kilometers);
        double d2 = (double) this.f;
        Double.isNaN(d2);
        textView.setText(String.format(string, new Object[]{Double.valueOf(d2 / 1000.0d), Integer.valueOf(this.g)}));
        this.c.setProgress(this.h == 0 ? 0.0f : (((float) this.e) * 1.0f) / ((float) this.h));
    }

    public void onResume() {
        super.onResume();
        DataManager.a().a((DataManager.DataChangeListener) this);
        c();
    }

    public void onPause() {
        super.onPause();
        DataManager.a().b((DataManager.DataChangeListener) this);
    }

    private void c() {
        StepDataItem b2 = DataManager.a().b(new Date());
        this.h = DataManager.a().f();
        if (b2 != null) {
            this.e = b2.g;
            this.f = b2.b;
            this.g = b2.f;
            b();
        }
    }

    public void onStepDataChanged() {
        c();
    }

    public void onUserDataChanged() {
        this.h = DataManager.a().e().e;
        b();
    }
}
