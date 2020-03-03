package com.ta.utdid2.b.a;

import com.ta.utdid2.b.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.WeakHashMap;

public class d {
    private static final Object b = new Object();

    /* renamed from: a  reason: collision with root package name */
    private File f8968a;

    /* renamed from: a  reason: collision with other field name */
    private final Object f43a = new Object();

    /* renamed from: a  reason: collision with other field name */
    private HashMap<File, a> f44a = new HashMap<>();

    public d(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.f8968a = new File(str);
    }

    private File a(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException("File " + str + " contains a path separator");
    }

    private File a() {
        File file;
        synchronized (this.f43a) {
            file = this.f8968a;
        }
        return file;
    }

    private File b(String str) {
        File a2 = a();
        return a(a2, str + ".xml");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processExcHandler(RegionMaker.java:1043)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:975)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    public com.ta.utdid2.b.a.b a(java.lang.String r5, int r6) {
        /*
            r4 = this;
            java.io.File r5 = r4.b((java.lang.String) r5)
            java.lang.Object r0 = b
            monitor-enter(r0)
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r1 = r4.f44a     // Catch:{ all -> 0x00ab }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x00ab }
            com.ta.utdid2.b.a.d$a r1 = (com.ta.utdid2.b.a.d.a) r1     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x0019
            boolean r2 = r1.c()     // Catch:{ all -> 0x00ab }
            if (r2 != 0) goto L_0x0019
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            return r1
        L_0x0019:
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            java.io.File r0 = a(r5)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x002a
            r5.delete()
            r0.renameTo(r5)
        L_0x002a:
            boolean r0 = r5.exists()
            r2 = 0
            if (r0 == 0) goto L_0x0087
            boolean r0 = r5.canRead()
            if (r0 == 0) goto L_0x0087
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ XmlPullParserException -> 0x0058, Exception -> 0x0051 }
            r0.<init>(r5)     // Catch:{ XmlPullParserException -> 0x0058, Exception -> 0x0051 }
            java.util.HashMap r3 = com.ta.utdid2.b.a.e.a(r0)     // Catch:{ XmlPullParserException -> 0x0059, Exception -> 0x0052, all -> 0x004c }
            r0.close()     // Catch:{ XmlPullParserException -> 0x004a, Exception -> 0x0048, all -> 0x004c }
            r0.close()     // Catch:{ Throwable -> 0x0046 }
        L_0x0046:
            r2 = r3
            goto L_0x0087
        L_0x0048:
            r2 = r3
            goto L_0x0052
        L_0x004a:
            r2 = r3
            goto L_0x0059
        L_0x004c:
            r5 = move-exception
            r2 = r0
            goto L_0x007c
        L_0x004f:
            r5 = move-exception
            goto L_0x007c
        L_0x0051:
            r0 = r2
        L_0x0052:
            if (r0 == 0) goto L_0x0087
            r0.close()     // Catch:{ Throwable -> 0x0087 }
            goto L_0x0087
        L_0x0058:
            r0 = r2
        L_0x0059:
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0073, all -> 0x006b }
            r3.<init>(r5)     // Catch:{ Exception -> 0x0073, all -> 0x006b }
            int r0 = r3.available()     // Catch:{ Exception -> 0x0074, all -> 0x0068 }
            byte[] r0 = new byte[r0]     // Catch:{ Exception -> 0x0074, all -> 0x0068 }
            r3.read(r0)     // Catch:{ Exception -> 0x0074, all -> 0x0068 }
            goto L_0x0076
        L_0x0068:
            r5 = move-exception
            r2 = r3
            goto L_0x006d
        L_0x006b:
            r5 = move-exception
            r2 = r0
        L_0x006d:
            if (r2 == 0) goto L_0x0072
            r2.close()     // Catch:{ Throwable -> 0x0072 }
        L_0x0072:
            throw r5     // Catch:{ all -> 0x004f }
        L_0x0073:
            r3 = r0
        L_0x0074:
            if (r3 == 0) goto L_0x0082
        L_0x0076:
            r3.close()     // Catch:{ Throwable -> 0x0082, all -> 0x007a }
            goto L_0x0082
        L_0x007a:
            r5 = move-exception
            r2 = r3
        L_0x007c:
            if (r2 == 0) goto L_0x0081
            r2.close()     // Catch:{ Throwable -> 0x0081 }
        L_0x0081:
            throw r5
        L_0x0082:
            if (r3 == 0) goto L_0x0087
            r3.close()     // Catch:{ Throwable -> 0x0087 }
        L_0x0087:
            java.lang.Object r3 = b
            monitor-enter(r3)
            if (r1 == 0) goto L_0x0092
            r1.a((java.util.Map) r2)     // Catch:{ all -> 0x0090 }
            goto L_0x00a7
        L_0x0090:
            r5 = move-exception
            goto L_0x00a9
        L_0x0092:
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r0 = r4.f44a     // Catch:{ all -> 0x0090 }
            java.lang.Object r0 = r0.get(r5)     // Catch:{ all -> 0x0090 }
            r1 = r0
            com.ta.utdid2.b.a.d$a r1 = (com.ta.utdid2.b.a.d.a) r1     // Catch:{ all -> 0x0090 }
            if (r1 != 0) goto L_0x00a7
            com.ta.utdid2.b.a.d$a r1 = new com.ta.utdid2.b.a.d$a     // Catch:{ all -> 0x0090 }
            r1.<init>(r5, r6, r2)     // Catch:{ all -> 0x0090 }
            java.util.HashMap<java.io.File, com.ta.utdid2.b.a.d$a> r6 = r4.f44a     // Catch:{ all -> 0x0090 }
            r6.put(r5, r1)     // Catch:{ all -> 0x0090 }
        L_0x00a7:
            monitor-exit(r3)     // Catch:{ all -> 0x0090 }
            return r1
        L_0x00a9:
            monitor-exit(r3)     // Catch:{ all -> 0x0090 }
            throw r5
        L_0x00ab:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ab }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.b.a.d.a(java.lang.String, int):com.ta.utdid2.b.a.b");
    }

    /* access modifiers changed from: private */
    public static File a(File file) {
        return new File(file.getPath() + ".bak");
    }

    private static final class a implements b {
        private static final Object c = new Object();

        /* renamed from: a  reason: collision with root package name */
        private Map f8969a;

        /* renamed from: a  reason: collision with other field name */
        private WeakHashMap<b.C0065b, Object> f45a;
        private final int b;

        /* renamed from: b  reason: collision with other field name */
        private final File f46b;

        /* renamed from: c  reason: collision with other field name */
        private final File f47c;
        private boolean g = false;

        a(File file, int i, Map map) {
            this.f46b = file;
            this.f47c = d.a(file);
            this.b = i;
            this.f8969a = map == null ? new HashMap() : map;
            this.f45a = new WeakHashMap<>();
        }

        /* renamed from: a  reason: collision with other method in class */
        public boolean m55a() {
            return this.f46b != null && new File(this.f46b.getAbsolutePath()).exists();
        }

        public void a(boolean z) {
            synchronized (this) {
                this.g = z;
            }
        }

        public boolean c() {
            boolean z;
            synchronized (this) {
                z = this.g;
            }
            return z;
        }

        public void a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.f8969a = map;
                }
            }
        }

        public Map<String, ?> getAll() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.f8969a);
            }
            return hashMap;
        }

        public String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.f8969a.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        public long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.f8969a.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        /* renamed from: com.ta.utdid2.b.a.d$a$a  reason: collision with other inner class name */
        public final class C0066a implements b.a {
            private final Map<String, Object> b = new HashMap();
            private boolean h = false;

            public C0066a() {
            }

            public b.a a(String str, String str2) {
                synchronized (this) {
                    this.b.put(str, str2);
                }
                return this;
            }

            public b.a a(String str, int i) {
                synchronized (this) {
                    this.b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            public b.a a(String str, long j) {
                synchronized (this) {
                    this.b.put(str, Long.valueOf(j));
                }
                return this;
            }

            public b.a a(String str, float f) {
                synchronized (this) {
                    this.b.put(str, Float.valueOf(f));
                }
                return this;
            }

            public b.a a(String str, boolean z) {
                synchronized (this) {
                    this.b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            public b.a a(String str) {
                synchronized (this) {
                    this.b.put(str, this);
                }
                return this;
            }

            public b.a b() {
                synchronized (this) {
                    this.h = true;
                }
                return this;
            }

            public boolean commit() {
                boolean z;
                ArrayList arrayList;
                HashSet<b.C0065b> hashSet;
                boolean a2;
                synchronized (d.a()) {
                    z = a.a(a.this).size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet<>(a.a(a.this).keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.h) {
                            a.a(a.this).clear();
                            this.h = false;
                        }
                        for (Map.Entry next : this.b.entrySet()) {
                            String str = (String) next.getKey();
                            Object value = next.getValue();
                            if (value == this) {
                                a.a(a.this).remove(str);
                            } else {
                                a.a(a.this).put(str, value);
                            }
                            if (z) {
                                arrayList.add(str);
                            }
                        }
                        this.b.clear();
                    }
                    a2 = a.a(a.this);
                    if (a2) {
                        a.this.a(true);
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        String str2 = (String) arrayList.get(size);
                        for (b.C0065b bVar : hashSet) {
                            if (bVar != null) {
                                bVar.a(a.this, str2);
                            }
                        }
                    }
                }
                return a2;
            }
        }

        public b.a a() {
            return new C0066a();
        }

        private FileOutputStream a(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException unused) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException unused2) {
                    return null;
                }
            }
        }

        private boolean d() {
            if (this.f46b.exists()) {
                if (this.f47c.exists()) {
                    this.f46b.delete();
                } else if (!this.f46b.renameTo(this.f47c)) {
                    return false;
                }
            }
            try {
                FileOutputStream a2 = a(this.f46b);
                if (a2 == null) {
                    return false;
                }
                e.a(this.f8969a, (OutputStream) a2);
                a2.close();
                this.f47c.delete();
                return true;
            } catch (Exception unused) {
                if (this.f46b.exists()) {
                    this.f46b.delete();
                }
                return false;
            }
        }
    }
}
