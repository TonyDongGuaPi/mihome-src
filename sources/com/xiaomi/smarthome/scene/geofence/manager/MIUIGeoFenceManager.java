package com.xiaomi.smarthome.scene.geofence.manager;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.text.TextUtils;
import com.qti.location.sdk.IZatGeofenceService;
import com.qti.location.sdk.IZatManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.scene.geofence.manager.model.GeoFenceItem;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;

public class MIUIGeoFenceManager implements GeoFenceInterface {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21543a = "MIUIGeoFenceManager";
    private static volatile MIUIGeoFenceManager b;
    private IZatGeofenceService c;
    private Map<String, GeoFenceItem> d = new ConcurrentHashMap();
    private Map<Object, Object> e = new ConcurrentHashMap();
    private Map<Object, Object> f = new ConcurrentHashMap();
    private AtomicBoolean g = new AtomicBoolean(false);
    private HashSet<String> h = new HashSet<>();

    private MIUIGeoFenceManager() {
        a(false);
    }

    public static MIUIGeoFenceManager b() {
        if (b == null) {
            synchronized (MIUIGeoFenceManager.class) {
                if (b == null) {
                    b = new MIUIGeoFenceManager();
                }
            }
        }
        return b;
    }

    public void c() {
        if (a(false) != null && !this.g.getAndSet(true)) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                MIUIGeoFenceConfigHelper.b();
                j();
                k();
                h();
            } catch (Throwable th) {
                th.printStackTrace();
                GrayLogDelegate.a(f21543a, "IZatManager connectGeofenceService exception:" + th.getMessage());
            }
            this.g.set(true);
            GrayLogDelegate.a(f21543a, "MIUIGeoFenceManager init cost " + (System.currentTimeMillis() - currentTimeMillis));
        }
    }

    private void h() {
        if (a()) {
            try {
                Map<IZatGeofenceService.IZatGeofenceHandle, IZatGeofenceService.IzatGeofence> a2 = this.c.a();
                StringBuilder sb = new StringBuilder();
                sb.append("recoverGeofenceFromMIUI size=");
                sb.append(a2 == null ? null : Integer.valueOf(a2.size()));
                GrayLogDelegate.a(f21543a, sb.toString());
                if (a2 != null) {
                    if (!a2.isEmpty()) {
                        this.f = new ConcurrentHashMap(a2);
                        Set<Object> keySet = this.f.keySet();
                        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                        Iterator<Object> it = keySet.iterator();
                        while (it.hasNext()) {
                            IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) it.next();
                            Object a3 = iZatGeofenceHandle.a();
                            if (a3 != null) {
                                if (a3 instanceof String) {
                                    String str = (String) a3;
                                    if (!TextUtils.isEmpty(str)) {
                                        GrayLogDelegate.a(f21543a, "recoverGeofences:" + str);
                                        if (!MIUIGeoFenceHelper.a(str)) {
                                            GrayLogDelegate.a(f21543a, "recoverGeofences is not mihome:" + str);
                                        } else {
                                            concurrentHashMap.put(str, iZatGeofenceHandle);
                                        }
                                    }
                                }
                            }
                        }
                        GrayLogDelegate.a(f21543a, "recoverGeofenceFromMIUI mihome's size:" + concurrentHashMap.size());
                        this.e = concurrentHashMap;
                        return;
                    }
                }
                this.f.clear();
                this.h.clear();
            } catch (Throwable th) {
                th.printStackTrace();
                GrayLogDelegate.a(f21543a, "recoverGeofence exception:" + th.getMessage());
            }
        }
    }

    private void i() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!this.g.get()) {
                c();
            }
            GrayLogDelegate.a(f21543a, "expunge mGeoFenceMap:" + this.d.size() + ",mHandleMapOnlyMihome:" + this.e.size() + ",mMIUIStoredGeoFence:" + this.f.size());
            for (String next : this.d.keySet()) {
                if (this.e.containsKey(next)) {
                    IZatGeofenceService.IzatGeofence a2 = MIUIGeoFenceHelper.a(this.d.get(next));
                    if (a2 == null) {
                        GrayLogDelegate.a(f21543a, "update fence exception");
                    } else if (a((IZatGeofenceService.IZatGeofenceHandle) this.e.get(next), a2)) {
                        IZatGeofenceService.IzatGeofence izatGeofence = (IZatGeofenceService.IzatGeofence) this.f.get(this.e.get(next));
                        ((IZatGeofenceService.IZatGeofenceHandle) this.e.get(next)).a(a2);
                        if (this.d.get(next) != null) {
                            c(next);
                        }
                        if (izatGeofence != null) {
                            GrayLogDelegate.a(f21543a, "update GeoFence:lat=" + izatGeofence.c() + ",lng=" + izatGeofence.d() + ",radius=" + izatGeofence.e() + ",id=" + next);
                        } else {
                            GrayLogDelegate.a(f21543a, "update GeoFence:lat=null");
                        }
                    }
                } else {
                    hashMap.put(next, this.d.get(next));
                }
            }
            GrayLogDelegate.a(f21543a, "expunge toBeAdded:" + hashMap.size());
            for (Object next2 : this.e.keySet()) {
                if (!this.d.containsKey(next2)) {
                    hashMap2.put(String.valueOf(next2), (IZatGeofenceService.IZatGeofenceHandle) this.e.get(next2));
                }
            }
            GrayLogDelegate.a(f21543a, "expunge toBeDeleted:" + hashMap2.size());
            for (Map.Entry entry : hashMap.entrySet()) {
                a((String) entry.getKey(), (GeoFenceItem) entry.getValue());
            }
            for (Map.Entry key : hashMap2.entrySet()) {
                b((String) key.getKey());
            }
            GrayLogDelegate.a(f21543a, "expunge costs:" + (System.currentTimeMillis() - currentTimeMillis));
        } catch (Throwable th) {
            th.printStackTrace();
            GrayLogDelegate.a(f21543a, "expunge exception:" + th.getMessage());
        }
    }

    public void d() {
        b = null;
        this.e.clear();
        try {
            if (this.c != null) {
                IZatManager.a(SHApplication.getAppContext()).a(this.c);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            GrayLogDelegate.a(f21543a, "IZatManager disconnectGeofenceService exception:" + th.getMessage());
        }
    }

    public boolean a() {
        boolean z;
        if (!SystemApi.c() || ServerCompact.e(SHApplication.getAppContext())) {
            return false;
        }
        boolean a2 = MIUIGeoFenceConfigHelper.a();
        LogUtilGrey.a(f21543a, "issupprotMIUIGeoFenceConfigHelper=" + a2);
        if (!a2) {
            return false;
        }
        try {
            z = IZatManager.a();
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtilGrey.a(f21543a, "connectGeofenceService exception " + th.getMessage() + ",Build.DEVICE=" + Build.DEVICE);
            z = false;
        }
        LogUtilGrey.a(f21543a, "issupprotIZatManager=" + z);
        if (!z) {
            return false;
        }
        this.c = a(false);
        LogUtilGrey.a(f21543a, "mZatGFService=" + this.c);
        if (this.c != null) {
            return true;
        }
        return false;
    }

    public void a(Map<String, GeoFenceItem> map) {
        if (a(false) != null) {
            if (map == null) {
                map = new ConcurrentHashMap<>();
            }
            for (String next : map.keySet()) {
                if (this.d.containsKey(next)) {
                    map.get(next).a(this.d.get(next).f());
                }
            }
            this.d = map;
            i();
        }
    }

    public boolean a(String str, GeoFenceItem geoFenceItem) {
        if (a(false) == null || TextUtils.isEmpty(str) || geoFenceItem == null || !a()) {
            return false;
        }
        try {
            IZatGeofenceService.IzatGeofence a2 = MIUIGeoFenceHelper.a(geoFenceItem);
            if (a2 == null) {
                GrayLogDelegate.a(f21543a, "add GeoFence failed: cannot generate IzatGeofence");
                return false;
            } else if (!this.e.containsKey(str) || !a((IZatGeofenceService.IZatGeofenceHandle) this.e.get(str), a2)) {
                IZatGeofenceService.IZatGeofenceHandle a3 = this.c.a(str, a2);
                c(str);
                this.d.put(str, geoFenceItem);
                this.e.put(str, a3);
                this.f.put(a3, a2);
                GrayLogDelegate.a(f21543a, "IZatManager add Geofence success:lat=" + geoFenceItem.b() + ",lng=" + geoFenceItem.c() + ",radius=" + geoFenceItem.d() + ",id=" + str);
                return true;
            } else {
                ((IZatGeofenceService.IZatGeofenceHandle) this.e.get(str)).a(a2);
                if (this.d.get(str) != null) {
                    c(str);
                }
                GrayLogDelegate.a(f21543a, "update GeoFence:lat=" + geoFenceItem.b() + ",lng=" + geoFenceItem.c() + ",radius=" + geoFenceItem.d() + ",id=" + str);
                return false;
            }
        } catch (Throwable th) {
            th.printStackTrace();
            GrayLogDelegate.a(f21543a, "IZatManager add Geofence exception:" + th.getMessage());
            return false;
        }
    }

    public GeoFenceItem a(String str) {
        if (TextUtils.isEmpty(str) || !a()) {
            return null;
        }
        return this.d.get(str);
    }

    public boolean b(String str) {
        IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle;
        if (a(false) == null || (iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) this.e.get(str)) == null) {
            return false;
        }
        try {
            this.c.a(iZatGeofenceHandle);
            this.e.remove(str);
            this.d.remove(str);
            this.f.remove(iZatGeofenceHandle);
            GrayLogDelegate.a(f21543a, "IZatManager removeGeoFence id=" + str);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            GrayLogDelegate.a(f21543a, "IZatManager removeGeoFence exception:" + th.getMessage());
            return false;
        }
    }

    private void j() {
        Intent intent = new Intent(SHApplication.getAppContext(), MIUIGeoFenceBroadcastReceiver.class);
        intent.setAction(MIUIGeoFenceBroadcastReceiver.GEOFENCE_ACTION);
        this.c.a(PendingIntent.getBroadcast(SHApplication.getAppContext(), 0, intent, 134217728));
        this.c.a((IZatGeofenceService.IZatGeofenceCallback) new IZatGeofenceService.IZatGeofenceCallback() {
            public void a(IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle, int i, Location location) {
                String str = i == 1 ? "GEOFENCE_EVENT_ENTERED" : i == 2 ? "GEOFENCE_EVENT_EXITED" : i == 4 ? "GEOFENCE_EVENT_UNCERTAIN" : null;
                if (str == null) {
                    str = "未知类型：" + i;
                }
                GrayLogDelegate.a(MIUIGeoFenceManager.f21543a, "MIUIGeoFenceManager mZatGFService onTransitionEvent event= " + str);
            }

            public void a(IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle, int i, int i2) {
                String str;
                String str2;
                switch (i) {
                    case 1:
                        str = "GEOFENCE_REQUEST_TYPE_ADD";
                        break;
                    case 2:
                        str = "GEOFENCE_REQUEST_TYPE_REMOVE";
                        break;
                    case 3:
                        str = "GEOFENCE_REQUEST_TYPE_PAUSE";
                        break;
                    case 4:
                        str = "GEOFENCE_REQUEST_TYPE_RESUME";
                        break;
                    case 5:
                        str = "GEOFENCE_REQUEST_TYPE_UPDATE";
                        break;
                    default:
                        str = "未知请求类型requestType: " + i;
                        break;
                }
                if (i == -149) {
                    str2 = "GEOFENCE_RESULT_ERROR_GENERIC";
                } else if (i != -100) {
                    switch (i) {
                        case -103:
                            str2 = "GEOFENCE_RESULT_ERROR_INVALID_TRANSITION";
                            break;
                        case -102:
                            str2 = "GEOFENCE_RESULT_ERROR_ID_UNKNOWN";
                            break;
                        default:
                            str2 = "未知错误类型rerrorCode: " + i2;
                            break;
                    }
                } else {
                    str2 = "GEOFENCE_RESULT_ERROR_TOO_MANY_GEOFENCES";
                }
                GrayLogDelegate.a(MIUIGeoFenceManager.f21543a, "MIUIGeoFenceManager mZatGFService onRequestFailed requestType=" + str + ",errorCode=" + str2);
            }

            public void a(int i, Location location) {
                String str;
                switch (i) {
                    case 1:
                        str = "GEOFENCE_GEN_ALERT_GNSS_UNAVAILABLE";
                        break;
                    case 2:
                        str = "GEOFENCE_GEN_ALERT_GNSS_AVAILABLE";
                        break;
                    case 3:
                        str = "GEOFENCE_GEN_ALERT_OOS";
                        break;
                    case 4:
                        str = "GEOFENCE_GEN_ALERT_TIME_INVALID";
                        break;
                    default:
                        str = "未知状态类型status: " + i;
                        break;
                }
                GrayLogDelegate.a(MIUIGeoFenceManager.f21543a, "MIUIGeoFenceManager mZatGFService onEngineReportStatus status=" + str);
            }
        });
        GrayLogDelegate.a(f21543a, "MIUIGeoFenceManager startWork PendingIntent registered");
    }

    public Map<String, GeoFenceItem> e() {
        return this.d;
    }

    public Map<Object, Object> f() {
        return this.e;
    }

    public Map<Object, Object> g() {
        return this.f;
    }

    private boolean a(IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle, IZatGeofenceService.IzatGeofence izatGeofence) {
        IZatGeofenceService.IzatGeofence izatGeofence2 = (IZatGeofenceService.IzatGeofence) this.f.get(iZatGeofenceHandle);
        if (izatGeofence2 != null && izatGeofence2.c() == izatGeofence.c() && izatGeofence2.d() == izatGeofence.d() && izatGeofence2.e() == izatGeofence.e() && izatGeofence2.f() == izatGeofence.f()) {
            return false;
        }
        return true;
    }

    private IZatGeofenceService a(boolean z) {
        if (this.c != null) {
            return this.c;
        }
        try {
            this.c = IZatManager.a(SHApplication.getAppContext()).e();
            return this.c;
        } catch (Throwable th) {
            th.printStackTrace();
            LogUtilGrey.a(f21543a, "connectGeofenceService exception " + th.getMessage() + ",Build.DEVICE=" + Build.DEVICE);
            return null;
        }
    }

    public void c(String str) {
        this.h.add(str);
        SharePrefsManager.a(SHApplication.getAppContext(), "scene_sp", "geo_tag_array", new JSONArray(this.h).toString());
    }

    public boolean d(String str) {
        if (!this.h.remove(str)) {
            return false;
        }
        SharePrefsManager.a(SHApplication.getAppContext(), "scene_sp", "geo_tag_array", new JSONArray(this.h).toString());
        return true;
    }

    private Set<String> k() {
        HashSet hashSet = new HashSet();
        try {
            JSONArray jSONArray = new JSONArray(SharePrefsManager.c(SHApplication.getAppContext(), "scene_sp", "geo_tag_array", ""));
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.optString(i));
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.h = new HashSet<>(hashSet);
        return this.h;
    }
}
