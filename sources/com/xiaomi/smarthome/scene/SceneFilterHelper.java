package com.xiaomi.smarthome.scene;

import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SceneFilterHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final int f21306a = 0;
    public static final int b = 1;
    public static final int c = 2;

    public static List<SceneApi.SmartHomeScene> a(Home home, List<SceneApi.SmartHomeScene> list) {
        ArrayList arrayList = new ArrayList();
        if (list == null || list.isEmpty()) {
            return arrayList;
        }
        if (home == null || home.j().equalsIgnoreCase("ALL_HOME")) {
            if (list != null && !list.isEmpty()) {
                arrayList.addAll(list);
            }
            return arrayList;
        }
        List<String> a2 = HomeManager.a().a(home.j(), true);
        if (a2 == null || a2.isEmpty()) {
            return arrayList;
        }
        HashSet hashSet = new HashSet(a2);
        for (int i = 0; i < list.size(); i++) {
            List<String> list2 = list.get(i).w;
            list2.addAll(a(list.get(i), 0));
            if (list2 != null && !list2.isEmpty()) {
                int i2 = 0;
                while (true) {
                    if (i2 >= (list2 != null ? list2.size() : 0)) {
                        break;
                    } else if (hashSet.contains(list2.get(i2))) {
                        arrayList.add(list.get(i));
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<SceneApi.SmartHomeScene> a(Home home, Room room, List<SceneApi.SmartHomeScene> list) {
        List<Device> list2;
        int i;
        List<Device> l;
        ArrayList arrayList = new ArrayList();
        if (home == null || list == null || list.isEmpty()) {
            return arrayList;
        }
        if (room == null || room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
            List<SceneApi.SmartHomeScene> a2 = a(home, list);
            if (a2 != null && !a2.isEmpty()) {
                arrayList.addAll(a2);
            }
            return arrayList;
        } else if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
            if (home.j().equalsIgnoreCase("ALL_HOME")) {
                List<Home> f = HomeManager.a().f();
                list2 = null;
                int i2 = 0;
                while (true) {
                    if (f == null) {
                        i = 0;
                    } else {
                        i = f.size();
                    }
                    if (i2 >= i) {
                        break;
                    }
                    if (f.get(i2).p() && (l = HomeManager.a().l(f.get(i2).j())) != null && l.size() > 0) {
                        if (list2 == null) {
                            list2 = new ArrayList<>();
                        }
                        list2.addAll(l);
                    }
                    i2++;
                }
            } else {
                list2 = HomeManager.a().l(home.j());
            }
            if (list2 == null || list2.isEmpty()) {
                return arrayList;
            }
            HashSet hashSet = new HashSet();
            for (int i3 = 0; i3 < list2.size(); i3++) {
                hashSet.add(list2.get(i3).did);
            }
            return a((Set<String>) hashSet, list);
        } else {
            List<String> h = room.h();
            if (h == null || h.isEmpty()) {
                return arrayList;
            }
            return a((Set<String>) new HashSet(h), list);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00d7, code lost:
        r7 = r3.get(r8.d);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene> a(com.xiaomi.smarthome.homeroom.model.Home r7, com.xiaomi.smarthome.device.utils.DeviceTagInterface.Category r8, java.util.List<com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene> r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r7 != 0) goto L_0x0008
            return r0
        L_0x0008:
            if (r8 != 0) goto L_0x000b
            return r0
        L_0x000b:
            if (r9 == 0) goto L_0x00f4
            boolean r1 = r9.isEmpty()
            if (r1 == 0) goto L_0x0015
            goto L_0x00f4
        L_0x0015:
            r1 = 0
            java.lang.String r2 = r7.j()
            java.lang.String r3 = "ALL_HOME"
            boolean r2 = r2.equalsIgnoreCase(r3)
            if (r2 == 0) goto L_0x00be
            com.xiaomi.smarthome.homeroom.HomeManager r7 = com.xiaomi.smarthome.homeroom.HomeManager.a()
            java.util.List r7 = r7.f()
            r2 = 0
            r3 = r1
            r1 = 0
        L_0x002d:
            if (r7 != 0) goto L_0x0031
            r4 = 0
            goto L_0x0035
        L_0x0031:
            int r4 = r7.size()
        L_0x0035:
            if (r1 >= r4) goto L_0x00ce
            java.lang.Object r4 = r7.get(r1)
            com.xiaomi.smarthome.homeroom.model.Home r4 = (com.xiaomi.smarthome.homeroom.model.Home) r4
            boolean r4 = r4.p()
            if (r4 != 0) goto L_0x0045
            goto L_0x00ba
        L_0x0045:
            com.xiaomi.smarthome.device.SmartHomeDeviceHelper r4 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
            com.xiaomi.smarthome.device.utils.DeviceTagInterface r4 = r4.b()
            java.lang.Object r5 = r7.get(r1)
            com.xiaomi.smarthome.homeroom.model.Home r5 = (com.xiaomi.smarthome.homeroom.model.Home) r5
            java.lang.String r5 = r5.j()
            java.util.Map r4 = r4.j(r5)
            if (r4 == 0) goto L_0x00ba
            int r5 = r4.size()
            if (r5 <= 0) goto L_0x00ba
            if (r3 != 0) goto L_0x006e
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            r3.putAll(r4)
            goto L_0x00ba
        L_0x006e:
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0076:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x00ba
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r6 = r5.getValue()
            if (r6 == 0) goto L_0x0076
            java.lang.Object r6 = r5.getValue()
            java.util.List r6 = (java.util.List) r6
            int r6 = r6.size()
            if (r6 != 0) goto L_0x0095
            goto L_0x0076
        L_0x0095:
            java.lang.Object r6 = r5.getKey()
            boolean r6 = r3.containsKey(r6)
            if (r6 == 0) goto L_0x0076
            java.lang.Object r6 = r5.getKey()
            java.lang.Object r6 = r3.get(r6)
            java.util.List r6 = (java.util.List) r6
            if (r6 != 0) goto L_0x00b0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x00b0:
            java.lang.Object r5 = r5.getValue()
            java.util.Collection r5 = (java.util.Collection) r5
            r6.addAll(r5)
            goto L_0x0076
        L_0x00ba:
            int r1 = r1 + 1
            goto L_0x002d
        L_0x00be:
            com.xiaomi.smarthome.device.SmartHomeDeviceHelper r1 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
            com.xiaomi.smarthome.device.utils.DeviceTagInterface r1 = r1.b()
            java.lang.String r7 = r7.j()
            java.util.Map r3 = r1.j(r7)
        L_0x00ce:
            if (r3 == 0) goto L_0x00f3
            boolean r7 = r3.isEmpty()
            if (r7 == 0) goto L_0x00d7
            goto L_0x00f3
        L_0x00d7:
            java.lang.String r7 = r8.d
            java.lang.Object r7 = r3.get(r7)
            java.util.List r7 = (java.util.List) r7
            if (r7 == 0) goto L_0x00f2
            boolean r8 = r7.isEmpty()
            if (r8 == 0) goto L_0x00e8
            goto L_0x00f2
        L_0x00e8:
            java.util.HashSet r8 = new java.util.HashSet
            r8.<init>(r7)
            java.util.List r7 = a((java.util.Set<java.lang.String>) r8, (java.util.List<com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene>) r9)
            return r7
        L_0x00f2:
            return r0
        L_0x00f3:
            return r0
        L_0x00f4:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.SceneFilterHelper.a(com.xiaomi.smarthome.homeroom.model.Home, com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category, java.util.List):java.util.List");
    }

    public static List<SceneApi.SmartHomeScene> a(Set<String> set, List<SceneApi.SmartHomeScene> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            List<String> list2 = list.get(i).w;
            list2.addAll(a(list.get(i), 0));
            int i2 = 0;
            while (true) {
                if (i2 >= (list2 != null ? list2.size() : 0)) {
                    break;
                } else if (set.contains(list2.get(i2))) {
                    arrayList.add(list.get(i));
                    break;
                } else {
                    i2++;
                }
            }
        }
        return arrayList;
    }

    public static int a(int i, Home home, Object obj, List<SceneApi.SmartHomeScene> list) {
        switch (i) {
            case 0:
                return a(home, list).size();
            case 1:
                return a(home, (Room) obj, list).size();
            case 2:
                return a(home, (DeviceTagInterface.Category) obj, list).size();
            default:
                return 0;
        }
    }

    private static Set<String> a(SceneApi.SmartHomeScene smartHomeScene, int i) {
        HashSet hashSet = new HashSet();
        if (!(smartHomeScene == null || smartHomeScene.k == null || smartHomeScene.k.size() <= 0)) {
            for (SceneApi.Action next : smartHomeScene.k) {
                if (next.g != null) {
                    SceneApi.SmartHomeScene smartHomeScene2 = null;
                    if (next.g instanceof SceneApi.SHSceneAutoPayload) {
                        smartHomeScene2 = SceneManager.x().e(((SceneApi.SHSceneAutoPayload) next.g).f21530a);
                    } else if (next.g instanceof SceneApi.SHLiteScenePayload) {
                        smartHomeScene2 = SceneManager.x().e(((SceneApi.SHLiteScenePayload) next.g).f21529a);
                    }
                    if (!(smartHomeScene2 == null || smartHomeScene2.w == null || smartHomeScene2.w.size() <= 0)) {
                        hashSet.addAll(smartHomeScene2.w);
                        if (i < 3) {
                            i++;
                            hashSet.addAll(a(smartHomeScene2, i));
                        }
                    }
                }
            }
        }
        return hashSet;
    }
}
