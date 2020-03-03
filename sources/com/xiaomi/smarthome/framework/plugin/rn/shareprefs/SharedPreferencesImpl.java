package com.xiaomi.smarthome.framework.plugin.rn.shareprefs;

import android.content.SharedPreferences;
import android.os.Looper;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import org.xmlpull.v1.XmlPullParserException;

public final class SharedPreferencesImpl implements SharedPreferences {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17496a = "SharedPreferencesImpl";
    private static final boolean b = false;
    private static final Object c = new Object();
    private static final long d = 256;
    private static HashMap<File, SharedPreferencesImpl> r = new HashMap<>();
    private final File e;
    private final File f;
    /* access modifiers changed from: private */
    public final Object g = new Object();
    /* access modifiers changed from: private */
    public final Object h = new Object();
    /* access modifiers changed from: private */
    public Map<String, Object> i;
    private Throwable j;
    /* access modifiers changed from: private */
    public int k = 0;
    private boolean l = false;
    /* access modifiers changed from: private */
    public final WeakHashMap<SharedPreferences.OnSharedPreferenceChangeListener, Object> m = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public long n;
    private long o;
    private final ExponentiallyBucketedHistogram p = new ExponentiallyBucketedHistogram(16);
    private int q = 0;

    static /* synthetic */ int e(SharedPreferencesImpl sharedPreferencesImpl) {
        int i2 = sharedPreferencesImpl.k;
        sharedPreferencesImpl.k = i2 + 1;
        return i2;
    }

    static /* synthetic */ long g(SharedPreferencesImpl sharedPreferencesImpl) {
        long j2 = sharedPreferencesImpl.n;
        sharedPreferencesImpl.n = 1 + j2;
        return j2;
    }

    static /* synthetic */ int j(SharedPreferencesImpl sharedPreferencesImpl) {
        int i2 = sharedPreferencesImpl.k;
        sharedPreferencesImpl.k = i2 - 1;
        return i2;
    }

    private SharedPreferencesImpl(File file) {
        this.e = file;
        this.f = b(file);
        this.l = false;
        this.i = null;
        this.j = null;
        b();
    }

    public static SharedPreferences a(File file) {
        SharedPreferencesImpl sharedPreferencesImpl = r.get(file);
        if (sharedPreferencesImpl != null) {
            return sharedPreferencesImpl;
        }
        SharedPreferencesImpl sharedPreferencesImpl2 = new SharedPreferencesImpl(file);
        r.put(file, sharedPreferencesImpl2);
        return sharedPreferencesImpl2;
    }

    private void b() {
        synchronized (this.g) {
            this.l = false;
        }
        new Thread("SharedPreferencesImpl-load") {
            public void run() {
                SharedPreferencesImpl.this.c();
            }
        }.start();
    }

