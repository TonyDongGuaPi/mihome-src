package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.AuthCode;
import com.xiaomi.smarthome.auth.AuthManager;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthSlaveListActivity;
import com.xiaomi.smarthome.device.bluetooth.ui.CommonBindView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.ConfigStep;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ThirdAppBindStep extends ConfigStep {
    public int b() {
        return 0;
    }

    public SmartConfigStep.Step b(int i) {
        return null;
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void k() {
    }

    public ArrayList<ConfigStep.ConfigTime> O_() {
        ArrayList<ConfigStep.ConfigTime> arrayList = new ArrayList<>();
        ConfigStep.ConfigTime configTime = new ConfigStep.ConfigTime();
        configTime.f22586a = 0;
        configTime.b = 15000;
        arrayList.add(configTime);
        return arrayList;
    }

    public void c(int i) {
        a(CommonBindView.StepStatus.LOADING, true);
        final String str = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.J);
        String str2 = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.G);
        String str3 = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.H);
        Long l = (Long) SmartConfigDataProvider.a().a(SmartConfigDataProvider.I);
        String str4 = (String) SmartConfigDataProvider.a().a(SmartConfigDataProvider.K);
        if (TextUtils.isEmpty(str4)) {
            DeviceApi.getInstance().bindThirdDevice(SHApplication.getAppContext(), str, str2, str3, l.longValue(), AuthManager.h().b(), new AsyncCallback<Integer, Error>() {
                /* renamed from: a */
                public void onSuccess(Integer num) {
                    ThirdAppBindStep.this.a(str);
                }

                public void onFailure(Error error) {
                    IAuthCallBack e = AuthManager.h().e();
                    if (e != null) {
                        try {
                            e.onFail(AuthCode.n, new Bundle());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                    ThirdAppBindStep.this.d_(false);
                }
            });
        } else {
            DeviceApi.getInstance().verifyQrcodeNew(SHApplication.getAppContext(), str4, new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("bind_did");
                    if (TextUtils.isEmpty(optString)) {
                        IAuthCallBack e = AuthManager.h().e();
                        if (e != null) {
                            try {
                                e.onFail(AuthCode.n, new Bundle());
                            } catch (RemoteException e2) {
                                e2.printStackTrace();
                            }
                        }
                        ThirdAppBindStep.this.d_(false);
                        return;
                    }
                    ThirdAppBindStep.this.a(optString);
                    Log.e("SmartHome", String.format("Bind Success did = %s", new Object[]{optString}));
                }

                public void onFailure(Error error) {
                    Log.e("SmartHome", String.format("Bind Error did = %s, error - %d", new Object[]{str, Integer.valueOf(error.a())}));
                    IAuthCallBack e = AuthManager.h().e();
                    if (e != null) {
                        try {
                            e.onFail(AuthCode.n, new Bundle());
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                    ThirdAppBindStep.this.d_(false);
                }
            });
        }
    }

    public void a(int i, int i2, Intent intent) {
        v();
        Log.e("SmartHome", "onActivityResult");
    }

    /* access modifiers changed from: package-private */
    public void a(final String str) {
        Log.e("SmartHome", String.format("getDeviceInfo did = %s", new Object[]{str}));
        DeviceApi.getInstance().getDeviceDetail(SHApplication.getAppContext(), new String[]{str}, new AsyncCallback<List<Device>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                if (list.size() > 0) {
                    Device b2 = SmartHomeDeviceManager.a().b(str);
                    if (b2 != null) {
                        SmartHomeDeviceManager.a().c(b2);
                    }
                    Device device = list.get(0);
                    SmartHomeDeviceManager.a().b(device);
                    Log.e("SmartHome", String.format("OnSuccess did = %s", new Object[]{str}));
                    DeviceFinder.a().c(str);
                    SmartConfigDataProvider.a().b(SmartConfigDataProvider.w, device);
                    ThirdAppBindStep.this.U_().postDelayed(new Runnable() {
                        public void run() {
                            Intent intent = new Intent(SHApplication.getAppContext(), DeviceAuthSlaveListActivity.class);
                            intent.putExtra("device_id", str);
                            intent.putExtra(DeviceAuthSlaveListActivity.INTENT_KEY_SHOW_BOTTOM_BAR, true);
                            ((Activity) ThirdAppBindStep.this.af).startActivityForResult(intent, 6);
                        }
                    }, 1000);
                    ThirdAppBindStep.this.U_().postDelayed(new Runnable() {
                        public void run() {
                            Log.e("SmartHome", "Start final page and do callback");
                            ThirdAppBindStep.this.v();
                            IAuthCallBack e = AuthManager.h().e();
                            Log.e("SmartHome", "  ThirdAppBindStep callback  " + e);
                            if (e != null) {
                                try {
                                    Log.e("SmartHome", "  ThirdAppBindStep callback onSuccess");
                                    e.onSuccess(101, new Bundle());
                                } catch (RemoteException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    }, 2000);
                    return;
                }
                Log.e("SmartHome", String.format("Find No Device did = %s", new Object[]{str}));
                ThirdAppBindStep.this.v();
                IAuthCallBack e = AuthManager.h().e();
                if (e != null) {
                    try {
                        e.onSuccess(100, new Bundle());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }

            public void onFailure(Error error) {
                IAuthCallBack e = AuthManager.h().e();
                if (e != null) {
                    try {
                        e.onFail(100, new Bundle());
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
                ThirdAppBindStep.this.v();
            }
        });
    }

    private void a(CommonBindView.StepStatus stepStatus, boolean z) {
        switch (stepStatus) {
            case LOADING:
                this.mCommonBindView.startProgressAnimation(3);
                if (z) {
                    this.mCommonBindView.addNextStep((int) R.string.kuailian_device_connect_wifi, (int) R.string.keep_phone_wifi_connect);
                    return;
                } else {
                    this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.LOADING, (int) R.string.kuailian_device_connect_wifi, (int) R.string.keep_phone_wifi_connect);
                    return;
                }
            case SUCCESS:
                this.mCommonBindView.updateCurrentStep(CommonBindView.StepStatus.SUCCESS, (int) R.string.kuailian_device_connect_wifi, (int) R.string.keep_phone_wifi_connect);
                return;
            case FAILED:
                a((int) R.string.kuailian_device_connect_wifi_fail, (int) R.string.keep_phone_wifi_connect, (int) R.drawable.common_bind_device_connect_network_failed);
                return;
            default:
                return;
        }
    }

    public boolean a() {
        try {
            new MLAlertDialog.Builder(this.af).a((CharSequence) this.af.getString(R.string.stop_connect_device_title)).b((CharSequence) this.af.getString(R.string.stop_connect_device_message)).a((CharSequence) this.af.getString(R.string.confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (DeviceFinder.a().e()) {
                        DeviceFinder.a().b();
                    }
                    ThirdAppBindStep.this.d_(false);
                }
            }).b((CharSequence) this.af.getString(R.string.cancel), (DialogInterface.OnClickListener) null).a(this.af.getResources().getColor(R.color.std_dialog_button_red), -1).d();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
