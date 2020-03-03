package com.ximalaya.ting.android.player;

import com.taobao.weex.el.parse.Operators;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

public class AudioFile {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2266a = "dl_mp3";
    private String b = null;
    private String c;
    private String d;
    private FileDesc e;
    private ByteBuffer f;

    public static AudioFile a(String str, String str2) throws FileNotFoundException, IOException {
        return new AudioFile(str, str2);
    }

    private AudioFile(String str, String str2) throws FileNotFoundException, IOException {
        Logger.a(f2266a, (Object) "======================AudioFile Constructor()");
        this.c = str;
        this.d = MD5.b(str2);
        this.b = str2;
        this.e = new FileDesc(str, str2);
    }

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.c + "/" + this.d;
    }

    private synchronized int b(int i, byte[] bArr, int i2, int i3) throws IOException {
        int read;
        RandomAccessFile randomAccessFile = new RandomAccessFile(this.c + "/" + this.d + ".chunk", "r");
        Logger.a(f2266a, (Object) "======================readChunkData0(" + i + ":" + randomAccessFile.length() + Operators.BRACKET_END_STR);
        randomAccessFile.seek((long) i);
        read = randomAccessFile.read(bArr, i2, i3);
        randomAccessFile.close();
        return read;
    }

    public synchronized int a(int i, int i2, byte[] bArr, int i3) throws IOException {
        if (!this.e.c.get(i)) {
            Logger.a(f2266a, (Object) "fileInfo.chunkExist.get(" + i + ")false");
            return -1;
        }
        int b2 = b(this.e.d.get(i).intValue() * i2, bArr, i3, i2);
        Logger.a(f2266a, (Object) "======================readChunkData(" + i + ":" + b2 + Operators.BRACKET_END_STR);
        return b2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0040, code lost:
        return -1;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized int a(byte[] r4, int r5, int r6) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r1.<init>()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r2 = r3.c     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r1.append(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r2 = r3.d     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r1.append(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r2 = ".chunk"
            r1.append(r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            java.lang.String r2 = "rw"
            r0.<init>(r1, r2)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            com.ximalaya.ting.android.player.FileDesc r1 = r3.e     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            int r1 = r1.g()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r2 = 65536(0x10000, float:9.18355E-41)
            int r1 = r1 * r2
            long r1 = (long) r1     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r0.seek(r1)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r0.write(r4, r5, r6)     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            r0.close()     // Catch:{ IOException -> 0x003e, all -> 0x003b }
            monitor-exit(r3)
            return r6
        L_0x003b:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        L_0x003e:
            r4 = -1
            monitor-exit(r3)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioFile.a(byte[], int, int):int");
    }

    public synchronized void a(int i) {
        this.e.f2271a = false;
        this.e.b = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r2, byte[] r3, int r4, int r5) {
        /*
            r1 = this;
            monitor-enter(r1)
            com.ximalaya.ting.android.player.FileDesc r0 = r1.e     // Catch:{ all -> 0x0018 }
            boolean r0 = r0.a(r2)     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)
            return
        L_0x000b:
            int r3 = r1.a(r3, r4, r5)     // Catch:{ all -> 0x0018 }
            if (r3 <= 0) goto L_0x0016
            com.ximalaya.ting.android.player.FileDesc r3 = r1.e     // Catch:{ all -> 0x0018 }
            r3.c(r2)     // Catch:{ all -> 0x0018 }
        L_0x0016:
            monitor-exit(r1)
            return
        L_0x0018:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ximalaya.ting.android.player.AudioFile.a(int, byte[], int, int):void");
    }

    public synchronized FileDesc d() {
        return this.e;
    }

    public String e() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public ByteBuffer f() {
        return this.f;
    }

    public void a(ByteBuffer byteBuffer) {
        this.f = byteBuffer;
    }

    public final synchronized boolean b(int i) {
        if (this.e.a(i)) {
            return true;
        }
        return false;
    }
}
