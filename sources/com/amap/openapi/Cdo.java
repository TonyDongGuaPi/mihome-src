package com.amap.openapi;

import com.amap.location.common.network.IHttpClient;
import com.amap.location.common.util.b;
import com.coloros.mcssdk.mode.CommandMessage;
import com.loc.fc;
import com.xiaomi.jr.idcardverifier.utils.VerifyConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.amap.openapi.do  reason: invalid class name */
public class Cdo implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private dt f4703a;
    private IHttpClient b;
    private dn c;

    public Cdo(dt dtVar, dn dnVar, IHttpClient iHttpClient) {
        this.f4703a = dtVar;
        this.c = dnVar;
        this.b = iHttpClient;
    }

    private void a() {
        fc fcVar = new fc();
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject.put("time", b.a(currentTimeMillis, (String) null));
            jSONObject.put(CommandMessage.COMMAND, this.c.a());
        } catch (JSONException unused) {
        }
        int a2 = bk.a(fcVar, this.f4703a.a());
        int b2 = dz.b(fcVar, new int[]{dy.a(fcVar, VerifyConstants.ErrorCode.e, dy.a(fcVar, jSONObject.toString().getBytes()), currentTimeMillis)});
        dz.a(fcVar);
        dz.a(fcVar, (byte) 1);
        dz.a(fcVar, a2);
        dz.c(fcVar, b2);
        dz.d(fcVar, dz.b(fcVar));
        ea.a(this.b, this.f4703a.a(2), fcVar.f(), 60000);
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void run() {
        /*
            r35 = this;
            r1 = r35
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r4 = 0
            com.amap.openapi.dt r0 = r1.f4703a     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r0 = r0.c()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5 = -1
            if (r0 != r5) goto L_0x002d
            int r0 = r2.size()
            if (r0 <= 0) goto L_0x002c
        L_0x0017:
            int r0 = r2.size()
            if (r4 >= r0) goto L_0x0029
            java.lang.Object r0 = r2.get(r4)
            android.database.Cursor r0 = (android.database.Cursor) r0
            com.amap.location.common.util.e.a((android.database.Cursor) r0)
            int r4 = r4 + 1
            goto L_0x0017
        L_0x0029:
            r2.clear()
        L_0x002c:
            return
        L_0x002d:
            com.amap.openapi.dt r5 = r1.f4703a     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.location.uptunnel.core.db.DBProvider r5 = r5.b()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            android.database.sqlite.SQLiteDatabase r6 = r5.c()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dn r7 = r1.c     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r7 = r7.b()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            android.net.Uri r13 = com.amap.openapi.dt.b(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dn r7 = r1.c     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r7 = r7.b()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r7 = com.amap.openapi.dt.c(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            long r8 = r5.a(r13)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r14 = 0
            int r10 = (r8 > r14 ? 1 : (r8 == r14 ? 0 : -1))
            if (r10 > 0) goto L_0x0074
            r35.a()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r0 = r2.size()
            if (r0 <= 0) goto L_0x0073
        L_0x005e:
            int r0 = r2.size()
            if (r4 >= r0) goto L_0x0070
            java.lang.Object r0 = r2.get(r4)
            android.database.Cursor r0 = (android.database.Cursor) r0
            com.amap.location.common.util.e.a((android.database.Cursor) r0)
            int r4 = r4 + 1
            goto L_0x005e
        L_0x0070:
            r2.clear()
        L_0x0073:
            return
        L_0x0074:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r9 = "select max(ID) from "
            r8.<init>(r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r8.append(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = r8.toString()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r9 = 0
            android.database.Cursor r8 = r6.rawQuery(r8, r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r2.add(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r8.moveToFirst()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            long r11 = r8.getLong(r4)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.location.common.util.e.a((android.database.Cursor) r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r16 = 400000(0x61a80, double:1.976263E-318)
            r18 = 1
            r20 = -1
            r10 = 1
            if (r0 != r10) goto L_0x00c9
            java.lang.String[] r8 = new java.lang.String[r10]     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = "ID"
            r8[r4] = r0     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r9 = 0
            r0 = 0
            r22 = 0
            java.lang.String r23 = "0,1"
            r6 = r5
            r7 = r13
            r3 = 1
            r10 = r0
            r14 = r11
            r11 = r22
            r12 = r23
            android.database.Cursor r0 = r6.b(r7, r8, r9, r10, r11, r12)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r2.add(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.moveToFirst()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            long r6 = r0.getLong(r4)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.location.common.util.e.a((android.database.Cursor) r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r3 = r6
            r22 = 0
            goto L_0x015e
        L_0x00c9:
            r14 = r11
            r3 = 1
            if (r0 != 0) goto L_0x015a
            long r11 = r14 + r18
            r3 = r11
            r10 = 0
        L_0x00d2:
            int r0 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r0 >= 0) goto L_0x0151
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = "select min(ID) from (select * from "
            r0.<init>(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.append(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = " where id < "
            r0.append(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.append(r3)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = " order by ID desc limit 0, 50)"
            r0.append(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            android.database.Cursor r0 = r6.rawQuery(r0, r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r2.add(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r0 == 0) goto L_0x014b
            boolean r8 = r0.moveToFirst()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r8 == 0) goto L_0x014b
            r26 = r10
            r8 = 0
            long r9 = r0.getLong(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.location.common.util.e.a((android.database.Cursor) r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r11 = 0
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 <= 0) goto L_0x0153
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = "select sum(size) from "
            r0.<init>(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.append(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = " where ID >= "
            r0.append(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.append(r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r8 = " and ID < "
            r0.append(r8)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.append(r3)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = r0.toString()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r3 = 0
            android.database.Cursor r0 = r6.rawQuery(r0, r3)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r2.add(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.moveToFirst()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r4 = 0
            long r11 = r0.getLong(r4)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r4 = 0
            long r11 = r26 + r11
            com.amap.location.common.util.e.a((android.database.Cursor) r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r33 = r9
            r9 = r3
            r3 = r33
            r10 = r11
            goto L_0x00d2
        L_0x014b:
            r26 = r10
            com.amap.location.common.util.e.a((android.database.Cursor) r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            goto L_0x0153
        L_0x0151:
            r26 = r10
        L_0x0153:
            r22 = 0
            int r0 = (r26 > r22 ? 1 : (r26 == r22 ? 0 : -1))
            if (r0 <= 0) goto L_0x015c
            goto L_0x015e
        L_0x015a:
            r22 = 0
        L_0x015c:
            r3 = r20
        L_0x015e:
            int r0 = (r3 > r22 ? 1 : (r3 == r22 ? 0 : -1))
            if (r0 <= 0) goto L_0x028f
            int r0 = (r14 > r3 ? 1 : (r14 == r3 ? 0 : -1))
            if (r0 < 0) goto L_0x028f
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.<init>()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.loc.fc r12 = new com.loc.fc     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r12.<init>()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String[] r8 = com.amap.openapi.dv.f4711a     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r7 = " id >= "
            r6.<init>(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r6.append(r3)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r7 = " and id <= "
            r6.append(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r6.append(r14)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r9 = r6.toString()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r10 = 0
            r11 = 0
            r6 = r5
            r7 = r13
            android.database.Cursor r6 = r6.a(r7, r8, r9, r10, r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r2.add(r6)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r6 == 0) goto L_0x028f
            int r7 = r6.getCount()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r7 == 0) goto L_0x028f
            r9 = r3
            r3 = r20
            r24 = r3
            r7 = r22
        L_0x01a2:
            boolean r11 = r6.moveToNext()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r11 == 0) goto L_0x01f5
            r11 = 0
            long r9 = r6.getLong(r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r24 = 0
            long r9 = r9 + r18
            r28 = r5
            r11 = 1
            int r5 = r6.getInt(r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r11 = 2
            byte[] r11 = r6.getBlob(r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r29 = r9
            r9 = 3
            long r9 = r6.getLong(r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r24 = (r3 > r20 ? 1 : (r3 == r20 ? 0 : -1))
            if (r24 != 0) goto L_0x01cb
            r31 = r9
            goto L_0x01cd
        L_0x01cb:
            r31 = r3
        L_0x01cd:
            r3 = 4
            int r3 = r6.getInt(r3)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r4 = com.amap.openapi.dy.a((com.loc.fc) r12, (byte[]) r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r4 = com.amap.openapi.dy.a(r12, r5, r4, r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r0.add(r4)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            long r3 = (long) r3     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            long r7 = r7 + r3
            int r3 = (r7 > r16 ? 1 : (r7 == r16 ? 0 : -1))
            if (r3 < 0) goto L_0x01ec
            r3 = r29
            r7 = r31
            goto L_0x01fb
        L_0x01ec:
            r24 = r9
            r5 = r28
            r9 = r29
            r3 = r31
            goto L_0x01a2
        L_0x01f5:
            r28 = r5
            r7 = r3
            r3 = r9
            r9 = r24
        L_0x01fb:
            com.amap.location.common.util.e.a((android.database.Cursor) r6)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r5 = r0.size()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int[] r5 = new int[r5]     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r6 = 0
        L_0x0205:
            int r11 = r0.size()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r6 >= r11) goto L_0x021a
            java.lang.Object r11 = r0.get(r6)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r11 = r11.intValue()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5[r6] = r11     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r6 = r6 + 1
            goto L_0x0205
        L_0x021a:
            com.amap.openapi.dt r6 = r1.f4703a     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            android.content.Context r6 = r6.a()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r6 = com.amap.openapi.bk.a(r12, r6)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r5 = com.amap.openapi.dz.b((com.loc.fc) r12, (int[]) r5)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dz.a(r12)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r11 = 1
            com.amap.openapi.dz.a((com.loc.fc) r12, (byte) r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dz.a((com.loc.fc) r12, (int) r6)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dz.c(r12, r5)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r5 = com.amap.openapi.dz.b(r12)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dz.d(r12, r5)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.location.common.network.IHttpClient r5 = r1.b     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dt r6 = r1.f4703a     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            com.amap.openapi.dn r11 = r1.c     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r11 = r11.b()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r6 = r6.a(r11)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            byte[] r11 = r12.f()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r12 = 120000(0x1d4c0, float:1.68156E-40)
            boolean r5 = com.amap.openapi.ea.a(r5, r6, r11, r12)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            if (r5 != 0) goto L_0x028b
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r6 = "UpTunnel fail,条数是:"
            r5.<init>(r6)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            int r0 = r0.size()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5.append(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = ", 最后一条 id  是:"
            r5.append(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5.append(r3)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = ",第一条时间："
            r5.append(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5.append(r7)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = ",最后一条时间："
            r5.append(r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5.append(r9)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            java.lang.String r0 = r5.toString()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            byte[] r0 = r0.getBytes()     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
            r5 = 800001(0xc3501, float:1.12104E-39)
            com.amap.openapi.dl.a((int) r5, (byte[]) r0)     // Catch:{ Throwable -> 0x02ae, all -> 0x02ac }
        L_0x028b:
            r5 = r28
            goto L_0x015e
        L_0x028f:
            int r0 = r2.size()
            if (r0 <= 0) goto L_0x02f7
            r0 = 0
        L_0x0296:
            int r3 = r2.size()
            if (r0 >= r3) goto L_0x02a8
            java.lang.Object r3 = r2.get(r0)
            android.database.Cursor r3 = (android.database.Cursor) r3
            com.amap.location.common.util.e.a((android.database.Cursor) r3)
            int r0 = r0 + 1
            goto L_0x0296
        L_0x02a8:
            r2.clear()
            return
        L_0x02ac:
            r0 = move-exception
            goto L_0x02be
        L_0x02ae:
            r0 = move-exception
            java.lang.String r0 = android.util.Log.getStackTraceString(r0)     // Catch:{ Exception -> 0x02db, all -> 0x02ac }
            byte[] r0 = r0.getBytes()     // Catch:{ Exception -> 0x02db, all -> 0x02ac }
            r3 = 800001(0xc3501, float:1.12104E-39)
            com.amap.openapi.dl.a((int) r3, (byte[]) r0)     // Catch:{ Exception -> 0x02db, all -> 0x02ac }
            goto L_0x02db
        L_0x02be:
            int r3 = r2.size()
            if (r3 <= 0) goto L_0x02da
            r3 = 0
        L_0x02c5:
            int r4 = r2.size()
            if (r3 >= r4) goto L_0x02d7
            java.lang.Object r4 = r2.get(r3)
            android.database.Cursor r4 = (android.database.Cursor) r4
            com.amap.location.common.util.e.a((android.database.Cursor) r4)
            int r3 = r3 + 1
            goto L_0x02c5
        L_0x02d7:
            r2.clear()
        L_0x02da:
            throw r0
        L_0x02db:
            int r0 = r2.size()
            if (r0 <= 0) goto L_0x02f7
            r0 = 0
        L_0x02e2:
            int r3 = r2.size()
            if (r0 >= r3) goto L_0x02f4
            java.lang.Object r3 = r2.get(r0)
            android.database.Cursor r3 = (android.database.Cursor) r3
            com.amap.location.common.util.e.a((android.database.Cursor) r3)
            int r0 = r0 + 1
            goto L_0x02e2
        L_0x02f4:
            r2.clear()
        L_0x02f7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.Cdo.run():void");
    }
}
