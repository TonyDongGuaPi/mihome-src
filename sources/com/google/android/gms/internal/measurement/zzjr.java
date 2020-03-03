package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.xiaomi.stat.a.j;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzjr implements zzec {
    private zzgl zzacw;
    zzgf zzaqa;
    zzfk zzaqb;
    private zzei zzaqc;
    private zzfp zzaqd;
    private zzjn zzaqe;
    private zzeb zzaqf;
    private boolean zzaqg;
    @VisibleForTesting
    private long zzaqh;
    private List<Runnable> zzaqi;
    private int zzaqj;
    private int zzaqk;
    private boolean zzaql;
    private boolean zzaqm;
    private boolean zzaqn;
    private FileLock zzaqo;
    private FileChannel zzaqp;
    private List<Long> zzaqq;
    private List<Long> zzaqr;
    long zzaqs;
    private boolean zzvo = false;

    @WorkerThread
    @VisibleForTesting
    private final int zza(FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzge().zzim().log("Bad channel to read from");
            return 0;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        try {
            fileChannel.position(0);
            int read = fileChannel.read(allocate);
            if (read != 4) {
                if (read != -1) {
                    zzge().zzip().zzg("Unexpected data length. Bytes read", Integer.valueOf(read));
                }
                return 0;
            }
            allocate.flip();
            return allocate.getInt();
        } catch (IOException e) {
            zzge().zzim().zzg("Failed to read from channel", e);
            return 0;
        }
    }

    private final zzdz zza(Context context, String str, String str2, boolean z, boolean z2, boolean z3, long j) {
        String str3;
        int i;
        String str4 = str;
        String str5 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            zzge().zzim().log("PackageManager is null, can not log app install information");
            return null;
        }
        try {
            str5 = packageManager.getInstallerPackageName(str4);
        } catch (IllegalArgumentException unused) {
            zzge().zzim().zzg("Error retrieving installer package name. appId", zzfg.zzbm(str));
        }
        if (str5 == null) {
            str5 = "manual_install";
        } else if ("com.android.vending".equals(str5)) {
            str5 = "";
        }
        String str6 = str5;
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(str4, 0);
            if (packageInfo != null) {
                CharSequence applicationLabel = Wrappers.packageManager(context).getApplicationLabel(str4);
                if (!TextUtils.isEmpty(applicationLabel)) {
                    String charSequence = applicationLabel.toString();
                }
                str3 = packageInfo.versionName;
                i = packageInfo.versionCode;
            } else {
                str3 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                i = Integer.MIN_VALUE;
            }
            return new zzdz(str, str2, str3, (long) i, str6, 12451, zzgb().zzd(context, str4), (String) null, z, false, "", 0, zzgg().zzba(str4) ? j : 0, 0, z2, z3, false);
        } catch (PackageManager.NameNotFoundException unused2) {
            zzge().zzim().zze("Error retrieving newly installed package info. appId, appName", zzfg.zzbm(str), AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
            return null;
        }
    }

    private static void zza(zzjq zzjq) {
        if (zzjq == null) {
            throw new IllegalStateException("Upload component not created");
        } else if (!zzjq.isInitialized()) {
            String valueOf = String.valueOf(zzjq.getClass());
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 27);
            sb.append("Component not initialized: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zza(int i, FileChannel fileChannel) {
        zzab();
        if (fileChannel == null || !fileChannel.isOpen()) {
            zzge().zzim().log("Bad channel to read from");
            return false;
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(i);
        allocate.flip();
        try {
            fileChannel.truncate(0);
            fileChannel.write(allocate);
            fileChannel.force(true);
            if (fileChannel.size() != 4) {
                zzge().zzim().zzg("Error writing to channel. Bytes written", Long.valueOf(fileChannel.size()));
            }
            return true;
        } catch (IOException e) {
            zzge().zzim().zzg("Failed to write to channel", e);
            return false;
        }
    }

    private final boolean zza(String str, zzeu zzeu) {
        long j;
        zzjz zzjz;
        String string = zzeu.zzafq.getString("currency");
        if (FirebaseAnalytics.Event.ECOMMERCE_PURCHASE.equals(zzeu.name)) {
            double doubleValue = zzeu.zzafq.zzbh("value").doubleValue() * 1000000.0d;
            if (doubleValue == 0.0d) {
                double longValue = (double) zzeu.zzafq.getLong("value").longValue();
                Double.isNaN(longValue);
                doubleValue = longValue * 1000000.0d;
            }
            if (doubleValue > 9.223372036854776E18d || doubleValue < -9.223372036854776E18d) {
                zzge().zzip().zze("Data lost. Currency value is too big. appId", zzfg.zzbm(str), Double.valueOf(doubleValue));
                return false;
            }
            j = Math.round(doubleValue);
        } else {
            j = zzeu.zzafq.getLong("value").longValue();
        }
        if (!TextUtils.isEmpty(string)) {
            String upperCase = string.toUpperCase(Locale.US);
            if (upperCase.matches("[A-Z]{3}")) {
                String valueOf = String.valueOf("_ltv_");
                String valueOf2 = String.valueOf(upperCase);
                String concat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
                zzjz zzh = zzix().zzh(str, concat);
                if (zzh == null || !(zzh.value instanceof Long)) {
                    zzei zzix = zzix();
                    int zzb = zzgg().zzb(str, zzew.zzahm) - 1;
                    Preconditions.checkNotEmpty(str);
                    zzix.zzab();
                    zzix.zzch();
                    try {
                        zzix.getWritableDatabase().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like '_ltv_%' order by set_timestamp desc limit ?,10);", new String[]{str, str, String.valueOf(zzb)});
                    } catch (SQLiteException e) {
                        zzix.zzge().zzim().zze("Error pruning currencies. appId", zzfg.zzbm(str), e);
                    }
                    zzjz = new zzjz(str, zzeu.origin, concat, zzbt().currentTimeMillis(), Long.valueOf(j));
                } else {
                    zzjz = new zzjz(str, zzeu.origin, concat, zzbt().currentTimeMillis(), Long.valueOf(((Long) zzh.value).longValue() + j));
                }
                if (!zzix().zza(zzjz)) {
                    zzge().zzim().zzd("Too many unique user properties are set. Ignoring user property. appId", zzfg.zzbm(str), zzga().zzbl(zzjz.name), zzjz.value);
                    zzgb().zza(str, 9, (String) null, (String) null, 0);
                }
            }
        }
        return true;
    }

    private final zzkm[] zza(String str, zzks[] zzksArr, zzkn[] zzknArr) {
        Preconditions.checkNotEmpty(str);
        return zziw().zza(str, zzknArr, zzksArr);
    }

    @WorkerThread
    private final void zzb(zzdy zzdy) {
        ArrayMap arrayMap;
        zzab();
        if (TextUtils.isEmpty(zzdy.getGmpAppId())) {
            zzb(zzdy.zzah(), 204, (Throwable) null, (byte[]) null, (Map<String, List<String>>) null);
            return;
        }
        String gmpAppId = zzdy.getGmpAppId();
        String appInstanceId = zzdy.getAppInstanceId();
        Uri.Builder builder = new Uri.Builder();
        Uri.Builder encodedAuthority = builder.scheme(zzew.zzagm.get()).encodedAuthority(zzew.zzagn.get());
        String valueOf = String.valueOf(gmpAppId);
        encodedAuthority.path(valueOf.length() != 0 ? "config/app/".concat(valueOf) : new String("config/app/")).appendQueryParameter("app_instance_id", appInstanceId).appendQueryParameter("platform", "android").appendQueryParameter("gmp_version", "12451");
        String uri = builder.build().toString();
        try {
            URL url = new URL(uri);
            zzge().zzit().zzg("Fetching remote configuration", zzdy.zzah());
            zzkk zzbu = zzkm().zzbu(zzdy.zzah());
            String zzbv = zzkm().zzbv(zzdy.zzah());
            if (zzbu == null || TextUtils.isEmpty(zzbv)) {
                arrayMap = null;
            } else {
                ArrayMap arrayMap2 = new ArrayMap();
                arrayMap2.put(HttpHeaders.IF_MODIFIED_SINCE, zzbv);
                arrayMap = arrayMap2;
            }
            this.zzaql = true;
            zzfk zzkn = zzkn();
            String zzah = zzdy.zzah();
            zzjt zzjt = new zzjt(this);
            zzkn.zzab();
            zzkn.zzch();
            Preconditions.checkNotNull(url);
            Preconditions.checkNotNull(zzjt);
            zzkn.zzgd().zzd((Runnable) new zzfo(zzkn, zzah, url, (byte[]) null, arrayMap, zzjt));
        } catch (MalformedURLException unused) {
            zzge().zzim().zze("Failed to parse config URL. Not fetching. appId", zzfg.zzbm(zzdy.zzah()), uri);
        }
    }

    @WorkerThread
    private final Boolean zzc(zzdy zzdy) {
        try {
            if (zzdy.zzgm() != -2147483648L) {
                if (zzdy.zzgm() == ((long) Wrappers.packageManager(getContext()).getPackageInfo(zzdy.zzah(), 0).versionCode)) {
                    return true;
                }
            } else {
                String str = Wrappers.packageManager(getContext()).getPackageInfo(zzdy.zzah(), 0).versionName;
                if (zzdy.zzag() != null && zzdy.zzag().equals(str)) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0543, code lost:
        r4 = true;
     */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzc(com.google.android.gms.internal.measurement.zzeu r35, com.google.android.gms.internal.measurement.zzdz r36) {
        /*
            r34 = this;
            r1 = r34
            r0 = r35
            r2 = r36
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r36)
            java.lang.String r3 = r2.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)
            long r3 = java.lang.System.nanoTime()
            r34.zzab()
            r34.zzkq()
            java.lang.String r15 = r2.packageName
            r34.zzgb()
            boolean r5 = com.google.android.gms.internal.measurement.zzka.zzd((com.google.android.gms.internal.measurement.zzeu) r35, (com.google.android.gms.internal.measurement.zzdz) r36)
            if (r5 != 0) goto L_0x0024
            return
        L_0x0024:
            boolean r5 = r2.zzadw
            if (r5 != 0) goto L_0x002c
            r1.zzg((com.google.android.gms.internal.measurement.zzdz) r2)
            return
        L_0x002c:
            com.google.android.gms.internal.measurement.zzgf r5 = r34.zzkm()
            java.lang.String r6 = r0.name
            boolean r5 = r5.zzn(r15, r6)
            r19 = 1
            if (r5 == 0) goto L_0x00ce
            com.google.android.gms.internal.measurement.zzfg r2 = r34.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzip()
            java.lang.String r3 = "Dropping blacklisted event. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r15)
            com.google.android.gms.internal.measurement.zzfe r5 = r34.zzga()
            java.lang.String r6 = r0.name
            java.lang.String r5 = r5.zzbj(r6)
            r2.zze(r3, r4, r5)
            com.google.android.gms.internal.measurement.zzgf r2 = r34.zzkm()
            boolean r2 = r2.zzby(r15)
            if (r2 != 0) goto L_0x006c
            com.google.android.gms.internal.measurement.zzgf r2 = r34.zzkm()
            boolean r2 = r2.zzbz(r15)
            if (r2 == 0) goto L_0x006a
            goto L_0x006c
        L_0x006a:
            r14 = 0
            goto L_0x006d
        L_0x006c:
            r14 = 1
        L_0x006d:
            if (r14 != 0) goto L_0x0088
            java.lang.String r2 = "_err"
            java.lang.String r3 = r0.name
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0088
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()
            r7 = 11
            java.lang.String r8 = "_ev"
            java.lang.String r9 = r0.name
            r10 = 0
            r6 = r15
            r5.zza((java.lang.String) r6, (int) r7, (java.lang.String) r8, (java.lang.String) r9, (int) r10)
        L_0x0088:
            if (r14 == 0) goto L_0x00cd
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            com.google.android.gms.internal.measurement.zzdy r0 = r0.zzbc(r15)
            if (r0 == 0) goto L_0x00cd
            long r2 = r0.zzgs()
            long r4 = r0.zzgr()
            long r2 = java.lang.Math.max(r2, r4)
            com.google.android.gms.common.util.Clock r4 = r34.zzbt()
            long r4 = r4.currentTimeMillis()
            long r4 = r4 - r2
            long r2 = java.lang.Math.abs(r4)
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r4 = com.google.android.gms.internal.measurement.zzew.zzahh
            java.lang.Object r4 = r4.get()
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x00cd
            com.google.android.gms.internal.measurement.zzfg r2 = r34.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzis()
            java.lang.String r3 = "Fetching config for blacklisted app"
            r2.log(r3)
            r1.zzb((com.google.android.gms.internal.measurement.zzdy) r0)
        L_0x00cd:
            return
        L_0x00ce:
            com.google.android.gms.internal.measurement.zzfg r5 = r34.zzge()
            r13 = 2
            boolean r5 = r5.isLoggable(r13)
            if (r5 == 0) goto L_0x00ee
            com.google.android.gms.internal.measurement.zzfg r5 = r34.zzge()
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzit()
            java.lang.String r6 = "Logging event"
            com.google.android.gms.internal.measurement.zzfe r7 = r34.zzga()
            java.lang.String r7 = r7.zzb((com.google.android.gms.internal.measurement.zzeu) r0)
            r5.zzg(r6, r7)
        L_0x00ee:
            com.google.android.gms.internal.measurement.zzei r5 = r34.zzix()
            r5.beginTransaction()
            r1.zzg((com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = "_iap"
            java.lang.String r6 = r0.name     // Catch:{ all -> 0x05ed }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x05ed }
            if (r5 != 0) goto L_0x010c
            java.lang.String r5 = "ecommerce_purchase"
            java.lang.String r6 = r0.name     // Catch:{ all -> 0x05ed }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x05ed }
            if (r5 == 0) goto L_0x0121
        L_0x010c:
            boolean r5 = r1.zza((java.lang.String) r15, (com.google.android.gms.internal.measurement.zzeu) r0)     // Catch:{ all -> 0x05ed }
            if (r5 != 0) goto L_0x0121
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            r0.endTransaction()
            return
        L_0x0121:
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x05ed }
            boolean r16 = com.google.android.gms.internal.measurement.zzka.zzcc(r5)     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = "_err"
            java.lang.String r6 = r0.name     // Catch:{ all -> 0x05ed }
            boolean r17 = r5.equals(r6)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r5 = r34.zzix()     // Catch:{ all -> 0x05ed }
            long r6 = r34.zzkr()     // Catch:{ all -> 0x05ed }
            r9 = 1
            r11 = 0
            r18 = 0
            r8 = r15
            r10 = r16
            r12 = r17
            r13 = r18
            com.google.android.gms.internal.measurement.zzej r5 = r5.zza(r6, r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x05ed }
            long r6 = r5.zzafe     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.zzew.zzags     // Catch:{ all -> 0x05ed }
            java.lang.Object r8 = r8.get()     // Catch:{ all -> 0x05ed }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x05ed }
            int r8 = r8.intValue()     // Catch:{ all -> 0x05ed }
            long r8 = (long) r8     // Catch:{ all -> 0x05ed }
            long r6 = r6 - r8
            r12 = 0
            int r8 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            r9 = 1000(0x3e8, double:4.94E-321)
            r20 = 1
            if (r8 <= 0) goto L_0x018b
            long r6 = r6 % r9
            int r0 = (r6 > r20 ? 1 : (r6 == r20 ? 0 : -1))
            if (r0 != 0) goto L_0x017c
            com.google.android.gms.internal.measurement.zzfg r0 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x05ed }
            java.lang.String r2 = "Data loss. Too many events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r15)     // Catch:{ all -> 0x05ed }
            long r4 = r5.zzafe     // Catch:{ all -> 0x05ed }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x05ed }
            r0.zze(r2, r3, r4)     // Catch:{ all -> 0x05ed }
        L_0x017c:
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            r0.endTransaction()
            return
        L_0x018b:
            if (r16 == 0) goto L_0x01de
            long r6 = r5.zzafd     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.zzew.zzagu     // Catch:{ all -> 0x05ed }
            java.lang.Object r8 = r8.get()     // Catch:{ all -> 0x05ed }
            java.lang.Integer r8 = (java.lang.Integer) r8     // Catch:{ all -> 0x05ed }
            int r8 = r8.intValue()     // Catch:{ all -> 0x05ed }
            r22 = r15
            long r14 = (long) r8     // Catch:{ all -> 0x05ed }
            long r6 = r6 - r14
            int r8 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x01e0
            long r6 = r6 % r9
            int r2 = (r6 > r20 ? 1 : (r6 == r20 ? 0 : -1))
            if (r2 != 0) goto L_0x01bf
            com.google.android.gms.internal.measurement.zzfg r2 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x05ed }
            java.lang.String r3 = "Data loss. Too many public events logged. appId, count"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r22)     // Catch:{ all -> 0x05ed }
            long r5 = r5.zzafd     // Catch:{ all -> 0x05ed }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x05ed }
            r2.zze(r3, r4, r5)     // Catch:{ all -> 0x05ed }
        L_0x01bf:
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            r7 = 16
            java.lang.String r8 = "_ev"
            java.lang.String r9 = r0.name     // Catch:{ all -> 0x05ed }
            r10 = 0
            r6 = r22
            r5.zza((java.lang.String) r6, (int) r7, (java.lang.String) r8, (java.lang.String) r9, (int) r10)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            r0.endTransaction()
            return
        L_0x01de:
            r22 = r15
        L_0x01e0:
            if (r17 == 0) goto L_0x022c
            long r6 = r5.zzafg     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzef r8 = r34.zzgg()     // Catch:{ all -> 0x05ed }
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r10 = com.google.android.gms.internal.measurement.zzew.zzagt     // Catch:{ all -> 0x05ed }
            int r8 = r8.zzb(r9, r10)     // Catch:{ all -> 0x05ed }
            r9 = 1000000(0xf4240, float:1.401298E-39)
            int r8 = java.lang.Math.min(r9, r8)     // Catch:{ all -> 0x05ed }
            r14 = 0
            int r8 = java.lang.Math.max(r14, r8)     // Catch:{ all -> 0x05ed }
            long r8 = (long) r8     // Catch:{ all -> 0x05ed }
            long r6 = r6 - r8
            int r8 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r8 <= 0) goto L_0x022d
            int r0 = (r6 > r20 ? 1 : (r6 == r20 ? 0 : -1))
            if (r0 != 0) goto L_0x021d
            com.google.android.gms.internal.measurement.zzfg r0 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x05ed }
            java.lang.String r2 = "Too many error events logged. appId, count"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r22)     // Catch:{ all -> 0x05ed }
            long r4 = r5.zzafg     // Catch:{ all -> 0x05ed }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x05ed }
            r0.zze(r2, r3, r4)     // Catch:{ all -> 0x05ed }
        L_0x021d:
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            r0.endTransaction()
            return
        L_0x022c:
            r14 = 0
        L_0x022d:
            com.google.android.gms.internal.measurement.zzer r5 = r0.zzafq     // Catch:{ all -> 0x05ed }
            android.os.Bundle r15 = r5.zzif()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = "_o"
            java.lang.String r7 = r0.origin     // Catch:{ all -> 0x05ed }
            r5.zza((android.os.Bundle) r15, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            r10 = r22
            boolean r5 = r5.zzcj(r10)     // Catch:{ all -> 0x05ed }
            if (r5 == 0) goto L_0x0264
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = "_dbg"
            java.lang.Long r7 = java.lang.Long.valueOf(r20)     // Catch:{ all -> 0x05ed }
            r5.zza((android.os.Bundle) r15, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = "_r"
            java.lang.Long r7 = java.lang.Long.valueOf(r20)     // Catch:{ all -> 0x05ed }
            r5.zza((android.os.Bundle) r15, (java.lang.String) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x05ed }
        L_0x0264:
            com.google.android.gms.internal.measurement.zzei r5 = r34.zzix()     // Catch:{ all -> 0x05ed }
            long r5 = r5.zzbd(r10)     // Catch:{ all -> 0x05ed }
            int r7 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r7 <= 0) goto L_0x0285
            com.google.android.gms.internal.measurement.zzfg r7 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzip()     // Catch:{ all -> 0x05ed }
            java.lang.String r8 = "Data lost. Too many events stored on disk, deleted. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r10)     // Catch:{ all -> 0x05ed }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x05ed }
            r7.zze(r8, r9, r5)     // Catch:{ all -> 0x05ed }
        L_0x0285:
            com.google.android.gms.internal.measurement.zzep r11 = new com.google.android.gms.internal.measurement.zzep     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzgl r6 = r1.zzacw     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = r0.origin     // Catch:{ all -> 0x05ed }
            java.lang.String r9 = r0.name     // Catch:{ all -> 0x05ed }
            long r12 = r0.zzagb     // Catch:{ all -> 0x05ed }
            r17 = 0
            r5 = r11
            r8 = r10
            r23 = r3
            r0 = r10
            r3 = r11
            r10 = r12
            r12 = r17
            r4 = 0
            r14 = r15
            r5.<init>((com.google.android.gms.internal.measurement.zzgl) r6, (java.lang.String) r7, (java.lang.String) r8, (java.lang.String) r9, (long) r10, (long) r12, (android.os.Bundle) r14)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r5 = r34.zzix()     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r3.name     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzeq r5 = r5.zzf(r0, r6)     // Catch:{ all -> 0x05ed }
            if (r5 != 0) goto L_0x030a
            com.google.android.gms.internal.measurement.zzei r5 = r34.zzix()     // Catch:{ all -> 0x05ed }
            long r5 = r5.zzbg(r0)     // Catch:{ all -> 0x05ed }
            r7 = 500(0x1f4, double:2.47E-321)
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 < 0) goto L_0x02f1
            if (r16 == 0) goto L_0x02f1
            com.google.android.gms.internal.measurement.zzfg r2 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x05ed }
            java.lang.String r4 = "Too many event names used, ignoring event. appId, name, supported count"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfg.zzbm(r0)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfe r6 = r34.zzga()     // Catch:{ all -> 0x05ed }
            java.lang.String r3 = r3.name     // Catch:{ all -> 0x05ed }
            java.lang.String r3 = r6.zzbj(r3)     // Catch:{ all -> 0x05ed }
            r6 = 500(0x1f4, float:7.0E-43)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x05ed }
            r2.zzd(r4, r5, r3, r6)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzka r5 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            r7 = 8
            r8 = 0
            r9 = 0
            r10 = 0
            r6 = r0
            r5.zza((java.lang.String) r6, (int) r7, (java.lang.String) r8, (java.lang.String) r9, (int) r10)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            r0.endTransaction()
            return
        L_0x02f1:
            com.google.android.gms.internal.measurement.zzeq r20 = new com.google.android.gms.internal.measurement.zzeq     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = r3.name     // Catch:{ all -> 0x05ed }
            r8 = 0
            r10 = 0
            long r12 = r3.timestamp     // Catch:{ all -> 0x05ed }
            r14 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            r5 = r20
            r6 = r0
            r5.<init>(r6, r7, r8, r10, r12, r14, r16, r17, r18)     // Catch:{ all -> 0x05ed }
            goto L_0x0319
        L_0x030a:
            com.google.android.gms.internal.measurement.zzgl r0 = r1.zzacw     // Catch:{ all -> 0x05ed }
            long r6 = r5.zzaft     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzep r11 = r3.zza(r0, r6)     // Catch:{ all -> 0x05ed }
            long r6 = r11.timestamp     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzeq r20 = r5.zzac(r6)     // Catch:{ all -> 0x05ed }
            r3 = r11
        L_0x0319:
            r0 = r20
            com.google.android.gms.internal.measurement.zzei r5 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r5.zza((com.google.android.gms.internal.measurement.zzeq) r0)     // Catch:{ all -> 0x05ed }
            r34.zzab()     // Catch:{ all -> 0x05ed }
            r34.zzkq()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r36)     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r3.zzti     // Catch:{ all -> 0x05ed }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r3.zzti     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = r2.packageName     // Catch:{ all -> 0x05ed }
            boolean r0 = r0.equals(r5)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.common.internal.Preconditions.checkArgument(r0)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzkq r5 = new com.google.android.gms.internal.measurement.zzkq     // Catch:{ all -> 0x05ed }
            r5.<init>()     // Catch:{ all -> 0x05ed }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r19)     // Catch:{ all -> 0x05ed }
            r5.zzath = r0     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = "android"
            r5.zzatp = r0     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r2.packageName     // Catch:{ all -> 0x05ed }
            r5.zzti = r0     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r2.zzadt     // Catch:{ all -> 0x05ed }
            r5.zzadt = r0     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r2.zzth     // Catch:{ all -> 0x05ed }
            r5.zzth = r0     // Catch:{ all -> 0x05ed }
            long r6 = r2.zzads     // Catch:{ all -> 0x05ed }
            r8 = -2147483648(0xffffffff80000000, double:NaN)
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r6 = 0
            if (r0 != 0) goto L_0x0365
            r0 = r6
            goto L_0x036c
        L_0x0365:
            long r7 = r2.zzads     // Catch:{ all -> 0x05ed }
            int r0 = (int) r7     // Catch:{ all -> 0x05ed }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x05ed }
        L_0x036c:
            r5.zzaub = r0     // Catch:{ all -> 0x05ed }
            long r7 = r2.zzadu     // Catch:{ all -> 0x05ed }
            java.lang.Long r0 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x05ed }
            r5.zzatt = r0     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r2.zzadm     // Catch:{ all -> 0x05ed }
            r5.zzadm = r0     // Catch:{ all -> 0x05ed }
            long r7 = r2.zzadv     // Catch:{ all -> 0x05ed }
            r9 = 0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 != 0) goto L_0x0384
            r0 = r6
            goto L_0x038a
        L_0x0384:
            long r7 = r2.zzadv     // Catch:{ all -> 0x05ed }
            java.lang.Long r0 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x05ed }
        L_0x038a:
            r5.zzatx = r0     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfr r0 = r34.zzgf()     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x05ed }
            android.util.Pair r0 = r0.zzbo(r7)     // Catch:{ all -> 0x05ed }
            if (r0 == 0) goto L_0x03b3
            java.lang.Object r7 = r0.first     // Catch:{ all -> 0x05ed }
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ all -> 0x05ed }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x05ed }
            if (r7 != 0) goto L_0x03b3
            boolean r7 = r2.zzady     // Catch:{ all -> 0x05ed }
            if (r7 == 0) goto L_0x0406
            java.lang.Object r7 = r0.first     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ all -> 0x05ed }
            r5.zzatv = r7     // Catch:{ all -> 0x05ed }
            java.lang.Object r0 = r0.second     // Catch:{ all -> 0x05ed }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x05ed }
            r5.zzatw = r0     // Catch:{ all -> 0x05ed }
            goto L_0x0406
        L_0x03b3:
            com.google.android.gms.internal.measurement.zzeo r0 = r34.zzfw()     // Catch:{ all -> 0x05ed }
            android.content.Context r7 = r34.getContext()     // Catch:{ all -> 0x05ed }
            boolean r0 = r0.zzf(r7)     // Catch:{ all -> 0x05ed }
            if (r0 != 0) goto L_0x0406
            boolean r0 = r2.zzadz     // Catch:{ all -> 0x05ed }
            if (r0 == 0) goto L_0x0406
            android.content.Context r0 = r34.getContext()     // Catch:{ all -> 0x05ed }
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r7)     // Catch:{ all -> 0x05ed }
            if (r0 != 0) goto L_0x03eb
            com.google.android.gms.internal.measurement.zzfg r0 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = "null secure ID. appId"
            java.lang.String r8 = r5.zzti     // Catch:{ all -> 0x05ed }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x05ed }
            r0.zzg(r7, r8)     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = "null"
            goto L_0x0404
        L_0x03eb:
            boolean r7 = r0.isEmpty()     // Catch:{ all -> 0x05ed }
            if (r7 == 0) goto L_0x0404
            com.google.android.gms.internal.measurement.zzfg r7 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzip()     // Catch:{ all -> 0x05ed }
            java.lang.String r8 = "empty secure ID. appId"
            java.lang.String r11 = r5.zzti     // Catch:{ all -> 0x05ed }
            java.lang.Object r11 = com.google.android.gms.internal.measurement.zzfg.zzbm(r11)     // Catch:{ all -> 0x05ed }
            r7.zzg(r8, r11)     // Catch:{ all -> 0x05ed }
        L_0x0404:
            r5.zzaue = r0     // Catch:{ all -> 0x05ed }
        L_0x0406:
            com.google.android.gms.internal.measurement.zzeo r0 = r34.zzfw()     // Catch:{ all -> 0x05ed }
            r0.zzch()     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = android.os.Build.MODEL     // Catch:{ all -> 0x05ed }
            r5.zzatr = r0     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzeo r0 = r34.zzfw()     // Catch:{ all -> 0x05ed }
            r0.zzch()     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x05ed }
            r5.zzatq = r0     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzeo r0 = r34.zzfw()     // Catch:{ all -> 0x05ed }
            long r7 = r0.zzic()     // Catch:{ all -> 0x05ed }
            int r0 = (int) r7     // Catch:{ all -> 0x05ed }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x05ed }
            r5.zzats = r0     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzeo r0 = r34.zzfw()     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r0.zzid()     // Catch:{ all -> 0x05ed }
            r5.zzafn = r0     // Catch:{ all -> 0x05ed }
            r5.zzatu = r6     // Catch:{ all -> 0x05ed }
            r5.zzatk = r6     // Catch:{ all -> 0x05ed }
            r5.zzatl = r6     // Catch:{ all -> 0x05ed }
            r5.zzatm = r6     // Catch:{ all -> 0x05ed }
            long r7 = r2.zzadx     // Catch:{ all -> 0x05ed }
            java.lang.Long r0 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x05ed }
            r5.zzaug = r0     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzgl r0 = r1.zzacw     // Catch:{ all -> 0x05ed }
            boolean r0 = r0.isEnabled()     // Catch:{ all -> 0x05ed }
            if (r0 == 0) goto L_0x0455
            boolean r0 = com.google.android.gms.internal.measurement.zzef.zzhk()     // Catch:{ all -> 0x05ed }
            if (r0 == 0) goto L_0x0455
            r5.zzauh = r6     // Catch:{ all -> 0x05ed }
        L_0x0455:
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r2.packageName     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzdy r0 = r0.zzbc(r6)     // Catch:{ all -> 0x05ed }
            if (r0 != 0) goto L_0x04c1
            com.google.android.gms.internal.measurement.zzdy r0 = new com.google.android.gms.internal.measurement.zzdy     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzgl r6 = r1.zzacw     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x05ed }
            r0.<init>(r6, r7)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzgl r6 = r1.zzacw     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfb r6 = r6.zzfv()     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r6.zzii()     // Catch:{ all -> 0x05ed }
            r0.zzal(r6)     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r2.zzado     // Catch:{ all -> 0x05ed }
            r0.zzao(r6)     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r2.zzadm     // Catch:{ all -> 0x05ed }
            r0.zzam(r6)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfr r6 = r34.zzgf()     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r6.zzbp(r7)     // Catch:{ all -> 0x05ed }
            r0.zzan(r6)     // Catch:{ all -> 0x05ed }
            r0.zzr(r9)     // Catch:{ all -> 0x05ed }
            r0.zzm(r9)     // Catch:{ all -> 0x05ed }
            r0.zzn(r9)     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r2.zzth     // Catch:{ all -> 0x05ed }
            r0.setAppVersion(r6)     // Catch:{ all -> 0x05ed }
            long r6 = r2.zzads     // Catch:{ all -> 0x05ed }
            r0.zzo(r6)     // Catch:{ all -> 0x05ed }
            java.lang.String r6 = r2.zzadt     // Catch:{ all -> 0x05ed }
            r0.zzap(r6)     // Catch:{ all -> 0x05ed }
            long r6 = r2.zzadu     // Catch:{ all -> 0x05ed }
            r0.zzp(r6)     // Catch:{ all -> 0x05ed }
            long r6 = r2.zzadv     // Catch:{ all -> 0x05ed }
            r0.zzq(r6)     // Catch:{ all -> 0x05ed }
            boolean r6 = r2.zzadw     // Catch:{ all -> 0x05ed }
            r0.setMeasurementEnabled(r6)     // Catch:{ all -> 0x05ed }
            long r6 = r2.zzadx     // Catch:{ all -> 0x05ed }
            r0.zzaa(r6)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r6 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r6.zza((com.google.android.gms.internal.measurement.zzdy) r0)     // Catch:{ all -> 0x05ed }
        L_0x04c1:
            java.lang.String r6 = r0.getAppInstanceId()     // Catch:{ all -> 0x05ed }
            r5.zzadl = r6     // Catch:{ all -> 0x05ed }
            java.lang.String r0 = r0.zzgj()     // Catch:{ all -> 0x05ed }
            r5.zzado = r0     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            java.lang.String r2 = r2.packageName     // Catch:{ all -> 0x05ed }
            java.util.List r0 = r0.zzbb(r2)     // Catch:{ all -> 0x05ed }
            int r2 = r0.size()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzks[] r2 = new com.google.android.gms.internal.measurement.zzks[r2]     // Catch:{ all -> 0x05ed }
            r5.zzatj = r2     // Catch:{ all -> 0x05ed }
            r2 = 0
        L_0x04e0:
            int r6 = r0.size()     // Catch:{ all -> 0x05ed }
            if (r2 >= r6) goto L_0x0519
            com.google.android.gms.internal.measurement.zzks r6 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x05ed }
            r6.<init>()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzks[] r7 = r5.zzatj     // Catch:{ all -> 0x05ed }
            r7[r2] = r6     // Catch:{ all -> 0x05ed }
            java.lang.Object r7 = r0.get(r2)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzjz r7 = (com.google.android.gms.internal.measurement.zzjz) r7     // Catch:{ all -> 0x05ed }
            java.lang.String r7 = r7.name     // Catch:{ all -> 0x05ed }
            r6.name = r7     // Catch:{ all -> 0x05ed }
            java.lang.Object r7 = r0.get(r2)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzjz r7 = (com.google.android.gms.internal.measurement.zzjz) r7     // Catch:{ all -> 0x05ed }
            long r7 = r7.zzaqz     // Catch:{ all -> 0x05ed }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x05ed }
            r6.zzaun = r7     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzka r7 = r34.zzgb()     // Catch:{ all -> 0x05ed }
            java.lang.Object r8 = r0.get(r2)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzjz r8 = (com.google.android.gms.internal.measurement.zzjz) r8     // Catch:{ all -> 0x05ed }
            java.lang.Object r8 = r8.value     // Catch:{ all -> 0x05ed }
            r7.zza((com.google.android.gms.internal.measurement.zzks) r6, (java.lang.Object) r8)     // Catch:{ all -> 0x05ed }
            int r2 = r2 + 1
            goto L_0x04e0
        L_0x0519:
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ IOException -> 0x0588 }
            long r6 = r0.zza((com.google.android.gms.internal.measurement.zzkq) r5)     // Catch:{ IOException -> 0x0588 }
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzer r2 = r3.zzafq     // Catch:{ all -> 0x05ed }
            if (r2 == 0) goto L_0x057f
            com.google.android.gms.internal.measurement.zzer r2 = r3.zzafq     // Catch:{ all -> 0x05ed }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x05ed }
        L_0x052f:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x05ed }
            if (r5 == 0) goto L_0x0545
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x05ed }
            java.lang.String r8 = "_r"
            boolean r5 = r8.equals(r5)     // Catch:{ all -> 0x05ed }
            if (r5 == 0) goto L_0x052f
        L_0x0543:
            r4 = 1
            goto L_0x057f
        L_0x0545:
            com.google.android.gms.internal.measurement.zzgf r2 = r34.zzkm()     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = r3.zzti     // Catch:{ all -> 0x05ed }
            java.lang.String r8 = r3.name     // Catch:{ all -> 0x05ed }
            boolean r2 = r2.zzo(r5, r8)     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzei r25 = r34.zzix()     // Catch:{ all -> 0x05ed }
            long r26 = r34.zzkr()     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = r3.zzti     // Catch:{ all -> 0x05ed }
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r33 = 0
            r28 = r5
            com.google.android.gms.internal.measurement.zzej r5 = r25.zza(r26, r28, r29, r30, r31, r32, r33)     // Catch:{ all -> 0x05ed }
            if (r2 == 0) goto L_0x057f
            long r11 = r5.zzafh     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzef r2 = r34.zzgg()     // Catch:{ all -> 0x05ed }
            java.lang.String r5 = r3.zzti     // Catch:{ all -> 0x05ed }
            int r2 = r2.zzar(r5)     // Catch:{ all -> 0x05ed }
            long r13 = (long) r2     // Catch:{ all -> 0x05ed }
            int r2 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r2 >= 0) goto L_0x057f
            goto L_0x0543
        L_0x057f:
            boolean r0 = r0.zza((com.google.android.gms.internal.measurement.zzep) r3, (long) r6, (boolean) r4)     // Catch:{ all -> 0x05ed }
            if (r0 == 0) goto L_0x059c
            r1.zzaqh = r9     // Catch:{ all -> 0x05ed }
            goto L_0x059c
        L_0x0588:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r2 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x05ed }
            java.lang.String r4 = "Data loss. Failed to insert raw event metadata. appId"
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x05ed }
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfg.zzbm(r5)     // Catch:{ all -> 0x05ed }
            r2.zze(r4, r5, r0)     // Catch:{ all -> 0x05ed }
        L_0x059c:
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()     // Catch:{ all -> 0x05ed }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfg r0 = r34.zzge()     // Catch:{ all -> 0x05ed }
            r2 = 2
            boolean r0 = r0.isLoggable(r2)     // Catch:{ all -> 0x05ed }
            if (r0 == 0) goto L_0x05c3
            com.google.android.gms.internal.measurement.zzfg r0 = r34.zzge()     // Catch:{ all -> 0x05ed }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()     // Catch:{ all -> 0x05ed }
            java.lang.String r2 = "Event recorded"
            com.google.android.gms.internal.measurement.zzfe r4 = r34.zzga()     // Catch:{ all -> 0x05ed }
            java.lang.String r3 = r4.zza((com.google.android.gms.internal.measurement.zzep) r3)     // Catch:{ all -> 0x05ed }
            r0.zzg(r2, r3)     // Catch:{ all -> 0x05ed }
        L_0x05c3:
            com.google.android.gms.internal.measurement.zzei r0 = r34.zzix()
            r0.endTransaction()
            r34.zzku()
            com.google.android.gms.internal.measurement.zzfg r0 = r34.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()
            java.lang.String r2 = "Background event processing time, ms"
            long r3 = java.lang.System.nanoTime()
            long r3 = r3 - r23
            r5 = 500000(0x7a120, double:2.47033E-318)
            long r3 = r3 + r5
            r5 = 1000000(0xf4240, double:4.940656E-318)
            long r3 = r3 / r5
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            r0.zzg(r2, r3)
            return
        L_0x05ed:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzei r2 = r34.zzix()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzc(com.google.android.gms.internal.measurement.zzeu, com.google.android.gms.internal.measurement.zzdz):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0227, code lost:
        if (r5 != null) goto L_0x01da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0249, code lost:
        if (r6 == null) goto L_0x0289;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        r1 = r0;
        r21 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0046, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0286, code lost:
        if (r6 != null) goto L_0x024b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
        r6 = null;
        r12 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0087, code lost:
        if (r3 != null) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x009f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a0, code lost:
        r6 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:436:?, code lost:
        r21.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x01d8, code lost:
        if (r5 != null) goto L_0x01da;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0040 A[ExcHandler: all (r0v20 'th' java.lang.Throwable A[CUSTOM_DECLARE]), PHI: r3 
      PHI: (r3v25 android.database.Cursor) = (r3v19 android.database.Cursor), (r3v28 android.database.Cursor), (r3v28 android.database.Cursor), (r3v28 android.database.Cursor), (r3v28 android.database.Cursor), (r3v0 android.database.Cursor), (r3v0 android.database.Cursor) binds: [B:47:0x00e4, B:24:0x0081, B:30:0x008e, B:32:0x0092, B:33:?, B:9:0x0031, B:10:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:9:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x028d A[Catch:{ SQLiteException -> 0x0aa5, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x029b A[Catch:{ SQLiteException -> 0x0aa5, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x0550 A[Catch:{ SQLiteException -> 0x0aa5, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x062b A[Catch:{ SQLiteException -> 0x0aa5, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0645 A[Catch:{ SQLiteException -> 0x0aa5, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x0665 A[Catch:{ SQLiteException -> 0x0aa5, all -> 0x0ae2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:428:0x0ac8 A[SYNTHETIC, Splitter:B:428:0x0ac8] */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x0ade A[SYNTHETIC, Splitter:B:435:0x0ade] */
    /* JADX WARNING: Removed duplicated region for block: B:457:0x0642 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0124 A[SYNTHETIC, Splitter:B:60:0x0124] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0147 A[SYNTHETIC, Splitter:B:69:0x0147] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:28:0x0089=Splitter:B:28:0x0089, B:144:0x0289=Splitter:B:144:0x0289, B:98:0x01da=Splitter:B:98:0x01da, B:123:0x024b=Splitter:B:123:0x024b} */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzd(java.lang.String r64, long r65) {
        /*
            r63 = this;
            r1 = r63
            com.google.android.gms.internal.measurement.zzei r2 = r63.zzix()
            r2.beginTransaction()
            com.google.android.gms.internal.measurement.zzjv r2 = new com.google.android.gms.internal.measurement.zzjv     // Catch:{ all -> 0x0ae2 }
            r3 = 0
            r2.<init>(r1, r3)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzei r4 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            long r5 = r1.zzaqs     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0ae2 }
            r4.zzab()     // Catch:{ all -> 0x0ae2 }
            r4.zzch()     // Catch:{ all -> 0x0ae2 }
            r7 = -1
            r9 = 2
            r10 = 0
            r11 = 1
            android.database.sqlite.SQLiteDatabase r15 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            boolean r12 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            if (r12 == 0) goto L_0x00a2
            int r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x004c
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            java.lang.String r13 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            r12[r10] = r13     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            java.lang.String r13 = java.lang.String.valueOf(r65)     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            r12[r11] = r13     // Catch:{ SQLiteException -> 0x0046, all -> 0x0040 }
            goto L_0x0054
        L_0x0040:
            r0 = move-exception
            r1 = r0
            r21 = r3
            goto L_0x0adc
        L_0x0046:
            r0 = move-exception
            r6 = r3
            r12 = r6
        L_0x0049:
            r3 = r0
            goto L_0x0275
        L_0x004c:
            java.lang.String[] r12 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r13 = java.lang.String.valueOf(r65)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r12[r10] = r13     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
        L_0x0054:
            int r13 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r13 == 0) goto L_0x005b
            java.lang.String r13 = "rowid <= ? and "
            goto L_0x005d
        L_0x005b:
            java.lang.String r13 = ""
        L_0x005d:
            java.lang.String r14 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            int r14 = r14.length()     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            int r14 = r14 + 148
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r3.<init>(r14)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r14 = "select app_id, metadata_fingerprint from raw_events where "
            r3.append(r14)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r3.append(r13)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r13 = "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;"
            r3.append(r13)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            android.database.Cursor r3 = r15.rawQuery(r3, r12)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            boolean r12 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0266, all -> 0x0040 }
            if (r12 != 0) goto L_0x008e
            if (r3 == 0) goto L_0x0289
        L_0x0089:
            r3.close()     // Catch:{ all -> 0x0ae2 }
            goto L_0x0289
        L_0x008e:
            java.lang.String r12 = r3.getString(r10)     // Catch:{ SQLiteException -> 0x0266, all -> 0x0040 }
            java.lang.String r13 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x009f, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x009f, all -> 0x0040 }
            r23 = r3
            r3 = r12
            r22 = r13
            goto L_0x00f9
        L_0x009f:
            r0 = move-exception
            r6 = r3
            goto L_0x0049
        L_0x00a2:
            int r3 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r3 == 0) goto L_0x00b2
            java.lang.String[] r3 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r12 = 0
            r3[r10] = r12     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r12 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r3[r11] = r12     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            goto L_0x00b7
        L_0x00b2:
            java.lang.String[] r3 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r12 = 0
            r3[r10] = r12     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
        L_0x00b7:
            int r12 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x00be
            java.lang.String r12 = " and rowid <= ?"
            goto L_0x00c0
        L_0x00be:
            java.lang.String r12 = ""
        L_0x00c0:
            java.lang.String r13 = java.lang.String.valueOf(r12)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            int r13 = r13.length()     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            int r13 = r13 + 84
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r14.<init>(r13)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r13 = "select metadata_fingerprint from raw_events where app_id = ?"
            r14.append(r13)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            r14.append(r12)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r12 = " order by rowid limit 1;"
            r14.append(r12)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            java.lang.String r12 = r14.toString()     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            android.database.Cursor r3 = r15.rawQuery(r12, r3)     // Catch:{ SQLiteException -> 0x0271, all -> 0x026b }
            boolean r12 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0266, all -> 0x0040 }
            if (r12 != 0) goto L_0x00ed
            if (r3 == 0) goto L_0x0289
            goto L_0x0089
        L_0x00ed:
            java.lang.String r13 = r3.getString(r10)     // Catch:{ SQLiteException -> 0x0266, all -> 0x0040 }
            r3.close()     // Catch:{ SQLiteException -> 0x0266, all -> 0x0040 }
            r23 = r3
            r22 = r13
            r3 = 0
        L_0x00f9:
            java.lang.String r13 = "raw_events_metadata"
            java.lang.String[] r14 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x0260, all -> 0x025a }
            java.lang.String r12 = "metadata"
            r14[r10] = r12     // Catch:{ SQLiteException -> 0x0260, all -> 0x025a }
            java.lang.String r16 = "app_id = ? and metadata_fingerprint = ?"
            java.lang.String[] r12 = new java.lang.String[r9]     // Catch:{ SQLiteException -> 0x0260, all -> 0x025a }
            r12[r10] = r3     // Catch:{ SQLiteException -> 0x0260, all -> 0x025a }
            r12[r11] = r22     // Catch:{ SQLiteException -> 0x0260, all -> 0x025a }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "2"
            r24 = r12
            r12 = r15
            r25 = r15
            r15 = r16
            r16 = r24
            android.database.Cursor r15 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x0260, all -> 0x025a }
            boolean r12 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            if (r12 != 0) goto L_0x0147
            com.google.android.gms.internal.measurement.zzfg r5 = r4.zzge()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzim()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            java.lang.String r6 = "Raw event metadata record is missing. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            r5.zzg(r6, r12)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            if (r15 == 0) goto L_0x0289
            r15.close()     // Catch:{ all -> 0x0ae2 }
            goto L_0x0289
        L_0x013c:
            r0 = move-exception
            r1 = r0
            r21 = r15
            goto L_0x0adc
        L_0x0142:
            r0 = move-exception
            r12 = r3
            r6 = r15
            goto L_0x0049
        L_0x0147:
            byte[] r12 = r15.getBlob(r10)     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            int r13 = r12.length     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            com.google.android.gms.internal.measurement.zzabv r12 = com.google.android.gms.internal.measurement.zzabv.zza(r12, r10, r13)     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            com.google.android.gms.internal.measurement.zzkq r13 = new com.google.android.gms.internal.measurement.zzkq     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r13.<init>()     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r13.zzb(r12)     // Catch:{ IOException -> 0x0235 }
            boolean r12 = r15.moveToNext()     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            if (r12 == 0) goto L_0x016f
            com.google.android.gms.internal.measurement.zzfg r12 = r4.zzge()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            com.google.android.gms.internal.measurement.zzfi r12 = r12.zzip()     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            java.lang.String r14 = "Get multiple raw event metadata records, expected one. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            r12.zzg(r14, r9)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
        L_0x016f:
            r15.close()     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r2.zzb(r13)     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            r14 = 3
            if (r9 == 0) goto L_0x018d
            java.lang.String r9 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            r12[r10] = r3     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            r12[r11] = r22     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            r6 = 2
            r12[r6] = r5     // Catch:{ SQLiteException -> 0x0142, all -> 0x013c }
            r5 = r9
            r16 = r12
            goto L_0x0198
        L_0x018d:
            java.lang.String r5 = "app_id = ? and metadata_fingerprint = ?"
            r6 = 2
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r9[r10] = r3     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r9[r11] = r22     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r16 = r9
        L_0x0198:
            java.lang.String r13 = "raw_events"
            r6 = 4
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            java.lang.String r9 = "rowid"
            r6[r10] = r9     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            java.lang.String r9 = "name"
            r6[r11] = r9     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            java.lang.String r9 = "timestamp"
            r12 = 2
            r6[r12] = r9     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            java.lang.String r9 = "data"
            r6[r14] = r9     // Catch:{ SQLiteException -> 0x0255, all -> 0x0251 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            r20 = 0
            r12 = r25
            r9 = 3
            r14 = r6
            r6 = r15
            r15 = r5
            android.database.Cursor r5 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x024f }
            boolean r6 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            if (r6 != 0) goto L_0x01df
            com.google.android.gms.internal.measurement.zzfg r6 = r4.zzge()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzip()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            java.lang.String r9 = "Raw event data disappeared while in transaction. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r6.zzg(r9, r12)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            if (r5 == 0) goto L_0x0289
        L_0x01da:
            r5.close()     // Catch:{ all -> 0x0ae2 }
            goto L_0x0289
        L_0x01df:
            long r12 = r5.getLong(r10)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            byte[] r6 = r5.getBlob(r9)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            int r14 = r6.length     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            com.google.android.gms.internal.measurement.zzabv r6 = com.google.android.gms.internal.measurement.zzabv.zza(r6, r10, r14)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            com.google.android.gms.internal.measurement.zzkn r14 = new com.google.android.gms.internal.measurement.zzkn     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r14.<init>()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r14.zzb(r6)     // Catch:{ IOException -> 0x020e }
            java.lang.String r6 = r5.getString(r11)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r14.name = r6     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r6 = 2
            long r15 = r5.getLong(r6)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            java.lang.Long r6 = java.lang.Long.valueOf(r15)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r14.zzatb = r6     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            boolean r6 = r2.zza(r12, r14)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            if (r6 != 0) goto L_0x0221
            if (r5 == 0) goto L_0x0289
            goto L_0x01da
        L_0x020e:
            r0 = move-exception
            r6 = r0
            com.google.android.gms.internal.measurement.zzfg r12 = r4.zzge()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            com.google.android.gms.internal.measurement.zzfi r12 = r12.zzim()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            java.lang.String r13 = "Data loss. Failed to merge raw event. appId"
            java.lang.Object r14 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            r12.zze(r13, r14, r6)     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
        L_0x0221:
            boolean r6 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x0230, all -> 0x022a }
            if (r6 != 0) goto L_0x01df
            if (r5 == 0) goto L_0x0289
            goto L_0x01da
        L_0x022a:
            r0 = move-exception
            r1 = r0
            r21 = r5
            goto L_0x0adc
        L_0x0230:
            r0 = move-exception
            r12 = r3
            r6 = r5
            goto L_0x0049
        L_0x0235:
            r0 = move-exception
            r5 = r0
            r6 = r15
            com.google.android.gms.internal.measurement.zzfg r9 = r4.zzge()     // Catch:{ SQLiteException -> 0x024f }
            com.google.android.gms.internal.measurement.zzfi r9 = r9.zzim()     // Catch:{ SQLiteException -> 0x024f }
            java.lang.String r12 = "Data loss. Failed to merge raw event metadata. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.zzfg.zzbm(r3)     // Catch:{ SQLiteException -> 0x024f }
            r9.zze(r12, r13, r5)     // Catch:{ SQLiteException -> 0x024f }
            if (r6 == 0) goto L_0x0289
        L_0x024b:
            r6.close()     // Catch:{ all -> 0x0ae2 }
            goto L_0x0289
        L_0x024f:
            r0 = move-exception
            goto L_0x0257
        L_0x0251:
            r0 = move-exception
            r6 = r15
            goto L_0x0ad9
        L_0x0255:
            r0 = move-exception
            r6 = r15
        L_0x0257:
            r12 = r3
            goto L_0x0049
        L_0x025a:
            r0 = move-exception
            r1 = r0
            r21 = r23
            goto L_0x0adc
        L_0x0260:
            r0 = move-exception
            r12 = r3
            r6 = r23
            goto L_0x0049
        L_0x0266:
            r0 = move-exception
            r6 = r3
            r12 = 0
            goto L_0x0049
        L_0x026b:
            r0 = move-exception
            r1 = r0
            r21 = 0
            goto L_0x0adc
        L_0x0271:
            r0 = move-exception
            r3 = r0
            r6 = 0
            r12 = 0
        L_0x0275:
            com.google.android.gms.internal.measurement.zzfg r4 = r4.zzge()     // Catch:{ all -> 0x0ad8 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x0ad8 }
            java.lang.String r5 = "Data loss. Error selecting raw event. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r12)     // Catch:{ all -> 0x0ad8 }
            r4.zze(r5, r9, r3)     // Catch:{ all -> 0x0ad8 }
            if (r6 == 0) goto L_0x0289
            goto L_0x024b
        L_0x0289:
            java.util.List<com.google.android.gms.internal.measurement.zzkn> r3 = r2.zzaqx     // Catch:{ all -> 0x0ae2 }
            if (r3 == 0) goto L_0x0298
            java.util.List<com.google.android.gms.internal.measurement.zzkn> r3 = r2.zzaqx     // Catch:{ all -> 0x0ae2 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0ae2 }
            if (r3 == 0) goto L_0x0296
            goto L_0x0298
        L_0x0296:
            r3 = 0
            goto L_0x0299
        L_0x0298:
            r3 = 1
        L_0x0299:
            if (r3 != 0) goto L_0x0ac8
            com.google.android.gms.internal.measurement.zzkq r3 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.util.List<com.google.android.gms.internal.measurement.zzkn> r4 = r2.zzaqx     // Catch:{ all -> 0x0ae2 }
            int r4 = r4.size()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r4 = new com.google.android.gms.internal.measurement.zzkn[r4]     // Catch:{ all -> 0x0ae2 }
            r3.zzati = r4     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzef r4 = r63.zzgg()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r3.zzti     // Catch:{ all -> 0x0ae2 }
            boolean r4 = r4.zzav(r5)     // Catch:{ all -> 0x0ae2 }
            r9 = 0
            r12 = 0
            r13 = 0
            r14 = 0
        L_0x02b6:
            java.util.List<com.google.android.gms.internal.measurement.zzkn> r7 = r2.zzaqx     // Catch:{ all -> 0x0ae2 }
            int r7 = r7.size()     // Catch:{ all -> 0x0ae2 }
            r16 = 1
            if (r9 >= r7) goto L_0x05a0
            java.util.List<com.google.android.gms.internal.measurement.zzkn> r7 = r2.zzaqx     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn r7 = (com.google.android.gms.internal.measurement.zzkn) r7     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzgf r8 = r63.zzkm()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r5 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r8.zzn(r5, r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 == 0) goto L_0x033e
            com.google.android.gms.internal.measurement.zzfg r5 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "Dropping blacklisted raw event. appId"
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfe r11 = r63.zzga()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r10 = r7.name     // Catch:{ all -> 0x0ae2 }
            java.lang.String r10 = r11.zzbj(r10)     // Catch:{ all -> 0x0ae2 }
            r5.zze(r6, r8, r10)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzgf r5 = r63.zzkm()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r6 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.zzby(r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x0316
            com.google.android.gms.internal.measurement.zzgf r5 = r63.zzkm()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r6 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.zzbz(r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 == 0) goto L_0x0314
            goto L_0x0316
        L_0x0314:
            r5 = 0
            goto L_0x0317
        L_0x0316:
            r5 = 1
        L_0x0317:
            if (r5 != 0) goto L_0x033a
            java.lang.String r5 = "_err"
            java.lang.String r6 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x033a
            com.google.android.gms.internal.measurement.zzka r26 = r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r5 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x0ae2 }
            r28 = 11
            java.lang.String r29 = "_ev"
            java.lang.String r6 = r7.name     // Catch:{ all -> 0x0ae2 }
            r31 = 0
            r27 = r5
            r30 = r6
            r26.zza((java.lang.String) r27, (int) r28, (java.lang.String) r29, (java.lang.String) r30, (int) r31)     // Catch:{ all -> 0x0ae2 }
        L_0x033a:
            r44 = r9
            goto L_0x059a
        L_0x033e:
            com.google.android.gms.internal.measurement.zzgf r5 = r63.zzkm()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r6 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.zzo(r6, r8)     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x035e
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r6 = com.google.android.gms.internal.measurement.zzka.zzcl(r6)     // Catch:{ all -> 0x0ae2 }
            if (r6 == 0) goto L_0x035a
            goto L_0x035e
        L_0x035a:
            r44 = r9
            goto L_0x0540
        L_0x035e:
            com.google.android.gms.internal.measurement.zzko[] r6 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            if (r6 != 0) goto L_0x0367
            r6 = 0
            com.google.android.gms.internal.measurement.zzko[] r8 = new com.google.android.gms.internal.measurement.zzko[r6]     // Catch:{ all -> 0x0ae2 }
            r7.zzata = r8     // Catch:{ all -> 0x0ae2 }
        L_0x0367:
            com.google.android.gms.internal.measurement.zzko[] r6 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r8 = r6.length     // Catch:{ all -> 0x0ae2 }
            r10 = 0
            r11 = 0
            r18 = 0
        L_0x036e:
            if (r10 >= r8) goto L_0x03a5
            r32 = r8
            r8 = r6[r10]     // Catch:{ all -> 0x0ae2 }
            r33 = r6
            java.lang.String r6 = "_c"
            r34 = r12
            java.lang.String r12 = r8.name     // Catch:{ all -> 0x0ae2 }
            boolean r6 = r6.equals(r12)     // Catch:{ all -> 0x0ae2 }
            if (r6 == 0) goto L_0x038a
            java.lang.Long r6 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            r8.zzate = r6     // Catch:{ all -> 0x0ae2 }
            r11 = 1
            goto L_0x039c
        L_0x038a:
            java.lang.String r6 = "_r"
            java.lang.String r12 = r8.name     // Catch:{ all -> 0x0ae2 }
            boolean r6 = r6.equals(r12)     // Catch:{ all -> 0x0ae2 }
            if (r6 == 0) goto L_0x039c
            java.lang.Long r6 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            r8.zzate = r6     // Catch:{ all -> 0x0ae2 }
            r18 = 1
        L_0x039c:
            int r10 = r10 + 1
            r8 = r32
            r6 = r33
            r12 = r34
            goto L_0x036e
        L_0x03a5:
            r34 = r12
            if (r11 != 0) goto L_0x03e5
            if (r5 == 0) goto L_0x03e5
            com.google.android.gms.internal.measurement.zzfg r6 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzit()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = "Marking event as conversion"
            com.google.android.gms.internal.measurement.zzfe r10 = r63.zzga()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r11 = r7.name     // Catch:{ all -> 0x0ae2 }
            java.lang.String r10 = r10.zzbj(r11)     // Catch:{ all -> 0x0ae2 }
            r6.zzg(r8, r10)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r6 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r8 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r8 = r8.length     // Catch:{ all -> 0x0ae2 }
            r10 = 1
            int r8 = r8 + r10
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r8)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r6 = (com.google.android.gms.internal.measurement.zzko[]) r6     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko r8 = new com.google.android.gms.internal.measurement.zzko     // Catch:{ all -> 0x0ae2 }
            r8.<init>()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r10 = "_c"
            r8.name = r10     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r10 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            r8.zzate = r10     // Catch:{ all -> 0x0ae2 }
            int r10 = r6.length     // Catch:{ all -> 0x0ae2 }
            r11 = 1
            int r10 = r10 - r11
            r6[r10] = r8     // Catch:{ all -> 0x0ae2 }
            r7.zzata = r6     // Catch:{ all -> 0x0ae2 }
        L_0x03e5:
            if (r18 != 0) goto L_0x0421
            com.google.android.gms.internal.measurement.zzfg r6 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzit()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = "Marking event as real-time"
            com.google.android.gms.internal.measurement.zzfe r10 = r63.zzga()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r11 = r7.name     // Catch:{ all -> 0x0ae2 }
            java.lang.String r10 = r10.zzbj(r11)     // Catch:{ all -> 0x0ae2 }
            r6.zzg(r8, r10)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r6 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r8 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r8 = r8.length     // Catch:{ all -> 0x0ae2 }
            r10 = 1
            int r8 = r8 + r10
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r8)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r6 = (com.google.android.gms.internal.measurement.zzko[]) r6     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko r8 = new com.google.android.gms.internal.measurement.zzko     // Catch:{ all -> 0x0ae2 }
            r8.<init>()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r10 = "_r"
            r8.name = r10     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r10 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            r8.zzate = r10     // Catch:{ all -> 0x0ae2 }
            int r10 = r6.length     // Catch:{ all -> 0x0ae2 }
            r11 = 1
            int r10 = r10 - r11
            r6[r10] = r8     // Catch:{ all -> 0x0ae2 }
            r7.zzata = r6     // Catch:{ all -> 0x0ae2 }
        L_0x0421:
            com.google.android.gms.internal.measurement.zzei r35 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            long r36 = r63.zzkr()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r6 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x0ae2 }
            r39 = 0
            r40 = 0
            r41 = 0
            r42 = 0
            r43 = 1
            r38 = r6
            com.google.android.gms.internal.measurement.zzej r6 = r35.zza(r36, r38, r39, r40, r41, r42, r43)     // Catch:{ all -> 0x0ae2 }
            long r10 = r6.zzafh     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzef r6 = r63.zzgg()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            int r6 = r6.zzar(r8)     // Catch:{ all -> 0x0ae2 }
            r44 = r9
            long r8 = (long) r6     // Catch:{ all -> 0x0ae2 }
            int r6 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r6 <= 0) goto L_0x048a
            r6 = 0
        L_0x0453:
            com.google.android.gms.internal.measurement.zzko[] r8 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r8 = r8.length     // Catch:{ all -> 0x0ae2 }
            if (r6 >= r8) goto L_0x0487
            java.lang.String r8 = "_r"
            com.google.android.gms.internal.measurement.zzko[] r9 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            r9 = r9[r6]     // Catch:{ all -> 0x0ae2 }
            java.lang.String r9 = r9.name     // Catch:{ all -> 0x0ae2 }
            boolean r8 = r8.equals(r9)     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x0484
            com.google.android.gms.internal.measurement.zzko[] r8 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r8 = r8.length     // Catch:{ all -> 0x0ae2 }
            r9 = 1
            int r8 = r8 - r9
            com.google.android.gms.internal.measurement.zzko[] r8 = new com.google.android.gms.internal.measurement.zzko[r8]     // Catch:{ all -> 0x0ae2 }
            if (r6 <= 0) goto L_0x0475
            com.google.android.gms.internal.measurement.zzko[] r9 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            r10 = 0
            java.lang.System.arraycopy(r9, r10, r8, r10, r6)     // Catch:{ all -> 0x0ae2 }
        L_0x0475:
            int r9 = r8.length     // Catch:{ all -> 0x0ae2 }
            if (r6 >= r9) goto L_0x0481
            com.google.android.gms.internal.measurement.zzko[] r9 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r10 = r6 + 1
            int r11 = r8.length     // Catch:{ all -> 0x0ae2 }
            int r11 = r11 - r6
            java.lang.System.arraycopy(r9, r10, r8, r6, r11)     // Catch:{ all -> 0x0ae2 }
        L_0x0481:
            r7.zzata = r8     // Catch:{ all -> 0x0ae2 }
            goto L_0x0487
        L_0x0484:
            int r6 = r6 + 1
            goto L_0x0453
        L_0x0487:
            r12 = r34
            goto L_0x048b
        L_0x048a:
            r12 = 1
        L_0x048b:
            java.lang.String r6 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r6 = com.google.android.gms.internal.measurement.zzka.zzcc(r6)     // Catch:{ all -> 0x0ae2 }
            if (r6 == 0) goto L_0x0540
            if (r5 == 0) goto L_0x0540
            com.google.android.gms.internal.measurement.zzei r26 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            long r27 = r63.zzkr()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r5 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x0ae2 }
            r30 = 0
            r31 = 0
            r32 = 1
            r33 = 0
            r34 = 0
            r29 = r5
            com.google.android.gms.internal.measurement.zzej r5 = r26.zza(r27, r29, r30, r31, r32, r33, r34)     // Catch:{ all -> 0x0ae2 }
            long r5 = r5.zzaff     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzef r8 = r63.zzgg()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r9 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r9 = r9.zzti     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r10 = com.google.android.gms.internal.measurement.zzew.zzagv     // Catch:{ all -> 0x0ae2 }
            int r8 = r8.zzb(r9, r10)     // Catch:{ all -> 0x0ae2 }
            long r8 = (long) r8     // Catch:{ all -> 0x0ae2 }
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x0540
            com.google.android.gms.internal.measurement.zzfg r5 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "Too many conversions. Not logging as conversion. appId"
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x0ae2 }
            r5.zzg(r6, r8)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r5 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r6 = r5.length     // Catch:{ all -> 0x0ae2 }
            r8 = 0
            r9 = 0
            r10 = 0
        L_0x04e1:
            if (r8 >= r6) goto L_0x0507
            r11 = r5[r8]     // Catch:{ all -> 0x0ae2 }
            r45 = r5
            java.lang.String r5 = "_c"
            r46 = r6
            java.lang.String r6 = r11.name     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 == 0) goto L_0x04f5
            r10 = r11
            goto L_0x0500
        L_0x04f5:
            java.lang.String r5 = "_err"
            java.lang.String r6 = r11.name     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 == 0) goto L_0x0500
            r9 = 1
        L_0x0500:
            int r8 = r8 + 1
            r5 = r45
            r6 = r46
            goto L_0x04e1
        L_0x0507:
            if (r9 == 0) goto L_0x051c
            if (r10 == 0) goto L_0x051c
            com.google.android.gms.internal.measurement.zzko[] r5 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            r6 = 1
            com.google.android.gms.internal.measurement.zzko[] r8 = new com.google.android.gms.internal.measurement.zzko[r6]     // Catch:{ all -> 0x0ae2 }
            r6 = 0
            r8[r6] = r10     // Catch:{ all -> 0x0ae2 }
            java.lang.Object[] r5 = com.google.android.gms.common.util.ArrayUtils.removeAll(r5, r8)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r5 = (com.google.android.gms.internal.measurement.zzko[]) r5     // Catch:{ all -> 0x0ae2 }
            r7.zzata = r5     // Catch:{ all -> 0x0ae2 }
            goto L_0x0540
        L_0x051c:
            if (r10 == 0) goto L_0x052b
            java.lang.String r5 = "_err"
            r10.name = r5     // Catch:{ all -> 0x0ae2 }
            r5 = 10
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0ae2 }
            r10.zzate = r5     // Catch:{ all -> 0x0ae2 }
            goto L_0x0540
        L_0x052b:
            com.google.android.gms.internal.measurement.zzfg r5 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzim()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "Did not find conversion parameter. appId"
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x0ae2 }
            r5.zzg(r6, r8)     // Catch:{ all -> 0x0ae2 }
        L_0x0540:
            if (r4 == 0) goto L_0x0593
            java.lang.String r5 = "_e"
            java.lang.String r6 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r5 = r5.equals(r6)     // Catch:{ all -> 0x0ae2 }
            if (r5 == 0) goto L_0x0593
            com.google.android.gms.internal.measurement.zzko[] r5 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            if (r5 == 0) goto L_0x0580
            com.google.android.gms.internal.measurement.zzko[] r5 = r7.zzata     // Catch:{ all -> 0x0ae2 }
            int r5 = r5.length     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x0556
            goto L_0x0580
        L_0x0556:
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = "_et"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzka.zzb(r7, r5)     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x0579
            com.google.android.gms.internal.measurement.zzfg r5 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "Engagement event does not include duration. appId"
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x0ae2 }
        L_0x0575:
            r5.zzg(r6, r8)     // Catch:{ all -> 0x0ae2 }
            goto L_0x0593
        L_0x0579:
            long r5 = r5.longValue()     // Catch:{ all -> 0x0ae2 }
            r8 = 0
            long r14 = r14 + r5
            goto L_0x0593
        L_0x0580:
            com.google.android.gms.internal.measurement.zzfg r5 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "Engagement event does not contain any parameters. appId"
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x0ae2 }
            goto L_0x0575
        L_0x0593:
            com.google.android.gms.internal.measurement.zzkn[] r5 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r6 = r13 + 1
            r5[r13] = r7     // Catch:{ all -> 0x0ae2 }
            r13 = r6
        L_0x059a:
            int r9 = r44 + 1
            r10 = 0
            r11 = 1
            goto L_0x02b6
        L_0x05a0:
            r34 = r12
            java.util.List<com.google.android.gms.internal.measurement.zzkn> r5 = r2.zzaqx     // Catch:{ all -> 0x0ae2 }
            int r5 = r5.size()     // Catch:{ all -> 0x0ae2 }
            if (r13 >= r5) goto L_0x05b4
            com.google.android.gms.internal.measurement.zzkn[] r5 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r13)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r5 = (com.google.android.gms.internal.measurement.zzkn[]) r5     // Catch:{ all -> 0x0ae2 }
            r3.zzati = r5     // Catch:{ all -> 0x0ae2 }
        L_0x05b4:
            if (r4 == 0) goto L_0x067b
            com.google.android.gms.internal.measurement.zzei r4 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r3.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "_lte"
            com.google.android.gms.internal.measurement.zzjz r4 = r4.zzh(r5, r6)     // Catch:{ all -> 0x0ae2 }
            if (r4 == 0) goto L_0x05ed
            java.lang.Object r5 = r4.value     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x05c9
            goto L_0x05ed
        L_0x05c9:
            com.google.android.gms.internal.measurement.zzjz r5 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ all -> 0x0ae2 }
            java.lang.String r7 = r3.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = "auto"
            java.lang.String r9 = "_lte"
            com.google.android.gms.common.util.Clock r6 = r63.zzbt()     // Catch:{ all -> 0x0ae2 }
            long r10 = r6.currentTimeMillis()     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x0ae2 }
            long r12 = r4.longValue()     // Catch:{ all -> 0x0ae2 }
            r4 = 0
            long r12 = r12 + r14
            java.lang.Long r12 = java.lang.Long.valueOf(r12)     // Catch:{ all -> 0x0ae2 }
            r6 = r5
            r6.<init>(r7, r8, r9, r10, r12)     // Catch:{ all -> 0x0ae2 }
            r4 = r5
            goto L_0x0608
        L_0x05ed:
            com.google.android.gms.internal.measurement.zzjz r4 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r3.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r28 = "auto"
            java.lang.String r29 = "_lte"
            com.google.android.gms.common.util.Clock r6 = r63.zzbt()     // Catch:{ all -> 0x0ae2 }
            long r30 = r6.currentTimeMillis()     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r32 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0ae2 }
            r26 = r4
            r27 = r5
            r26.<init>(r27, r28, r29, r30, r32)     // Catch:{ all -> 0x0ae2 }
        L_0x0608:
            com.google.android.gms.internal.measurement.zzks r5 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x0ae2 }
            r5.<init>()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "_lte"
            r5.name = r6     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.common.util.Clock r6 = r63.zzbt()     // Catch:{ all -> 0x0ae2 }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x0ae2 }
            r5.zzaun = r6     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r6 = r4.value     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x0ae2 }
            r5.zzate = r6     // Catch:{ all -> 0x0ae2 }
            r6 = 0
        L_0x0626:
            com.google.android.gms.internal.measurement.zzks[] r7 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            int r7 = r7.length     // Catch:{ all -> 0x0ae2 }
            if (r6 >= r7) goto L_0x0642
            java.lang.String r7 = "_lte"
            com.google.android.gms.internal.measurement.zzks[] r8 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            r8 = r8[r6]     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.name     // Catch:{ all -> 0x0ae2 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x0ae2 }
            if (r7 == 0) goto L_0x063f
            com.google.android.gms.internal.measurement.zzks[] r7 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            r7[r6] = r5     // Catch:{ all -> 0x0ae2 }
            r6 = 1
            goto L_0x0643
        L_0x063f:
            int r6 = r6 + 1
            goto L_0x0626
        L_0x0642:
            r6 = 0
        L_0x0643:
            if (r6 != 0) goto L_0x065f
            com.google.android.gms.internal.measurement.zzks[] r6 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzks[] r7 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            int r7 = r7.length     // Catch:{ all -> 0x0ae2 }
            r8 = 1
            int r7 = r7 + r8
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzks[] r6 = (com.google.android.gms.internal.measurement.zzks[]) r6     // Catch:{ all -> 0x0ae2 }
            r3.zzatj = r6     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzks[] r6 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r7 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzks[] r7 = r7.zzatj     // Catch:{ all -> 0x0ae2 }
            int r7 = r7.length     // Catch:{ all -> 0x0ae2 }
            r8 = 1
            int r7 = r7 - r8
            r6[r7] = r5     // Catch:{ all -> 0x0ae2 }
        L_0x065f:
            r5 = 0
            int r7 = (r14 > r5 ? 1 : (r14 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x067b
            com.google.android.gms.internal.measurement.zzei r5 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            r5.zza((com.google.android.gms.internal.measurement.zzjz) r4)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfg r5 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzis()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "Updated lifetime engagement user property with value. Value"
            java.lang.Object r4 = r4.value     // Catch:{ all -> 0x0ae2 }
            r5.zzg(r6, r4)     // Catch:{ all -> 0x0ae2 }
        L_0x067b:
            java.lang.String r4 = r3.zzti     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzks[] r5 = r3.zzatj     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r6 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkm[] r4 = r1.zza(r4, r5, r6)     // Catch:{ all -> 0x0ae2 }
            r3.zzaua = r4     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzef r4 = r63.zzgg()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r5 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x0ae2 }
            boolean r4 = r4.zzau(r5)     // Catch:{ all -> 0x0ae2 }
            if (r4 == 0) goto L_0x0913
            java.util.HashMap r4 = new java.util.HashMap     // Catch:{ all -> 0x0ae2 }
            r4.<init>()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r5 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r5 = r5.length     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r5 = new com.google.android.gms.internal.measurement.zzkn[r5]     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzka r6 = r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            java.security.SecureRandom r6 = r6.zzlc()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r7 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r8 = r7.length     // Catch:{ all -> 0x0ae2 }
            r9 = 0
            r10 = 0
        L_0x06ac:
            if (r9 >= r8) goto L_0x08e4
            r11 = r7[r9]     // Catch:{ all -> 0x0ae2 }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0ae2 }
            java.lang.String r13 = "_ep"
            boolean r12 = r12.equals(r13)     // Catch:{ all -> 0x0ae2 }
            if (r12 == 0) goto L_0x0727
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r12 = "_en"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzka.zzb(r11, r12)     // Catch:{ all -> 0x0ae2 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r13 = r4.get(r12)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r13 = (com.google.android.gms.internal.measurement.zzeq) r13     // Catch:{ all -> 0x0ae2 }
            if (r13 != 0) goto L_0x06dc
            com.google.android.gms.internal.measurement.zzei r13 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r14 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r14 = r14.zzti     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r13 = r13.zzf(r14, r12)     // Catch:{ all -> 0x0ae2 }
            r4.put(r12, r13)     // Catch:{ all -> 0x0ae2 }
        L_0x06dc:
            java.lang.Long r12 = r13.zzafv     // Catch:{ all -> 0x0ae2 }
            if (r12 != 0) goto L_0x0721
            java.lang.Long r12 = r13.zzafw     // Catch:{ all -> 0x0ae2 }
            long r14 = r12.longValue()     // Catch:{ all -> 0x0ae2 }
            int r12 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r12 <= 0) goto L_0x06f9
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r12 = r11.zzata     // Catch:{ all -> 0x0ae2 }
            java.lang.String r14 = "_sr"
            java.lang.Long r15 = r13.zzafw     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r12 = com.google.android.gms.internal.measurement.zzka.zza((com.google.android.gms.internal.measurement.zzko[]) r12, (java.lang.String) r14, (java.lang.Object) r15)     // Catch:{ all -> 0x0ae2 }
            r11.zzata = r12     // Catch:{ all -> 0x0ae2 }
        L_0x06f9:
            java.lang.Boolean r12 = r13.zzafx     // Catch:{ all -> 0x0ae2 }
            if (r12 == 0) goto L_0x0716
            java.lang.Boolean r12 = r13.zzafx     // Catch:{ all -> 0x0ae2 }
            boolean r12 = r12.booleanValue()     // Catch:{ all -> 0x0ae2 }
            if (r12 == 0) goto L_0x0716
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r12 = r11.zzata     // Catch:{ all -> 0x0ae2 }
            java.lang.String r13 = "_efs"
            java.lang.Long r14 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r12 = com.google.android.gms.internal.measurement.zzka.zza((com.google.android.gms.internal.measurement.zzko[]) r12, (java.lang.String) r13, (java.lang.Object) r14)     // Catch:{ all -> 0x0ae2 }
            r11.zzata = r12     // Catch:{ all -> 0x0ae2 }
        L_0x0716:
            int r12 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0ae2 }
            r47 = r7
            r48 = r8
            r10 = r12
            goto L_0x08da
        L_0x0721:
            r47 = r7
            r48 = r8
            goto L_0x08da
        L_0x0727:
            java.lang.String r12 = "_dbg"
            java.lang.Long r13 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            boolean r14 = android.text.TextUtils.isEmpty(r12)     // Catch:{ all -> 0x0ae2 }
            if (r14 != 0) goto L_0x0777
            if (r13 != 0) goto L_0x0736
            goto L_0x0777
        L_0x0736:
            com.google.android.gms.internal.measurement.zzko[] r14 = r11.zzata     // Catch:{ all -> 0x0ae2 }
            int r15 = r14.length     // Catch:{ all -> 0x0ae2 }
            r1 = 0
        L_0x073a:
            if (r1 >= r15) goto L_0x0777
            r47 = r7
            r7 = r14[r1]     // Catch:{ all -> 0x0ae2 }
            r48 = r8
            java.lang.String r8 = r7.name     // Catch:{ all -> 0x0ae2 }
            boolean r8 = r12.equals(r8)     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x0770
            boolean r1 = r13 instanceof java.lang.Long     // Catch:{ all -> 0x0ae2 }
            if (r1 == 0) goto L_0x0756
            java.lang.Long r1 = r7.zzate     // Catch:{ all -> 0x0ae2 }
            boolean r1 = r13.equals(r1)     // Catch:{ all -> 0x0ae2 }
            if (r1 != 0) goto L_0x076e
        L_0x0756:
            boolean r1 = r13 instanceof java.lang.String     // Catch:{ all -> 0x0ae2 }
            if (r1 == 0) goto L_0x0762
            java.lang.String r1 = r7.zzajf     // Catch:{ all -> 0x0ae2 }
            boolean r1 = r13.equals(r1)     // Catch:{ all -> 0x0ae2 }
            if (r1 != 0) goto L_0x076e
        L_0x0762:
            boolean r1 = r13 instanceof java.lang.Double     // Catch:{ all -> 0x0ae2 }
            if (r1 == 0) goto L_0x077b
            java.lang.Double r1 = r7.zzarc     // Catch:{ all -> 0x0ae2 }
            boolean r1 = r13.equals(r1)     // Catch:{ all -> 0x0ae2 }
            if (r1 == 0) goto L_0x077b
        L_0x076e:
            r1 = 1
            goto L_0x077c
        L_0x0770:
            int r1 = r1 + 1
            r7 = r47
            r8 = r48
            goto L_0x073a
        L_0x0777:
            r47 = r7
            r48 = r8
        L_0x077b:
            r1 = 0
        L_0x077c:
            if (r1 != 0) goto L_0x078d
            com.google.android.gms.internal.measurement.zzgf r1 = r63.zzkm()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r7 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r7 = r7.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0ae2 }
            int r1 = r1.zzp(r7, r8)     // Catch:{ all -> 0x0ae2 }
            goto L_0x078e
        L_0x078d:
            r1 = 1
        L_0x078e:
            if (r1 > 0) goto L_0x07aa
            com.google.android.gms.internal.measurement.zzfg r7 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = "Sample rate must be positive. event, rate"
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0ae2 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0ae2 }
            r7.zze(r8, r12, r1)     // Catch:{ all -> 0x0ae2 }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0ae2 }
        L_0x07a7:
            r10 = r1
            goto L_0x08da
        L_0x07aa:
            java.lang.String r7 = r11.name     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r7 = r4.get(r7)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r7 = (com.google.android.gms.internal.measurement.zzeq) r7     // Catch:{ all -> 0x0ae2 }
            if (r7 != 0) goto L_0x07fa
            com.google.android.gms.internal.measurement.zzei r7 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zzf(r8, r12)     // Catch:{ all -> 0x0ae2 }
            if (r7 != 0) goto L_0x07fa
            com.google.android.gms.internal.measurement.zzfg r7 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = "Event being bundled has no eventAggregate. appId, eventName"
            com.google.android.gms.internal.measurement.zzkq r12 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r12 = r12.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r13 = r11.name     // Catch:{ all -> 0x0ae2 }
            r7.zze(r8, r12, r13)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r7 = new com.google.android.gms.internal.measurement.zzeq     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r8 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r8.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.String r12 = r11.name     // Catch:{ all -> 0x0ae2 }
            r52 = 1
            r54 = 1
            java.lang.Long r13 = r11.zzatb     // Catch:{ all -> 0x0ae2 }
            long r56 = r13.longValue()     // Catch:{ all -> 0x0ae2 }
            r58 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r49 = r7
            r50 = r8
            r51 = r12
            r49.<init>(r50, r51, r52, r54, r56, r58, r60, r61, r62)     // Catch:{ all -> 0x0ae2 }
        L_0x07fa:
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = "_eid"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzka.zzb(r11, r8)     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x0809
            r12 = 1
            goto L_0x080a
        L_0x0809:
            r12 = 0
        L_0x080a:
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r12)     // Catch:{ all -> 0x0ae2 }
            r13 = 1
            if (r1 != r13) goto L_0x0833
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0ae2 }
            boolean r8 = r12.booleanValue()     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x07a7
            java.lang.Long r8 = r7.zzafv     // Catch:{ all -> 0x0ae2 }
            if (r8 != 0) goto L_0x0827
            java.lang.Long r8 = r7.zzafw     // Catch:{ all -> 0x0ae2 }
            if (r8 != 0) goto L_0x0827
            java.lang.Boolean r8 = r7.zzafx     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x07a7
        L_0x0827:
            r8 = 0
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zza(r8, r8, r8)     // Catch:{ all -> 0x0ae2 }
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0ae2 }
        L_0x082e:
            r4.put(r8, r7)     // Catch:{ all -> 0x0ae2 }
            goto L_0x07a7
        L_0x0833:
            int r13 = r6.nextInt(r1)     // Catch:{ all -> 0x0ae2 }
            if (r13 != 0) goto L_0x086b
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r8 = r11.zzata     // Catch:{ all -> 0x0ae2 }
            java.lang.String r13 = "_sr"
            long r14 = (long) r1     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r1 = com.google.android.gms.internal.measurement.zzka.zza((com.google.android.gms.internal.measurement.zzko[]) r8, (java.lang.String) r13, (java.lang.Object) r1)     // Catch:{ all -> 0x0ae2 }
            r11.zzata = r1     // Catch:{ all -> 0x0ae2 }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0ae2 }
            boolean r8 = r12.booleanValue()     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x085e
            java.lang.Long r8 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0ae2 }
            r10 = 0
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zza(r10, r8, r10)     // Catch:{ all -> 0x0ae2 }
        L_0x085e:
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r10 = r11.zzatb     // Catch:{ all -> 0x0ae2 }
            long r10 = r10.longValue()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zzad(r10)     // Catch:{ all -> 0x0ae2 }
            goto L_0x082e
        L_0x086b:
            long r13 = r7.zzafu     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r15 = r11.zzatb     // Catch:{ all -> 0x0ae2 }
            long r18 = r15.longValue()     // Catch:{ all -> 0x0ae2 }
            r15 = 0
            long r18 = r18 - r13
            long r13 = java.lang.Math.abs(r18)     // Catch:{ all -> 0x0ae2 }
            r18 = 86400000(0x5265c00, double:4.2687272E-316)
            int r15 = (r13 > r18 ? 1 : (r13 == r18 ? 0 : -1))
            if (r15 < 0) goto L_0x08ca
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r8 = r11.zzata     // Catch:{ all -> 0x0ae2 }
            java.lang.String r13 = "_efs"
            java.lang.Long r14 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r8 = com.google.android.gms.internal.measurement.zzka.zza((com.google.android.gms.internal.measurement.zzko[]) r8, (java.lang.String) r13, (java.lang.Object) r14)     // Catch:{ all -> 0x0ae2 }
            r11.zzata = r8     // Catch:{ all -> 0x0ae2 }
            r63.zzgb()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r8 = r11.zzata     // Catch:{ all -> 0x0ae2 }
            java.lang.String r13 = "_sr"
            long r14 = (long) r1     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r1 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzko[] r1 = com.google.android.gms.internal.measurement.zzka.zza((com.google.android.gms.internal.measurement.zzko[]) r8, (java.lang.String) r13, (java.lang.Object) r1)     // Catch:{ all -> 0x0ae2 }
            r11.zzata = r1     // Catch:{ all -> 0x0ae2 }
            int r1 = r10 + 1
            r5[r10] = r11     // Catch:{ all -> 0x0ae2 }
            boolean r8 = r12.booleanValue()     // Catch:{ all -> 0x0ae2 }
            if (r8 == 0) goto L_0x08bc
            java.lang.Long r8 = java.lang.Long.valueOf(r14)     // Catch:{ all -> 0x0ae2 }
            r10 = 1
            java.lang.Boolean r12 = java.lang.Boolean.valueOf(r10)     // Catch:{ all -> 0x0ae2 }
            r10 = 0
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zza(r10, r8, r12)     // Catch:{ all -> 0x0ae2 }
        L_0x08bc:
            java.lang.String r8 = r11.name     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r10 = r11.zzatb     // Catch:{ all -> 0x0ae2 }
            long r10 = r10.longValue()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zzad(r10)     // Catch:{ all -> 0x0ae2 }
            goto L_0x082e
        L_0x08ca:
            boolean r1 = r12.booleanValue()     // Catch:{ all -> 0x0ae2 }
            if (r1 == 0) goto L_0x08da
            java.lang.String r1 = r11.name     // Catch:{ all -> 0x0ae2 }
            r11 = 0
            com.google.android.gms.internal.measurement.zzeq r7 = r7.zza(r8, r11, r11)     // Catch:{ all -> 0x0ae2 }
            r4.put(r1, r7)     // Catch:{ all -> 0x0ae2 }
        L_0x08da:
            int r9 = r9 + 1
            r7 = r47
            r8 = r48
            r1 = r63
            goto L_0x06ac
        L_0x08e4:
            com.google.android.gms.internal.measurement.zzkn[] r1 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r1 = r1.length     // Catch:{ all -> 0x0ae2 }
            if (r10 >= r1) goto L_0x08f1
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r5, r10)     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkn[] r1 = (com.google.android.gms.internal.measurement.zzkn[]) r1     // Catch:{ all -> 0x0ae2 }
            r3.zzati = r1     // Catch:{ all -> 0x0ae2 }
        L_0x08f1:
            java.util.Set r1 = r4.entrySet()     // Catch:{ all -> 0x0ae2 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0ae2 }
        L_0x08f9:
            boolean r4 = r1.hasNext()     // Catch:{ all -> 0x0ae2 }
            if (r4 == 0) goto L_0x0913
            java.lang.Object r4 = r1.next()     // Catch:{ all -> 0x0ae2 }
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzei r5 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r4 = r4.getValue()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzeq r4 = (com.google.android.gms.internal.measurement.zzeq) r4     // Catch:{ all -> 0x0ae2 }
            r5.zza((com.google.android.gms.internal.measurement.zzeq) r4)     // Catch:{ all -> 0x0ae2 }
            goto L_0x08f9
        L_0x0913:
            r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0ae2 }
            r3.zzatl = r1     // Catch:{ all -> 0x0ae2 }
            r4 = -9223372036854775808
            java.lang.Long r1 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0ae2 }
            r3.zzatm = r1     // Catch:{ all -> 0x0ae2 }
            r1 = 0
        L_0x0927:
            com.google.android.gms.internal.measurement.zzkn[] r4 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r4 = r4.length     // Catch:{ all -> 0x0ae2 }
            if (r1 >= r4) goto L_0x095b
            com.google.android.gms.internal.measurement.zzkn[] r4 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            r4 = r4[r1]     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r5 = r4.zzatb     // Catch:{ all -> 0x0ae2 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r7 = r3.zzatl     // Catch:{ all -> 0x0ae2 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0ae2 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 >= 0) goto L_0x0944
            java.lang.Long r5 = r4.zzatb     // Catch:{ all -> 0x0ae2 }
            r3.zzatl = r5     // Catch:{ all -> 0x0ae2 }
        L_0x0944:
            java.lang.Long r5 = r4.zzatb     // Catch:{ all -> 0x0ae2 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r7 = r3.zzatm     // Catch:{ all -> 0x0ae2 }
            long r7 = r7.longValue()     // Catch:{ all -> 0x0ae2 }
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 <= 0) goto L_0x0958
            java.lang.Long r4 = r4.zzatb     // Catch:{ all -> 0x0ae2 }
            r3.zzatm = r4     // Catch:{ all -> 0x0ae2 }
        L_0x0958:
            int r1 = r1 + 1
            goto L_0x0927
        L_0x095b:
            com.google.android.gms.internal.measurement.zzkq r1 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r1 = r1.zzti     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzei r4 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzdy r4 = r4.zzbc(r1)     // Catch:{ all -> 0x0ae2 }
            if (r4 != 0) goto L_0x097f
            com.google.android.gms.internal.measurement.zzfg r4 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = "Bundling raw events w/o app info. appId"
            com.google.android.gms.internal.measurement.zzkq r6 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r6)     // Catch:{ all -> 0x0ae2 }
            r4.zzg(r5, r6)     // Catch:{ all -> 0x0ae2 }
            goto L_0x09db
        L_0x097f:
            com.google.android.gms.internal.measurement.zzkn[] r5 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r5 = r5.length     // Catch:{ all -> 0x0ae2 }
            if (r5 <= 0) goto L_0x09db
            long r5 = r4.zzgl()     // Catch:{ all -> 0x0ae2 }
            r7 = 0
            int r9 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r9 == 0) goto L_0x0993
            java.lang.Long r7 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0ae2 }
            goto L_0x0994
        L_0x0993:
            r7 = 0
        L_0x0994:
            r3.zzato = r7     // Catch:{ all -> 0x0ae2 }
            long r7 = r4.zzgk()     // Catch:{ all -> 0x0ae2 }
            r9 = 0
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 != 0) goto L_0x09a1
            goto L_0x09a2
        L_0x09a1:
            r5 = r7
        L_0x09a2:
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 == 0) goto L_0x09ab
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x0ae2 }
            goto L_0x09ac
        L_0x09ab:
            r5 = 0
        L_0x09ac:
            r3.zzatn = r5     // Catch:{ all -> 0x0ae2 }
            r4.zzgt()     // Catch:{ all -> 0x0ae2 }
            long r5 = r4.zzgq()     // Catch:{ all -> 0x0ae2 }
            int r5 = (int) r5     // Catch:{ all -> 0x0ae2 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0ae2 }
            r3.zzaty = r5     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r5 = r3.zzatl     // Catch:{ all -> 0x0ae2 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0ae2 }
            r4.zzm(r5)     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r5 = r3.zzatm     // Catch:{ all -> 0x0ae2 }
            long r5 = r5.longValue()     // Catch:{ all -> 0x0ae2 }
            r4.zzn(r5)     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r4.zzhb()     // Catch:{ all -> 0x0ae2 }
            r3.zzaek = r5     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzei r5 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            r5.zza((com.google.android.gms.internal.measurement.zzdy) r4)     // Catch:{ all -> 0x0ae2 }
        L_0x09db:
            com.google.android.gms.internal.measurement.zzkn[] r4 = r3.zzati     // Catch:{ all -> 0x0ae2 }
            int r4 = r4.length     // Catch:{ all -> 0x0ae2 }
            if (r4 <= 0) goto L_0x0a27
            com.google.android.gms.internal.measurement.zzgf r4 = r63.zzkm()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkq r5 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = r5.zzti     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzkk r4 = r4.zzbu(r5)     // Catch:{ all -> 0x0ae2 }
            if (r4 == 0) goto L_0x09f8
            java.lang.Long r5 = r4.zzasp     // Catch:{ all -> 0x0ae2 }
            if (r5 != 0) goto L_0x09f3
            goto L_0x09f8
        L_0x09f3:
            java.lang.Long r4 = r4.zzasp     // Catch:{ all -> 0x0ae2 }
        L_0x09f5:
            r3.zzauf = r4     // Catch:{ all -> 0x0ae2 }
            goto L_0x0a1e
        L_0x09f8:
            com.google.android.gms.internal.measurement.zzkq r4 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r4 = r4.zzadm     // Catch:{ all -> 0x0ae2 }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x0ae2 }
            if (r4 == 0) goto L_0x0a09
            r4 = -1
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x0ae2 }
            goto L_0x09f5
        L_0x0a09:
            com.google.android.gms.internal.measurement.zzfg r4 = r63.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzip()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = "Did not find measurement config or missing version info. appId"
            com.google.android.gms.internal.measurement.zzkq r6 = r2.zzaqv     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = r6.zzti     // Catch:{ all -> 0x0ae2 }
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r6)     // Catch:{ all -> 0x0ae2 }
            r4.zzg(r5, r6)     // Catch:{ all -> 0x0ae2 }
        L_0x0a1e:
            com.google.android.gms.internal.measurement.zzei r4 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            r12 = r34
            r4.zza((com.google.android.gms.internal.measurement.zzkq) r3, (boolean) r12)     // Catch:{ all -> 0x0ae2 }
        L_0x0a27:
            com.google.android.gms.internal.measurement.zzei r3 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            java.util.List<java.lang.Long> r2 = r2.zzaqw     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r2)     // Catch:{ all -> 0x0ae2 }
            r3.zzab()     // Catch:{ all -> 0x0ae2 }
            r3.zzch()     // Catch:{ all -> 0x0ae2 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = "rowid in ("
            r4.<init>(r5)     // Catch:{ all -> 0x0ae2 }
            r5 = 0
        L_0x0a3e:
            int r6 = r2.size()     // Catch:{ all -> 0x0ae2 }
            if (r5 >= r6) goto L_0x0a5b
            if (r5 == 0) goto L_0x0a4b
            java.lang.String r6 = ","
            r4.append(r6)     // Catch:{ all -> 0x0ae2 }
        L_0x0a4b:
            java.lang.Object r6 = r2.get(r5)     // Catch:{ all -> 0x0ae2 }
            java.lang.Long r6 = (java.lang.Long) r6     // Catch:{ all -> 0x0ae2 }
            long r6 = r6.longValue()     // Catch:{ all -> 0x0ae2 }
            r4.append(r6)     // Catch:{ all -> 0x0ae2 }
            int r5 = r5 + 1
            goto L_0x0a3e
        L_0x0a5b:
            java.lang.String r5 = ")"
            r4.append(r5)     // Catch:{ all -> 0x0ae2 }
            android.database.sqlite.SQLiteDatabase r5 = r3.getWritableDatabase()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r6 = "raw_events"
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0ae2 }
            r7 = 0
            int r4 = r5.delete(r6, r4, r7)     // Catch:{ all -> 0x0ae2 }
            int r5 = r2.size()     // Catch:{ all -> 0x0ae2 }
            if (r4 == r5) goto L_0x0a8e
            com.google.android.gms.internal.measurement.zzfg r3 = r3.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r5 = "Deleted fewer rows from raw events table than expected"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0ae2 }
            int r2 = r2.size()     // Catch:{ all -> 0x0ae2 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ all -> 0x0ae2 }
            r3.zze(r5, r4, r2)     // Catch:{ all -> 0x0ae2 }
        L_0x0a8e:
            com.google.android.gms.internal.measurement.zzei r2 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            android.database.sqlite.SQLiteDatabase r3 = r2.getWritableDatabase()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r4 = "delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)"
            r5 = 2
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x0aa5 }
            r6 = 0
            r5[r6] = r1     // Catch:{ SQLiteException -> 0x0aa5 }
            r6 = 1
            r5[r6] = r1     // Catch:{ SQLiteException -> 0x0aa5 }
            r3.execSQL(r4, r5)     // Catch:{ SQLiteException -> 0x0aa5 }
            goto L_0x0ab8
        L_0x0aa5:
            r0 = move-exception
            r3 = r0
            com.google.android.gms.internal.measurement.zzfg r2 = r2.zzge()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x0ae2 }
            java.lang.String r4 = "Failed to remove unused event metadata. appId"
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzfg.zzbm(r1)     // Catch:{ all -> 0x0ae2 }
            r2.zze(r4, r1, r3)     // Catch:{ all -> 0x0ae2 }
        L_0x0ab8:
            com.google.android.gms.internal.measurement.zzei r1 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzei r1 = r63.zzix()
            r1.endTransaction()
            r1 = 1
            return r1
        L_0x0ac8:
            com.google.android.gms.internal.measurement.zzei r1 = r63.zzix()     // Catch:{ all -> 0x0ae2 }
            r1.setTransactionSuccessful()     // Catch:{ all -> 0x0ae2 }
            com.google.android.gms.internal.measurement.zzei r1 = r63.zzix()
            r1.endTransaction()
            r1 = 0
            return r1
        L_0x0ad8:
            r0 = move-exception
        L_0x0ad9:
            r1 = r0
            r21 = r6
        L_0x0adc:
            if (r21 == 0) goto L_0x0ae1
            r21.close()     // Catch:{ all -> 0x0ae2 }
        L_0x0ae1:
            throw r1     // Catch:{ all -> 0x0ae2 }
        L_0x0ae2:
            r0 = move-exception
            r1 = r0
            com.google.android.gms.internal.measurement.zzei r2 = r63.zzix()
            r2.endTransaction()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzd(java.lang.String, long):boolean");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x014e  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzdy zzg(com.google.android.gms.internal.measurement.zzdz r9) {
        /*
            r8 = this;
            r8.zzab()
            r8.zzkq()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r9)
            java.lang.String r0 = r9.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            com.google.android.gms.internal.measurement.zzei r0 = r8.zzix()
            java.lang.String r1 = r9.packageName
            com.google.android.gms.internal.measurement.zzdy r0 = r0.zzbc(r1)
            com.google.android.gms.internal.measurement.zzfr r1 = r8.zzgf()
            java.lang.String r2 = r9.packageName
            java.lang.String r1 = r1.zzbp(r2)
            r2 = 1
            if (r0 != 0) goto L_0x0040
            com.google.android.gms.internal.measurement.zzdy r0 = new com.google.android.gms.internal.measurement.zzdy
            com.google.android.gms.internal.measurement.zzgl r3 = r8.zzacw
            java.lang.String r4 = r9.packageName
            r0.<init>(r3, r4)
            com.google.android.gms.internal.measurement.zzgl r3 = r8.zzacw
            com.google.android.gms.internal.measurement.zzfb r3 = r3.zzfv()
            java.lang.String r3 = r3.zzii()
            r0.zzal(r3)
            r0.zzan(r1)
        L_0x003e:
            r1 = 1
            goto L_0x005c
        L_0x0040:
            java.lang.String r3 = r0.zzgi()
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x005b
            r0.zzan(r1)
            com.google.android.gms.internal.measurement.zzgl r1 = r8.zzacw
            com.google.android.gms.internal.measurement.zzfb r1 = r1.zzfv()
            java.lang.String r1 = r1.zzii()
            r0.zzal(r1)
            goto L_0x003e
        L_0x005b:
            r1 = 0
        L_0x005c:
            java.lang.String r3 = r9.zzadm
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0076
            java.lang.String r3 = r9.zzadm
            java.lang.String r4 = r0.getGmpAppId()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0076
            java.lang.String r1 = r9.zzadm
            r0.zzam(r1)
            r1 = 1
        L_0x0076:
            java.lang.String r3 = r9.zzado
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0090
            java.lang.String r3 = r9.zzado
            java.lang.String r4 = r0.zzgj()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0090
            java.lang.String r1 = r9.zzado
            r0.zzao(r1)
            r1 = 1
        L_0x0090:
            long r3 = r9.zzadu
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00a8
            long r3 = r9.zzadu
            long r5 = r0.zzgo()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00a8
            long r3 = r9.zzadu
            r0.zzp(r3)
            r1 = 1
        L_0x00a8:
            java.lang.String r3 = r9.zzth
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x00c2
            java.lang.String r3 = r9.zzth
            java.lang.String r4 = r0.zzag()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00c2
            java.lang.String r1 = r9.zzth
            r0.setAppVersion(r1)
            r1 = 1
        L_0x00c2:
            long r3 = r9.zzads
            long r5 = r0.zzgm()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00d2
            long r3 = r9.zzads
            r0.zzo(r3)
            r1 = 1
        L_0x00d2:
            java.lang.String r3 = r9.zzadt
            if (r3 == 0) goto L_0x00e8
            java.lang.String r3 = r9.zzadt
            java.lang.String r4 = r0.zzgn()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x00e8
            java.lang.String r1 = r9.zzadt
            r0.zzap(r1)
            r1 = 1
        L_0x00e8:
            long r3 = r9.zzadv
            long r5 = r0.zzgp()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00f8
            long r3 = r9.zzadv
            r0.zzq(r3)
            r1 = 1
        L_0x00f8:
            boolean r3 = r9.zzadw
            boolean r4 = r0.isMeasurementEnabled()
            if (r3 == r4) goto L_0x0106
            boolean r1 = r9.zzadw
            r0.setMeasurementEnabled(r1)
            r1 = 1
        L_0x0106:
            java.lang.String r3 = r9.zzaek
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0120
            java.lang.String r3 = r9.zzaek
            java.lang.String r4 = r0.zzha()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0120
            java.lang.String r1 = r9.zzaek
            r0.zzaq(r1)
            r1 = 1
        L_0x0120:
            long r3 = r9.zzadx
            long r5 = r0.zzhc()
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0130
            long r3 = r9.zzadx
            r0.zzaa(r3)
            r1 = 1
        L_0x0130:
            boolean r3 = r9.zzady
            boolean r4 = r0.zzhd()
            if (r3 == r4) goto L_0x013e
            boolean r1 = r9.zzady
            r0.zzd(r1)
            r1 = 1
        L_0x013e:
            boolean r3 = r9.zzadz
            boolean r4 = r0.zzhe()
            if (r3 == r4) goto L_0x014c
            boolean r9 = r9.zzadz
            r0.zze(r9)
            r1 = 1
        L_0x014c:
            if (r1 == 0) goto L_0x0155
            com.google.android.gms.internal.measurement.zzei r9 = r8.zzix()
            r9.zza((com.google.android.gms.internal.measurement.zzdy) r0)
        L_0x0155:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzg(com.google.android.gms.internal.measurement.zzdz):com.google.android.gms.internal.measurement.zzdy");
    }

    private final zzgf zzkm() {
        zza((zzjq) this.zzaqa);
        return this.zzaqa;
    }

    private final zzfp zzko() {
        if (this.zzaqd != null) {
            return this.zzaqd;
        }
        throw new IllegalStateException("Network broadcast receiver not created");
    }

    private final zzjn zzkp() {
        zza((zzjq) this.zzaqe);
        return this.zzaqe;
    }

    private final long zzkr() {
        long currentTimeMillis = zzbt().currentTimeMillis();
        zzfr zzgf = zzgf();
        zzgf.zzch();
        zzgf.zzab();
        long j = zzgf.zzajy.get();
        if (j == 0) {
            j = 1 + ((long) zzgf.zzgb().zzlc().nextInt(86400000));
            zzgf.zzajy.set(j);
        }
        return ((((currentTimeMillis + j) / 1000) / 60) / 60) / 24;
    }

    private final boolean zzkt() {
        zzab();
        zzkq();
        return zzix().zzhs() || !TextUtils.isEmpty(zzix().zzhn());
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0187  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzku() {
        /*
            r20 = this;
            r0 = r20
            r20.zzab()
            r20.zzkq()
            boolean r1 = r20.zzky()
            if (r1 != 0) goto L_0x000f
            return
        L_0x000f:
            long r1 = r0.zzaqh
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0050
            com.google.android.gms.common.util.Clock r1 = r20.zzbt()
            long r1 = r1.elapsedRealtime()
            r5 = 3600000(0x36ee80, double:1.7786363E-317)
            long r7 = r0.zzaqh
            long r1 = r1 - r7
            long r1 = java.lang.Math.abs(r1)
            long r5 = r5 - r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 <= 0) goto L_0x004e
            com.google.android.gms.internal.measurement.zzfg r1 = r20.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "Upload has been suspended. Will update scheduling later in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r5)
            r1.zzg(r2, r3)
            com.google.android.gms.internal.measurement.zzfp r1 = r20.zzko()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzjn r1 = r20.zzkp()
            r1.cancel()
            return
        L_0x004e:
            r0.zzaqh = r3
        L_0x0050:
            com.google.android.gms.internal.measurement.zzgl r1 = r0.zzacw
            boolean r1 = r1.zzjv()
            if (r1 == 0) goto L_0x0224
            boolean r1 = r20.zzkt()
            if (r1 != 0) goto L_0x0060
            goto L_0x0224
        L_0x0060:
            com.google.android.gms.common.util.Clock r1 = r20.zzbt()
            long r1 = r1.currentTimeMillis()
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r5 = com.google.android.gms.internal.measurement.zzew.zzahi
            java.lang.Object r5 = r5.get()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.measurement.zzei r7 = r20.zzix()
            boolean r7 = r7.zzht()
            if (r7 != 0) goto L_0x008f
            com.google.android.gms.internal.measurement.zzei r7 = r20.zzix()
            boolean r7 = r7.zzho()
            if (r7 == 0) goto L_0x008d
            goto L_0x008f
        L_0x008d:
            r7 = 0
            goto L_0x0090
        L_0x008f:
            r7 = 1
        L_0x0090:
            if (r7 == 0) goto L_0x00ae
            com.google.android.gms.internal.measurement.zzef r9 = r20.zzgg()
            java.lang.String r9 = r9.zzhj()
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 != 0) goto L_0x00ab
            java.lang.String r10 = ".none."
            boolean r9 = r10.equals(r9)
            if (r9 != 0) goto L_0x00ab
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r9 = com.google.android.gms.internal.measurement.zzew.zzahd
            goto L_0x00b0
        L_0x00ab:
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r9 = com.google.android.gms.internal.measurement.zzew.zzahc
            goto L_0x00b0
        L_0x00ae:
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r9 = com.google.android.gms.internal.measurement.zzew.zzahb
        L_0x00b0:
            java.lang.Object r9 = r9.get()
            java.lang.Long r9 = (java.lang.Long) r9
            long r9 = r9.longValue()
            long r9 = java.lang.Math.max(r3, r9)
            com.google.android.gms.internal.measurement.zzfr r11 = r20.zzgf()
            com.google.android.gms.internal.measurement.zzfu r11 = r11.zzaju
            long r11 = r11.get()
            com.google.android.gms.internal.measurement.zzfr r13 = r20.zzgf()
            com.google.android.gms.internal.measurement.zzfu r13 = r13.zzajv
            long r13 = r13.get()
            com.google.android.gms.internal.measurement.zzei r15 = r20.zzix()
            r16 = r9
            long r8 = r15.zzhq()
            com.google.android.gms.internal.measurement.zzei r10 = r20.zzix()
            r18 = r5
            long r5 = r10.zzhr()
            long r5 = java.lang.Math.max(r8, r5)
            int r8 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r8 != 0) goto L_0x00f1
        L_0x00ee:
            r8 = r3
            goto L_0x0167
        L_0x00f1:
            r8 = 0
            long r5 = r5 - r1
            long r5 = java.lang.Math.abs(r5)
            long r5 = r1 - r5
            long r11 = r11 - r1
            long r8 = java.lang.Math.abs(r11)
            long r8 = r1 - r8
            long r13 = r13 - r1
            long r10 = java.lang.Math.abs(r13)
            long r1 = r1 - r10
            long r8 = java.lang.Math.max(r8, r1)
            long r10 = r5 + r18
            if (r7 == 0) goto L_0x0118
            int r7 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r7 <= 0) goto L_0x0118
            long r10 = java.lang.Math.min(r5, r8)
            long r10 = r10 + r16
        L_0x0118:
            com.google.android.gms.internal.measurement.zzka r7 = r20.zzgb()
            r12 = r16
            boolean r7 = r7.zza((long) r8, (long) r12)
            if (r7 != 0) goto L_0x0126
            long r8 = r8 + r12
            goto L_0x0127
        L_0x0126:
            r8 = r10
        L_0x0127:
            int r7 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x0167
            int r7 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r7 < 0) goto L_0x0167
            r5 = 0
        L_0x0130:
            r6 = 20
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzew.zzahk
            java.lang.Object r7 = r7.get()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r10 = 0
            int r7 = java.lang.Math.max(r10, r7)
            int r6 = java.lang.Math.min(r6, r7)
            if (r5 >= r6) goto L_0x00ee
            r6 = 1
            long r6 = r6 << r5
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r11 = com.google.android.gms.internal.measurement.zzew.zzahj
            java.lang.Object r11 = r11.get()
            java.lang.Long r11 = (java.lang.Long) r11
            long r11 = r11.longValue()
            long r11 = java.lang.Math.max(r3, r11)
            long r11 = r11 * r6
            long r8 = r8 + r11
            int r6 = (r8 > r1 ? 1 : (r8 == r1 ? 0 : -1))
            if (r6 <= 0) goto L_0x0164
            goto L_0x0167
        L_0x0164:
            int r5 = r5 + 1
            goto L_0x0130
        L_0x0167:
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x0187
            com.google.android.gms.internal.measurement.zzfg r1 = r20.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "Next upload time is 0"
            r1.log(r2)
            com.google.android.gms.internal.measurement.zzfp r1 = r20.zzko()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzjn r1 = r20.zzkp()
            r1.cancel()
            return
        L_0x0187:
            com.google.android.gms.internal.measurement.zzfk r1 = r20.zzkn()
            boolean r1 = r1.zzex()
            if (r1 != 0) goto L_0x01ad
            com.google.android.gms.internal.measurement.zzfg r1 = r20.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "No network"
            r1.log(r2)
            com.google.android.gms.internal.measurement.zzfp r1 = r20.zzko()
            r1.zzeu()
            com.google.android.gms.internal.measurement.zzjn r1 = r20.zzkp()
            r1.cancel()
            return
        L_0x01ad:
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzgf()
            com.google.android.gms.internal.measurement.zzfu r1 = r1.zzajw
            long r1 = r1.get()
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r5 = com.google.android.gms.internal.measurement.zzew.zzagz
            java.lang.Object r5 = r5.get()
            java.lang.Long r5 = (java.lang.Long) r5
            long r5 = r5.longValue()
            long r5 = java.lang.Math.max(r3, r5)
            com.google.android.gms.internal.measurement.zzka r7 = r20.zzgb()
            boolean r7 = r7.zza((long) r1, (long) r5)
            if (r7 != 0) goto L_0x01d6
            long r1 = r1 + r5
            long r8 = java.lang.Math.max(r8, r1)
        L_0x01d6:
            com.google.android.gms.internal.measurement.zzfp r1 = r20.zzko()
            r1.unregister()
            com.google.android.gms.common.util.Clock r1 = r20.zzbt()
            long r1 = r1.currentTimeMillis()
            long r8 = r8 - r1
            int r1 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x020b
            com.google.android.gms.internal.measurement.zzex<java.lang.Long> r1 = com.google.android.gms.internal.measurement.zzew.zzahe
            java.lang.Object r1 = r1.get()
            java.lang.Long r1 = (java.lang.Long) r1
            long r1 = r1.longValue()
            long r8 = java.lang.Math.max(r3, r1)
            com.google.android.gms.internal.measurement.zzfr r1 = r20.zzgf()
            com.google.android.gms.internal.measurement.zzfu r1 = r1.zzaju
            com.google.android.gms.common.util.Clock r2 = r20.zzbt()
            long r2 = r2.currentTimeMillis()
            r1.set(r2)
        L_0x020b:
            com.google.android.gms.internal.measurement.zzfg r1 = r20.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "Upload scheduled in approximately ms"
            java.lang.Long r3 = java.lang.Long.valueOf(r8)
            r1.zzg(r2, r3)
            com.google.android.gms.internal.measurement.zzjn r1 = r20.zzkp()
            r1.zzh(r8)
            return
        L_0x0224:
            com.google.android.gms.internal.measurement.zzfg r1 = r20.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "Nothing to upload or uploading impossible"
            r1.log(r2)
            com.google.android.gms.internal.measurement.zzfp r1 = r20.zzko()
            r1.unregister()
            com.google.android.gms.internal.measurement.zzjn r1 = r20.zzkp()
            r1.cancel()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzku():void");
    }

    @WorkerThread
    private final void zzkv() {
        zzab();
        if (this.zzaql || this.zzaqm || this.zzaqn) {
            zzge().zzit().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzaql), Boolean.valueOf(this.zzaqm), Boolean.valueOf(this.zzaqn));
            return;
        }
        zzge().zzit().log("Stopping uploading service(s)");
        if (this.zzaqi != null) {
            for (Runnable run : this.zzaqi) {
                run.run();
            }
            this.zzaqi.clear();
        }
    }

    @WorkerThread
    @VisibleForTesting
    private final boolean zzkw() {
        String str;
        zzfi zzfi;
        zzab();
        try {
            this.zzaqp = new RandomAccessFile(new File(getContext().getFilesDir(), "google_app_measurement.db"), "rw").getChannel();
            this.zzaqo = this.zzaqp.tryLock();
            if (this.zzaqo != null) {
                zzge().zzit().log("Storage concurrent access okay");
                return true;
            }
            zzge().zzim().log("Storage concurrent data access panic");
            return false;
        } catch (FileNotFoundException e) {
            e = e;
            zzfi = zzge().zzim();
            str = "Failed to acquire storage lock";
            zzfi.zzg(str, e);
            return false;
        } catch (IOException e2) {
            e = e2;
            zzfi = zzge().zzim();
            str = "Failed to access storage lock file";
            zzfi.zzg(str, e);
            return false;
        }
    }

    @WorkerThread
    private final boolean zzky() {
        zzab();
        zzkq();
        return this.zzaqg;
    }

    public Context getContext() {
        return this.zzacw.getContext();
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    public void start() {
        zzab();
        zzix().zzhp();
        if (zzgf().zzaju.get() == 0) {
            zzgf().zzaju.set(zzbt().currentTimeMillis());
        }
        zzku();
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final void zza(int i, Throwable th, byte[] bArr, String str) {
        zzei zzix;
        zzab();
        zzkq();
        if (bArr == null) {
            try {
                bArr = new byte[0];
            } catch (Throwable th2) {
                this.zzaqm = false;
                zzkv();
                throw th2;
            }
        }
        List<Long> list = this.zzaqq;
        this.zzaqq = null;
        boolean z = true;
        if ((i == 200 || i == 204) && th == null) {
            try {
                zzgf().zzaju.set(zzbt().currentTimeMillis());
                zzgf().zzajv.set(0);
                zzku();
                zzge().zzit().zze("Successful upload. Got network response. code, size", Integer.valueOf(i), Integer.valueOf(bArr.length));
                zzix().beginTransaction();
                try {
                    for (Long next : list) {
                        try {
                            zzix = zzix();
                            long longValue = next.longValue();
                            zzix.zzab();
                            zzix.zzch();
                            if (zzix.getWritableDatabase().delete("queue", "rowid=?", new String[]{String.valueOf(longValue)}) != 1) {
                                throw new SQLiteException("Deleted fewer rows from queue than expected");
                            }
                        } catch (SQLiteException e) {
                            zzix.zzge().zzim().zzg("Failed to delete a bundle in a queue table", e);
                            throw e;
                        } catch (SQLiteException e2) {
                            if (this.zzaqr == null || !this.zzaqr.contains(next)) {
                                throw e2;
                            }
                        }
                    }
                    zzix().setTransactionSuccessful();
                    zzix().endTransaction();
                    this.zzaqr = null;
                    if (!zzkn().zzex() || !zzkt()) {
                        this.zzaqs = -1;
                        zzku();
                    } else {
                        zzks();
                    }
                    this.zzaqh = 0;
                } catch (Throwable th3) {
                    zzix().endTransaction();
                    throw th3;
                }
            } catch (SQLiteException e3) {
                zzge().zzim().zzg("Database error while trying to delete uploaded bundles", e3);
                this.zzaqh = zzbt().elapsedRealtime();
                zzge().zzit().zzg("Disable upload, time", Long.valueOf(this.zzaqh));
            }
        } else {
            zzge().zzit().zze("Network upload failed. Will retry later. code, error", Integer.valueOf(i), th);
            zzgf().zzajv.set(zzbt().currentTimeMillis());
            if (i != 503) {
                if (i != 429) {
                    z = false;
                }
            }
            if (z) {
                zzgf().zzajw.set(zzbt().currentTimeMillis());
            }
            if (zzgg().zzax(str)) {
                zzix().zzc(list);
            }
            zzku();
        }
        this.zzaqm = false;
        zzkv();
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzgl zzgl) {
        this.zzacw = zzgl;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(zzjw zzjw) {
        zzab();
        zzei zzei = new zzei(this.zzacw);
        zzei.zzm();
        this.zzaqc = zzei;
        zzgg().zza(this.zzaqa);
        zzeb zzeb = new zzeb(this.zzacw);
        zzeb.zzm();
        this.zzaqf = zzeb;
        zzjn zzjn = new zzjn(this.zzacw);
        zzjn.zzm();
        this.zzaqe = zzjn;
        this.zzaqd = new zzfp(this.zzacw);
        if (this.zzaqj != this.zzaqk) {
            zzge().zzim().zze("Not all upload components initialized", Integer.valueOf(this.zzaqj), Integer.valueOf(this.zzaqk));
        }
        this.zzvo = true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v0, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v1, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r19v2, resolved type: byte[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v19, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v20, resolved type: java.lang.Long} */
    /* JADX WARNING: Multi-variable type inference failed */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zza(@android.support.annotation.NonNull com.google.android.gms.internal.measurement.zzeu r33, @android.support.annotation.Size(min = 1) java.lang.String r34) {
        /*
            r32 = this;
            r1 = r32
            r0 = r33
            r15 = r34
            r32.zzkq()
            r32.zzab()
            com.google.android.gms.internal.measurement.zzgl.zzfr()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r33)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            com.google.android.gms.internal.measurement.zzkp r14 = new com.google.android.gms.internal.measurement.zzkp
            r14.<init>()
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()
            r2.beginTransaction()
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzdy r13 = r2.zzbc(r15)     // Catch:{ all -> 0x04bf }
            r11 = 0
            if (r13 != 0) goto L_0x0043
            com.google.android.gms.internal.measurement.zzfg r0 = r32.zzge()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzis()     // Catch:{ all -> 0x04bf }
            java.lang.String r2 = "Log and bundle not available. package_name"
            r0.zzg(r2, r15)     // Catch:{ all -> 0x04bf }
        L_0x0039:
            byte[] r0 = new byte[r11]     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()
            r2.endTransaction()
            return r0
        L_0x0043:
            boolean r2 = r13.isMeasurementEnabled()     // Catch:{ all -> 0x04bf }
            if (r2 != 0) goto L_0x0057
            com.google.android.gms.internal.measurement.zzfg r0 = r32.zzge()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzis()     // Catch:{ all -> 0x04bf }
            java.lang.String r2 = "Log and bundle disabled. package_name"
            r0.zzg(r2, r15)     // Catch:{ all -> 0x04bf }
            goto L_0x0039
        L_0x0057:
            java.lang.String r2 = "_iap"
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x04bf }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x04bf }
            if (r2 != 0) goto L_0x006b
            java.lang.String r2 = "ecommerce_purchase"
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x04bf }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x04bf }
            if (r2 == 0) goto L_0x0082
        L_0x006b:
            boolean r2 = r1.zza((java.lang.String) r15, (com.google.android.gms.internal.measurement.zzeu) r0)     // Catch:{ all -> 0x04bf }
            if (r2 != 0) goto L_0x0082
            com.google.android.gms.internal.measurement.zzfg r2 = r32.zzge()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzip()     // Catch:{ all -> 0x04bf }
            java.lang.String r3 = "Failed to handle purchase event at single event bundle creation. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r34)     // Catch:{ all -> 0x04bf }
            r2.zzg(r3, r4)     // Catch:{ all -> 0x04bf }
        L_0x0082:
            com.google.android.gms.internal.measurement.zzef r2 = r32.zzgg()     // Catch:{ all -> 0x04bf }
            boolean r2 = r2.zzav(r15)     // Catch:{ all -> 0x04bf }
            r16 = 0
            java.lang.Long r3 = java.lang.Long.valueOf(r16)     // Catch:{ all -> 0x04bf }
            if (r2 == 0) goto L_0x00dd
            java.lang.String r4 = "_e"
            java.lang.String r5 = r0.name     // Catch:{ all -> 0x04bf }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x04bf }
            if (r4 == 0) goto L_0x00dd
            com.google.android.gms.internal.measurement.zzer r4 = r0.zzafq     // Catch:{ all -> 0x04bf }
            if (r4 == 0) goto L_0x00ce
            com.google.android.gms.internal.measurement.zzer r4 = r0.zzafq     // Catch:{ all -> 0x04bf }
            int r4 = r4.size()     // Catch:{ all -> 0x04bf }
            if (r4 != 0) goto L_0x00a9
            goto L_0x00ce
        L_0x00a9:
            com.google.android.gms.internal.measurement.zzer r4 = r0.zzafq     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = "_et"
            java.lang.Long r4 = r4.getLong(r5)     // Catch:{ all -> 0x04bf }
            if (r4 != 0) goto L_0x00c5
            com.google.android.gms.internal.measurement.zzfg r4 = r32.zzge()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzip()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = "The engagement event does not include duration. appId"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r34)     // Catch:{ all -> 0x04bf }
        L_0x00c1:
            r4.zzg(r5, r6)     // Catch:{ all -> 0x04bf }
            goto L_0x00dd
        L_0x00c5:
            com.google.android.gms.internal.measurement.zzer r3 = r0.zzafq     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = "_et"
            java.lang.Long r3 = r3.getLong(r4)     // Catch:{ all -> 0x04bf }
            goto L_0x00dd
        L_0x00ce:
            com.google.android.gms.internal.measurement.zzfg r4 = r32.zzge()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzip()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = "The engagement event does not contain any parameters. appId"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r34)     // Catch:{ all -> 0x04bf }
            goto L_0x00c1
        L_0x00dd:
            com.google.android.gms.internal.measurement.zzkq r12 = new com.google.android.gms.internal.measurement.zzkq     // Catch:{ all -> 0x04bf }
            r12.<init>()     // Catch:{ all -> 0x04bf }
            r9 = 1
            com.google.android.gms.internal.measurement.zzkq[] r4 = new com.google.android.gms.internal.measurement.zzkq[r9]     // Catch:{ all -> 0x04bf }
            r4[r11] = r12     // Catch:{ all -> 0x04bf }
            r14.zzatf = r4     // Catch:{ all -> 0x04bf }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r9)     // Catch:{ all -> 0x04bf }
            r12.zzath = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = "android"
            r12.zzatp = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r13.zzah()     // Catch:{ all -> 0x04bf }
            r12.zzti = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r13.zzgn()     // Catch:{ all -> 0x04bf }
            r12.zzadt = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r13.zzag()     // Catch:{ all -> 0x04bf }
            r12.zzth = r4     // Catch:{ all -> 0x04bf }
            long r4 = r13.zzgm()     // Catch:{ all -> 0x04bf }
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            r10 = 0
            if (r8 != 0) goto L_0x0113
            r4 = r10
            goto L_0x0118
        L_0x0113:
            int r4 = (int) r4     // Catch:{ all -> 0x04bf }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x04bf }
        L_0x0118:
            r12.zzaub = r4     // Catch:{ all -> 0x04bf }
            long r4 = r13.zzgo()     // Catch:{ all -> 0x04bf }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x04bf }
            r12.zzatt = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r13.getGmpAppId()     // Catch:{ all -> 0x04bf }
            r12.zzadm = r4     // Catch:{ all -> 0x04bf }
            long r4 = r13.zzgp()     // Catch:{ all -> 0x04bf }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x04bf }
            r12.zzatx = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzgl r4 = r1.zzacw     // Catch:{ all -> 0x04bf }
            boolean r4 = r4.isEnabled()     // Catch:{ all -> 0x04bf }
            if (r4 == 0) goto L_0x0150
            boolean r4 = com.google.android.gms.internal.measurement.zzef.zzhk()     // Catch:{ all -> 0x04bf }
            if (r4 == 0) goto L_0x0150
            com.google.android.gms.internal.measurement.zzef r4 = r32.zzgg()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = r12.zzti     // Catch:{ all -> 0x04bf }
            boolean r4 = r4.zzat(r5)     // Catch:{ all -> 0x04bf }
            if (r4 == 0) goto L_0x0150
            r12.zzauh = r10     // Catch:{ all -> 0x04bf }
        L_0x0150:
            com.google.android.gms.internal.measurement.zzfr r4 = r32.zzgf()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = r13.zzah()     // Catch:{ all -> 0x04bf }
            android.util.Pair r4 = r4.zzbo(r5)     // Catch:{ all -> 0x04bf }
            boolean r5 = r13.zzhd()     // Catch:{ all -> 0x04bf }
            if (r5 == 0) goto L_0x017a
            if (r4 == 0) goto L_0x017a
            java.lang.Object r5 = r4.first     // Catch:{ all -> 0x04bf }
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5     // Catch:{ all -> 0x04bf }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x04bf }
            if (r5 != 0) goto L_0x017a
            java.lang.Object r5 = r4.first     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x04bf }
            r12.zzatv = r5     // Catch:{ all -> 0x04bf }
            java.lang.Object r4 = r4.second     // Catch:{ all -> 0x04bf }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x04bf }
            r12.zzatw = r4     // Catch:{ all -> 0x04bf }
        L_0x017a:
            com.google.android.gms.internal.measurement.zzeo r4 = r32.zzfw()     // Catch:{ all -> 0x04bf }
            r4.zzch()     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = android.os.Build.MODEL     // Catch:{ all -> 0x04bf }
            r12.zzatr = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzeo r4 = r32.zzfw()     // Catch:{ all -> 0x04bf }
            r4.zzch()     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = android.os.Build.VERSION.RELEASE     // Catch:{ all -> 0x04bf }
            r12.zzatq = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzeo r4 = r32.zzfw()     // Catch:{ all -> 0x04bf }
            long r4 = r4.zzic()     // Catch:{ all -> 0x04bf }
            int r4 = (int) r4     // Catch:{ all -> 0x04bf }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x04bf }
            r12.zzats = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzeo r4 = r32.zzfw()     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r4.zzid()     // Catch:{ all -> 0x04bf }
            r12.zzafn = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r13.getAppInstanceId()     // Catch:{ all -> 0x04bf }
            r12.zzadl = r4     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r13.zzgj()     // Catch:{ all -> 0x04bf }
            r12.zzado = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r4 = r32.zzix()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = r13.zzah()     // Catch:{ all -> 0x04bf }
            java.util.List r4 = r4.zzbb(r5)     // Catch:{ all -> 0x04bf }
            int r5 = r4.size()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r5 = new com.google.android.gms.internal.measurement.zzks[r5]     // Catch:{ all -> 0x04bf }
            r12.zzatj = r5     // Catch:{ all -> 0x04bf }
            if (r2 == 0) goto L_0x022c
            com.google.android.gms.internal.measurement.zzei r5 = r32.zzix()     // Catch:{ all -> 0x04bf }
            java.lang.String r6 = r12.zzti     // Catch:{ all -> 0x04bf }
            java.lang.String r7 = "_lte"
            com.google.android.gms.internal.measurement.zzjz r5 = r5.zzh(r6, r7)     // Catch:{ all -> 0x04bf }
            if (r5 == 0) goto L_0x0212
            java.lang.Object r6 = r5.value     // Catch:{ all -> 0x04bf }
            if (r6 != 0) goto L_0x01de
            goto L_0x0212
        L_0x01de:
            long r6 = r3.longValue()     // Catch:{ all -> 0x04bf }
            int r8 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r8 <= 0) goto L_0x022d
            com.google.android.gms.internal.measurement.zzjz r6 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ all -> 0x04bf }
            java.lang.String r7 = r12.zzti     // Catch:{ all -> 0x04bf }
            java.lang.String r20 = "auto"
            java.lang.String r21 = "_lte"
            com.google.android.gms.common.util.Clock r8 = r32.zzbt()     // Catch:{ all -> 0x04bf }
            long r22 = r8.currentTimeMillis()     // Catch:{ all -> 0x04bf }
            java.lang.Object r5 = r5.value     // Catch:{ all -> 0x04bf }
            java.lang.Long r5 = (java.lang.Long) r5     // Catch:{ all -> 0x04bf }
            long r18 = r5.longValue()     // Catch:{ all -> 0x04bf }
            long r24 = r3.longValue()     // Catch:{ all -> 0x04bf }
            r5 = 0
            long r18 = r18 + r24
            java.lang.Long r24 = java.lang.Long.valueOf(r18)     // Catch:{ all -> 0x04bf }
            r18 = r6
            r19 = r7
            r18.<init>(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x04bf }
            r5 = r6
            goto L_0x022d
        L_0x0212:
            com.google.android.gms.internal.measurement.zzjz r5 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ all -> 0x04bf }
            java.lang.String r6 = r12.zzti     // Catch:{ all -> 0x04bf }
            java.lang.String r20 = "auto"
            java.lang.String r21 = "_lte"
            com.google.android.gms.common.util.Clock r7 = r32.zzbt()     // Catch:{ all -> 0x04bf }
            long r22 = r7.currentTimeMillis()     // Catch:{ all -> 0x04bf }
            r18 = r5
            r19 = r6
            r24 = r3
            r18.<init>(r19, r20, r21, r22, r24)     // Catch:{ all -> 0x04bf }
            goto L_0x022d
        L_0x022c:
            r5 = r10
        L_0x022d:
            r7 = r10
            r6 = 0
        L_0x022f:
            int r8 = r4.size()     // Catch:{ all -> 0x04bf }
            if (r6 >= r8) goto L_0x028b
            com.google.android.gms.internal.measurement.zzks r8 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x04bf }
            r8.<init>()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r10 = r12.zzatj     // Catch:{ all -> 0x04bf }
            r10[r6] = r8     // Catch:{ all -> 0x04bf }
            java.lang.Object r10 = r4.get(r6)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzjz r10 = (com.google.android.gms.internal.measurement.zzjz) r10     // Catch:{ all -> 0x04bf }
            java.lang.String r10 = r10.name     // Catch:{ all -> 0x04bf }
            r8.name = r10     // Catch:{ all -> 0x04bf }
            java.lang.Object r10 = r4.get(r6)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzjz r10 = (com.google.android.gms.internal.measurement.zzjz) r10     // Catch:{ all -> 0x04bf }
            long r9 = r10.zzaqz     // Catch:{ all -> 0x04bf }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x04bf }
            r8.zzaun = r9     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzka r9 = r32.zzgb()     // Catch:{ all -> 0x04bf }
            java.lang.Object r10 = r4.get(r6)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzjz r10 = (com.google.android.gms.internal.measurement.zzjz) r10     // Catch:{ all -> 0x04bf }
            java.lang.Object r10 = r10.value     // Catch:{ all -> 0x04bf }
            r9.zza((com.google.android.gms.internal.measurement.zzks) r8, (java.lang.Object) r10)     // Catch:{ all -> 0x04bf }
            if (r2 == 0) goto L_0x0286
            java.lang.String r9 = "_lte"
            java.lang.String r10 = r8.name     // Catch:{ all -> 0x04bf }
            boolean r9 = r9.equals(r10)     // Catch:{ all -> 0x04bf }
            if (r9 == 0) goto L_0x0286
            java.lang.Object r7 = r5.value     // Catch:{ all -> 0x04bf }
            java.lang.Long r7 = (java.lang.Long) r7     // Catch:{ all -> 0x04bf }
            r8.zzate = r7     // Catch:{ all -> 0x04bf }
            com.google.android.gms.common.util.Clock r7 = r32.zzbt()     // Catch:{ all -> 0x04bf }
            long r9 = r7.currentTimeMillis()     // Catch:{ all -> 0x04bf }
            java.lang.Long r7 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x04bf }
            r8.zzaun = r7     // Catch:{ all -> 0x04bf }
            r7 = r8
        L_0x0286:
            int r6 = r6 + 1
            r9 = 1
            r10 = 0
            goto L_0x022f
        L_0x028b:
            if (r2 == 0) goto L_0x02c5
            if (r7 != 0) goto L_0x02c5
            com.google.android.gms.internal.measurement.zzks r2 = new com.google.android.gms.internal.measurement.zzks     // Catch:{ all -> 0x04bf }
            r2.<init>()     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = "_lte"
            r2.name = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.common.util.Clock r4 = r32.zzbt()     // Catch:{ all -> 0x04bf }
            long r6 = r4.currentTimeMillis()     // Catch:{ all -> 0x04bf }
            java.lang.Long r4 = java.lang.Long.valueOf(r6)     // Catch:{ all -> 0x04bf }
            r2.zzaun = r4     // Catch:{ all -> 0x04bf }
            java.lang.Object r4 = r5.value     // Catch:{ all -> 0x04bf }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x04bf }
            r2.zzate = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r4 = r12.zzatj     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r6 = r12.zzatj     // Catch:{ all -> 0x04bf }
            int r6 = r6.length     // Catch:{ all -> 0x04bf }
            r7 = 1
            int r6 = r6 + r7
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r4, r6)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r4 = (com.google.android.gms.internal.measurement.zzks[]) r4     // Catch:{ all -> 0x04bf }
            r12.zzatj = r4     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r4 = r12.zzatj     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r6 = r12.zzatj     // Catch:{ all -> 0x04bf }
            int r6 = r6.length     // Catch:{ all -> 0x04bf }
            r9 = 1
            int r6 = r6 - r9
            r4[r6] = r2     // Catch:{ all -> 0x04bf }
            goto L_0x02c6
        L_0x02c5:
            r9 = 1
        L_0x02c6:
            long r2 = r3.longValue()     // Catch:{ all -> 0x04bf }
            int r4 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r4 <= 0) goto L_0x02d5
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()     // Catch:{ all -> 0x04bf }
            r2.zza((com.google.android.gms.internal.measurement.zzjz) r5)     // Catch:{ all -> 0x04bf }
        L_0x02d5:
            com.google.android.gms.internal.measurement.zzer r2 = r0.zzafq     // Catch:{ all -> 0x04bf }
            android.os.Bundle r10 = r2.zzif()     // Catch:{ all -> 0x04bf }
            java.lang.String r2 = "_iap"
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x04bf }
            boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x04bf }
            r3 = 1
            if (r2 == 0) goto L_0x02fe
            java.lang.String r2 = "_c"
            r10.putLong(r2, r3)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfg r2 = r32.zzge()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzis()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = "Marking in-app purchase as real-time"
            r2.log(r5)     // Catch:{ all -> 0x04bf }
            java.lang.String r2 = "_r"
            r10.putLong(r2, r3)     // Catch:{ all -> 0x04bf }
        L_0x02fe:
            java.lang.String r2 = "_o"
            java.lang.String r5 = r0.origin     // Catch:{ all -> 0x04bf }
            r10.putString(r2, r5)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzka r2 = r32.zzgb()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = r12.zzti     // Catch:{ all -> 0x04bf }
            boolean r2 = r2.zzcj(r5)     // Catch:{ all -> 0x04bf }
            if (r2 == 0) goto L_0x032b
            com.google.android.gms.internal.measurement.zzka r2 = r32.zzgb()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = "_dbg"
            java.lang.Long r6 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x04bf }
            r2.zza((android.os.Bundle) r10, (java.lang.String) r5, (java.lang.Object) r6)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzka r2 = r32.zzgb()     // Catch:{ all -> 0x04bf }
            java.lang.String r5 = "_r"
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x04bf }
            r2.zza((android.os.Bundle) r10, (java.lang.String) r5, (java.lang.Object) r3)     // Catch:{ all -> 0x04bf }
        L_0x032b:
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()     // Catch:{ all -> 0x04bf }
            java.lang.String r3 = r0.name     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzeq r2 = r2.zzf(r15, r3)     // Catch:{ all -> 0x04bf }
            if (r2 != 0) goto L_0x0375
            com.google.android.gms.internal.measurement.zzeq r7 = new com.google.android.gms.internal.measurement.zzeq     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r0.name     // Catch:{ all -> 0x04bf }
            r5 = 1
            r18 = 0
            long r2 = r0.zzagb     // Catch:{ all -> 0x04bf }
            r20 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r26 = r2
            r2 = r7
            r3 = r34
            r28 = r7
            r7 = r18
            r18 = r10
            r19 = 0
            r9 = r26
            r29 = r12
            r11 = r20
            r30 = r13
            r13 = r22
            r31 = r14
            r14 = r23
            r15 = r24
            r2.<init>(r3, r4, r5, r7, r9, r11, r13, r14, r15)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()     // Catch:{ all -> 0x04bf }
            r3 = r28
            r2.zza((com.google.android.gms.internal.measurement.zzeq) r3)     // Catch:{ all -> 0x04bf }
            r9 = r16
            goto L_0x0393
        L_0x0375:
            r18 = r10
            r29 = r12
            r30 = r13
            r31 = r14
            r19 = 0
            long r3 = r2.zzaft     // Catch:{ all -> 0x04bf }
            long r5 = r0.zzagb     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzeq r2 = r2.zzac(r5)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzeq r2 = r2.zzie()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r5 = r32.zzix()     // Catch:{ all -> 0x04bf }
            r5.zza((com.google.android.gms.internal.measurement.zzeq) r2)     // Catch:{ all -> 0x04bf }
            r9 = r3
        L_0x0393:
            com.google.android.gms.internal.measurement.zzep r12 = new com.google.android.gms.internal.measurement.zzep     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzgl r3 = r1.zzacw     // Catch:{ all -> 0x04bf }
            java.lang.String r4 = r0.origin     // Catch:{ all -> 0x04bf }
            java.lang.String r6 = r0.name     // Catch:{ all -> 0x04bf }
            long r7 = r0.zzagb     // Catch:{ all -> 0x04bf }
            r2 = r12
            r5 = r34
            r11 = r18
            r2.<init>((com.google.android.gms.internal.measurement.zzgl) r3, (java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (long) r7, (long) r9, (android.os.Bundle) r11)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzkn r0 = new com.google.android.gms.internal.measurement.zzkn     // Catch:{ all -> 0x04bf }
            r0.<init>()     // Catch:{ all -> 0x04bf }
            r2 = 1
            com.google.android.gms.internal.measurement.zzkn[] r2 = new com.google.android.gms.internal.measurement.zzkn[r2]     // Catch:{ all -> 0x04bf }
            r3 = 0
            r2[r3] = r0     // Catch:{ all -> 0x04bf }
            r4 = r29
            r4.zzati = r2     // Catch:{ all -> 0x04bf }
            long r5 = r12.timestamp     // Catch:{ all -> 0x04bf }
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04bf }
            r0.zzatb = r2     // Catch:{ all -> 0x04bf }
            java.lang.String r2 = r12.name     // Catch:{ all -> 0x04bf }
            r0.name = r2     // Catch:{ all -> 0x04bf }
            long r5 = r12.zzafp     // Catch:{ all -> 0x04bf }
            java.lang.Long r2 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04bf }
            r0.zzatc = r2     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzer r2 = r12.zzafq     // Catch:{ all -> 0x04bf }
            int r2 = r2.size()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzko[] r2 = new com.google.android.gms.internal.measurement.zzko[r2]     // Catch:{ all -> 0x04bf }
            r0.zzata = r2     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzer r2 = r12.zzafq     // Catch:{ all -> 0x04bf }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x04bf }
            r5 = 0
        L_0x03d9:
            boolean r6 = r2.hasNext()     // Catch:{ all -> 0x04bf }
            if (r6 == 0) goto L_0x0401
            java.lang.Object r6 = r2.next()     // Catch:{ all -> 0x04bf }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzko r7 = new com.google.android.gms.internal.measurement.zzko     // Catch:{ all -> 0x04bf }
            r7.<init>()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzko[] r8 = r0.zzata     // Catch:{ all -> 0x04bf }
            int r9 = r5 + 1
            r8[r5] = r7     // Catch:{ all -> 0x04bf }
            r7.name = r6     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzer r5 = r12.zzafq     // Catch:{ all -> 0x04bf }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzka r6 = r32.zzgb()     // Catch:{ all -> 0x04bf }
            r6.zza((com.google.android.gms.internal.measurement.zzko) r7, (java.lang.Object) r5)     // Catch:{ all -> 0x04bf }
            r5 = r9
            goto L_0x03d9
        L_0x0401:
            java.lang.String r2 = r30.zzah()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzks[] r5 = r4.zzatj     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzkn[] r6 = r4.zzati     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzkm[] r2 = r1.zza(r2, r5, r6)     // Catch:{ all -> 0x04bf }
            r4.zzaua = r2     // Catch:{ all -> 0x04bf }
            java.lang.Long r2 = r0.zzatb     // Catch:{ all -> 0x04bf }
            r4.zzatl = r2     // Catch:{ all -> 0x04bf }
            java.lang.Long r0 = r0.zzatb     // Catch:{ all -> 0x04bf }
            r4.zzatm = r0     // Catch:{ all -> 0x04bf }
            long r5 = r30.zzgl()     // Catch:{ all -> 0x04bf }
            int r0 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x0424
            java.lang.Long r10 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04bf }
            goto L_0x0426
        L_0x0424:
            r10 = r19
        L_0x0426:
            r4.zzato = r10     // Catch:{ all -> 0x04bf }
            long r7 = r30.zzgk()     // Catch:{ all -> 0x04bf }
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x0431
            goto L_0x0432
        L_0x0431:
            r5 = r7
        L_0x0432:
            int r0 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x043b
            java.lang.Long r10 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04bf }
            goto L_0x043d
        L_0x043b:
            r10 = r19
        L_0x043d:
            r4.zzatn = r10     // Catch:{ all -> 0x04bf }
            r30.zzgt()     // Catch:{ all -> 0x04bf }
            long r5 = r30.zzgq()     // Catch:{ all -> 0x04bf }
            int r0 = (int) r5     // Catch:{ all -> 0x04bf }
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch:{ all -> 0x04bf }
            r4.zzaty = r0     // Catch:{ all -> 0x04bf }
            r5 = 12451(0x30a3, double:6.1516E-320)
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04bf }
            r4.zzatu = r0     // Catch:{ all -> 0x04bf }
            com.google.android.gms.common.util.Clock r0 = r32.zzbt()     // Catch:{ all -> 0x04bf }
            long r5 = r0.currentTimeMillis()     // Catch:{ all -> 0x04bf }
            java.lang.Long r0 = java.lang.Long.valueOf(r5)     // Catch:{ all -> 0x04bf }
            r4.zzatk = r0     // Catch:{ all -> 0x04bf }
            java.lang.Boolean r0 = java.lang.Boolean.TRUE     // Catch:{ all -> 0x04bf }
            r4.zzatz = r0     // Catch:{ all -> 0x04bf }
            java.lang.Long r0 = r4.zzatl     // Catch:{ all -> 0x04bf }
            long r5 = r0.longValue()     // Catch:{ all -> 0x04bf }
            r0 = r30
            r0.zzm(r5)     // Catch:{ all -> 0x04bf }
            java.lang.Long r2 = r4.zzatm     // Catch:{ all -> 0x04bf }
            long r4 = r2.longValue()     // Catch:{ all -> 0x04bf }
            r0.zzn(r4)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()     // Catch:{ all -> 0x04bf }
            r2.zza((com.google.android.gms.internal.measurement.zzdy) r0)     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r0 = r32.zzix()     // Catch:{ all -> 0x04bf }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x04bf }
            com.google.android.gms.internal.measurement.zzei r0 = r32.zzix()
            r0.endTransaction()
            int r0 = r31.zzvm()     // Catch:{ IOException -> 0x04ac }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x04ac }
            int r2 = r0.length     // Catch:{ IOException -> 0x04ac }
            com.google.android.gms.internal.measurement.zzabw r2 = com.google.android.gms.internal.measurement.zzabw.zzb(r0, r3, r2)     // Catch:{ IOException -> 0x04ac }
            r3 = r31
            r3.zza(r2)     // Catch:{ IOException -> 0x04ac }
            r2.zzve()     // Catch:{ IOException -> 0x04ac }
            com.google.android.gms.internal.measurement.zzka r2 = r32.zzgb()     // Catch:{ IOException -> 0x04ac }
            byte[] r0 = r2.zza((byte[]) r0)     // Catch:{ IOException -> 0x04ac }
            return r0
        L_0x04ac:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r2 = r32.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()
            java.lang.String r3 = "Data loss. Failed to bundle and serialize. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r34)
            r2.zze(r3, r4, r0)
            return r19
        L_0x04bf:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzei r2 = r32.zzix()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zza(com.google.android.gms.internal.measurement.zzeu, java.lang.String):byte[]");
    }

    @WorkerThread
    public void zzab() {
        zzgd().zzab();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzed zzed, zzdz zzdz) {
        zzfi zzim;
        String str;
        Object zzbm;
        String zzbl;
        Object value;
        zzfi zzim2;
        String str2;
        Object zzbm2;
        String zzbl2;
        Object obj;
        Preconditions.checkNotNull(zzed);
        Preconditions.checkNotEmpty(zzed.packageName);
        Preconditions.checkNotNull(zzed.origin);
        Preconditions.checkNotNull(zzed.zzaep);
        Preconditions.checkNotEmpty(zzed.zzaep.name);
        zzab();
        zzkq();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            zzed zzed2 = new zzed(zzed);
            boolean z = false;
            zzed2.active = false;
            zzix().beginTransaction();
            try {
                zzed zzi = zzix().zzi(zzed2.packageName, zzed2.zzaep.name);
                if (zzi != null && !zzi.origin.equals(zzed2.origin)) {
                    zzge().zzip().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", zzga().zzbl(zzed2.zzaep.name), zzed2.origin, zzi.origin);
                }
                if (zzi != null && zzi.active) {
                    zzed2.origin = zzi.origin;
                    zzed2.creationTimestamp = zzi.creationTimestamp;
                    zzed2.triggerTimeout = zzi.triggerTimeout;
                    zzed2.triggerEventName = zzi.triggerEventName;
                    zzed2.zzaer = zzi.zzaer;
                    zzed2.active = zzi.active;
                    zzed2.zzaep = new zzjx(zzed2.zzaep.name, zzi.zzaep.zzaqz, zzed2.zzaep.getValue(), zzi.zzaep.origin);
                } else if (TextUtils.isEmpty(zzed2.triggerEventName)) {
                    zzed2.zzaep = new zzjx(zzed2.zzaep.name, zzed2.creationTimestamp, zzed2.zzaep.getValue(), zzed2.zzaep.origin);
                    zzed2.active = true;
                    z = true;
                }
                if (zzed2.active) {
                    zzjx zzjx = zzed2.zzaep;
                    zzjz zzjz = new zzjz(zzed2.packageName, zzed2.origin, zzjx.name, zzjx.zzaqz, zzjx.getValue());
                    if (zzix().zza(zzjz)) {
                        zzim2 = zzge().zzis();
                        str2 = "User property updated immediately";
                        zzbm2 = zzed2.packageName;
                        zzbl2 = zzga().zzbl(zzjz.name);
                        obj = zzjz.value;
                    } else {
                        zzim2 = zzge().zzim();
                        str2 = "(2)Too many active user properties, ignoring";
                        zzbm2 = zzfg.zzbm(zzed2.packageName);
                        zzbl2 = zzga().zzbl(zzjz.name);
                        obj = zzjz.value;
                    }
                    zzim2.zzd(str2, zzbm2, zzbl2, obj);
                    if (z && zzed2.zzaer != null) {
                        zzc(new zzeu(zzed2.zzaer, zzed2.creationTimestamp), zzdz);
                    }
                }
                if (zzix().zza(zzed2)) {
                    zzim = zzge().zzis();
                    str = "Conditional property added";
                    zzbm = zzed2.packageName;
                    zzbl = zzga().zzbl(zzed2.zzaep.name);
                    value = zzed2.zzaep.getValue();
                } else {
                    zzim = zzge().zzim();
                    str = "Too many conditional properties, ignoring";
                    zzbm = zzfg.zzbm(zzed2.packageName);
                    zzbl = zzga().zzbl(zzed2.zzaep.name);
                    value = zzed2.zzaep.getValue();
                }
                zzim.zzd(str, zzbm, zzbl, value);
                zzix().setTransactionSuccessful();
            } finally {
                zzix().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzeu zzeu, zzdz zzdz) {
        List<zzed> list;
        List<zzed> list2;
        List<zzed> list3;
        zzfi zzim;
        String str;
        Object zzbm;
        String zzbl;
        Object obj;
        zzeu zzeu2 = zzeu;
        zzdz zzdz2 = zzdz;
        Preconditions.checkNotNull(zzdz);
        Preconditions.checkNotEmpty(zzdz2.packageName);
        zzab();
        zzkq();
        String str2 = zzdz2.packageName;
        long j = zzeu2.zzagb;
        zzgb();
        if (zzka.zzd(zzeu, zzdz)) {
            if (!zzdz2.zzadw) {
                zzg(zzdz2);
                return;
            }
            zzix().beginTransaction();
            try {
                zzei zzix = zzix();
                Preconditions.checkNotEmpty(str2);
                zzix.zzab();
                zzix.zzch();
                if (j < 0) {
                    zzix.zzge().zzip().zze("Invalid time querying timed out conditional properties", zzfg.zzbm(str2), Long.valueOf(j));
                    list = Collections.emptyList();
                } else {
                    list = zzix.zzb("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{str2, String.valueOf(j)});
                }
                for (zzed next : list) {
                    if (next != null) {
                        zzge().zzis().zzd("User property timed out", next.packageName, zzga().zzbl(next.zzaep.name), next.zzaep.getValue());
                        if (next.zzaeq != null) {
                            zzc(new zzeu(next.zzaeq, j), zzdz2);
                        }
                        zzix().zzj(str2, next.zzaep.name);
                    }
                }
                zzei zzix2 = zzix();
                Preconditions.checkNotEmpty(str2);
                zzix2.zzab();
                zzix2.zzch();
                if (j < 0) {
                    zzix2.zzge().zzip().zze("Invalid time querying expired conditional properties", zzfg.zzbm(str2), Long.valueOf(j));
                    list2 = Collections.emptyList();
                } else {
                    list2 = zzix2.zzb("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{str2, String.valueOf(j)});
                }
                ArrayList arrayList = new ArrayList(list2.size());
                for (zzed next2 : list2) {
                    if (next2 != null) {
                        zzge().zzis().zzd("User property expired", next2.packageName, zzga().zzbl(next2.zzaep.name), next2.zzaep.getValue());
                        zzix().zzg(str2, next2.zzaep.name);
                        if (next2.zzaes != null) {
                            arrayList.add(next2.zzaes);
                        }
                        zzix().zzj(str2, next2.zzaep.name);
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj2 = arrayList2.get(i);
                    i++;
                    zzc(new zzeu((zzeu) obj2, j), zzdz2);
                }
                zzei zzix3 = zzix();
                String str3 = zzeu2.name;
                Preconditions.checkNotEmpty(str2);
                Preconditions.checkNotEmpty(str3);
                zzix3.zzab();
                zzix3.zzch();
                if (j < 0) {
                    zzix3.zzge().zzip().zzd("Invalid time querying triggered conditional properties", zzfg.zzbm(str2), zzix3.zzga().zzbj(str3), Long.valueOf(j));
                    list3 = Collections.emptyList();
                } else {
                    list3 = zzix3.zzb("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{str2, str3, String.valueOf(j)});
                }
                ArrayList arrayList3 = new ArrayList(list3.size());
                for (zzed next3 : list3) {
                    if (next3 != null) {
                        zzjx zzjx = next3.zzaep;
                        zzjz zzjz = r4;
                        zzjz zzjz2 = new zzjz(next3.packageName, next3.origin, zzjx.name, j, zzjx.getValue());
                        if (zzix().zza(zzjz)) {
                            zzim = zzge().zzis();
                            str = "User property triggered";
                            zzbm = next3.packageName;
                            zzbl = zzga().zzbl(zzjz.name);
                            obj = zzjz.value;
                        } else {
                            zzim = zzge().zzim();
                            str = "Too many active user properties, ignoring";
                            zzbm = zzfg.zzbm(next3.packageName);
                            zzbl = zzga().zzbl(zzjz.name);
                            obj = zzjz.value;
                        }
                        zzim.zzd(str, zzbm, zzbl, obj);
                        if (next3.zzaer != null) {
                            arrayList3.add(next3.zzaer);
                        }
                        next3.zzaep = new zzjx(zzjz);
                        next3.active = true;
                        zzix().zza(next3);
                    }
                }
                zzc(zzeu, zzdz);
                ArrayList arrayList4 = arrayList3;
                int size2 = arrayList4.size();
                int i2 = 0;
                while (i2 < size2) {
                    Object obj3 = arrayList4.get(i2);
                    i2++;
                    zzc(new zzeu((zzeu) obj3, j), zzdz2);
                }
                zzix().setTransactionSuccessful();
            } finally {
                zzix().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzb(zzjq zzjq) {
        this.zzaqj++;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzb(zzjx zzjx, zzdz zzdz) {
        zzab();
        zzkq();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            int zzcf = zzgb().zzcf(zzjx.name);
            if (zzcf != 0) {
                zzgb();
                zzgb().zza(zzdz.packageName, zzcf, "_ev", zzka.zza(zzjx.name, 24, true), zzjx.name != null ? zzjx.name.length() : 0);
                return;
            }
            int zzi = zzgb().zzi(zzjx.name, zzjx.getValue());
            if (zzi != 0) {
                zzgb();
                String zza = zzka.zza(zzjx.name, 24, true);
                Object value = zzjx.getValue();
                zzgb().zza(zzdz.packageName, zzi, "_ev", zza, (value == null || (!(value instanceof String) && !(value instanceof CharSequence))) ? 0 : String.valueOf(value).length());
                return;
            }
            Object zzj = zzgb().zzj(zzjx.name, zzjx.getValue());
            if (zzj != null) {
                zzjz zzjz = new zzjz(zzdz.packageName, zzjx.origin, zzjx.name, zzjx.zzaqz, zzj);
                zzge().zzis().zze("Setting user property", zzga().zzbl(zzjz.name), zzj);
                zzix().beginTransaction();
                try {
                    zzg(zzdz);
                    boolean zza2 = zzix().zza(zzjz);
                    zzix().setTransactionSuccessful();
                    if (zza2) {
                        zzge().zzis().zze("User property set", zzga().zzbl(zzjz.name), zzjz.value);
                    } else {
                        zzge().zzim().zze("Too many unique user properties are set. Ignoring user property", zzga().zzbl(zzjz.name), zzjz.value);
                        zzgb().zza(zzdz.packageName, 9, (String) null, (String) null, 0);
                    }
                } finally {
                    zzix().endTransaction();
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0124 A[Catch:{ all -> 0x0167, all -> 0x000f }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0132 A[Catch:{ all -> 0x0167, all -> 0x000f }] */
    @android.support.annotation.WorkerThread
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzb(java.lang.String r7, int r8, java.lang.Throwable r9, byte[] r10, java.util.Map<java.lang.String, java.util.List<java.lang.String>> r11) {
        /*
            r6 = this;
            r6.zzab()
            r6.zzkq()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r7)
            r0 = 0
            if (r10 != 0) goto L_0x0012
            byte[] r10 = new byte[r0]     // Catch:{ all -> 0x000f }
            goto L_0x0012
        L_0x000f:
            r7 = move-exception
            goto L_0x0170
        L_0x0012:
            com.google.android.gms.internal.measurement.zzfg r1 = r6.zzge()     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()     // Catch:{ all -> 0x000f }
            java.lang.String r2 = "onConfigFetched. Response size"
            int r3 = r10.length     // Catch:{ all -> 0x000f }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x000f }
            r1.zzg(r2, r3)     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzei r1 = r6.zzix()     // Catch:{ all -> 0x000f }
            r1.beginTransaction()     // Catch:{ all -> 0x000f }
            com.google.android.gms.internal.measurement.zzei r1 = r6.zzix()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzdy r1 = r1.zzbc(r7)     // Catch:{ all -> 0x0167 }
            r2 = 200(0xc8, float:2.8E-43)
            r3 = 1
            r4 = 304(0x130, float:4.26E-43)
            if (r8 == r2) goto L_0x0040
            r2 = 204(0xcc, float:2.86E-43)
            if (r8 == r2) goto L_0x0040
            if (r8 != r4) goto L_0x0044
        L_0x0040:
            if (r9 != 0) goto L_0x0044
            r2 = 1
            goto L_0x0045
        L_0x0044:
            r2 = 0
        L_0x0045:
            if (r1 != 0) goto L_0x005a
            com.google.android.gms.internal.measurement.zzfg r8 = r6.zzge()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzip()     // Catch:{ all -> 0x0167 }
            java.lang.String r9 = "App does not exist in onConfigFetched. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r7)     // Catch:{ all -> 0x0167 }
            r8.zzg(r9, r7)     // Catch:{ all -> 0x0167 }
            goto L_0x015b
        L_0x005a:
            r5 = 404(0x194, float:5.66E-43)
            if (r2 != 0) goto L_0x00be
            if (r8 != r5) goto L_0x0061
            goto L_0x00be
        L_0x0061:
            com.google.android.gms.common.util.Clock r10 = r6.zzbt()     // Catch:{ all -> 0x0167 }
            long r10 = r10.currentTimeMillis()     // Catch:{ all -> 0x0167 }
            r1.zzt(r10)     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzei r10 = r6.zzix()     // Catch:{ all -> 0x0167 }
            r10.zza((com.google.android.gms.internal.measurement.zzdy) r1)     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfg r10 = r6.zzge()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfi r10 = r10.zzit()     // Catch:{ all -> 0x0167 }
            java.lang.String r11 = "Fetching config failed. code, error"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0167 }
            r10.zze(r11, r1, r9)     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzgf r9 = r6.zzkm()     // Catch:{ all -> 0x0167 }
            r9.zzbw(r7)     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfr r7 = r6.zzgf()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfu r7 = r7.zzajv     // Catch:{ all -> 0x0167 }
            com.google.android.gms.common.util.Clock r9 = r6.zzbt()     // Catch:{ all -> 0x0167 }
            long r9 = r9.currentTimeMillis()     // Catch:{ all -> 0x0167 }
            r7.set(r9)     // Catch:{ all -> 0x0167 }
            r7 = 503(0x1f7, float:7.05E-43)
            if (r8 == r7) goto L_0x00a6
            r7 = 429(0x1ad, float:6.01E-43)
            if (r8 != r7) goto L_0x00a5
            goto L_0x00a6
        L_0x00a5:
            r3 = 0
        L_0x00a6:
            if (r3 == 0) goto L_0x00b9
            com.google.android.gms.internal.measurement.zzfr r7 = r6.zzgf()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfu r7 = r7.zzajw     // Catch:{ all -> 0x0167 }
            com.google.android.gms.common.util.Clock r8 = r6.zzbt()     // Catch:{ all -> 0x0167 }
            long r8 = r8.currentTimeMillis()     // Catch:{ all -> 0x0167 }
            r7.set(r8)     // Catch:{ all -> 0x0167 }
        L_0x00b9:
            r6.zzku()     // Catch:{ all -> 0x0167 }
            goto L_0x015b
        L_0x00be:
            r9 = 0
            if (r11 == 0) goto L_0x00ca
            java.lang.String r2 = "Last-Modified"
            java.lang.Object r11 = r11.get(r2)     // Catch:{ all -> 0x0167 }
            java.util.List r11 = (java.util.List) r11     // Catch:{ all -> 0x0167 }
            goto L_0x00cb
        L_0x00ca:
            r11 = r9
        L_0x00cb:
            if (r11 == 0) goto L_0x00da
            int r2 = r11.size()     // Catch:{ all -> 0x0167 }
            if (r2 <= 0) goto L_0x00da
            java.lang.Object r11 = r11.get(r0)     // Catch:{ all -> 0x0167 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ all -> 0x0167 }
            goto L_0x00db
        L_0x00da:
            r11 = r9
        L_0x00db:
            if (r8 == r5) goto L_0x00f7
            if (r8 != r4) goto L_0x00e0
            goto L_0x00f7
        L_0x00e0:
            com.google.android.gms.internal.measurement.zzgf r9 = r6.zzkm()     // Catch:{ all -> 0x0167 }
            boolean r9 = r9.zza(r7, r10, r11)     // Catch:{ all -> 0x0167 }
            if (r9 != 0) goto L_0x0110
            com.google.android.gms.internal.measurement.zzei r7 = r6.zzix()     // Catch:{ all -> 0x000f }
        L_0x00ee:
            r7.endTransaction()     // Catch:{ all -> 0x000f }
            r6.zzaql = r0
            r6.zzkv()
            return
        L_0x00f7:
            com.google.android.gms.internal.measurement.zzgf r11 = r6.zzkm()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzkk r11 = r11.zzbu(r7)     // Catch:{ all -> 0x0167 }
            if (r11 != 0) goto L_0x0110
            com.google.android.gms.internal.measurement.zzgf r11 = r6.zzkm()     // Catch:{ all -> 0x0167 }
            boolean r9 = r11.zza(r7, r9, r9)     // Catch:{ all -> 0x0167 }
            if (r9 != 0) goto L_0x0110
            com.google.android.gms.internal.measurement.zzei r7 = r6.zzix()     // Catch:{ all -> 0x000f }
            goto L_0x00ee
        L_0x0110:
            com.google.android.gms.common.util.Clock r9 = r6.zzbt()     // Catch:{ all -> 0x0167 }
            long r2 = r9.currentTimeMillis()     // Catch:{ all -> 0x0167 }
            r1.zzs(r2)     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzei r9 = r6.zzix()     // Catch:{ all -> 0x0167 }
            r9.zza((com.google.android.gms.internal.measurement.zzdy) r1)     // Catch:{ all -> 0x0167 }
            if (r8 != r5) goto L_0x0132
            com.google.android.gms.internal.measurement.zzfg r8 = r6.zzge()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zziq()     // Catch:{ all -> 0x0167 }
            java.lang.String r9 = "Config not found. Using empty config. appId"
            r8.zzg(r9, r7)     // Catch:{ all -> 0x0167 }
            goto L_0x0148
        L_0x0132:
            com.google.android.gms.internal.measurement.zzfg r7 = r6.zzge()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzit()     // Catch:{ all -> 0x0167 }
            java.lang.String r9 = "Successfully fetched config. Got network response. code, size"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ all -> 0x0167 }
            int r10 = r10.length     // Catch:{ all -> 0x0167 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ all -> 0x0167 }
            r7.zze(r9, r8, r10)     // Catch:{ all -> 0x0167 }
        L_0x0148:
            com.google.android.gms.internal.measurement.zzfk r7 = r6.zzkn()     // Catch:{ all -> 0x0167 }
            boolean r7 = r7.zzex()     // Catch:{ all -> 0x0167 }
            if (r7 == 0) goto L_0x00b9
            boolean r7 = r6.zzkt()     // Catch:{ all -> 0x0167 }
            if (r7 == 0) goto L_0x00b9
            r6.zzks()     // Catch:{ all -> 0x0167 }
        L_0x015b:
            com.google.android.gms.internal.measurement.zzei r7 = r6.zzix()     // Catch:{ all -> 0x0167 }
            r7.setTransactionSuccessful()     // Catch:{ all -> 0x0167 }
            com.google.android.gms.internal.measurement.zzei r7 = r6.zzix()     // Catch:{ all -> 0x000f }
            goto L_0x00ee
        L_0x0167:
            r7 = move-exception
            com.google.android.gms.internal.measurement.zzei r8 = r6.zzix()     // Catch:{ all -> 0x000f }
            r8.endTransaction()     // Catch:{ all -> 0x000f }
            throw r7     // Catch:{ all -> 0x000f }
        L_0x0170:
            r6.zzaql = r0
            r6.zzkv()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzb(java.lang.String, int, java.lang.Throwable, byte[], java.util.Map):void");
    }

    public Clock zzbt() {
        return this.zzacw.zzbt();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzed zzed, zzdz zzdz) {
        Preconditions.checkNotNull(zzed);
        Preconditions.checkNotEmpty(zzed.packageName);
        Preconditions.checkNotNull(zzed.zzaep);
        Preconditions.checkNotEmpty(zzed.zzaep.name);
        zzab();
        zzkq();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            zzix().beginTransaction();
            try {
                zzg(zzdz);
                zzed zzi = zzix().zzi(zzed.packageName, zzed.zzaep.name);
                if (zzi != null) {
                    zzge().zzis().zze("Removing conditional user property", zzed.packageName, zzga().zzbl(zzed.zzaep.name));
                    zzix().zzj(zzed.packageName, zzed.zzaep.name);
                    if (zzi.active) {
                        zzix().zzg(zzed.packageName, zzed.zzaep.name);
                    }
                    if (zzed.zzaes != null) {
                        Bundle bundle = null;
                        if (zzed.zzaes.zzafq != null) {
                            bundle = zzed.zzaes.zzafq.zzif();
                        }
                        Bundle bundle2 = bundle;
                        zzc(zzgb().zza(zzed.zzaes.name, bundle2, zzi.origin, zzed.zzaes.zzagb, true, false), zzdz);
                    }
                } else {
                    zzge().zzip().zze("Conditional user property doesn't exist", zzfg.zzbm(zzed.packageName), zzga().zzbl(zzed.zzaep.name));
                }
                zzix().setTransactionSuccessful();
            } finally {
                zzix().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzeu zzeu, String str) {
        zzeu zzeu2 = zzeu;
        String str2 = str;
        zzdy zzbc = zzix().zzbc(str2);
        if (zzbc == null || TextUtils.isEmpty(zzbc.zzag())) {
            zzge().zzis().zzg("No app data available; dropping event", str2);
            return;
        }
        Boolean zzc = zzc(zzbc);
        if (zzc == null) {
            if (!"_ui".equals(zzeu2.name)) {
                zzge().zzip().zzg("Could not find package. appId", zzfg.zzbm(str));
            }
        } else if (!zzc.booleanValue()) {
            zzge().zzim().zzg("App version does not match; dropping event. appId", zzfg.zzbm(str));
            return;
        }
        zzdz zzdz = r2;
        zzdy zzdy = zzbc;
        zzdz zzdz2 = new zzdz(str, zzbc.getGmpAppId(), zzbc.zzag(), zzbc.zzgm(), zzbc.zzgn(), zzbc.zzgo(), zzbc.zzgp(), (String) null, zzbc.isMeasurementEnabled(), false, zzdy.zzgj(), zzdy.zzhc(), 0, 0, zzdy.zzhd(), zzdy.zzhe(), false);
        zzb(zzeu2, zzdz);
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzc(zzjx zzjx, zzdz zzdz) {
        zzab();
        zzkq();
        if (!TextUtils.isEmpty(zzdz.zzadm)) {
            if (!zzdz.zzadw) {
                zzg(zzdz);
                return;
            }
            zzge().zzis().zzg("Removing user property", zzga().zzbl(zzjx.name));
            zzix().beginTransaction();
            try {
                zzg(zzdz);
                zzix().zzg(zzdz.packageName, zzjx.name);
                zzix().setTransactionSuccessful();
                zzge().zzis().zzg("User property removed", zzga().zzbl(zzjx.name));
            } finally {
                zzix().endTransaction();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final zzdz zzcb(String str) {
        zzfi zzis;
        String str2;
        Object obj;
        String str3 = str;
        zzdy zzbc = zzix().zzbc(str3);
        if (zzbc == null || TextUtils.isEmpty(zzbc.zzag())) {
            zzis = zzge().zzis();
            str2 = "No app data available; dropping";
            obj = str3;
        } else {
            Boolean zzc = zzc(zzbc);
            if (zzc == null || zzc.booleanValue()) {
                zzdy zzdy = zzbc;
                return new zzdz(str, zzbc.getGmpAppId(), zzbc.zzag(), zzbc.zzgm(), zzbc.zzgn(), zzbc.zzgo(), zzbc.zzgp(), (String) null, zzbc.isMeasurementEnabled(), false, zzbc.zzgj(), zzbc.zzhc(), 0, 0, zzdy.zzhd(), zzdy.zzhe(), false);
            }
            zzis = zzge().zzim();
            str2 = "App version does not match; dropping. appId";
            obj = zzfg.zzbm(str);
        }
        zzis.zzg(str2, obj);
        return null;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzd(zzdz zzdz) {
        if (this.zzaqq != null) {
            this.zzaqr = new ArrayList();
            this.zzaqr.addAll(this.zzaqq);
        }
        zzei zzix = zzix();
        String str = zzdz.packageName;
        Preconditions.checkNotEmpty(str);
        zzix.zzab();
        zzix.zzch();
        try {
            SQLiteDatabase writableDatabase = zzix.getWritableDatabase();
            String[] strArr = {str};
            int delete = writableDatabase.delete("apps", "app_id=?", strArr) + 0 + writableDatabase.delete(j.b, "app_id=?", strArr) + writableDatabase.delete("user_attributes", "app_id=?", strArr) + writableDatabase.delete("conditional_properties", "app_id=?", strArr) + writableDatabase.delete("raw_events", "app_id=?", strArr) + writableDatabase.delete("raw_events_metadata", "app_id=?", strArr) + writableDatabase.delete("queue", "app_id=?", strArr) + writableDatabase.delete("audience_filter_values", "app_id=?", strArr) + writableDatabase.delete("main_event_params", "app_id=?", strArr);
            if (delete > 0) {
                zzix.zzge().zzit().zze("Reset analytics data. app, records", str, Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzix.zzge().zzim().zze("Error resetting analytics data. appId, error", zzfg.zzbm(str), e);
        }
        zzdz zza = zza(getContext(), zzdz.packageName, zzdz.zzadm, zzdz.zzadw, zzdz.zzady, zzdz.zzadz, zzdz.zzaem);
        if (!zzgg().zzaz(zzdz.packageName) || zzdz.zzadw) {
            zzf(zza);
        }
    }

    /* access modifiers changed from: package-private */
    public final void zze(zzdz zzdz) {
        zzab();
        zzkq();
        Preconditions.checkNotEmpty(zzdz.packageName);
        zzg(zzdz);
    }

    /* JADX WARNING: Removed duplicated region for block: B:112:0x0399 A[Catch:{ SQLiteException -> 0x0141, all -> 0x03c2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x01e0 A[Catch:{ SQLiteException -> 0x0141, all -> 0x03c2 }] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzf(com.google.android.gms.internal.measurement.zzdz r19) {
        /*
            r18 = this;
            r1 = r18
            r2 = r19
            r18.zzab()
            r18.zzkq()
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r19)
            java.lang.String r0 = r2.packageName
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r0)
            java.lang.String r0 = r2.zzadm
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x001b
            return
        L_0x001b:
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()
            java.lang.String r3 = r2.packageName
            com.google.android.gms.internal.measurement.zzdy r0 = r0.zzbc(r3)
            r3 = 0
            if (r0 == 0) goto L_0x004e
            java.lang.String r5 = r0.getGmpAppId()
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 == 0) goto L_0x004e
            java.lang.String r5 = r2.zzadm
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x004e
            r0.zzs(r3)
            com.google.android.gms.internal.measurement.zzei r5 = r18.zzix()
            r5.zza((com.google.android.gms.internal.measurement.zzdy) r0)
            com.google.android.gms.internal.measurement.zzgf r0 = r18.zzkm()
            java.lang.String r5 = r2.packageName
            r0.zzbx(r5)
        L_0x004e:
            boolean r0 = r2.zzadw
            if (r0 != 0) goto L_0x0056
            r18.zzg((com.google.android.gms.internal.measurement.zzdz) r19)
            return
        L_0x0056:
            long r5 = r2.zzaem
            int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r0 != 0) goto L_0x0064
            com.google.android.gms.common.util.Clock r0 = r18.zzbt()
            long r5 = r0.currentTimeMillis()
        L_0x0064:
            int r0 = r2.zzaen
            r13 = 0
            r14 = 1
            if (r0 == 0) goto L_0x0085
            if (r0 == r14) goto L_0x0085
            com.google.android.gms.internal.measurement.zzfg r7 = r18.zzge()
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzip()
            java.lang.String r8 = "Incorrect app type, assuming installed app. appId, appType"
            java.lang.String r9 = r2.packageName
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r9)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r7.zze(r8, r9, r0)
            r15 = 0
            goto L_0x0086
        L_0x0085:
            r15 = r0
        L_0x0086:
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()
            r0.beginTransaction()
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()     // Catch:{ all -> 0x03c2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzdy r0 = r0.zzbc(r7)     // Catch:{ all -> 0x03c2 }
            r16 = 0
            if (r0 == 0) goto L_0x0155
            java.lang.String r7 = r0.getGmpAppId()     // Catch:{ all -> 0x03c2 }
            if (r7 == 0) goto L_0x0155
            java.lang.String r7 = r0.getGmpAppId()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = r2.zzadm     // Catch:{ all -> 0x03c2 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x03c2 }
            if (r7 != 0) goto L_0x0155
            com.google.android.gms.internal.measurement.zzfg r7 = r18.zzge()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzip()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "New GMP App Id passed in. Removing cached database data. appId"
            java.lang.String r9 = r0.zzah()     // Catch:{ all -> 0x03c2 }
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r9)     // Catch:{ all -> 0x03c2 }
            r7.zzg(r8, r9)     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzei r7 = r18.zzix()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = r0.zzah()     // Catch:{ all -> 0x03c2 }
            r7.zzch()     // Catch:{ all -> 0x03c2 }
            r7.zzab()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r8)     // Catch:{ all -> 0x03c2 }
            android.database.sqlite.SQLiteDatabase r0 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0141 }
            java.lang.String[] r9 = new java.lang.String[r14]     // Catch:{ SQLiteException -> 0x0141 }
            r9[r13] = r8     // Catch:{ SQLiteException -> 0x0141 }
            java.lang.String r10 = "events"
            java.lang.String r11 = "app_id=?"
            int r10 = r0.delete(r10, r11, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r13
            java.lang.String r11 = "user_attributes"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "conditional_properties"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "apps"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "raw_events"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "raw_events_metadata"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "event_filters"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "property_filters"
            java.lang.String r12 = "app_id=?"
            int r11 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r11
            java.lang.String r11 = "audience_filter_values"
            java.lang.String r12 = "app_id=?"
            int r0 = r0.delete(r11, r12, r9)     // Catch:{ SQLiteException -> 0x0141 }
            int r10 = r10 + r0
            if (r10 <= 0) goto L_0x0153
            com.google.android.gms.internal.measurement.zzfg r0 = r7.zzge()     // Catch:{ SQLiteException -> 0x0141 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()     // Catch:{ SQLiteException -> 0x0141 }
            java.lang.String r9 = "Deleted application data. app, records"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ SQLiteException -> 0x0141 }
            r0.zze(r9, r8, r10)     // Catch:{ SQLiteException -> 0x0141 }
            goto L_0x0153
        L_0x0141:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r7 = r7.zzge()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzim()     // Catch:{ all -> 0x03c2 }
            java.lang.String r9 = "Error deleting application data. appId, error"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x03c2 }
            r7.zze(r9, r8, r0)     // Catch:{ all -> 0x03c2 }
        L_0x0153:
            r0 = r16
        L_0x0155:
            if (r0 == 0) goto L_0x01bf
            long r7 = r0.zzgm()     // Catch:{ all -> 0x03c2 }
            r9 = -2147483648(0xffffffff80000000, double:NaN)
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x018e
            long r7 = r0.zzgm()     // Catch:{ all -> 0x03c2 }
            long r9 = r2.zzads     // Catch:{ all -> 0x03c2 }
            int r11 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r11 == 0) goto L_0x01bf
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ all -> 0x03c2 }
            r7.<init>()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_pv"
            java.lang.String r0 = r0.zzag()     // Catch:{ all -> 0x03c2 }
            r7.putString(r8, r0)     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzeu r0 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_au"
            com.google.android.gms.internal.measurement.zzer r9 = new com.google.android.gms.internal.measurement.zzer     // Catch:{ all -> 0x03c2 }
            r9.<init>(r7)     // Catch:{ all -> 0x03c2 }
            java.lang.String r10 = "auto"
            r7 = r0
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x03c2 }
        L_0x018a:
            r1.zzb((com.google.android.gms.internal.measurement.zzeu) r0, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
            goto L_0x01bf
        L_0x018e:
            java.lang.String r7 = r0.zzag()     // Catch:{ all -> 0x03c2 }
            if (r7 == 0) goto L_0x01bf
            java.lang.String r7 = r0.zzag()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = r2.zzth     // Catch:{ all -> 0x03c2 }
            boolean r7 = r7.equals(r8)     // Catch:{ all -> 0x03c2 }
            if (r7 != 0) goto L_0x01bf
            android.os.Bundle r7 = new android.os.Bundle     // Catch:{ all -> 0x03c2 }
            r7.<init>()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_pv"
            java.lang.String r0 = r0.zzag()     // Catch:{ all -> 0x03c2 }
            r7.putString(r8, r0)     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzeu r0 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_au"
            com.google.android.gms.internal.measurement.zzer r9 = new com.google.android.gms.internal.measurement.zzer     // Catch:{ all -> 0x03c2 }
            r9.<init>(r7)     // Catch:{ all -> 0x03c2 }
            java.lang.String r10 = "auto"
            r7 = r0
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x03c2 }
            goto L_0x018a
        L_0x01bf:
            r18.zzg((com.google.android.gms.internal.measurement.zzdz) r19)     // Catch:{ all -> 0x03c2 }
            if (r15 != 0) goto L_0x01d1
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()     // Catch:{ all -> 0x03c2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_f"
        L_0x01cc:
            com.google.android.gms.internal.measurement.zzeq r0 = r0.zzf(r7, r8)     // Catch:{ all -> 0x03c2 }
            goto L_0x01de
        L_0x01d1:
            if (r15 != r14) goto L_0x01dc
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()     // Catch:{ all -> 0x03c2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_v"
            goto L_0x01cc
        L_0x01dc:
            r0 = r16
        L_0x01de:
            if (r0 != 0) goto L_0x0399
            r7 = 3600000(0x36ee80, double:1.7786363E-317)
            long r9 = r5 / r7
            r11 = 1
            long r9 = r9 + r11
            long r9 = r9 * r7
            if (r15 != 0) goto L_0x0328
            com.google.android.gms.internal.measurement.zzjx r0 = new com.google.android.gms.internal.measurement.zzjx     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_fot"
            java.lang.Long r15 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x03c2 }
            java.lang.String r17 = "auto"
            r7 = r0
            r9 = r5
            r13 = r11
            r11 = r15
            r12 = r17
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x03c2 }
            r1.zzb((com.google.android.gms.internal.measurement.zzjx) r0, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
            r18.zzab()     // Catch:{ all -> 0x03c2 }
            r18.zzkq()     // Catch:{ all -> 0x03c2 }
            android.os.Bundle r15 = new android.os.Bundle     // Catch:{ all -> 0x03c2 }
            r15.<init>()     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "_c"
            r15.putLong(r0, r13)     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "_r"
            r15.putLong(r0, r13)     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "_uwa"
            r15.putLong(r0, r3)     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "_pfo"
            r15.putLong(r0, r3)     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "_sys"
            r15.putLong(r0, r3)     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "_sysu"
            r15.putLong(r0, r3)     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzef r0 = r18.zzgg()     // Catch:{ all -> 0x03c2 }
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x03c2 }
            boolean r0 = r0.zzaz(r7)     // Catch:{ all -> 0x03c2 }
            if (r0 == 0) goto L_0x0240
            boolean r0 = r2.zzaeo     // Catch:{ all -> 0x03c2 }
            if (r0 == 0) goto L_0x0240
            java.lang.String r0 = "_dac"
            r15.putLong(r0, r13)     // Catch:{ all -> 0x03c2 }
        L_0x0240:
            android.content.Context r0 = r18.getContext()     // Catch:{ all -> 0x03c2 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x03c2 }
            if (r0 != 0) goto L_0x025f
            com.google.android.gms.internal.measurement.zzfg r0 = r18.zzge()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x03c2 }
            java.lang.String r7 = "PackageManager is null, first open report might be inaccurate. appId"
            java.lang.String r8 = r2.packageName     // Catch:{ all -> 0x03c2 }
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ all -> 0x03c2 }
            r0.zzg(r7, r8)     // Catch:{ all -> 0x03c2 }
            goto L_0x02f4
        L_0x025f:
            android.content.Context r0 = r18.getContext()     // Catch:{ NameNotFoundException -> 0x026f }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x026f }
            java.lang.String r7 = r2.packageName     // Catch:{ NameNotFoundException -> 0x026f }
            r8 = 0
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r7, r8)     // Catch:{ NameNotFoundException -> 0x026f }
            goto L_0x0285
        L_0x026f:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r7 = r18.zzge()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzim()     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "Package info is null, first open report might be inaccurate. appId"
            java.lang.String r9 = r2.packageName     // Catch:{ all -> 0x03c2 }
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r9)     // Catch:{ all -> 0x03c2 }
            r7.zze(r8, r9, r0)     // Catch:{ all -> 0x03c2 }
            r0 = r16
        L_0x0285:
            if (r0 == 0) goto L_0x02b6
            long r7 = r0.firstInstallTime     // Catch:{ all -> 0x03c2 }
            int r9 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r9 == 0) goto L_0x02b6
            long r7 = r0.firstInstallTime     // Catch:{ all -> 0x03c2 }
            long r9 = r0.lastUpdateTime     // Catch:{ all -> 0x03c2 }
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x029c
            java.lang.String r0 = "_uwa"
            r15.putLong(r0, r13)     // Catch:{ all -> 0x03c2 }
            r0 = 0
            goto L_0x029d
        L_0x029c:
            r0 = 1
        L_0x029d:
            com.google.android.gms.internal.measurement.zzjx r12 = new com.google.android.gms.internal.measurement.zzjx     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_fi"
            if (r0 == 0) goto L_0x02a5
            r9 = r13
            goto L_0x02a6
        L_0x02a5:
            r9 = r3
        L_0x02a6:
            java.lang.Long r11 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x03c2 }
            java.lang.String r0 = "auto"
            r7 = r12
            r9 = r5
            r3 = r12
            r12 = r0
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x03c2 }
            r1.zzb((com.google.android.gms.internal.measurement.zzjx) r3, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
        L_0x02b6:
            android.content.Context r0 = r18.getContext()     // Catch:{ NameNotFoundException -> 0x02c6 }
            com.google.android.gms.common.wrappers.PackageManagerWrapper r0 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r0)     // Catch:{ NameNotFoundException -> 0x02c6 }
            java.lang.String r3 = r2.packageName     // Catch:{ NameNotFoundException -> 0x02c6 }
            r4 = 0
            android.content.pm.ApplicationInfo r0 = r0.getApplicationInfo(r3, r4)     // Catch:{ NameNotFoundException -> 0x02c6 }
            goto L_0x02dc
        L_0x02c6:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r3 = r18.zzge()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ all -> 0x03c2 }
            java.lang.String r4 = "Application info is null, first open report might be inaccurate. appId"
            java.lang.String r7 = r2.packageName     // Catch:{ all -> 0x03c2 }
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r7)     // Catch:{ all -> 0x03c2 }
            r3.zze(r4, r7, r0)     // Catch:{ all -> 0x03c2 }
            r0 = r16
        L_0x02dc:
            if (r0 == 0) goto L_0x02f4
            int r3 = r0.flags     // Catch:{ all -> 0x03c2 }
            r4 = 1
            r3 = r3 & r4
            if (r3 == 0) goto L_0x02e9
            java.lang.String r3 = "_sys"
            r15.putLong(r3, r13)     // Catch:{ all -> 0x03c2 }
        L_0x02e9:
            int r0 = r0.flags     // Catch:{ all -> 0x03c2 }
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x02f4
            java.lang.String r0 = "_sysu"
            r15.putLong(r0, r13)     // Catch:{ all -> 0x03c2 }
        L_0x02f4:
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()     // Catch:{ all -> 0x03c2 }
            java.lang.String r3 = r2.packageName     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r3)     // Catch:{ all -> 0x03c2 }
            r0.zzab()     // Catch:{ all -> 0x03c2 }
            r0.zzch()     // Catch:{ all -> 0x03c2 }
            java.lang.String r4 = "first_open_count"
            long r3 = r0.zzm(r3, r4)     // Catch:{ all -> 0x03c2 }
            r7 = 0
            int r0 = (r3 > r7 ? 1 : (r3 == r7 ? 0 : -1))
            if (r0 < 0) goto L_0x0314
            java.lang.String r0 = "_pfo"
            r15.putLong(r0, r3)     // Catch:{ all -> 0x03c2 }
        L_0x0314:
            com.google.android.gms.internal.measurement.zzeu r0 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_f"
            com.google.android.gms.internal.measurement.zzer r9 = new com.google.android.gms.internal.measurement.zzer     // Catch:{ all -> 0x03c2 }
            r9.<init>(r15)     // Catch:{ all -> 0x03c2 }
            java.lang.String r10 = "auto"
            r7 = r0
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x03c2 }
            r1.zzb((com.google.android.gms.internal.measurement.zzeu) r0, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
            goto L_0x037b
        L_0x0328:
            r13 = r11
            r3 = 1
            if (r15 != r3) goto L_0x037b
            com.google.android.gms.internal.measurement.zzjx r0 = new com.google.android.gms.internal.measurement.zzjx     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_fvt"
            java.lang.Long r11 = java.lang.Long.valueOf(r9)     // Catch:{ all -> 0x03c2 }
            java.lang.String r12 = "auto"
            r7 = r0
            r9 = r5
            r7.<init>(r8, r9, r11, r12)     // Catch:{ all -> 0x03c2 }
            r1.zzb((com.google.android.gms.internal.measurement.zzjx) r0, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
            r18.zzab()     // Catch:{ all -> 0x03c2 }
            r18.zzkq()     // Catch:{ all -> 0x03c2 }
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x03c2 }
            r0.<init>()     // Catch:{ all -> 0x03c2 }
            java.lang.String r3 = "_c"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x03c2 }
            java.lang.String r3 = "_r"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzef r3 = r18.zzgg()     // Catch:{ all -> 0x03c2 }
            java.lang.String r4 = r2.packageName     // Catch:{ all -> 0x03c2 }
            boolean r3 = r3.zzaz(r4)     // Catch:{ all -> 0x03c2 }
            if (r3 == 0) goto L_0x0368
            boolean r3 = r2.zzaeo     // Catch:{ all -> 0x03c2 }
            if (r3 == 0) goto L_0x0368
            java.lang.String r3 = "_dac"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x03c2 }
        L_0x0368:
            com.google.android.gms.internal.measurement.zzeu r3 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_v"
            com.google.android.gms.internal.measurement.zzer r9 = new com.google.android.gms.internal.measurement.zzer     // Catch:{ all -> 0x03c2 }
            r9.<init>(r0)     // Catch:{ all -> 0x03c2 }
            java.lang.String r10 = "auto"
            r7 = r3
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x03c2 }
            r1.zzb((com.google.android.gms.internal.measurement.zzeu) r3, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
        L_0x037b:
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x03c2 }
            r0.<init>()     // Catch:{ all -> 0x03c2 }
            java.lang.String r3 = "_et"
            r0.putLong(r3, r13)     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzeu r3 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_e"
            com.google.android.gms.internal.measurement.zzer r9 = new com.google.android.gms.internal.measurement.zzer     // Catch:{ all -> 0x03c2 }
            r9.<init>(r0)     // Catch:{ all -> 0x03c2 }
            java.lang.String r10 = "auto"
            r7 = r3
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x03c2 }
        L_0x0395:
            r1.zzb((com.google.android.gms.internal.measurement.zzeu) r3, (com.google.android.gms.internal.measurement.zzdz) r2)     // Catch:{ all -> 0x03c2 }
            goto L_0x03b3
        L_0x0399:
            boolean r0 = r2.zzael     // Catch:{ all -> 0x03c2 }
            if (r0 == 0) goto L_0x03b3
            android.os.Bundle r0 = new android.os.Bundle     // Catch:{ all -> 0x03c2 }
            r0.<init>()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzeu r3 = new com.google.android.gms.internal.measurement.zzeu     // Catch:{ all -> 0x03c2 }
            java.lang.String r8 = "_cd"
            com.google.android.gms.internal.measurement.zzer r9 = new com.google.android.gms.internal.measurement.zzer     // Catch:{ all -> 0x03c2 }
            r9.<init>(r0)     // Catch:{ all -> 0x03c2 }
            java.lang.String r10 = "auto"
            r7 = r3
            r11 = r5
            r7.<init>(r8, r9, r10, r11)     // Catch:{ all -> 0x03c2 }
            goto L_0x0395
        L_0x03b3:
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()     // Catch:{ all -> 0x03c2 }
            r0.setTransactionSuccessful()     // Catch:{ all -> 0x03c2 }
            com.google.android.gms.internal.measurement.zzei r0 = r18.zzix()
            r0.endTransaction()
            return
        L_0x03c2:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzei r2 = r18.zzix()
            r2.endTransaction()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzf(com.google.android.gms.internal.measurement.zzdz):void");
    }

    public zzeo zzfw() {
        return this.zzacw.zzfw();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzg(Runnable runnable) {
        zzab();
        if (this.zzaqi == null) {
            this.zzaqi = new ArrayList();
        }
        this.zzaqi.add(runnable);
    }

    public zzfe zzga() {
        return this.zzacw.zzga();
    }

    public zzka zzgb() {
        return this.zzacw.zzgb();
    }

    public zzgg zzgd() {
        return this.zzacw.zzgd();
    }

    public zzfg zzge() {
        return this.zzacw.zzge();
    }

    public zzfr zzgf() {
        return this.zzacw.zzgf();
    }

    public zzef zzgg() {
        return this.zzacw.zzgg();
    }

    public final String zzh(zzdz zzdz) {
        try {
            return (String) zzgd().zzb(new zzju(this, zzdz)).get(30000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            zzge().zzim().zze("Failed to get app instance id. appId", zzfg.zzbm(zzdz.packageName), e);
            return null;
        }
    }

    public final zzeb zziw() {
        zza((zzjq) this.zzaqf);
        return this.zzaqf;
    }

    public final zzei zzix() {
        zza((zzjq) this.zzaqc);
        return this.zzaqc;
    }

    public final zzfk zzkn() {
        zza((zzjq) this.zzaqb);
        return this.zzaqb;
    }

    /* access modifiers changed from: package-private */
    public final void zzkq() {
        if (!this.zzvo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:77|78) */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        zzge().zzim().zze("Failed to parse upload URL. Not uploading. appId", com.google.android.gms.internal.measurement.zzfg.zzbm(r5), r6);
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:77:0x0254 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzks() {
        /*
            r17 = this;
            r1 = r17
            r17.zzab()
            r17.zzkq()
            r0 = 1
            r1.zzaqn = r0
            r2 = 0
            com.google.android.gms.internal.measurement.zzgl r3 = r1.zzacw     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzii r3 = r3.zzfx()     // Catch:{ all -> 0x028c }
            java.lang.Boolean r3 = r3.zzkf()     // Catch:{ all -> 0x028c }
            if (r3 != 0) goto L_0x002b
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()     // Catch:{ all -> 0x028c }
            java.lang.String r3 = "Upload data called on the client side before use of service was decided"
        L_0x0022:
            r0.log(r3)     // Catch:{ all -> 0x028c }
        L_0x0025:
            r1.zzaqn = r2
            r17.zzkv()
            return
        L_0x002b:
            boolean r3 = r3.booleanValue()     // Catch:{ all -> 0x028c }
            if (r3 == 0) goto L_0x003c
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x028c }
            java.lang.String r3 = "Upload called in the client side when service should be used"
            goto L_0x0022
        L_0x003c:
            long r3 = r1.zzaqh     // Catch:{ all -> 0x028c }
            r5 = 0
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 <= 0) goto L_0x0048
        L_0x0044:
            r17.zzku()     // Catch:{ all -> 0x028c }
            goto L_0x0025
        L_0x0048:
            r17.zzab()     // Catch:{ all -> 0x028c }
            java.util.List<java.lang.Long> r3 = r1.zzaqq     // Catch:{ all -> 0x028c }
            if (r3 == 0) goto L_0x0051
            r3 = 1
            goto L_0x0052
        L_0x0051:
            r3 = 0
        L_0x0052:
            if (r3 == 0) goto L_0x005f
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()     // Catch:{ all -> 0x028c }
            java.lang.String r3 = "Uploading requested multiple times"
            goto L_0x0022
        L_0x005f:
            com.google.android.gms.internal.measurement.zzfk r3 = r17.zzkn()     // Catch:{ all -> 0x028c }
            boolean r3 = r3.zzex()     // Catch:{ all -> 0x028c }
            if (r3 != 0) goto L_0x0077
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()     // Catch:{ all -> 0x028c }
            java.lang.String r3 = "Network not connected, ignoring upload request"
            r0.log(r3)     // Catch:{ all -> 0x028c }
            goto L_0x0044
        L_0x0077:
            com.google.android.gms.common.util.Clock r3 = r17.zzbt()     // Catch:{ all -> 0x028c }
            long r3 = r3.currentTimeMillis()     // Catch:{ all -> 0x028c }
            long r7 = com.google.android.gms.internal.measurement.zzef.zzhi()     // Catch:{ all -> 0x028c }
            r9 = 0
            long r7 = r3 - r7
            r9 = 0
            r1.zzd(r9, r7)     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfr r7 = r17.zzgf()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfu r7 = r7.zzaju     // Catch:{ all -> 0x028c }
            long r7 = r7.get()     // Catch:{ all -> 0x028c }
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x00b0
            com.google.android.gms.internal.measurement.zzfg r5 = r17.zzge()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzis()     // Catch:{ all -> 0x028c }
            java.lang.String r6 = "Uploading events. Elapsed time since last upload attempt (ms)"
            r10 = 0
            long r7 = r3 - r7
            long r7 = java.lang.Math.abs(r7)     // Catch:{ all -> 0x028c }
            java.lang.Long r7 = java.lang.Long.valueOf(r7)     // Catch:{ all -> 0x028c }
            r5.zzg(r6, r7)     // Catch:{ all -> 0x028c }
        L_0x00b0:
            com.google.android.gms.internal.measurement.zzei r5 = r17.zzix()     // Catch:{ all -> 0x028c }
            java.lang.String r5 = r5.zzhn()     // Catch:{ all -> 0x028c }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x028c }
            r7 = -1
            if (r6 != 0) goto L_0x0267
            long r10 = r1.zzaqs     // Catch:{ all -> 0x028c }
            int r6 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r6 != 0) goto L_0x00d0
            com.google.android.gms.internal.measurement.zzei r6 = r17.zzix()     // Catch:{ all -> 0x028c }
            long r6 = r6.zzhu()     // Catch:{ all -> 0x028c }
            r1.zzaqs = r6     // Catch:{ all -> 0x028c }
        L_0x00d0:
            com.google.android.gms.internal.measurement.zzef r6 = r17.zzgg()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r7 = com.google.android.gms.internal.measurement.zzew.zzago     // Catch:{ all -> 0x028c }
            int r6 = r6.zzb(r5, r7)     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzef r7 = r17.zzgg()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzex<java.lang.Integer> r8 = com.google.android.gms.internal.measurement.zzew.zzagp     // Catch:{ all -> 0x028c }
            int r7 = r7.zzb(r5, r8)     // Catch:{ all -> 0x028c }
            int r7 = java.lang.Math.max(r2, r7)     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzei r8 = r17.zzix()     // Catch:{ all -> 0x028c }
            java.util.List r6 = r8.zzb((java.lang.String) r5, (int) r6, (int) r7)     // Catch:{ all -> 0x028c }
            boolean r7 = r6.isEmpty()     // Catch:{ all -> 0x028c }
            if (r7 != 0) goto L_0x0025
            java.util.Iterator r7 = r6.iterator()     // Catch:{ all -> 0x028c }
        L_0x00fa:
            boolean r8 = r7.hasNext()     // Catch:{ all -> 0x028c }
            if (r8 == 0) goto L_0x0115
            java.lang.Object r8 = r7.next()     // Catch:{ all -> 0x028c }
            android.util.Pair r8 = (android.util.Pair) r8     // Catch:{ all -> 0x028c }
            java.lang.Object r8 = r8.first     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq r8 = (com.google.android.gms.internal.measurement.zzkq) r8     // Catch:{ all -> 0x028c }
            java.lang.String r10 = r8.zzatv     // Catch:{ all -> 0x028c }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ all -> 0x028c }
            if (r10 != 0) goto L_0x00fa
            java.lang.String r7 = r8.zzatv     // Catch:{ all -> 0x028c }
            goto L_0x0116
        L_0x0115:
            r7 = r9
        L_0x0116:
            if (r7 == 0) goto L_0x0141
            r8 = 0
        L_0x0119:
            int r10 = r6.size()     // Catch:{ all -> 0x028c }
            if (r8 >= r10) goto L_0x0141
            java.lang.Object r10 = r6.get(r8)     // Catch:{ all -> 0x028c }
            android.util.Pair r10 = (android.util.Pair) r10     // Catch:{ all -> 0x028c }
            java.lang.Object r10 = r10.first     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq r10 = (com.google.android.gms.internal.measurement.zzkq) r10     // Catch:{ all -> 0x028c }
            java.lang.String r11 = r10.zzatv     // Catch:{ all -> 0x028c }
            boolean r11 = android.text.TextUtils.isEmpty(r11)     // Catch:{ all -> 0x028c }
            if (r11 != 0) goto L_0x013e
            java.lang.String r10 = r10.zzatv     // Catch:{ all -> 0x028c }
            boolean r10 = r10.equals(r7)     // Catch:{ all -> 0x028c }
            if (r10 != 0) goto L_0x013e
            java.util.List r6 = r6.subList(r2, r8)     // Catch:{ all -> 0x028c }
            goto L_0x0141
        L_0x013e:
            int r8 = r8 + 1
            goto L_0x0119
        L_0x0141:
            com.google.android.gms.internal.measurement.zzkp r7 = new com.google.android.gms.internal.measurement.zzkp     // Catch:{ all -> 0x028c }
            r7.<init>()     // Catch:{ all -> 0x028c }
            int r8 = r6.size()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq[] r8 = new com.google.android.gms.internal.measurement.zzkq[r8]     // Catch:{ all -> 0x028c }
            r7.zzatf = r8     // Catch:{ all -> 0x028c }
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch:{ all -> 0x028c }
            int r10 = r6.size()     // Catch:{ all -> 0x028c }
            r8.<init>(r10)     // Catch:{ all -> 0x028c }
            boolean r10 = com.google.android.gms.internal.measurement.zzef.zzhk()     // Catch:{ all -> 0x028c }
            if (r10 == 0) goto L_0x0169
            com.google.android.gms.internal.measurement.zzef r10 = r17.zzgg()     // Catch:{ all -> 0x028c }
            boolean r10 = r10.zzat(r5)     // Catch:{ all -> 0x028c }
            if (r10 == 0) goto L_0x0169
            r10 = 1
            goto L_0x016a
        L_0x0169:
            r10 = 0
        L_0x016a:
            r11 = 0
        L_0x016b:
            com.google.android.gms.internal.measurement.zzkq[] r12 = r7.zzatf     // Catch:{ all -> 0x028c }
            int r12 = r12.length     // Catch:{ all -> 0x028c }
            if (r11 >= r12) goto L_0x01b6
            com.google.android.gms.internal.measurement.zzkq[] r12 = r7.zzatf     // Catch:{ all -> 0x028c }
            java.lang.Object r13 = r6.get(r11)     // Catch:{ all -> 0x028c }
            android.util.Pair r13 = (android.util.Pair) r13     // Catch:{ all -> 0x028c }
            java.lang.Object r13 = r13.first     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq r13 = (com.google.android.gms.internal.measurement.zzkq) r13     // Catch:{ all -> 0x028c }
            r12[r11] = r13     // Catch:{ all -> 0x028c }
            java.lang.Object r12 = r6.get(r11)     // Catch:{ all -> 0x028c }
            android.util.Pair r12 = (android.util.Pair) r12     // Catch:{ all -> 0x028c }
            java.lang.Object r12 = r12.second     // Catch:{ all -> 0x028c }
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ all -> 0x028c }
            r8.add(r12)     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq[] r12 = r7.zzatf     // Catch:{ all -> 0x028c }
            r12 = r12[r11]     // Catch:{ all -> 0x028c }
            r13 = 12451(0x30a3, double:6.1516E-320)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)     // Catch:{ all -> 0x028c }
            r12.zzatu = r13     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq[] r12 = r7.zzatf     // Catch:{ all -> 0x028c }
            r12 = r12[r11]     // Catch:{ all -> 0x028c }
            java.lang.Long r13 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x028c }
            r12.zzatk = r13     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzkq[] r12 = r7.zzatf     // Catch:{ all -> 0x028c }
            r12 = r12[r11]     // Catch:{ all -> 0x028c }
            java.lang.Boolean r13 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x028c }
            r12.zzatz = r13     // Catch:{ all -> 0x028c }
            if (r10 != 0) goto L_0x01b3
            com.google.android.gms.internal.measurement.zzkq[] r12 = r7.zzatf     // Catch:{ all -> 0x028c }
            r12 = r12[r11]     // Catch:{ all -> 0x028c }
            r12.zzauh = r9     // Catch:{ all -> 0x028c }
        L_0x01b3:
            int r11 = r11 + 1
            goto L_0x016b
        L_0x01b6:
            com.google.android.gms.internal.measurement.zzfg r6 = r17.zzge()     // Catch:{ all -> 0x028c }
            r10 = 2
            boolean r6 = r6.isLoggable(r10)     // Catch:{ all -> 0x028c }
            if (r6 == 0) goto L_0x01c9
            com.google.android.gms.internal.measurement.zzfe r6 = r17.zzga()     // Catch:{ all -> 0x028c }
            java.lang.String r9 = r6.zza((com.google.android.gms.internal.measurement.zzkp) r7)     // Catch:{ all -> 0x028c }
        L_0x01c9:
            com.google.android.gms.internal.measurement.zzka r6 = r17.zzgb()     // Catch:{ all -> 0x028c }
            byte[] r14 = r6.zzb((com.google.android.gms.internal.measurement.zzkp) r7)     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzex<java.lang.String> r6 = com.google.android.gms.internal.measurement.zzew.zzagy     // Catch:{ all -> 0x028c }
            java.lang.Object r6 = r6.get()     // Catch:{ all -> 0x028c }
            java.lang.String r6 = (java.lang.String) r6     // Catch:{ all -> 0x028c }
            java.net.URL r13 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0254 }
            r13.<init>(r6)     // Catch:{ MalformedURLException -> 0x0254 }
            boolean r10 = r8.isEmpty()     // Catch:{ MalformedURLException -> 0x0254 }
            r10 = r10 ^ r0
            com.google.android.gms.common.internal.Preconditions.checkArgument(r10)     // Catch:{ MalformedURLException -> 0x0254 }
            java.util.List<java.lang.Long> r10 = r1.zzaqq     // Catch:{ MalformedURLException -> 0x0254 }
            if (r10 == 0) goto L_0x01f8
            com.google.android.gms.internal.measurement.zzfg r8 = r17.zzge()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzim()     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r10 = "Set uploading progress before finishing the previous upload"
            r8.log(r10)     // Catch:{ MalformedURLException -> 0x0254 }
            goto L_0x01ff
        L_0x01f8:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ MalformedURLException -> 0x0254 }
            r10.<init>(r8)     // Catch:{ MalformedURLException -> 0x0254 }
            r1.zzaqq = r10     // Catch:{ MalformedURLException -> 0x0254 }
        L_0x01ff:
            com.google.android.gms.internal.measurement.zzfr r8 = r17.zzgf()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzfu r8 = r8.zzajv     // Catch:{ MalformedURLException -> 0x0254 }
            r8.set(r3)     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r3 = "?"
            com.google.android.gms.internal.measurement.zzkq[] r4 = r7.zzatf     // Catch:{ MalformedURLException -> 0x0254 }
            int r4 = r4.length     // Catch:{ MalformedURLException -> 0x0254 }
            if (r4 <= 0) goto L_0x0215
            com.google.android.gms.internal.measurement.zzkq[] r3 = r7.zzatf     // Catch:{ MalformedURLException -> 0x0254 }
            r3 = r3[r2]     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r3 = r3.zzti     // Catch:{ MalformedURLException -> 0x0254 }
        L_0x0215:
            com.google.android.gms.internal.measurement.zzfg r4 = r17.zzge()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzit()     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.String r7 = "Uploading data. app, uncompressed size, data"
            int r8 = r14.length     // Catch:{ MalformedURLException -> 0x0254 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ MalformedURLException -> 0x0254 }
            r4.zzd(r7, r3, r8, r9)     // Catch:{ MalformedURLException -> 0x0254 }
            r1.zzaqm = r0     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzfk r11 = r17.zzkn()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzjs r0 = new com.google.android.gms.internal.measurement.zzjs     // Catch:{ MalformedURLException -> 0x0254 }
            r0.<init>(r1, r5)     // Catch:{ MalformedURLException -> 0x0254 }
            r11.zzab()     // Catch:{ MalformedURLException -> 0x0254 }
            r11.zzch()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r13)     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r14)     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r0)     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzgg r3 = r11.zzgd()     // Catch:{ MalformedURLException -> 0x0254 }
            com.google.android.gms.internal.measurement.zzfo r4 = new com.google.android.gms.internal.measurement.zzfo     // Catch:{ MalformedURLException -> 0x0254 }
            r15 = 0
            r10 = r4
            r12 = r5
            r16 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16)     // Catch:{ MalformedURLException -> 0x0254 }
            r3.zzd((java.lang.Runnable) r4)     // Catch:{ MalformedURLException -> 0x0254 }
            goto L_0x0025
        L_0x0254:
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x028c }
            java.lang.String r3 = "Failed to parse upload URL. Not uploading. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r5)     // Catch:{ all -> 0x028c }
            r0.zze(r3, r4, r6)     // Catch:{ all -> 0x028c }
            goto L_0x0025
        L_0x0267:
            r1.zzaqs = r7     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzei r0 = r17.zzix()     // Catch:{ all -> 0x028c }
            long r5 = com.google.android.gms.internal.measurement.zzef.zzhi()     // Catch:{ all -> 0x028c }
            r7 = 0
            long r3 = r3 - r5
            java.lang.String r0 = r0.zzab(r3)     // Catch:{ all -> 0x028c }
            boolean r3 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x028c }
            if (r3 != 0) goto L_0x0025
            com.google.android.gms.internal.measurement.zzei r3 = r17.zzix()     // Catch:{ all -> 0x028c }
            com.google.android.gms.internal.measurement.zzdy r0 = r3.zzbc(r0)     // Catch:{ all -> 0x028c }
            if (r0 == 0) goto L_0x0025
            r1.zzb((com.google.android.gms.internal.measurement.zzdy) r0)     // Catch:{ all -> 0x028c }
            goto L_0x0025
        L_0x028c:
            r0 = move-exception
            r1.zzaqn = r2
            r17.zzkv()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzjr.zzks():void");
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzkx() {
        zzfi zzim;
        String str;
        zzab();
        zzkq();
        if (!this.zzaqg) {
            zzge().zzir().log("This instance being marked as an uploader");
            zzab();
            zzkq();
            if (zzky() && zzkw()) {
                int zza = zza(this.zzaqp);
                int zzij = this.zzacw.zzfv().zzij();
                zzab();
                if (zza > zzij) {
                    zzim = zzge().zzim();
                    str = "Panic: can't downgrade version. Previous, current version";
                } else if (zza < zzij) {
                    if (zza(zzij, this.zzaqp)) {
                        zzim = zzge().zzit();
                        str = "Storage version upgraded. Previous, current version";
                    } else {
                        zzim = zzge().zzim();
                        str = "Storage version upgrade failed. Previous, current version";
                    }
                }
                zzim.zze(str, Integer.valueOf(zza), Integer.valueOf(zzij));
            }
            this.zzaqg = true;
            zzku();
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzkz() {
        this.zzaqk++;
    }

    /* access modifiers changed from: package-private */
    public final zzgl zzla() {
        return this.zzacw;
    }

    public final void zzm(boolean z) {
        zzku();
    }
}
