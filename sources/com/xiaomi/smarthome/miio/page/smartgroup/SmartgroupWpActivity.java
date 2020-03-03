package com.xiaomi.smarthome.miio.page.smartgroup;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.model.AreaPropAirWaterInfo;
import com.xiaomi.smarthome.framework.api.model.AreaPropInfo;
import com.xiaomi.smarthome.framework.location.LocationApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.PictureShareActivity;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.shop.utils.ShopLauncher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SmartgroupWpActivity extends BaseActivity {
    public static final int TYPE_AH = 2;
    public static final int TYPE_AP = 0;
    public static final int TYPE_WP = 1;

    /* renamed from: a  reason: collision with root package name */
    private static String f19897a = "SmartgroupWpActivity";
    private static final String[] b = {SmartGroupConstants.b, SmartGroupConstants.c, SmartGroupConstants.d, SmartGroupConstants.e};
    private int c;
    private String d;
    private SmartgroupWpAdapter e;
    private Typeface f;
    private HashMap<String, AreaPropAirWaterInfo> g;
    private BroadcastReceiver h = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            SmartgroupWpActivity.this.j();
            SmartgroupWpActivity.this.k();
        }
    };
    @BindView(2131430971)
    ImageView mIvShare;
    @BindView(2131430969)
    ImageView mIvTitleReturn;
    @BindView(2131430257)
    ImageView mIvWapWork;
    @BindView(2131430742)
    FakeListView mLvItems;
    @BindView(2131433182)
    TextView mTvAddress;
    @BindView(2131433269)
    TextView mTvSource;
    @BindView(2131430975)
    TextView mTvTitle;
    @BindView(2131433551)
    TextView mTvWapTitle;
    @BindView(2131433554)
    TextView mTvWpDataMax;
    @BindView(2131433555)
    TextView mTvWpDataMin;
    @BindView(2131433848)
    LinearLayout mViewShare;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getIntent().getIntExtra("type", 0);
        if (l()) {
            setContentView(R.layout.activity_smartgroup_wp);
        } else if (n()) {
            setContentView(R.layout.activity_smartgroup_ah);
        } else {
            setContentView(R.layout.activity_smartgroup_ap);
        }
        ButterKnife.bind((Activity) this);
        e();
        this.f = FontManager.a(SmartGroupConstants.f);
        this.mTvWpDataMax.setTypeface(this.f);
        this.mTvWpDataMin.setTypeface(this.f);
        this.e = new SmartgroupWpAdapter(this, this.c);
        this.mLvItems.setAdapter(this.e);
        if (m()) {
            b();
        } else if (n()) {
            a();
        } else {
            d();
        }
        this.d = getIntent().getStringExtra(SmartGroupConstants.f19890a);
        j();
        k();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.h, new IntentFilter(AreaInfoManager.f11897a));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.h);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a() {
        this.mIvWapWork.setImageResource(R.drawable.smartgroup_ah_work);
        this.mTvWapTitle.setText(R.string.smartgroup_ah_title_item);
        findViewById(R.id.iv_smartgroup_shop1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShopLauncher.a(SmartgroupWpActivity.this.getContext(), Uri.parse("https://home.mi.com/shop/detail?gid=410&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
            }
        });
    }

    private void b() {
        this.mIvWapWork.setImageResource(R.drawable.smartgroup_ap_work);
        ((ImageView) findViewById(R.id.iv_smartgroup_shop2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShopLauncher.a(SmartgroupWpActivity.this.getContext(), Uri.parse("https://home.mi.com/shop/detail?gid=97&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
            }
        });
        c();
    }

    private void c() {
        int G = AreaInfoManager.a().G();
        ImageView imageView = (ImageView) findViewById(R.id.iv_wap_work_bg);
        if (imageView != null) {
            imageView.setVisibility(0);
            imageView.setColorFilter(G);
        }
    }

    private void d() {
        ((ImageView) findViewById(R.id.iv_smartgroup_shop1)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShopLauncher.a(SmartgroupWpActivity.this.getContext(), Uri.parse("https://home.mi.com/shop/detail?gid=667&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
            }
        });
        ((ImageView) findViewById(R.id.iv_smartgroup_shop2)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShopLauncher.a(SmartgroupWpActivity.this.getContext(), Uri.parse("https://home.mi.com/shop/detail?gid=152&source=" + SHApplication.getAppContext().getPackageName()).toString(), false);
            }
        });
    }

    private void e() {
        this.mIvTitleReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartgroupWpActivity.this.finish();
            }
        });
        if (m()) {
            this.mTvTitle.setText(R.string.smartgroup_ap);
        } else if (l()) {
            this.mTvTitle.setText(R.string.smartgroup_wp);
        } else if (n()) {
            this.mTvTitle.setText(R.string.smartgroup_ah);
        }
        this.mIvShare.setImageResource(R.drawable.smartgroup_share_icon);
        this.mIvShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SmartgroupWpActivity.this.f();
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        String a2 = a(g());
        if (a2 != null) {
            a(a2);
        }
    }

    private Bitmap g() {
        int width = this.mViewShare.getWidth();
        int height = this.mViewShare.getHeight();
        Drawable drawable = getResources().getDrawable(R.drawable.smartgroup_share_water);
        if (m()) {
            drawable = getResources().getDrawable(R.drawable.smartgroup_share_air);
        }
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
        this.mViewShare.draw(canvas);
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.miio.page.smartgroup.SmartgroupWpActivity.a(android.graphics.Bitmap):java.lang.String");
    }

    private void a(String str) {
        PictureShareActivity.share((Context) this, getString(R.string.smartgroup_share_title), (String) null, str);
    }

    private List<String> h() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.d);
        arrayList.addAll(Arrays.asList(b));
        int i = 1;
        while (true) {
            if (i >= arrayList.size()) {
                break;
            } else if (((String) arrayList.get(i)).equalsIgnoreCase(this.d) || AreaInfoManager.a().m().equalsIgnoreCase(getProvince((String) arrayList.get(i)))) {
                arrayList.remove(i);
            } else {
                i++;
            }
        }
        arrayList.remove(i);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void i() {
        List<String> h2 = h();
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < h2.size(); i++) {
            AreaPropAirWaterInfo areaPropAirWaterInfo = this.g.get(h2.get(i));
            if (areaPropAirWaterInfo != null) {
                arrayList.add(areaPropAirWaterInfo);
            }
        }
        this.e.a(arrayList);
        this.mLvItems.refreshUI();
        if (m()) {
            c();
        }
    }

    /* access modifiers changed from: private */
    public void j() {
        AreaInfoManager a2 = AreaInfoManager.a();
        this.mTvAddress.setText(a2.l());
        this.mTvAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CoreApi.a().D()) {
                    AreaInfoManager.a().a(SmartgroupWpActivity.this.getContext());
                }
            }
        });
        AreaPropInfo H = a2.H();
        if (H == null) {
            return;
        }
        if (l()) {
            this.mTvWapTitle.setText(R.string.smartgroup_wp_title);
            this.mTvWpDataMax.setText(H.u);
            this.mTvWpDataMin.setText(H.v);
            try {
                this.mTvSource.setText(getResources().getQuantityString(R.plurals.smartgroup_data_source, Integer.parseInt(H.w), new Object[]{H.w}));
            } catch (Exception unused) {
                this.mTvSource.setText(getResources().getQuantityString(R.plurals.smartgroup_data_source, 4000, new Object[]{H.w}));
            }
        } else if (m()) {
            TextView textView = (TextView) findViewById(R.id.local_aqi_value_tv);
            if (textView != null) {
                textView.setText(H.s);
            }
            this.mTvWpDataMax.setText(H.q);
            this.mTvWpDataMin.setText(H.r);
            try {
                this.mTvSource.setText(getResources().getQuantityString(R.plurals.smartgroup_data_source, Integer.parseInt(H.t), new Object[]{H.t}));
            } catch (Exception unused2) {
                this.mTvSource.setText(getResources().getQuantityString(R.plurals.smartgroup_data_source, 4000, new Object[]{H.t}));
            }
        } else if (n()) {
            this.mTvWapTitle.setText(R.string.smartgroup_ah_title);
            this.mTvWpDataMax.setText(H.z);
            this.mTvWpDataMin.setText(H.y);
            try {
                this.mTvSource.setText(getResources().getQuantityString(R.plurals.smartgroup_data_source, Integer.parseInt(H.A), new Object[]{H.A}));
            } catch (Exception unused3) {
                this.mTvSource.setText(getResources().getQuantityString(R.plurals.smartgroup_data_source, 4000, new Object[]{H.A}));
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(List<AreaPropAirWaterInfo> list) {
        if (this.g == null) {
            this.g = new HashMap<>();
        } else {
            this.g.clear();
        }
        for (AreaPropAirWaterInfo next : list) {
            this.g.put(next.r, next);
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(b));
        LocationApi.a().a((Context) this, (List<String>) arrayList, (AsyncCallback<List<AreaPropAirWaterInfo>, Error>) new AsyncCallback<List<AreaPropAirWaterInfo>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(List<AreaPropAirWaterInfo> list) {
                SmartgroupWpActivity.this.a(list);
                SmartgroupWpActivity.this.i();
            }
        });
    }

    public static String getProvince(String str) {
        if (str == null) {
            return "";
        }
        if (SmartGroupConstants.b.equalsIgnoreCase(str)) {
            return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.smartgroup_address_beijing);
        }
        if (SmartGroupConstants.c.equalsIgnoreCase(str)) {
            return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.smartgroup_address_shanghai);
        }
        if (SmartGroupConstants.d.equalsIgnoreCase(str)) {
            return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.smartgroup_address_guangzhou);
        }
        return SmartGroupConstants.e.equalsIgnoreCase(str) ? XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.smartgroup_address_shenzhen) : "";
    }

    private boolean l() {
        return this.c == 1;
    }

    private boolean m() {
        return this.c == 0;
    }

    private boolean n() {
        return this.c == 2;
    }

    /* access modifiers changed from: protected */
    public String getActivityName() {
        String name = getClass().getName();
        if (l()) {
            return name + "(WaterPurifier)";
        } else if (m()) {
            return name + "(AirPurifier)";
        } else if (!n()) {
            return name;
        } else {
            return name + "(AirHumidity)";
        }
    }
}
