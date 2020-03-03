package com.xiaomi.smarthome.light.group;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mijia.model.property.CameraPropertyBase;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.BleMeshFirmwareUpdateInfoV2;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.file.FileUtils;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.update.ui.UpdateActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.widget.ProgressButton;
import com.xiaomi.smarthome.light.group.LightGroupMemberUpdateActivity;
import com.xiaomi.smarthome.newui.widget.ExpandableTextView;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

public class LightGroupMemberUpdateActivity extends BaseActivity {
    public static final String KEY_MEMBER = "members";
    public static final String KEY_NO_BLE_MESH_DEVICES = "key_no_ble_mesh_devices";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f19147a = "LightGroupMemberUpdateActivity";
    private static final String b = "no_need_to_update";
    /* access modifiers changed from: private */
    public List<BleFirmwareUpdateItem> c = new ArrayList();
    private ListView d;
    private SimpleAdapter e;
    private ArrayList<BleMeshFirmwareUpdateInfoV2> f;
    private ArrayList<KeyValuePair> g;
    private BleFirmwareUpdateItem h;
    private TextView i;
    private TextView j;
    /* access modifiers changed from: private */
    public CompositeDisposable k = new CompositeDisposable();
    private View l;
    private AtomicBoolean m = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean n = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean o = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public AtomicBoolean p = new AtomicBoolean(false);
    private PowerManager.WakeLock q;

    enum UpdateStatus {
        PENDING,
        WATTING,
        UPDATING,
        UPDATING_SUCCESS,
        UPDATING_FAILURE
    }

