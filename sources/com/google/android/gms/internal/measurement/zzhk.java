package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzhk extends zzhh {
    @VisibleForTesting
    protected zzid zzanp;
    private AppMeasurement.EventInterceptor zzanq;
    private final Set<AppMeasurement.OnEventListener> zzanr = new CopyOnWriteArraySet();
    private boolean zzans;
    private final AtomicReference<String> zzant = new AtomicReference<>();
    @VisibleForTesting
    protected boolean zzanu = true;

    protected zzhk(zzgl zzgl) {
        super(zzgl);
    }

    private final void zza(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty.mValue);
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = conditionalUserProperty.mName;
        Object obj = conditionalUserProperty.mValue;
        if (zzgb().zzcf(str) != 0) {
            zzge().zzim().zzg("Invalid conditional user property name", zzga().zzbl(str));
        } else if (zzgb().zzi(str, obj) != 0) {
            zzge().zzim().zze("Invalid conditional user property value", zzga().zzbl(str), obj);
        } else {
            Object zzj = zzgb().zzj(str, obj);
            if (zzj == null) {
                zzge().zzim().zze("Unable to normalize conditional user property value", zzga().zzbl(str), obj);
                return;
            }
            conditionalUserProperty.mValue = zzj;
            long j = conditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(conditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                long j2 = conditionalUserProperty.mTimeToLive;
                if (j2 > 15552000000L || j2 < 1) {
                    zzge().zzim().zze("Invalid conditional user property time to live", zzga().zzbl(str), Long.valueOf(j2));
                } else {
                    zzgd().zzc((Runnable) new zzhr(this, conditionalUserProperty));
                }
            } else {
                zzge().zzim().zze("Invalid conditional user property timeout", zzga().zzbl(str), Long.valueOf(j));
            }
        }
    }

    private final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        Bundle bundle3 = bundle;
        if (bundle3 == null) {
            bundle2 = new Bundle();
        } else {
            Bundle bundle4 = new Bundle(bundle3);
            for (String str4 : bundle4.keySet()) {
                Object obj = bundle4.get(str4);
                if (obj instanceof Bundle) {
                    bundle4.putBundle(str4, new Bundle((Bundle) obj));
                } else {
                    int i = 0;
                    if (obj instanceof Parcelable[]) {
                        Parcelable[] parcelableArr = (Parcelable[]) obj;
                        while (i < parcelableArr.length) {
                            if (parcelableArr[i] instanceof Bundle) {
                                parcelableArr[i] = new Bundle((Bundle) parcelableArr[i]);
                            }
                            i++;
                        }
                    } else if (obj instanceof ArrayList) {
                        ArrayList arrayList = (ArrayList) obj;
                        while (i < arrayList.size()) {
                            Object obj2 = arrayList.get(i);
                            if (obj2 instanceof Bundle) {
                                arrayList.set(i, new Bundle((Bundle) obj2));
                            }
                            i++;
                        }
                    }
                }
            }
            bundle2 = bundle4;
        }
        zzgd().zzc((Runnable) new zzic(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzgd().zzc((Runnable) new zzhm(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zzbt().currentTimeMillis(), bundle, true, z2, z3, (String) null);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zza(String str, String str2, Object obj, long j) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        if (!this.zzacw.isEnabled()) {
            zzge().zzis().log("User property not set since app measurement is disabled");
        } else if (this.zzacw.zzjv()) {
            zzge().zzis().zze("Setting user property (FE)", zzga().zzbj(str2), obj);
            zzfx().zzb(new zzjx(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzbt().currentTimeMillis();
        Preconditions.checkNotEmpty(str2);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
        conditionalUserProperty.mAppId = str;
        conditionalUserProperty.mName = str2;
        conditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            conditionalUserProperty.mExpiredEventName = str3;
            conditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzgd().zzc((Runnable) new zzhs(this, conditionalUserProperty));
    }

    @VisibleForTesting
    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        zzfi zzip;
        String str4;
        if (zzgd().zzjk()) {
            zzip = zzge().zzim();
            str4 = "Cannot get user properties from analytics worker thread";
        } else {
            zzgd();
            if (zzgg.isMainThread()) {
                zzip = zzge().zzim();
                str4 = "Cannot get user properties from main thread";
            } else {
                AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzacw.zzgd().zzc((Runnable) new zzhu(this, atomicReference, str, str2, str3, z));
                    try {
                        atomicReference.wait(5000);
                    } catch (InterruptedException e) {
                        zzge().zzip().zzg("Interrupted waiting for get user properties", e);
                    }
                }
                List<zzjx> list = (List) atomicReference.get();
                if (list == null) {
                    zzip = zzge().zzip();
                    str4 = "Timed out waiting for get user properties";
                } else {
                    ArrayMap arrayMap = new ArrayMap(list.size());
                    for (zzjx zzjx : list) {
                        arrayMap.put(zzjx.name, zzjx.getValue());
                    }
                    return arrayMap;
                }
            }
        }
        zzip.log(str4);
        return Collections.emptyMap();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzb(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mName);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mOrigin);
        Preconditions.checkNotNull(conditionalUserProperty2.mValue);
        if (!this.zzacw.isEnabled()) {
            zzge().zzis().log("Conditional property not sent since Firebase Analytics is disabled");
            return;
        }
        zzjx zzjx = new zzjx(conditionalUserProperty2.mName, conditionalUserProperty2.mTriggeredTimestamp, conditionalUserProperty2.mValue, conditionalUserProperty2.mOrigin);
        try {
            zzeu zza = zzgb().zza(conditionalUserProperty2.mTriggeredEventName, conditionalUserProperty2.mTriggeredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzeu zza2 = zzgb().zza(conditionalUserProperty2.mTimedOutEventName, conditionalUserProperty2.mTimedOutEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            zzeu zza3 = zzgb().zza(conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, 0, true, false);
            String str = conditionalUserProperty2.mAppId;
            String str2 = conditionalUserProperty2.mOrigin;
            long j = conditionalUserProperty2.mCreationTimestamp;
            String str3 = conditionalUserProperty2.mTriggerEventName;
            long j2 = conditionalUserProperty2.mTriggerTimeout;
            zzed zzed = r3;
            zzed zzed2 = new zzed(str, str2, zzjx, j, false, str3, zza2, j2, zza, conditionalUserProperty2.mTimeToLive, zza3);
            zzfx().zzd(zzed);
        } catch (IllegalArgumentException unused) {
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a6  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r28, java.lang.String r29, long r30, android.os.Bundle r32, boolean r33, boolean r34, boolean r35, java.lang.String r36) {
        /*
            r27 = this;
            r1 = r27
            r8 = r28
            r9 = r29
            r10 = r32
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r28)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r29)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r32)
            r27.zzab()
            r27.zzch()
            com.google.android.gms.internal.measurement.zzgl r0 = r1.zzacw
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto L_0x002d
            com.google.android.gms.internal.measurement.zzfg r0 = r27.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzis()
            java.lang.String r2 = "Event not sent since app measurement is disabled"
            r0.log(r2)
            return
        L_0x002d:
            boolean r0 = r1.zzans
            r12 = 0
            r13 = 0
            r14 = 1
            if (r0 != 0) goto L_0x0070
            r1.zzans = r14
            java.lang.String r0 = "com.google.android.gms.tagmanager.TagManagerService"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x0063 }
            java.lang.String r3 = "initialize"
            java.lang.Class[] r4 = new java.lang.Class[r14]     // Catch:{ Exception -> 0x0054 }
            java.lang.Class<android.content.Context> r5 = android.content.Context.class
            r4[r13] = r5     // Catch:{ Exception -> 0x0054 }
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r3, r4)     // Catch:{ Exception -> 0x0054 }
            java.lang.Object[] r3 = new java.lang.Object[r14]     // Catch:{ Exception -> 0x0054 }
            android.content.Context r4 = r27.getContext()     // Catch:{ Exception -> 0x0054 }
            r3[r13] = r4     // Catch:{ Exception -> 0x0054 }
            r0.invoke(r12, r3)     // Catch:{ Exception -> 0x0054 }
            goto L_0x0070
        L_0x0054:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r3 = r27.zzge()     // Catch:{ ClassNotFoundException -> 0x0063 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzip()     // Catch:{ ClassNotFoundException -> 0x0063 }
            java.lang.String r4 = "Failed to invoke Tag Manager's initialize() method"
            r3.zzg(r4, r0)     // Catch:{ ClassNotFoundException -> 0x0063 }
            goto L_0x0070
        L_0x0063:
            com.google.android.gms.internal.measurement.zzfg r0 = r27.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzir()
            java.lang.String r3 = "Tag Manager is not found and thus will not be used"
            r0.log(r3)
        L_0x0070:
            r0 = 40
            r3 = 2
            if (r35 == 0) goto L_0x00d6
            java.lang.String r4 = "_iap"
            boolean r4 = r4.equals(r9)
            if (r4 != 0) goto L_0x00d6
            com.google.android.gms.internal.measurement.zzgl r4 = r1.zzacw
            com.google.android.gms.internal.measurement.zzka r4 = r4.zzgb()
            java.lang.String r5 = "event"
            boolean r5 = r4.zzq(r5, r9)
            if (r5 != 0) goto L_0x008d
        L_0x008b:
            r4 = 2
            goto L_0x00a4
        L_0x008d:
            java.lang.String r5 = "event"
            java.lang.String[] r6 = com.google.android.gms.measurement.AppMeasurement.Event.zzacx
            boolean r5 = r4.zza((java.lang.String) r5, (java.lang.String[]) r6, (java.lang.String) r9)
            if (r5 != 0) goto L_0x009a
            r4 = 13
            goto L_0x00a4
        L_0x009a:
            java.lang.String r5 = "event"
            boolean r4 = r4.zza((java.lang.String) r5, (int) r0, (java.lang.String) r9)
            if (r4 != 0) goto L_0x00a3
            goto L_0x008b
        L_0x00a3:
            r4 = 0
        L_0x00a4:
            if (r4 == 0) goto L_0x00d6
            com.google.android.gms.internal.measurement.zzfg r2 = r27.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzio()
            java.lang.String r3 = "Invalid public event name. Event will not be logged (FE)"
            com.google.android.gms.internal.measurement.zzfe r5 = r27.zzga()
            java.lang.String r5 = r5.zzbj(r9)
            r2.zzg(r3, r5)
            com.google.android.gms.internal.measurement.zzgl r2 = r1.zzacw
            r2.zzgb()
            java.lang.String r0 = com.google.android.gms.internal.measurement.zzka.zza((java.lang.String) r9, (int) r0, (boolean) r14)
            if (r9 == 0) goto L_0x00ca
            int r13 = r29.length()
        L_0x00ca:
            com.google.android.gms.internal.measurement.zzgl r2 = r1.zzacw
            com.google.android.gms.internal.measurement.zzka r2 = r2.zzgb()
            java.lang.String r3 = "_ev"
            r2.zza(r4, r3, r0, r13)
            return
        L_0x00d6:
            com.google.android.gms.internal.measurement.zzif r4 = r27.zzfy()
            com.google.android.gms.internal.measurement.zzie r15 = r4.zzkc()
            if (r15 == 0) goto L_0x00ea
            java.lang.String r4 = "_sc"
            boolean r4 = r10.containsKey(r4)
            if (r4 != 0) goto L_0x00ea
            r15.zzaok = r14
        L_0x00ea:
            if (r33 == 0) goto L_0x00f0
            if (r35 == 0) goto L_0x00f0
            r4 = 1
            goto L_0x00f1
        L_0x00f0:
            r4 = 0
        L_0x00f1:
            com.google.android.gms.internal.measurement.zzif.zza((com.google.android.gms.internal.measurement.zzie) r15, (android.os.Bundle) r10, (boolean) r4)
            java.lang.String r4 = "am"
            boolean r16 = r4.equals(r8)
            boolean r4 = com.google.android.gms.internal.measurement.zzka.zzci(r29)
            if (r33 == 0) goto L_0x0133
            com.google.android.gms.measurement.AppMeasurement$EventInterceptor r2 = r1.zzanq
            if (r2 == 0) goto L_0x0133
            if (r4 != 0) goto L_0x0133
            if (r16 != 0) goto L_0x0133
            com.google.android.gms.internal.measurement.zzfg r0 = r27.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzis()
            java.lang.String r2 = "Passing event to registered event handler (FE)"
            com.google.android.gms.internal.measurement.zzfe r3 = r27.zzga()
            java.lang.String r3 = r3.zzbj(r9)
            com.google.android.gms.internal.measurement.zzfe r4 = r27.zzga()
            java.lang.String r4 = r4.zzb((android.os.Bundle) r10)
            r0.zze(r2, r3, r4)
            com.google.android.gms.measurement.AppMeasurement$EventInterceptor r2 = r1.zzanq
            r3 = r28
            r4 = r29
            r5 = r32
            r6 = r30
            r2.interceptEvent(r3, r4, r5, r6)
            return
        L_0x0133:
            com.google.android.gms.internal.measurement.zzgl r2 = r1.zzacw
            boolean r2 = r2.zzjv()
            if (r2 != 0) goto L_0x013c
            return
        L_0x013c:
            com.google.android.gms.internal.measurement.zzka r2 = r27.zzgb()
            int r2 = r2.zzcd(r9)
            if (r2 == 0) goto L_0x0180
            com.google.android.gms.internal.measurement.zzfg r3 = r27.zzge()
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzio()
            java.lang.String r4 = "Invalid event name. Event will not be logged (FE)"
            com.google.android.gms.internal.measurement.zzfe r5 = r27.zzga()
            java.lang.String r5 = r5.zzbj(r9)
            r3.zzg(r4, r5)
            r27.zzgb()
            java.lang.String r0 = com.google.android.gms.internal.measurement.zzka.zza((java.lang.String) r9, (int) r0, (boolean) r14)
            if (r9 == 0) goto L_0x0168
            int r13 = r29.length()
        L_0x0168:
            com.google.android.gms.internal.measurement.zzgl r3 = r1.zzacw
            com.google.android.gms.internal.measurement.zzka r3 = r3.zzgb()
            java.lang.String r4 = "_ev"
            r28 = r3
            r29 = r36
            r30 = r2
            r31 = r4
            r32 = r0
            r33 = r13
            r28.zza((java.lang.String) r29, (int) r30, (java.lang.String) r31, (java.lang.String) r32, (int) r33)
            return
        L_0x0180:
            r0 = 4
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r2 = "_o"
            r0[r13] = r2
            java.lang.String r2 = "_sn"
            r0[r14] = r2
            java.lang.String r2 = "_sc"
            r0[r3] = r2
            r2 = 3
            java.lang.String r3 = "_si"
            r0[r2] = r3
            java.util.List r0 = com.google.android.gms.common.util.CollectionUtils.listOf((T[]) r0)
            com.google.android.gms.internal.measurement.zzka r2 = r27.zzgb()
            r7 = 1
            r3 = r29
            r4 = r32
            r5 = r0
            r6 = r35
            android.os.Bundle r7 = r2.zza((java.lang.String) r3, (android.os.Bundle) r4, (java.util.List<java.lang.String>) r5, (boolean) r6, (boolean) r7)
            if (r7 == 0) goto L_0x01da
            java.lang.String r2 = "_sc"
            boolean r2 = r7.containsKey(r2)
            if (r2 == 0) goto L_0x01da
            java.lang.String r2 = "_si"
            boolean r2 = r7.containsKey(r2)
            if (r2 != 0) goto L_0x01bb
            goto L_0x01da
        L_0x01bb:
            java.lang.String r2 = "_sn"
            java.lang.String r2 = r7.getString(r2)
            java.lang.String r3 = "_sc"
            java.lang.String r3 = r7.getString(r3)
            java.lang.String r4 = "_si"
            long r4 = r7.getLong(r4)
            java.lang.Long r4 = java.lang.Long.valueOf(r4)
            com.google.android.gms.internal.measurement.zzie r12 = new com.google.android.gms.internal.measurement.zzie
            long r4 = r4.longValue()
            r12.<init>(r2, r3, r4)
        L_0x01da:
            if (r12 != 0) goto L_0x01dd
            r12 = r15
        L_0x01dd:
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r15.add(r7)
            com.google.android.gms.internal.measurement.zzka r2 = r27.zzgb()
            java.security.SecureRandom r2 = r2.zzlc()
            long r5 = r2.nextLong()
            java.util.Set r2 = r7.keySet()
            int r3 = r32.size()
            java.lang.String[] r3 = new java.lang.String[r3]
            java.lang.Object[] r2 = r2.toArray(r3)
            r10 = r2
            java.lang.String[] r10 = (java.lang.String[]) r10
            java.util.Arrays.sort(r10)
            int r4 = r10.length
            r2 = 0
            r3 = 0
        L_0x0208:
            if (r3 >= r4) goto L_0x02a3
            r13 = r10[r3]
            java.lang.Object r17 = r7.get(r13)
            r27.zzgb()
            android.os.Bundle[] r14 = com.google.android.gms.internal.measurement.zzka.zze(r17)
            if (r14 == 0) goto L_0x028e
            r18 = r2
            int r2 = r14.length
            r7.putInt(r13, r2)
            r19 = r3
            r2 = 0
        L_0x0222:
            int r3 = r14.length
            if (r2 >= r3) goto L_0x027f
            r3 = r14[r2]
            r20 = r2
            r2 = 1
            com.google.android.gms.internal.measurement.zzif.zza((com.google.android.gms.internal.measurement.zzie) r12, (android.os.Bundle) r3, (boolean) r2)
            com.google.android.gms.internal.measurement.zzka r2 = r27.zzgb()
            java.lang.String r17 = "_ep"
            r21 = 0
            r22 = r18
            r23 = r20
            r18 = r19
            r19 = r3
            r3 = r17
            r17 = r4
            r4 = r19
            r24 = r5
            r5 = r0
            r6 = r35
            r26 = r0
            r0 = r7
            r7 = r21
            android.os.Bundle r2 = r2.zza((java.lang.String) r3, (android.os.Bundle) r4, (java.util.List<java.lang.String>) r5, (boolean) r6, (boolean) r7)
            java.lang.String r3 = "_en"
            r2.putString(r3, r9)
            java.lang.String r3 = "_eid"
            r4 = r24
            r2.putLong(r3, r4)
            java.lang.String r3 = "_gn"
            r2.putString(r3, r13)
            java.lang.String r3 = "_ll"
            int r6 = r14.length
            r2.putInt(r3, r6)
            java.lang.String r3 = "_i"
            r6 = r23
            r2.putInt(r3, r6)
            r15.add(r2)
            int r2 = r6 + 1
            r7 = r0
            r5 = r4
            r4 = r17
            r19 = r18
            r18 = r22
            r0 = r26
            goto L_0x0222
        L_0x027f:
            r26 = r0
            r17 = r4
            r4 = r5
            r0 = r7
            r22 = r18
            r18 = r19
            int r2 = r14.length
            r13 = r22
            int r2 = r2 + r13
            goto L_0x0297
        L_0x028e:
            r26 = r0
            r13 = r2
            r18 = r3
            r17 = r4
            r4 = r5
            r0 = r7
        L_0x0297:
            int r3 = r18 + 1
            r7 = r0
            r5 = r4
            r4 = r17
            r0 = r26
            r13 = 0
            r14 = 1
            goto L_0x0208
        L_0x02a3:
            r13 = r2
            r4 = r5
            r0 = r7
            if (r13 == 0) goto L_0x02b2
            java.lang.String r2 = "_eid"
            r0.putLong(r2, r4)
            java.lang.String r2 = "_epc"
            r0.putInt(r2, r13)
        L_0x02b2:
            r0 = 0
        L_0x02b3:
            int r2 = r15.size()
            if (r0 >= r2) goto L_0x0336
            java.lang.Object r2 = r15.get(r0)
            android.os.Bundle r2 = (android.os.Bundle) r2
            if (r0 == 0) goto L_0x02c3
            r3 = 1
            goto L_0x02c4
        L_0x02c3:
            r3 = 0
        L_0x02c4:
            if (r3 == 0) goto L_0x02c9
            java.lang.String r3 = "_ep"
            goto L_0x02ca
        L_0x02c9:
            r3 = r9
        L_0x02ca:
            java.lang.String r4 = "_o"
            r2.putString(r4, r8)
            if (r34 == 0) goto L_0x02d9
            com.google.android.gms.internal.measurement.zzka r4 = r27.zzgb()
            android.os.Bundle r2 = r4.zzd((android.os.Bundle) r2)
        L_0x02d9:
            r11 = r2
            com.google.android.gms.internal.measurement.zzfg r2 = r27.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzis()
            java.lang.String r4 = "Logging event (FE)"
            com.google.android.gms.internal.measurement.zzfe r5 = r27.zzga()
            java.lang.String r5 = r5.zzbj(r9)
            com.google.android.gms.internal.measurement.zzfe r6 = r27.zzga()
            java.lang.String r6 = r6.zzb((android.os.Bundle) r11)
            r2.zze(r4, r5, r6)
            com.google.android.gms.internal.measurement.zzeu r12 = new com.google.android.gms.internal.measurement.zzeu
            com.google.android.gms.internal.measurement.zzer r4 = new com.google.android.gms.internal.measurement.zzer
            r4.<init>(r11)
            r2 = r12
            r5 = r28
            r6 = r30
            r2.<init>(r3, r4, r5, r6)
            com.google.android.gms.internal.measurement.zzii r2 = r27.zzfx()
            r13 = r36
            r2.zzb(r12, r13)
            if (r16 != 0) goto L_0x0332
            java.util.Set<com.google.android.gms.measurement.AppMeasurement$OnEventListener> r2 = r1.zzanr
            java.util.Iterator r12 = r2.iterator()
        L_0x0317:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x0332
            java.lang.Object r2 = r12.next()
            com.google.android.gms.measurement.AppMeasurement$OnEventListener r2 = (com.google.android.gms.measurement.AppMeasurement.OnEventListener) r2
            android.os.Bundle r5 = new android.os.Bundle
            r5.<init>(r11)
            r3 = r28
            r4 = r29
            r6 = r30
            r2.onEvent(r3, r4, r5, r6)
            goto L_0x0317
        L_0x0332:
            int r0 = r0 + 1
            goto L_0x02b3
        L_0x0336:
            com.google.android.gms.internal.measurement.zzif r0 = r27.zzfy()
            com.google.android.gms.internal.measurement.zzie r0 = r0.zzkc()
            if (r0 == 0) goto L_0x0350
            java.lang.String r0 = "_ae"
            boolean r0 = r0.equals(r9)
            if (r0 == 0) goto L_0x0350
            com.google.android.gms.internal.measurement.zzjh r0 = r27.zzgc()
            r2 = 1
            r0.zzl(r2)
        L_0x0350:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhk.zzb(java.lang.String, java.lang.String, long, android.os.Bundle, boolean, boolean, boolean, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzc(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = conditionalUserProperty;
        zzab();
        zzch();
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty2.mName);
        if (!this.zzacw.isEnabled()) {
            zzge().zzis().log("Conditional property not cleared since Firebase Analytics is disabled");
            return;
        }
        zzjx zzjx = new zzjx(conditionalUserProperty2.mName, 0, (Object) null, (String) null);
        try {
            zzeu zza = zzgb().zza(conditionalUserProperty2.mExpiredEventName, conditionalUserProperty2.mExpiredEventParams, conditionalUserProperty2.mOrigin, conditionalUserProperty2.mCreationTimestamp, true, false);
            zzed zzed = r3;
            zzed zzed2 = new zzed(conditionalUserProperty2.mAppId, conditionalUserProperty2.mOrigin, zzjx, conditionalUserProperty2.mCreationTimestamp, conditionalUserProperty2.mActive, conditionalUserProperty2.mTriggerEventName, (zzeu) null, conditionalUserProperty2.mTriggerTimeout, (zzeu) null, conditionalUserProperty2.mTimeToLive, zza);
            zzfx().zzd(zzed);
        } catch (IllegalArgumentException unused) {
        }
    }

    @VisibleForTesting
    private final List<AppMeasurement.ConditionalUserProperty> zzf(String str, String str2, String str3) {
        zzfi zzim;
        String str4;
        if (zzgd().zzjk()) {
            zzim = zzge().zzim();
            str4 = "Cannot get conditional user properties from analytics worker thread";
        } else {
            zzgd();
            if (zzgg.isMainThread()) {
                zzim = zzge().zzim();
                str4 = "Cannot get conditional user properties from main thread";
            } else {
                AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzacw.zzgd().zzc((Runnable) new zzht(this, atomicReference, str, str2, str3));
                    try {
                        atomicReference.wait(5000);
                    } catch (InterruptedException e) {
                        zzge().zzip().zze("Interrupted waiting for get conditional user properties", str, e);
                    }
                }
                List<zzed> list = (List) atomicReference.get();
                if (list == null) {
                    zzge().zzip().zzg("Timed out waiting for get conditional user properties", str);
                    return Collections.emptyList();
                }
                ArrayList arrayList = new ArrayList(list.size());
                for (zzed zzed : list) {
                    AppMeasurement.ConditionalUserProperty conditionalUserProperty = new AppMeasurement.ConditionalUserProperty();
                    conditionalUserProperty.mAppId = str;
                    conditionalUserProperty.mOrigin = str2;
                    conditionalUserProperty.mCreationTimestamp = zzed.creationTimestamp;
                    conditionalUserProperty.mName = zzed.zzaep.name;
                    conditionalUserProperty.mValue = zzed.zzaep.getValue();
                    conditionalUserProperty.mActive = zzed.active;
                    conditionalUserProperty.mTriggerEventName = zzed.triggerEventName;
                    if (zzed.zzaeq != null) {
                        conditionalUserProperty.mTimedOutEventName = zzed.zzaeq.name;
                        if (zzed.zzaeq.zzafq != null) {
                            conditionalUserProperty.mTimedOutEventParams = zzed.zzaeq.zzafq.zzif();
                        }
                    }
                    conditionalUserProperty.mTriggerTimeout = zzed.triggerTimeout;
                    if (zzed.zzaer != null) {
                        conditionalUserProperty.mTriggeredEventName = zzed.zzaer.name;
                        if (zzed.zzaer.zzafq != null) {
                            conditionalUserProperty.mTriggeredEventParams = zzed.zzaer.zzafq.zzif();
                        }
                    }
                    conditionalUserProperty.mTriggeredTimestamp = zzed.zzaep.zzaqz;
                    conditionalUserProperty.mTimeToLive = zzed.timeToLive;
                    if (zzed.zzaes != null) {
                        conditionalUserProperty.mExpiredEventName = zzed.zzaes.name;
                        if (zzed.zzaes.zzafq != null) {
                            conditionalUserProperty.mExpiredEventParams = zzed.zzaes.zzafq.zzif();
                        }
                    }
                    arrayList.add(conditionalUserProperty);
                }
                return arrayList;
            }
        }
        zzim.log(str4);
        return Collections.emptyList();
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzi(boolean z) {
        zzab();
        zzch();
        zzge().zzis().zzg("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzgf().setMeasurementEnabled(z);
        if (!zzgg().zzaz(zzfv().zzah())) {
            zzfx().zzke();
        } else if (!this.zzacw.isEnabled() || !this.zzanu) {
            zzfx().zzke();
        } else {
            zzge().zzis().log("Recording app launch after enabling measurement for the first time (FE)");
            zzkb();
        }
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zza((String) null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotEmpty(str);
        zzfr();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzja = zzgf().zzja();
            return zzja != null ? Tasks.forResult(zzja) : Tasks.call(zzgd().zzjl(), new zzho(this));
        } catch (Exception e) {
            zzge().zzip().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return zzf((String) null, str, str2);
    }

    public final List<AppMeasurement.ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzfr();
        return zzf(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return zzb((String) null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        Preconditions.checkNotEmpty(str);
        zzfr();
        return zzb(str, str2, str3, z);
    }

    public final void logEvent(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, this.zzanq == null || zzka.zzci(str2), false, (String) null);
    }

    public final void registerOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzanr.add(onEventListener)) {
            zzge().zzip().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzgd().zzc((Runnable) new zzhq(this, zzbt().currentTimeMillis()));
    }

    public final void setConditionalUserProperty(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        AppMeasurement.ConditionalUserProperty conditionalUserProperty2 = new AppMeasurement.ConditionalUserProperty(conditionalUserProperty);
        if (!TextUtils.isEmpty(conditionalUserProperty2.mAppId)) {
            zzge().zzip().log("Package name should be null when calling setConditionalUserProperty");
        }
        conditionalUserProperty2.mAppId = null;
        zza(conditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement.ConditionalUserProperty conditionalUserProperty) {
        Preconditions.checkNotNull(conditionalUserProperty);
        Preconditions.checkNotEmpty(conditionalUserProperty.mAppId);
        zzfr();
        zza(new AppMeasurement.ConditionalUserProperty(conditionalUserProperty));
    }

    @WorkerThread
    public final void setEventInterceptor(AppMeasurement.EventInterceptor eventInterceptor) {
        zzab();
        zzch();
        if (!(eventInterceptor == null || eventInterceptor == this.zzanq)) {
            Preconditions.checkState(this.zzanq == null, "EventInterceptor already set.");
        }
        this.zzanq = eventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzch();
        zzgd().zzc((Runnable) new zzhz(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzgd().zzc((Runnable) new zzia(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzgd().zzc((Runnable) new zzib(this, j));
    }

    public final void setUserProperty(String str, String str2, Object obj) {
        Preconditions.checkNotEmpty(str);
        long currentTimeMillis = zzbt().currentTimeMillis();
        int zzcf = zzgb().zzcf(str2);
        int i = 0;
        if (zzcf != 0) {
            zzgb();
            String zza = zzka.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzacw.zzgb().zza(zzcf, "_ev", zza, i);
        } else if (obj != null) {
            int zzi = zzgb().zzi(str2, obj);
            if (zzi != 0) {
                zzgb();
                String zza2 = zzka.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzacw.zzgb().zza(zzi, "_ev", zza2, i);
                return;
            }
            Object zzj = zzgb().zzj(str2, obj);
            if (zzj != null) {
                zza(str, str2, currentTimeMillis, zzj);
            }
        } else {
            zza(str, str2, currentTimeMillis, (Object) null);
        }
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement.OnEventListener onEventListener) {
        zzch();
        Preconditions.checkNotNull(onEventListener);
        if (!this.zzanr.remove(onEventListener)) {
            zzge().zzip().log("OnEventListener had not been registered");
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, String str2, Bundle bundle) {
        zzab();
        zzb(str, str2, zzbt().currentTimeMillis(), bundle, true, this.zzanq == null || zzka.zzci(str2), false, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, j, bundle, false, true, true, (String) null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        zza(str, str2, bundle, true, this.zzanq == null || zzka.zzci(str2), true, (String) null);
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:10|11|12|13) */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        zzge().zzip().log("Interrupted waiting for app instance id");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001d */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzae(long r4) {
        /*
            r3 = this;
            java.util.concurrent.atomic.AtomicReference r0 = new java.util.concurrent.atomic.AtomicReference
            r0.<init>()
            monitor-enter(r0)
            com.google.android.gms.internal.measurement.zzgg r1 = r3.zzgd()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.measurement.zzhp r2 = new com.google.android.gms.internal.measurement.zzhp     // Catch:{ all -> 0x002d }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x002d }
            r1.zzc((java.lang.Runnable) r2)     // Catch:{ all -> 0x002d }
            r0.wait(r4)     // Catch:{ InterruptedException -> 0x001d }
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            java.lang.Object r4 = r0.get()
            java.lang.String r4 = (java.lang.String) r4
            return r4
        L_0x001d:
            com.google.android.gms.internal.measurement.zzfg r4 = r3.zzge()     // Catch:{ all -> 0x002d }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzip()     // Catch:{ all -> 0x002d }
            java.lang.String r5 = "Interrupted waiting for app instance id"
            r4.log(r5)     // Catch:{ all -> 0x002d }
            r4 = 0
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            return r4
        L_0x002d:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002d }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzhk.zzae(long):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public final void zzbr(@Nullable String str) {
        this.zzant.set(str);
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final /* bridge */ /* synthetic */ void zzfr() {
        super.zzfr();
    }

    public final /* bridge */ /* synthetic */ void zzfs() {
        super.zzfs();
    }

    public final /* bridge */ /* synthetic */ zzdu zzft() {
        return super.zzft();
    }

    public final /* bridge */ /* synthetic */ zzhk zzfu() {
        return super.zzfu();
    }

    public final /* bridge */ /* synthetic */ zzfb zzfv() {
        return super.zzfv();
    }

    public final /* bridge */ /* synthetic */ zzeo zzfw() {
        return super.zzfw();
    }

    public final /* bridge */ /* synthetic */ zzii zzfx() {
        return super.zzfx();
    }

    public final /* bridge */ /* synthetic */ zzif zzfy() {
        return super.zzfy();
    }

    public final /* bridge */ /* synthetic */ zzfc zzfz() {
        return super.zzfz();
    }

    public final /* bridge */ /* synthetic */ zzfe zzga() {
        return super.zzga();
    }

    public final /* bridge */ /* synthetic */ zzka zzgb() {
        return super.zzgb();
    }

    public final /* bridge */ /* synthetic */ zzjh zzgc() {
        return super.zzgc();
    }

    public final /* bridge */ /* synthetic */ zzgg zzgd() {
        return super.zzgd();
    }

    public final /* bridge */ /* synthetic */ zzfg zzge() {
        return super.zzge();
    }

    public final /* bridge */ /* synthetic */ zzfr zzgf() {
        return super.zzgf();
    }

    public final /* bridge */ /* synthetic */ zzef zzgg() {
        return super.zzgg();
    }

    /* access modifiers changed from: protected */
    public final boolean zzhf() {
        return false;
    }

    public final String zzhm() {
        AtomicReference atomicReference = new AtomicReference();
        return (String) zzgd().zza(atomicReference, 15000, "String test flag value", new zzhv(this, atomicReference));
    }

    public final List<zzjx> zzj(boolean z) {
        zzfi zzip;
        String str;
        zzch();
        zzge().zzis().log("Fetching user attributes (FE)");
        if (zzgd().zzjk()) {
            zzip = zzge().zzim();
            str = "Cannot get all user properties from analytics worker thread";
        } else {
            zzgd();
            if (zzgg.isMainThread()) {
                zzip = zzge().zzim();
                str = "Cannot get all user properties from main thread";
            } else {
                AtomicReference atomicReference = new AtomicReference();
                synchronized (atomicReference) {
                    this.zzacw.zzgd().zzc((Runnable) new zzhn(this, atomicReference, z));
                    try {
                        atomicReference.wait(5000);
                    } catch (InterruptedException e) {
                        zzge().zzip().zzg("Interrupted waiting for get user properties", e);
                    }
                }
                List<zzjx> list = (List) atomicReference.get();
                if (list != null) {
                    return list;
                }
                zzip = zzge().zzip();
                str = "Timed out waiting for get user properties";
            }
        }
        zzip.log(str);
        return Collections.emptyList();
    }

    @Nullable
    public final String zzja() {
        return this.zzant.get();
    }

    public final Boolean zzjx() {
        AtomicReference atomicReference = new AtomicReference();
        return (Boolean) zzgd().zza(atomicReference, 15000, "boolean test flag value", new zzhl(this, atomicReference));
    }

    public final Long zzjy() {
        AtomicReference atomicReference = new AtomicReference();
        return (Long) zzgd().zza(atomicReference, 15000, "long test flag value", new zzhw(this, atomicReference));
    }

    public final Integer zzjz() {
        AtomicReference atomicReference = new AtomicReference();
        return (Integer) zzgd().zza(atomicReference, 15000, "int test flag value", new zzhx(this, atomicReference));
    }

    public final Double zzka() {
        AtomicReference atomicReference = new AtomicReference();
        return (Double) zzgd().zza(atomicReference, 15000, "double test flag value", new zzhy(this, atomicReference));
    }

    @WorkerThread
    public final void zzkb() {
        zzab();
        zzch();
        if (this.zzacw.zzjv()) {
            zzfx().zzkb();
            this.zzanu = false;
            String zzjd = zzgf().zzjd();
            if (!TextUtils.isEmpty(zzjd)) {
                zzfw().zzch();
                if (!zzjd.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("_po", zzjd);
                    logEvent("auto", "_ou", bundle);
                }
            }
        }
    }
}
