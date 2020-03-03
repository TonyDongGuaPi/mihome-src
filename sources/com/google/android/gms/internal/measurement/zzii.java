package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@VisibleForTesting
public final class zzii extends zzhh {
    /* access modifiers changed from: private */
    public final zziw zzaox;
    /* access modifiers changed from: private */
    public zzey zzaoy;
    private volatile Boolean zzaoz;
    private final zzem zzapa;
    private final zzjm zzapb;
    private final List<Runnable> zzapc = new ArrayList();
    private final zzem zzapd;

    protected zzii(zzgl zzgl) {
        super(zzgl);
        this.zzapb = new zzjm(zzgl.zzbt());
        this.zzaox = new zziw(this);
        this.zzapa = new zzij(this, zzgl);
        this.zzapd = new zzio(this, zzgl);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void onServiceDisconnected(ComponentName componentName) {
        zzab();
        if (this.zzaoy != null) {
            this.zzaoy = null;
            zzge().zzit().zzg("Disconnected from device MeasurementService", componentName);
            zzab();
            zzdf();
        }
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzcu() {
        zzab();
        this.zzapb.start();
        this.zzapa.zzh(zzew.zzaho.get().longValue());
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzcv() {
        zzab();
        if (isConnected()) {
            zzge().zzit().log("Inactivity, disconnecting from the service");
            disconnect();
        }
    }

    @WorkerThread
    private final void zzf(Runnable runnable) throws IllegalStateException {
        zzab();
        if (isConnected()) {
            runnable.run();
        } else if (((long) this.zzapc.size()) >= 1000) {
            zzge().zzim().log("Discarding data. Max runnable queue size reached");
        } else {
            this.zzapc.add(runnable);
            this.zzapd.zzh(60000);
            zzdf();
        }
    }

    @Nullable
    @WorkerThread
    private final zzdz zzk(boolean z) {
        return zzfv().zzbi(z ? zzge().zziv() : null);
    }

    /* access modifiers changed from: private */
    @WorkerThread
    public final void zzkg() {
        zzab();
        zzge().zzit().zzg("Processing queued up service tasks", Integer.valueOf(this.zzapc.size()));
        for (Runnable run : this.zzapc) {
            try {
                run.run();
            } catch (Exception e) {
                zzge().zzim().zzg("Task exception while flushing queue", e);
            }
        }
        this.zzapc.clear();
        this.zzapd.cancel();
    }

    @WorkerThread
    public final void disconnect() {
        zzab();
        zzch();
        try {
            ConnectionTracker.getInstance().unbindService(getContext(), this.zzaox);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        this.zzaoy = null;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final boolean isConnected() {
        zzab();
        zzch();
        return this.zzaoy != null;
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void resetAnalyticsData() {
        zzab();
        zzch();
        zzdz zzk = zzk(false);
        zzfz().resetAnalyticsData();
        zzf(new zzik(this, zzk));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzey zzey) {
        zzab();
        Preconditions.checkNotNull(zzey);
        this.zzaoy = zzey;
        zzcu();
        zzkg();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zza(zzey zzey, AbstractSafeParcelable abstractSafeParcelable, zzdz zzdz) {
        int i;
        zzfi zzim;
        String str;
        zzab();
        zzch();
        int i2 = 0;
        int i3 = 100;
        while (i2 < 1001 && i3 == 100) {
            ArrayList arrayList = new ArrayList();
            List<AbstractSafeParcelable> zzp = zzfz().zzp(100);
            if (zzp != null) {
                arrayList.addAll(zzp);
                i = zzp.size();
            } else {
                i = 0;
            }
            if (abstractSafeParcelable != null && i < 100) {
                arrayList.add(abstractSafeParcelable);
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i4 = 0;
            while (i4 < size) {
                Object obj = arrayList2.get(i4);
                i4++;
                AbstractSafeParcelable abstractSafeParcelable2 = (AbstractSafeParcelable) obj;
                if (abstractSafeParcelable2 instanceof zzeu) {
                    try {
                        zzey.zza((zzeu) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e) {
                        e = e;
                        zzim = zzge().zzim();
                        str = "Failed to send event to the service";
                    }
                } else if (abstractSafeParcelable2 instanceof zzjx) {
                    try {
                        zzey.zza((zzjx) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e2) {
                        e = e2;
                        zzim = zzge().zzim();
                        str = "Failed to send attribute to the service";
                    }
                } else if (abstractSafeParcelable2 instanceof zzed) {
                    try {
                        zzey.zza((zzed) abstractSafeParcelable2, zzdz);
                    } catch (RemoteException e3) {
                        e = e3;
                        zzim = zzge().zzim();
                        str = "Failed to send conditional property to the service";
                    }
                } else {
                    zzge().zzim().log("Discarding data. Unrecognized parcel type.");
                }
            }
            i2++;
            i3 = i;
        }
        return;
        zzim.zzg(str, e);
    }

    @WorkerThread
    public final void zza(AtomicReference<String> atomicReference) {
        zzab();
        zzch();
        zzf(new zzil(this, atomicReference, zzk(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzed>> atomicReference, String str, String str2, String str3) {
        zzab();
        zzch();
        zzf(new zzis(this, atomicReference, str, str2, str3, zzk(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzjx>> atomicReference, String str, String str2, String str3, boolean z) {
        zzab();
        zzch();
        zzf(new zzit(this, atomicReference, str, str2, str3, z, zzk(false)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zza(AtomicReference<List<zzjx>> atomicReference, boolean z) {
        zzab();
        zzch();
        zzf(new zziv(this, atomicReference, zzk(false), z));
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzeu zzeu, String str) {
        Preconditions.checkNotNull(zzeu);
        zzab();
        zzch();
        zzf(new zziq(this, true, zzfz().zza(zzeu), zzeu, zzk(true), str));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzie zzie) {
        zzab();
        zzch();
        zzf(new zzin(this, zzie));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzb(zzjx zzjx) {
        zzab();
        zzch();
        zzf(new zziu(this, zzfz().zza(zzjx), zzjx, zzk(true)));
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzd(zzed zzed) {
        Preconditions.checkNotNull(zzed);
        zzab();
        zzch();
        zzf(new zzir(this, true, zzfz().zzc(zzed), new zzed(zzed), zzk(true), zzed));
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c5, code lost:
        r0 = true;
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00ee  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzdf() {
        /*
            r6 = this;
            r6.zzab()
            r6.zzch()
            boolean r0 = r6.isConnected()
            if (r0 == 0) goto L_0x000d
            return
        L_0x000d:
            java.lang.Boolean r0 = r6.zzaoz
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x00fb
            r6.zzab()
            r6.zzch()
            com.google.android.gms.internal.measurement.zzfr r0 = r6.zzgf()
            java.lang.Boolean r0 = r0.zzjb()
            if (r0 == 0) goto L_0x002c
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x002c
            r3 = 1
            goto L_0x00f5
        L_0x002c:
            com.google.android.gms.internal.measurement.zzfb r0 = r6.zzfv()
            int r0 = r0.zzik()
            if (r0 != r2) goto L_0x003a
        L_0x0036:
            r0 = 1
            r3 = 1
            goto L_0x00ec
        L_0x003a:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()
            java.lang.String r3 = "Checking service availability"
            r0.log(r3)
            com.google.android.gms.internal.measurement.zzka r0 = r6.zzgb()
            com.google.android.gms.common.GoogleApiAvailabilityLight r3 = com.google.android.gms.common.GoogleApiAvailabilityLight.getInstance()
            android.content.Context r0 = r0.getContext()
            r4 = 12451(0x30a3, float:1.7448E-41)
            int r0 = r3.isGooglePlayServicesAvailable(r0, r4)
            r3 = 9
            if (r0 == r3) goto L_0x00e1
            r3 = 18
            if (r0 == r3) goto L_0x00d6
            switch(r0) {
                case 0: goto L_0x00c7;
                case 1: goto L_0x00b8;
                case 2: goto L_0x0087;
                case 3: goto L_0x0079;
                default: goto L_0x0064;
            }
        L_0x0064:
            com.google.android.gms.internal.measurement.zzfg r3 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzip()
            java.lang.String r4 = "Unexpected service status"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.zzg(r4, r0)
        L_0x0075:
            r0 = 0
        L_0x0076:
            r3 = 0
            goto L_0x00ec
        L_0x0079:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r3 = "Service disabled"
        L_0x0083:
            r0.log(r3)
            goto L_0x0075
        L_0x0087:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzis()
            java.lang.String r3 = "Service container out of date"
            r0.log(r3)
            com.google.android.gms.internal.measurement.zzka r0 = r6.zzgb()
            int r0 = r0.zzld()
            r3 = 12600(0x3138, float:1.7656E-41)
            if (r0 >= r3) goto L_0x00a1
            goto L_0x00c5
        L_0x00a1:
            com.google.android.gms.internal.measurement.zzfr r0 = r6.zzgf()
            java.lang.Boolean r0 = r0.zzjb()
            if (r0 == 0) goto L_0x00b4
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x00b2
            goto L_0x00b4
        L_0x00b2:
            r0 = 0
            goto L_0x00b5
        L_0x00b4:
            r0 = 1
        L_0x00b5:
            r3 = r0
            r0 = 0
            goto L_0x00ec
        L_0x00b8:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()
            java.lang.String r3 = "Service missing"
            r0.log(r3)
        L_0x00c5:
            r0 = 1
            goto L_0x0076
        L_0x00c7:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()
            java.lang.String r3 = "Service available"
        L_0x00d1:
            r0.log(r3)
            goto L_0x0036
        L_0x00d6:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r3 = "Service updating"
            goto L_0x00d1
        L_0x00e1:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r3 = "Service invalid"
            goto L_0x0083
        L_0x00ec:
            if (r0 == 0) goto L_0x00f5
            com.google.android.gms.internal.measurement.zzfr r0 = r6.zzgf()
            r0.zzf(r3)
        L_0x00f5:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            r6.zzaoz = r0
        L_0x00fb:
            java.lang.Boolean r0 = r6.zzaoz
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L_0x0109
            com.google.android.gms.internal.measurement.zziw r0 = r6.zzaox
            r0.zzkh()
            return
        L_0x0109:
            android.content.Context r0 = r6.getContext()
            android.content.pm.PackageManager r0 = r0.getPackageManager()
            android.content.Intent r3 = new android.content.Intent
            r3.<init>()
            android.content.Context r4 = r6.getContext()
            java.lang.String r5 = "com.google.android.gms.measurement.AppMeasurementService"
            android.content.Intent r3 = r3.setClassName(r4, r5)
            r4 = 65536(0x10000, float:9.18355E-41)
            java.util.List r0 = r0.queryIntentServices(r3, r4)
            if (r0 == 0) goto L_0x012f
            int r0 = r0.size()
            if (r0 <= 0) goto L_0x012f
            r1 = 1
        L_0x012f:
            if (r1 == 0) goto L_0x014c
            android.content.Intent r0 = new android.content.Intent
            java.lang.String r1 = "com.google.android.gms.measurement.START"
            r0.<init>(r1)
            android.content.ComponentName r1 = new android.content.ComponentName
            android.content.Context r2 = r6.getContext()
            java.lang.String r3 = "com.google.android.gms.measurement.AppMeasurementService"
            r1.<init>(r2, r3)
            r0.setComponent(r1)
            com.google.android.gms.internal.measurement.zziw r1 = r6.zzaox
            r1.zzc(r0)
            return
        L_0x014c:
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()
            java.lang.String r1 = "Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest"
            r0.log(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzii.zzdf():void");
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

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzkb() {
        zzab();
        zzch();
        zzf(new zzim(this, zzk(true)));
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public final void zzke() {
        zzab();
        zzch();
        zzf(new zzip(this, zzk(true)));
    }

    /* access modifiers changed from: package-private */
    public final Boolean zzkf() {
        return this.zzaoz;
    }
}
