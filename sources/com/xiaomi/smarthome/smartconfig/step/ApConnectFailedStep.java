package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;

public class ApConnectFailedStep extends SmartConfigStep {
    @BindView(2131430790)
    TextView mManualIcon;
    @BindView(2131430792)
    TextView mManualText;
    @BindView(2131427824)
    View mManualView;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131431178)
    Button mSettingWifiBtn;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitlebarTv;

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return SmartConfigStep.Step.STEP_CONNECT_AP_ERROR;
    }

    public boolean a() {
        d_(false);
        return true;
    }

    public void a(Message message) {
        if (this.af != null && message.what == 101) {
            g();
        }
    }

    private void g() {
        ScanResult l;
        WifiManager wifiManager = (WifiManager) this.af.getApplicationContext().getSystemService("wifi");
        if (wifiManager != null) {
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            Log.e("ApConnectFailedStep", "WifiState" + connectionInfo);
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID()) && !connectionInfo.getSSID().contains("<unknown ssid>") && connectionInfo.getSupplicantState() == SupplicantState.COMPLETED && (l = l()) != null && WifiSettingUtils.a(connectionInfo.getSSID(), l.SSID)) {
                D();
            }
        }
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_connect_ap_failed_ui);
        y();
        STAT.d.F(this.aj);
        TitleBarUtil.a(this.mTitleBar);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ApConnectFailedStep.this.a();
            }
        });
        this.mTitlebarTv.setText(this.af.getString(R.string.kuailian_connect_device));
        b();
    }

    /* access modifiers changed from: package-private */
    public void b() {
        if (this.af != null) {
            this.mManualView.setVisibility(0);
            this.mSettingWifiBtn.setVisibility(0);
            this.mSettingWifiBtn.setText(R.string.smart_config_set_wifi_btn);
            ScanResult scanResult = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
            String str = (String) SmartConfigDataProvider.a().a("wifi_ssid");
            if (scanResult != null) {
                str = scanResult.SSID;
                MobclickAgent.a(this.af, DeviceFactory.a(scanResult), "ap_connect_device_manual");
            }
            if (str != null) {
                String format = String.format(this.af.getText(R.string.smart_config_manual_text).toString(), new Object[]{str});
                this.mManualIcon.setText(str);
                this.mManualText.setText(format);
            }
            this.mSettingWifiBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MobclickAgent.a(ApConnectFailedStep.this.af, MiStatType.CLICK.getValue(), "connect_ap_manual_connect");
                    StatHelper.d("connect_ap_manual_connect");
                    Intent intent = new Intent();
                    STAT.d.z(ApConnectFailedStep.this.aj);
                    intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                    try {
                        ((Activity) ApConnectFailedStep.this.af).startActivityForResult(intent, 102);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void a(int i, int i2, Intent intent) {
        super.a(i, i2, intent);
        if (i == 102) {
            g();
        }
    }
}
