package com.xiaomi.smarthome.miio.page.deviceophistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import java.util.ArrayList;

public class DeviceOpHistoryAdapter extends AbstractExpandableItemAdapter<DeviceOpHistoryGroupViewHolder, DeviceOpHistoryChildViewHolder> implements ExpandableDraggableItemAdapter<DeviceOpHistoryGroupViewHolder, DeviceOpHistoryChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private ArrayList<DeviceOpHistoryGroupData> f19736a = DeviceOpHistoryGroupData.a();
    private Context b;

    public ItemDraggableRange a(DeviceOpHistoryChildViewHolder deviceOpHistoryChildViewHolder, int i, int i2) {
        return null;
    }

    public ItemDraggableRange a(DeviceOpHistoryGroupViewHolder deviceOpHistoryGroupViewHolder, int i) {
        return null;
    }

    public void a(int i, int i2) {
    }

    public void a(int i, int i2, int i3, int i4) {
    }

    public boolean a(DeviceOpHistoryChildViewHolder deviceOpHistoryChildViewHolder, int i, int i2, int i3, int i4) {
        return false;
    }

    public boolean a(DeviceOpHistoryGroupViewHolder deviceOpHistoryGroupViewHolder, int i, int i2, int i3) {
        return false;
    }

    public boolean a(DeviceOpHistoryGroupViewHolder deviceOpHistoryGroupViewHolder, int i, int i2, int i3, boolean z) {
        return false;
    }

    public boolean b(int i, int i2) {
        return false;
    }

    public boolean b(int i, int i2, int i3, int i4) {
        return false;
    }

    public DeviceOpHistoryAdapter(Context context) {
        this.b = context;
        setHasStableIds(true);
    }

    public int a() {
        if (this.f19736a == null) {
            return 0;
        }
        return this.f19736a.size();
    }

    public int a(int i) {
        if (this.f19736a != null && i >= 0 && i < this.f19736a.size()) {
            return this.f19736a.get(i).e.size();
        }
        return 0;
    }

    public long b(int i) {
        return this.f19736a.get(i).d;
    }

    public long c(int i, int i2) {
        return this.f19736a.get(i).e.get(i2).f19738a;
    }

    /* renamed from: c */
    public DeviceOpHistoryGroupViewHolder a(ViewGroup viewGroup, int i) {
        return new DeviceOpHistoryGroupViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_device_op_history_group, viewGroup, false));
    }

    /* renamed from: d */
    public DeviceOpHistoryChildViewHolder b(ViewGroup viewGroup, int i) {
        return new DeviceOpHistoryChildViewHolder(LayoutInflater.from(this.b).inflate(R.layout.item_device_op_history_child, viewGroup, false));
    }

    /* renamed from: a */
    public void b(DeviceOpHistoryGroupViewHolder deviceOpHistoryGroupViewHolder, int i, int i2) {
        deviceOpHistoryGroupViewHolder.a(this.b, this.f19736a.get(i));
    }

    /* renamed from: a */
    public void b(DeviceOpHistoryChildViewHolder deviceOpHistoryChildViewHolder, int i, int i2, int i3) {
        deviceOpHistoryChildViewHolder.a(this.f19736a.get(i).e.get(i2));
    }
}
