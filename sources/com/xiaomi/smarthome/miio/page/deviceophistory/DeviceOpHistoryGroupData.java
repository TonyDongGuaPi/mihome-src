package com.xiaomi.smarthome.miio.page.deviceophistory;

import com.mi.global.shop.model.Tags;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import org.json.JSONObject;

public class DeviceOpHistoryGroupData {

    /* renamed from: a  reason: collision with root package name */
    public int f19739a;
    public int b;
    public String c;
    public long d;
    public ArrayList<DeviceOpHistoryChildData> e = new ArrayList<>();

    public static ArrayList<DeviceOpHistoryGroupData> a() {
        ArrayList<DeviceOpHistoryGroupData> arrayList = new ArrayList<>();
        DeviceOpHistoryGroupData deviceOpHistoryGroupData = new DeviceOpHistoryGroupData();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        long j = 1479268104;
        gregorianCalendar.setTimeInMillis(1479268104);
        deviceOpHistoryGroupData.f19739a = gregorianCalendar.get(5);
        deviceOpHistoryGroupData.b = gregorianCalendar.get(2) + 1;
        deviceOpHistoryGroupData.c = a(gregorianCalendar.get(7));
        deviceOpHistoryGroupData.d = 1479268104;
        for (int i = 0; i < 10; i++) {
            deviceOpHistoryGroupData.e.add(DeviceOpHistoryChildData.a(j, "action" + i, "result 1", "source " + i));
            j -= 1000;
        }
        arrayList.add(deviceOpHistoryGroupData);
        return arrayList;
    }

    public static List<DeviceOpHistoryGroupData> a(JSONObject jSONObject) {
        JSONObject optJSONObject;
        ArrayList arrayList = new ArrayList();
        if (jSONObject.isNull(Tags.RepairOrder.SERVICE_LOG) || (optJSONObject = jSONObject.optJSONObject(Tags.RepairOrder.SERVICE_LOG)) == null) {
            return arrayList;
        }
        Iterator<String> keys = optJSONObject.keys();
        TreeMap treeMap = new TreeMap(new Comparator<Long>() {
            /* renamed from: a */
            public int compare(Long l, Long l2) {
                if (l.longValue() < l2.longValue()) {
                    return 1;
                }
                return l.longValue() > l2.longValue() ? -1 : 0;
            }
        });
        while (keys.hasNext()) {
            String next = keys.next();
            treeMap.put(Long.valueOf(Long.parseLong(next)), optJSONObject.optJSONObject(next));
        }
        DeviceOpHistoryGroupData deviceOpHistoryGroupData = null;
        for (Long longValue : treeMap.keySet()) {
            long longValue2 = longValue.longValue();
            JSONObject jSONObject2 = (JSONObject) treeMap.get(Long.valueOf(longValue2));
            if (jSONObject2 != null) {
                if (deviceOpHistoryGroupData == null) {
                    deviceOpHistoryGroupData = new DeviceOpHistoryGroupData();
                    deviceOpHistoryGroupData.a(longValue2);
                    deviceOpHistoryGroupData.e.add(DeviceOpHistoryChildData.a(longValue2, jSONObject2.optString("status"), "", jSONObject2.optString("trigger")));
                    arrayList.add(deviceOpHistoryGroupData);
                } else {
                    if (!CalendarUtils.a(new Date(longValue2), new Date(deviceOpHistoryGroupData.d))) {
                        deviceOpHistoryGroupData = new DeviceOpHistoryGroupData();
                        deviceOpHistoryGroupData.a(longValue2);
                        arrayList.add(deviceOpHistoryGroupData);
                    }
                    deviceOpHistoryGroupData.e.add(DeviceOpHistoryChildData.a(longValue2, jSONObject2.optString("status"), "", jSONObject2.optString("trigger")));
                }
            }
        }
        return arrayList;
    }

    private void a(long j) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(j);
        this.f19739a = gregorianCalendar.get(5);
        this.b = gregorianCalendar.get(2) + 1;
        this.c = a(gregorianCalendar.get(7));
        this.d = j;
    }

    public static String a(int i) {
        return SHApplication.getAppContext().getResources().getStringArray(R.array.weekday)[i - 1];
    }
}
