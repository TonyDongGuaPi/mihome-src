package com.xiaomi.smarthome.homeroom;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import java.util.ArrayList;
import java.util.List;

public class DefaultRoomDeviceListActivity extends BaseActivity {
    public static final String HOME_ID = "home_id";

    /* renamed from: a  reason: collision with root package name */
    private String f17935a;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_default_room_device_list);
        this.f17935a = getIntent().getStringExtra("home_id");
        if (TextUtils.isEmpty(this.f17935a)) {
            this.f17935a = HomeManager.a().l();
        }
        a();
    }

    private void a() {
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DefaultRoomDeviceListActivity.this.finish();
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.tag_recommend_defaultroom);
        PullDownDragListView pullDownDragListView = (PullDownDragListView) findViewById(R.id.device_list);
        View inflate = LayoutInflater.from(this).inflate(R.layout.tag_group_item_common_3, pullDownDragListView, false);
        pullDownDragListView.addHeaderView(inflate);
        pullDownDragListView.setAdapter(new DefaultRoomDeviceAdapter(HomeManager.a().m(this.f17935a)));
        ViewGroup.LayoutParams layoutParams = inflate.getLayoutParams();
        layoutParams.height = DisplayUtils.a(20.0f);
        inflate.setLayoutParams(layoutParams);
    }

    private class DefaultRoomDeviceAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<Device> f17937a = new ArrayList();

        public long getItemId(int i) {
            return 0;
        }

        public DefaultRoomDeviceAdapter(List<Device> list) {
            if (list != null) {
                this.f17937a = list;
            }
        }

        public int getCount() {
            return this.f17937a.size();
        }

        /* renamed from: a */
        public Device getItem(int i) {
            if (i < 0 || i >= this.f17937a.size()) {
                return null;
            }
            return this.f17937a.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(DefaultRoomDeviceListActivity.this).inflate(R.layout.tag_child_item_editor_select, viewGroup, false);
                viewHolder = new ViewHolder(view);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.a(i, getItem(i));
            return view;
        }

        class ViewHolder {
            private CheckBox b;
            private TextView c;
            private TextView d;
            private SimpleDraweeView e;
            private View f;

            public ViewHolder(View view) {
                this.e = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.c = (TextView) view.findViewById(R.id.title);
                this.f = view.findViewById(R.id.divider_item);
                this.b = (CheckBox) view.findViewById(R.id.select_check);
                this.d = (TextView) view.findViewById(R.id.room);
            }

            public void a(int i, Device device) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f.getLayoutParams();
                if (i == DefaultRoomDeviceAdapter.this.getCount() - 1) {
                    layoutParams.leftMargin = 0;
                } else {
                    layoutParams.leftMargin = DisplayUtils.a((Context) DefaultRoomDeviceListActivity.this, 13.0f);
                }
                if (device != null) {
                    this.c.setText(device.name);
                    DeviceFactory.b(device.model, this.e);
                }
                this.b.setVisibility(8);
                this.d.setVisibility(8);
            }
        }
    }
}
