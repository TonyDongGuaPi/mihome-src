package com.xiaomi.smarthome.miio.page.devicetag;

import android.app.Activity;
import android.os.Handler;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DeviceTagEditorAdapter extends DeviceTagAdapter {
    private String d;
    private final Activity e;

    public long b(int i) {
        return (long) i;
    }

    public DeviceTagEditorAdapter(Activity activity, String str) {
        super(activity, str);
        this.e = activity;
        SmartHomeDeviceHelper.a().b().k();
        b();
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

    public void a(String str) {
        this.d = str;
        g();
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b = DeviceTagGroupManager.a().a(this.d);
    }

    public void d(int i) {
        if (this.b.size() == 2) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(0);
            if (deviceTagGroup.w != null && i < deviceTagGroup.w.size()) {
                DeviceTagGroup deviceTagGroup2 = (DeviceTagGroup) this.b.get(1);
                DeviceTagChild remove = deviceTagGroup.w.remove(i);
                deviceTagGroup.u = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_contain_title, deviceTagGroup.w.size());
                if (deviceTagGroup2.s == 4) {
                    deviceTagGroup2.s = 5;
                    deviceTagGroup2.w.clear();
                }
                deviceTagGroup2.w.add(remove);
                if (deviceTagGroup.w.isEmpty()) {
                    deviceTagGroup.s = 4;
                    DeviceTagChild deviceTagChild = new DeviceTagChild();
                    deviceTagChild.d = SHApplication.getAppContext().getString(R.string.tag_no_device);
                    deviceTagGroup.w.add(deviceTagChild);
                }
                notifyDataSetChanged();
            }
        }
    }

    public void e(int i) {
        if (this.b.size() == 2) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(0);
            DeviceTagGroup deviceTagGroup2 = (DeviceTagGroup) this.b.get(1);
            if (deviceTagGroup2.w != null && i < deviceTagGroup2.w.size()) {
                DeviceTagChild remove = deviceTagGroup2.w.remove(i);
                if (deviceTagGroup.s == 4) {
                    deviceTagGroup.s = 5;
                    deviceTagGroup.w.clear();
                }
                deviceTagGroup.w.add(remove);
                deviceTagGroup.u = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_contain_title, deviceTagGroup.w.size());
                if (deviceTagGroup2.w.isEmpty()) {
                    deviceTagGroup2.s = 4;
                    DeviceTagChild deviceTagChild = new DeviceTagChild();
                    deviceTagChild.d = SHApplication.getAppContext().getString(R.string.tag_no_device_to_add);
                    deviceTagGroup2.w.add(deviceTagChild);
                }
                notifyDataSetChanged();
            }
        }
    }

    public void c(int i, boolean z) {
        if (this.b.size() == 1) {
            int i2 = 0;
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(0);
            deviceTagGroup.w.get(i).h = z;
            for (DeviceTagChild deviceTagChild : deviceTagGroup.w) {
                if (deviceTagChild.h) {
                    i2++;
                }
            }
            deviceTagGroup.u = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_selected_title, i2);
            if (deviceTagGroup.w.isEmpty()) {
                deviceTagGroup.s = 4;
                DeviceTagChild deviceTagChild2 = new DeviceTagChild();
                deviceTagChild2.d = SHApplication.getAppContext().getString(R.string.tag_no_device_to_add);
                deviceTagGroup.w.add(deviceTagChild2);
            }
            new Handler().post(new Runnable() {
                public void run() {
                    DeviceTagEditorAdapter.this.notifyDataSetChanged();
                }
            });
        }
    }

    public Set<String> h() {
        DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(0);
        if (deviceTagGroup.s != 9) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        if (deviceTagGroup.w != null && deviceTagGroup.w.size() > 0) {
            for (DeviceTagChild next : deviceTagGroup.w) {
                if (next.g != null && next.h) {
                    hashSet.add(next.g.iterator().next());
                }
            }
        }
        return hashSet;
    }

    public void a(ArrayList<String> arrayList) {
        boolean z;
        if (arrayList != null && !arrayList.isEmpty() && this.b.size() == 2) {
            DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(0);
            DeviceTagGroup deviceTagGroup2 = (DeviceTagGroup) this.b.get(1);
            if (deviceTagGroup2.s != 4) {
                if (deviceTagGroup.s == 5) {
                    ArrayList arrayList2 = new ArrayList();
                    for (DeviceTagChild next : deviceTagGroup.w) {
                        if (next.g != null && !next.g.isEmpty() && !arrayList.contains(next.g.iterator().next())) {
                            arrayList2.add(next);
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            deviceTagGroup.w.remove((DeviceTagChild) it.next());
                        }
                    }
                    Iterator<String> it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        String next2 = it2.next();
                        Iterator<DeviceTagChild> it3 = deviceTagGroup.w.iterator();
                        while (true) {
                            if (!it3.hasNext()) {
                                z = false;
                                break;
                            }
                            DeviceTagChild next3 = it3.next();
                            if (next3.g != null && next3.g.contains(next2)) {
                                z = true;
                                break;
                            }
                        }
                        if (!z) {
                            Iterator<DeviceTagChild> it4 = deviceTagGroup2.w.iterator();
                            while (true) {
                                if (!it4.hasNext()) {
                                    break;
                                }
                                DeviceTagChild next4 = it4.next();
                                if (next4.g != null && next4.g.contains(next2)) {
                                    deviceTagGroup.w.add(next4);
                                    deviceTagGroup2.w.remove(next4);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    Iterator<String> it5 = arrayList.iterator();
                    boolean z2 = false;
                    while (it5.hasNext()) {
                        String next5 = it5.next();
                        Iterator<DeviceTagChild> it6 = deviceTagGroup2.w.iterator();
                        while (true) {
                            if (!it6.hasNext()) {
                                break;
                            }
                            DeviceTagChild next6 = it6.next();
                            if (next6.g != null && next6.g.contains(next5)) {
                                deviceTagGroup.w.add(next6);
                                deviceTagGroup2.w.remove(next6);
                                z2 = true;
                                break;
                            }
                        }
                    }
                    if (z2) {
                        deviceTagGroup.w.remove(0);
                    }
                }
                deviceTagGroup.s = 5;
                deviceTagGroup.u = SHApplication.getAppContext().getResources().getQuantityString(R.plurals.tag_contain_title, deviceTagGroup.w.size());
                if (deviceTagGroup2.w.isEmpty()) {
                    deviceTagGroup2.s = 4;
                    DeviceTagChild deviceTagChild = new DeviceTagChild();
                    deviceTagChild.d = SHApplication.getAppContext().getString(R.string.tag_no_device_to_add);
                    deviceTagGroup2.w.add(deviceTagChild);
                }
                notifyDataSetChanged();
            }
        }
    }

    public String i() {
        Activity activity = this.e;
        return activity instanceof DeviceTagEditorActivity ? ((DeviceTagEditorActivity) activity).getCurrentTag() : "";
    }
}
