package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRMatchingDeviceTypeAdapter;
import com.xiaomi.infrared.bean.IRMatchingDeviceTypeData;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.utils.IRDataUtil;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.Arrays;
import java.util.List;

public class IRMatchingDeviceTypeActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final int TYPE_COPY = 0;
    public static final int TYPE_NEW = 1;

    /* renamed from: a  reason: collision with root package name */
    private static final String f10181a = "extra_type";
    private List<IRMatchingDeviceTypeData> b;
    private int c;
    private DeviceStat d;

    public static void showMatchingDeviceTypeActivity(Activity activity, DeviceStat deviceStat, int i, String[] strArr, Bundle bundle) {
        if (strArr == null || strArr.length != 1) {
            Intent intent = new Intent(activity, IRMatchingDeviceTypeActivity.class);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            intent.putExtra("extra_device", deviceStat);
            intent.putExtra(InifraredContants.IntentParams.h, strArr);
            intent.putExtra(f10181a, i);
            activity.startActivityForResult(intent, 10000);
            return;
        }
        a(activity, deviceStat, i, strArr[0], bundle);
    }

    private static void a(Activity activity, DeviceStat deviceStat, int i, String str, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("extra_device", deviceStat);
        intent.putExtra(InifraredContants.IntentParams.v, IRType.valueOfModel(str).value());
        if (InifraredContants.q.equals(str)) {
            intent.setClass(activity, IRStudyActivity.class);
        } else if (i == 0) {
            if (InifraredContants.h.equals(str)) {
                intent.setClass(activity, IRMatchBranchSetTopBoxActivity.class);
            } else {
                intent.setClass(activity, IRMatchingBrandActivity.class);
            }
        } else if (InifraredContants.f.equals(str) || InifraredContants.g.equals(str)) {
            intent.setClass(activity, IRStudyFailTipsActivity.class);
        } else {
            intent.setClass(activity, IRStudyActivity.class);
        }
        activity.startActivityForResult(intent, 10000);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.d = (DeviceStat) intent.getParcelableExtra("extra_device");
        String[] stringArrayExtra = intent.getStringArrayExtra(InifraredContants.IntentParams.h);
        this.c = intent.getIntExtra(f10181a, 0);
        if (this.d == null) {
            Log.e("openIRController", "XmPluginHostApi.instance().openIRController first param is null");
            finish();
            return;
        }
        setContentView(R.layout.activity_ir_matching_devices);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        findViewById(R.id.module_a_3_return_more_more_btn).setVisibility(8);
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (this.c == 0) {
            textView.setText(R.string.ir_matching_controller);
        } else {
            textView.setText(R.string.ir_study_controller_title);
        }
        GridView gridView = (GridView) findViewById(R.id.ir_matching_device_grid);
        a(stringArrayExtra);
        gridView.setAdapter(new IRMatchingDeviceTypeAdapter(this, this.b));
        gridView.setOnItemClickListener(this);
        findViewById.setOnClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        a(this, this.d, this.c, this.b.get(i).a(), getIntent().getExtras());
    }

    private void a(String[] strArr) {
        LogUtil.c("IRMatchingDeviceTypeActivity", "openAddIRController " + Arrays.toString(strArr));
        if (strArr != null && strArr.length != 0) {
            this.b = IRDataUtil.a(strArr);
        } else if (this.c == 0) {
            this.b = IRDataUtil.b();
        } else {
            this.b = IRDataUtil.c();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_return_btn) {
            onBackPressed();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
    }
}
