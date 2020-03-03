package com.xiaomi.smarthome.smartconfig;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.TemporaryDeviceSearch;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.widget.ProgressButton;
import com.xiaomi.smarthome.miio.device.TemporaryDevice;
import com.xiaomi.smarthome.stat.STAT;
import java.util.Iterator;

public class HideDeviceEntranceAct extends BaseActivity {
    public static final String MODEL_KEY = "model";

    /* renamed from: a  reason: collision with root package name */
    private static final int f22290a = 1;
    private SimpleDraweeView b;
    /* access modifiers changed from: private */
    public ProgressButton c;
    private TextView d;
    private TextView e;
    private TextView f;
    /* access modifiers changed from: private */
    public TextView g;
    private View h;
    /* access modifiers changed from: private */
    public Device i;
    /* access modifiers changed from: private */
    public PluginDownloadCallback j;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hide_dev_entrance);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HideDeviceEntranceAct.this.finish();
            }
        });
        this.h = findViewById(R.id.bottom_btn_container);
        this.f = (TextView) findViewById(R.id.left_btn);
        this.g = (TextView) findViewById(R.id.right_btn);
        findViewById(R.id.title_bar).setBackgroundColor(getResources().getColor(R.color.white));
        this.d = (TextView) findViewById(R.id.name);
        this.e = (TextView) findViewById(R.id.desc);
        this.c = (ProgressButton) findViewById(R.id.add_btn);
        this.b = (SimpleDraweeView) findViewById(R.id.icon);
        this.b.setHierarchy(new GenericDraweeHierarchyBuilder(this.b.getResources()).setPlaceholderImage(getResources().getDrawable(R.drawable.device_list_phone_no)).setFadeDuration(200).build());
        String stringExtra = getIntent().getStringExtra("model");
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        PluginRecord d2 = CoreApi.a().d(stringExtra);
        Iterator<Device> it = SmartHomeDeviceManager.a().d().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Device next = it.next();
            if (next.model.equals(stringExtra)) {
                this.i = next;
                break;
            }
        }
        if (this.i == null) {
            this.i = TemporaryDeviceSearch.a().b(stringExtra);
        }
        this.d.setText(d2.p());
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HideDeviceEntranceAct.this.a();
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HideDeviceEntranceAct.this.finish();
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (XmPluginHostApi.instance().getDeviceByDid(HideDeviceEntranceAct.this.i.did) == null) {
                    TemporaryDeviceSearch.a().a(HideDeviceEntranceAct.this.i.model, true);
                    SmartHomeDeviceManager.a().b(HideDeviceEntranceAct.this.i);
                }
                Intent intent = new Intent();
                PluginApi.getInstance().sendMessage(HideDeviceEntranceAct.this, CoreApi.a().d(HideDeviceEntranceAct.this.i.model), 1, intent, HideDeviceEntranceAct.this.i.newDeviceStat(), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
                HideDeviceEntranceAct.this.finish();
            }
        });
        if (((this.i instanceof TemporaryDevice) && TemporaryDeviceSearch.a().a(this.i.model)) || d2.k() || d2.l()) {
            this.c.setText(R.string.open_imediate);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HideDeviceEntranceAct.this.g.performClick();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        BluetoothLog.c(String.format("startDownloadingPlugin", new Object[0]));
        c();
        PluginRecord d2 = CoreApi.a().d(this.i.model);
        if (d2.l() || d2.k()) {
            this.mHandler.sendEmptyMessage(1);
            return;
        }
        this.j = new PluginDownloadCallback() {
            private long c = 0;

            public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
            }

            public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                this.c = System.currentTimeMillis();
            }

            public void onProgress(PluginRecord pluginRecord, float f) {
                if (!this.b) {
                    HideDeviceEntranceAct.this.c.setProgress((int) (f * 100.0f));
                }
            }

            public void onSuccess(PluginRecord pluginRecord) {
                if (this.c > 0 && pluginRecord != null) {
                    STAT.i.a(System.currentTimeMillis() - this.c, pluginRecord.o());
                }
                BluetoothLog.e("Plugin Download onSuccess");
                if (!this.b) {
                    HideDeviceEntranceAct.this.b();
                }
            }

            public void onFailure(PluginError pluginError) {
                BluetoothLog.e("Plugin Download onFailure");
                if (!this.b) {
                    Toast.makeText(HideDeviceEntranceAct.this.getContext(), "下载插件失败", 0).show();
                    HideDeviceEntranceAct.this.b();
                }
            }

            public void onCancel() {
                BluetoothLog.e("Plugin Download onCancel");
                if (!this.b) {
                    Toast.makeText(HideDeviceEntranceAct.this.getContext(), R.string.fimi_add_failed, 0).show();
                    HideDeviceEntranceAct.this.d();
                }
            }
        };
        CoreApi.a().a(this.i.model, (CoreApi.DownloadPluginCallback) this.j);
    }

    /* access modifiers changed from: private */
    public void b() {
        BluetoothLog.c(String.format("onPluginDownloadComplete", new Object[0]));
        Toast.makeText(this, R.string.fimi_add_success, 0).show();
        a(8);
        this.h.setVisibility(0);
        if (!isFinishing()) {
            TemporaryDeviceSearch.a().a(this.i.model, true);
            SmartHomeDeviceManager.a().b(this.i);
            a(this.i);
        }
    }

    private void a(Device device) {
        Home m;
        if (device != null && (m = HomeManager.a().m()) != null) {
            HomeManager.a().a(m, (Room) null, device, (HomeManager.IHomeOperationCallback) null);
        }
    }

    private void c() {
        BluetoothLog.c(String.format("onPluginDownloadStart", new Object[0]));
        this.c.setText(R.string.cancel);
        a(0);
        this.c.setButtonMode(1);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HideDeviceEntranceAct.this.j != null) {
                    HideDeviceEntranceAct.this.j.b = true;
                }
                HideDeviceEntranceAct.this.mHandler.removeCallbacksAndMessages((Object) null);
                HideDeviceEntranceAct.this.d();
            }
        });
        this.h.setVisibility(8);
    }

    private void a(int i2) {
        this.c.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void d() {
        this.c.setButtonMode(0);
        a(0);
        this.c.setText(R.string.add_btn_text);
        this.h.setVisibility(8);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HideDeviceEntranceAct.this.a();
            }
        });
    }

    private static abstract class PluginDownloadCallback implements CoreApi.DownloadPluginCallback {
        boolean b;

        private PluginDownloadCallback() {
        }
    }
}
