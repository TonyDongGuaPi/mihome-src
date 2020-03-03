package com.xiaomi.smarthome.miio.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.widget.DragListView;
import java.util.ArrayList;
import java.util.List;

public class DeviceTagSelectDeviceActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    private View f19557a;
    private DragListView b;
    private ListAdapter c;
    private List<Device> d;

    private static class ListAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        private Context f19558a;
        private List<Device> b;
        private LayoutInflater c;

        public long getItemId(int i) {
            return (long) i;
        }

        public ListAdapter(Context context, List<Device> list) {
            this.f19558a = context;
            this.b = list;
            this.c = LayoutInflater.from(context);
        }

        public int getCount() {
            if (this.b != null) {
                return this.b.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (this.b != null) {
                return this.b.get(i);
            }
            return null;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = this.c.inflate(R.layout.item_device_tag_select, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f19559a = (SimpleDraweeView) view.findViewById(R.id.image);
                viewHolder.b = (TextView) view.findViewById(R.id.name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Device device = (Device) getItem(i);
            if (device != null) {
                DeviceFactory.b(device.model, viewHolder.f19559a);
                viewHolder.b.setText(device.getName());
            }
            return view;
        }

        private static class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            public SimpleDraweeView f19559a;
            public TextView b;

            private ViewHolder() {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.share_device_layout);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.tag_select_device);
        this.f19557a = findViewById(R.id.module_a_3_return_btn);
        this.f19557a.setOnClickListener(this);
        this.b = (DragListView) findViewById(R.id.share_device_list);
        a();
    }

    public void onClick(View view) {
        if (view == this.f19557a) {
            finish();
        }
    }

    private void a() {
        if (this.c == null) {
            this.d = new ArrayList();
            List<Device> d2 = SmartHomeDeviceManager.a().d();
            if (d2 != null) {
                if (IRDeviceUtil.c()) {
                    for (Device next : d2) {
                        if (!IRDeviceUtil.a(next.did)) {
                            this.d.add(next);
                        }
                    }
                } else {
                    this.d.addAll(d2);
                }
            }
            this.c = new ListAdapter(this, this.d);
            this.b.setAdapter(this.c);
            this.b.setOnItemClickListener(this);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.d != null && i <= this.d.size()) {
            Intent intent = new Intent(this, DeviceTagEditActivity.class);
            intent.putExtra("did", this.d.get(i).did);
            startActivity(intent);
        }
    }
}
