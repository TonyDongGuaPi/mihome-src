package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.ResetDevicePage;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class PushNewDeviceStep extends ConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22625a = 0;
    /* access modifiers changed from: private */
    public int b;
    /* access modifiers changed from: private */
    public boolean c;
    /* access modifiers changed from: private */
    public PushBindEntity d;
    /* access modifiers changed from: private */
    public Runnable e = new Runnable() {
        public void run() {
            if (PushNewDeviceStep.this.d != null && PushNewDeviceStep.this.c && !PushNewDeviceStep.this.ag) {
                DeviceApi.getInstance().getNewDevice(SHApplication.getAppContext(), (String) null, false, PushNewDeviceStep.this.d.d, PushNewDeviceStep.this.d.e, PushNewDeviceStep.this.d.f, (String) null, new AsyncCallback<List<Device>, Error>() {
                    /* renamed from: a */
                    public void onSuccess(List<Device> list) {
                        Device device;
                        if (list.size() == 0 || (device = list.get(0)) == null) {
                            a();
                            return;
                        }
                        SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, device);
                        SmartHomeDeviceManager.a().b(device);
                        SmartHomeDeviceManager.a().p(device.did);
                        BluetoothMyLogger.f("PushNewDeviceStep DeviceFinderCallback.onDeviceConnectionSuccess");
                        int unused = PushNewDeviceStep.this.b = 0;
                        PushNewDeviceStep.this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.device_bind_success, (int) R.string.gateway_wifi_hintdevice);
                        PushNewDeviceStep.this.a(0);
                        DevicePushBindManager.instance.needScan();
                    }

                    public void onFailure(Error error) {
                        a();
                    }

                    private void a() {
                        PushNewDeviceStep.this.U_().postDelayed(PushNewDeviceStep.this.e, 4000);
                    }
                });
            }
        }
    };

    public SmartConfigStep.Step f() {
        return null;
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 60000;
        arrayList.add(configTime);
        return arrayList;
    }

    public void a(Context context) {
        BluetoothLog.c("PushNewDeviceStep is onCreateStep");
        super.a(context);
    }

    public void c(int i) {
        this.c = true;
        this.d = (PushBindEntity) SmartConfigDataProvider.a().a(SmartConfigDataProvider.r);
        DeviceApi.getInstance().bindPushApDevice(this.af, this.d.f22312a.o(), this.d.b, this.d.c, this.d.d, this.d.e, this.d.f, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                PushNewDeviceStep.this.e.run();
            }

            public void onFailure(Error error) {
                BluetoothLog.c("PushNewDeviceStep startConnection.onFailure");
                PushNewDeviceStep.this.a((int) R.string.kuailian_phone_connect_device_fail, (int) R.string.kuailian_phone_connect_device_fail, (int) R.drawable.common_bind_device_has_binded_failed);
            }
        });
    }

    public SmartConfigStep.Step b(int i) {
        this.c = false;
        BluetoothLog.c("PushNewDeviceStep onStageTimeOut index:" + i);
        DeviceFinder.a().b();
        a((int) R.string.kuailian_phone_connect_device_fail, (int) R.string.kuailian_phone_connect_device_fail, (int) R.drawable.common_bind_device_has_binded_failed);
        a(false, 0, (SmartConfigStep.Step) null);
        this.mCommonBindView.setCommonBtnText(this.af.getString(R.string.push_changewifi_fail_rechose));
        this.mCommonBindView.setCommonBtnListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((Activity) PushNewDeviceStep.this.af).startActivityForResult(new Intent(PushNewDeviceStep.this.af, ResetDevicePage.class).putExtra("model", PushNewDeviceStep.this.d.f22312a.o()), 101);
            }
        });
        DevicePushBindManager.instance.addBlackList(this.d);
        return null;
    }

    public void k() {
        this.b = 0;
    }

    public int b() {
        BluetoothLog.c(String.format("PushNewDeviceStep getCurrentStageIndex return 0", new Object[0]));
        return this.b;
    }

    public void e() {
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.b, (Object) null);
        BluetoothLog.c(String.format("PushNewDeviceStep onFinishStep", new Object[0]));
        DeviceFinder.a().b();
        super.e();
    }

    public void h() {
        super.h();
        this.mCommonBindView.resetUi();
        this.mCommonBindView.addNextStep((int) R.string.wifi_recongise_device, (int) R.string.wifi_add_device);
        this.mCommonBindView.startProgressAnimation(1);
    }

    public void a(int i, int i2, Intent intent) {
        super.a(i, i2, intent);
        D();
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    PushNewDeviceStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
