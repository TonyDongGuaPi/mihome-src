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
import com.xiaomi.smarthome.miio.miband.adpter.SimpleStackedRecyclerAdapter;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.SleepDataItem;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MiBandSleepDetailActivity extends BaseWhiteActivity implements DataManager.DataChangeListener {
    private static final int g = 0;

    /* renamed from: a  reason: collision with root package name */
    private LinearLayoutManager f19433a;
    private RecyclerView b;
    private SimpleStackedRecyclerAdapter c;
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

    public void onStepDataChanged() {
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
        setContentView(R.layout.miband_sleep_detail_layout);
        d();
        a();
        b();
    }

    public void onResume() {
        super.onResume();
        DataManager.a().a((DataManager.DataChangeListener) this);
        a(DataManager.a().c());
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
                    MiBandSleepDetailActivity.this.finish();
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_transparent_title);
        if (textView != null) {
            textView.setText(R.string.sleep_detail_title);
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
                        com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity r0 = com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity.this
                        com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity r1 = com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity.this
                        android.content.res.Resources r1 = r1.getResources()
                        r2 = 2131497318(0x7f0c1166, float:1.8618226E38)
                        java.lang.String r1 = r1.getString(r2)
                        r2 = 0
                        boolean r4 = com.xiaomi.smarthome.miio.LauncherUtil.a(r0, r4, r2, r1)
                        if (r4 == 0) goto L_0x0040
                        com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity r4 = com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity.this
                        android.content.pm.PackageManager r4 = r4.getPackageManager()
                        java.lang.String r0 = "com.xiaomi.hm.health"
                        android.content.Intent r4 = r4.getLaunchIntentForPackage(r0)
                        r0 = 1
                        r1 = 0
                        if (r4 == 0) goto L_0x0031
                        com.xiaomi.smarthome.miio.LauncherUtil.a(r4)
                        com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity r2 = com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity.this     // Catch:{ Exception -> 0x0031 }
                        r2.startActivity(r4)     // Catch:{ Exception -> 0x0031 }
                        goto L_0x0032
                    L_0x0031:
                        r0 = 0
                    L_0x0032:
                        if (r0 != 0) goto L_0x0040
                        com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity r4 = com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity.this
                        android.content.Context r4 = r4.getContext()
                        r0 = 2131499327(0x7f0c193f, float:1.86223E38)
                        com.xiaomi.smarthome.library.common.util.ToastUtil.a((android.content.Context) r4, (int) r0, (int) r1)
                    L_0x0040:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.miband.MiBandSleepDetailActivity.AnonymousClass2.onClick(android.view.View):void");
                }
            });
        }
    }

    private void b() {
        c();
    }

    private void c() {
        this.f19433a = new LinearLayoutManager(this);
        this.f19433a.setOrientation(0);
        this.f19433a.setReverseLayout(true);
        this.f19433a.scrollToPosition(0);
        this.b.setLayoutManager(this.f19433a);
        this.c = new SimpleStackedRecyclerAdapter(this);
        this.c.a((SimpleStackedRecyclerAdapter.OnItemClickedListener) new SimpleStackedRecyclerAdapter.OnItemClickedListener() {
            public void a(int i, SleepDataItem sleepDataItem) {
                MiBandSleepDetailActivity.this.a(sleepDataItem);
            }
        });
        this.c.a(1);
        this.b.setAdapter(this.c);
        a(this.c.b());
    }

    private void a(List<SleepDataItem> list) {
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

    private void b(List<SleepDataItem> list) {
        if (list == null || list.size() == 0) {
            this.c.a((List<SleepDataItem>) null);
            this.c.a(0.0f, 0.0f);
            this.mHandler.sendEmptyMessage(0);
            return;
        }
        SleepDataItem sleepDataItem = list.get(list.size() - 1);
        this.e = (float) (sleepDataItem.f19464a + sleepDataItem.f19464a);
        this.f = (float) (sleepDataItem.f19464a + sleepDataItem.f19464a);
        Date date = new Date(sleepDataItem.g);
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
        instance.setTime(DataManager.a(instance, new Date(list.get(list.size() - 1).g)));
        for (int i3 = 0; i3 < list.size(); i3++) {
            SleepDataItem sleepDataItem2 = list.get(i3);
            a((float) (sleepDataItem2.f19464a + sleepDataItem2.b));
        }
        this.c.a(list);
        this.c.a(this.e, this.f);
        this.mHandler.sendEmptyMessage(0);
    }

    private void d() {
        this.b = (RecyclerView) findViewById(R.id.recycler_view);
        this.h = (TextView) findViewById(R.id.all_sleep_hour_id);
        this.i = (TextView) findViewById(R.id.all_sleep_minute_id);
        this.j = (TextView) findViewById(R.id.deep_sleep_hour_id);
        this.k = (TextView) findViewById(R.id.deep_sleep_minute_id);
        this.l = (TextView) findViewById(R.id.shallow_sleep_hour_id);
        this.m = (TextView) findViewById(R.id.shallow_sleep_minute_id);
        this.n = (TextView) findViewById(R.id.start_sleep_hour_id);
        this.o = (TextView) findViewById(R.id.start_sleep_minute_id);
        this.p = (TextView) findViewById(R.id.end_sleep_hour_id);
        this.q = (TextView) findViewById(R.id.end_sleep_minute_id);
        this.r = (TextView) findViewById(R.id.wake_time_hour_id);
        this.s = (TextView) findViewById(R.id.wake_time_minute_id);
    }

    /* access modifiers changed from: private */
    public void a(SleepDataItem sleepDataItem) {
        if (sleepDataItem != null) {
            int i2 = sleepDataItem.b + sleepDataItem.f19464a;
            this.h.setText(String.valueOf(i2 / 60));
            this.i.setText(String.valueOf(i2 % 60));
            this.j.setText(String.valueOf(sleepDataItem.b / 60));
            this.k.setText(String.valueOf(sleepDataItem.b % 60));
            this.l.setText(String.valueOf(sleepDataItem.f19464a / 60));
            this.m.setText(String.valueOf(sleepDataItem.f19464a % 60));
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(sleepDataItem.d * 1000);
            this.n.setText(String.valueOf(instance.get(11)));
            this.o.setText(String.format("%02d", new Object[]{Integer.valueOf(instance.get(12))}));
            instance.setTimeInMillis(sleepDataItem.e * 1000);
            this.p.setText(String.valueOf(instance.get(11)));
            this.q.setText(String.format("%02d", new Object[]{Integer.valueOf(instance.get(12))}));
            this.r.setText(String.valueOf(sleepDataItem.c / 60));
            this.s.setText(String.valueOf(sleepDataItem.c % 60));
        }
    }

    public void onSleepDataChanged() {
        a(DataManager.a().c());
    }
}
