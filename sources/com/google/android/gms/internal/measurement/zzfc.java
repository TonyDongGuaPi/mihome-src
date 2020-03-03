package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzfc extends zzhh {
    private final zzfd zzaig = new zzfd(this, getContext(), "google_app_measurement_local.db");
    private boolean zzaih;

    zzfc(zzgl zzgl) {
        super(zzgl);
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzaih) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzaig.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzaih = true;
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v4, resolved type: android.database.sqlite.SQLiteDatabase} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [int, boolean] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c9 A[SYNTHETIC, Splitter:B:49:0x00c9] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ec  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x011b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x011b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x011b A[SYNTHETIC] */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r18, byte[] r19) {
        /*
            r17 = this;
            r1 = r17
            r17.zzab()
            boolean r0 = r1.zzaih
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r0 = "type"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)
            r3.put(r0, r4)
            java.lang.String r0 = "entry"
            r4 = r19
            r3.put(r0, r4)
            r4 = 5
            r5 = 0
            r6 = 5
        L_0x0024:
            if (r5 >= r4) goto L_0x012e
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r17.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x0102, SQLiteDatabaseLockedException -> 0x00f0, SQLiteException -> 0x00c5, all -> 0x00c1 }
            if (r9 != 0) goto L_0x003e
            r1.zzaih = r8     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x00f1, SQLiteException -> 0x0036 }
            if (r9 == 0) goto L_0x0035
            r9.close()
        L_0x0035:
            return r2
        L_0x0036:
            r0 = move-exception
            r12 = r7
        L_0x0038:
            r7 = r9
            goto L_0x00c7
        L_0x003b:
            r0 = move-exception
            goto L_0x0104
        L_0x003e:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x00f1, SQLiteException -> 0x0036 }
            r10 = 0
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x003b, SQLiteDatabaseLockedException -> 0x00f1, SQLiteException -> 0x0036 }
            if (r12 == 0) goto L_0x005f
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            if (r0 == 0) goto L_0x005f
            long r10 = r12.getLong(r2)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            goto L_0x005f
        L_0x0056:
            r0 = move-exception
            goto L_0x0123
        L_0x0059:
            r0 = move-exception
            goto L_0x0038
        L_0x005b:
            r0 = move-exception
            r7 = r12
            goto L_0x0104
        L_0x005f:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 < 0) goto L_0x00a9
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.String r15 = "Data loss, local db full"
            r0.log(r15)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            r0 = 0
            long r13 = r13 - r10
            r10 = 1
            long r13 = r13 + r10
            java.lang.String r0 = "messages"
            java.lang.String r10 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.String r15 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            r11[r2] = r15     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            int r0 = r9.delete(r0, r10, r11)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            long r10 = (long) r0     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x00a9
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r4 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            java.lang.Long r2 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            r16 = 0
            long r13 = r13 - r10
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            r0.zzd(r15, r4, r2, r10)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
        L_0x00a9:
            java.lang.String r0 = "messages"
            r9.insertOrThrow(r0, r7, r3)     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x005b, SQLiteDatabaseLockedException -> 0x00bf, SQLiteException -> 0x0059, all -> 0x0056 }
            if (r12 == 0) goto L_0x00b9
            r12.close()
        L_0x00b9:
            if (r9 == 0) goto L_0x00be
            r9.close()
        L_0x00be:
            return r8
        L_0x00bf:
            r7 = r12
            goto L_0x00f1
        L_0x00c1:
            r0 = move-exception
            r9 = r7
            r12 = r9
            goto L_0x0123
        L_0x00c5:
            r0 = move-exception
            r12 = r7
        L_0x00c7:
            if (r7 == 0) goto L_0x00d6
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00d3 }
            if (r2 == 0) goto L_0x00d6
            r7.endTransaction()     // Catch:{ all -> 0x00d3 }
            goto L_0x00d6
        L_0x00d3:
            r0 = move-exception
            r9 = r7
            goto L_0x0123
        L_0x00d6:
            com.google.android.gms.internal.measurement.zzfg r2 = r17.zzge()     // Catch:{ all -> 0x00d3 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x00d3 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zzg(r4, r0)     // Catch:{ all -> 0x00d3 }
            r1.zzaih = r8     // Catch:{ all -> 0x00d3 }
            if (r12 == 0) goto L_0x00ea
            r12.close()
        L_0x00ea:
            if (r7 == 0) goto L_0x011b
            r7.close()
            goto L_0x011b
        L_0x00f0:
            r9 = r7
        L_0x00f1:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x0121 }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00fc
            r7.close()
        L_0x00fc:
            if (r9 == 0) goto L_0x011b
        L_0x00fe:
            r9.close()
            goto L_0x011b
        L_0x0102:
            r0 = move-exception
            r9 = r7
        L_0x0104:
            com.google.android.gms.internal.measurement.zzfg r2 = r17.zzge()     // Catch:{ all -> 0x0121 }
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()     // Catch:{ all -> 0x0121 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zzg(r4, r0)     // Catch:{ all -> 0x0121 }
            r1.zzaih = r8     // Catch:{ all -> 0x0121 }
            if (r7 == 0) goto L_0x0118
            r7.close()
        L_0x0118:
            if (r9 == 0) goto L_0x011b
            goto L_0x00fe
        L_0x011b:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0024
        L_0x0121:
            r0 = move-exception
            r12 = r7
        L_0x0123:
            if (r12 == 0) goto L_0x0128
            r12.close()
        L_0x0128:
            if (r9 == 0) goto L_0x012d
            r9.close()
        L_0x012d:
            throw r0
        L_0x012e:
            com.google.android.gms.internal.measurement.zzfg r0 = r17.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.log(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfc.zza(int, byte[]):boolean");
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzab();
        try {
            int delete = getWritableDatabase().delete("messages", (String) null, (String[]) null) + 0;
            if (delete > 0) {
                zzge().zzit().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzge().zzim().zzg("Error resetting local analytics data. error", e);
        }
    }

    public final boolean zza(zzeu zzeu) {
        Parcel obtain = Parcel.obtain();
        zzeu.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzge().zzip().log("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzjx zzjx) {
        Parcel obtain = Parcel.obtain();
        zzjx.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzge().zzip().log("User property too long for local database. Sending directly to service");
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzab() {
        super.zzab();
    }

    public final /* bridge */ /* synthetic */ Clock zzbt() {
        return super.zzbt();
    }

    public final boolean zzc(zzed zzed) {
        zzgb();
        byte[] zza = zzka.zza((Parcelable) zzed);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzge().zzip().log("Conditional user property too long for local database. Sending directly to service");
        return false;
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

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v0, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v3, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v19, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v24, resolved type: android.database.Cursor} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: android.database.Cursor} */
    /* JADX WARNING: type inference failed for: r2v28 */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:54|55|56|57) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:69|70|71|72) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:41|42|43|44|165) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0036, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        r9 = r2;
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003b, code lost:
        r2 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003e, code lost:
        r0 = e;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        zzge().zzim().log("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r13.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        zzge().zzim().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r13.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        zzge().zzim().log("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r13.recycle();
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x00a6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:54:0x00d6 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:69:0x0109 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x018c A[SYNTHETIC, Splitter:B:112:0x018c] */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01a9  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01ae  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x01f0  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01e5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x01e5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x01e5 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x002e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzp(int r20) {
        /*
            r19 = this;
            r1 = r19
            r19.zzab()
            boolean r0 = r1.zzaih
            r2 = 0
            if (r0 == 0) goto L_0x000b
            return r2
        L_0x000b:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.content.Context r0 = r19.getContext()
            java.lang.String r4 = "google_app_measurement_local.db"
            java.io.File r0 = r0.getDatabasePath(r4)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0021
            return r3
        L_0x0021:
            r4 = 5
            r5 = 0
            r6 = 0
            r7 = 5
        L_0x0025:
            if (r6 >= r4) goto L_0x01f9
            r8 = 1
            android.database.sqlite.SQLiteDatabase r15 = r19.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x01c9, SQLiteDatabaseLockedException -> 0x01b2, SQLiteException -> 0x0187, all -> 0x0182 }
            if (r15 != 0) goto L_0x0041
            r1.zzaih = r8     // Catch:{ SQLiteFullException -> 0x003e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0036 }
            if (r15 == 0) goto L_0x0035
            r15.close()
        L_0x0035:
            return r2
        L_0x0036:
            r0 = move-exception
            r9 = r2
            r2 = r15
            goto L_0x018a
        L_0x003b:
            r2 = r15
            goto L_0x017b
        L_0x003e:
            r0 = move-exception
            goto L_0x01cc
        L_0x0041:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0178, all -> 0x0175 }
            java.lang.String r10 = "messages"
            r0 = 3
            java.lang.String[] r11 = new java.lang.String[r0]     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0178, all -> 0x0175 }
            java.lang.String r0 = "rowid"
            r11[r5] = r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0178, all -> 0x0175 }
            java.lang.String r0 = "type"
            r11[r8] = r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0178, all -> 0x0175 }
            java.lang.String r0 = "entry"
            r14 = 2
            r11[r14] = r0     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0178, all -> 0x0175 }
            r12 = 0
            r13 = 0
            r0 = 0
            r16 = 0
            java.lang.String r17 = "rowid asc"
            r9 = 100
            java.lang.String r18 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x017e, SQLiteDatabaseLockedException -> 0x003b, SQLiteException -> 0x0178, all -> 0x0175 }
            r9 = r15
            r4 = 2
            r14 = r0
            r2 = r15
            r15 = r16
            r16 = r17
            r17 = r18
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteFullException -> 0x0172, SQLiteDatabaseLockedException -> 0x017b, SQLiteException -> 0x0170, all -> 0x016e }
            r10 = -1
        L_0x0074:
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            if (r0 == 0) goto L_0x0130
            long r10 = r9.getLong(r5)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            int r0 = r9.getInt(r8)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            byte[] r12 = r9.getBlob(r4)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            if (r0 != 0) goto L_0x00bb
            android.os.Parcel r13 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00a6 }
            r13.unmarshall(r12, r5, r0)     // Catch:{ ParseException -> 0x00a6 }
            r13.setDataPosition(r5)     // Catch:{ ParseException -> 0x00a6 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzeu> r0 = com.google.android.gms.internal.measurement.zzeu.CREATOR     // Catch:{ ParseException -> 0x00a6 }
            java.lang.Object r0 = r0.createFromParcel(r13)     // Catch:{ ParseException -> 0x00a6 }
            com.google.android.gms.internal.measurement.zzeu r0 = (com.google.android.gms.internal.measurement.zzeu) r0     // Catch:{ ParseException -> 0x00a6 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            if (r0 == 0) goto L_0x0074
        L_0x00a0:
            r3.add(r0)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            goto L_0x0074
        L_0x00a4:
            r0 = move-exception
            goto L_0x00b7
        L_0x00a6:
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ all -> 0x00a4 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x00a4 }
            java.lang.String r12 = "Failed to load event from local database"
            r0.log(r12)     // Catch:{ all -> 0x00a4 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            goto L_0x0074
        L_0x00b7:
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
        L_0x00bb:
            if (r0 != r8) goto L_0x00ee
            android.os.Parcel r13 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x00d6 }
            r13.unmarshall(r12, r5, r0)     // Catch:{ ParseException -> 0x00d6 }
            r13.setDataPosition(r5)     // Catch:{ ParseException -> 0x00d6 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzjx> r0 = com.google.android.gms.internal.measurement.zzjx.CREATOR     // Catch:{ ParseException -> 0x00d6 }
            java.lang.Object r0 = r0.createFromParcel(r13)     // Catch:{ ParseException -> 0x00d6 }
            com.google.android.gms.internal.measurement.zzjx r0 = (com.google.android.gms.internal.measurement.zzjx) r0     // Catch:{ ParseException -> 0x00d6 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            goto L_0x00e7
        L_0x00d4:
            r0 = move-exception
            goto L_0x00ea
        L_0x00d6:
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ all -> 0x00d4 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x00d4 }
            java.lang.String r12 = "Failed to load user property from local database"
            r0.log(r12)     // Catch:{ all -> 0x00d4 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            r0 = 0
        L_0x00e7:
            if (r0 == 0) goto L_0x0074
            goto L_0x00a0
        L_0x00ea:
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
        L_0x00ee:
            if (r0 != r4) goto L_0x0121
            android.os.Parcel r13 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            int r0 = r12.length     // Catch:{ ParseException -> 0x0109 }
            r13.unmarshall(r12, r5, r0)     // Catch:{ ParseException -> 0x0109 }
            r13.setDataPosition(r5)     // Catch:{ ParseException -> 0x0109 }
            android.os.Parcelable$Creator<com.google.android.gms.internal.measurement.zzed> r0 = com.google.android.gms.internal.measurement.zzed.CREATOR     // Catch:{ ParseException -> 0x0109 }
            java.lang.Object r0 = r0.createFromParcel(r13)     // Catch:{ ParseException -> 0x0109 }
            com.google.android.gms.internal.measurement.zzed r0 = (com.google.android.gms.internal.measurement.zzed) r0     // Catch:{ ParseException -> 0x0109 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            goto L_0x011a
        L_0x0107:
            r0 = move-exception
            goto L_0x011d
        L_0x0109:
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ all -> 0x0107 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ all -> 0x0107 }
            java.lang.String r12 = "Failed to load user property from local database"
            r0.log(r12)     // Catch:{ all -> 0x0107 }
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            r0 = 0
        L_0x011a:
            if (r0 == 0) goto L_0x0074
            goto L_0x00a0
        L_0x011d:
            r13.recycle()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
        L_0x0121:
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            java.lang.String r12 = "Unknown record type in local database"
            r0.log(r12)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            goto L_0x0074
        L_0x0130:
            java.lang.String r0 = "messages"
            java.lang.String r4 = "rowid <= ?"
            java.lang.String[] r12 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            java.lang.String r10 = java.lang.Long.toString(r10)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            r12[r5] = r10     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            int r0 = r2.delete(r0, r4, r12)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            int r4 = r3.size()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            if (r0 >= r4) goto L_0x0153
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            java.lang.String r4 = "Fewer entries removed from local database than expected"
            r0.log(r4)     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
        L_0x0153:
            r2.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            r2.endTransaction()     // Catch:{ SQLiteFullException -> 0x0169, SQLiteDatabaseLockedException -> 0x0166, SQLiteException -> 0x0164 }
            if (r9 == 0) goto L_0x015e
            r9.close()
        L_0x015e:
            if (r2 == 0) goto L_0x0163
            r2.close()
        L_0x0163:
            return r3
        L_0x0164:
            r0 = move-exception
            goto L_0x018a
        L_0x0166:
            r4 = r2
            r2 = r9
            goto L_0x01b4
        L_0x0169:
            r0 = move-exception
            r15 = r2
            r2 = r9
            goto L_0x01cc
        L_0x016e:
            r0 = move-exception
            goto L_0x0184
        L_0x0170:
            r0 = move-exception
            goto L_0x0189
        L_0x0172:
            r0 = move-exception
            r15 = r2
            goto L_0x0180
        L_0x0175:
            r0 = move-exception
            r2 = r15
            goto L_0x0184
        L_0x0178:
            r0 = move-exception
            r2 = r15
            goto L_0x0189
        L_0x017b:
            r4 = r2
            r2 = 0
            goto L_0x01b4
        L_0x017e:
            r0 = move-exception
            r2 = r15
        L_0x0180:
            r2 = 0
            goto L_0x01cc
        L_0x0182:
            r0 = move-exception
            r2 = 0
        L_0x0184:
            r9 = 0
            goto L_0x01ee
        L_0x0187:
            r0 = move-exception
            r2 = 0
        L_0x0189:
            r9 = 0
        L_0x018a:
            if (r2 == 0) goto L_0x0198
            boolean r4 = r2.inTransaction()     // Catch:{ all -> 0x0196 }
            if (r4 == 0) goto L_0x0198
            r2.endTransaction()     // Catch:{ all -> 0x0196 }
            goto L_0x0198
        L_0x0196:
            r0 = move-exception
            goto L_0x01ee
        L_0x0198:
            com.google.android.gms.internal.measurement.zzfg r4 = r19.zzge()     // Catch:{ all -> 0x0196 }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x0196 }
            java.lang.String r10 = "Error reading entries from local database"
            r4.zzg(r10, r0)     // Catch:{ all -> 0x0196 }
            r1.zzaih = r8     // Catch:{ all -> 0x0196 }
            if (r9 == 0) goto L_0x01ac
            r9.close()
        L_0x01ac:
            if (r2 == 0) goto L_0x01e5
            r2.close()
            goto L_0x01e5
        L_0x01b2:
            r2 = 0
            r4 = 0
        L_0x01b4:
            long r8 = (long) r7
            android.os.SystemClock.sleep(r8)     // Catch:{ all -> 0x01c5 }
            int r7 = r7 + 20
            if (r2 == 0) goto L_0x01bf
            r2.close()
        L_0x01bf:
            if (r4 == 0) goto L_0x01e5
            r4.close()
            goto L_0x01e5
        L_0x01c5:
            r0 = move-exception
            r9 = r2
            r2 = r4
            goto L_0x01ee
        L_0x01c9:
            r0 = move-exception
            r2 = 0
            r15 = 0
        L_0x01cc:
            com.google.android.gms.internal.measurement.zzfg r4 = r19.zzge()     // Catch:{ all -> 0x01eb }
            com.google.android.gms.internal.measurement.zzfi r4 = r4.zzim()     // Catch:{ all -> 0x01eb }
            java.lang.String r9 = "Error reading entries from local database"
            r4.zzg(r9, r0)     // Catch:{ all -> 0x01eb }
            r1.zzaih = r8     // Catch:{ all -> 0x01eb }
            if (r2 == 0) goto L_0x01e0
            r2.close()
        L_0x01e0:
            if (r15 == 0) goto L_0x01e5
            r15.close()
        L_0x01e5:
            int r6 = r6 + 1
            r2 = 0
            r4 = 5
            goto L_0x0025
        L_0x01eb:
            r0 = move-exception
            r9 = r2
            r2 = r15
        L_0x01ee:
            if (r9 == 0) goto L_0x01f3
            r9.close()
        L_0x01f3:
            if (r2 == 0) goto L_0x01f8
            r2.close()
        L_0x01f8:
            throw r0
        L_0x01f9:
            com.google.android.gms.internal.measurement.zzfg r0 = r19.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r2 = "Failed to read events from database in reasonable time"
            r0.log(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzfc.zzp(int):java.util.List");
    }
}