    /* access modifiers changed from: private */
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
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
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
    public void c() {
        /*
            r6 = this;
            java.lang.Object r0 = r6.g
            monitor-enter(r0)
            boolean r1 = r6.l     // Catch:{ all -> 0x00ce }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            return
        L_0x0009:
            java.io.File r1 = r6.f     // Catch:{ all -> 0x00ce }
            boolean r1 = r1.exists()     // Catch:{ all -> 0x00ce }
            if (r1 == 0) goto L_0x001d
            java.io.File r1 = r6.e     // Catch:{ all -> 0x00ce }
            r1.delete()     // Catch:{ all -> 0x00ce }
            java.io.File r1 = r6.f     // Catch:{ all -> 0x00ce }
            java.io.File r2 = r6.e     // Catch:{ all -> 0x00ce }
            r1.renameTo(r2)     // Catch:{ all -> 0x00ce }
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            java.io.File r0 = r6.e
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x004b
            java.io.File r0 = r6.e
            boolean r0 = r0.canRead()
            if (r0 != 0) goto L_0x004b
            java.lang.String r0 = "SharedPreferencesImpl"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Attempt to read preferences file "
            r1.append(r2)
            java.io.File r2 = r6.e
            r1.append(r2)
            java.lang.String r2 = " without permission"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            android.util.Log.w(r0, r1)
        L_0x004b:
            r0 = 0
            java.io.File r1 = r6.e     // Catch:{ Exception -> 0x0098, Throwable -> 0x009a }
            boolean r1 = r1.canRead()     // Catch:{ Exception -> 0x0098, Throwable -> 0x009a }
            if (r1 == 0) goto L_0x0098
            java.io.BufferedInputStream r1 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0071, all -> 0x006e }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0071, all -> 0x006e }
            java.io.File r3 = r6.e     // Catch:{ Exception -> 0x0071, all -> 0x006e }
            r2.<init>(r3)     // Catch:{ Exception -> 0x0071, all -> 0x006e }
            r3 = 16384(0x4000, float:2.2959E-41)
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0071, all -> 0x006e }
            java.util.HashMap r2 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.XmlUtils.a((java.io.InputStream) r1)     // Catch:{ Exception -> 0x006c }
            com.nostra13.universalimageloader.utils.IoUtils.a((java.io.Closeable) r1)     // Catch:{ Exception -> 0x009d, Throwable -> 0x006a }
            goto L_0x009d
        L_0x006a:
            r0 = move-exception
            goto L_0x009d
        L_0x006c:
            r2 = move-exception
            goto L_0x0073
        L_0x006e:
            r2 = move-exception
            r1 = r0
            goto L_0x0094
        L_0x0071:
            r2 = move-exception
            r1 = r0
        L_0x0073:
            java.lang.String r3 = "SharedPreferencesImpl"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r4.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "Cannot read "
            r4.append(r5)     // Catch:{ all -> 0x0093 }
            java.io.File r5 = r6.e     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = r5.getAbsolutePath()     // Catch:{ all -> 0x0093 }
            r4.append(r5)     // Catch:{ all -> 0x0093 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0093 }
            android.util.Log.w(r3, r4, r2)     // Catch:{ all -> 0x0093 }
            com.nostra13.universalimageloader.utils.IoUtils.a((java.io.Closeable) r1)     // Catch:{ Exception -> 0x0098, Throwable -> 0x009a }
            goto L_0x0098
        L_0x0093:
            r2 = move-exception
        L_0x0094:
            com.nostra13.universalimageloader.utils.IoUtils.a((java.io.Closeable) r1)     // Catch:{ Exception -> 0x0098, Throwable -> 0x009a }
            throw r2     // Catch:{ Exception -> 0x0098, Throwable -> 0x009a }
        L_0x0098:
            r2 = r0
            goto L_0x009d
        L_0x009a:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x009d:
            java.lang.Object r1 = r6.g
            monitor-enter(r1)
            r3 = 1
            r6.l = r3     // Catch:{ all -> 0x00cb }
            r6.j = r0     // Catch:{ all -> 0x00cb }
            if (r0 != 0) goto L_0x00c6
            if (r2 == 0) goto L_0x00b0
            r6.i = r2     // Catch:{ Throwable -> 0x00ae }
            goto L_0x00c6
        L_0x00ac:
            r0 = move-exception
            goto L_0x00c0
        L_0x00ae:
            r0 = move-exception
            goto L_0x00b8
        L_0x00b0:
            java.util.HashMap r0 = new java.util.HashMap     // Catch:{ Throwable -> 0x00ae }
            r0.<init>()     // Catch:{ Throwable -> 0x00ae }
            r6.i = r0     // Catch:{ Throwable -> 0x00ae }
            goto L_0x00c6
        L_0x00b8:
            r6.j = r0     // Catch:{ all -> 0x00ac }
            java.lang.Object r0 = r6.g     // Catch:{ all -> 0x00cb }
        L_0x00bc:
            r0.notifyAll()     // Catch:{ all -> 0x00cb }
            goto L_0x00c9
        L_0x00c0:
            java.lang.Object r2 = r6.g     // Catch:{ all -> 0x00cb }
            r2.notifyAll()     // Catch:{ all -> 0x00cb }
            throw r0     // Catch:{ all -> 0x00cb }
        L_0x00c6:
            java.lang.Object r0 = r6.g     // Catch:{ all -> 0x00cb }
            goto L_0x00bc
        L_0x00c9:
            monitor-exit(r1)     // Catch:{ all -> 0x00cb }
            return
        L_0x00cb:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x00cb }
            throw r0
        L_0x00ce:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00ce }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.c():void");
    }

    static File b(File file) {
        return new File(file.getPath() + ".bak");
    }

    /* access modifiers changed from: package-private */
    public void a() {
        synchronized (this.g) {
            if (d()) {
                b();
            }
        }
    }

    private boolean d() {
        synchronized (this.g) {
            if (this.k > 0) {
                return false;
            }
            return true;
        }
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.g) {
            this.m.put(onSharedPreferenceChangeListener, c);
        }
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        synchronized (this.g) {
            this.m.remove(onSharedPreferenceChangeListener);
        }
    }

