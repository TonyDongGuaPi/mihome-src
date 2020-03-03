package com.xiaomi.smarthome.miio.miband;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.download.DownloadManager;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.miio.miband.adpter.DeviceAdapter;
import com.xiaomi.smarthome.miio.miband.adpter.MibandAdapter;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.PluginDeviceDownloadItem;
import com.xiaomi.smarthome.miio.miband.data.UserData;
import com.xiaomi.smarthome.miio.miband.fragments.SleepFragment;
import com.xiaomi.smarthome.miio.miband.fragments.StepFragment;
import com.xiaomi.smarthome.miio.miband.utils.AccessManager;
import com.xiaomi.smarthome.miio.miband.utils.BandConstants;
import com.xiaomi.smarthome.miio.miband.utils.PluginDeviceNavigateHelper;
import com.xiaomi.smarthome.shop.utils.ShopLauncher;
import java.util.ArrayList;
import java.util.List;

public class MiBandMainActivity extends BaseWhiteActivity {
    public static final int GET_OAUTH_FAILED = 4097;
    public static final int GET_OAUTH_SUCCESS = 4096;
    public static final String MAC = "mac";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public BleDevice f19413a;
    private ImageView b;
    private ImageView c;
    private TextView d;
    private ViewPager e;
    private View f;
    private SleepFragment g;
    private StepFragment h;
    private List<Fragment> i;
    private MibandAdapter j;
    private DataManager k;
    private ListView l;
    PluginDeviceNavigateHelper.DownloadStateListener listener = new PluginDeviceNavigateHelper.DownloadStateListener() {
        public void a(PluginDeviceDownloadItem pluginDeviceDownloadItem) {
            if (MiBandMainActivity.this.m != null) {
                MiBandMainActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        MiBandMainActivity.this.m.notifyDataSetChanged();
                    }
                });
            }
        }
    };
    /* access modifiers changed from: private */
    public DeviceAdapter m;
    private UserData n;
    private DownloadManager o;
    private ViewPager.OnPageChangeListener p = new ViewPager.OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            MiBandMainActivity.this.a(i);
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("mac");
        if (SmartHomeDeviceManager.a().d() != null && !TextUtils.isEmpty(stringExtra)) {
            this.f19413a = (BleDevice) SmartHomeDeviceManager.a().b(stringExtra);
        }
        if (this.f19413a == null) {
            finish();
            return;
        }
        this.k = DataManager.a();
        this.k.a((Device) this.f19413a);
        a();
        setContentView(R.layout.miband_main_activity);
        d();
        c();
        f();
        b();
        this.o = new DownloadManager(getContentResolver(), getPackageName());
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                this.k.d();
                this.k.g();
                return;
            case 4097:
                int i2 = message.getData().getInt("code");
                MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
                builder.d(false);
                builder.a((int) R.string.get_access_failed_title);
                if (i2 == 24578) {
                    builder.b((int) R.string.check_account_retry);
                } else if (i2 == 37121) {
                    finish();
                    return;
                } else {
                    builder.b((int) R.string.check_network_retry);
                }
                builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        MiBandMainActivity.this.finish();
                    }
                }).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        MiBandMainActivity.this.mHandler.post(new Runnable() {
                            public void run() {
                                MiBandMainActivity.this.a();
                            }
                        });
                    }
                }).b().show();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                AccessManager.c().a((Activity) MiBandMainActivity.this, (AsyncResponseCallback<AccessManager.AccessInfo>) new AsyncResponseCallback<AccessManager.AccessInfo>() {
                    public void a(AccessManager.AccessInfo accessInfo) {
                        MiBandMainActivity.this.mHandler.sendMessage(MiBandMainActivity.this.mHandler.obtainMessage(4096));
                    }

                    public void a(int i) {
                        Log.d("OAuth:", String.valueOf(i));
                        Message obtainMessage = MiBandMainActivity.this.mHandler.obtainMessage(4097);
                        Bundle bundle = new Bundle();
                        bundle.putInt("code", i);
                        obtainMessage.setData(bundle);
                        MiBandMainActivity.this.mHandler.sendMessage(obtainMessage);
                    }

                    public void a(int i, Object obj) {
                        Log.d("OAuth:", String.format("error code: %d, error info:%s", new Object[]{Integer.valueOf(i), obj}));
                        Message obtainMessage = MiBandMainActivity.this.mHandler.obtainMessage(4097);
                        Bundle bundle = new Bundle();
                        bundle.putInt("code", i);
                        obtainMessage.setData(bundle);
                        MiBandMainActivity.this.mHandler.sendMessage(obtainMessage);
                    }
                });
            }
        }, 200);
    }

    public void onResume() {
        super.onResume();
        PluginDeviceNavigateHelper.a().a(this.listener);
    }

    public void onPause() {
        super.onPause();
        PluginDeviceNavigateHelper.a().b(this.listener);
    }

    private void b() {
        this.i = new ArrayList();
        this.h = new StepFragment();
        this.g = new SleepFragment();
        this.i.add(this.h);
        this.i.add(this.g);
        this.j = new MibandAdapter(getSupportFragmentManager(), this.i);
        this.e.setAdapter(this.j);
        this.e.setOnPageChangeListener(this.p);
        this.e.setCurrentItem(0);
    }

    private void c() {
        ((TextView) findViewById(R.id.module_a_3_return_transparent_title)).setText(R.string.miband_title);
        View findViewById = findViewById(R.id.module_a_3_return_more_more_transparent_btn);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        View findViewById2 = findViewById(R.id.module_a_3_return_transparent_btn);
        if (findViewById2 != null) {
            findViewById2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MiBandMainActivity.this.finish();
                }
            });
        }
    }

    private void d() {
        this.e = (ViewPager) findViewById(R.id.pager);
        this.b = (ImageView) findViewById(R.id.iv_steps_indicator);
        this.c = (ImageView) findViewById(R.id.iv_sleep_indicator);
        this.f = findViewById(R.id.view_container);
        this.l = (ListView) findViewById(R.id.lv_band_devices);
        this.d = (TextView) findViewById(R.id.device_list_title);
    }

    private void e() {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse("http://www.baidu.com"));
        request.a((Context) this, "/mishare/", "test.html");
        this.o.a(request);
    }

    private void f() {
        List<Device> d2 = SmartHomeDeviceManager.a().d();
        ArrayList arrayList = new ArrayList();
        for (Device next : d2) {
            if ((next.model.equalsIgnoreCase("midea.aircondition.v1") || next.model.equalsIgnoreCase("yeelink.light.ble1")) && next.isBinded() && !next.isShared()) {
                arrayList.add(next);
            }
        }
        View findViewById = findViewById(R.id.rl_device_list_empty);
        View findViewById2 = findViewById(R.id.btn_go_shop);
        findViewById(R.id.interactive_device_container);
        findViewById2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                if (view.isEnabled()) {
                    view.setEnabled(false);
                    MiBandMainActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            view.setEnabled(true);
                        }
                    }, 500);
                    ShopLauncher.a(MiBandMainActivity.this, String.format(BandConstants.n, new Object[]{MiBandMainActivity.this.getString(R.string.yeelight_device_name), MiBandMainActivity.this.getPackageName()}), false);
                }
            }
        });
        if (arrayList.size() > 0) {
            this.d.setText(R.string.device_connect_with_band);
            findViewById.setVisibility(8);
            this.l.setVisibility(0);
        } else {
            this.d.setText(R.string.device_with_band);
            findViewById.setVisibility(0);
            this.l.setVisibility(8);
        }
        this.m = new DeviceAdapter(this, arrayList);
        if (this.l != null) {
            this.l.setAdapter(this.m);
        }
        this.l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long j) {
                Device device = (Device) MiBandMainActivity.this.m.getItem(i);
                if (!AccessManager.c().d()) {
                    Toast.makeText(MiBandMainActivity.this, R.string.not_oauth, 0).show();
                } else if (device != null && view.isEnabled()) {
                    view.setEnabled(false);
                    MiBandMainActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            view.setEnabled(true);
                        }
                    }, 500);
                    if (!device.isOnline) {
                        Toast.makeText(MiBandMainActivity.this, R.string.other_device_offline, 0).show();
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("did", device.did);
                    intent.putExtra(BandConstants.b, MiBandMainActivity.this.f19413a.did);
                    intent.putExtra("mihome_page_navigate_path", "/navigate_page_to_band");
                    PluginDeviceNavigateHelper.a().b(MiBandMainActivity.this, device.model, intent);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (i2 == 0) {
            this.b.setImageResource(R.drawable.dot_indicator_selected);
            this.c.setImageResource(R.drawable.dot_indicator_unselected);
            this.f.setBackgroundColor(getResources().getColor(R.color.step_background_color));
            return;
        }
        this.b.setImageResource(R.drawable.dot_indicator_unselected);
        this.c.setImageResource(R.drawable.dot_indicator_selected);
        this.f.setBackgroundColor(getResources().getColor(R.color.sleep_background_color));
    }
}
