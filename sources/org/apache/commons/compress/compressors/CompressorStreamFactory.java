package org.apache.commons.compress.compressors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorInputStream;
import org.apache.commons.compress.compressors.deflate.DeflateCompressorOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorInputStream;
import org.apache.commons.compress.compressors.lzma.LZMACompressorOutputStream;
import org.apache.commons.compress.compressors.lzma.LZMAUtils;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorInputStream;
import org.apache.commons.compress.compressors.pack200.Pack200CompressorOutputStream;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.compressors.xz.XZCompressorOutputStream;
import org.apache.commons.compress.compressors.xz.XZUtils;
import org.apache.commons.compress.compressors.z.ZCompressorInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.ServiceLoaderIterator;
import org.apache.commons.compress.utils.Sets;

public class CompressorStreamFactory implements CompressorStreamProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3309a = "bzip2";
    public static final String b = "gz";
    public static final String c = "pack200";
    public static final String d = "xz";
    public static final String e = "lzma";
    public static final String f = "snappy-framed";
    public static final String g = "snappy-raw";
    public static final String h = "z";
    public static final String i = "deflate";
    /* access modifiers changed from: private */
    public static final CompressorStreamFactory j = new CompressorStreamFactory();
    private final Boolean k;
    private SortedMap<String, CompressorStreamProvider> l;
    private SortedMap<String, CompressorStreamProvider> m;
    private volatile boolean n;

    public static String c() {
        return f3309a;
    }

    public static String d() {
        return i;
    }

    public static String e() {
        return b;
    }

    public static String f() {
        return e;
    }

    public static String g() {
        return c;
    }

    public static String i() {
        return f;
    }

    public static String j() {
        return g;
    }

    public static String k() {
        return d;
    }

    public static String l() {
        return h;
    }

    public static SortedMap<String, CompressorStreamProvider> a() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, CompressorStreamProvider>>() {
            /* renamed from: a */
            public SortedMap<String, CompressorStreamProvider> run() {
                TreeMap treeMap = new TreeMap();
                CompressorStreamFactory.a(CompressorStreamFactory.j.q(), (CompressorStreamProvider) CompressorStreamFactory.j, (TreeMap<String, CompressorStreamProvider>) treeMap);
                Iterator it = CompressorStreamFactory.u().iterator();
                while (it.hasNext()) {
                    CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider) it.next();
                    CompressorStreamFactory.a(compressorStreamProvider.q(), compressorStreamProvider, (TreeMap<String, CompressorStreamProvider>) treeMap);
                }
                return treeMap;
            }
        });
    }

    public static SortedMap<String, CompressorStreamProvider> b() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, CompressorStreamProvider>>() {
            /* renamed from: a */
            public SortedMap<String, CompressorStreamProvider> run() {
                TreeMap treeMap = new TreeMap();
                CompressorStreamFactory.a(CompressorStreamFactory.j.r(), (CompressorStreamProvider) CompressorStreamFactory.j, (TreeMap<String, CompressorStreamProvider>) treeMap);
                Iterator it = CompressorStreamFactory.u().iterator();
                while (it.hasNext()) {
                    CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider) it.next();
                    CompressorStreamFactory.a(compressorStreamProvider.r(), compressorStreamProvider, (TreeMap<String, CompressorStreamProvider>) treeMap);
                }
                return treeMap;
            }
        });
    }

    /* access modifiers changed from: private */
    public static ArrayList<CompressorStreamProvider> u() {
        return Lists.a(v());
    }

    public static CompressorStreamFactory h() {
        return j;
    }

    static void a(Set<String> set, CompressorStreamProvider compressorStreamProvider, TreeMap<String, CompressorStreamProvider> treeMap) {
        for (String a2 : set) {
            treeMap.put(a(a2), compressorStreamProvider);
        }
    }

    private static Iterator<CompressorStreamProvider> v() {
        return new ServiceLoaderIterator(CompressorStreamProvider.class);
    }

    private static String a(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public CompressorStreamFactory() {
        this.n = false;
        this.k = null;
    }

    public CompressorStreamFactory(boolean z) {
        this.n = false;
        this.k = Boolean.valueOf(z);
        this.n = z;
    }

    public CompressorInputStream a(InputStream inputStream) throws CompressorException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Stream must not be null.");
        } else if (inputStream.markSupported()) {
            byte[] bArr = new byte[12];
            inputStream.mark(bArr.length);
            try {
                int a2 = IOUtils.a(inputStream, bArr);
                inputStream.reset();
                if (BZip2CompressorInputStream.a(bArr, a2)) {
                    return new BZip2CompressorInputStream(inputStream, this.n);
                }
                if (GzipCompressorInputStream.a(bArr, a2)) {
                    return new GzipCompressorInputStream(inputStream, this.n);
                }
                if (Pack200CompressorInputStream.a(bArr, a2)) {
                    return new Pack200CompressorInputStream(inputStream);
                }
                if (FramedSnappyCompressorInputStream.a(bArr, a2)) {
                    return new FramedSnappyCompressorInputStream(inputStream);
                }
                if (ZCompressorInputStream.a(bArr, a2)) {
                    return new ZCompressorInputStream(inputStream);
                }
                if (DeflateCompressorInputStream.a(bArr, a2)) {
                    return new DeflateCompressorInputStream(inputStream);
                }
                if (XZUtils.a(bArr, a2) && XZUtils.a()) {
                    return new XZCompressorInputStream(inputStream, this.n);
                }
                if (LZMAUtils.a(bArr, a2) && LZMAUtils.a()) {
                    return new LZMACompressorInputStream(inputStream);
                }
                throw new CompressorException("No Compressor found for the stream signature.");
            } catch (IOException e2) {
                throw new CompressorException("Failed to detect Compressor from InputStream.", e2);
            }
        } else {
            throw new IllegalArgumentException("Mark is not supported.");
        }
    }

    public CompressorInputStream a(String str, InputStream inputStream) throws CompressorException {
        return a(str, inputStream, this.n);
    }

    public CompressorInputStream a(String str, InputStream inputStream, boolean z) throws CompressorException {
        if (str == null || inputStream == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (b.equalsIgnoreCase(str)) {
                return new GzipCompressorInputStream(inputStream, z);
            }
            if (f3309a.equalsIgnoreCase(str)) {
                return new BZip2CompressorInputStream(inputStream, z);
            }
            if (d.equalsIgnoreCase(str)) {
                return new XZCompressorInputStream(inputStream, z);
            }
            if (e.equalsIgnoreCase(str)) {
                return new LZMACompressorInputStream(inputStream);
            }
            if (c.equalsIgnoreCase(str)) {
                return new Pack200CompressorInputStream(inputStream);
            }
            if (g.equalsIgnoreCase(str)) {
                return new SnappyCompressorInputStream(inputStream);
            }
            if (f.equalsIgnoreCase(str)) {
                return new FramedSnappyCompressorInputStream(inputStream);
            }
            if (h.equalsIgnoreCase(str)) {
                return new ZCompressorInputStream(inputStream);
            }
            if (i.equalsIgnoreCase(str)) {
                return new DeflateCompressorInputStream(inputStream);
            }
            CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider) m().get(a(str));
            if (compressorStreamProvider != null) {
                return compressorStreamProvider.a(str, inputStream, z);
            }
            throw new CompressorException("Compressor: " + str + " not found.");
        } catch (IOException e2) {
            throw new CompressorException("Could not create CompressorInputStream.", e2);
        }
    }

    public CompressorOutputStream a(String str, OutputStream outputStream) throws CompressorException {
        if (str == null || outputStream == null) {
            throw new IllegalArgumentException("Compressor name and stream must not be null.");
        }
        try {
            if (b.equalsIgnoreCase(str)) {
                return new GzipCompressorOutputStream(outputStream);
            }
            if (f3309a.equalsIgnoreCase(str)) {
                return new BZip2CompressorOutputStream(outputStream);
            }
            if (d.equalsIgnoreCase(str)) {
                return new XZCompressorOutputStream(outputStream);
            }
            if (c.equalsIgnoreCase(str)) {
                return new Pack200CompressorOutputStream(outputStream);
            }
            if (e.equalsIgnoreCase(str)) {
                return new LZMACompressorOutputStream(outputStream);
            }
            if (i.equalsIgnoreCase(str)) {
                return new DeflateCompressorOutputStream(outputStream);
            }
            CompressorStreamProvider compressorStreamProvider = (CompressorStreamProvider) n().get(a(str));
            if (compressorStreamProvider != null) {
                return compressorStreamProvider.a(str, outputStream);
            }
            throw new CompressorException("Compressor: " + str + " not found.");
        } catch (IOException e2) {
            throw new CompressorException("Could not create CompressorOutputStream", e2);
        }
    }

    public SortedMap<String, CompressorStreamProvider> m() {
        if (this.l == null) {
            this.l = Collections.unmodifiableSortedMap(a());
        }
        return this.l;
    }

    public SortedMap<String, CompressorStreamProvider> n() {
        if (this.m == null) {
            this.m = Collections.unmodifiableSortedMap(b());
        }
        return this.m;
    }

    /* access modifiers changed from: package-private */
    public boolean o() {
        return this.n;
    }

    public Boolean p() {
        return this.k;
    }

    public Set<String> q() {
        return Sets.a(b, f3309a, d, e, c, g, f, h, i);
    }

    public Set<String> r() {
        return Sets.a(b, f3309a, d, e, c, i);
    }

    @Deprecated
    public void a(boolean z) {
        if (this.k == null) {
            this.n = z;
            return;
        }
        throw new IllegalStateException("Cannot override the setting defined by the constructor");
    }
}
