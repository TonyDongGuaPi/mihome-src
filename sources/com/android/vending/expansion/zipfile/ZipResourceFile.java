package com.android.vending.expansion.zipfile;

import android.content.res.AssetFileDescriptor;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import cn.com.fmsh.communication.core.MessageHead;
import com.google.android.exoplayer2.C;
import com.taobao.weex.el.parse.Operators;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipResourceFile {
    static final int A = 10000;

    /* renamed from: a  reason: collision with root package name */
    static final String f4757a = "zipro";
    static final boolean b = false;
    static final int c = 101010256;
    static final int d = 22;
    static final int e = 8;
    static final int f = 12;
    static final int g = 16;
    static final int h = 65535;
    static final int i = 65557;
    static final int j = 67324752;
    static final int k = 30;
    static final int l = 26;
    static final int m = 28;
    static final int n = 33639248;
    static final int o = 46;
    static final int p = 10;
    static final int q = 12;
    static final int r = 16;
    static final int s = 20;
    static final int t = 24;
    static final int u = 28;
    static final int v = 30;
    static final int w = 32;
    static final int x = 42;
    static final int y = 0;
    static final int z = 8;
    public HashMap<File, ZipFile> B = new HashMap<>();
    ByteBuffer C = ByteBuffer.allocate(4);
    private HashMap<String, ZipEntryRO> D = new HashMap<>();

    private static int a(int i2) {
        return ((i2 & 255) << 24) + ((65280 & i2) << 8) + ((16711680 & i2) >>> 8) + ((i2 >>> 24) & 255);
    }

    private static int a(short s2) {
        return ((s2 & 65280) >>> 8) | ((s2 & 255) << 8);
    }

    public static final class ZipEntryRO {

        /* renamed from: a  reason: collision with root package name */
        public final File f4758a;
        public final String b;
        public final String c;
        public long d;
        public int e;
        public long f;
        public long g;
        public long h;
        public long i;
        public long j = -1;

        public ZipEntryRO(String str, File file, String str2) {
            this.b = str2;
            this.c = str;
            this.f4758a = file;
        }

        public void a(RandomAccessFile randomAccessFile, ByteBuffer byteBuffer) throws IOException {
            long j2 = this.d;
            try {
                randomAccessFile.seek(j2);
                randomAccessFile.readFully(byteBuffer.array());
                if (byteBuffer.getInt(0) == ZipResourceFile.j) {
                    this.j = j2 + 30 + ((long) (byteBuffer.getShort(26) & 65535)) + ((long) (byteBuffer.getShort(28) & 65535));
                } else {
                    Log.w(ZipResourceFile.f4757a, "didn't find signature at start of lfh");
                    throw new IOException();
                }
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }

        public long a() {
            return this.j;
        }

        public boolean b() {
            return this.e == 0;
        }

        public AssetFileDescriptor c() {
            if (this.e != 0) {
                return null;
            }
            try {
                return new AssetFileDescriptor(ParcelFileDescriptor.open(this.f4758a, C.ENCODING_PCM_MU_LAW), a(), this.i);
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public String d() {
            return this.c;
        }

        public File e() {
            return this.f4758a;
        }
    }

    public ZipResourceFile(String str) throws IOException {
        d(str);
    }

    /* access modifiers changed from: package-private */
    public ZipEntryRO[] a(String str) {
        Vector vector = new Vector();
        Collection<ZipEntryRO> values = this.D.values();
        if (str == null) {
            str = "";
        }
        int length = str.length();
        for (ZipEntryRO next : values) {
            if (next.b.startsWith(str) && -1 == next.b.indexOf(47, length)) {
                vector.add(next);
            }
        }
        return (ZipEntryRO[]) vector.toArray(new ZipEntryRO[vector.size()]);
    }

    public ZipEntryRO[] a() {
        Collection<ZipEntryRO> values = this.D.values();
        return (ZipEntryRO[]) values.toArray(new ZipEntryRO[values.size()]);
    }

    public AssetFileDescriptor b(String str) {
        ZipEntryRO zipEntryRO = this.D.get(str);
        if (zipEntryRO != null) {
            return zipEntryRO.c();
        }
        return null;
    }

    public InputStream c(String str) throws IOException {
        ZipEntryRO zipEntryRO = this.D.get(str);
        if (zipEntryRO == null) {
            return null;
        }
        if (zipEntryRO.b()) {
            return zipEntryRO.c().createInputStream();
        }
        ZipFile zipFile = this.B.get(zipEntryRO.e());
        if (zipFile == null) {
            zipFile = new ZipFile(zipEntryRO.e(), 1);
            this.B.put(zipEntryRO.e(), zipFile);
        }
        ZipEntry entry = zipFile.getEntry(str);
        if (entry != null) {
            return zipFile.getInputStream(entry);
        }
        return null;
    }

    private static int a(RandomAccessFile randomAccessFile) throws EOFException, IOException {
        return a(randomAccessFile.readInt());
    }

    /* access modifiers changed from: package-private */
    public void d(String str) throws IOException {
        String str2 = str;
        File file = new File(str2);
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        long length = randomAccessFile.length();
        if (length >= 22) {
            long j2 = 65557;
            if (65557 > length) {
                j2 = length;
            }
            randomAccessFile.seek(0);
            int a2 = a(randomAccessFile);
            if (a2 == c) {
                Log.i(f4757a, "Found Zip archive, but it looks empty");
                throw new IOException();
            } else if (a2 == j) {
                randomAccessFile.seek(length - j2);
                ByteBuffer allocate = ByteBuffer.allocate((int) j2);
                byte[] array = allocate.array();
                randomAccessFile.readFully(array);
                allocate.order(ByteOrder.LITTLE_ENDIAN);
                int length2 = array.length - 22;
                while (length2 >= 0 && (array[length2] != 80 || allocate.getInt(length2) != c)) {
                    length2--;
                }
                if (length2 < 0) {
                    Log.d(f4757a, "Zip: EOCD not found, " + str2 + " is not zip");
                }
                short s2 = allocate.getShort(length2 + 8);
                long j3 = ((long) allocate.getInt(length2 + 12)) & MessageHead.SERIAL_MAK;
                long j4 = ((long) allocate.getInt(length2 + 16)) & MessageHead.SERIAL_MAK;
                if (j4 + j3 > length) {
                    Log.w(f4757a, "bad offsets (dir " + j4 + ", size " + j3 + ", eocd " + length2 + Operators.BRACKET_END_STR);
                    throw new IOException();
                } else if (s2 != 0) {
                    MappedByteBuffer map = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY, j4, j3);
                    map.order(ByteOrder.LITTLE_ENDIAN);
                    short s3 = 65535;
                    byte[] bArr = new byte[65535];
                    ByteBuffer allocate2 = ByteBuffer.allocate(30);
                    allocate2.order(ByteOrder.LITTLE_ENDIAN);
                    int i2 = 0;
                    int i3 = 0;
                    int i4 = 0;
                    while (i3 < s2) {
                        if (map.getInt(i4) == n) {
                            short s4 = map.getShort(i4 + 28) & s3;
                            short s5 = map.getShort(i4 + 30) & s3;
                            short s6 = map.getShort(i4 + 32) & s3;
                            map.position(i4 + 46);
                            map.get(bArr, i2, s4);
                            map.position(i2);
                            String str3 = new String(bArr, i2, s4);
                            ZipEntryRO zipEntryRO = new ZipEntryRO(str2, file, str3);
                            zipEntryRO.e = map.getShort(i4 + 10) & s3;
                            zipEntryRO.f = ((long) map.getInt(i4 + 12)) & MessageHead.SERIAL_MAK;
                            zipEntryRO.g = map.getLong(i4 + 16) & MessageHead.SERIAL_MAK;
                            zipEntryRO.h = map.getLong(i4 + 20) & MessageHead.SERIAL_MAK;
                            zipEntryRO.i = map.getLong(i4 + 24) & MessageHead.SERIAL_MAK;
                            zipEntryRO.d = ((long) map.getInt(i4 + 42)) & MessageHead.SERIAL_MAK;
                            allocate2.clear();
                            zipEntryRO.a(randomAccessFile, allocate2);
                            this.D.put(str3, zipEntryRO);
                            i4 += s4 + 46 + s5 + s6;
                            i3++;
                            bArr = bArr;
                            s3 = 65535;
                            i2 = 0;
                        } else {
                            Log.w(f4757a, "Missed a central dir sig (at " + i4 + Operators.BRACKET_END_STR);
                            throw new IOException();
                        }
                    }
                } else {
                    Log.w(f4757a, "empty archive?");
                    throw new IOException();
                }
            } else {
                Log.v(f4757a, "Not a Zip archive");
                throw new IOException();
            }
        } else {
            throw new IOException();
        }
    }
}
