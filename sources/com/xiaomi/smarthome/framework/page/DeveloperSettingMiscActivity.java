package com.xiaomi.smarthome.framework.page;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.setting.PluginSetting;
import java.util.Arrays;
import java.util.Set;

public class DeveloperSettingMiscActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f16759a = false;
    private SwitchButton b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.developer_setting_misc_list_activity);
        findViewById(R.id.clear_common_use).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonUseDeviceDataManager.a().c();
            }
        });
        findViewById(R.id.dump_threads).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean unused = DeveloperSettingMiscActivity.this.f16759a = !DeveloperSettingMiscActivity.this.f16759a;
                DeveloperSettingMiscActivity.this.a(DeveloperSettingMiscActivity.this.f16759a);
            }
        });
        a();
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        Set<Thread> keySet = Thread.getAllStackTraces().keySet();
        if (keySet == null || keySet.isEmpty()) {
            ToastUtil.a((CharSequence) "empty threads set");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("threads:" + keySet.size() + "\n");
        for (Thread next : keySet) {
            sb.append(next.getName() + "\n");
            if (!z) {
                sb.append(Arrays.toString(next.getStackTrace()) + "\n");
            }
            sb.append("==========================\n\n");
        }
        TextView textView = new TextView(getContext());
        textView.setText(sb.toString());
        textView.setTextColor(-16777216);
        textView.setTextIsSelectable(true);
        ScrollView scrollView = new ScrollView(getContext());
        scrollView.addView(textView, new FrameLayout.LayoutParams(-1, -1));
        new MLAlertDialog.Builder(getContext()).b((View) scrollView).d();
    }

    private void a() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeveloperSettingMiscActivity.this.finish();
            }
        });
        this.b = (SwitchButton) findViewById(R.id.swb_rn_plugin_time_total);
        this.b.setChecked(PluginSetting.b());
        this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.c("RnPluginTimeToal", "RnPluginTimeToal isChecked: " + z);
                PluginSetting.a(z);
            }
        });
    }
}
