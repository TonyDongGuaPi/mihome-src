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

public class CameraApChooseConnection extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22228a = "****";
    private String b;
    private BroadcastReceiver c = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Parcelable parcelableExtra;
            if (intent.getAction().equals("android.net.wifi.STATE_CHANGE") && (parcelableExtra = intent.getParcelableExtra(StatType.NETWORKINFO)) != null) {
                Message obtainMessage = CameraApChooseConnection.this.mHandler.obtainMessage();
                obtainMessage.what = 101;
                obtainMessage.obj = (NetworkInfo) parcelableExtra;
                CameraApChooseConnection.this.mHandler.sendMessage(obtainMessage);
            }
        }
    };
    @BindView(2131430969)
    ImageView mIvReturn;
    @BindView(2131431178)
    Button mSettingWifiBtn;
    @BindView(2131427687)
    TextView mTvAp;
    @BindView(2131430975)
    TextView mTvTitle;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.camera_ap_choose);
        ButterKnife.bind((Activity) this);
        this.b = getIntent().getStringExtra("model");
        this.mIvReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraApChooseConnection.this.finish();
            }
        });
        this.mTvTitle.setText(R.string.camera_connect_guide);
        this.mSettingWifiBtn.setVisibility(0);
        this.mSettingWifiBtn.setText(R.string.smart_config_goto_setting);
        ((WifiManager) getSystemService("wifi")).getConnectionInfo();
        this.mSettingWifiBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                try {
                    CameraApChooseConnection.this.startActivity(intent);
                } catch (Exception unused) {
                }
            }
        });
        registerReceiver(this.c, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        if ("isa.camera.isc5".equals(this.b)) {
            this.mTvAp.setText("isa-camera-isc5_miap****");
        } else if ("mijia.camera.v1".equals(this.b)) {
            this.mTvAp.setText("mijia-camera-v1_mibt****");
        }
    }

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
            if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID()) && !connectionInfo.getSSID().contains("<unknown ssid>") && detailedState == NetworkInfo.DetailedState.CONNECTED && networkInfo.isConnected()) {
                if ("isa.camera.isc5".equals(this.b) && connectionInfo.getSSID().contains("isa-camera-isc5")) {
                    finish();
                }
                if ("mijia.camera.v1".equals(this.b) && connectionInfo.getSSID().contains("mijia-camera-v1")) {
                    finish();
                }
            }
        }
    }
}
