package com.xiaomi.smarthome.scene.geofence.manager;

import com.xiaomi.smarthome.scene.geofence.manager.model.GeoFenceItem;
import java.util.Map;

public interface GeoFenceInterface {
    GeoFenceItem a(String str);

    void a(Map<String, GeoFenceItem> map);

    boolean a();

    boolean a(String str, GeoFenceItem geoFenceItem);

    boolean b(String str);
}