    private void e() {
        boolean z = this.l;
        while (!this.l) {
            try {
                this.g.wait();
            } catch (InterruptedException unused) {
            }
        }
        if (this.j != null) {
            throw new IllegalStateException(this.j);
        }
    }

    public Map<String, ?> getAll() {
        HashMap hashMap;
        synchronized (this.g) {
            e();
            hashMap = new HashMap(this.i);
        }
        return hashMap;
    }

    public String getString(String str, String str2) {
        String str3;
        synchronized (this.g) {
            e();
            str3 = (String) this.i.get(str);
            if (str3 == null) {
                str3 = str2;
            }
        }
        return str3;
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        Set<String> set2;
        synchronized (this.g) {
            e();
            set2 = (Set) this.i.get(str);
            if (set2 == null) {
                set2 = set;
            }
        }
        return set2;
    }

    public int getInt(String str, int i2) {
        synchronized (this.g) {
            e();
            Integer num = (Integer) this.i.get(str);
            if (num != null) {
                i2 = num.intValue();
            }
        }
        return i2;
    }

    public long getLong(String str, long j2) {
        synchronized (this.g) {
            e();
            Long l2 = (Long) this.i.get(str);
            if (l2 != null) {
                j2 = l2.longValue();
            }
        }
        return j2;
    }

    public float getFloat(String str, float f2) {
        synchronized (this.g) {
            e();
            Float f3 = (Float) this.i.get(str);
            if (f3 != null) {
                f2 = f3.floatValue();
            }
        }
        return f2;
    }

    public boolean getBoolean(String str, boolean z) {
        synchronized (this.g) {
            e();
            Boolean bool = (Boolean) this.i.get(str);
            if (bool != null) {
                z = bool.booleanValue();
            }
        }
        return z;
    }

    public boolean contains(String str) {
        boolean containsKey;
        synchronized (this.g) {
            e();
            containsKey = this.i.containsKey(str);
        }
        return containsKey;
    }

    public SharedPreferences.Editor edit() {
        synchronized (this.g) {
            e();
        }
        return new EditorImpl();
    }

    private static class MemoryCommitResult {

        /* renamed from: a  reason: collision with root package name */
        final long f17503a;
        final List<String> b;
        final Set<SharedPreferences.OnSharedPreferenceChangeListener> c;
        final Map<String, Object> d;
        final CountDownLatch e;
        volatile boolean f;
        boolean g;

        private MemoryCommitResult(long j, List<String> list, Set<SharedPreferences.OnSharedPreferenceChangeListener> set, Map<String, Object> map) {
            this.e = new CountDownLatch(1);
            this.f = false;
            this.g = false;
            this.f17503a = j;
            this.b = list;
            this.c = set;
            this.d = map;
        }

        /* access modifiers changed from: package-private */
        public void a(boolean z, boolean z2) {
            this.g = z;
            this.f = z2;
            this.e.countDown();
        }
    }

    public final class EditorImpl implements SharedPreferences.Editor {
        private final Object b = new Object();
        private final Map<String, Object> c = new HashMap();
        private boolean d = false;

        public EditorImpl() {
        }

        public SharedPreferences.Editor putString(String str, String str2) {
            synchronized (this.b) {
                this.c.put(str, str2);
            }
            return this;
        }

        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            synchronized (this.b) {
                this.c.put(str, set == null ? null : new HashSet(set));
            }
            return this;
        }

