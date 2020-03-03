package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.ArrayList;
import java.util.List;

public class QRCodeStep extends ConfigStep {
    private static final int c = 0;

    /* renamed from: a  reason: collision with root package name */
    String f22642a;
    private String b;
    /* access modifiers changed from: private */
    public int d;

    public SmartConfigStep.Step f() {
        return null;
    }

    public void k() {
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 50000;
        arrayList.add(configTime);
        return arrayList;
    }

    public void a(Context context) {
        this.b = (String) SmartConfigDataProvider.a().a("device_model");
        BluetoothLog.c(String.format("model is %s", new Object[]{this.b}));
        super.a(context);
    }

    public void a(Message message) {
        int i = message.what;
        super.a(message);
    }

    private boolean o() {
        return !TextUtils.isEmpty(this.b) && DeviceFactory.r(this.b);
    }

    public void c(int i) {
        String str;
        BluetoothLog.c(String.format("startConnection index = %d, fromXiaoxun = %b", new Object[]{Integer.valueOf(i), Boolean.valueOf(o())}));
        this.d = i;
        if (this.d == 0) {
            this.mCommonBindView.setCurrentIndex(0);
            if (!o() && (str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.D)) != null) {
                if (TextUtils.isEmpty(str)) {
                    d_(false);
                    return;
                }
                this.f22642a = str;
            }
            if (o()) {
                U_().postDelayed(new Runnable() {
                    public void run() {
                        QRCodeStep.this.a(QRCodeStep.this.d);
                    }
                }, 5000);
            } else if (this.f22642a != null) {
                a(this.f22642a);
            }
        }
    }

    public SmartConfigStep.Step b(int i) {
        BluetoothLog.c(String.format("onStageTimeOut index = %d", new Object[]{Integer.valueOf(i)}));
        if (i != 0) {
            return null;
        }
        if (!NetworkUtils.c()) {
            a((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.phone_wifi_error, (int) R.drawable.common_bind_app_connect_network_failed);
        } else {
            a((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.make_device_near_router, (int) R.drawable.common_bind_device_connect_network_failed);
        }
        DeviceFinder.a().b();
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
        this.mCommonBindView.addNextStep((int) R.string.connecting_device, (int) R.string.keep_phone_wifi_connect);
        this.mCommonBindView.startProgressAnimation(3);
    }

    public void i() {
        super.i();
        if (this.d == 0) {
            this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.device_bind_success, (int) R.string.keep_phone_wifi_connect);
        }
    }

    public void j() {
        super.j();
        this.mCommonBindView.addNextStep((int) R.string.connecting_device, (int) R.string.keep_phone_wifi_connect);
        this.mCommonBindView.startProgressAnimation(3);
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
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, b2);
                    QRCodeStep.this.a(0);
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                QRCodeStep.this.a(0);
            }
        });
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    QRCodeStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
