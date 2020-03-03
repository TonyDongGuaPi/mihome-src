package com.xiaomi.smarthome.scenenew.pluginrecommend;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActionActivity;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import java.util.List;

public class LockDissConnectBleActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private TextView f22031a;
    private TextView b;
    private String c;
    private int d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.light_rec_scene_condition_activity);
        this.c = getIntent().getStringExtra("home_id");
        this.d = getIntent().getIntExtra("sr_id", 0);
        this.f22031a = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LockDissConnectBleActivity.this.a(view);
            }
        });
        if (RecommendSceneCreator.a().f21963a != null && !TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.entryDesc)) {
            this.f22031a.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        final List<Device> a2 = RecommendSceneCreator.a(this.c);
        View inflate = ((ViewStub) findViewById(R.id.ble_list_view)).inflate();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        this.b = (TextView) inflate.findViewById(R.id.connect_tip);
        if (this.d == 1000) {
            this.b.setText(R.string.rec_scene_lock_connect_ble_top);
        } else {
            this.b.setText(R.string.rec_scene_device_connect_ble_top);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<PluginRecommendSceneActionActivity.BleViewHolder>() {
            public long getItemId(int i) {
                return (long) i;
            }

            @NonNull
            /* renamed from: a */
            public PluginRecommendSceneActionActivity.BleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new PluginRecommendSceneActionActivity.BleViewHolder(LayoutInflater.from(LockDissConnectBleActivity.this).inflate(R.layout.item_plugin_rec_action_connect_ble, (ViewGroup) null));
            }

            /* renamed from: a */
            public void onBindViewHolder(@NonNull PluginRecommendSceneActionActivity.BleViewHolder bleViewHolder, int i) {
                if (i == a2.size() - 1) {
                    bleViewHolder.c.setVisibility(0);
                } else {
                    bleViewHolder.c.setVisibility(8);
                }
                String str = ((Device) a2.get(i)).name;
                if (!TextUtils.isEmpty(str)) {
                    bleViewHolder.b.setText(str);
                } else {
                    bleViewHolder.b.setText("");
                }
                DeviceFactory.b(((Device) a2.get(i)).model, bleViewHolder.f21816a);
            }

            public int getItemCount() {
                if (a2 == null) {
                    return 0;
                }
                return a2.size();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
    }
}
