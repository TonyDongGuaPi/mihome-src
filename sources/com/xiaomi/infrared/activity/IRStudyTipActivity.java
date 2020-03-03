package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.infrared.utils.IRDataUtil;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.youpin.utils.LogUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class IRStudyTipActivity extends BaseActivity implements View.OnClickListener {
    public static final String IR_API_JSON_KEY = "key";
    public static final String IR_API_JSON_MIIO_LEARN = "miIO.ir_learn";
    public static final String IR_API_JSON_MIIO_READ = "miIO.ir_read";
    public static final String IR_API_JSON_MIIO_STOP_LEARN = "miIO.ir_learn_stop";
    public static final String TAG = "IRStudyTipActivity";
    private static final int i = -9950;
    private static final int j = -5003;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Runnable f10211a = new Runnable() {
        public void run() {
            if (!IRStudyTipActivity.this.isFinishing()) {
                IRStudyTipActivity.this.a();
            }
        }
    };
    private Bundle b;
    private String c;
    /* access modifiers changed from: private */
    public String d;
    private String e;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public int g = 0;
    private DeviceStat h;

    static /* synthetic */ int access$408(IRStudyTipActivity iRStudyTipActivity) {
        int i2 = iRStudyTipActivity.g;
        iRStudyTipActivity.g = i2 + 1;
        return i2;
    }

    public static void showStudyTipActivity(Activity activity, IRKeyValue iRKeyValue, Bundle bundle, int i2) {
        Intent intent = new Intent(activity, IRStudyTipActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(InifraredContants.IntentParams.q, iRKeyValue);
        intent.putExtra(IRStudyActivity.RESULT_BUNDLE_KEY, bundle);
        activity.startActivityForResult(intent, i2);
    }

    public static void showStudyTipActivity(Activity activity, String str, Bundle bundle, int i2) {
        Intent intent = new Intent(activity, IRStudyTipActivity.class);
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.putExtra(InifraredContants.IntentParams.p, str);
        intent.putExtra(IRStudyActivity.RESULT_BUNDLE_KEY, bundle);
        activity.startActivityForResult(intent, i2);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.b = intent.getBundleExtra(IRStudyActivity.RESULT_BUNDLE_KEY);
        String string = this.b.getString(IRStudyActivity.KEY_DEVICE_IMAGE);
        this.d = intent.getStringExtra(InifraredContants.IntentParams.p);
        IRKeyValue iRKeyValue = (IRKeyValue) intent.getParcelableExtra(InifraredContants.IntentParams.q);
        this.h = (DeviceStat) intent.getParcelableExtra("extra_device");
        if (iRKeyValue != null) {
            this.d = iRKeyValue.e();
            this.e = CommUtil.a(iRKeyValue);
            this.c = iRKeyValue.a();
        }
        setContentView(R.layout.activity_ir_study_tip);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        View findViewById2 = findViewById(R.id.module_a_3_return_more_more_btn);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.ivDevice);
        findViewById2.setVisibility(8);
        if (TextUtils.isEmpty(string)) {
            string = this.h.deviceIconReal;
        }
        simpleDraweeView.setImageURI(string);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.h.name);
        LogUtils.e(TAG, "initView: device mDeviceStat.did :" + this.h.did);
        findViewById.setOnClickListener(this);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
        b();
        this.mHandler.removeCallbacks(this.f10211a);
    }

    public void onResume() {
        super.onResume();
        c();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_return_btn) {
            onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        sendRequest(IR_API_JSON_MIIO_READ, "key", this.f, new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                LogUtils.c(IRStudyTipActivity.TAG, "read mStudyKey " + IRStudyTipActivity.this.d + " " + IRStudyTipActivity.this.f + " success" + str);
                String a2 = IRDataUtil.a(IRStudyTipActivity.this.f, str);
                if (!a2.isEmpty()) {
                    IRStudyTipActivity.this.a(a2);
                    return;
                }
                int unused = IRStudyTipActivity.this.g = 0;
                IRStudyTipActivity.this.mHandler.removeCallbacks(IRStudyTipActivity.this.f10211a);
                IRStudyTipActivity.this.mHandler.postDelayed(IRStudyTipActivity.this.f10211a, 1000);
            }

            public void onFailure(int i, String str) {
                LogUtils.c(IRStudyTipActivity.TAG, "read mStudyKey " + IRStudyTipActivity.this.d + " failed " + i + " errorInfo " + str);
                int i2 = R.string.ir_toast_key_read_other_error;
                if (i == IRStudyTipActivity.i || i == -3) {
                    if (IRStudyTipActivity.access$408(IRStudyTipActivity.this) < 10) {
                        IRStudyTipActivity.this.mHandler.removeCallbacks(IRStudyTipActivity.this.f10211a);
                        IRStudyTipActivity.this.mHandler.postDelayed(IRStudyTipActivity.this.f10211a, 1000);
                        i2 = -1;
                    }
                } else if (i == -5003) {
                    i2 = R.string.ir_toast_key_read_timeout;
                }
                if (i2 != -1) {
                    IRStudyTipActivity.this.b(IRStudyTipActivity.this.getString(i2) + Operators.BRACKET_START_STR + i + Operators.BRACKET_END_STR);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        LogUtils.c(TAG, "got code " + str + " mStudyKey  " + this.d + "  mDisplayKey  :" + this.e);
        IRKeyValue iRKeyValue = new IRKeyValue();
        iRKeyValue.f(str);
        iRKeyValue.d(this.d);
        iRKeyValue.a(this.c);
        iRKeyValue.c(TextUtils.isEmpty(this.e) ? this.d : this.e);
        iRKeyValue.a((int) InifraredContants.b);
        Intent intent = new Intent();
        intent.putExtra(InifraredContants.IntentParams.r, iRKeyValue);
        intent.putExtra(IRStudyActivity.RESULT_BUNDLE_KEY, this.b);
        setResult(-1, intent);
        finish();
    }

    private void b() {
        if (!TextUtils.isEmpty(this.d)) {
            sendRequest(IR_API_JSON_MIIO_STOP_LEARN, "key", this.f, new Callback<String>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    LogUtils.c(IRStudyTipActivity.TAG, "stopStudyKey" + IRStudyTipActivity.this.d + " " + IRStudyTipActivity.this.f + " success " + str);
                }

                public void onFailure(int i, String str) {
                    LogUtils.c(IRStudyTipActivity.TAG, "stopStudyKey key " + IRStudyTipActivity.this.d + " " + IRStudyTipActivity.this.f + " failed " + i);
                }
            });
        }
    }

    private void c() {
        if (!TextUtils.isEmpty(this.d)) {
            this.f = IRDataUtil.a();
            sendRequest(IR_API_JSON_MIIO_LEARN, "key", this.f, new Callback<String>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    LogUtils.c(IRStudyTipActivity.TAG, "startStudyKey key " + IRStudyTipActivity.this.d + " " + IRStudyTipActivity.this.f + " success " + str);
                    IRStudyTipActivity.this.mHandler.removeCallbacks(IRStudyTipActivity.this.f10211a);
                    IRStudyTipActivity.this.mHandler.postDelayed(IRStudyTipActivity.this.f10211a, 1000);
                }

                public void onFailure(int i, String str) {
                    LogUtils.c(IRStudyTipActivity.TAG, "startStudyKey key " + IRStudyTipActivity.this.d + " " + IRStudyTipActivity.this.f + " failed " + i);
                    StringBuilder sb = new StringBuilder();
                    sb.append(IRStudyTipActivity.this.getString(R.string.ir_toast_key_read_other_error));
                    sb.append(Operators.BRACKET_START_STR);
                    sb.append(i);
                    sb.append(Operators.BRACKET_END_STR);
                    IRStudyTipActivity.this.b(sb.toString());
                }
            });
        }
    }

    public void sendRequest(String str, String str2, String str3, Callback<String> callback) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!str2.isEmpty()) {
                jSONObject.put(str2, str3);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        XmPluginHostApi.instance().callMethod(this.h.did, str, jSONObject, callback, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return str;
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        Toast.makeText(this, str, 0).show();
        finish();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
    }
}
