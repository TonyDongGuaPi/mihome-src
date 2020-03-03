package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.statcode.ApErrorCode;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class QRScanStep extends ConfigStep {
    private static int c = 0;
    private static long d = 60000;

    /* renamed from: a  reason: collision with root package name */
    private String f22664a;
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public boolean e = false;

    public int b() {
        return 0;
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void j() {
    }

    public void k() {
    }

    public void a(Context context) {
        this.f22664a = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.z);
        String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.A);
        if (!TextUtils.isEmpty(str) && str.startsWith(this.f22664a)) {
            try {
                d = Math.min(Long.parseLong(str.split(",")[1]) * 1000, 120000);
            } catch (Exception e2) {
                Log.e("QRScanStep", Log.getStackTraceString(e2));
            }
        }
        if (d < 60000) {
            d = 60000;
        }
        this.b = (String) SmartConfigDataProvider.a().a("device_model");
        super.a(context);
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.b = d;
        configTime.f22586a = c;
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        arrayList.add(configTime);
        return arrayList;
    }

    public void a(Message message) {
        if (message.what != 122) {
            super.a(message);
        } else {
            o();
        }
    }

    public void e() {
        super.e();
    }

    public void d() {
        super.d();
        Handler U_ = U_();
        if (U_ != null) {
            U_.removeMessages(122);
        }
    }

    public SmartConfigStep.Step b(int i) {
        if (!this.al && this.ak && NetworkUtils.c()) {
            this.al = true;
            STAT.c.a(0, this.b, 3101, "");
        }
        w();
        a(false, 0, (SmartConfigStep.Step) null);
        p();
        return null;
    }

    public void c(int i) {
        BluetoothLog.c(String.format("startConnection bindKey = %s", new Object[]{this.f22664a}));
        String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.L);
        if (!TextUtils.isEmpty(str)) {
            a(str);
            return;
        }
        Handler U_ = U_();
        if (U_ != null) {
            U_.sendEmptyMessage(122);
        }
    }

    /* access modifiers changed from: package-private */
    public void o() {
        BluetoothLog.c(String.format("checkBindKey bindKey = %s", new Object[]{this.f22664a}));
        CameraApi.getInstance().checkBindKey(this.af, this.f22664a, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Context context = QRScanStep.this.af;
                if (context != null) {
                    if (!(context instanceof CommonActivity) || ((CommonActivity) context).isValid()) {
                        BluetoothLog.c(String.format("onSuccess %s", new Object[]{jSONObject}));
                        int optInt = jSONObject.optInt("ret");
                        if (optInt == 0) {
                            int optInt2 = jSONObject.optInt("check_after");
                            if ("chuangmi.camera.ipc009".equals(QRScanStep.this.b)) {
                                optInt2 = 5;
                            }
                            if (QRScanStep.this.U_() == null) {
                                return;
                            }
                            if (optInt2 > 0) {
                                QRScanStep.this.U_().removeMessages(122);
                                QRScanStep.this.U_().sendEmptyMessageDelayed(122, (long) (optInt2 * 1000));
                                return;
                            }
                            QRScanStep.this.U_().removeMessages(122);
                            QRScanStep.this.U_().sendEmptyMessageDelayed(122, 2000);
                        } else if (optInt == 1) {
                            String a2 = QRScanStep.this.b(jSONObject.optString("bind_did"));
                            boolean unused = QRScanStep.this.e = true;
                            QRScanStep.this.a(a2);
                        } else if (optInt == -2) {
                            QRScanStep.this.ak = false;
                            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(context);
                            builder.b((int) R.string.kuailian_falied_bindkey_invalide);
                            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    int intValue = ((Integer) SmartConfigDataProvider.a().a(SmartConfigDataProvider.ab, -1)).intValue();
                                    BluetoothLog.c(String.format("strategy idx = %d", new Object[]{Integer.valueOf(intValue)}));
                                    QRScanStep.this.a(intValue == 9 ? SmartConfigStep.Step.STEP_QR_CAMERA : SmartConfigStep.Step.STEP_QR_CONFIG);
                                }
                            });
                            builder.d();
                        } else if (optInt == -3) {
                            QRScanStep.this.ak = false;
                            QRScanStep.this.a(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
                        } else if (QRScanStep.this.U_() != null) {
                            int i = 2000;
                            if ("chuangmi.camera.ipc009".equals(QRScanStep.this.b)) {
                                i = 5000;
                            }
                            QRScanStep.this.U_().removeMessages(122);
                            QRScanStep.this.U_().sendEmptyMessageDelayed(122, (long) i);
                        }
                    }
                }
            }

            public void onFailure(Error error) {
                Context context = QRScanStep.this.af;
                if (context != null) {
                    if (!(context instanceof CommonActivity) || ((CommonActivity) context).isValid()) {
                        BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                        Handler U_ = QRScanStep.this.U_();
                        if (U_ != null) {
                            U_.removeMessages(122);
                            U_.sendEmptyMessageDelayed(122, 2000);
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String b(String str) {
        if ("yunyi.camera.mj1".equals(this.b) || !this.b.contains("yunyi.camera") || str.startsWith("yunyi.")) {
            return str;
        }
        return "yunyi." + str;
    }

    /* access modifiers changed from: package-private */
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
                    QRScanStep.this.a(0);
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                QRScanStep.this.a(0);
            }
        });
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (System.currentTimeMillis() - QRScanStep.this.ai < 30000) {
                        QRScanStep.this.ak = false;
                    }
                    if (!QRScanStep.this.al && QRScanStep.this.ak && NetworkUtils.c()) {
                        QRScanStep.this.al = true;
                        STAT.c.a(0, QRScanStep.this.b, (int) ApErrorCode.i, "");
                    }
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    QRScanStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }

    public void h() {
        this.mCommonBindView.addNextStep((int) R.string.kuailian_device_connect_wifi, (int) R.string.make_device_near_router);
        this.mCommonBindView.startProgressAnimation(1);
    }

    public void i() {
        this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_device_connect_wifi_success, (int) R.string.make_device_near_router);
    }

    private void p() {
        if (!NetworkUtils.c()) {
            b((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_app_connect_network_failed);
        } else {
            b((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.make_device_near_router, (int) R.drawable.common_bind_device_connect_network_failed);
        }
    }
}
