package com.xiaomi.smarthome.device;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.infrared.adapter.BaseRecyclerAdapter;
import com.xiaomi.infrared.adapter.ViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.card.GatewaySupportManger;
import java.util.ArrayList;
import java.util.List;

public class GatewayAddDeviceListActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14849a = "GatewayAddDeviceListAct";
    private static final int b = 11;
    /* access modifiers changed from: private */
    public Device c;
    /* access modifiers changed from: private */
    public SubDeviceAdapter d;
    /* access modifiers changed from: private */
    public List<PluginRecord> e = new ArrayList();

    public static void showActivity(Activity activity, String str) {
        activity.startActivity(new Intent(activity, GatewayAddDeviceListActivity.class).putExtra("gateway_did", str));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bind_subdevice_list);
        this.c = SmartHomeDeviceManager.a().b(getIntent().getStringExtra("gateway_did"));
        if (this.c == null) {
            ToastUtil.a((int) R.string.gateway_recongise_device_fail);
            finish();
            return;
        }
        a();
        GatewaySupportManger.d().a();
        if (GatewaySupportManger.d().c()) {
            a(GatewaySupportManger.d().a(this.c.model));
        } else {
            GatewaySupportManger.d().a((GatewaySupportManger.OnGatewaySupportListener) new GatewaySupportManger.OnGatewaySupportListener() {
                private List<String> b;

                public void a() {
                    if (this.b == null) {
                        b();
                    }
                }

                public void b() {
                    List<String> a2 = GatewaySupportManger.d().a(GatewayAddDeviceListActivity.this.c.model);
                    if (!a2.equals(this.b)) {
                        GatewaySupportManger.d().b((GatewaySupportManger.OnGatewaySupportListener) this);
                        this.b = a2;
                        GatewayAddDeviceListActivity.this.a(this.b);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(List<String> list) {
        final ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (String d2 : list) {
                PluginRecord d3 = CoreApi.a().d(d2);
                if (d3 != null) {
                    arrayList.add(d3);
                }
            }
            this.mHandler.post(new Runnable() {
                public void run() {
                    GatewayAddDeviceListActivity.this.e.clear();
                    GatewayAddDeviceListActivity.this.e.addAll(arrayList);
                    GatewayAddDeviceListActivity.this.d.notifyDataSetChanged();
                }
            });
        }
    }

    private void a() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.device_list_rv);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GatewayAddDeviceListActivity.this.onBackPressed();
            }
        });
        View findViewById = findViewById(R.id.title_bar_more);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.gateway_add_subdevice);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        this.d = new SubDeviceAdapter(this, this.e);
        recyclerView.setAdapter(this.d);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.d.a((BaseRecyclerAdapter.OnItemClickListener) this);
    }

    public void onItemClick(RecyclerView recyclerView, View view, int i) {
        ChooseGatewayDevice.nextActivity(this, this.c, this.e.get(i), 11, (Bundle) null);
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

    private static class SubDeviceAdapter extends BaseRecyclerAdapter<PluginRecord> {
        public int c(int i) {
            return R.layout.gateway_choose_device_item_layout;
        }

        public SubDeviceAdapter(Context context, List<PluginRecord> list) {
            super(context, list);
        }

        public void a(ViewHolder viewHolder, PluginRecord pluginRecord, int i) {
            ((TextView) viewHolder.a((int) R.id.add_sub_device_item_tv)).setText(pluginRecord.p());
            DeviceFactory.b(pluginRecord.o(), (SimpleDraweeView) viewHolder.a((int) R.id.add_sub_device_item_iv));
        }
    }
}
