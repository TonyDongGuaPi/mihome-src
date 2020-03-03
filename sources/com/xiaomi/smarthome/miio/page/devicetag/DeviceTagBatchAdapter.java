package com.xiaomi.smarthome.miio.page.devicetag;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DeviceTagBatchAdapter extends DeviceTagAdapter {
    private Set<String> d = new HashSet();

    public long b(int i) {
        return (long) i;
    }

    public DeviceTagBatchAdapter(Activity activity, String str) {
        super(activity, str);
    }

    public long c(int i, int i2) {
        DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(i);
        DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
        String str = "" + deviceTagGroup.t + deviceTagChild.d;
        if (deviceTagChild.g == null) {
            return (long) str.hashCode();
        }
        for (String str2 : deviceTagChild.g) {
            str = str + str2;
        }
        return (long) str.hashCode();
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b = DeviceTagGroupManager.a().a(this.d);
    }

    public void a(List<String> list) {
        if (list != null) {
            this.d.addAll(list);
        }
        g();
    }

    public Set<String> h() {
        return this.d;
    }

    public boolean a(DeviceTagGroupViewHolder deviceTagGroupViewHolder, int i, int i2, int i3, boolean z) {
        if (deviceTagGroupViewHolder instanceof SelectGroupViewHolder) {
            return a(((SelectGroupViewHolder) deviceTagGroupViewHolder).f19830a, deviceTagGroupViewHolder.b, i2, i3);
        }
        return false;
    }

    public boolean a(View view, View view2, int i, int i2) {
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        view2.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i, i2);
    }

    public void a(int i, int i2, boolean z) {
        if (this.b != null && i >= 0 && i < this.b.size()) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(i);
            if (deviceTagGroup.w != null && i2 >= 0 && i2 < deviceTagGroup.w.size()) {
                DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
                deviceTagChild.h = z;
                if (z) {
                    deviceTagGroup.x = z;
                } else {
                    boolean z2 = false;
                    Iterator<DeviceTagChild> it = deviceTagGroup.w.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (it.next().h) {
                                z2 = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    deviceTagGroup.x = z2;
                }
                a(deviceTagGroup.t, deviceTagChild.g, z);
            }
        }
    }

    public void c(int i, boolean z) {
        if (this.b != null && i >= 0 && i < this.b.size()) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(i);
            if (deviceTagGroup.w != null && !deviceTagGroup.w.isEmpty()) {
                deviceTagGroup.x = z;
                HashSet hashSet = new HashSet();
                for (DeviceTagChild next : deviceTagGroup.w) {
                    next.h = z;
                    if (next.g != null && !next.g.isEmpty()) {
                        hashSet.addAll(next.g);
                    }
                }
                a(deviceTagGroup.t, (Set<String>) hashSet, z);
            }
        }
    }

    private void a(int i, Set<String> set, boolean z) {
        if (set != null && !set.isEmpty()) {
            for (DeviceTagGroup deviceTagGroup : this.b) {
                if (!(deviceTagGroup.t == i || deviceTagGroup.w == null || deviceTagGroup.w.isEmpty())) {
                    boolean z2 = false;
                    for (DeviceTagChild next : deviceTagGroup.w) {
                        if (next.g != null && !next.g.isEmpty()) {
                            if (set.contains(next.g.iterator().next())) {
                                next.h = z;
                                if (!z) {
                                }
                            } else if (!next.h) {
                            }
                            z2 = true;
                        }
                    }
                    deviceTagGroup.x = z2;
                }
            }
            if (z) {
                this.d.addAll(set);
            } else {
                this.d.removeAll(set);
            }
            i();
            notifyDataSetChanged();
        }
    }

    private void i() {
        LocalBroadcastManager.getInstance(f()).sendBroadcast(new Intent(DeviceTagBatchActivity.ACTION_SELECTION_CHANGED));
    }
}
