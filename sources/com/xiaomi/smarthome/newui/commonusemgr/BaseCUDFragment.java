package com.xiaomi.smarthome.newui.commonusemgr;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionParameters;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import com.xiaomi.smarthome.newui.commonusemgr.CUDAActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCUDFragment extends BaseFragment {
    protected static final String f = "title";

    /* renamed from: a  reason: collision with root package name */
    protected RecyclerView f20629a;
    protected SectionedRecyclerViewAdapter b;
    protected View c;
    protected View d;
    protected View e;
    protected String g;
    protected Map<String, Boolean> h = new HashMap();
    protected CUDAActivity.IDeviceSelectCallback i;

    public interface ItemData {
        List<String> a();

        String b();
    }

    /* access modifiers changed from: protected */
    public abstract void b();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = getArguments().getString("title");
    }

    public void a(Map<String, Boolean> map) {
        this.h = map;
        if (this.b != null) {
            this.b.notifyDataSetChanged();
        }
    }

    public void a(CUDAActivity.IDeviceSelectCallback iDeviceSelectCallback) {
        this.i = iDeviceSelectCallback;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        a(layoutInflater, viewGroup, bundle);
        b();
        return this.c;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.c.postDelayed(new Runnable() {
            public void run() {
                if (BaseCUDFragment.this.isValid()) {
                    BaseCUDFragment.this.b.notifyDataSetChanged();
                    BaseCUDFragment.this.f20629a.invalidate();
                }
            }
        }, 10);
    }

    private void a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.c == null) {
            this.c = LayoutInflater.from(getContext()).inflate(R.layout.nested_scroll_recyclerview, viewGroup, false);
            this.f20629a = (RecyclerView) this.c.findViewById(R.id.recyclerview);
            this.f20629a.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int i) {
                    return BaseCUDFragment.this.b.a(i) != 0 ? 1 : 4;
                }
            });
            this.d = this.c.findViewById(R.id.common_white_empty_view);
            this.e = this.f20629a;
            if (Build.VERSION.SDK_INT >= 21) {
                this.f20629a.setNestedScrollingEnabled(true);
            }
            this.f20629a.setLayoutManager(gridLayoutManager);
            this.b = new SectionedRecyclerViewAdapter();
            this.f20629a.setAdapter(this.b);
            this.b.notifyDataSetChanged();
            this.c.findViewById(R.id.list_space).setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public void a() {
        this.d.setVisibility(0);
        this.e.setVisibility(8);
        ((TextView) this.d.findViewById(R.id.common_white_empty_text)).setText(R.string.no_supported_device);
    }

    protected class BaseSection extends Section {
        private ItemData b;
        /* access modifiers changed from: private */
        public List<Device> i;
        private int j;

        public BaseSection(ItemData itemData, List<Device> list, int i2) {
            super(new SectionParameters.Builder(R.layout.item_add_common).a((int) R.layout.item_add_common_section).a());
            this.b = itemData;
            this.j = i2;
            if (list != null) {
                this.i = list;
            } else if (itemData == null) {
                this.i = list == null ? new ArrayList<>() : list;
            } else {
                this.i = new ArrayList();
                List<String> a2 = this.b.a();
                if (a2 != null && a2.size() > 0) {
                    for (int i3 = 0; i3 < a2.size(); i3++) {
                        Device b2 = SmartHomeDeviceManager.a().b(a2.get(i3));
                        if (b2 != null) {
                            this.i.add(b2);
                        }
                    }
                }
            }
        }

        public int a() {
            if (this.i == null) {
                return 0;
            }
            return this.i.size();
        }

        private Device a(int i2) {
            if (this.i == null || this.i.isEmpty() || i2 < 0 || i2 >= this.i.size()) {
                return null;
            }
            return this.i.get(i2);
        }

        public RecyclerView.ViewHolder b(View view) {
            return new ChildViewHolder(view);
        }

        public void a(RecyclerView.ViewHolder viewHolder, int i2) {
            Boolean bool;
            ChildViewHolder childViewHolder = (ChildViewHolder) viewHolder;
            final Device a2 = a(i2);
            if (a2 != null) {
                childViewHolder.c.setText(a2.name);
                DeviceFactory.b(a2.model, childViewHolder.b);
                childViewHolder.f20636a.setVisibility(8);
                if (BaseCUDFragment.this.h.containsKey(a2.did) && (bool = BaseCUDFragment.this.h.get(a2.did)) != null && (bool instanceof Boolean)) {
                    childViewHolder.f20636a.setVisibility(bool.booleanValue() ? 0 : 4);
                }
                childViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        boolean z = true;
                        ArrayList arrayList = new ArrayList(1);
                        arrayList.add(a2);
                        if (BaseCUDFragment.this.h.containsKey(a2.did)) {
                            Boolean bool = BaseCUDFragment.this.h.get(a2.did);
                            z = (bool == null || !(bool instanceof Boolean)) ? false : true ^ bool.booleanValue();
                            BaseCUDFragment.this.h.put(a2.did, Boolean.valueOf(z));
                        } else {
                            BaseCUDFragment.this.h.put(a2.did, true);
                        }
                        if (BaseCUDFragment.this.i != null) {
                            BaseCUDFragment.this.i.a(BaseCUDFragment.this, arrayList, z);
                        }
                        BaseCUDFragment.this.b.notifyDataSetChanged();
                    }
                });
            }
        }

        public RecyclerView.ViewHolder a(View view) {
            return new HeaderViewHolder(view);
        }

        public void a(RecyclerView.ViewHolder viewHolder) {
            final HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
            if (this.b != null) {
                headerViewHolder.b.setText(this.b.b());
            } else {
                headerViewHolder.b.setText(R.string.room_default);
            }
            List<Device> list = this.i;
            final boolean z = false;
            if (list != null && !list.isEmpty()) {
                int i2 = 0;
                while (true) {
                    if (i2 >= list.size()) {
                        z = true;
                        break;
                    }
                    Device device = list.get(i2);
                    if (device != null && !TextUtils.isEmpty(device.did)) {
                        Boolean bool = BaseCUDFragment.this.h.get(device.did);
                        if (!((bool == null || !(bool instanceof Boolean)) ? false : bool.booleanValue())) {
                            break;
                        }
                    }
                    i2++;
                }
            }
            if (z) {
                headerViewHolder.c.setImageResource(R.drawable.home_control_choose_hig);
            } else {
                headerViewHolder.c.setImageResource(R.drawable.home_control_choose_nor);
            }
            headerViewHolder.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (BaseCUDFragment.this.i != null) {
                        BaseCUDFragment.this.i.a(BaseCUDFragment.this, BaseSection.this.i, !z);
                    }
                }
            });
            headerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    headerViewHolder.c.performClick();
                }
            });
        }

        private class ChildViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            ImageView f20636a;
            SimpleDraweeView b;
            TextView c;
            View d;

            public ChildViewHolder(View view) {
                super(view);
                this.d = view;
                this.c = (TextView) view.findViewById(R.id.text);
                this.b = (SimpleDraweeView) view.findViewById(R.id.image);
                this.f20636a = (ImageView) view.findViewById(R.id.check_image);
            }
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public final TextView b;
            /* access modifiers changed from: private */
            public final ImageView c;
            private final View d;

            HeaderViewHolder(View view) {
                super(view);
                this.b = (TextView) view.findViewById(R.id.section_name);
                this.c = (ImageView) view.findViewById(R.id.check_image);
                this.d = view.findViewById(R.id.layout_divider);
            }
        }
    }
}
