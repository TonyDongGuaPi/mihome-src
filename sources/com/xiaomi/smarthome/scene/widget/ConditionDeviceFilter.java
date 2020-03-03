package com.xiaomi.smarthome.scene.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.newui.widget.FixHeightRecyclerView;
import com.xiaomi.smarthome.newui.widget.topnavi.widgets.FlowLayoutCopy;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class ConditionDeviceFilter {
    public static final String b = "scene_device_filter";
    public static final String c = "slected_home";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f21716a;
    private MLAlertDialog d;
    private View e;
    private View f;
    private Home g;
    private Room h;
    private DeviceTagInterface.Category i;
    private FixHeightRecyclerView j;
    private FixHeightRecyclerView k;
    private FilterAdapter l;
    private HomeAdapter m;
    private List<ViewDataModel> n = new ArrayList();
    private Room o;
    private Room p;

    public abstract int a(String str, DeviceTagInterface.Category category);

    public abstract int a(String str, Room room);

    public abstract void a(Home home);

    public abstract void a(Home home, DeviceTagInterface.Category category);

    public abstract void a(Home home, Room room);

    public abstract int b(Home home);

    public ConditionDeviceFilter(Context context) {
        this.f21716a = context;
        this.o = new Room();
        this.o.d(SelectRoomDialogView.ALL_ROOM_ID);
        this.o.e(context.getString(R.string.tag_all));
        this.p = new Room();
        this.p.d(SelectRoomDialogView.DEFAULT_ROOM_ID);
        this.p.e(context.getString(R.string.tag_recommend_defaultroom));
        this.e = LayoutInflater.from(context).inflate(R.layout.scene_filter_dialog, (ViewGroup) null);
        this.j = (FixHeightRecyclerView) this.e.findViewById(R.id.recycler_tag);
        this.j.setLayoutManager(new LinearLayoutManager(context));
    }

    public MLAlertDialog a() {
        if (this.d == null) {
            this.d = new MLAlertDialog.Builder(this.f21716a).b(this.e).b();
        }
        return this.d;
    }

    public boolean b() {
        Home j2;
        List<Home> f2 = f();
        boolean z = false;
        if (f2.isEmpty()) {
            if (this.f != null) {
                this.f.setVisibility(8);
            }
            this.g = null;
            this.h = null;
            this.i = null;
            return false;
        }
        if (this.f == null) {
            this.f = ((ViewStub) this.e.findViewById(R.id.vs_family)).inflate();
            this.k = (FixHeightRecyclerView) this.f.findViewById(R.id.sc_home_layout);
            this.k.setLayoutManager(new LinearLayoutManager(this.f21716a));
            if (this.m == null) {
                this.m = new HomeAdapter();
                this.k.setAdapter(this.m);
            }
        }
        this.m.a();
        this.m.notifyDataSetChanged();
        this.n = a(f2);
        if (this.n.isEmpty()) {
            if (this.f != null) {
                this.f.setVisibility(8);
            }
            this.g = null;
            this.h = null;
            this.i = null;
            return false;
        }
        this.f.setVisibility(0);
        if (this.g == null) {
            String c2 = SharePrefsManager.c(this.f21716a, b, c, "");
            if (!TextUtils.isEmpty(c2) && (j2 = HomeManager.a().j(c2)) != null && j2.p()) {
                this.g = j2;
            }
            if (this.g == null) {
                this.g = HomeManager.a().m();
            }
            if (!c(this.g)) {
                this.g = d();
            }
            this.h = null;
            this.i = null;
        }
        e();
        String j3 = this.g.j();
        if (!f2.isEmpty() && f2.size() != 0) {
            z = true;
        }
        a(j3, z);
        return true;
    }

    private Home d() {
        Home home;
        List<Home> f2 = HomeManager.a().f();
        int i2 = 0;
        boolean z = false;
        int i3 = -1;
        while (true) {
            if (i2 >= f2.size() || !f2.get(i2).p()) {
                home = null;
            } else {
                if (!z) {
                    z = true;
                    i3 = i2;
                }
                if (c(f2.get(i2))) {
                    home = f2.get(i2);
                    break;
                }
                i2++;
            }
        }
        return home == null ? f2.get(i3) : home;
    }

    private void e() {
        if (this.k != null) {
            if (this.m == null) {
                this.m = new HomeAdapter();
            }
            this.m.a();
            this.m.notifyDataSetChanged();
            if (this.n.isEmpty() || this.n.size() == 1) {
                this.f.setVisibility(8);
                return;
            }
            this.m.a(0, this.n);
            this.m.notifyDataSetChanged();
            this.f.setVisibility(0);
        }
    }

    private List<Home> f() {
        ArrayList arrayList = new ArrayList();
        List<Home> f2 = HomeManager.a().f();
        if (!f2.isEmpty()) {
            arrayList.addAll(f2);
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (!((Home) arrayList.get(size)).p()) {
                    arrayList.remove(size);
                }
            }
        }
        return arrayList;
    }

    public void c() {
        b();
        if (this.d != null) {
            this.d.show();
        }
    }

    public class HomeAdapter extends FilterAdapter {
        public HomeAdapter() {
            super();
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull DataHolder dataHolder, int i) {
            dataHolder.f21717a.removeAllViews();
            for (int i2 = 0; i2 < ((List) this.f21718a.get(i)).size(); i2++) {
                dataHolder.f21717a.addView(ConditionDeviceFilter.this.a(ConditionDeviceFilter.this.f21716a, (ViewDataModel) ((List) this.f21718a.get(i)).get(i2), (ViewGroup) dataHolder.f21717a));
            }
        }
    }

    public class FilterAdapter extends RecyclerView.Adapter<DataHolder> {

        /* renamed from: a  reason: collision with root package name */
        SparseArray<List<ViewDataModel>> f21718a = new SparseArray<>();

        public long getItemId(int i) {
            return (long) i;
        }

        public FilterAdapter() {
        }

        public boolean a(int i, int i2, ViewDataModel viewDataModel) {
            List list;
            if (this.f21718a.get(i) != null) {
                list = this.f21718a.get(i);
            } else {
                ArrayList arrayList = new ArrayList();
                this.f21718a.put(i, arrayList);
                list = arrayList;
            }
            if (i2 < 0 || i2 >= this.f21718a.size()) {
                return false;
            }
            list.add(i2, viewDataModel);
            return true;
        }

        public void a(int i, List<ViewDataModel> list) {
            List list2;
            if (this.f21718a.get(i) != null) {
                list2 = this.f21718a.get(i);
            } else {
                ArrayList arrayList = new ArrayList();
                this.f21718a.put(i, arrayList);
                list2 = arrayList;
            }
            list2.clear();
            if (list != null) {
                list2.addAll(list);
            }
        }

        @NonNull
        /* renamed from: a */
        public DataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DataHolder(new FlowLayoutCopy(ConditionDeviceFilter.this.f21716a));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull DataHolder dataHolder, int i) {
            dataHolder.f21717a.removeAllViews();
            if (this.f21718a.get(i) != null && this.f21718a.get(i).size() > 0) {
                if (((ViewDataModel) this.f21718a.get(i).get(0)).e == 1) {
                    View view = new View(dataHolder.f21717a.getContext());
                    view.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, DisplayUtils.a(0.5f)));
                    view.setBackgroundResource(R.color.black_15_transparent);
                    dataHolder.f21717a.addView(view);
                    ViewGroup.LayoutParams layoutParams = dataHolder.f21717a.getLayoutParams();
                    if (layoutParams == null) {
                        layoutParams = new ViewGroup.MarginLayoutParams(-1, -2);
                        dataHolder.f21717a.setLayoutParams(layoutParams);
                    }
                    if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                        marginLayoutParams.topMargin = DisplayUtils.a(20.0f);
                        if (getItemCount() > 2) {
                            marginLayoutParams.bottomMargin = DisplayUtils.a(20.0f);
                        } else {
                            marginLayoutParams.bottomMargin = 0;
                        }
                        dataHolder.f21717a.setLayoutParams(marginLayoutParams);
                        return;
                    }
                    return;
                }
                for (int i2 = 0; i2 < this.f21718a.get(i).size(); i2++) {
                    dataHolder.f21717a.addView(ConditionDeviceFilter.this.a(ConditionDeviceFilter.this.f21716a, (ViewDataModel) this.f21718a.get(i).get(i2), (ViewGroup) dataHolder.f21717a));
                }
            }
        }

        public int getItemCount() {
            if (this.f21718a == null) {
                return 0;
            }
            return this.f21718a.size();
        }

        public void a() {
            if (this.f21718a != null && this.f21718a.size() > 0) {
                this.f21718a.clear();
            }
        }
    }

    private boolean c(Home home) {
        if (this.n.isEmpty() || home == null) {
            return false;
        }
        for (ViewDataModel viewDataModel : this.n) {
            if (TextUtils.equals(viewDataModel.g, home.j())) {
                return true;
            }
        }
        return false;
    }

    private void a(String str, boolean z) {
        List<ViewDataModel> a2;
        if (!TextUtils.isEmpty(str)) {
            List<Room> a3 = HomeManager.a().a(str);
            ArrayList arrayList = new ArrayList();
            if (a3 != null) {
                arrayList.addAll(a3);
            }
            arrayList.add(0, this.p);
            if (this.l == null) {
                this.l = new FilterAdapter();
                this.j.setAdapter(this.l);
            }
            this.l.a();
            this.l.a(0, a(str, (List<Room>) arrayList));
            this.l.a(0, 0, new ViewDataModel(0, this.f21716a.getString(R.string.tag_all), SelectRoomDialogView.ALL_ROOM_ID));
            if (this.h == null && this.i == null) {
                this.h = this.o;
            }
            Map<String, List<String>> j2 = SmartHomeDeviceHelper.a().b().j(str);
            if (!j2.isEmpty()) {
                Set<Map.Entry<String, List<String>>> entrySet = j2.entrySet();
                ArrayList arrayList2 = new ArrayList();
                for (Map.Entry next : entrySet) {
                    DeviceTagInterface.Category d2 = SmartHomeDeviceHelper.a().b().d((String) next.getKey());
                    if (!(d2 == null || next.getValue() == null || ((List) next.getValue()).size() <= 0)) {
                        arrayList2.add(d2);
                    }
                }
                if (arrayList2.size() > 0 && (a2 = a(str, (ArrayList<DeviceTagInterface.Category>) arrayList2)) != null && a2.size() > 0) {
                    ViewDataModel viewDataModel = new ViewDataModel();
                    viewDataModel.e = 1;
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(viewDataModel);
                    this.l.a(1, (List<ViewDataModel>) arrayList3);
                    this.l.a(2, a2);
                }
            }
            this.l.notifyDataSetChanged();
        } else if (this.l != null) {
            this.l.a();
            this.l.notifyDataSetChanged();
        }
    }

    private List<ViewDataModel> a(List<Home> list) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            for (Home next : list) {
                if (next != null && !TextUtils.isEmpty(next.j()) && !TextUtils.isEmpty(next.k()) && b(next) > 0) {
                    ViewDataModel viewDataModel = new ViewDataModel();
                    viewDataModel.f = next.k();
                    viewDataModel.g = next.j();
                    viewDataModel.e = 3;
                    arrayList.add(viewDataModel);
                }
            }
        }
        return arrayList;
    }

    private List<ViewDataModel> a(String str, List<Room> list) {
        ArrayList arrayList = new ArrayList();
        if (!list.isEmpty()) {
            for (Room next : list) {
                if (next != null && !TextUtils.isEmpty(next.d()) && !TextUtils.isEmpty(next.e()) && a(str, next) > 0) {
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

    private List<ViewDataModel> a(String str, ArrayList<DeviceTagInterface.Category> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        if (!arrayList.isEmpty()) {
            Iterator<DeviceTagInterface.Category> it = arrayList.iterator();
            while (it.hasNext()) {
                DeviceTagInterface.Category next = it.next();
                if (next != null && !TextUtils.isEmpty(next.f15435a) && !TextUtils.isEmpty(next.d) && a(str, next) > 0) {
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
        if (android.text.TextUtils.equals(r6.g, r4.g.j()) != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004b, code lost:
        if (android.text.TextUtils.equals(r6.g, r4.i.f15435a) != false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005e, code lost:
        if (android.text.TextUtils.equals(r6.g, r4.h.d()) != false) goto L_0x0062;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.widget.TextView a(android.content.Context r5, com.xiaomi.smarthome.scene.widget.ViewDataModel r6, android.view.ViewGroup r7) {
        /*
            r4 = this;
            android.view.LayoutInflater r0 = android.view.LayoutInflater.from(r5)
            r1 = 0
            r2 = 2130904891(0x7f03073b, float:1.7416641E38)
            android.view.View r7 = r0.inflate(r2, r7, r1)
            android.widget.TextView r7 = (android.widget.TextView) r7
            java.lang.String r0 = r6.f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x001c
            java.lang.String r0 = r6.f
            r7.setText(r0)
            goto L_0x0021
        L_0x001c:
            java.lang.String r0 = ""
            r7.setText(r0)
        L_0x0021:
            int r0 = r6.e
            r2 = 1
            if (r0 == 0) goto L_0x004e
            switch(r0) {
                case 2: goto L_0x003d;
                case 3: goto L_0x002a;
                default: goto L_0x0029;
            }
        L_0x0029:
            goto L_0x0061
        L_0x002a:
            com.xiaomi.smarthome.homeroom.model.Home r0 = r4.g
            if (r0 == 0) goto L_0x0061
            java.lang.String r0 = r6.g
            com.xiaomi.smarthome.homeroom.model.Home r3 = r4.g
            java.lang.String r3 = r3.j()
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 == 0) goto L_0x0061
            goto L_0x0062
        L_0x003d:
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r0 = r4.i
            if (r0 == 0) goto L_0x0061
            java.lang.String r0 = r6.g
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r3 = r4.i
            java.lang.String r3 = r3.f15435a
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 == 0) goto L_0x0061
            goto L_0x0062
        L_0x004e:
            com.xiaomi.smarthome.homeroom.model.Room r0 = r4.h
            if (r0 == 0) goto L_0x0061
            java.lang.String r0 = r6.g
            com.xiaomi.smarthome.homeroom.model.Room r3 = r4.h
            java.lang.String r3 = r3.d()
            boolean r0 = android.text.TextUtils.equals(r0, r3)
            if (r0 == 0) goto L_0x0061
            goto L_0x0062
        L_0x0061:
            r2 = 0
        L_0x0062:
            r7.setSelected(r2)
            com.xiaomi.smarthome.scene.widget.-$$Lambda$ConditionDeviceFilter$I3ACxZXeR2SxvD3CrkK9TqaIEC0 r0 = new com.xiaomi.smarthome.scene.widget.-$$Lambda$ConditionDeviceFilter$I3ACxZXeR2SxvD3CrkK9TqaIEC0
            r0.<init>(r6, r5)
            r7.setOnClickListener(r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.widget.ConditionDeviceFilter.a(android.content.Context, com.xiaomi.smarthome.scene.widget.ViewDataModel, android.view.ViewGroup):android.widget.TextView");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(ViewDataModel viewDataModel, Context context, View view) {
        int i2 = viewDataModel.e;
        if (i2 != 0) {
            switch (i2) {
                case 2:
                    if (!TextUtils.isEmpty(viewDataModel.g)) {
                        this.h = null;
                        this.i = SmartHomeDeviceHelper.a().b().h(viewDataModel.g);
                        a(this.g, this.i);
                        SharePrefsManager.a(context, b, c, this.g.j());
                        return;
                    }
                    return;
                case 3:
                    if (!TextUtils.isEmpty(viewDataModel.g) && !TextUtils.equals(this.g.j(), viewDataModel.g)) {
                        this.g = HomeManager.a().j(viewDataModel.g);
                        if (this.g.p()) {
                            this.h = null;
                            this.i = null;
                            a(this.g);
                            e();
                            a(viewDataModel.g, true);
                            return;
                        }
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else if (!TextUtils.isEmpty(viewDataModel.g)) {
            if (TextUtils.equals(this.o.d(), viewDataModel.g)) {
                this.h = this.o;
            } else if (TextUtils.equals(this.p.d(), viewDataModel.g)) {
                this.h = this.p;
            } else {
                this.h = HomeManager.a().i(viewDataModel.g);
            }
            this.i = null;
            a(this.g, this.h);
            SharePrefsManager.a(context, b, c, this.g.j());
        }
    }

    static class DataHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public FlowLayoutCopy f21717a;

        public DataHolder(@NonNull View view) {
            super(view);
            this.f21717a = (FlowLayoutCopy) view;
            this.f21717a.setSingleLine(false);
            this.f21717a.setItemSpacing(DisplayUtils.a(10.0f));
            this.f21717a.setLineSpacing(DisplayUtils.a(14.0f));
        }
    }
}
