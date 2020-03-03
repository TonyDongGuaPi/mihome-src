package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.KeyValuePair;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.plugin.runtime.util.OfflineViewDelegate;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import java.text.NumberFormat;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceNetworkInfoActivity extends BaseActivity {
    public static final String ARGS_KEY_DID = "did";

    /* renamed from: a  reason: collision with root package name */
    private static final int f16829a = -30;
    private static final int b = -85;
    protected Context mContext;
    protected Device mDevice;
    boolean mIsDestroyed = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int i = 0;
        this.mIsDestroyed = false;
        overridePendingTransition(R.anim.activity_slide_in_right, R.anim.activity_slide_out_left);
        this.mContext = this;
        TitleBarUtil.a((Activity) this);
        setContentView(R.layout.device_network_activity);
        String stringExtra = getIntent().getStringExtra("did");
        this.mDevice = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.mDevice == null) {
            this.mDevice = SmartHomeDeviceManager.a().l(stringExtra);
            if (this.mDevice == null) {
                finish();
                return;
            }
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceNetworkInfoActivity.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            textView.setText(R.string.device_more_activity_network_info);
        }
        TextView textView2 = (TextView) findViewById(R.id.wifi_name);
        if (textView2 != null) {
            textView2.setText(this.mDevice.ssid);
        }
        TextView textView3 = (TextView) findViewById(R.id.wifi_signal);
        if (this.mDevice.rssi >= -30) {
            i = 100;
        } else if (this.mDevice.rssi > b) {
            i = ((Math.abs(b) + this.mDevice.rssi) * 100) / 55;
        }
        if (textView3 != null) {
            textView3.setText(i + Operators.MOD);
        }
        TextView textView4 = (TextView) findViewById(R.id.rssi);
        if (textView4 != null) {
            textView4.setText("" + this.mDevice.rssi);
        }
        TextView textView5 = (TextView) findViewById(R.id.network_type);
        if (textView5 != null) {
            textView5.setText(R.string.network_info_loading);
        }
        if (this.mDevice instanceof CameraDevice) {
            XmPluginHostApi.instance().callHttpApiV13("yunyi.camera.v1", ConnectionHelper.HTTP_PREFIX + this.mDevice.ip + "/cgi-bin/isxiaoyi.cgi", "GET", (List<KeyValuePair>) null, new Callback<String>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    TextView textView;
                    if (DeviceNetworkInfoActivity.this.isValid() && (textView = (TextView) DeviceNetworkInfoActivity.this.findViewById(R.id.network_type)) != null) {
                        textView.setText(R.string.network_info_intranet);
                    }
                }

                public void onFailure(int i, String str) {
                    TextView textView;
                    if (DeviceNetworkInfoActivity.this.isValid() && (textView = (TextView) DeviceNetworkInfoActivity.this.findViewById(R.id.network_type)) != null) {
                        textView.setText(R.string.network_info_extranet);
                    }
                }
            }, new Parser<String>() {
                /* renamed from: a */
                public String parse(String str) throws JSONException {
                    return str;
                }
            });
        } else {
            XmPluginHostApi.instance().localPing(this.mDevice.did, new Callback<Void>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (DeviceNetworkInfoActivity.this.isValid()) {
                        DeviceNetworkInfoActivity.this.mDevice.location = Device.Location.LOCAL;
                        TextView textView = (TextView) DeviceNetworkInfoActivity.this.findViewById(R.id.network_type);
                        if (textView != null) {
                            textView.setText(R.string.network_info_intranet);
                        }
                    }
                }

                public void onFailure(int i, String str) {
                    TextView textView;
                    if (DeviceNetworkInfoActivity.this.isValid() && (textView = (TextView) DeviceNetworkInfoActivity.this.findViewById(R.id.network_type)) != null) {
                        textView.setText(R.string.network_info_extranet);
                    }
                }
            });
        }
        TextView textView6 = (TextView) findViewById(R.id.network_ip);
        if (textView6 != null) {
            textView6.setText(this.mDevice.ip);
        }
        TextView textView7 = (TextView) findViewById(R.id.network_mac);
        if (textView7 != null) {
            textView7.setText(this.mDevice.mac);
        }
        final ImageView imageView = (ImageView) findViewById(R.id.iv_rssi_signal);
        final TextView textView8 = (TextView) findViewById(R.id.tv_rssi_details);
        final TextView textView9 = (TextView) findViewById(R.id.packet_loss_rate);
        OfflineViewDelegate.checkDeviceRssi(this, new AsyncCallback<Pair<Integer, JSONObject>, Error>() {
            /* renamed from: a */
            public void onSuccess(Pair<Integer, JSONObject> pair) {
                if (pair != null && DeviceNetworkInfoActivity.this.isValid() && pair.first != null && pair.second != null) {
                    imageView.setBackgroundResource(((Integer) pair.first).intValue() == 0 ? R.drawable.weak_rssi_wifi_big_enable : R.drawable.weak_rssi_wifi_big_disable);
                    String optString = ((JSONObject) pair.second).optString("note");
                    if (!TextUtils.isEmpty(optString)) {
                        textView8.setText(optString);
                    }
                    double optDouble = ((JSONObject) pair.second).optDouble("lost_rate");
                    NumberFormat percentInstance = NumberFormat.getPercentInstance();
                    percentInstance.setMaximumFractionDigits(2);
                    textView9.setText(percentInstance.format(optDouble));
                }
            }

            public void onFailure(Error error) {
                if (DeviceNetworkInfoActivity.this.isValid()) {
                }
            }
        }, this.mDevice.did, 1);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mIsDestroyed = true;
    }
}
