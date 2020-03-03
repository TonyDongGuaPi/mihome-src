package com.xiaomi.smarthome.smartconfig;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.youpin.hawkeye.entity.StatType;

public class XiaofangChooseConnection extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private BroadcastReceiver f22332a = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExtra;
            if (intent.getAction().equals("android.net.wifi.STATE_CHANGE") && (parcelableExtra = intent.getParcelableExtra(StatType.NETWORKINFO)) != null) {
                Message obtainMessage = XiaofangChooseConnection.this.mHandler.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = (NetworkInfo) parcelableExtra;
                XiaofangChooseConnection.this.mHandler.sendMessage(obtainMessage);
            }
        }
    };
    @BindView(2131427881)
    View mBottomContainer;
    @BindView(2131432516)
    ImageView mIcon;
    @BindView(2131432518)
    TextView mInfo;
    @BindView(2131432517)
    TextView mInfoSubTitle;
    @BindView(2131430408)
    TextView mLeftBtn;
    @BindView(2131427825)
    View mMainIconContainer;
    @BindView(2131430790)
    TextView mManualIcon;
    @BindView(2131430792)
    TextView mManualText;
    @BindView(2131427824)
    View mManualView;
    @BindView(2131431897)
    TextView mRightBtn;
    @BindView(2131431178)
    Button mSettingWifiBtn;

    public void handleMessage(Message message) {
        if (message.what == 101) {
            WifiManager wifiManager = (WifiManager) getSystemService("wifi");
            NetworkInfo networkInfo = (NetworkInfo) message.obj;
            NetworkInfo.DetailedState detailedState = networkInfo.getDetailedState();
            StringBuilder sb = new StringBuilder();
            sb.append(detailedState.toString());
            sb.append(", ");
            sb.append(networkInfo.getReason() != null ? networkInfo.getReason() : "");
            sb.append(", ");
            sb.append(networkInfo.getExtraInfo() != null ? networkInfo.getExtraInfo() : "");
            sb.append(", ");
            sb.append(networkInfo.getReason() != null ? networkInfo.getReason() : "");
            Log.e("WifiState", sb.toString());
            WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID()) && !connectionInfo.getSSID().contains("<unknown ssid>") && detailedState == NetworkInfo.DetailedState.CONNECTED && networkInfo.isConnected() && connectionInfo.getSSID().contains("isa-camera-isc5")) {
                finish();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smart_config_connect_ap_failed_ui);
        ButterKnife.bind((Activity) this);
        this.mMainIconContainer.setVisibility(8);
        this.mBottomContainer.setVisibility(8);
        this.mManualView.setVisibility(0);
        this.mSettingWifiBtn.setVisibility(0);
        this.mSettingWifiBtn.setText(R.string.smart_config_set_wifi_btn);
        String format = String.format(getText(R.string.smart_config_manual_text).toString(), new Object[]{"isa-camera-isc5_miap****"});
        this.mManualIcon.setText("isa-camera-isc5_miap****");
        this.mManualText.setText(format);
        this.mSettingWifiBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                XiaofangChooseConnection.this.startActivity(intent);
            }
        });
        registerReceiver(this.f22332a, new IntentFilter("android.net.wifi.STATE_CHANGE"));
    }
}
