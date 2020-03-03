package com.xiaomi.smarthome.homeroom.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ChildPositionItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RoomListAdapter extends AbstractExpandableItemAdapter<BaseGroupViewHolder, RoomChildViewHolder> implements ExpandableDraggableItemAdapter<BaseGroupViewHolder, RoomChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    private boolean f18377a = false;
    protected Context b;
    protected List<Room> c = new ArrayList();
    private Set<String> d = new HashSet();
    private ArrayList<Room> e = new ArrayList<>();
    private View f;
    private EditModeListener g;
    private OnItemClickListener h;
    private boolean i = false;
    private boolean j = false;
    private Home k;
    private int l = 0;
    private int m = 1;

    public interface EditModeListener {
        void a();

        void a(int i);
    }

    public interface OnItemClickListener {
        void a(int i, int i2);
    }

    public int a() {
        return 1;
    }

    public ItemDraggableRange a(BaseGroupViewHolder baseGroupViewHolder, int i2) {
        return null;
    }

    public void a(int i2, int i3) {
    }

    public boolean a(BaseGroupViewHolder baseGroupViewHolder, int i2, int i3, int i4) {
        return false;
    }

    public boolean a(BaseGroupViewHolder baseGroupViewHolder, int i2, int i3, int i4, boolean z) {
        return false;
    }

    public long b(int i2) {
        return 0;
    }

    public boolean b(int i2, int i3) {
        return false;
    }

    public boolean b(int i2, int i3, int i4, int i5) {
        return i2 == i4;
    }

    public int c(int i2) {
        return 0;
    }

    public RoomListAdapter(Activity activity) {
        this.b = activity;
        setHasStableIds(true);
    }

    /* access modifiers changed from: protected */
    public void a(Home home) {
        List<Room> list;
        this.k = home;
        if (home == null) {
            list = HomeManager.a().e();
        } else {
            list = home.d();
        }
        if (list == null) {
            this.c = new ArrayList();
        } else {
            this.c = new ArrayList(list);
        }
    }

    public Context c() {
        return this.b;
    }

    public void d() {
        a((Home) null);
        notifyDataSetChanged();
    }

    public void b(Home home) {
        a(home);
        notifyDataSetChanged();
    }

    public boolean a(RoomChildViewHolder roomChildViewHolder, int i2, int i3, int i4, int i5) {
        View view = roomChildViewHolder instanceof RoomChildViewHolder ? roomChildViewHolder.e : null;
        if (view == null || view.getVisibility() != 0) {
            return false;
        }
        Rect rect = new Rect();
        roomChildViewHolder.itemView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i4, i5);
    }

    public ItemDraggableRange a(RoomChildViewHolder roomChildViewHolder, int i2, int i3) {
        return new ChildPositionItemDraggableRange(0, this.c.size() > 0 ? this.c.size() - 1 : 0);
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (i2 == i4 && i3 != i5 && this.c != null && i3 < this.c.size() && i5 < this.c.size()) {
            this.c.add(i5, this.c.remove(i3));
            this.f18377a = true;
            notifyItemMoved(i3, i5);
        }
    }

    public int a(int i2) {
        int i3 = this.f != null ? 1 : 0;
        return i2 == 0 ? this.c.size() + i3 : i3;
    }

    public int e() {
        return this.c.size() + ((this.f == null || this.f.findViewById(R.id.view_group) == null || this.f.findViewById(R.id.view_group).getVisibility() != 0) ? 0 : 1);
    }

    public long c(int i2, int i3) {
        Room room;
        if (i3 < 0 || i3 >= this.c.size() || (room = this.c.get(i3)) == null || TextUtils.isEmpty(room.d())) {
            return 0;
        }
        return (long) room.d().hashCode();
    }

    public int d(int i2, int i3) {
        if (this.f != null && i2 == 0 && i3 == a(i2) - 1) {
            return this.m;
        }
        return this.l;
    }

    /* renamed from: c */
    public BaseGroupViewHolder a(ViewGroup viewGroup, int i2) {
        return new CommonGroupTitleViewHolder(LayoutInflater.from(this.b).inflate(R.layout.tag_group_item_common_6, viewGroup, false));
    }

    /* renamed from: d */
    public RoomChildViewHolder b(ViewGroup viewGroup, int i2) {
        if (i2 == this.m) {
            return new RoomChildViewHolder(this.f, this.g);
        }
        return new RoomEditChildViewHolder(LayoutInflater.from(this.b).inflate(R.layout.tag_child_item_sort_edit, viewGroup, false), this.g);
    }

    /* renamed from: a */
    public void b(BaseGroupViewHolder baseGroupViewHolder, int i2, int i3) {
        if (i2 >= 0) {
            baseGroupViewHolder.a((RecyclerView.Adapter) this, this.b.getString(R.string.tag_custom_title));
        }
    }

    public Room d(int i2) {
        if (i2 < 0 || i2 >= this.c.size()) {
            return null;
        }
        return this.c.get(i2);
    }

    /* renamed from: a */
    public void b(RoomChildViewHolder roomChildViewHolder, int i2, int i3, int i4) {
        if (i3 < 0 || i3 >= this.c.size()) {
            getItemCount();
        } else {
            roomChildViewHolder.a(this, this.c.get(i3), i2, i3);
            boolean z = true;
            if (i3 != this.c.size() - 1) {
                z = false;
            }
            roomChildViewHolder.a(z);
            roomChildViewHolder.a(this.h);
        }
        if (i4 == this.m) {
            roomChildViewHolder.a(this, i2, i3, this.k);
        }
    }

    public void a(boolean z) {
        String str;
        if (this.f18377a) {
            if (z) {
                ToastUtil.a(c().getResources().getText(R.string.toast_sort_succeed));
                if (this.k != null) {
                    str = this.k.j();
                } else {
                    str = HomeManager.a().l();
                }
                HomeManager.a().a(str, this.c);
            } else {
                this.c.clear();
                this.c.addAll(this.e);
                notifyDataSetChanged();
            }
            this.f18377a = false;
        }
    }

    public void a(View view) {
        this.f = view;
    }

    public void a(EditModeListener editModeListener) {
        this.g = editModeListener;
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.h = onItemClickListener;
    }

    public void f() {
        this.i = true;
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public void g() {
        this.i = false;
        this.j = false;
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public void h() {
        this.j = true;
        this.d.clear();
        this.e = new ArrayList<>(this.c);
        notifyDataSetChanged();
    }

    public void a(Room room, boolean z) {
        if (z) {
            this.d.add(room.d());
        } else {
            this.d.remove(room.d());
        }
        this.g.a(this.d.size());
    }

    public void b(boolean z) {
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            a(this.c.get(i2), z);
        }
    }

    public boolean i() {
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public Set<String> k() {
        return this.d;
    }

    public int l() {
        return this.c.size();
    }
}
