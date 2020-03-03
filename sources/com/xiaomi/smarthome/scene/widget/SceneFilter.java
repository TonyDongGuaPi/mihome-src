package com.xiaomi.smarthome.scene.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.widget.FixHeightRecyclerView;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SceneFilter {

    /* renamed from: a  reason: collision with root package name */
    public Room f21719a;
    public Room b;
    public Home c;
    /* access modifiers changed from: private */
    public Context d;
    private boolean e = false;
    private ViewDataModel f;
    private ViewGroup g;
    private View h;
    private View i;
    private View j;
    private TextView k;
    /* access modifiers changed from: private */
    public View l;
    /* access modifiers changed from: private */
    public FixHeightRecyclerView m;
    private FilterAdapter n;
    /* access modifiers changed from: private */
    public int[] o = new int[2];
    /* access modifiers changed from: private */
    public View p;
    private int q = -1;
    private Home r;
    private Room s;
    private DeviceTagInterface.Category t;
    private Map<String, List<Room>> u = new HashMap();
    private final ViewTreeObserver.OnGlobalLayoutListener v = new ViewTreeObserver.OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            try {
                SceneFilter.this.p.getLocationOnScreen(SceneFilter.this.o);
                int height = SceneFilter.this.o[1] + SceneFilter.this.p.getHeight();
                if (height - SceneFilter.this.p.getHeight() <= 0) {
                    SceneFilter.this.a(false);
                    return;
                }
                SceneFilter.this.m.setY((float) height);
                SceneFilter.this.l.setY((float) (height - SceneFilter.this.p.getHeight()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public abstract void a(View view);

    public abstract void a(TextView textView);

    public abstract void a(Home home, DeviceTagInterface.Category category);

    public abstract void a(Home home, Room room);

    public abstract void a(Home home, List<Room> list);

    public abstract int b(Home home, DeviceTagInterface.Category category);

    public abstract int b(Home home, Room room);

    public abstract void b(Home home);

    public abstract void c(Home home);

    public SceneFilter(Context context, ViewGroup viewGroup, View view) {
        this.d = context;
        this.g = viewGroup;
        this.p = view;
        this.h = LayoutInflater.from(context).inflate(R.layout.scene_filter_menu_layout, (ViewGroup) null);
        this.i = this.h.findViewById(R.id.bg_mask);
        this.i.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SceneFilter.this.e(view);
            }
        });
        this.j = this.h.findViewById(R.id.tv_room_tag_filter);
        this.j.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SceneFilter.this.d(view);
            }
        });
        this.k = (TextView) this.h.findViewById(R.id.tv_home_filter);
        this.k.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SceneFilter.this.c(view);
            }
        });
        this.l = this.h.findViewById(R.id.layout_filter);
        this.l.findViewById(R.id.arrow_down_img).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SceneFilter.this.b(view);
            }
        });
        this.m = (FixHeightRecyclerView) this.h.findViewById(R.id.recycler_tag);
        this.m.setLayoutManager(new LinearLayoutManager(context));
        this.m.setMaxLines(10);
        this.n = new FilterAdapter();
        this.m.setAdapter(this.n);
        if (this.p != null) {
            this.p.findViewById(R.id.arrow_down_img).getLocationInWindow(this.o);
            this.p.getViewTreeObserver().addOnGlobalLayoutListener(this.v);
        }
        this.f21719a = new Room();
        this.f21719a.d(SelectRoomDialogView.ALL_ROOM_ID);
        this.f21719a.e(context.getString(R.string.tag_all));
        this.b = new Room();
        this.b.d(SelectRoomDialogView.DEFAULT_ROOM_ID);
        this.b.e(context.getString(R.string.tag_recommend_defaultroom));
        Home.Builder builder = new Home.Builder();
        builder.f("ALL_HOME");
        builder.g(context.getString(R.string.tag_all));
        this.c = builder.a();
        this.f = new ViewDataModel();
        this.f.e = 1;
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        a(this.q == 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        if (this.q == 1) {
            a(false);
        } else {
            a(view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (this.q == 0) {
            a(true);
        } else {
            a((TextView) view);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        this.k.performClick();
    }

    public void a() {
        if (this.r == null) {
            Home m2 = HomeManager.a().m();
            if (m2 == null || !m2.p()) {
                this.r = this.c;
                this.s = this.f21719a;
                this.t = null;
            } else {
                this.r = m2;
                this.s = this.f21719a;
                this.t = null;
            }
        }
        if (this.r != null) {
            int i2 = 0;
            for (Home p2 : HomeManager.a().f()) {
                if (p2.p()) {
                    i2++;
                    continue;
                }
                if (i2 > 1) {
                    break;
                }
            }
            if (i2 == 1) {
                this.r = this.c;
            }
            if (this.k != null) {
                this.k.setText(this.r.k());
            }
            b(this.r);
        }
    }

    public void a(Home home) {
        this.r = home;
        this.s = null;
        this.t = null;
    }

    public void b() {
        if (this.g.indexOfChild(this.h) < 0) {
            this.g.addView(this.h);
        }
        this.q = 0;
        if (this.l != null) {
            this.l.findViewById(R.id.arrow_down_img).animate().rotation(180.0f).setDuration(125).setInterpolator(new AccelerateDecelerateInterpolator());
        }
        this.n.a((Integer) 0, g());
        this.n.notifyDataSetChanged();
    }

    public void c() {
        List<Room> list;
        Map<String, List<String>> map;
        int i2;
        Map<String, List<String>> j2;
        int i3;
        if (this.q == 0 && this.l != null) {
            ViewPropertyAnimator startDelay = this.l.findViewById(R.id.arrow_down_img).animate().rotation(0.0f).setDuration(125).setStartDelay(125);
            startDelay.setInterpolator(new AccelerateDecelerateInterpolator());
            startDelay.start();
        }
        if (this.g.indexOfChild(this.h) < 0) {
            this.g.addView(this.h);
        }
        this.q = 1;
        if (this.r != null) {
            ArrayList arrayList = new ArrayList();
            if (TextUtils.equals(this.r.j(), this.c.j())) {
                this.u.clear();
                List<Home> f2 = HomeManager.a().f();
                list = null;
                int i4 = 0;
                while (true) {
                    if (f2 == null) {
                        i3 = 0;
                    } else {
                        i3 = f2.size();
                    }
                    if (i4 >= i3) {
                        break;
                    }
                    if (f2.get(i4).p()) {
                        for (Room next : HomeManager.a().a(f2.get(i4).j())) {
                            if (!this.u.containsKey(next.e())) {
                                this.u.put(next.e(), new ArrayList());
                                if (list == null) {
                                    list = new ArrayList<>();
                                }
                                list.add(next);
                            }
                            this.u.get(next.e()).add(next);
                        }
                    }
                    i4++;
                }
            } else {
                list = HomeManager.a().a(this.r.j());
            }
            if (list != null) {
                arrayList.addAll(list);
                arrayList.add(0, this.f21719a);
                arrayList.add(1, this.b);
                List<ViewDataModel> b2 = b(this.r, (List<Room>) arrayList);
                if (this.s == null && this.t == null) {
                    this.s = this.f21719a;
                }
                List arrayList2 = new ArrayList();
                if (TextUtils.equals(this.r.j(), this.c.j())) {
                    List<Home> f3 = HomeManager.a().f();
                    map = null;
                    int i5 = 0;
                    while (true) {
                        if (f3 == null) {
                            i2 = 0;
                        } else {
                            i2 = f3.size();
                        }
                        if (i5 >= i2) {
                            break;
                        }
                        if (f3.get(i5).p() && (j2 = SmartHomeDeviceHelper.a().b().j(f3.get(i5).j())) != null && !j2.isEmpty()) {
                            if (map == null) {
                                map = new HashMap<>();
                            }
                            map.putAll(j2);
                        }
                        i5++;
                    }
                } else {
                    map = SmartHomeDeviceHelper.a().b().j(this.r.j());
                }
                if (map != null && !map.isEmpty()) {
                    Set<Map.Entry<String, List<String>>> entrySet = map.entrySet();
                    ArrayList arrayList3 = new ArrayList();
                    for (Map.Entry next2 : entrySet) {
                        DeviceTagInterface.Category d2 = SmartHomeDeviceHelper.a().b().d((String) next2.getKey());
                        if (!(d2 == null || next2.getValue() == null || ((List) next2.getValue()).size() <= 0)) {
                            arrayList3.add(d2);
                        }
                    }
                    if (arrayList3.size() > 0) {
                        arrayList2 = a(this.r, (ArrayList<DeviceTagInterface.Category>) arrayList3);
                    }
                }
                if (b2.size() > 0) {
                    this.n.a((Integer) 0, b2);
                    if (arrayList2.size() > 0) {
                        this.n.b((Integer) 1, this.f);
                        this.n.b((Integer) 2, (List<ViewDataModel>) arrayList2);
                    }
                } else if (arrayList2.size() > 0) {
                    this.n.a((Integer) 0, (List<ViewDataModel>) arrayList2);
                } else {
                    ViewDataModel viewDataModel = new ViewDataModel();
                    viewDataModel.e = 0;
                    viewDataModel.g = this.f21719a.d();
                    viewDataModel.f = this.f21719a.e();
                    this.n.a((Integer) 0, viewDataModel);
                }
                this.n.notifyDataSetChanged();
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        this.q = -1;
        if (this.g != null && this.g.indexOfChild(this.h) >= 0) {
            this.g.removeView(this.h);
        }
        if (z && this.l != null) {
            ViewPropertyAnimator startDelay = this.l.findViewById(R.id.arrow_down_img).animate().rotation(0.0f).setDuration(125).setStartDelay(125);
            startDelay.setInterpolator(new AccelerateDecelerateInterpolator());
            startDelay.start();
        }
    }

    private void a(View view, boolean z) {
        if (view != null) {
            view.animate().rotation(z ? -180.0f : 0.0f).setDuration(800).setInterpolator(new AccelerateDecelerateInterpolator());
        }
    }

    public void d() {
        if (this.r == null) {
            a();
        }
        if (this.r == null) {
            c((Home) null);
        } else if (this.t != null) {
            a(this.r, this.t);
        } else if (this.s == null) {
            a(this.r, this.f21719a);
        } else if (!TextUtils.equals(this.r.j(), this.c.j())) {
            a(this.r, this.s);
        } else if (this.s == null || TextUtils.equals(this.s.d(), this.f21719a.d())) {
            a(this.r, this.f21719a);
        } else {
            a(this.r, this.u.get(this.s.e()));
        }
    }

    private List<ViewDataModel> g() {
        ArrayList arrayList = new ArrayList();
        for (Home next : HomeManager.a().f()) {
            if (next.p()) {
                ViewDataModel viewDataModel = new ViewDataModel();
                viewDataModel.g = next.j();
                viewDataModel.f = next.k();
                viewDataModel.e = 3;
                arrayList.add(viewDataModel);
            }
        }
        ViewDataModel viewDataModel2 = new ViewDataModel();
        viewDataModel2.g = this.c.j();
        viewDataModel2.f = this.c.k();
        viewDataModel2.e = 3;
        if (arrayList.size() > 1) {
            arrayList.add(0, viewDataModel2);
        } else if (arrayList.size() == 1) {
            arrayList.set(0, viewDataModel2);
        }
        return arrayList;
    }

    private List<ViewDataModel> b(Home home, List<Room> list) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            for (Room next : list) {
                if (next != null && !TextUtils.isEmpty(next.d()) && !TextUtils.isEmpty(next.e())) {
                    ViewDataModel viewDataModel = new ViewDataModel();
                    viewDataModel.f = next.e();
                    viewDataModel.g = next.d();
                    viewDataModel.e = 0;
                    arrayList.add(viewDataModel);
                }
            }
        }
        return arrayList;
    }

    private List<ViewDataModel> a(Home home, ArrayList<DeviceTagInterface.Category> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (!arrayList.isEmpty()) {
            Iterator<DeviceTagInterface.Category> it = arrayList.iterator();
            while (it.hasNext()) {
                DeviceTagInterface.Category next = it.next();
                if (next != null && !TextUtils.isEmpty(next.f15435a) && !TextUtils.isEmpty(next.d)) {
                    ViewDataModel viewDataModel = new ViewDataModel();
                    viewDataModel.f = next.d;
                    viewDataModel.g = next.f15435a;
                    viewDataModel.e = 2;
                    arrayList2.add(viewDataModel);
                }
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003a, code lost:
        if (android.text.TextUtils.equals(r5.g, r3.r.j()) != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (android.text.TextUtils.equals(r5.g, r3.t.f15435a) != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
        if (android.text.TextUtils.equals(r5.g, r3.s.d()) != false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.TextView a(android.content.Context r4, com.xiaomi.smarthome.scene.widget.ViewDataModel r5, android.view.ViewGroup r6) {
        /*
            r3 = this;
            android.view.LayoutInflater r4 = android.view.LayoutInflater.from(r4)
            r0 = 0
            r1 = 2130904891(0x7f03073b, float:1.7416641E38)
            android.view.View r4 = r4.inflate(r1, r6, r0)
            android.widget.TextView r4 = (android.widget.TextView) r4
            java.lang.String r6 = r5.f
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 != 0) goto L_0x001c
            java.lang.String r6 = r5.f
            r4.setText(r6)
            goto L_0x0021
        L_0x001c:
            java.lang.String r6 = ""
            r4.setText(r6)
        L_0x0021:
            int r6 = r5.e
            r1 = 1
            if (r6 == 0) goto L_0x004e
            switch(r6) {
                case 2: goto L_0x003d;
                case 3: goto L_0x002a;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x0061
        L_0x002a:
            com.xiaomi.smarthome.homeroom.model.Home r6 = r3.r
            if (r6 == 0) goto L_0x0061
            java.lang.String r6 = r5.g
            com.xiaomi.smarthome.homeroom.model.Home r2 = r3.r
            java.lang.String r2 = r2.j()
            boolean r6 = android.text.TextUtils.equals(r6, r2)
            if (r6 == 0) goto L_0x0061
            goto L_0x0062
        L_0x003d:
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r6 = r3.t
            if (r6 == 0) goto L_0x0061
            java.lang.String r6 = r5.g
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r2 = r3.t
            java.lang.String r2 = r2.f15435a
            boolean r6 = android.text.TextUtils.equals(r6, r2)
            if (r6 == 0) goto L_0x0061
            goto L_0x0062
        L_0x004e:
            com.xiaomi.smarthome.homeroom.model.Room r6 = r3.s
            if (r6 == 0) goto L_0x0061
            java.lang.String r6 = r5.g
            com.xiaomi.smarthome.homeroom.model.Room r2 = r3.s
            java.lang.String r2 = r2.d()
            boolean r6 = android.text.TextUtils.equals(r6, r2)
            if (r6 == 0) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r1 = 0
        L_0x0062:
            r4.setSelected(r1)
            com.xiaomi.smarthome.scene.widget.-$$Lambda$SceneFilter$4RahMLZNXJRe_N1zJbeoVC8yogw r6 = new com.xiaomi.smarthome.scene.widget.-$$Lambda$SceneFilter$4RahMLZNXJRe_N1zJbeoVC8yogw
            r6.<init>(r5)
            r4.setOnClickListener(r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.widget.SceneFilter.a(android.content.Context, com.xiaomi.smarthome.scene.widget.ViewDataModel, android.view.ViewGroup):android.widget.TextView");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ViewDataModel viewDataModel, View view) {
        int i2 = viewDataModel.e;
        if (i2 != 0) {
            switch (i2) {
                case 2:
                    if (!TextUtils.isEmpty(viewDataModel.g)) {
                        this.s = null;
                        this.t = SmartHomeDeviceHelper.a().b().h(viewDataModel.g);
                        a(this.r, this.t);
                        break;
                    }
                    break;
                case 3:
                    if (!TextUtils.isEmpty(viewDataModel.g)) {
                        if (TextUtils.equals(this.c.j(), viewDataModel.g)) {
                            this.r = this.c;
                        } else {
                            this.r = HomeManager.a().j(viewDataModel.g);
                        }
                        e();
                        break;
                    }
                    break;
            }
        } else if (!TextUtils.isEmpty(viewDataModel.g)) {
            if (TextUtils.equals(this.f21719a.d(), viewDataModel.g)) {
                this.s = this.f21719a;
            } else if (TextUtils.equals(this.b.d(), viewDataModel.g)) {
                this.s = this.b;
            } else {
                this.s = HomeManager.a().i(viewDataModel.g);
            }
            this.t = null;
            a(this.r, this.s);
        }
        a(viewDataModel.e == 3);
    }

    public void e() {
        if (this.r != null) {
            this.s = this.f21719a;
            this.t = null;
        }
        c(this.r);
        if (this.k != null) {
            this.k.setText(this.r.k());
        }
        c(this.r);
        b(this.r);
    }

    public boolean f() {
        boolean z = false;
        if (this.q < 0) {
            return false;
        }
        if (this.q == 0) {
            z = true;
        }
        a(z);
        return true;
    }

    public class FilterAdapter extends RecyclerView.Adapter<DataHolder> {
        private Map<Integer, List<ViewDataModel>> b = new HashMap();

        public long getItemId(int i) {
            return (long) i;
        }

        public FilterAdapter() {
        }

        public void a(Integer num, List<ViewDataModel> list) {
            this.b.clear();
            if (list != null && !list.isEmpty()) {
                if (this.b.containsKey(num)) {
                    this.b.get(num).clear();
                } else {
                    this.b.put(num, new ArrayList());
                }
                this.b.get(num).addAll(list);
            }
        }

        public void a(Integer num, ViewDataModel viewDataModel) {
            this.b.clear();
            if (viewDataModel != null) {
                if (this.b.containsKey(num)) {
                    this.b.get(num).clear();
                } else {
                    this.b.put(num, new ArrayList());
                }
                this.b.get(num).add(viewDataModel);
            }
        }

        public void b(Integer num, List<ViewDataModel> list) {
            if (list != null && !list.isEmpty()) {
                if (this.b.containsKey(num)) {
                    this.b.get(num).clear();
                } else {
                    this.b.put(num, new ArrayList());
                }
                this.b.get(num).addAll(list);
            }
        }

        public void b(Integer num, ViewDataModel viewDataModel) {
            if (viewDataModel != null) {
                if (this.b.containsKey(num)) {
                    this.b.get(num).clear();
                } else {
                    this.b.put(num, new ArrayList());
                }
                this.b.get(num).add(viewDataModel);
            }
        }

        @NonNull
        /* renamed from: a */
        public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DataHolder(new FlowLayoutCopy(SceneFilter.this.d));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull DataHolder dataHolder, int i) {
            dataHolder.f21721a.removeAllViews();
            if (this.b.get(Integer.valueOf(i)) != null && !this.b.get(Integer.valueOf(i)).isEmpty()) {
                if (((ViewDataModel) this.b.get(Integer.valueOf(i)).get(0)).e == 1) {
                    View view = new View(dataHolder.f21721a.getContext());
                    view.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, DisplayUtils.a(0.5f)));
                    view.setBackgroundResource(R.color.black_15_transparent);
                    dataHolder.f21721a.addView(view);
                    ViewGroup.LayoutParams layoutParams = dataHolder.f21721a.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
                        dataHolder.f21721a.setLayoutParams(layoutParams);
                    }
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        marginLayoutParams.topMargin = DisplayUtils.a(20.0f);
                        if (getItemCount() > 2) {
                            marginLayoutParams.bottomMargin = DisplayUtils.a(20.0f);
                        } else {
                            marginLayoutParams.bottomMargin = 0;
                        }
                        dataHolder.f21721a.setLayoutParams(marginLayoutParams);
                        return;
                    }
                    return;
                }
                if (i > 0 && ((ViewDataModel) this.b.get(Integer.valueOf(i - 1)).get(0)).e == 1) {
                    ViewGroup.LayoutParams layoutParams2 = dataHolder.f21721a.getLayoutParams();
                    if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                        marginLayoutParams2.topMargin = 0;
                        dataHolder.f21721a.setLayoutParams(marginLayoutParams2);
                    }
                }
                for (int i2 = 0; i2 < this.b.get(Integer.valueOf(i)).size(); i2++) {
                    dataHolder.f21721a.addView(SceneFilter.this.a(SceneFilter.this.d, (ViewDataModel) this.b.get(Integer.valueOf(i)).get(i2), dataHolder.f21721a));
                }
            }
        }

        public int getItemCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }
    }

    static class DataHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public FlowLayoutCopy f21721a;

        public DataHolder(@NonNull View view) {
            super(view);
            this.f21721a = (FlowLayoutCopy) view;
            this.f21721a.setSingleLine(false);
            this.f21721a.setItemSpacing(DisplayUtils.a(10.0f));
            this.f21721a.setLineSpacing(DisplayUtils.a(14.0f));
        }
    }
}
