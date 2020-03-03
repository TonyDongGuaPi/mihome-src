package com.xiaomi.smarthome.newui.commonusemgr;

import android.os.Bundle;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.homeroom.HomeVirtualDeviceHelper;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import com.xiaomi.smarthome.newui.AddToCommonDialogHelper;
import com.xiaomi.smarthome.newui.commonusemgr.BaseCUDFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CategoryDeviceFragment extends BaseCUDFragment {
    private int j = 0;
    private AddToCommonDialogHelper k = new AddToCommonDialogHelper();

    private void a(BaseCUDFragment.ItemData itemData, List<Device> list) {
        this.b.a((Section) new BaseCUDFragment.BaseSection(itemData, list, this.j));
        this.j++;
    }

    static BaseCUDFragment a(String str) {
        CategoryDeviceFragment categoryDeviceFragment = new CategoryDeviceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", str);
        categoryDeviceFragment.setArguments(bundle);
        return categoryDeviceFragment;
    }

    /* access modifiers changed from: protected */
    public void b() {
        Device b;
        DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        HashMap hashMap = new HashMap(b2.d() == null ? new HashMap<>() : b2.d());
        HashSet hashSet = new HashSet();
        for (Map.Entry entry : hashMap.entrySet()) {
            String str = (String) entry.getKey();
            ArrayList<String> arrayList = new ArrayList<>((Set) entry.getValue());
            ArrayList<Device> arrayList2 = new ArrayList<>();
            for (String b3 : arrayList) {
                Device b4 = SmartHomeDeviceManager.a().b(b3);
                if (b4 != null && !AddToCommonDialogHelper.a(b4)) {
                    arrayList2.add(b4);
                }
            }
            this.k.a((List<Device>) arrayList2);
            if (!arrayList2.isEmpty()) {
                for (Device device : arrayList2) {
                    hashSet.add(device.did);
                }
                a(new Category(str, (List<String>) null), arrayList2);
            }
        }
        ArrayList arrayList3 = new ArrayList();
        for (Map.Entry entry2 : this.h.entrySet()) {
            if (!TextUtils.isEmpty((CharSequence) entry2.getKey()) && !hashSet.contains(entry2.getKey()) && (b = SmartHomeDeviceManager.a().b((String) entry2.getKey())) != null) {
                arrayList3.add(b);
            }
        }
        this.k.a((List<Device>) arrayList3);
        arrayList3.addAll(HomeVirtualDeviceHelper.a());
        if (!arrayList3.isEmpty()) {
            a(new Category(getResources().getString(R.string.other_device1), (List<String>) null), arrayList3);
        }
        if (this.b.getItemCount() <= 0) {
            a();
        } else {
            this.b.notifyDataSetChanged();
        }
    }

    class Category implements BaseCUDFragment.ItemData {
        private String b;
        private List<String> c;

        public Category(String str, List<String> list) {
            this.b = str;
            this.c = list;
        }

        public List<String> a() {
            return this.c;
        }

        public String b() {
            return this.b;
        }
    }
}
