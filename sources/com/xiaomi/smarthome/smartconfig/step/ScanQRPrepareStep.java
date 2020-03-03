package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.family.FamilyMemberData;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ScanQRPrepareStep extends SmartConfigStep implements DeviceFinder.IDeviceLooper {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22683a = 100;
    private static final int d = -1;
    private static final int e = 0;
    private static final int f = 1;
    private static final int l = 1827;
    private String b;
    private String c;
    private int g;
    private String h;
    private String i;
    private String j;
    private TextView k;
    private ImageView m;
    private PieProgressBar n;
    private TextView o;
    private AnimationDrawable p;

    public void c() {
    }

    public void d() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Message message) {
        if (message.what == l) {
            int i2 = message.arg1;
            if (i2 >= 0) {
                this.k.setText(String.format("%d", new Object[]{Integer.valueOf(i2)}));
            }
            if (i2 >= 0) {
                Handler U_ = U_();
                Message obtainMessage = U_.obtainMessage(l, i2 - 1, 0);
                this.n.setPercent(((float) (60 - i2)) / 0.6f);
                U_.sendMessageDelayed(obtainMessage, 1000);
                return;
            }
            DeviceFinder.a().b();
            a(SmartConfigStep.Step.STEP_XIAOXUN_ERROR);
        }
    }

    public void a(Context context) {
        a(context, R.layout.scan_barcode_connect_activity);
        TitleBarUtil.a((Activity) this.af);
        this.b = (String) SmartConfigDataProvider.a().a("device_model");
        this.k = (TextView) this.ae.findViewById(R.id.timing);
        this.m = (ImageView) this.ae.findViewById(R.id.icon);
        this.o = (TextView) this.ae.findViewById(R.id.tips);
        this.n = (PieProgressBar) this.ae.findViewById(R.id.pbar);
        Intent intent = new Intent();
        intent.setClass(context, ScanBarcodeActivity.class);
        intent.putExtra("model", this.b);
        intent.putExtra("from", 100);
        ((Activity) context).startActivityForResult(intent, 100);
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            d_(false);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.h = jSONObject.optString("sid");
            this.i = jSONObject.optString("did");
            this.j = jSONObject.optString(FamilyMemberData.d);
            this.g = jSONObject.optInt("bindType", -1);
            if (!TextUtils.isEmpty(this.i)) {
                if (this.g != -1) {
                    b();
                    return;
                }
            }
            a(SmartConfigStep.Step.STEP_XIAOXUN_ERROR);
        } catch (JSONException e2) {
            e2.printStackTrace();
            d_(false);
        }
    }

    private void b() {
        this.ae.setBackgroundColor(Color.parseColor("#fe6e46"));
        switch (this.g) {
            case 0:
                this.m.setVisibility(0);
                this.m.setBackgroundResource(R.drawable.xiaoxun_frame);
                this.o.setText(R.string.xiaoxun_tips_1);
                this.p = (AnimationDrawable) this.m.getBackground();
                this.n.setVisibility(8);
                this.p.start();
                break;
            case 1:
                this.m.setVisibility(8);
                this.n.setVisibility(0);
                this.o.setText(this.af.getString(R.string.xiaoxun_tips_2, new Object[]{this.j, this.j}));
                break;
        }
        g();
        i();
    }

    private void g() {
        Handler U_ = U_();
        U_.sendMessage(U_.obtainMessage(l, 60, 0));
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.p != null) {
            this.p.stop();
        }
        U_().removeMessages(l);
    }

    /* access modifiers changed from: private */
    public void i() {
        BluetoothLog.c(String.format("scanXiaoxunWatch model = %s, sid = %s, did = %s", new Object[]{this.b, this.h, this.i}));
        if (this.ag) {
            BluetoothLog.c(String.format("isFinished %b", new Object[]{Boolean.valueOf(this.ag)}));
            return;
        }
        DeviceFinder.a().a((ConfigStep.DeviceFinderCallback) new DeviceFinder.DeviceFinderCallback2() {
            public void a(int i) {
                if (i == -9) {
                    ScanQRPrepareStep.this.a(SmartConfigStep.Step.STEP_XIAOXUN_ERROR);
                }
            }

            public void a(List<Device> list) {
                BluetoothLog.c(String.format("onDeviceConnectionSuccess", new Object[0]));
                if (list != null) {
                    for (Device next : list) {
                        BluetoothLog.c(String.format("name = %s, did = %s", new Object[]{next.name, next.did}));
                    }
                }
                ScanQRPrepareStep.this.h();
                ScanQRPrepareStep.this.D();
            }

            public void a() {
                BluetoothLog.c(String.format("onDeviceConnectionFailure", new Object[0]));
                if (ScanQRPrepareStep.this.U_().hasMessages(ScanQRPrepareStep.l)) {
                    ScanQRPrepareStep.this.i();
                } else {
                    ScanQRPrepareStep.this.a(SmartConfigStep.Step.STEP_XIAOXUN_ERROR);
                }
            }

            public void b(List<Device> list) {
                BluetoothLog.c(String.format("onDeviceConnectionBind", new Object[0]));
            }
        }, this.b, this.h, this.i, (DeviceFinder.IDeviceLooper) this);
    }

    private boolean j() {
        return !TextUtils.isEmpty(this.b) && DeviceFactory.r(this.b);
    }

    public void a(int i2, int i3, Intent intent) {
        if (i2 != 100) {
            return;
        }
        if (i3 == -1) {
            this.c = intent.getStringExtra("scan_result");
            SmartConfigDataProvider.a().b(SmartConfigDataProvider.D, this.c);
            if (j()) {
                a(this.c);
            } else {
                D();
            }
        } else {
            d_(false);
        }
    }

    public void e() {
        BluetoothLog.c(String.format("onFinishStep", new Object[0]));
        if (j()) {
            U_().removeCallbacksAndMessages((Object) null);
            DeviceFinder.a().b();
        }
    }

    public void a(Context context, String[] strArr, String str, String str2, String str3, AsyncCallback<List<Device>, Error> asyncCallback) {
        DeviceApi.getInstance().getNewDevice2(context, strArr, str, str2, str3, asyncCallback);
    }

    public boolean a() {
        d_(false);
        return true;
    }
}
