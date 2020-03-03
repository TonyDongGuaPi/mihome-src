package com.xiaomi.smarthome.wificonfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ApDeviceManager;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.choosedevice.ScanDeviceProgressBar;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.stage.ConfigStage;
import com.xiaomi.smarthome.smartconfig.step.ApConnectFailedStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RescanWifiActivity extends BaseActivity implements ScanDeviceProgressBar.OnTimeOutListener {
    public static final String SELECT_PASSWORD = "select_password";
    public static final String SELECT_SCAN_RESULT = "select_scan_result";
    public static final String SELECT_SSID = "select_ssid";

    /* renamed from: a  reason: collision with root package name */
    private ScanResult f22909a;
    private String b;
    private ScanDeviceProgressBar c;
    private View d;
    private ApConnectFailedStep e;
    private List<String> f = new ArrayList();
    private SmartConfigStep.StepListener g = new SmartConfigStep.StepListener() {
        public void a(SmartConfigStep.Step step, SmartConfigStep.Step step2) {
        }

        public void b(SmartConfigStep.Step step, SmartConfigStep.Step step2) {
        }

        public void a() {
            RescanWifiActivity.this.onBackPressed();
        }

        public Handler b() {
            return RescanWifiActivity.this.mHandler;
        }

        public void a(boolean z) {
            RescanWifiActivity.this.onBackPressed();
        }
    };
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("wifi_scan_result_broadcast".equals(intent.getAction())) {
                boolean unused = RescanWifiActivity.this.c();
            } else {
                boolean unused2 = RescanWifiActivity.this.d();
            }
        }
    };

    public void onTimeOut() {
        if (isValid() && this.e == null) {
            STAT.c.f(this.b);
            new MLAlertDialog.Builder(this).b((int) R.string.cannot_scan_find_device).a((int) R.string.restart_scan, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    RescanWifiActivity.this.b(dialogInterface, i);
                }
            }).b((int) R.string.how_to_reset, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    RescanWifiActivity.this.a(dialogInterface, i);
                }
            }).a(false).b().show();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i) {
        STAT.d.aP(this.b);
        this.c.reset();
        this.c.start();
        this.d.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i) {
        STAT.d.aO(this.b);
        finish();
    }

    private void a() {
        this.c.stop();
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.rescan_wifi_layout);
        if (!(getIntent() == null || getIntent().getParcelableExtra("scanResult") == null)) {
            this.f22909a = (ScanResult) getIntent().getParcelableExtra("scanResult");
        }
        if (getIntent() != null) {
            this.b = getIntent().getStringExtra("model");
            b();
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RescanWifiActivity.this.onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.scan_device));
        this.c = (ScanDeviceProgressBar) findViewById(R.id.progress_bar);
        this.d = findViewById(R.id.connect_failed_tips);
        this.c.registerTimeOutListener(this);
        this.d.setOnClickListener(new View.OnClickListener((ViewGroup) findViewById(R.id.top_view)) {
            private final /* synthetic */ ViewGroup f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                RescanWifiActivity.this.a(this.f$1, view);
            }
        });
        STAT.c.e(this.b);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ViewGroup viewGroup, View view) {
        STAT.d.aN(this.b);
        if (this.e == null) {
            if (this.f22909a == null) {
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.h);
            } else {
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.h, this.f22909a);
            }
            SmartConfigDataProvider.a().b("wifi_ssid", this.f22909a != null ? this.f22909a.SSID : DeviceFactory.a(this.b, "xxxx"));
            this.e = new ApConnectFailedStep();
            this.e.a(this.g);
            this.e.b((Context) this);
            viewGroup.addView(this.e.z());
            this.c.stop();
            CoreApi.a().L();
        }
    }

    private void b() {
        if (!TextUtils.isEmpty(this.b)) {
            List<PluginRecord> O = CoreApi.a().O();
            PluginRecord d2 = CoreApi.a().d(this.b);
            for (PluginRecord next : O) {
                if (next.c().N() != 0 && next.c().N() == d2.c().d()) {
                    this.f.add(next.o());
                }
            }
            this.f.add(this.b);
        }
    }

    public void onBackPressed() {
        View z;
        if (this.e == null || (z = this.e.z()) == null) {
            Intent intent = new Intent();
            intent.putExtra(Constants.Event.FINISH, false);
            setResult(-1, intent);
            finish();
            return;
        }
        ViewGroup viewGroup = (ViewGroup) z.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(z);
        }
        this.e.a();
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.h);
        SmartConfigDataProvider.a().b("wifi_ssid");
        this.e = null;
        this.c.reset();
        this.c.start();
        WifiScanManager.a().b();
    }

    public void onResume() {
        String str;
        WifiInfo connectionInfo;
        List<ScanResult> scanResults;
        super.onResume();
        if (this.e != null) {
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService("wifi");
            if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null || (scanResults = wifiManager.getScanResults()) == null) {
                StringBuilder sb = new StringBuilder();
                sb.append("RescanWifiActivity ");
                if (wifiManager == null) {
                    str = "manager is null";
                } else {
                    str = wifiManager.getConnectionInfo() + " " + wifiManager.getScanResults();
                }
                sb.append(str);
                LogUtilGrey.a("scanResult", sb.toString(), true);
            } else {
                Iterator<ScanResult> it = scanResults.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ScanResult next = it.next();
                    if (next != null) {
                        LogUtilGrey.a("scanResult", "RescanWifiActivity " + connectionInfo.getBSSID() + " " + next.BSSID, true);
                        if (WifiSettingUtils.a(connectionInfo.getBSSID(), next.BSSID)) {
                            if (DeviceFactory.f(DeviceFactory.a(next), this.b) || (this.f22909a != null && DeviceFactory.h(this.f22909a).equals(DeviceFactory.h(next)))) {
                                gotoWifiSettingPage(next);
                            }
                        }
                    }
                }
            }
        } else if (!c() && !d()) {
            IntentFilter intentFilter = new IntentFilter("wifi_scan_result_broadcast");
            intentFilter.addAction(ApDeviceManager.d);
            LocalBroadcastManager.getInstance(this).registerReceiver(this.mBroadcastReceiver, intentFilter);
            CoreApi.a().a(2000);
            WifiScanManager.a().b();
            this.c.reset();
            this.c.setTime(30000);
            this.c.start();
        }
        STAT.c.a(0, this.b);
    }

    public void onPause() {
        super.onPause();
        this.c.stop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mBroadcastReceiver);
        CoreApi.a().a(10000);
        CoreApi.a().L();
        STAT.c.a(this.mEnterTime, this.b);
    }

    /* access modifiers changed from: private */
    public boolean c() {
        if (WifiDeviceFinder.j == null) {
            return false;
        }
        Iterator<ScanResult> it = WifiDeviceFinder.j.iterator();
        while (it.hasNext()) {
            ScanResult next = it.next();
            if (this.f22909a == null || DeviceFactory.d(next) != DeviceFactory.AP_TYPE.AP_MIAP || !DeviceFactory.h(this.f22909a).equals(DeviceFactory.h(next))) {
                Iterator<String> it2 = this.f.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        String next2 = it2.next();
                        if (next2 != null && DeviceFactory.f(DeviceFactory.a(next), next2)) {
                            gotoWifiSettingPage(next);
                            a();
                            return true;
                        }
                    }
                }
            } else {
                this.f22909a = next;
                gotoWifiSettingPage(next);
                a();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public boolean d() {
        if (ApDeviceManager.a().c() == null || ApDeviceManager.a().c().size() <= 0) {
            return false;
        }
        for (ScanResult next : ApDeviceManager.a().c()) {
            if (this.f22909a == null || DeviceFactory.b(next) != DeviceFactory.AP_TYPE.AP_MIDEVICE || !DeviceFactory.h(this.f22909a).equals(DeviceFactory.h(next))) {
                Iterator<String> it = this.f.iterator();
                while (true) {
                    if (it.hasNext()) {
                        String next2 = it.next();
                        if (next2 != null && DeviceFactory.f(DeviceFactory.c(next), next2)) {
                            gotoWifiSettingPage(next);
                            a();
                            return true;
                        }
                    }
                }
            } else {
                this.f22909a = next;
                gotoWifiSettingPage(next);
                a();
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void gotoWifiSettingPage(ScanResult scanResult) {
        Intent a2 = ConfigStage.a(this, scanResult, DeviceFactory.a(scanResult), (String) null, (String) null);
        if (a2 != null) {
            a2.putExtra(SELECT_SCAN_RESULT, getIntent().getParcelableExtra(SELECT_SCAN_RESULT));
            a2.putExtra(SELECT_SSID, getIntent().getStringExtra(SELECT_SSID));
            a2.putExtra(SELECT_PASSWORD, getIntent().getStringExtra(SELECT_PASSWORD));
            if (getIntent() != null) {
                a2.putExtra(SmartConfigDataProvider.N, getIntent().getBooleanExtra(SmartConfigDataProvider.N, false));
            }
            if (getIntent() != null && getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                a2.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
            }
            startActivityForResult(a2, 100);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null ? intent.getBooleanExtra(Constants.Event.FINISH, true) : false) {
            Intent intent2 = new Intent();
            intent2.putExtra(Constants.Event.FINISH, true);
            setResult(-1, intent2);
        }
        finish();
    }
}