        public SharedPreferences.Editor putInt(String str, int i) {
            synchronized (this.b) {
                this.c.put(str, Integer.valueOf(i));
            }
            return this;
        }

        public SharedPreferences.Editor putLong(String str, long j) {
            synchronized (this.b) {
                this.c.put(str, Long.valueOf(j));
            }
            return this;
        }

        public SharedPreferences.Editor putFloat(String str, float f) {
            synchronized (this.b) {
                this.c.put(str, Float.valueOf(f));
            }
            return this;
        }

        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            synchronized (this.b) {
                this.c.put(str, Boolean.valueOf(z));
            }
            return this;
        }

        public SharedPreferences.Editor remove(String str) {
            synchronized (this.b) {
                this.c.put(str, this);
            }
            return this;
        }

        public SharedPreferences.Editor clear() {
            synchronized (this.b) {
                this.d = true;
            }
            return this;
        }

        public void apply() {
            final long currentTimeMillis = System.currentTimeMillis();
            final MemoryCommitResult a2 = a();
            final AnonymousClass1 r3 = new Runnable() {
                public void run() {
                    try {
                        a2.e.await();
                    } catch (InterruptedException unused) {
                    }
                }
            };
            QueuedWork.a(r3);
            SharedPreferencesImpl.this.a(a2, (Runnable) new Runnable() {
                public void run() {
                    r3.run();
                    QueuedWork.b(r3);
                }
            });
            a(a2);
        }

        /* JADX WARNING: Removed duplicated region for block: B:44:0x00b5  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.MemoryCommitResult a() {
            /*
                r12 = this;
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r0 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this
                java.lang.Object r0 = r0.g
                monitor-enter(r0)
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                int r1 = r1.k     // Catch:{ all -> 0x00db }
                if (r1 <= 0) goto L_0x001f
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                java.util.HashMap r2 = new java.util.HashMap     // Catch:{ all -> 0x00db }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r3 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                java.util.Map r3 = r3.i     // Catch:{ all -> 0x00db }
                r2.<init>(r3)     // Catch:{ all -> 0x00db }
                java.util.Map unused = r1.i = r2     // Catch:{ all -> 0x00db }
            L_0x001f:
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                java.util.Map r7 = r1.i     // Catch:{ all -> 0x00db }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.e(r1)     // Catch:{ all -> 0x00db }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                java.util.WeakHashMap r1 = r1.m     // Catch:{ all -> 0x00db }
                int r1 = r1.size()     // Catch:{ all -> 0x00db }
                r2 = 0
                r3 = 1
                if (r1 <= 0) goto L_0x003a
                r1 = 1
                goto L_0x003b
            L_0x003a:
                r1 = 0
            L_0x003b:
                r4 = 0
                if (r1 == 0) goto L_0x0055
                java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x00db }
                r4.<init>()     // Catch:{ all -> 0x00db }
                java.util.HashSet r5 = new java.util.HashSet     // Catch:{ all -> 0x00db }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r6 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00db }
                java.util.WeakHashMap r6 = r6.m     // Catch:{ all -> 0x00db }
                java.util.Set r6 = r6.keySet()     // Catch:{ all -> 0x00db }
                r5.<init>(r6)     // Catch:{ all -> 0x00db }
                r6 = r5
                r5 = r4
                goto L_0x0057
            L_0x0055:
                r5 = r4
                r6 = r5
            L_0x0057:
                java.lang.Object r4 = r12.b     // Catch:{ all -> 0x00db }
                monitor-enter(r4)     // Catch:{ all -> 0x00db }
                boolean r8 = r12.d     // Catch:{ all -> 0x00d8 }
                if (r8 == 0) goto L_0x006d
                boolean r8 = r7.isEmpty()     // Catch:{ all -> 0x00d8 }
                if (r8 != 0) goto L_0x0069
                r7.clear()     // Catch:{ all -> 0x00d8 }
                r8 = 1
                goto L_0x006a
            L_0x0069:
                r8 = 0
            L_0x006a:
                r12.d = r2     // Catch:{ all -> 0x00d8 }
                r2 = r8
            L_0x006d:
                java.util.Map<java.lang.String, java.lang.Object> r8 = r12.c     // Catch:{ all -> 0x00d8 }
                java.util.Set r8 = r8.entrySet()     // Catch:{ all -> 0x00d8 }
                java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00d8 }
            L_0x0077:
                boolean r9 = r8.hasNext()     // Catch:{ all -> 0x00d8 }
                if (r9 == 0) goto L_0x00ba
                java.lang.Object r9 = r8.next()     // Catch:{ all -> 0x00d8 }
                java.util.Map$Entry r9 = (java.util.Map.Entry) r9     // Catch:{ all -> 0x00d8 }
                java.lang.Object r10 = r9.getKey()     // Catch:{ all -> 0x00d8 }
                java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x00d8 }
                java.lang.Object r9 = r9.getValue()     // Catch:{ all -> 0x00d8 }
                if (r9 == r12) goto L_0x00a9
                if (r9 != 0) goto L_0x0092
                goto L_0x00a9
            L_0x0092:
                boolean r11 = r7.containsKey(r10)     // Catch:{ all -> 0x00d8 }
                if (r11 == 0) goto L_0x00a5
                java.lang.Object r11 = r7.get(r10)     // Catch:{ all -> 0x00d8 }
                if (r11 == 0) goto L_0x00a5
                boolean r11 = r11.equals(r9)     // Catch:{ all -> 0x00d8 }
                if (r11 == 0) goto L_0x00a5
                goto L_0x0077
            L_0x00a5:
                r7.put(r10, r9)     // Catch:{ all -> 0x00d8 }
                goto L_0x00b3
            L_0x00a9:
                boolean r9 = r7.containsKey(r10)     // Catch:{ all -> 0x00d8 }
                if (r9 != 0) goto L_0x00b0
                goto L_0x0077
            L_0x00b0:
                r7.remove(r10)     // Catch:{ all -> 0x00d8 }
            L_0x00b3:
                if (r1 == 0) goto L_0x00b8
                r5.add(r10)     // Catch:{ all -> 0x00d8 }
            L_0x00b8:
                r2 = 1
                goto L_0x0077
            L_0x00ba:
                java.util.Map<java.lang.String, java.lang.Object> r1 = r12.c     // Catch:{ all -> 0x00d8 }
                r1.clear()     // Catch:{ all -> 0x00d8 }
                if (r2 == 0) goto L_0x00c6
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00d8 }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.g(r1)     // Catch:{ all -> 0x00d8 }
            L_0x00c6:
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this     // Catch:{ all -> 0x00d8 }
                long r8 = r1.n     // Catch:{ all -> 0x00d8 }
                monitor-exit(r4)     // Catch:{ all -> 0x00d8 }
                monitor-exit(r0)     // Catch:{ all -> 0x00db }
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl$MemoryCommitResult r0 = new com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl$MemoryCommitResult
                r1 = 0
                r2 = r0
                r3 = r8
                r8 = r1
                r2.<init>(r3, r5, r6, r7)
                return r0
            L_0x00d8:
                r1 = move-exception
                monitor-exit(r4)     // Catch:{ all -> 0x00d8 }
                throw r1     // Catch:{ all -> 0x00db }
            L_0x00db:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x00db }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.EditorImpl.a():com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl$MemoryCommitResult");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
            return false;
         */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean commit() {
            /*
                r3 = this;
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl$MemoryCommitResult r0 = r3.a()
                com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl r1 = com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.this
                r2 = 0
                r1.a((com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.MemoryCommitResult) r0, (java.lang.Runnable) r2)
                java.util.concurrent.CountDownLatch r1 = r0.e     // Catch:{ InterruptedException -> 0x0017, all -> 0x0015 }
                r1.await()     // Catch:{ InterruptedException -> 0x0017, all -> 0x0015 }
                r3.a(r0)
                boolean r0 = r0.f
                return r0
            L_0x0015:
                r0 = move-exception
                throw r0
            L_0x0017:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.shareprefs.SharedPreferencesImpl.EditorImpl.commit():boolean");
        }

        /* access modifiers changed from: private */
        public void a(final MemoryCommitResult memoryCommitResult) {
            if (memoryCommitResult.c != null && memoryCommitResult.b != null && memoryCommitResult.b.size() != 0) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    for (int size = memoryCommitResult.b.size() - 1; size >= 0; size--) {
                        String str = memoryCommitResult.b.get(size);
                        for (SharedPreferences.OnSharedPreferenceChangeListener next : memoryCommitResult.c) {
                            if (next != null) {
                                next.onSharedPreferenceChanged(SharedPreferencesImpl.this, str);
                            }
                        }
                    }
                    return;
                }
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        EditorImpl.this.a(memoryCommitResult);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(final MemoryCommitResult memoryCommitResult, final Runnable runnable) {
        boolean z = false;
        final boolean z2 = runnable == null;
        AnonymousClass2 r3 = new Runnable() {
            public void run() {
                synchronized (SharedPreferencesImpl.this.h) {
                    SharedPreferencesImpl.this.a(memoryCommitResult, z2);
                }
                synchronized (SharedPreferencesImpl.this.g) {
                    SharedPreferencesImpl.j(SharedPreferencesImpl.this);
                }
                if (runnable != null) {
                    runnable.run();
                }
            }
        };
        if (z2) {
            synchronized (this.g) {
                if (this.k == 1) {
                    z = true;
                }
            }
            if (z) {
                r3.run();
                return;
            }
        }
        QueuedWork.a(r3, !z2);
    }

    private static FileOutputStream c(File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException unused) {
            if (!file.getParentFile().mkdir()) {
                Log.e(f17496a, "Couldn't create directory for SharedPreferences file " + file);
                return null;
            }
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e2) {
                Log.e(f17496a, "Couldn't create SharedPreferences file " + file, e2);
                return null;
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(MemoryCommitResult memoryCommitResult, boolean z) {
        boolean z2;
        if (this.e.exists()) {
            if (this.o >= memoryCommitResult.f17503a) {
                z2 = false;
            } else if (z) {
                z2 = true;
            } else {
                synchronized (this.g) {
                    z2 = this.n == memoryCommitResult.f17503a;
                }
            }
            if (!z2) {
                memoryCommitResult.a(false, true);
                return;
            } else if (this.f.exists()) {
                this.e.delete();
            } else if (!this.e.renameTo(this.f)) {
                Log.e(f17496a, "Couldn't rename file " + this.e + " to backup file " + this.f);
                memoryCommitResult.a(false, false);
                return;
            }
        }
        try {
            FileOutputStream c2 = c(this.e);
            if (c2 == null) {
                memoryCommitResult.a(false, false);
                return;
            }
            XmlUtils.a((Map) memoryCommitResult.d, (OutputStream) c2);
            long currentTimeMillis = System.currentTimeMillis();
            a(c2);
            long currentTimeMillis2 = System.currentTimeMillis();
            c2.close();
            this.f.delete();
            this.o = memoryCommitResult.f17503a;
            memoryCommitResult.a(true, true);
            long j2 = currentTimeMillis2 - currentTimeMillis;
            this.p.a((int) j2);
            this.q++;
            if (this.q % 1024 == 0 || j2 > 256) {
                this.p.a(f17496a, "Time required to fsync " + this.e + ": ");
            }
        } catch (XmlPullParserException e2) {
            Log.w(f17496a, "writeToFile: Got exception:", e2);
            if (this.e.exists() && !this.e.delete()) {
                Log.e(f17496a, "Couldn't clean up partially-written file " + this.e);
            }
            memoryCommitResult.a(false, false);
        } catch (IOException e3) {
            Log.w(f17496a, "writeToFile: Got exception:", e3);
            Log.e(f17496a, "Couldn't clean up partially-written file " + this.e);
            memoryCommitResult.a(false, false);
        }
    }

    public static boolean a(FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return true;
        }
        try {
            fileOutputStream.getFD().sync();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }
}
