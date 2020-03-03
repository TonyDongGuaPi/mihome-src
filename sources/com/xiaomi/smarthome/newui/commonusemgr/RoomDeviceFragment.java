package com.xiaomi.smarthome.newui.commonusemgr;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.newui.AddToCommonDialogHelper;
import com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RoomDeviceFragment extends BaseCUDFragment {
    private int j = 0;
    private AddToCommonDialogHelper k = new AddToCommonDialogHelper();

    private void a(BaseCUDFragment.ItemData itemData, List<Device> list) {
        this.b.a((Section) new BaseCUDFragment.BaseSection(itemData, list, this.j));
        this.j++;
    }

    static BaseCUDFragment a(String str) {
        RoomDeviceFragment roomDeviceFragment = new RoomDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        roomDeviceFragment.setArguments(bundle);
        return roomDeviceFragment;
    }

    /* access modifiers changed from: protected */
    public void b() {
        Device b;
        ArrayList arrayList = new ArrayList(HomeManager.a().e());
        ArrayList arrayList2 = new ArrayList(HomeManager.a().j());
        this.k.a((List<Device>) arrayList2);
        if (!arrayList.isEmpty() || AddToCommonDialogHelper.b(SmartHomeDeviceManager.a().d()) != 0) {
            HashSet hashSet = new HashSet();
            for (int i = 0; i < arrayList.size(); i++) {
                final Room room = (Room) arrayList.get(i);
                if (!(room == null || room.h() == null || room.h().isEmpty())) {
                    List<String> h = room.h();
                    ArrayList arrayList3 = new ArrayList();
                    for (int i2 = 0; i2 < h.size(); i2++) {
                        Device b2 = SmartHomeDeviceManager.a().b(h.get(i2));
                        if (b2 != null && !AddToCommonDialogHelper.a(b2)) {
                            arrayList3.add(b2);
                            hashSet.add(b2.did);
                        }
                    }
                    this.k.a((List<Device>) arrayList3);
                    if (!arrayList3.isEmpty()) {
                        a((BaseCUDFragment.ItemData) new BaseCUDFragment.ItemData() {
                            public List<String> a() {
                                return room.h();
                            }

                            public String b() {
                                return room.e();
                            }
                        }, (List<Device>) arrayList3);
                    }
                }
            }
            a((List<Device>) arrayList2, (Set<String>) hashSet);
            ArrayList arrayList4 = new ArrayList();
            for (Map.Entry entry : this.h.entrySet()) {
                if (!TextUtils.isEmpty((CharSequence) entry.getKey()) && !hashSet.contains(entry.getKey()) && (b = SmartHomeDeviceManager.a().b((String) entry.getKey())) != null) {
                    arrayList4.add(b);
                }
            }
            this.k.a((List<Device>) arrayList4);
            arrayList4.addAll(HomeVirtualDeviceHelper.a());
            if (!arrayList4.isEmpty()) {
                final Room room2 = new Room();
                room2.e(getResources().getString(R.string.other_device1));
                a((BaseCUDFragment.ItemData) new BaseCUDFragment.ItemData() {
                    public List<String> a() {
                        return room2.h();
                    }

                    public String b() {
                        return room2.e();
                    }
                }, (List<Device>) arrayList4);
            }
            if (this.b.getItemCount() <= 0) {
                a();
            } else {
                this.b.notifyDataSetChanged();
            }
        } else if (HomeVirtualDeviceHelper.a().size() != 0) {
        }
    }

    private void a(List<Device> list, Set<String> set) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);
            if (device != null && !AddToCommonDialogHelper.a(device)) {
                arrayList.add(device);
                if (set != null) {
                    set.add(device.did);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            a((BaseCUDFragment.ItemData) null, list);
        }
    }
}
