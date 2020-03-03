package com.xiaomi.smarthome.miio.miband.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.SleepDataItem;
import com.xiaomi.smarthome.miio.miband.utils.AccessManager;
import java.util.Date;

public class SleepFragment extends BaseFragment implements DataManager.DataChangeListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f19467a;
    private TextView b;
    private View c;
    private int d = 0;
    private int e = 0;

    public void onStepDataChanged() {
    }

    public void onUserDataChanged() {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.miband_sleep_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
        a();
        b();
    }

    private void a(View view) {
        this.f19467a = (TextView) view.findViewById(R.id.tv_all_sleep);
        this.b = (TextView) view.findViewById(R.id.tv_deep_sleep);
        this.c = view.findViewById(R.id.sleep_container);
    }

    private void a() {
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (AccessManager.c().d()) {
                    SleepFragment.this.startActivity(new Intent(SleepFragment.this.getActivity(), MiBandSleepDetailActivity.class));
                }
            }
        });
    }

    private void b() {
        this.f19467a.setText(getString(R.string.all_sleep_time, getResources().getQuantityString(R.plurals.miband_hour, this.d / 60, new Object[]{Integer.valueOf(this.d / 60)}), getResources().getQuantityString(R.plurals.miband_minute, this.d % 60, new Object[]{Integer.valueOf(this.d % 60)})));
        this.b.setText(getString(R.string.deep_sleep_time, getResources().getQuantityString(R.plurals.miband_hour, this.e / 60, new Object[]{Integer.valueOf(this.e / 60)}), getResources().getQuantityString(R.plurals.miband_minute, this.e % 60, new Object[]{Integer.valueOf(this.e % 60)})));
    }

    public void onResume() {
        super.onResume();
        DataManager.a().a((DataManager.DataChangeListener) this);
        b();
    }

    public void onPause() {
        super.onPause();
        DataManager.a().b((DataManager.DataChangeListener) this);
    }

    private void c() {
        SleepDataItem a2 = DataManager.a().a(new Date());
        if (a2 != null) {
            this.e = a2.b;
            this.d = a2.f19464a + this.e;
            b();
        }
    }

    public void onSleepDataChanged() {
        c();
    }
}