    public static void open(Activity activity, List<BleMeshFirmwareUpdateInfoV2> list, ArrayList<KeyValuePair> arrayList) {
        if (activity != null && list != null) {
            Intent intent = new Intent();
            intent.setClass(activity, LightGroupMemberUpdateActivity.class);
            intent.putExtra(KEY_MEMBER, (Serializable) list);
            intent.putParcelableArrayListExtra(KEY_NO_BLE_MESH_DEVICES, arrayList);
            activity.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_group_member_update);
        Intent intent = getIntent();
        this.f = (ArrayList) intent.getSerializableExtra(KEY_MEMBER);
        this.g = intent.getParcelableArrayListExtra(KEY_NO_BLE_MESH_DEVICES);
        a();
        b();
        try {
            STAT.d.bm(SmartHomeDeviceManager.a().c(this.f.get(0).did).model);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void onBackPressed() {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        LogUtil.a(f19147a, "onPause: ");
        this.n.set(false);
        this.m.set(false);
        if (this.q != null) {
            this.q.release();
            this.q = null;
        }
        if (this.o.get() && this.h != null) {
            LogUtil.a(f19147a, "cancelBleMeshUpgrade: ");
            XmBluetoothManager.getInstance().cancelBleMeshUpgrade(this.h.g);
        }
        if (this.p.get() && this.h != null) {
            LogUtil.a(f19147a, "cancelFirmwareLoading: ");
            XmPluginHostApi.instance().cancelDownloadBleFirmware(this.h.k);
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        LogUtil.a(f19147a, "onDestroy: ");
        super.onDestroy();
        this.k.clear();
    }

    private void a() {
        Iterator<BleMeshFirmwareUpdateInfoV2> it = this.f.iterator();
        while (it.hasNext()) {
            BleMeshFirmwareUpdateInfoV2 next = it.next();
            Device c2 = SmartHomeDeviceManager.a().c(next.did);
            if (c2 != null) {
                BleFirmwareUpdateItem bleFirmwareUpdateItem = new BleFirmwareUpdateItem();
                bleFirmwareUpdateItem.c = c2.model;
                bleFirmwareUpdateItem.d = c2.name;
                bleFirmwareUpdateItem.e = c2.did;
                bleFirmwareUpdateItem.f = c2.pid;
                bleFirmwareUpdateItem.g = c2.mac;
                bleFirmwareUpdateItem.h = next.currentVersion;
                bleFirmwareUpdateItem.i = next.version;
                bleFirmwareUpdateItem.j = next.changeLog;
                bleFirmwareUpdateItem.k = next.safeUrl;
                bleFirmwareUpdateItem.l = next.uploadTime;
                this.c.add(bleFirmwareUpdateItem);
            }
        }
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.update_firmware);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupMemberUpdateActivity.this.c(view);
            }
        });
        this.i = (TextView) findViewById(R.id.onekey_upgrade);
        this.i.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupMemberUpdateActivity.this.b(view);
            }
        });
        int i2 = 0;
        this.i.setVisibility((this.f == null || this.f.isEmpty()) ? 4 : 0);
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        this.d = (ListView) findViewById(R.id.listview);
        c();
        d();
        this.e = new SimpleAdapter();
        this.d.setAdapter(this.e);
        this.l = findViewById(R.id.more_ota_device);
        this.l.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupMemberUpdateActivity.this.a(view);
            }
        });
        View view = this.l;
        if (this.g == null || this.g.isEmpty()) {
            i2 = 4;
        }
        view.setVisibility(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        f();
        try {
            STAT.d.bm(SmartHomeDeviceManager.a().c(this.f.get(0).did).model);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        UpdateActivity.start(getContext(), this.g);
    }

    private void c() {
        this.j = (TextView) findViewById(R.id.category);
        int size = this.c.size();
        this.j.setText(XMStringUtils.a((Context) this, (int) R.plurals.fireware_update_size, size, (Object) Integer.valueOf(size)));
        if (this.d == null) {
            this.j.setVisibility(8);
        }
    }

    private void d() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.update_ble_mesh_update_header, this.d, false);
        inflate.setBackgroundResource(R.color.home_scene_activity_item_bg);
        ((TextView) inflate.findViewById(R.id.category)).setText(R.string.ble_rssi_match_line1);
        if (this.d == null) {
            inflate.setVisibility(8);
        }
        this.d.addFooterView(inflate);
    }

    private void e() {
        int i2 = 0;
        for (BleFirmwareUpdateItem a2 : this.c) {
            if (a2.a() == UpdateStatus.UPDATING_SUCCESS) {
                i2++;
            }
        }
        this.j.setTextColor(getResources().getColor(R.color.class_text_17));
        this.j.setText(i2 + "/" + XMStringUtils.a((Context) this, (int) R.plurals.fireware_update_succ_size, this.c.size(), (Object) Integer.valueOf(this.c.size())));
    }

    private void f() {
        LogUtil.a(f19147a, "oneKeyUpgrade: ");
        if (!this.m.get()) {
            this.m.set(true);
            if (this.q == null) {
                this.q = ((PowerManager) getSystemService(CameraPropertyBase.l)).newWakeLock(6, f19147a);
                this.q.acquire();
            }
            a(0);
        }
    }

    private void a(long j2) {
        if (this.m.get()) {
            LogUtil.a(f19147a, "upgradeNext: 一键更新");
            for (int i2 = 0; i2 < this.c.size(); i2++) {
                if (this.c.get(i2).a() == UpdateStatus.PENDING) {
                    requestFirmwarUpdate(i2, j2);
                    return;
                }
            }
            this.m.set(false);
        }
    }

    public void startUpgrade(final BleFirmwareUpdateItem bleFirmwareUpdateItem) {
        if (bleFirmwareUpdateItem != null && !this.n.getAndSet(true)) {
            this.i.setVisibility(4);
            this.h = bleFirmwareUpdateItem;
            for (BleFirmwareUpdateItem next : this.c) {
                if (next.a() == UpdateStatus.PENDING && !TextUtils.equals(next.e, bleFirmwareUpdateItem.e)) {
                    next.f19151a = UpdateStatus.WATTING;
                }
            }
            bleFirmwareUpdateItem.f19151a = UpdateStatus.UPDATING;
            this.e.notifyDataSetChanged();
            e();
            Observable.create(new ObservableOnSubscribe(bleFirmwareUpdateItem) {
                private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$1;

                {
                    this.f$1 = r2;
                }

                public final void subscribe(ObservableEmitter observableEmitter) {
                    LightGroupMemberUpdateActivity.this.c(this.f$1, observableEmitter);
                }
            }).flatMap(new Function(bleFirmwareUpdateItem) {
                private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$1;

                {
                    this.f$1 = r2;
                }

                public final Object apply(Object obj) {
                    return LightGroupMemberUpdateActivity.this.b(this.f$1, (Boolean) obj);
                }
            }).flatMap(new Function(bleFirmwareUpdateItem) {
                private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$1;

                {
                    this.f$1 = r2;
                }

                public final Object apply(Object obj) {
                    return LightGroupMemberUpdateActivity.this.a(this.f$1, (Boolean) obj);
                }
            }).flatMap(new Function(bleFirmwareUpdateItem) {
                private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$1;

                {
                    this.f$1 = r2;
                }

                public final Object apply(Object obj) {
                    return LightGroupMemberUpdateActivity.this.a(this.f$1, (String) obj);
                }
            }).subscribe(new Observer<Boolean>() {
                public void onSubscribe(Disposable disposable) {
                    LightGroupMemberUpdateActivity.this.k.add(disposable);
                    LogUtil.a(LightGroupMemberUpdateActivity.f19147a, "onSubscribe: ");
                }

                /* renamed from: a */
                public void onNext(Boolean bool) {
                    LogUtil.a(LightGroupMemberUpdateActivity.f19147a, "onNext: ");
                }

                public void onError(Throwable th) {
                    String access$100 = LightGroupMemberUpdateActivity.f19147a;
                    LogUtil.a(access$100, "onError: " + th.getMessage());
                    ToastUtil.a((CharSequence) th.getMessage());
                    XmBluetoothManager.getInstance().disconnect(bleFirmwareUpdateItem.g);
                    if (TextUtils.equals(th.getMessage(), LightGroupMemberUpdateActivity.b)) {
                        LightGroupMemberUpdateActivity.this.b(bleFirmwareUpdateItem);
                    } else {
                        LightGroupMemberUpdateActivity.this.a(bleFirmwareUpdateItem);
                    }
                }

                public void onComplete() {
                    LogUtil.a(LightGroupMemberUpdateActivity.f19147a, "onComplete: ");
                    XmBluetoothManager.getInstance().disconnect(bleFirmwareUpdateItem.g);
                    LightGroupMemberUpdateActivity.this.b(bleFirmwareUpdateItem);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(BleFirmwareUpdateItem bleFirmwareUpdateItem, ObservableEmitter observableEmitter) throws Exception {
        XmBluetoothManager.getInstance().bleMeshConnect(bleFirmwareUpdateItem.g, bleFirmwareUpdateItem.e, new Response.BleConnectResponse(observableEmitter, bleFirmwareUpdateItem) {
            private final /* synthetic */ ObservableEmitter f$1;
            private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onResponse(int i, Object obj) {
                LightGroupMemberUpdateActivity.this.a(this.f$1, this.f$2, i, (Bundle) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ObservableEmitter observableEmitter, BleFirmwareUpdateItem bleFirmwareUpdateItem, int i2, Bundle bundle) {
        if (!observableEmitter.isDisposed()) {
            String str = f19147a;
            LogUtil.a(str, "connectOnResponse: " + i2);
            if (!this.n.get() || i2 != 0) {
                observableEmitter.onError(new Exception("connectErr: " + i2));
                return;
            }
            bleFirmwareUpdateItem.b = 1.0f;
            a(this.c.indexOf(bleFirmwareUpdateItem));
            observableEmitter.onNext(true);
            observableEmitter.onComplete();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource b(BleFirmwareUpdateItem bleFirmwareUpdateItem, Boolean bool) throws Exception {
        return Observable.create(new ObservableOnSubscribe(bleFirmwareUpdateItem) {
            private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                LightGroupMemberUpdateActivity.this.b(this.f$1, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(BleFirmwareUpdateItem bleFirmwareUpdateItem, ObservableEmitter observableEmitter) throws Exception {
        XmBluetoothManager.getInstance().getBleMeshFirmwareVersion(bleFirmwareUpdateItem.g, new Response.BleReadFirmwareVersionResponse(observableEmitter, bleFirmwareUpdateItem) {
            private final /* synthetic */ ObservableEmitter f$1;
            private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onResponse(int i, Object obj) {
                LightGroupMemberUpdateActivity.this.a(this.f$1, this.f$2, i, (String) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ObservableEmitter observableEmitter, BleFirmwareUpdateItem bleFirmwareUpdateItem, int i2, String str) {
        if (!observableEmitter.isDisposed()) {
            String str2 = f19147a;
            LogUtil.a(str2, "getBleMeshFirmwareVersion: " + i2);
            if (!this.n.get() || i2 != 0) {
                observableEmitter.onError(new Exception("getBleMeshFirmwareVersionErr: " + i2));
                return;
            }
            String str3 = f19147a;
            LogUtil.a(str3, "getBleMeshFirmwareVersion: " + str);
            if (BluetoothHelper.a(bleFirmwareUpdateItem.i, str) > 0) {
                bleFirmwareUpdateItem.b = 2.0f;
                a(this.c.indexOf(bleFirmwareUpdateItem));
                observableEmitter.onNext(true);
                observableEmitter.onComplete();
                return;
            }
            observableEmitter.onError(new Exception(b));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource a(BleFirmwareUpdateItem bleFirmwareUpdateItem, Boolean bool) throws Exception {
        return Observable.create(new ObservableOnSubscribe(bleFirmwareUpdateItem) {
            private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$1;

            {
                this.f$1 = r2;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                LightGroupMemberUpdateActivity.this.a(this.f$1, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(final BleFirmwareUpdateItem bleFirmwareUpdateItem, final ObservableEmitter observableEmitter) throws Exception {
        this.p.set(true);
        XmPluginHostApi.instance().downloadFirmware(bleFirmwareUpdateItem.k, new Response.FirmwareUpgradeResponse() {
            public void onProgress(int i) {
                if (!observableEmitter.isDisposed()) {
                    float f = (float) (i / 10);
                    if (f > bleFirmwareUpdateItem.b) {
                        bleFirmwareUpdateItem.b = f;
                        LightGroupMemberUpdateActivity.this.a(LightGroupMemberUpdateActivity.this.c.indexOf(bleFirmwareUpdateItem));
                    }
                }
            }

            public void onResponse(int i, String str, String str2) {
                if (!observableEmitter.isDisposed()) {
                    String access$100 = LightGroupMemberUpdateActivity.f19147a;
                    LogUtil.a(access$100, "downloadBleFirmwareOnResponse: " + i);
                    String access$1002 = LightGroupMemberUpdateActivity.f19147a;
                    LogUtil.a(access$1002, "path: " + str);
                    LightGroupMemberUpdateActivity.this.p.set(false);
                    if (!LightGroupMemberUpdateActivity.this.n.get() || i != 0 || TextUtils.isEmpty(str)) {
                        ObservableEmitter observableEmitter = observableEmitter;
                        observableEmitter.onError(new Exception("downloadBleFirmwareErr: " + i));
                        return;
                    }
                    observableEmitter.onNext(str);
                    observableEmitter.onComplete();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ ObservableSource a(BleFirmwareUpdateItem bleFirmwareUpdateItem, String str) throws Exception {
        return Observable.create(new ObservableOnSubscribe(str, bleFirmwareUpdateItem) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ LightGroupMemberUpdateActivity.BleFirmwareUpdateItem f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void subscribe(ObservableEmitter observableEmitter) {
                LightGroupMemberUpdateActivity.this.a(this.f$1, this.f$2, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(final String str, final BleFirmwareUpdateItem bleFirmwareUpdateItem, final ObservableEmitter observableEmitter) throws Exception {
        if (!observableEmitter.isDisposed()) {
            if (!new File(str).exists()) {
                observableEmitter.onError(new Exception("BleMeshUpgradeErr"));
                return;
            }
            this.o.set(true);
            XmBluetoothManager.getInstance().startBleMeshUpgrade(bleFirmwareUpdateItem.g, bleFirmwareUpdateItem.e, bleFirmwareUpdateItem.h, str, new Response.BleUpgradeResponse() {
                public boolean isMeshDevice() {
                    return true;
                }

                public void onProgress(int i) {
                    if (!observableEmitter.isDisposed()) {
                        float f = (float) i;
                        if (f > bleFirmwareUpdateItem.b) {
                            bleFirmwareUpdateItem.b = f;
                            LightGroupMemberUpdateActivity.this.a(LightGroupMemberUpdateActivity.this.c.indexOf(bleFirmwareUpdateItem));
                        }
                    }
                }

                /* renamed from: a */
                public void onResponse(int i, String str) {
                    String access$100 = LightGroupMemberUpdateActivity.f19147a;
                    LogUtil.a(access$100, "BleMeshUpgradetOnResponse: " + i);
                    LightGroupMemberUpdateActivity.this.o.set(false);
                    FileUtils.d(str);
                    if (!LightGroupMemberUpdateActivity.this.n.get() || i != 0) {
                        ObservableEmitter observableEmitter = observableEmitter;
                        observableEmitter.onError(new Exception("BleMeshUpgradeErr " + str));
                        return;
                    }
                    observableEmitter.onNext(true);
                    observableEmitter.onComplete();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(BleFirmwareUpdateItem bleFirmwareUpdateItem) {
        LogUtil.a(f19147a, "upgradeFailed: ");
        bleFirmwareUpdateItem.b = 0.0f;
        bleFirmwareUpdateItem.f19151a = UpdateStatus.UPDATING_FAILURE;
        for (BleFirmwareUpdateItem next : this.c) {
            if (!TextUtils.equals(next.e, bleFirmwareUpdateItem.e) && next.a() == UpdateStatus.WATTING) {
                next.f19151a = UpdateStatus.PENDING;
            }
        }
        this.e.notifyDataSetChanged();
        this.n.getAndSet(false);
        this.h = null;
        a(2000);
    }

    /* access modifiers changed from: private */
    public void b(BleFirmwareUpdateItem bleFirmwareUpdateItem) {
        LogUtil.a(f19147a, "upgradeSuccess: ");
        bleFirmwareUpdateItem.f19151a = UpdateStatus.UPDATING_SUCCESS;
        for (BleFirmwareUpdateItem next : this.c) {
            if (!TextUtils.equals(next.e, bleFirmwareUpdateItem.e) && next.a() == UpdateStatus.WATTING) {
                next.f19151a = UpdateStatus.PENDING;
            }
        }
        this.e.notifyDataSetChanged();
        this.n.getAndSet(false);
        this.h = null;
        e();
        a(2000);
    }

    public void handleMessage(Message message) {
        String str = f19147a;
        LogUtil.a(str, "handleMessage " + message.what);
        int i2 = message.what;
        if (i2 < this.c.size()) {
            startUpgrade(this.c.get(i2));
        }
    }

    public void requestFirmwarUpdate(int i2, long j2) {
        if (i2 < this.c.size()) {
            this.mHandler.removeCallbacksAndMessages((Object) null);
            this.mHandler.sendEmptyMessageDelayed(i2, j2);
        }
    }

    class BleFirmwareUpdateItem {

        /* renamed from: a  reason: collision with root package name */
        UpdateStatus f19151a = UpdateStatus.PENDING;
        float b = 0.0f;
        String c;
        String d;
        String e;
        int f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;

        BleFirmwareUpdateItem() {
        }

        /* access modifiers changed from: package-private */
        public UpdateStatus a() {
            return this.f19151a;
        }

        /* access modifiers changed from: package-private */
        public float b() {
            return this.b;
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        int firstVisiblePosition;
        if (isValid() && i2 >= 0 && i2 < this.c.size() && (firstVisiblePosition = i2 - this.d.getFirstVisiblePosition()) >= 0) {
            this.e.a(this.d.getChildAt(firstVisiblePosition), i2);
        }
    }

    /* access modifiers changed from: private */
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(Long.parseLong(str) * 1000));
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    class SimpleAdapter extends BaseAdapter {
        private final SparseBooleanArray b = new SparseBooleanArray();

        public long getItemId(int i) {
            return (long) i;
        }

        public SimpleAdapter() {
        }

        public void a(View view, int i) {
            if (view != null) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                BleFirmwareUpdateItem bleFirmwareUpdateItem = (BleFirmwareUpdateItem) LightGroupMemberUpdateActivity.this.c.get(i);
                if (viewHolder.e.getButtonMode() != 1) {
                    viewHolder.e.setButtonMode(1);
                }
                viewHolder.e.setProgress((int) bleFirmwareUpdateItem.b());
                viewHolder.e.setText(((int) bleFirmwareUpdateItem.b()) + Operators.MOD);
            }
        }

        public int getCount() {
            return LightGroupMemberUpdateActivity.this.c.size();
        }

        public Object getItem(int i) {
            return LightGroupMemberUpdateActivity.this.c.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LightGroupMemberUpdateActivity.this.getLayoutInflater().inflate(R.layout.update_ble_mesh_update_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f19153a = view.findViewById(R.id.bottom_divider);
                viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.icon);
                viewHolder.c = (TextView) view.findViewById(R.id.title);
                viewHolder.d = (TextView) view.findViewById(R.id.sub_title_1);
                viewHolder.e = (ProgressButton) view.findViewById(R.id.update_btn);
                viewHolder.f = (ExpandableTextView) view.findViewById(R.id.expand_text_view);
                viewHolder.g = (TextView) view.findViewById(R.id.change_time);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f19153a.setVisibility(8);
                viewHolder.b.setImageURI(CommonUtils.c((int) R.drawable.device_list_phone_no));
                viewHolder.c.setText("");
                viewHolder.d.setText("");
                viewHolder.e.setText("");
                viewHolder.e.setBackgroundResource(R.drawable.ble_mesh_item_button_pending_shape);
                viewHolder.e.setButtonMode(0);
                viewHolder.e.setTextColor(LightGroupMemberUpdateActivity.this.getResources().getColor(R.color.class_text_17));
                viewHolder.f.setText("");
                viewHolder.g.setText("");
            }
            if (i == LightGroupMemberUpdateActivity.this.c.size() - 1) {
                viewHolder.f19153a.setVisibility(0);
            }
            BleFirmwareUpdateItem bleFirmwareUpdateItem = (BleFirmwareUpdateItem) LightGroupMemberUpdateActivity.this.c.get(i);
            if (!TextUtils.isEmpty(bleFirmwareUpdateItem.d)) {
                viewHolder.c.setText(bleFirmwareUpdateItem.d);
            } else {
                viewHolder.c.setText(LightGroupMemberUpdateActivity.this.getString(R.string.update_unknown_device));
            }
            viewHolder.d.setText(bleFirmwareUpdateItem.h + " → " + bleFirmwareUpdateItem.i);
            DeviceFactory.b(bleFirmwareUpdateItem.c, viewHolder.b);
            viewHolder.f.setText(LightGroupMemberUpdateActivity.this.getString(R.string.fireware_update_content) + bleFirmwareUpdateItem.j, this.b, i);
            viewHolder.g.setText(LightGroupMemberUpdateActivity.this.getString(R.string.fireware_update_time) + LightGroupMemberUpdateActivity.a(bleFirmwareUpdateItem.l));
            if (bleFirmwareUpdateItem.a() == UpdateStatus.PENDING) {
                viewHolder.e.setText(LightGroupMemberUpdateActivity.this.getString(R.string.update));
            } else if (bleFirmwareUpdateItem.a() == UpdateStatus.WATTING) {
                viewHolder.e.setTextColor(LightGroupMemberUpdateActivity.this.getResources().getColor(R.color.class_text_3));
                viewHolder.e.setBackgroundResource(R.drawable.ble_mesh_item_button_waiting_shape);
                viewHolder.e.setText(LightGroupMemberUpdateActivity.this.getString(R.string.watting));
            } else if (bleFirmwareUpdateItem.a() == UpdateStatus.UPDATING) {
                if (viewHolder.e.getButtonMode() != 1) {
                    viewHolder.e.setButtonMode(1);
                }
                viewHolder.e.setProgress((int) bleFirmwareUpdateItem.b());
                viewHolder.e.setText(((int) bleFirmwareUpdateItem.b()) + Operators.MOD);
            } else if (bleFirmwareUpdateItem.a() == UpdateStatus.UPDATING_SUCCESS) {
                viewHolder.e.setBackgroundResource(R.drawable.ble_mesh_update_button_normal_shape);
                viewHolder.e.setTextColor(LightGroupMemberUpdateActivity.this.getResources().getColor(R.color.white));
                viewHolder.e.setText(LightGroupMemberUpdateActivity.this.getString(R.string.complete));
            } else if (bleFirmwareUpdateItem.a() == UpdateStatus.UPDATING_FAILURE) {
                viewHolder.e.setText(LightGroupMemberUpdateActivity.this.getString(R.string.failed));
            }
            viewHolder.e.setTag(bleFirmwareUpdateItem);
            viewHolder.e.setOnClickListener(new View.OnClickListener(i) {
                private final /* synthetic */ int f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    LightGroupMemberUpdateActivity.SimpleAdapter.this.a(this.f$1, view);
                }
            });
            return view;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, View view) {
            BleFirmwareUpdateItem bleFirmwareUpdateItem = (BleFirmwareUpdateItem) view.getTag();
            if (bleFirmwareUpdateItem.f19151a != UpdateStatus.UPDATING && bleFirmwareUpdateItem.f19151a != UpdateStatus.UPDATING_SUCCESS && bleFirmwareUpdateItem.f19151a != UpdateStatus.WATTING) {
                if ((bleFirmwareUpdateItem.f19151a == UpdateStatus.PENDING || bleFirmwareUpdateItem.f19151a == UpdateStatus.UPDATING_FAILURE) && !LightGroupMemberUpdateActivity.this.n.get()) {
                    LightGroupMemberUpdateActivity.this.requestFirmwarUpdate(i, 0);
                }
            }
        }
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19153a;
        SimpleDraweeView b;
        TextView c;
        TextView d;
        ProgressButton e;
        ExpandableTextView f;
        TextView g;

        ViewHolder() {
        }
    }
}
