package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import com.xiaomi.qrcode.QrCodeParser;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.ArrayList;
import java.util.List;

public class GatewaySubdeviceStep extends ConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22608a = "GatewaySubdeviceStep";
    private static final byte ap = 1;
    private static final byte aq = 2;
    private static final byte ar = 3;
    private static final byte as = 4;
    private static final byte at = 5;
    private static final byte au = 6;
    private static final byte av = 7;
    private static final int b = 263;
    private static final int c = 264;
    private static final int d = 0;
    private static final int e = 1;
    private static final long f = 30000;
    private static final long g = 2000;
    private static final int h = 5;
    private int aA;
    private PluginRecord aB;
    private String aC;
    private String aw;
    private String ax;
    private String ay;
    private List<Device> az;
    private int i;
    private int j;
    private QrCodeParser k;
    private byte l;

    private void a(QrCodeParser qrCodeParser) {
    }

    private void o() {
    }

    public SmartConfigStep.Step b(int i2) {
        return null;
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 10;
        arrayList.add(configTime);
        ConfigStep.ConfigTime configTime2 = new ConfigStep.ConfigTime();
        configTime2.f22586a = 1;
        configTime2.b = 30000;
        return arrayList;
    }

    public void a(Context context) {
        BluetoothLog.c("GatewaySubdeviceStep is onCreateStep");
        this.aC = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.J);
        this.aw = (String) SmartConfigDataProvider.a().a("gateway_did");
        this.ax = (String) SmartConfigDataProvider.a().a("device_model");
        this.ay = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.T);
        this.aB = CoreApi.a().d(this.ax);
        this.k = new QrCodeParser((String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.D));
        super.a(context);
    }

    public void a(Message message) {
        int i2 = message.what;
        if (i2 != 111) {
            switch (i2) {
                case 263:
                    a(this.k);
                    return;
                case 264:
                    o();
                    return;
                default:
                    return;
            }
        } else {
            super.a(message);
        }
    }

    public void c(int i2) {
        Device b2 = SmartHomeDeviceManager.a().b(this.aC);
        LogUtil.c(f22608a, "did:" + this.aC + " device:" + b2);
        SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, b2);
        this.mCommonBindView.addNextStep((int) R.string.ble_new_bind_step_loading, (int) R.string.gateway_online_check);
        this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.device_bind_success, (int) R.string.gateway_online_check);
        a(0);
    }

    private void b(long j2) {
        U_().sendEmptyMessageDelayed(264, j2);
    }

    public void k() {
        this.i = 0;
    }

    public int b() {
        BluetoothLog.c(String.format("getCurrentStageIndex return 0", new Object[0]));
        return this.i;
    }

    public void e() {
        BluetoothLog.c(String.format("onFinishStep GatewaySubdeviceStep", new Object[0]));
        U_().removeMessages(264);
        super.e();
    }

    public void h() {
        super.h();
    }

    public void i() {
        super.i();
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    GatewaySubdeviceStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return true;
        }
    }
}
