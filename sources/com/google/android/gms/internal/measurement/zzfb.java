package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class zzfb extends zzhh {
    private String zzadm;
    private String zzadt;
    private long zzadx;
    private int zzaen;
    private int zzaie;
    private long zzaif;
    private String zztg;
    private String zzth;
    private String zzti;

    zzfb(zzgl zzgl) {
        super(zzgl);
    }

    @WorkerThread
    private final String zzgj() {
        zzab();
        if (zzgg().zzay(this.zzti) && !this.zzacw.isEnabled()) {
            return null;
        }
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException unused) {
            zzge().zzip().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    /* access modifiers changed from: package-private */
    public final String getGmpAppId() {
        zzch();
        return this.zzadm;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    /* access modifiers changed from: package-private */
    public final String zzah() {
        zzch();
        return this.zzti;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzdz zzbi(String str) {
        zzab();
        String zzah = zzah();
        String gmpAppId = getGmpAppId();
        zzch();
        String str2 = this.zzth;
        long zzij = (long) zzij();
        zzch();
        String str3 = this.zzadt;
        zzch();
        zzab();
        if (this.zzaif == 0) {
            this.zzaif = this.zzacw.zzgb().zzd(getContext(), getContext().getPackageName());
        }
        long j = this.zzaif;
        boolean isEnabled = this.zzacw.isEnabled();
        boolean z = true;
        boolean z2 = !zzgf().zzakn;
        String zzgj = zzgj();
        zzch();
        long zzjt = this.zzacw.zzjt();
        int zzik = zzik();
        Boolean zzas = zzgg().zzas("google_analytics_adid_collection_enabled");
        boolean booleanValue = Boolean.valueOf(zzas == null || zzas.booleanValue()).booleanValue();
        Boolean zzas2 = zzgg().zzas("google_analytics_ssaid_collection_enabled");
        if (zzas2 != null && !zzas2.booleanValue()) {
            z = false;
        }
        return new zzdz(zzah, gmpAppId, str2, zzij, str3, 12451, j, str, isEnabled, z2, zzgj, 0, zzjt, zzik, booleanValue, Boolean.valueOf(z).booleanValue(), zzgf().zzje());
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
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0142 A[Catch:{ IllegalStateException -> 0x015a }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0148 A[Catch:{ IllegalStateException -> 0x015a }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x017d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzih() {
        /*
            r10 = this;
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "Unknown"
            java.lang.String r2 = "Unknown"
            android.content.Context r3 = r10.getContext()
            java.lang.String r3 = r3.getPackageName()
            android.content.Context r4 = r10.getContext()
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            r5 = 0
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r4 != 0) goto L_0x002e
            com.google.android.gms.internal.measurement.zzfg r4 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()
            java.lang.String r7 = "PackageManager is null, app identity information might be inaccurate. appId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)
            r4.zzg(r7, r8)
            goto L_0x008c
        L_0x002e:
            java.lang.String r7 = r4.getInstallerPackageName(r3)     // Catch:{ IllegalArgumentException -> 0x0034 }
            r0 = r7
            goto L_0x0045
        L_0x0034:
            com.google.android.gms.internal.measurement.zzfg r7 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzim()
            java.lang.String r8 = "Error retrieving app installer package name. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)
            r7.zzg(r8, r9)
        L_0x0045:
            if (r0 != 0) goto L_0x004a
            java.lang.String r0 = "manual_install"
            goto L_0x0054
        L_0x004a:
            java.lang.String r7 = "com.android.vending"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x0054
            java.lang.String r0 = ""
        L_0x0054:
            android.content.Context r7 = r10.getContext()     // Catch:{ NameNotFoundException -> 0x007b }
            java.lang.String r7 = r7.getPackageName()     // Catch:{ NameNotFoundException -> 0x007b }
            android.content.pm.PackageInfo r7 = r4.getPackageInfo(r7, r5)     // Catch:{ NameNotFoundException -> 0x007b }
            if (r7 == 0) goto L_0x008c
            android.content.pm.ApplicationInfo r8 = r7.applicationInfo     // Catch:{ NameNotFoundException -> 0x007b }
            java.lang.CharSequence r4 = r4.getApplicationLabel(r8)     // Catch:{ NameNotFoundException -> 0x007b }
            boolean r8 = android.text.TextUtils.isEmpty(r4)     // Catch:{ NameNotFoundException -> 0x007b }
            if (r8 != 0) goto L_0x0073
            java.lang.String r4 = r4.toString()     // Catch:{ NameNotFoundException -> 0x007b }
            r2 = r4
        L_0x0073:
            java.lang.String r4 = r7.versionName     // Catch:{ NameNotFoundException -> 0x007b }
            int r1 = r7.versionCode     // Catch:{ NameNotFoundException -> 0x007a }
            r6 = r1
            r1 = r4
            goto L_0x008c
        L_0x007a:
            r1 = r4
        L_0x007b:
            com.google.android.gms.internal.measurement.zzfg r4 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()
            java.lang.String r7 = "Error retrieving package info. appId, appName"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)
            r4.zze(r7, r8, r2)
        L_0x008c:
            r10.zzti = r3
            r10.zzadt = r0
            r10.zzth = r1
            r10.zzaie = r6
            r10.zztg = r2
            r0 = 0
            r10.zzaif = r0
            android.content.Context r2 = r10.getContext()
            com.google.android.gms.common.api.Status r2 = com.google.android.gms.common.api.internal.GoogleServices.initialize(r2)
            r4 = 1
            if (r2 == 0) goto L_0x00ad
            boolean r6 = r2.isSuccess()
            if (r6 == 0) goto L_0x00ad
            r6 = 1
            goto L_0x00ae
        L_0x00ad:
            r6 = 0
        L_0x00ae:
            if (r6 != 0) goto L_0x00d9
            if (r2 != 0) goto L_0x00c0
            com.google.android.gms.internal.measurement.zzfg r2 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()
            java.lang.String r7 = "GoogleService failed to initialize (no status)"
            r2.log(r7)
            goto L_0x00d9
        L_0x00c0:
            com.google.android.gms.internal.measurement.zzfg r7 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzim()
            java.lang.String r8 = "GoogleService failed to initialize, status"
            int r9 = r2.getStatusCode()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            java.lang.String r2 = r2.getStatusMessage()
            r7.zze(r8, r9, r2)
        L_0x00d9:
            if (r6 == 0) goto L_0x0131
            com.google.android.gms.internal.measurement.zzef r2 = r10.zzgg()
            java.lang.String r6 = "firebase_analytics_collection_enabled"
            java.lang.Boolean r2 = r2.zzas(r6)
            com.google.android.gms.internal.measurement.zzef r6 = r10.zzgg()
            boolean r6 = r6.zzhg()
            if (r6 == 0) goto L_0x00fd
            com.google.android.gms.internal.measurement.zzfg r2 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzir()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_deactivated=1"
        L_0x00f9:
            r2.log(r4)
            goto L_0x0131
        L_0x00fd:
            if (r2 == 0) goto L_0x0110
            boolean r6 = r2.booleanValue()
            if (r6 != 0) goto L_0x0110
            com.google.android.gms.internal.measurement.zzfg r2 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzir()
            java.lang.String r4 = "Collection disabled with firebase_analytics_collection_enabled=0"
            goto L_0x00f9
        L_0x0110:
            if (r2 != 0) goto L_0x0123
            boolean r2 = com.google.android.gms.common.api.internal.GoogleServices.isMeasurementExplicitlyDisabled()
            if (r2 == 0) goto L_0x0123
            com.google.android.gms.internal.measurement.zzfg r2 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzir()
            java.lang.String r4 = "Collection disabled with google_app_measurement_enable=0"
            goto L_0x00f9
        L_0x0123:
            com.google.android.gms.internal.measurement.zzfg r2 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzit()
            java.lang.String r6 = "Collection enabled"
            r2.log(r6)
            goto L_0x0132
        L_0x0131:
            r4 = 0
        L_0x0132:
            java.lang.String r2 = ""
            r10.zzadm = r2
            r10.zzadx = r0
            java.lang.String r0 = com.google.android.gms.common.api.internal.GoogleServices.getGoogleAppId()     // Catch:{ IllegalStateException -> 0x015a }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ IllegalStateException -> 0x015a }
            if (r1 == 0) goto L_0x0144
            java.lang.String r0 = ""
        L_0x0144:
            r10.zzadm = r0     // Catch:{ IllegalStateException -> 0x015a }
            if (r4 == 0) goto L_0x016c
            com.google.android.gms.internal.measurement.zzfg r0 = r10.zzge()     // Catch:{ IllegalStateException -> 0x015a }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()     // Catch:{ IllegalStateException -> 0x015a }
            java.lang.String r1 = "App package, google app id"
            java.lang.String r2 = r10.zzti     // Catch:{ IllegalStateException -> 0x015a }
            java.lang.String r4 = r10.zzadm     // Catch:{ IllegalStateException -> 0x015a }
            r0.zze(r1, r2, r4)     // Catch:{ IllegalStateException -> 0x015a }
            goto L_0x016c
        L_0x015a:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r1 = r10.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()
            java.lang.String r2 = "getGoogleAppId or isMeasurementEnabled failed with exception. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)
            r1.zze(r2, r3, r0)
        L_0x016c:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 16
            if (r0 < r1) goto L_0x017d
            android.content.Context r0 = r10.getContext()
            boolean r0 = com.google.android.gms.common.wrappers.InstantApps.isInstantApp(r0)
            r10.zzaen = r0
            return
        L_0x017d:
            r10.zzaen = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfb.zzih():void");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final String zzii() {
        byte[] bArr = new byte[16];
        zzgb().zzlc().nextBytes(bArr);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, bArr)});
    }

    /* access modifiers changed from: package-private */
    public final int zzij() {
        zzch();
        return this.zzaie;
    }

    /* access modifiers changed from: package-private */
    public final int zzik() {
        zzch();
        return this.zzaen;
    }
}
