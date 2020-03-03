package com.xiaomi.smarthome.miio.page.smartgroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.PictureShareActivity;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SmartGroupWeatherActivity extends BaseActivity {
    private static final String B = "fonts/FounderLantingFiberBlackSimplified.ttf";
    private static final int F = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f19891a = "SmartGroupWeatherActivity";
    private static final String b = "-";
    private static final String u = "101010100";
    private static final String z = "fonts/DIN-Regular.ttf";
    private Typeface A;
    private Typeface C;
    private ImageView D;
    /* access modifiers changed from: private */
    public MyHandler E;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    BroadcastReceiver mBackgroundBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (AreaInfoManager.f11897a.equals(intent.getAction())) {
                SmartGroupWeatherActivity.this.E.sendEmptyMessage(1);
            }
        }
    };
    private TextView n;
    private TextView o;
    private TextView p;
    private LinearLayout q;
    private ImageView r;
    private LinearLayout s;
    private String t;
    private volatile boolean v = false;
    private AreaPropInfo w;
    private List<String> x = new ArrayList();
    private LayoutInflater y;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smart_group_weather);
        this.E = new MyHandler(this);
        Intent intent = getIntent();
        if (intent == null) {
            this.t = "101010100";
        } else {
            this.t = intent.getStringExtra(SmartGroupConstants.f19890a);
            if (TextUtils.isEmpty(this.t)) {
                this.t = "101010100";
            }
        }
        this.A = FontManager.a("fonts/DIN-Regular.ttf");
        this.C = FontManager.a(B);
        this.y = LayoutInflater.from(this);
        a();
        b();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mBackgroundBroadcastReceiver, new IntentFilter(AreaInfoManager.f11897a));
    }

    private void a() {
        this.x.add(getString(R.string.sunday1));
        this.x.add(getString(R.string.monday1));
        this.x.add(getString(R.string.tuesday1));
        this.x.add(getString(R.string.wednesday1));
        this.x.add(getString(R.string.thursday1));
        this.x.add(getString(R.string.friday1));
        this.x.add(getString(R.string.saturday1));
    }

    private void b() {
        this.r = (ImageView) findViewById(R.id.module_a_3_return_more_more_btn);
        this.r.setImageResource(R.drawable.smartgroup_share_icon);
        this.r.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartGroupWeatherActivity.this.f();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.smart_group_weather_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartGroupWeatherActivity.this.finish();
            }
        });
        this.s = (LinearLayout) findViewById(R.id.view_share);
        this.c = (TextView) findViewById(R.id.day_of_week_tv1);
        this.d = (TextView) findViewById(R.id.day_of_week_tv2);
        this.e = (TextView) findViewById(R.id.day_of_week_tv3);
        this.f = (TextView) findViewById(R.id.weather_info_tv1);
        this.g = (TextView) findViewById(R.id.weather_info_tv2);
        this.h = (TextView) findViewById(R.id.weather_info_tv3);
        this.i = (TextView) findViewById(R.id.temperature_tv1);
        this.j = (TextView) findViewById(R.id.temperature_tv2);
        this.k = (TextView) findViewById(R.id.temperature_tv3);
        this.i.setTypeface(this.A);
        this.j.setTypeface(this.A);
        this.k.setTypeface(this.A);
        this.l = (TextView) findViewById(R.id.local_location_tv);
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().D()) {
                    AreaInfoManager.a().a(SmartGroupWeatherActivity.this.getContext());
                }
            }
        });
        this.m = (TextView) findViewById(R.id.local_weather_tv1);
        this.n = (TextView) findViewById(R.id.local_weather_tv2);
        this.o = (TextView) findViewById(R.id.local_weather_tv3);
        this.p = (TextView) findViewById(R.id.local_temperature_tv1);
        this.p.setTypeface(this.C);
        this.q = (LinearLayout) findViewById(R.id.weather_property_list);
        this.D = (ImageView) findViewById(R.id.weather_bg);
        c();
    }

    private void c() {
        this.D.setColorFilter(AreaInfoManager.a().F());
    }

    private void d() {
        AreaInfoManager.a().a(SHApplication.getAppContext(), true);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        d();
        if (this.E != null) {
            this.E.sendEmptyMessage(1);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mBackgroundBroadcastReceiver);
        if (this.E != null) {
            this.E.removeCallbacksAndMessages((Object) null);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        this.w = AreaInfoManager.a().H();
        if (this.w != null) {
            AreaPropInfo.Forecast forecast = this.w.G;
            AreaPropInfo.WeatherIndex weatherIndex = this.w.H;
            a(forecast);
            a(this.w);
            a(weatherIndex);
        }
        c();
    }

    private void a(String str, TextView textView, String str2, TextView textView2) {
        if (StringUtil.c(str)) {
            textView.setText("-/-");
        } else {
            String[] a2 = AreaInfoManager.a(str);
            if (a2 == null || a2.length < 2) {
                textView.setText("-/-");
            } else {
                textView.setText(a2[0] + "/" + a2[1]);
            }
        }
        if (StringUtil.c(str2)) {
            textView2.setText("-");
        } else {
            textView2.setText(str2);
        }
    }

    private void a(AreaPropInfo.Forecast forecast) {
        String str;
        String str2;
        String str3;
        Calendar instance = Calendar.getInstance();
        instance.add(5, 1);
        this.c.setText(getString(R.string.plug_timer_today));
        instance.add(5, 1);
        this.d.setText(getString(R.string.plug_timer_tomorrow));
        instance.add(5, 1);
        this.e.setText(this.x.get(instance.get(7) - 1));
        String str4 = null;
        if (forecast == null) {
            str = null;
        } else {
            str = forecast.bL;
        }
        a(str, this.i, forecast == null ? null : forecast.bX, this.f);
        if (forecast == null) {
            str2 = null;
        } else {
            str2 = forecast.bM;
        }
        a(str2, this.j, forecast == null ? null : forecast.bY, this.g);
        if (forecast == null) {
            str3 = null;
        } else {
            str3 = forecast.bN;
        }
        TextView textView = this.k;
        if (forecast != null) {
            str4 = forecast.bZ;
        }
        a(str3, textView, str4, this.h);
    }

    private void a(AreaPropInfo areaPropInfo) {
        this.l.setText(AreaInfoManager.a().l());
        this.p.setText(AreaInfoManager.a().A());
        String w2 = AreaInfoManager.a().w();
        if (!(areaPropInfo.G == null || this.w == null || this.w.I == null || StringUtil.c(this.w.I.b))) {
            w2 = w2 + " " + this.w.I.b + this.w.I.c;
        }
        this.m.setCompoundDrawablesWithIntrinsicBounds(AreaInfoManager.a().a(true), 0, 0, 0);
        this.m.setText(w2);
        this.o.setText(AreaInfoManager.a().D());
    }

    private void a(AreaPropInfo.WeatherIndex weatherIndex) {
        if (weatherIndex == null || weatherIndex.f16449a == null || weatherIndex.f16449a.size() == 0) {
            this.q.setVisibility(8);
            return;
        }
        this.q.setVisibility(0);
        this.q.removeAllViews();
        int childCount = this.q.getChildCount();
        for (int i2 = 0; i2 < weatherIndex.f16449a.size(); i2++) {
            AreaPropInfo.WeatherIndex.WeatherIndexItem weatherIndexItem = weatherIndex.f16449a.get(i2);
            if (weatherIndexItem != null) {
                View inflate = this.y.inflate(R.layout.smart_group_weather_property_list_item, this.q, false);
                ((TextView) inflate.findViewById(R.id.property_name)).setText(weatherIndexItem.d);
                ((TextView) inflate.findViewById(R.id.property_value)).setText(weatherIndexItem.c);
                this.q.addView(inflate, i2 + childCount);
            }
        }
        this.q.invalidate();
    }

    public static class MyHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<SmartGroupWeatherActivity> f19896a;

        public MyHandler(SmartGroupWeatherActivity smartGroupWeatherActivity) {
            this.f19896a = new WeakReference<>(smartGroupWeatherActivity);
        }

        public void handleMessage(Message message) {
            SmartGroupWeatherActivity smartGroupWeatherActivity;
            if (message.what == 1 && (smartGroupWeatherActivity = (SmartGroupWeatherActivity) this.f19896a.get()) != null && !smartGroupWeatherActivity.isFinishing()) {
                smartGroupWeatherActivity.e();
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        String a2 = a(g());
        if (a2 != null) {
            a(a2);
        }
    }

    private void a(String str) {
        PictureShareActivity.share((Context) this, getString(R.string.smartgroup_share_title), (String) null, str);
    }

    private Bitmap g() {
        int width = this.s.getWidth();
        int height = this.s.getHeight();
        Drawable drawable = getResources().getDrawable(R.drawable.smartgroup_share_weather);
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        Drawable drawable2 = getResources().getDrawable(R.drawable.smartgroup_share_footer);
        int intrinsicWidth2 = drawable2.getIntrinsicWidth();
        int intrinsicHeight2 = drawable2.getIntrinsicHeight();
        drawable2.setBounds(0, 0, intrinsicWidth2, intrinsicHeight2);
        Bitmap createBitmap = Bitmap.createBitmap(width, height + intrinsicHeight + intrinsicHeight2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        drawable.draw(canvas);
        canvas.translate(0.0f, (float) intrinsicHeight);
        this.s.draw(canvas);
        canvas.translate(0.0f, (float) height);
        drawable2.draw(canvas);
        return createBitmap;
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[SYNTHETIC, Splitter:B:11:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038 A[SYNTHETIC, Splitter:B:19:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(android.graphics.Bitmap r6) {
        /*
            r5 = this;
            java.lang.String r0 = android.os.Environment.DIRECTORY_DCIM
            java.io.File r0 = android.os.Environment.getExternalStoragePublicDirectory(r0)
            java.io.File r1 = new java.io.File
            java.lang.String r2 = "share.jpg"
            r1.<init>(r0, r2)
            r0 = 0
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0035, all -> 0x002d }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0035, all -> 0x002d }
            android.graphics.Bitmap$CompressFormat r3 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r4 = 90
            r6.compress(r3, r4, r2)     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r2.flush()     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r2.close()     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r6.recycle()     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            java.lang.String r6 = r1.getAbsolutePath()     // Catch:{ IOException -> 0x0036, all -> 0x002b }
            r2.close()     // Catch:{ IOException -> 0x003c }
            goto L_0x003c
        L_0x002b:
            r6 = move-exception
            goto L_0x002f
        L_0x002d:
            r6 = move-exception
            r2 = r0
        L_0x002f:
            if (r2 == 0) goto L_0x0034
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            throw r6
        L_0x0035:
            r2 = r0
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ IOException -> 0x003b }
        L_0x003b:
            r6 = r0
        L_0x003c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupWeatherActivity.a(android.graphics.Bitmap):java.lang.String");
    }
}
