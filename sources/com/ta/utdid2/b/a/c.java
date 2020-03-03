package com.ta.utdid2.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.ta.utdid2.a.a.f;
import com.ta.utdid2.b.a.b;
import java.io.File;
import java.util.Map;

public class c {

    /* renamed from: a  reason: collision with root package name */
    private SharedPreferences.Editor f8967a = null;

    /* renamed from: a  reason: collision with other field name */
    private SharedPreferences f38a = null;

    /* renamed from: a  reason: collision with other field name */
    private b.a f39a = null;

    /* renamed from: a  reason: collision with other field name */
    private b f40a = null;

    /* renamed from: a  reason: collision with other field name */
    private d f41a = null;

    /* renamed from: a  reason: collision with other field name */
    private String f42a = "";
    private String b = "";
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private Context mContext = null;

    /* JADX WARNING: Removed duplicated region for block: B:80:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:99:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public c(android.content.Context r9, java.lang.String r10, java.lang.String r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            r8.<init>()
            java.lang.String r0 = ""
            r8.f42a = r0
            java.lang.String r0 = ""
            r8.b = r0
            r0 = 0
            r8.c = r0
            r8.d = r0
            r8.e = r0
            r1 = 0
            r8.f38a = r1
            r8.f40a = r1
            r8.f8967a = r1
            r8.f39a = r1
            r8.mContext = r1
            r8.f41a = r1
            r8.f = r0
            r8.c = r12
            r8.f = r13
            r8.f42a = r11
            r8.b = r10
            r8.mContext = r9
            r2 = 0
            if (r9 == 0) goto L_0x003e
            android.content.SharedPreferences r12 = r9.getSharedPreferences(r11, r0)
            r8.f38a = r12
            android.content.SharedPreferences r12 = r8.f38a
            java.lang.String r4 = "t"
            long r4 = r12.getLong(r4, r2)
            goto L_0x003f
        L_0x003e:
            r4 = r2
        L_0x003f:
            java.lang.String r12 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x0044 }
            goto L_0x0049
        L_0x0044:
            r12 = move-exception
            r12.printStackTrace()
            r12 = r1
        L_0x0049:
            boolean r1 = com.ta.utdid2.a.a.f.isEmpty(r12)
            if (r1 == 0) goto L_0x0054
            r8.e = r0
            r8.d = r0
            goto L_0x0073
        L_0x0054:
            java.lang.String r1 = "mounted"
            boolean r1 = r12.equals(r1)
            r6 = 1
            if (r1 == 0) goto L_0x0062
            r8.e = r6
            r8.d = r6
            goto L_0x0073
        L_0x0062:
            java.lang.String r1 = "mounted_ro"
            boolean r12 = r12.equals(r1)
            if (r12 == 0) goto L_0x006f
            r8.d = r6
            r8.e = r0
            goto L_0x0073
        L_0x006f:
            r8.e = r0
            r8.d = r0
        L_0x0073:
            boolean r12 = r8.d
            if (r12 != 0) goto L_0x007b
            boolean r12 = r8.e
            if (r12 == 0) goto L_0x015e
        L_0x007b:
            if (r9 == 0) goto L_0x015e
            boolean r12 = com.ta.utdid2.a.a.f.isEmpty(r10)
            if (r12 != 0) goto L_0x015e
            com.ta.utdid2.b.a.d r10 = r8.a((java.lang.String) r10)
            r8.f41a = r10
            com.ta.utdid2.b.a.d r10 = r8.f41a
            if (r10 == 0) goto L_0x015e
            com.ta.utdid2.b.a.d r10 = r8.f41a     // Catch:{ Exception -> 0x015e }
            com.ta.utdid2.b.a.b r10 = r10.a((java.lang.String) r11, (int) r0)     // Catch:{ Exception -> 0x015e }
            r8.f40a = r10     // Catch:{ Exception -> 0x015e }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015e }
            java.lang.String r12 = "t"
            long r6 = r10.getLong(r12, r2)     // Catch:{ Exception -> 0x015e }
            if (r13 != 0) goto L_0x00dc
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 <= 0) goto L_0x00b4
            android.content.SharedPreferences r9 = r8.f38a     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015f }
            r8.a((android.content.SharedPreferences) r9, (com.ta.utdid2.b.a.b) r10)     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.d r9 = r8.f41a     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.b r9 = r9.a((java.lang.String) r11, (int) r0)     // Catch:{ Exception -> 0x015f }
            r8.f40a = r9     // Catch:{ Exception -> 0x015f }
            goto L_0x015f
        L_0x00b4:
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 >= 0) goto L_0x00c7
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015f }
            android.content.SharedPreferences r12 = r8.f38a     // Catch:{ Exception -> 0x015f }
            r8.a((com.ta.utdid2.b.a.b) r10, (android.content.SharedPreferences) r12)     // Catch:{ Exception -> 0x015f }
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r11, r0)     // Catch:{ Exception -> 0x015f }
            r8.f38a = r9     // Catch:{ Exception -> 0x015f }
            goto L_0x015f
        L_0x00c7:
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x015f
            android.content.SharedPreferences r9 = r8.f38a     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015f }
            r8.a((android.content.SharedPreferences) r9, (com.ta.utdid2.b.a.b) r10)     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.d r9 = r8.f41a     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.b r9 = r9.a((java.lang.String) r11, (int) r0)     // Catch:{ Exception -> 0x015f }
            r8.f40a = r9     // Catch:{ Exception -> 0x015f }
            goto L_0x015f
        L_0x00dc:
            android.content.SharedPreferences r10 = r8.f38a     // Catch:{ Exception -> 0x015f }
            java.lang.String r12 = "t2"
            long r12 = r10.getLong(r12, r2)     // Catch:{ Exception -> 0x015f }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015c }
            java.lang.String r1 = "t2"
            long r4 = r10.getLong(r1, r2)     // Catch:{ Exception -> 0x015c }
            int r10 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r10 >= 0) goto L_0x0104
            int r10 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r10 <= 0) goto L_0x0104
            android.content.SharedPreferences r9 = r8.f38a     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015b }
            r8.a((android.content.SharedPreferences) r9, (com.ta.utdid2.b.a.b) r10)     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.d r9 = r8.f41a     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.b r9 = r9.a((java.lang.String) r11, (int) r0)     // Catch:{ Exception -> 0x015b }
            r8.f40a = r9     // Catch:{ Exception -> 0x015b }
            goto L_0x015b
        L_0x0104:
            int r10 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r10 <= 0) goto L_0x011a
            int r10 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r10 <= 0) goto L_0x011a
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015b }
            android.content.SharedPreferences r1 = r8.f38a     // Catch:{ Exception -> 0x015b }
            r8.a((com.ta.utdid2.b.a.b) r10, (android.content.SharedPreferences) r1)     // Catch:{ Exception -> 0x015b }
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r11, r0)     // Catch:{ Exception -> 0x015b }
            r8.f38a = r9     // Catch:{ Exception -> 0x015b }
            goto L_0x015b
        L_0x011a:
            int r10 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r10 != 0) goto L_0x0130
            int r10 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r10 <= 0) goto L_0x0130
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015b }
            android.content.SharedPreferences r1 = r8.f38a     // Catch:{ Exception -> 0x015b }
            r8.a((com.ta.utdid2.b.a.b) r10, (android.content.SharedPreferences) r1)     // Catch:{ Exception -> 0x015b }
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r11, r0)     // Catch:{ Exception -> 0x015b }
            r8.f38a = r9     // Catch:{ Exception -> 0x015b }
            goto L_0x015b
        L_0x0130:
            int r9 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r9 != 0) goto L_0x0148
            int r9 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r9 <= 0) goto L_0x0148
            android.content.SharedPreferences r9 = r8.f38a     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015b }
            r8.a((android.content.SharedPreferences) r9, (com.ta.utdid2.b.a.b) r10)     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.d r9 = r8.f41a     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.b r9 = r9.a((java.lang.String) r11, (int) r0)     // Catch:{ Exception -> 0x015b }
            r8.f40a = r9     // Catch:{ Exception -> 0x015b }
            goto L_0x015b
        L_0x0148:
            int r9 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r9 != 0) goto L_0x015b
            android.content.SharedPreferences r9 = r8.f38a     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.b r10 = r8.f40a     // Catch:{ Exception -> 0x015b }
            r8.a((android.content.SharedPreferences) r9, (com.ta.utdid2.b.a.b) r10)     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.d r9 = r8.f41a     // Catch:{ Exception -> 0x015b }
            com.ta.utdid2.b.a.b r9 = r9.a((java.lang.String) r11, (int) r0)     // Catch:{ Exception -> 0x015b }
            r8.f40a = r9     // Catch:{ Exception -> 0x015b }
        L_0x015b:
            r6 = r4
        L_0x015c:
            r4 = r12
            goto L_0x015f
        L_0x015e:
            r6 = r2
        L_0x015f:
            int r9 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r9 != 0) goto L_0x016b
            int r9 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r9 != 0) goto L_0x01a3
            int r9 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r9 != 0) goto L_0x01a3
        L_0x016b:
            long r9 = java.lang.System.currentTimeMillis()
            boolean r11 = r8.f
            if (r11 == 0) goto L_0x017f
            boolean r11 = r8.f
            if (r11 == 0) goto L_0x01a3
            int r11 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r11 != 0) goto L_0x01a3
            int r11 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r11 != 0) goto L_0x01a3
        L_0x017f:
            android.content.SharedPreferences r11 = r8.f38a
            if (r11 == 0) goto L_0x0191
            android.content.SharedPreferences r11 = r8.f38a
            android.content.SharedPreferences$Editor r11 = r11.edit()
            java.lang.String r12 = "t2"
            r11.putLong(r12, r9)
            r11.commit()
        L_0x0191:
            com.ta.utdid2.b.a.b r11 = r8.f40a     // Catch:{ Exception -> 0x01a3 }
            if (r11 == 0) goto L_0x01a3
            com.ta.utdid2.b.a.b r11 = r8.f40a     // Catch:{ Exception -> 0x01a3 }
            com.ta.utdid2.b.a.b$a r11 = r11.a()     // Catch:{ Exception -> 0x01a3 }
            java.lang.String r12 = "t2"
            r11.a((java.lang.String) r12, (long) r9)     // Catch:{ Exception -> 0x01a3 }
            r11.commit()     // Catch:{ Exception -> 0x01a3 }
        L_0x01a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.c.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
    }

    private d a(String str) {
        File a2 = a(str);
        if (a2 == null) {
            return null;
        }
        this.f41a = new d(a2.getAbsolutePath());
        return this.f41a;
    }

    /* renamed from: a  reason: collision with other method in class */
    private File m53a(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{externalStorageDirectory.getAbsolutePath(), File.separator, str}));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    private void a(SharedPreferences sharedPreferences, b bVar) {
        b.a a2;
        if (sharedPreferences != null && bVar != null && (a2 = bVar.a()) != null) {
            a2.b();
            for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
                String str = (String) next.getKey();
                Object value = next.getValue();
                if (value instanceof String) {
                    a2.a(str, (String) value);
                } else if (value instanceof Integer) {
                    a2.a(str, ((Integer) value).intValue());
                } else if (value instanceof Long) {
                    a2.a(str, ((Long) value).longValue());
                } else if (value instanceof Float) {
                    a2.a(str, ((Float) value).floatValue());
                } else if (value instanceof Boolean) {
                    a2.a(str, ((Boolean) value).booleanValue());
                }
            }
            a2.commit();
        }
    }

    private void a(b bVar, SharedPreferences sharedPreferences) {
        SharedPreferences.Editor edit;
        if (bVar != null && sharedPreferences != null && (edit = sharedPreferences.edit()) != null) {
            edit.clear();
            for (Map.Entry next : bVar.getAll().entrySet()) {
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

    /* renamed from: b  reason: collision with other method in class */
    private boolean m54b() {
        if (this.f40a == null) {
            return false;
        }
        boolean a2 = this.f40a.a();
        if (!a2) {
            commit();
        }
        return a2;
    }

    private void b() {
        if (this.f8967a == null && this.f38a != null) {
            this.f8967a = this.f38a.edit();
        }
        if (this.e && this.f39a == null && this.f40a != null) {
            this.f39a = this.f40a.a();
        }
        b();
    }

    public void putString(String str, String str2) {
        if (!f.isEmpty(str) && !str.equals("t")) {
            b();
            if (this.f8967a != null) {
                this.f8967a.putString(str, str2);
            }
            if (this.f39a != null) {
                this.f39a.a(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!f.isEmpty(str) && !str.equals("t")) {
            b();
            if (this.f8967a != null) {
                this.f8967a.remove(str);
            }
            if (this.f39a != null) {
                this.f39a.a(str);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean commit() {
        /*
            r5 = this;
            long r0 = java.lang.System.currentTimeMillis()
            android.content.SharedPreferences$Editor r2 = r5.f8967a
            r3 = 0
            if (r2 == 0) goto L_0x0022
            boolean r2 = r5.f
            if (r2 != 0) goto L_0x0018
            android.content.SharedPreferences r2 = r5.f38a
            if (r2 == 0) goto L_0x0018
            android.content.SharedPreferences$Editor r2 = r5.f8967a
            java.lang.String r4 = "t"
            r2.putLong(r4, r0)
        L_0x0018:
            android.content.SharedPreferences$Editor r0 = r5.f8967a
            boolean r0 = r0.commit()
            if (r0 != 0) goto L_0x0022
            r0 = 0
            goto L_0x0023
        L_0x0022:
            r0 = 1
        L_0x0023:
            android.content.SharedPreferences r1 = r5.f38a
            if (r1 == 0) goto L_0x0035
            android.content.Context r1 = r5.mContext
            if (r1 == 0) goto L_0x0035
            android.content.Context r1 = r5.mContext
            java.lang.String r2 = r5.f42a
            android.content.SharedPreferences r1 = r1.getSharedPreferences(r2, r3)
            r5.f38a = r1
        L_0x0035:
            r1 = 0
            java.lang.String r2 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x003c }
            r1 = r2
            goto L_0x0040
        L_0x003c:
            r2 = move-exception
            r2.printStackTrace()
        L_0x0040:
            boolean r2 = com.ta.utdid2.a.a.f.isEmpty(r1)
            if (r2 != 0) goto L_0x00ad
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x008b
            com.ta.utdid2.b.a.b r2 = r5.f40a
            if (r2 != 0) goto L_0x007e
            java.lang.String r2 = r5.b
            com.ta.utdid2.b.a.d r2 = r5.a((java.lang.String) r2)
            if (r2 == 0) goto L_0x008b
            java.lang.String r4 = r5.f42a
            com.ta.utdid2.b.a.b r2 = r2.a((java.lang.String) r4, (int) r3)
            r5.f40a = r2
            boolean r2 = r5.f
            if (r2 != 0) goto L_0x006e
            android.content.SharedPreferences r2 = r5.f38a
            com.ta.utdid2.b.a.b r4 = r5.f40a
            r5.a((android.content.SharedPreferences) r2, (com.ta.utdid2.b.a.b) r4)
            goto L_0x0075
        L_0x006e:
            com.ta.utdid2.b.a.b r2 = r5.f40a
            android.content.SharedPreferences r4 = r5.f38a
            r5.a((com.ta.utdid2.b.a.b) r2, (android.content.SharedPreferences) r4)
        L_0x0075:
            com.ta.utdid2.b.a.b r2 = r5.f40a
            com.ta.utdid2.b.a.b$a r2 = r2.a()
            r5.f39a = r2
            goto L_0x008b
        L_0x007e:
            com.ta.utdid2.b.a.b$a r2 = r5.f39a
            if (r2 == 0) goto L_0x008b
            com.ta.utdid2.b.a.b$a r2 = r5.f39a
            boolean r2 = r2.commit()
            if (r2 != 0) goto L_0x008b
            r0 = 0
        L_0x008b:
            java.lang.String r2 = "mounted"
            boolean r2 = r1.equals(r2)
            if (r2 != 0) goto L_0x009f
            java.lang.String r2 = "mounted_ro"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x00ad
            com.ta.utdid2.b.a.b r1 = r5.f40a
            if (r1 == 0) goto L_0x00ad
        L_0x009f:
            com.ta.utdid2.b.a.d r1 = r5.f41a     // Catch:{ Exception -> 0x00ad }
            if (r1 == 0) goto L_0x00ad
            com.ta.utdid2.b.a.d r1 = r5.f41a     // Catch:{ Exception -> 0x00ad }
            java.lang.String r2 = r5.f42a     // Catch:{ Exception -> 0x00ad }
            com.ta.utdid2.b.a.b r1 = r1.a((java.lang.String) r2, (int) r3)     // Catch:{ Exception -> 0x00ad }
            r5.f40a = r1     // Catch:{ Exception -> 0x00ad }
        L_0x00ad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.c.commit():boolean");
    }

    public String getString(String str) {
        b();
        if (this.f38a != null) {
            String string = this.f38a.getString(str, "");
            if (!f.isEmpty(string)) {
                return string;
            }
        }
        return this.f40a != null ? this.f40a.getString(str, "") : "";
    }
}
