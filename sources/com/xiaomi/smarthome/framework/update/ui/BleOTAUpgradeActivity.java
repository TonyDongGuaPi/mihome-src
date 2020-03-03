package com.xiaomi.smarthome.framework.update.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.update.BleUpgradePresenter;
import com.xiaomi.smarthome.framework.update.blemesh.IUpgradeHost;
import com.xiaomi.smarthome.framework.update.blemesh.IUpgradePresenter;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;

public class BleOTAUpgradeActivity extends BaseActivity implements IUpgradeHost {
    public static final String ARG_DID = "arg_did";

    /* renamed from: a  reason: collision with root package name */
    private static final String f17785a = "BleOTAUpgradeActivity";
    private static final String b = "arg_auth_type";
    private static final String c = "arg_test_dfu_url";
    private static final String d = "arg_test_version_name";
    private static final String e = "arg_test_file_md5";
    /* access modifiers changed from: private */
    public ViewSwitcher f;
    /* access modifiers changed from: private */
    public View g;
    /* access modifiers changed from: private */
    public PieProgressBar h;
    /* access modifiers changed from: private */
    public TextView i;
    /* access modifiers changed from: private */
    public TextView j;
    /* access modifiers changed from: private */
    public TextView k;
    /* access modifiers changed from: private */
    public TextView l;
    /* access modifiers changed from: private */
    public Button m;
    private String n;
    private Device o;
    /* access modifiers changed from: private */
    public IUpgradePresenter p;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public TextView r;

    public void onCallback(int i2) {
    }

