package com.xiaomi.stat.a;

class g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ c f23004a;

    g(c cVar) {
        this.f23004a = cVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:44:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r17 = this;
            r1 = r17
            com.xiaomi.stat.a.c r0 = r1.f23004a     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            com.xiaomi.stat.a.a r0 = r0.l     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            java.util.Calendar r11 = java.util.Calendar.getInstance()     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            long r3 = com.xiaomi.stat.d.r.b()     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r11.setTimeInMillis(r3)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r3 = 6
            int r4 = r11.get(r3)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            int r4 = r4 + -7
            r11.set(r3, r4)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r3 = 11
            r12 = 0
            r11.set(r3, r12)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r3 = 12
            r11.set(r3, r12)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r3 = 13
            r11.set(r3, r12)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            long r3 = r11.getTimeInMillis()     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            java.lang.String r13 = "ts < ? and e != ?"
            r14 = 2
            java.lang.String[] r15 = new java.lang.String[r14]     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            java.lang.String r3 = java.lang.Long.toString(r3)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r15[r12] = r3     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            java.lang.String r3 = "mistat_delete_event"
            r10 = 1
            r15[r10] = r3     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            java.lang.String r4 = "events"
            java.lang.String[] r5 = new java.lang.String[r10]     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            java.lang.String r3 = "ts"
            r5[r12] = r3     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            r8 = 0
            r9 = 0
            java.lang.String r16 = "ts ASC"
            r3 = r0
            r6 = r13
            r7 = r15
            r2 = 1
            r10 = r16
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x0117, all -> 0x0114 }
            int r4 = r3.getCount()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r4 == 0) goto L_0x0109
            com.xiaomi.stat.aj r5 = new com.xiaomi.stat.aj     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r6 = "ca"
            r5.putInt(r6, r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r6 = "EventManager"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r7.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r8 = "delete obsolete events total number "
            r7.append(r8)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r7.append(r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r4 = r7.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.xiaomi.stat.d.k.c(r6, r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r4 = "ts"
            int r4 = r3.getColumnIndex(r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r6 = 0
            r7 = 0
        L_0x0089:
            boolean r8 = r3.moveToNext()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r8 == 0) goto L_0x00e5
            long r8 = r3.getLong(r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r11.setTimeInMillis(r8)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            int r8 = r11.get(r2)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            int r9 = r11.get(r14)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            int r9 = r9 + r2
            r10 = 5
            int r10 = r11.get(r10)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r14 = "%4d%02d%02d"
            r2 = 3
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r2[r12] = r8     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r9 = 1
            r2[r9] = r8     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r10)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r10 = 2
            r2[r10] = r8     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r2 = java.lang.String.format(r14, r2)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            boolean r8 = android.text.TextUtils.equals(r6, r2)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            if (r8 != 0) goto L_0x00e0
            if (r6 == 0) goto L_0x00dd
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r8.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r14 = "c_"
            r8.append(r14)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r8.append(r6)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r6 = r8.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.putInt(r6, r7)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
        L_0x00dd:
            r6 = r2
            r7 = 1
            goto L_0x00e2
        L_0x00e0:
            int r7 = r7 + 1
        L_0x00e2:
            r2 = 1
            r14 = 2
            goto L_0x0089
        L_0x00e5:
            if (r6 == 0) goto L_0x00fb
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r2.<init>()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r4 = "c_"
            r2.append(r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r2.append(r6)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r2 = r2.toString()     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r5.putInt(r2, r7)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
        L_0x00fb:
            com.xiaomi.stat.a.c r2 = r1.f23004a     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            com.xiaomi.stat.a.l r4 = com.xiaomi.stat.a.l.a((com.xiaomi.stat.aj) r5)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            r2.b((com.xiaomi.stat.a.l) r4)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
            java.lang.String r2 = "events"
            r0.delete(r2, r13, r15)     // Catch:{ Exception -> 0x0111, all -> 0x010f }
        L_0x0109:
            if (r3 == 0) goto L_0x0134
            r3.close()
            goto L_0x0134
        L_0x010f:
            r0 = move-exception
            goto L_0x0137
        L_0x0111:
            r0 = move-exception
            r2 = r3
            goto L_0x0119
        L_0x0114:
            r0 = move-exception
            r3 = 0
            goto L_0x0137
        L_0x0117:
            r0 = move-exception
            r2 = 0
        L_0x0119:
            java.lang.String r3 = "EventManager"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0135 }
            r4.<init>()     // Catch:{ all -> 0x0135 }
            java.lang.String r5 = "remove obsolete events failed with "
            r4.append(r5)     // Catch:{ all -> 0x0135 }
            r4.append(r0)     // Catch:{ all -> 0x0135 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0135 }
            com.xiaomi.stat.d.k.c(r3, r0)     // Catch:{ all -> 0x0135 }
            if (r2 == 0) goto L_0x0134
            r2.close()
        L_0x0134:
            return
        L_0x0135:
            r0 = move-exception
            r3 = r2
        L_0x0137:
            if (r3 == 0) goto L_0x013c
            r3.close()
        L_0x013c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.a.g.run():void");
    }
}