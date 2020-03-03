package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.bean.IRBrandType;
import com.xiaomi.infrared.bean.IRFunctionType;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.infrared.bean.IRSTBData;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.bean.InfraredControllerInfo;
import com.xiaomi.infrared.bean.MJSetResultBean;
import com.xiaomi.infrared.bean.MatchInfraredButton;
import com.xiaomi.infrared.bean.MjSingleMatchNodesV2;
import com.xiaomi.infrared.request.ImiSingleMatchManager;
import com.xiaomi.infrared.request.InifraredRequestApi;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.infrared.utils.IRDataUtil;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.audiorecord.ToastUtil;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.FrameManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IRSingleMatchBaseActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f10193a = 2;
    /* access modifiers changed from: private */
    public IRBrandType b;
    private IRSTBData c;
    /* access modifiers changed from: private */
    public int d;
    private TextView e;
    private ImageView f;
    private MatchInfraredButton g;
    private View h;
    private View i;
    private TextView j;
    private View k;
    private LinearLayout l;
    private View m;
    public int[] mTvRes = {R.string.ir_text_tv_tips_no, R.string.ir_text_tv_tips_yes, R.string.ir_text_tv_msg};
    private View n;
    private View o;
    private InifraredRequestApi p = new InifraredRequestApi();
    /* access modifiers changed from: private */
    public ImageView q;
    /* access modifiers changed from: private */
    public ImiSingleMatchManager r;
    private DeviceStat s;
    private String t;

    public static void showSingleMatchActivity(Activity activity, int i2, String str, IRBrandType iRBrandType) {
        Intent intent = new Intent(activity, IRSingleMatchBaseActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(InifraredContants.IntentParams.w, str);
        intent.putExtra(InifraredContants.IntentParams.u, iRBrandType);
        intent.putExtra(InifraredContants.IntentParams.v, i2);
        activity.startActivityForResult(intent, 10000);
    }

    public static void showStbSingleMatchActivity(Activity activity, int i2, IRSTBData iRSTBData) {
        Intent intent = new Intent(activity, IRSingleMatchBaseActivity.class);
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
        this.d = intent.getIntExtra(InifraredContants.IntentParams.v, 0);
        this.s = (DeviceStat) intent.getParcelableExtra("extra_device");
        this.t = intent.getStringExtra(InifraredContants.IntentParams.w);
        if (this.d == IRType.STB.value()) {
            this.c = (IRSTBData) intent.getParcelableExtra(InifraredContants.IntentParams.t);
        } else {
            this.b = (IRBrandType) intent.getParcelableExtra(InifraredContants.IntentParams.u);
        }
        setContentView(R.layout.activity_ir_single_match);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        this.p.a((Context) this);
        this.f = (ImageView) findViewById(R.id.ir_image_btn_icon);
        this.e = (TextView) findViewById(R.id.ir_image_btn_text);
        this.m = findViewById(R.id.ir_single_match_tip_view);
        TextView textView = (TextView) findViewById(R.id.irv_match_no);
        TextView textView2 = (TextView) findViewById(R.id.irv_match_yes);
        TextView textView3 = (TextView) findViewById(R.id.irv3_match_tip_text);
        this.n = findViewById(R.id.ir_rl_test);
        this.h = findViewById(R.id.ir_test_button_next);
        this.i = findViewById(R.id.ir_test_button_last);
        this.j = (TextView) findViewById(R.id.ir_text_test_btn_num);
        this.k = findViewById(R.id.ir_ic_text_operation_ll);
        this.l = (LinearLayout) findViewById(R.id.irv_match_other_ll);
        this.l.setVisibility(8);
        this.o = findViewById(R.id.ir_imi_progress);
        this.q = (ImageView) findViewById(R.id.ivProgress);
        g();
        String string = getResources().getString(IRDataUtil.a(this.d).b());
        String string2 = getResources().getString(R.string.ir_add_ic_operation_description, new Object[]{string});
        String string3 = getResources().getString(R.string.ir_add_ic_title_name, new Object[]{string});
        ((TextView) findViewById(R.id.ir_ic_text_operation_description)).setText(string2);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(string3);
        if (this.d == IRType.TV.value()) {
            e();
        } else {
            a(false);
            c();
        }
        findViewById.setOnClickListener(this);
        this.f.setOnClickListener(this);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    private void a() {
        this.k.setVisibility(4);
        this.o.setVisibility(0);
        Drawable drawable = this.q.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.k.setVisibility(0);
        this.o.setVisibility(8);
        Drawable drawable = this.q.getDrawable();
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).stop();
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        AnonymousClass1 r0 = new AsyncCallback<MjSingleMatchNodesV2, Error>() {
            /* renamed from: a */
            public void onSuccess(MjSingleMatchNodesV2 mjSingleMatchNodesV2) {
                IRSingleMatchBaseActivity.this.b();
                ImiSingleMatchManager unused = IRSingleMatchBaseActivity.this.r = new ImiSingleMatchManager(mjSingleMatchNodesV2.g(), IRType.valueOf(IRSingleMatchBaseActivity.this.d), new ImiSingleMatchManager.IIRSingleMatchResult() {
                    public void a(String str) {
                        IRSingleMatchBaseActivity.this.a(str);
                    }

                    public void a(IRKeyValue iRKeyValue, int i, int i2, int i3) {
                        IRSingleMatchBaseActivity.this.a(iRKeyValue, i, i2, i3);
                    }

                    public void a() {
                        IRSingleMatchBaseActivity.this.b();
                        IRSingleMatchBaseActivity.this.d();
                    }
                });
            }

            public void onFailure(Error error) {
                IRSingleMatchBaseActivity.this.b();
                ToastUtil.a(IRSingleMatchBaseActivity.this, IRSingleMatchBaseActivity.this.getString(R.string.ir_toast_get_data_failed));
            }
        };
        if (this.d == IRType.STB.value()) {
            this.p.a(this.c, (AsyncCallback<MjSingleMatchNodesV2, Error>) r0);
        } else {
            this.p.a(this.d, this.b.c(), (AsyncCallback<MjSingleMatchNodesV2, Error>) r0);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        ToastUtil.a(this, getString(R.string.ir_toast_key_has_no_infra));
        if (IRType.valueOf(this.d) != IRType.AC) {
            ToastUtil.a(this, getString(R.string.ir_single_match_failed));
            Intent intent = getIntent().setClass(this, IRStudyActivity.class);
            intent.putExtra(InifraredContants.IntentParams.v, this.d);
            startActivityForResult(intent, 2);
        }
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        InfraredControllerInfo infraredControllerInfo = new InfraredControllerInfo();
        infraredControllerInfo.i(String.valueOf(str));
        infraredControllerInfo.a(IRType.valueOf(this.d));
        infraredControllerInfo.g(this.c == null ? this.t : this.c.c());
        if (this.d == IRType.STB.value()) {
            infraredControllerInfo.a(this.c.d());
        }
        infraredControllerInfo.a(IRFunctionType.MATCH);
        HashMap hashMap = new HashMap();
        hashMap.put(infraredControllerInfo.j(), infraredControllerInfo);
        a();
        this.p.a(this.s, (Map<String, InfraredControllerInfo>) hashMap, (AsyncCallback<MJSetResultBean, Error>) new AsyncCallback<MJSetResultBean, Error>() {
            /* renamed from: a */
            public void onSuccess(final MJSetResultBean mJSetResultBean) {
                AnonymousClass1 r0 = new Runnable() {
                    public void run() {
                        Intent intent = new Intent();
                        intent.putExtra(InifraredContants.IntentParams.d, true);
                        intent.putExtra(InifraredContants.IntentParams.e, mJSetResultBean.a());
                        intent.putExtra(InifraredContants.IntentParams.f, mJSetResultBean.f());
                        intent.putExtra(InifraredContants.IntentParams.g, mJSetResultBean.c());
                        intent.putExtra(InifraredContants.IntentParams.h, mJSetResultBean.d());
                        IRSingleMatchBaseActivity.this.setResult(-1, intent);
                        IRSingleMatchBaseActivity.this.finish();
                    }
                };
                IRSingleMatchBaseActivity.this.b();
                if (IRSingleMatchBaseActivity.this.getIntent().getBooleanExtra("create_device", true)) {
                    IRSingleMatchBaseActivity.this.q.postDelayed(r0, 1000);
                    FrameManager.b().k().onDeviceReady(IRSingleMatchBaseActivity.this, mJSetResultBean.d(), mJSetResultBean.a(), new IXmPluginHostActivity.AsyncCallback<Void>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                        }

                        public void onFailure(int i, Object obj) {
                        }
                    });
                    return;
                }
                IRSingleMatchBaseActivity.this.q.postDelayed(r0, 0);
            }

            public void onFailure(Error error) {
                IRSingleMatchBaseActivity.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            this.k.setVisibility(4);
            this.n.setVisibility(8);
            this.l.setVisibility(0);
            return;
        }
        this.k.setVisibility(0);
        this.n.setVisibility(0);
        this.l.setVisibility(8);
        a();
    }

    private void e() {
        a(true);
        View inflate = getLayoutInflater().inflate(R.layout.include_ir_tv_tip, (ViewGroup) null);
        this.l.addView(inflate, -1, -1);
        TextView textView = (TextView) inflate.findViewById(R.id.irv_match_no);
        TextView textView2 = (TextView) inflate.findViewById(R.id.irv_match_yes);
        textView.setText(this.mTvRes[0]);
        textView2.setText(this.mTvRes[1]);
        ((TextView) inflate.findViewById(R.id.irv3_match_tip_text)).setText(this.mTvRes[2]);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRSingleMatchBaseActivity.this.a(false);
                IRSingleMatchBaseActivity.this.b.a(false);
                IRSingleMatchBaseActivity.this.c();
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                IRSingleMatchBaseActivity.this.a(false);
                IRSingleMatchBaseActivity.this.b.a(true);
                IRSingleMatchBaseActivity.this.c();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ir_image_btn_icon /*2131429919*/:
                h();
                f();
                return;
            case R.id.ir_test_button_last /*2131429948*/:
                if (this.r != null) {
                    this.r.a();
                    return;
                }
                return;
            case R.id.ir_test_button_next /*2131429949*/:
                if (this.r != null) {
                    this.r.b();
                    return;
                }
                return;
            case R.id.irv_match_no /*2131429977*/:
                if (this.r != null) {
                    this.r.d();
                }
                g();
                return;
            case R.id.irv_match_yes /*2131429979*/:
                if (this.r != null) {
                    this.r.c();
                }
                g();
                return;
            case R.id.module_a_3_return_btn /*2131430969*/:
                finish();
                return;
            default:
                return;
        }
    }

    private void f() {
        this.m.setVisibility(0);
    }

    private void g() {
        this.m.setVisibility(4);
    }

    private void h() {
        if (this.g != null) {
            this.p.a(this.g, (AsyncCallback<String, Error>) null);
        } else {
            ToastUtil.a((Context) this, (int) R.string.ir_toast_key_not_matched, 1);
        }
    }

    /* access modifiers changed from: private */
    public void a(IRKeyValue iRKeyValue, int i2, int i3, int i4) {
        String str;
        String str2;
        int indexOf;
        String str3;
        new ArrayList().add(iRKeyValue);
        InfraredControllerInfo infraredControllerInfo = new InfraredControllerInfo();
        infraredControllerInfo.a(iRKeyValue.b());
        String g2 = iRKeyValue.g();
        if (infraredControllerInfo.f() != IRType.AC) {
            str2 = CommUtil.a(iRKeyValue);
        } else if (InifraredContants.r.equals(g2)) {
            str2 = getResources().getString(R.string.match_ac_power_on);
            iRKeyValue.c(InifraredContants.W);
        } else if (InifraredContants.s.equals(g2)) {
            str2 = getResources().getString(R.string.match_ac_power_off);
            iRKeyValue.c(InifraredContants.X);
        } else if (g2 == null || !g2.startsWith("M") || (indexOf = g2.indexOf(JSMethod.NOT_SET)) <= 0) {
            str2 = getResources().getString(R.string.match_ac_power_auto);
        } else {
            try {
                switch (Integer.parseInt(g2.substring(1, indexOf))) {
                    case 0:
                        str3 = getResources().getString(R.string.match_ac_power_cool);
                        break;
                    case 1:
                        str3 = getResources().getString(R.string.match_ac_power_hot);
                        break;
                    case 2:
                        str3 = getResources().getString(R.string.match_ac_power_auto);
                        break;
                    case 3:
                        str3 = getResources().getString(R.string.match_ac_power_wind);
                        break;
                    case 4:
                        str3 = getResources().getString(R.string.match_ac_power_dir);
                        break;
                    default:
                        str3 = getResources().getString(R.string.match_ac_power_auto);
                        break;
                }
                str = str3;
            } catch (Throwable unused) {
                str2 = getResources().getString(R.string.match_ac_power_auto);
            }
            this.g = new MatchInfraredButton(str, iRKeyValue.d(), g2, this.s.did, infraredControllerInfo.f(), iRKeyValue.c());
            a(i2, i3, i4);
        }
        str = str2;
        this.g = new MatchInfraredButton(str, iRKeyValue.d(), g2, this.s.did, infraredControllerInfo.f(), iRKeyValue.c());
        a(i2, i3, i4);
    }

    private void a(int i2, int i3, int i4) {
        this.f.setImageResource(this.g.a());
        this.e.setText(this.g.b());
        if (i4 == 1) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
        }
        if (i2 == i3) {
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
        }
        this.j.setText(String.format(String.format(getString(R.string.ir_add_ic_test_btn_num), new Object[]{Integer.valueOf(i2), Integer.valueOf(i3)}), new Object[0]));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.p.a();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        } else if (i2 == 2) {
            finish();
        }
    }
}
