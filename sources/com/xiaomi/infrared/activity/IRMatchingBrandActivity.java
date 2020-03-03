package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRMatchingBrandAdapter;
import com.xiaomi.infrared.bean.IRBrandType;
import com.xiaomi.infrared.bean.IRMatchingDeviceTypeData;
import com.xiaomi.infrared.bean.IRSTBData;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.request.InifraredRequestApi;
import com.xiaomi.infrared.utils.IRDataUtil;
import com.xiaomi.infrared.widget.SideBar;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class IRMatchingBrandActivity extends BaseActivity implements View.OnClickListener {
    public static final int REQUEST_SEARCH = 11;

    /* renamed from: a  reason: collision with root package name */
    private static final String f10177a = "IRMatchingBrandActivity";
    /* access modifiers changed from: private */
    public ListView b;
    /* access modifiers changed from: private */
    public IRMatchingBrandAdapter c;
    /* access modifiers changed from: private */
    public ArrayList<IRBrandType> d = new ArrayList<>();
    private IRSTBData e;
    private InifraredRequestApi f = new InifraredRequestApi();
    /* access modifiers changed from: private */
    public SideBar g;
    private TextView h;
    private View i;
    /* access modifiers changed from: private */
    public int j;
    private ImageView k;

    public static void showMatchingBrandActivity(Activity activity, IRSTBData iRSTBData, int i2) {
        Intent intent = new Intent(activity, IRMatchingBrandActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(InifraredContants.IntentParams.t, iRSTBData);
        intent.putExtra(InifraredContants.IntentParams.v, i2);
        activity.startActivityForResult(intent, 10000);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.j = intent.getIntExtra(InifraredContants.IntentParams.v, 0);
        if (this.j == IRType.STB.value()) {
            this.e = (IRSTBData) intent.getParcelableExtra(InifraredContants.IntentParams.t);
        }
        setContentView(R.layout.activity_ir_matching_brand);
        this.f.a((Context) this);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        IRMatchingDeviceTypeData a2 = IRDataUtil.a(this.j);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(String.format(getResources().getString(R.string.ir_matching_brand_title), new Object[]{getResources().getString(a2.b())}));
        this.b = (ListView) findViewById(R.id.ir_select_tv_brand_list);
        this.c = new IRMatchingBrandAdapter(this, this.d);
        this.b.setAdapter(this.c);
        this.g = (SideBar) findViewById(R.id.ir_select_index_bar);
        this.h = (TextView) findViewById(R.id.ir_select_index_dialog);
        this.i = findViewById(R.id.ir_imi_progress);
        this.k = (ImageView) findViewById(R.id.ivProgress);
        c();
        d();
        findViewById.setOnClickListener(this);
        ((TextView) findViewById(R.id.ir_search_text)).setOnClickListener(this);
        this.b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                IRMatchingBrandActivity.this.a((IRBrandType) IRMatchingBrandActivity.this.d.get(i));
            }
        });
    }

    private void a() {
        this.i.setVisibility(0);
        Drawable drawable = this.k.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.i.setVisibility(8);
        Drawable drawable = this.k.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ir_search_text) {
            IRSearchActivity.showSearchActivity(this, this.d, 11);
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    private void c() {
        this.g.setTextView(this.h);
        this.g.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            public void a(String str) {
                int b;
                if (IRMatchingBrandActivity.this.c != null && (b = IRMatchingBrandActivity.this.c.b(str.charAt(0))) != -1) {
                    IRMatchingBrandActivity.this.b.setSelection(b);
                }
            }
        });
    }

    private void d() {
        a();
        AnonymousClass3 r0 = new AsyncCallback<ArrayList<IRBrandType>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<IRBrandType> arrayList) {
                IRMatchingBrandActivity.this.d.clear();
                IRMatchingBrandActivity.this.d.addAll(arrayList);
                Collections.sort(IRMatchingBrandActivity.this.d);
                if (IRMatchingBrandActivity.this.j != IRType.AC.value()) {
                    IRBrandType iRBrandType = new IRBrandType();
                    String string = IRMatchingBrandActivity.this.getResources().getString(R.string.ir_device_type_unknown);
                    iRBrandType.c(string);
                    iRBrandType.d(string);
                    iRBrandType.a("#");
                    iRBrandType.e("#");
                    IRMatchingBrandActivity.this.d.add(iRBrandType);
                }
                ArrayList arrayList2 = new ArrayList();
                String str = null;
                Iterator it = IRMatchingBrandActivity.this.d.iterator();
                while (it.hasNext()) {
                    IRBrandType iRBrandType2 = (IRBrandType) it.next();
                    if (!TextUtils.equals(str, iRBrandType2.f())) {
                        str = iRBrandType2.f();
                        arrayList2.add(str);
                    }
                }
                SideBar.b = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                IRMatchingBrandActivity.this.g.invalidate();
                IRMatchingBrandActivity.this.c.notifyDataSetChanged();
                IRMatchingBrandActivity.this.b();
            }

            public void onFailure(Error error) {
                IRMatchingBrandActivity.this.b();
            }
        };
        if (this.j == IRType.STB.value()) {
            this.f.c(r0);
        } else {
            this.f.a(this.j, (AsyncCallback<ArrayList<IRBrandType>, Error>) r0);
        }
    }

    /* access modifiers changed from: private */
    public void a(IRBrandType iRBrandType) {
        if (getResources().getString(R.string.ir_device_type_unknown).equals(iRBrandType.d())) {
            Intent intent = new Intent(this, IRStudyActivity.class);
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                intent.putExtras(extras);
            }
            intent.putExtra(InifraredContants.IntentParams.v, this.j);
            startActivityForResult(intent, 10000);
        } else if (this.j == IRType.STB.value()) {
            this.e.c(iRBrandType.c());
            this.e.e(iRBrandType.d());
            IRSingleMatchBaseActivity.showStbSingleMatchActivity(this, IRType.STB.value(), this.e);
        } else {
            IRSingleMatchBaseActivity.showSingleMatchActivity(this, this.j, iRBrandType.c(), iRBrandType);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f.a();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent != null) {
            boolean z = false;
            if (intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
                setResult(-1, intent);
                finish();
                return;
            }
            boolean z2 = 11 == i2;
            if (-1 == i3) {
                z = true;
            }
            if (z2 && z) {
                a((IRBrandType) intent.getParcelableExtra(InifraredContants.IntentParams.u));
            }
        }
    }
}
