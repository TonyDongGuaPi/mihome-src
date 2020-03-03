package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.loc.dy;
import java.io.File;
import java.util.Map;

public final class dz {

    /* renamed from: a  reason: collision with root package name */
    private String f6571a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private SharedPreferences f;
    private dy g;
    private SharedPreferences.Editor h;
    private dy.a i;
    private Context j;
    private ea k;
    private boolean l;

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0083 A[SYNTHETIC, Splitter:B:22:0x0083] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0114  */
    /* JADX WARNING: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public dz(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        /*
            r10 = this;
            r10.<init>()
            java.lang.String r0 = ""
            r10.f6571a = r0
            java.lang.String r0 = ""
            r10.b = r0
            r0 = 0
            r10.c = r0
            r10.d = r0
            r10.e = r0
            r1 = 0
            r10.f = r1
            r10.g = r1
            r10.h = r1
            r10.i = r1
            r10.j = r1
            r10.k = r1
            r10.l = r0
            r10.c = r0
            r1 = 1
            r10.l = r1
            r10.f6571a = r13
            r10.b = r12
            r10.j = r11
            r2 = 0
            if (r11 == 0) goto L_0x0040
            android.content.SharedPreferences r4 = r11.getSharedPreferences(r13, r0)
            r10.f = r4
            android.content.SharedPreferences r4 = r10.f
            java.lang.String r5 = "t"
            long r4 = r4.getLong(r5, r2)
            goto L_0x0041
        L_0x0040:
            r4 = r2
        L_0x0041:
            java.lang.String r6 = android.os.Environment.getExternalStorageState()
            boolean r7 = com.loc.dw.a(r6)
            if (r7 != 0) goto L_0x0065
            java.lang.String r7 = "mounted"
            boolean r7 = r6.equals(r7)
            if (r7 == 0) goto L_0x0058
            r10.e = r1
            r10.d = r1
            goto L_0x0069
        L_0x0058:
            java.lang.String r7 = "mounted_ro"
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L_0x0065
            r10.d = r1
            r10.e = r0
            goto L_0x0069
        L_0x0065:
            r10.e = r0
            r10.d = r0
        L_0x0069:
            boolean r1 = r10.d
            if (r1 != 0) goto L_0x0071
            boolean r1 = r10.e
            if (r1 == 0) goto L_0x0107
        L_0x0071:
            if (r11 == 0) goto L_0x0107
            boolean r1 = com.loc.dw.a(r12)
            if (r1 != 0) goto L_0x0107
            com.loc.ea r12 = r10.b(r12)
            r10.k = r12
            com.loc.ea r12 = r10.k
            if (r12 == 0) goto L_0x0107
            com.loc.ea r12 = r10.k     // Catch:{ Exception -> 0x0107 }
            com.loc.dy r12 = r12.a((java.lang.String) r13)     // Catch:{ Exception -> 0x0107 }
            r10.g = r12     // Catch:{ Exception -> 0x0107 }
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0107 }
            java.lang.String r1 = "t"
            long r6 = r12.a(r1)     // Catch:{ Exception -> 0x0107 }
            android.content.SharedPreferences r12 = r10.f     // Catch:{ Exception -> 0x0108 }
            java.lang.String r1 = "t2"
            long r8 = r12.getLong(r1, r2)     // Catch:{ Exception -> 0x0108 }
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0105 }
            java.lang.String r1 = "t2"
            long r4 = r12.a(r1)     // Catch:{ Exception -> 0x0105 }
            int r12 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r12 >= 0) goto L_0x00be
            int r12 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r12 <= 0) goto L_0x00be
            android.content.SharedPreferences r11 = r10.f     // Catch:{ Exception -> 0x0104 }
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0104 }
            a((android.content.SharedPreferences) r11, (com.loc.dy) r12)     // Catch:{ Exception -> 0x0104 }
            com.loc.ea r11 = r10.k     // Catch:{ Exception -> 0x0104 }
        L_0x00b7:
            com.loc.dy r11 = r11.a((java.lang.String) r13)     // Catch:{ Exception -> 0x0104 }
            r10.g = r11     // Catch:{ Exception -> 0x0104 }
            goto L_0x0104
        L_0x00be:
            int r12 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r12 <= 0) goto L_0x00d4
            int r12 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r12 <= 0) goto L_0x00d4
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0104 }
            android.content.SharedPreferences r1 = r10.f     // Catch:{ Exception -> 0x0104 }
            a((com.loc.dy) r12, (android.content.SharedPreferences) r1)     // Catch:{ Exception -> 0x0104 }
        L_0x00cd:
            android.content.SharedPreferences r11 = r11.getSharedPreferences(r13, r0)     // Catch:{ Exception -> 0x0104 }
            r10.f = r11     // Catch:{ Exception -> 0x0104 }
            goto L_0x0104
        L_0x00d4:
            int r12 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r12 != 0) goto L_0x00e4
            int r12 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r12 <= 0) goto L_0x00e4
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0104 }
            android.content.SharedPreferences r1 = r10.f     // Catch:{ Exception -> 0x0104 }
            a((com.loc.dy) r12, (android.content.SharedPreferences) r1)     // Catch:{ Exception -> 0x0104 }
            goto L_0x00cd
        L_0x00e4:
            int r11 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r11 != 0) goto L_0x00f6
            int r11 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r11 <= 0) goto L_0x00f6
            android.content.SharedPreferences r11 = r10.f     // Catch:{ Exception -> 0x0104 }
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0104 }
            a((android.content.SharedPreferences) r11, (com.loc.dy) r12)     // Catch:{ Exception -> 0x0104 }
            com.loc.ea r11 = r10.k     // Catch:{ Exception -> 0x0104 }
            goto L_0x00b7
        L_0x00f6:
            int r11 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r11 != 0) goto L_0x0104
            android.content.SharedPreferences r11 = r10.f     // Catch:{ Exception -> 0x0104 }
            com.loc.dy r12 = r10.g     // Catch:{ Exception -> 0x0104 }
            a((android.content.SharedPreferences) r11, (com.loc.dy) r12)     // Catch:{ Exception -> 0x0104 }
            com.loc.ea r11 = r10.k     // Catch:{ Exception -> 0x0104 }
            goto L_0x00b7
        L_0x0104:
            r6 = r4
        L_0x0105:
            r4 = r8
            goto L_0x0108
        L_0x0107:
            r6 = r2
        L_0x0108:
            int r11 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r11 != 0) goto L_0x0114
            int r11 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r11 != 0) goto L_0x014e
            int r11 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r11 != 0) goto L_0x014e
        L_0x0114:
            long r11 = java.lang.System.currentTimeMillis()
            boolean r13 = r10.l
            if (r13 == 0) goto L_0x0128
            boolean r13 = r10.l
            if (r13 == 0) goto L_0x014e
            int r13 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r13 != 0) goto L_0x014e
            int r13 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r13 != 0) goto L_0x014e
        L_0x0128:
            android.content.SharedPreferences r13 = r10.f
            if (r13 == 0) goto L_0x013b
            android.content.SharedPreferences r13 = r10.f
            android.content.SharedPreferences$Editor r13 = r13.edit()
            java.lang.String r0 = "t2"
            r13.putLong(r0, r11)
            r13.commit()
        L_0x013b:
            com.loc.dy r13 = r10.g     // Catch:{ Exception -> 0x014e }
            if (r13 == 0) goto L_0x014e
            com.loc.dy r13 = r10.g     // Catch:{ Exception -> 0x014e }
            com.loc.dy$a r13 = r13.c()     // Catch:{ Exception -> 0x014e }
            java.lang.String r0 = "t2"
            r13.a((java.lang.String) r0, (long) r11)     // Catch:{ Exception -> 0x014e }
            r13.b()     // Catch:{ Exception -> 0x014e }
        L_0x014e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.<init>(android.content.Context, java.lang.String, java.lang.String):void");
    }

    private static void a(SharedPreferences sharedPreferences, dy dyVar) {
        if (sharedPreferences != null && dyVar != null) {
            dy.a c2 = dyVar.c();
            c2.a();
            for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                String str = (String) next.getKey();
                Object value = next.getValue();
                if (value instanceof String) {
                    c2.a(str, (String) value);
                } else if (value instanceof Integer) {
                    c2.a(str, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    c2.a(str, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    c2.a(str, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    c2.a(str, ((Boolean) value).booleanValue());
                }
            }
            c2.b();
        }
    }

    private static void a(dy dyVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (dyVar != null && sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.clear();
            for (Map.Entry next : dyVar.b().entrySet()) {
                String str = (String) next.getKey();
                Object value = next.getValue();
                if (value instanceof String) {
                    edit.putString(str, (String) value);
                } else if (value instanceof Integer) {
                    edit.putInt(str, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    edit.putLong(str, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    edit.putFloat(str, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    edit.putBoolean(str, ((Boolean) value).booleanValue());
                }
            }
            edit.commit();
        }
    }

    private ea b(String str) {
        File file;
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            file = new File(String.format("%s%s%s", new Object[]{externalStorageDirectory.getAbsolutePath(), File.separator, str}));
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            file = null;
        }
        if (file == null) {
            return null;
        }
        this.k = new ea(file.getAbsolutePath());
        return this.k;
    }

    private boolean b() {
        if (this.g == null) {
            return false;
        }
        boolean a2 = this.g.a();
        if (!a2) {
            a();
        }
        return a2;
    }

    private void c() {
        if (this.h == null && this.f != null) {
            this.h = this.f.edit();
        }
        if (this.e && this.i == null && this.g != null) {
            this.i = this.g.c();
        }
        b();
    }

    public final String a(String str) {
        b();
        if (this.f != null) {
            String string = this.f.getString(str, "");
            if (!dw.a(string)) {
                return string;
            }
        }
        return this.g != null ? this.g.a(str, "") : "";
    }

    public final void a(String str, long j2) {
        if (!dw.a(str) && !str.equals("t")) {
            c();
            if (this.h != null) {
                this.h.putLong(str, j2);
            }
            if (this.i != null) {
                this.i.a(str, j2);
            }
        }
    }

    public final void a(String str, String str2) {
        if (!dw.a(str) && !str.equals("t")) {
            c();
            if (this.h != null) {
                this.h.putString(str, str2);
            }
            if (this.i != null) {
                this.i.a(str, str2);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean a() {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r5.h
            r3 = 0
            if (r2 == 0) goto L_0x0023
            boolean r2 = r5.l
            if (r2 != 0) goto L_0x0019
            android.content.SharedPreferences r2 = r5.f
            if (r2 == 0) goto L_0x0019
            android.content.SharedPreferences$Editor r2 = r5.h
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L_0x0019:
            android.content.SharedPreferences$Editor r0 = r5.h
            boolean r0 = r0.commit()
            if (r0 != 0) goto L_0x0023
            r0 = 0
            goto L_0x0024
        L_0x0023:
            r0 = 1
        L_0x0024:
            android.content.SharedPreferences r1 = r5.f
            if (r1 == 0) goto L_0x0036
            android.content.Context r1 = r5.j
            if (r1 == 0) goto L_0x0036
            android.content.Context r1 = r5.j
            java.lang.String r2 = r5.f6571a
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r5.f = r1
        L_0x0036:
            java.lang.String r1 = android.os.Environment.getExternalStorageState()
            boolean r2 = com.loc.dw.a(r1)
            if (r2 != 0) goto L_0x00a7
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0085
            com.loc.dy r2 = r5.g
            if (r2 != 0) goto L_0x0078
            java.lang.String r2 = r5.b
            com.loc.ea r2 = r5.b(r2)
            if (r2 == 0) goto L_0x0085
            java.lang.String r3 = r5.f6571a
            com.loc.dy r2 = r2.a((java.lang.String) r3)
            r5.g = r2
            boolean r2 = r5.l
            if (r2 != 0) goto L_0x0068
            android.content.SharedPreferences r2 = r5.f
            com.loc.dy r3 = r5.g
            a((android.content.SharedPreferences) r2, (com.loc.dy) r3)
            goto L_0x006f
        L_0x0068:
            com.loc.dy r2 = r5.g
            android.content.SharedPreferences r3 = r5.f
            a((com.loc.dy) r2, (android.content.SharedPreferences) r3)
        L_0x006f:
            com.loc.dy r2 = r5.g
            com.loc.dy$a r2 = r2.c()
            r5.i = r2
            goto L_0x0085
        L_0x0078:
            com.loc.dy$a r2 = r5.i
            if (r2 == 0) goto L_0x0085
            com.loc.dy$a r2 = r5.i
            boolean r2 = r2.b()
            if (r2 != 0) goto L_0x0085
            r0 = 0
        L_0x0085:
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x0099
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00a7
            com.loc.dy r1 = r5.g
            if (r1 == 0) goto L_0x00a7
        L_0x0099:
            com.loc.ea r1 = r5.k     // Catch:{ Exception -> 0x00a7 }
            if (r1 == 0) goto L_0x00a7
            com.loc.ea r1 = r5.k     // Catch:{ Exception -> 0x00a7 }
            java.lang.String r2 = r5.f6571a     // Catch:{ Exception -> 0x00a7 }
            com.loc.dy r1 = r1.a((java.lang.String) r2)     // Catch:{ Exception -> 0x00a7 }
            r5.g = r1     // Catch:{ Exception -> 0x00a7 }
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.dz.a():boolean");
    }
}
