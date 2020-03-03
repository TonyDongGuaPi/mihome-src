package com.xiaomi.smarthome.homeroom.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ChildPositionItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeMemberManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.HomeInviteInfo;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

public class HomeListAdapter extends AbstractExpandableItemAdapter<BaseGroupViewHolder, BaseViewHolder> implements ExpandableDraggableItemAdapter<BaseGroupViewHolder, BaseViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18360a = 0;
    public static final int b = 1;
    public static final int c = 0;
    public static final int d = 1;
    protected Context e;
    private boolean f = false;
    private List<Home> g = new ArrayList();
    private List<HomeInviteInfo> h = new ArrayList();
    private ArrayList<Home> i = new ArrayList<>();
    private View j;
    private EditModeListener k;
    private boolean l = false;
    private boolean m = false;
    private View.OnClickListener n;

    public interface EditModeListener {
        void a();

        void a(int i);
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
        return (long) i2;
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

    public int d(int i2, int i3) {
        return i2 == 0 ? 0 : 1;
    }

    public HomeListAdapter(Activity activity) {
        this.e = activity;
        setHasStableIds(true);
    }

    /* access modifiers changed from: protected */
    public void b() {
        List<Home> f2 = HomeManager.a().f();
        if (f2 == null) {
            this.g = new ArrayList();
        } else {
            this.g = new ArrayList(f2);
        }
        List<HomeInviteInfo> a2 = HomeMemberManager.a().a(HomeInviteInfo.f18314a);
        if (a2 == null) {
            this.h = new ArrayList();
        } else {
            this.h = a2;
        }
    }

    public int c() {
        return this.g.size();
    }

    public int d() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.g.size(); i3++) {
            if (!this.g.get(i3).p()) {
                i2++;
            }
        }
        return i2;
    }

    public int e() {
        return this.h.size();
    }

    public Context f() {
        return this.e;
    }

    public void g() {
        b();
        notifyDataSetChanged();
    }

    public boolean a(BaseViewHolder baseViewHolder, int i2, int i3, int i4, int i5) {
        View view;
        if (!(baseViewHolder instanceof HomeChildViewHolder) || (view = ((HomeChildViewHolder) baseViewHolder).f18358a) == null || view.getVisibility() != 0) {
            return false;
        }
        Rect rect = new Rect();
        baseViewHolder.itemView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i4, i5);
    }

    public ItemDraggableRange a(BaseViewHolder baseViewHolder, int i2, int i3) {
        if (i2 != 0) {
            return new ChildPositionItemDraggableRange(0, 0);
        }
        return new ChildPositionItemDraggableRange(0, this.g.size() > 0 ? this.g.size() - 1 : 0);
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (i2 == i4 && i3 != i5 && i2 == 0 && this.g != null && i3 < this.g.size() && i5 < this.g.size()) {
            this.g.add(i5, this.g.remove(i3));
            this.f = true;
            notifyItemMoved(i3, i5);
        }
    }

    public void a(View.OnClickListener onClickListener) {
        this.n = onClickListener;
    }

    public int a() {
        return this.h.size() > 0 ? 2 : 1;
    }

    public int a(int i2) {
        int i3 = this.j != null ? 1 : 0;
        if (i2 == 0) {
            return this.g.size() + i3;
        }
        return i2 == 1 ? this.h.size() : i3;
    }

    public long c(int i2, int i3) {
        if (i2 == 0 && i3 >= 0 && i3 < this.g.size()) {
            return (long) this.g.get(i3).j().hashCode();
        }
        if (i2 != 1 || i3 < 0 || i3 >= this.h.size()) {
            return 0;
        }
        return (long) this.h.get(i3).hashCode();
    }

    /* renamed from: c */
    public BaseGroupViewHolder a(ViewGroup viewGroup, int i2) {
        return new CommonGroupTitleViewHolder(LayoutInflater.from(this.e).inflate(R.layout.tag_group_item_common_6, viewGroup, false));
    }

    /* renamed from: d */
    public BaseViewHolder b(ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.e);
        if (i2 == 0) {
            HomeChildViewHolder homeChildViewHolder = new HomeChildViewHolder(from.inflate(R.layout.item_home_list, viewGroup, false), this.k, 0);
            homeChildViewHolder.a(this.n);
            return homeChildViewHolder;
        }
        InviteChildViewHolder inviteChildViewHolder = new InviteChildViewHolder(from.inflate(R.layout.item_home_invite_list, viewGroup, false), this.k);
        inviteChildViewHolder.a(this.n);
        return inviteChildViewHolder;
    }

    /* renamed from: a */
    public void b(BaseGroupViewHolder baseGroupViewHolder, int i2, int i3) {
        if (i2 == 0) {
            baseGroupViewHolder.a((RecyclerView.Adapter) this, this.e.getString(R.string.my_home_2));
        } else if (i2 == 1) {
            baseGroupViewHolder.a((RecyclerView.Adapter) this, this.e.getString(R.string.home_member_new_invite));
            ((RecyclerView.LayoutParams) baseGroupViewHolder.b.getLayoutParams()).setMargins(0, DisplayUtils.a(8.0f), 0, 0);
            if (baseGroupViewHolder instanceof CommonGroupTitleViewHolder) {
                ((CommonGroupTitleViewHolder) baseGroupViewHolder).f18357a.setVisibility(0);
            }
        }
    }

    /* renamed from: a */
    public void b(BaseViewHolder baseViewHolder, int i2, int i3, int i4) {
        boolean z = false;
        if (i4 == 0) {
            try {
                ((HomeChildViewHolder) baseViewHolder).a(this, this.g.get(i3), i2, i3);
                HomeChildViewHolder homeChildViewHolder = (HomeChildViewHolder) baseViewHolder;
                if (i3 == this.g.size() - 1) {
                    z = true;
                }
                homeChildViewHolder.a(z);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (i4 == 1) {
            ((InviteChildViewHolder) baseViewHolder).a(this, this.h.get(i3));
            InviteChildViewHolder inviteChildViewHolder = (InviteChildViewHolder) baseViewHolder;
            if (i3 == this.h.size() - 1) {
                z = true;
            }
            inviteChildViewHolder.a(z);
        }
    }

    public void a(boolean z) {
        if (this.f) {
            if (z) {
                ToastUtil.a(f().getResources().getText(R.string.toast_sort_succeed));
            } else {
                this.g.clear();
                this.g.addAll(this.i);
                notifyDataSetChanged();
            }
            this.f = false;
        }
    }

    public void a(View view) {
        this.j = view;
    }

    public void a(EditModeListener editModeListener) {
        this.k = editModeListener;
    }

    public void h() {
        this.l = true;
        this.i.clear();
        notifyDataSetChanged();
    }

    public void i() {
        this.l = false;
        this.m = false;
        this.i.clear();
        notifyDataSetChanged();
    }

    public void j() {
        this.m = true;
        this.i = new ArrayList<>(this.g);
        notifyDataSetChanged();
    }

    public boolean k() {
        return this.l;
    }

    public boolean l() {
        return this.m;
    }

    public int m() {
        return this.g.size();
    }
}
