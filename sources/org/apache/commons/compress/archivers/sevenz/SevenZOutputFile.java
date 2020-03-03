package org.apache.commons.compress.archivers.sevenz;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.utils.CountingOutputStream;

public class SevenZOutputFile implements Closeable {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final SeekableByteChannel f3235a;
    private final List<SevenZArchiveEntry> b;
    private int c;
    /* access modifiers changed from: private */
    public final CRC32 d;
    /* access modifiers changed from: private */
    public final CRC32 e;
    private long f;
    private boolean g;
    private CountingOutputStream h;
    private CountingOutputStream[] i;
    private Iterable<? extends SevenZMethodConfiguration> j;
    private final Map<SevenZArchiveEntry, long[]> k;

    static /* synthetic */ long a(SevenZOutputFile sevenZOutputFile, long j2) {
        long j3 = sevenZOutputFile.f + j2;
        sevenZOutputFile.f = j3;
        return j3;
    }

    static /* synthetic */ long d(SevenZOutputFile sevenZOutputFile) {
        long j2 = sevenZOutputFile.f;
        sevenZOutputFile.f = 1 + j2;
        return j2;
    }

    public SevenZOutputFile(File file) throws IOException {
        this(Files.newByteChannel(file.toPath(), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]));
    }

    public SevenZOutputFile(SeekableByteChannel seekableByteChannel) throws IOException {
        this.b = new ArrayList();
        this.c = 0;
        this.d = new CRC32();
        this.e = new CRC32();
        this.f = 0;
        this.g = false;
        this.j = Collections.singletonList(new SevenZMethodConfiguration(SevenZMethod.LZMA2));
        this.k = new HashMap();
        this.f3235a = seekableByteChannel;
        seekableByteChannel.position(32);
    }

    public void a(SevenZMethod sevenZMethod) {
        a((Iterable<? extends SevenZMethodConfiguration>) Collections.singletonList(new SevenZMethodConfiguration(sevenZMethod)));
    }

    public void a(Iterable<? extends SevenZMethodConfiguration> iterable) {
        this.j = b(iterable);
    }

    public void close() throws IOException {
        if (!this.g) {
            b();
        }
        this.f3235a.close();
    }

    public SevenZArchiveEntry a(File file, String str) throws IOException {
        SevenZArchiveEntry sevenZArchiveEntry = new SevenZArchiveEntry();
        sevenZArchiveEntry.b(file.isDirectory());
        sevenZArchiveEntry.a(str);
        sevenZArchiveEntry.b(new Date(file.lastModified()));
        return sevenZArchiveEntry;
    }

    public void a(ArchiveEntry archiveEntry) throws IOException {
        this.b.add((SevenZArchiveEntry) archiveEntry);
    }

    public void a() throws IOException {
        if (this.h != null) {
            this.h.flush();
            this.h.close();
        }
        SevenZArchiveEntry sevenZArchiveEntry = this.b.get(this.b.size() - 1);
        if (this.f > 0) {
            sevenZArchiveEntry.a(true);
            this.c++;
            sevenZArchiveEntry.f(this.h.a());
            sevenZArchiveEntry.g(this.f);
            sevenZArchiveEntry.d(this.d.getValue());
            sevenZArchiveEntry.e(this.e.getValue());
            sevenZArchiveEntry.h(true);
            if (this.i != null) {
                long[] jArr = new long[this.i.length];
                for (int i2 = 0; i2 < this.i.length; i2++) {
                    jArr[i2] = this.i[i2].a();
                }
                this.k.put(sevenZArchiveEntry, jArr);
            }
        } else {
            sevenZArchiveEntry.a(false);
            sevenZArchiveEntry.f(0);
            sevenZArchiveEntry.g(0);
            sevenZArchiveEntry.h(false);
        }
        this.h = null;
        this.i = null;
        this.d.reset();
        this.e.reset();
        this.f = 0;
    }

    public void a(int i2) throws IOException {
        c().write(i2);
    }

    public void a(byte[] bArr) throws IOException {
        a(bArr, 0, bArr.length);
    }

    public void a(byte[] bArr, int i2, int i3) throws IOException {
        if (i3 > 0) {
            c().write(bArr, i2, i3);
        }
    }

    public void b() throws IOException {
        if (!this.g) {
            this.g = true;
            long position = this.f3235a.position();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a((DataOutput) dataOutputStream);
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            this.f3235a.write(ByteBuffer.wrap(byteArray));
            CRC32 crc32 = new CRC32();
            crc32.update(byteArray);
            ByteBuffer order = ByteBuffer.allocate(SevenZFile.b.length + 2 + 4 + 8 + 8 + 4).order(ByteOrder.LITTLE_ENDIAN);
            this.f3235a.position(0);
            order.put(SevenZFile.b);
            order.put((byte) 0).put((byte) 2);
            order.putInt(0);
            order.putLong(position - 32).putLong(((long) byteArray.length) & MessageHead.SERIAL_MAK).putInt((int) crc32.getValue());
            crc32.reset();
            crc32.update(order.array(), SevenZFile.b.length + 6, 20);
            order.putInt(SevenZFile.b.length + 2, (int) crc32.getValue());
            order.flip();
            this.f3235a.write(order);
            return;
        }
        throw new IOException("This archive has already been finished");
    }

    private OutputStream c() throws IOException {
        if (this.h == null) {
            this.h = d();
        }
        return this.h;
    }

    private CountingOutputStream d() throws IOException {
        if (!this.b.isEmpty()) {
            CountingOutputStream outputStreamWrapper = new OutputStreamWrapper();
            ArrayList arrayList = new ArrayList();
            boolean z = true;
            for (SevenZMethodConfiguration sevenZMethodConfiguration : a(this.b.get(this.b.size() - 1))) {
                if (!z) {
                    CountingOutputStream countingOutputStream = new CountingOutputStream(outputStreamWrapper);
                    arrayList.add(countingOutputStream);
                    outputStreamWrapper = countingOutputStream;
                }
                outputStreamWrapper = Coders.a(outputStreamWrapper, sevenZMethodConfiguration.a(), sevenZMethodConfiguration.b());
                z = false;
            }
            if (!arrayList.isEmpty()) {
                this.i = (CountingOutputStream[]) arrayList.toArray(new CountingOutputStream[arrayList.size()]);
            }
            return new CountingOutputStream(outputStreamWrapper) {
                public void write(int i) throws IOException {
                    super.write(i);
                    SevenZOutputFile.this.d.update(i);
                }

                public void write(byte[] bArr) throws IOException {
                    super.write(bArr);
                    SevenZOutputFile.this.d.update(bArr);
                }

                public void write(byte[] bArr, int i, int i2) throws IOException {
                    super.write(bArr, i, i2);
                    SevenZOutputFile.this.d.update(bArr, i, i2);
                }
            };
        }
        throw new IllegalStateException("No current 7z entry");
    }

    private Iterable<? extends SevenZMethodConfiguration> a(SevenZArchiveEntry sevenZArchiveEntry) {
        Iterable<? extends SevenZMethodConfiguration> q = sevenZArchiveEntry.q();
        return q == null ? this.j : q;
    }

    private void a(DataOutput dataOutput) throws IOException {
        dataOutput.write(1);
        dataOutput.write(4);
        b(dataOutput);
        f(dataOutput);
        dataOutput.write(0);
    }

    private void b(DataOutput dataOutput) throws IOException {
        if (this.c > 0) {
            c(dataOutput);
            d(dataOutput);
        }
        e(dataOutput);
        dataOutput.write(0);
    }

    private void c(DataOutput dataOutput) throws IOException {
        dataOutput.write(6);
        a(dataOutput, 0);
        a(dataOutput, ((long) this.c) & MessageHead.SERIAL_MAK);
        dataOutput.write(9);
        for (SevenZArchiveEntry next : this.b) {
            if (next.b()) {
                a(dataOutput, next.p());
            }
        }
        dataOutput.write(10);
        dataOutput.write(1);
        for (SevenZArchiveEntry next2 : this.b) {
            if (next2.b()) {
                dataOutput.writeInt(Integer.reverseBytes((int) next2.o()));
            }
        }
        dataOutput.write(0);
    }

    private void d(DataOutput dataOutput) throws IOException {
        dataOutput.write(7);
        dataOutput.write(11);
        a(dataOutput, (long) this.c);
        dataOutput.write(0);
        for (SevenZArchiveEntry next : this.b) {
            if (next.b()) {
                a(dataOutput, next);
            }
        }
        dataOutput.write(12);
        for (SevenZArchiveEntry next2 : this.b) {
            if (next2.b()) {
                long[] jArr = this.k.get(next2);
                if (jArr != null) {
                    for (long a2 : jArr) {
                        a(dataOutput, a2);
                    }
                }
                a(dataOutput, next2.getSize());
            }
        }
        dataOutput.write(10);
        dataOutput.write(1);
        for (SevenZArchiveEntry next3 : this.b) {
            if (next3.b()) {
                dataOutput.writeInt(Integer.reverseBytes((int) next3.m()));
            }
        }
        dataOutput.write(0);
    }

    private void a(DataOutput dataOutput, SevenZArchiveEntry sevenZArchiveEntry) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        for (SevenZMethodConfiguration a2 : a(sevenZArchiveEntry)) {
            i2++;
            a(a2, (OutputStream) byteArrayOutputStream);
        }
        a(dataOutput, (long) i2);
        dataOutput.write(byteArrayOutputStream.toByteArray());
        long j2 = 0;
        while (j2 < ((long) (i2 - 1))) {
            long j3 = 1 + j2;
            a(dataOutput, j3);
            a(dataOutput, j2);
            j2 = j3;
        }
    }

    private void a(SevenZMethodConfiguration sevenZMethodConfiguration, OutputStream outputStream) throws IOException {
        byte[] id = sevenZMethodConfiguration.a().getId();
        byte[] b2 = Coders.a(sevenZMethodConfiguration.a()).b(sevenZMethodConfiguration.b());
        int length = id.length;
        if (b2.length > 0) {
            length |= 32;
        }
        outputStream.write(length);
        outputStream.write(id);
        if (b2.length > 0) {
            outputStream.write(b2.length);
            outputStream.write(b2);
        }
    }

    private void e(DataOutput dataOutput) throws IOException {
        dataOutput.write(8);
        dataOutput.write(0);
    }

    private void f(DataOutput dataOutput) throws IOException {
        dataOutput.write(5);
        a(dataOutput, (long) this.b.size());
        g(dataOutput);
        h(dataOutput);
        i(dataOutput);
        j(dataOutput);
        k(dataOutput);
        l(dataOutput);
        m(dataOutput);
        n(dataOutput);
        dataOutput.write(0);
    }

    private void g(DataOutput dataOutput) throws IOException {
        int i2;
        boolean z;
        Iterator<SevenZArchiveEntry> it = this.b.iterator();
        while (true) {
            if (it.hasNext()) {
                if (!it.next().b()) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (z) {
            dataOutput.write(14);
            BitSet bitSet = new BitSet(this.b.size());
            for (i2 = 0; i2 < this.b.size(); i2++) {
                bitSet.set(i2, !this.b.get(i2).b());
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a((DataOutput) dataOutputStream, bitSet, this.b.size());
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void h(DataOutput dataOutput) throws IOException {
        boolean z = false;
        BitSet bitSet = new BitSet(0);
        int i2 = 0;
        for (SevenZArchiveEntry next : this.b) {
            if (!next.b()) {
                boolean isDirectory = next.isDirectory();
                bitSet.set(i2, !isDirectory);
                z |= !isDirectory;
                i2++;
            }
        }
        if (z) {
            dataOutput.write(15);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a((DataOutput) dataOutputStream, bitSet, i2);
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void i(DataOutput dataOutput) throws IOException {
        boolean z = false;
        BitSet bitSet = new BitSet(0);
        int i2 = 0;
        for (SevenZArchiveEntry next : this.b) {
            if (!next.b()) {
                boolean c2 = next.c();
                bitSet.set(i2, c2);
                z |= c2;
                i2++;
            }
        }
        if (z) {
            dataOutput.write(16);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            a((DataOutput) dataOutputStream, bitSet, i2);
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void j(DataOutput dataOutput) throws IOException {
        dataOutput.write(17);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.write(0);
        for (SevenZArchiveEntry name : this.b) {
            dataOutputStream.write(name.getName().getBytes("UTF-16LE"));
            dataOutputStream.writeShort(0);
        }
        dataOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        a(dataOutput, (long) byteArray.length);
        dataOutput.write(byteArray);
    }

    private void k(DataOutput dataOutput) throws IOException {
        int i2 = 0;
        for (SevenZArchiveEntry d2 : this.b) {
            if (d2.d()) {
                i2++;
            }
        }
        if (i2 > 0) {
            dataOutput.write(18);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i2 != this.b.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.b.size());
                for (int i3 = 0; i3 < this.b.size(); i3++) {
                    bitSet.set(i3, this.b.get(i3).d());
                }
                a((DataOutput) dataOutputStream, bitSet, this.b.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry next : this.b) {
                if (next.d()) {
                    dataOutputStream.writeLong(Long.reverseBytes(SevenZArchiveEntry.d(next.e())));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void l(DataOutput dataOutput) throws IOException {
        int i2 = 0;
        for (SevenZArchiveEntry g2 : this.b) {
            if (g2.g()) {
                i2++;
            }
        }
        if (i2 > 0) {
            dataOutput.write(19);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i2 != this.b.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.b.size());
                for (int i3 = 0; i3 < this.b.size(); i3++) {
                    bitSet.set(i3, this.b.get(i3).g());
                }
                a((DataOutput) dataOutputStream, bitSet, this.b.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry next : this.b) {
                if (next.g()) {
                    dataOutputStream.writeLong(Long.reverseBytes(SevenZArchiveEntry.d(next.h())));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void m(DataOutput dataOutput) throws IOException {
        int i2 = 0;
        for (SevenZArchiveEntry f2 : this.b) {
            if (f2.f()) {
                i2++;
            }
        }
        if (i2 > 0) {
            dataOutput.write(20);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i2 != this.b.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.b.size());
                for (int i3 = 0; i3 < this.b.size(); i3++) {
                    bitSet.set(i3, this.b.get(i3).f());
                }
                a((DataOutput) dataOutputStream, bitSet, this.b.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry next : this.b) {
                if (next.f()) {
                    dataOutputStream.writeLong(Long.reverseBytes(SevenZArchiveEntry.d(next.a())));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void n(DataOutput dataOutput) throws IOException {
        int i2 = 0;
        for (SevenZArchiveEntry i3 : this.b) {
            if (i3.i()) {
                i2++;
            }
        }
        if (i2 > 0) {
            dataOutput.write(21);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            if (i2 != this.b.size()) {
                dataOutputStream.write(0);
                BitSet bitSet = new BitSet(this.b.size());
                for (int i4 = 0; i4 < this.b.size(); i4++) {
                    bitSet.set(i4, this.b.get(i4).i());
                }
                a((DataOutput) dataOutputStream, bitSet, this.b.size());
            } else {
                dataOutputStream.write(1);
            }
            dataOutputStream.write(0);
            for (SevenZArchiveEntry next : this.b) {
                if (next.i()) {
                    dataOutputStream.writeInt(Integer.reverseBytes(next.j()));
                }
            }
            dataOutputStream.flush();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            a(dataOutput, (long) byteArray.length);
            dataOutput.write(byteArray);
        }
    }

    private void a(DataOutput dataOutput, long j2) throws IOException {
        int i2 = 0;
        int i3 = 0;
        int i4 = 128;
        while (true) {
            if (i2 >= 8) {
                break;
            }
            int i5 = i2 + 1;
            if (j2 < (1 << (i5 * 7))) {
                i3 = (int) (((long) i3) | (j2 >>> (i2 * 8)));
                break;
            }
            i3 |= i4;
            i4 >>>= 1;
            i2 = i5;
        }
        dataOutput.write(i3);
        while (i2 > 0) {
            dataOutput.write((int) (255 & j2));
            j2 >>>= 8;
            i2--;
        }
    }

    private void a(DataOutput dataOutput, BitSet bitSet, int i2) throws IOException {
        int i3 = 0;
        int i4 = 7;
        for (int i5 = 0; i5 < i2; i5++) {
            i3 |= (bitSet.get(i5) ? 1 : 0) << i4;
            i4--;
            if (i4 < 0) {
                dataOutput.write(i3);
                i3 = 0;
                i4 = 7;
            }
        }
        if (i4 != 7) {
            dataOutput.write(i3);
        }
    }

    private static <T> Iterable<T> b(Iterable<T> iterable) {
        LinkedList linkedList = new LinkedList();
        for (T addFirst : iterable) {
            linkedList.addFirst(addFirst);
        }
        return linkedList;
    }

    private class OutputStreamWrapper extends OutputStream {
        private static final int b = 8192;
        private final ByteBuffer c;

        public void close() throws IOException {
        }

        public void flush() throws IOException {
        }

        private OutputStreamWrapper() {
            this.c = ByteBuffer.allocate(8192);
        }

        public void write(int i) throws IOException {
            this.c.clear();
            this.c.put((byte) i).flip();
            SevenZOutputFile.this.f3235a.write(this.c);
            SevenZOutputFile.this.e.update(i);
            SevenZOutputFile.d(SevenZOutputFile.this);
        }

        public void write(byte[] bArr) throws IOException {
            write(bArr, 0, bArr.length);
        }

        public void write(byte[] bArr, int i, int i2) throws IOException {
            if (i2 > 8192) {
                SevenZOutputFile.this.f3235a.write(ByteBuffer.wrap(bArr, i, i2));
            } else {
                this.c.clear();
                this.c.put(bArr, i, i2).flip();
                SevenZOutputFile.this.f3235a.write(this.c);
            }
            SevenZOutputFile.this.e.update(bArr, i, i2);
            SevenZOutputFile.a(SevenZOutputFile.this, (long) i2);
        }
    }
}
