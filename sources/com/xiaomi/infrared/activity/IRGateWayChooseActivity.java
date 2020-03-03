package com.xiaomi.infrared.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.infrared.InifraredContants;
import com.xiaomi.infrared.adapter.IRGateWayChooseAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class IRGateWayChooseActivity extends BaseActivity implements View.OnClickListener, IRGateWayChooseAdapter.AdapterOnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10171a = "model";

    public static void showIRGateWayChooseActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, IRGateWayChooseActivity.class);
        intent.putExtra("model", str);
        activity.startActivityForResult(intent, 10000);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ir_gateway_choose);
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        View findViewById2 = findViewById(R.id.module_a_3_return_more_more_btn);
        findViewById2.setVisibility(8);
        ListView listView = (ListView) findViewById(R.id.ir_gateway_choose_list_view);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.get_way_choose_title);
        View findViewById3 = findViewById(R.id.no_gateway_root);
        TextView textView = (TextView) findViewById(R.id.gateway_go_shop);
        textView.getPaint().setFlags(8);
        List<Device> d = SmartHomeDeviceManager.a().d();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < d.size(); i++) {
            Device device = d.get(i);
            PluginRecord d2 = CoreApi.a().d(device.model);
            if (d2 != null && d2.c().t(16)) {
                arrayList.add(device.newDeviceStat());
            }
        }
        if (arrayList.size() == 0) {
            findViewById3.setVisibility(0);
        } else {
            listView.setAdapter(new IRGateWayChooseAdapter(this, arrayList, this));
        }
        findViewById.setOnClickListener(this);
        findViewById2.setOnClickListener(this);
        textView.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_return_btn) {
            onBackPressed();
        }
    }

    public void onClick(View view, DeviceStat deviceStat) {
        String stringExtra = getIntent().getStringExtra("model");
        boolean equals = InifraredContants.q.equals(stringExtra);
        IRMatchingDeviceTypeActivity.showMatchingDeviceTypeActivity(this, deviceStat, equals ? 1 : 0, new String[]{stringExtra}, getIntent().getExtras());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null && intent.getBooleanExtra(InifraredContants.IntentParams.d, false)) {
            setResult(-1, intent);
            finish();
        }
    }
}
