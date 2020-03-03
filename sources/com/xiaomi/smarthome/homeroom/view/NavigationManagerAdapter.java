package com.xiaomi.smarthome.homeroom.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ChildPositionItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableDraggableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.RoomListActivity;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NavigationManagerAdapter extends AbstractExpandableItemAdapter<BaseGroupViewHolder, ChildViewHolder> implements ExpandableDraggableItemAdapter<BaseGroupViewHolder, ChildViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    protected Context f18366a;
    protected List<Room> b = new ArrayList();
    private boolean c = false;
    private Set<String> d = new HashSet();
    private ArrayList<Room> e = new ArrayList<>();
    private EditModeListener f;
    private boolean g = false;
    /* access modifiers changed from: private */
    public boolean h = false;
    private boolean i = true;
    private boolean j = true;
    /* access modifiers changed from: private */
    public Home k;
    private View l;
    private final int m = 0;
    private final int n = 1;
    private final int o = 2;
    private final int p = 3;
    private final int q = 0;
    private final int r = 1;
    private List<GroupBean> s = new ArrayList();

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

    private class GroupBean {

        /* renamed from: a  reason: collision with root package name */
        String f18370a;
        List<ChildBean> b = new ArrayList();
        int c = 0;

        GroupBean(String str, List<ChildBean> list) {
            this.f18370a = str;
            this.b.clear();
            this.b.addAll(list);
        }
    }

    private class ChildBean {

        /* renamed from: a  reason: collision with root package name */
        int f18367a;
        List<Device> b = new ArrayList();
        String c;
        String d;
        String e;

        ChildBean(int i, List<Device> list, String str, String str2) {
            this.f18367a = i;
            this.b.clear();
            this.b.addAll(list);
            this.c = str;
            this.e = str2;
        }
    }

    public NavigationManagerAdapter(Activity activity, Home home) {
        this.f18366a = activity;
        a(home);
        setHasStableIds(true);
    }

    public void a(Home home) {
        if (home == null) {
            home = HomeManager.a().m();
        }
        if (home != null) {
            this.k = home;
            if (home.d() == null) {
                this.b = new ArrayList();
            } else {
                this.b = new ArrayList(home.d());
            }
            b();
            notifyDataSetChanged();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0104 A[LOOP:3: B:33:0x00fe->B:35:0x0104, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x019b  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0206  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r14 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r1 = r14.s
            r1.clear()
            android.content.Context r1 = r14.f18366a
            java.lang.String r2 = "prefs_lite_config"
            java.lang.String r3 = "sh_lite_config_show_category"
            r4 = 1
            boolean r1 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.b((android.content.Context) r1, (java.lang.String) r2, (java.lang.String) r3, (boolean) r4)
            r14.i = r1
            boolean r1 = r14.i
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L_0x00f4
            com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
            com.xiaomi.smarthome.device.utils.DeviceTagInterface r1 = r1.b()
            com.xiaomi.smarthome.homeroom.model.Home r5 = r14.k
            java.lang.String r5 = r5.j()
            java.util.Map r5 = r1.j(r5)
            java.util.Set r5 = r5.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0037:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00df
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            r12 = r7
            java.lang.String r12 = (java.lang.String) r12
            java.util.ArrayList r7 = new java.util.ArrayList
            java.lang.Object r6 = r6.getValue()
            java.util.Collection r6 = (java.util.Collection) r6
            r7.<init>(r6)
            boolean r6 = r7.isEmpty()
            if (r6 != 0) goto L_0x0037
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r6 = 0
        L_0x0061:
            int r8 = r7.size()
            if (r6 >= r8) goto L_0x007d
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.Object r9 = r7.get(r6)
            java.lang.String r9 = (java.lang.String) r9
            com.xiaomi.smarthome.device.Device r8 = r8.b((java.lang.String) r9)
            if (r8 == 0) goto L_0x007a
            r11.add(r8)
        L_0x007a:
            int r6 = r6 + 1
            goto L_0x0061
        L_0x007d:
            boolean r6 = r11.isEmpty()
            if (r6 != 0) goto L_0x0037
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r6 = r1.d(r12)
            if (r6 == 0) goto L_0x0037
            com.xiaomi.smarthome.homeroom.HomeManager r7 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = r6.f15435a
            r8.append(r9)
            java.lang.String r9 = ""
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            java.util.List r7 = r7.a((java.lang.String) r2, (java.lang.String) r8)
            r8 = 0
        L_0x00a5:
            int r9 = r7.size()
            if (r8 >= r9) goto L_0x00d0
            java.lang.Object r9 = r7.get(r8)
            java.lang.String r9 = (java.lang.String) r9
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 == 0) goto L_0x00b8
            goto L_0x00cd
        L_0x00b8:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r10 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            com.xiaomi.smarthome.device.Device r9 = r10.b((java.lang.String) r9)
            boolean r10 = r11.contains(r9)
            if (r10 == 0) goto L_0x00cd
            r11.remove(r9)
            r11.add(r3, r9)
            goto L_0x00d0
        L_0x00cd:
            int r8 = r8 + 1
            goto L_0x00a5
        L_0x00d0:
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean r7 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean
            r10 = 2
            java.lang.String r13 = r6.f15435a
            r8 = r7
            r9 = r14
            r8.<init>(r10, r11, r12, r13)
            r0.add(r7)
            goto L_0x0037
        L_0x00df:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x00f4
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean r1 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean
            android.content.Context r5 = r14.f18366a
            r6 = 2131499527(0x7f0c1a07, float:1.8622706E38)
            java.lang.String r5 = r5.getString(r6)
            r1.<init>(r5, r0)
            goto L_0x00f5
        L_0x00f4:
            r1 = r2
        L_0x00f5:
            r0.clear()
            java.util.List<com.xiaomi.smarthome.homeroom.model.Room> r5 = r14.b
            java.util.Iterator r5 = r5.iterator()
        L_0x00fe:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x012c
            java.lang.Object r6 = r5.next()
            com.xiaomi.smarthome.homeroom.model.Room r6 = (com.xiaomi.smarthome.homeroom.model.Room) r6
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean r13 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean
            r9 = 0
            com.xiaomi.smarthome.homeroom.HomeManager r7 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.util.List r10 = r7.c((com.xiaomi.smarthome.homeroom.model.Room) r6)
            java.lang.String r11 = r6.e()
            java.lang.String r12 = r6.d()
            r7 = r13
            r8 = r14
            r7.<init>(r9, r10, r11, r12)
            java.lang.String r6 = r6.a()
            r13.d = r6
            r0.add(r13)
            goto L_0x00fe
        L_0x012c:
            com.xiaomi.smarthome.homeroom.model.Home r5 = r14.k
            java.util.List r5 = r5.m()
            if (r5 == 0) goto L_0x0168
            com.xiaomi.smarthome.homeroom.model.Home r5 = r14.k
            java.util.List r5 = r5.m()
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0168
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean r5 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean
            r8 = 1
            com.xiaomi.smarthome.homeroom.HomeManager r6 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            com.xiaomi.smarthome.homeroom.model.Home r7 = r14.k
            java.lang.String r7 = r7.j()
            java.util.List r9 = r6.m((java.lang.String) r7)
            android.content.Context r6 = r14.f18366a
            android.content.res.Resources r6 = r6.getResources()
            r7 = 2131499554(0x7f0c1a22, float:1.862276E38)
            java.lang.String r10 = r6.getString(r7)
            java.lang.String r11 = "mijia.roomid.default"
            r6 = r5
            r7 = r14
            r6.<init>(r8, r9, r10, r11)
            r0.add(r5)
        L_0x0168:
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x017c
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean r2 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean
            android.content.Context r5 = r14.f18366a
            r6 = 2131499530(0x7f0c1a0a, float:1.8622712E38)
            java.lang.String r5 = r5.getString(r6)
            r2.<init>(r5, r0)
        L_0x017c:
            android.content.Context r5 = r14.f18366a
            java.lang.String r6 = "prefs_lite_config"
            java.lang.String r7 = "sh_lite_config_show_category_in_front_of_room"
            boolean r5 = com.xiaomi.smarthome.library.common.util.SharePrefsManager.b((android.content.Context) r5, (java.lang.String) r6, (java.lang.String) r7, (boolean) r3)
            r14.j = r5
            boolean r5 = r14.j
            if (r5 == 0) goto L_0x019b
            if (r1 == 0) goto L_0x0193
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r5 = r14.s
            r5.add(r1)
        L_0x0193:
            if (r2 == 0) goto L_0x01a9
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r1 = r14.s
            r1.add(r2)
            goto L_0x01a9
        L_0x019b:
            if (r2 == 0) goto L_0x01a2
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r5 = r14.s
            r5.add(r2)
        L_0x01a2:
            if (r1 == 0) goto L_0x01a9
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r2 = r14.s
            r2.add(r1)
        L_0x01a9:
            r0.clear()
            com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager r1 = com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager.a()
            java.util.List r8 = r1.e()
            if (r8 == 0) goto L_0x01d6
            boolean r1 = r8.isEmpty()
            if (r1 != 0) goto L_0x01d6
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean r1 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean
            r7 = 3
            android.content.Context r2 = r14.f18366a
            android.content.res.Resources r2 = r2.getResources()
            r5 = 2131498870(0x7f0c1776, float:1.8621374E38)
            java.lang.String r9 = r2.getString(r5)
            java.lang.String r10 = "mijia.roomid.share"
            r5 = r1
            r6 = r14
            r5.<init>(r7, r8, r9, r10)
            r0.add(r1)
        L_0x01d6:
            com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager r1 = com.xiaomi.smarthome.homeroom.MultiHomeDeviceManager.a()
            java.util.List r8 = r1.g()
            if (r8 == 0) goto L_0x0200
            boolean r1 = r8.isEmpty()
            if (r1 != 0) goto L_0x0200
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean r1 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$ChildBean
            r7 = 3
            android.content.Context r2 = r14.f18366a
            android.content.res.Resources r2 = r2.getResources()
            r5 = 2131497210(0x7f0c10fa, float:1.8618007E38)
            java.lang.String r9 = r2.getString(r5)
            java.lang.String r10 = "mijia.roomid.nearby"
            r5 = r1
            r6 = r14
            r5.<init>(r7, r8, r9, r10)
            r0.add(r1)
        L_0x0200:
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x0219
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean r1 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean
            android.content.Context r2 = r14.f18366a
            r5 = 2131495214(0x7f0c092e, float:1.8613958E38)
            java.lang.String r2 = r2.getString(r5)
            r1.<init>(r2, r0)
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r0 = r14.s
            r0.add(r1)
        L_0x0219:
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r0 = r14.s
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L_0x023f
            com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean r0 = new com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean
            android.content.Context r1 = r14.f18366a
            android.content.res.Resources r1 = r1.getResources()
            r2 = 2131493952(0x7f0c0440, float:1.8611399E38)
            java.lang.String r1 = r1.getString(r2)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r0.<init>(r1, r2)
            r0.c = r4
            java.util.List<com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter$GroupBean> r1 = r14.s
            r1.add(r3, r0)
        L_0x023f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.view.NavigationManagerAdapter.b():void");
    }

    public boolean a(ChildViewHolder childViewHolder, int i2, int i3, int i4, int i5) {
        View a2 = childViewHolder != null ? childViewHolder.g : null;
        if (a2 == null || a2.getVisibility() != 0) {
            return false;
        }
        Rect rect = new Rect();
        childViewHolder.itemView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        a2.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i4, i5);
    }

    public ItemDraggableRange a(ChildViewHolder childViewHolder, int i2, int i3) {
        return new ChildPositionItemDraggableRange(0, this.b.size() > 0 ? this.b.size() - 1 : 0);
    }

    public void a(int i2, int i3, int i4, int i5) {
        if (i2 == i4 && i3 != i5 && this.b != null && i3 < this.b.size() && i5 < this.b.size()) {
            this.b.add(i5, this.b.remove(i3));
            List<ChildBean> list = this.s.get(i2).b;
            list.add(i5, list.remove(i3));
            this.c = true;
            notifyItemMoved(i3, i5);
        }
    }

    public int a() {
        return this.s.size();
    }

    public int a(int i2) {
        return this.s.get(i2).b.size();
    }

    public long c(int i2, int i3) {
        GroupBean groupBean = this.s.get(i2);
        if (TextUtils.isEmpty(groupBean.f18370a)) {
            return 0;
        }
        return (long) (groupBean.f18370a.hashCode() + (TextUtils.isEmpty(groupBean.b.get(i3).c) ? 0 : groupBean.b.get(i3).c.hashCode()));
    }

    public int c(int i2) {
        return this.s.get(i2).c;
    }

    public int d(int i2, int i3) {
        return this.s.get(i2).b.get(i3).f18367a;
    }

    /* renamed from: c */
    public BaseGroupViewHolder a(ViewGroup viewGroup, int i2) {
        LayoutInflater from = LayoutInflater.from(this.f18366a);
        if (i2 == 1) {
            return new BaseGroupViewHolder(this.l);
        }
        return new CommonGroupTitleViewHolder(from.inflate(R.layout.tag_group_item_common_4, viewGroup, false));
    }

    /* renamed from: d */
    public ChildViewHolder b(ViewGroup viewGroup, int i2) {
        return new ChildViewHolder(LayoutInflater.from(this.f18366a).inflate(R.layout.tag_child_item_sort_edit, viewGroup, false));
    }

    /* renamed from: a */
    public void b(BaseGroupViewHolder baseGroupViewHolder, int i2, int i3) {
        if (i3 != 1) {
            baseGroupViewHolder.a((RecyclerView.Adapter) this, this.s.get(i2).f18370a);
        }
    }

    /* renamed from: a */
    public void b(ChildViewHolder childViewHolder, int i2, int i3, int i4) {
        childViewHolder.a(this.s.get(i2).b.get(i3));
        childViewHolder.e(i3 == this.s.get(i2).b.size() + -1 ? 8 : 0);
    }

    public int c() {
        return this.b.size() + ((this.k == null || this.k.m() == null || this.k.m().size() <= 0) ? 0 : 1);
    }

    public Room d(int i2) {
        if (i2 < 0 || i2 >= this.b.size()) {
            return null;
        }
        return this.b.get(i2);
    }

    public void a(boolean z) {
        String str;
        if (this.c) {
            if (z) {
                ToastUtil.a((int) R.string.toast_sort_succeed);
                if (this.k != null) {
                    str = this.k.j();
                } else {
                    str = HomeManager.a().l();
                }
                HomeManager.a().a(str, this.b);
            } else {
                this.b.clear();
                this.b.addAll(this.e);
                notifyDataSetChanged();
            }
            this.c = false;
        }
    }

    public void a(View view) {
        this.l = view;
    }

    public void a(EditModeListener editModeListener) {
        this.f = editModeListener;
    }

    public void d() {
        this.g = true;
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public void e() {
        this.g = false;
        this.h = false;
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public void f() {
        this.h = true;
        this.d.clear();
        this.e = new ArrayList<>(this.b);
        notifyDataSetChanged();
    }

    public void g() {
        this.h = false;
        this.d.clear();
        this.e.clear();
        notifyDataSetChanged();
    }

    public void a(Room room, boolean z) {
        if (z) {
            this.d.add(room.d());
        } else {
            this.d.remove(room.d());
        }
        this.f.a(this.d.size());
    }

    public void b(boolean z) {
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            a(this.b.get(i2), z);
        }
    }

    public boolean h() {
        return this.g;
    }

    public boolean i() {
        return this.h;
    }

    public Set<String> j() {
        return this.d;
    }

    public int k() {
        return this.b.size();
    }

    class ChildViewHolder extends BaseViewHolder {
        private View c;
        private TextView d;
        private TextView e;
        private View f;
        /* access modifiers changed from: private */
        public View g;
        private View h;
        private View i;
        private CheckBox j;
        private Set<String> k;
        private View l;
        private View m;

        public ChildViewHolder(View view) {
            super(view);
            this.c = view.findViewById(R.id.root);
            this.d = (TextView) view.findViewById(R.id.title);
            this.e = (TextView) view.findViewById(R.id.desc);
            this.f = view.findViewById(R.id.next_btn);
            this.g = view.findViewById(R.id.sort_icon);
            this.h = view.findViewById(R.id.delete_btn);
            this.i = view.findViewById(R.id.divider_item);
            this.j = (CheckBox) view.findViewById(R.id.checkbox);
            this.l = view.findViewById(R.id.view_group);
            this.m = view.findViewById(R.id.desc_edit_mode);
        }

        public void a(final ChildBean childBean) {
            this.f.setVisibility(childBean.b.isEmpty() ? 8 : 0);
            this.g.setVisibility(8);
            if (NavigationManagerAdapter.this.h) {
                this.f.setVisibility(8);
                if (childBean.f18367a == 0) {
                    this.g.setVisibility(0);
                }
            }
            int size = childBean.b.size();
            if (size <= 1) {
                this.e.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_count, size, new Object[]{Integer.valueOf(size)}));
            } else {
                this.e.setText(SHApplication.getAppContext().getResources().getQuantityString(R.plurals.choose_device_device_counts, size, new Object[]{Integer.valueOf(size)}));
            }
            this.d.setText(childBean.c);
            this.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!NavigationManagerAdapter.this.h && !childBean.b.isEmpty()) {
                        RoomListActivity.showRoomDeviceList(NavigationManagerAdapter.this.f18366a, NavigationManagerAdapter.this.k.j(), childBean.e, childBean.c);
                    }
                }
            });
        }

        /* access modifiers changed from: package-private */
        public void e(int i2) {
            this.i.setVisibility(i2);
        }
    }
}
