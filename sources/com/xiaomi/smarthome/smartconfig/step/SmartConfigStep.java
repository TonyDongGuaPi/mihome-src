package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.login.util.LoginUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.wificonfig.WifiDeviceFinder;
import com.xiaomi.smarthome.wificonfig.WifiSettingUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public abstract class SmartConfigStep {
    public static final int A = 101;
    public static final int B = 102;
    public static final int C = 103;
    public static final int D = 106;
    public static final int E = 104;
    public static final int F = 107;
    public static final int G = 108;
    public static final int H = 109;
    public static final int I = 110;
    public static final int J = 111;
    public static final int K = 112;
    public static final int L = 113;
    public static final int M = 114;
    public static final int N = 115;
    public static final int O = 116;
    public static final int P = 117;
    public static final int Q = 118;
    public static final int R = 119;
    public static final int S = 120;
    public static final int T = 121;
    public static final int U = 122;
    public static final int V = 123;
    public static final int W = 124;
    public static final int X = 125;
    public static final int Y = 126;
    public static final int Z = 127;

    /* renamed from: a  reason: collision with root package name */
    private static HashMap<Step, Class> f22685a = new HashMap<>();
    public static final int aa = 128;
    public static final int ab = 129;
    public static final int ac = 130;
    public static final int ad = 132;
    public static final int z = 100;
    protected View ae;
    protected Context af;
    protected boolean ag = false;
    protected boolean ah = false;
    protected long ai;
    protected String aj;
    public boolean ak = true;
    protected boolean al = false;
    protected boolean am = false;
    protected String an = "";
    protected ScanResult ao;
    private StepListener b;

    public enum Step {
        STEP_DEVICE_INFO,
        STEP_GET_ROUTER_INFO,
        STEP_CHOOSE_WIFI,
        STEP_SMART_CONFIG_STEP,
        STEP_AP_CONFIG_STEP,
        STEP_CONNECT_AP_ERROR,
        STEP_CONNECT_AP_ERROR2,
        STEP_CONNECT_ROUTER_ERROR,
        STEP_SEND_ROUTER_INFO_ERROR,
        STEP_SEND_ROUTER_INFO_FINAL_ERROR,
        STEP_CONNECT_SELECTED_AP_FAILED,
        STEP_DOWNLOAD_PLUGIN_FAILED,
        STEP_FIND_DEVICE_FAILED,
        STEP_FIND_DEVICE_FAILED_ERROR,
        STEP_NORMAL_FAILED_STEP,
        STEP_SUCCESS,
        STEP_QR_SCAN,
        STEP_QR_CONFIG,
        STEP_QR_HELP,
        STEP_QR_CAMERA,
        STEP_SW_CAMERA,
        STEP_NBIOT,
        STEP_XIAOFANG_RESET,
        STEP_CAMERA_RESET,
        STEP_SCAN_QR,
        STEP_PREPARE_SCAN_QR,
        STEP_SCAN_QR_FAIL_BIND,
        STEP_SCAN_QR_FAIL_FIND,
        STEP_BLE_COMBO_CONFIG,
        STEP_COMBO_BLE_WAY_CONFIG,
        STEP_BLE_PWD_ERROR,
        STEP_BLE_SEND_ERROR,
        STEP_BLE_CONNECT_ERROR,
        STEP_AP_DEVICE_CONFIG_PASSWD_CHOOSER,
        STEP_AP_DEVICE_CONFIG_STEP,
        STEP_BIND_KEY,
        STEP_AP_DEVICE_USER_ERROR,
        STEP_AP_INPUT_SSID_PASSWD_CONFIG,
        STEP_XIAOXUN_ERROR,
        STEP_BIND_DEVICE_FROM_THIRD_APP,
        STEP_BIND_BY_OTHER_ERROR,
        STEP_GATEWAY_SUBDEVICE,
        STEP_APSECURE_CONFIG_STEP,
        STEP_APSECURE_PIN_STEP,
        STEP_PUSH_NEWDEVICE
    }

    public interface StepListener {
        void a();

        void a(Step step, Step step2);

        void a(boolean z);

        Handler b();

        void b(Step step, Step step2);
    }

    public void a(int i, int i2, Intent intent) {
    }

    public abstract void a(Context context);

    public abstract void a(Message message);

    public boolean a() {
        return false;
    }

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract Step f();

    static {
        f22685a.put(Step.STEP_GET_ROUTER_INFO, RouterInfoStep.class);
        f22685a.put(Step.STEP_DEVICE_INFO, DeviceInfoStep.class);
        f22685a.put(Step.STEP_CHOOSE_WIFI, ChooseWifiStepV2.class);
        f22685a.put(Step.STEP_AP_CONFIG_STEP, ApConfigStep.class);
        f22685a.put(Step.STEP_CONNECT_AP_ERROR, ApConnectFailedStep.class);
        f22685a.put(Step.STEP_CONNECT_AP_ERROR2, ApConnectFailed2Step.class);
        f22685a.put(Step.STEP_CONNECT_ROUTER_ERROR, ConnectRouterErrorStep.class);
        f22685a.put(Step.STEP_SEND_ROUTER_INFO_ERROR, PasswdSendFailedStep.class);
        f22685a.put(Step.STEP_SEND_ROUTER_INFO_FINAL_ERROR, PasswdSendFinalFailedStep.class);
        f22685a.put(Step.STEP_NORMAL_FAILED_STEP, NormalFailedStep.class);
        f22685a.put(Step.STEP_CONNECT_SELECTED_AP_FAILED, ConnectSelectApFailedStep.class);
        f22685a.put(Step.STEP_FIND_DEVICE_FAILED, FindDeviceFailedStep.class);
        f22685a.put(Step.STEP_FIND_DEVICE_FAILED_ERROR, FindDeviceFailedFinalStep.class);
        f22685a.put(Step.STEP_SUCCESS, SuccessStep.class);
        f22685a.put(Step.STEP_DOWNLOAD_PLUGIN_FAILED, DownloadPluginFailedStep.class);
        f22685a.put(Step.STEP_QR_CONFIG, QRConfigStep.class);
        f22685a.put(Step.STEP_QR_CAMERA, QRCameraStep.class);
        f22685a.put(Step.STEP_SW_CAMERA, SWCameraStep.class);
        f22685a.put(Step.STEP_QR_SCAN, QRScanStep.class);
        f22685a.put(Step.STEP_QR_HELP, QRHelpStep.class);
        f22685a.put(Step.STEP_PREPARE_SCAN_QR, ScanQRPrepareStep.class);
        f22685a.put(Step.STEP_SCAN_QR_FAIL_BIND, ScanQRBindFailStep.class);
        f22685a.put(Step.STEP_SCAN_QR_FAIL_FIND, QRScanFindFailedStep.class);
        f22685a.put(Step.STEP_SCAN_QR, QRCodeStep.class);
        f22685a.put(Step.STEP_BLE_COMBO_CONFIG, BleComboStep.class);
        f22685a.put(Step.STEP_COMBO_BLE_WAY_CONFIG, BleComboBleWayStep.class);
        f22685a.put(Step.STEP_BLE_PWD_ERROR, BlePwdErrorStep.class);
        f22685a.put(Step.STEP_BLE_SEND_ERROR, BleErrorStep.class);
        f22685a.put(Step.STEP_AP_DEVICE_CONFIG_PASSWD_CHOOSER, ApDevicePasswdChooseStep.class);
        f22685a.put(Step.STEP_AP_DEVICE_CONFIG_STEP, ApDeviceConfigStep.class);
        f22685a.put(Step.STEP_XIAOFANG_RESET, XiaofangResetStep.class);
        f22685a.put(Step.STEP_CAMERA_RESET, CameraResetStep.class);
        f22685a.put(Step.STEP_BIND_KEY, BindKeyStep.class);
        f22685a.put(Step.STEP_AP_DEVICE_USER_ERROR, ApDeviceUserErrorStep.class);
        f22685a.put(Step.STEP_AP_INPUT_SSID_PASSWD_CONFIG, WifiExtApPasswdInputStep.class);
        f22685a.put(Step.STEP_XIAOXUN_ERROR, XiaoxunErrorStep.class);
        f22685a.put(Step.STEP_BIND_DEVICE_FROM_THIRD_APP, ThirdAppBindStep.class);
        f22685a.put(Step.STEP_BIND_BY_OTHER_ERROR, HaveBindByOtherErrorStep.class);
        f22685a.put(Step.STEP_NBIOT, NbiotStep.class);
        f22685a.put(Step.STEP_GATEWAY_SUBDEVICE, GatewaySubdeviceStep.class);
        f22685a.put(Step.STEP_APSECURE_CONFIG_STEP, ApSecureConfigStep.class);
        f22685a.put(Step.STEP_APSECURE_PIN_STEP, APSecurePinStep.class);
        f22685a.put(Step.STEP_PUSH_NEWDEVICE, PushNewDeviceStep.class);
    }

    public static SmartConfigStep b(Step step) {
        Class cls = f22685a.get(step);
        if (cls == null) {
            return null;
        }
        try {
            return (SmartConfigStep) cls.newInstance();
        } catch (IllegalAccessException | InstantiationException unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void a(Context context, int i) {
        this.ae = LayoutInflater.from(context).inflate(i, (ViewGroup) null);
        ButterKnife.bind((Object) this, this.ae);
    }

    /* access modifiers changed from: protected */
    public void y() {
        this.aj = (String) SmartConfigDataProvider.a().a("device_model", "");
        this.ai = System.currentTimeMillis();
        String b2 = SmartConfigDataProvider.a().b();
        int intValue = ((Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.g, 0)).intValue();
        if ((DeviceFactory.d(b2) && !DeviceFactory.c(this.aj)) || ((!TextUtils.isEmpty(b2) && LoginUtil.a(b2)) || (!TextUtils.equals(this.aj, "xiaomi.repeater.v2") && WifiSettingUtils.a(intValue, 100) <= 60))) {
            this.ak = false;
        }
    }

    public View z() {
        return this.ae;
    }

    public void a(StepListener stepListener) {
        this.b = stepListener;
    }

    public void A() {
        this.b = null;
    }

    public Handler U_() {
        if (this.b != null) {
            return this.b.b();
        }
        return new Handler(Looper.getMainLooper());
    }

    public void b(Context context) {
        this.ag = false;
        this.ah = false;
        this.af = context;
        a(context);
    }

    public void B() {
        this.ah = false;
        c();
    }

    public void c(Step step) {
        if (!this.ah) {
            this.ah = true;
            d();
            this.b.b(f(), step);
        }
    }

    public void C() {
        if (!this.ag) {
            this.ah = true;
            this.ag = true;
            e();
            A();
            this.af = null;
        }
    }

    /* access modifiers changed from: protected */
    public void D() {
        if (!this.ag) {
            this.ah = true;
            this.ag = true;
            e();
            this.af = null;
            this.b.a();
            A();
        }
    }

    /* access modifiers changed from: protected */
    public void a(Step step) {
        if (!this.ag) {
            this.ah = true;
            this.ag = true;
            e();
            this.af = null;
            this.b.a(f(), step);
            A();
        }
    }

    /* access modifiers changed from: protected */
    public void d_(boolean z2) {
        if (!this.ag) {
            this.ah = true;
            this.ag = true;
            e();
            this.af = null;
            this.b.a(z2);
            A();
        }
    }

    public String a(long j) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(String.valueOf((int) (j & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 8) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 16) & 255)));
        stringBuffer.append('.');
        stringBuffer.append(String.valueOf((int) ((j >> 24) & 255)));
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public ScanResult l() {
        if (this.ao == null) {
            this.ao = (ScanResult) SmartConfigDataProvider.a().a(SmartConfigDataProvider.h);
            if (this.ao == null) {
                String str = (String) SmartConfigDataProvider.a().a("wifi_ssid");
                ArrayList<ScanResult> arrayList = WifiDeviceFinder.j;
                if (arrayList != null) {
                    Iterator<ScanResult> it = arrayList.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ScanResult next = it.next();
                        if (WifiSettingUtils.a(next.SSID, str)) {
                            this.ao = next;
                            break;
                        }
                    }
                }
            }
        }
        return this.ao;
    }
}
