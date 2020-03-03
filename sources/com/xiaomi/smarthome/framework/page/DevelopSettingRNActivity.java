package com.xiaomi.smarthome.framework.page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.baseui.ToastManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginRuntimeManager;
import com.xiaomi.smarthome.framework.page.rndebug.RnDebugConstant;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.setting.PluginSetting;
import org.json.JSONException;
import org.json.JSONObject;

public class DevelopSettingRNActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f16715a = "json_data";
    /* access modifiers changed from: private */
    public CheckBox b;
    /* access modifiers changed from: private */
    public CheckBox c;
    /* access modifiers changed from: private */
    public EditText d;
    /* access modifiers changed from: private */
    public EditText e;
    /* access modifiers changed from: private */
    public EditText f;
    private CheckBox g;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public JSONObject i;

    public static void startActivityForResult(BaseActivity baseActivity, int i2) {
        baseActivity.startActivityForResult(new Intent(baseActivity, DevelopSettingRNActivity.class), i2);
    }

    public static void startActivityForResult(BaseActivity baseActivity, int i2, String str) {
        Intent intent = new Intent(baseActivity, DevelopSettingRNActivity.class);
        intent.putExtra(f16715a, str);
        baseActivity.startActivityForResult(intent, i2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.develop_rn_activity);
        a();
        b();
        c();
    }

    private void a() {
        String stringExtra = getIntent().getStringExtra(f16715a);
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                this.i = new JSONObject(stringExtra);
            } catch (JSONException unused) {
                this.i = null;
            }
        }
    }

    private void b() {
        findViewById(R.id.module_a_5_return_finish_btn).setEnabled(true);
        this.b = (CheckBox) findViewById(R.id.remote_debug);
        this.c = (CheckBox) findViewById(R.id.rn_timestamp);
        this.d = (EditText) findViewById(R.id.package_name);
        this.e = (EditText) findViewById(R.id.model_name);
        this.f = (EditText) findViewById(R.id.et_self_scene_id);
        this.g = (CheckBox) findViewById(R.id.cb_debug_plugin_scene_check);
        this.b.setChecked(DevelopSharePreManager.a().f());
        this.c.setChecked(PluginSetting.b());
        findViewById(R.id.module_a_5_return_more_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DevelopSettingRNActivity.this.onBackPressed();
            }
        });
        findViewById(R.id.module_a_5_return_finish_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(DevelopSettingRNActivity.this.d.getText().toString().trim())) {
                    ToastManager.a().a("请输入插件包名");
                } else if (TextUtils.isEmpty(DevelopSettingRNActivity.this.e.getText().toString().trim())) {
                    ToastManager.a().a("请输入设备model");
                } else {
                    String trim = DevelopSettingRNActivity.this.d.getText().toString().trim();
                    if (!TextUtils.isEmpty(trim) && trim.indexOf(46) > 0) {
                        CoreApi.a().c(trim, DevelopSettingRNActivity.this.e.getText().toString());
                    }
                    DevelopSharePreManager.a().a(DevelopSettingRNActivity.this.b.isChecked(), trim, DevelopSettingRNActivity.this.e.getText().toString(), DevelopSettingRNActivity.this.c.isChecked());
                    PluginRuntimeManager.getInstance().exitALLProcess();
                    if (DevelopSettingRNActivity.this.i == null) {
                        JSONObject unused = DevelopSettingRNActivity.this.i = new JSONObject();
                    }
                    try {
                        DevelopSettingRNActivity.this.i.put(RnDebugConstant.f16953a, trim);
                        DevelopSettingRNActivity.this.i.put(RnDebugConstant.b, DevelopSettingRNActivity.this.e.getText().toString().trim());
                        DevelopSettingRNActivity.this.i.put(RnDebugConstant.c, true);
                        DevelopSettingRNActivity.this.i.put(RnDebugConstant.d, DevelopSettingRNActivity.this.f.getText().toString().trim());
                        DevelopSettingRNActivity.this.i.put(RnDebugConstant.e, DevelopSettingRNActivity.this.h);
                    } catch (JSONException e) {
                        RnPluginLog.b(e.toString());
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("jsonData", DevelopSettingRNActivity.this.i.toString());
                    Intent intent = DevelopSettingRNActivity.this.getIntent();
                    intent.putExtra("data", bundle);
                    DevelopSettingRNActivity.this.setResult(1000, intent);
                    DevelopSettingRNActivity.this.onBackPressed();
                    ToastManager.a().a("saved successfully");
                }
            }
        });
        this.g.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                boolean unused = DevelopSettingRNActivity.this.h = z;
            }
        });
    }

    private void c() {
        if (this.i != null) {
            this.d.setText(this.i.optString(RnDebugConstant.f16953a, ""));
            this.e.setText(this.i.optString(RnDebugConstant.b, ""));
            this.f.setText(this.i.optString(RnDebugConstant.d, ""));
            this.h = this.i.optBoolean(RnDebugConstant.e, false);
            this.g.setChecked(this.h);
        }
    }
}
