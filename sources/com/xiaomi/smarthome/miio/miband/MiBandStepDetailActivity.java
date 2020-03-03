package com.xiaomi.smarthome.miio.miband;

import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.miio.miband.adpter.SimpleRecyclerAdapter;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.StepDataItem;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MiBandStepDetailActivity extends BaseWhiteActivity implements DataManager.DataChangeListener {
    private static final int g = 0;

    /* renamed from: a  reason: collision with root package name */
    private LinearLayoutManager f19437a;
    private RecyclerView b;
    private SimpleRecyclerAdapter c;
    private int d = 2;
    private float e = -1.0f;
    private float f = -1.0f;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;

    public void onSleepDataChanged() {
    }

    public void onUserDataChanged() {
    }

    public void handleMessage(Message message) {
        if (!isFinishing()) {
            if ((Build.VERSION.SDK_INT < 18 || !isDestroyed()) && message.what == 0) {
                this.c.b(this.d);
                this.c.c(1);
                a(this.c.b());
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.miband_step_detail_layout);
        d();
        a();
        b();
    }

    public void onResume() {
        super.onResume();
        DataManager.a().a((DataManager.DataChangeListener) this);
        a(DataManager.a().b());
    }

    public void onPause() {
        super.onPause();
        DataManager.a().b((DataManager.DataChangeListener) this);
    }

    private void a() {
        View findViewById = findViewById(R.id.module_a_3_return_transparent_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiBandStepDetailActivity.this.finish();
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_transparent_title);
        if (textView != null) {
            textView.setText(R.string.sport_detail_title);
        }
        View findViewById2 = findViewById(R.id.module_a_3_return_more_more_transparent_btn);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new View.OnClickListener() {
                /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
                /* JADX WARNING: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onClick(android.view.View r4) {
                    /*
                        r3 = this;
                        java.lang.String r4 = "com.xiaomi.hm.health"
                        com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity r0 = com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity.this
                        com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity r1 = com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity.this
                        android.content.res.Resources r1 = r1.getResources()
                        r2 = 2131497318(0x7f0c1166, float:1.8618226E38)
                        java.lang.String r1 = r1.getString(r2)
                        r2 = 0
                        boolean r4 = com.xiaomi.smarthome.miio.LauncherUtil.a(r0, r4, r2, r1)
                        if (r4 == 0) goto L_0x0040
                        com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity r4 = com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity.this
                        android.content.pm.PackageManager r4 = r4.getPackageManager()
                        java.lang.String r0 = "com.xiaomi.hm.health"
                        android.content.Intent r4 = r4.getLaunchIntentForPackage(r0)
                        r0 = 1
                        r1 = 0
                        if (r4 == 0) goto L_0x0031
                        com.xiaomi.smarthome.miio.LauncherUtil.a(r4)
                        com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity r2 = com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity.this     // Catch:{ Exception -> 0x0031 }
                        r2.startActivity(r4)     // Catch:{ Exception -> 0x0031 }
                        goto L_0x0032
                    L_0x0031:
                        r0 = 0
                    L_0x0032:
                        if (r0 != 0) goto L_0x0040
                        com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity r4 = com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity.this
                        android.content.Context r4 = r4.getContext()
                        r0 = 2131499327(0x7f0c193f, float:1.86223E38)
                        com.xiaomi.smarthome.library.common.util.ToastUtil.a((android.content.Context) r4, (int) r0, (int) r1)
                    L_0x0040:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.miband.MiBandStepDetailActivity.AnonymousClass2.onClick(android.view.View):void");
                }
            });
        }
    }

    private void b() {
        c();
    }

    private void c() {
        this.f19437a = new LinearLayoutManager(this);
        this.f19437a.setOrientation(0);
        this.f19437a.setReverseLayout(true);
        this.f19437a.scrollToPosition(0);
        this.b.setLayoutManager(this.f19437a);
        this.c = new SimpleRecyclerAdapter(this, this.f19437a);
        this.c.a((SimpleRecyclerAdapter.OnItemClickedListener) new SimpleRecyclerAdapter.OnItemClickedListener() {
            public void a(int i, StepDataItem stepDataItem) {
                MiBandStepDetailActivity.this.a(stepDataItem);
            }
        });
        this.c.a(1);
        this.b.setAdapter(this.c);
        a(this.c.b());
    }

    private void a(List<StepDataItem> list) {
        b(list);
    }

    private void a(float f2) {
        if (f2 > this.e) {
            this.e = f2;
        }
        if (f2 < this.f) {
            this.f = f2;
        }
    }

    private void b(List<StepDataItem> list) {
        if (list == null || list.size() == 0) {
            this.c.a((List<StepDataItem>) null);
            this.c.a(0.0f, 0.0f);
            this.mHandler.sendEmptyMessage(0);
            return;
        }
        StepDataItem stepDataItem = list.get(list.size() - 1);
        this.e = (float) stepDataItem.g;
        this.f = (float) stepDataItem.g;
        Date date = new Date(stepDataItem.i);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int i2 = 11;
        if (this.d == 2) {
            i2 = 6;
        } else if (this.d == 1) {
            i2 = 3;
        } else if (this.d == 0) {
            i2 = 2;
        }
        instance.get(i2);
        instance.setTime(DataManager.a(instance, new Date(list.get(list.size() - 1).i)));
        for (int i3 = 0; i3 < list.size(); i3++) {
            a((float) list.get(i3).g);
        }
        this.c.a(list);
        this.c.a(this.e, this.f);
        this.mHandler.sendEmptyMessage(0);
    }

    private void d() {
        this.b = (RecyclerView) findViewById(R.id.recycler_view);
        this.h = (TextView) findViewById(R.id.day_total_distance_id);
        this.i = (TextView) findViewById(R.id.day_total_distance_symbol);
        this.j = (TextView) findViewById(R.id.day_total_step_id);
        this.k = (TextView) findViewById(R.id.day_total_calorie_id);
        this.l = (TextView) findViewById(R.id.walk_distance_id);
        this.m = (TextView) findViewById(R.id.walk_distance_symbol);
        this.n = (TextView) findViewById(R.id.walk_time_hour_id);
        this.o = (TextView) findViewById(R.id.walk_time_minute_id);
        this.p = (TextView) findViewById(R.id.walk_calorie_id);
        this.q = (TextView) findViewById(R.id.run_distance_id);
        this.r = (TextView) findViewById(R.id.run_distance_symbol);
        this.s = (TextView) findViewById(R.id.run_time_hour_id);
        this.t = (TextView) findViewById(R.id.run_time_minute_id);
        this.u = (TextView) findViewById(R.id.run_calorie_id);
    }

    /* access modifiers changed from: private */
    public void a(StepDataItem stepDataItem) {
        if (stepDataItem != null) {
            int i2 = stepDataItem.b;
            if (i2 / 1000 > 0) {
                this.i.setText(R.string.symbol_kilometer);
                TextView textView = this.h;
                double d2 = (double) i2;
                Double.isNaN(d2);
                textView.setText(String.format("%1.2f", new Object[]{Double.valueOf(d2 / 1000.0d)}));
            } else {
                this.i.setText(R.string.symbol_meter);
                this.h.setText(String.valueOf(i2));
            }
            this.j.setText(String.valueOf(stepDataItem.g));
            this.k.setText(String.valueOf(stepDataItem.f));
            int i3 = stepDataItem.b - stepDataItem.d;
            if (i3 / 1000 > 0) {
                this.m.setText(R.string.symbol_kilometer);
                TextView textView2 = this.l;
                double d3 = (double) i3;
                Double.isNaN(d3);
                textView2.setText(String.format("%1.2f", new Object[]{Double.valueOf(d3 / 1000.0d)}));
            } else {
                this.m.setText(R.string.symbol_meter);
                this.l.setText(String.valueOf(i3));
            }
            this.n.setText(String.valueOf(stepDataItem.f19465a / 60));
            this.o.setText(String.valueOf(stepDataItem.f19465a % 60));
            this.p.setText(String.valueOf(stepDataItem.f - stepDataItem.e));
            int i4 = stepDataItem.d;
            if (i4 / 1000 > 0) {
                this.r.setText(R.string.symbol_kilometer);
                TextView textView3 = this.q;
                double d4 = (double) i4;
                Double.isNaN(d4);
                textView3.setText(String.format("%1.2f", new Object[]{Double.valueOf(d4 / 1000.0d)}));
            } else {
                this.r.setText(R.string.symbol_meter);
                this.q.setText(String.valueOf(i4));
            }
            this.s.setText(String.valueOf(stepDataItem.c / 60));
            this.t.setText(String.valueOf(stepDataItem.c % 60));
            this.u.setText(String.valueOf(stepDataItem.e));
        }
    }

    public void onStepDataChanged() {
        a(DataManager.a().b());
    }
}
