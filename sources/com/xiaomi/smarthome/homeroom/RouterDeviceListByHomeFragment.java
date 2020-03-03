package com.xiaomi.smarthome.homeroom;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
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
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RouterDeviceListByHomeFragment extends BaseFragment {

    /* renamed from: a  reason: collision with root package name */
    protected RecyclerView f18198a;
    protected View b;
    protected SectionedRecyclerViewAdapter c;
    private View d;
    private LinearLayoutManager e;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = LayoutInflater.from(getContext()).inflate(R.layout.nested_scroll_recyclerview, viewGroup, false);
            this.f18198a = (RecyclerView) this.d.findViewById(R.id.recyclerview);
            this.e = new LinearLayoutManager(getContext());
            this.f18198a.setLayoutManager(this.e);
            this.f18198a.setHasFixedSize(true);
            this.b = this.d.findViewById(R.id.common_white_empty_view);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.d.findViewById(R.id.empty_icon).getLayoutParams();
            layoutParams.topMargin = DisplayUtils.b(SHApplication.getAppContext(), 83.0f);
            this.d.findViewById(R.id.empty_icon).setLayoutParams(layoutParams);
            ((TextView) this.d.findViewById(R.id.common_white_empty_text)).setText(R.string.tag_no_device_to_add);
            this.c = new SectionedRecyclerViewAdapter();
            this.f18198a.setAdapter(this.c);
            this.d.findViewById(R.id.list_space).setVisibility(8);
        }
        d();
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
        this.b.setVisibility(0);
    }

    private void d() {
        Room room;
        HomeEditActivityBridge b2 = b();
        if (b2 == null) {
            room = null;
        } else {
            room = b2.getEditRoom();
        }
        ArrayList<DeviceTagRoom> arrayList = new ArrayList<>();
        List<Home> f = HomeManager.a().f();
        if (f != null) {
            for (int i = 0; i < f.size(); i++) {
                Home home = f.get(i);
                if (home != null && home.p() && ((home.m() != null && !home.m().isEmpty()) || (home.d() != null && !home.d().isEmpty()))) {
                    arrayList.addAll(HomeManager.a().a(home.j(), room));
                }
            }
        }
        if (arrayList.isEmpty()) {
            c();
            return;
        }
        DeviceTagInterface<Device> b3 = SmartHomeDeviceHelper.a().b();
        final HashMap hashMap = new HashMap();
        if (arrayList.size() > 1) {
            for (Map.Entry entry : new HashMap(b3.q() == null ? new HashMap<>() : b3.q()).entrySet()) {
                ArrayList<String> arrayList2 = new ArrayList<>((Set) entry.getValue());
                String e2 = b3.e((String) entry.getKey());
                for (String put : arrayList2) {
                    hashMap.put(put, e2);
                }
            }
            Collections.sort(arrayList, new Comparator<DeviceTagRoom>() {
                /* renamed from: a */
                public int compare(DeviceTagRoom deviceTagRoom, DeviceTagRoom deviceTagRoom2) {
                    String str = (String) hashMap.get(deviceTagRoom.g.did);
                    String str2 = (String) hashMap.get(deviceTagRoom2.g.did);
                    if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        return 1;
                    }
                    if (!TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                        return -1;
                    }
                    if (!TextUtils.isEmpty(str) || !TextUtils.isEmpty(str2)) {
                        return str.compareTo(str2);
                    }
                    return 0;
                }
            });
        }
        ArrayList<Pair> arrayList3 = new ArrayList<>();
        String str = null;
        ArrayList arrayList4 = null;
        for (DeviceTagRoom deviceTagRoom : arrayList) {
            String str2 = (String) hashMap.get(deviceTagRoom.g.did);
            if (TextUtils.isEmpty(str2)) {
                if (!TextUtils.isEmpty(str)) {
                    arrayList4 = new ArrayList();
                    arrayList4.add(deviceTagRoom);
                    arrayList3.add(new Pair((Object) null, arrayList4));
                } else if (arrayList4 == null) {
                    arrayList4 = new ArrayList();
                    arrayList4.add(deviceTagRoom);
                    arrayList3.add(new Pair((Object) null, arrayList4));
                } else {
                    arrayList4.add(deviceTagRoom);
                }
            } else if (!TextUtils.equals(str2, str)) {
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(deviceTagRoom);
                arrayList3.add(new Pair(str2, arrayList5));
                arrayList4 = arrayList5;
            } else if (arrayList4 == null) {
                arrayList4 = new ArrayList();
                arrayList4.add(deviceTagRoom);
                arrayList3.add(new Pair(str, arrayList4));
            } else {
                arrayList4.add(deviceTagRoom);
            }
            str = str2;
        }
        new HashMap(b3.q() == null ? new HashMap<>() : b3.q());
        for (Pair pair : arrayList3) {
            if (pair != null) {
                this.c.a((Section) new CustomSection((String) pair.first, (List) pair.second, new SectionParameters.Builder(R.layout.item_add_room_device).a((int) R.layout.item_add_room_device_section).a()));
            }
        }
    }

    public void a() {
        if (this.c != null) {
            this.c.notifyDataSetChanged();
        }
    }

    protected class CustomSection extends Section {
        private String b;
        private List<DeviceTagRoom> i = new ArrayList();

        public CustomSection(String str, List<DeviceTagRoom> list, SectionParameters sectionParameters) {
            super(sectionParameters);
            this.b = str;
            this.i.addAll(list);
        }

        public int a() {
            return this.i.size();
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i2) {
            ((ChildViewHolder) viewHolder).a(this.i.get(i2));
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            if (TextUtils.isEmpty(this.b)) {
                headerViewHolder.b.setText(R.string.other_device1);
            } else {
                headerViewHolder.b.setText(this.b);
            }
            headerViewHolder.e.setVisibility(0);
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
            private View f;
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
            public void a(final com.xiaomi.smarthome.homeroom.model.DeviceTagRoom r8) {
                /*
                    r7 = this;
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection r0 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment r0 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.this
                    com.xiaomi.smarthome.homeroom.HomeEditActivityBridge r0 = r0.b()
                    if (r0 != 0) goto L_0x000b
                    return
                L_0x000b:
                    java.util.Map r1 = r0.getDeviceSelectedStatus()
                    if (r1 != 0) goto L_0x0012
                    return
                L_0x0012:
                    android.widget.CheckBox r2 = r7.b
                    r3 = 0
                    r2.setOnCheckedChangeListener(r3)
                    android.widget.TextView r2 = r7.c
                    java.lang.String r3 = r8.d
                    r2.setText(r3)
                    com.xiaomi.smarthome.homeroom.HomeManager r2 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    com.xiaomi.smarthome.homeroom.model.Room r3 = r8.j
                    java.lang.String r3 = r3.f()
                    com.xiaomi.smarthome.homeroom.model.Home r2 = r2.j((java.lang.String) r3)
                    android.widget.CheckBox r3 = r7.b
                    android.graphics.drawable.Drawable r4 = r7.g
                    r3.setButtonDrawable(r4)
                    int r3 = r8.k
                    if (r3 != 0) goto L_0x0091
                    android.widget.TextView r3 = r7.d
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection r4 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment r4 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.this
                    android.content.Context r4 = r4.getContext()
                    android.content.res.Resources r4 = r4.getResources()
                    r5 = 2131166535(0x7f070547, float:1.7947318E38)
                    int r4 = r4.getColor(r5)
                    r3.setTextColor(r4)
                    android.widget.TextView r3 = r7.d
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection r5 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment r5 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.this
                    android.content.Context r5 = r5.mContext
                    android.content.res.Resources r5 = r5.getResources()
                    r6 = 2131499554(0x7f0c1a22, float:1.862276E38)
                    java.lang.String r5 = r5.getString(r6)
                    r4.append(r5)
                    if (r2 != 0) goto L_0x0070
                    java.lang.String r2 = ""
                    goto L_0x0085
                L_0x0070:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    java.lang.String r6 = " - "
                    r5.append(r6)
                    java.lang.String r2 = r2.k()
                    r5.append(r2)
                    java.lang.String r2 = r5.toString()
                L_0x0085:
                    r4.append(r2)
                    java.lang.String r2 = r4.toString()
                    r3.setText(r2)
                    goto L_0x014d
                L_0x0091:
                    int r3 = r8.k
                    r4 = 1
                    r5 = 2131165427(0x7f0700f3, float:1.794507E38)
                    if (r3 != r4) goto L_0x00ee
                    android.widget.TextView r3 = r7.d
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection r4 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment r4 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.this
                    android.content.Context r4 = r4.getContext()
                    android.content.res.Resources r4 = r4.getResources()
                    int r4 = r4.getColor(r5)
                    r3.setTextColor(r4)
                    android.widget.TextView r3 = r7.d
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection r5 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment r5 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.this
                    android.content.Context r5 = r5.mContext
                    android.content.res.Resources r5 = r5.getResources()
                    r6 = 2131499550(0x7f0c1a1e, float:1.8622753E38)
                    java.lang.String r5 = r5.getString(r6)
                    r4.append(r5)
                    if (r2 != 0) goto L_0x00ce
                    java.lang.String r2 = ""
                    goto L_0x00e3
                L_0x00ce:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    java.lang.String r6 = " - "
                    r5.append(r6)
                    java.lang.String r2 = r2.k()
                    r5.append(r2)
                    java.lang.String r2 = r5.toString()
                L_0x00e3:
                    r4.append(r2)
                    java.lang.String r2 = r4.toString()
                    r3.setText(r2)
                    goto L_0x014d
                L_0x00ee:
                    int r3 = r8.k
                    r4 = 2
                    if (r3 != r4) goto L_0x014d
                    android.widget.TextView r3 = r7.d
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection r4 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.this
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment r4 = com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.this
                    android.content.Context r4 = r4.getContext()
                    android.content.res.Resources r4 = r4.getResources()
                    int r4 = r4.getColor(r5)
                    r3.setTextColor(r4)
                    android.widget.TextView r3 = r7.d
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "["
                    r4.append(r5)
                    com.xiaomi.smarthome.homeroom.model.Room r5 = r8.j
                    java.lang.String r5 = r5.e()
                    r4.append(r5)
                    java.lang.String r5 = "]"
                    r4.append(r5)
                    if (r2 != 0) goto L_0x0127
                    java.lang.String r2 = ""
                    goto L_0x013c
                L_0x0127:
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r5.<init>()
                    java.lang.String r6 = " - "
                    r5.append(r6)
                    java.lang.String r2 = r2.k()
                    r5.append(r2)
                    java.lang.String r2 = r5.toString()
                L_0x013c:
                    r4.append(r2)
                    java.lang.String r2 = r4.toString()
                    r3.setText(r2)
                    android.widget.CheckBox r2 = r7.b
                    android.graphics.drawable.Drawable r3 = r7.h
                    r2.setButtonDrawable(r3)
                L_0x014d:
                    android.widget.CheckBox r2 = r7.b
                    com.xiaomi.smarthome.device.Device r3 = r8.g
                    java.lang.String r3 = r3.did
                    boolean r3 = r1.containsKey(r3)
                    r2.setChecked(r3)
                    com.xiaomi.smarthome.device.Device r2 = r8.g
                    java.lang.String r2 = r2.did
                    boolean r2 = r1.containsKey(r2)
                    r8.h = r2
                    com.xiaomi.smarthome.device.Device r2 = r8.g
                    if (r2 == 0) goto L_0x0171
                    com.xiaomi.smarthome.device.Device r2 = r8.g
                    java.lang.String r2 = r2.model
                    com.facebook.drawee.view.SimpleDraweeView r3 = r7.e
                    com.xiaomi.smarthome.device.DeviceFactory.b((java.lang.String) r2, (com.facebook.drawee.view.SimpleDraweeView) r3)
                L_0x0171:
                    android.widget.CheckBox r2 = r7.b
                    r3 = 0
                    r2.setVisibility(r3)
                    android.widget.TextView r2 = r7.d
                    r2.setVisibility(r3)
                    android.widget.CheckBox r2 = r7.b
                    com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection$ChildViewHolder$2 r3 = new com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment$CustomSection$ChildViewHolder$2
                    r3.<init>(r8, r0, r1)
                    r2.setOnCheckedChangeListener(r3)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.RouterDeviceListByHomeFragment.CustomSection.ChildViewHolder.a(com.xiaomi.smarthome.homeroom.model.DeviceTagRoom):void");
            }
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public final TextView b;
        private final ImageView c;
        private final View d;
        /* access modifiers changed from: private */
        public final View e;

        public HeaderViewHolder(View view) {
            super(view);
            this.e = view.findViewById(R.id.list_space);
            view.findViewById(R.id.divider_bottom).setVisibility(8);
            this.b = (TextView) view.findViewById(R.id.section_name);
            this.c = (ImageView) view.findViewById(R.id.check_image);
            this.c.setVisibility(8);
            this.d = view.findViewById(R.id.layout_divider);
        }
    }
}
