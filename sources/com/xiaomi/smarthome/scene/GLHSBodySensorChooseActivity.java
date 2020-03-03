package com.xiaomi.smarthome.scene;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class GLHSBodySensorChooseActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ListView f21206a;
    /* access modifiers changed from: private */
    public List<Device> b = new ArrayList();
    private DeviceChooseAdapter c;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.item_choose_layout);
        a();
        c();
        b();
    }

    private void a() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                GLHSBodySensorChooseActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.gateway_motion_name);
    }

    private void b() {
        ((TextView) findViewById(R.id.setting_page_category_title)).setText(R.string.choose_body_sensor);
        this.f21206a = (ListView) findViewById(R.id.content_list_view);
        this.c = new DeviceChooseAdapter();
        this.f21206a.setAdapter(this.c);
    }

    private void c() {
        List<Device> k = SmartHomeDeviceManager.a().k();
        this.b.clear();
        for (Device next : k) {
            if ((next.isOwner() || next.isFamily()) && ("lumi.sensor_motion.v1".equalsIgnoreCase(next.model) || "lumi.sensor_motion.v2".equalsIgnoreCase(next.model))) {
                this.b.add(next);
            }
        }
    }

    private class DeviceChooseAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public int b;

        public long getItemId(int i) {
            return 0;
        }

        private DeviceChooseAdapter() {
            this.b = -1;
        }

        public int getCount() {
            return GLHSBodySensorChooseActivity.this.b.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= GLHSBodySensorChooseActivity.this.b.size()) {
                return null;
            }
            return GLHSBodySensorChooseActivity.this.b.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(GLHSBodySensorChooseActivity.this.getContext()).inflate(R.layout.wifi_choose_list_item, (ViewGroup) null, false);
                viewHolder = new ViewHolder();
                viewHolder.f21210a = (TextView) view.findViewById(R.id.wifi_text);
                viewHolder.b = view.findViewById(R.id.select_flag_image);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Device device = (Device) GLHSBodySensorChooseActivity.this.b.get(i);
            if (device != null) {
                viewHolder.f21210a.setText(device.getName());
                viewHolder.b.setVisibility(8);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ViewHolder viewHolder;
                    if (DeviceChooseAdapter.this.b != i) {
                        if (!(DeviceChooseAdapter.this.b == -1 || (viewHolder = (ViewHolder) GLHSBodySensorChooseActivity.this.f21206a.getChildAt(DeviceChooseAdapter.this.b).getTag()) == null)) {
                            viewHolder.b.setVisibility(8);
                            viewHolder.b.invalidate();
                        }
                        viewHolder.b.setVisibility(0);
                        viewHolder.b.invalidate();
                        int unused = DeviceChooseAdapter.this.b = i;
                    }
                }
            });
            return view;
        }
    }

    private class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21210a;
        public View b;

        private ViewHolder() {
        }
    }
}