    public static void invokeActivity(Context context, int i2, String str, String str2, String str3, String str4) {
        Intent intent = new Intent(context, BleOTAUpgradeActivity.class);
        intent.putExtra(b, i2);
        intent.putExtra(ARG_DID, str2);
        intent.putExtra(c, str);
        intent.putExtra(d, str3);
        intent.putExtra(e, str4);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ble_ota_upgrade_activity);
        b();
        c();
        if (BluetoothUtils.b()) {
            e();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!BluetoothUtils.b()) {
            a();
        }
    }

    private void a() {
        BluetoothUtils.a(this, R.string.open_bluetooth_tips, new BleResponse() {
            public void a(int i, Object obj) {
                if (i == 0) {
                    BleOTAUpgradeActivity.this.e();
                } else {
                    BleOTAUpgradeActivity.this.finish();
                }
            }
        });
    }

    private void b() {
        Intent intent = getIntent();
        this.n = intent.getStringExtra(ARG_DID);
        int intExtra = intent.getIntExtra(b, -1);
        this.q = intent.getStringExtra(c);
        String stringExtra = intent.getStringExtra(e);
        this.o = SmartHomeDeviceManager.a().b(this.n);
        String stringExtra2 = intent.getStringExtra(d);
        if (this.o == null || intExtra < 0) {
            LogUtil.e(f17785a, "onCreate: mDevice is null,mDid=" + this.n + ",or auth type less than 0");
            finish();
            return;
        }
        this.p = new BleUpgradePresenter(this, intExtra, this.o, this.q, stringExtra2, stringExtra);
    }

    private void c() {
        this.f = (ViewSwitcher) findViewById(R.id.vs_root);
        this.g = findViewById(R.id.ll_loading);
        this.h = (PieProgressBar) findViewById(R.id.pb_progress);
        this.i = (TextView) findViewById(R.id.txt_progress);
        this.j = (TextView) findViewById(R.id.txt_update_title);
        this.k = (TextView) findViewById(R.id.txt_update_desc);
        this.l = (TextView) findViewById(R.id.txt_updating_tip);
        this.m = (Button) findViewById(R.id.btn_bottom);
        this.r = (TextView) findViewById(R.id.txt_ota_tips);
        this.h.setPercentView(this.i);
        d();
    }

    private void d() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleOTAUpgradeActivity.this.finish();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (this.o != null) {
            textView.setText(this.o.name);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        showLoading();
        this.p.f();
    }

    public void showLoading() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (BleOTAUpgradeActivity.this.f.getCurrentView() != BleOTAUpgradeActivity.this.g) {
                    BleOTAUpgradeActivity.this.f.showNext();
                }
                LogUtil.a(BleOTAUpgradeActivity.f17785a, "showLoading");
            }
        });
    }

    public void hideLoaing() {
        runOnUiThread(new Runnable() {
            public void run() {
                if (BleOTAUpgradeActivity.this.f.getCurrentView() == BleOTAUpgradeActivity.this.g) {
                    BleOTAUpgradeActivity.this.f.showNext();
                }
                LogUtil.a(BleOTAUpgradeActivity.f17785a, "hide loading");
            }
        });
    }

    public void updateUpgradeInfoView() {
        runOnUiThread(new Runnable() {
            public void run() {
                BleOTAUpgradeActivity.this.j.setVisibility(0);
                String a2 = BleOTAUpgradeActivity.this.p.a();
                String b = BleOTAUpgradeActivity.this.p.b();
                if (TextUtils.isEmpty(BleOTAUpgradeActivity.this.q) && (TextUtils.isEmpty(a2) || TextUtils.isEmpty(b))) {
                    LogUtil.e(BleOTAUpgradeActivity.f17785a, "check update info error,cur:" + a2 + ",latest:" + b);
                    if (GlobalSetting.j) {
                        Context context = BleOTAUpgradeActivity.this.getContext();
                        Toast.makeText(context, "check update info error:" + BleOTAUpgradeActivity.this.p.d(), 0).show();
                    }
                    BleOTAUpgradeActivity.this.showFinishInfo(false);
                } else if (BleOTAUpgradeActivity.this.p.e() || BluetoothHelper.a(b, a2) > 0) {
                    BleOTAUpgradeActivity.this.i.setVisibility(8);
                    BleOTAUpgradeActivity.this.h.setBackgroundResource(R.drawable.update_img_update);
                    BleOTAUpgradeActivity.this.h.setPercent(0.0f);
                    BleOTAUpgradeActivity.this.h.setOnClickListener((View.OnClickListener) null);
                    TextView access$300 = BleOTAUpgradeActivity.this.j;
                    access$300.setText(BleOTAUpgradeActivity.this.getResources().getString(R.string.list_item_curr_version) + " " + a2 + "\n\n" + BleOTAUpgradeActivity.this.getResources().getString(R.string.list_item_latest_version) + " " + b);
                    BleOTAUpgradeActivity.this.k.setText(BleOTAUpgradeActivity.this.p.c());
                    BleOTAUpgradeActivity.this.k.setVisibility(0);
                    BleOTAUpgradeActivity.this.l.setVisibility(8);
                    BleOTAUpgradeActivity.this.m.setVisibility(0);
                    BleOTAUpgradeActivity.this.m.setText(R.string.update_now);
                    BleOTAUpgradeActivity.this.m.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            BleOTAUpgradeActivity.this.m.setVisibility(4);
                            BleOTAUpgradeActivity.this.p.g();
                        }
                    });
                } else {
                    BleOTAUpgradeActivity.this.showFinishInfo(false);
                }
                if (BleOTAUpgradeActivity.this.f.getCurrentView() == BleOTAUpgradeActivity.this.g) {
                    BleOTAUpgradeActivity.this.f.showNext();
                }
            }
        });
    }

    public void showProgress(final int i2) {
        runOnUiThread(new Runnable() {
            public void run() {
                BleOTAUpgradeActivity.this.j.setText(R.string.model_updating);
                BleOTAUpgradeActivity.this.k.setVisibility(8);
                BleOTAUpgradeActivity.this.l.setText(R.string.miio_update_tips);
                BleOTAUpgradeActivity.this.l.setVisibility(0);
                BleOTAUpgradeActivity.this.i.setVisibility(0);
                BleOTAUpgradeActivity.this.h.setVisibility(0);
                BleOTAUpgradeActivity.this.h.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
                BleOTAUpgradeActivity.this.h.setPercent((float) i2);
                BleOTAUpgradeActivity.this.l.setVisibility(0);
            }
        });
    }

    public void showFailedInfo(String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                BleOTAUpgradeActivity.this.k.setVisibility(8);
                BleOTAUpgradeActivity.this.l.setVisibility(8);
                BleOTAUpgradeActivity.this.m.setVisibility(0);
                BleOTAUpgradeActivity.this.m.setText(R.string.retry);
                BleOTAUpgradeActivity.this.m.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        String a2 = BleOTAUpgradeActivity.this.p.a();
                        String b = BleOTAUpgradeActivity.this.p.b();
                        if (TextUtils.isEmpty(a2) || TextUtils.isEmpty(b)) {
                            BleOTAUpgradeActivity.this.e();
                        } else {
                            BleOTAUpgradeActivity.this.p.g();
                        }
                        BleOTAUpgradeActivity.this.m.setVisibility(4);
                    }
                });
                BleOTAUpgradeActivity.this.h.setBackgroundResource(R.drawable.update_img_failed);
                BleOTAUpgradeActivity.this.h.setPercent(0.0f);
                BleOTAUpgradeActivity.this.i.setVisibility(8);
                BleOTAUpgradeActivity.this.h.setVisibility(0);
                BleOTAUpgradeActivity.this.j.setText(R.string.update_failed_retry);
                if (BleOTAUpgradeActivity.this.f.getCurrentView() == BleOTAUpgradeActivity.this.g) {
                    BleOTAUpgradeActivity.this.f.showNext();
                }
            }
        });
    }

    public void showLog(final String str) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (BleOTAUpgradeActivity.this.p.e()) {
                    BleOTAUpgradeActivity.this.r.setVisibility(0);
                    String charSequence = BleOTAUpgradeActivity.this.r.getText().toString();
                    TextView access$1100 = BleOTAUpgradeActivity.this.r;
                    access$1100.setText(charSequence + "\n" + str);
                    return;
                }
                BleOTAUpgradeActivity.this.r.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean f() {
        String a2 = this.p.a();
        return !TextUtils.isEmpty(a2) && a2.equals(this.p.b());
    }

    public void showFinishInfo(final boolean z) {
        runOnUiThread(new Runnable() {
            public void run() {
                if (z) {
                    BleOTAUpgradeActivity.this.h.setBackgroundResource(R.drawable.update_img_success);
                    TextView access$300 = BleOTAUpgradeActivity.this.j;
                    access$300.setText(BleOTAUpgradeActivity.this.getResources().getString(R.string.model_update_success) + "\n\n" + BleOTAUpgradeActivity.this.getResources().getString(R.string.app_curr_version) + " " + BleOTAUpgradeActivity.this.p.a());
                } else if (BleOTAUpgradeActivity.this.f()) {
                    BleOTAUpgradeActivity.this.h.setBackgroundResource(R.drawable.update_img_latest);
                    TextView access$3002 = BleOTAUpgradeActivity.this.j;
                    access$3002.setText(BleOTAUpgradeActivity.this.getResources().getString(R.string.model_latest) + "\n\n" + BleOTAUpgradeActivity.this.getResources().getString(R.string.app_curr_version) + " " + BleOTAUpgradeActivity.this.p.a());
                } else {
                    BleOTAUpgradeActivity.this.h.setBackgroundResource(R.drawable.update_img_failed);
                    BleOTAUpgradeActivity.this.j.setText(BleOTAUpgradeActivity.this.getResources().getString(R.string.update_failure));
                }
                BleOTAUpgradeActivity.this.i.setVisibility(8);
                BleOTAUpgradeActivity.this.h.setPercent(0.0f);
                BleOTAUpgradeActivity.this.h.setOnClickListener((View.OnClickListener) null);
                BleOTAUpgradeActivity.this.h.setVisibility(0);
                BleOTAUpgradeActivity.this.k.setVisibility(8);
                BleOTAUpgradeActivity.this.l.setVisibility(8);
                BleOTAUpgradeActivity.this.m.setVisibility(0);
                BleOTAUpgradeActivity.this.m.setText(R.string.ok_button);
                BleOTAUpgradeActivity.this.m.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        BleOTAUpgradeActivity.this.finish();
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.p.h();
    }
}
