package com.xiaomi.smarthome.homeroom;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class DeviceUninitedListActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private RecyclerView f17958a;
    private View b;
    /* access modifiers changed from: private */
    public List<Device> c = new ArrayList();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_uninited_list_layout);
        for (String b2 : new ArrayList(DeviceInitChecker.f17939a)) {
            Device b3 = SmartHomeDeviceManager.a().b(b2);
            if (b3 != null) {
                this.c.add(b3);
            }
        }
        a();
    }

    private void a() {
        View findViewById = findViewById(R.id.title_bar);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.tag_custom_empty);
        ((TextView) findViewById.findViewById(R.id.module_a_3_return_title)).setText(R.string.device_init_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceUninitedListActivity.this.finish();
            }
        });
        findViewById(R.id.module_a_3_right_iv_setting_btn).setVisibility(8);
        this.f17958a = (RecyclerView) findViewById(R.id.recyclerview);
        this.f17958a.setLayoutManager(new LinearLayoutManager(this));
        this.b = findViewById(R.id.common_white_empty_view);
        this.f17958a.setAdapter(new SimpleAdapter());
        if (this.c.isEmpty()) {
            b();
        } else {
            c();
        }
    }

    private void b() {
        this.b.setVisibility(0);
        this.f17958a.setVisibility(8);
    }

    private void c() {
        this.b.setVisibility(8);
        this.f17958a.setVisibility(0);
    }

    private class SimpleAdapter extends RecyclerView.Adapter<SimpleHolder> {
        private SimpleAdapter() {
        }

        /* renamed from: a */
        public SimpleHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new SimpleHolder(LayoutInflater.from(DeviceUninitedListActivity.this.getContext()).inflate(R.layout.device_uninited_list_item, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(SimpleHolder simpleHolder, int i) {
            simpleHolder.a(i);
        }

        public int getItemCount() {
            return DeviceUninitedListActivity.this.c.size();
        }

        public class SimpleHolder extends RecyclerView.ViewHolder {
            private SimpleDraweeView b;
            private TextView c;

            public SimpleHolder(View view) {
                super(view);
                this.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                this.c = (TextView) view.findViewById(R.id.device_name);
            }

            /* JADX WARNING: Code restructure failed: missing block: B:3:0x0011, code lost:
                r3 = (com.xiaomi.smarthome.device.Device) com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity.access$100(r2.f17961a.f17960a).get(r3);
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(int r3) {
                /*
                    r2 = this;
                    if (r3 < 0) goto L_0x003b
                    com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity$SimpleAdapter r0 = com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity.SimpleAdapter.this
                    com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity r0 = com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity.this
                    java.util.List r0 = r0.c
                    int r0 = r0.size()
                    if (r3 < r0) goto L_0x0011
                    goto L_0x003b
                L_0x0011:
                    com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity$SimpleAdapter r0 = com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity.SimpleAdapter.this
                    com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity r0 = com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity.this
                    java.util.List r0 = r0.c
                    java.lang.Object r3 = r0.get(r3)
                    com.xiaomi.smarthome.device.Device r3 = (com.xiaomi.smarthome.device.Device) r3
                    if (r3 != 0) goto L_0x0022
                    return
                L_0x0022:
                    java.lang.String r0 = r3.model
                    com.facebook.drawee.view.SimpleDraweeView r1 = r2.b
                    com.xiaomi.smarthome.device.DeviceFactory.b((java.lang.String) r0, (com.facebook.drawee.view.SimpleDraweeView) r1)
                    android.widget.TextView r0 = r2.c
                    java.lang.String r1 = r3.name
                    r0.setText(r1)
                    android.view.View r0 = r2.itemView
                    com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity$SimpleAdapter$SimpleHolder$1 r1 = new com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity$SimpleAdapter$SimpleHolder$1
                    r1.<init>(r3)
                    r0.setOnClickListener(r1)
                    return
                L_0x003b:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.DeviceUninitedListActivity.SimpleAdapter.SimpleHolder.a(int):void");
            }
        }
    }
}
