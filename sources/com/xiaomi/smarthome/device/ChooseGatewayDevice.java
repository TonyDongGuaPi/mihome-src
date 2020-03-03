package com.xiaomi.smarthome.device;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.card.GatewaySupportManger;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChooseGatewayDevice extends BaseActivity {
    public static final String EXTRA_GATEWAY_DID = "gateway_did";
    public static final String EXTRA_MODEL = "model";
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_RECORD = "plugin_record";
    public static final String SUPPORT_MIHOME_CONNECT = "support_MIHOME_CONNECT";

    /* renamed from: a  reason: collision with root package name */
    private static final String f14806a = "ChooseGatewayDevice";
    private static final int b = 11;
    /* access modifiers changed from: private */
    public List<Device> c = new ArrayList();
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public PluginRecord e;
    private BaseAdapter f = new BaseAdapter() {
        public long getItemId(int i) {
            return (long) i;
        }

        public int getCount() {
            return ChooseGatewayDevice.this.c.size();
        }

        public Object getItem(int i) {
            return ChooseGatewayDevice.this.c.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(ChooseGatewayDevice.this).inflate(R.layout.choose_device_item, viewGroup, false);
            }
            final Device device = (Device) ChooseGatewayDevice.this.c.get(i);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image);
            TextView textView = (TextView) view.findViewById(R.id.name);
            TextView textView2 = (TextView) view.findViewById(R.id.name_status);
            if (!GatewaySupportManger.d().a(device, ChooseGatewayDevice.this.d)) {
                textView2.setVisibility(0);
                textView2.setText(R.string.no_zigbee_version_low);
                view.setAlpha(0.7f);
            } else if (!GatewaySupportManger.d().a(ChooseGatewayDevice.this.e.o(), device)) {
                textView2.setVisibility(0);
                textView2.setText(R.string.zigbee_gateway_version_too_low);
                view.setAlpha(0.7f);
            } else if (device.isOnline) {
                textView2.setVisibility(8);
                view.setAlpha(1.0f);
            } else {
                textView2.setVisibility(0);
                textView2.setText(R.string.list_device_offline);
                view.setAlpha(0.7f);
            }
            DeviceFactory.b(device.model, simpleDraweeView);
            textView.setText(device.name);
            if (i == ChooseGatewayDevice.this.c.size() - 1) {
                View findViewById = view.findViewById(R.id.divider);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                findViewById.setLayoutParams(layoutParams);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (GatewaySupportManger.d().a(device, ChooseGatewayDevice.this.d) && GatewaySupportManger.d().a(ChooseGatewayDevice.this.e.o(), device) && device.isOnline) {
                        ChooseGatewayDevice.nextActivity(ChooseGatewayDevice.this, device, ChooseGatewayDevice.this.e, 11, ChooseGatewayDevice.this.getIntent().getExtras());
                    }
                }
            });
            return view;
        }
    };
    @BindView(2131430969)
    View mBackBt;
    @BindView(2131428999)
    ImageView mEmptyImage;
    @BindView(2131428501)
    TextView mEmptyTv;
    @BindView(2131429236)
    View mFindNoDeviceView;
    @BindView(2131430476)
    View mListTitle;
    @BindView(2131430457)
    ListView mListView;
    @BindView(2131431015)
    TextView mMoreMessage;
    @BindView(2131430975)
    TextView mTitle;

    public static void selectActivity(final Activity activity, final PluginRecord pluginRecord, final int i, final Bundle bundle) {
        AnonymousClass1 r0 = new GatewaySupportManger.OnGatewaySupportListener() {
            public void a() {
                b();
            }

            public void b() {
                GatewaySupportManger.d().b((GatewaySupportManger.OnGatewaySupportListener) this);
                List<Device> gatewayDevices = ChooseGatewayDevice.getGatewayDevices(pluginRecord.o());
                if (gatewayDevices.size() == 1) {
                    Device device = gatewayDevices.get(0);
                    if (!GatewaySupportManger.d().a(device, pluginRecord.o()) || !device.isOnline || !GatewaySupportManger.d().a(pluginRecord.o(), device)) {
                        ChooseGatewayDevice.a(activity, pluginRecord, i, bundle);
                    } else {
                        ChooseGatewayDevice.nextActivity(activity, device, pluginRecord, i, bundle);
                    }
                } else {
                    ChooseGatewayDevice.a(activity, pluginRecord, i, bundle);
                }
            }
        };
        if (GatewaySupportManger.d().c()) {
            r0.b();
        } else {
            GatewaySupportManger.d().a((GatewaySupportManger.OnGatewaySupportListener) r0);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_choose_gateway_device);
        ButterKnife.bind((Activity) this);
        a();
        if (this.c.size() == 0) {
            this.mListView.setVisibility(8);
            this.mListTitle.setVisibility(8);
            this.mFindNoDeviceView.setVisibility(0);
            findViewById(R.id.root_view).setBackgroundColor(-1);
            this.mEmptyImage.setImageResource(R.drawable.no_gateway_device);
            if (CoreApi.a().D()) {
                this.mMoreMessage.setVisibility(8);
            } else {
                this.mMoreMessage.setVisibility(0);
            }
            this.mEmptyTv.setText(getString(R.string.no_zigbee_device));
            return;
        }
        this.mListView.setAdapter(this.f);
        this.mFindNoDeviceView.setVisibility(8);
    }

    private void a() {
        GatewaySupportManger.d().a();
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getStringExtra("model");
            this.e = CoreApi.a().d(this.d);
            this.c = getGatewayDevices(this.d);
            if (!TextUtils.isEmpty(intent.getStringExtra("name"))) {
                this.mTitle.setText(getResources().getString(R.string.add));
            }
        }
        this.mBackBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseGatewayDevice.this.onBackPressed();
            }
        });
        this.mMoreMessage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c("https://home.mi.com/shop/search?keyword=网关");
            }
        });
    }

    public static void nextActivity(Activity activity, Device device, PluginRecord pluginRecord, int i, Bundle bundle) {
        if (!GatewaySupportManger.d().a(pluginRecord.o(), device)) {
            ToastUtil.a((int) R.string.gateway_firm_too_low);
            return;
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("gateway_did", device.did);
        ResetDevicePage.showActivity(activity, pluginRecord, i, bundle);
    }

    /* access modifiers changed from: private */
    public static void a(Activity activity, PluginRecord pluginRecord, int i, Bundle bundle) {
        Intent intent = new Intent(activity, ChooseGatewayDevice.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.putExtra("model", pluginRecord.o());
        intent.putExtra("name", pluginRecord.p());
        activity.startActivityForResult(intent, i);
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra(Constants.Event.FINISH, false);
        setResult(0, intent);
        finish();
    }

    public static List<Device> getGatewayDevices(String str) {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet(HomeManager.a().a(HomeManager.a().l(), new boolean[0]));
        for (Device next : SmartHomeDeviceManager.a().d()) {
            if (!(next == null || GatewaySupportManger.d().a(next.model) == null || !hashSet.contains(next.did))) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 11) {
            if (i2 == -1) {
                setResult(-2, intent);
            }
            finish();
        }
    }
}
