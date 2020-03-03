package com.xiaomi.smarthome.device.bluetooth.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.device.DeviceFactory;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.device.bluetooth.BleDispatcher;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothConstants;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.shop.view.VerticalViewPager;
import java.util.ArrayList;
import java.util.List;

public class BleMatchActivity extends BaseActivity {
    public static final String KEY_COMBINE_MODEL = "key_combine_model";

    /* renamed from: a  reason: collision with root package name */
    private static final int f15223a = 2130903618;
    public static BleDevice mMatchedDevice;
    private View b;
    private TextView c;
    private VerticalViewPager d;
    private BleMatchPagerAdapter e;
    private List<BleMatchFragment> f;
    private BleDevice g;
    private ArrayList<String> h;
    private BleRssiMatchFragment i;
    private BleMatchImageFragment j;
    private BleMatchListFragment k;
    private String l;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!a()) {
            finish();
        } else if (!checkDirectBind()) {
            d();
            j();
            mMatchedDevice = null;
        }
    }

    public boolean checkDirectBind() {
        if (this.g != null && (this.g instanceof BleDeviceGroup)) {
            BleDeviceGroup bleDeviceGroup = (BleDeviceGroup) this.g;
            if (bleDeviceGroup.r() == 1 && !BleDispatcher.a(bleDeviceGroup.w().get(0))) {
                String t = this.g.t();
                switch (this.g.u()) {
                    case 0:
                    case 1:
                        if (TextUtils.isEmpty(t)) {
                            onDeviceMatched(bleDeviceGroup.w().get(0));
                            return true;
                        }
                        break;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean a() {
        BleDeviceGroup a2;
        Intent intent = getIntent();
        this.l = intent.getStringExtra(BluetoothConstants.i);
        if (b()) {
            String stringExtra = intent.getStringExtra("model");
            if (!TextUtils.isEmpty(stringExtra) && (a2 = BLEDeviceManager.a(stringExtra)) != null && a2.r() > 0) {
                this.g = a2;
            }
        } else if (c()) {
            this.g = (BleDevice) SmartHomeDeviceManager.a().b(intent.getStringExtra("extra_did"));
        }
        this.h = intent.getStringArrayListExtra(KEY_COMBINE_MODEL);
        return this.g != null && !TextUtils.isEmpty(this.g.model);
    }

    private boolean b() {
        return BluetoothConstants.o.equalsIgnoreCase(this.l);
    }

    private boolean c() {
        return BluetoothConstants.p.equalsIgnoreCase(this.l);
    }

    private void d() {
        setContentView(R.layout.ble_match_activity);
        this.b = findViewById(R.id.title_bar);
        this.c = (TextView) findViewById(R.id.module_a_3_return_title);
        this.d = (VerticalViewPager) findViewById(R.id.viewpager);
        e();
        f();
        this.e = new BleMatchPagerAdapter(getSupportFragmentManager(), this.f);
        this.d.setAdapter(this.e);
        this.d.setCurrentItem(0);
    }

    private void e() {
        if (DeviceUtils.a(this.g)) {
            this.b.setVisibility(0);
        } else if (!TextUtils.isEmpty(this.g.t())) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
        }
    }

    private void f() {
        boolean a2 = (!(this.g instanceof BleDeviceGroup) || ((BleDeviceGroup) this.g).r() != 1) ? false : BleDispatcher.a(((BleDeviceGroup) this.g).w().get(0));
        this.f = new ArrayList();
        if (!DeviceUtils.a(this.g)) {
            if (!a2) {
                switch (this.g.u()) {
                    case 0:
                        h();
                        if (TextUtils.equals(this.g.model, DeviceFactory.C) || TextUtils.equals(this.g.model, DeviceFactory.D)) {
                            g();
                            break;
                        }
                    case 1:
                        if (TextUtils.isEmpty(this.g.t())) {
                            h();
                            if (TextUtils.equals(this.g.model, DeviceFactory.C) || TextUtils.equals(this.g.model, DeviceFactory.D)) {
                                g();
                                break;
                            }
                        } else {
                            g();
                            break;
                        }
                    case 2:
                        i();
                        break;
                }
            } else {
                this.b.setVisibility(8);
                g();
            }
        } else {
            g();
            h();
        }
        if (this.f.size() != 1) {
            return;
        }
        if (this.k != this.f.get(0)) {
            this.b.setVisibility(8);
            return;
        }
        this.c.setText(this.g.s());
        if (this.f.get(0) instanceof BleMatchListFragment) {
            this.b.setVisibility(0);
        }
    }

    private void g() {
        this.j = BleMatchImageFragment.b();
        this.j.a(this.g, this.h);
        this.f.add(this.j);
    }

    private void h() {
        this.k = BleMatchListFragment.b();
        this.k.a(this.g, (List<String>) this.h);
        this.f.add(this.k);
    }

    private void i() {
        this.i = BleRssiMatchFragment.b();
        this.i.a(this.g, this.h);
        this.f.add(this.i);
    }

    private void j() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleMatchActivity.this.finish();
            }
        });
    }

    public static void setMatchedDevice(BleDevice bleDevice) {
        mMatchedDevice = bleDevice;
        BleCacheUtils.x(mMatchedDevice.mac);
    }

    public void onDeviceMatched(BleDevice bleDevice) {
        Intent intent = new Intent();
        if (getIntent().hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
            intent.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, getIntent().getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
        }
        onDeviceMatched(this, bleDevice, intent);
    }

    public static void onDeviceMatched(Activity activity, BleDevice bleDevice, Intent intent) {
        BluetoothLog.e(String.format("onDeviceMatched: " + bleDevice.toString(), new Object[0]));
        setMatchedDevice(bleDevice);
        BleDispatcher.a(activity, bleDevice, intent);
    }

    public void onDeviceNotFound(String str) {
        BluetoothLog.e("onDeviceNotFound");
        Intent intent = new Intent();
        intent.putExtras(getIntent());
        intent.setClass(this, BleErrorActivity.class);
        intent.putExtra(BleErrorActivity.ERROR_FROM, 1);
        intent.putExtra("model", str);
        startActivity(intent);
        finish();
    }

    public boolean hasMatchListFragment() {
        if (TextUtils.equals(this.g.model, DeviceFactory.C) || TextUtils.equals(this.g.model, DeviceFactory.D)) {
            return false;
        }
        for (BleMatchFragment bleMatchFragment : this.f) {
            if (bleMatchFragment instanceof BleMatchListFragment) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMatchImageFragment() {
        for (BleMatchFragment bleMatchFragment : this.f) {
            if (bleMatchFragment instanceof BleMatchImageFragment) {
                return true;
            }
        }
        return false;
    }

    public void switchToMatchListFragment() {
        int indexOf;
        if (this.k != null && (indexOf = this.f.indexOf(this.k)) >= 0 && indexOf < this.f.size()) {
            this.d.setCurrentItem(indexOf);
        }
    }

    public void switchToMatchImageFragment() {
        int indexOf;
        if (this.j != null && (indexOf = this.f.indexOf(this.j)) >= 0 && indexOf < this.f.size()) {
            this.b.setVisibility(8);
            this.d.setCurrentItem(indexOf);
        }
    }
}
