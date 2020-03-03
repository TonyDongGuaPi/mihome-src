package com.xiaomi.smarthome.wificonfig;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;

public class ChangeNewWifiActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT != 26) {
            setRequestedOrientation(1);
        }
        setContentView(R.layout.change_new_router);
        final String string = getIntent().getExtras().getString("did");
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChangeNewWifiActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_right_text_btn)).setText(R.string.complete);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.wifi_input_wifi);
        findViewById(R.id.module_a_3_right_text_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                for (Device next : SmartHomeDeviceManager.a().d()) {
                    if (next.did.equalsIgnoreCase(string)) {
                        ((MiioDeviceV2) next).a(((EditText) ChangeNewWifiActivity.this.findViewById(R.id.ssid_editor)).getText().toString(), ((EditText) ChangeNewWifiActivity.this.findViewById(R.id.passwd_editor)).getText().toString());
                        ChangeNewWifiActivity.this.finish();
                    }
                }
            }
        });
    }
}
