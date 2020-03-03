package com.xiaomi.smarthome.device.bluetooth.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.bluetooth.BleCacheUtils;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.device.bluetooth.BleDispatcher;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BleMatchListFragment extends BleMatchFragment {
    private static final int d = 2130903608;
    private ListView e;
    private BindListAdapter f;
    /* access modifiers changed from: private */
    public List<BleDevice> g;
    private TextView h;

    public static BleMatchListFragment b() {
        return new BleMatchListFragment();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.ble_bind_list, (ViewGroup) null);
        this.e = (ListView) inflate.findViewById(R.id.listview);
        this.e.setSelector(new ColorDrawable(0));
        this.f = new BindListAdapter();
        this.e.setAdapter(this.f);
        this.h = (TextView) inflate.findViewById(R.id.add_batch);
        if (this.f15225a != null) {
            PluginDeviceInfo c = CoreApi.a().d(this.f15225a.model).c();
            if (c != null && c.t() == Device.PID_BLE_MESH) {
                this.h.setVisibility(0);
                this.h.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        BleMatchListFragment.this.a(view);
                    }
                });
            }
            STAT.c.g(this.f15225a.model, SmartConfigMainActivity.DEVICE_FROM_APP_PLUS_TYPE == 7 ? 1 : 2);
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        Intent intent = new Intent(this.mContext, BleMatchListChoiceActivity.class);
        intent.putExtra("key_model", a());
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void a(BleDevice bleDevice, List<String> list) {
        super.a(bleDevice, list);
        if (this.g == null) {
            this.g = new ArrayList();
        } else {
            this.g.clear();
        }
        this.g.addAll(this.b.values());
        Collections.sort(this.g, new Comparator<BleDevice>() {
            /* renamed from: a */
            public int compare(BleDevice bleDevice, BleDevice bleDevice2) {
                return bleDevice2.rssi - bleDevice.rssi;
            }
        });
    }

    private class BindListAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private BindListAdapter() {
        }

        public int getCount() {
            if (BleMatchListFragment.this.g != null) {
                return BleMatchListFragment.this.g.size();
            }
            return 0;
        }

        /* renamed from: a */
        public BleDevice getItem(int i) {
            if (i < 0 || i >= getCount()) {
                return null;
            }
            return (BleDevice) BleMatchListFragment.this.g.get(i);
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            SimpleDraweeView f15248a;
            TextView b;
            TextView c;
            View d;
            ImageView e;
            ImageView f;

            private ViewHolder() {
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(BleMatchListFragment.this.mContext).inflate(R.layout.ble_bind_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f15248a = (SimpleDraweeView) view.findViewById(R.id.icon);
                viewHolder.b = (TextView) view.findViewById(R.id.name);
                viewHolder.c = (TextView) view.findViewById(R.id.mac);
                viewHolder.d = view.findViewById(R.id.content);
                viewHolder.e = (ImageView) view.findViewById(R.id.signal);
                viewHolder.f = (ImageView) view.findViewById(R.id.arrow);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final BleDevice a2 = getItem(i);
            viewHolder.b.setText(BleMatchListFragment.this.a(a2));
            viewHolder.c.setText(a2.mac);
            viewHolder.c.setVisibility(TextUtils.isEmpty(a2.mac) ? 8 : 0);
            DeviceFactory.b(a2.model, viewHolder.f15248a);
            viewHolder.d.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.aZ(a2.model);
                    if (!BleDispatcher.a(a2) || !((BleMatchActivity) BleMatchListFragment.this.mContext).hasMatchImageFragment()) {
                        ((BleMatchActivity) BleMatchListFragment.this.mContext).onDeviceMatched(a2);
                    } else {
                        ((BleMatchActivity) BleMatchListFragment.this.mContext).switchToMatchImageFragment();
                    }
                }
            });
            viewHolder.f.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!BleDispatcher.a(a2) || !((BleMatchActivity) BleMatchListFragment.this.mContext).hasMatchImageFragment()) {
                        ((BleMatchActivity) BleMatchListFragment.this.mContext).onDeviceMatched(a2);
                    } else {
                        ((BleMatchActivity) BleMatchListFragment.this.mContext).switchToMatchImageFragment();
                    }
                }
            });
            if (a2.rssi >= -30) {
                viewHolder.e.setImageResource(R.drawable.tag_ble_04);
            } else if (a2.rssi >= -50) {
                viewHolder.e.setImageResource(R.drawable.tag_ble_03);
            } else if (a2.rssi >= -70) {
                viewHolder.e.setImageResource(R.drawable.tag_ble_02);
            } else {
                viewHolder.e.setImageResource(R.drawable.tag_ble_01);
            }
            return view;
        }
    }

    /* access modifiers changed from: private */
    public String a(BleDevice bleDevice) {
        String d2 = BleCacheUtils.d(bleDevice.mac);
        return TextUtils.isEmpty(d2) ? BleDeviceGroup.c(bleDevice.model) : d2;
    }
}
