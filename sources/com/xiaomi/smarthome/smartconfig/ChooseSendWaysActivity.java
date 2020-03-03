package com.xiaomi.smarthome.smartconfig;

import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.util.List;

public class ChooseSendWaysActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22268a = 1;
    private ScanResult b;
    private String c;
    private List<Integer> d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.choose_send_ways);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseSendWaysActivity.this.finish();
            }
        });
        this.c = (String) SmartConfigDataProvider.a().a("device_model");
        BluetoothLog.c(String.format("ChooseSendWays, model = %s", new Object[]{this.c}));
        this.b = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.camera_choose_send_ways_title);
        this.d = CameraFastConnectConfigs.b(this.c);
        a();
        c();
        e();
        g();
    }

    private void a(View view, int i, int i2, int i3, View.OnClickListener onClickListener, boolean z) {
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        imageView.setImageResource(i);
        TextView textView = (TextView) view.findViewById(R.id.send_title);
        textView.setText(i2);
        TextView textView2 = (TextView) view.findViewById(R.id.send_subtitle);
        textView2.setText(i3);
        view.setOnClickListener(onClickListener);
        view.setEnabled(z);
        if (!z) {
            textView.setTextColor(Color.parseColor("#bfbfbf"));
            textView2.setTextColor(Color.parseColor("#bfbfbf"));
            imageView.setAlpha(0.3f);
            view.setVisibility(8);
            return;
        }
        view.setVisibility(0);
    }

    private void a() {
        a(findViewById(R.id.bluetooth), R.drawable.connect_icon_bluetooth_nor, R.string.camera_send_bluetooth, R.string.camera_send_bluetooth_tips, this, this.d.contains(1));
    }

    private void b() {
        if (this.b == null) {
            startActivityForResult(new Intent(this, ChooseCameraActivity.class), 1);
            return;
        }
        setResult(-1);
        finish();
    }

    private void c() {
        a(findViewById(R.id.qrcode), R.drawable.connect_icon_qrc_nor, R.string.camera_send_qrcode, R.string.camera_send_qrcode_tips, this, this.d.contains(2));
    }

    private void d() {
        setResult(-1);
        finish();
    }

    private void e() {
        a(findViewById(R.id.wifi), R.drawable.connect_icon_wifi_nor, R.string.camera_send_wifi, R.string.camera_send_wifi_tips, this, this.d.contains(3));
    }

    private void f() {
        setResult(-1);
        finish();
    }

    private void g() {
        a(findViewById(R.id.sound), R.drawable.connect_icon_sound_nor, R.string.camera_send_sound, R.string.camera_send_sound_tips, this, this.d.contains(4));
    }

    private void h() {
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private void a(int i) {
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.V, Integer.valueOf(i));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.bluetooth) {
            a(1);
            b();
        } else if (id == R.id.qrcode) {
            a(2);
            d();
        } else if (id == R.id.sound) {
            a(4);
            h();
        } else if (id == R.id.wifi) {
            a(3);
            f();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 1 || i2 != -1) {
            return;
        }
        if (intent == null || intent.getIntExtra(SmartConfigConstants.f22322a, 0) != 2) {
            setResult(-1, intent);
            finish();
        }
    }
}
