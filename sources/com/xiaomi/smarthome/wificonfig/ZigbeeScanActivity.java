package com.xiaomi.smarthome.wificonfig;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.stat.STAT;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ZigbeeScanActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22965a = "IKEA_ZIGBEE";
    private static final int i = 30;
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    private static final int m = 3;
    private static final int n = 4;
    private static final int o = 5;
    private static final int p = 6;
    private static final int q = 7;
    private static final int r = 8;
    private String b;
    private String c;
    private PluginRecord d;
    /* access modifiers changed from: private */
    public List<Device> e;
    /* access modifiers changed from: private */
    public ImageView f;
    private View g;
    /* access modifiers changed from: private */
    public TextView h;
    /* access modifiers changed from: private */
    public volatile boolean s = false;
    /* access modifiers changed from: private */
    public volatile boolean t = false;
    /* access modifiers changed from: private */
    public int u = 30;
    /* access modifiers changed from: private */
    public boolean v = false;
    /* access modifiers changed from: private */
    public Handler w = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (!ZigbeeScanActivity.this.s) {
                        List unused = ZigbeeScanActivity.this.e = (List) message.obj;
                        ZigbeeScanActivity.this.d();
                        return;
                    }
                    return;
                case 1:
                    if (!ZigbeeScanActivity.this.s) {
                        ZigbeeScanActivity.this.c();
                        return;
                    }
                    return;
                case 2:
                    if (!ZigbeeScanActivity.this.s) {
                        ZigbeeScanActivity.this.d();
                        return;
                    }
                    return;
                case 3:
                    List list = (List) message.obj;
                    synchronized (ZigbeeScanActivity.class) {
                        if (ZigbeeScanActivity.this.a((List<Device>) ZigbeeScanActivity.this.e, (List<Device>) list)) {
                            boolean unused2 = ZigbeeScanActivity.this.s = true;
                            ZigbeeScanActivity.this.a(ZigbeeScanActivity.this.b((List<Device>) ZigbeeScanActivity.this.e, (List<Device>) list));
                        } else if (!ZigbeeScanActivity.this.s) {
                            ZigbeeScanActivity.this.w.postDelayed(new Runnable() {
                                public void run() {
                                    ZigbeeScanActivity.this.c();
                                }
                            }, 1000);
                        }
                    }
                    return;
                case 4:
                    if (!ZigbeeScanActivity.this.s) {
                        ZigbeeScanActivity.this.b();
                        return;
                    }
                    return;
                case 5:
                    if (!ZigbeeScanActivity.this.s) {
                        ZigbeeScanActivity.this.c();
                        return;
                    }
                    return;
                case 6:
                    ZigbeeScanActivity.this.startChooseRoom((Device) message.obj);
                    return;
                case 7:
                    if (!ZigbeeScanActivity.this.s) {
                        ZigbeeScanActivity.access$910(ZigbeeScanActivity.this);
                        TextView access$1000 = ZigbeeScanActivity.this.h;
                        access$1000.setText(ZigbeeScanActivity.this.u + "");
                        if (ZigbeeScanActivity.this.u <= 0) {
                            ZigbeeScanActivity.this.onTimeOut();
                            return;
                        }
                        ZigbeeScanActivity.this.w.sendMessageDelayed(Message.obtain(ZigbeeScanActivity.this.w, 7), 1000);
                        return;
                    }
                    return;
                case 8:
                    boolean unused3 = ZigbeeScanActivity.this.v = !ZigbeeScanActivity.this.v;
                    if (ZigbeeScanActivity.this.v) {
                        ZigbeeScanActivity.this.f.setImageResource(R.drawable.add_ikea_light_on);
                    } else {
                        ZigbeeScanActivity.this.f.setImageResource(R.drawable.add_ikea_light_off);
                    }
                    if (!ZigbeeScanActivity.this.t) {
                        ZigbeeScanActivity.this.w.sendEmptyMessageDelayed(8, 200);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    static /* synthetic */ int access$910(ZigbeeScanActivity zigbeeScanActivity) {
        int i2 = zigbeeScanActivity.u;
        zigbeeScanActivity.u = i2 - 1;
        return i2;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smart_config_zigbee_scan);
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getStringExtra("model");
            this.c = intent.getStringExtra("gateway_did");
            this.d = (PluginRecord) intent.getParcelableExtra("plugin_record");
            b();
            findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ZigbeeScanActivity.this.onBackPressed();
                }
            });
            ((TextView) findViewById(R.id.module_a_3_return_title)).setText(this.d.p());
            this.f = (ImageView) findViewById(R.id.image);
            this.f.setImageResource(R.drawable.add_ikea_light_on);
            this.h = (TextView) findViewById(R.id.tv_count);
            this.h.setText("30");
            this.g = findViewById(R.id.btn_cancel);
            this.g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ZigbeeScanActivity.this.e();
                    ZigbeeScanActivity.this.onBackPressed();
                }
            });
            a();
            this.w.sendEmptyMessageDelayed(7, 1000);
            return;
        }
        onBackPressed();
    }

    private void a() {
        this.w.sendEmptyMessageDelayed(8, 200);
    }

    public void onTimeOut() {
        if (isValid()) {
            this.s = true;
            new MLAlertDialog.Builder(this).b((int) R.string.cannot_scan_find_device).a((int) R.string.restart_scan, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ZigbeeScanActivity.this.b(dialogInterface, i);
                }
            }).b((int) R.string.how_to_reset, (DialogInterface.OnClickListener) $$Lambda$ZigbeeScanActivity$3SEG_wcMtksQKhrZkSCCX8RM9pg.INSTANCE).a(false).b().show();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        this.s = false;
        this.u = 30;
        TextView textView = this.h;
        textView.setText(this.u + "");
        this.w.sendMessageDelayed(Message.obtain(this.w, 7), 1000);
        b();
    }

    /* access modifiers changed from: private */
    public void a(Device device) {
        if (device != null) {
            a(device.did);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        DeviceApi.getInstance().getSubDevice(this, new String[]{this.c}, new GetSubDeviceOfGatewayCallback(this.w, true));
    }

    /* access modifiers changed from: private */
    public void c() {
        DeviceApi.getInstance().getSubDevice(this, new String[]{this.c}, new GetSubDeviceOfGatewayCallback(this.w, false));
    }

    /* access modifiers changed from: private */
    public boolean a(List<Device> list, List<Device> list2) {
        if (list2 == null || list2.size() <= 0) {
            return false;
        }
        if (list == null || list.size() <= 0 || list2.size() > list.size()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.smarthome.device.Device b(java.util.List<com.xiaomi.smarthome.device.Device> r3, java.util.List<com.xiaomi.smarthome.device.Device> r4) {
        /*
            r2 = this;
            java.util.Iterator r4 = r4.iterator()
        L_0x0004:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0021
            java.lang.Object r0 = r4.next()
            com.xiaomi.smarthome.device.Device r0 = (com.xiaomi.smarthome.device.Device) r0
            if (r3 == 0) goto L_0x0020
            int r1 = r3.size()
            if (r1 > 0) goto L_0x0019
            goto L_0x0020
        L_0x0019:
            boolean r1 = r3.contains(r0)
            if (r1 != 0) goto L_0x0004
            return r0
        L_0x0020:
            return r0
        L_0x0021:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.wificonfig.ZigbeeScanActivity.b(java.util.List, java.util.List):com.xiaomi.smarthome.device.Device");
    }

    private int a(List<Device> list, String str) {
        int i2 = 0;
        for (Device device : list) {
            if (str.equals(device.model)) {
                i2++;
            }
        }
        return i2;
    }

    /* access modifiers changed from: private */
    public void d() {
        MiioDeviceV2 miioDeviceV2 = (MiioDeviceV2) SmartHomeDeviceManager.a().b(this.c);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", "start_zigbee_join");
            jSONArray.put(30);
            jSONObject.put("params", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new GatewayCallback(this.w));
    }

    private void a(final String str) {
        BluetoothLog.c(String.format("getDeviceInfo did = %s", new Object[]{str}));
        DeviceApi.getInstance().getDeviceDetail(SHApplication.getAppContext(), new String[]{str}, new AsyncCallback<List<Device>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                BluetoothLog.c(String.format("onSuccess size = %d", new Object[]{Integer.valueOf(list.size())}));
                if (list.size() > 0) {
                    Device b2 = SmartHomeDeviceManager.a().b(str);
                    if (b2 != null) {
                        SmartHomeDeviceManager.a().c(b2);
                    }
                    for (Device next : list) {
                        if (str.equals(next.did)) {
                            b2 = next;
                        }
                    }
                    SmartHomeDeviceManager.a().b(b2);
                    DeviceFinder.a().c(str);
                    Message obtainMessage = ZigbeeScanActivity.this.w.obtainMessage(6);
                    obtainMessage.obj = b2;
                    obtainMessage.sendToTarget();
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void startChooseRoom(Device device) {
        Context appContext = SHApplication.getAppContext();
        Intent intent = new Intent(appContext, InitDeviceRoomActivity.class);
        if (device != null) {
            intent.putExtra("device_id", device.did);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            appContext.startActivity(intent);
            HomeManager.a().b(device);
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void e() {
        MiioDeviceV2 miioDeviceV2 = (MiioDeviceV2) SmartHomeDeviceManager.a().b(this.c);
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        try {
            jSONObject.put("id", (int) ((Math.random() * 100000.0d) + 100000.0d));
            jSONObject.put("method", "start_zigbee_join");
            jSONArray.put(0);
            jSONObject.put("params", jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        miioDeviceV2.a(jSONObject, (AsyncCallback<JSONObject, Error>) new GatewayCallback(this.w));
    }

    public void onResume() {
        super.onResume();
        STAT.c.a(0, this.b);
    }

    public void onPause() {
        super.onPause();
        STAT.c.a(this.mEnterTime, this.b);
    }

    public void onBackPressed() {
        this.s = true;
        Intent intent = new Intent();
        intent.putExtra(Constants.Event.FINISH, false);
        setResult(-1, intent);
        finish();
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.s = true;
        this.t = true;
    }

    static class GetSubDeviceOfGatewayCallback extends AsyncCallback<List<Device>, Error> {

        /* renamed from: a  reason: collision with root package name */
        Handler f22972a;
        boolean b;

        GetSubDeviceOfGatewayCallback(Handler handler, boolean z) {
            this.f22972a = handler;
            this.b = z;
        }

        /* renamed from: a */
        public void onSuccess(List<Device> list) {
            Message message;
            if (this.b) {
                message = this.f22972a.obtainMessage(0);
            } else {
                message = this.f22972a.obtainMessage(3);
            }
            message.obj = list;
            this.f22972a.sendMessage(message);
        }

        public void onFailure(Error error) {
            if (this.b) {
                this.f22972a.obtainMessage(4).sendToTarget();
            } else {
                this.f22972a.obtainMessage(3);
            }
        }
    }

    static class GatewayCallback extends AsyncCallback<JSONObject, Error> {

        /* renamed from: a  reason: collision with root package name */
        Handler f22971a;

        GatewayCallback(Handler handler) {
            this.f22971a = handler;
        }

        /* renamed from: a */
        public void onSuccess(JSONObject jSONObject) {
            try {
                if (jSONObject.getInt("code") == 0) {
                    JSONArray jSONArray = jSONObject.getJSONArray("result");
                    if (jSONArray != null) {
                        if (jSONArray.length() == 1) {
                            if ("ok".equals(jSONArray.get(0))) {
                                this.f22971a.sendEmptyMessage(1);
                                return;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f22971a.sendEmptyMessage(2);
        }

        public void onFailure(Error error) {
            this.f22971a.sendEmptyMessage(2);
        }
    }
}
