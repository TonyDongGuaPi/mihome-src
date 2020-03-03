package com.loc;

import com.loc.dy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public final class ea {
    /* access modifiers changed from: private */
    public static final Object c = new Object();

    /* renamed from: a  reason: collision with root package name */
    private final Object f6572a = new Object();
    private File b;
    private HashMap<File, a> d = new HashMap<>();

    private static final class a implements dy {
        private static final Object f = new Object();

        /* renamed from: a  reason: collision with root package name */
        private final File f6573a;
        private final File b;
        private final int c;
        /* access modifiers changed from: private */
        public Map d;
        private boolean e = false;
        /* access modifiers changed from: private */
        public WeakHashMap<Object, Object> g;

        /* renamed from: com.loc.ea$a$a  reason: collision with other inner class name */
        public final class C0058a implements dy.a {
            private final Map<String, Object> b = new HashMap();
            private boolean c = false;

            public C0058a() {
            }

            public final dy.a a() {
                synchronized (this) {
                    this.c = true;
                }
                return this;
            }

            public final dy.a a(String str, float f) {
                synchronized (this) {
                    this.b.put(str, Float.valueOf(f));
                }
                return this;
            }

            public final dy.a a(String str, int i) {
                synchronized (this) {
                    this.b.put(str, Integer.valueOf(i));
                }
                return this;
            }

            public final dy.a a(String str, long j) {
                synchronized (this) {
                    this.b.put(str, Long.valueOf(j));
                }
                return this;
            }

            public final dy.a a(String str, String str2) {
                synchronized (this) {
                    this.b.put(str, str2);
                }
                return this;
            }

            public final dy.a a(String str, boolean z) {
                synchronized (this) {
                    this.b.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            public final boolean b() {
                boolean z;
                ArrayList arrayList;
                HashSet hashSet;
                boolean c2;
                synchronized (ea.c) {
                    z = a.this.g.size() > 0;
                    arrayList = null;
                    if (z) {
                        arrayList = new ArrayList();
                        hashSet = new HashSet(a.this.g.keySet());
                    } else {
                        hashSet = null;
                    }
                    synchronized (this) {
                        if (this.c) {
                            a.this.d.clear();
                            this.c = false;
                        }
                        for (Map.Entry next : this.b.entrySet()) {
                            String str = (String) next.getKey();
                            Object value = next.getValue();
                            if (value == this) {
                                a.this.d.remove(str);
                            } else {
                                a.this.d.put(str, value);
                            }
                            if (z) {
                                arrayList.add(str);
                            }
                        }
                        this.b.clear();
                    }
                    c2 = a.this.f();
                    if (c2) {
                        a.this.d();
                    }
                }
                if (z) {
                    for (int size = arrayList.size() - 1; size >= 0; size--) {
                        arrayList.get(size);
                        Iterator it = hashSet.iterator();
                        while (it.hasNext()) {
                            it.next();
                        }
                    }
                }
                return c2;
            }
        }

        a(File file, Map map) {
            this.f6573a = file;
            this.b = ea.b(file);
            this.c = 0;
            this.d = map == null ? new HashMap() : map;
            this.g = new WeakHashMap<>();
        }

        private static FileOutputStream a(File file) {
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

        /* access modifiers changed from: private */
        public boolean f() {
            if (this.f6573a.exists()) {
                if (this.b.exists()) {
                    this.f6573a.delete();
                } else if (!this.f6573a.renameTo(this.b)) {
                    return false;
                }
            }
            try {
                FileOutputStream a2 = a(this.f6573a);
                if (a2 == null) {
                    return false;
                }
                Map map = this.d;
                dx dxVar = new dx();
                dxVar.setOutput(a2, "utf-8");
                dxVar.startDocument((String) null, true);
                dxVar.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);
                cw.a(map, (String) null, (XmlSerializer) dxVar);
                dxVar.endDocument();
                a2.close();
                this.b.delete();
                return true;
            } catch (IOException | XmlPullParserException unused) {
                if (this.f6573a.exists()) {
                    this.f6573a.delete();
                }
                return false;
            }
        }

        public final long a(String str) {
            long longValue;
            synchronized (this) {
                Long l = (Long) this.d.get(str);
                longValue = l != null ? l.longValue() : 0;
            }
            return longValue;
        }

        public final String a(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.d.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        public final void a(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.d = map;
                }
            }
        }

        public final boolean a() {
            return this.f6573a != null && new File(this.f6573a.getAbsolutePath()).exists();
        }

        public final Map<String, ?> b() {
            HashMap hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.d);
            }
            return hashMap;
        }

        public final dy.a c() {
            return new C0058a();
        }

        public final void d() {
            synchronized (this) {
                this.e = true;
            }
        }

        public final boolean e() {
            boolean z;
            synchronized (this) {
                z = this.e;
            }
            return z;
        }
    }

    public ea(String str) {
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.b = new File(str);
    }

    private File b() {
        File file;
        synchronized (this.f6572a) {
            file = this.b;
        }
        return file;
    }

    /* access modifiers changed from: private */
    public static File b(File file) {
        return new File(file.getPath() + ".bak");
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
        	at jadx.core.dex.visitors.regions.RegionMaker.processHandlersOutBlocks(RegionMaker.java:1008)
        	at jadx.core.dex.visitors.regions.RegionMaker.processTryCatchBlocks(RegionMaker.java:978)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:52)
        */
    /* JADX WARNING: Missing exception handler attribute for start block: B:30:0x0079 */
    public final com.loc.dy a(java.lang.String r8) {
        /*
            r7 = this;
            java.io.File r0 = r7.b()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r8)
            java.lang.String r8 = ".xml"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            char r1 = java.io.File.separatorChar
            int r1 = r8.indexOf(r1)
            if (r1 >= 0) goto L_0x00c0
            java.io.File r1 = new java.io.File
            r1.<init>(r0, r8)
            java.lang.Object r0 = c
            monitor-enter(r0)
            java.util.HashMap<java.io.File, com.loc.ea$a> r8 = r7.d     // Catch:{ all -> 0x00bd }
            java.lang.Object r8 = r8.get(r1)     // Catch:{ all -> 0x00bd }
            com.loc.ea$a r8 = (com.loc.ea.a) r8     // Catch:{ all -> 0x00bd }
            if (r8 == 0) goto L_0x0037
            boolean r2 = r8.e()     // Catch:{ all -> 0x00bd }
            if (r2 != 0) goto L_0x0037
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            return r8
        L_0x0037:
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            java.io.File r0 = b(r1)
            boolean r2 = r0.exists()
            if (r2 == 0) goto L_0x0048
            r1.delete()
            r0.renameTo(r1)
        L_0x0048:
            boolean r0 = r1.exists()
            if (r0 == 0) goto L_0x0051
            r1.canRead()
        L_0x0051:
            boolean r0 = r1.exists()
            r2 = 0
            if (r0 == 0) goto L_0x009a
            boolean r0 = r1.canRead()
            if (r0 == 0) goto L_0x009a
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            r0.<init>(r1)     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            org.xmlpull.v1.XmlPullParser r3 = android.util.Xml.newPullParser()     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            r3.setInput(r0, r2)     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            java.lang.Object r3 = com.loc.cw.a((org.xmlpull.v1.XmlPullParser) r3, (java.lang.String[]) r4)     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            java.util.HashMap r3 = (java.util.HashMap) r3     // Catch:{ XmlPullParserException -> 0x0079, FileNotFoundException | IOException -> 0x009a }
            r0.close()     // Catch:{ XmlPullParserException -> 0x0078, FileNotFoundException | IOException -> 0x0076 }
        L_0x0076:
            r2 = r3
            goto L_0x009a
        L_0x0078:
            r2 = r3
        L_0x0079:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            r0.<init>(r1)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            int r3 = r0.available()     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            byte[] r3 = new byte[r3]     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            r0.read(r3)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            java.lang.String r0 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            r4 = 0
            int r5 = r3.length     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            java.lang.String r6 = "UTF-8"
            r0.<init>(r3, r4, r5, r6)     // Catch:{ FileNotFoundException -> 0x0096, IOException -> 0x0091 }
            goto L_0x009a
        L_0x0091:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x009a
        L_0x0096:
            r0 = move-exception
            r0.printStackTrace()
        L_0x009a:
            java.lang.Object r3 = c
            monitor-enter(r3)
            if (r8 == 0) goto L_0x00a5
            r8.a((java.util.Map) r2)     // Catch:{ all -> 0x00a3 }
            goto L_0x00b9
        L_0x00a3:
            r8 = move-exception
            goto L_0x00bb
        L_0x00a5:
            java.util.HashMap<java.io.File, com.loc.ea$a> r8 = r7.d     // Catch:{ all -> 0x00a3 }
            java.lang.Object r8 = r8.get(r1)     // Catch:{ all -> 0x00a3 }
            com.loc.ea$a r8 = (com.loc.ea.a) r8     // Catch:{ all -> 0x00a3 }
            if (r8 != 0) goto L_0x00b9
            com.loc.ea$a r8 = new com.loc.ea$a     // Catch:{ all -> 0x00a3 }
            r8.<init>(r1, r2)     // Catch:{ all -> 0x00a3 }
            java.util.HashMap<java.io.File, com.loc.ea$a> r0 = r7.d     // Catch:{ all -> 0x00a3 }
            r0.put(r1, r8)     // Catch:{ all -> 0x00a3 }
        L_0x00b9:
            monitor-exit(r3)     // Catch:{ all -> 0x00a3 }
            return r8
        L_0x00bb:
            monitor-exit(r3)     // Catch:{ all -> 0x00a3 }
            throw r8
        L_0x00bd:
            r8 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00bd }
            throw r8
        L_0x00c0:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "File "
            r1.<init>(r2)
            r1.append(r8)
            java.lang.String r8 = " contains a path separator"
            r1.append(r8)
            java.lang.String r8 = r1.toString()
            r0.<init>(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ea.a(java.lang.String):com.loc.dy");
    }
}
