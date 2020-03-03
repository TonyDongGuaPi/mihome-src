package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import com.xiaomi.qrcode.QrCodeParser;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.ArrayList;
import java.util.List;

public class NbiotStep extends ConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22612a = "NbiotStep";
    private static final byte ap = 4;
    private static final byte aq = 5;
    private static final byte ar = 6;
    private static final byte as = 7;
    private static final int b = 263;
    private static final int c = 264;
    private static final int d = 0;
    private static final byte j = 1;
    private static final byte k = 2;
    private static final byte l = 3;
    private int e;
    /* access modifiers changed from: private */
    public int f;
    private QrCodeParser g;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public byte i;

    public SmartConfigStep.Step f() {
        return null;
    }

    public void k() {
    }

    static /* synthetic */ int a(NbiotStep nbiotStep) {
        int i2 = nbiotStep.f;
        nbiotStep.f = i2 + 1;
        return i2;
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 30000;
        arrayList.add(configTime);
        return arrayList;
    }

    public void a(Context context) {
        BluetoothLog.c("NbiotStep is onCreateStep");
        this.g = new QrCodeParser((String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.D));
        super.a(context);
    }

    public void a(Message message) {
        switch (message.what) {
            case 263:
                a(this.g);
                return;
            case 264:
                a(this.h, this.g);
                return;
            default:
                super.a(message);
                return;
        }
    }

    public void c(int i2) {
        this.e = i2;
        if (this.e == 0) {
            this.mCommonBindView.setCurrentIndex(0);
            if (this.g.b() == QrCodeParser.QrCodeType.NBIOT) {
                a(this.g);
            } else {
                d_(false);
            }
        }
    }

    private void a(final QrCodeParser qrCodeParser) {
        LogUtilGrey.a(f22612a, "response requestBindKey:" + qrCodeParser);
        CameraApi.getInstance().getBindKey(this.af.getApplicationContext(), new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                int unused = NbiotStep.this.f = 0;
                String unused2 = NbiotStep.this.h = str;
                NbiotStep.this.a(str, qrCodeParser);
            }

            public void onFailure(Error error) {
                if (NbiotStep.a(NbiotStep.this) < 3) {
                    NbiotStep.this.U_().sendEmptyMessageDelayed(263, 3000);
                    return;
                }
                byte unused = NbiotStep.this.i = (byte) 1;
                NbiotStep.this.u();
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r9, com.xiaomi.qrcode.QrCodeParser r10) {
        /*
            r8 = this;
            java.lang.String r0 = "HmacSHA256"
            javax.crypto.Mac r0 = javax.crypto.Mac.getInstance(r0)     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            javax.crypto.spec.SecretKeySpec r1 = new javax.crypto.spec.SecretKeySpec     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            java.lang.String r2 = r10.f()     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            byte[] r2 = r2.getBytes()     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            java.lang.String r3 = "HmacSHA256"
            r1.<init>(r2, r3)     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            r0.init(r1)     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            byte[] r1 = r9.getBytes()     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            byte[] r0 = r0.doFinal(r1)     // Catch:{ NoSuchAlgorithmException -> 0x002a, Exception -> 0x0021 }
            goto L_0x0033
        L_0x0021:
            r0 = move-exception
            java.lang.String r1 = "NbiotStep"
            java.lang.String r2 = "fatal"
            android.util.Log.e(r1, r2, r0)
            goto L_0x0032
        L_0x002a:
            r0 = move-exception
            java.lang.String r1 = "NbiotStep"
            java.lang.String r2 = "fatal"
            android.util.Log.e(r1, r2, r0)
        L_0x0032:
            r0 = 0
        L_0x0033:
            if (r0 == 0) goto L_0x0055
            int r1 = r0.length
            if (r1 != 0) goto L_0x0039
            goto L_0x0055
        L_0x0039:
            com.xiaomi.smarthome.device.api.DeviceApi r2 = com.xiaomi.smarthome.device.api.DeviceApi.getInstance()
            android.content.Context r1 = r8.af
            android.content.Context r3 = r1.getApplicationContext()
            java.lang.String r4 = r10.e()
            java.lang.String r6 = com.xiaomi.smarthome.library.common.util.ByteUtils.d(r0)
            com.xiaomi.smarthome.smartconfig.step.NbiotStep$2 r7 = new com.xiaomi.smarthome.smartconfig.step.NbiotStep$2
            r7.<init>()
            r5 = r9
            r2.bindNbIotDevice(r3, r4, r5, r6, r7)
            goto L_0x005b
        L_0x0055:
            r9 = 5
            r8.i = r9
            r8.u()
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.smartconfig.step.NbiotStep.a(java.lang.String, com.xiaomi.qrcode.QrCodeParser):void");
    }

    /* access modifiers changed from: private */
    public void a(final String str) {
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
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, b2);
                }
                NbiotStep.this.a(0);
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                NbiotStep.this.a(0);
            }
        });
    }

    public SmartConfigStep.Step b(int i2) {
        BluetoothLog.c(String.format("onStageTimeOut index = %d", new Object[]{Integer.valueOf(i2)}));
        if (i2 != 0) {
            return null;
        }
        byte b2 = this.i;
        if (b2 == 2) {
            a((int) R.string.ble_new_auth_step_failed, (int) R.string.binding_scurity_nodevice, (int) R.drawable.common_bind_nodevice);
        } else if (b2 != 7) {
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_auth_step_success, (int) R.string.keep_phone_wifi_connect);
            this.mCommonBindView.addNextStep((int) R.string.ble_new_bind_step_loading, (int) R.string.keep_phone_wifi_connect);
            a((int) R.string.device_bind_fail, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_device_has_binded_failed);
        } else {
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_auth_step_success, (int) R.string.keep_phone_wifi_connect);
            this.mCommonBindView.addNextStep((int) R.string.ble_new_bind_step_loading, (int) R.string.keep_phone_wifi_connect);
            a((int) R.string.ble_new_bind_step_failed, (int) R.string.verifying_qrcode_same_device, (int) R.drawable.common_bind_device_has_binded_failed);
        }
        a(false, 0, (SmartConfigStep.Step) null);
        return null;
    }

    public int b() {
        BluetoothLog.c(String.format("getCurrentStageIndex return 0", new Object[0]));
        return 0;
    }

    public void e() {
        BluetoothLog.c(String.format("onFinishStep QRCodeStep", new Object[0]));
        U_().removeMessages(124);
        DeviceFinder.a().b();
        super.e();
    }

    public void h() {
        super.h();
        this.mCommonBindView.addNextStep((int) R.string.binding_device_scurity, (int) R.string.keep_phone_wifi_connect);
        this.mCommonBindView.startProgressAnimation(4);
    }

    public void i() {
        super.i();
        if (this.e == 0) {
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.ble_new_auth_step_success, (int) R.string.keep_phone_wifi_connect);
            this.mCommonBindView.addNextStep((int) R.string.ble_new_bind_step_loading, (int) R.string.keep_phone_wifi_connect);
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.device_bind_success, (int) R.string.keep_phone_wifi_connect);
        }
    }

    public void j() {
        super.j();
        this.mCommonBindView.addNextStep((int) R.string.binding_device_scurity, (int) R.string.keep_phone_wifi_connect);
        this.mCommonBindView.startProgressAnimation(4);
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    NbiotStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
