package com.xiaomi.smarthome.homeroom;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.homeroom.model.DeviceTagRoom;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.shop.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AllHomeDeviceListFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    protected RecyclerView f17912a;
    protected View b;
    protected SectionedRecyclerViewAdapter c;
    private View d;
    private LinearLayoutManager e;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = LayoutInflater.from(getContext()).inflate(R.layout.nested_scroll_recyclerview, viewGroup, false);
            this.f17912a = (RecyclerView) this.d.findViewById(R.id.recyclerview);
            this.e = new LinearLayoutManager(getContext());
            this.f17912a.setLayoutManager(this.e);
            this.f17912a.setHasFixedSize(true);
            this.b = this.d.findViewById(R.id.common_white_empty_view);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.d.findViewById(R.id.empty_icon).getLayoutParams();
            layoutParams.topMargin = DisplayUtils.b(SHApplication.getAppContext(), 83.0f);
            this.d.findViewById(R.id.empty_icon).setLayoutParams(layoutParams);
            ((TextView) this.d.findViewById(R.id.common_white_empty_text)).setText(R.string.tag_no_device_to_add);
            this.c = new SectionedRecyclerViewAdapter();
            this.f17912a.setAdapter(this.c);
            this.d.findViewById(R.id.list_space).setVisibility(8);
        }
        c();
        return this.d;
    }

    /* access modifiers changed from: private */
    public HomeEditActivityBridge b() {
        FragmentActivity activity = getActivity();
        if (activity instanceof HomeEditActivityBridge) {
            return (HomeEditActivityBridge) activity;
        }
        return null;
    }

    private void c() {
        Room room;
        List<DeviceTagRoom> a2;
        HomeEditActivityBridge b2 = b();
        if (b2 == null) {
            room = null;
        } else {
            room = b2.getEditRoom();
        }
        List<Home> f = HomeManager.a().f();
        if (f != null) {
            ArrayList arrayList = new ArrayList();
            CustomSection customSection = null;
            for (int i = 0; i < f.size(); i++) {
                Home home = f.get(i);
                if (home != null && MultiHomeDeviceManager.a().d(home.j()).size() >= 1 && home.p() && (a2 = HomeManager.a().a(home.j(), room)) != null && !a2.isEmpty()) {
                    CustomSection customSection2 = new CustomSection(home, a2, new SectionParameters.Builder(R.layout.item_add_room_device).a((int) R.layout.item_add_room_device_section).a());
                    if (customSection2.a() <= 0 || !TextUtils.equals(room.f(), home.j())) {
                        arrayList.add(customSection2);
                    } else {
                        customSection = customSection2;
                    }
                }
            }
            Collections.sort(arrayList, new Comparator<CustomSection>() {
                /* renamed from: a */
                public int compare(CustomSection customSection, CustomSection customSection2) {
                    if (customSection.a() > customSection2.a()) {
                        return -1;
                    }
                    return customSection.a() < customSection2.a() ? 1 : 0;
                }
            });
            if (customSection != null) {
                arrayList.add(0, customSection);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.c.a((Section) it.next());
            }
            if (arrayList.size() < 1) {
                d();
                return;
            }
            return;
        }
        d();
    }

    private void d() {
        this.b.setVisibility(0);
    }

    public void a() {
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    protected class CustomSection extends Section {
        private Home b;
        private List<DeviceTagRoom> i = new ArrayList();

        public CustomSection(Home home, List<DeviceTagRoom> list, SectionParameters sectionParameters) {
            super(sectionParameters);
            this.b = home;
            this.i = list == null ? new ArrayList<>() : list;
        }

        public int a() {
            return this.i.size();
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i2) {
            ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
            childViewHolder.a(this.i.get(i2));
            if (HomeManager.a().f() != null && HomeManager.a().f().size() <= 1 && i2 == 0) {
                childViewHolder.f.setVisibility(8);
            }
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            headerViewHolder.b.setText(this.b.k());
            if (HomeManager.a().f() != null && HomeManager.a().f().size() <= 1) {
                headerViewHolder.c.setVisibility(8);
                headerViewHolder.f.setVisibility(8);
            }
        }

        public RecyclerView.ViewHolder a(View view) {
            return new HeaderViewHolder(view);
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ChildViewHolder(view);
        }

        private class ChildViewHolder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public CheckBox b;
            private TextView c;
            private TextView d;
            private SimpleDraweeView e;
            /* access modifiers changed from: private */
            public View f;
            private Drawable g = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_normal_checkbox_layout);
            private Drawable h = SHApplication.getAppContext().getResources().getDrawable(R.drawable.tag_other_checkbox_layout);

            public ChildViewHolder(View view) {
                super(view);
                view.findViewById(R.id.root_item).setOnClickListener(new View.OnClickListener(CustomSection.this) {
                    public void onClick(View view) {
                        ChildViewHolder.this.b.setChecked(!ChildViewHolder.this.b.isChecked());
                    }
                });
                this.e = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.b = (CheckBox) view.findViewById(R.id.select_check);
                this.c = (TextView) view.findViewById(R.id.title);
                this.d = (TextView) view.findViewById(R.id.room);
                this.f = view.findViewById(R.id.divider_item);
            }

            /* JADX WARNING: Code restructure failed: missing block: B:3:0x000b, code lost:
                r1 = r0.getDeviceSelectedStatus();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void a(final com.xiaomi.smarthome.homeroom.model.DeviceTagRoom r6) {
                /*
                    r5 = this;
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection r0 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment r0 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.this
                    com.xiaomi.smarthome.homeroom.HomeEditActivityBridge r0 = r0.b()
                    if (r0 != 0) goto L_0x000b
                    return
                L_0x000b:
                    java.util.Map r1 = r0.getDeviceSelectedStatus()
                    if (r1 != 0) goto L_0x0012
                    return
                L_0x0012:
                    android.widget.CheckBox r2 = r5.b
                    r3 = 0
                    r2.setOnCheckedChangeListener(r3)
                    android.widget.TextView r2 = r5.c
                    java.lang.String r3 = r6.d
                    r2.setText(r3)
                    android.widget.CheckBox r2 = r5.b
                    android.graphics.drawable.Drawable r3 = r5.g
                    r2.setButtonDrawable(r3)
                    int r2 = r6.k
                    if (r2 != 0) goto L_0x0059
                    android.widget.TextView r2 = r5.d
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.this
                    android.content.Context r3 = r3.getContext()
                    android.content.res.Resources r3 = r3.getResources()
                    r4 = 2131166535(0x7f070547, float:1.7947318E38)
                    int r3 = r3.getColor(r4)
                    r2.setTextColor(r3)
                    android.widget.TextView r2 = r5.d
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.this
                    android.content.Context r3 = r3.mContext
                    android.content.res.Resources r3 = r3.getResources()
                    r4 = 2131499554(0x7f0c1a22, float:1.862276E38)
                    java.lang.String r3 = r3.getString(r4)
                    r2.setText(r3)
                    goto L_0x00cf
                L_0x0059:
                    int r2 = r6.k
                    r3 = 1
                    r4 = 2131165427(0x7f0700f3, float:1.794507E38)
                    if (r2 != r3) goto L_0x008d
                    android.widget.TextView r2 = r5.d
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.this
                    android.content.Context r3 = r3.getContext()
                    android.content.res.Resources r3 = r3.getResources()
                    int r3 = r3.getColor(r4)
                    r2.setTextColor(r3)
                    android.widget.TextView r2 = r5.d
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.this
                    android.content.Context r3 = r3.mContext
                    android.content.res.Resources r3 = r3.getResources()
                    r4 = 2131499550(0x7f0c1a1e, float:1.8622753E38)
                    java.lang.String r3 = r3.getString(r4)
                    r2.setText(r3)
                    goto L_0x00cf
                L_0x008d:
                    int r2 = r6.k
                    r3 = 2
                    if (r2 != r3) goto L_0x00cf
                    android.widget.TextView r2 = r5.d
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment r3 = com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.this
                    android.content.Context r3 = r3.getContext()
                    android.content.res.Resources r3 = r3.getResources()
                    int r3 = r3.getColor(r4)
                    r2.setTextColor(r3)
                    android.widget.TextView r2 = r5.d
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r4 = "["
                    r3.append(r4)
                    com.xiaomi.smarthome.homeroom.model.Room r4 = r6.j
                    java.lang.String r4 = r4.e()
                    r3.append(r4)
                    java.lang.String r4 = "]"
                    r3.append(r4)
                    java.lang.String r3 = r3.toString()
                    r2.setText(r3)
                    android.widget.CheckBox r2 = r5.b
                    android.graphics.drawable.Drawable r3 = r5.h
                    r2.setButtonDrawable(r3)
                L_0x00cf:
                    android.widget.CheckBox r2 = r5.b
                    com.xiaomi.smarthome.device.Device r3 = r6.g
                    java.lang.String r3 = r3.did
                    boolean r3 = r1.containsKey(r3)
                    r2.setChecked(r3)
                    com.xiaomi.smarthome.device.Device r2 = r6.g
                    java.lang.String r2 = r2.did
                    boolean r2 = r1.containsKey(r2)
                    r6.h = r2
                    com.xiaomi.smarthome.device.Device r2 = r6.g
                    if (r2 == 0) goto L_0x00f3
                    com.xiaomi.smarthome.device.Device r2 = r6.g
                    java.lang.String r2 = r2.model
                    com.facebook.drawee.view.SimpleDraweeView r3 = r5.e
                    com.xiaomi.smarthome.device.DeviceFactory.b((java.lang.String) r2, (com.facebook.drawee.view.SimpleDraweeView) r3)
                L_0x00f3:
                    android.widget.CheckBox r2 = r5.b
                    com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection$ChildViewHolder$2 r3 = new com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment$CustomSection$ChildViewHolder$2
                    r3.<init>(r6, r0, r1)
                    r2.setOnCheckedChangeListener(r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.AllHomeDeviceListFragment.CustomSection.ChildViewHolder.a(com.xiaomi.smarthome.homeroom.model.DeviceTagRoom):void");
            }
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public final TextView b;
            /* access modifiers changed from: private */
            public final View c;
            private final ImageView d;
            private final View e;
            /* access modifiers changed from: private */
            public final View f;

            public HeaderViewHolder(View view) {
                super(view);
                this.f = view.findViewById(R.id.list_space);
                view.findViewById(R.id.divider_bottom).setVisibility(8);
                this.b = (TextView) view.findViewById(R.id.section_name);
                this.c = view.findViewById(R.id.ll_section_name);
                this.d = (ImageView) view.findViewById(R.id.check_image);
                this.d.setVisibility(8);
                this.e = view.findViewById(R.id.layout_divider);
            }
        }
    }
}
