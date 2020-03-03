package com.xiaomi.smarthome.miio.page.usrexpplan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AppUsrExpPlanUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.scenenew.debug.MyDebugLogger;

public class PrivacySettingActivity extends FragmentActivity {
    public static final String INTENT_KEY_DID = "device_did";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public View f19944a;
    /* access modifiers changed from: private */
    public String b;
    private TextView c;
    private SwitchButton d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.activity_privacy_setting_layout);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacySettingActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.privacy_setting);
        this.c = (TextView) findViewById(R.id.app_info);
        this.d = (SwitchButton) findViewById(R.id.usr_exp_plan_switch);
        getWindow().addFlags(524288);
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getStringExtra(INTENT_KEY_DID);
        }
        b();
        findViewById(R.id.usr_exp_plan_tip).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacySettingActivity.this.a();
            }
        });
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            this.c.setText(String.format(getString(R.string.version_name_string), new Object[]{packageInfo.versionName}));
            this.c.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    Vibrator vibrator = (Vibrator) PrivacySettingActivity.this.getSystemService("vibrator");
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(100);
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    final SharedPreferences sharedPreferences = SHApplication.getAppContext().getSharedPreferences(MyDebugLogger.f21933a, 0);
                    SharePrefsManager.a(sharedPreferences, MyDebugLogger.b, true);
                    SharePrefsManager.b(sharedPreferences, MyDebugLogger.c, currentTimeMillis);
                    SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                        public void run() {
                            if (System.currentTimeMillis() - SharePrefsManager.a(sharedPreferences, MyDebugLogger.c, 0) >= 60000) {
                                SharePrefsManager.a(sharedPreferences, MyDebugLogger.b, false);
                            }
                        }
                    }, 60000);
                    return false;
                }
            });
            if (CoreApi.a().D()) {
                findViewById(R.id.international_icon).setVisibility(0);
            }
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        Intent intent = new Intent(this, UsrExpPlanActivity.class);
        intent.putExtras(getIntent());
        startActivity(intent);
    }

    private void b() {
        this.f19944a = findViewById(R.id.join_usr_exp_plan_container);
        this.f19944a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PrivacySettingActivity.this.a(!TextUtils.isEmpty(PrivacySettingActivity.this.b) ? !AppUsrExpPlanUtil.a(PrivacySettingActivity.this.getApplicationContext(), PrivacySettingActivity.this.b) : !AppUsrExpPlanUtil.a(PrivacySettingActivity.this.getApplicationContext()));
            }
        });
        this.d.setChecked(TextUtils.isEmpty(this.b) ? AppUsrExpPlanUtil.a(getApplicationContext()) : AppUsrExpPlanUtil.a(getApplicationContext(), this.b));
        this.d.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PrivacySettingActivity.this.f19944a.performClick();
            }
        });
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (ServerCompact.e(CoreApi.a().F())) {
                    ((TextView) PrivacySettingActivity.this.findViewById(R.id.usr_exp_plan_container_tv)).setText(R.string.usr_exp_plan2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        if (TextUtils.isEmpty(this.b)) {
            AppUsrExpPlanUtil.a(getApplicationContext(), z);
        } else {
            AppUsrExpPlanUtil.a(getApplicationContext(), this.b, z);
        }
        this.d.setChecked(z);
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.c(this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.a(context));
    }
}
