package com.loc;

import android.content.Context;
import android.text.TextUtils;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import java.util.ArrayList;
import java.util.Hashtable;

public final class ei {

    /* renamed from: a  reason: collision with root package name */
    Hashtable<String, ArrayList<a>> f6585a = new Hashtable<>();
    boolean b = true;
    long c = 0;
    String d = null;
    ed e = null;
    boolean f = true;
    boolean g = true;
    String h = String.valueOf(AMapLocationClientOption.GeoLanguage.DEFAULT);
    private long i = 0;
    private boolean j = false;
    private String k = "2.0.201501131131".replace(".", "");
    private String l = null;
    private String m = null;
    private long n = 0;

    static class a {

        /* renamed from: a  reason: collision with root package name */
        private AMapLocationServer f6586a = null;
        private String b = null;

        protected a() {
        }

        public final AMapLocationServer a() {
            return this.f6586a;
        }

        public final void a(AMapLocationServer aMapLocationServer) {
            this.f6586a = aMapLocationServer;
        }

        public final void a(String str) {
            this.b = TextUtils.isEmpty(str) ? null : str.replace("##", "#");
        }

        public final String b() {
            return this.b;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005f A[Catch:{ Throwable -> 0x0086 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x006e A[Catch:{ Throwable -> 0x0086 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.autonavi.aps.amapapi.model.AMapLocationServer a(java.lang.String r5, java.lang.StringBuilder r6) {
        /*
            r4 = this;
            r0 = 0
            java.lang.String r1 = "cgiwifi"
            boolean r1 = r5.contains(r1)     // Catch:{ Throwable -> 0x0086 }
            if (r1 == 0) goto L_0x000e
        L_0x0009:
            com.loc.ei$a r6 = r4.a((java.lang.StringBuilder) r6, (java.lang.String) r5)     // Catch:{ Throwable -> 0x0086 }
            goto L_0x0039
        L_0x000e:
            java.lang.String r1 = "wifi"
            boolean r1 = r5.contains(r1)     // Catch:{ Throwable -> 0x0086 }
            if (r1 == 0) goto L_0x0018
            goto L_0x0009
        L_0x0018:
            java.lang.String r6 = "cgi"
            boolean r6 = r5.contains(r6)     // Catch:{ Throwable -> 0x0086 }
            if (r6 == 0) goto L_0x0038
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r6 = r4.f6585a     // Catch:{ Throwable -> 0x0086 }
            boolean r6 = r6.containsKey(r5)     // Catch:{ Throwable -> 0x0086 }
            if (r6 == 0) goto L_0x0038
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r6 = r4.f6585a     // Catch:{ Throwable -> 0x0086 }
            java.lang.Object r6 = r6.get(r5)     // Catch:{ Throwable -> 0x0086 }
            java.util.ArrayList r6 = (java.util.ArrayList) r6     // Catch:{ Throwable -> 0x0086 }
            r1 = 0
            java.lang.Object r6 = r6.get(r1)     // Catch:{ Throwable -> 0x0086 }
            com.loc.ei$a r6 = (com.loc.ei.a) r6     // Catch:{ Throwable -> 0x0086 }
            goto L_0x0039
        L_0x0038:
            r6 = r0
        L_0x0039:
            if (r6 == 0) goto L_0x008e
            com.autonavi.aps.amapapi.model.AMapLocationServer r1 = r6.a()     // Catch:{ Throwable -> 0x0086 }
            boolean r1 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r1)     // Catch:{ Throwable -> 0x0086 }
            if (r1 == 0) goto L_0x008e
            com.autonavi.aps.amapapi.model.AMapLocationServer r1 = r6.a()     // Catch:{ Throwable -> 0x0086 }
            java.lang.String r2 = "mem"
            r1.e(r2)     // Catch:{ Throwable -> 0x0086 }
            java.lang.String r2 = r6.b()     // Catch:{ Throwable -> 0x0086 }
            r1.h(r2)     // Catch:{ Throwable -> 0x0086 }
            long r2 = r1.getTime()     // Catch:{ Throwable -> 0x0086 }
            boolean r2 = com.loc.er.b((long) r2)     // Catch:{ Throwable -> 0x0086 }
            if (r2 == 0) goto L_0x006e
            boolean r5 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r1)     // Catch:{ Throwable -> 0x0086 }
            if (r5 == 0) goto L_0x0069
            r5 = 0
            r4.c = r5     // Catch:{ Throwable -> 0x0086 }
        L_0x0069:
            r5 = 4
            r1.setLocationType(r5)     // Catch:{ Throwable -> 0x0086 }
            return r1
        L_0x006e:
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r1 = r4.f6585a     // Catch:{ Throwable -> 0x0086 }
            if (r1 == 0) goto L_0x008e
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r1 = r4.f6585a     // Catch:{ Throwable -> 0x0086 }
            boolean r1 = r1.containsKey(r5)     // Catch:{ Throwable -> 0x0086 }
            if (r1 == 0) goto L_0x008e
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r1 = r4.f6585a     // Catch:{ Throwable -> 0x0086 }
            java.lang.Object r5 = r1.get(r5)     // Catch:{ Throwable -> 0x0086 }
            java.util.ArrayList r5 = (java.util.ArrayList) r5     // Catch:{ Throwable -> 0x0086 }
            r5.remove(r6)     // Catch:{ Throwable -> 0x0086 }
            goto L_0x008e
        L_0x0086:
            r5 = move-exception
            java.lang.String r6 = "Cache"
            java.lang.String r1 = "get1"
            com.loc.es.a(r5, r6, r1)
        L_0x008e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(java.lang.String, java.lang.StringBuilder):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ed A[LOOP:1: B:34:0x00e7->B:36:0x00ed, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0107 A[LOOP:2: B:38:0x0101->B:40:0x0107, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0177 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.loc.ei.a a(java.lang.StringBuilder r26, java.lang.String r27) {
        /*
            r25 = this;
            r0 = r25
            r1 = r27
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r2 = r0.f6585a
            boolean r2 = r2.isEmpty()
            r3 = 0
            if (r2 != 0) goto L_0x0195
            boolean r2 = android.text.TextUtils.isEmpty(r26)
            if (r2 == 0) goto L_0x0015
            goto L_0x0195
        L_0x0015:
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r2 = r0.f6585a
            boolean r2 = r2.containsKey(r1)
            if (r2 != 0) goto L_0x001e
            return r3
        L_0x001e:
            java.util.Hashtable r2 = new java.util.Hashtable
            r2.<init>()
            java.util.Hashtable r4 = new java.util.Hashtable
            r4.<init>()
            java.util.Hashtable r5 = new java.util.Hashtable
            r5.<init>()
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r6 = r0.f6585a
            java.lang.Object r1 = r6.get(r1)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r6 = r1.size()
            r7 = 1
            int r6 = r6 - r7
        L_0x003b:
            if (r6 < 0) goto L_0x0189
            java.lang.Object r8 = r1.get(r6)
            com.loc.ei$a r8 = (com.loc.ei.a) r8
            java.lang.String r9 = r8.b()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L_0x0182
            java.lang.String r9 = r8.b()
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            r11 = 0
            if (r10 != 0) goto L_0x00b6
            boolean r10 = android.text.TextUtils.isEmpty(r26)
            if (r10 == 0) goto L_0x005f
            goto L_0x00b6
        L_0x005f:
            java.lang.String r10 = ",access"
            boolean r10 = r9.contains(r10)
            if (r10 == 0) goto L_0x00b6
            java.lang.String r10 = ",access"
            r12 = r26
            int r10 = r12.indexOf(r10)
            r13 = -1
            if (r10 != r13) goto L_0x0073
            goto L_0x00b8
        L_0x0073:
            java.lang.String r10 = ",access"
            java.lang.String[] r9 = r9.split(r10)
            r10 = r9[r11]
            java.lang.String r13 = "#"
            boolean r10 = r10.contains(r13)
            if (r10 == 0) goto L_0x0093
            r10 = r9[r11]
            r9 = r9[r11]
            java.lang.String r13 = "#"
            int r9 = r9.lastIndexOf(r13)
            int r9 = r9 + r7
            java.lang.String r9 = r10.substring(r9)
            goto L_0x0095
        L_0x0093:
            r9 = r9[r11]
        L_0x0095:
            boolean r10 = android.text.TextUtils.isEmpty(r9)
            if (r10 == 0) goto L_0x009c
            goto L_0x00b8
        L_0x009c:
            java.lang.String r10 = r26.toString()
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            r13.append(r9)
            java.lang.String r9 = ",access"
            r13.append(r9)
            java.lang.String r9 = r13.toString()
            boolean r9 = r10.contains(r9)
            goto L_0x00b9
        L_0x00b6:
            r12 = r26
        L_0x00b8:
            r9 = 0
        L_0x00b9:
            if (r9 == 0) goto L_0x00cd
            java.lang.String r9 = r8.b()
            java.lang.String r10 = r26.toString()
            boolean r9 = com.loc.fa.a((java.lang.String) r9, (java.lang.String) r10)
            if (r9 == 0) goto L_0x00cb
            goto L_0x017f
        L_0x00cb:
            r9 = 1
            goto L_0x00ce
        L_0x00cd:
            r9 = 0
        L_0x00ce:
            java.lang.String r10 = r8.b()
            a((java.lang.String) r10, (java.util.Hashtable<java.lang.String, java.lang.String>) r2)
            java.lang.String r10 = r26.toString()
            a((java.lang.String) r10, (java.util.Hashtable<java.lang.String, java.lang.String>) r4)
            r5.clear()
            java.util.Set r10 = r2.keySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x00e7:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x00f9
            java.lang.Object r13 = r10.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r14 = ""
            r5.put(r13, r14)
            goto L_0x00e7
        L_0x00f9:
            java.util.Set r10 = r4.keySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x0101:
            boolean r13 = r10.hasNext()
            if (r13 == 0) goto L_0x0113
            java.lang.Object r13 = r10.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.String r14 = ""
            r5.put(r13, r14)
            goto L_0x0101
        L_0x0113:
            java.util.Set r10 = r5.keySet()
            int r13 = r10.size()
            double[] r13 = new double[r13]
            int r14 = r10.size()
            double[] r14 = new double[r14]
            java.util.Iterator r15 = r10.iterator()
            r16 = 0
        L_0x0129:
            if (r15 == 0) goto L_0x0158
            boolean r17 = r15.hasNext()
            if (r17 == 0) goto L_0x0158
            java.lang.Object r17 = r15.next()
            r3 = r17
            java.lang.String r3 = (java.lang.String) r3
            boolean r17 = r2.containsKey(r3)
            r19 = 0
            r21 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            if (r17 == 0) goto L_0x0146
            r23 = r21
            goto L_0x0148
        L_0x0146:
            r23 = r19
        L_0x0148:
            r13[r16] = r23
            boolean r3 = r4.containsKey(r3)
            if (r3 == 0) goto L_0x0152
            r19 = r21
        L_0x0152:
            r14[r16] = r19
            int r16 = r16 + 1
            r3 = 0
            goto L_0x0129
        L_0x0158:
            r10.clear()
            double[] r3 = a((double[]) r13, (double[]) r14)
            r13 = r3[r11]
            r15 = 4605380979056443392(0x3fe99999a0000000, double:0.800000011920929)
            int r10 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r10 < 0) goto L_0x016b
            goto L_0x017f
        L_0x016b:
            r13 = r3[r7]
            r15 = 4603741668684706349(0x3fe3c6a7ef9db22d, double:0.618)
            int r10 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r10 < 0) goto L_0x0177
            goto L_0x017f
        L_0x0177:
            if (r9 == 0) goto L_0x0184
            r9 = r3[r11]
            int r3 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r3 < 0) goto L_0x0184
        L_0x017f:
            r18 = r8
            goto L_0x018b
        L_0x0182:
            r12 = r26
        L_0x0184:
            int r6 = r6 + -1
            r3 = 0
            goto L_0x003b
        L_0x0189:
            r18 = 0
        L_0x018b:
            r2.clear()
            r4.clear()
            r5.clear()
            return r18
        L_0x0195:
            r1 = r3
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(java.lang.StringBuilder, java.lang.String):com.loc.ei$a");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r6, java.lang.StringBuilder r7, android.content.Context r8) {
        /*
            r5 = this;
            r0 = 0
            if (r8 != 0) goto L_0x0004
            return r0
        L_0x0004:
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r2 = r5.l     // Catch:{  }
            if (r2 != 0) goto L_0x0019
            java.lang.String r2 = "MD5"
            java.lang.String r8 = com.loc.u.c(r8)     // Catch:{  }
            java.lang.String r8 = com.loc.eh.a((java.lang.String) r2, (java.lang.String) r8)     // Catch:{  }
            r5.l = r8     // Catch:{  }
        L_0x0019:
            java.lang.String r8 = "&"
            boolean r8 = r6.contains(r8)     // Catch:{  }
            r2 = 0
            if (r8 == 0) goto L_0x002c
            java.lang.String r8 = "&"
            int r8 = r6.indexOf(r8)     // Catch:{  }
            java.lang.String r6 = r6.substring(r2, r8)     // Catch:{  }
        L_0x002c:
            java.lang.String r8 = "#"
            int r8 = r6.lastIndexOf(r8)     // Catch:{  }
            int r8 = r8 + 1
            java.lang.String r8 = r6.substring(r8)     // Catch:{  }
            java.lang.String r3 = "cgi"
            boolean r3 = r8.equals(r3)     // Catch:{  }
            if (r3 == 0) goto L_0x0050
            java.lang.String r7 = "cgi"
            int r8 = r6.length()     // Catch:{  }
            int r8 = r8 + -12
            java.lang.String r6 = r6.substring(r2, r8)     // Catch:{  }
        L_0x004c:
            r1.put(r7, r6)     // Catch:{  }
            goto L_0x009d
        L_0x0050:
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{  }
            if (r3 != 0) goto L_0x009d
            java.lang.String r3 = ",access"
            int r3 = r7.indexOf(r3)     // Catch:{  }
            r4 = -1
            if (r3 == r4) goto L_0x009d
            int r8 = r8.length()     // Catch:{  }
            int r8 = r8 + 9
            int r3 = r6.length()     // Catch:{  }
            int r3 = r3 - r8
            java.lang.String r6 = r6.substring(r2, r3)     // Catch:{  }
            java.lang.String r8 = "cgi"
            r1.put(r8, r6)     // Catch:{  }
            java.lang.String r6 = r7.toString()     // Catch:{  }
            java.lang.String r7 = ",access"
            java.lang.String[] r6 = r6.split(r7)     // Catch:{  }
            r7 = r6[r2]     // Catch:{  }
            java.lang.String r8 = "#"
            boolean r7 = r7.contains(r8)     // Catch:{  }
            if (r7 == 0) goto L_0x0098
            r7 = r6[r2]     // Catch:{  }
            r6 = r6[r2]     // Catch:{  }
            java.lang.String r8 = "#"
            int r6 = r6.lastIndexOf(r8)     // Catch:{  }
            int r6 = r6 + 1
            java.lang.String r6 = r7.substring(r6)     // Catch:{  }
            goto L_0x009a
        L_0x0098:
            r6 = r6[r2]     // Catch:{  }
        L_0x009a:
            java.lang.String r7 = "mmac"
            goto L_0x004c
        L_0x009d:
            java.lang.String r6 = r1.toString()     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r7 = "UTF-8"
            byte[] r6 = r6.getBytes(r7)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r7 = r5.l     // Catch:{ Throwable -> 0x00b2 }
            byte[] r6 = com.loc.eh.c(r6, r7)     // Catch:{ Throwable -> 0x00b2 }
            java.lang.String r6 = com.loc.y.b((byte[]) r6)     // Catch:{  }
            return r6
        L_0x00b2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(java.lang.String, java.lang.StringBuilder, android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ed A[Catch:{ Throwable -> 0x026e, all -> 0x026c }] */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0295  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r11, java.lang.String r12) throws java.lang.Exception {
        /*
            r10 = this;
            boolean r0 = com.loc.er.o()
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            if (r11 != 0) goto L_0x000a
            return
        L_0x000a:
            r0 = 0
            java.lang.String r1 = "hmdb"
            r2 = 0
            android.database.sqlite.SQLiteDatabase r1 = r11.openOrCreateDatabase(r1, r2, r0)     // Catch:{ Throwable -> 0x0277, all -> 0x0273 }
            java.lang.String r3 = "hist"
            boolean r3 = com.loc.fa.a((android.database.sqlite.SQLiteDatabase) r1, (java.lang.String) r3)     // Catch:{ Throwable -> 0x0271 }
            if (r3 != 0) goto L_0x0026
            if (r1 == 0) goto L_0x0025
            boolean r11 = r1.isOpen()     // Catch:{ Throwable -> 0x0271 }
            if (r11 == 0) goto L_0x0025
            r1.close()     // Catch:{ Throwable -> 0x0271 }
        L_0x0025:
            return
        L_0x0026:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0271 }
            r3.<init>()     // Catch:{ Throwable -> 0x0271 }
            java.lang.String r4 = "SELECT feature, nb, loc FROM "
            r3.append(r4)     // Catch:{ Throwable -> 0x0271 }
            java.lang.String r4 = "hist"
            r3.append(r4)     // Catch:{ Throwable -> 0x0271 }
            java.lang.String r4 = r10.k     // Catch:{ Throwable -> 0x0271 }
            r3.append(r4)     // Catch:{ Throwable -> 0x0271 }
            long r4 = com.loc.fa.b()     // Catch:{ Throwable -> 0x0271 }
            long r6 = com.loc.er.n()     // Catch:{ Throwable -> 0x0271 }
            r8 = 0
            long r4 = r4 - r6
            java.lang.String r6 = " WHERE time > "
            r3.append(r6)     // Catch:{ Throwable -> 0x0271 }
            r3.append(r4)     // Catch:{ Throwable -> 0x0271 }
            if (r12 == 0) goto L_0x0067
            java.lang.String r4 = " and feature = '"
            r3.append(r4)     // Catch:{ Throwable -> 0x0271 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0271 }
            r4.<init>()     // Catch:{ Throwable -> 0x0271 }
            r4.append(r12)     // Catch:{ Throwable -> 0x0271 }
            java.lang.String r12 = "'"
            r4.append(r12)     // Catch:{ Throwable -> 0x0271 }
            java.lang.String r12 = r4.toString()     // Catch:{ Throwable -> 0x0271 }
            r3.append(r12)     // Catch:{ Throwable -> 0x0271 }
        L_0x0067:
            java.lang.String r12 = " ORDER BY time ASC;"
            r3.append(r12)     // Catch:{ Throwable -> 0x0271 }
            java.lang.String r12 = r3.toString()     // Catch:{ Throwable -> 0x0271 }
            android.database.Cursor r12 = r1.rawQuery(r12, r0)     // Catch:{ Throwable -> 0x0271 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r4 = r10.l     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 != 0) goto L_0x0089
            java.lang.String r4 = "MD5"
            java.lang.String r5 = com.loc.u.c(r11)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r4 = com.loc.eh.a((java.lang.String) r4, (java.lang.String) r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r10.l = r4     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x0089:
            if (r12 == 0) goto L_0x025b
            boolean r4 = r12.moveToFirst()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 == 0) goto L_0x025b
        L_0x0091:
            java.lang.String r4 = r12.getString(r2)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "{"
            boolean r4 = r4.startsWith(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x00f7
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r7 = r12.getString(r2)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.<init>(r7)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            int r7 = r0.length()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.delete(r2, r7)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r7 = r12.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            boolean r7 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r7 != 0) goto L_0x00c2
            java.lang.String r6 = r12.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x00be:
            r0.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            goto L_0x00db
        L_0x00c2:
            java.lang.String r6 = "mmac"
            boolean r6 = com.loc.fa.a((org.json.JSONObject) r4, (java.lang.String) r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r6 == 0) goto L_0x00db
            java.lang.String r6 = "#"
            r0.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "mmac"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = ",access"
            goto L_0x00be
        L_0x00db:
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = r12.getString(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r6.<init>(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "type"
            boolean r5 = com.loc.fa.a((org.json.JSONObject) r6, (java.lang.String) r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r5 == 0) goto L_0x0182
            java.lang.String r5 = "type"
            java.lang.String r7 = "new"
            r6.put(r5, r7)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            goto L_0x0182
        L_0x00f7:
            java.lang.String r4 = r12.getString(r2)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            byte[] r4 = com.loc.y.b((java.lang.String) r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r8 = new java.lang.String     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r9 = r10.l     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            byte[] r4 = com.loc.eh.d(r4, r9)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r9 = "UTF-8"
            r8.<init>(r4, r9)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r7.<init>(r8)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            int r4 = r0.length()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.delete(r2, r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r4 = r12.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            boolean r4 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 != 0) goto L_0x013b
            java.lang.String r4 = r12.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            byte[] r4 = com.loc.y.b((java.lang.String) r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = new java.lang.String     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r8 = r10.l     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            byte[] r4 = com.loc.eh.d(r4, r8)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r8 = "UTF-8"
            r6.<init>(r4, r8)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            goto L_0x0156
        L_0x013b:
            java.lang.String r4 = "mmac"
            boolean r4 = com.loc.fa.a((org.json.JSONObject) r7, (java.lang.String) r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 == 0) goto L_0x0156
            java.lang.String r4 = "#"
            r0.append(r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r4 = "mmac"
            java.lang.String r4 = r7.getString(r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.append(r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r4 = ",access"
            r0.append(r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x0156:
            java.lang.String r4 = r12.getString(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            byte[] r4 = com.loc.y.b((java.lang.String) r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = new java.lang.String     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r8 = r10.l     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            byte[] r4 = com.loc.eh.d(r4, r8)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r8 = "UTF-8"
            r5.<init>(r4, r8)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r6.<init>(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r4 = "type"
            boolean r4 = com.loc.fa.a((org.json.JSONObject) r6, (java.lang.String) r4)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 == 0) goto L_0x0181
            java.lang.String r4 = "type"
            java.lang.String r5 = "new"
            r6.put(r4, r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x0181:
            r4 = r7
        L_0x0182:
            com.autonavi.aps.amapapi.model.AMapLocationServer r7 = new com.autonavi.aps.amapapi.model.AMapLocationServer     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = ""
            r7.<init>(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r7.b((org.json.JSONObject) r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "mmac"
            boolean r5 = com.loc.fa.a((org.json.JSONObject) r4, (java.lang.String) r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r5 == 0) goto L_0x01f4
            java.lang.String r5 = "cgi"
            boolean r5 = com.loc.fa.a((org.json.JSONObject) r4, (java.lang.String) r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r5 == 0) goto L_0x01f4
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r5.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "cgi"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r5.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "#"
            r5.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r6.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r6.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "network#"
            r6.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = r6.toString()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "cgi"
            java.lang.String r4 = r4.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "#"
            boolean r4 = r4.contains(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 == 0) goto L_0x01e5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "cgiwifi"
            r4.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x01df:
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r5 = r4
            goto L_0x0240
        L_0x01e5:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "wifi"
            r4.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            goto L_0x01df
        L_0x01f4:
            java.lang.String r5 = "cgi"
            boolean r5 = com.loc.fa.a((org.json.JSONObject) r4, (java.lang.String) r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r5 == 0) goto L_0x0247
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r5.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "cgi"
            java.lang.String r6 = r4.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r5.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "#"
            r5.append(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r6.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r6.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "network#"
            r6.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = r6.toString()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "cgi"
            java.lang.String r4 = r4.getString(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r6 = "#"
            boolean r4 = r4.contains(r6)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 == 0) goto L_0x0247
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.<init>()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r4.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            java.lang.String r5 = "cgi"
            r4.append(r5)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            goto L_0x01df
        L_0x0240:
            r9 = 0
            r4 = r10
            r6 = r0
            r8 = r11
            r4.a(r5, r6, r7, r8, r9)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x0247:
            boolean r4 = r12.moveToNext()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            if (r4 != 0) goto L_0x0091
            int r11 = r0.length()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r0.delete(r2, r11)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            int r11 = r3.length()     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
            r3.delete(r2, r11)     // Catch:{ Throwable -> 0x026e, all -> 0x026c }
        L_0x025b:
            if (r12 == 0) goto L_0x0260
            r12.close()
        L_0x0260:
            if (r1 == 0) goto L_0x026b
            boolean r11 = r1.isOpen()
            if (r11 == 0) goto L_0x026b
            r1.close()
        L_0x026b:
            return
        L_0x026c:
            r11 = move-exception
            goto L_0x0293
        L_0x026e:
            r11 = move-exception
            r0 = r12
            goto L_0x0279
        L_0x0271:
            r11 = move-exception
            goto L_0x0279
        L_0x0273:
            r11 = move-exception
            r12 = r0
            r1 = r12
            goto L_0x0293
        L_0x0277:
            r11 = move-exception
            r1 = r0
        L_0x0279:
            java.lang.String r12 = "DB"
            java.lang.String r2 = "fetchHist p2"
            com.loc.es.a(r11, r12, r2)     // Catch:{ all -> 0x0291 }
            if (r0 == 0) goto L_0x0285
            r0.close()
        L_0x0285:
            if (r1 == 0) goto L_0x0290
            boolean r11 = r1.isOpen()
            if (r11 == 0) goto L_0x0290
            r1.close()
        L_0x0290:
            return
        L_0x0291:
            r11 = move-exception
            r12 = r0
        L_0x0293:
            if (r12 == 0) goto L_0x0298
            r12.close()
        L_0x0298:
            if (r1 == 0) goto L_0x02a3
            boolean r12 = r1.isOpen()
            if (r12 == 0) goto L_0x02a3
            r1.close()
        L_0x02a3:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(android.content.Context, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r6, com.amap.api.location.AMapLocation r7, java.lang.StringBuilder r8, android.content.Context r9) throws java.lang.Exception {
        /*
            r5 = this;
            if (r9 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.String r0 = r5.l
            if (r0 != 0) goto L_0x0013
            java.lang.String r0 = "MD5"
            java.lang.String r1 = com.loc.u.c(r9)
            java.lang.String r0 = com.loc.eh.a((java.lang.String) r0, (java.lang.String) r1)
            r5.l = r0
        L_0x0013:
            java.lang.String r6 = r5.a(r6, r8, r9)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            r2 = 0
            java.lang.String r3 = "hmdb"
            android.database.sqlite.SQLiteDatabase r9 = r9.openOrCreateDatabase(r3, r2, r1)     // Catch:{ Throwable -> 0x00c5 }
            java.lang.String r1 = "CREATE TABLE IF NOT EXISTS hist"
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = r5.k     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = " (feature VARCHAR PRIMARY KEY, nb VARCHAR, loc VARCHAR, time VARCHAR);"
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = r0.toString()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r9.execSQL(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            int r1 = r0.length()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r0.delete(r2, r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = "REPLACE INTO "
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = "hist"
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = r5.k     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r1 = " VALUES (?, ?, ?, ?)"
            r0.append(r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r1 = 4
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r1[r2] = r6     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r6 = r8.toString()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r8 = "UTF-8"
            byte[] r6 = r6.getBytes(r8)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r8 = r5.l     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            byte[] r6 = com.loc.eh.c(r6, r8)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r8 = 1
            r1[r8] = r6     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r6 = 2
            java.lang.String r3 = r7.toStr()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r4 = "UTF-8"
            byte[] r3 = r3.getBytes(r4)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r4 = r5.l     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            byte[] r3 = com.loc.eh.c(r3, r4)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r1[r6] = r3     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            long r6 = r7.getTime()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.Long r6 = java.lang.Long.valueOf(r6)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r7 = 3
            r1[r7] = r6     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
        L_0x008b:
            if (r8 >= r7) goto L_0x009c
            r6 = r1[r8]     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            byte[] r6 = (byte[]) r6     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            byte[] r6 = (byte[]) r6     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            java.lang.String r6 = com.loc.y.b((byte[]) r6)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r1[r8] = r6     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            int r8 = r8 + 1
            goto L_0x008b
        L_0x009c:
            java.lang.String r6 = r0.toString()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r9.execSQL(r6, r1)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            int r6 = r0.length()     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            r0.delete(r2, r6)     // Catch:{ Throwable -> 0x00bf, all -> 0x00bd }
            int r6 = r0.length()
            r0.delete(r2, r6)
            if (r9 == 0) goto L_0x00bc
            boolean r6 = r9.isOpen()
            if (r6 == 0) goto L_0x00bc
            r9.close()
        L_0x00bc:
            return
        L_0x00bd:
            r6 = move-exception
            goto L_0x00e1
        L_0x00bf:
            r6 = move-exception
            r1 = r9
            goto L_0x00c6
        L_0x00c2:
            r6 = move-exception
            r9 = r1
            goto L_0x00e1
        L_0x00c5:
            r6 = move-exception
        L_0x00c6:
            java.lang.String r7 = "DB"
            java.lang.String r8 = "updateHist"
            com.loc.es.a(r6, r7, r8)     // Catch:{ all -> 0x00c2 }
            int r6 = r0.length()
            r0.delete(r2, r6)
            if (r1 == 0) goto L_0x00e0
            boolean r6 = r1.isOpen()
            if (r6 == 0) goto L_0x00e0
            r1.close()
        L_0x00e0:
            return
        L_0x00e1:
            int r7 = r0.length()
            r0.delete(r2, r7)
            if (r9 == 0) goto L_0x00f3
            boolean r7 = r9.isOpen()
            if (r7 == 0) goto L_0x00f3
            r9.close()
        L_0x00f3:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(java.lang.String, com.amap.api.location.AMapLocation, java.lang.StringBuilder, android.content.Context):void");
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            hashtable.clear();
            for (String str2 : str.split("#")) {
                if (!TextUtils.isEmpty(str2) && !str2.contains("|")) {
                    hashtable.put(str2, "");
                }
            }
        }
    }

    private static double[] a(double[] dArr, double[] dArr2) {
        double[] dArr3 = dArr;
        double[] dArr4 = new double[3];
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < dArr3.length; i4++) {
            d2 += dArr3[i4] * dArr3[i4];
            d3 += dArr2[i4] * dArr2[i4];
            d4 += dArr3[i4] * dArr2[i4];
            if (dArr2[i4] == 1.0d) {
                i3++;
                if (dArr3[i4] == 1.0d) {
                    i2++;
                }
            }
        }
        dArr4[0] = d4 / (Math.sqrt(d2) * Math.sqrt(d3));
        double d5 = (double) i2;
        Double.isNaN(d5);
        double d6 = (double) i3;
        Double.isNaN(d6);
        dArr4[1] = (d5 * 1.0d) / d6;
        dArr4[2] = d5;
        for (int i5 = 0; i5 < 2; i5++) {
            if (dArr4[i5] > 1.0d) {
                dArr4[i5] = 1.0d;
            }
        }
        return dArr4;
    }

    private boolean b() {
        return this.i != 0 && (this.f6585a.size() > 360 || fa.c() - this.i > 36000000);
    }

    private void c() {
        this.i = 0;
        if (!this.f6585a.isEmpty()) {
            this.f6585a.clear();
        }
        this.j = false;
    }

    public final AMapLocationServer a(Context context, String str, StringBuilder sb, boolean z) {
        if (TextUtils.isEmpty(str) || !er.o()) {
            return null;
        }
        String str2 = str + com.alipay.sdk.sys.a.b + this.f + com.alipay.sdk.sys.a.b + this.g + com.alipay.sdk.sys.a.b + this.h;
        if (str2.contains("gps") || !er.o() || sb == null) {
            return null;
        }
        if (b()) {
            c();
            return null;
        }
        if (z && !this.j) {
            try {
                String a2 = a(str2, sb, context);
                c();
                a(context, a2);
            } catch (Throwable unused) {
            }
        }
        if (this.f6585a.isEmpty()) {
            return null;
        }
        return a(str2, sb);
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0046 A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a3 A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00aa A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00b8 A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00d0 A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00e2 A[ADDED_TO_REGION, Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00f8 A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00fb A[Catch:{ Throwable -> 0x010c }] */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x00ff A[Catch:{ Throwable -> 0x010c }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.autonavi.aps.amapapi.model.AMapLocationServer a(com.loc.ee r18, boolean r19, com.autonavi.aps.amapapi.model.AMapLocationServer r20, com.loc.eg r21, java.lang.StringBuilder r22, java.lang.String r23, android.content.Context r24) {
        /*
            r17 = this;
            r0 = r17
            r2 = r20
            r3 = r23
            boolean r4 = r0.b
            if (r4 == 0) goto L_0x0013
            boolean r4 = com.loc.er.o()
            if (r4 != 0) goto L_0x0011
            goto L_0x0013
        L_0x0011:
            r4 = 1
            goto L_0x0014
        L_0x0013:
            r4 = 0
        L_0x0014:
            if (r4 != 0) goto L_0x0018
        L_0x0016:
            r4 = 0
            goto L_0x0026
        L_0x0018:
            if (r2 == 0) goto L_0x0025
            long r7 = r20.getTime()
            boolean r4 = com.loc.er.b((long) r7)
            if (r4 != 0) goto L_0x0025
            goto L_0x0016
        L_0x0025:
            r4 = 1
        L_0x0026:
            r7 = 0
            if (r4 != 0) goto L_0x002a
            return r7
        L_0x002a:
            com.loc.ed r4 = r18.c()     // Catch:{ Throwable -> 0x010c }
            if (r4 != 0) goto L_0x0034
            com.loc.ed r8 = r0.e     // Catch:{ Throwable -> 0x010c }
            if (r8 == 0) goto L_0x0041
        L_0x0034:
            com.loc.ed r8 = r0.e     // Catch:{ Throwable -> 0x010c }
            if (r8 == 0) goto L_0x0043
            com.loc.ed r8 = r0.e     // Catch:{ Throwable -> 0x010c }
            boolean r4 = r8.equals(r4)     // Catch:{ Throwable -> 0x010c }
            if (r4 != 0) goto L_0x0041
            goto L_0x0043
        L_0x0041:
            r4 = 0
            goto L_0x0044
        L_0x0043:
            r4 = 1
        L_0x0044:
            if (r2 == 0) goto L_0x005e
            java.util.ArrayList r8 = r21.c()     // Catch:{ Throwable -> 0x010c }
            int r8 = r8.size()     // Catch:{ Throwable -> 0x010c }
            float r9 = r20.getAccuracy()     // Catch:{ Throwable -> 0x010c }
            r10 = 1133871104(0x43958000, float:299.0)
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 <= 0) goto L_0x005e
            r9 = 5
            if (r8 <= r9) goto L_0x005e
            r8 = 1
            goto L_0x005f
        L_0x005e:
            r8 = 0
        L_0x005f:
            r9 = 3000(0xbb8, double:1.482E-320)
            r11 = 0
            if (r2 == 0) goto L_0x00a0
            java.lang.String r13 = r0.d     // Catch:{ Throwable -> 0x010c }
            if (r13 == 0) goto L_0x00a0
            if (r8 != 0) goto L_0x00a0
            if (r4 != 0) goto L_0x00a0
            java.lang.String r4 = r0.d     // Catch:{ Throwable -> 0x010c }
            java.lang.String r13 = r22.toString()     // Catch:{ Throwable -> 0x010c }
            boolean r4 = com.loc.fa.a((java.lang.String) r4, (java.lang.String) r13)     // Catch:{ Throwable -> 0x010c }
            long r13 = r0.c     // Catch:{ Throwable -> 0x010c }
            int r15 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r15 == 0) goto L_0x008b
            long r13 = com.loc.fa.c()     // Catch:{ Throwable -> 0x010c }
            long r5 = r0.c     // Catch:{ Throwable -> 0x010c }
            r15 = 0
            long r13 = r13 - r5
            int r5 = (r13 > r9 ? 1 : (r13 == r9 ? 0 : -1))
            if (r5 >= 0) goto L_0x008b
            r5 = 1
            goto L_0x008c
        L_0x008b:
            r5 = 0
        L_0x008c:
            if (r4 != 0) goto L_0x0090
            if (r5 == 0) goto L_0x00a1
        L_0x0090:
            boolean r5 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r20)     // Catch:{ Throwable -> 0x010c }
            if (r5 == 0) goto L_0x00a1
            java.lang.String r1 = "mem"
            r2.e(r1)     // Catch:{ Throwable -> 0x010c }
            r1 = 2
            r2.setLocationType(r1)     // Catch:{ Throwable -> 0x010c }
            return r2
        L_0x00a0:
            r4 = 0
        L_0x00a1:
            if (r4 != 0) goto L_0x00aa
            long r4 = com.loc.fa.c()     // Catch:{ Throwable -> 0x010c }
            r0.c = r4     // Catch:{ Throwable -> 0x010c }
            goto L_0x00ac
        L_0x00aa:
            r0.c = r11     // Catch:{ Throwable -> 0x010c }
        L_0x00ac:
            java.lang.String r2 = r0.m     // Catch:{ Throwable -> 0x010c }
            if (r2 == 0) goto L_0x00d0
            java.lang.String r2 = r0.m     // Catch:{ Throwable -> 0x010c }
            boolean r2 = r3.equals(r2)     // Catch:{ Throwable -> 0x010c }
            if (r2 != 0) goto L_0x00d0
            long r4 = com.loc.fa.b()     // Catch:{ Throwable -> 0x010c }
            long r13 = r0.n     // Catch:{ Throwable -> 0x010c }
            r2 = 0
            long r4 = r4 - r13
            int r2 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r2 >= 0) goto L_0x00c7
            java.lang.String r2 = r0.m     // Catch:{ Throwable -> 0x010c }
            goto L_0x00e0
        L_0x00c7:
            long r4 = com.loc.fa.b()     // Catch:{ Throwable -> 0x010c }
        L_0x00cb:
            r0.n = r4     // Catch:{ Throwable -> 0x010c }
            r0.m = r3     // Catch:{ Throwable -> 0x010c }
            goto L_0x00df
        L_0x00d0:
            java.lang.String r2 = r0.m     // Catch:{ Throwable -> 0x010c }
            if (r2 != 0) goto L_0x00d9
            long r4 = com.loc.fa.b()     // Catch:{ Throwable -> 0x010c }
            goto L_0x00cb
        L_0x00d9:
            long r4 = com.loc.fa.b()     // Catch:{ Throwable -> 0x010c }
            r0.n = r4     // Catch:{ Throwable -> 0x010c }
        L_0x00df:
            r2 = r3
        L_0x00e0:
            if (r8 != 0) goto L_0x00ee
            if (r19 != 0) goto L_0x00ee
            r3 = r22
            r4 = r24
            r5 = 0
            com.autonavi.aps.amapapi.model.AMapLocationServer r2 = r0.a((android.content.Context) r4, (java.lang.String) r2, (java.lang.StringBuilder) r3, (boolean) r5)     // Catch:{ Throwable -> 0x010c }
            goto L_0x00f0
        L_0x00ee:
            r5 = 0
            r2 = r7
        L_0x00f0:
            if (r19 != 0) goto L_0x00fb
            boolean r3 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r2)     // Catch:{ Throwable -> 0x010c }
            if (r3 != 0) goto L_0x00fb
            r16 = 1
            goto L_0x00fd
        L_0x00fb:
            r16 = 0
        L_0x00fd:
            if (r16 != 0) goto L_0x010c
            if (r8 == 0) goto L_0x0102
            goto L_0x010c
        L_0x0102:
            if (r19 == 0) goto L_0x0105
            return r7
        L_0x0105:
            r0.c = r11     // Catch:{ Throwable -> 0x010c }
            r1 = 4
            r2.setLocationType(r1)     // Catch:{ Throwable -> 0x010c }
            return r2
        L_0x010c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(com.loc.ee, boolean, com.autonavi.aps.amapapi.model.AMapLocationServer, com.loc.eg, java.lang.StringBuilder, java.lang.String, android.content.Context):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final void a() {
        this.c = 0;
        this.d = null;
    }

    public final void a(Context context) {
        if (!this.j) {
            try {
                c();
                a(context, (String) null);
            } catch (Throwable th) {
                es.a(th, "Cache", "loadDB");
            }
            this.j = true;
        }
    }

    public final void a(AMapLocationClientOption aMapLocationClientOption) {
        this.g = aMapLocationClientOption.isNeedAddress();
        this.f = aMapLocationClientOption.isOffset();
        this.b = aMapLocationClientOption.isLocationCacheEnable();
        this.h = String.valueOf(aMapLocationClientOption.getGeoLanguage());
    }

    public final void a(ed edVar) {
        this.e = edVar;
    }

    public final void a(String str) {
        this.d = str;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d A[Catch:{ Throwable -> 0x01a1, Throwable -> 0x01aa }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008e A[Catch:{ Throwable -> 0x01a1, Throwable -> 0x01aa }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r9, java.lang.StringBuilder r10, com.autonavi.aps.amapapi.model.AMapLocationServer r11, android.content.Context r12, boolean r13) {
        /*
            r8 = this;
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r11)     // Catch:{ Throwable -> 0x01aa }
            if (r0 != 0) goto L_0x0007
            return
        L_0x0007:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01aa }
            r0.<init>()     // Catch:{ Throwable -> 0x01aa }
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r9 = "&"
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            boolean r9 = r11.isOffset()     // Catch:{ Throwable -> 0x01aa }
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r9 = "&"
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            boolean r9 = r11.i()     // Catch:{ Throwable -> 0x01aa }
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r9 = "&"
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r9 = r11.j()     // Catch:{ Throwable -> 0x01aa }
            r0.append(r9)     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r9 = r0.toString()     // Catch:{ Throwable -> 0x01aa }
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch:{ Throwable -> 0x01aa }
            r1 = 0
            if (r0 != 0) goto L_0x0055
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r11)     // Catch:{ Throwable -> 0x01aa }
            if (r0 != 0) goto L_0x0045
            goto L_0x0055
        L_0x0045:
            java.lang.String r0 = "#"
            boolean r0 = r9.startsWith(r0)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x004e
            goto L_0x0055
        L_0x004e:
            java.lang.String r0 = "network"
            boolean r0 = r9.contains(r0)     // Catch:{ Throwable -> 0x01aa }
            goto L_0x0056
        L_0x0055:
            r0 = 0
        L_0x0056:
            if (r0 != 0) goto L_0x0059
            return
        L_0x0059:
            java.lang.String r0 = r11.e()     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r2 = "mem"
            boolean r0 = r0.equals(r2)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0066
            return
        L_0x0066:
            java.lang.String r0 = r11.e()     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r2 = "file"
            boolean r0 = r0.equals(r2)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0073
            return
        L_0x0073:
            java.lang.String r0 = r11.e()     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r2 = "wifioff"
            boolean r0 = r0.equals(r2)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0081
            return
        L_0x0081:
            java.lang.String r0 = "-3"
            java.lang.String r2 = r11.d()     // Catch:{ Throwable -> 0x01aa }
            boolean r0 = r0.equals(r2)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x008e
            return
        L_0x008e:
            boolean r0 = r8.b()     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0097
            r8.c()     // Catch:{ Throwable -> 0x01aa }
        L_0x0097:
            org.json.JSONObject r0 = r11.f()     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r2 = "offpct"
            boolean r2 = com.loc.fa.a((org.json.JSONObject) r0, (java.lang.String) r2)     // Catch:{ Throwable -> 0x01aa }
            if (r2 == 0) goto L_0x00ab
            java.lang.String r2 = "offpct"
            r0.remove(r2)     // Catch:{ Throwable -> 0x01aa }
            r11.a((org.json.JSONObject) r0)     // Catch:{ Throwable -> 0x01aa }
        L_0x00ab:
            java.lang.String r0 = "wifi"
            boolean r0 = r9.contains(r0)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0122
            boolean r0 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x00bb
            return
        L_0x00bb:
            float r0 = r11.getAccuracy()     // Catch:{ Throwable -> 0x01aa }
            r2 = 1133903872(0x43960000, float:300.0)
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 < 0) goto L_0x00e7
            java.lang.String r0 = r10.toString()     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r2 = "#"
            java.lang.String[] r0 = r0.split(r2)     // Catch:{ Throwable -> 0x01aa }
            int r2 = r0.length     // Catch:{ Throwable -> 0x01aa }
            r3 = 0
        L_0x00d1:
            if (r1 >= r2) goto L_0x00e2
            r4 = r0[r1]     // Catch:{ Throwable -> 0x01aa }
            java.lang.String r5 = ","
            boolean r4 = r4.contains(r5)     // Catch:{ Throwable -> 0x01aa }
            if (r4 == 0) goto L_0x00df
            int r3 = r3 + 1
        L_0x00df:
            int r1 = r1 + 1
            goto L_0x00d1
        L_0x00e2:
            r0 = 8
            if (r3 < r0) goto L_0x00f2
            return
        L_0x00e7:
            float r0 = r11.getAccuracy()     // Catch:{ Throwable -> 0x01aa }
            r1 = 1077936128(0x40400000, float:3.0)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x00f2
            return
        L_0x00f2:
            java.lang.String r0 = "cgiwifi"
            boolean r0 = r9.contains(r0)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0143
            java.lang.String r0 = r11.g()     // Catch:{ Throwable -> 0x01aa }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x01aa }
            if (r0 != 0) goto L_0x0143
            java.lang.String r0 = "cgiwifi"
            java.lang.String r1 = "cgi"
            java.lang.String r3 = r9.replace(r0, r1)     // Catch:{ Throwable -> 0x01aa }
            com.autonavi.aps.amapapi.model.AMapLocationServer r5 = r11.h()     // Catch:{ Throwable -> 0x01aa }
            boolean r0 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r5)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0143
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x01aa }
            r4.<init>()     // Catch:{ Throwable -> 0x01aa }
            r7 = 1
            r2 = r8
            r6 = r12
            r2.a(r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x01aa }
            goto L_0x0143
        L_0x0122:
            java.lang.String r0 = "cgi"
            boolean r0 = r9.contains(r0)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0143
            if (r10 == 0) goto L_0x0136
            java.lang.String r0 = ","
            int r0 = r10.indexOf(r0)     // Catch:{ Throwable -> 0x01aa }
            r1 = -1
            if (r0 == r1) goto L_0x0136
            return
        L_0x0136:
            java.lang.String r0 = "4"
            java.lang.String r1 = r11.d()     // Catch:{ Throwable -> 0x01aa }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x0143
            return
        L_0x0143:
            com.autonavi.aps.amapapi.model.AMapLocationServer r0 = r8.a((java.lang.String) r9, (java.lang.StringBuilder) r10)     // Catch:{ Throwable -> 0x01aa }
            boolean r1 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r0)     // Catch:{ Throwable -> 0x01aa }
            if (r1 == 0) goto L_0x015d
            java.lang.String r0 = r0.toStr()     // Catch:{ Throwable -> 0x01aa }
            r1 = 3
            java.lang.String r1 = r11.toStr(r1)     // Catch:{ Throwable -> 0x01aa }
            boolean r0 = r0.equals(r1)     // Catch:{ Throwable -> 0x01aa }
            if (r0 == 0) goto L_0x015d
            return
        L_0x015d:
            long r0 = com.loc.fa.c()     // Catch:{ Throwable -> 0x01aa }
            r8.i = r0     // Catch:{ Throwable -> 0x01aa }
            com.loc.ei$a r0 = new com.loc.ei$a     // Catch:{ Throwable -> 0x01aa }
            r0.<init>()     // Catch:{ Throwable -> 0x01aa }
            r0.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r11)     // Catch:{ Throwable -> 0x01aa }
            boolean r1 = android.text.TextUtils.isEmpty(r10)     // Catch:{ Throwable -> 0x01aa }
            if (r1 == 0) goto L_0x0173
            r1 = 0
            goto L_0x0177
        L_0x0173:
            java.lang.String r1 = r10.toString()     // Catch:{ Throwable -> 0x01aa }
        L_0x0177:
            r0.a((java.lang.String) r1)     // Catch:{ Throwable -> 0x01aa }
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r1 = r8.f6585a     // Catch:{ Throwable -> 0x01aa }
            boolean r1 = r1.containsKey(r9)     // Catch:{ Throwable -> 0x01aa }
            if (r1 == 0) goto L_0x018e
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r1 = r8.f6585a     // Catch:{ Throwable -> 0x01aa }
            java.lang.Object r1 = r1.get(r9)     // Catch:{ Throwable -> 0x01aa }
            java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ Throwable -> 0x01aa }
            r1.add(r0)     // Catch:{ Throwable -> 0x01aa }
            goto L_0x019b
        L_0x018e:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Throwable -> 0x01aa }
            r1.<init>()     // Catch:{ Throwable -> 0x01aa }
            r1.add(r0)     // Catch:{ Throwable -> 0x01aa }
            java.util.Hashtable<java.lang.String, java.util.ArrayList<com.loc.ei$a>> r0 = r8.f6585a     // Catch:{ Throwable -> 0x01aa }
            r0.put(r9, r1)     // Catch:{ Throwable -> 0x01aa }
        L_0x019b:
            if (r13 == 0) goto L_0x01a9
            r8.a((java.lang.String) r9, (com.amap.api.location.AMapLocation) r11, (java.lang.StringBuilder) r10, (android.content.Context) r12)     // Catch:{ Throwable -> 0x01a1 }
            return
        L_0x01a1:
            r9 = move-exception
            java.lang.String r10 = "Cache"
            java.lang.String r11 = "add"
            com.loc.es.a(r9, r10, r11)     // Catch:{ Throwable -> 0x01aa }
        L_0x01a9:
            return
        L_0x01aa:
            r9 = move-exception
            java.lang.String r10 = "Cache"
            java.lang.String r11 = "add"
            com.loc.es.a(r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.a(java.lang.String, java.lang.StringBuilder, com.autonavi.aps.amapapi.model.AMapLocationServer, android.content.Context, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        if (r9.isOpen() != false) goto L_0x0069;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007d A[SYNTHETIC, Splitter:B:38:0x007d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(android.content.Context r9) {
        /*
            r8 = this;
            r8.c()     // Catch:{ Throwable -> 0x009a }
            r0 = 0
            r1 = 0
            if (r9 == 0) goto L_0x0091
            java.lang.String r2 = "hmdb"
            android.database.sqlite.SQLiteDatabase r9 = r9.openOrCreateDatabase(r2, r0, r1)     // Catch:{ Throwable -> 0x0072, all -> 0x006f }
            java.lang.String r2 = "hist"
            boolean r2 = com.loc.fa.a((android.database.sqlite.SQLiteDatabase) r9, (java.lang.String) r2)     // Catch:{ Throwable -> 0x006d }
            if (r2 != 0) goto L_0x0022
            if (r9 == 0) goto L_0x0091
            boolean r2 = r9.isOpen()     // Catch:{ Throwable -> 0x006d }
            if (r2 == 0) goto L_0x0091
            r9.close()     // Catch:{ Throwable -> 0x006d }
            goto L_0x0091
        L_0x0022:
            java.lang.String r2 = "time<?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Throwable -> 0x006d }
            long r4 = com.loc.fa.b()     // Catch:{ Throwable -> 0x006d }
            r6 = 86400000(0x5265c00, double:4.2687272E-316)
            long r4 = r4 - r6
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Throwable -> 0x006d }
            r3[r0] = r4     // Catch:{ Throwable -> 0x006d }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x004a }
            java.lang.String r5 = "hist"
            r4.<init>(r5)     // Catch:{ Throwable -> 0x004a }
            java.lang.String r5 = r8.k     // Catch:{ Throwable -> 0x004a }
            r4.append(r5)     // Catch:{ Throwable -> 0x004a }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x004a }
            r9.delete(r4, r2, r3)     // Catch:{ Throwable -> 0x004a }
            goto L_0x0061
        L_0x004a:
            r2 = move-exception
            java.lang.String r3 = "DB"
            java.lang.String r4 = "clearHist"
            com.loc.es.a(r2, r3, r4)     // Catch:{ Throwable -> 0x006d }
            java.lang.String r2 = r2.getMessage()     // Catch:{ Throwable -> 0x006d }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x006d }
            if (r3 != 0) goto L_0x0061
            java.lang.String r3 = "no such table"
            r2.contains(r3)     // Catch:{ Throwable -> 0x006d }
        L_0x0061:
            if (r9 == 0) goto L_0x0091
            boolean r2 = r9.isOpen()     // Catch:{ Throwable -> 0x009a }
            if (r2 == 0) goto L_0x0091
        L_0x0069:
            r9.close()     // Catch:{ Throwable -> 0x009a }
            goto L_0x0091
        L_0x006d:
            r2 = move-exception
            goto L_0x0074
        L_0x006f:
            r0 = move-exception
            r9 = r1
            goto L_0x0085
        L_0x0072:
            r2 = move-exception
            r9 = r1
        L_0x0074:
            java.lang.String r3 = "DB"
            java.lang.String r4 = "clearHist p2"
            com.loc.es.a(r2, r3, r4)     // Catch:{ all -> 0x0084 }
            if (r9 == 0) goto L_0x0091
            boolean r2 = r9.isOpen()     // Catch:{ Throwable -> 0x009a }
            if (r2 == 0) goto L_0x0091
            goto L_0x0069
        L_0x0084:
            r0 = move-exception
        L_0x0085:
            if (r9 == 0) goto L_0x0090
            boolean r1 = r9.isOpen()     // Catch:{ Throwable -> 0x009a }
            if (r1 == 0) goto L_0x0090
            r9.close()     // Catch:{ Throwable -> 0x009a }
        L_0x0090:
            throw r0     // Catch:{ Throwable -> 0x009a }
        L_0x0091:
            r8.j = r0     // Catch:{ Throwable -> 0x009a }
            r8.d = r1     // Catch:{ Throwable -> 0x009a }
            r0 = 0
            r8.n = r0     // Catch:{ Throwable -> 0x009a }
            return
        L_0x009a:
            r9 = move-exception
            java.lang.String r0 = "Cache"
            java.lang.String r1 = "destroy part"
            com.loc.es.a(r9, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ei.b(android.content.Context):void");
    }
}
