package org.apache.commons.compress.archivers.ar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.utils.ArchiveUtils;

public class ArArchiveOutputStream extends ArchiveOutputStream {
    public static final int b = 0;
    public static final int c = 1;
    private final OutputStream d;
    private long e = 0;
    private ArArchiveEntry f;
    private boolean g = false;
    private int h = 0;
    private boolean i = false;

    public ArArchiveOutputStream(OutputStream outputStream) {
        this.d = outputStream;
    }

    public void b(int i2) {
        this.h = i2;
    }

    private long e() throws IOException {
        byte[] a2 = ArchiveUtils.a(ArArchiveEntry.b);
        this.d.write(a2);
        return (long) a2.length;
    }

    public void a() throws IOException {
        if (this.i) {
            throw new IOException("Stream has already been finished");
        } else if (this.f == null || !this.g) {
            throw new IOException("No current entry to close");
        } else {
            if (this.e % 2 != 0) {
                this.d.write(10);
            }
            this.g = false;
        }
    }

    public void a(ArchiveEntry archiveEntry) throws IOException {
        if (!this.i) {
            ArArchiveEntry arArchiveEntry = (ArArchiveEntry) archiveEntry;
            if (this.f == null) {
                e();
            } else if (this.f.f() != this.e) {
                throw new IOException("length does not match entry (" + this.f.f() + " != " + this.e);
            } else if (this.g) {
                a();
            }
            this.f = arArchiveEntry;
            a(arArchiveEntry);
            this.e = 0;
            this.g = true;
            return;
        }
        throw new IOException("Stream has already been finished");
    }

    private long a(long j, long j2, char c2) throws IOException {
        long j3 = j2 - j;
        if (j3 > 0) {
            for (int i2 = 0; ((long) i2) < j3; i2++) {
                write(c2);
            }
        }
        return j2;
    }

    private long a(String str) throws IOException {
        byte[] bytes = str.getBytes("ascii");
        write(bytes);
        return (long) bytes.length;
    }

    private long a(ArArchiveEntry arArchiveEntry) throws IOException {
        long j;
        String name = arArchiveEntry.getName();
        if (this.h != 0 || name.length() <= 16) {
            boolean z = true;
            int i2 = 0;
            if (1 != this.h || (name.length() <= 16 && !name.contains(" "))) {
                j = a(name) + 0;
                z = false;
            } else {
                j = a("#1/" + String.valueOf(name.length())) + 0;
            }
            long a2 = a(j, 16, ' ');
            String str = "" + arArchiveEntry.e();
            if (str.length() <= 12) {
                long a3 = a(a2 + a(str), 28, ' ');
                String str2 = "" + arArchiveEntry.b();
                if (str2.length() <= 6) {
                    long a4 = a(a3 + a(str2), 34, ' ');
                    String str3 = "" + arArchiveEntry.c();
                    if (str3.length() <= 6) {
                        long a5 = a(a4 + a(str3), 40, ' ');
                        String str4 = "" + Integer.toString(arArchiveEntry.d(), 8);
                        if (str4.length() <= 8) {
                            long a6 = a(a5 + a(str4), 48, ' ');
                            long f2 = arArchiveEntry.f();
                            if (z) {
                                i2 = name.length();
                            }
                            String valueOf = String.valueOf(f2 + ((long) i2));
                            if (valueOf.length() <= 10) {
                                long a7 = a(a6 + a(valueOf), 58, ' ') + a(ArArchiveEntry.c);
                                return z ? a7 + a(name) : a7;
                            }
                            throw new IOException("size too long");
                        }
                        throw new IOException("filemode too long");
                    }
                    throw new IOException("groupid too long");
                }
                throw new IOException("userid too long");
            }
            throw new IOException("modified too long");
        }
        throw new IOException("filename too long, > 16 chars: " + name);
    }

    public void write(byte[] bArr, int i2, int i3) throws IOException {
        this.d.write(bArr, i2, i3);
        a(i3);
        this.e += (long) i3;
    }

    public void close() throws IOException {
        if (!this.i) {
            b();
        }
        this.d.close();
        this.f = null;
    }

    public ArchiveEntry a(File file, String str) throws IOException {
        if (!this.i) {
            return new ArArchiveEntry(file, str);
        }
        throw new IOException("Stream has already been finished");
    }

    public void b() throws IOException {
        if (this.g) {
            throw new IOException("This archive contains unclosed entries.");
        } else if (!this.i) {
            this.i = true;
        } else {
            throw new IOException("This archive has already been finished");
        }
    }
}
