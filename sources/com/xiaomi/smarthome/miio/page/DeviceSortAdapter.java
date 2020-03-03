package com.xiaomi.smarthome.miio.page;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.expandable.GroupPositionItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import java.util.List;

public class DeviceSortAdapter extends AbstractExpandableItemAdapter<GroupViewHolder, ChildViewHolder> implements ExpandableDraggableItemAdapter<GroupViewHolder, ChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    static final String f19538a = "DeviceSortAdapter";
    DeviceSortActivity b;
    final RecyclerViewExpandableItemManager c;
    public List<DeviceGroup> d = DeviceGroupManager.a().b(DeviceSortHelper.a().f19544a);

    public ItemDraggableRange a(GroupViewHolder groupViewHolder, int i) {
        return null;
    }

    public void a(int i, int i2) {
        if (i != i2) {
        }
    }

    public boolean a(GroupViewHolder groupViewHolder, int i, int i2, int i3) {
        return false;
    }

    public boolean a(GroupViewHolder groupViewHolder, int i, int i2, int i3, boolean z) {
        return false;
    }

    public boolean b(int i, int i2) {
        return false;
    }

    public boolean b(int i, int i2, int i3, int i4) {
        return false;
    }

    public int c(int i) {
        return 0;
    }

    public int d(int i, int i2) {
        return 0;
    }

    public static abstract class MyBaseViewHolder extends AbstractDraggableSwipeableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        private int f19542a;
        public View e;

        public MyBaseViewHolder(View view) {
            super(view);
            this.e = view;
        }

        public int K_() {
            return this.f19542a;
        }

        public void c_(int i) {
            this.f19542a = i;
        }

        public View k() {
            return this.e;
        }
    }

    public static class GroupViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19541a;
        public ImageButton b;

        public GroupViewHolder(View view) {
            super(view);
            if (view instanceof ViewGroup) {
                this.f19541a = (TextView) view.findViewById(R.id.txt_group_title);
                this.b = (ImageButton) view.findViewById(R.id.imb_group_top);
            }
        }
    }

    public static class ChildViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f19540a;
        public TextView b;
        public SimpleDraweeView c;
        public ImageView d;

        public ChildViewHolder(View view) {
            super(view);
            this.f19540a = (TextView) view.findViewById(R.id.name);
            this.b = (TextView) view.findViewById(R.id.name_status);
            this.c = (SimpleDraweeView) view.findViewById(R.id.image);
            this.d = (ImageView) view.findViewById(R.id.img_handle);
        }
    }

    public DeviceSortAdapter(DeviceSortActivity deviceSortActivity, RecyclerViewExpandableItemManager recyclerViewExpandableItemManager) {
        this.b = deviceSortActivity;
        this.c = recyclerViewExpandableItemManager;
        setHasStableIds(true);
    }

    public int a() {
        return this.d.size();
    }

    public int a(int i) {
        return this.d.get(i).c.size();
    }

    public long b(int i) {
        return (long) this.d.get(i).f19530a.hashCode();
    }

    public long c(int i, int i2) {
        return (long) this.d.get(i).c.get(i2).did.hashCode();
    }

    /* renamed from: c */
    public GroupViewHolder a(ViewGroup viewGroup, int i) {
        return new GroupViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.smart_device_group_sort_list_item, viewGroup, false));
    }

    /* renamed from: d */
    public ChildViewHolder b(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.client_all_item_v2_sort, viewGroup, false));
    }

    /* renamed from: a */
    public void b(GroupViewHolder groupViewHolder, final int i, int i2) {
        TextView textView = groupViewHolder.f19541a;
        textView.setText("" + DeviceGroup.a(this.d.get(i)));
        groupViewHolder.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceSortAdapter.this.d(i);
            }
        });
    }

    /* renamed from: a */
    public void b(ChildViewHolder childViewHolder, int i, int i2, int i3) {
        DeviceGroup deviceGroup = this.d.get(i);
        ((ListItemView) childViewHolder.e).setPosition(i2);
        Device a2 = i2 < deviceGroup.c.size() ? deviceGroup.a(i2) : null;
        if (a2 != null) {
            DeviceFactory.b(a2.model, childViewHolder.c);
            childViewHolder.f19540a.setText(a2.getName());
            if (a2.isOnline) {
                childViewHolder.f19540a.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.black_80_transparent));
            } else {
                childViewHolder.f19540a.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.black_30_transparent));
            }
        }
    }

    public boolean a(View view, View view2, int i, int i2) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        view2.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i, i2);
    }

    public boolean a(ChildViewHolder childViewHolder, int i, int i2, int i3, int i4) {
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
        return rect2.contains(i3, i4);
    }

    public ItemDraggableRange a(ChildViewHolder childViewHolder, int i, int i2) {
        return new GroupPositionItemDraggableRange(i, i);
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 != i4) {
            this.d.get(i).a(i2, i4);
            this.b.orderChanged();
        }
    }

    public void d(int i) {
        if (i >= 0 && i < this.d.size()) {
            this.d.add(0, this.d.remove(i));
            notifyDataSetChanged();
            this.b.orderChanged();
        }
    }
}
