package com.xiaomi.smarthome.miio.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.IBleUpgradeController;
import com.xiaomi.smarthome.bluetooth.IBleUpgradeViewer;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.IXmPluginHostActivity;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.update.ui.MiioUpgradeActivity;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;

public class BleUpgradeActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private ViewSwitcher f11709a;
    private View b;
    private PieProgressBar c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private Button h;
    /* access modifiers changed from: private */
    public ImageView i;
    /* access modifiers changed from: private */
    public String j;
    private int k;
    private String l;
    private Device m;
    private IBleUpgradeController n;
    /* access modifiers changed from: private */
    public boolean o = true;
    /* access modifiers changed from: private */
    public final Handler p = new Handler() {
        public void handleMessage(Message message) {
            int i = message.what;
            Bundle bundle = (Bundle) message.obj;
            if (bundle == null) {
                bundle = new Bundle();
            }
            BluetoothMyLogger.f("BleUpgradeActivity.showPage " + i);
            switch (i) {
                case 0:
                    BleUpgradeActivity.this.a();
                    return;
                case 1:
                    BleUpgradeActivity.this.d();
                    return;
                case 2:
                    BleUpgradeActivity.this.e();
                    return;
                case 3:
                    BleUpgradeActivity.this.a(bundle.getInt(XmBluetoothManager.EXTRA_UPGRADE_PROCESS));
                    return;
                case 4:
                    BleUpgradeActivity.this.f();
                    CoreApi.a().a(BleUpgradeActivity.this.j, false);
                    return;
                case 5:
                    BleUpgradeActivity.this.g();
                    CoreApi.a().a(BleUpgradeActivity.this.j, false);
                    return;
                default:
                    return;
            }
        }
    };
    private final IBleUpgradeViewer.Stub q = new IBleUpgradeViewer.Stub() {
        public void showPage(int i, Bundle bundle) throws RemoteException {
            BleUpgradeActivity.this.p.obtainMessage(i, bundle).sendToTarget();
        }

        public void setBtnBackEnabled(boolean z) throws RemoteException {
            boolean unused = BleUpgradeActivity.this.o = z;
            if (BleUpgradeActivity.this.i != null) {
                BleUpgradeActivity.this.i.setEnabled(z);
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.miio_setting_update);
        getWindow().addFlags(128);
        Intent intent = getIntent();
        this.j = intent.getStringExtra(MiioUpgradeActivity.MIIO_UPGRADE_DID);
        this.k = intent.getIntExtra(MiioUpgradeActivity.MIIO_UPGRADE_PID, 0);
        this.l = intent.getStringExtra(MiioUpgradeActivity.MIIO_UPGRADE_NAME);
        this.m = SmartHomeDeviceManager.a().b(this.j);
        this.i = (ImageView) findViewById(R.id.module_a_3_return_btn);
        c();
        this.f11709a = (ViewSwitcher) findViewById(R.id.vs_root);
        this.b = findViewById(R.id.ll_loading);
        this.c = (PieProgressBar) findViewById(R.id.pb_progress);
        this.d = (TextView) findViewById(R.id.txt_progress);
        this.e = (TextView) findViewById(R.id.txt_update_title);
        this.f = (TextView) findViewById(R.id.txt_update_desc);
        this.g = (TextView) findViewById(R.id.txt_updating_tip);
        this.h = (Button) findViewById(R.id.btn_bottom);
        this.c.setPercentView(this.d);
        a();
        Bundle extras = intent.getExtras();
        if (!a(extras)) {
            Toast.makeText(this, "Upgrade Controller null", 0).show();
            finish();
            return;
        }
        b(extras);
    }

    private boolean a(Bundle bundle) {
        IBinder binder = (bundle == null || Build.VERSION.SDK_INT < 18) ? null : bundle.getBinder(IXmPluginHostActivity.BleMenuItem.EXTRA_UPGRADE_CONTROLLER);
        if (binder != null) {
            this.n = IBleUpgradeController.Stub.asInterface(binder);
        }
        if (this.n == null) {
            return false;
        }
        a((IBleUpgradeViewer) this.q);
        return true;
    }

    public void onBackPressed() {
        if (this.o) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.f11709a.getCurrentView() != this.b) {
            this.f11709a.showNext();
        }
    }

    private void b() {
        if (this.f11709a.getCurrentView() == this.b) {
            this.f11709a.showNext();
        }
    }

    private void c() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleUpgradeActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.l);
    }

    /* access modifiers changed from: private */
    public void d() {
        b();
        this.c.setBackgroundResource(R.drawable.update_img_latest);
        TextView textView = this.e;
        textView.setText(getResources().getString(R.string.model_latest) + "\n\n" + getResources().getString(R.string.app_curr_version) + " " + h());
        this.d.setVisibility(8);
        this.c.setPercent(0.0f);
        this.c.setOnClickListener((View.OnClickListener) null);
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setText(R.string.ok_button);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleUpgradeActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        b();
        this.d.setVisibility(8);
        this.c.setBackgroundResource(R.drawable.update_img_update);
        this.c.setPercent(0.0f);
        this.c.setOnClickListener((View.OnClickListener) null);
        this.e.setVisibility(0);
        TextView textView = this.e;
        textView.setText(getResources().getString(R.string.list_item_curr_version) + " " + h() + "\n\n" + getResources().getString(R.string.list_item_latest_version) + " " + i());
        this.f.setVisibility(0);
        this.f.setText(j());
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setText(R.string.update_now);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleUpgradeActivity.this.k();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        b();
        this.e.setVisibility(0);
        this.e.setText(R.string.model_updating);
        this.f.setVisibility(8);
        this.g.setVisibility(0);
        this.h.setVisibility(4);
        this.c.setOnClickListener((View.OnClickListener) null);
        this.c.setBackgroundResource(R.drawable.kuailian_progress_filled_not);
        this.d.setVisibility(0);
        if (i2 >= 0 && i2 < 100) {
            this.c.setPercent((float) i2);
        } else if (i2 >= 100) {
            this.c.setPercent(99.0f);
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        b();
        this.c.setBackgroundResource(R.drawable.update_img_success);
        TextView textView = this.e;
        textView.setText(getResources().getString(R.string.model_update_success) + "\n\n" + getResources().getString(R.string.app_curr_version) + " " + h());
        this.d.setVisibility(8);
        this.c.setPercent(0.0f);
        this.c.setOnClickListener((View.OnClickListener) null);
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setText(R.string.ok_button);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleUpgradeActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        b();
        this.e.setVisibility(0);
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(0);
        this.h.setText(R.string.ok_button);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleUpgradeActivity.this.finish();
            }
        });
        this.c.setBackgroundResource(R.drawable.update_img_failed);
        this.c.setPercent(0.0f);
        this.d.setVisibility(8);
        this.e.setText(R.string.update_failed_retry);
        this.c.setOnClickListener((View.OnClickListener) null);
    }

    private String h() {
        try {
            return this.n.getCurrentVersion();
        } catch (Exception unused) {
            return "";
        }
    }

    private String i() {
        try {
            return this.n.getLatestVersion();
        } catch (Exception unused) {
            return "";
        }
    }

    private String j() {
        try {
            return this.n.getUpgradeDescription();
        } catch (Exception unused) {
            return "";
        }
    }

    /* access modifiers changed from: private */
    public void k() {
        try {
            CoreApi.a().a(this.j, true);
            this.n.startUpgrade();
        } catch (Exception unused) {
        }
    }

    private void a(IBleUpgradeViewer iBleUpgradeViewer) {
        try {
            this.n.attachUpgradeCaller(iBleUpgradeViewer);
        } catch (Exception unused) {
        }
    }

    private void l() {
        try {
            this.n.detachUpgradeCaller();
        } catch (Exception unused) {
        }
    }

    private void b(Bundle bundle) {
        try {
            this.n.onActivityCreated(bundle);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        l();
        super.onDestroy();
        CoreApi.a().a(this.j, false);
    }
}
