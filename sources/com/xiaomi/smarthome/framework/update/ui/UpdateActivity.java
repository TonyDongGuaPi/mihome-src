package com.xiaomi.smarthome.framework.update.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.core.entity.plugin.PluginUpdateInfo;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.HostSetting;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.network.NetworkManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.framework.update.AppGrayUpdateInfo;
import com.xiaomi.smarthome.framework.update.AppInnerUpdateInfo;
import com.xiaomi.smarthome.framework.update.AppReleaseUpdateInfo;
import com.xiaomi.smarthome.framework.update.FirmwareUpdateInfo;
import com.xiaomi.smarthome.framework.update.UpdateItemHelper;
import com.xiaomi.smarthome.framework.update.UpdateManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpdateActivity extends BaseActivity {
    public static final int REQUEST_CODE_INSTALL_APK = 10001;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static boolean f17814a = (HostSetting.g || HostSetting.i);
    private static final String b = "arg_did_list";
    private static final String c = "UpdateActivity";
    /* access modifiers changed from: private */
    public Context d;
    /* access modifiers changed from: private */
    public SoftwareUpdateItem e;
    private MLAlertDialog f;
    /* access modifiers changed from: private */
    public boolean g;
    private AppDownloadCallback h = new AppDownloadCallback(this);
    boolean isDestroyed = false;
    SimpleAdapter mAdapter;
    boolean mAppChecked = false;
    View mCheckingUpdateLayer;
    boolean mFirmwareChecked = false;
    View mFooter;
    View mHasUpdateLayer;
    ListView mListView;
    View mNoneUpdateLayer;
    boolean mPluginChecked = false;
    TextView mUpdateAllButton;
    List<BaseUpdateItem> mUpdateItemList = new ArrayList();

    private interface NoneWifiConfirmCallback {
        void a();

        void b();
    }

    enum UpdateStatus {
        PENDING,
        UPDATING,
        UPDATING_SUCCESS,
        UPDATING_FAILURE
    }

    /* access modifiers changed from: private */
    public void a(AppUpdateItem appUpdateItem) {
    }

    public void installApk(String str) {
    }

    public static void start(Context context, ArrayList<KeyValuePair> arrayList) {
        Intent intent = new Intent(context, UpdateActivity.class);
        intent.putParcelableArrayListExtra(b, arrayList);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtilGrey.a("UpdateActivity", "UpdateActivity onCreate:googleplay");
        this.d = this;
        setContentView(R.layout.update_activity);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UpdateActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getString(R.string.activity_title_upgrade));
        this.mCheckingUpdateLayer = findViewById(R.id.checking_update_layer);
        this.mHasUpdateLayer = findViewById(R.id.has_update_layer);
        this.mNoneUpdateLayer = findViewById(R.id.none_update_layer);
        this.mListView = (ListView) findViewById(R.id.listview);
        this.mAdapter = new SimpleAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mFooter = findViewById(R.id.footer);
        this.mUpdateAllButton = (TextView) findViewById(R.id.update_all);
        this.mUpdateAllButton.setOnClickListener(new View.OnClickListener() {
            /* JADX WARNING: Removed duplicated region for block: B:15:0x0053  */
            /* JADX WARNING: Removed duplicated region for block: B:30:0x0066 A[EDGE_INSN: B:30:0x0066->B:20:0x0066 ?: BREAK  , SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onClick(android.view.View r6) {
                /*
                    r5 = this;
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$SoftwareUpdateItem r6 = r6.e
                    r0 = 1
                    r1 = 0
                    if (r6 == 0) goto L_0x0066
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$SoftwareUpdateItem r6 = r6.e
                    boolean r6 = r6.c()
                    if (r6 == 0) goto L_0x0033
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$SoftwareUpdateItem r6 = r6.e
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$AppUpdateItem r6 = r6.d
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r6 = r6.f
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r2 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.UpdateStatus.PENDING
                    if (r6 == r2) goto L_0x0032
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$SoftwareUpdateItem r6 = r6.e
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$AppUpdateItem r6 = r6.d
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r6 = r6.f
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r2 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.UpdateStatus.UPDATING_FAILURE
                    if (r6 != r2) goto L_0x0033
                L_0x0032:
                    r1 = 1
                L_0x0033:
                    if (r1 != 0) goto L_0x0066
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$SoftwareUpdateItem r6 = r6.e
                    boolean r6 = r6.d()
                    if (r6 == 0) goto L_0x0066
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$SoftwareUpdateItem r6 = r6.e
                    java.util.List<com.xiaomi.smarthome.framework.update.ui.UpdateActivity$PluginUpdateItem> r6 = r6.e
                    java.util.Iterator r6 = r6.iterator()
                L_0x004d:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L_0x0066
                    java.lang.Object r2 = r6.next()
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$PluginUpdateItem r2 = (com.xiaomi.smarthome.framework.update.ui.UpdateActivity.PluginUpdateItem) r2
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r3 = r2.f
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r4 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.UpdateStatus.PENDING
                    if (r3 == r4) goto L_0x0065
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r2 = r2.f
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$UpdateStatus r3 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.UpdateStatus.UPDATING_FAILURE
                    if (r2 != r3) goto L_0x004d
                L_0x0065:
                    r1 = 1
                L_0x0066:
                    com.xiaomi.smarthome.framework.network.NetworkManager r6 = com.xiaomi.smarthome.framework.network.NetworkManager.a()
                    boolean r6 = r6.e()
                    if (r6 != 0) goto L_0x007d
                    if (r1 == 0) goto L_0x007d
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity$2$1 r0 = new com.xiaomi.smarthome.framework.update.ui.UpdateActivity$2$1
                    r0.<init>()
                    r6.a((com.xiaomi.smarthome.framework.update.ui.UpdateActivity.NoneWifiConfirmCallback) r0)
                    goto L_0x0085
                L_0x007d:
                    com.xiaomi.smarthome.framework.statistic.StatHelper.ae()
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    r6.d()
                L_0x0085:
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    boolean r6 = r6.g
                    if (r6 == 0) goto L_0x0092
                    com.xiaomi.smarthome.stat.StatClick r6 = com.xiaomi.smarthome.stat.STAT.d
                    r6.bj()
                L_0x0092:
                    com.xiaomi.smarthome.framework.update.ui.UpdateActivity r6 = com.xiaomi.smarthome.framework.update.ui.UpdateActivity.this
                    r6.f()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.update.ui.UpdateActivity.AnonymousClass2.onClick(android.view.View):void");
            }
        });
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(b);
        this.g = parcelableArrayListExtra != null;
        if (!this.g) {
            parcelableArrayListExtra = new ArrayList();
            for (Device next : SmartHomeDeviceManager.a().d()) {
                if (next != null) {
                    parcelableArrayListExtra.add(new KeyValuePair(next.did, next.model));
                }
            }
        }
        UpdateManager.a().a((List<KeyValuePair>) parcelableArrayListExtra, (UpdateManager.CheckAllFirmwareUpdateCallback) new UpdateManager.CheckAllFirmwareUpdateCallback() {
            public void a() {
                UpdateActivity.this.mFirmwareChecked = true;
                UpdateActivity.this.b();
                UpdateActivity.this.f();
            }

            public void a(List<FirmwareUpdateInfo> list) {
                Device device;
                UpdateActivity.this.mFirmwareChecked = true;
                UpdateActivity.this.b();
                if (list != null && list.size() > 0) {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        FirmwareUpdateInfo firmwareUpdateInfo = list.get(i);
                        try {
                            device = SmartHomeDeviceManager.a().b(firmwareUpdateInfo.f17724a);
                        } catch (Exception unused) {
                            device = null;
                        }
                        if (device != null && device.isOnline && !firmwareUpdateInfo.h.equals("installed") && !firmwareUpdateInfo.e) {
                            FirmwareUpdateItem firmwareUpdateItem = new FirmwareUpdateItem();
                            firmwareUpdateItem.e = device.model;
                            firmwareUpdateItem.f = device.name;
                            firmwareUpdateItem.g = firmwareUpdateInfo.f17724a;
                            firmwareUpdateItem.h = device.pid;
                            firmwareUpdateItem.i = firmwareUpdateInfo.b;
                            firmwareUpdateItem.j = firmwareUpdateInfo.c;
                            firmwareUpdateItem.k = firmwareUpdateInfo.d;
                            firmwareUpdateItem.l = firmwareUpdateInfo.f;
                            firmwareUpdateItem.n = firmwareUpdateInfo.h;
                            firmwareUpdateItem.m = firmwareUpdateInfo.g;
                            if (firmwareUpdateInfo.b) {
                                firmwareUpdateItem.b = UpdateStatus.UPDATING;
                            }
                            UpdateActivity.this.mUpdateItemList.add(firmwareUpdateItem);
                            if (firmwareUpdateInfo.b) {
                                firmwareUpdateItem.d = true;
                                UpdateActivity.this.d(firmwareUpdateItem);
                            }
                        }
                    }
                }
                UpdateActivity.this.a();
                UpdateActivity.this.f();
                try {
                    if (UpdateActivity.this.g) {
                        StringBuilder sb = new StringBuilder();
                        for (int i2 = 0; i2 < UpdateActivity.this.mUpdateItemList.size(); i2++) {
                            sb.append(((FirmwareUpdateItem) UpdateActivity.this.mUpdateItemList.get(i2)).e);
                            if (i2 != UpdateActivity.this.mUpdateItemList.size() - 1) {
                                sb.append(",");
                            }
                        }
                        STAT.c.s(sb.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void b() {
                UpdateActivity.this.mFirmwareChecked = true;
                UpdateActivity.this.b();
                UpdateActivity.this.f();
            }
        });
        if (!this.g) {
            UpdateManager.a().a((UpdateManager.CheckAppUpdateCallback) new UpdateManager.CheckAppUpdateCallback() {
                public void a(AppGrayUpdateInfo appGrayUpdateInfo) {
                    Log.d("UpdateActivity", "onGrayNormalUpdate");
                    UpdateActivity.this.mAppChecked = true;
                    if (UpdateActivity.this.e == null) {
                        SoftwareUpdateItem unused = UpdateActivity.this.e = new SoftwareUpdateItem();
                    }
                    AppUpdateItem appUpdateItem = new AppUpdateItem();
                    appUpdateItem.f17830a = appGrayUpdateInfo.b;
                    appUpdateItem.b = appGrayUpdateInfo.c;
                    appUpdateItem.c = appGrayUpdateInfo.d;
                    appUpdateItem.e = appGrayUpdateInfo.e;
                    UpdateActivity.this.e.d = appUpdateItem;
                    UpdateActivity.this.c();
                    UpdateActivity.this.a(UpdateActivity.this.e);
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }

                public void a(AppReleaseUpdateInfo appReleaseUpdateInfo) {
                    Log.d("UpdateActivity", "onReleaseForceUpdate");
                    UpdateActivity.this.mAppChecked = true;
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }

                public void b(AppReleaseUpdateInfo appReleaseUpdateInfo) {
                    Log.d("UpdateActivity", "onReleaseNormalUpdate");
                    UpdateActivity.this.mAppChecked = true;
                    if (UpdateActivity.this.e == null) {
                        SoftwareUpdateItem unused = UpdateActivity.this.e = new SoftwareUpdateItem();
                    }
                    AppUpdateItem appUpdateItem = new AppUpdateItem();
                    appUpdateItem.f17830a = appReleaseUpdateInfo.c;
                    appUpdateItem.b = appReleaseUpdateInfo.d;
                    appUpdateItem.c = appReleaseUpdateInfo.e;
                    appUpdateItem.e = appReleaseUpdateInfo.f;
                    UpdateActivity.this.e.d = appUpdateItem;
                    UpdateActivity.this.c();
                    UpdateActivity.this.a(UpdateActivity.this.e);
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }

                public void a(AppInnerUpdateInfo appInnerUpdateInfo) {
                    Log.d("UpdateActivity", "onInnerNormalUpdate");
                    UpdateActivity.this.mAppChecked = true;
                    if (UpdateActivity.this.e == null) {
                        SoftwareUpdateItem unused = UpdateActivity.this.e = new SoftwareUpdateItem();
                    }
                    AppUpdateItem appUpdateItem = new AppUpdateItem();
                    appUpdateItem.f17830a = appInnerUpdateInfo.b;
                    appUpdateItem.b = appInnerUpdateInfo.c;
                    appUpdateItem.c = appInnerUpdateInfo.d;
                    appUpdateItem.e = appInnerUpdateInfo.e;
                    UpdateActivity.this.e.d = appUpdateItem;
                    UpdateActivity.this.c();
                    UpdateActivity.this.a(UpdateActivity.this.e);
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }

                public void a() {
                    Log.d("UpdateActivity", "onLatest");
                    UpdateActivity.this.mAppChecked = true;
                    if (UpdateActivity.this.e == null) {
                        UpdateActivity.this.c();
                    }
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }

                public void b() {
                    Log.d("UpdateActivity", "onCheckFailure");
                    UpdateActivity.this.mAppChecked = true;
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }
            });
            CoreApi.a().a(false, (CoreApi.UpdatePluginAllCallback) new CoreApi.UpdatePluginAllCallback() {
                public void a() {
                    UpdateActivity.this.mPluginChecked = true;
                    List<PluginRecord> O = CoreApi.a().O();
                    boolean z = false;
                    for (int i = 0; i < O.size(); i++) {
                        PluginRecord pluginRecord = O.get(i);
                        if (pluginRecord.l() && pluginRecord.n()) {
                            if (UpdateActivity.this.e == null) {
                                SoftwareUpdateItem unused = UpdateActivity.this.e = new SoftwareUpdateItem();
                            }
                            PluginUpdateItem pluginUpdateItem = new PluginUpdateItem();
                            pluginUpdateItem.c = pluginRecord.o();
                            pluginUpdateItem.e = pluginRecord;
                            PluginUpdateInfo j = pluginRecord.j();
                            pluginUpdateItem.b = j.e();
                            pluginUpdateItem.d = j.f();
                            pluginUpdateItem.f17833a = j.c();
                            UpdateActivity.this.e.e.add(pluginUpdateItem);
                            z = true;
                        }
                    }
                    if (z) {
                        UpdateActivity.this.c();
                        UpdateActivity.this.a(UpdateActivity.this.e);
                    }
                    UpdateActivity.this.a();
                    UpdateActivity.this.f();
                }

                public void a(PluginError pluginError) {
                    UpdateActivity.this.mPluginChecked = true;
                    UpdateActivity.this.f();
                }
            });
        } else {
            this.mPluginChecked = true;
            this.mAppChecked = true;
        }
        f();
        UpdateItemHelper.a().c();
        UpdateManager.a().a((UpdateManager.DownloadAppCallback) this.h);
    }

    /* access modifiers changed from: private */
    public void a() {
        Collections.sort(this.mUpdateItemList, new Comparator<BaseUpdateItem>() {
            /* renamed from: a */
            public int compare(BaseUpdateItem baseUpdateItem, BaseUpdateItem baseUpdateItem2) {
                if (baseUpdateItem instanceof SoftwareUpdateItem) {
                    return -1;
                }
                return baseUpdateItem2 instanceof SoftwareUpdateItem ? 1 : 0;
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(SoftwareUpdateItem softwareUpdateItem) {
        this.mUpdateItemList.add(this.mUpdateItemList.size(), softwareUpdateItem);
    }

    /* access modifiers changed from: private */
    public void b() {
        int i = 0;
        while (i < this.mUpdateItemList.size()) {
            if (this.mUpdateItemList.get(i) instanceof FirmwareUpdateItem) {
                this.mUpdateItemList.remove(i);
                i--;
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        int i = 0;
        while (i < this.mUpdateItemList.size()) {
            if (this.mUpdateItemList.get(i) instanceof SoftwareUpdateItem) {
                this.mUpdateItemList.remove(i);
                i--;
            }
            i++;
        }
    }

    /* access modifiers changed from: private */
    public void a(FirmwareUpdateItem firmwareUpdateItem) {
        String str = firmwareUpdateItem.g;
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 == null) {
            b2 = SmartHomeDeviceManager.a().l(str);
        }
        if (b2 == null) {
            b2 = SmartHomeDeviceManager.a().a(str);
        }
        PluginRecord d2 = CoreApi.a().d(firmwareUpdateItem.e);
        if (b2 == null || d2 == null) {
            Toast.makeText(this.d, R.string.update_success_open_failure, 0).show();
            return;
        }
        Intent intent = new Intent();
        final XQProgressHorizontalDialog b3 = XQProgressHorizontalDialog.b(this, getString(R.string.plugin_downloading) + d2.p() + getString(R.string.plugin));
        PluginApi.getInstance().sendMessage(this.d, d2, 1, intent, b2.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
            public void onDownloadStart(final PluginRecord pluginRecord, final PluginDownloadTask pluginDownloadTask) {
                if (UpdateActivity.this.isValid() && b3 != null) {
                    b3.a(true);
                    b3.c();
                    b3.setCancelable(true);
                    b3.show();
                    b3.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            CoreApi.a().a(pluginRecord.o(), pluginDownloadTask, (CoreApi.CancelPluginDownloadCallback) null);
                        }
                    });
                }
            }

            public void onDownloadProgress(PluginRecord pluginRecord, float f) {
                if (b3 != null && b3.isShowing()) {
                    b3.a(100, (int) (f * 100.0f));
                }
            }

            public void onDownloadSuccess(PluginRecord pluginRecord) {
                if (b3 != null && b3.isShowing()) {
                    b3.dismiss();
                }
            }

            public void onDownloadFailure(PluginError pluginError) {
                if (b3 != null && b3.isShowing()) {
                    b3.dismiss();
                }
            }

            public void onDownloadCancel() {
                if (b3 != null && b3.isShowing()) {
                    b3.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final FirmwareUpdateItem firmwareUpdateItem) {
        if (firmwareUpdateItem.b != UpdateStatus.UPDATING && firmwareUpdateItem.b != UpdateStatus.UPDATING_SUCCESS) {
            firmwareUpdateItem.b = UpdateStatus.UPDATING;
            f();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("method", "miIO.ota_install");
                jSONObject.put("params", new JSONArray());
            } catch (Exception unused) {
            }
            DeviceApi.getInstance().rpcAsyncRemote(this.d, firmwareUpdateItem.g, jSONObject.toString(), new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    firmwareUpdateItem.b = UpdateStatus.UPDATING_SUCCESS;
                    UpdateActivity.this.f();
                }

                public void onFailure(Error error) {
                    firmwareUpdateItem.b = UpdateStatus.UPDATING;
                    UpdateActivity.this.f();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void c(final FirmwareUpdateItem firmwareUpdateItem) {
        if (firmwareUpdateItem.b != UpdateStatus.UPDATING && firmwareUpdateItem.b != UpdateStatus.UPDATING_SUCCESS) {
            firmwareUpdateItem.b = UpdateStatus.UPDATING;
            f();
            UpdateManager.a().a(firmwareUpdateItem.g, firmwareUpdateItem.h, firmwareUpdateItem.k, (UpdateManager.FirmwareUpdateCallback) new UpdateManager.FirmwareUpdateCallback() {
                public void a() {
                    UpdateActivity.this.d(firmwareUpdateItem);
                }

                public void b() {
                    firmwareUpdateItem.b = UpdateStatus.UPDATING_FAILURE;
                    UpdateActivity.this.f();
                    StatHelper.ai();
                }
            });
        }
    }

    private static class MyFirmwareUpdatePollCallback implements UpdateManager.FirmwareUpdatePollCallback {

        /* renamed from: a  reason: collision with root package name */
        private final FirmwareUpdateItem f17832a;

        private MyFirmwareUpdatePollCallback(FirmwareUpdateItem firmwareUpdateItem) {
            this.f17832a = firmwareUpdateItem;
        }

        public void a(float f) {
            int i = (int) (f * 100.0f);
            UpdateItemHelper.a().a(this.f17832a.g, this.f17832a.d, i);
            if (UpdateActivity.f17814a) {
                Log.d("UpdateActivity", "onProgress: did: " + this.f17832a.g + " ; " + i);
            }
        }

        public void a() {
            UpdateItemHelper.a().a(this.f17832a.g, this.f17832a.d, 100);
            if (UpdateActivity.f17814a) {
                Log.d("UpdateActivity", "onSuccess: " + this.f17832a.g);
            }
        }

        public void b() {
            UpdateItemHelper.a().a(this.f17832a.g);
            if (UpdateActivity.f17814a) {
                Log.d("UpdateActivity", "onFailure: " + this.f17832a.g);
            }
        }
    }

    /* access modifiers changed from: private */
    public void d(FirmwareUpdateItem firmwareUpdateItem) {
        UpdateManager.a().a(firmwareUpdateItem.g, firmwareUpdateItem.h, firmwareUpdateItem.k, (UpdateManager.FirmwareUpdatePollCallback) new MyFirmwareUpdatePollCallback(firmwareUpdateItem));
    }

    private static class AppDownloadCallback implements UpdateManager.DownloadAppCallback {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<UpdateActivity> f17829a;

        public void a(HttpAsyncHandle httpAsyncHandle) {
        }

        public void a(String str) {
        }

        public void b(HttpAsyncHandle httpAsyncHandle) {
        }

        AppDownloadCallback(UpdateActivity updateActivity) {
            this.f17829a = new WeakReference<>(updateActivity);
        }

        public void a(float f) {
            UpdateActivity updateActivity = (UpdateActivity) this.f17829a.get();
            if (updateActivity != null && !updateActivity.isDestroyed && updateActivity.e != null && updateActivity.e.c()) {
                AppUpdateItem appUpdateItem = updateActivity.e.d;
                appUpdateItem.g = f;
                appUpdateItem.f = UpdateStatus.UPDATING;
                updateActivity.e();
                updateActivity.f();
            }
        }

        public void a() {
            UpdateActivity updateActivity = (UpdateActivity) this.f17829a.get();
            if (updateActivity != null && !updateActivity.isDestroyed && updateActivity.e != null && updateActivity.e.c()) {
                updateActivity.e.d.f = UpdateStatus.UPDATING_FAILURE;
                updateActivity.e();
                updateActivity.f();
                StatHelper.ak();
            }
        }

        public void b() {
            UpdateActivity updateActivity = (UpdateActivity) this.f17829a.get();
            if (updateActivity != null && !updateActivity.isDestroyed && updateActivity.e != null && updateActivity.e.c()) {
                updateActivity.e.d.f = UpdateStatus.UPDATING_FAILURE;
                updateActivity.e();
                updateActivity.f();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final PluginUpdateItem pluginUpdateItem) {
        if (pluginUpdateItem.f != UpdateStatus.UPDATING && pluginUpdateItem.f != UpdateStatus.UPDATING_SUCCESS) {
            pluginUpdateItem.f = UpdateStatus.UPDATING;
            f();
            CoreApi.a().a(pluginUpdateItem.e.o(), true, (CoreApi.UpdatePluginCallback) new CoreApi.UpdatePluginCallback() {
                public void a(PluginRecord pluginRecord, PluginUpdateInfo pluginUpdateInfo) {
                }

                public void c(PluginRecord pluginRecord) {
                }

                public void d(PluginRecord pluginRecord) {
                }

                public void a(PluginRecord pluginRecord) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginRecord.o())) {
                                next.f = UpdateStatus.UPDATING_SUCCESS;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                    }
                }

                public void b(PluginRecord pluginRecord) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginRecord.o())) {
                                next.f = UpdateStatus.UPDATING_SUCCESS;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                    }
                }

                public void a(PluginRecord pluginRecord, float f) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginRecord.o())) {
                                next.g = f;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                    }
                }

                public void e(PluginRecord pluginRecord) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginRecord.o())) {
                                next.f = UpdateStatus.UPDATING_SUCCESS;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                    }
                }

                public void f(PluginRecord pluginRecord) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginRecord.o())) {
                                next.f = UpdateStatus.UPDATING_FAILURE;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                    }
                }

                public void g(PluginRecord pluginRecord) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginRecord.o())) {
                                next.f = UpdateStatus.UPDATING_FAILURE;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                    }
                }

                public void a(PluginError pluginError) {
                    if (UpdateActivity.this.e != null && UpdateActivity.this.e.d()) {
                        Iterator<PluginUpdateItem> it = UpdateActivity.this.e.e.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            PluginUpdateItem next = it.next();
                            if (!TextUtils.isEmpty(next.c) && next.c.equalsIgnoreCase(pluginUpdateItem.e.o())) {
                                next.f = UpdateStatus.UPDATING_FAILURE;
                                break;
                            }
                        }
                        UpdateActivity.this.e();
                        UpdateActivity.this.f();
                        StatHelper.aj();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        int size = this.mUpdateItemList.size();
        for (int i = 0; i < size; i++) {
            BaseUpdateItem baseUpdateItem = this.mUpdateItemList.get(i);
            if (baseUpdateItem instanceof FirmwareUpdateItem) {
                FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) baseUpdateItem;
                if (!(firmwareUpdateItem.b == UpdateStatus.UPDATING || firmwareUpdateItem.b == UpdateStatus.UPDATING_SUCCESS)) {
                    if ("downloaded".equals(firmwareUpdateItem.n)) {
                        b(firmwareUpdateItem);
                    } else {
                        c(firmwareUpdateItem);
                    }
                }
            } else if (baseUpdateItem instanceof SoftwareUpdateItem) {
                SoftwareUpdateItem softwareUpdateItem = (SoftwareUpdateItem) baseUpdateItem;
                if (!(softwareUpdateItem.b == UpdateStatus.UPDATING || softwareUpdateItem.b == UpdateStatus.UPDATING_SUCCESS)) {
                    softwareUpdateItem.b = UpdateStatus.UPDATING;
                    if (softwareUpdateItem.c()) {
                        a(softwareUpdateItem.d);
                    }
                    if (softwareUpdateItem.d()) {
                        int size2 = softwareUpdateItem.e.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            a(softwareUpdateItem.e.get(i2));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        boolean z;
        int i;
        boolean z2 = false;
        float f2 = 0.0f;
        if (this.e.c()) {
            AppUpdateItem appUpdateItem = this.e.d;
            if (appUpdateItem.f == UpdateStatus.UPDATING) {
                z2 = true;
            } else if (appUpdateItem.f == UpdateStatus.UPDATING_FAILURE) {
                z = true;
                f2 = 0.0f + appUpdateItem.g;
                i = 1;
            }
            z = false;
            f2 = 0.0f + appUpdateItem.g;
            i = 1;
        } else {
            i = 0;
            z = false;
        }
        if (this.e.d()) {
            for (PluginUpdateItem next : this.e.e) {
                if (next.f == UpdateStatus.UPDATING) {
                    z2 = true;
                } else if (next.f == UpdateStatus.UPDATING_FAILURE) {
                    z = true;
                }
                i++;
                f2 += next.g;
            }
        }
        if (z2) {
            this.e.b = UpdateStatus.UPDATING;
        } else if (z) {
            if (this.e.c() && this.e.d.f == UpdateStatus.UPDATING_SUCCESS) {
                installApk(this.e.d.h);
            }
            this.e.b = UpdateStatus.UPDATING_FAILURE;
        } else {
            if (this.e.c() && this.e.d.f == UpdateStatus.UPDATING_SUCCESS) {
                installApk(this.e.d.h);
            }
            this.e.b = UpdateStatus.UPDATING_SUCCESS;
        }
        this.e.c = f2 / ((float) i);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 10001 && i2 == 0) {
            try {
                this.e.d.f = UpdateStatus.UPDATING_FAILURE;
                e();
                f();
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (!this.mFirmwareChecked || !this.mAppChecked || !this.mPluginChecked) {
            this.mCheckingUpdateLayer.setVisibility(0);
            this.mHasUpdateLayer.setVisibility(8);
            this.mNoneUpdateLayer.setVisibility(8);
            return;
        }
        this.mCheckingUpdateLayer.setVisibility(8);
        if (this.mUpdateItemList == null || this.mUpdateItemList.size() <= 0) {
            this.mHasUpdateLayer.setVisibility(8);
            this.mNoneUpdateLayer.setVisibility(0);
        } else {
            boolean z = true;
            boolean z2 = true;
            for (BaseUpdateItem next : this.mUpdateItemList) {
                if (next instanceof FirmwareUpdateItem) {
                    FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) next;
                    if (firmwareUpdateItem.a() != UpdateStatus.PENDING) {
                        if (firmwareUpdateItem.a() != UpdateStatus.UPDATING_SUCCESS) {
                            if (firmwareUpdateItem.a() != UpdateStatus.UPDATING_FAILURE) {
                                if (firmwareUpdateItem.a() == UpdateStatus.UPDATING) {
                                    z = false;
                                }
                            }
                        }
                    }
                    z = false;
                    z2 = false;
                } else if (next instanceof SoftwareUpdateItem) {
                    SoftwareUpdateItem softwareUpdateItem = (SoftwareUpdateItem) next;
                    if (softwareUpdateItem.c()) {
                        if (softwareUpdateItem.d.f != UpdateStatus.PENDING) {
                            if (softwareUpdateItem.d.f != UpdateStatus.UPDATING_SUCCESS) {
                                if (softwareUpdateItem.d.f != UpdateStatus.UPDATING_FAILURE) {
                                    if (softwareUpdateItem.d.f == UpdateStatus.UPDATING) {
                                        z = false;
                                    }
                                }
                            }
                        }
                        z = false;
                        z2 = false;
                    }
                    if (softwareUpdateItem.d()) {
                        for (PluginUpdateItem next2 : softwareUpdateItem.e) {
                            if (next2.f != UpdateStatus.PENDING) {
                                if (next2.f != UpdateStatus.UPDATING_SUCCESS) {
                                    if (next2.f != UpdateStatus.UPDATING_FAILURE) {
                                        if (next2.f == UpdateStatus.UPDATING) {
                                            z = false;
                                        }
                                    }
                                }
                            }
                            z = false;
                            z2 = false;
                        }
                    }
                }
            }
            if (z) {
                this.mFooter.setVisibility(8);
                this.mUpdateAllButton.setEnabled(false);
                this.mUpdateAllButton.setVisibility(4);
            } else if (z2) {
                this.mFooter.setVisibility(0);
                this.mUpdateAllButton.setEnabled(false);
                this.mUpdateAllButton.setVisibility(0);
                this.mUpdateAllButton.setText(R.string.update_all_updating);
                this.mUpdateAllButton.setTextColor(getResources().getColor(R.color.gray));
                this.mUpdateAllButton.setAlpha(0.5f);
            } else {
                this.mFooter.setVisibility(0);
                this.mUpdateAllButton.setEnabled(true);
                this.mUpdateAllButton.setVisibility(0);
                this.mUpdateAllButton.setText(R.string.update_all);
                this.mUpdateAllButton.setTextColor(getResources().getColor(R.color.white));
                this.mUpdateAllButton.setAlpha(1.0f);
            }
            this.mHasUpdateLayer.setVisibility(0);
            this.mNoneUpdateLayer.setVisibility(8);
        }
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void a(final NoneWifiConfirmCallback noneWifiConfirmCallback) {
        this.f = new MLAlertDialog.Builder(this.d).b((CharSequence) getString(R.string.update_none_wifi_confirm)).a((int) R.string.update_none_wifi_confirm_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (noneWifiConfirmCallback != null) {
                    noneWifiConfirmCallback.a();
                }
            }
        }).b((int) R.string.update_none_wifi_confirm_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (noneWifiConfirmCallback != null) {
                    noneWifiConfirmCallback.b();
                }
            }
        }).b();
        this.f.show();
    }

    static abstract class BaseUpdateItem {

        /* renamed from: a  reason: collision with root package name */
        boolean f17831a = true;

        /* access modifiers changed from: package-private */
        public abstract UpdateStatus a();

        /* access modifiers changed from: package-private */
        public abstract float b();

        BaseUpdateItem() {
        }
    }

    static class FirmwareUpdateItem extends BaseUpdateItem {
        UpdateStatus b = UpdateStatus.PENDING;
        float c = 0.0f;
        boolean d = false;
        String e;
        String f;
        String g;
        int h;
        boolean i;
        String j;
        String k;
        String l;
        int m;
        String n;

        FirmwareUpdateItem() {
        }

        /* access modifiers changed from: package-private */
        public UpdateStatus a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public float b() {
            return this.c;
        }
    }

    static class SoftwareUpdateItem extends BaseUpdateItem {
        UpdateStatus b = UpdateStatus.PENDING;
        float c = 0.0f;
        AppUpdateItem d;
        List<PluginUpdateItem> e = new ArrayList();

        SoftwareUpdateItem() {
        }

        /* access modifiers changed from: package-private */
        public UpdateStatus a() {
            return this.b;
        }

        /* access modifiers changed from: package-private */
        public float b() {
            return this.c;
        }

        /* access modifiers changed from: package-private */
        public boolean c() {
            return this.d != null;
        }

        /* access modifiers changed from: package-private */
        public boolean d() {
            return this.e != null && this.e.size() > 0;
        }
    }

    static class AppUpdateItem {

        /* renamed from: a  reason: collision with root package name */
        int f17830a;
        String b;
        String c;
        long d;
        String e;
        UpdateStatus f = UpdateStatus.PENDING;
        float g;
        String h;

        AppUpdateItem() {
        }
    }

    static class PluginUpdateItem {

        /* renamed from: a  reason: collision with root package name */
        long f17833a;
        int b;
        String c;
        String d;
        PluginRecord e;
        UpdateStatus f = UpdateStatus.PENDING;
        float g;

        PluginUpdateItem() {
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f17838a;
        TextView b;
        SimpleDraweeView c;
        TextView d;
        TextView e;
        TextView f;
        Button g;
        PieProgressBar h;
        TextView i;
        TextView j;
        ImageView k;
        ProgressBar l;
        TextView m;

        ViewHolder() {
        }
    }

    class SimpleAdapter extends BaseAdapter implements UpdateItemHelper.FirmwareUpdateStatusListener {
        public long getItemId(int i) {
            return (long) i;
        }

        public SimpleAdapter() {
            UpdateItemHelper.a().a((UpdateItemHelper.FirmwareUpdateStatusListener) this);
        }

        public int getCount() {
            return UpdateActivity.this.mUpdateItemList.size();
        }

        public Object getItem(int i) {
            return UpdateActivity.this.mUpdateItemList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            View view2;
            Device device;
            int i2 = i;
            String str = null;
            int i3 = 0;
            if (view == null) {
                view2 = UpdateActivity.this.getLayoutInflater().inflate(R.layout.update_activity_update_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f17838a = view2.findViewById(R.id.category_divider);
                viewHolder.b = (TextView) view2.findViewById(R.id.category);
                viewHolder.c = (SimpleDraweeView) view2.findViewById(R.id.icon);
                viewHolder.d = (TextView) view2.findViewById(R.id.title);
                viewHolder.e = (TextView) view2.findViewById(R.id.sub_title_1);
                viewHolder.f = (TextView) view2.findViewById(R.id.sub_title_2);
                viewHolder.g = (Button) view2.findViewById(R.id.update_btn);
                viewHolder.h = (PieProgressBar) view2.findViewById(R.id.update_progress);
                viewHolder.i = (TextView) view2.findViewById(R.id.update_percent);
                viewHolder.h.setPercentView(viewHolder.i);
                viewHolder.j = (TextView) view2.findViewById(R.id.change_log);
                viewHolder.k = (ImageView) view2.findViewById(R.id.fold);
                viewHolder.l = (ProgressBar) view2.findViewById(R.id.installing_progress_bar);
                viewHolder.m = (TextView) view2.findViewById(R.id.installing_progress_info);
                view2.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f17838a.setVisibility(8);
                viewHolder.b.setText("");
                viewHolder.b.setVisibility(8);
                viewHolder.c.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone_no));
                viewHolder.d.setText("");
                viewHolder.e.setText("");
                viewHolder.f.setText("");
                viewHolder.g.setText("");
                viewHolder.g.setBackgroundResource(R.drawable.std_btn_update);
                viewHolder.g.setTextColor(UpdateActivity.this.getResources().getColor(R.color.white));
                viewHolder.h.setPercent(0.0f);
                viewHolder.i.setText("");
                viewHolder.j.setText("");
                viewHolder.k.setTag((Object) null);
                viewHolder.l.setVisibility(8);
                viewHolder.m.setVisibility(8);
                view2 = view;
            }
            BaseUpdateItem baseUpdateItem = UpdateActivity.this.mUpdateItemList.get(i2);
            if (baseUpdateItem.a() == UpdateStatus.PENDING) {
                viewHolder.g.setVisibility(0);
                viewHolder.h.setVisibility(8);
                viewHolder.i.setVisibility(8);
                viewHolder.g.setText(UpdateActivity.this.getString(R.string.update_immediately));
            } else if (baseUpdateItem.a() == UpdateStatus.UPDATING) {
                viewHolder.g.setVisibility(4);
                viewHolder.h.setVisibility(0);
                viewHolder.i.setVisibility(0);
                viewHolder.h.setPercent(baseUpdateItem.b() * 100.0f);
            } else if (baseUpdateItem.a() == UpdateStatus.UPDATING_SUCCESS) {
                viewHolder.g.setVisibility(0);
                viewHolder.h.setVisibility(8);
                viewHolder.i.setVisibility(8);
                if (baseUpdateItem instanceof FirmwareUpdateItem) {
                    viewHolder.g.setBackgroundResource(R.drawable.std_btn_update_success);
                    viewHolder.g.setTextColor(UpdateActivity.this.getResources().getColor(R.color.class_text_28));
                    viewHolder.g.setText(UpdateActivity.this.getString(R.string.update_success_open));
                } else {
                    viewHolder.g.setBackgroundResource(R.drawable.common_btn_not_cllicked);
                    viewHolder.g.setTextColor(UpdateActivity.this.getResources().getColor(R.color.gray));
                    viewHolder.g.setText(UpdateActivity.this.getString(R.string.update_success));
                }
            } else if (baseUpdateItem.a() == UpdateStatus.UPDATING_FAILURE) {
                viewHolder.g.setVisibility(0);
                viewHolder.h.setVisibility(8);
                viewHolder.i.setVisibility(8);
                viewHolder.g.setText(UpdateActivity.this.getString(R.string.update_failure));
            }
            viewHolder.g.setTag(baseUpdateItem);
            viewHolder.g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Object tag = view.getTag();
                    if (tag instanceof FirmwareUpdateItem) {
                        FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) tag;
                        if (firmwareUpdateItem.b != UpdateStatus.UPDATING) {
                            if (firmwareUpdateItem.b == UpdateStatus.UPDATING_SUCCESS) {
                                UpdateActivity.this.a(firmwareUpdateItem);
                                return;
                            }
                            if ("downloaded".equals(firmwareUpdateItem.n)) {
                                UpdateActivity.this.b(firmwareUpdateItem);
                            } else {
                                UpdateActivity.this.c(firmwareUpdateItem);
                            }
                            UpdateActivity.this.f();
                            StatHelper.af();
                        }
                    } else if (tag instanceof SoftwareUpdateItem) {
                        final SoftwareUpdateItem softwareUpdateItem = (SoftwareUpdateItem) tag;
                        if (softwareUpdateItem.b != UpdateStatus.UPDATING && softwareUpdateItem.b != UpdateStatus.UPDATING_SUCCESS) {
                            if (softwareUpdateItem.c()) {
                                if (NetworkManager.a().e() || !(softwareUpdateItem.d.f == UpdateStatus.PENDING || softwareUpdateItem.d.f == UpdateStatus.UPDATING_FAILURE)) {
                                    softwareUpdateItem.b = UpdateStatus.UPDATING;
                                    UpdateActivity.this.a(softwareUpdateItem.d);
                                    UpdateActivity.this.f();
                                    StatHelper.ag();
                                } else {
                                    UpdateActivity.this.a((NoneWifiConfirmCallback) new NoneWifiConfirmCallback() {
                                        public void b() {
                                        }

                                        public void a() {
                                            softwareUpdateItem.b = UpdateStatus.UPDATING;
                                            UpdateActivity.this.a(softwareUpdateItem.d);
                                            UpdateActivity.this.f();
                                            StatHelper.ag();
                                        }
                                    });
                                }
                            }
                            if (softwareUpdateItem.d()) {
                                int size = softwareUpdateItem.e.size();
                                boolean z = !NetworkManager.a().e();
                                for (int i = 0; i < size; i++) {
                                    final PluginUpdateItem pluginUpdateItem = softwareUpdateItem.e.get(i);
                                    if (!z || pluginUpdateItem.f != UpdateStatus.PENDING) {
                                        softwareUpdateItem.b = UpdateStatus.UPDATING;
                                        UpdateActivity.this.a(pluginUpdateItem);
                                        UpdateActivity.this.f();
                                        StatHelper.ah();
                                    } else {
                                        UpdateActivity.this.a((NoneWifiConfirmCallback) new NoneWifiConfirmCallback() {
                                            public void b() {
                                            }

                                            public void a() {
                                                softwareUpdateItem.b = UpdateStatus.UPDATING;
                                                UpdateActivity.this.a(pluginUpdateItem);
                                                UpdateActivity.this.f();
                                                StatHelper.ah();
                                            }
                                        });
                                        z = false;
                                    }
                                }
                            }
                        }
                    }
                }
            });
            int i4 = 1;
            if (baseUpdateItem instanceof FirmwareUpdateItem) {
                if ((UpdateActivity.this.e != null && i2 == 1) || (UpdateActivity.this.e == null && i2 == 0)) {
                    viewHolder.b.setVisibility(0);
                    viewHolder.b.setText(UpdateActivity.this.getString(R.string.update_firmware));
                    viewHolder.f17838a.setVisibility(0);
                }
                FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) baseUpdateItem;
                String str2 = firmwareUpdateItem.f;
                DeviceFactory.b(firmwareUpdateItem.e, viewHolder.c);
                if (!TextUtils.isEmpty(str2)) {
                    viewHolder.d.setText(str2);
                } else {
                    viewHolder.d.setText(UpdateActivity.this.getString(R.string.update_unknown_device));
                }
                if (baseUpdateItem.a() != UpdateStatus.UPDATING_SUCCESS) {
                    viewHolder.e.setText(UpdateActivity.this.getString(R.string.list_item_curr_version) + ": " + firmwareUpdateItem.j);
                    viewHolder.f.setText(UpdateActivity.this.getString(R.string.list_item_latest_version) + ": " + firmwareUpdateItem.k);
                } else {
                    viewHolder.e.setText(UpdateActivity.this.getString(R.string.list_item_curr_version) + ": " + firmwareUpdateItem.k);
                    viewHolder.f.setText("");
                }
                viewHolder.j.setText(firmwareUpdateItem.l);
            } else if (baseUpdateItem instanceof SoftwareUpdateItem) {
                viewHolder.b.setVisibility(0);
                viewHolder.b.setText(UpdateActivity.this.getString(R.string.update_software));
                SoftwareUpdateItem softwareUpdateItem = (SoftwareUpdateItem) baseUpdateItem;
                if (softwareUpdateItem.c()) {
                    viewHolder.c.setImageResource(R.drawable.ic_launcher);
                    viewHolder.d.setText(UpdateActivity.this.getString(R.string.app_name));
                    viewHolder.e.setText(UpdateActivity.this.getString(R.string.list_item_curr_version) + ": " + SystemApi.a().f(UpdateActivity.this.d));
                    viewHolder.f.setText(UpdateActivity.this.getString(R.string.list_item_latest_version) + ": " + softwareUpdateItem.d.b);
                } else {
                    int size = softwareUpdateItem.e != null ? softwareUpdateItem.e.size() : 0;
                    if (size == 1) {
                        PluginUpdateItem pluginUpdateItem = softwareUpdateItem.e.get(0);
                        String t = pluginUpdateItem.e.t();
                        String p = pluginUpdateItem.e.p();
                        if (!TextUtils.isEmpty(t)) {
                            viewHolder.c.setImageURI(Uri.parse(t));
                        } else {
                            viewHolder.c.setImageURI(CommonUtils.c((int) R.drawable.icon_plugin_update));
                        }
                        viewHolder.d.setText(String.format(UpdateActivity.this.getString(R.string.plugin_update_only_one_desc), new Object[]{p}));
                    } else {
                        viewHolder.c.setImageResource(R.drawable.icon_plugin_update);
                        viewHolder.d.setText(UpdateActivity.this.getResources().getQuantityString(R.plurals.list_item_plugin_only, size, new Object[]{Integer.valueOf(size)}));
                    }
                    viewHolder.e.setVisibility(8);
                    viewHolder.f.setVisibility(8);
                }
                String str3 = "";
                if (softwareUpdateItem.c()) {
                    str3 = str3 + UpdateActivity.this.getString(R.string.app_name) + ":\n" + softwareUpdateItem.d.e;
                }
                if (softwareUpdateItem.d()) {
                    SparseArray sparseArray = new SparseArray();
                    int size2 = softwareUpdateItem.e.size();
                    for (int i5 = 0; i5 < size2; i5++) {
                        PluginUpdateItem pluginUpdateItem2 = softwareUpdateItem.e.get(i5);
                        List list = (List) sparseArray.get((int) pluginUpdateItem2.f17833a);
                        if (list == null) {
                            list = new ArrayList();
                        }
                        list.add(pluginUpdateItem2);
                        sparseArray.put((int) pluginUpdateItem2.f17833a, list);
                    }
                    String str4 = str3;
                    int i6 = 0;
                    while (i6 < sparseArray.size()) {
                        List list2 = (List) sparseArray.valueAt(i6);
                        if (!(list2 == null || list2.size() == 0)) {
                            if (list2.size() == i4) {
                                PluginUpdateItem pluginUpdateItem3 = (PluginUpdateItem) list2.get(i3);
                                if (i6 != 0 || softwareUpdateItem.c()) {
                                    str4 = str4 + "\n\n" + pluginUpdateItem3.e.p() + ":\n" + pluginUpdateItem3.d;
                                } else {
                                    str4 = str4 + pluginUpdateItem3.e.p() + ":\n" + pluginUpdateItem3.d;
                                }
                            } else {
                                String str5 = str;
                                for (int i7 = 0; i7 < list2.size(); i7++) {
                                    PluginUpdateItem pluginUpdateItem4 = (PluginUpdateItem) list2.get(i7);
                                    List<Device> d = SmartHomeDeviceManager.a().d();
                                    int i8 = 0;
                                    while (true) {
                                        if (i8 >= d.size()) {
                                            device = null;
                                            break;
                                        }
                                        device = d.get(i8);
                                        if (!TextUtils.equals(pluginUpdateItem4.c, device.model)) {
                                            break;
                                        }
                                        i8++;
                                    }
                                    if (device != null) {
                                        StringBuilder sb = new StringBuilder();
                                        sb.append(device.name);
                                        sb.append(UpdateActivity.this.getResources().getQuantityString(R.plurals.more_device, list2.size(), new Object[]{list2.size() + ""}));
                                        str5 = sb.toString();
                                    }
                                }
                                if (!TextUtils.isEmpty(str5)) {
                                    if (i6 != 0 || softwareUpdateItem.c()) {
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append(str4);
                                        sb2.append("\n\n");
                                        sb2.append(str5);
                                        sb2.append("\n");
                                        i3 = 0;
                                        sb2.append(((PluginUpdateItem) list2.get(0)).d);
                                        str4 = sb2.toString();
                                    } else {
                                        str4 = str4 + str5 + "\n" + ((PluginUpdateItem) list2.get(0)).d;
                                    }
                                }
                                i3 = 0;
                            }
                        }
                        i6++;
                        str = null;
                        i4 = 1;
                    }
                    str3 = str4;
                }
                viewHolder.j.setText(str3);
            }
            return view2;
        }

        public void a(String str, int i) {
            for (BaseUpdateItem next : UpdateActivity.this.mUpdateItemList) {
                if (next instanceof FirmwareUpdateItem) {
                    FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) next;
                    if (firmwareUpdateItem.g.equals(str)) {
                        if (!(firmwareUpdateItem.b == UpdateStatus.UPDATING_FAILURE || firmwareUpdateItem.b == UpdateStatus.UPDATING_SUCCESS)) {
                            firmwareUpdateItem.b = UpdateStatus.UPDATING;
                            double d = (double) i;
                            Double.isNaN(d);
                            firmwareUpdateItem.c = (float) (d / 100.0d);
                        }
                        UpdateActivity.this.f();
                        return;
                    }
                }
            }
        }

        public void a(String str) {
            for (BaseUpdateItem next : UpdateActivity.this.mUpdateItemList) {
                if (next instanceof FirmwareUpdateItem) {
                    FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) next;
                    if (firmwareUpdateItem.g.equals(str)) {
                        if (!(firmwareUpdateItem.b == UpdateStatus.UPDATING_FAILURE || firmwareUpdateItem.b == UpdateStatus.UPDATING_SUCCESS)) {
                            firmwareUpdateItem.b = UpdateStatus.UPDATING_SUCCESS;
                        }
                        UpdateActivity.this.f();
                        return;
                    }
                }
            }
        }

        public void b(String str) {
            for (BaseUpdateItem next : UpdateActivity.this.mUpdateItemList) {
                if (next instanceof FirmwareUpdateItem) {
                    FirmwareUpdateItem firmwareUpdateItem = (FirmwareUpdateItem) next;
                    if (firmwareUpdateItem.g.equals(str)) {
                        firmwareUpdateItem.b = UpdateStatus.UPDATING_FAILURE;
                        UpdateActivity.this.f();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.isDestroyed = true;
        UpdateItemHelper.a().a(false);
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
    }
}
