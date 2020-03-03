package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.mapsweeper;

import java.util.List;

public class MapPoint {

    /* renamed from: a  reason: collision with root package name */
    public int f17616a;
    public int b;
    public int c;
    private boolean d = false;

    public MapPoint() {
    }

    public MapPoint(int i, int i2, int i3) {
        this.f17616a = i;
        this.b = i2;
        this.c = i3;
    }

    public int a() {
        return this.f17616a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.f17616a + "##" + this.b;
    }

    public static MapPoint a(MapPoint mapPoint, float f) {
        if (mapPoint != null && !mapPoint.d && f > 0.0f && f != 1.0f) {
            mapPoint.f17616a = (int) (((float) mapPoint.f17616a) * f);
            mapPoint.b = (int) (((float) mapPoint.b) * f);
            mapPoint.d = true;
        }
        return mapPoint;
    }

    public static List<MapPoint> a(List<MapPoint> list, float f) {
        if (list == null || list.size() == 0) {
            return list;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a(list.get(i), f);
        }
        return list;
    }
}
