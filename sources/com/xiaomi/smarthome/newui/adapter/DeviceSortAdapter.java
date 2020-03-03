package com.xiaomi.smarthome.newui.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ChildPositionItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.DeviceSortActivity;
import java.util.ArrayList;
import java.util.List;

public class DeviceSortAdapter extends AbstractExpandableItemAdapter<GroupViewHolder, ChildViewHolder> implements ExpandableDraggableItemAdapter<GroupViewHolder, ChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    static final String f20387a = "DeviceSortAdapter";
    DeviceSortActivity b;
    final RecyclerViewExpandableItemManager c;
    private boolean d = false;
    private List<Device> e = new ArrayList();
    private final int f;

    public int a() {
        return 1;
    }

    public ItemDraggableRange a(GroupViewHolder groupViewHolder, int i) {
        return null;
    }

    public void a(int i, int i2) {
    }

    /* renamed from: a */
    public void b(GroupViewHolder groupViewHolder, int i, int i2) {
    }

    public boolean a(GroupViewHolder groupViewHolder, int i, int i2, int i3) {
        return false;
    }

    public boolean a(GroupViewHolder groupViewHolder, int i, int i2, int i3, boolean z) {
        return false;
    }

    public long b(int i) {
        return 0;
    }

    public boolean b(int i, int i2) {
        return false;
    }

    public boolean b(int i, int i2, int i3, int i4) {
        return i == i3;
    }

    public int c(int i) {
        return 0;
    }

    public int d(int i, int i2) {
        return 0;
    }

    public static abstract class MyBaseViewHolder extends AbstractDraggableSwipeableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        private int f20390a;
        public View c;

        public MyBaseViewHolder(View view) {
            super(view);
            this.c = view;
        }

        public int K_() {
            return this.f20390a;
        }

        public void c_(int i) {
            this.f20390a = i;
        }

        public View k() {
            return this.c;
        }
    }

    public static class GroupViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f20389a;
        public TextView b;

        public GroupViewHolder(View view) {
            super(view);
            if (view instanceof ViewGroup) {
                this.f20389a = (TextView) view.findViewById(R.id.title_left);
                this.b = (TextView) view.findViewById(R.id.title_right);
                this.b.setVisibility(8);
            }
        }
    }

    public static class ChildViewHolder extends MyBaseViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f20388a;
        public View b;
        /* access modifiers changed from: private */
        public TextView d;
        private TextView e;
        /* access modifiers changed from: private */
        public SimpleDraweeView f;

        public ChildViewHolder(View view) {
            super(view);
            this.d = (TextView) view.findViewById(R.id.tv_device_name);
            this.e = (TextView) view.findViewById(R.id.tv_device_desc);
            this.e.setVisibility(8);
            this.f = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.f20388a = view.findViewById(R.id.sort_icon);
            this.f20388a.setVisibility(0);
            this.b = view.findViewById(R.id.divider);
        }
    }

    public DeviceSortAdapter(DeviceSortActivity deviceSortActivity, RecyclerViewExpandableItemManager recyclerViewExpandableItemManager) {
        this.b = deviceSortActivity;
        this.c = recyclerViewExpandableItemManager;
        HomeManager a2 = HomeManager.a();
        this.f = this.b.getType();
        if (this.f == 4 && !TextUtils.isEmpty(this.b.getRoomID())) {
            this.e = a2.c(a2.i(this.b.getRoomID()));
        } else if (this.f == 5) {
            this.e = a2.j();
        } else if (this.f == 1) {
            this.e = MultiHomeDeviceManager.a().e();
        }
        setHasStableIds(true);
    }

    public int a(int i) {
        return this.e.size();
    }

    public long c(int i, int i2) {
        if (i2 < 0 || i2 >= this.e.size()) {
            return 0;
        }
        return (long) this.e.get(i2).did.hashCode();
    }

    /* renamed from: c */
    public GroupViewHolder a(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tag_group_item_common_3, viewGroup, false);
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.height = DisplayUtils.a(20.0f);
        inflate.setLayoutParams(layoutParams);
        return new GroupViewHolder(inflate);
    }

    /* renamed from: d */
    public ChildViewHolder b(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_smart_group_list_child, viewGroup, false));
    }

    /* renamed from: a */
    public void b(ChildViewHolder childViewHolder, int i, int i2, int i3) {
        if (i2 >= 0 && i2 < this.e.size()) {
            Device device = this.e.get(i2);
            if (device.isNew) {
                device.isNew = false;
            }
            DeviceFactory.b(device.model, childViewHolder.f);
            childViewHolder.d.setText(device.getName());
            if (device.isOnline) {
                childViewHolder.d.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.black_80_transparent));
            } else {
                childViewHolder.d.setTextColor(SHApplication.getAppContext().getResources().getColor(R.color.black_30_transparent));
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) childViewHolder.b.getLayoutParams();
            int a2 = DisplayUtils.a((Context) this.b, 13.0f);
            if (i2 == this.e.size() - 1) {
                a2 = 0;
            }
            layoutParams.leftMargin = a2;
        }
    }

    public boolean a(ChildViewHolder childViewHolder, int i, int i2, int i3, int i4) {
        View view = childViewHolder.f20388a;
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        childViewHolder.itemView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i3, i4);
    }

    public ItemDraggableRange a(ChildViewHolder childViewHolder, int i, int i2) {
        return new ChildPositionItemDraggableRange(0, this.e.size() > 0 ? this.e.size() - 1 : 0);
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 != i4 && this.e != null && i2 < this.e.size() && i4 < this.e.size()) {
            this.e.add(i4, this.e.remove(i2));
            this.d = true;
            notifyItemMoved(i2, i4);
            this.b.orderChanged();
        }
    }

    public void b() {
        if (this.d) {
            ArrayList arrayList = new ArrayList();
            for (Device device : this.e) {
                arrayList.add(device.did);
            }
            List<Room> e2 = HomeManager.a().e();
            if (this.f == 4) {
                for (int i = 0; i < e2.size(); i++) {
                    Room room = e2.get(i);
                    if (room.d().equals(this.b.getRoomID())) {
                        room.a((List<String>) arrayList);
                    }
                }
            } else if (this.f == 5) {
                HomeManager.a().h(HomeManager.d).a((List<String>) arrayList);
                ArrayList arrayList2 = new ArrayList(arrayList);
                Home m = HomeManager.a().m();
                List m2 = m.m();
                if (m2 == null) {
                    m2 = new ArrayList();
                    m.b((List<String>) m2);
                }
                m2.clear();
                m2.addAll(arrayList2);
            } else if (this.f == 1) {
                HomeManager.a().h(HomeManager.e).a((List<String>) arrayList);
            }
            HomeManager.a().a((String) null, e2);
            this.d = false;
        }
    }
}
