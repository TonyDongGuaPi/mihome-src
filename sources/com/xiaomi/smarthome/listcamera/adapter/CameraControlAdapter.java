package com.xiaomi.smarthome.listcamera.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.listcamera.DeviceControlChooseActivity;
import java.util.ArrayList;
import java.util.List;

public class CameraControlAdapter extends BaseAdapter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public DeviceControlChooseActivity f19280a;
    private LayoutInflater b;
    private ArrayList<Device> c = new ArrayList<>();
    /* access modifiers changed from: private */
    public boolean d = false;
    private int e;

    public long getItemId(int i) {
        return 0;
    }

    public CameraControlAdapter(DeviceControlChooseActivity deviceControlChooseActivity, boolean z, int i) {
        this.f19280a = deviceControlChooseActivity;
        this.b = LayoutInflater.from(deviceControlChooseActivity);
        this.d = z;
        this.e = i;
    }

    public void a(List<Device> list) {
        this.c = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        if (this.c == null) {
            return 0;
        }
        return this.c.size();
    }

    public Object getItem(int i) {
        if (this.c == null || i > this.c.size() || i < 0) {
            return null;
        }
        return this.c.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.b.inflate(R.layout.item_edit_client_all_column, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.f19282a = (TextView) view.findViewById(R.id.column_name);
            viewHolder.b = view.findViewById(R.id.img_drag_handle);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (viewHolder != null && i < this.c.size()) {
            if (!this.d) {
                viewHolder.b.setVisibility(8);
            }
            final Device device = this.c.get(i);
            viewHolder.f19282a.setText(device.name);
            if (this.e != 0) {
                viewHolder.f19282a.setCompoundDrawablesWithIntrinsicBounds(this.f19280a.getResources().getDrawable(this.e), (Drawable) null, (Drawable) null, (Drawable) null);
            }
            viewHolder.f19282a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (CameraControlAdapter.this.d) {
                        CameraControlAdapter.this.f19280a.removeControl(device);
                    } else {
                        CameraControlAdapter.this.f19280a.addControl(device);
                    }
                }
            });
        }
        return view;
    }

    private static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f19282a;
        View b;

        private ViewHolder() {
        }
    }
}
