package com.xiaomi.metoknlp.geofencing;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.xiaomi.metoknlp.geofencing.IGeoFencing;
import java.util.ArrayList;
import java.util.List;

public class GeoFencingServiceWrapper {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11128a = "GeoFencingServiceWrapper";
    private static final String d = "com.xiaomi.metoknlp.GeoFencingService";
    private static final String e = "com.xiaomi.metoknlp";
    private static final int h = 10;
    private static final int i = 1;
    private static final int j = 2;
    /* access modifiers changed from: private */
    public Context b;
    /* access modifiers changed from: private */
    public IGeoFencing c;
    /* access modifiers changed from: private */
    public boolean f = false;
    /* access modifiers changed from: private */
    public int g = 0;
    private List<PendingFence> k = new ArrayList();
    /* access modifiers changed from: private */
    public Handler l;
    private final ServiceConnection m = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.v(GeoFencingServiceWrapper.f11128a, "*** GeoFencingService connected ***");
            IGeoFencing unused = GeoFencingServiceWrapper.this.c = IGeoFencing.Stub.asInterface(iBinder);
            if (GeoFencingServiceWrapper.this.l != null) {
                GeoFencingServiceWrapper.this.l.sendEmptyMessageDelayed(2, 60000);
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            Log.v(GeoFencingServiceWrapper.f11128a, "*** GeoFencingService disconnected ***");
            IGeoFencing unused = GeoFencingServiceWrapper.this.c = null;
        }
    };

    static /* synthetic */ int a(GeoFencingServiceWrapper geoFencingServiceWrapper) {
        int i2 = geoFencingServiceWrapper.g;
        geoFencingServiceWrapper.g = i2 + 1;
        return i2;
    }

    public GeoFencingServiceWrapper(Context context) {
        this.b = context;
        this.f = false;
        c(context);
        HandlerThread handlerThread = new HandlerThread(f11128a);
        handlerThread.start();
        this.l = new BinderServiceHandler(handlerThread.getLooper());
        if (!this.f) {
            this.l.sendEmptyMessageDelayed(1, 10000);
        }
    }

    private class BinderServiceHandler extends Handler {
        public BinderServiceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    GeoFencingServiceWrapper.a(GeoFencingServiceWrapper.this);
                    GeoFencingServiceWrapper.this.c(GeoFencingServiceWrapper.this.b);
                    Log.w(GeoFencingServiceWrapper.f11128a, "Try bindService count=" + GeoFencingServiceWrapper.this.g + ",mBinded=" + GeoFencingServiceWrapper.this.f);
                    if (!GeoFencingServiceWrapper.this.f && GeoFencingServiceWrapper.this.l != null && GeoFencingServiceWrapper.this.g < 10) {
                        GeoFencingServiceWrapper.this.l.sendEmptyMessageDelayed(1, 10000);
                        return;
                    }
                    return;
                case 2:
                    GeoFencingServiceWrapper.this.a();
                    return;
                default:
                    Log.w(GeoFencingServiceWrapper.f11128a, "unknown message type ");
                    return;
            }
        }
    }

    private class PendingFence {

        /* renamed from: a  reason: collision with root package name */
        public double f11131a;
        public double b;
        public float c;
        public long d;
        public String e;
        public String f;
        public String g;

        public PendingFence(double d2, double d3, float f2, long j, String str, String str2, String str3) {
            this.f11131a = d2;
            this.b = d3;
            this.c = f2;
            this.d = j;
            this.e = str;
            this.f = str2;
            this.g = str3;
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        Log.d(f11128a, "registerPendingFence");
        for (PendingFence next : this.k) {
            if (!(next == null || this.c == null)) {
                try {
                    this.c.registerFenceListener(next.f11131a, next.b, next.c, next.d, next.e, next.f, next.g);
                } catch (RemoteException e2) {
                    Log.w(f11128a, "registerPendingFence:" + e2);
                }
            }
        }
    }

    public void a(Context context, List<String> list, String str) {
        c(context);
        if (this.c != null) {
            try {
                this.c.setLocationListener(list, str);
                Log.d(f11128a, "calling setLocationListener success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        }
    }

    public void a(Context context, String str) {
        c(context);
        if (this.c != null) {
            try {
                this.c.unsetLocationListener(str);
                Log.d(f11128a, "calling unsetLocationListener success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        }
    }

    public String a(Context context) {
        c(context);
        if (this.c == null) {
            return null;
        }
        try {
            String deviceLocation = this.c.getDeviceLocation();
            Log.d(f11128a, "calling getDeviceLocation success");
            return deviceLocation;
        } catch (RemoteException e2) {
            throw new RuntimeException("GeoFencingService has died", e2);
        }
    }

    public List<String> b(Context context, String str) {
        List<String> arrayList = new ArrayList<>();
        c(context);
        if (this.c != null) {
            try {
                arrayList = this.c.getLocationSsids(str);
                Log.d(f11128a, "calling getLocationSsids success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        }
        return arrayList;
    }

    public int b(Context context) {
        c(context);
        if (this.c == null) {
            return -1;
        }
        try {
            int version = this.c.getVersion();
            Log.d(f11128a, "calling getVersion success");
            return version;
        } catch (RemoteException e2) {
            throw new RuntimeException("GeoFencingService has died", e2);
        }
    }

    public void a(Context context, double d2, double d3, float f2, long j2, String str, String str2, String str3) {
        c(context);
        if (this.c != null) {
            try {
                this.c.registerFenceListener(d2, d3, f2, j2, str, str2, str3);
                Log.d(f11128a, "calling registerFenceListener success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        } else {
            Log.d(f11128a, "registerFenceListener service not ready, add to pending list.");
            this.k.add(new PendingFence(d2, d3, f2, j2, str, str2, str3));
        }
    }

    public void a(Context context, double d2, double d3, float f2, long j2, PendingIntent pendingIntent, String str, String str2) {
        c(context);
        if (this.c != null) {
            try {
                this.c.registerFenceListenerByIntent(d2, d3, f2, j2, pendingIntent, str, str2);
                Log.d(f11128a, "calling registerFenceListenerByIntent success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        }
    }

    public void a(Context context, String str, String str2) {
        c(context);
        if (this.c != null) {
            try {
                this.c.unregisterFenceListener(str, str2);
                Log.d(f11128a, "calling unregisterFenceListener success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        }
    }

    public void a(Context context, PendingIntent pendingIntent) {
        c(context);
        if (this.c != null) {
            try {
                this.c.unregisterFenceListenerByIntent(pendingIntent);
                Log.d(f11128a, "calling unregisterFenceListenerByIntent success");
            } catch (RemoteException e2) {
                throw new RuntimeException("GeoFencingService has died", e2);
            }
        }
    }

    public void c(Context context) {
        if (!this.f && context != null) {
            if (this.c == null) {
                Intent intent = new Intent(d);
                intent.setPackage(e);
                try {
                    if (!context.bindService(intent, this.m, 1)) {
                        Log.d(f11128a, "Can't bind GeoFencingService");
                        this.f = false;
                        return;
                    }
                    Log.d(f11128a, "GeoFencingService started");
                    this.f = true;
                } catch (SecurityException e2) {
                    Log.e(f11128a, "SecurityException:" + e2);
                }
            } else {
                Log.d(f11128a, "GeoFencingService already started");
            }
        }
    }

    public void d(Context context) {
        if (context != null && this.f) {
            context.unbindService(this.m);
            this.f = false;
        }
    }
}
