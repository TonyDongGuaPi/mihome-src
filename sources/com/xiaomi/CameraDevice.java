package com.xiaomi;

import com.mijia.model.local.LocalFileManager;
import com.mijia.model.property.CameraPropertyBase;
import com.mijia.model.property.PropertyManger;
import com.mijia.model.sdcard.DownloadSdCardManager;
import com.xiaomi.mistream.XmStreamClient;
import com.xiaomi.smarthome.camera.XmCameraP2p;
import com.xiaomi.smarthome.camera.XmP2PInfo;
import com.xiaomi.smarthome.device.api.BaseDevice;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import org.json.JSONArray;

public abstract class CameraDevice extends BaseDevice {
    public static final int b = 1;
    public static final int c = 5;
    public static final int d = 33;

    /* renamed from: a  reason: collision with root package name */
    private XmCameraP2p f9890a = null;
    protected String e;
    private XmStreamClient f = null;
    /* access modifiers changed from: private */
    public XmP2PInfo g = null;

    public abstract LocalFileManager b();

    public abstract CameraPropertyBase f();

    public abstract PropertyManger g();

    public abstract DownloadSdCardManager h();

    public abstract String p();

    public CameraDevice(DeviceStat deviceStat) {
        super(deviceStat);
    }

    public XmP2PInfo t() {
        return this.g;
    }

    public synchronized XmCameraP2p u() {
        if (this.f9890a == null) {
            this.f9890a = XmPluginHostApi.instance().createCameraP2p(t(), 1);
        }
        return this.f9890a;
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public synchronized com.xiaomi.mistream.XmStreamClient v() {
        /*
            r1 = this;
            monitor-enter(r1)
            com.xiaomi.mistream.XmStreamClient r0 = r1.f     // Catch:{ all -> 0x0019 }
            if (r0 != 0) goto L_0x0015
            monitor-enter(r1)     // Catch:{ all -> 0x0019 }
            com.xiaomi.smarthome.device.api.DeviceStat r0 = r1.deviceStat()     // Catch:{ all -> 0x0012 }
            com.xiaomi.mistream.XmStreamClient r0 = com.xiaomi.mistream.XmStreamClient.getInstance(r0)     // Catch:{ all -> 0x0012 }
            r1.f = r0     // Catch:{ all -> 0x0012 }
            monitor-exit(r1)     // Catch:{ all -> 0x0012 }
            goto L_0x0015
        L_0x0012:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0012 }
            throw r0     // Catch:{ all -> 0x0019 }
        L_0x0015:
            com.xiaomi.mistream.XmStreamClient r0 = r1.f     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return r0
        L_0x0019:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.CameraDevice.v():com.xiaomi.mistream.XmStreamClient");
    }

    public String r() {
        return this.e;
    }

    public String w() {
        return this.g != null ? this.g.getPwd() : "";
    }

    public void x() {
        this.f9890a = null;
    }

    public void f(final Callback<Void> callback) {
        XmPluginHostApi.instance().updateP2pPwd(deviceStat(), 1, new Callback<XmP2PInfo>() {
            /* renamed from: a */
            public void onSuccess(XmP2PInfo xmP2PInfo) {
                XmP2PInfo unused = CameraDevice.this.g = xmP2PInfo;
                CameraDevice.this.e = CameraDevice.this.g.getUid();
                if (callback != null) {
                    callback.onSuccess(null);
                }
            }

            public void onFailure(int i, String str) {
                if (callback != null) {
                    callback.onFailure(i, str);
                }
            }
        });
    }

    public void a(String str, Object obj, Callback<Object> callback) {
        JSONArray jSONArray = new JSONArray();
        if (obj != null) {
            jSONArray.put(obj);
        }
        callMethod(str, jSONArray, callback, (Parser) null);
    }

    public String getDid() {
        return this.mDeviceStat != null ? this.mDeviceStat.did : "";
    }

    public String y() {
        return this.mDeviceStat.ssid;
    }
}
