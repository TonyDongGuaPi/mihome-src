package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.xiaomi.infrared.utils.CommUtil;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.device.BleGatewayNestedScrollingParent;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.bluetooth.BleGatewayManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadListViewAdapter;
import com.xiaomi.smarthome.miio.dialog.BleGatewayMeshDialog;
import com.xiaomi.smarthome.operation.js_sdk.OperationCommonWebViewActivity;
import com.xiaomi.smarthome.scene.action.AutoSceneAction;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONObject;

public class BleGatewayActivity extends BaseActivity {
    public static final String XIAOAI_SPEAKER_APP_PACKAGE_NAME = "com.xiaomi.mico";

    /* renamed from: a  reason: collision with root package name */
    private static final String f11663a = "BleGatewayActivity";
    private static final String k = "wifispeaker";
    private List<Device> b = new ArrayList();
    private List<Device> c = new ArrayList();
    /* access modifiers changed from: private */
    public List<Device> d = new ArrayList();
    /* access modifiers changed from: private */
    public RecyclerViewAdapter e;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public Device g;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public int i = 0;
    private boolean j = false;
    /* access modifiers changed from: private */
    public XQProgressDialog l;
    @BindView(2131430969)
    ImageView mBackBt;
    @BindView(2131427859)
    CardView mBleSwitch;
    @BindView(2131427951)
    SwitchButton mBleSwitchBtn;
    @BindView(2131433201)
    TextView mBleSwitchTitle;
    @BindView(2131428503)
    View mEmptyView;
    @BindView(2131429008)
    View mEmptyViewContainer;
    @BindView(2131428501)
    TextView mFirstEmptyText;
    @BindView(2131429534)
    ImageView mHeadAnimImage;
    @BindView(2131428743)
    TextView mHeadDescView;
    @BindView(2131429540)
    SimpleDraweeView mHeadImageExtend;
    @BindView(2131429541)
    SimpleDraweeView mHeadImageShrink;
    @BindView(2131430835)
    TextView mMeshGatewayGuideView;
    @BindView(2131432051)
    BleGatewayNestedScrollingParent mNestedScrollView;
    @BindView(2131430457)
    RecyclerView mRecyclerView;
    @BindView(2131428502)
    TextView mSecondEmptyText;
    @BindView(2131430975)
    TextView mTitle;
    @BindView(2131433838)
    View mViewListLayer;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ble_gateway);
        ButterKnife.bind((Activity) this);
        l();
        m();
        if (a()) {
            b();
        }
        TitleBarUtil.b((Activity) this);
    }

    private boolean a() {
        if (this.g == null || TextUtils.isEmpty(this.g.did) || TextUtils.isEmpty(this.g.model)) {
            return false;
        }
        this.j = BleGatewayManager.a(this.g.did);
        String[] split = this.g.model.split("\\.");
        if (split.length <= 1 || !TextUtils.equals(split[1], k)) {
            return false;
        }
        return true;
    }

    private void b() {
        DeviceApi.getInstance().requestBleWifiSpeakerState(getApplicationContext(), this.g.did, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                JSONObject optJSONObject;
                if (jSONObject != null) {
                    Log.d(BleGatewayActivity.f11663a, "onSuccess: " + jSONObject.toString());
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("switch");
                if (optJSONObject2 != null && (optJSONObject = optJSONObject2.optJSONObject(BleGatewayActivity.this.g.did)) != null) {
                    boolean unused = BleGatewayActivity.this.h = optJSONObject.optBoolean(AutoSceneAction.f21495a);
                    if (BleGatewayActivity.this.h) {
                        BleGatewayActivity.this.c();
                        BleGatewayActivity.this.i();
                    }
                }
            }

            public void onFailure(Error error) {
                Log.d("BLEGateWay", "onFailure: " + error.b());
            }
        });
        e();
    }

    /* access modifiers changed from: private */
    public void c() {
        this.mBleSwitch.setVisibility(0);
        if (!this.j) {
            this.mBleSwitchTitle.setText(R.string.ble_gateway_switch_text);
        }
        d();
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.mRecyclerView.getVisibility() != 0 || this.mBleSwitchBtn.isChecked()) {
            this.mViewListLayer.setVisibility(8);
        } else {
            this.mViewListLayer.setVisibility(0);
        }
    }

    private void e() {
        this.mBleSwitchBtn.setOnTouchEnable(false);
        this.mBleSwitch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (BleGatewayActivity.this.h) {
                    if (BleGatewayActivity.this.mBleSwitchBtn.isChecked()) {
                        BleGatewayActivity.this.a(1);
                    } else {
                        BleGatewayActivity.this.j();
                    }
                } else if (BleGatewayActivity.this.i == 2) {
                    BleGatewayActivity.this.a(3);
                } else if (BleGatewayActivity.this.i == 3) {
                    BleGatewayActivity.this.a(4);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final int i2) {
        BleGatewayMeshDialog.a(i2, this.j).a((BleGatewayMeshDialog.BLEDialogClickListener) new BleGatewayMeshDialog.BLEDialogClickListener() {
            public void onClick() {
                if (i2 == 1) {
                    BleGatewayActivity.this.k();
                } else if (i2 == 4) {
                    BleGatewayActivity.this.f();
                }
            }
        }).show(getSupportFragmentManager(), (String) null);
    }

    /* access modifiers changed from: private */
    public void f() {
        try {
            if (checkApkExist(getApplicationContext(), XIAOAI_SPEAKER_APP_PACKAGE_NAME)) {
                String format = String.format("mico://homepage/main?uid=%s", new Object[]{AccountManager.a().m()});
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setPackage(XIAOAI_SPEAKER_APP_PACKAGE_NAME);
                intent.setData(Uri.parse(format));
                startActivity(intent);
                return;
            }
            a(2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean checkApkExist(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getApplicationInfo(str, 8192);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    private void g() {
        if (this.l == null) {
            this.l = new XQProgressDialog(this);
            this.l.setMessage(getString(R.string.loading));
            this.l.setCancelable(true);
        }
        this.l.show();
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.l != null) {
            getWindow().getDecorView().postDelayed(new Runnable() {
                public void run() {
                    if (BleGatewayActivity.this.l != null) {
                        BleGatewayActivity.this.l.dismiss();
                    }
                }
            }, 200);
        }
    }

    /* access modifiers changed from: private */
    public void i() {
        XmPluginHostApi.instance().callMethod(this.g.did, "bt_gateway_status", new Object[0], new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    Log.d(BleGatewayActivity.f11663a, "getmBleSwitchState onSuccess: " + jSONObject.toString());
                    if (TextUtils.equals(AutoSceneAction.f21495a, jSONObject.optString("gateway_status"))) {
                        BleGatewayActivity.this.mBleSwitchBtn.setChecked(true);
                    }
                }
            }

            public void onFailure(int i, String str) {
                Log.d(BleGatewayActivity.f11663a, "getmBleSwitchState onFailure: " + i + " " + str);
                Toast.makeText(BleGatewayActivity.this.getApplicationContext(), R.string.ble_mesh_switch_state_failed, 0).show();
            }
        }, Parser.DEFAULT_PARSER);
    }

    /* access modifiers changed from: private */
    public void j() {
        g();
        XmPluginHostApi.instance().callMethod(this.g.did, "bt_gateway_enable", new Object[0], new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                BleGatewayActivity.this.h();
                if (jSONObject != null) {
                    Log.d(BleGatewayActivity.f11663a, "getmBleSwitchState onSuccess: " + jSONObject.toString());
                }
                BleGatewayActivity.this.mBleSwitchBtn.setChecked(true);
                BleGatewayActivity.this.d();
            }

            public void onFailure(int i, String str) {
                Log.d(BleGatewayActivity.f11663a, "getmBleSwitchState onFailure: " + i + " " + str);
                BleGatewayActivity.this.h();
                Toast.makeText(BleGatewayActivity.this.getApplicationContext(), R.string.ble_mesh_toast_failed, 0).show();
            }
        }, Parser.DEFAULT_PARSER);
    }

    /* access modifiers changed from: private */
    public void k() {
        g();
        XmPluginHostApi.instance().callMethod(this.g.did, "bt_gateway_disable", new Object[0], new Callback<JSONObject>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                BleGatewayActivity.this.h();
                if (jSONObject != null) {
                    Log.d(BleGatewayActivity.f11663a, "getmBleSwitchState onSuccess: " + jSONObject.toString());
                }
                BleGatewayActivity.this.mBleSwitchBtn.setChecked(false);
                BleGatewayActivity.this.d();
            }

            public void onFailure(int i, String str) {
                Log.d(BleGatewayActivity.f11663a, "getmBleSwitchState onFailure: " + i + " " + str);
                BleGatewayActivity.this.h();
                Toast.makeText(BleGatewayActivity.this.getApplicationContext(), R.string.ble_mesh_toast_failed, 0).show();
            }
        }, Parser.DEFAULT_PARSER);
    }

    private void l() {
        this.mEmptyView.setVisibility(0);
        if (CoreApi.a().D()) {
            this.mFirstEmptyText.setText(R.string.miio_ble_gateway_scan_empty_international);
        } else {
            this.mFirstEmptyText.setText(R.string.miio_ble_gateway_scan_empty);
            this.mSecondEmptyText.setVisibility(0);
            this.mSecondEmptyText.setTextColor(Color.parseColor(CloudVideoDownloadListViewAdapter.COLOR_CLASS_S));
            this.mSecondEmptyText.setText(R.string.miio_find_ble_device_in_store);
            this.mSecondEmptyText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    UrlDispatchManger.a().c("https://home.mi.com/app/shop/content?id=vae2ad03985fd3bd7");
                }
            });
        }
        this.mBackBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleGatewayActivity.this.finish();
            }
        });
        this.mBackBt.setColorFilter(-1);
        this.e = new RecyclerViewAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.e);
        o();
        this.mNestedScrollView.setOnRefreshListener(new BleGatewayNestedScrollingParent.RefreshListener() {
            public void a() {
                BleGatewayActivity.this.n();
            }
        });
        this.mMeshGatewayGuideView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SHApplication.getAppContext(), OperationCommonWebViewActivity.class);
                intent.putExtra("url", CommUtil.a() ? "https://home.mi.com/app_webview/newoperation/index.html#/article?articleId=898437912000000001" : "https://home.mi.com/app_webview/newoperation/index.html#/article?articleId=100147623000000001");
                intent.putExtra("title", BleGatewayActivity.this.getResources().getString(R.string.mesh_gateway_guide));
                intent.setFlags(C.ENCODING_PCM_MU_LAW);
                try {
                    SHApplication.getAppContext().startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void m() {
        ArrayList arrayList;
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra(BleGatewayListActivity.KEY_GATEWAY_DID);
            arrayList = getIntent().getParcelableArrayListExtra(BleGatewayListActivity.KEY_GATEWAY_ITEMS);
            this.g = SmartHomeDeviceManager.a().b(stringExtra);
        } else {
            arrayList = null;
        }
        if (this.g == null) {
            finish();
            return;
        }
        if (BleGatewayManager.a(this.g.did)) {
            this.mHeadDescView.setText(R.string.mesh_gateway_desc);
        }
        this.mTitle.setText(this.g.name);
        DeviceFactory.a(this.g.model, this.mHeadImageExtend, (int) R.drawable.device_list_phone_no);
        DeviceFactory.a(this.g.model, this.mHeadImageShrink, (int) R.drawable.device_list_phone_no);
        if (arrayList == null) {
            n();
        } else {
            a((List<BleGatewayManager.BleGatewayItem>) arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void a(List<BleGatewayManager.BleGatewayItem> list) {
        this.b.clear();
        this.c.clear();
        if (list != null) {
            for (BleGatewayManager.BleGatewayItem next : list) {
                Device b2 = SmartHomeDeviceManager.a().b(next.a());
                if (b2 != null) {
                    b2.rssi = next.b();
                    if (!BleGatewayManager.a(this.g.did) || !(b2.pid == Device.PID_BLE_MESH || b2.pid == Device.PID_VIRTUAL_GROUP)) {
                        this.c.add(b2);
                    } else {
                        this.b.add(b2);
                    }
                }
            }
            if (this.b.size() > 1) {
                c(this.b);
            }
            if (this.c.size() > 1) {
                c(this.c);
            }
        }
        if (this.b.size() > 0 || this.c.size() > 0) {
            this.d.clear();
            if (this.b.size() > 0) {
                Device device = new Device();
                device.name = getResources().getString(R.string.gateway_controllable_device);
                this.d.add(device);
                this.d.addAll(this.b);
            }
            if (this.c.size() > 0) {
                Device device2 = new Device();
                device2.name = getResources().getString(R.string.gateway_viewable_device);
                this.d.add(device2);
                this.d.addAll(this.c);
            }
            if (BleGatewayManager.a(this.g.did)) {
                Device device3 = new Device();
                device3.model = RecyclerViewAdapter.f11679a;
                this.d.add(device3);
            }
            this.mEmptyViewContainer.setVisibility(8);
            this.mRecyclerView.setVisibility(0);
            this.e.notifyDataSetChanged();
            b(this.d);
            if (BleGatewayManager.a(this.g.did)) {
                this.mMeshGatewayGuideView.setVisibility(0);
                return;
            }
            return;
        }
        this.mEmptyViewContainer.setVisibility(0);
        this.mRecyclerView.setVisibility(8);
        if (BleGatewayManager.a(this.g.did)) {
            this.mMeshGatewayGuideView.setVisibility(8);
        }
    }

    private void b(List<Device> list) {
        DeviceApi.getInstance().updateDeviceDesc(this, list, new AsyncCallback<List<Device>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(List<Device> list) {
                BleGatewayActivity.this.e.notifyDataSetChanged();
            }
        });
    }

    private void c(List<Device> list) {
        Collections.sort(list, new Comparator<Device>() {
            /* renamed from: a */
            public int compare(Device device, Device device2) {
                return device2.rssi - device.rssi;
            }
        });
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        public static final String f11679a = "last_empty_model";
        int b;
        int c;
        int d;

        private RecyclerViewAdapter() {
            this.b = 1;
            this.c = 2;
            this.d = 3;
        }

        public int getItemCount() {
            return BleGatewayActivity.this.d.size();
        }

        public int getItemViewType(int i) {
            if (((Device) BleGatewayActivity.this.d.get(i)).model == null) {
                return this.b;
            }
            if (TextUtils.equals(((Device) BleGatewayActivity.this.d.get(i)).model, f11679a)) {
                return this.d;
            }
            return this.c;
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == this.c) {
                return new ItemViewHolder(LayoutInflater.from(BleGatewayActivity.this).inflate(R.layout.ble_gateway_item, viewGroup, false));
            }
            if (i == this.d) {
                return new ItemViewHolder(LayoutInflater.from(BleGatewayActivity.this).inflate(R.layout.ble_gateway_empty_item, viewGroup, false));
            }
            return new TagViewHolder(LayoutInflater.from(BleGatewayActivity.this).inflate(R.layout.common_list_title, viewGroup, false));
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            Device device = (Device) BleGatewayActivity.this.d.get(i);
            int itemViewType = getItemViewType(i);
            if (itemViewType == this.b) {
                ((TagViewHolder) viewHolder).f11680a.setText(((Device) BleGatewayActivity.this.d.get(i)).name);
            } else if (itemViewType != this.d) {
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                DeviceFactory.a(device.model, itemViewHolder.f11678a, (int) R.drawable.device_list_phone_no);
                itemViewHolder.b.setText(((Device) BleGatewayActivity.this.d.get(i)).name);
                itemViewHolder.d.setImageLevel(((Device) BleGatewayActivity.this.d.get(i)).rssi);
                if (device.rssi >= -50) {
                    itemViewHolder.d.setImageResource(R.drawable.defaule_icon_ble_04);
                } else if (device.rssi >= -70) {
                    itemViewHolder.d.setImageResource(R.drawable.defaule_icon_ble_03);
                } else if (device.rssi >= -90) {
                    itemViewHolder.d.setImageResource(R.drawable.defaule_icon_ble_02);
                } else if (device.rssi >= -100) {
                    itemViewHolder.d.setImageResource(R.drawable.defaule_icon_ble_01);
                } else {
                    itemViewHolder.d.setImageResource(R.drawable.defaule_icon_ble_00);
                }
                int i2 = 0;
                boolean z = true;
                if ((device.pid == Device.PID_BLE_MESH || device.pid == Device.PID_VIRTUAL_GROUP) && !device.isOnline) {
                    itemViewHolder.c.setText(R.string.list_device_offline);
                } else {
                    String str = "";
                    ArrayList<Pair> c2 = MainPageOpManager.a().c(device);
                    if (c2 != null && c2.size() > 0) {
                        StringBuilder sb = new StringBuilder();
                        boolean z2 = true;
                        for (int i3 = 0; i3 < c2.size(); i3++) {
                            Pair pair = c2.get(i3);
                            if (pair != null && (pair.first instanceof String) && (pair.second instanceof String)) {
                                if (z2) {
                                    sb.append((String) pair.second);
                                    z2 = false;
                                } else {
                                    sb.append("|");
                                    sb.append((String) pair.second);
                                }
                            }
                        }
                        str = sb.toString();
                    }
                    if (!TextUtils.isEmpty(str)) {
                        itemViewHolder.c.setText(str);
                    } else if (!TextUtils.isEmpty(device.descNew)) {
                        itemViewHolder.c.setText(device.descNew);
                    } else if (!TextUtils.isEmpty(device.desc)) {
                        itemViewHolder.c.setText(device.desc);
                    } else if (device.pid == Device.PID_BLE_MESH || device.pid == Device.PID_VIRTUAL_GROUP) {
                        itemViewHolder.c.setText(R.string.list_device_online);
                    } else {
                        itemViewHolder.c.setText(R.string.miio_ble_gateway_default_subtitle);
                    }
                }
                if (i != getItemCount() - 1) {
                    z = false;
                }
                ViewGroup.LayoutParams layoutParams = itemViewHolder.e.getLayoutParams();
                if (layoutParams != null) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    if (!z) {
                        i2 = DisplayUtils.a(24.0f);
                    }
                    marginLayoutParams.leftMargin = i2;
                }
            }
        }
    }

    private static class ItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f11678a;
        TextView b;
        TextView c;
        ImageView d;
        View e;
        View f;

        ItemViewHolder(View view) {
            super(view);
            this.f11678a = (SimpleDraweeView) view.findViewById(R.id.image);
            this.b = (TextView) view.findViewById(R.id.name);
            this.c = (TextView) view.findViewById(R.id.status);
            this.d = (ImageView) view.findViewById(R.id.signal_strength);
            this.e = view.findViewById(R.id.divider);
            this.f = view.findViewById(R.id.item_root);
        }
    }

    private static class TagViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f11680a;

        TagViewHolder(View view) {
            super(view);
            this.f11680a = (TextView) view.findViewById(R.id.title);
        }
    }

    /* access modifiers changed from: private */
    public void n() {
        if (!this.f && this.g != null) {
            this.f = true;
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.g.did);
            BleGatewayManager.a(arrayList, new BleGatewayManager.BleGatewayBatchCallback() {
                public void a(List<BleGatewayManager.BleGateway> list) {
                    if (!BleGatewayActivity.this.isFinishing()) {
                        boolean unused = BleGatewayActivity.this.f = false;
                        BleGatewayActivity.this.mNestedScrollView.refreshFinish();
                        if (list == null || list.size() <= 0) {
                            BleGatewayActivity.this.mEmptyViewContainer.setVisibility(0);
                            BleGatewayActivity.this.mRecyclerView.setVisibility(8);
                            return;
                        }
                        BleGatewayActivity.this.a((List<BleGatewayManager.BleGatewayItem>) list.get(0).b());
                    }
                }
            });
        }
    }

    private void o() {
        this.mHeadAnimImage.setImageResource(R.drawable.ble_gateway_head);
        ((AnimationDrawable) this.mHeadAnimImage.getDrawable()).start();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mHeadAnimImage.setImageResource(R.drawable.ble_gateway_head);
        ((AnimationDrawable) this.mHeadAnimImage.getDrawable()).stop();
        this.mNestedScrollView.refreshFinish();
    }
}
