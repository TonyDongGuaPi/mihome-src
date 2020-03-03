package com.xiaomi.smarthome.miio.miband.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import com.xiaomi.smarthome.miio.miband.data.PluginDeviceDownloadItem;
import com.xiaomi.smarthome.miio.miband.utils.PluginDeviceNavigateHelper;
import java.lang.ref.WeakReference;
import java.util.List;

public class DeviceAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private List<Device> f19441a;
    private WeakReference<Context> b;

    public long getItemId(int i) {
        return 0;
    }

    public DeviceAdapter(Context context, List<Device> list) {
        this.f19441a = list;
        this.b = new WeakReference<>(context);
    }

    public void a(List<Device> list) {
        this.f19441a = list;
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.f19441a == null) {
            return 0;
        }
        return this.f19441a.size();
    }

    public Object getItem(int i) {
        if (i < 0 || this.f19441a == null || i >= this.f19441a.size()) {
            return null;
        }
        return this.f19441a.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Context context = this.b != null ? (Context) this.b.get() : null;
        Device device = this.f19441a.get(i);
        if (context == null || device == null || CoreApi.a().d(device.model) == null) {
            return null;
        }
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.miband_device_list_item, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.f19442a = (ImageView) view.findViewById(R.id.iv_device_icon);
            viewHolder.b = (TextView) view.findViewById(R.id.tv_device_title);
            viewHolder.c = (TextView) view.findViewById(R.id.tv_device_detail);
            viewHolder.d = (PieProgressBar) view.findViewById(R.id.update_progress);
            viewHolder.e = (TextView) view.findViewById(R.id.update_percent);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.b.setText(device.name);
        PluginDeviceDownloadItem a2 = PluginDeviceNavigateHelper.a().a(device.model);
        viewHolder.d.setVisibility(4);
        viewHolder.e.setVisibility(4);
        if (a2 != null) {
            if (a2.c.equals(PluginDeviceDownloadItem.Status.DOWNLOADING)) {
                viewHolder.d.setVisibility(0);
                viewHolder.e.setVisibility(0);
                viewHolder.d.setPercent(a2.f19463a * 100.0f);
                viewHolder.e.setText(String.format("%d%%", new Object[]{Integer.valueOf(((int) a2.f19463a) * 100)}));
            } else {
                viewHolder.d.setVisibility(4);
                viewHolder.e.setVisibility(4);
            }
        }
        return view;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f19442a;
        public TextView b;
        public TextView c;
        public PieProgressBar d;
        public TextView e;

        ViewHolder() {
        }
    }
}
