package org.apache.commons.compress.archivers;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
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
import org.apache.commons.compress.archivers.ar.ArArchiveInputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveOutputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveInputStream;
import org.apache.commons.compress.archivers.cpio.CpioArchiveOutputStream;
import org.apache.commons.compress.archivers.dump.DumpArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.archivers.jar.JarArchiveOutputStream;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.compress.utils.ServiceLoaderIterator;
import org.apache.commons.compress.utils.Sets;

public class ArchiveStreamFactory implements ArchiveStreamProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3199a = "ar";
    public static final String b = "arj";
    public static final String c = "cpio";
    public static final String d = "dump";
    public static final String e = "jar";
    public static final String f = "tar";
    public static final String g = "zip";
    public static final String h = "7z";
    private static final int i = 512;
    private static final int j = 32;
    private static final int k = 12;
    /* access modifiers changed from: private */
    public static final ArchiveStreamFactory l = new ArchiveStreamFactory();
    private final String m;
    private volatile String n;
    private SortedMap<String, ArchiveStreamProvider> o;
    private SortedMap<String, ArchiveStreamProvider> p;

    /* access modifiers changed from: private */
    public static ArrayList<ArchiveStreamProvider> j() {
        return Lists.a(k());
    }

    static void a(Set<String> set, ArchiveStreamProvider archiveStreamProvider, TreeMap<String, ArchiveStreamProvider> treeMap) {
        for (String b2 : set) {
            treeMap.put(b(b2), archiveStreamProvider);
        }
    }

    private static Iterator<ArchiveStreamProvider> k() {
        return new ServiceLoaderIterator(ArchiveStreamProvider.class);
    }

    private static String b(String str) {
        return str.toUpperCase(Locale.ROOT);
    }

    public static SortedMap<String, ArchiveStreamProvider> a() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, ArchiveStreamProvider>>() {
            /* renamed from: a */
            public SortedMap<String, ArchiveStreamProvider> run() {
                TreeMap treeMap = new TreeMap();
                ArchiveStreamFactory.a(ArchiveStreamFactory.l.f(), (ArchiveStreamProvider) ArchiveStreamFactory.l, (TreeMap<String, ArchiveStreamProvider>) treeMap);
                Iterator it = ArchiveStreamFactory.j().iterator();
                while (it.hasNext()) {
                    ArchiveStreamProvider archiveStreamProvider = (ArchiveStreamProvider) it.next();
                    ArchiveStreamFactory.a(archiveStreamProvider.f(), archiveStreamProvider, (TreeMap<String, ArchiveStreamProvider>) treeMap);
                }
                return treeMap;
            }
        });
    }

    public static SortedMap<String, ArchiveStreamProvider> b() {
        return (SortedMap) AccessController.doPrivileged(new PrivilegedAction<SortedMap<String, ArchiveStreamProvider>>() {
            /* renamed from: a */
            public SortedMap<String, ArchiveStreamProvider> run() {
                TreeMap treeMap = new TreeMap();
                ArchiveStreamFactory.a(ArchiveStreamFactory.l.g(), (ArchiveStreamProvider) ArchiveStreamFactory.l, (TreeMap<String, ArchiveStreamProvider>) treeMap);
                Iterator it = ArchiveStreamFactory.j().iterator();
                while (it.hasNext()) {
                    ArchiveStreamProvider archiveStreamProvider = (ArchiveStreamProvider) it.next();
                    ArchiveStreamFactory.a(archiveStreamProvider.g(), archiveStreamProvider, (TreeMap<String, ArchiveStreamProvider>) treeMap);
                }
                return treeMap;
            }
        });
    }

    public ArchiveStreamFactory() {
        this((String) null);
    }

    public ArchiveStreamFactory(String str) {
        this.m = str;
        this.n = str;
    }

    public String c() {
        return this.n;
    }

    @Deprecated
    public void a(String str) {
        if (this.m == null) {
            this.n = str;
            return;
        }
        throw new IllegalStateException("Cannot overide encoding set by the constructor");
    }

    public ArchiveInputStream a(String str, InputStream inputStream) throws ArchiveException {
        return a(str, inputStream, this.n);
    }

    public ArchiveInputStream a(String str, InputStream inputStream, String str2) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        } else if (inputStream == null) {
            throw new IllegalArgumentException("InputStream must not be null.");
        } else if ("ar".equalsIgnoreCase(str)) {
            return new ArArchiveInputStream(inputStream);
        } else {
            if (b.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new ArjArchiveInputStream(inputStream, str2);
                }
                return new ArjArchiveInputStream(inputStream);
            } else if (g.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new ZipArchiveInputStream(inputStream, str2);
                }
                return new ZipArchiveInputStream(inputStream);
            } else if (f.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new TarArchiveInputStream(inputStream, str2);
                }
                return new TarArchiveInputStream(inputStream);
            } else if ("jar".equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new JarArchiveInputStream(inputStream, str2);
                }
                return new JarArchiveInputStream(inputStream);
            } else if (c.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new CpioArchiveInputStream(inputStream, str2);
                }
                return new CpioArchiveInputStream(inputStream);
            } else if (d.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new DumpArchiveInputStream(inputStream, str2);
                }
                return new DumpArchiveInputStream(inputStream);
            } else if (!h.equalsIgnoreCase(str)) {
                ArchiveStreamProvider archiveStreamProvider = (ArchiveStreamProvider) d().get(b(str));
                if (archiveStreamProvider != null) {
                    return archiveStreamProvider.a(str, inputStream, str2);
                }
                throw new ArchiveException("Archiver: " + str + " not found.");
            } else {
                throw new StreamingNotSupportedException(h);
            }
        }
    }

    public ArchiveOutputStream a(String str, OutputStream outputStream) throws ArchiveException {
        return a(str, outputStream, this.n);
    }

    public ArchiveOutputStream a(String str, OutputStream outputStream, String str2) throws ArchiveException {
        if (str == null) {
            throw new IllegalArgumentException("Archivername must not be null.");
        } else if (outputStream == null) {
            throw new IllegalArgumentException("OutputStream must not be null.");
        } else if ("ar".equalsIgnoreCase(str)) {
            return new ArArchiveOutputStream(outputStream);
        } else {
            if (g.equalsIgnoreCase(str)) {
                ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(outputStream);
                if (str2 != null) {
                    zipArchiveOutputStream.a(str2);
                }
                return zipArchiveOutputStream;
            } else if (f.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new TarArchiveOutputStream(outputStream, str2);
                }
                return new TarArchiveOutputStream(outputStream);
            } else if ("jar".equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new JarArchiveOutputStream(outputStream, str2);
                }
                return new JarArchiveOutputStream(outputStream);
            } else if (c.equalsIgnoreCase(str)) {
                if (str2 != null) {
                    return new CpioArchiveOutputStream(outputStream, str2);
                }
                return new CpioArchiveOutputStream(outputStream);
            } else if (!h.equalsIgnoreCase(str)) {
                ArchiveStreamProvider archiveStreamProvider = (ArchiveStreamProvider) e().get(b(str));
                if (archiveStreamProvider != null) {
                    return archiveStreamProvider.a(str, outputStream, str2);
                }
                throw new ArchiveException("Archiver: " + str + " not found.");
            } else {
                throw new StreamingNotSupportedException(h);
            }
        }
    }

    public ArchiveInputStream a(InputStream inputStream) throws ArchiveException {
        if (inputStream == null) {
            throw new IllegalArgumentException("Stream must not be null.");
        } else if (inputStream.markSupported()) {
            byte[] bArr = new byte[12];
            inputStream.mark(bArr.length);
            try {
                int a2 = IOUtils.a(inputStream, bArr);
                inputStream.reset();
                if (ZipArchiveInputStream.b(bArr, a2)) {
                    return a(g, inputStream);
                }
                if (JarArchiveInputStream.a(bArr, a2)) {
                    return a("jar", inputStream);
                }
                if (ArArchiveInputStream.a(bArr, a2)) {
                    return a("ar", inputStream);
                }
                if (CpioArchiveInputStream.a(bArr, a2)) {
                    return a(c, inputStream);
                }
                if (ArjArchiveInputStream.a(bArr, a2)) {
                    return a(b, inputStream);
                }
                if (!SevenZFile.a(bArr, a2)) {
                    byte[] bArr2 = new byte[32];
                    inputStream.mark(bArr2.length);
                    int a3 = IOUtils.a(inputStream, bArr2);
                    inputStream.reset();
                    if (DumpArchiveInputStream.a(bArr2, a3)) {
                        return a(d, inputStream);
                    }
                    byte[] bArr3 = new byte[512];
                    inputStream.mark(bArr3.length);
                    int a4 = IOUtils.a(inputStream, bArr3);
                    inputStream.reset();
                    if (TarArchiveInputStream.a(bArr3, a4)) {
                        return a(f, inputStream);
                    }
                    if (a4 >= 512) {
                        TarArchiveInputStream tarArchiveInputStream = null;
                        try {
                            TarArchiveInputStream tarArchiveInputStream2 = new TarArchiveInputStream(new ByteArrayInputStream(bArr3));
                            try {
                                if (tarArchiveInputStream2.e().j()) {
                                    ArchiveInputStream a5 = a(f, inputStream);
                                    IOUtils.a((Closeable) tarArchiveInputStream2);
                                    return a5;
                                }
                                IOUtils.a((Closeable) tarArchiveInputStream2);
                            } catch (Exception unused) {
                                tarArchiveInputStream = tarArchiveInputStream2;
                                IOUtils.a((Closeable) tarArchiveInputStream);
                                throw new ArchiveException("No Archiver found for the stream signature");
                            } catch (Throwable th) {
                                th = th;
                                tarArchiveInputStream = tarArchiveInputStream2;
                                IOUtils.a((Closeable) tarArchiveInputStream);
                                throw th;
                            }
                        } catch (Exception unused2) {
                            IOUtils.a((Closeable) tarArchiveInputStream);
                            throw new ArchiveException("No Archiver found for the stream signature");
                        } catch (Throwable th2) {
                            th = th2;
                            IOUtils.a((Closeable) tarArchiveInputStream);
                            throw th;
                        }
                    }
                    throw new ArchiveException("No Archiver found for the stream signature");
                }
                throw new StreamingNotSupportedException(h);
            } catch (IOException e2) {
                throw new ArchiveException("Could not use reset and mark operations.", e2);
            }
        } else {
            throw new IllegalArgumentException("Mark is not supported.");
        }
    }

    public SortedMap<String, ArchiveStreamProvider> d() {
        if (this.o == null) {
            this.o = Collections.unmodifiableSortedMap(a());
        }
        return this.o;
    }

    public SortedMap<String, ArchiveStreamProvider> e() {
        if (this.p == null) {
            this.p = Collections.unmodifiableSortedMap(b());
        }
        return this.p;
    }

    public Set<String> f() {
        return Sets.a("ar", b, g, f, "jar", c, d, h);
    }

    public Set<String> g() {
        return Sets.a("ar", g, f, "jar", c, h);
    }
}
