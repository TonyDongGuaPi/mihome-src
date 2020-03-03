package com.xiaomi.smarthome.device.choosedevice;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.card.GatewaySupportManger;

public class ResetPageRoute extends BaseActivity {
    public static final String EXTRA_MODEL = "extra_model";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ChooseDeviceHelper f15344a;
    boolean isChoose = false;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("extra_model");
        if (TextUtils.isEmpty(stringExtra)) {
            ToastUtil.a((int) R.string.failed);
            finish();
            return;
        }
        setContentView(new FrameLayout(this));
        this.f15344a = new ChooseDeviceHelper();
        this.f15344a.a((CommonActivity) this, (ChooseDeviceHelper.OnChooseDeviceListener) null);
        final PluginRecord d = CoreApi.a().d(stringExtra);
        if (d == null) {
            ToastUtil.a((int) R.string.failed);
        } else if (d.c().t() == Device.PID_SUBDEVICE) {
            GatewaySupportManger.d().a();
            if (GatewaySupportManger.d().c()) {
                this.f15344a.a(d, (Intent) null, false);
            } else {
                GatewaySupportManger.d().a((GatewaySupportManger.OnGatewaySupportListener) new GatewaySupportManger.OnGatewaySupportListener() {
                    public void a() {
                        GatewaySupportManger.d().b((GatewaySupportManger.OnGatewaySupportListener) this);
                        if (!ResetPageRoute.this.isChoose) {
                            ResetPageRoute.this.isChoose = true;
                            ResetPageRoute.this.f15344a.a(d, (Intent) null, false);
                        }
                    }

                    public void b() {
                        GatewaySupportManger.d().b((GatewaySupportManger.OnGatewaySupportListener) this);
                        if (!ResetPageRoute.this.isChoose) {
                            ResetPageRoute.this.isChoose = true;
                            ResetPageRoute.this.f15344a.a(d, (Intent) null, false);
                        }
                    }
                });
            }
        } else {
            this.f15344a.a(d, (Intent) null, false);
        }
        finish();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f15344a != null) {
            this.f15344a.a();
        }
    }
}
