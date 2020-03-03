package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.Adapter;
import com.google.firebase.analytics.connector.internal.zzb;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzbof;
    private final AppMeasurement zzboe;
    @VisibleForTesting
    final Map<String, Adapter> zzbog = new ConcurrentHashMap();

    private AnalyticsConnectorImpl(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.zzboe = appMeasurement;
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @KeepForSdk
    public static AnalyticsConnector getInstance(Context context) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzbof == null) {
            synchronized (AnalyticsConnector.class) {
                if (zzbof == null) {
                    zzbof = new AnalyticsConnectorImpl(AppMeasurement.getInstance(context));
                }
            }
        }
        return zzbof;
    }

    @KeepForSdk
    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    /* access modifiers changed from: private */
    public final boolean zzfc(@NonNull String str) {
        return !str.isEmpty() && this.zzbog.containsKey(str) && this.zzbog.get(str) != null;
    }

    @KeepForSdk
    public void clearConditionalUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        if (str2 == null || zzb.zza(str2, bundle)) {
            this.zzboe.clearConditionalUserProperty(str, str2, bundle);
        } else {
            Log.d("FA-C", "Event or Params not allowed");
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x007e  */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.analytics.connector.internal.Adapter createAdapter(@android.support.annotation.NonNull java.lang.String r3, com.google.android.gms.measurement.AppMeasurement r4, com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener r5) {
        /*
            r2 = this;
            int r4 = r3.hashCode()
            r5 = 3308(0xcec, float:4.635E-42)
            if (r4 == r5) goto L_0x0045
            r5 = 101200(0x18b50, float:1.41811E-40)
            if (r4 == r5) goto L_0x003b
            r5 = 101230(0x18b6e, float:1.41853E-40)
            if (r4 == r5) goto L_0x0031
            r5 = 101655(0x18d17, float:1.42449E-40)
            if (r4 == r5) goto L_0x0027
            r5 = 94921639(0x5a863a7, float:1.583525E-35)
            if (r4 == r5) goto L_0x001d
            goto L_0x004f
        L_0x001d:
            java.lang.String r4 = "crash"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x004f
            r4 = 4
            goto L_0x0050
        L_0x0027:
            java.lang.String r4 = "frc"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x004f
            r4 = 2
            goto L_0x0050
        L_0x0031:
            java.lang.String r4 = "fdl"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x004f
            r4 = 1
            goto L_0x0050
        L_0x003b:
            java.lang.String r4 = "fcm"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x004f
            r4 = 0
            goto L_0x0050
        L_0x0045:
            java.lang.String r4 = "gs"
            boolean r4 = r3.equals(r4)
            if (r4 == 0) goto L_0x004f
            r4 = 3
            goto L_0x0050
        L_0x004f:
            r4 = -1
        L_0x0050:
            r5 = 0
            switch(r4) {
                case 0: goto L_0x007e;
                case 1: goto L_0x0079;
                case 2: goto L_0x0074;
                case 3: goto L_0x006f;
                case 4: goto L_0x0067;
                default: goto L_0x0054;
            }
        L_0x0054:
            java.lang.String r4 = "FA-C"
            java.lang.String r0 = "Adapter not defined for "
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r1 = r3.length()
            if (r1 == 0) goto L_0x0083
            java.lang.String r3 = r0.concat(r3)
            goto L_0x0088
        L_0x0067:
            java.lang.String r3 = "FA-C"
            java.lang.String r4 = "Crash Adapter not implemented"
        L_0x006b:
            android.util.Log.d(r3, r4)
            return r5
        L_0x006f:
            java.lang.String r3 = "FA-C"
            java.lang.String r4 = "Search Adapter not implemented"
            goto L_0x006b
        L_0x0074:
            java.lang.String r3 = "FA-C"
            java.lang.String r4 = "FRC Adapter not implemented"
            goto L_0x006b
        L_0x0079:
            java.lang.String r3 = "FA-C"
            java.lang.String r4 = "FDL Adapter not implemented"
            goto L_0x006b
        L_0x007e:
            java.lang.String r3 = "FA-C"
            java.lang.String r4 = "FCM Adapter not implemented"
            goto L_0x006b
        L_0x0083:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r0)
        L_0x0088:
            android.util.Log.d(r4, r3)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.analytics.connector.AnalyticsConnectorImpl.createAdapter(java.lang.String, com.google.android.gms.measurement.AppMeasurement, com.google.firebase.analytics.connector.AnalyticsConnector$AnalyticsConnectorListener):com.google.firebase.analytics.connector.internal.Adapter");
    }

    @WorkerThread
    @KeepForSdk
    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(@NonNull String str, @Nullable @Size(max = 23, min = 1) String str2) {
        ArrayList arrayList = new ArrayList();
        for (AppMeasurement.ConditionalUserProperty zzd : this.zzboe.getConditionalUserProperties(str, str2)) {
            arrayList.add(zzb.zzd(zzd));
        }
        return arrayList;
    }

    @WorkerThread
    @KeepForSdk
    public int getMaxUserProperties(@Size(min = 1) @NonNull String str) {
        return this.zzboe.getMaxUserProperties(str);
    }

    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzboe.getUserProperties(z);
    }

    @WorkerThread
    @KeepForSdk
    public void logEvent(@NonNull String str, @NonNull String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!zzb.zzfd(str)) {
            String valueOf = String.valueOf(str);
            Log.d("FA-C", valueOf.length() != 0 ? "Origin not allowed : ".concat(valueOf) : new String("Origin not allowed : "));
        } else if (!zzb.zza(str2, bundle)) {
            Log.d("FA-C", "Event or Params not allowed");
        } else if (!zzb.zzb(str, str2, bundle)) {
            Log.d("FA-C", "Campaign events not allowed");
        } else {
            this.zzboe.logEventInternal(str, str2, bundle);
        }
    }

    @WorkerThread
    @KeepForSdk
    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(@NonNull String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        String str2;
        String str3;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (!zzb.zzfd(str)) {
            str2 = "FA-C";
            str3 = "Registering with non allowed origin";
        } else if (zzfc(str)) {
            str2 = "FA-C";
            str3 = "Registering duplicate listener";
        } else {
            Adapter createAdapter = createAdapter(str, this.zzboe, analyticsConnectorListener);
            if (createAdapter == null) {
                return null;
            }
            this.zzbog.put(str, createAdapter);
            return new zza(this, str);
        }
        Log.d(str2, str3);
        return null;
    }

    @KeepForSdk
    public void setConditionalUserProperty(@NonNull AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        if (!zzb.zza(conditionalUserProperty)) {
            Log.d("FA-C", "Conditional user property has invalid event or params");
        } else {
            this.zzboe.setConditionalUserProperty(zzb.zzb(conditionalUserProperty));
        }
    }

    @KeepForSdk
    public void setUserProperty(@NonNull String str, @NonNull String str2, Object obj) {
        if (!zzb.zzfd(str)) {
            String valueOf = String.valueOf(str);
            Log.d("FA-C", valueOf.length() != 0 ? "Origin not allowed : ".concat(valueOf) : new String("Origin not allowed : "));
        } else if (!zzb.zzfe(str2)) {
            String valueOf2 = String.valueOf(str2);
            Log.d("FA-C", valueOf2.length() != 0 ? "User Property not allowed : ".concat(valueOf2) : new String("User Property not allowed : "));
        } else if ((str2.equals("_ce1") || str2.equals("_ce2")) && !str.equals(AppMeasurement.FCM_ORIGIN) && !str.equals("frc")) {
            String valueOf3 = String.valueOf(str2);
            Log.d("FA-C", valueOf3.length() != 0 ? "User Property not allowed for this origin: ".concat(valueOf3) : new String("User Property not allowed for this origin: "));
        } else {
            this.zzboe.setUserPropertyInternal(str, str2, obj);
        }
    }
}
