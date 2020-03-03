package com.xiaomi.smarthome.miio.page.devicetag;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import java.util.List;

public class DeviceTagAdapter extends AbstractExpandableItemAdapter<DeviceTagGroupViewHolder, DeviceTagChildViewHolder> implements ExpandableDraggableItemAdapter<DeviceTagGroupViewHolder, DeviceTagChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    protected Context f19795a;
    protected List<DeviceTagGroup> b;
    protected String c;

    public ItemDraggableRange a(DeviceTagChildViewHolder deviceTagChildViewHolder, int i, int i2) {
        return null;
    }

    public ItemDraggableRange a(DeviceTagGroupViewHolder deviceTagGroupViewHolder, int i) {
        return null;
    }

    public void a(int i, int i2) {
    }

    public void a(int i, int i2, int i3, int i4) {
    }

    public boolean a(DeviceTagChildViewHolder deviceTagChildViewHolder, int i, int i2, int i3, int i4) {
        return false;
    }

    public boolean a(DeviceTagGroupViewHolder deviceTagGroupViewHolder, int i, int i2, int i3) {
        return false;
    }

    public boolean a(DeviceTagGroupViewHolder deviceTagGroupViewHolder, int i, int i2, int i3, boolean z) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void b() {
    }

    public boolean b(int i, int i2) {
        return false;
    }

    public boolean b(int i, int i2, int i3, int i4) {
        return false;
    }

    public DeviceTagAdapter(Activity activity, String str) {
        this.f19795a = activity;
        this.c = str;
        setHasStableIds(true);
    }

    public boolean c() {
        return TextUtils.equals(this.c, DeviceTagFragment.b);
    }

    public boolean d() {
        return TextUtils.equals(this.c, DeviceTagFragment.c);
    }

    public boolean e() {
        return TextUtils.equals(this.c, DeviceTagFragment.d);
    }

    public Context f() {
        return this.f19795a;
    }

    public void g() {
        b();
        notifyDataSetChanged();
    }

    public int a() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public int a(int i) {
        if (i >= this.b.size()) {
            return 0;
        }
        DeviceTagGroup deviceTagGroup = this.b.get(i);
        if (deviceTagGroup.w == null) {
            return 0;
        }
        return deviceTagGroup.w.size();
    }

    public long b(int i) {
        return (long) new String("" + this.b.get(i).t).hashCode();
    }

    public long c(int i, int i2) {
        DeviceTagGroup deviceTagGroup = this.b.get(i);
        DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(deviceTagGroup.t);
        sb.append(deviceTagChild.d);
        sb.append(deviceTagChild.e == null ? "" : deviceTagChild.e);
        return (long) new String(sb.toString()).hashCode();
    }

    public int c(int i) {
        if (i < this.b.size()) {
            return this.b.get(i).r;
        }
        return 0;
    }

    public int d(int i, int i2) {
        if (i < this.b.size()) {
            return this.b.get(i).s;
        }
        return 0;
    }

    /* renamed from: c */
    public DeviceTagGroupViewHolder a(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.f19795a);
        if (i == 0) {
            return new AllGroupViewHolder(from.inflate(R.layout.tag_group_item_all, (ViewGroup) null));
        }
        if (i == 3) {
            return new CommonGroupViewHolder(from.inflate(R.layout.tag_group_item_common, (ViewGroup) null));
        }
        if (i == 4) {
            return new SelectGroupViewHolder(from.inflate(R.layout.tag_group_item_select, (ViewGroup) null));
        }
        if (i == 5) {
            return new CommonGroupViewHolder(from.inflate(R.layout.tag_group_item_common_2, (ViewGroup) null));
        }
        if (i == 6) {
            return new CommonGroupViewHolder(from.inflate(R.layout.tag_group_item_common_3, (ViewGroup) null));
        }
        throw new IllegalStateException("Illegal group view type " + i);
    }

    /* renamed from: d */
    public DeviceTagChildViewHolder b(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.f19795a);
        if (i == 0) {
            return new SwitchChildViewHolder(from.inflate(R.layout.tag_child_item_switch, viewGroup, false));
        }
        if (i == 1) {
            return new SelectChildViewHolder(from.inflate(R.layout.tag_child_item_select, viewGroup, false));
        }
        if (i == 2) {
            return new SortEditChildViewHolder(from.inflate(R.layout.tag_child_item_sort_edit, viewGroup, false));
        }
        if (i == 3) {
            return new SortCommonChildViewHolder(from.inflate(R.layout.tag_child_item_sort_common, viewGroup, false));
        }
        if (i == 4) {
            return new EditorNoneChildViewHolder(from.inflate(R.layout.tag_child_item_editor_none, viewGroup, false));
        }
        if (i == 5) {
            return new EditorCommonChildViewHolder(from.inflate(R.layout.tag_child_item_editor_common, viewGroup, false));
        }
        if (i == 6) {
            return new SelectIndentChildViewHolder(from.inflate(R.layout.tag_child_item_select_indent, viewGroup, false));
        }
        if (i == 7) {
            return new EditorNoneChildViewHolder(from.inflate(R.layout.tag_child_item_empty, viewGroup, false));
        }
        if (i == 8) {
            return new AddSelectChildViewHolder(from.inflate(R.layout.tag_child_item_add_select, viewGroup, false));
        }
        if (i == 9) {
            return new EditorSelectChildViewHolder(from.inflate(R.layout.tag_child_item_editor_select, viewGroup, false), this.f19795a);
        }
        throw new IllegalStateException("Illegal child view type " + i);
    }

    /* renamed from: a */
    public void b(DeviceTagGroupViewHolder deviceTagGroupViewHolder, int i, int i2) {
        if (i >= 0 && i < this.b.size()) {
            deviceTagGroupViewHolder.a(this, this.b.get(i), i);
        }
    }

    /* renamed from: a */
    public void b(DeviceTagChildViewHolder deviceTagChildViewHolder, int i, int i2, int i3) {
        if (i >= 0 && i < this.b.size()) {
            deviceTagChildViewHolder.a(this, this.b.get(i), i, i2);
        }
    }
}
