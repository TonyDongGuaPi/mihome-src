package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRLocationAdapter;
import com.xiaomi.infrared.bean.NameIdEntity;
import com.xiaomi.infrared.request.InifraredRequestApi;
import com.xiaomi.infrared.widget.SideBar;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;

public class IRSelectProvinceActivity extends BaseActivity implements View.OnClickListener {
    public static final int AREA_CODE = 102;
    public static final int CITY_CODE = 101;
    public static final String INTENT_KEY_SHOW_TYPE = "intent_key_show_type";
    public static final int PROVINCE_CODE = 100;

    /* renamed from: a  reason: collision with root package name */
    private View f10187a;
    /* access modifiers changed from: private */
    public ListView b;
    private SideBar c;
    private TextView d;
    /* access modifiers changed from: private */
    public ArrayList<NameIdEntity> e;
    private InifraredRequestApi f = new InifraredRequestApi();
    /* access modifiers changed from: private */
    public IRLocationAdapter g;
    private int h = 0;
    private ImageView i;

    public static void startProvince(Activity activity, int i2) {
        Intent intent = new Intent(activity, IRSelectProvinceActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(INTENT_KEY_SHOW_TYPE, 102);
        activity.startActivityForResult(intent, i2);
    }

    public static void startCity(Activity activity, String str, String str2, int i2) {
        Intent intent = new Intent(activity, IRSelectProvinceActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(INTENT_KEY_SHOW_TYPE, 101);
        intent.putExtra(InifraredContants.IntentParams.l, str);
        intent.putExtra(InifraredContants.IntentParams.k, str2);
        activity.startActivityForResult(intent, i2);
    }

    public static void startArea(Activity activity, String str, String str2, int i2) {
        Intent intent = new Intent(activity, IRSelectProvinceActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(INTENT_KEY_SHOW_TYPE, 103);
        intent.putExtra(InifraredContants.IntentParams.j, str2);
        intent.putExtra(InifraredContants.IntentParams.m, str);
        activity.startActivityForResult(intent, i2);
    }

    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.h = intent.getIntExtra(INTENT_KEY_SHOW_TYPE, 0);
        if (this.h == 101) {
            str = intent.getStringExtra(InifraredContants.IntentParams.l);
        } else {
            str = this.h == 103 ? intent.getStringExtra(InifraredContants.IntentParams.m) : null;
        }
        setContentView(R.layout.activity_ir_matching_brand);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        View findViewById2 = findViewById(R.id.module_a_3_return_more_more_btn);
        findViewById2.setVisibility(4);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.ir_select_area_title);
        this.f.a((Context) this);
        this.b = (ListView) findViewById(R.id.ir_select_tv_brand_list);
        this.c = (SideBar) findViewById(R.id.ir_select_index_bar);
        this.d = (TextView) findViewById(R.id.ir_select_index_dialog);
        this.f10187a = findViewById(R.id.ir_imi_progress);
        this.i = (ImageView) findViewById(R.id.ivProgress);
        TextView textView = (TextView) findViewById(R.id.ir_search_text);
        textView.setText(R.string.inifrare_search);
        c();
        this.c.setVisibility(4);
        a(str);
        findViewById.setOnClickListener(this);
        findViewById2.setOnClickListener(this);
        textView.setOnClickListener(this);
        this.b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                IRSelectProvinceActivity.this.a((NameIdEntity) IRSelectProvinceActivity.this.e.get(i));
            }
        });
    }

    private void a() {
        this.f10187a.setVisibility(0);
        Drawable drawable = this.i.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.f10187a.setVisibility(8);
        Drawable drawable = this.i.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
    }

    /* access modifiers changed from: private */
    public void a(NameIdEntity nameIdEntity) {
        if (this.h == 103) {
            String a2 = nameIdEntity.a();
            String c2 = nameIdEntity.c();
            Intent intent = getIntent();
            intent.putExtra(InifraredContants.IntentParams.o, c2);
            intent.putExtra(InifraredContants.IntentParams.n, a2);
            intent.putExtra(InifraredContants.IntentParams.i, true);
            setResult(-1, intent);
            onBackPressed();
        } else if (this.h == 102) {
            String a3 = nameIdEntity.a();
            startCity(this, String.valueOf(a3), nameIdEntity.c(), 101);
        } else {
            String a4 = nameIdEntity.a();
            startArea(this, String.valueOf(a4), nameIdEntity.c(), 102);
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ir_search_text) {
            IRSearchProvinceActivity.showActivity(this, this.e, this.h);
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    private void c() {
        this.c.setTextView(this.d);
        this.c.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            public void a(String str) {
                int b = IRSelectProvinceActivity.this.g.b(str.charAt(0));
                if (b != -1) {
                    IRSelectProvinceActivity.this.b.setSelection(b);
                }
            }
        });
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
    }

    private void a(String str) {
        a();
        if (this.h == 102) {
            d();
        } else if (this.h == 101) {
            b(str);
        } else if (this.h == 103) {
            c(str);
        }
    }

    private void b(String str) {
        this.f.b(str, (AsyncCallback<ArrayList<NameIdEntity>, Error>) new AsyncCallback<ArrayList<NameIdEntity>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<NameIdEntity> arrayList) {
                ArrayList unused = IRSelectProvinceActivity.this.e = arrayList;
                IRSelectProvinceActivity.this.e();
                IRSelectProvinceActivity.this.b();
            }

            public void onFailure(Error error) {
                IRSelectProvinceActivity.this.b();
            }
        });
    }

    private void d() {
        this.f.b(new AsyncCallback<ArrayList<NameIdEntity>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<NameIdEntity> arrayList) {
                ArrayList unused = IRSelectProvinceActivity.this.e = arrayList;
                IRSelectProvinceActivity.this.e();
                IRSelectProvinceActivity.this.b();
            }

            public void onFailure(Error error) {
                IRSelectProvinceActivity.this.b();
            }
        });
    }

    private void c(String str) {
        this.f.c(str, new AsyncCallback<ArrayList<NameIdEntity>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<NameIdEntity> arrayList) {
                ArrayList unused = IRSelectProvinceActivity.this.e = arrayList;
                IRSelectProvinceActivity.this.e();
                IRSelectProvinceActivity.this.b();
            }

            public void onFailure(Error error) {
                IRSelectProvinceActivity.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.g == null) {
            this.g = new IRLocationAdapter(this, this.e, this.h);
            this.b.setAdapter(this.g);
            return;
        }
        this.g.a(this.e);
    }

    public void onDestroy() {
        super.onDestroy();
        this.f.a();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent == null) {
            return;
        }
        if (intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        } else if (intent.getBooleanExtra(InifraredContants.IntentParams.i, false)) {
            setResult(-1, intent);
            finish();
        } else if (i2 == this.h && -1 == i3) {
            a((NameIdEntity) intent.getParcelableExtra(InifraredContants.IntentParams.c));
        }
    }
}
