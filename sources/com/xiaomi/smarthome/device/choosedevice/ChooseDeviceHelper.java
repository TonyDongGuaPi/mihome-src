package com.xiaomi.smarthome.device.choosedevice;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.infrared.activity.IRGateWayChooseActivity;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfoStatus;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ChooseGatewayDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.ModelGroupInfo;
import com.xiaomi.smarthome.device.ModifyGroupActivity;
import com.xiaomi.smarthome.device.ResetDevicePage;
import com.xiaomi.smarthome.device.ScanChooseBluetoothDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceHelper;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.light.group.LightGroupSettingActivity;
import com.xiaomi.smarthome.smartconfig.CameraResetConnection;
import com.xiaomi.smarthome.smartconfig.ChooseGeneralAPActivity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.router.RouterConfigActivity;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ChooseDeviceHelper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15324a = "ChooseDeviceHelper";
    private List<ModelGroupInfo> b = new ArrayList();
    /* access modifiers changed from: private */
    public Dialog c;
    /* access modifiers changed from: private */
    public CommonActivity d;

    private interface IDownloadPluginCallback {
        void a();

        void b();
    }

    public interface OnChooseDeviceListener {
        void a();

        void a(List<ModelGroupInfo> list, List<PluginRecord> list2);
    }

    public void a(final CommonActivity commonActivity, final OnChooseDeviceListener onChooseDeviceListener) {
        this.d = commonActivity;
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                CoreApi.a().a((Context) commonActivity, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                    public void onCoreReady() {
                        CoreApi.a().P();
                        ChooseDeviceHelper.this.c(onChooseDeviceListener);
                        CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                            public void a(PluginError pluginError) {
                            }

                            public void a(boolean z, boolean z2) {
                                if (z) {
                                    ChooseDeviceHelper.this.c(onChooseDeviceListener);
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void c(OnChooseDeviceListener onChooseDeviceListener) {
        if (CoreApi.a().O().size() == 0) {
            if (onChooseDeviceListener != null) {
                onChooseDeviceListener.a();
            }
            SHApplication.getGlobalWorkerHandler().postDelayed(new Runnable(onChooseDeviceListener) {
                private final /* synthetic */ ChooseDeviceHelper.OnChooseDeviceListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void run() {
                    ChooseDeviceHelper.this.c(this.f$1);
                }
            }, 1000);
            return;
        }
        b(onChooseDeviceListener);
    }

    private void b(OnChooseDeviceListener onChooseDeviceListener) {
        ArrayList<PluginRecord> arrayList = new ArrayList<>();
        List<PluginRecord> O = CoreApi.a().O();
        if (O != null) {
            arrayList.addAll(O);
            List<ModelGroupInfo> k = SmartHomeDeviceHelper.a().k();
            ArrayList arrayList2 = new ArrayList();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                PluginDeviceInfo c2 = ((PluginRecord) arrayList.get(size)).c();
                if (c2.t() == 5 && k != null && k.size() > 0) {
                    Iterator<ModelGroupInfo> it = k.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        ModelGroupInfo next = it.next();
                        if (next.d.equalsIgnoreCase(((PluginRecord) arrayList.get(size)).o()) && HomeVirtualDeviceHelper.a(next.d)) {
                            arrayList2.add(next);
                            break;
                        }
                    }
                }
                int f = c2.f();
                if (f == 3 || f == 4 || f == 1) {
                    PluginDeviceInfoStatus v = c2.v();
                    if (!(v == PluginDeviceInfoStatus.RELEASE || v == PluginDeviceInfoStatus.WHITE_LIST)) {
                        arrayList.remove(size);
                        LogUtil.c(f15324a, "name:" + c2.k() + " model:" + c2.c() + " status:" + v);
                    }
                } else {
                    arrayList.remove(size);
                    LogUtil.c(f15324a, "name:" + c2.k() + " model:" + c2.c() + " scStatus:" + f);
                }
            }
            if (CoreApi.a().d("isa.camera.isc5") != null) {
                PluginRecord pluginRecord = new PluginRecord();
                PluginDeviceInfo pluginDeviceInfo = new PluginDeviceInfo();
                pluginDeviceInfo.b(SHApplication.getAppContext().getString(R.string.smart_config_xiaofang_name));
                pluginDeviceInfo.a("isa.camera.isc5");
                pluginDeviceInfo.b(4);
                pluginDeviceInfo.q(3);
                pluginRecord.a(pluginDeviceInfo);
                arrayList.add(pluginRecord);
            }
            HashMap hashMap = new HashMap();
            for (PluginRecord c3 : arrayList) {
                hashMap.put(Integer.valueOf(c3.c().d()), true);
            }
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                PluginDeviceInfo c4 = ((PluginRecord) arrayList.get(size2)).c();
                int N = c4.N();
                if (!(N == 0 || hashMap.get(Integer.valueOf(N)) == null)) {
                    arrayList.remove(size2);
                    LogUtil.c(f15324a, "name:" + c4.k() + " model:" + c4.c() + "inheritId:" + N);
                }
            }
            this.d.mHandler.post(new Runnable(arrayList2, onChooseDeviceListener, arrayList) {
                private final /* synthetic */ List f$1;
                private final /* synthetic */ ChooseDeviceHelper.OnChooseDeviceListener f$2;
                private final /* synthetic */ List f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void run() {
                    ChooseDeviceHelper.this.a(this.f$1, this.f$2, this.f$3);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, OnChooseDeviceListener onChooseDeviceListener, List list2) {
        this.b.addAll(list);
        if (onChooseDeviceListener != null) {
            onChooseDeviceListener.a(list, list2);
        }
    }

    public void a(PluginRecord pluginRecord, Intent intent, boolean z) {
        List<Device> g;
        ModelGroupInfo modelGroupInfo;
        PluginRecord d2 = CoreApi.a().d(pluginRecord.o());
        if (d2 != null) {
            if (d2.v() > SystemApi.a().e(this.d)) {
                Toast.makeText(this.d, this.d.getResources().getString(R.string.device_not_support_now), 0).show();
            } else if (!d2.c().a()) {
                Toast.makeText(this.d, this.d.getResources().getString(R.string.device_not_support_now), 0).show();
            } else if (IRDeviceUtil.a(d2)) {
                IRGateWayChooseActivity.showIRGateWayChooseActivity(this.d, d2.o());
            } else if (d2.c().t() == Device.PID_SUBDEVICE) {
                ChooseGatewayDevice.selectActivity(this.d, d2, 1001, (Bundle) null);
            } else if (d2.c().e() == 15) {
                Intent intent2 = new Intent(this.d, ResetDevicePage.class);
                intent2.putExtra("model", d2.o());
                this.d.startActivityForResult(intent2, 1001);
            } else if (d2.c().e() == 2 || d2.c().e() == 11) {
                if ("ryeex.bracelet.sake".equals(d2.o()) && (g = SmartHomeDeviceManager.a().g("ryeex.bracelet.sake")) != null && g.size() > 0) {
                    Toast.makeText(this.d, this.d.getString(R.string.already_bind_one_device), 0).show();
                } else if (d2.c().t() == Device.PID_BLE_MESH) {
                    Intent intent3 = new Intent(this.d, ResetDevicePage.class);
                    intent3.putExtra("model", d2.o());
                    this.d.startActivityForResult(intent3, 100);
                } else {
                    Intent intent4 = new Intent(this.d, ScanChooseBluetoothDevice.class);
                    intent4.putExtra("model", d2.o());
                    intent4.putExtra("deviceName", d2.p());
                    if (intent != null && intent.hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                        intent4.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, intent.getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                    }
                    this.d.startActivity(intent4);
                }
            } else if (d2.c().e() == 7) {
                Intent intent5 = new Intent(this.d, ChooseGeneralAPActivity.class);
                intent5.putExtra("model", d2.o());
                this.d.startActivity(intent5);
            } else if (d2.c().e() == 4) {
                if (d2.o().equals("yunyi.camera.v1")) {
                    Intent intent6 = new Intent(this.d, SmartConfigMainActivity.class);
                    intent6.putExtra("model", d2.o());
                    intent6.putExtra("strategy_id", 3);
                    if (intent != null && intent.hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                        intent6.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, intent.getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                    }
                    this.d.startActivityForResult(intent6, 100);
                } else if (d2.o().equals("isa.camera.isc5") || d2.o().equals("isa.camera.df3")) {
                    b(d2, z);
                } else if (DeviceFactory.a(d2.o())) {
                    a(d2, z);
                } else {
                    c(d2, z);
                }
            } else if (d2.c().e() == 5) {
                Intent intent7 = new Intent(this.d, ResetDevicePage.class);
                intent7.putExtra("model", d2.o());
                this.d.startActivityForResult(intent7, 100);
            } else if (d2.c().e() == 12) {
                d(d2, z);
            } else if (RouterConfigActivity.isSupportRouterConfig(d2.o())) {
                Intent intent8 = new Intent(this.d, RouterConfigActivity.class);
                intent8.putExtra("model", d2.o());
                this.d.startActivity(intent8);
            } else if (d2.c().t() == 5) {
                Iterator<ModelGroupInfo> it = this.b.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        modelGroupInfo = null;
                        break;
                    }
                    modelGroupInfo = it.next();
                    if (d2.o().equalsIgnoreCase(modelGroupInfo.d)) {
                        break;
                    }
                }
                if (modelGroupInfo != null) {
                    String[] a2 = SmartHomeDeviceHelper.a().a(modelGroupInfo);
                    if (a2 == null || a2.length != 1) {
                        Intent intent9 = new Intent(this.d, ModifyGroupActivity.class);
                        intent9.putExtra("group_device_ids", a2);
                        intent9.putExtra("group_model", modelGroupInfo.d);
                        this.d.startActivity(intent9);
                        this.d.setResult(-1);
                        return;
                    }
                    final Device b2 = SmartHomeDeviceManager.a().b(a2[0]);
                    if (b2 != null && CoreApi.a().c(b2.model)) {
                        if (this.c != null && this.c.isShowing()) {
                            this.c.dismiss();
                        }
                        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this.d);
                        builder.a((int) R.string.dialog_title_one_device_tip);
                        builder.a((int) R.string.dialog_option_one_more, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                UrlDispatchManger a2 = UrlDispatchManger.a();
                                a2.c("https://home.mi.com/shop/search?keyword=" + b2.name);
                                ChooseDeviceHelper.this.d.setResult(-1);
                            }
                        });
                        builder.b((int) R.string.dialog_option_okay, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        this.c = builder.b();
                        this.c.show();
                    }
                }
            } else if (d2.c().t() == Device.PID_VIRTUAL_GROUP) {
                Intent intent10 = new Intent(this.d, LightGroupSettingActivity.class);
                intent10.putExtra(LightGroupSettingActivity.ARGS_KEY_GROUP_MODEL, d2.c().c());
                this.d.startActivity(intent10);
            } else {
                int t = d2.c().t();
                if (t == 0 || t == 8 || d2.o().equals("midr.cardvr.v1")) {
                    a(d2.o(), z, intent);
                } else {
                    Toast.makeText(this.d, this.d.getResources().getString(R.string.device_not_support_now), 0).show();
                }
            }
        }
    }

    private void a(PluginRecord pluginRecord, boolean z) {
        if (pluginRecord.p().equals(this.d.getString(R.string.smart_config_mijia_camera_zhilian))) {
            String c2 = WifiUtil.c(this.d);
            if (TextUtils.isEmpty(c2) || !c2.contains("mijia-camera-v1")) {
                a("mijia.camera.v1");
            } else {
                Toast.makeText(this.d, R.string.smart_config_mijia_camera_connecting, 0).show();
            }
        } else {
            c(pluginRecord, z);
        }
    }

    private void b(PluginRecord pluginRecord, boolean z) {
        if (pluginRecord.p().equals(SHApplication.getAppContext().getString(R.string.smart_config_xiaofang_name))) {
            String c2 = WifiUtil.c(this.d);
            if (TextUtils.isEmpty(c2) || !c2.contains("isa-camera-isc5")) {
                a("isa.camera.isc5");
            } else {
                Toast.makeText(this.d, R.string.smart_config_xiaofang_error_connecting, 0).show();
            }
        } else {
            c(pluginRecord, z);
        }
    }

    private void c(PluginRecord pluginRecord, boolean z) {
        List<Integer> i = pluginRecord.c().i();
        if (i == null || (!i.contains(0) && !i.contains(1))) {
            Intent intent = new Intent(this.d, SmartConfigMainActivity.class);
            intent.putExtra("model", pluginRecord.o());
            intent.putExtra("strategy_id", 9);
            intent.putExtra(SmartConfigDataProvider.N, z);
            this.d.startActivityForResult(intent, 100);
            return;
        }
        a(pluginRecord.o(), z, (Intent) null);
    }

    private void d(PluginRecord pluginRecord, boolean z) {
        List<Integer> i = pluginRecord.c().i();
        if (i == null || (!i.contains(0) && !i.contains(1))) {
            Intent intent = new Intent(this.d, SmartConfigMainActivity.class);
            intent.putExtra("model", pluginRecord.o());
            intent.putExtra("strategy_id", 11);
            intent.putExtra(SmartConfigDataProvider.N, z);
            this.d.startActivityForResult(intent, 100);
            return;
        }
        a(pluginRecord.o(), z, (Intent) null);
    }

    private void a(String str, boolean z, Intent intent) {
        Intent intent2 = new Intent(this.d, ResetDevicePage.class);
        intent2.putExtra("model", str);
        intent2.putExtra(SmartConfigDataProvider.N, z);
        if (intent != null && intent.hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
            intent2.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, intent.getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
        }
        this.d.startActivityForResult(intent2, 100);
    }

    private void a(final String str) {
        a(str, (IDownloadPluginCallback) new IDownloadPluginCallback() {
            public void a() {
                Intent intent = new Intent(ChooseDeviceHelper.this.d, CameraResetConnection.class);
                intent.putExtra("model", str);
                ChooseDeviceHelper.this.d.startActivity(intent);
                ChooseDeviceHelper.this.d.finish();
            }

            public void b() {
                Toast.makeText(ChooseDeviceHelper.this.d, R.string.smart_config_xiaofang_switch_back_error, 0).show();
            }
        });
    }

    private void a(String str, final IDownloadPluginCallback iDownloadPluginCallback) {
        final PluginRecord d2 = CoreApi.a().d(str);
        if (d2 != null) {
            if (this.c != null && this.c.isShowing()) {
                this.c.dismiss();
            }
            CommonActivity commonActivity = this.d;
            this.c = XQProgressHorizontalDialog.b(commonActivity, this.d.getString(R.string.plugin_downloading) + d2.p() + this.d.getString(R.string.plugin));
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            if (!d2.k()) {
                CoreApi.a().a(d2.o(), (CoreApi.DownloadPluginCallback) new CoreApi.DownloadPluginCallback() {
                    private long e = 0;

                    public void onCancel() {
                    }

                    public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    }

                    public void onStartAlready(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    }

                    public void onStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                        this.e = System.currentTimeMillis();
                        if (ChooseDeviceHelper.this.d.isValid() && ChooseDeviceHelper.this.c != null) {
                            ((XQProgressHorizontalDialog) ChooseDeviceHelper.this.c).a(100, 0);
                            ((XQProgressHorizontalDialog) ChooseDeviceHelper.this.c).c();
                            ChooseDeviceHelper.this.c.setCancelable(false);
                            ChooseDeviceHelper.this.c.show();
                            ChooseDeviceHelper.this.c.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                public void onCancel(DialogInterface dialogInterface) {
                                    CoreApi.a().a(d2.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
                                }
                            });
                        }
                    }

                    public void onProgress(PluginRecord pluginRecord, float f) {
                        if (ChooseDeviceHelper.this.c != null) {
                            ((XQProgressHorizontalDialog) ChooseDeviceHelper.this.c).a(100, (int) (f * 100.0f));
                        }
                    }

                    public void onSuccess(PluginRecord pluginRecord) {
                        if (this.e > 0 && pluginRecord != null) {
                            STAT.i.a(System.currentTimeMillis() - this.e, pluginRecord.o());
                        }
                        if (ChooseDeviceHelper.this.c != null) {
                            ChooseDeviceHelper.this.c.dismiss();
                        }
                        if (iDownloadPluginCallback != null) {
                            iDownloadPluginCallback.a();
                        }
                    }

                    public void onFailure(PluginError pluginError) {
                        if (ChooseDeviceHelper.this.c != null) {
                            ChooseDeviceHelper.this.c.dismiss();
                        }
                        if (iDownloadPluginCallback != null) {
                            iDownloadPluginCallback.b();
                        }
                    }
                });
            } else if (iDownloadPluginCallback != null) {
                iDownloadPluginCallback.a();
            }
        }
    }

    public void a() {
        if (this.c != null && this.c.isShowing()) {
            this.c.dismiss();
        }
    }
}
