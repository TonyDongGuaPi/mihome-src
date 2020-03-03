package com.xiaomi.smarthome.miio.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.config.model.SupportBleGatewayFirmwareVersion;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleGatewayManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BleGatewayListActivity extends BaseActivity {
    public static final String KEY_GATEWAY_DID = "key_gateway_did";
    public static final String KEY_GATEWAY_ITEMS = "key_gateway_items";

    /* renamed from: a  reason: collision with root package name */
    private static final String f11697a = "key_ble_gateway_guide_show";
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public BleGatewayAdapter c;
    /* access modifiers changed from: private */
    public List<BleGatewayManager.BleGateway> d = new ArrayList();
    /* access modifiers changed from: private */
    public BleGatewayManager.BleGatewayBatchCallback e = new BleGatewayManager.BleGatewayBatchCallback() {
        public void a(List<BleGatewayManager.BleGateway> list) {
            BleGatewayListActivity.this.d.clear();
            BleGatewayListActivity.this.d.addAll(list);
            BleGatewayListActivity.this.b.post(new Runnable() {
                public void run() {
                    if (!BleGatewayListActivity.this.isFinishing()) {
                        if (BleGatewayListActivity.this.d.size() == 0) {
                            BleGatewayListActivity.this.mEmptyView.setVisibility(0);
                            BleGatewayListActivity.this.mListView.setVisibility(8);
                        }
                        BleGatewayListActivity.this.c.notifyDataSetChanged();
                        try {
                            BleGatewayListActivity.this.mListView.postRefresh();
                        } catch (Exception unused) {
                        }
                    }
                }
            });
        }
    };
    private Runnable f = new Runnable() {
        public void run() {
            List<String> bleGatewayDids = BleGatewayListActivity.this.getBleGatewayDids();
            if (bleGatewayDids.size() == 0) {
                BleGatewayListActivity.this.mEmptyView.setVisibility(0);
                BleGatewayListActivity.this.mListView.setVisibility(8);
                BleGatewayListActivity.this.c.notifyDataSetChanged();
                BleGatewayListActivity.this.mListView.postRefresh();
                return;
            }
            BleGatewayManager.a(bleGatewayDids, BleGatewayListActivity.this.e);
        }
    };
    @BindView(2131427441)
    TextView mAboutBleGateway;
    @BindView(2131428503)
    View mEmptyView;
    @BindView(2131428501)
    TextView mFirstEmptyText;
    @BindView(2131427866)
    PullDownDragListView mListView;
    @BindView(2131430969)
    ImageView mReturnButton;
    @BindView(2131428502)
    TextView mSecondEmptyText;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitleText;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bluetooth_gateway_list_layout);
        ButterKnife.bind((Activity) this);
        this.b = new Handler();
        a();
        this.mListView.doRefresh();
        if (!PreferenceUtils.a(f11697a, false)) {
            b();
            PreferenceUtils.b(f11697a, true);
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mListView.doRefresh();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.b.removeCallbacks(this.f);
    }

    private void a() {
        this.mTitleText.setText(R.string.miio_bluetooth_gateway);
        this.mReturnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleGatewayListActivity.this.finish();
            }
        });
        this.mEmptyView.setVisibility(8);
        if (CoreApi.a().D()) {
            this.mFirstEmptyText.setText(R.string.miio_empty_ble_gateway_list_international);
        } else {
            this.mFirstEmptyText.setText(R.string.miio_empty_ble_gateway_list);
            this.mSecondEmptyText.setVisibility(0);
            this.mSecondEmptyText.setText(Html.fromHtml(getResources().getString(R.string.miio_find_ble_gateway_in_store)));
            this.mSecondEmptyText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    BleGatewayListActivity.this.mSecondEmptyText.setEnabled(false);
                    UrlDispatchManger.a().c("https://home.mi.com/app/shop/content?id=m6da1d039f1601ca1");
                }
            });
        }
        this.c = new BleGatewayAdapter();
        this.mListView.setAdapter(this.c);
        this.mListView.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                BleGatewayListActivity.this.c();
            }
        });
        this.mAboutBleGateway.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                BleGatewayListActivity.this.mAboutBleGateway.setEnabled(false);
                BleGatewayListActivity.this.b();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mSecondEmptyText.setEnabled(true);
        this.mAboutBleGateway.setEnabled(true);
    }

    /* access modifiers changed from: private */
    public void b() {
        startActivity(new Intent(this, BleGatewayGuideActivity.class));
    }

    public List<String> getBleGatewayDids() {
        ArrayList arrayList = new ArrayList();
        for (Device next : SmartHomeDeviceManager.a().d()) {
            PluginRecord d2 = CoreApi.a().d(next.model);
            if (!(!next.isOwner() || d2 == null || d2.c() == null)) {
                boolean z = true;
                if (d2.c().J() == 1) {
                    if (next instanceof MiioDeviceV2) {
                        String str = ((MiioDeviceV2) next).D;
                        List<SupportBleGatewayFirmwareVersion> f2 = AndroidCommonConfigManager.a().f();
                        if (f2 != null && f2.size() > 0 && !TextUtils.isEmpty(str)) {
                            Iterator<SupportBleGatewayFirmwareVersion> it = f2.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                SupportBleGatewayFirmwareVersion next2 = it.next();
                                if (TextUtils.equals(next2.f13958a, next.model)) {
                                    if (BluetoothHelper.a(str, next2.b) < 0) {
                                        z = false;
                                    }
                                }
                            }
                        }
                    }
                    if (z) {
                        arrayList.add(next.did);
                    }
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void c() {
        List<String> bleGatewayDids = getBleGatewayDids();
        if (bleGatewayDids.size() == 0) {
            this.b.postDelayed(this.f, 3000);
        } else {
            BleGatewayManager.a(bleGatewayDids, this.e);
        }
    }

    public class BleGatewayAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        public BleGatewayAdapter() {
        }

        public int getCount() {
            return BleGatewayListActivity.this.d.size();
        }

        public Object getItem(int i) {
            return BleGatewayListActivity.this.d.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(BleGatewayListActivity.this).inflate(R.layout.ble_list_gateway_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f11707a = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                viewHolder.b = (TextView) view.findViewById(R.id.device_item);
                viewHolder.c = (TextView) view.findViewById(R.id.device_item_info);
                viewHolder.d = (ImageView) view.findViewById(R.id.arrow);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            BleGatewayManager.BleGateway bleGateway = (BleGatewayManager.BleGateway) BleGatewayListActivity.this.d.get(i);
            Device b = SmartHomeDeviceManager.a().b(bleGateway.a());
            if (b != null) {
                DeviceFactory.b(b.model, viewHolder.f11707a);
                viewHolder.b.setText(b.name);
                if (!b.isOnline) {
                    String string = BleGatewayListActivity.this.getResources().getString(R.string.miio_ble_gateway_offline);
                    String r = HomeManager.a().r(b.did);
                    if (!TextUtils.isEmpty(r)) {
                        string = r + " | " + string;
                    }
                    viewHolder.c.setText(string);
                    viewHolder.d.setColorFilter(BleGatewayListActivity.this.getResources().getColor(R.color.white_50_transparent));
                    viewHolder.d.setAlpha(0.3f);
                    viewHolder.b.setAlpha(0.3f);
                    viewHolder.c.setAlpha(0.3f);
                    viewHolder.f11707a.setAlpha(0.3f);
                    view.setOnClickListener((View.OnClickListener) null);
                } else {
                    String quantityString = BleGatewayListActivity.this.getResources().getQuantityString(R.plurals.miio_bluetooth_gateway_count, bleGateway.b().size(), new Object[]{Integer.valueOf(bleGateway.b().size())});
                    String r2 = HomeManager.a().r(b.did);
                    if (!TextUtils.isEmpty(r2)) {
                        quantityString = r2 + " | " + quantityString;
                    }
                    viewHolder.c.setText(quantityString);
                    viewHolder.d.clearColorFilter();
                    viewHolder.d.setAlpha(1.0f);
                    viewHolder.b.setAlpha(1.0f);
                    viewHolder.c.setAlpha(1.0f);
                    viewHolder.f11707a.setAlpha(1.0f);
                    view.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (i < BleGatewayListActivity.this.d.size()) {
                                Intent intent = new Intent(BleGatewayListActivity.this, BleGatewayActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString(BleGatewayListActivity.KEY_GATEWAY_DID, ((BleGatewayManager.BleGateway) BleGatewayListActivity.this.d.get(i)).a());
                                bundle.putParcelableArrayList(BleGatewayListActivity.KEY_GATEWAY_ITEMS, ((BleGatewayManager.BleGateway) BleGatewayListActivity.this.d.get(i)).b());
                                intent.putExtras(bundle);
                                BleGatewayListActivity.this.startActivity(intent);
                            }
                        }
                    });
                }
            }
            if (i != getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            }
            return view;
        }
    }

    public class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f11707a;
        TextView b;
        TextView c;
        ImageView d;

        public ViewHolder() {
        }
    }
}
