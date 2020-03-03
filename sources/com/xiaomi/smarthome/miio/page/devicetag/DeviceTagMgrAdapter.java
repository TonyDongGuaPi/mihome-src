package com.xiaomi.smarthome.miio.page.devicetag;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import com.h6ah4i.android.widget.advrecyclerview.draggable.ItemDraggableRange;
import com.h6ah4i.android.widget.advrecyclerview.expandable.GroupPositionItemDraggableRange;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import java.util.List;

public class DeviceTagMgrAdapter extends DeviceTagAdapter {
    private boolean d = false;

    public boolean b(int i, int i2, int i3, int i4) {
        return i == i3;
    }

    public DeviceTagMgrAdapter(Activity activity, String str) {
        super(activity, str);
        b();
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b = DeviceTagGroupManager.a().c();
    }

    public boolean a(DeviceTagChildViewHolder deviceTagChildViewHolder, int i, int i2, int i3, int i4) {
        View view;
        if (deviceTagChildViewHolder instanceof SelectChildViewHolder) {
            view = ((SelectChildViewHolder) deviceTagChildViewHolder).f19828a;
        } else if (deviceTagChildViewHolder instanceof SortEditChildViewHolder) {
            view = ((SortEditChildViewHolder) deviceTagChildViewHolder).f19837a;
        } else {
            view = deviceTagChildViewHolder instanceof SortCommonChildViewHolder ? ((SortCommonChildViewHolder) deviceTagChildViewHolder).f19834a : null;
        }
        if (view == null) {
            return false;
        }
        Rect rect = new Rect();
        deviceTagChildViewHolder.itemView.getGlobalVisibleRect(rect);
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        rect2.left -= rect.left;
        rect2.top -= rect.top;
        return rect2.contains(i3, i4);
    }

    public ItemDraggableRange a(DeviceTagChildViewHolder deviceTagChildViewHolder, int i, int i2) {
        return new GroupPositionItemDraggableRange(i, i);
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i == i3 && i2 != i4 && i < this.b.size()) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(i);
            if (deviceTagGroup.w != null && i2 < deviceTagGroup.w.size() && i4 < deviceTagGroup.w.size()) {
                deviceTagGroup.w.add(i4, deviceTagGroup.w.remove(i2));
                this.d = true;
            }
        }
    }

    public void a(int i, int i2, boolean z) {
        if (i >= 0 && i < this.b.size()) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(i);
            if (deviceTagGroup.w != null && i2 >= 0 && i2 < deviceTagGroup.w.size()) {
                DeviceTagChild deviceTagChild = deviceTagGroup.w.get(i2);
                if (deviceTagChild.h != z) {
                    deviceTagChild.h = z;
                    this.d = true;
                }
            }
        }
    }

    public void h() {
        if (this.d) {
            ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).d((List<DeviceTagGroup>) this.b);
            this.d = false;
        }
    }
}
