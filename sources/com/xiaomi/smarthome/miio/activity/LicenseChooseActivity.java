package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import com.mi.global.shop.util.ClickUtil;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.usrexpplan.UsrExpPlanActivity;
import com.xiaomi.smarthome.newui.onekey_delete.AppDataCleanHelper;
import com.xiaomi.smarthome.newui.onekey_delete.PaperShredderView;
import com.xiaomi.smarthome.newui.onekey_delete.RevokeAuthActivity;
import com.xiaomi.smarthome.stat.STAT;

public class LicenseChooseActivity extends FragmentActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11773a = 602;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        DisplayUtils.e(this);
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26 || !DisplayUtils.d(this)) {
            setRequestedOrientation(1);
        }
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        setContentView(R.layout.activity_license_and_privacy_setting);
        a();
        b();
    }

    private void a() {
        TitleBarUtil.a((Activity) this);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LicenseChooseActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.device_more_activity_license_privacy));
    }

    private void b() {
        new Intent(this, UserLicense.class);
        findViewById(R.id.license).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!ClickUtil.a()) {
                    OpenExternalBrowserCompat.a(LicenseChooseActivity.this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_LICENSE));
                }
            }
        });
        findViewById(R.id.privacy).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!ClickUtil.a()) {
                    OpenExternalBrowserCompat.a(LicenseChooseActivity.this, UserLicense.getUrlByTpye(UserLicense.TYPE_USER_PRIVACY));
                }
            }
        });
        View findViewById = findViewById(R.id.usr_exp_plan_container);
        findViewById.setVisibility(0);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!ClickUtil.a()) {
                    LicenseChooseActivity.this.startActivity(new Intent(LicenseChooseActivity.this, UsrExpPlanActivity.class));
                }
            }
        });
        CoreApi.a().a(SHApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                if (ServerCompact.e(CoreApi.a().F())) {
                    ((TextView) LicenseChooseActivity.this.findViewById(R.id.usr_exp_plan_container_tv)).setText(R.string.usr_exp_plan2);
                }
            }
        });
        findViewById(R.id.cancel_license_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.bm();
                LicenseChooseActivity.this.startActivityForResult(new Intent(LicenseChooseActivity.this, RevokeAuthActivity.class), 602);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        STAT.c.l();
    }

    public void onContentChanged() {
        super.onContentChanged();
        TitleBarUtil.c(this);
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(LanguageUtil.a(context));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 602) {
            findViewById(R.id.item_group).setVisibility(4);
            findViewById(R.id.clear_group).setVisibility(0);
            c();
        }
    }

    private void c() {
        PaperShredderView paperShredderView = (PaperShredderView) findViewById(R.id.clear_data_image);
        paperShredderView.setVisibility(0);
        paperShredderView.startAnim();
        SHApplication.getGlobalWorkerHandler().postDelayed(new Runnable() {
            public final void run() {
                LicenseChooseActivity.this.d();
            }
        }, 3000);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d() {
        AppDataCleanHelper.a(this);
        Process.killProcess(Process.myPid());
    }
}
