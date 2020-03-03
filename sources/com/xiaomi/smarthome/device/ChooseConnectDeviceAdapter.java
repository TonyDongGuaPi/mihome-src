package com.xiaomi.smarthome.device;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.server.internal.bluetooth.BluetoothApi;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.miio.activity.GDPRLicenseActivity;
import com.xiaomi.smarthome.service.DeviceObserveService;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.smartconfig.PushBindConfigActivity;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.smartconfig.stage.ConfigStage;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChooseConnectDeviceAdapter extends BaseAdapter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final Context f14772a;
    /* access modifiers changed from: private */
    public final Fragment b;
    private boolean c;
    private List<Object> d = new ArrayList();
    private MLAlertDialog e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public String g = null;
    /* access modifiers changed from: private */
    public String h = null;
    private int i;
    /* access modifiers changed from: private */
    public DeviceScanManager j;

    public enum ScanDeviceType {
        AP,
        AP_DIRECT,
        BLE,
        MI_TV,
        AIOT
    }

    public Object getItem(int i2) {
        return null;
    }

    public long getItemId(int i2) {
        return (long) i2;
    }

    public ChooseConnectDeviceAdapter(Context context, Fragment fragment, Intent intent, int i2, boolean z) {
        this.f14772a = context;
        this.b = fragment;
        this.c = z;
        if (intent != null) {
            this.g = intent.getStringExtra("bssid");
            this.h = intent.getStringExtra("password");
            this.f = intent.getBooleanExtra(SmartConfigDataProvider.N, false);
            this.i = i2;
        }
    }

    public void a(DeviceScanManager deviceScanManager) {
        this.j = deviceScanManager;
    }

    public View getView(final int i2, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.f14772a).inflate(this.i, viewGroup, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.name);
        TextView textView2 = (TextView) view.findViewById(R.id.name_status);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image);
        View findViewById = view.findViewById(R.id.divider);
        simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_CENTER).build());
        textView2.setVisibility(8);
        Object obj = this.d.get(i2);
        if (obj instanceof PushBindEntity) {
            final PushBindEntity pushBindEntity = (PushBindEntity) obj;
            DeviceFactory.b(pushBindEntity.f22312a.o(), simpleDraweeView);
            textView.setText(pushBindEntity.f22312a.p());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ChooseConnectDeviceAdapter.this.a(ScanDeviceType.AIOT, (String) null, (ScanResult) null, (BleDevice) null, (MiTVDevice) null, pushBindEntity);
                    STAT.d.c(pushBindEntity.f22312a.o(), i2 + 1, ChooseConnectDeviceAdapter.this.getCount());
                }
            });
        } else if (obj instanceof ScanResult) {
            final ScanResult scanResult = (ScanResult) obj;
            if (!DeviceFactory.e(scanResult)) {
                final String a2 = DeviceFactory.a(scanResult);
                DeviceFactory.a(a2, simpleDraweeView, (int) R.drawable.device_list_phone_no);
                textView.setText(DeviceFactory.i(scanResult));
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ChooseConnectDeviceAdapter.this.a(ScanDeviceType.AP, a2, scanResult, (BleDevice) null, (MiTVDevice) null, (PushBindEntity) null);
                        STAT.d.c(a2, i2 + 1, ChooseConnectDeviceAdapter.this.getCount());
                    }
                });
            } else {
                final String c2 = DeviceFactory.c(scanResult);
                DeviceFactory.a(c2, simpleDraweeView, (int) R.drawable.device_list_phone_no);
                textView.setText(DeviceFactory.i(scanResult));
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ChooseConnectDeviceAdapter.this.a(ScanDeviceType.AP_DIRECT, c2, scanResult, (BleDevice) null, (MiTVDevice) null, (PushBindEntity) null);
                        STAT.d.c(c2, i2 + 1, ChooseConnectDeviceAdapter.this.getCount());
                    }
                });
            }
        } else if (obj instanceof BleDevice) {
            final BleDevice bleDevice = (BleDevice) obj;
            DeviceFactory.b(bleDevice.model, simpleDraweeView);
            textView.setText(bleDevice.q());
            int r = bleDevice.r();
            if (r != 1) {
                textView2.setVisibility(0);
                if (this.c) {
                    textView2.setText(this.f14772a.getResources().getQuantityString(R.plurals.choose_device_device_count, r, new Object[]{Integer.valueOf(r)}));
                } else {
                    textView2.setText(r + "");
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ChooseConnectDeviceAdapter.this.a(ScanDeviceType.BLE, (String) null, (ScanResult) null, bleDevice, (MiTVDevice) null, (PushBindEntity) null);
                    STAT.d.c(bleDevice.model, i2 + 1, ChooseConnectDeviceAdapter.this.getCount());
                }
            });
        } else if (obj instanceof MiTVDevice) {
            final MiTVDevice miTVDevice = (MiTVDevice) obj;
            DeviceFactory.b(miTVDevice.model, simpleDraweeView);
            textView.setText(miTVDevice.name);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ChooseConnectDeviceAdapter.this.a(ScanDeviceType.MI_TV, (String) null, (ScanResult) null, (BleDevice) null, miTVDevice, (PushBindEntity) null);
                    STAT.d.c(miTVDevice.model, i2 + 1, ChooseConnectDeviceAdapter.this.getCount());
                }
            });
        }
        if (findViewById != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
            if (i2 == getCount() - 1) {
                layoutParams.leftMargin = 0;
            } else {
                layoutParams.leftMargin = DisplayUtils.a(this.f14772a, 13.0f);
            }
        }
        if (view instanceof ListItemView) {
            ((ListItemView) view).setPosition(i2);
        }
        return view;
    }

    public int getCount() {
        return this.d.size();
    }

    public void a(Collection<?> collection) {
        this.d.clear();
        this.d.addAll(collection);
        super.notifyDataSetChanged();
    }

    public void a(ScanDeviceType scanDeviceType, String str, ScanResult scanResult, BleDevice bleDevice, final MiTVDevice miTVDevice, PushBindEntity pushBindEntity) {
        PluginRecord pluginRecord;
        List<Device> g2;
        if (!TextUtils.isEmpty(str)) {
            pluginRecord = CoreApi.a().d(str);
        } else {
            pluginRecord = bleDevice != null ? CoreApi.a().d(bleDevice.model) : null;
        }
        if (pluginRecord == null || pluginRecord.c().a()) {
            SmartConfigMainActivity.DEVICE_FROM_APP_PLUS_TYPE = 6;
            STAT.d.s(str);
            STAT.d.aI(str);
            switch (scanDeviceType) {
                case AIOT:
                    this.b.startActivityForResult(new Intent(this.f14772a, PushBindConfigActivity.class).putExtra(DevicePushBindManager.EXTRA_BINDWIFI, pushBindEntity), 1000);
                    return;
                case AP:
                    a(str, scanResult);
                    return;
                case AP_DIRECT:
                    a(str, scanResult);
                    return;
                case BLE:
                    if (!"ryeex.bracelet.sake".equals(bleDevice.model) || (g2 = SmartHomeDeviceManager.a().g("ryeex.bracelet.sake")) == null || g2.size() <= 0) {
                        a(bleDevice);
                        return;
                    } else {
                        Toast.makeText(this.f14772a, this.f14772a.getResources().getText(R.string.already_bind_one_device), 0).show();
                        return;
                    }
                case MI_TV:
                    if (miTVDevice.isBinded() || !miTVDevice.d() || !miTVDevice.isOnline) {
                        MitvDeviceManager.b().c(miTVDevice.did);
                        if (this.j != null) {
                            this.j.removeMitv(miTVDevice);
                        }
                        SmartHomeDeviceManager.a().b((Device) miTVDevice);
                        Toast.makeText(this.f14772a, R.string.smarthome_add_divece_suscess, 0).show();
                        return;
                    }
                    new MiTVDeviceLoginHelper().a(this.f14772a, miTVDevice, new AsyncResponseCallback<Void>() {
                        public void a(int i) {
                        }

                        public void a(int i, Object obj) {
                        }

                        public void a(Void voidR) {
                            if (ChooseConnectDeviceAdapter.this.j != null) {
                                ChooseConnectDeviceAdapter.this.j.removeMitv(miTVDevice);
                            }
                            Toast.makeText(ChooseConnectDeviceAdapter.this.f14772a, R.string.smarthome_add_divece_suscess, 0).show();
                        }
                    });
                    return;
                default:
                    return;
            }
        } else {
            CommonApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    Toast.makeText(CommonApplication.getAppContext(), CommonApplication.getAppContext().getResources().getString(R.string.device_not_support_now), 0).show();
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final String str, final ScanResult scanResult) {
        if (scanResult != null) {
            DeviceObserveService.getInstance().notifiyNotified(scanResult.SSID);
            if (DeviceFactory.d(scanResult) != DeviceFactory.AP_TYPE.AP_MIBAP || !BluetoothUtils.a() || BluetoothUtils.b()) {
                Intent a2 = ConfigStage.a(this.f14772a, scanResult, str, this.g, this.h);
                if (a2 != null) {
                    a2.putExtra(SmartConfigDataProvider.N, this.f);
                    this.b.startActivityForResult(a2, 1000);
                    return;
                }
                return;
            }
            BluetoothStateHelper.a((BleResponse) new BleResponse() {
                public void a(int i, Object obj) {
                    Intent a2 = ConfigStage.a(ChooseConnectDeviceAdapter.this.f14772a, scanResult, str, ChooseConnectDeviceAdapter.this.g, ChooseConnectDeviceAdapter.this.h);
                    if (a2 != null) {
                        a2.putExtra(SmartConfigDataProvider.N, ChooseConnectDeviceAdapter.this.f);
                        ChooseConnectDeviceAdapter.this.b.startActivityForResult(a2, 1000);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void b(BleDevice bleDevice) {
        int i2;
        Log.i("ChooseDevice", "Config Comb device BEGIN.");
        boolean z = true;
        if (bleDevice.d() == null || bleDevice.d().f14276a == null) {
            i2 = 0;
        } else {
            i2 = bleDevice.d().f14276a.k;
            if (!BluetoothApi.a(bleDevice.d().f14276a.l)) {
                z = false;
            }
        }
        if (i2 != 0 || !z) {
            Log.e("ChooseDevice", "don't support authMode: " + i2);
            CommonApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    Toast.makeText(CommonApplication.getAppContext(), CommonApplication.getAppContext().getResources().getString(R.string.device_not_support_now), 0).show();
                }
            });
            return;
        }
        Intent intent = new Intent(this.f14772a, SmartConfigMainActivity.class);
        intent.putExtra("strategy_id", 13);
        intent.putExtra("model", bleDevice.model);
        intent.putExtra(SmartConfigDataProvider.O, bleDevice.mac);
        if (bleDevice.d() != null) {
            intent.putExtra(SmartConfigDataProvider.P, bleDevice.d().f);
        }
        if (!TextUtils.isEmpty(this.g)) {
            intent.putExtra("bssid", this.g);
            intent.putExtra("password", this.h);
        }
        intent.putExtra(SmartConfigDataProvider.N, this.f);
        this.b.startActivityForResult(intent, 1000);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(final com.xiaomi.smarthome.device.BleDevice r9) {
        /*
            r8 = this;
            java.lang.String r0 = r9.mac
            int r0 = com.xiaomi.smarthome.device.bluetooth.BleCacheUtils.f(r0)
            r1 = 1
            if (r0 == r1) goto L_0x00c8
            if (r9 == 0) goto L_0x001b
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r0 = r9.d()
            if (r0 == 0) goto L_0x001b
            com.xiaomi.smarthome.core.server.internal.bluetooth.recognizer.beacon.MiotBleAdvPacket r0 = r9.d()
            java.lang.String r0 = r0.f
            if (r0 == 0) goto L_0x001b
            goto L_0x00c8
        L_0x001b:
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            com.xiaomi.smarthome.frame.server_compact.ServerBean r0 = r0.F()
            boolean r0 = com.xiaomi.smarthome.frame.server_compact.ServerCompact.d((com.xiaomi.smarthome.frame.server_compact.ServerBean) r0)
            r1 = 0
            if (r0 == 0) goto L_0x007d
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r0 = r8.e
            if (r0 == 0) goto L_0x003b
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r0 = r8.e
            boolean r0 = r0.isShowing()
            if (r0 == 0) goto L_0x003b
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r0 = r8.e
            r0.dismiss()
        L_0x003b:
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = new com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder
            android.content.Context r2 = r8.f14772a
            r0.<init>(r2)
            android.content.Context r2 = r8.f14772a
            r3 = 2131496089(0x7f0c0c99, float:1.8615733E38)
            java.lang.String r2 = r2.getString(r3)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((java.lang.CharSequence) r2)
            android.text.SpannableStringBuilder r2 = r8.c((com.xiaomi.smarthome.device.BleDevice) r9)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.a((android.text.SpannableStringBuilder) r2)
            android.content.Context r2 = r8.f14772a
            r3 = 2131496087(0x7f0c0c97, float:1.8615729E38)
            java.lang.String r2 = r2.getString(r3)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r0 = r0.b((java.lang.CharSequence) r2, (android.content.DialogInterface.OnClickListener) r1)
            android.content.Context r1 = r8.f14772a
            r2 = 2131496088(0x7f0c0c98, float:1.861573E38)
            java.lang.String r1 = r1.getString(r2)
            com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter$11 r2 = new com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter$11
            r2.<init>(r9)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog$Builder r9 = r0.a((java.lang.CharSequence) r1, (android.content.DialogInterface.OnClickListener) r2)
            com.xiaomi.smarthome.library.common.dialog.MLAlertDialog r9 = r9.d()
            r8.e = r9
            goto L_0x00c7
        L_0x007d:
            boolean r0 = r9 instanceof com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup
            if (r0 == 0) goto L_0x0093
            r0 = r9
            com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup r0 = (com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup) r0
            java.util.ArrayList r2 = r0.z()
            int r2 = r2.size()
            if (r2 <= 0) goto L_0x0093
            java.util.ArrayList r0 = r0.z()
            goto L_0x0094
        L_0x0093:
            r0 = r1
        L_0x0094:
            com.xiaomi.smarthome.frame.core.CoreApi r2 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            java.lang.String r3 = r9.model
            boolean r2 = r2.c((java.lang.String) r3)
            if (r2 == 0) goto L_0x00a6
            android.content.Context r2 = r8.f14772a
            com.xiaomi.smarthome.device.bluetooth.BleDispatcher.a((android.content.Context) r2, (com.xiaomi.smarthome.device.BleDevice) r9, (android.content.Intent) r1, (java.util.ArrayList<java.lang.String>) r0)
            goto L_0x00c7
        L_0x00a6:
            boolean r0 = com.xiaomi.smarthome.device.DeviceUtils.a((com.xiaomi.smarthome.device.BleDevice) r9)
            if (r0 == 0) goto L_0x00b2
            android.content.Context r0 = r8.f14772a
            com.xiaomi.smarthome.device.bluetooth.BleDispatcher.b(r0, r9, r1, r1)
            return
        L_0x00b2:
            com.xiaomi.smarthome.device.renderer.DeviceRenderer r2 = com.xiaomi.smarthome.device.renderer.DeviceRenderer.a((com.xiaomi.smarthome.device.Device) r9)
            android.content.Context r4 = r8.f14772a
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r9
            android.content.Intent r9 = r2.a((com.xiaomi.smarthome.device.Device) r3, (android.content.Context) r4, (android.os.Bundle) r5, (boolean) r6, (com.xiaomi.smarthome.device.renderer.DeviceRenderer.LoadingCallback) r7)
            if (r9 == 0) goto L_0x00c7
            android.support.v4.app.Fragment r0 = r8.b
            r0.startActivity(r9)
        L_0x00c7:
            return
        L_0x00c8:
            boolean r0 = com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils.a()
            if (r0 == 0) goto L_0x00dd
            boolean r0 = com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils.b()
            if (r0 != 0) goto L_0x00dd
            com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter$10 r0 = new com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter$10
            r0.<init>(r9)
            com.xiaomi.smarthome.device.BluetoothStateHelper.a((com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse) r0)
            goto L_0x00e0
        L_0x00dd:
            r8.b((com.xiaomi.smarthome.device.BleDevice) r9)
        L_0x00e0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter.a(com.xiaomi.smarthome.device.BleDevice):void");
    }

    private SpannableStringBuilder c(final BleDevice bleDevice) {
        String string = this.f14772a.getString(R.string.license_content);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass12 r0 = new ClickableSpan() {
            public void onClick(View view) {
                Intent intent = new Intent(ChooseConnectDeviceAdapter.this.f14772a, GDPRLicenseActivity.class);
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                intent.putExtra("key_model", bleDevice.model);
                ChooseConnectDeviceAdapter.this.b.startActivity(intent);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#0099ff"));
                textPaint.setUnderlineText(false);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return spannableStringBuilder;
    }
}
