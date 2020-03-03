package com.google.android.gms.internal.measurement;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import bolts.MeasurementEvent;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.service.DeviceInfoResult;
import com.xiaomi.stat.a.j;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class zzei extends zzjq {
    /* access modifiers changed from: private */
    public static final String[] zzaev = {"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzaew = {"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
    /* access modifiers changed from: private */
    public static final String[] zzaex = {"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", DeviceInfoResult.BUNDLE_KEY_ANDROID_ID, "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzaey = {"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzaez = {"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
    /* access modifiers changed from: private */
    public static final String[] zzafa = {"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    private final zzel zzafb = new zzel(this, getContext(), "google_app_measurement.db");
    /* access modifiers changed from: private */
    public final zzjm zzafc = new zzjm(zzbt());

    zzei(zzjr zzjr) {
        super(zzjr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003b  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zza(java.lang.String r4, java.lang.String[] r5) {
        /*
            r3 = this;
            android.database.sqlite.SQLiteDatabase r0 = r3.getWritableDatabase()
            r1 = 0
            android.database.Cursor r5 = r0.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x002a }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r0 == 0) goto L_0x001a
            r0 = 0
            long r0 = r5.getLong(r0)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            if (r5 == 0) goto L_0x0019
            r5.close()
        L_0x0019:
            return r0
        L_0x001a:
            android.database.sqlite.SQLiteException r0 = new android.database.sqlite.SQLiteException     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            java.lang.String r1 = "Database returned empty set"
            r0.<init>(r1)     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
            throw r0     // Catch:{ SQLiteException -> 0x0024, all -> 0x0022 }
        L_0x0022:
            r4 = move-exception
            goto L_0x0039
        L_0x0024:
            r0 = move-exception
            r1 = r5
            goto L_0x002b
        L_0x0027:
            r4 = move-exception
            r5 = r1
            goto L_0x0039
        L_0x002a:
            r0 = move-exception
        L_0x002b:
            com.google.android.gms.internal.measurement.zzfg r5 = r3.zzge()     // Catch:{ all -> 0x0027 }
            com.google.android.gms.internal.measurement.zzfi r5 = r5.zzim()     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "Database error"
            r5.zze(r2, r4, r0)     // Catch:{ all -> 0x0027 }
            throw r0     // Catch:{ all -> 0x0027 }
        L_0x0039:
            if (r5 == 0) goto L_0x003e
            r5.close()
        L_0x003e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zza(java.lang.String, java.lang.String[]):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0039  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zza(java.lang.String r3, java.lang.String[] r4, long r5) {
        /*
            r2 = this;
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()
            r1 = 0
            android.database.Cursor r4 = r0.rawQuery(r3, r4)     // Catch:{ SQLiteException -> 0x0028 }
            boolean r0 = r4.moveToFirst()     // Catch:{ SQLiteException -> 0x0023, all -> 0x0020 }
            if (r0 == 0) goto L_0x001a
            r5 = 0
            long r5 = r4.getLong(r5)     // Catch:{ SQLiteException -> 0x0023, all -> 0x0020 }
            if (r4 == 0) goto L_0x0019
            r4.close()
        L_0x0019:
            return r5
        L_0x001a:
            if (r4 == 0) goto L_0x001f
            r4.close()
        L_0x001f:
            return r5
        L_0x0020:
            r3 = move-exception
            r1 = r4
            goto L_0x0037
        L_0x0023:
            r5 = move-exception
            r1 = r4
            goto L_0x0029
        L_0x0026:
            r3 = move-exception
            goto L_0x0037
        L_0x0028:
            r5 = move-exception
        L_0x0029:
            com.google.android.gms.internal.measurement.zzfg r4 = r2.zzge()     // Catch:{ all -> 0x0026 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x0026 }
            java.lang.String r6 = "Database error"
            r4.zze(r6, r3, r5)     // Catch:{ all -> 0x0026 }
            throw r5     // Catch:{ all -> 0x0026 }
        L_0x0037:
            if (r1 == 0) goto L_0x003c
            r1.close()
        L_0x003c:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zza(java.lang.String, java.lang.String[], long):long");
    }

    @WorkerThread
    @VisibleForTesting
    private final Object zza(Cursor cursor, int i) {
        int type = cursor.getType(i);
        switch (type) {
            case 0:
                zzge().zzim().log("Loaded invalid null value from database");
                return null;
            case 1:
                return Long.valueOf(cursor.getLong(i));
            case 2:
                return Double.valueOf(cursor.getDouble(i));
            case 3:
                return cursor.getString(i);
            case 4:
                zzge().zzim().log("Loaded invalid blob type value, ignoring it");
                return null;
            default:
                zzge().zzim().zzg("Loaded invalid unknown value type, ignoring it", Integer.valueOf(type));
                return null;
        }
    }

    @WorkerThread
    private static void zza(ContentValues contentValues, String str, Object obj) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(obj);
        if (obj instanceof String) {
            contentValues.put(str, (String) obj);
        } else if (obj instanceof Long) {
            contentValues.put(str, (Long) obj);
        } else if (obj instanceof Double) {
            contentValues.put(str, (Double) obj);
        } else {
            throw new IllegalArgumentException("Invalid value type");
        }
    }

    static void zza(zzfg zzfg, SQLiteDatabase sQLiteDatabase) {
        if (zzfg != null) {
            File file = new File(sQLiteDatabase.getPath());
            if (!file.setReadable(false, false)) {
                zzfg.zzip().log("Failed to turn off database read permission");
            }
            if (!file.setWritable(false, false)) {
                zzfg.zzip().log("Failed to turn off database write permission");
            }
            if (!file.setReadable(true, true)) {
                zzfg.zzip().log("Failed to turn on database read permission for owner");
            }
            if (!file.setWritable(true, true)) {
                zzfg.zzip().log("Failed to turn on database write permission for owner");
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    @WorkerThread
    static void zza(zzfg zzfg, SQLiteDatabase sQLiteDatabase, String str, String str2, String str3, String[] strArr) throws SQLiteException {
        if (zzfg != null) {
            if (!zza(zzfg, sQLiteDatabase, str)) {
                sQLiteDatabase.execSQL(str2);
            }
            if (zzfg != null) {
                try {
                    Set<String> zzb = zzb(sQLiteDatabase, str);
                    String[] split = str3.split(",");
                    int length = split.length;
                    int i = 0;
                    while (i < length) {
                        String str4 = split[i];
                        if (zzb.remove(str4)) {
                            i++;
                        } else {
                            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35 + String.valueOf(str4).length());
                            sb.append("Table ");
                            sb.append(str);
                            sb.append(" is missing required column: ");
                            sb.append(str4);
                            throw new SQLiteException(sb.toString());
                        }
                    }
                    if (strArr != null) {
                        for (int i2 = 0; i2 < strArr.length; i2 += 2) {
                            if (!zzb.remove(strArr[i2])) {
                                sQLiteDatabase.execSQL(strArr[i2 + 1]);
                            }
                        }
                    }
                    if (!zzb.isEmpty()) {
                        zzfg.zzip().zze("Table has extra columns. table, columns", str, TextUtils.join(", ", zzb));
                    }
                } catch (SQLiteException e) {
                    zzfg.zzim().zzg("Failed to verify columns on table that was just created", str);
                    throw e;
                }
            } else {
                throw new IllegalArgumentException("Monitor must not be null");
            }
        } else {
            throw new IllegalArgumentException("Monitor must not be null");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0041  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zza(com.google.android.gms.internal.measurement.zzfg r11, android.database.sqlite.SQLiteDatabase r12, java.lang.String r13) {
        /*
            if (r11 == 0) goto L_0x0045
            r0 = 0
            r1 = 0
            java.lang.String r3 = "SQLITE_MASTER"
            r2 = 1
            java.lang.String[] r4 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002f }
            java.lang.String r5 = "name"
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x002f }
            java.lang.String r5 = "name=?"
            java.lang.String[] r6 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002f }
            r6[r1] = r13     // Catch:{ SQLiteException -> 0x002f }
            r7 = 0
            r8 = 0
            r9 = 0
            r2 = r12
            android.database.Cursor r12 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ SQLiteException -> 0x002f }
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0028, all -> 0x0025 }
            if (r12 == 0) goto L_0x0024
            r12.close()
        L_0x0024:
            return r0
        L_0x0025:
            r11 = move-exception
            r0 = r12
            goto L_0x003f
        L_0x0028:
            r0 = move-exception
            r10 = r0
            r0 = r12
            r12 = r10
            goto L_0x0030
        L_0x002d:
            r11 = move-exception
            goto L_0x003f
        L_0x002f:
            r12 = move-exception
        L_0x0030:
            com.google.android.gms.internal.measurement.zzfi r11 = r11.zzip()     // Catch:{ all -> 0x002d }
            java.lang.String r2 = "Error querying for table"
            r11.zze(r2, r13, r12)     // Catch:{ all -> 0x002d }
            if (r0 == 0) goto L_0x003e
            r0.close()
        L_0x003e:
            return r1
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r11
        L_0x0045:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.String r12 = "Monitor must not be null"
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zza(com.google.android.gms.internal.measurement.zzfg, android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzke zzke) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzke);
        if (TextUtils.isEmpty(zzke.zzarq)) {
            zzge().zzip().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzfg.zzbm(str), Integer.valueOf(i), String.valueOf(zzke.zzarp));
            return false;
        }
        try {
            byte[] bArr = new byte[zzke.zzvm()];
            zzabw zzb = zzabw.zzb(bArr, 0, bArr.length);
            zzke.zza(zzb);
            zzb.zzve();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzke.zzarp);
            contentValues.put(MeasurementEvent.b, zzke.zzarq);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("event_filters", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzge().zzim().zzg("Failed to insert event filter (got -1). appId", zzfg.zzbm(str));
                return true;
            } catch (SQLiteException e) {
                zzge().zzim().zze("Error storing event filter. appId", zzfg.zzbm(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzge().zzim().zze("Configuration loss. Failed to serialize event filter. appId", zzfg.zzbm(str), e2);
            return false;
        }
    }

    @WorkerThread
    private final boolean zza(String str, int i, zzkh zzkh) {
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkh);
        if (TextUtils.isEmpty(zzkh.zzasf)) {
            zzge().zzip().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzfg.zzbm(str), Integer.valueOf(i), String.valueOf(zzkh.zzarp));
            return false;
        }
        try {
            byte[] bArr = new byte[zzkh.zzvm()];
            zzabw zzb = zzabw.zzb(bArr, 0, bArr.length);
            zzkh.zza(zzb);
            zzb.zzve();
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("audience_id", Integer.valueOf(i));
            contentValues.put("filter_id", zzkh.zzarp);
            contentValues.put("property_name", zzkh.zzasf);
            contentValues.put("data", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("property_filters", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzge().zzim().zzg("Failed to insert property filter (got -1). appId", zzfg.zzbm(str));
                return false;
            } catch (SQLiteException e) {
                zzge().zzim().zze("Error storing property filter. appId", zzfg.zzbm(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzge().zzim().zze("Configuration loss. Failed to serialize property filter. appId", zzfg.zzbm(str), e2);
            return false;
        }
    }

    private final boolean zza(String str, List<Integer> list) {
        Preconditions.checkNotEmpty(str);
        zzch();
        zzab();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        try {
            long zza = zza("select count(1) from audience_filter_values where app_id=?", new String[]{str});
            int max = Math.max(0, Math.min(2000, zzgg().zzb(str, zzew.zzahn)));
            if (zza <= ((long) max)) {
                return false;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                Integer num = list.get(i);
                if (num == null || !(num instanceof Integer)) {
                    return false;
                }
                arrayList.add(Integer.toString(num.intValue()));
            }
            String join = TextUtils.join(",", arrayList);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append(Operators.BRACKET_START_STR);
            sb.append(join);
            sb.append(Operators.BRACKET_END_STR);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 140);
            sb3.append("audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in ");
            sb3.append(sb2);
            sb3.append(" order by rowid desc limit -1 offset ?)");
            return writableDatabase.delete("audience_filter_values", sb3.toString(), new String[]{str, Integer.toString(max)}) > 0;
        } catch (SQLiteException e) {
            zzge().zzim().zze("Database error querying filters. appId", zzfg.zzbm(str), e);
            return false;
        }
    }

    @WorkerThread
    private static Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
        HashSet hashSet = new HashSet();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
        sb.append("SELECT * FROM ");
        sb.append(str);
        sb.append(" LIMIT 0");
        Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), (String[]) null);
        try {
            Collections.addAll(hashSet, rawQuery.getColumnNames());
            return hashSet;
        } finally {
            rawQuery.close();
        }
    }

    private final boolean zzhv() {
        return getContext().getDatabasePath("google_app_measurement.db").exists();
    }

    @WorkerThread
    public final void beginTransaction() {
        zzch();
        getWritableDatabase().beginTransaction();
    }

    @WorkerThread
    public final void endTransaction() {
        zzch();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final SQLiteDatabase getWritableDatabase() {
        zzab();
        try {
            return this.zzafb.getWritableDatabase();
        } catch (SQLiteException e) {
            zzge().zzip().zzg("Error opening database", e);
            throw e;
        }
    }

    @WorkerThread
    public final void setTransactionSuccessful() {
        zzch();
        getWritableDatabase().setTransactionSuccessful();
    }

    public final long zza(zzkq zzkq) throws IOException {
        long j;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzkq);
        Preconditions.checkNotEmpty(zzkq.zzti);
        try {
            byte[] bArr = new byte[zzkq.zzvm()];
            zzabw zzb = zzabw.zzb(bArr, 0, bArr.length);
            zzkq.zza(zzb);
            zzb.zzve();
            zzka zzgb = zzgb();
            Preconditions.checkNotNull(bArr);
            zzgb.zzab();
            MessageDigest messageDigest = zzka.getMessageDigest("MD5");
            if (messageDigest == null) {
                zzgb.zzge().zzim().log("Failed to get MD5");
                j = 0;
            } else {
                j = zzka.zzc(messageDigest.digest(bArr));
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzkq.zzti);
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put(TtmlNode.TAG_METADATA, bArr);
            try {
                getWritableDatabase().insertWithOnConflict("raw_events_metadata", (String) null, contentValues, 4);
                return j;
            } catch (SQLiteException e) {
                zzge().zzim().zze("Error storing raw event metadata. appId", zzfg.zzbm(zzkq.zzti), e);
                throw e;
            }
        } catch (IOException e2) {
            zzge().zzim().zze("Data loss. Failed to serialize event metadata. appId", zzfg.zzbm(zzkq.zzti), e2);
            throw e2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x008f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.util.Pair<com.google.android.gms.internal.measurement.zzkn, java.lang.Long> zza(java.lang.String r8, java.lang.Long r9) {
        /*
            r7 = this;
            r7.zzab()
            r7.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r2 = "select main_event, children_to_process from main_event_params where app_id=? and event_id=?"
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r4 = 0
            r3[r4] = r8     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            java.lang.String r5 = java.lang.String.valueOf(r9)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            r6 = 1
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            android.database.Cursor r1 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0077, all -> 0x0074 }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0072 }
            if (r2 != 0) goto L_0x0037
            com.google.android.gms.internal.measurement.zzfg r8 = r7.zzge()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzit()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r9 = "Main event not found"
            r8.log(r9)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r4)     // Catch:{ SQLiteException -> 0x0072 }
            long r5 = r1.getLong(r6)     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x0072 }
            int r5 = r2.length     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzabv r2 = com.google.android.gms.internal.measurement.zzabv.zza(r2, r4, r5)     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzkn r4 = new com.google.android.gms.internal.measurement.zzkn     // Catch:{ SQLiteException -> 0x0072 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x0072 }
            r4.zzb(r2)     // Catch:{ IOException -> 0x005a }
            android.util.Pair r8 = android.util.Pair.create(r4, r3)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0059
            r1.close()
        L_0x0059:
            return r8
        L_0x005a:
            r2 = move-exception
            com.google.android.gms.internal.measurement.zzfg r3 = r7.zzge()     // Catch:{ SQLiteException -> 0x0072 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ SQLiteException -> 0x0072 }
            java.lang.String r4 = "Failed to merge main event. appId, eventId"
            java.lang.Object r8 = com.google.android.gms.internal.measurement.zzfg.zzbm(r8)     // Catch:{ SQLiteException -> 0x0072 }
            r3.zzd(r4, r8, r9, r2)     // Catch:{ SQLiteException -> 0x0072 }
            if (r1 == 0) goto L_0x0071
            r1.close()
        L_0x0071:
            return r0
        L_0x0072:
            r8 = move-exception
            goto L_0x0079
        L_0x0074:
            r8 = move-exception
            r1 = r0
            goto L_0x008d
        L_0x0077:
            r8 = move-exception
            r1 = r0
        L_0x0079:
            com.google.android.gms.internal.measurement.zzfg r9 = r7.zzge()     // Catch:{ all -> 0x008c }
            com.google.android.gms.internal.measurement.zzfi r9 = r9.zzim()     // Catch:{ all -> 0x008c }
            java.lang.String r2 = "Error selecting main event"
            r9.zzg(r2, r8)     // Catch:{ all -> 0x008c }
            if (r1 == 0) goto L_0x008b
            r1.close()
        L_0x008b:
            return r0
        L_0x008c:
            r8 = move-exception
        L_0x008d:
            if (r1 == 0) goto L_0x0092
            r1.close()
        L_0x0092:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zza(java.lang.String, java.lang.Long):android.util.Pair");
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0139  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzej zza(long r20, java.lang.String r22, boolean r23, boolean r24, boolean r25, boolean r26, boolean r27) {
        /*
            r19 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            r19.zzab()
            r19.zzch()
            r0 = 1
            java.lang.String[] r2 = new java.lang.String[r0]
            r3 = 0
            r2[r3] = r22
            com.google.android.gms.internal.measurement.zzej r4 = new com.google.android.gms.internal.measurement.zzej
            r4.<init>()
            android.database.sqlite.SQLiteDatabase r14 = r19.getWritableDatabase()     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r7 = "apps"
            r6 = 6
            java.lang.String[] r8 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r6 = "day"
            r8[r3] = r6     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r6 = "daily_events_count"
            r8[r0] = r6     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r6 = "daily_public_events_count"
            r15 = 2
            r8[r15] = r6     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r6 = "daily_conversions_count"
            r13 = 3
            r8[r13] = r6     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r6 = "daily_error_events_count"
            r12 = 4
            r8[r12] = r6     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r6 = "daily_realtime_events_count"
            r11 = 5
            r8[r11] = r6     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            java.lang.String r9 = "app_id=?"
            java.lang.String[] r10 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            r10[r3] = r22     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            r16 = 0
            r17 = 0
            r18 = 0
            r6 = r14
            r5 = 5
            r11 = r16
            r5 = 4
            r12 = r17
            r5 = 3
            r13 = r18
            android.database.Cursor r6 = r6.query(r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x011c, all -> 0x0119 }
            boolean r7 = r6.moveToFirst()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            if (r7 != 0) goto L_0x0070
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r2 = "Not updating daily counts, app is not known. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r22)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.zzg(r2, r3)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            if (r6 == 0) goto L_0x006f
            r6.close()
        L_0x006f:
            return r4
        L_0x0070:
            long r7 = r6.getLong(r3)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            int r3 = (r7 > r20 ? 1 : (r7 == r20 ? 0 : -1))
            if (r3 != 0) goto L_0x0098
            long r7 = r6.getLong(r0)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r4.zzafe = r7     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            long r7 = r6.getLong(r15)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r4.zzafd = r7     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            long r7 = r6.getLong(r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r4.zzaff = r7     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 4
            long r7 = r6.getLong(r0)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r4.zzafg = r7     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 5
            long r7 = r6.getLong(r0)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r4.zzafh = r7     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
        L_0x0098:
            r7 = 1
            if (r23 == 0) goto L_0x00a2
            long r11 = r4.zzafe     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 0
            long r11 = r11 + r7
            r4.zzafe = r11     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
        L_0x00a2:
            if (r24 == 0) goto L_0x00aa
            long r11 = r4.zzafd     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 0
            long r11 = r11 + r7
            r4.zzafd = r11     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
        L_0x00aa:
            if (r25 == 0) goto L_0x00b2
            long r11 = r4.zzaff     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 0
            long r11 = r11 + r7
            r4.zzaff = r11     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
        L_0x00b2:
            if (r26 == 0) goto L_0x00ba
            long r11 = r4.zzafg     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 0
            long r11 = r11 + r7
            r4.zzafg = r11     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
        L_0x00ba:
            if (r27 == 0) goto L_0x00c2
            long r11 = r4.zzafh     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0 = 0
            long r11 = r11 + r7
            r4.zzafh = r11     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
        L_0x00c2:
            android.content.ContentValues r0 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "day"
            java.lang.Long r5 = java.lang.Long.valueOf(r20)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.put(r3, r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "daily_public_events_count"
            long r7 = r4.zzafd     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.put(r3, r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "daily_events_count"
            long r7 = r4.zzafe     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.put(r3, r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "daily_conversions_count"
            long r7 = r4.zzaff     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.put(r3, r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "daily_error_events_count"
            long r7 = r4.zzafg     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.put(r3, r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "daily_realtime_events_count"
            long r7 = r4.zzafh     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.Long r5 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            r0.put(r3, r5)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            java.lang.String r3 = "apps"
            java.lang.String r5 = "app_id=?"
            r14.update(r3, r0, r5, r2)     // Catch:{ SQLiteException -> 0x0116, all -> 0x0114 }
            if (r6 == 0) goto L_0x0113
            r6.close()
        L_0x0113:
            return r4
        L_0x0114:
            r0 = move-exception
            goto L_0x0137
        L_0x0116:
            r0 = move-exception
            r5 = r6
            goto L_0x011e
        L_0x0119:
            r0 = move-exception
            r6 = 0
            goto L_0x0137
        L_0x011c:
            r0 = move-exception
            r5 = 0
        L_0x011e:
            com.google.android.gms.internal.measurement.zzfg r2 = r19.zzge()     // Catch:{ all -> 0x0135 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x0135 }
            java.lang.String r3 = "Error updating daily counts. appId"
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzfg.zzbm(r22)     // Catch:{ all -> 0x0135 }
            r2.zze(r3, r1, r0)     // Catch:{ all -> 0x0135 }
            if (r5 == 0) goto L_0x0134
            r5.close()
        L_0x0134:
            return r4
        L_0x0135:
            r0 = move-exception
            r6 = r5
        L_0x0137:
            if (r6 == 0) goto L_0x013c
            r6.close()
        L_0x013c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zza(long, java.lang.String, boolean, boolean, boolean, boolean, boolean):com.google.android.gms.internal.measurement.zzej");
    }

    @WorkerThread
    public final void zza(zzdy zzdy) {
        Preconditions.checkNotNull(zzdy);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzdy.zzah());
        contentValues.put("app_instance_id", zzdy.getAppInstanceId());
        contentValues.put("gmp_app_id", zzdy.getGmpAppId());
        contentValues.put("resettable_device_id_hash", zzdy.zzgi());
        contentValues.put("last_bundle_index", Long.valueOf(zzdy.zzgq()));
        contentValues.put("last_bundle_start_timestamp", Long.valueOf(zzdy.zzgk()));
        contentValues.put("last_bundle_end_timestamp", Long.valueOf(zzdy.zzgl()));
        contentValues.put("app_version", zzdy.zzag());
        contentValues.put("app_store", zzdy.zzgn());
        contentValues.put("gmp_version", Long.valueOf(zzdy.zzgo()));
        contentValues.put("dev_cert_hash", Long.valueOf(zzdy.zzgp()));
        contentValues.put("measurement_enabled", Boolean.valueOf(zzdy.isMeasurementEnabled()));
        contentValues.put("day", Long.valueOf(zzdy.zzgu()));
        contentValues.put("daily_public_events_count", Long.valueOf(zzdy.zzgv()));
        contentValues.put("daily_events_count", Long.valueOf(zzdy.zzgw()));
        contentValues.put("daily_conversions_count", Long.valueOf(zzdy.zzgx()));
        contentValues.put("config_fetched_time", Long.valueOf(zzdy.zzgr()));
        contentValues.put("failed_config_fetch_time", Long.valueOf(zzdy.zzgs()));
        contentValues.put("app_version_int", Long.valueOf(zzdy.zzgm()));
        contentValues.put("firebase_instance_id", zzdy.zzgj());
        contentValues.put("daily_error_events_count", Long.valueOf(zzdy.zzgz()));
        contentValues.put("daily_realtime_events_count", Long.valueOf(zzdy.zzgy()));
        contentValues.put("health_monitor_sample", zzdy.zzha());
        contentValues.put(DeviceInfoResult.BUNDLE_KEY_ANDROID_ID, Long.valueOf(zzdy.zzhc()));
        contentValues.put("adid_reporting_enabled", Boolean.valueOf(zzdy.zzhd()));
        contentValues.put("ssaid_reporting_enabled", Boolean.valueOf(zzdy.zzhe()));
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (((long) writableDatabase.update("apps", contentValues, "app_id = ?", new String[]{zzdy.zzah()})) == 0 && writableDatabase.insertWithOnConflict("apps", (String) null, contentValues, 5) == -1) {
                zzge().zzim().zzg("Failed to insert/update app (got -1). appId", zzfg.zzbm(zzdy.zzah()));
            }
        } catch (SQLiteException e) {
            zzge().zzim().zze("Error storing app. appId", zzfg.zzbm(zzdy.zzah()), e);
        }
    }

    @WorkerThread
    public final void zza(zzeq zzeq) {
        Preconditions.checkNotNull(zzeq);
        zzab();
        zzch();
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzeq.zzti);
        contentValues.put("name", zzeq.name);
        contentValues.put("lifetime_count", Long.valueOf(zzeq.zzafr));
        contentValues.put("current_bundle_count", Long.valueOf(zzeq.zzafs));
        contentValues.put("last_fire_timestamp", Long.valueOf(zzeq.zzaft));
        contentValues.put("last_bundled_timestamp", Long.valueOf(zzeq.zzafu));
        contentValues.put("last_sampled_complex_event_id", zzeq.zzafv);
        contentValues.put("last_sampling_rate", zzeq.zzafw);
        contentValues.put("last_exempt_from_sampling", (zzeq.zzafx == null || !zzeq.zzafx.booleanValue()) ? null : 1L);
        try {
            if (getWritableDatabase().insertWithOnConflict(j.b, (String) null, contentValues, 5) == -1) {
                zzge().zzim().zzg("Failed to insert/update event aggregates (got -1). appId", zzfg.zzbm(zzeq.zzti));
            }
        } catch (SQLiteException e) {
            zzge().zzim().zze("Error storing event aggregates. appId", zzfg.zzbm(zzeq.zzti), e);
        }
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zza(String str, zzkd[] zzkdArr) {
        boolean z;
        zzfi zzip;
        String str2;
        Object zzbm;
        Integer num;
        zzch();
        zzab();
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(zzkdArr);
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            zzch();
            zzab();
            Preconditions.checkNotEmpty(str);
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            writableDatabase2.delete("property_filters", "app_id=?", new String[]{str});
            writableDatabase2.delete("event_filters", "app_id=?", new String[]{str});
            for (zzkd zzkd : zzkdArr) {
                zzch();
                zzab();
                Preconditions.checkNotEmpty(str);
                Preconditions.checkNotNull(zzkd);
                Preconditions.checkNotNull(zzkd.zzarn);
                Preconditions.checkNotNull(zzkd.zzarm);
                if (zzkd.zzarl != null) {
                    int intValue = zzkd.zzarl.intValue();
                    zzke[] zzkeArr = zzkd.zzarn;
                    int length = zzkeArr.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            zzkh[] zzkhArr = zzkd.zzarm;
                            int length2 = zzkhArr.length;
                            int i2 = 0;
                            while (i2 < length2) {
                                if (zzkhArr[i2].zzarp == null) {
                                    zzip = zzge().zzip();
                                    str2 = "Property filter with no ID. Audience definition ignored. appId, audienceId";
                                    zzbm = zzfg.zzbm(str);
                                    num = zzkd.zzarl;
                                } else {
                                    i2++;
                                }
                            }
                            zzke[] zzkeArr2 = zzkd.zzarn;
                            int length3 = zzkeArr2.length;
                            int i3 = 0;
                            while (true) {
                                if (i3 >= length3) {
                                    z = true;
                                    break;
                                } else if (!zza(str, intValue, zzkeArr2[i3])) {
                                    z = false;
                                    break;
                                } else {
                                    i3++;
                                }
                            }
                            if (z) {
                                zzkh[] zzkhArr2 = zzkd.zzarm;
                                int length4 = zzkhArr2.length;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= length4) {
                                        break;
                                    } else if (!zza(str, intValue, zzkhArr2[i4])) {
                                        z = false;
                                        break;
                                    } else {
                                        i4++;
                                    }
                                }
                            }
                            if (!z) {
                                zzch();
                                zzab();
                                Preconditions.checkNotEmpty(str);
                                SQLiteDatabase writableDatabase3 = getWritableDatabase();
                                writableDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                                writableDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{str, String.valueOf(intValue)});
                            }
                        } else if (zzkeArr[i].zzarp == null) {
                            zzip = zzge().zzip();
                            str2 = "Event filter with no ID. Audience definition ignored. appId, audienceId";
                            zzbm = zzfg.zzbm(str);
                            num = zzkd.zzarl;
                            break;
                        } else {
                            i++;
                        }
                    }
                    zzip.zze(str2, zzbm, num);
                    break;
                } else {
                    zzge().zzip().zzg("Audience with no ID. appId", zzfg.zzbm(str));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (zzkd zzkd2 : zzkdArr) {
                arrayList.add(zzkd2.zzarl);
            }
            zza(str, (List<Integer>) arrayList);
            writableDatabase.setTransactionSuccessful();
        } finally {
            writableDatabase.endTransaction();
        }
    }

    @WorkerThread
    public final boolean zza(zzed zzed) {
        Preconditions.checkNotNull(zzed);
        zzab();
        zzch();
        if (zzh(zzed.packageName, zzed.zzaep.name) == null) {
            if (zza("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{zzed.packageName}) >= 1000) {
                return false;
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzed.packageName);
        contentValues.put("origin", zzed.origin);
        contentValues.put("name", zzed.zzaep.name);
        zza(contentValues, "value", zzed.zzaep.getValue());
        contentValues.put(AppStateModule.APP_STATE_ACTIVE, Boolean.valueOf(zzed.active));
        contentValues.put("trigger_event_name", zzed.triggerEventName);
        contentValues.put("trigger_timeout", Long.valueOf(zzed.triggerTimeout));
        zzgb();
        contentValues.put("timed_out_event", zzka.zza((Parcelable) zzed.zzaeq));
        contentValues.put("creation_timestamp", Long.valueOf(zzed.creationTimestamp));
        zzgb();
        contentValues.put("triggered_event", zzka.zza((Parcelable) zzed.zzaer));
        contentValues.put("triggered_timestamp", Long.valueOf(zzed.zzaep.zzaqz));
        contentValues.put("time_to_live", Long.valueOf(zzed.timeToLive));
        zzgb();
        contentValues.put("expired_event", zzka.zza((Parcelable) zzed.zzaes));
        try {
            if (getWritableDatabase().insertWithOnConflict("conditional_properties", (String) null, contentValues, 5) == -1) {
                zzge().zzim().zzg("Failed to insert/update conditional user property (got -1)", zzfg.zzbm(zzed.packageName));
            }
        } catch (SQLiteException e) {
            zzge().zzim().zze("Error storing conditional user property", zzfg.zzbm(zzed.packageName), e);
        }
        return true;
    }

    public final boolean zza(zzep zzep, long j, boolean z) {
        zzfi zzim;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzep);
        Preconditions.checkNotEmpty(zzep.zzti);
        zzkn zzkn = new zzkn();
        zzkn.zzatc = Long.valueOf(zzep.zzafp);
        zzkn.zzata = new zzko[zzep.zzafq.size()];
        Iterator<String> it = zzep.zzafq.iterator();
        int i = 0;
        while (it.hasNext()) {
            String next = it.next();
            zzko zzko = new zzko();
            int i2 = i + 1;
            zzkn.zzata[i] = zzko;
            zzko.name = next;
            zzgb().zza(zzko, zzep.zzafq.get(next));
            i = i2;
        }
        try {
            byte[] bArr = new byte[zzkn.zzvm()];
            zzabw zzb = zzabw.zzb(bArr, 0, bArr.length);
            zzkn.zza(zzb);
            zzb.zzve();
            zzge().zzit().zze("Saving event, name, data size", zzga().zzbj(zzep.name), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzep.zzti);
            contentValues.put("name", zzep.name);
            contentValues.put("timestamp", Long.valueOf(zzep.timestamp));
            contentValues.put("metadata_fingerprint", Long.valueOf(j));
            contentValues.put("data", bArr);
            contentValues.put("realtime", Integer.valueOf(z ? 1 : 0));
            try {
                if (getWritableDatabase().insert("raw_events", (String) null, contentValues) != -1) {
                    return true;
                }
                zzge().zzim().zzg("Failed to insert raw event (got -1). appId", zzfg.zzbm(zzep.zzti));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zzim = zzge().zzim();
                str = "Error storing raw event. appId";
                zzim.zze(str, zzfg.zzbm(zzep.zzti), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zzim = zzge().zzim();
            str = "Data loss. Failed to serialize event params/data. appId";
            zzim.zze(str, zzfg.zzbm(zzep.zzti), e);
            return false;
        }
    }

    @WorkerThread
    public final boolean zza(zzjz zzjz) {
        Preconditions.checkNotNull(zzjz);
        zzab();
        zzch();
        if (zzh(zzjz.zzti, zzjz.name) == null) {
            if (zzka.zzcc(zzjz.name)) {
                if (zza("select count(1) from user_attributes where app_id=? and name not like '!_%' escape '!'", new String[]{zzjz.zzti}) >= 25) {
                    return false;
                }
            } else {
                if (zza("select count(1) from user_attributes where app_id=? and origin=? AND name like '!_%' escape '!'", new String[]{zzjz.zzti, zzjz.origin}) >= 25) {
                    return false;
                }
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_id", zzjz.zzti);
        contentValues.put("origin", zzjz.origin);
        contentValues.put("name", zzjz.name);
        contentValues.put("set_timestamp", Long.valueOf(zzjz.zzaqz));
        zza(contentValues, "value", zzjz.value);
        try {
            if (getWritableDatabase().insertWithOnConflict("user_attributes", (String) null, contentValues, 5) == -1) {
                zzge().zzim().zzg("Failed to insert/update user property (got -1). appId", zzfg.zzbm(zzjz.zzti));
            }
        } catch (SQLiteException e) {
            zzge().zzim().zze("Error storing user property. appId", zzfg.zzbm(zzjz.zzti), e);
        }
        return true;
    }

    @WorkerThread
    public final boolean zza(zzkq zzkq, boolean z) {
        zzfi zzim;
        String str;
        zzab();
        zzch();
        Preconditions.checkNotNull(zzkq);
        Preconditions.checkNotEmpty(zzkq.zzti);
        Preconditions.checkNotNull(zzkq.zzatm);
        zzhp();
        long currentTimeMillis = zzbt().currentTimeMillis();
        if (zzkq.zzatm.longValue() < currentTimeMillis - zzef.zzhh() || zzkq.zzatm.longValue() > zzef.zzhh() + currentTimeMillis) {
            zzge().zzip().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzfg.zzbm(zzkq.zzti), Long.valueOf(currentTimeMillis), zzkq.zzatm);
        }
        try {
            byte[] bArr = new byte[zzkq.zzvm()];
            zzabw zzb = zzabw.zzb(bArr, 0, bArr.length);
            zzkq.zza(zzb);
            zzb.zzve();
            byte[] zza = zzgb().zza(bArr);
            zzge().zzit().zzg("Saving bundle, size", Integer.valueOf(zza.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", zzkq.zzti);
            contentValues.put("bundle_end_timestamp", zzkq.zzatm);
            contentValues.put("data", zza);
            contentValues.put("has_realtime", Integer.valueOf(z ? 1 : 0));
            if (zzkq.zzauj != null) {
                contentValues.put("retry_count", zzkq.zzauj);
            }
            try {
                if (getWritableDatabase().insert("queue", (String) null, contentValues) != -1) {
                    return true;
                }
                zzge().zzim().zzg("Failed to insert bundle (got -1). appId", zzfg.zzbm(zzkq.zzti));
                return false;
            } catch (SQLiteException e) {
                e = e;
                zzim = zzge().zzim();
                str = "Error storing bundle. appId";
                zzim.zze(str, zzfg.zzbm(zzkq.zzti), e);
                return false;
            }
        } catch (IOException e2) {
            e = e2;
            zzim = zzge().zzim();
            str = "Data loss. Failed to serialize bundle. appId";
            zzim.zze(str, zzfg.zzbm(zzkq.zzti), e);
            return false;
        }
    }

    public final boolean zza(String str, Long l, long j, zzkn zzkn) {
        zzab();
        zzch();
        Preconditions.checkNotNull(zzkn);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(l);
        try {
            byte[] bArr = new byte[zzkn.zzvm()];
            zzabw zzb = zzabw.zzb(bArr, 0, bArr.length);
            zzkn.zza(zzb);
            zzb.zzve();
            zzge().zzit().zze("Saving complex main event, appId, data size", zzga().zzbj(str), Integer.valueOf(bArr.length));
            ContentValues contentValues = new ContentValues();
            contentValues.put("app_id", str);
            contentValues.put("event_id", l);
            contentValues.put("children_to_process", Long.valueOf(j));
            contentValues.put("main_event", bArr);
            try {
                if (getWritableDatabase().insertWithOnConflict("main_event_params", (String) null, contentValues, 5) != -1) {
                    return true;
                }
                zzge().zzim().zzg("Failed to insert complex main event (got -1). appId", zzfg.zzbm(str));
                return false;
            } catch (SQLiteException e) {
                zzge().zzim().zze("Error storing complex main event. appId", zzfg.zzbm(str), e);
                return false;
            }
        } catch (IOException e2) {
            zzge().zzim().zzd("Data loss. Failed to serialize event params/data. appId, eventId", zzfg.zzbm(str), l, e2);
            return false;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzab(long r5) {
        /*
            r4 = this;
            r4.zzab()
            r4.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r4.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r2 = "select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            r6 = 0
            r3[r6] = r5     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            android.database.Cursor r5 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x0043, all -> 0x0040 }
            boolean r1 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x003e }
            if (r1 != 0) goto L_0x0034
            com.google.android.gms.internal.measurement.zzfg r6 = r4.zzge()     // Catch:{ SQLiteException -> 0x003e }
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzit()     // Catch:{ SQLiteException -> 0x003e }
            java.lang.String r1 = "No expired configs for apps with pending events"
            r6.log(r1)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x0033
            r5.close()
        L_0x0033:
            return r0
        L_0x0034:
            java.lang.String r6 = r5.getString(r6)     // Catch:{ SQLiteException -> 0x003e }
            if (r5 == 0) goto L_0x003d
            r5.close()
        L_0x003d:
            return r6
        L_0x003e:
            r6 = move-exception
            goto L_0x0045
        L_0x0040:
            r6 = move-exception
            r5 = r0
            goto L_0x0059
        L_0x0043:
            r6 = move-exception
            r5 = r0
        L_0x0045:
            com.google.android.gms.internal.measurement.zzfg r1 = r4.zzge()     // Catch:{ all -> 0x0058 }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x0058 }
            java.lang.String r2 = "Error selecting expired configs"
            r1.zzg(r2, r6)     // Catch:{ all -> 0x0058 }
            if (r5 == 0) goto L_0x0057
            r5.close()
        L_0x0057:
            return r0
        L_0x0058:
            r6 = move-exception
        L_0x0059:
            if (r5 == 0) goto L_0x005e
            r5.close()
        L_0x005e:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzab(long):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00fb  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<android.util.Pair<com.google.android.gms.internal.measurement.zzkq, java.lang.Long>> zzb(java.lang.String r16, int r17, int r18) {
        /*
            r15 = this;
            r1 = r18
            r15.zzab()
            r15.zzch()
            r2 = 1
            r3 = 0
            if (r17 <= 0) goto L_0x000e
            r4 = 1
            goto L_0x000f
        L_0x000e:
            r4 = 0
        L_0x000f:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r4)
            if (r1 <= 0) goto L_0x0016
            r4 = 1
            goto L_0x0017
        L_0x0016:
            r4 = 0
        L_0x0017:
            com.google.android.gms.common.internal.Preconditions.checkArgument(r4)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r16)
            r4 = 0
            android.database.sqlite.SQLiteDatabase r5 = r15.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00dd }
            java.lang.String r6 = "queue"
            r7 = 3
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteException -> 0x00dd }
            java.lang.String r8 = "rowid"
            r7[r3] = r8     // Catch:{ SQLiteException -> 0x00dd }
            java.lang.String r8 = "data"
            r7[r2] = r8     // Catch:{ SQLiteException -> 0x00dd }
            java.lang.String r8 = "retry_count"
            r14 = 2
            r7[r14] = r8     // Catch:{ SQLiteException -> 0x00dd }
            java.lang.String r8 = "app_id=?"
            java.lang.String[] r9 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x00dd }
            r9[r3] = r16     // Catch:{ SQLiteException -> 0x00dd }
            r10 = 0
            r11 = 0
            java.lang.String r12 = "rowid"
            java.lang.String r13 = java.lang.String.valueOf(r17)     // Catch:{ SQLiteException -> 0x00dd }
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10, r11, r12, r13)     // Catch:{ SQLiteException -> 0x00dd }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            if (r0 != 0) goto L_0x0056
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            if (r5 == 0) goto L_0x0055
            r5.close()
        L_0x0055:
            return r0
        L_0x0056:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r4.<init>()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r6 = 0
        L_0x005c:
            long r7 = r5.getLong(r3)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            byte[] r0 = r5.getBlob(r2)     // Catch:{ IOException -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzka r9 = r15.zzgb()     // Catch:{ IOException -> 0x00b5 }
            byte[] r0 = r9.zzb((byte[]) r0)     // Catch:{ IOException -> 0x00b5 }
            boolean r9 = r4.isEmpty()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            if (r9 != 0) goto L_0x0076
            int r9 = r0.length     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            int r9 = r9 + r6
            if (r9 > r1) goto L_0x00cf
        L_0x0076:
            int r9 = r0.length     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzabv r9 = com.google.android.gms.internal.measurement.zzabv.zza(r0, r3, r9)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzkq r10 = new com.google.android.gms.internal.measurement.zzkq     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r10.<init>()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r10.zzb(r9)     // Catch:{ IOException -> 0x00a1 }
            boolean r9 = r5.isNull(r14)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            if (r9 != 0) goto L_0x0093
            int r9 = r5.getInt(r14)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r10.zzauj = r9     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
        L_0x0093:
            int r0 = r0.length     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            int r6 = r6 + r0
            java.lang.Long r0 = java.lang.Long.valueOf(r7)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            android.util.Pair r0 = android.util.Pair.create(r10, r0)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r4.add(r0)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            goto L_0x00c7
        L_0x00a1:
            r0 = move-exception
            r7 = r0
            com.google.android.gms.internal.measurement.zzfg r0 = r15.zzge()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            java.lang.String r8 = "Failed to merge queued bundle. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r16)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r0.zze(r8, r9, r7)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            goto L_0x00c7
        L_0x00b5:
            r0 = move-exception
            com.google.android.gms.internal.measurement.zzfg r7 = r15.zzge()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            com.google.android.gms.internal.measurement.zzfi r7 = r7.zzim()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            java.lang.String r8 = "Failed to unzip queued bundle. appId"
            java.lang.Object r9 = com.google.android.gms.internal.measurement.zzfg.zzbm(r16)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            r7.zze(r8, r9, r0)     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
        L_0x00c7:
            boolean r0 = r5.moveToNext()     // Catch:{ SQLiteException -> 0x00d7, all -> 0x00d5 }
            if (r0 == 0) goto L_0x00cf
            if (r6 <= r1) goto L_0x005c
        L_0x00cf:
            if (r5 == 0) goto L_0x00d4
            r5.close()
        L_0x00d4:
            return r4
        L_0x00d5:
            r0 = move-exception
            goto L_0x00f9
        L_0x00d7:
            r0 = move-exception
            r4 = r5
            goto L_0x00de
        L_0x00da:
            r0 = move-exception
            r5 = r4
            goto L_0x00f9
        L_0x00dd:
            r0 = move-exception
        L_0x00de:
            com.google.android.gms.internal.measurement.zzfg r1 = r15.zzge()     // Catch:{ all -> 0x00da }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x00da }
            java.lang.String r2 = "Error querying bundles. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r16)     // Catch:{ all -> 0x00da }
            r1.zze(r2, r3, r0)     // Catch:{ all -> 0x00da }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x00da }
            if (r4 == 0) goto L_0x00f8
            r4.close()
        L_0x00f8:
            return r0
        L_0x00f9:
            if (r5 == 0) goto L_0x00fe
            r5.close()
        L_0x00fe:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzb(java.lang.String, int, int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0122, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0123, code lost:
        r14 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0126, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0127, code lost:
        r14 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x012a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x012b, code lost:
        r14 = r21;
        r11 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x012f, code lost:
        r5 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x014d, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0126 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000f] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x014d  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzjz> zzb(java.lang.String r22, java.lang.String r23, java.lang.String r24) {
        /*
            r21 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r22)
            r21.zzab()
            r21.zzch()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            r3 = 3
            r2.<init>(r3)     // Catch:{ SQLiteException -> 0x012a, all -> 0x0126 }
            r11 = r22
            r2.add(r11)     // Catch:{ SQLiteException -> 0x0122, all -> 0x0126 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x0122, all -> 0x0126 }
            java.lang.String r5 = "app_id=?"
            r4.<init>(r5)     // Catch:{ SQLiteException -> 0x0122, all -> 0x0126 }
            boolean r5 = android.text.TextUtils.isEmpty(r23)     // Catch:{ SQLiteException -> 0x0122, all -> 0x0126 }
            if (r5 != 0) goto L_0x0037
            r5 = r23
            r2.add(r5)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r6 = " and origin=?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            goto L_0x0039
        L_0x0032:
            r0 = move-exception
            r14 = r21
            goto L_0x0131
        L_0x0037:
            r5 = r23
        L_0x0039:
            boolean r6 = android.text.TextUtils.isEmpty(r24)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            if (r6 != 0) goto L_0x0051
            java.lang.String r6 = java.lang.String.valueOf(r24)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r7 = "*"
            java.lang.String r6 = r6.concat(r7)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            r2.add(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r6 = " and name glob ?"
            r4.append(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
        L_0x0051:
            int r6 = r2.size()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String[] r6 = new java.lang.String[r6]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.Object[] r2 = r2.toArray(r6)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            r16 = r2
            java.lang.String[] r16 = (java.lang.String[]) r16     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            android.database.sqlite.SQLiteDatabase r12 = r21.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r13 = "user_attributes"
            r2 = 4
            java.lang.String[] r14 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r2 = "name"
            r10 = 0
            r14[r10] = r2     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r2 = "set_timestamp"
            r8 = 1
            r14[r8] = r2     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r2 = "value"
            r9 = 2
            r14[r9] = r2     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r2 = "origin"
            r14[r3] = r2     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            java.lang.String r15 = r4.toString()     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            r17 = 0
            r18 = 0
            java.lang.String r19 = "rowid"
            java.lang.String r20 = "1001"
            android.database.Cursor r2 = r12.query(r13, r14, r15, r16, r17, r18, r19, r20)     // Catch:{ SQLiteException -> 0x0032, all -> 0x0126 }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            if (r4 != 0) goto L_0x009a
            if (r2 == 0) goto L_0x0099
            r2.close()
        L_0x0099:
            return r0
        L_0x009a:
            int r4 = r0.size()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r4 < r6) goto L_0x00b6
            com.google.android.gms.internal.measurement.zzfg r3 = r21.zzge()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            java.lang.String r4 = "Read more than the max allowed user properties, ignoring excess"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r3.zzg(r4, r6)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r14 = r21
            goto L_0x0103
        L_0x00b6:
            java.lang.String r7 = r2.getString(r10)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            long r12 = r2.getLong(r8)     // Catch:{ SQLiteException -> 0x011e, all -> 0x011a }
            r14 = r21
            java.lang.Object r15 = r14.zza((android.database.Cursor) r2, (int) r9)     // Catch:{ SQLiteException -> 0x0118 }
            java.lang.String r6 = r2.getString(r3)     // Catch:{ SQLiteException -> 0x0118 }
            if (r15 != 0) goto L_0x00e8
            com.google.android.gms.internal.measurement.zzfg r4 = r21.zzge()     // Catch:{ SQLiteException -> 0x00e5 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ SQLiteException -> 0x00e5 }
            java.lang.String r5 = "(2)Read invalid user property value, ignoring it"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r22)     // Catch:{ SQLiteException -> 0x00e5 }
            r12 = r24
            r4.zzd(r5, r7, r6, r12)     // Catch:{ SQLiteException -> 0x00e5 }
            r16 = r6
            r12 = 0
            r17 = 1
            r18 = 2
            goto L_0x00fd
        L_0x00e5:
            r0 = move-exception
            r5 = r6
            goto L_0x0132
        L_0x00e8:
            com.google.android.gms.internal.measurement.zzjz r5 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ SQLiteException -> 0x0112 }
            r4 = r5
            r3 = r5
            r5 = r22
            r16 = r6
            r17 = 1
            r18 = 2
            r8 = r12
            r12 = 0
            r10 = r15
            r4.<init>(r5, r6, r7, r8, r10)     // Catch:{ SQLiteException -> 0x0110 }
            r0.add(r3)     // Catch:{ SQLiteException -> 0x0110 }
        L_0x00fd:
            boolean r3 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0110 }
            if (r3 != 0) goto L_0x0109
        L_0x0103:
            if (r2 == 0) goto L_0x0108
            r2.close()
        L_0x0108:
            return r0
        L_0x0109:
            r5 = r16
            r3 = 3
            r8 = 1
            r9 = 2
            r10 = 0
            goto L_0x009a
        L_0x0110:
            r0 = move-exception
            goto L_0x0115
        L_0x0112:
            r0 = move-exception
            r16 = r6
        L_0x0115:
            r5 = r16
            goto L_0x0132
        L_0x0118:
            r0 = move-exception
            goto L_0x0132
        L_0x011a:
            r0 = move-exception
            r14 = r21
            goto L_0x014a
        L_0x011e:
            r0 = move-exception
            r14 = r21
            goto L_0x0132
        L_0x0122:
            r0 = move-exception
            r14 = r21
            goto L_0x012f
        L_0x0126:
            r0 = move-exception
            r14 = r21
            goto L_0x014b
        L_0x012a:
            r0 = move-exception
            r14 = r21
            r11 = r22
        L_0x012f:
            r5 = r23
        L_0x0131:
            r2 = r1
        L_0x0132:
            com.google.android.gms.internal.measurement.zzfg r3 = r21.zzge()     // Catch:{ all -> 0x0149 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ all -> 0x0149 }
            java.lang.String r4 = "(2)Error querying user properties"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r22)     // Catch:{ all -> 0x0149 }
            r3.zzd(r4, r6, r5, r0)     // Catch:{ all -> 0x0149 }
            if (r2 == 0) goto L_0x0148
            r2.close()
        L_0x0148:
            return r1
        L_0x0149:
            r0 = move-exception
        L_0x014a:
            r1 = r2
        L_0x014b:
            if (r1 == 0) goto L_0x0150
            r1.close()
        L_0x0150:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzb(java.lang.String, java.lang.String, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x017a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzed> zzb(java.lang.String r40, java.lang.String[] r41) {
        /*
            r39 = this;
            r39.zzab()
            r39.zzch()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r2 = r39.getWritableDatabase()     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r3 = "conditional_properties"
            r4 = 13
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "app_id"
            r11 = 0
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "origin"
            r12 = 1
            r4[r12] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "name"
            r13 = 2
            r4[r13] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "value"
            r14 = 3
            r4[r14] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "active"
            r15 = 4
            r4[r15] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "trigger_event_name"
            r10 = 5
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "trigger_timeout"
            r9 = 6
            r4[r9] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "timed_out_event"
            r8 = 7
            r4[r8] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "creation_timestamp"
            r7 = 8
            r4[r7] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "triggered_event"
            r6 = 9
            r4[r6] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "triggered_timestamp"
            r1 = 10
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "time_to_live"
            r1 = 11
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            java.lang.String r5 = "expired_event"
            r1 = 12
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            r19 = 0
            r20 = 0
            java.lang.String r21 = "rowid"
            java.lang.String r22 = "1001"
            r5 = r40
            r1 = 9
            r6 = r41
            r1 = 8
            r7 = r19
            r1 = 7
            r8 = r20
            r1 = 6
            r9 = r21
            r1 = 5
            r10 = r22
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x015d, all -> 0x015a }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            if (r3 != 0) goto L_0x008d
            if (r2 == 0) goto L_0x008c
            r2.close()
        L_0x008c:
            return r0
        L_0x008d:
            int r3 = r0.size()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r3 < r4) goto L_0x00a8
            com.google.android.gms.internal.measurement.zzfg r1 = r39.zzge()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            java.lang.String r3 = "Read more than the max allowed conditional properties, ignoring extra"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r1.zzg(r3, r4)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            goto L_0x014b
        L_0x00a8:
            java.lang.String r3 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            java.lang.String r10 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            java.lang.String r5 = r2.getString(r13)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r9 = r39
            java.lang.Object r8 = r9.zza((android.database.Cursor) r2, (int) r14)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            int r4 = r2.getInt(r15)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            if (r4 == 0) goto L_0x00c3
            r22 = 1
            goto L_0x00c5
        L_0x00c3:
            r22 = 0
        L_0x00c5:
            java.lang.String r28 = r2.getString(r1)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r6 = 6
            long r29 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzka r4 = r39.zzgb()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r7 = 7
            byte[] r1 = r2.getBlob(r7)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r6 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            android.os.Parcelable r1 = r4.zza((byte[]) r1, r6)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzeu r1 = (com.google.android.gms.internal.measurement.zzeu) r1     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r6 = 8
            long r20 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzka r4 = r39.zzgb()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r11 = 9
            byte[] r6 = r2.getBlob(r11)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r7 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            android.os.Parcelable r4 = r4.zza((byte[]) r6, r7)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r27 = r4
            com.google.android.gms.internal.measurement.zzeu r27 = (com.google.android.gms.internal.measurement.zzeu) r27     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r6 = 10
            long r16 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r7 = 11
            long r34 = r2.getLong(r7)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzka r4 = r39.zzgb()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r11 = 12
            byte[] r6 = r2.getBlob(r11)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r7 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            android.os.Parcelable r4 = r4.zza((byte[]) r6, r7)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r38 = r4
            com.google.android.gms.internal.measurement.zzeu r38 = (com.google.android.gms.internal.measurement.zzeu) r38     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzjx r19 = new com.google.android.gms.internal.measurement.zzjx     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r4 = r19
            r31 = 6
            r32 = 7
            r33 = 8
            r36 = 10
            r37 = 11
            r6 = r16
            r9 = r10
            r4.<init>(r5, r6, r8, r9)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            com.google.android.gms.internal.measurement.zzed r4 = new com.google.android.gms.internal.measurement.zzed     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r16 = r4
            r17 = r3
            r18 = r10
            r23 = r28
            r24 = r1
            r25 = r29
            r28 = r34
            r30 = r38
            r16.<init>(r17, r18, r19, r20, r22, r23, r24, r25, r27, r28, r30)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            r0.add(r4)     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            boolean r1 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x0157, all -> 0x0155 }
            if (r1 != 0) goto L_0x0151
        L_0x014b:
            if (r2 == 0) goto L_0x0150
            r2.close()
        L_0x0150:
            return r0
        L_0x0151:
            r1 = 5
            r11 = 0
            goto L_0x008d
        L_0x0155:
            r0 = move-exception
            goto L_0x0178
        L_0x0157:
            r0 = move-exception
            r1 = r2
            goto L_0x015f
        L_0x015a:
            r0 = move-exception
            r2 = 0
            goto L_0x0178
        L_0x015d:
            r0 = move-exception
            r1 = 0
        L_0x015f:
            com.google.android.gms.internal.measurement.zzfg r2 = r39.zzge()     // Catch:{ all -> 0x0176 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x0176 }
            java.lang.String r3 = "Error querying conditional user property value"
            r2.zzg(r3, r0)     // Catch:{ all -> 0x0176 }
            java.util.List r0 = java.util.Collections.emptyList()     // Catch:{ all -> 0x0176 }
            if (r1 == 0) goto L_0x0175
            r1.close()
        L_0x0175:
            return r0
        L_0x0176:
            r0 = move-exception
            r2 = r1
        L_0x0178:
            if (r2 == 0) goto L_0x017d
            r2.close()
        L_0x017d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzb(java.lang.String, java.lang.String[]):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00bb  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.internal.measurement.zzjz> zzbb(java.lang.String r23) {
        /*
            r22 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            r22.zzab()
            r22.zzch()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r22.getWritableDatabase()     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            java.lang.String r3 = "user_attributes"
            r4 = 4
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            java.lang.String r5 = "name"
            r11 = 0
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            java.lang.String r5 = "origin"
            r12 = 1
            r4[r12] = r5     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            java.lang.String r5 = "set_timestamp"
            r13 = 2
            r4[r13] = r5     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            java.lang.String r5 = "value"
            r14 = 3
            r4[r14] = r5     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            r6[r11] = r23     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            r7 = 0
            r8 = 0
            java.lang.String r9 = "rowid"
            java.lang.String r10 = "1000"
            android.database.Cursor r2 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x009d, all -> 0x0098 }
            boolean r3 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0094, all -> 0x0090 }
            if (r3 != 0) goto L_0x004b
            if (r2 == 0) goto L_0x004a
            r2.close()
        L_0x004a:
            return r0
        L_0x004b:
            java.lang.String r18 = r2.getString(r11)     // Catch:{ SQLiteException -> 0x0094, all -> 0x0090 }
            java.lang.String r3 = r2.getString(r12)     // Catch:{ SQLiteException -> 0x0094, all -> 0x0090 }
            if (r3 != 0) goto L_0x0057
            java.lang.String r3 = ""
        L_0x0057:
            r17 = r3
            long r19 = r2.getLong(r13)     // Catch:{ SQLiteException -> 0x0094, all -> 0x0090 }
            r3 = r22
            java.lang.Object r21 = r3.zza((android.database.Cursor) r2, (int) r14)     // Catch:{ SQLiteException -> 0x008e }
            if (r21 != 0) goto L_0x0077
            com.google.android.gms.internal.measurement.zzfg r4 = r22.zzge()     // Catch:{ SQLiteException -> 0x008e }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ SQLiteException -> 0x008e }
            java.lang.String r5 = "Read invalid user property value, ignoring it. appId"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r23)     // Catch:{ SQLiteException -> 0x008e }
            r4.zzg(r5, r6)     // Catch:{ SQLiteException -> 0x008e }
            goto L_0x0082
        L_0x0077:
            com.google.android.gms.internal.measurement.zzjz r4 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ SQLiteException -> 0x008e }
            r15 = r4
            r16 = r23
            r15.<init>(r16, r17, r18, r19, r21)     // Catch:{ SQLiteException -> 0x008e }
            r0.add(r4)     // Catch:{ SQLiteException -> 0x008e }
        L_0x0082:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x008e }
            if (r4 != 0) goto L_0x004b
            if (r2 == 0) goto L_0x008d
            r2.close()
        L_0x008d:
            return r0
        L_0x008e:
            r0 = move-exception
            goto L_0x00a1
        L_0x0090:
            r0 = move-exception
            r3 = r22
            goto L_0x00b9
        L_0x0094:
            r0 = move-exception
            r3 = r22
            goto L_0x00a1
        L_0x0098:
            r0 = move-exception
            r3 = r22
            r2 = r1
            goto L_0x00b9
        L_0x009d:
            r0 = move-exception
            r3 = r22
            r2 = r1
        L_0x00a1:
            com.google.android.gms.internal.measurement.zzfg r4 = r22.zzge()     // Catch:{ all -> 0x00b8 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x00b8 }
            java.lang.String r5 = "Error querying user properties. appId"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r23)     // Catch:{ all -> 0x00b8 }
            r4.zze(r5, r6, r0)     // Catch:{ all -> 0x00b8 }
            if (r2 == 0) goto L_0x00b7
            r2.close()
        L_0x00b7:
            return r1
        L_0x00b8:
            r0 = move-exception
        L_0x00b9:
            if (r2 == 0) goto L_0x00be
            r2.close()
        L_0x00be:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzbb(java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x017b A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x017f A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x01b3 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x01b6 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x01c5 A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x01ed A[Catch:{ SQLiteException -> 0x0204 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x022a  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0231  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzdy zzbc(java.lang.String r20) {
        /*
            r19 = this;
            r1 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r19.zzab()
            r19.zzch()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r19.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r4 = "apps"
            r0 = 25
            java.lang.String[] r5 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "app_instance_id"
            r11 = 0
            r5[r11] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "gmp_app_id"
            r12 = 1
            r5[r12] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "resettable_device_id_hash"
            r13 = 2
            r5[r13] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "last_bundle_index"
            r14 = 3
            r5[r14] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "last_bundle_start_timestamp"
            r15 = 4
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "last_bundle_end_timestamp"
            r10 = 5
            r5[r10] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "app_version"
            r9 = 6
            r5[r9] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "app_store"
            r8 = 7
            r5[r8] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "gmp_version"
            r7 = 8
            r5[r7] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 9
            java.lang.String r6 = "dev_cert_hash"
            r5[r0] = r6     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "measurement_enabled"
            r6 = 10
            r5[r6] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 11
            java.lang.String r16 = "day"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 12
            java.lang.String r16 = "daily_public_events_count"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 13
            java.lang.String r16 = "daily_events_count"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 14
            java.lang.String r16 = "daily_conversions_count"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 15
            java.lang.String r16 = "config_fetched_time"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 16
            java.lang.String r16 = "failed_config_fetch_time"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "app_version_int"
            r15 = 17
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 18
            java.lang.String r16 = "firebase_instance_id"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 19
            java.lang.String r16 = "daily_error_events_count"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 20
            java.lang.String r16 = "daily_realtime_events_count"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r0 = 21
            java.lang.String r16 = "health_monitor_sample"
            r5[r0] = r16     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "android_id"
            r15 = 22
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "adid_reporting_enabled"
            r15 = 23
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "ssaid_reporting_enabled"
            r15 = 24
            r5[r15] = r0     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            java.lang.String r0 = "app_id=?"
            java.lang.String[] r7 = new java.lang.String[r12]     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r7[r11] = r1     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            r16 = 0
            r17 = 0
            r18 = 0
            r15 = 10
            r6 = r0
            r0 = 8
            r15 = 7
            r8 = r16
            r0 = 6
            r9 = r17
            r15 = 5
            r10 = r18
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0213, all -> 0x020e }
            boolean r4 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x020a, all -> 0x0206 }
            if (r4 != 0) goto L_0x00cf
            if (r3 == 0) goto L_0x00ce
            r3.close()
        L_0x00ce:
            return r2
        L_0x00cf:
            com.google.android.gms.internal.measurement.zzdy r4 = new com.google.android.gms.internal.measurement.zzdy     // Catch:{ SQLiteException -> 0x020a, all -> 0x0206 }
            r5 = r19
            com.google.android.gms.internal.measurement.zzjr r6 = r5.zzajp     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.internal.measurement.zzgl r6 = r6.zzla()     // Catch:{ SQLiteException -> 0x0204 }
            r4.<init>(r6, r1)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = r3.getString(r11)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzal(r6)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = r3.getString(r12)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzam(r6)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = r3.getString(r13)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzan(r6)     // Catch:{ SQLiteException -> 0x0204 }
            long r6 = r3.getLong(r14)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzr(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r6 = 4
            long r6 = r3.getLong(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzm(r6)     // Catch:{ SQLiteException -> 0x0204 }
            long r6 = r3.getLong(r15)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzn(r6)     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.setAppVersion(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 7
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzap(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 8
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzp(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 9
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzq(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 10
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 != 0) goto L_0x0139
            int r0 = r3.getInt(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x0137
            goto L_0x0139
        L_0x0137:
            r0 = 0
            goto L_0x013a
        L_0x0139:
            r0 = 1
        L_0x013a:
            r4.setMeasurementEnabled(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 11
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzu(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 12
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzv(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 13
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzw(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 14
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzx(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 15
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzs(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 16
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzt(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 17
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 == 0) goto L_0x017f
            r6 = -2147483648(0xffffffff80000000, double:NaN)
            goto L_0x0184
        L_0x017f:
            int r0 = r3.getInt(r0)     // Catch:{ SQLiteException -> 0x0204 }
            long r6 = (long) r0     // Catch:{ SQLiteException -> 0x0204 }
        L_0x0184:
            r4.zzo(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 18
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzao(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 19
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzz(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 20
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzy(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 21
            java.lang.String r0 = r3.getString(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzaq(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 22
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 == 0) goto L_0x01b6
            r6 = 0
            goto L_0x01ba
        L_0x01b6:
            long r6 = r3.getLong(r0)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x01ba:
            r4.zzaa(r6)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 23
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 != 0) goto L_0x01ce
            int r0 = r3.getInt(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x01cc
            goto L_0x01ce
        L_0x01cc:
            r0 = 0
            goto L_0x01cf
        L_0x01ce:
            r0 = 1
        L_0x01cf:
            r4.zzd(r0)     // Catch:{ SQLiteException -> 0x0204 }
            r0 = 24
            boolean r6 = r3.isNull(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r6 != 0) goto L_0x01e0
            int r0 = r3.getInt(r0)     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x01e1
        L_0x01e0:
            r11 = 1
        L_0x01e1:
            r4.zze(r11)     // Catch:{ SQLiteException -> 0x0204 }
            r4.zzgh()     // Catch:{ SQLiteException -> 0x0204 }
            boolean r0 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0204 }
            if (r0 == 0) goto L_0x01fe
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ SQLiteException -> 0x0204 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteException -> 0x0204 }
            java.lang.String r6 = "Got multiple records for app, expected one. appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r20)     // Catch:{ SQLiteException -> 0x0204 }
            r0.zzg(r6, r7)     // Catch:{ SQLiteException -> 0x0204 }
        L_0x01fe:
            if (r3 == 0) goto L_0x0203
            r3.close()
        L_0x0203:
            return r4
        L_0x0204:
            r0 = move-exception
            goto L_0x0217
        L_0x0206:
            r0 = move-exception
            r5 = r19
            goto L_0x022f
        L_0x020a:
            r0 = move-exception
            r5 = r19
            goto L_0x0217
        L_0x020e:
            r0 = move-exception
            r5 = r19
            r3 = r2
            goto L_0x022f
        L_0x0213:
            r0 = move-exception
            r5 = r19
            r3 = r2
        L_0x0217:
            com.google.android.gms.internal.measurement.zzfg r4 = r19.zzge()     // Catch:{ all -> 0x022e }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x022e }
            java.lang.String r6 = "Error querying app. appId"
            java.lang.Object r1 = com.google.android.gms.internal.measurement.zzfg.zzbm(r20)     // Catch:{ all -> 0x022e }
            r4.zze(r6, r1, r0)     // Catch:{ all -> 0x022e }
            if (r3 == 0) goto L_0x022d
            r3.close()
        L_0x022d:
            return r2
        L_0x022e:
            r0 = move-exception
        L_0x022f:
            if (r3 == 0) goto L_0x0234
            r3.close()
        L_0x0234:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzbc(java.lang.String):com.google.android.gms.internal.measurement.zzdy");
    }

    public final long zzbd(String str) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        try {
            return (long) getWritableDatabase().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{str, String.valueOf(Math.max(0, Math.min(1000000, zzgg().zzb(str, zzew.zzagx))))});
        } catch (SQLiteException e) {
            zzge().zzim().zze("Error deleting over the limit events. appId", zzfg.zzbm(str), e);
            return 0;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0079  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] zzbe(java.lang.String r12) {
        /*
            r11 = this;
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            r11.zzab()
            r11.zzch()
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.getWritableDatabase()     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r2 = "apps"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r5 = "remote_config"
            r9 = 0
            r4[r9] = r5     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            java.lang.String r5 = "app_id=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            r6[r9] = r12     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            r7 = 0
            r8 = 0
            r10 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r8
            r8 = r10
            android.database.Cursor r1 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005d, all -> 0x005a }
            boolean r2 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0058 }
            if (r2 != 0) goto L_0x0037
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r0
        L_0x0037:
            byte[] r2 = r1.getBlob(r9)     // Catch:{ SQLiteException -> 0x0058 }
            boolean r3 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0058 }
            if (r3 == 0) goto L_0x0052
            com.google.android.gms.internal.measurement.zzfg r3 = r11.zzge()     // Catch:{ SQLiteException -> 0x0058 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ SQLiteException -> 0x0058 }
            java.lang.String r4 = "Got multiple records for app config, expected one. appId"
            java.lang.Object r5 = com.google.android.gms.internal.measurement.zzfg.zzbm(r12)     // Catch:{ SQLiteException -> 0x0058 }
            r3.zzg(r4, r5)     // Catch:{ SQLiteException -> 0x0058 }
        L_0x0052:
            if (r1 == 0) goto L_0x0057
            r1.close()
        L_0x0057:
            return r2
        L_0x0058:
            r2 = move-exception
            goto L_0x005f
        L_0x005a:
            r12 = move-exception
            r1 = r0
            goto L_0x0077
        L_0x005d:
            r2 = move-exception
            r1 = r0
        L_0x005f:
            com.google.android.gms.internal.measurement.zzfg r3 = r11.zzge()     // Catch:{ all -> 0x0076 }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ all -> 0x0076 }
            java.lang.String r4 = "Error querying remote config. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfg.zzbm(r12)     // Catch:{ all -> 0x0076 }
            r3.zze(r4, r12, r2)     // Catch:{ all -> 0x0076 }
            if (r1 == 0) goto L_0x0075
            r1.close()
        L_0x0075:
            return r0
        L_0x0076:
            r12 = move-exception
        L_0x0077:
            if (r1 == 0) goto L_0x007c
            r1.close()
        L_0x007c:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzbe(java.lang.String):byte[]");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, com.google.android.gms.internal.measurement.zzkr> zzbf(java.lang.String r12) {
        /*
            r11 = this;
            r11.zzch()
            r11.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r12)
            android.database.sqlite.SQLiteDatabase r0 = r11.getWritableDatabase()
            r8 = 0
            java.lang.String r1 = "audience_filter_values"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            java.lang.String r3 = "audience_id"
            r9 = 0
            r2[r9] = r3     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            java.lang.String r3 = "current_results"
            r10 = 1
            r2[r10] = r3     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            java.lang.String r3 = "app_id=?"
            java.lang.String[] r4 = new java.lang.String[r10]     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            r4[r9] = r12     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x007f, all -> 0x007c }
            boolean r1 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x007a }
            if (r1 != 0) goto L_0x0036
            if (r0 == 0) goto L_0x0035
            r0.close()
        L_0x0035:
            return r8
        L_0x0036:
            android.support.v4.util.ArrayMap r1 = new android.support.v4.util.ArrayMap     // Catch:{ SQLiteException -> 0x007a }
            r1.<init>()     // Catch:{ SQLiteException -> 0x007a }
        L_0x003b:
            int r2 = r0.getInt(r9)     // Catch:{ SQLiteException -> 0x007a }
            byte[] r3 = r0.getBlob(r10)     // Catch:{ SQLiteException -> 0x007a }
            int r4 = r3.length     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.zzabv r3 = com.google.android.gms.internal.measurement.zzabv.zza(r3, r9, r4)     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.zzkr r4 = new com.google.android.gms.internal.measurement.zzkr     // Catch:{ SQLiteException -> 0x007a }
            r4.<init>()     // Catch:{ SQLiteException -> 0x007a }
            r4.zzb(r3)     // Catch:{ IOException -> 0x0058 }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r1.put(r2, r4)     // Catch:{ SQLiteException -> 0x007a }
            goto L_0x006e
        L_0x0058:
            r3 = move-exception
            com.google.android.gms.internal.measurement.zzfg r4 = r11.zzge()     // Catch:{ SQLiteException -> 0x007a }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ SQLiteException -> 0x007a }
            java.lang.String r5 = "Failed to merge filter results. appId, audienceId, error"
            java.lang.Object r6 = com.google.android.gms.internal.measurement.zzfg.zzbm(r12)     // Catch:{ SQLiteException -> 0x007a }
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)     // Catch:{ SQLiteException -> 0x007a }
            r4.zzd(r5, r6, r2, r3)     // Catch:{ SQLiteException -> 0x007a }
        L_0x006e:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteException -> 0x007a }
            if (r2 != 0) goto L_0x003b
            if (r0 == 0) goto L_0x0079
            r0.close()
        L_0x0079:
            return r1
        L_0x007a:
            r1 = move-exception
            goto L_0x0081
        L_0x007c:
            r12 = move-exception
            r0 = r8
            goto L_0x0099
        L_0x007f:
            r1 = move-exception
            r0 = r8
        L_0x0081:
            com.google.android.gms.internal.measurement.zzfg r2 = r11.zzge()     // Catch:{ all -> 0x0098 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x0098 }
            java.lang.String r3 = "Database error querying filter results. appId"
            java.lang.Object r12 = com.google.android.gms.internal.measurement.zzfg.zzbm(r12)     // Catch:{ all -> 0x0098 }
            r2.zze(r3, r12, r1)     // Catch:{ all -> 0x0098 }
            if (r0 == 0) goto L_0x0097
            r0.close()
        L_0x0097:
            return r8
        L_0x0098:
            r12 = move-exception
        L_0x0099:
            if (r0 == 0) goto L_0x009e
            r0.close()
        L_0x009e:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzbf(java.lang.String):java.util.Map");
    }

    public final long zzbg(String str) {
        Preconditions.checkNotEmpty(str);
        return zza("select count(1) from events where app_id=? and name not like '!_%' escape '!'", new String[]{str}, 0);
    }

    @WorkerThread
    public final List<zzed> zzc(String str, String str2, String str3) {
        Preconditions.checkNotEmpty(str);
        zzab();
        zzch();
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(str);
        StringBuilder sb = new StringBuilder("app_id=?");
        if (!TextUtils.isEmpty(str2)) {
            arrayList.add(str2);
            sb.append(" and origin=?");
        }
        if (!TextUtils.isEmpty(str3)) {
            arrayList.add(String.valueOf(str3).concat("*"));
            sb.append(" and name glob ?");
        }
        return zzb(sb.toString(), (String[]) arrayList.toArray(new String[arrayList.size()]));
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    @VisibleForTesting
    public final void zzc(List<Long> list) {
        zzab();
        zzch();
        Preconditions.checkNotNull(list);
        Preconditions.checkNotZero(list.size());
        if (zzhv()) {
            String join = TextUtils.join(",", list);
            StringBuilder sb = new StringBuilder(String.valueOf(join).length() + 2);
            sb.append(Operators.BRACKET_START_STR);
            sb.append(join);
            sb.append(Operators.BRACKET_END_STR);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder(String.valueOf(sb2).length() + 80);
            sb3.append("SELECT COUNT(1) FROM queue WHERE rowid IN ");
            sb3.append(sb2);
            sb3.append(" AND retry_count =  2147483647 LIMIT 1");
            if (zza(sb3.toString(), (String[]) null) > 0) {
                zzge().zzip().log("The number of upload retries exceeds the limit. Will remain unchanged.");
            }
            try {
                SQLiteDatabase writableDatabase = getWritableDatabase();
                StringBuilder sb4 = new StringBuilder(String.valueOf(sb2).length() + 127);
                sb4.append("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN ");
                sb4.append(sb2);
                sb4.append(" AND (retry_count IS NULL OR retry_count < 2147483647)");
                writableDatabase.execSQL(sb4.toString());
            } catch (SQLiteException e) {
                zzge().zzim().zzg("Error incrementing retry count. error", e);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0112  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzeq zzf(java.lang.String r23, java.lang.String r24) {
        /*
            r22 = this;
            r15 = r24
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r23)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r24)
            r22.zzab()
            r22.zzch()
            r16 = 0
            android.database.sqlite.SQLiteDatabase r1 = r22.getWritableDatabase()     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r2 = "events"
            r0 = 7
            java.lang.String[] r3 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "lifetime_count"
            r9 = 0
            r3[r9] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "current_bundle_count"
            r10 = 1
            r3[r10] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "last_fire_timestamp"
            r11 = 2
            r3[r11] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "last_bundled_timestamp"
            r12 = 3
            r3[r12] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "last_sampled_complex_event_id"
            r13 = 4
            r3[r13] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "last_sampling_rate"
            r14 = 5
            r3[r14] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r0 = "last_exempt_from_sampling"
            r8 = 6
            r3[r8] = r0     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            java.lang.String r4 = "app_id=? and name=?"
            java.lang.String[] r5 = new java.lang.String[r11]     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            r5[r9] = r23     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            r5[r10] = r15     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            r6 = 0
            r7 = 0
            r0 = 0
            r8 = r0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00ed, all -> 0x00e9 }
            boolean r0 = r8.moveToFirst()     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            if (r0 != 0) goto L_0x0058
            if (r8 == 0) goto L_0x0057
            r8.close()
        L_0x0057:
            return r16
        L_0x0058:
            long r4 = r8.getLong(r9)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            long r6 = r8.getLong(r10)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            long r18 = r8.getLong(r11)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            boolean r0 = r8.isNull(r12)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            if (r0 == 0) goto L_0x006e
            r0 = 0
        L_0x006c:
            r11 = r0
            goto L_0x0073
        L_0x006e:
            long r0 = r8.getLong(r12)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            goto L_0x006c
        L_0x0073:
            boolean r0 = r8.isNull(r13)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            if (r0 == 0) goto L_0x007c
            r0 = r16
            goto L_0x0084
        L_0x007c:
            long r0 = r8.getLong(r13)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
        L_0x0084:
            boolean r1 = r8.isNull(r14)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            if (r1 == 0) goto L_0x008e
            r13 = r16
        L_0x008c:
            r1 = 6
            goto L_0x0098
        L_0x008e:
            long r1 = r8.getLong(r14)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            r13 = r1
            goto L_0x008c
        L_0x0098:
            boolean r2 = r8.isNull(r1)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            if (r2 != 0) goto L_0x00af
            long r1 = r8.getLong(r1)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            r20 = 1
            int r3 = (r1 > r20 ? 1 : (r1 == r20 ? 0 : -1))
            if (r3 != 0) goto L_0x00a9
            r9 = 1
        L_0x00a9:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r9)     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            r14 = r1
            goto L_0x00b1
        L_0x00af:
            r14 = r16
        L_0x00b1:
            com.google.android.gms.internal.measurement.zzeq r17 = new com.google.android.gms.internal.measurement.zzeq     // Catch:{ SQLiteException -> 0x00e5, all -> 0x00e1 }
            r1 = r17
            r2 = r23
            r3 = r24
            r20 = r8
            r8 = r18
            r10 = r11
            r12 = r0
            r1.<init>(r2, r3, r4, r6, r8, r10, r12, r13, r14)     // Catch:{ SQLiteException -> 0x00df }
            boolean r0 = r20.moveToNext()     // Catch:{ SQLiteException -> 0x00df }
            if (r0 == 0) goto L_0x00d9
            com.google.android.gms.internal.measurement.zzfg r0 = r22.zzge()     // Catch:{ SQLiteException -> 0x00df }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteException -> 0x00df }
            java.lang.String r1 = "Got multiple records for event aggregates, expected one. appId"
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzfg.zzbm(r23)     // Catch:{ SQLiteException -> 0x00df }
            r0.zzg(r1, r2)     // Catch:{ SQLiteException -> 0x00df }
        L_0x00d9:
            if (r20 == 0) goto L_0x00de
            r20.close()
        L_0x00de:
            return r17
        L_0x00df:
            r0 = move-exception
            goto L_0x00f0
        L_0x00e1:
            r0 = move-exception
            r20 = r8
            goto L_0x0110
        L_0x00e5:
            r0 = move-exception
            r20 = r8
            goto L_0x00f0
        L_0x00e9:
            r0 = move-exception
            r20 = r16
            goto L_0x0110
        L_0x00ed:
            r0 = move-exception
            r20 = r16
        L_0x00f0:
            com.google.android.gms.internal.measurement.zzfg r1 = r22.zzge()     // Catch:{ all -> 0x010f }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x010f }
            java.lang.String r2 = "Error querying events. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r23)     // Catch:{ all -> 0x010f }
            com.google.android.gms.internal.measurement.zzfe r4 = r22.zzga()     // Catch:{ all -> 0x010f }
            java.lang.String r4 = r4.zzbj(r15)     // Catch:{ all -> 0x010f }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x010f }
            if (r20 == 0) goto L_0x010e
            r20.close()
        L_0x010e:
            return r16
        L_0x010f:
            r0 = move-exception
        L_0x0110:
            if (r20 == 0) goto L_0x0115
            r20.close()
        L_0x0115:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzf(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzeq");
    }

    @WorkerThread
    public final void zzg(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            zzge().zzit().zzg("Deleted user attribute rows", Integer.valueOf(getWritableDatabase().delete("user_attributes", "app_id=? and name=?", new String[]{str, str2})));
        } catch (SQLiteException e) {
            zzge().zzim().zzd("Error deleting user attribute. appId", zzfg.zzbm(str), zzga().zzbl(str2), e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b1  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzjz zzh(java.lang.String r19, java.lang.String r20) {
        /*
            r18 = this;
            r8 = r20
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r19)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r20)
            r18.zzab()
            r18.zzch()
            r9 = 0
            android.database.sqlite.SQLiteDatabase r10 = r18.getWritableDatabase()     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            java.lang.String r11 = "user_attributes"
            r0 = 3
            java.lang.String[] r12 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            java.lang.String r0 = "set_timestamp"
            r1 = 0
            r12[r1] = r0     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            java.lang.String r0 = "value"
            r2 = 1
            r12[r2] = r0     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            java.lang.String r0 = "origin"
            r3 = 2
            r12[r3] = r0     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            java.lang.String r13 = "app_id=? and name=?"
            java.lang.String[] r14 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            r14[r1] = r19     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            r14[r2] = r8     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteException -> 0x008b, all -> 0x0086 }
            boolean r0 = r10.moveToFirst()     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            if (r0 != 0) goto L_0x0047
            if (r10 == 0) goto L_0x0046
            r10.close()
        L_0x0046:
            return r9
        L_0x0047:
            long r5 = r10.getLong(r1)     // Catch:{ SQLiteException -> 0x0082, all -> 0x007e }
            r11 = r18
            java.lang.Object r7 = r11.zza((android.database.Cursor) r10, (int) r2)     // Catch:{ SQLiteException -> 0x007c }
            java.lang.String r3 = r10.getString(r3)     // Catch:{ SQLiteException -> 0x007c }
            com.google.android.gms.internal.measurement.zzjz r0 = new com.google.android.gms.internal.measurement.zzjz     // Catch:{ SQLiteException -> 0x007c }
            r1 = r0
            r2 = r19
            r4 = r20
            r1.<init>(r2, r3, r4, r5, r7)     // Catch:{ SQLiteException -> 0x007c }
            boolean r1 = r10.moveToNext()     // Catch:{ SQLiteException -> 0x007c }
            if (r1 == 0) goto L_0x0076
            com.google.android.gms.internal.measurement.zzfg r1 = r18.zzge()     // Catch:{ SQLiteException -> 0x007c }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ SQLiteException -> 0x007c }
            java.lang.String r2 = "Got multiple records for user property, expected one. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r19)     // Catch:{ SQLiteException -> 0x007c }
            r1.zzg(r2, r3)     // Catch:{ SQLiteException -> 0x007c }
        L_0x0076:
            if (r10 == 0) goto L_0x007b
            r10.close()
        L_0x007b:
            return r0
        L_0x007c:
            r0 = move-exception
            goto L_0x008f
        L_0x007e:
            r0 = move-exception
            r11 = r18
            goto L_0x00af
        L_0x0082:
            r0 = move-exception
            r11 = r18
            goto L_0x008f
        L_0x0086:
            r0 = move-exception
            r11 = r18
            r10 = r9
            goto L_0x00af
        L_0x008b:
            r0 = move-exception
            r11 = r18
            r10 = r9
        L_0x008f:
            com.google.android.gms.internal.measurement.zzfg r1 = r18.zzge()     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x00ae }
            java.lang.String r2 = "Error querying user property. appId"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r19)     // Catch:{ all -> 0x00ae }
            com.google.android.gms.internal.measurement.zzfe r4 = r18.zzga()     // Catch:{ all -> 0x00ae }
            java.lang.String r4 = r4.zzbl(r8)     // Catch:{ all -> 0x00ae }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x00ae }
            if (r10 == 0) goto L_0x00ad
            r10.close()
        L_0x00ad:
            return r9
        L_0x00ae:
            r0 = move-exception
        L_0x00af:
            if (r10 == 0) goto L_0x00b4
            r10.close()
        L_0x00b4:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzh(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzjz");
    }

    /* access modifiers changed from: protected */
    public final boolean zzhf() {
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0041  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String zzhn() {
        /*
            r6 = this;
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()
            r1 = 0
            java.lang.String r2 = "select app_id from queue order by has_realtime desc, rowid asc limit 1;"
            android.database.Cursor r0 = r0.rawQuery(r2, r1)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0024 }
            boolean r2 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0022 }
            if (r2 == 0) goto L_0x001c
            r2 = 0
            java.lang.String r2 = r0.getString(r2)     // Catch:{ SQLiteException -> 0x0022 }
            if (r0 == 0) goto L_0x001b
            r0.close()
        L_0x001b:
            return r2
        L_0x001c:
            if (r0 == 0) goto L_0x0021
            r0.close()
        L_0x0021:
            return r1
        L_0x0022:
            r2 = move-exception
            goto L_0x002b
        L_0x0024:
            r0 = move-exception
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x003f
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            com.google.android.gms.internal.measurement.zzfg r3 = r6.zzge()     // Catch:{ all -> 0x003e }
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()     // Catch:{ all -> 0x003e }
            java.lang.String r4 = "Database error getting next bundle app id"
            r3.zzg(r4, r2)     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            return r1
        L_0x003e:
            r1 = move-exception
        L_0x003f:
            if (r0 == 0) goto L_0x0044
            r0.close()
        L_0x0044:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzhn():java.lang.String");
    }

    public final boolean zzho() {
        return zza("select count(1) > 0 from queue where has_realtime = 1", (String[]) null) != 0;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public final void zzhp() {
        int delete;
        zzab();
        zzch();
        if (zzhv()) {
            long j = zzgf().zzajx.get();
            long elapsedRealtime = zzbt().elapsedRealtime();
            if (Math.abs(elapsedRealtime - j) > zzew.zzahg.get().longValue()) {
                zzgf().zzajx.set(elapsedRealtime);
                zzab();
                zzch();
                if (zzhv() && (delete = getWritableDatabase().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(zzbt().currentTimeMillis()), String.valueOf(zzef.zzhh())})) > 0) {
                    zzge().zzit().zzg("Deleted stale rows. rowsDeleted", Integer.valueOf(delete));
                }
            }
        }
    }

    @WorkerThread
    public final long zzhq() {
        return zza("select max(bundle_end_timestamp) from queue", (String[]) null, 0);
    }

    @WorkerThread
    public final long zzhr() {
        return zza("select max(timestamp) from raw_events", (String[]) null, 0);
    }

    public final boolean zzhs() {
        return zza("select count(1) > 0 from raw_events", (String[]) null) != 0;
    }

    public final boolean zzht() {
        return zza("select count(1) > 0 from raw_events where realtime = 1", (String[]) null) != 0;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0044  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long zzhu() {
        /*
            r7 = this;
            r0 = -1
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.getWritableDatabase()     // Catch:{ SQLiteException -> 0x002e }
            java.lang.String r4 = "select rowid from raw_events order by rowid desc limit 1;"
            android.database.Cursor r3 = r3.rawQuery(r4, r2)     // Catch:{ SQLiteException -> 0x002e }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0027, all -> 0x0024 }
            if (r2 != 0) goto L_0x0019
            if (r3 == 0) goto L_0x0018
            r3.close()
        L_0x0018:
            return r0
        L_0x0019:
            r2 = 0
            long r4 = r3.getLong(r2)     // Catch:{ SQLiteException -> 0x0027, all -> 0x0024 }
            if (r3 == 0) goto L_0x0023
            r3.close()
        L_0x0023:
            return r4
        L_0x0024:
            r0 = move-exception
            r2 = r3
            goto L_0x0042
        L_0x0027:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x002f
        L_0x002c:
            r0 = move-exception
            goto L_0x0042
        L_0x002e:
            r3 = move-exception
        L_0x002f:
            com.google.android.gms.internal.measurement.zzfg r4 = r7.zzge()     // Catch:{ all -> 0x002c }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x002c }
            java.lang.String r5 = "Error querying raw events"
            r4.zzg(r5, r3)     // Catch:{ all -> 0x002c }
            if (r2 == 0) goto L_0x0041
            r2.close()
        L_0x0041:
            return r0
        L_0x0042:
            if (r2 == 0) goto L_0x0047
            r2.close()
        L_0x0047:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzhu():long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x015a  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzed zzi(java.lang.String r33, java.lang.String r34) {
        /*
            r32 = this;
            r7 = r34
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r33)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r34)
            r32.zzab()
            r32.zzch()
            r8 = 0
            android.database.sqlite.SQLiteDatabase r9 = r32.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r10 = "conditional_properties"
            r0 = 11
            java.lang.String[] r11 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "origin"
            r1 = 0
            r11[r1] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "value"
            r2 = 1
            r11[r2] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "active"
            r3 = 2
            r11[r3] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "trigger_event_name"
            r4 = 3
            r11[r4] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "trigger_timeout"
            r5 = 4
            r11[r5] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "timed_out_event"
            r6 = 5
            r11[r6] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "creation_timestamp"
            r15 = 6
            r11[r15] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "triggered_event"
            r14 = 7
            r11[r14] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "triggered_timestamp"
            r13 = 8
            r11[r13] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "time_to_live"
            r12 = 9
            r11[r12] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "expired_event"
            r6 = 10
            r11[r6] = r0     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            java.lang.String r0 = "app_id=? and name=?"
            java.lang.String[] r13 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            r13[r1] = r33     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            r13[r2] = r7     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            r16 = 0
            r17 = 0
            r18 = 0
            r6 = 9
            r12 = r0
            r0 = 8
            r6 = 7
            r14 = r16
            r0 = 6
            r15 = r17
            r16 = r18
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16)     // Catch:{ SQLiteException -> 0x0134, all -> 0x012f }
            boolean r10 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x012b, all -> 0x0127 }
            if (r10 != 0) goto L_0x0085
            if (r9 == 0) goto L_0x0084
            r9.close()
        L_0x0084:
            return r8
        L_0x0085:
            java.lang.String r19 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x012b, all -> 0x0127 }
            r10 = r32
            java.lang.Object r11 = r10.zza((android.database.Cursor) r9, (int) r2)     // Catch:{ SQLiteException -> 0x0125 }
            int r3 = r9.getInt(r3)     // Catch:{ SQLiteException -> 0x0125 }
            if (r3 == 0) goto L_0x0098
            r23 = 1
            goto L_0x009a
        L_0x0098:
            r23 = 0
        L_0x009a:
            java.lang.String r24 = r9.getString(r4)     // Catch:{ SQLiteException -> 0x0125 }
            long r26 = r9.getLong(r5)     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzka r1 = r32.zzgb()     // Catch:{ SQLiteException -> 0x0125 }
            r2 = 5
            byte[] r2 = r9.getBlob(r2)     // Catch:{ SQLiteException -> 0x0125 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r3 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ SQLiteException -> 0x0125 }
            android.os.Parcelable r1 = r1.zza((byte[]) r2, r3)     // Catch:{ SQLiteException -> 0x0125 }
            r25 = r1
            com.google.android.gms.internal.measurement.zzeu r25 = (com.google.android.gms.internal.measurement.zzeu) r25     // Catch:{ SQLiteException -> 0x0125 }
            long r21 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzka r0 = r32.zzgb()     // Catch:{ SQLiteException -> 0x0125 }
            byte[] r1 = r9.getBlob(r6)     // Catch:{ SQLiteException -> 0x0125 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r2 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ SQLiteException -> 0x0125 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x0125 }
            r28 = r0
            com.google.android.gms.internal.measurement.zzeu r28 = (com.google.android.gms.internal.measurement.zzeu) r28     // Catch:{ SQLiteException -> 0x0125 }
            r0 = 8
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0125 }
            r0 = 9
            long r29 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzka r0 = r32.zzgb()     // Catch:{ SQLiteException -> 0x0125 }
            r1 = 10
            byte[] r1 = r9.getBlob(r1)     // Catch:{ SQLiteException -> 0x0125 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r2 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ SQLiteException -> 0x0125 }
            android.os.Parcelable r0 = r0.zza((byte[]) r1, r2)     // Catch:{ SQLiteException -> 0x0125 }
            r31 = r0
            com.google.android.gms.internal.measurement.zzeu r31 = (com.google.android.gms.internal.measurement.zzeu) r31     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzjx r20 = new com.google.android.gms.internal.measurement.zzjx     // Catch:{ SQLiteException -> 0x0125 }
            r1 = r20
            r2 = r34
            r5 = r11
            r6 = r19
            r1.<init>(r2, r3, r5, r6)     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzed r0 = new com.google.android.gms.internal.measurement.zzed     // Catch:{ SQLiteException -> 0x0125 }
            r17 = r0
            r18 = r33
            r17.<init>(r18, r19, r20, r21, r23, r24, r25, r26, r28, r29, r31)     // Catch:{ SQLiteException -> 0x0125 }
            boolean r1 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x0125 }
            if (r1 == 0) goto L_0x011f
            com.google.android.gms.internal.measurement.zzfg r1 = r32.zzge()     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ SQLiteException -> 0x0125 }
            java.lang.String r2 = "Got multiple records for conditional property, expected one"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r33)     // Catch:{ SQLiteException -> 0x0125 }
            com.google.android.gms.internal.measurement.zzfe r4 = r32.zzga()     // Catch:{ SQLiteException -> 0x0125 }
            java.lang.String r4 = r4.zzbl(r7)     // Catch:{ SQLiteException -> 0x0125 }
            r1.zze(r2, r3, r4)     // Catch:{ SQLiteException -> 0x0125 }
        L_0x011f:
            if (r9 == 0) goto L_0x0124
            r9.close()
        L_0x0124:
            return r0
        L_0x0125:
            r0 = move-exception
            goto L_0x0138
        L_0x0127:
            r0 = move-exception
            r10 = r32
            goto L_0x0158
        L_0x012b:
            r0 = move-exception
            r10 = r32
            goto L_0x0138
        L_0x012f:
            r0 = move-exception
            r10 = r32
            r9 = r8
            goto L_0x0158
        L_0x0134:
            r0 = move-exception
            r10 = r32
            r9 = r8
        L_0x0138:
            com.google.android.gms.internal.measurement.zzfg r1 = r32.zzge()     // Catch:{ all -> 0x0157 }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x0157 }
            java.lang.String r2 = "Error querying conditional property"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r33)     // Catch:{ all -> 0x0157 }
            com.google.android.gms.internal.measurement.zzfe r4 = r32.zzga()     // Catch:{ all -> 0x0157 }
            java.lang.String r4 = r4.zzbl(r7)     // Catch:{ all -> 0x0157 }
            r1.zzd(r2, r3, r4, r0)     // Catch:{ all -> 0x0157 }
            if (r9 == 0) goto L_0x0156
            r9.close()
        L_0x0156:
            return r8
        L_0x0157:
            r0 = move-exception
        L_0x0158:
            if (r9 == 0) goto L_0x015d
            r9.close()
        L_0x015d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzi(java.lang.String, java.lang.String):com.google.android.gms.internal.measurement.zzed");
    }

    @WorkerThread
    public final int zzj(String str, String str2) {
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        try {
            return getWritableDatabase().delete("conditional_properties", "app_id=? and name=?", new String[]{str, str2});
        } catch (SQLiteException e) {
            zzge().zzim().zzd("Error deleting conditional property", zzfg.zzbm(str), zzga().zzbl(str2), e);
            return 0;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzke>> zzk(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzch()
            r12.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "event_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "app_id=? AND event_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r10] = r13     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0097 }
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r0
        L_0x0048:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0097 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzabv r1 = com.google.android.gms.internal.measurement.zzabv.zza(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzke r2 = new com.google.android.gms.internal.measurement.zzke     // Catch:{ SQLiteException -> 0x0097 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            r2.zzb(r1)     // Catch:{ IOException -> 0x0079 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0097 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0097 }
            if (r3 != 0) goto L_0x0075
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0097 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x0075:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0097 }
            goto L_0x008b
        L_0x0079:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfg r2 = r12.zzge()     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.String r3 = "Failed to merge filter. appId"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r13)     // Catch:{ SQLiteException -> 0x0097 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x008b:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0096
            r14.close()
        L_0x0096:
            return r0
        L_0x0097:
            r0 = move-exception
            goto L_0x009e
        L_0x0099:
            r13 = move-exception
            r14 = r9
            goto L_0x00b6
        L_0x009c:
            r0 = move-exception
            r14 = r9
        L_0x009e:
            com.google.android.gms.internal.measurement.zzfg r1 = r12.zzge()     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.zzfg.zzbm(r13)     // Catch:{ all -> 0x00b5 }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00b5 }
            if (r14 == 0) goto L_0x00b4
            r14.close()
        L_0x00b4:
            return r9
        L_0x00b5:
            r13 = move-exception
        L_0x00b6:
            if (r14 == 0) goto L_0x00bb
            r14.close()
        L_0x00bb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzk(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.Map<java.lang.Integer, java.util.List<com.google.android.gms.internal.measurement.zzkh>> zzl(java.lang.String r13, java.lang.String r14) {
        /*
            r12 = this;
            r12.zzch()
            r12.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r13)
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r14)
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            android.database.sqlite.SQLiteDatabase r1 = r12.getWritableDatabase()
            r9 = 0
            java.lang.String r2 = "property_filters"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "audience_id"
            r10 = 0
            r4[r10] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "data"
            r11 = 1
            r4[r11] = r5     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            java.lang.String r5 = "app_id=? AND property_name=?"
            java.lang.String[] r6 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r10] = r13     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r6[r11] = r14     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            r14 = 0
            r7 = 0
            r8 = 0
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r14
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x009c, all -> 0x0099 }
            boolean r1 = r14.moveToFirst()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            java.util.Map r0 = java.util.Collections.emptyMap()     // Catch:{ SQLiteException -> 0x0097 }
            if (r14 == 0) goto L_0x0047
            r14.close()
        L_0x0047:
            return r0
        L_0x0048:
            byte[] r1 = r14.getBlob(r11)     // Catch:{ SQLiteException -> 0x0097 }
            int r2 = r1.length     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzabv r1 = com.google.android.gms.internal.measurement.zzabv.zza(r1, r10, r2)     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzkh r2 = new com.google.android.gms.internal.measurement.zzkh     // Catch:{ SQLiteException -> 0x0097 }
            r2.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            r2.zzb(r1)     // Catch:{ IOException -> 0x0079 }
            int r1 = r14.getInt(r10)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Object r3 = r0.get(r3)     // Catch:{ SQLiteException -> 0x0097 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ SQLiteException -> 0x0097 }
            if (r3 != 0) goto L_0x0075
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0097 }
            r3.<init>()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ SQLiteException -> 0x0097 }
            r0.put(r1, r3)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x0075:
            r3.add(r2)     // Catch:{ SQLiteException -> 0x0097 }
            goto L_0x008b
        L_0x0079:
            r1 = move-exception
            com.google.android.gms.internal.measurement.zzfg r2 = r12.zzge()     // Catch:{ SQLiteException -> 0x0097 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ SQLiteException -> 0x0097 }
            java.lang.String r3 = "Failed to merge filter"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r13)     // Catch:{ SQLiteException -> 0x0097 }
            r2.zze(r3, r4, r1)     // Catch:{ SQLiteException -> 0x0097 }
        L_0x008b:
            boolean r1 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x0097 }
            if (r1 != 0) goto L_0x0048
            if (r14 == 0) goto L_0x0096
            r14.close()
        L_0x0096:
            return r0
        L_0x0097:
            r0 = move-exception
            goto L_0x009e
        L_0x0099:
            r13 = move-exception
            r14 = r9
            goto L_0x00b6
        L_0x009c:
            r0 = move-exception
            r14 = r9
        L_0x009e:
            com.google.android.gms.internal.measurement.zzfg r1 = r12.zzge()     // Catch:{ all -> 0x00b5 }
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzim()     // Catch:{ all -> 0x00b5 }
            java.lang.String r2 = "Database error querying filters. appId"
            java.lang.Object r13 = com.google.android.gms.internal.measurement.zzfg.zzbm(r13)     // Catch:{ all -> 0x00b5 }
            r1.zze(r2, r13, r0)     // Catch:{ all -> 0x00b5 }
            if (r14 == 0) goto L_0x00b4
            r14.close()
        L_0x00b4:
            return r9
        L_0x00b5:
            r13 = move-exception
        L_0x00b6:
            if (r14 == 0) goto L_0x00bb
            r14.close()
        L_0x00bb:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzei.zzl(java.lang.String, java.lang.String):java.util.Map");
    }

    /* access modifiers changed from: protected */
    @WorkerThread
    @VisibleForTesting
    public final long zzm(String str, String str2) {
        long j;
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotEmpty(str2);
        zzab();
        zzch();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 32);
            sb.append("select ");
            sb.append(str2);
            sb.append(" from app2 where app_id=?");
            j = zza(sb.toString(), new String[]{str}, -1);
            if (j == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("app_id", str);
                contentValues.put("first_open_count", 0);
                contentValues.put("previous_install_count", 0);
                if (writableDatabase.insertWithOnConflict("app2", (String) null, contentValues, 5) == -1) {
                    zzge().zzim().zze("Failed to insert column (got -1). appId", zzfg.zzbm(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                j = 0;
            }
            try {
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("app_id", str);
                contentValues2.put(str2, Long.valueOf(1 + j));
                if (((long) writableDatabase.update("app2", contentValues2, "app_id = ?", new String[]{str})) == 0) {
                    zzge().zzim().zze("Failed to update column (got 0). appId", zzfg.zzbm(str), str2);
                    writableDatabase.endTransaction();
                    return -1;
                }
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return j;
            } catch (SQLiteException e) {
                e = e;
            }
        } catch (SQLiteException e2) {
            e = e2;
            j = 0;
            try {
                zzge().zzim().zzd("Error inserting column. appId", zzfg.zzbm(str), str2, e);
                writableDatabase.endTransaction();
                return j;
            } catch (Throwable th) {
                writableDatabase.endTransaction();
                throw th;
            }
        }
    }
}
