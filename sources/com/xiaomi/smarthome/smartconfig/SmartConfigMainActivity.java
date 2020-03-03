package com.xiaomi.smarthome.smartconfig;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.HomeKeyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.service.DeviceObserveService;
import com.xiaomi.smarthome.smartconfig.stage.ConfigStage;
import com.xiaomi.smarthome.smartconfig.step.CameraResetStep;
import com.xiaomi.smarthome.smartconfig.step.ChooseWifiStep;
import com.xiaomi.smarthome.smartconfig.step.ChooseWifiStepV2;
import com.xiaomi.smarthome.smartconfig.step.DeviceInfoStep;
import com.xiaomi.smarthome.smartconfig.step.QRCameraStep;
import com.xiaomi.smarthome.smartconfig.step.ScanQRPrepareStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.smartconfig.step.XiaofangResetStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.WifiScanManager;
import com.xiaomi.youpin.hawkeye.entity.StatType;
import java.util.Stack;

public class SmartConfigMainActivity extends BaseActivity {
    public static int DEVICE_FROM = 0;
    public static int DEVICE_FROM_APP_PLUS_TYPE = 6;
    public static final int REQUESR_CODE_RESETPAGE = 101;
    public static final int REQUESR_CODE_SYSTEM_CHECKWIFI = 102;

