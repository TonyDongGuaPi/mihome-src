package com.xiaomi.smarthome.miio.page.devicetag;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.miio.Miio;
import java.util.HashSet;
import java.util.Set;

public class DeviceTagAddActivity extends BaseActivity implements View.OnClickListener {
    public static final String ACTION_TAG_SELECTED = "tag_selected_action";
    private static final int f = 10001;
    private static final int g = 10002;

    /* renamed from: a  reason: collision with root package name */
    private View f19796a;
    private Button b;
    private String c;
    /* access modifiers changed from: private */
    public DeviceTagAddFragment d;
    private View e;
    private BroadcastReceiver h = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(DeviceTagAddActivity.ACTION_TAG_SELECTED, intent.getAction())) {
                DeviceTagAddActivity.this.c();
            }
        }
    };

    public void handleMessage(Message message) {
        if (message.what == 10002) {
            a();
        } else if (message.what == 10001) {
            b();
        }
        super.handleMessage(message);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = getIntent().getStringExtra("did");
        if (TextUtils.isEmpty(this.c)) {
            finish();
            Miio.b("ABC: did null!");
            return;
        }
        setContentView(R.layout.activity_device_tag_add);
        this.d = (DeviceTagAddFragment) getSupportFragmentManager().findFragmentById(R.id.device_tag_left_fragment);
        this.d.a(this.c);
        this.f19796a = findViewById(R.id.module_a_4_return_more_btn);
        this.b = (Button) findViewById(R.id.module_a_4_return_finish_btn);
        ((TextView) findViewById(R.id.module_a_4_return_more_title)).setText(R.string.device_tag_add_title);
        this.b.setText(R.string.confirm);
        this.f19796a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.e = findViewById(R.id.button);
        this.e.setOnClickListener(this);
        StatHelper.p();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.h, new IntentFilter(ACTION_TAG_SELECTED));
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.h);
    }

    public void onClick(View view) {
        if (view == this.f19796a) {
            e();
        } else if (view == this.b) {
            d();
        } else if (view == this.e) {
            a();
        }
    }

    private void a() {
        final DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) LayoutInflater.from(this).inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        MLAlertDialog b3 = new MLAlertDialog.Builder(this).a((int) R.string.tag_add_title).b((View) clientRemarkInputView).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = clientRemarkInputView.getEditText().getText().toString();
                if (b2.a(4, obj)) {
                    DeviceTagAddActivity.this.mHandler.sendEmptyMessage(10001);
                    return;
                }
                b2.a(obj, (Set<String>) null);
                DeviceTagAddActivity.this.d.b(obj);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
        clientRemarkInputView.initialize((ClientRemarkInputView.RenameInterface) null, b3, (String) null, getString(R.string.input_tag_hint), false);
        b3.show();
    }

    private void b() {
        new MLAlertDialog.Builder(this).b((int) R.string.tag_name_duplicate).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DeviceTagAddActivity.this.mHandler.sendEmptyMessage(10002);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void c() {
        this.b.setEnabled(true);
    }

    private void d() {
        String b2 = this.d.b();
        if (!TextUtils.isEmpty(b2)) {
            DeviceTagInterface<Device> b3 = SmartHomeDeviceHelper.a().b();
            HashSet hashSet = new HashSet();
            hashSet.add(this.c);
            b3.a(b2, (Set<String>) hashSet);
        }
        finish();
    }

    private void e() {
        Set<String> c2 = this.d.c();
        if (c2 != null && !c2.isEmpty()) {
            ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).a(c2, this.c, false, (HomeManager.IHomeOperationCallback) null);
        }
        finish();
    }
}
