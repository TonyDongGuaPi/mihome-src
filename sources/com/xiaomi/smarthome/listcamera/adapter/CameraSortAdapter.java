package com.xiaomi.smarthome.listcamera.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.DraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.listcamera.CameraGroupManager;
import com.xiaomi.smarthome.listcamera.CameraSortActivity;
import com.xiaomi.smarthome.miio.page.DeviceSortAdapter;
import java.util.ArrayList;
import java.util.List;

public class CameraSortAdapter extends RecyclerView.Adapter<ChildViewHolder> implements DraggableItemAdapter<ChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    static final String f19313a = DeviceSortAdapter.class.getSimpleName();
    CameraSortActivity b;
    public List<Device> c = new ArrayList();

    public ItemDraggableRange a(ChildViewHolder childViewHolder, int i) {
        return null;
    }

    public boolean b(int i, int i2) {
        return false;
    }

    public CameraSortAdapter(CameraSortActivity cameraSortActivity) {
        this.b = cameraSortActivity;
        for (CameraGroupManager.GroupInfo groupInfo : CameraGroupManager.a().d()) {
            Device b2 = SmartHomeDeviceManager.a().b(groupInfo.f19240a);
            if (b2 != null) {
                this.c.add(b2);
            }
        }
        setHasStableIds(true);
    }

    public long getItemId(int i) {
        return (long) this.c.get(i).hashCode();
    }

    public boolean a(ChildViewHolder childViewHolder, int i, int i2, int i3) {
        ImageView imageView = childViewHolder.d;
        if (imageView == null) {
            return false;
        }
        Rect rect = new Rect();
        childViewHolder.itemView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        imageView.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i2, i3);
    }

    public void b_(int i, int i2) {
        this.c.add(i2, this.c.remove(i));
        this.b.orderChanged();
    }

    /* renamed from: a */
    public ChildViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.client_all_item_v2_sort, viewGroup, false));
    }

    /* renamed from: b */
    public void onBindViewHolder(ChildViewHolder childViewHolder, int i) {
        ((ListItemView) childViewHolder.e).setPosition(i);
        Device device = this.c.get(i);
        if (device != null) {
            DeviceFactory.b(device.model, childViewHolder.c);
            childViewHolder.f19314a.setText(device.getName());
            if (device.isOnline) {
                childViewHolder.f19314a.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.black_80_transparent));
            } else {
                childViewHolder.f19314a.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.black_30_transparent));
            }
        }
    }

    public int getItemCount() {
        return this.c.size();
    }

    public static abstract class MyBaseViewHolder extends AbstractDraggableSwipeableItemViewHolder {
        public View e;

        public MyBaseViewHolder(View view) {
            super(view);
            this.e = view;
        }

        public View k() {
            return this.e;
        }
    }

    public static class ChildViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19314a;
        public TextView b;
        public SimpleDraweeView c;
        public ImageView d;

        public ChildViewHolder(View view) {
            super(view);
            this.f19314a = (TextView) view.findViewById(R.id.name);
            this.b = (TextView) view.findViewById(R.id.name_status);
            this.c = (SimpleDraweeView) view.findViewById(R.id.image);
            this.d = (ImageView) view.findViewById(R.id.img_handle);
        }
    }
}
