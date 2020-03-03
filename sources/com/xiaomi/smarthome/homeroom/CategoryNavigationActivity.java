package com.xiaomi.smarthome.homeroom;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.stat.STAT;

public class CategoryNavigationActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public View f17922a;
    /* access modifiers changed from: private */
    public View b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_category_navigation);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.category_manager);
        findViewById(R.id.module_a_3_right_text_btn).setVisibility(8);
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CategoryNavigationActivity.this.onBackPressed();
            }
        });
        View findViewById = findViewById(R.id.category_toogle);
        final SwitchButton switchButton = (SwitchButton) findViewById(R.id.category_toogle_btn);
        switchButton.setChecked(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.t, true));
        switchButton.setOnTouchEnable(false);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchButton.setChecked(!switchButton.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.t, switchButton.isChecked());
                if (switchButton.isChecked()) {
                    CategoryNavigationActivity.this.f17922a.setVisibility(0);
                    CategoryNavigationActivity.this.b.setVisibility(0);
                } else {
                    CategoryNavigationActivity.this.f17922a.setVisibility(8);
                    CategoryNavigationActivity.this.b.setVisibility(8);
                }
                STAT.d.c(switchButton.isChecked());
            }
        });
        this.f17922a = findViewById(R.id.category_show_first);
        if (switchButton.isChecked()) {
            this.f17922a.setVisibility(0);
        } else {
            this.f17922a.setVisibility(8);
        }
        final SwitchButton switchButton2 = (SwitchButton) findViewById(R.id.category_show_first_btn);
        switchButton2.setChecked(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.u, false));
        switchButton2.setOnTouchEnable(false);
        this.f17922a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchButton2.setChecked(!switchButton2.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.u, switchButton2.isChecked());
                STAT.d.d(switchButton2.isChecked());
            }
        });
        this.b = findViewById(R.id.subcategory_show);
        if (switchButton.isChecked()) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        final SwitchButton switchButton3 = (SwitchButton) findViewById(R.id.subcategory_show_btn);
        switchButton3.setChecked(SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.v, false));
        switchButton3.setOnTouchEnable(false);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                switchButton3.setChecked(!switchButton3.isChecked());
                SharePrefsManager.a(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.v, switchButton3.isChecked());
                DeviceTagManager.W = !switchButton3.isChecked();
            }
        });
    }
}
