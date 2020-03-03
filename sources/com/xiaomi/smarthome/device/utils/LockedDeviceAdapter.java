package com.xiaomi.smarthome.device.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.utils.LockedDeviceViewManager;
import com.xiaomi.smarthome.library.common.widget.AnimateFakeList;
import com.xiaomi.smarthome.miio.activity.ClientAllLockedActivity;
import com.xiaomi.smarthome.miio.ir.IRHeaderTabs;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Deprecated
public class LockedDeviceAdapter extends LockedBaseAdapter implements Device.StateChangedListener {

    /* renamed from: a  reason: collision with root package name */
    static final String f15480a = "LockedDeviceAdapter";
    final ClientAllLockedActivity b;
    HashMap<Device, View> c = null;
    AnimateFakeList d;
    List<Device> e;

    public long getItemId(int i) {
        return (long) i;
    }

    public LockedDeviceAdapter(ClientAllLockedActivity clientAllLockedActivity, AnimateFakeList animateFakeList) {
        this.b = clientAllLockedActivity;
        LockedDeviceViewManager.b = new WeakReference<>(this);
        this.d = animateFakeList;
    }

    public Context a() {
        return this.b;
    }

    public void a(List<Device> list) {
        this.e = list;
    }

    public void b() {
        IRHeaderTabs iRHeaderTabs;
        if (this.e == null) {
            this.e = new ArrayList();
        }
        if (this.c != null) {
            this.c.clear();
        } else {
            this.c = new HashMap<>(this.e.size());
        }
        for (int i = 0; i < this.e.size(); i++) {
            Device device = this.e.get(i);
            if (IRDeviceUtil.a(device.did)) {
                IRHeaderTabs iRHeaderTabs2 = new IRHeaderTabs(this.b);
                iRHeaderTabs2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                iRHeaderTabs = iRHeaderTabs2;
            } else if (LockedDeviceViewManager.c(device)) {
                iRHeaderTabs = LayoutInflater.from(this.b).inflate(R.layout.client_all_item_v2_locked_button, this.d, false);
            } else if (DeviceUtils.a(device)) {
                iRHeaderTabs = LayoutInflater.from(this.b).inflate(R.layout.client_all_item_v2_locked_switch, this.d, false);
            } else if (LockedDeviceViewManager.d(device)) {
                iRHeaderTabs = LayoutInflater.from(this.b).inflate(R.layout.client_all_item_v2_locked_router, this.d, false);
            } else {
                iRHeaderTabs = LayoutInflater.from(this.b).inflate(R.layout.client_all_item_v2_locked, this.d, false);
            }
            LockedDeviceViewManager.a(iRHeaderTabs, device);
            LockedDeviceViewManager.a(this.b, iRHeaderTabs);
            this.c.put(device, iRHeaderTabs);
            device.addStateChangedListener(this);
        }
    }

    public void a(boolean z) {
        View view;
        Object tag;
        Log.d(f15480a, "updateSecureWidgetState,shouldShow=" + z);
        if (this.e != null) {
            for (int i = 0; i < this.e.size(); i++) {
                Device device = this.e.get(i);
                if (!(device == null || (view = this.c.get(device)) == null || (tag = view.getTag()) == null || !(tag instanceof LockedDeviceViewManager.ViewHolder))) {
                    LockedDeviceViewManager.ViewHolder viewHolder = (LockedDeviceViewManager.ViewHolder) tag;
                    if (viewHolder.f != null) {
                        if (z) {
                            viewHolder.f.setVisibility(0);
                        } else {
                            viewHolder.f.setVisibility(8);
                        }
                    }
                    if (viewHolder.e != null) {
                        if (!z || !device.isOnline) {
                            viewHolder.e.setVisibility(8);
                        } else {
                            viewHolder.e.setVisibility(0);
                        }
                    }
                    if (viewHolder.g != null) {
                        if (z) {
                            viewHolder.g.setVisibility(0);
                        } else {
                            viewHolder.g.setVisibility(8);
                        }
                    }
                }
            }
        }
    }

    public int getCount() {
        return this.e.size();
    }

    public Object getItem(int i) {
        return this.e.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Device device;
        if (i < this.e.size() && (device = this.e.get(i)) != null) {
            return this.c.get(device);
        }
        return null;
    }

    public void c() {
        for (Device device : this.e) {
            try {
                View view = this.c.get(device);
                if (view != null) {
                    LockedDeviceViewManager.a(this.b, view);
                }
            } catch (Exception e2) {
                Log.e(f15480a, "" + e2);
            }
        }
    }

    public void a(Device device) {
        TextView textView;
        if (device != null) {
            for (Device next : this.e) {
                try {
                    if (TextUtils.equals(next.did, device.did)) {
                        View view = this.c.get(next);
                        if (view != null && (textView = (TextView) view.findViewById(R.id.name_status)) != null) {
                            textView.setText(R.string.retrieving_data);
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    Log.e(f15480a, "" + e2);
                }
            }
        }
    }

    public void onStateChanged(Device device) {
        if (device == null) {
            for (Device device2 : this.e) {
                try {
                    View view = this.c.get(device2);
                    if (view != null) {
                        LockedDeviceViewManager.a(this.b, view);
                    }
                } catch (Exception e2) {
                    Log.e(f15480a, "" + e2);
                }
            }
            return;
        }
        for (Device next : this.e) {
            try {
                if (TextUtils.equals(next.did, device.did)) {
                    View view2 = this.c.get(next);
                    if (view2 != null) {
                        LockedDeviceViewManager.a(this.b, view2);
                        return;
                    }
                    return;
                }
            } catch (Exception e3) {
                Log.e(f15480a, "" + e3);
            }
        }
    }
}
