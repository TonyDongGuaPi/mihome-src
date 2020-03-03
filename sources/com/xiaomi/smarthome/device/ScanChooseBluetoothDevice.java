package com.xiaomi.smarthome.device;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.exoplayer2.C;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.device.bluetooth.BleDispatcher;
import com.xiaomi.smarthome.device.bluetooth.MiioBtSearchResponse;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.choosedevice.ScanDeviceProgressBar;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.miio.activity.GDPRLicenseActivity;
import com.xiaomi.smarthome.setting.ServerRouteUtil;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScanChooseBluetoothDevice extends BaseActivity implements ScanDeviceProgressBar.OnTimeOutListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f14962a;
    /* access modifiers changed from: private */
    public boolean b;
    /* access modifiers changed from: private */
    public ArrayList<String> c = new ArrayList<>();
    private BroadcastReceiver d = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (!ScanChooseBluetoothDevice.this.b && BLEDeviceManager.f15064a.equals(intent.getAction())) {
                List<BleDeviceGroup> c = BLEDeviceManager.c();
                if (ScanChooseBluetoothDevice.this.f14962a != null) {
                    for (BleDeviceGroup next : c) {
                        Iterator it = ScanChooseBluetoothDevice.this.c.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (next.h((String) it.next())) {
                                    boolean unused = ScanChooseBluetoothDevice.this.b = true;
                                    ScanChooseBluetoothDevice.this.a(next);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    };
    private final MiioBtSearchResponse e = new MiioBtSearchResponse() {
        public void a() {
        }

        public void a(BleDevice bleDevice) {
        }

        public void b() {
            if (!ScanChooseBluetoothDevice.this.b) {
                ScanChooseBluetoothDevice.this.e();
            }
        }

        public void c() {
            if (!ScanChooseBluetoothDevice.this.b) {
                ScanChooseBluetoothDevice.this.e();
            }
        }
    };
    @BindView(2131428170)
    TextView mCannotFindDeviceTx;
    @BindView(2131431627)
    ScanDeviceProgressBar mProgressBar;
    @BindView(2131432119)
    Button mRescanBt;
    @BindView(2131432122)
    TextView mScanDescText;
    @BindView(2131432124)
    RelativeLayout mScanFailView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan_choose_bluetooth_device);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent != null) {
            this.f14962a = intent.getStringExtra("model");
            a();
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanChooseBluetoothDevice.this.onBackPressed();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.scan_device));
        this.mProgressBar.registerTimeOutListener(this);
        this.mRescanBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aY(ScanChooseBluetoothDevice.this.f14962a);
                ScanChooseBluetoothDevice.this.d();
            }
        });
        this.mCannotFindDeviceTx.getPaint().setFlags(9);
        this.mCannotFindDeviceTx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aX(ScanChooseBluetoothDevice.this.f14962a);
                Intent intent = new Intent(ScanChooseBluetoothDevice.this, WebShellActivity.class);
                intent.putExtra("url", ScanChooseBluetoothDevice.this.b());
                ScanChooseBluetoothDevice.this.startActivity(intent);
            }
        });
        STAT.c.j(this.f14962a);
    }

    private void a() {
        if (!TextUtils.isEmpty(this.f14962a)) {
            List<PluginRecord> O = CoreApi.a().O();
            PluginRecord d2 = CoreApi.a().d(this.f14962a);
            for (PluginRecord next : O) {
                if (next.c().N() != 0 && next.c().N() == d2.c().d()) {
                    this.c.add(next.o());
                }
            }
            this.c.add(this.f14962a);
        }
    }

    /* access modifiers changed from: private */
    public String b() {
        return ServerRouteUtil.b(SHApplication.getAppContext()) + "/views/faqDetail.html?question=" + getString(R.string.param_question_cannot_find_device);
    }

    private void c() {
        BluetoothUtils.a(this, R.string.open_bluetooth_tips, new BleResponse() {
            public void a(int i, Object obj) {
                if (i == 0) {
                    ScanChooseBluetoothDevice.this.d();
                } else {
                    ScanChooseBluetoothDevice.this.a((BleDevice) null);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        BLEDeviceManager.a(this.e);
        this.mScanFailView.setVisibility(8);
        this.mScanDescText.setVisibility(0);
        this.mProgressBar.setVisibility(0);
        this.mProgressBar.reset();
        this.mProgressBar.setTime(25000);
        this.mProgressBar.start();
    }

    /* access modifiers changed from: private */
    public void e() {
        STAT.c.k(this.f14962a);
        this.mProgressBar.stop();
        this.mScanDescText.setVisibility(8);
        this.mProgressBar.setVisibility(8);
        this.mScanFailView.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void a(BleDevice bleDevice) {
        if (bleDevice != null) {
            c(bleDevice);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(Constants.Event.FINISH, false);
        setResult(0, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void b(BleDevice bleDevice) {
        if (CoreApi.a().c(bleDevice.model)) {
            Intent intent = new Intent();
            if (getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                intent.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
            }
            BleDispatcher.a((Context) this, bleDevice, intent, this.c);
        } else if (DeviceUtils.a(bleDevice)) {
            BleDispatcher.b(this, bleDevice, (Intent) null, this.c);
            return;
        } else {
            Intent a2 = DeviceRenderer.a((Device) bleDevice).a((Device) bleDevice, getContext(), (Bundle) null, false, (DeviceRenderer.LoadingCallback) null);
            if (a2 != null) {
                startActivity(a2);
            }
        }
        new Intent().putExtra(Constants.Event.FINISH, false);
        setResult(-1);
        finish();
    }

    private void c(final BleDevice bleDevice) {
        if (ServerCompact.d(CoreApi.a().F())) {
            new MLAlertDialog.Builder(this).a((CharSequence) getString(R.string.license_title)).a(d(bleDevice)).b((CharSequence) getString(R.string.license_negative_btn), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ScanChooseBluetoothDevice.this.a((BleDevice) null);
                }
            }).a((CharSequence) getString(R.string.license_positive_btn), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ScanChooseBluetoothDevice.this.b(bleDevice);
                }
            }).d();
        } else {
            b(bleDevice);
        }
    }

    private SpannableStringBuilder d(final BleDevice bleDevice) {
        String string = getString(R.string.license_content);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass9 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(ScanChooseBluetoothDevice.this, GDPRLicenseActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_model", bleDevice.model);
                ScanChooseBluetoothDevice.this.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#0099ff"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return spannableStringBuilder;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        unregisterReceiver(this.d);
        BLEDeviceManager.f();
        this.mProgressBar.stop();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        registerReceiver(this.d, new IntentFilter(BLEDeviceManager.f15064a));
        if (!BluetoothUtils.b()) {
            c();
        } else {
            d();
        }
    }

    public void onBackPressed() {
        a((BleDevice) null);
        this.mProgressBar.stop();
    }

    public void onTimeOut() {
        BLEDeviceManager.f();
        if (!this.b) {
            e();
        }
    }
}
