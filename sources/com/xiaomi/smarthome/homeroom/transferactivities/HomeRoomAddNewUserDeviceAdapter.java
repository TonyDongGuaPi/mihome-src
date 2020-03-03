package com.xiaomi.smarthome.homeroom.transferactivities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.ExpandGridView;
import com.xiaomi.smarthome.miio.page.devicetag.DeviceTagEditorActivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

class HomeRoomAddNewUserDeviceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18333a = 65281;
    public static final int b = 65282;
    private static final String c = "HomeRoomAddNewUserDeviceAdapter";
    /* access modifiers changed from: private */
    public Context d;
    /* access modifiers changed from: private */
    public List<DeviceTagGrid> e = new ArrayList();
    private List<String> f = new ArrayList();

    private class DeviceTagGrid {

        /* renamed from: a  reason: collision with root package name */
        int f18335a;
        String b;
        List<String> c;

        DeviceTagGrid(int i, String str, List<String> list) {
            this.f18335a = i;
            this.b = str;
            this.c = list;
        }
    }

    public HomeRoomAddNewUserDeviceAdapter(Context context) {
        this.d = context;
    }

    public void a(List<String> list) {
        if (list != null && list.size() > 0) {
            this.f.clear();
            this.f.addAll(list);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(this.d);
        switch (i) {
            case f18333a /*65281*/:
            case b /*65282*/:
                return new GroupHolder(from.inflate(R.layout.expandgridview_transfer, viewGroup, false));
            default:
                Log.d("error", "viewholder is null");
                return null;
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof GroupHolder) {
            ((GroupHolder) viewHolder).a(i);
        }
    }

    public int getItemCount() {
        return this.e.size();
    }

    public int getItemViewType(int i) {
        return this.e.get(i).f18335a;
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    switch (HomeRoomAddNewUserDeviceAdapter.this.getItemViewType(i)) {
                        case HomeRoomAddNewUserDeviceAdapter.f18333a /*65281*/:
                        case HomeRoomAddNewUserDeviceAdapter.b /*65282*/:
                            return gridLayoutManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    public void a() {
        b(this.f);
        notifyDataSetChanged();
    }

    private void b(List<String> list) {
        this.e.clear();
        for (String next : list) {
            ArrayList arrayList = new ArrayList();
            Set<String> b2 = SmartHomeDeviceHelper.a().b().b(4, next);
            if (b2 != null) {
                for (String next2 : b2) {
                    Device b3 = SmartHomeDeviceManager.a().b(next2);
                    if (b3 != null && HomeManager.a(b3)) {
                        arrayList.add(next2);
                    }
                }
            }
            this.e.add(new DeviceTagGrid(f18333a, next, arrayList));
        }
        ArrayList arrayList2 = new ArrayList();
        List<String> o = SmartHomeDeviceHelper.a().b().o();
        if (o != null) {
            for (int i = 0; i < o.size(); i++) {
                Device b4 = SmartHomeDeviceManager.a().b(o.get(i));
                if (b4 != null && HomeManager.a(b4)) {
                    arrayList2.add(o.get(i));
                }
            }
        }
        if (arrayList2.size() > 0) {
            this.e.add(new DeviceTagGrid(b, SHApplication.getAppContext().getResources().getString(R.string.tag_recommend_defaultroom), arrayList2));
        }
    }

    private class GroupHolder extends RecyclerView.ViewHolder {
        private ExpandGridView b;
        /* access modifiers changed from: private */
        public TextView c;
        /* access modifiers changed from: private */
        public GroupAdapter d;

        GroupHolder(View view) {
            super(view);
            this.b = (ExpandGridView) view.findViewById(R.id.grid_view);
            this.c = (TextView) view.findViewById(R.id.title_left);
            this.d = new GroupAdapter();
        }

        public void a(int i) {
            DeviceTagGrid deviceTagGrid = (DeviceTagGrid) HomeRoomAddNewUserDeviceAdapter.this.e.get(i);
            this.c.setText(deviceTagGrid.b);
            this.b.setTag(deviceTagGrid);
            this.b.setAdapter(this.d);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            int a2 = DisplayUtils.a(30.0f);
            int a3 = DisplayUtils.a(15.0f);
            if (i == 0) {
                layoutParams.setMargins(a2, a3, 0, 0);
            } else {
                layoutParams.setMargins(a2, 0, 0, 0);
            }
            this.c.setLayoutParams(layoutParams);
            this.d.a(deviceTagGrid);
            if (deviceTagGrid.f18335a == 65281) {
                this.b.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        if (i >= GroupHolder.this.d.getCount() - 1 && (HomeRoomAddNewUserDeviceAdapter.this.d instanceof HomeRoomAddNewUserDeviceActivity)) {
                            HomeRoomAddNewUserDeviceActivity homeRoomAddNewUserDeviceActivity = (HomeRoomAddNewUserDeviceActivity) HomeRoomAddNewUserDeviceAdapter.this.d;
                            Intent intent = new Intent(homeRoomAddNewUserDeviceActivity, DeviceTagEditorActivity.class);
                            intent.putExtra(DeviceTagEditorActivity.TAG_PARAM, GroupHolder.this.c.getText());
                            intent.putExtra("result", true);
                            homeRoomAddNewUserDeviceActivity.startActivityForResult(intent, 1);
                        }
                    }
                });
            }
        }
    }

    class GroupAdapter extends BaseAdapter {
        private DeviceTagGrid b;

        public long getItemId(int i) {
            return 0;
        }

        GroupAdapter() {
        }

        public void a(DeviceTagGrid deviceTagGrid) {
            this.b = deviceTagGrid;
            notifyDataSetChanged();
        }

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            List<String> list = this.b.c;
            if (this.b.f18335a == 65281) {
                if (list == null) {
                    return 0;
                }
                return list.size() + 1;
            } else if (list == null) {
                return 0;
            } else {
                return list.size();
            }
        }

        public Object getItem(int i) {
            if (this.b == null) {
                return null;
            }
            List<String> list = this.b.c;
            int i2 = this.b.f18335a;
            if (i < 0 || list == null || i >= list.size()) {
                return null;
            }
            return list.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(HomeRoomAddNewUserDeviceAdapter.this.d).inflate(R.layout.tag_child_item_grid_common, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f18337a = (SimpleDraweeView) view.findViewById(R.id.icon);
                viewHolder.b = (ImageView) view.findViewById(R.id.icon_add);
                viewHolder.c = (TextView) view.findViewById(R.id.name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            this.b = (DeviceTagGrid) viewGroup.getTag();
            if (this.b == null) {
                return view;
            }
            List<String> list = this.b.c;
            int i2 = this.b.f18335a;
            if (i >= list.size()) {
                viewHolder.c.setText(R.string.tag_add);
                viewHolder.b.setVisibility(0);
                viewHolder.f18337a.setVisibility(8);
            } else {
                viewHolder.b.setVisibility(8);
                viewHolder.f18337a.setVisibility(0);
                String str = list.get(i);
                if (!TextUtils.isEmpty(str)) {
                    Device b2 = SmartHomeDeviceManager.a().b(str);
                    if (b2 == null) {
                        b2 = SmartHomeDeviceManager.a().l(str);
                    }
                    if (b2 != null) {
                        DeviceFactory.b(b2.model, viewHolder.f18337a);
                        viewHolder.c.setText(b2.getName());
                    }
                }
            }
            return view;
        }

        class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f18337a;
            ImageView b;
            TextView c;

            ViewHolder() {
            }
        }
    }
}
