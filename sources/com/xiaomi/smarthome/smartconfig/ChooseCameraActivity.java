package com.xiaomi.smarthome.smartconfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.bluetooth.SearchRequest;
import com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import com.xiaomi.smarthome.smartconfig.ChooseCameraAdapter;
import com.xiaomi.smarthome.wificonfig.CameraBarcodeHelpActivity;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiScanManager;
import java.util.HashMap;
import java.util.LinkedList;

public class ChooseCameraActivity extends BaseActivity implements ChooseCameraAdapter.ICameraChooser {
    public static final String EXTRA_MODEL = "extra.model";

    /* renamed from: a  reason: collision with root package name */
    private static final int f22245a = 1;
    private static final int b = 1;
    private static final int c = 2000;
    private static final int d = 2000;
    /* access modifiers changed from: private */
    public String e;
    private ListView f;
    private ChooseCameraAdapter g;
    /* access modifiers changed from: private */
    public Button h;
    /* access modifiers changed from: private */
    public ProgressBar i;
    private HashMap<String, ScanResult> j;
    private HashMap<String, BleDevice> k;
    private HashMap<String, CameraItem> l;
    private CameraReceiver m;

    public class CameraItem {

        /* renamed from: a  reason: collision with root package name */
        ScanResult f22252a;
        BleDevice b;

        public CameraItem(ScanResult scanResult, BleDevice bleDevice) {
            this.f22252a = scanResult;
            this.b = bleDevice;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.choose_camera_activity);
        this.e = (String) SmartConfigDataProvider.a().a("device_model");
        BluetoothLog.c(String.format("ChooseCameraActivity model = %s", new Object[]{this.e}));
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseCameraActivity.this.finish();
            }
        });
        findViewById(R.id.module_a_3_more).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseCameraActivity.this.finish();
            }
        });
        this.i = (ProgressBar) findViewById(R.id.pbar);
        this.h = (Button) findViewById(R.id.refresh);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseCameraActivity.this.a();
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.nothing);
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(getString(R.string.nothing));
        valueOf.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#527acc"));
                textPaint.setUnderlineText(false);
            }

            public void onClick(View view) {
                Intent intent = new Intent(ChooseCameraActivity.this, CameraBarcodeHelpActivity.class);
                intent.putExtra("model", ChooseCameraActivity.this.e);
                intent.putExtra("url", "/faq/detail.html?id=1016");
                ChooseCameraActivity.this.startActivityForResult(intent, 1);
            }
        }, 0, valueOf.length(), 33);
        textView2.setHighlightColor(0);
        textView2.setText(valueOf);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        this.f = (ListView) findViewById(R.id.listview);
        this.f.addHeaderView(LayoutInflater.from(this).inflate(R.layout.choose_camera_listview_header, (ViewGroup) null));
        this.f.setHeaderDividersEnabled(false);
        this.f.setDivider((Drawable) null);
        this.f.setDividerHeight(0);
        this.g = new ChooseCameraAdapter(this);
        this.f.setAdapter(this.g);
        this.j = new HashMap<>();
        this.k = new HashMap<>();
        this.l = new HashMap<>();
        a();
        e();
    }

    /* access modifiers changed from: private */
    public void a() {
        BluetoothLog.c(String.format("scanBleForCameraAddress", new Object[0]));
        if (!BluetoothUtils.b()) {
            BluetoothUtils.a(this, R.string.open_bluetooth_tips, new BleResponse() {
                public void a(int i, Object obj) {
                    if (i == 0) {
                        ChooseCameraActivity.this.b();
                    } else {
                        ChooseCameraActivity.this.finish();
                    }
                }
            });
        } else {
            b();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        h();
        BLEDeviceManager.a(new SearchRequest.Builder().a(3000, 10).a(), (MiioBtSearchResponse) new MiioBtSearchResponse() {
            public void a() {
                ChooseCameraActivity.this.h.setEnabled(false);
                ChooseCameraActivity.this.i.setVisibility(0);
                ChooseCameraActivity.this.h.setText("");
            }

            public void a(BleDevice bleDevice) {
                ChooseCameraActivity.this.a(bleDevice);
            }

            public void b() {
                ChooseCameraActivity.this.c();
            }

            public void c() {
                ChooseCameraActivity.this.c();
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        this.h.setEnabled(true);
        this.h.setText(R.string.refresh);
        this.i.setVisibility(8);
        i();
    }

    private void d() {
        this.l.clear();
        for (String next : this.j.keySet()) {
            if (this.k.containsKey(next)) {
                this.l.put(next, new CameraItem(this.j.get(next), this.k.get(next)));
            }
        }
        if (!this.l.isEmpty()) {
            this.f.setVisibility(0);
        } else {
            this.f.setVisibility(8);
        }
        if (!this.mHandler.hasMessages(1)) {
            this.mHandler.sendEmptyMessageDelayed(1, 2000);
        }
    }

    /* access modifiers changed from: private */
    public void a(BleDevice bleDevice) {
        MiotBleAdvPacket d2 = bleDevice.d();
        if (d2 != null && !TextUtils.isEmpty(d2.f) && this.e.equals(bleDevice.model)) {
            BluetoothLog.e(String.format(">>> ble -> %s", new Object[]{bleDevice}));
            this.k.put(d2.f, bleDevice);
        }
        d();
    }

    /* access modifiers changed from: private */
    public void e() {
        LinkedList<ScanResult> linkedList = new LinkedList<>();
        linkedList.addAll(WifiDeviceFinder.j);
        if (!ListUtils.a(linkedList)) {
            for (ScanResult scanResult : linkedList) {
                if (DeviceFactory.a(scanResult).equalsIgnoreCase(this.e)) {
                    BluetoothLog.e(String.format(">>> wifi -> %s", new Object[]{scanResult}));
                    this.j.put(DeviceFactory.h(scanResult), scanResult);
                }
            }
        }
        d();
    }

    private class CameraReceiver extends BroadcastReceiver {
        private CameraReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if ("wifi_scan_result_broadcast".equals(intent.getAction())) {
                ChooseCameraActivity.this.e();
            }
        }
    }

    private void f() {
        if (this.m == null) {
            this.m = new CameraReceiver();
            registerReceiver(this.m, new IntentFilter("wifi_scan_result_broadcast"));
        }
    }

    private void g() {
        if (this.m != null) {
            unregisterReceiver(this.m);
            this.m = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        i();
    }

    private void h() {
        f();
        CoreApi.a().a(2000);
        WifiScanManager.a().b();
    }

    private void i() {
        g();
        BLEDeviceManager.f();
        CoreApi.a().L();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onCameraChoose(CameraItem cameraItem) {
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.h, cameraItem.f22252a);
        setResult(-1);
        finish();
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            this.g.a(this.l.values());
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 1 && i3 == -1) {
            setResult(-1, intent);
            finish();
        }
    }
}
