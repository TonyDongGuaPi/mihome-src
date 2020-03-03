package com.xiaomi.smarthome.scene;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import java.util.List;

public class SceneItemChooseFragment extends BaseFragment {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<ItemData> f21307a;
    /* access modifiers changed from: private */
    public ListView b;
    /* access modifiers changed from: private */
    public ItemData c;
    private DeviceChooseAdapter d;
    private TextView e;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.item_choose_layout, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
        this.d = new DeviceChooseAdapter();
        this.b.setAdapter(this.d);
    }

    public void a(View view) {
        this.b = (ListView) view.findViewById(R.id.content_list_view);
        this.e = (TextView) view.findViewById(R.id.setting_page_category_title);
    }

    public void a(List<ItemData> list, ItemData itemData) {
        this.f21307a = list;
        this.c = itemData;
        this.d.notifyDataSetChanged();
    }

    public void a(String str) {
        if (this.e != null && str != null) {
            this.e.setText(str);
        }
    }

    public ItemData a() {
        return this.c;
    }

    private class DeviceChooseAdapter extends BaseAdapter {
        /* access modifiers changed from: private */
        public int b;

        public long getItemId(int i) {
            return 0;
        }

        private DeviceChooseAdapter() {
            this.b = -1;
        }

        public int getCount() {
            if (SceneItemChooseFragment.this.f21307a != null) {
                return SceneItemChooseFragment.this.f21307a.size();
            }
            return 0;
        }

        public Object getItem(int i) {
            if (SceneItemChooseFragment.this.f21307a != null && i >= 0 && i < SceneItemChooseFragment.this.f21307a.size()) {
                return SceneItemChooseFragment.this.f21307a.get(i);
            }
            return null;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(SceneItemChooseFragment.this.getContext()).inflate(R.layout.wifi_choose_list_item, (ViewGroup) null, false);
                viewHolder = new ViewHolder();
                viewHolder.f21311a = (TextView) view.findViewById(R.id.wifi_text);
                viewHolder.b = (ImageView) view.findViewById(R.id.select_flag_image);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final ItemData itemData = (ItemData) SceneItemChooseFragment.this.f21307a.get(i);
            if (itemData != null) {
                viewHolder.f21311a.setText(itemData.b);
                if (itemData.equals(SceneItemChooseFragment.this.c)) {
                    viewHolder.b.setImageResource(R.drawable.wifi_check_press);
                    this.b = i;
                } else {
                    viewHolder.b.setImageResource(R.drawable.wifi_check_normal);
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ViewHolder viewHolder;
                    if (DeviceChooseAdapter.this.b != i) {
                        if (!(DeviceChooseAdapter.this.b == -1 || (viewHolder = (ViewHolder) SceneItemChooseFragment.this.b.getChildAt(DeviceChooseAdapter.this.b).getTag()) == null)) {
                            viewHolder.b.setImageResource(R.drawable.wifi_check_normal);
                            viewHolder.b.invalidate();
                        }
                        viewHolder.b.setImageResource(R.drawable.wifi_check_press);
                        viewHolder.b.invalidate();
                        ItemData unused = SceneItemChooseFragment.this.c = itemData;
                        int unused2 = DeviceChooseAdapter.this.b = i;
                    }
                }
            });
            return view;
        }
    }

    static class ItemData implements Parcelable {
        public static final Parcelable.Creator<ItemData> CREATOR = new Parcelable.Creator<ItemData>() {
            /* renamed from: a */
            public ItemData createFromParcel(Parcel parcel) {
                return new ItemData(parcel);
            }

            /* renamed from: a */
            public ItemData[] newArray(int i) {
                return new ItemData[i];
            }
        };

        /* renamed from: a  reason: collision with root package name */
        public String f21310a;
        public String b;

        public int describeContents() {
            return 0;
        }

        public ItemData() {
        }

        public ItemData(Parcel parcel) {
            this.f21310a = parcel.readString();
            this.b = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f21310a);
            parcel.writeString(this.b);
        }
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21311a;
        public ImageView b;

        ViewHolder() {
        }
    }
}
