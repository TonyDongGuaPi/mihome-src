package com.google.android.gms.internal.measurement;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzeb extends zzjq {
    zzeb(zzjr zzjr) {
        super(zzjr);
    }

    private final Boolean zza(double d, zzkg zzkg) {
        try {
            return zza(new BigDecimal(d), zzkg, Math.ulp(d));
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    private final Boolean zza(long j, zzkg zzkg) {
        try {
            return zza(new BigDecimal(j), zzkg, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        boolean startsWith;
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException unused) {
                    zzge().zzip().zzg("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                startsWith = str.startsWith(str2);
                break;
            case 3:
                startsWith = str.endsWith(str2);
                break;
            case 4:
                startsWith = str.contains(str2);
                break;
            case 5:
                startsWith = str.equals(str2);
                break;
            case 6:
                startsWith = list.contains(str);
                break;
            default:
                return null;
        }
        return Boolean.valueOf(startsWith);
    }

    private final Boolean zza(String str, zzkg zzkg) {
        if (!zzka.zzck(str)) {
            return null;
        }
        try {
            return zza(new BigDecimal(str), zzkg, 0.0d);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    @VisibleForTesting
    private final Boolean zza(String str, zzki zzki) {
        ArrayList arrayList;
        Preconditions.checkNotNull(zzki);
        if (str == null || zzki.zzash == null || zzki.zzash.intValue() == 0) {
            return null;
        }
        if (zzki.zzash.intValue() == 6) {
            if (zzki.zzask == null || zzki.zzask.length == 0) {
                return null;
            }
        } else if (zzki.zzasi == null) {
            return null;
        }
        int intValue = zzki.zzash.intValue();
        boolean z = zzki.zzasj != null && zzki.zzasj.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzki.zzasi : zzki.zzasi.toUpperCase(Locale.ENGLISH);
        if (zzki.zzask == null) {
            arrayList = null;
        } else {
            String[] strArr = zzki.zzask;
            if (z) {
                arrayList = Arrays.asList(strArr);
            } else {
                ArrayList arrayList2 = new ArrayList();
                for (String upperCase2 : strArr) {
                    arrayList2.add(upperCase2.toUpperCase(Locale.ENGLISH));
                }
                arrayList = arrayList2;
            }
        }
        return zza(str, intValue, z, upperCase, arrayList, intValue == 1 ? upperCase : null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0071, code lost:
        if (r3 != null) goto L_0x0073;
     */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r7, com.google.android.gms.internal.measurement.zzkg r8, double r9) {
        /*
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r8)
            java.lang.Integer r0 = r8.zzarz
            r1 = 0
            if (r0 == 0) goto L_0x00ed
            java.lang.Integer r0 = r8.zzarz
            int r0 = r0.intValue()
            if (r0 != 0) goto L_0x0012
            goto L_0x00ed
        L_0x0012:
            java.lang.Integer r0 = r8.zzarz
            int r0 = r0.intValue()
            r2 = 4
            if (r0 != r2) goto L_0x0024
            java.lang.String r0 = r8.zzasc
            if (r0 == 0) goto L_0x0023
            java.lang.String r0 = r8.zzasd
            if (r0 != 0) goto L_0x0029
        L_0x0023:
            return r1
        L_0x0024:
            java.lang.String r0 = r8.zzasb
            if (r0 != 0) goto L_0x0029
            return r1
        L_0x0029:
            java.lang.Integer r0 = r8.zzarz
            int r0 = r0.intValue()
            java.lang.Integer r3 = r8.zzarz
            int r3 = r3.intValue()
            if (r3 != r2) goto L_0x005a
            java.lang.String r3 = r8.zzasc
            boolean r3 = com.google.android.gms.internal.measurement.zzka.zzck(r3)
            if (r3 == 0) goto L_0x0059
            java.lang.String r3 = r8.zzasd
            boolean r3 = com.google.android.gms.internal.measurement.zzka.zzck(r3)
            if (r3 != 0) goto L_0x0048
            goto L_0x0059
        L_0x0048:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r4 = r8.zzasc     // Catch:{ NumberFormatException -> 0x0059 }
            r3.<init>(r4)     // Catch:{ NumberFormatException -> 0x0059 }
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0059 }
            java.lang.String r8 = r8.zzasd     // Catch:{ NumberFormatException -> 0x0059 }
            r4.<init>(r8)     // Catch:{ NumberFormatException -> 0x0059 }
            r8 = r3
            r3 = r1
            goto L_0x006c
        L_0x0059:
            return r1
        L_0x005a:
            java.lang.String r3 = r8.zzasb
            boolean r3 = com.google.android.gms.internal.measurement.zzka.zzck(r3)
            if (r3 != 0) goto L_0x0063
            return r1
        L_0x0063:
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x00ed }
            java.lang.String r8 = r8.zzasb     // Catch:{ NumberFormatException -> 0x00ed }
            r3.<init>(r8)     // Catch:{ NumberFormatException -> 0x00ed }
            r8 = r1
            r4 = r8
        L_0x006c:
            if (r0 != r2) goto L_0x0071
            if (r8 != 0) goto L_0x0073
            return r1
        L_0x0071:
            if (r3 == 0) goto L_0x00ed
        L_0x0073:
            r2 = -1
            r5 = 0
            r6 = 1
            switch(r0) {
                case 1: goto L_0x00e1;
                case 2: goto L_0x00d5;
                case 3: goto L_0x008c;
                case 4: goto L_0x007a;
                default: goto L_0x0079;
            }
        L_0x0079:
            goto L_0x00ed
        L_0x007a:
            int r8 = r7.compareTo(r8)
            if (r8 == r2) goto L_0x0087
            int r7 = r7.compareTo(r4)
            if (r7 == r6) goto L_0x0087
            r5 = 1
        L_0x0087:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x008c:
            r0 = 0
            int r8 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r8 == 0) goto L_0x00c9
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r0 = new java.math.BigDecimal
            r1 = 2
            r0.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r0)
            java.math.BigDecimal r8 = r3.subtract(r8)
            int r8 = r7.compareTo(r8)
            if (r8 != r6) goto L_0x00c4
            java.math.BigDecimal r8 = new java.math.BigDecimal
            r8.<init>(r9)
            java.math.BigDecimal r9 = new java.math.BigDecimal
            r9.<init>(r1)
            java.math.BigDecimal r8 = r8.multiply(r9)
            java.math.BigDecimal r8 = r3.add(r8)
            int r7 = r7.compareTo(r8)
            if (r7 != r2) goto L_0x00c4
            r5 = 1
        L_0x00c4:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00c9:
            int r7 = r7.compareTo(r3)
            if (r7 != 0) goto L_0x00d0
            r5 = 1
        L_0x00d0:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00d5:
            int r7 = r7.compareTo(r3)
            if (r7 != r6) goto L_0x00dc
            r5 = 1
        L_0x00dc:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00e1:
            int r7 = r7.compareTo(r3)
            if (r7 != r2) goto L_0x00e8
            r5 = 1
        L_0x00e8:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r5)
            return r7
        L_0x00ed:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeb.zza(java.math.BigDecimal, com.google.android.gms.internal.measurement.zzkg, double):java.lang.Boolean");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v63, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v30, resolved type: java.lang.Long} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v67, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: java.lang.String} */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0564, code lost:
        r1.zze(r2, r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x05bb, code lost:
        r3 = zzga().zzbj(r0);
        r4 = zzga().zzbk(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x0684, code lost:
        r1 = java.lang.Boolean.valueOf(r42);
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x02f7  */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0348  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x036e  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x04e5  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x051e  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x06a5  */
    /* JADX WARNING: Removed duplicated region for block: B:215:0x06a8  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x06ae  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x06b6  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0969  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x096c  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x0972  */
    /* JADX WARNING: Removed duplicated region for block: B:299:0x097b  */
    /* JADX WARNING: Removed duplicated region for block: B:365:0x056e A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0293  */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.measurement.zzkm[] zza(java.lang.String r63, com.google.android.gms.internal.measurement.zzkn[] r64, com.google.android.gms.internal.measurement.zzks[] r65) {
        /*
            r62 = this;
            r1 = r62
            r15 = r63
            r14 = r64
            r13 = r65
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r63)
            java.util.HashSet r11 = new java.util.HashSet
            r11.<init>()
            android.support.v4.util.ArrayMap r12 = new android.support.v4.util.ArrayMap
            r12.<init>()
            android.support.v4.util.ArrayMap r9 = new android.support.v4.util.ArrayMap
            r9.<init>()
            android.support.v4.util.ArrayMap r10 = new android.support.v4.util.ArrayMap
            r10.<init>()
            com.google.android.gms.internal.measurement.zzei r0 = r62.zzix()
            java.util.Map r0 = r0.zzbf(r15)
            if (r0 == 0) goto L_0x00fa
            java.util.Set r2 = r0.keySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x0031:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x00fa
            java.lang.Object r3 = r2.next()
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.measurement.zzkr r4 = (com.google.android.gms.internal.measurement.zzkr) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r3)
            java.lang.Object r5 = r9.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            java.lang.Object r6 = r10.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r5 != 0) goto L_0x0079
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r9.put(r6, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            r10.put(r7, r6)
        L_0x0079:
            r7 = 0
        L_0x007a:
            long[] r8 = r4.zzauk
            int r8 = r8.length
            int r8 = r8 << 6
            if (r7 >= r8) goto L_0x00c2
            long[] r8 = r4.zzauk
            boolean r8 = com.google.android.gms.internal.measurement.zzka.zza((long[]) r8, (int) r7)
            if (r8 == 0) goto L_0x00b3
            com.google.android.gms.internal.measurement.zzfg r8 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzit()
            r17 = r0
            java.lang.String r0 = "Filter already evaluated. audience ID, filter ID"
            r18 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r19 = r9
            java.lang.Integer r9 = java.lang.Integer.valueOf(r7)
            r8.zze(r0, r2, r9)
            r6.set(r7)
            long[] r0 = r4.zzaul
            boolean r0 = com.google.android.gms.internal.measurement.zzka.zza((long[]) r0, (int) r7)
            if (r0 == 0) goto L_0x00b9
            r5.set(r7)
            goto L_0x00b9
        L_0x00b3:
            r17 = r0
            r18 = r2
            r19 = r9
        L_0x00b9:
            int r7 = r7 + 1
            r0 = r17
            r2 = r18
            r9 = r19
            goto L_0x007a
        L_0x00c2:
            r17 = r0
            r18 = r2
            r19 = r9
            com.google.android.gms.internal.measurement.zzkm r0 = new com.google.android.gms.internal.measurement.zzkm
            r0.<init>()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            r12.put(r2, r0)
            r2 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)
            r0.zzasy = r3
            r0.zzasx = r4
            com.google.android.gms.internal.measurement.zzkr r2 = new com.google.android.gms.internal.measurement.zzkr
            r2.<init>()
            r0.zzasw = r2
            com.google.android.gms.internal.measurement.zzkr r2 = r0.zzasw
            long[] r3 = com.google.android.gms.internal.measurement.zzka.zza((java.util.BitSet) r5)
            r2.zzaul = r3
            com.google.android.gms.internal.measurement.zzkr r0 = r0.zzasw
            long[] r2 = com.google.android.gms.internal.measurement.zzka.zza((java.util.BitSet) r6)
            r0.zzauk = r2
            r0 = r17
            r2 = r18
            goto L_0x0031
        L_0x00fa:
            r19 = r9
            if (r14 == 0) goto L_0x0734
            android.support.v4.util.ArrayMap r6 = new android.support.v4.util.ArrayMap
            r6.<init>()
            int r4 = r14.length
            r17 = 0
            r20 = r17
            r0 = 0
            r2 = 0
            r3 = 0
        L_0x010b:
            if (r3 >= r4) goto L_0x0734
            r8 = r14[r3]
            java.lang.String r9 = r8.name
            com.google.android.gms.internal.measurement.zzko[] r7 = r8.zzata
            com.google.android.gms.internal.measurement.zzef r5 = r62.zzgg()
            r24 = r3
            com.google.android.gms.internal.measurement.zzex<java.lang.Boolean> r3 = com.google.android.gms.internal.measurement.zzew.zzahv
            boolean r3 = r5.zzd(r15, r3)
            if (r3 == 0) goto L_0x02da
            r62.zzgb()
            java.lang.String r3 = "_eid"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzka.zzb(r8, r3)
            r5 = r3
            java.lang.Long r5 = (java.lang.Long) r5
            if (r5 == 0) goto L_0x0131
            r3 = 1
            goto L_0x0132
        L_0x0131:
            r3 = 0
        L_0x0132:
            if (r3 == 0) goto L_0x0140
            r25 = r4
            java.lang.String r4 = "_ep"
            boolean r4 = r9.equals(r4)
            if (r4 == 0) goto L_0x0142
            r4 = 1
            goto L_0x0143
        L_0x0140:
            r25 = r4
        L_0x0142:
            r4 = 0
        L_0x0143:
            if (r4 == 0) goto L_0x0293
            r62.zzgb()
            java.lang.String r3 = "_en"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzka.zzb(r8, r3)
            r9 = r3
            java.lang.String r9 = (java.lang.String) r9
            boolean r3 = android.text.TextUtils.isEmpty(r9)
            if (r3 == 0) goto L_0x016e
            com.google.android.gms.internal.measurement.zzfg r3 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()
            java.lang.String r4 = "Extra parameter without an event name. eventId"
            r3.zzg(r4, r5)
            r31 = r6
            r32 = r10
            r16 = 0
            r22 = 1
            goto L_0x0285
        L_0x016e:
            if (r0 == 0) goto L_0x0183
            if (r2 == 0) goto L_0x0183
            long r3 = r5.longValue()
            long r26 = r2.longValue()
            int r28 = (r3 > r26 ? 1 : (r3 == r26 ? 0 : -1))
            if (r28 == 0) goto L_0x017f
            goto L_0x0183
        L_0x017f:
            r4 = r0
            r26 = r2
            goto L_0x01ab
        L_0x0183:
            com.google.android.gms.internal.measurement.zzei r3 = r62.zzix()
            android.util.Pair r3 = r3.zza((java.lang.String) r15, (java.lang.Long) r5)
            if (r3 == 0) goto L_0x0270
            java.lang.Object r4 = r3.first
            if (r4 != 0) goto L_0x0193
            goto L_0x0270
        L_0x0193:
            java.lang.Object r0 = r3.first
            com.google.android.gms.internal.measurement.zzkn r0 = (com.google.android.gms.internal.measurement.zzkn) r0
            java.lang.Object r2 = r3.second
            java.lang.Long r2 = (java.lang.Long) r2
            long r20 = r2.longValue()
            r62.zzgb()
            java.lang.String r2 = "_eid"
            java.lang.Object r2 = com.google.android.gms.internal.measurement.zzka.zzb(r0, r2)
            java.lang.Long r2 = (java.lang.Long) r2
            goto L_0x017f
        L_0x01ab:
            r2 = 1
            long r20 = r20 - r2
            int r0 = (r20 > r17 ? 1 : (r20 == r17 ? 0 : -1))
            if (r0 > 0) goto L_0x01fb
            com.google.android.gms.internal.measurement.zzei r2 = r62.zzix()
            r2.zzab()
            com.google.android.gms.internal.measurement.zzfg r0 = r2.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzit()
            java.lang.String r3 = "Clearing complex main event info. appId"
            r0.zzg(r3, r15)
            android.database.sqlite.SQLiteDatabase r0 = r2.getWritableDatabase()     // Catch:{ SQLiteException -> 0x01de }
            java.lang.String r3 = "delete from main_event_params where app_id=?"
            r29 = r4
            r5 = 1
            java.lang.String[] r4 = new java.lang.String[r5]     // Catch:{ SQLiteException -> 0x01dc }
            r16 = 0
            r4[r16] = r15     // Catch:{ SQLiteException -> 0x01da }
            r0.execSQL(r3, r4)     // Catch:{ SQLiteException -> 0x01da }
            goto L_0x01f1
        L_0x01da:
            r0 = move-exception
            goto L_0x01e4
        L_0x01dc:
            r0 = move-exception
            goto L_0x01e2
        L_0x01de:
            r0 = move-exception
            r29 = r4
            r5 = 1
        L_0x01e2:
            r16 = 0
        L_0x01e4:
            com.google.android.gms.internal.measurement.zzfg r2 = r2.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzim()
            java.lang.String r3 = "Error clearing complex main event"
            r2.zzg(r3, r0)
        L_0x01f1:
            r31 = r6
            r32 = r10
            r2 = r29
            r22 = 1
            r10 = r7
            goto L_0x0218
        L_0x01fb:
            r29 = r4
            r16 = 0
            r22 = 1
            com.google.android.gms.internal.measurement.zzei r2 = r62.zzix()
            r3 = r63
            r30 = r29
            r4 = r5
            r31 = r6
            r5 = r20
            r32 = r10
            r10 = r7
            r7 = r30
            r2.zza(r3, r4, r5, r7)
            r2 = r30
        L_0x0218:
            com.google.android.gms.internal.measurement.zzko[] r0 = r2.zzata
            int r0 = r0.length
            int r3 = r10.length
            int r0 = r0 + r3
            com.google.android.gms.internal.measurement.zzko[] r0 = new com.google.android.gms.internal.measurement.zzko[r0]
            com.google.android.gms.internal.measurement.zzko[] r3 = r2.zzata
            int r4 = r3.length
            r5 = 0
            r6 = 0
        L_0x0224:
            if (r5 >= r4) goto L_0x023f
            r7 = r3[r5]
            r62.zzgb()
            r33 = r2
            java.lang.String r2 = r7.name
            com.google.android.gms.internal.measurement.zzko r2 = com.google.android.gms.internal.measurement.zzka.zza((com.google.android.gms.internal.measurement.zzkn) r8, (java.lang.String) r2)
            if (r2 != 0) goto L_0x023a
            int r2 = r6 + 1
            r0[r6] = r7
            r6 = r2
        L_0x023a:
            int r5 = r5 + 1
            r2 = r33
            goto L_0x0224
        L_0x023f:
            r33 = r2
            if (r6 <= 0) goto L_0x0261
            int r2 = r10.length
            r3 = 0
        L_0x0245:
            if (r3 >= r2) goto L_0x0251
            r4 = r10[r3]
            int r5 = r6 + 1
            r0[r6] = r4
            int r3 = r3 + 1
            r6 = r5
            goto L_0x0245
        L_0x0251:
            int r2 = r0.length
            if (r6 != r2) goto L_0x0256
        L_0x0254:
            r7 = r0
            goto L_0x025d
        L_0x0256:
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r0, r6)
            com.google.android.gms.internal.measurement.zzko[] r0 = (com.google.android.gms.internal.measurement.zzko[]) r0
            goto L_0x0254
        L_0x025d:
            r0 = r9
            r9 = r7
            goto L_0x02eb
        L_0x0261:
            com.google.android.gms.internal.measurement.zzfg r0 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r2 = "No unique parameters in main event. eventName"
            r0.zzg(r2, r9)
            goto L_0x02e9
        L_0x0270:
            r31 = r6
            r32 = r10
            r16 = 0
            r22 = 1
            com.google.android.gms.internal.measurement.zzfg r3 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()
            java.lang.String r4 = "Extra parameter without existing main event. eventName, eventId"
            r3.zze(r4, r9, r5)
        L_0x0285:
            r5 = r1
            r9 = r11
            r10 = r12
            r6 = r15
            r12 = r19
            r45 = r31
            r49 = r32
            r42 = 0
            goto L_0x0720
        L_0x0293:
            r31 = r6
            r32 = r10
            r16 = 0
            r22 = 1
            r10 = r7
            if (r3 == 0) goto L_0x02e5
            r62.zzgb()
            java.lang.String r0 = "_epc"
            java.lang.Long r2 = java.lang.Long.valueOf(r17)
            java.lang.Object r0 = com.google.android.gms.internal.measurement.zzka.zzb(r8, r0)
            if (r0 != 0) goto L_0x02ae
            r0 = r2
        L_0x02ae:
            java.lang.Long r0 = (java.lang.Long) r0
            long r20 = r0.longValue()
            int r0 = (r20 > r17 ? 1 : (r20 == r17 ? 0 : -1))
            if (r0 > 0) goto L_0x02c7
            com.google.android.gms.internal.measurement.zzfg r0 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzip()
            java.lang.String r2 = "Complex event with zero extra param count. eventName"
            r0.zzg(r2, r9)
            r0 = r5
            goto L_0x02d5
        L_0x02c7:
            com.google.android.gms.internal.measurement.zzei r2 = r62.zzix()
            r3 = r63
            r4 = r5
            r0 = r5
            r5 = r20
            r7 = r8
            r2.zza(r3, r4, r5, r7)
        L_0x02d5:
            r26 = r0
            r33 = r8
            goto L_0x02e9
        L_0x02da:
            r25 = r4
            r31 = r6
            r32 = r10
            r16 = 0
            r22 = 1
            r10 = r7
        L_0x02e5:
            r33 = r0
            r26 = r2
        L_0x02e9:
            r0 = r9
            r9 = r10
        L_0x02eb:
            com.google.android.gms.internal.measurement.zzei r2 = r62.zzix()
            java.lang.String r3 = r8.name
            com.google.android.gms.internal.measurement.zzeq r2 = r2.zzf(r15, r3)
            if (r2 != 0) goto L_0x0348
            com.google.android.gms.internal.measurement.zzfg r2 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzip()
            java.lang.String r3 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r4 = com.google.android.gms.internal.measurement.zzfg.zzbm(r63)
            com.google.android.gms.internal.measurement.zzfe r5 = r62.zzga()
            java.lang.String r5 = r5.zzbj(r0)
            r2.zze(r3, r4, r5)
            com.google.android.gms.internal.measurement.zzeq r23 = new com.google.android.gms.internal.measurement.zzeq
            java.lang.String r4 = r8.name
            r5 = 1
            r27 = 1
            java.lang.Long r2 = r8.zzatb
            long r29 = r2.longValue()
            r34 = 0
            r36 = 0
            r37 = 0
            r38 = 0
            r2 = r23
            r3 = r63
            r10 = 0
            r7 = r27
            r41 = r9
            r39 = r19
            r40 = r32
            r42 = 0
            r9 = r29
            r43 = r11
            r44 = r12
            r11 = r34
            r13 = r36
            r14 = r37
            r1 = r15
            r15 = r38
            r2.<init>(r3, r4, r5, r7, r9, r11, r13, r14, r15)
            goto L_0x035b
        L_0x0348:
            r41 = r9
            r43 = r11
            r44 = r12
            r1 = r15
            r39 = r19
            r40 = r32
            r42 = 0
            com.google.android.gms.internal.measurement.zzeq r23 = r2.zzie()
            r2 = r23
        L_0x035b:
            com.google.android.gms.internal.measurement.zzei r3 = r62.zzix()
            r3.zza((com.google.android.gms.internal.measurement.zzeq) r2)
            long r2 = r2.zzafr
            r4 = r31
            java.lang.Object r5 = r4.get(r0)
            java.util.Map r5 = (java.util.Map) r5
            if (r5 != 0) goto L_0x0380
            com.google.android.gms.internal.measurement.zzei r5 = r62.zzix()
            java.util.Map r5 = r5.zzk(r1, r0)
            if (r5 != 0) goto L_0x037d
            android.support.v4.util.ArrayMap r5 = new android.support.v4.util.ArrayMap
            r5.<init>()
        L_0x037d:
            r4.put(r0, r5)
        L_0x0380:
            java.util.Set r6 = r5.keySet()
            java.util.Iterator r6 = r6.iterator()
        L_0x0388:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x070f
            java.lang.Object r7 = r6.next()
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            r9 = r43
            boolean r8 = r9.contains(r8)
            if (r8 == 0) goto L_0x03b8
            com.google.android.gms.internal.measurement.zzfg r8 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r8 = r8.zzit()
            java.lang.String r10 = "Skipping failed audience ID"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8.zzg(r10, r7)
            r43 = r9
            goto L_0x0388
        L_0x03b8:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            r10 = r44
            java.lang.Object r8 = r10.get(r8)
            com.google.android.gms.internal.measurement.zzkm r8 = (com.google.android.gms.internal.measurement.zzkm) r8
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            r12 = r39
            java.lang.Object r11 = r12.get(r11)
            java.util.BitSet r11 = (java.util.BitSet) r11
            java.lang.Integer r13 = java.lang.Integer.valueOf(r7)
            r14 = r40
            java.lang.Object r13 = r14.get(r13)
            java.util.BitSet r13 = (java.util.BitSet) r13
            if (r8 != 0) goto L_0x0408
            com.google.android.gms.internal.measurement.zzkm r8 = new com.google.android.gms.internal.measurement.zzkm
            r8.<init>()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r7)
            r10.put(r11, r8)
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r22)
            r8.zzasy = r11
            java.util.BitSet r11 = new java.util.BitSet
            r11.<init>()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            r12.put(r8, r11)
            java.util.BitSet r13 = new java.util.BitSet
            r13.<init>()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            r14.put(r8, r13)
        L_0x0408:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            java.lang.Object r8 = r5.get(r8)
            java.util.List r8 = (java.util.List) r8
            java.util.Iterator r8 = r8.iterator()
        L_0x0416:
            boolean r15 = r8.hasNext()
            if (r15 == 0) goto L_0x06ff
            java.lang.Object r15 = r8.next()
            com.google.android.gms.internal.measurement.zzke r15 = (com.google.android.gms.internal.measurement.zzke) r15
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            r45 = r4
            r4 = 2
            boolean r1 = r1.isLoggable(r4)
            if (r1 == 0) goto L_0x046a
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r4 = "Evaluating filter. audience, filter, event"
            r46 = r5
            java.lang.Integer r5 = java.lang.Integer.valueOf(r7)
            r47 = r6
            java.lang.Integer r6 = r15.zzarp
            r48 = r8
            com.google.android.gms.internal.measurement.zzfe r8 = r62.zzga()
            r49 = r14
            java.lang.String r14 = r15.zzarq
            java.lang.String r8 = r8.zzbj(r14)
            r1.zzd(r4, r5, r6, r8)
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r4 = "Filter definition"
            com.google.android.gms.internal.measurement.zzfe r5 = r62.zzga()
            java.lang.String r5 = r5.zza((com.google.android.gms.internal.measurement.zzke) r15)
            r1.zzg(r4, r5)
            goto L_0x0472
        L_0x046a:
            r46 = r5
            r47 = r6
            r48 = r8
            r49 = r14
        L_0x0472:
            java.lang.Integer r1 = r15.zzarp
            if (r1 == 0) goto L_0x06cf
            java.lang.Integer r1 = r15.zzarp
            int r1 = r1.intValue()
            r4 = 256(0x100, float:3.59E-43)
            if (r1 <= r4) goto L_0x0482
            goto L_0x06cf
        L_0x0482:
            java.lang.Integer r1 = r15.zzarp
            int r1 = r1.intValue()
            boolean r1 = r11.get(r1)
            if (r1 == 0) goto L_0x04af
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r5 = "Event filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)
            java.lang.Integer r8 = r15.zzarp
            r1.zze(r5, r6, r8)
            r4 = r45
            r5 = r46
            r6 = r47
            r8 = r48
            r14 = r49
            r1 = r63
            goto L_0x0416
        L_0x04af:
            com.google.android.gms.internal.measurement.zzkg r1 = r15.zzart
            if (r1 == 0) goto L_0x04d6
            com.google.android.gms.internal.measurement.zzkg r1 = r15.zzart
            r5 = r62
            r6 = r63
            java.lang.Boolean r1 = r5.zza((long) r2, (com.google.android.gms.internal.measurement.zzkg) r1)
            if (r1 != 0) goto L_0x04c6
            r50 = r2
        L_0x04c1:
            r52 = r41
        L_0x04c3:
            r1 = 0
            goto L_0x0699
        L_0x04c6:
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L_0x04da
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r42)
            r50 = r2
            r52 = r41
            goto L_0x0699
        L_0x04d6:
            r5 = r62
            r6 = r63
        L_0x04da:
            java.util.HashSet r1 = new java.util.HashSet
            r1.<init>()
            com.google.android.gms.internal.measurement.zzkf[] r8 = r15.zzarr
            int r14 = r8.length
            r4 = 0
        L_0x04e3:
            if (r4 >= r14) goto L_0x0511
            r50 = r2
            r2 = r8[r4]
            java.lang.String r3 = r2.zzary
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 == 0) goto L_0x0507
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "null or empty param name in filter. event"
            com.google.android.gms.internal.measurement.zzfe r3 = r62.zzga()
            java.lang.String r3 = r3.zzbj(r0)
            r1.zzg(r2, r3)
            goto L_0x04c1
        L_0x0507:
            java.lang.String r2 = r2.zzary
            r1.add(r2)
            int r4 = r4 + 1
            r2 = r50
            goto L_0x04e3
        L_0x0511:
            r50 = r2
            android.support.v4.util.ArrayMap r2 = new android.support.v4.util.ArrayMap
            r2.<init>()
            r3 = r41
            int r4 = r3.length
            r8 = 0
        L_0x051c:
            if (r8 >= r4) goto L_0x056e
            r14 = r3[r8]
            r52 = r3
            java.lang.String r3 = r14.name
            boolean r3 = r1.contains(r3)
            if (r3 == 0) goto L_0x0569
            java.lang.Long r3 = r14.zzate
            if (r3 == 0) goto L_0x0536
            java.lang.String r3 = r14.name
            java.lang.Long r14 = r14.zzate
        L_0x0532:
            r2.put(r3, r14)
            goto L_0x0569
        L_0x0536:
            java.lang.Double r3 = r14.zzarc
            if (r3 == 0) goto L_0x053f
            java.lang.String r3 = r14.name
            java.lang.Double r14 = r14.zzarc
            goto L_0x0532
        L_0x053f:
            java.lang.String r3 = r14.zzajf
            if (r3 == 0) goto L_0x0548
            java.lang.String r3 = r14.name
            java.lang.String r14 = r14.zzajf
            goto L_0x0532
        L_0x0548:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "Unknown value for param. event, param"
            com.google.android.gms.internal.measurement.zzfe r3 = r62.zzga()
            java.lang.String r3 = r3.zzbj(r0)
            com.google.android.gms.internal.measurement.zzfe r4 = r62.zzga()
            java.lang.String r8 = r14.name
            java.lang.String r4 = r4.zzbk(r8)
        L_0x0564:
            r1.zze(r2, r3, r4)
            goto L_0x04c3
        L_0x0569:
            int r8 = r8 + 1
            r3 = r52
            goto L_0x051c
        L_0x056e:
            r52 = r3
            com.google.android.gms.internal.measurement.zzkf[] r1 = r15.zzarr
            int r3 = r1.length
            r4 = 0
        L_0x0574:
            if (r4 >= r3) goto L_0x0695
            r8 = r1[r4]
            java.lang.Boolean r14 = java.lang.Boolean.TRUE
            r53 = r1
            java.lang.Boolean r1 = r8.zzarx
            boolean r1 = r14.equals(r1)
            java.lang.String r14 = r8.zzary
            boolean r16 = android.text.TextUtils.isEmpty(r14)
            if (r16 == 0) goto L_0x05a1
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "Event has empty param name. event"
            com.google.android.gms.internal.measurement.zzfe r3 = r62.zzga()
            java.lang.String r3 = r3.zzbj(r0)
            r1.zzg(r2, r3)
            goto L_0x04c3
        L_0x05a1:
            r54 = r3
            java.lang.Object r3 = r2.get(r14)
            r55 = r2
            boolean r2 = r3 instanceof java.lang.Long
            if (r2 == 0) goto L_0x05e7
            com.google.android.gms.internal.measurement.zzkg r2 = r8.zzarw
            if (r2 != 0) goto L_0x05cc
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "No number filter for long param. event, param"
        L_0x05bb:
            com.google.android.gms.internal.measurement.zzfe r3 = r62.zzga()
            java.lang.String r3 = r3.zzbj(r0)
            com.google.android.gms.internal.measurement.zzfe r4 = r62.zzga()
            java.lang.String r4 = r4.zzbk(r14)
            goto L_0x0564
        L_0x05cc:
            java.lang.Long r3 = (java.lang.Long) r3
            long r2 = r3.longValue()
            com.google.android.gms.internal.measurement.zzkg r8 = r8.zzarw
            java.lang.Boolean r2 = r5.zza((long) r2, (com.google.android.gms.internal.measurement.zzkg) r8)
            if (r2 != 0) goto L_0x05dc
        L_0x05da:
            goto L_0x04c3
        L_0x05dc:
            boolean r2 = r2.booleanValue()
            r2 = r2 ^ 1
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x0643
        L_0x05e5:
            goto L_0x0684
        L_0x05e7:
            boolean r2 = r3 instanceof java.lang.Double
            if (r2 == 0) goto L_0x0613
            com.google.android.gms.internal.measurement.zzkg r2 = r8.zzarw
            if (r2 != 0) goto L_0x05fa
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "No number filter for double param. event, param"
            goto L_0x05bb
        L_0x05fa:
            java.lang.Double r3 = (java.lang.Double) r3
            double r2 = r3.doubleValue()
            com.google.android.gms.internal.measurement.zzkg r8 = r8.zzarw
            java.lang.Boolean r2 = r5.zza((double) r2, (com.google.android.gms.internal.measurement.zzkg) r8)
            if (r2 != 0) goto L_0x0609
            goto L_0x05da
        L_0x0609:
            boolean r2 = r2.booleanValue()
            r2 = r2 ^ 1
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x0643
            goto L_0x05e5
        L_0x0613:
            boolean r2 = r3 instanceof java.lang.String
            if (r2 == 0) goto L_0x0665
            com.google.android.gms.internal.measurement.zzki r2 = r8.zzarv
            if (r2 == 0) goto L_0x0624
            java.lang.String r3 = (java.lang.String) r3
            com.google.android.gms.internal.measurement.zzki r2 = r8.zzarv
            java.lang.Boolean r2 = r5.zza((java.lang.String) r3, (com.google.android.gms.internal.measurement.zzki) r2)
            goto L_0x0636
        L_0x0624:
            com.google.android.gms.internal.measurement.zzkg r2 = r8.zzarw
            if (r2 == 0) goto L_0x0659
            java.lang.String r3 = (java.lang.String) r3
            boolean r2 = com.google.android.gms.internal.measurement.zzka.zzck(r3)
            if (r2 == 0) goto L_0x064d
            com.google.android.gms.internal.measurement.zzkg r2 = r8.zzarw
            java.lang.Boolean r2 = r5.zza((java.lang.String) r3, (com.google.android.gms.internal.measurement.zzkg) r2)
        L_0x0636:
            if (r2 != 0) goto L_0x0639
            goto L_0x05da
        L_0x0639:
            boolean r2 = r2.booleanValue()
            r2 = r2 ^ 1
            r1 = r1 ^ r2
            if (r1 == 0) goto L_0x0643
            goto L_0x05e5
        L_0x0643:
            int r4 = r4 + 1
            r1 = r53
            r3 = r54
            r2 = r55
            goto L_0x0574
        L_0x064d:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "Invalid param value for number filter. event, param"
            goto L_0x05bb
        L_0x0659:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "No filter for String param. event, param"
            goto L_0x05bb
        L_0x0665:
            if (r3 != 0) goto L_0x0689
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "Missing param for filter. event, param"
            com.google.android.gms.internal.measurement.zzfe r3 = r62.zzga()
            java.lang.String r3 = r3.zzbj(r0)
            com.google.android.gms.internal.measurement.zzfe r4 = r62.zzga()
            java.lang.String r4 = r4.zzbk(r14)
            r1.zze(r2, r3, r4)
        L_0x0684:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r42)
            goto L_0x0699
        L_0x0689:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "Unknown param type. event, param"
            goto L_0x05bb
        L_0x0695:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r22)
        L_0x0699:
            com.google.android.gms.internal.measurement.zzfg r2 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzit()
            java.lang.String r3 = "Event filter result"
            if (r1 != 0) goto L_0x06a8
            java.lang.String r4 = "null"
            goto L_0x06a9
        L_0x06a8:
            r4 = r1
        L_0x06a9:
            r2.zzg(r3, r4)
            if (r1 != 0) goto L_0x06b6
            java.lang.Integer r1 = java.lang.Integer.valueOf(r7)
            r9.add(r1)
            goto L_0x06ee
        L_0x06b6:
            java.lang.Integer r2 = r15.zzarp
            int r2 = r2.intValue()
            r13.set(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x06ee
            java.lang.Integer r1 = r15.zzarp
            int r1 = r1.intValue()
            r11.set(r1)
            goto L_0x06ee
        L_0x06cf:
            r50 = r2
            r52 = r41
            r5 = r62
            r6 = r63
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r2 = "Invalid event filter ID. appId, id"
            java.lang.Object r3 = com.google.android.gms.internal.measurement.zzfg.zzbm(r63)
            java.lang.Integer r4 = r15.zzarp
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r1.zze(r2, r3, r4)
        L_0x06ee:
            r1 = r6
            r4 = r45
            r5 = r46
            r6 = r47
            r8 = r48
            r14 = r49
            r2 = r50
            r41 = r52
            goto L_0x0416
        L_0x06ff:
            r46 = r5
            r5 = r62
            r43 = r9
            r44 = r10
            r39 = r12
            r40 = r14
            r5 = r46
            goto L_0x0388
        L_0x070f:
            r6 = r1
            r45 = r4
            r12 = r39
            r49 = r40
            r9 = r43
            r10 = r44
            r5 = r62
            r2 = r26
            r0 = r33
        L_0x0720:
            int r3 = r24 + 1
            r14 = r64
            r1 = r5
            r15 = r6
            r11 = r9
            r19 = r12
            r4 = r25
            r6 = r45
            r13 = r65
            r12 = r10
            r10 = r49
            goto L_0x010b
        L_0x0734:
            r5 = r1
            r49 = r10
            r9 = r11
            r10 = r12
            r6 = r15
            r12 = r19
            r22 = 1
            r42 = 0
            r1 = r65
            if (r1 == 0) goto L_0x09dc
            android.support.v4.util.ArrayMap r0 = new android.support.v4.util.ArrayMap
            r0.<init>()
            int r2 = r1.length
            r3 = 0
        L_0x074b:
            if (r3 >= r2) goto L_0x09dc
            r4 = r1[r3]
            java.lang.String r7 = r4.name
            java.lang.Object r7 = r0.get(r7)
            java.util.Map r7 = (java.util.Map) r7
            if (r7 != 0) goto L_0x076f
            com.google.android.gms.internal.measurement.zzei r7 = r62.zzix()
            java.lang.String r8 = r4.name
            java.util.Map r7 = r7.zzl(r6, r8)
            if (r7 != 0) goto L_0x076a
            android.support.v4.util.ArrayMap r7 = new android.support.v4.util.ArrayMap
            r7.<init>()
        L_0x076a:
            java.lang.String r8 = r4.name
            r0.put(r8, r7)
        L_0x076f:
            java.util.Set r8 = r7.keySet()
            java.util.Iterator r8 = r8.iterator()
        L_0x0777:
            boolean r11 = r8.hasNext()
            if (r11 == 0) goto L_0x09ca
            java.lang.Object r11 = r8.next()
            java.lang.Integer r11 = (java.lang.Integer) r11
            int r11 = r11.intValue()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            boolean r13 = r9.contains(r13)
            if (r13 == 0) goto L_0x07a3
            com.google.android.gms.internal.measurement.zzfg r13 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r13 = r13.zzit()
            java.lang.String r14 = "Skipping failed audience ID"
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r13.zzg(r14, r11)
            goto L_0x0777
        L_0x07a3:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            java.lang.Object r13 = r10.get(r13)
            com.google.android.gms.internal.measurement.zzkm r13 = (com.google.android.gms.internal.measurement.zzkm) r13
            java.lang.Integer r14 = java.lang.Integer.valueOf(r11)
            java.lang.Object r14 = r12.get(r14)
            java.util.BitSet r14 = (java.util.BitSet) r14
            java.lang.Integer r15 = java.lang.Integer.valueOf(r11)
            r1 = r49
            java.lang.Object r15 = r1.get(r15)
            java.util.BitSet r15 = (java.util.BitSet) r15
            if (r13 != 0) goto L_0x07ef
            com.google.android.gms.internal.measurement.zzkm r13 = new com.google.android.gms.internal.measurement.zzkm
            r13.<init>()
            java.lang.Integer r14 = java.lang.Integer.valueOf(r11)
            r10.put(r14, r13)
            java.lang.Boolean r14 = java.lang.Boolean.valueOf(r22)
            r13.zzasy = r14
            java.util.BitSet r14 = new java.util.BitSet
            r14.<init>()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            r12.put(r13, r14)
            java.util.BitSet r15 = new java.util.BitSet
            r15.<init>()
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            r1.put(r13, r15)
        L_0x07ef:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
            java.lang.Object r13 = r7.get(r13)
            java.util.List r13 = (java.util.List) r13
            java.util.Iterator r13 = r13.iterator()
        L_0x07fd:
            boolean r16 = r13.hasNext()
            if (r16 == 0) goto L_0x09c0
            java.lang.Object r16 = r13.next()
            r56 = r0
            r0 = r16
            com.google.android.gms.internal.measurement.zzkh r0 = (com.google.android.gms.internal.measurement.zzkh) r0
            r57 = r2
            com.google.android.gms.internal.measurement.zzfg r2 = r62.zzge()
            r58 = r7
            r7 = 2
            boolean r2 = r2.isLoggable(r7)
            if (r2 == 0) goto L_0x0855
            com.google.android.gms.internal.measurement.zzfg r2 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r2 = r2.zzit()
            java.lang.String r7 = "Evaluating filter. audience, filter, property"
            r59 = r8
            java.lang.Integer r8 = java.lang.Integer.valueOf(r11)
            r60 = r13
            java.lang.Integer r13 = r0.zzarp
            com.google.android.gms.internal.measurement.zzfe r6 = r62.zzga()
            r61 = r1
            java.lang.String r1 = r0.zzasf
            java.lang.String r1 = r6.zzbl(r1)
            r2.zzd(r7, r8, r13, r1)
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r2 = "Filter definition"
            com.google.android.gms.internal.measurement.zzfe r6 = r62.zzga()
            java.lang.String r6 = r6.zza((com.google.android.gms.internal.measurement.zzkh) r0)
            r1.zzg(r2, r6)
            goto L_0x085b
        L_0x0855:
            r61 = r1
            r59 = r8
            r60 = r13
        L_0x085b:
            java.lang.Integer r1 = r0.zzarp
            if (r1 == 0) goto L_0x0995
            java.lang.Integer r1 = r0.zzarp
            int r1 = r1.intValue()
            r2 = 256(0x100, float:3.59E-43)
            if (r1 <= r2) goto L_0x086b
            goto L_0x0997
        L_0x086b:
            java.lang.Integer r1 = r0.zzarp
            int r1 = r1.intValue()
            boolean r1 = r14.get(r1)
            if (r1 == 0) goto L_0x089a
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzit()
            java.lang.String r6 = "Property filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r11)
            java.lang.Integer r0 = r0.zzarp
            r1.zze(r6, r7, r0)
        L_0x088a:
            r0 = r56
            r2 = r57
            r7 = r58
            r8 = r59
            r13 = r60
            r1 = r61
            r6 = r63
            goto L_0x07fd
        L_0x089a:
            com.google.android.gms.internal.measurement.zzkf r1 = r0.zzasg
            if (r1 != 0) goto L_0x08b8
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "Missing property filter. property"
        L_0x08a8:
            com.google.android.gms.internal.measurement.zzfe r7 = r62.zzga()
            java.lang.String r8 = r4.name
            java.lang.String r7 = r7.zzbl(r8)
            r1.zzg(r6, r7)
        L_0x08b5:
            r1 = 0
            goto L_0x095d
        L_0x08b8:
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            java.lang.Boolean r7 = r1.zzarx
            boolean r6 = r6.equals(r7)
            java.lang.Long r7 = r4.zzate
            if (r7 == 0) goto L_0x08e5
            com.google.android.gms.internal.measurement.zzkg r7 = r1.zzarw
            if (r7 != 0) goto L_0x08d3
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "No number filter for long property. property"
            goto L_0x08a8
        L_0x08d3:
            java.lang.Long r7 = r4.zzate
            long r7 = r7.longValue()
            com.google.android.gms.internal.measurement.zzkg r1 = r1.zzarw
            java.lang.Boolean r1 = r5.zza((long) r7, (com.google.android.gms.internal.measurement.zzkg) r1)
        L_0x08df:
            java.lang.Boolean r1 = zza((java.lang.Boolean) r1, (boolean) r6)
            goto L_0x095d
        L_0x08e5:
            java.lang.Double r7 = r4.zzarc
            if (r7 == 0) goto L_0x0905
            com.google.android.gms.internal.measurement.zzkg r7 = r1.zzarw
            if (r7 != 0) goto L_0x08f8
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "No number filter for double property. property"
            goto L_0x08a8
        L_0x08f8:
            java.lang.Double r7 = r4.zzarc
            double r7 = r7.doubleValue()
            com.google.android.gms.internal.measurement.zzkg r1 = r1.zzarw
            java.lang.Boolean r1 = r5.zza((double) r7, (com.google.android.gms.internal.measurement.zzkg) r1)
            goto L_0x08df
        L_0x0905:
            java.lang.String r7 = r4.zzajf
            if (r7 == 0) goto L_0x0951
            com.google.android.gms.internal.measurement.zzki r7 = r1.zzarv
            if (r7 != 0) goto L_0x0948
            com.google.android.gms.internal.measurement.zzkg r7 = r1.zzarw
            if (r7 != 0) goto L_0x091c
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "No string or number filter defined. property"
            goto L_0x08a8
        L_0x091c:
            java.lang.String r7 = r4.zzajf
            boolean r7 = com.google.android.gms.internal.measurement.zzka.zzck(r7)
            if (r7 == 0) goto L_0x092d
            java.lang.String r7 = r4.zzajf
            com.google.android.gms.internal.measurement.zzkg r1 = r1.zzarw
            java.lang.Boolean r1 = r5.zza((java.lang.String) r7, (com.google.android.gms.internal.measurement.zzkg) r1)
            goto L_0x08df
        L_0x092d:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "Invalid user property value for Numeric number filter. property, value"
            com.google.android.gms.internal.measurement.zzfe r7 = r62.zzga()
            java.lang.String r8 = r4.name
            java.lang.String r7 = r7.zzbl(r8)
            java.lang.String r8 = r4.zzajf
            r1.zze(r6, r7, r8)
            goto L_0x08b5
        L_0x0948:
            java.lang.String r7 = r4.zzajf
            com.google.android.gms.internal.measurement.zzki r1 = r1.zzarv
            java.lang.Boolean r1 = r5.zza((java.lang.String) r7, (com.google.android.gms.internal.measurement.zzki) r1)
            goto L_0x08df
        L_0x0951:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "User property has no value, property"
            goto L_0x08a8
        L_0x095d:
            com.google.android.gms.internal.measurement.zzfg r6 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r6 = r6.zzit()
            java.lang.String r7 = "Property filter result"
            if (r1 != 0) goto L_0x096c
            java.lang.String r8 = "null"
            goto L_0x096d
        L_0x096c:
            r8 = r1
        L_0x096d:
            r6.zzg(r7, r8)
            if (r1 != 0) goto L_0x097b
            java.lang.Integer r0 = java.lang.Integer.valueOf(r11)
            r9.add(r0)
            goto L_0x088a
        L_0x097b:
            java.lang.Integer r6 = r0.zzarp
            int r6 = r6.intValue()
            r15.set(r6)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x088a
            java.lang.Integer r0 = r0.zzarp
            int r0 = r0.intValue()
            r14.set(r0)
            goto L_0x088a
        L_0x0995:
            r2 = 256(0x100, float:3.59E-43)
        L_0x0997:
            com.google.android.gms.internal.measurement.zzfg r1 = r62.zzge()
            com.google.android.gms.internal.measurement.zzfi r1 = r1.zzip()
            java.lang.String r6 = "Invalid property filter ID. appId, id"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r63)
            java.lang.Integer r0 = r0.zzarp
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.zze(r6, r7, r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r11)
            r9.add(r0)
            r0 = r56
            r2 = r57
            r7 = r58
            r8 = r59
            r49 = r61
            goto L_0x09c4
        L_0x09c0:
            r57 = r2
            r49 = r1
        L_0x09c4:
            r1 = r65
            r6 = r63
            goto L_0x0777
        L_0x09ca:
            r56 = r0
            r57 = r2
            r61 = r49
            r2 = 256(0x100, float:3.59E-43)
            int r3 = r3 + 1
            r2 = r57
            r1 = r65
            r6 = r63
            goto L_0x074b
        L_0x09dc:
            r61 = r49
            int r0 = r12.size()
            com.google.android.gms.internal.measurement.zzkm[] r1 = new com.google.android.gms.internal.measurement.zzkm[r0]
            java.util.Set r0 = r12.keySet()
            java.util.Iterator r2 = r0.iterator()
            r8 = 0
        L_0x09ed:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L_0x0ae3
            java.lang.Object r0 = r2.next()
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            boolean r3 = r9.contains(r3)
            if (r3 != 0) goto L_0x0adf
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.Object r3 = r10.get(r3)
            com.google.android.gms.internal.measurement.zzkm r3 = (com.google.android.gms.internal.measurement.zzkm) r3
            if (r3 != 0) goto L_0x0a18
            com.google.android.gms.internal.measurement.zzkm r3 = new com.google.android.gms.internal.measurement.zzkm
            r3.<init>()
        L_0x0a18:
            int r4 = r8 + 1
            r1[r8] = r3
            java.lang.Integer r6 = java.lang.Integer.valueOf(r0)
            r3.zzarl = r6
            com.google.android.gms.internal.measurement.zzkr r6 = new com.google.android.gms.internal.measurement.zzkr
            r6.<init>()
            r3.zzasw = r6
            com.google.android.gms.internal.measurement.zzkr r6 = r3.zzasw
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            java.lang.Object r7 = r12.get(r7)
            java.util.BitSet r7 = (java.util.BitSet) r7
            long[] r7 = com.google.android.gms.internal.measurement.zzka.zza((java.util.BitSet) r7)
            r6.zzaul = r7
            com.google.android.gms.internal.measurement.zzkr r6 = r3.zzasw
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            r11 = r61
            java.lang.Object r7 = r11.get(r7)
            java.util.BitSet r7 = (java.util.BitSet) r7
            long[] r7 = com.google.android.gms.internal.measurement.zzka.zza((java.util.BitSet) r7)
            r6.zzauk = r7
            com.google.android.gms.internal.measurement.zzei r6 = r62.zzix()
            com.google.android.gms.internal.measurement.zzkr r3 = r3.zzasw
            r6.zzch()
            r6.zzab()
            com.google.android.gms.common.internal.Preconditions.checkNotEmpty(r63)
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r3)
            int r7 = r3.zzvm()     // Catch:{ IOException -> 0x0ac2 }
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x0ac2 }
            int r8 = r7.length     // Catch:{ IOException -> 0x0ac2 }
            r13 = 0
            com.google.android.gms.internal.measurement.zzabw r8 = com.google.android.gms.internal.measurement.zzabw.zzb(r7, r13, r8)     // Catch:{ IOException -> 0x0ac0 }
            r3.zza(r8)     // Catch:{ IOException -> 0x0ac0 }
            r8.zzve()     // Catch:{ IOException -> 0x0ac0 }
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r8 = "app_id"
            r14 = r63
            r3.put(r8, r14)
            java.lang.String r8 = "audience_id"
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.put(r8, r0)
            java.lang.String r0 = "current_results"
            r3.put(r0, r7)
            android.database.sqlite.SQLiteDatabase r0 = r6.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0ab3 }
            java.lang.String r7 = "audience_filter_values"
            r8 = 5
            r15 = 0
            long r7 = r0.insertWithOnConflict(r7, r15, r3, r8)     // Catch:{ SQLiteException -> 0x0ab1 }
            r16 = -1
            int r0 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x0ad8
            com.google.android.gms.internal.measurement.zzfg r0 = r6.zzge()     // Catch:{ SQLiteException -> 0x0ab1 }
            com.google.android.gms.internal.measurement.zzfi r0 = r0.zzim()     // Catch:{ SQLiteException -> 0x0ab1 }
            java.lang.String r3 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r63)     // Catch:{ SQLiteException -> 0x0ab1 }
            r0.zzg(r3, r7)     // Catch:{ SQLiteException -> 0x0ab1 }
            goto L_0x0ad8
        L_0x0ab1:
            r0 = move-exception
            goto L_0x0ab5
        L_0x0ab3:
            r0 = move-exception
            r15 = 0
        L_0x0ab5:
            com.google.android.gms.internal.measurement.zzfg r3 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()
            java.lang.String r6 = "Error storing filter results. appId"
            goto L_0x0ad1
        L_0x0ac0:
            r0 = move-exception
            goto L_0x0ac4
        L_0x0ac2:
            r0 = move-exception
            r13 = 0
        L_0x0ac4:
            r14 = r63
            r15 = 0
            com.google.android.gms.internal.measurement.zzfg r3 = r6.zzge()
            com.google.android.gms.internal.measurement.zzfi r3 = r3.zzim()
            java.lang.String r6 = "Configuration loss. Failed to serialize filter results. appId"
        L_0x0ad1:
            java.lang.Object r7 = com.google.android.gms.internal.measurement.zzfg.zzbm(r63)
            r3.zze(r6, r7, r0)
        L_0x0ad8:
            r8 = r4
            r61 = r11
            r42 = 0
            goto L_0x09ed
        L_0x0adf:
            r14 = r63
            goto L_0x09ed
        L_0x0ae3:
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r1, r8)
            com.google.android.gms.internal.measurement.zzkm[] r0 = (com.google.android.gms.internal.measurement.zzkm[]) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzeb.zza(java.lang.String, com.google.android.gms.internal.measurement.zzkn[], com.google.android.gms.internal.measurement.zzks[]):com.google.android.gms.internal.measurement.zzkm[]");
    }

    /* access modifiers changed from: protected */
    public final boolean zzhf() {
        return false;
    }
}
