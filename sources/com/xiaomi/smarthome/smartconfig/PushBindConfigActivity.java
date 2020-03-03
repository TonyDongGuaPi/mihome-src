package com.xiaomi.smarthome.smartconfig;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.LocalRouterDeviceInfo;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.widget.ProgressView;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class PushBindConfigActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22303a = 100;
    /* access modifiers changed from: private */
    public PushBindEntity b;
    /* access modifiers changed from: private */
    public TextView c;
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (PushBindConfigActivity.this.c != null) {
                PushBindConfigActivity.this.a();
            }
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = (PushBindEntity) getIntent().getParcelableExtra(DevicePushBindManager.EXTRA_BINDWIFI);
        if (this.b == null) {
            LogUtilGrey.a("PushBindConfigActivity", "onCreate data is null EXTRA_BINDWIFI");
            finish();
            return;
        }
        if (DeviceUtils.b(this.b.f22312a.o())) {
            setContentView(R.layout.activity_pushhint);
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.push_bindhint_title);
            final ProgressView progressView = (ProgressView) findViewById(R.id.image);
            final View findViewById = findViewById(R.id.tv_title);
            final View findViewById2 = findViewById(R.id.tv_content);
            int a2 = DisplayUtils.a(1.5f);
            progressView.setWidth(a2, a2);
            progressView.setColor(0, -14105143, 0);
            progressView.setProgress(-1.0f);
            findViewById.setVisibility(4);
            findViewById2.setVisibility(4);
            DeviceApi.getInstance().bindPushApDevice(this, this.b.f22312a.o(), this.b.b, this.b.c, this.b.d, this.b.e, this.b.f, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    DevicePushBindManager.instance.bind(PushBindConfigActivity.this.b.d);
                    progressView.setColor(-14105143, -14105143, 0);
                    progressView.setProgress(100.0f);
                    findViewById.setVisibility(0);
                    findViewById2.setVisibility(0);
                }

                public void onFailure(Error error) {
                    LogUtilGrey.a("PushBindConfigActivity", PushBindConfigActivity.this.b + "  bindPushApDevice error:" + error);
                    PushBindConfigActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            if (PushBindConfigActivity.this.isValid()) {
                                DeviceApi.getInstance().bindPushApDevice(PushBindConfigActivity.this, PushBindConfigActivity.this.b.f22312a.o(), PushBindConfigActivity.this.b.b, PushBindConfigActivity.this.b.c, PushBindConfigActivity.this.b.d, PushBindConfigActivity.this.b.e, PushBindConfigActivity.this.b.f, this);
                            }
                        }
                    }, 1000);
                }
            });
        } else {
            setContentView(R.layout.activity_pushbind);
            registerReceiver(this.d, new IntentFilter("android.net.wifi.STATE_CHANGE"));
            this.c = (TextView) findViewById(R.id.tvHint);
            a();
            ((TextView) findViewById(R.id.tv_name)).setText(this.b.f22312a.p());
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.push_confirmbind_title);
            findViewById(R.id.module_a_3_right_tv_text).setVisibility(8);
            findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!PushBindConfigActivity.this.b.f()) {
                        Intent intent = new Intent(PushBindConfigActivity.this.getContext(), SmartConfigMainActivity.class);
                        intent.putExtra("strategy_id", 16);
                        intent.putExtra(SmartConfigDataProvider.r, PushBindConfigActivity.this.b);
                        PushBindConfigActivity.this.startActivityForResult(intent, 100);
                    } else if (LocalRouterDeviceInfo.a().a(PushBindConfigActivity.this.b.e, PushBindConfigActivity.this.b.f)) {
                        final XQProgressDialog xQProgressDialog = new XQProgressDialog(PushBindConfigActivity.this);
                        xQProgressDialog.setCancelable(true);
                        xQProgressDialog.setMessage(PushBindConfigActivity.this.getResources().getString(R.string.loading));
                        xQProgressDialog.show();
                        LocalRouterDeviceInfo.a().a((AsyncResponseCallback<RouterRemoteApi.WifiDetail>) new AsyncResponseCallback<RouterRemoteApi.WifiDetail>() {
                            public void a(RouterRemoteApi.WifiDetail wifiDetail) {
                                xQProgressDialog.dismiss();
                                Iterator<RouterRemoteApi.WifiInfo> it = wifiDetail.f16425a.iterator();
                                while (it.hasNext()) {
                                    RouterRemoteApi.WifiInfo next = it.next();
                                    if (WifiSettingUtils.a(next.c, PushBindConfigActivity.this.b.e)) {
                                        Intent intent = new Intent(PushBindConfigActivity.this.getContext(), SmartConfigMainActivity.class);
                                        intent.putExtra("strategy_id", 9);
                                        intent.putExtra("model", PushBindConfigActivity.this.b.f22312a.o());
                                        intent.putExtra("bssid", PushBindConfigActivity.this.b.f);
                                        intent.putExtra(SmartConfigDataProvider.b, next);
                                        intent.putExtra("use_password_page", false);
                                        intent.putExtra("use_reset_page", false);
                                        intent.putExtra(SmartConfigDataProvider.r, PushBindConfigActivity.this.b);
                                        PushBindConfigActivity.this.startActivityForResult(intent, 100);
                                        return;
                                    }
                                }
                                ToastUtil.a((int) R.string.push_getwifiinfo_error);
                            }

                            public void a(int i) {
                                xQProgressDialog.dismiss();
                                ToastUtil.a((int) R.string.push_getwifiinfo_error);
                            }

                            public void a(int i, Object obj) {
                                xQProgressDialog.dismiss();
                                ToastUtil.a((int) R.string.push_getwifiinfo_error);
                            }
                        });
                    } else {
                        ToastUtil.a((CharSequence) PushBindConfigActivity.this.getString(R.string.push_changewifi_hint, new Object[]{PushBindConfigActivity.this.b.e}));
                    }
                }
            });
            DeviceImageApi.a(this.b.f22312a.o(), new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
                /* renamed from: a */
                public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                    DeviceFactory.a((SimpleDraweeView) PushBindConfigActivity.this.findViewById(R.id.icon_static), deviceImageEntity.f21157a, 0);
                }

                public void onFailure(Error error) {
                    DeviceFactory.a((SimpleDraweeView) PushBindConfigActivity.this.findViewById(R.id.icon_static), (String) null, 0);
                }
            });
            View findViewById3 = findViewById(R.id.checkWifi);
            try {
                findViewById3.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (!PushBindConfigActivity.this.b.f22312a.c().a()) {
                            Toast.makeText(PushBindConfigActivity.this.getContext(), PushBindConfigActivity.this.getContext().getString(R.string.device_not_support_now), 0).show();
                            return;
                        }
                        Intent intent = new Intent(PushBindConfigActivity.this.getContext(), SmartConfigMainActivity.class);
                        if (PushBindConfigActivity.this.b.f22312a.c().e() == 4) {
                            List<Integer> i = PushBindConfigActivity.this.b.f22312a.c().i();
                            if (i != null && i.contains(1) && BluetoothUtils.a()) {
                                intent.putExtra("strategy_id", 4);
                            } else if (i == null || !i.contains(0)) {
                                intent.putExtra("strategy_id", 9);
                                intent.putExtra("use_reset_page", false);
                            } else {
                                intent.putExtra("strategy_id", 2);
                            }
                        } else if (DeviceFactory.b(PushBindConfigActivity.this.b.c, PushBindConfigActivity.this.b.d) != DeviceFactory.AP_TYPE.AP_MIBAP || !BluetoothUtils.a()) {
                            intent.putExtra("strategy_id", 2);
                        } else {
                            intent.putExtra("strategy_id", 4);
                        }
                        intent.putExtra(SmartConfigDataProvider.r, PushBindConfigActivity.this.b);
                        intent.putExtra(DeviceTagInterface.e, PushBindConfigActivity.this.b.c);
                        intent.putExtra("model", PushBindConfigActivity.this.b.f22312a.o());
                        PushBindConfigActivity.this.startActivityForResult(intent, 100);
                    }
                });
            } catch (Exception e) {
                findViewById3.setVisibility(8);
                Log.e(DevicePushBindManager.TAG, "PushBindConfigActivity", e);
            }
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PushBindConfigActivity.this.onBackPressed();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        if (!this.b.f() || LocalRouterDeviceInfo.a().a(this.b.e, this.b.f)) {
            this.c.setText(getString(R.string.push_confirmbind_hint, new Object[]{this.b.e}));
            LocalRouterDeviceInfo.a().a((AsyncResponseCallback<RouterRemoteApi.WifiDetail>) null);
            return;
        }
        this.c.setText(getString(R.string.push_confirmbind_hint, new Object[]{this.b.e}));
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        boolean booleanExtra = intent != null ? intent.getBooleanExtra(Constants.Event.FINISH, true) : true;
        if (i2 == 0) {
            booleanExtra = false;
        }
        if (booleanExtra) {
            Intent intent2 = new Intent();
            intent2.putExtra(Constants.Event.FINISH, true);
            if (intent != null) {
                intent2.putExtras(intent);
            }
            setResult(-1, intent2);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.d);
        } catch (Exception unused) {
        }
    }
}
