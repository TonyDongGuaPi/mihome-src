package com.xiaomi.smarthome.device.bluetooth.ui;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.log.BluetoothMyLogger;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.ManageDeviceBatchRoomActivity;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.IBleSecureConnectResponse;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.light.group.LightGroupSettingV2Activity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BleMatchListBatchActivity extends BaseActivity {
    public static final String KEY_MACS = "key_macs";
    public static final String KEY_TITLE = "key_title";
    public static final String TAG = "BleMatchListBatchActivity";
    private static AtomicBoolean h = new AtomicBoolean(false);

    /* renamed from: a  reason: collision with root package name */
    private ListView f15235a;
    private BindListAdapter b;
    private TextView c;
    /* access modifiers changed from: private */
    public List<BleBindItem> d = new ArrayList();
    private BleConnectOptions e;
    private TextView f;
    private XQProgressDialog g;
    /* access modifiers changed from: private */
    public BleBindItem i;
    private IBleSecureConnectResponse j = new IBleSecureConnectResponse() {
        public void a(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onConnectResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            LogUtilGrey.a(BleMatchListBatchActivity.TAG, String.format("BleBindActivity onConnectResponse: code = %d", new Object[]{Integer.valueOf(i)}));
        }

        public void b(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onAuthResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            LogUtilGrey.a(BleMatchListBatchActivity.TAG, String.format("BleBindActivity onAuthResponse: code = %d", new Object[]{Integer.valueOf(i)}));
        }

        public void c(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onBindResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            LogUtilGrey.a(BleMatchListBatchActivity.TAG, String.format("BleBindActivity onBindResponse: code = %d", new Object[]{Integer.valueOf(i)}));
        }

        public void d(int i, Bundle bundle) {
            BluetoothMyLogger.d(String.format("BleBindActivity onLastResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            LogUtilGrey.a(BleMatchListBatchActivity.TAG, String.format("BleBindActivity onLastResponse: code = %d", new Object[]{Integer.valueOf(i)}));
            if (i == 0) {
                BLEDeviceManager.b((Device) BleMatchListBatchActivity.this.i.c);
                BleMatchListBatchActivity.this.c(BleMatchListBatchActivity.this.i);
                return;
            }
            BleMatchListBatchActivity.this.b(BleMatchListBatchActivity.this.i);
        }
    };
    private View.OnClickListener k = new View.OnClickListener() {
        public final void onClick(View view) {
            BleMatchListBatchActivity.this.a(view);
        }
    };
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3) {
                SmartHomeDeviceManager.a().c(BleMatchListBatchActivity.this.listener);
                BleMatchListBatchActivity.this.c();
                BleMatchListBatchActivity.this.hideLoadingDialog();
            }
        }
    };

    enum UpdateStatus {
        PENDING,
        UPDATING,
        UPDATING_SUCCESS,
        UPDATING_FAILURE
    }

    public static void open(Activity activity, ArrayList<String> arrayList, String str) {
        if (activity != null && !TextUtils.isEmpty(str) && arrayList != null) {
            Intent intent = new Intent();
            intent.setClass(activity, BleMatchListBatchActivity.class);
            intent.putStringArrayListExtra(KEY_MACS, arrayList);
            intent.putExtra(KEY_TITLE, str);
            activity.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ble_match_list_batch);
        a();
        b();
        this.e = new BleConnectOptions.Builder().a(1).c(31000).b(2).d(15000).a();
        a(1000);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (h.get() && this.i != null) {
            this.i.c.l();
        }
    }

    private void a() {
        ArrayList<String> stringArrayListExtra = getIntent().getStringArrayListExtra(KEY_MACS);
        if (stringArrayListExtra == null) {
            ToastUtil.a((CharSequence) "macs == null");
            finish();
            return;
        }
        Iterator<String> it = stringArrayListExtra.iterator();
        while (it.hasNext()) {
            BleDevice d2 = BLEDeviceManager.d(it.next());
            if (d2 != null) {
                BleBindItem bleBindItem = new BleBindItem();
                bleBindItem.c = d2;
                this.d.add(bleBindItem);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (isValid()) {
            this.g = new XQProgressDialog(this);
            this.g.setMessage(getString(R.string.device_more_security_loading_operation));
            this.g.show();
        }
    }

    /* access modifiers changed from: protected */
    public void hideLoadingDialog() {
        if (this.g != null) {
            this.g.dismiss();
        }
    }

    private void b() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(getIntent().getStringExtra(KEY_TITLE));
        ((ImageView) findViewById(R.id.module_a_3_return_btn)).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BleMatchListBatchActivity.this.c(view);
            }
        });
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        this.f = (TextView) findViewById(R.id.next);
        this.f.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                BleMatchListBatchActivity.this.b(view);
            }
        });
        this.c = (TextView) findViewById(R.id.category);
        d();
        this.f15235a = (ListView) findViewById(R.id.listview);
        this.f15235a.setSelector(new ColorDrawable(0));
        this.b = new BindListAdapter();
        this.f15235a.setAdapter(this.b);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        SmartHomeDeviceManager.a().a(this.listener);
        SmartHomeDeviceManager.a().p();
        showLoadingDialog();
    }

    /* access modifiers changed from: private */
    public void c() {
        ArrayList arrayList = new ArrayList();
        for (BleBindItem next : this.d) {
            if (next.a() == UpdateStatus.UPDATING_SUCCESS) {
                Device f2 = SmartHomeDeviceManager.a().f(next.c.mac);
                if (f2 == null) {
                    String str = TAG;
                    LogUtilGrey.a(str, "find device by mac == null " + next.c.mac);
                } else {
                    arrayList.add(f2.did);
                    boolean isOnlineAdvance = f2.isOnlineAdvance();
                }
            }
        }
        if (!arrayList.isEmpty()) {
            b((ArrayList<String>) arrayList);
            return;
        }
        ToastUtil.a((CharSequence) "didlist == " + arrayList.size());
    }

    private void a(ArrayList<String> arrayList) {
        new MLAlertDialog.Builder(this).a((int) R.string.light_group_create_tips_title).b((int) R.string.light_group_not_created_yet, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(arrayList) {
            private final /* synthetic */ ArrayList f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                BleMatchListBatchActivity.this.b(this.f$1, dialogInterface, i);
            }
        }).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(arrayList) {
            private final /* synthetic */ ArrayList f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                BleMatchListBatchActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).b((CharSequence) XMStringUtils.a((Context) this, (int) R.plurals.light_group_create_tips_msg, arrayList.size(), (Object) Integer.valueOf(arrayList.size()))).d();
        try {
            STAT.c.a(arrayList.size(), this.d.get(0).c.model);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(ArrayList arrayList, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        b((ArrayList<String>) arrayList);
        STAT.d.bl();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ArrayList arrayList, DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        Intent intent = new Intent(this, LightGroupSettingV2Activity.class);
        intent.putStringArrayListExtra(LightGroupSettingV2Activity.ARGS_KEY_DID_LIST, arrayList);
        startActivity(intent);
        STAT.d.bk();
    }

    private void b(ArrayList<String> arrayList) {
        Intent intent = new Intent(this, ManageDeviceBatchRoomActivity.class);
        intent.putStringArrayListExtra(ManageDeviceBatchRoomActivity.INTENT_KEY_DID_LIST, arrayList);
        startActivity(intent);
        finish();
    }

    private void d() {
        int i2 = 0;
        int i3 = 0;
        for (BleBindItem next : this.d) {
            if (next.a() == UpdateStatus.UPDATING_SUCCESS) {
                i2++;
            }
            if (next.a() == UpdateStatus.PENDING) {
                i3++;
            }
        }
        this.c.setTextColor(getResources().getColor(R.color.class_text_17));
        this.c.setText(i2 + "/" + XMStringUtils.a((Context) this, (int) R.plurals.ble_mesh_add_succ_size, this.d.size(), (Object) Integer.valueOf(this.d.size())));
        if (i2 <= 0 || i3 != 0) {
            this.f.setEnabled(false);
            return;
        }
        SmartHomeDeviceManager.a().p();
        this.f.setEnabled(true);
    }

    public void handleMessage(Message message) {
        String str = TAG;
        LogUtilGrey.a(str, "handleMessage " + message.what);
        if (message.what < this.d.size()) {
            a(this.d.get(message.what));
        }
    }

    private void a(long j2) {
        LogUtilGrey.a(TAG, "bindNext: ");
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (this.d.get(i2).a() == UpdateStatus.PENDING) {
                this.mHandler.removeCallbacksAndMessages((Object) null);
                this.mHandler.sendEmptyMessageDelayed(i2, j2);
                return;
            }
        }
    }

    private void a(BleBindItem bleBindItem) {
        BleDevice bleDevice = bleBindItem.c;
        if (bleDevice != null && !h.getAndSet(true)) {
            this.i = bleBindItem;
            bleBindItem.f15240a = UpdateStatus.UPDATING;
            this.b.notifyDataSetChanged();
            if (a(bleDevice)) {
                BluetoothMyLogger.d("Start bleMeshBind");
                BluetoothHelper.d(bleDevice.mac, this.e, this.j);
                return;
            }
            b(bleBindItem);
        }
    }

    private boolean a(BleDevice bleDevice) {
        if (bleDevice == null) {
            return false;
        }
        PluginRecord d2 = CoreApi.a().d(bleDevice.model);
        if (d2 != null && d2.c().t() == Device.PID_BLE_MESH) {
            return true;
        }
        BleDevice d3 = BLEDeviceManager.d(bleDevice.mac);
        if ((bleDevice.d() == null || bleDevice.d().f14276a == null || !bleDevice.d().f14276a.e) && (d3 == null || d3.d() == null || d3.d().f14276a == null || !d3.d().f14276a.e)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void b(BleBindItem bleBindItem) {
        LogUtilGrey.a(TAG, "bindFailed: ");
        bleBindItem.f15240a = UpdateStatus.UPDATING_FAILURE;
        this.b.notifyDataSetChanged();
        h.getAndSet(false);
        this.i = null;
        d();
        bleBindItem.c.l();
        a(500);
    }

    /* access modifiers changed from: private */
    public void c(BleBindItem bleBindItem) {
        LogUtilGrey.a(TAG, "bindSuccess: ");
        bleBindItem.f15240a = UpdateStatus.UPDATING_SUCCESS;
        this.b.notifyDataSetChanged();
        h.getAndSet(false);
        this.i = null;
        d();
        bleBindItem.c.l();
        a(500);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (h.get()) {
            new MLAlertDialog.Builder(this).a((int) R.string.ble_new_cancel_dialog_title).b((int) R.string.ble_new_cancel_dialog_message).a((int) R.string.ble_new_cancel_dialog_ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    BleMatchListBatchActivity.this.a(dialogInterface, i);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
        } else {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        if (this.i != null) {
            this.i.c.l();
        }
        finish();
    }

    public void onBackPressed() {
        this.k.onClick((View) null);
    }

    class BleBindItem {

        /* renamed from: a  reason: collision with root package name */
        UpdateStatus f15240a = UpdateStatus.PENDING;
        float b = 0.0f;
        BleDevice c;

        BleBindItem() {
        }

        /* access modifiers changed from: package-private */
        public UpdateStatus a() {
            return this.f15240a;
        }

        /* access modifiers changed from: package-private */
        public float b() {
            return this.b;
        }
    }

    private class BindListAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private BindListAdapter() {
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f15239a;
            TextView b;
            TextView c;
            View d;
            ImageView e;
            ImageView f;
            ImageView g;

            private ViewHolder() {
            }
        }

        public int getCount() {
            if (BleMatchListBatchActivity.this.d != null) {
                return BleMatchListBatchActivity.this.d.size();
            }
            return 0;
        }

        /* renamed from: a */
        public BleBindItem getItem(int i) {
            if (i < 0 || i >= getCount()) {
                return null;
            }
            return (BleBindItem) BleMatchListBatchActivity.this.d.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(BleMatchListBatchActivity.this).inflate(R.layout.ble_bind_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f15239a = (SimpleDraweeView) view.findViewById(R.id.icon);
                viewHolder.b = (TextView) view.findViewById(R.id.name);
                viewHolder.c = (TextView) view.findViewById(R.id.mac);
                viewHolder.d = view.findViewById(R.id.content);
                viewHolder.e = (ImageView) view.findViewById(R.id.signal);
                viewHolder.f = (ImageView) view.findViewById(R.id.arrow);
                viewHolder.g = (ImageView) view.findViewById(R.id.check);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            BleBindItem a2 = getItem(i);
            viewHolder.b.setText(a2.c.name);
            viewHolder.c.setText(a2.c.mac);
            viewHolder.c.setVisibility(TextUtils.isEmpty(a2.c.mac) ? 8 : 0);
            viewHolder.f.setVisibility(8);
            viewHolder.e.setVisibility(8);
            viewHolder.g.setVisibility(0);
            if (a2.a() == UpdateStatus.PENDING) {
                viewHolder.g.setBackgroundResource(R.drawable.icon_pending);
                viewHolder.g.clearAnimation();
            } else if (a2.a() == UpdateStatus.UPDATING) {
                viewHolder.g.setBackgroundResource(R.drawable.icon_light_group_loading);
                viewHolder.g.startAnimation(AnimationUtils.loadAnimation(BleMatchListBatchActivity.this.getContext(), R.anim.rotate_infinite));
                viewHolder.g.setOnClickListener((View.OnClickListener) null);
            } else if (a2.a() == UpdateStatus.UPDATING_SUCCESS) {
                viewHolder.g.clearAnimation();
                viewHolder.g.setBackgroundResource(R.drawable.icon_light_group_checked);
            } else if (a2.a() == UpdateStatus.UPDATING_FAILURE) {
                viewHolder.g.clearAnimation();
                viewHolder.g.setBackgroundResource(R.drawable.icon_checkbox_offline);
            }
            DeviceFactory.b(a2.c.model, viewHolder.f15239a);
            return view;
        }
    }
}
