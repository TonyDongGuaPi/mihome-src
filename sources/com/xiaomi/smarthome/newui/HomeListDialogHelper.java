package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.newui.HomeListDialogHelper;
import com.xiaomi.smarthome.newui.widget.IsCheckedableView;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeListDialogHelper {

    /* renamed from: a  reason: collision with root package name */
    public static AtomicBoolean f20298a = new AtomicBoolean(false);

    public interface HomeListDialogV2Listener {
        void a();

        void a(Home home);
    }

    public interface ItemClickListener {
        void a(Object obj);
    }

    public static void a(Context context, View view, boolean z, final ItemClickListener itemClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.popupwindow, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(R.id.lv_home);
        if (z) {
            View inflate2 = LayoutInflater.from(context).inflate(R.layout.item_homelist_footview, (ViewGroup) null);
            inflate2.findViewById(R.id.footer_manager).setVisibility(8);
            inflate2.findViewById(R.id.footer_change_home).setVisibility(0);
            listView.addFooterView(inflate2);
        }
        InternalHomeListAdapter internalHomeListAdapter = new InternalHomeListAdapter(context);
        internalHomeListAdapter.a(HomeManager.a().f());
        listView.setAdapter(internalHomeListAdapter);
        final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
        popupWindow.setClippingEnabled(false);
        popupWindow.setAnimationStyle(R.style.anim_popupwindow);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setFocusable(true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.update();
        popupWindow.showAsDropDown(view, view.getPaddingLeft(), -DisplayUtils.a(5.0f));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
            /* JADX WARNING: Unknown variable types count: 1 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                /*
                    r0 = this;
                    com.xiaomi.smarthome.newui.HomeListDialogHelper$ItemClickListener r2 = r8
                    if (r2 == 0) goto L_0x0013
                    com.xiaomi.smarthome.newui.HomeListDialogHelper$ItemClickListener r2 = r8
                    android.widget.Adapter r1 = r1.getAdapter()
                    java.lang.Object r1 = r1.getItem(r3)
                    com.xiaomi.smarthome.homeroom.model.Home r1 = (com.xiaomi.smarthome.homeroom.model.Home) r1
                    r2.a(r1)
                L_0x0013:
                    android.widget.PopupWindow r1 = r5
                    r1.dismiss()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.HomeListDialogHelper.AnonymousClass1.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
            }
        });
    }

    public static void a(final Context context, View view) {
        if (!f20298a.getAndSet(true)) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.popupwindow, (ViewGroup) null);
            ListView listView = (ListView) inflate.findViewById(R.id.lv_home);
            View inflate2 = LayoutInflater.from(context).inflate(R.layout.item_homelist_footview, (ViewGroup) null);
            inflate2.findViewById(R.id.footer_manager).setVisibility(8);
            inflate2.findViewById(R.id.footer_change_home).setVisibility(0);
            InternalHomeListAdapter internalHomeListAdapter = new InternalHomeListAdapter(context);
            internalHomeListAdapter.a(HomeManager.a().f());
            listView.addFooterView(inflate2);
            listView.setAdapter(internalHomeListAdapter);
            final PopupWindow popupWindow = new PopupWindow(inflate, -2, -2);
            popupWindow.setClippingEnabled(false);
            popupWindow.setAnimationStyle(R.style.anim_popupwindow);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener($$Lambda$HomeListDialogHelper$KMie_W7tni7e0VTVBeSl_kRnrM.INSTANCE);
            popupWindow.update();
            popupWindow.showAsDropDown(view, DisplayUtils.a(14.0f), -DisplayUtils.a(10.0f));
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
                /* JADX WARNING: Unknown variable types count: 1 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
                    /*
                        r0 = this;
                        android.widget.Adapter r2 = r1.getAdapter()
                        int r2 = r2.getCount()
                        int r2 = r2 + -1
                        if (r3 < r2) goto L_0x0020
                        android.content.Intent r1 = new android.content.Intent
                        android.content.Context r2 = r7
                        java.lang.Class<com.xiaomi.smarthome.newui.MultiHomeManagerActivity> r3 = com.xiaomi.smarthome.newui.MultiHomeManagerActivity.class
                        r1.<init>(r2, r3)
                        android.content.Context r2 = r7
                        r2.startActivity(r1)
                        com.xiaomi.smarthome.stat.StatClick r1 = com.xiaomi.smarthome.stat.STAT.d
                        r1.Z()
                        goto L_0x0038
                    L_0x0020:
                        android.widget.Adapter r1 = r1.getAdapter()
                        java.lang.Object r1 = r1.getItem(r3)
                        com.xiaomi.smarthome.homeroom.model.Home r1 = (com.xiaomi.smarthome.homeroom.model.Home) r1
                        if (r1 == 0) goto L_0x0038
                        com.xiaomi.smarthome.homeroom.HomeManager r2 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                        java.lang.String r1 = r1.j()
                        r3 = 0
                        r2.a((java.lang.String) r1, (com.xiaomi.smarthome.frame.AsyncCallback) r3)
                    L_0x0038:
                        android.widget.PopupWindow r1 = r3
                        r1.dismiss()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.HomeListDialogHelper.AnonymousClass2.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
                }
            });
        }
    }

    public static void a(Context context, View view, boolean z, String str, boolean z2, HomeListDialogV2Listener homeListDialogV2Listener) {
        if (!f20298a.getAndSet(true) && view != null && !TextUtils.isEmpty(str)) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.popupwindowv2, (ViewGroup) null);
            ListView listView = (ListView) inflate.findViewById(R.id.lv_home);
            View findViewById = inflate.findViewById(R.id.footer_change_home);
            findViewById.setVisibility(z ? 0 : 8);
            InternalHomeListAdapterV2 internalHomeListAdapterV2 = new InternalHomeListAdapterV2(context);
            List<Home> f = HomeManager.a().f();
            ArrayList arrayList = new ArrayList();
            Observable<T> filter = Observable.fromIterable(f).filter(new Predicate(z2) {
                private final /* synthetic */ boolean f$0;

                {
                    this.f$0 = r1;
                }

                public final boolean test(Object obj) {
                    return HomeListDialogHelper.a(this.f$0, (Home) obj);
                }
            });
            arrayList.getClass();
            filter.subscribe((Consumer<? super T>) new Consumer(arrayList) {
                private final /* synthetic */ ArrayList f$0;

                {
                    this.f$0 = r1;
                }

                public final void accept(Object obj) {
                    this.f$0.add((Home) obj);
                }
            });
            internalHomeListAdapterV2.a((List<Home>) arrayList);
            listView.setAdapter(internalHomeListAdapterV2);
            if (internalHomeListAdapterV2.a(str) != -1) {
                listView.setItemChecked(internalHomeListAdapterV2.a(str), true);
            }
            PopupWindow popupWindow = new PopupWindow(inflate, -1, -2);
            popupWindow.setClippingEnabled(false);
            popupWindow.setAnimationStyle(R.style.anim_popupwindow_v2);
            popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            popupWindow.setFocusable(true);
            popupWindow.setTouchable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                public final void onDismiss() {
                    HomeListDialogHelper.a(HomeListDialogHelper.HomeListDialogV2Listener.this);
                }
            });
            popupWindow.update();
            popupWindow.showAsDropDown(view, 0, 0);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(popupWindow, homeListDialogV2Listener) {
                private final /* synthetic */ PopupWindow f$0;
                private final /* synthetic */ HomeListDialogHelper.HomeListDialogV2Listener f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                    HomeListDialogHelper.a(this.f$0, this.f$1, adapterView, view, i, j);
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener(popupWindow, context) {
                private final /* synthetic */ PopupWindow f$0;
                private final /* synthetic */ Context f$1;

                {
                    this.f$0 = r1;
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    HomeListDialogHelper.a(this.f$0, this.f$1, view);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(boolean z, Home home) throws Exception {
        return !z || home.p();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(HomeListDialogV2Listener homeListDialogV2Listener) {
        f20298a.set(false);
        if (homeListDialogV2Listener != null) {
            homeListDialogV2Listener.a();
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(PopupWindow popupWindow, HomeListDialogV2Listener homeListDialogV2Listener, AdapterView adapterView, View view, int i, long j) {
        popupWindow.dismiss();
        Home home = (Home) adapterView.getAdapter().getItem(i);
        if (homeListDialogV2Listener != null) {
            homeListDialogV2Listener.a(home);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(PopupWindow popupWindow, Context context, View view) {
        popupWindow.dismiss();
        Intent intent = new Intent(context, MultiHomeManagerActivity.class);
        intent.putExtra("from", 7);
        context.startActivity(intent);
        STAT.d.Z();
    }

    public static void a(Context context, String str, String str2, ItemClickListener itemClickListener) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.move_room_dialog_layout, (ViewGroup) null);
        MLAlertDialog b = new MLAlertDialog.Builder(context).a((int) R.string.voice_control_device_auth_choose_home).b(inflate).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(false);
        ArrayList arrayList = new ArrayList();
        for (Home next : HomeManager.a().f()) {
            if (next.p()) {
                arrayList.add(next);
            }
        }
        ViewGroup.LayoutParams layoutParams = inflate.findViewById(R.id.recycler_view_container).getLayoutParams();
        if (arrayList.size() > 3) {
            layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.std_list_item_height_single_line) * 4;
        } else {
            layoutParams.height = context.getResources().getDimensionPixelSize(R.dimen.std_list_item_height_single_line) * arrayList.size();
        }
        inflate.findViewById(R.id.recycler_view_container).setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        InternalHomeAlertListAdapter internalHomeAlertListAdapter = new InternalHomeAlertListAdapter(context, b, str, str2, itemClickListener);
        recyclerView.setAdapter(internalHomeAlertListAdapter);
        internalHomeAlertListAdapter.a((List<Home>) arrayList);
        b.show();
        LinearLayout linearLayout = (LinearLayout) b.getButton(-2).getParent();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        layoutParams2.topMargin = 0;
        linearLayout.setLayoutParams(layoutParams2);
    }

    private static class InternalHomeListAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        protected List<Home> f20304a;
        protected Context b;

        public long getItemId(int i) {
            return (long) i;
        }

        public InternalHomeListAdapter(Context context) {
            this.b = context;
        }

        public void a(List<Home> list) {
            this.f20304a = list;
        }

        public int getCount() {
            if (this.f20304a != null) {
                return this.f20304a.size();
            }
            return 0;
        }

        /* renamed from: a */
        public Home getItem(int i) {
            if (this.f20304a != null) {
                return this.f20304a.get(i);
            }
            return null;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = LayoutInflater.from(this.b).inflate(R.layout.item_window_home_list, viewGroup, false);
                viewHolder.f20305a = (TextView) view2.findViewById(R.id.name);
                viewHolder.c = (RelativeLayout) view2.findViewById(R.id.root);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            Home a2 = getItem(i);
            if (a2 != null) {
                viewHolder.f20305a.setText(a2.k());
                viewHolder.c.setBackgroundResource(i == 0 ? R.drawable.selector_home_window_item_header : R.drawable.selector_home_window_item);
            }
            return view2;
        }

        private static class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f20305a;
            ImageView b;
            RelativeLayout c;

            private ViewHolder() {
            }
        }
    }

    private static class InternalHomeListAdapterV2 extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        protected List<Home> f20306a;
        protected Context b;

        public long getItemId(int i) {
            return (long) i;
        }

        public InternalHomeListAdapterV2(Context context) {
            this.b = context;
        }

        public void a(List<Home> list) {
            this.f20306a = list;
        }

        public int a(String str) {
            for (int i = 0; i < this.f20306a.size(); i++) {
                if (TextUtils.equals(str, this.f20306a.get(i).j())) {
                    return i;
                }
            }
            return -1;
        }

        public int getCount() {
            if (this.f20306a != null) {
                return this.f20306a.size();
            }
            return 0;
        }

        /* renamed from: a */
        public Home getItem(int i) {
            if (this.f20306a != null) {
                return this.f20306a.get(i);
            }
            return null;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            boolean z = false;
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = LayoutInflater.from(this.b).inflate(R.layout.item_single_choice, viewGroup, false);
                viewHolder.f20307a = (IsCheckedableView) view2.findViewById(R.id.root);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            Home a2 = getItem(i);
            if (a2 != null) {
                viewHolder.f20307a.setText(a2.k());
            }
            IsCheckedableView isCheckedableView = viewHolder.f20307a;
            if (i != this.f20306a.size() - 1) {
                z = true;
            }
            isCheckedableView.setDividerVisivle(z);
            return view2;
        }

        private static class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            IsCheckedableView f20307a;

            private ViewHolder() {
            }
        }
    }

    public static class InternalHomeAlertListAdapter extends RecyclerView.Adapter<HomeViewHolder> {

        /* renamed from: a  reason: collision with root package name */
        protected Context f20301a;
        protected List<Home> b = new ArrayList();
        /* access modifiers changed from: private */
        public MLAlertDialog c;
        private String d;
        private Home e;
        private String f;
        /* access modifiers changed from: private */
        public ItemClickListener g;

        public InternalHomeAlertListAdapter(Context context, MLAlertDialog mLAlertDialog, String str, String str2, ItemClickListener itemClickListener) {
            this.c = mLAlertDialog;
            this.f20301a = context;
            this.d = str;
            this.f = str2;
            this.g = itemClickListener;
        }

        /* access modifiers changed from: protected */
        public void a(List<Home> list) {
            if (list != null && list.size() > 0) {
                this.b.clear();
                this.b.addAll(list);
                this.e = HomeManager.a().q(this.d);
                if (this.e != null) {
                    this.b.remove(this.e);
                    this.b.add(0, this.e);
                }
            }
        }

        /* renamed from: a */
        public HomeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new HomeViewHolder(LayoutInflater.from(this.f20301a).inflate(R.layout.home_room_auth_select_home_list, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(HomeViewHolder homeViewHolder, int i) {
            String str;
            homeViewHolder.c.setVisibility(i == getItemCount() + -1 ? 8 : 0);
            final Home home = this.b.get(i);
            if (this.e == null || !TextUtils.equals(home.j(), this.e.j())) {
                str = home.k();
            } else {
                str = home.k() + " " + this.f20301a.getString(R.string.device_located_home);
            }
            homeViewHolder.b.setText(str);
            if (TextUtils.equals(home.j(), this.f)) {
                homeViewHolder.f20303a.setVisibility(0);
                homeViewHolder.b.setTextColor(Color.parseColor("#ff32BAC0"));
            } else {
                homeViewHolder.f20303a.setVisibility(8);
                homeViewHolder.b.setTextColor(this.f20301a.getResources().getColor(R.color.black));
            }
            homeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    InternalHomeAlertListAdapter.this.c.dismiss();
                    if (InternalHomeAlertListAdapter.this.g != null) {
                        InternalHomeAlertListAdapter.this.g.a(home.j());
                    }
                }
            });
        }

        public int getItemCount() {
            return this.b.size();
        }

        public static class HomeViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            public View f20303a;
            public TextView b;
            public View c;

            public HomeViewHolder(View view) {
                super(view);
                this.f20303a = view.findViewById(R.id.home_select_indicator);
                this.b = (TextView) view.findViewById(R.id.title);
                this.c = view.findViewById(R.id.divider_item);
            }
        }
    }
}
