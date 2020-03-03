package com.xiaomi.smarthome.framework.page.rndebug;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.AppConfigHelper;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.server.internal.plugin.api.PluginSmartHomeApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.DevelopSharePreManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.setting.PluginSetting;

public class RnDebugOtherSettingActivity extends BaseActivity {
    @BindView(2131432686)
    SwitchButton mSwBtnRnPluginSdkVersionInfo;
    @BindView(2131432687)
    SwitchButton mSwBtnRnPluginTimeToal;
    @BindView(2131432688)
    SwitchButton mSwBtnUseOldPluginOnly;
    @BindView(2131432689)
    SwitchButton mSwBtnUsePreviewAppConfig;
    @BindView(2131430975)
    TextView tvHeadTitle;
    @BindView(2131430969)
    View viewHeadLeft;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, RnDebugOtherSettingActivity.class));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rn_debug_other_setting);
        ButterKnife.bind((Activity) this);
        a();
        b();
    }

    private void a() {
        this.tvHeadTitle.setText("其他设置");
        this.mSwBtnRnPluginTimeToal.setChecked(PluginSetting.b());
        this.mSwBtnRnPluginSdkVersionInfo.setChecked(DevelopSharePreManager.a().h());
        this.mSwBtnUseOldPluginOnly.setChecked(PluginSmartHomeApi.a(getContext()));
        this.mSwBtnUsePreviewAppConfig.setChecked(GlobalSetting.E);
    }

    private void b() {
        this.viewHeadLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RnDebugOtherSettingActivity.this.finish();
            }
        });
        this.mSwBtnRnPluginTimeToal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.c("RnPluginTimeToal", "RnPluginTimeToal isChecked: " + z);
                PluginSetting.a(z);
            }
        });
        this.mSwBtnRnPluginSdkVersionInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.c("RnPluginTimeToal", "RnPluginToastSdkVersion isChecked: " + z);
                DevelopSharePreManager.a().e(z);
            }
        });
        this.mSwBtnUseOldPluginOnly.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                PluginSmartHomeApi.a(RnDebugOtherSettingActivity.this.getContext(), z);
            }
        });
        this.mSwBtnUsePreviewAppConfig.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SharePrefsManager.a(CommonApplication.getAppContext(), AppConfigHelper.f13358a, AppConfigHelper.b, z);
                if (z) {
                    GlobalSetting.E = true;
                } else {
                    GlobalSetting.E = false;
                }
            }
        });
    }
}