    /* renamed from: a  reason: collision with root package name */
    private FrameLayout f22324a;
    /* access modifiers changed from: private */
    public Stack<SmartConfigStep> b = new Stack<>();
    /* access modifiers changed from: private */
    public SmartConfigStep.Step c;
    private int d;
    private boolean e = false;
    private long f;
    private boolean g = true;
    private Dialog h;
    private boolean i;
    private long j = System.currentTimeMillis();
    /* access modifiers changed from: private */
    public boolean k = true;
    private BroadcastReceiver l = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                SmartConfigMainActivity.this.mHandler.sendEmptyMessage(100);
            } else if (action.equals("android.net.wifi.STATE_CHANGE")) {
                Parcelable parcelableExtra = intent.getParcelableExtra(StatType.NETWORKINFO);
                if (parcelableExtra != null) {
                    Message obtainMessage = SmartConfigMainActivity.this.mHandler.obtainMessage();
                    obtainMessage.what = 101;
                    obtainMessage.obj = (NetworkInfo) parcelableExtra;
                    SmartConfigMainActivity.this.mHandler.sendMessage(obtainMessage);
                }
            } else if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                SmartConfigMainActivity.this.mHandler.sendEmptyMessage(102);
            }
        }
    };
    SmartConfigStep.StepListener mListener = new SmartConfigStep.StepListener() {
        public void a() {
            if (!SmartConfigMainActivity.this.b.isEmpty()) {
                SmartConfigMainActivity.this.b.pop();
            }
            if (SmartConfigMainActivity.this.b.empty()) {
                SmartConfigMainActivity.this.switchToStep(SmartConfigMainActivity.this.c);
                SmartConfigStep.Step unused = SmartConfigMainActivity.this.c = null;
                return;
            }
            SmartConfigMainActivity.this.resumeStep((SmartConfigStep) SmartConfigMainActivity.this.b.peek());
        }

        public Handler b() {
            return SmartConfigMainActivity.this.mHandler;
        }

        public void a(SmartConfigStep.Step step, SmartConfigStep.Step step2) {
            if (!SmartConfigMainActivity.this.b.isEmpty()) {
                SmartConfigMainActivity.this.b.pop();
            }
            SmartConfigMainActivity.this.switchToStep(step2);
        }

        public void b(SmartConfigStep.Step step, SmartConfigStep.Step step2) {
            SmartConfigMainActivity.this.switchToStep(step2);
        }

        public void a(boolean z) {
            if (SmartConfigMainActivity.this.b.size() > 0) {
                SmartConfigStep smartConfigStep = (SmartConfigStep) SmartConfigMainActivity.this.b.peek();
                if (!smartConfigStep.ak || (smartConfigStep instanceof ChooseWifiStep) || (smartConfigStep instanceof ChooseWifiStepV2) || (smartConfigStep instanceof CameraResetStep) || (smartConfigStep instanceof QRCameraStep) || (smartConfigStep instanceof XiaofangResetStep)) {
                    boolean unused = SmartConfigMainActivity.this.k = false;
                }
            }
            if (!SmartConfigMainActivity.this.b.isEmpty()) {
                SmartConfigMainActivity.this.b.pop();
            }
            Intent intent = new Intent();
            intent.putExtra(Constants.Event.FINISH, z);
            SmartConfigMainActivity.this.setResult(-1, intent);
            SmartConfigMainActivity.this.finish();
        }
    };

    public void handleMessage(Message message) {
        if (!this.b.empty()) {
            this.b.peek().a(message);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!CoreApi.a().q()) {
            if (this.h == null) {
                this.h = SHApplication.showLoginDialog(this, true);
                this.h.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialogInterface) {
                        boolean unused = SmartConfigMainActivity.this.k = false;
                        SmartConfigMainActivity.this.finish();
                    }
                });
                this.h.setCanceledOnTouchOutside(false);
            } else if (!this.h.isShowing()) {
                this.h.show();
            }
        }
        if (this.d == 16) {
            WifiScanManager.a().b();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.d == 16) {
            CoreApi.a().L();
        }
    }

    /* access modifiers changed from: package-private */
    public void switchToStep(SmartConfigStep.Step step) {
        if (step == null) {
            this.k = false;
            finish();
            return;
        }
        LogUtilGrey.a("bindDevice", "SmartConfigMainActivity.switchToStep step:" + step + "  mStrategyIndex:" + this.d);
        this.f22324a.removeAllViews();
        SmartConfigStep b2 = SmartConfigStep.b(step);
        if ((b2 instanceof CameraResetStep) || (b2 instanceof ChooseWifiStep) || (b2 instanceof DeviceInfoStep) || (b2 instanceof ScanQRPrepareStep) || (b2 instanceof XiaofangResetStep)) {
            this.i = false;
        } else if (!this.i) {
            SmartConfigDataProvider a2 = SmartConfigDataProvider.a();
            long currentTimeMillis = System.currentTimeMillis();
            this.j = currentTimeMillis;
            a2.b(SmartConfigDataProvider.ac, Long.valueOf(currentTimeMillis));
            this.i = true;
        }
        if (b2 != null) {
            b2.a(this.mListener);
            b2.b((Context) this);
            if (b2.z() != null) {
                this.f22324a.addView(b2.z());
                this.b.push(b2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void resumeStep(SmartConfigStep smartConfigStep) {
        this.f22324a.removeAllViews();
        smartConfigStep.B();
        if (smartConfigStep.z() != null) {
            this.f22324a.addView(smartConfigStep.z());
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = System.currentTimeMillis();
        setContentView(R.layout.smart_config_main_layout);
        STAT.i.a();
        this.f22324a = (FrameLayout) findViewById(R.id.main_container);
        DeviceObserveService.getInstance().onStartSmartConfig((String) null);
        prepareConfigData();
        registerListener();
        startFirstPage();
        HomeKeyManager.a().a((BaseActivity) this);
    }

    public void onBackPressed() {
        if (!(!this.b.isEmpty() ? this.b.peek().a() : false)) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        unregisterListener();
        if (this.b.size() > 0) {
            SmartConfigStep peek = this.b.peek();
            if (!peek.ak || (peek instanceof ChooseWifiStep) || (peek instanceof ChooseWifiStepV2) || (peek instanceof CameraResetStep) || (peek instanceof QRCameraStep) || (peek instanceof XiaofangResetStep)) {
                this.k = false;
            }
        }
        while (!this.b.isEmpty()) {
            this.b.pop().C();
        }
        DeviceObserveService.getInstance().onFinishSmartConfig((String) null, true);
        HomeKeyManager.a().b((BaseActivity) this);
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (!TextUtils.isEmpty(str) || this.i) {
            STAT.i.a(str, this.f);
            if (!"fail".equalsIgnoreCase((String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.aa, "fail"))) {
                String d2 = SmartConfigDataProvider.a().d();
                if (!TextUtils.isEmpty(d2)) {
                    STAT.i.a(d2.getBytes().length);
                }
            } else if (this.k && NetworkUtils.c()) {
                STAT.i.b(str, System.currentTimeMillis() - this.j);
            }
        }
        DEVICE_FROM = 0;
        DEVICE_FROM_APP_PLUS_TYPE = 6;
    }

    /* access modifiers changed from: package-private */
    public void registerListener() {
        registerReceiver(this.l, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        registerReceiver(this.l, new IntentFilter("android.net.wifi.STATE_CHANGE"));
        registerReceiver(this.l, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
    }

    /* access modifiers changed from: package-private */
    public void unregisterListener() {
        unregisterReceiver(this.l);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x023a, code lost:
        if (com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a() != false) goto L_0x023c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0285  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x028e  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0297  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void prepareConfigData() {
        /*
            r12 = this;
            android.content.Intent r0 = r12.getIntent()
            r1 = 0
            if (r0 == 0) goto L_0x02de
            java.lang.String r2 = "resume_strategy"
            boolean r2 = r0.getBooleanExtra(r2, r1)
            r12.e = r2
            java.lang.String r2 = "goto_error_page"
            boolean r2 = r0.getBooleanExtra(r2, r1)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r3 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r4 = "goto_error_page"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
            r3.b(r4, r2)
            java.lang.String r2 = "strategy_id"
            r3 = -1
            int r2 = r0.getIntExtra(r2, r3)
            r12.d = r2
            java.lang.String r2 = "use_reset_page"
            r4 = 1
            boolean r2 = r0.getBooleanExtra(r2, r4)
            r12.g = r2
            boolean r2 = r12.e
            if (r2 == 0) goto L_0x0039
            return
        L_0x0039:
            java.lang.String r2 = "model"
            java.lang.String r2 = r0.getStringExtra(r2)
            java.lang.String r5 = "bssid"
            java.lang.String r5 = r0.getStringExtra(r5)
            java.lang.String r6 = "scanResult"
            android.os.Parcelable r6 = r0.getParcelableExtra(r6)
            android.net.wifi.ScanResult r6 = (android.net.wifi.ScanResult) r6
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            r7.f()
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "key_strategy_index"
            int r9 = r12.d
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "key_time_start"
            long r9 = r12.j
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "device_ap"
            r7.b(r8, r6)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "device_model"
            r7.b(r8, r2)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "target_bssid"
            r7.b(r8, r5)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "target_passwd"
            java.lang.String r9 = "password"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "wifi_ssid"
            java.lang.String r9 = "ssid"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "bind_device_did"
            java.lang.String r9 = "did"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "bind_device_token"
            java.lang.String r9 = "token"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "bind_device_sn"
            java.lang.String r9 = "sn"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "bind_device_timestamp"
            java.lang.String r9 = "timestamp"
            r10 = 0
            long r9 = r0.getLongExtra(r9, r10)
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "bind_device_key"
            java.lang.String r9 = "bind_key"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "scan_qr_code"
            java.lang.String r9 = "scan_qr_code"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "gateway_did"
            java.lang.String r9 = "gateway_did"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "aiot_wifi"
            java.lang.String r9 = "aiot_wifi"
            android.os.Parcelable r9 = r0.getParcelableExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "combo_ble_mac"
            java.lang.String r9 = "combo_ble_mac"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "combo_ble_key"
            java.lang.String r9 = "combo_ble_key"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "mi_router_info"
            android.content.Intent r9 = r12.getIntent()
            java.lang.String r10 = "mi_router_info"
            android.os.Parcelable r9 = r9.getParcelableExtra(r10)
            r7.b(r8, r9)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "camera_qrcode_oob"
            android.content.Intent r9 = r12.getIntent()
            java.lang.String r10 = "key_qrcode_oob"
            java.lang.String r9 = r9.getStringExtra(r10)
            r7.b(r8, r9)
            if (r2 != 0) goto L_0x018c
            if (r6 == 0) goto L_0x018c
            java.lang.String r2 = com.xiaomi.smarthome.device.DeviceFactory.a((android.net.wifi.ScanResult) r6)
            java.lang.String r7 = "none"
            boolean r7 = r2.equals(r7)
            if (r7 != 0) goto L_0x018c
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r7 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r8 = "device_model"
            java.lang.String r9 = "model"
            java.lang.String r9 = r0.getStringExtra(r9)
            r7.b(r8, r9)
        L_0x018c:
            if (r5 != 0) goto L_0x0192
            if (r6 == 0) goto L_0x0192
            java.lang.String r5 = r6.BSSID
        L_0x0192:
            com.xiaomi.smarthome.smartconfig.DeviceBindStatis.a((android.content.Context) r12, (java.lang.String) r2, (java.lang.String) r5)
            java.lang.String r2 = "category"
            int r2 = r0.getIntExtra(r2, r1)
            if (r2 <= 0) goto L_0x01b2
            if (r2 != r4) goto L_0x01b0
            com.xiaomi.smarthome.stat.StatClick r5 = com.xiaomi.smarthome.stat.STAT.d
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r6 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r7 = "device_model"
            java.lang.Object r6 = r6.a(r7)
            java.lang.String r6 = (java.lang.String) r6
            r5.R(r6)
        L_0x01b0:
            DEVICE_FROM = r2
        L_0x01b2:
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r5 = "selected_ap"
            java.lang.String r6 = "select_scan_result"
            android.os.Parcelable r6 = r0.getParcelableExtra(r6)
            r2.b(r5, r6)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r5 = "selected_ap_ssid"
            java.lang.String r6 = "select_ssid"
            java.lang.String r6 = r0.getStringExtra(r6)
            r2.b(r5, r6)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r5 = "selected_ap_passwd"
            java.lang.String r6 = "select_password"
            java.lang.String r6 = r0.getStringExtra(r6)
            r2.b(r5, r6)
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r5 = "from_miui"
            java.lang.String r6 = "from_miui"
            boolean r6 = r0.getBooleanExtra(r6, r1)
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
            r2.b(r5, r6)
            int r2 = r12.d
            if (r2 != r3) goto L_0x01fc
            r12.k = r1
            r12.finish()
            return
        L_0x01fc:
            java.util.GregorianCalendar r1 = new java.util.GregorianCalendar
            r1.<init>()
            java.util.TimeZone r1 = r1.getTimeZone()
            int r1 = r1.getRawOffset()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.SECONDS
            long r5 = (long) r1
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r1 = r2.convert(r5, r1)
            int r1 = (int) r1
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r2 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r3 = "gmt_offset"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r1)
            r2.b(r3, r5)
            java.util.TimeZone r2 = java.util.TimeZone.getDefault()
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r3 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r5 = "gmt_tz"
            java.lang.String r2 = r2.getID()
            r3.b(r5, r2)
            r2 = 0
            java.lang.Class r3 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.b()     // Catch:{ Error -> 0x023c }
            boolean r5 = com.xiaomi.smarthome.wificonfig.WifiSettingUtils.a()     // Catch:{ Error -> 0x023d }
            if (r5 == 0) goto L_0x023d
        L_0x023c:
            r3 = r2
        L_0x023d:
            com.xiaomi.smarthome.frame.core.CoreApi r5 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r5 = r5.D()
            if (r5 == 0) goto L_0x025a
            com.xiaomi.smarthome.frame.core.CoreApi r3 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.frame.server_compact.ServerBean r3 = r3.F()
            if (r3 != 0) goto L_0x0254
            java.lang.String r3 = ""
            goto L_0x0256
        L_0x0254:
            java.lang.String r3 = r3.f1530a
        L_0x0256:
            com.xiaomi.miio.MiioLocalAPI.a((java.lang.String) r3)
            r3 = r2
        L_0x025a:
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r5 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r6 = "scan_qr_model"
            java.lang.String r7 = "scan_device_model"
            java.lang.String r0 = r0.getStringExtra(r7)
            r5.b(r6, r0)
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r0 = r0.s()
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            long r5 = r0.longValue()
            r7 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x0281
            r3 = r2
        L_0x0281:
            boolean r0 = com.xiaomi.smarthome.library.common.ApiHelper.e
            if (r0 == 0) goto L_0x0286
            r3 = r2
        L_0x0286:
            int r0 = r12.d
            boolean r0 = com.xiaomi.smarthome.smartconfig.stage.ConfigStage.d(r0)
            if (r0 != 0) goto L_0x028f
            r3 = r2
        L_0x028f:
            r0 = 28800(0x7080, float:4.0357E-41)
            if (r1 == r0) goto L_0x0294
            goto L_0x0295
        L_0x0294:
            r2 = r3
        L_0x0295:
            if (r2 == 0) goto L_0x02a0
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r0 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r1 = "miui_class"
            r0.b(r1, r2)
        L_0x02a0:
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r0 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r1 = "device_model"
            java.lang.Object r0 = r0.a(r1)
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "yunyi.camera.v1"
            boolean r1 = r1.equals(r0)
            if (r1 != 0) goto L_0x02bc
            java.lang.String r1 = "yunyi.camera.v2"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x02c9
        L_0x02bc:
            com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider r0 = com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider.a()
            java.lang.String r1 = "user_qr_code"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r4)
            r0.b(r1, r2)
        L_0x02c9:
            android.content.Context r0 = r12.getContext()
            com.xiaomi.smarthome.framework.statistic.MiStatType r1 = com.xiaomi.smarthome.framework.statistic.MiStatType.CLICK
            java.lang.String r1 = r1.getValue()
            java.lang.String r2 = "connect_ap_all"
            com.umeng.analytics.MobclickAgent.a((android.content.Context) r0, (java.lang.String) r1, (java.lang.String) r2)
            java.lang.String r0 = "connect_ap_all"
            com.xiaomi.smarthome.framework.statistic.StatHelper.d((java.lang.String) r0)
            goto L_0x02e3
        L_0x02de:
            r12.k = r1
            r12.finish()
        L_0x02e3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity.prepareConfigData():void");
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (!this.b.isEmpty()) {
            this.b.peek().a(i2, i3, intent);
        }
    }

    /* access modifiers changed from: package-private */
    public void startFirstPage() {
        if (this.e) {
            switchToStep(ConfigStage.c(this.d));
            return;
        }
        this.c = ConfigStage.c(this.d);
        if (this.c == null) {
            this.k = false;
            finish();
            return;
        }
        DeviceFinder.a().g();
        PluginRecord d2 = CoreApi.a().d(getIntent().getStringExtra("model"));
        if (d2 != null && d2.c().e() == 4 && SmartConfigDataProvider.a().a(SmartConfigDataProvider.c) != null && SmartConfigDataProvider.a().a(SmartConfigDataProvider.d) != null && SmartConfigDataProvider.a().a(SmartConfigDataProvider.e) != null && SmartConfigDataProvider.a().a(SmartConfigDataProvider.h) != null) {
            switchToStep(SmartConfigStep.Step.STEP_AP_CONFIG_STEP);
        } else if (ConfigStage.a(this.d) || (d2 != null && d2.c().e() == 4 && !this.g)) {
            if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.u, false)).booleanValue()) {
                switchToStep(SmartConfigStep.Step.STEP_DEVICE_INFO);
            } else if (((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.f22323a, false)).booleanValue()) {
                switchToStep(SmartConfigStep.Step.STEP_GET_ROUTER_INFO);
            } else if (SmartConfigDataProvider.a().a(SmartConfigDataProvider.b) == null) {
                switchToStep(SmartConfigStep.Step.STEP_CHOOSE_WIFI);
            } else {
                switchToStep(this.c);
                this.c = null;
            }
        } else if (ConfigStage.b(this.d) != null && this.g) {
            switchToStep(ConfigStage.b(this.d));
        } else if (ConfigStage.c(this.d) != null) {
            switchToStep(this.c);
            this.c = null;
        } else {
            this.k = false;
            finish();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }
}
