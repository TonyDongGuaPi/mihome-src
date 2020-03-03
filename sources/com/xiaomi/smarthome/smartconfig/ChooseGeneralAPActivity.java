package com.xiaomi.smarthome.smartconfig;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.GeneralAPDeviceSearch;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.widget.ProgressButton;
import com.xiaomi.smarthome.miio.device.GeneralAPDevice;
import com.xiaomi.smarthome.stat.STAT;

public class ChooseGeneralAPActivity extends BaseActivity {
    public static final String KEY_MODEL = "model";

    /* renamed from: a  reason: collision with root package name */
    private static final int f22259a = 1;
    private SimpleDraweeView b;
    /* access modifiers changed from: private */
    public ProgressButton c;
    private TextView d;
    private TextView e;
    private TextView f;
    /* access modifiers changed from: private */
    public TextView g;
    /* access modifiers changed from: private */
    public GeneralAPDevice h;
    private View i;
    /* access modifiers changed from: private */
    public PluginDownloadCallback j;
    private PluginRecord k;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.feimi_choose);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("model");
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        this.h = GeneralAPDeviceSearch.a().b(stringExtra);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseGeneralAPActivity.this.finish();
            }
        });
        this.i = findViewById(R.id.bottom_btn_container);
        this.f = (TextView) findViewById(R.id.left_btn);
        this.g = (TextView) findViewById(R.id.right_btn);
        findViewById(R.id.title_bar).setBackgroundColor(getResources().getColor(R.color.white));
        this.d = (TextView) findViewById(R.id.name);
        this.e = (TextView) findViewById(R.id.desc);
        this.c = (ProgressButton) findViewById(R.id.add_btn);
        this.b = (SimpleDraweeView) findViewById(R.id.icon);
        this.b.setHierarchy(new GenericDraweeHierarchyBuilder(this.b.getResources()).setPlaceholderImage(getResources().getDrawable(R.drawable.device_list_phone_no)).setFadeDuration(200).build());
        this.k = CoreApi.a().d(this.h.model);
        if (this.k != null) {
            this.d.setText(this.k.p());
            this.e.setText(this.k.c().s());
            a(this.k.t());
        }
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseGeneralAPActivity.this.c();
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseGeneralAPActivity.this.finish();
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                PluginApi.getInstance().sendMessage(ChooseGeneralAPActivity.this, CoreApi.a().d(ChooseGeneralAPActivity.this.h.model), 1, intent, ChooseGeneralAPActivity.this.h.newDeviceStat(), (RunningProcess) null, false, (PluginApi.SendMessageCallback) null);
                ChooseGeneralAPActivity.this.finish();
            }
        });
        if (GeneralAPDeviceSearch.a().a(this.h.model)) {
            this.c.setText(R.string.open_imediate);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ChooseGeneralAPActivity.this.g.performClick();
                }
            });
        }
    }

    private void a(String str) {
        this.b.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).build())).build());
    }

    private static abstract class PluginDownloadCallback implements CoreApi.DownloadPluginCallback {
        boolean b;

        private PluginDownloadCallback() {
        }
    }

    private void a(int i2) {
        this.c.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public void a() {
        this.c.setButtonMode(0);
        a(0);
        this.c.setText(R.string.add_btn_text);
        this.i.setVisibility(8);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseGeneralAPActivity.this.c();
            }
        });
    }

    private void b() {
        BluetoothLog.c(String.format("onPluginDownloadStart", new Object[0]));
        this.c.setText(R.string.cancel);
        a(0);
        this.c.setButtonMode(1);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ChooseGeneralAPActivity.this.j != null) {
                    ChooseGeneralAPActivity.this.j.b = true;
                }
                ChooseGeneralAPActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
                ChooseGeneralAPActivity.this.a();
            }
        });
        this.i.setVisibility(8);
    }

    public void handleMessage(Message message) {
        if (message.what == 1) {
            int i2 = message.arg1;
            this.c.setProgress(i2);
            if (i2 == 100) {
                d();
                return;
            }
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(1, i2 + 1, 0), 10);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        BluetoothLog.c(String.format("startDownloadingPlugin", new Object[0]));
        b();
        PluginRecord d2 = CoreApi.a().d(this.h.model);
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
                    ChooseGeneralAPActivity.this.c.setProgress((int) (f * 100.0f));
                }
            }

            public void onSuccess(PluginRecord pluginRecord) {
                if (this.c > 0 && pluginRecord != null) {
                    STAT.i.a(System.currentTimeMillis() - this.c, pluginRecord.o());
                }
                BluetoothLog.e("Plugin Download onSuccess");
                if (!this.b) {
                    ChooseGeneralAPActivity.this.d();
                }
            }

            public void onFailure(PluginError pluginError) {
                BluetoothLog.e("Plugin Download onFailure");
                if (!this.b) {
                    Toast.makeText(ChooseGeneralAPActivity.this.getContext(), R.string.fimi_add_failed, 0).show();
                    ChooseGeneralAPActivity.this.a();
                }
            }

            public void onCancel() {
                BluetoothLog.e("Plugin Download onCancel");
                if (!this.b) {
                    Toast.makeText(ChooseGeneralAPActivity.this.getContext(), R.string.fimi_add_failed, 0).show();
                    ChooseGeneralAPActivity.this.a();
                }
            }
        };
        CoreApi.a().a(this.h.model, (CoreApi.DownloadPluginCallback) this.j);
    }

    /* access modifiers changed from: private */
    public void d() {
        BluetoothLog.c(String.format("onPluginDownloadComplete", new Object[0]));
        Toast.makeText(this, R.string.fimi_add_success, 0).show();
        a(8);
        this.i.setVisibility(0);
        if (!isFinishing()) {
            GeneralAPDeviceSearch.a().a((Device) this.h);
            GeneralAPDeviceSearch.a().a(true, this.h.model);
            SmartHomeDeviceManager.a().b((Device) this.h);
            DeviceFinder.a().c(this.h.did);
        }
    }
}
