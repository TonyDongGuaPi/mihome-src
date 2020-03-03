package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.Set;

public class DeviceTagBatchActivity extends BaseActivity implements View.OnClickListener {
    public static final String ACTION_SELECTION_CHANGED = "selection_changed_action";

    /* renamed from: a  reason: collision with root package name */
    private View f19801a;
    private Button b;
    private DeviceTagBatchFragment c;
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(DeviceTagBatchActivity.ACTION_SELECTION_CHANGED, intent.getAction())) {
                DeviceTagBatchActivity.this.b();
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_tag_batch);
        this.f19801a = findViewById(R.id.module_a_4_return_more_btn);
        this.b = (Button) findViewById(R.id.module_a_4_return_finish_btn);
        ((TextView) findViewById(R.id.module_a_4_return_more_title)).setText(R.string.tag_add_device_batch);
        this.f19801a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra("contained_dids_param");
        this.c = (DeviceTagBatchFragment) getSupportFragmentManager().findFragmentById(R.id.device_tag_left_fragment);
        this.c.a(stringArrayListExtra);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.d, new IntentFilter(ACTION_SELECTION_CHANGED));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.d);
    }

    public void onClick(View view) {
        if (this.f19801a == view) {
            finish();
        } else if (this.b == view) {
            a();
        }
    }

    private void a() {
        Set<String> c2 = this.c.c();
        if (c2 != null && !c2.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(c2);
            Intent intent = new Intent();
            intent.putExtra("contained_dids_param", arrayList);
            setResult(-1, intent);
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void b() {
        Set<String> c2 = this.c.c();
        if (c2 == null || c2.isEmpty()) {
            this.b.setEnabled(false);
        } else {
            this.b.setEnabled(true);
        }
    }
}
